package org.jmc;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.awt.image.ImagingOpException;
import java.awt.image.WritableRaster;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import javax.imageio.ImageIO;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathFactory;

import org.jmc.util.Filesystem;
import org.jmc.util.Log;
import org.jmc.util.Xml;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 * Utility class that can extract the individual textures from minecraft texture
 * packs.
 */
public class TextureExporter {
	private static final int FORMAT_1_6 = 3; // 1.6 resource pack
	private static final int FORMAT_1_5 = 2; // 1.5 texture pack
	private static final int FORMAT_PRE_1_5 = 1; // pre-1.5 texture pack
	private static final int FORMAT_INVALID = 0; // unrecognized format

	/**
	 * Holds a single texture
	 */
	private static class Texture {
		public BufferedImage image;
		public String name;
		public boolean repeating;
		public boolean luma;

		public Texture(String name, BufferedImage img, boolean repeating, boolean luma) {
			this.repeating = repeating;
			this.name = name;
			this.image = img;
			this.luma = luma;
		}
	}

	private static BufferedImage loadImageFromFile(File file) throws IOException {
		return ImageIO.read(file);
	}
	
	private static BufferedImage overlayImg (BufferedImage imgBase, BufferedImage imgOverlay)
	{
		
		BufferedImage foreground = imgOverlay;
	    BufferedImage background = imgBase;
	    BufferedImage combinedImg = new BufferedImage(background.getWidth(), background.getHeight(), BufferedImage.TYPE_INT_ARGB);

	    Graphics2D g = combinedImg.createGraphics();
	    g.drawImage(background, 0, 0, null);
	    g.drawImage(foreground, 0, 0, null);

	    try {
			ImageIO.write(combinedImg, "PNG", new File("masked.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	    g.dispose();
		
		return combinedImg;
	}
	
	private static BufferedImage loadImageFromZip(File zipfile, String imagePath) throws IOException {
		ZipInputStream zis = null;
		try {
			zis = new ZipInputStream(new FileInputStream(zipfile));

			ZipEntry entry = null;
			while ((entry = zis.getNextEntry()) != null)
				if (!entry.isDirectory() && entry.getName().equals(imagePath))
					break;

			if (entry == null)
				throw new IOException("Couldn't find " + imagePath + " in " + zipfile.getName());

			BufferedImage result = ImageIO.read(zis);
			return result;
		} finally {
			if (zis != null)
				zis.close();
		}
	}

	private static BufferedImage convertImageType(BufferedImage image) {
		int w = image.getWidth();
		int h = image.getHeight();
		BufferedImage ret = new BufferedImage(w, h, BufferedImage.TYPE_4BYTE_ABGR);

		int[] pixels = new int[w * h];
		image.getRGB(0, 0, w, h, pixels, 0, w);
		ret.setRGB(0, 0, w, h, pixels, 0, w);

		return ret;
	}

	private static void convertToAlpha(BufferedImage img) {
		int w = img.getWidth();
		int h = img.getHeight();
		int c = img.getColorModel().getPixelSize() / 8;

		if (c != 4)
			throw new ImagingOpException("Texture is not 32-bit!");

		int[] buffer = new int[w * h * c];

		WritableRaster raster = img.getRaster();
		raster.getPixels(0, 0, w, h, buffer);

		for (int i = 0; i < w * h; i++) {
			buffer[4 * i] = buffer[4 * i + 3];
			buffer[4 * i + 1] = buffer[4 * i + 3];
			buffer[4 * i + 2] = buffer[4 * i + 3];
			buffer[4 * i + 3] = 255;
		}

		raster.setPixels(0, 0, w, h, buffer);
	}

	private static void tintImage(BufferedImage img, Color tint) {
		int w = img.getWidth();
		int h = img.getHeight();
		int c = img.getColorModel().getPixelSize() / 8;

		if (c != 4)
			throw new ImagingOpException("Texture is not 32-bit!");

		int[] buffer = new int[w * h * c];

		WritableRaster raster = img.getRaster();
		raster.getPixels(0, 0, w, h, buffer);

		int r = tint.getRed();
		int g = tint.getGreen();
		int b = tint.getBlue();

		for (int i = 0; i < w * h; i++) {
			c = (buffer[4 * i] * r) >> 8;
			if (c > 255)
				c = 255;
			buffer[4 * i] = c;

			c = (buffer[4 * i + 1] * g) >> 8;
			if (c > 255)
				c = 255;
			buffer[4 * i + 1] = c;

			c = (buffer[4 * i + 2] * b) >> 8;
			if (c > 255)
				c = 255;
			buffer[4 * i + 2] = c;
		}

		raster.setPixels(0, 0, w, h, buffer);
	}

	private static BufferedImage scaleImage(BufferedImage img, double factor) {
		int w = img.getWidth();
		int h = img.getHeight();
		int new_w = (int) (w * factor);
		int new_h = (int) (h * factor);

		BufferedImage result = new BufferedImage(new_w, new_h, img.getType());
		for (int y = 0; y < new_h; y++) {
			for (int x = 0; x < new_w; x++) {
				int src = img.getRGB((int) (x / factor), (int) (y / factor));
				result.setRGB(x, y, src);
			}
		}

		return result;
	}

	/**
	 * Looks inside the given zip file to determine the format of the texture
	 * pack.
	 * 
	 * @param zipfile
	 * @return Constant indicating texture pack format.
	 * @throws IOException
	 */
	private static int detectTexturePackFormat(File zipfile) throws IOException {
		ZipInputStream zis = null;
		try {
			zis = new ZipInputStream(new FileInputStream(zipfile));

			boolean foundAssetsDir = false;
			boolean foundBlocksDir = false;
			boolean foundTerrainPng = false;

			ZipEntry entry = null;
			while ((entry = zis.getNextEntry()) != null) {
				String entryName = entry.getName();
				if (entryName.startsWith("assets/minecraft/"))
					foundAssetsDir = true;
				else if (entryName.startsWith("textures/blocks/"))
					foundBlocksDir = true;
				else if (entryName.equals("terrain.png"))
					foundTerrainPng = true;
			}

			return	foundAssetsDir ? FORMAT_1_6 : 
					foundBlocksDir ? FORMAT_1_5 : 
					foundTerrainPng ? FORMAT_PRE_1_5 :
					FORMAT_INVALID;
		} finally {
			if (zis != null)
				zis.close();
		}
	}

	/**
	 * Reads the configuration file "texsplit.conf". Private method to retrieve
	 * textures from a texturepack in a list. This was extracted into a separate
	 * method so it can be reused between the split and merge methods.
	 * 
	 * @param texturePack
	 * @param scale
	 * @param progress
	 * @return
	 * @throws Exception
	 */
//	private static List<Texture> getTextures(File texturePack, double scale, ProgressCallback progress)
	private static List<Texture> getTextures(File texturePack, double scale, ProgressCallback progress, boolean alphas, boolean merging, File destination)
			throws Exception {
		List<Texture> ret = new LinkedList<Texture>();
		Set<String> texnameset = new HashSet<String>();

		File zipfile;
		if (texturePack == null)
			zipfile = Filesystem.getMinecraftJar();
		else
			zipfile = texturePack;
		if (!zipfile.canRead())
			throw new Exception("Cannot open " + zipfile.getName());

		String confFilePath;
		switch (detectTexturePackFormat(zipfile)) {
		case FORMAT_1_6:
			Log.info("Found resource pack (Minecraft 1.6 or later)");
			confFilePath = "conf/texsplit_1.6.conf";
			break;
		case FORMAT_1_5:
			Log.info("Found texture pack (Minecraft 1.5)");
			confFilePath = "conf/texsplit_1.5.conf";
			break;
		case FORMAT_PRE_1_5:
			Log.info("Found texture pack (Minecraft 1.4 or earlier)");
			confFilePath = "conf/texsplit_old.conf";
			break;
		case FORMAT_INVALID:
		default:
			throw new Exception(zipfile.toString() + " does not appear to contain a Minecraft texture pack.");
		}

		File confFile = new File(Filesystem.getDatafilesDir(), confFilePath);
		if (!confFile.canRead())
			throw new Exception("Cannot open configuration file " + confFilePath);

		Document doc = Xml.loadDocument(confFile);
		XPath xpath = XPathFactory.newInstance().newXPath();

		NodeList fileNodes = (NodeList) xpath.evaluate("/texsplit/file", doc, XPathConstants.NODESET);
		for (int i = 0; i < fileNodes.getLength(); i++) {
			Node fileNode = fileNodes.item(i);
			String source = Xml.getAttribute(fileNode, "source", "texturepack");
			String fileName = Xml.getAttribute(fileNode, "name");
			String rows = Xml.getAttribute(fileNode, "rows", "1");
			String cols = Xml.getAttribute(fileNode, "cols", "1");

			if (fileName == null || fileName.length() == 0)
				throw new Exception("In " + confFilePath + ": 'file' tag is missing required attribute 'name'.");

			BufferedImage image;
			
			try {
				if (source.equalsIgnoreCase("texturepack"))
					image = loadImageFromZip(zipfile, fileName);
				else if (source.equalsIgnoreCase("distr"))
					image = loadImageFromFile(new File(Filesystem.getDatafilesDir(), fileName));
				else
					image = loadImageFromFile(new File(fileName));
			} catch (Exception e) {
				Log.info("Error loading image: " + e.getMessage());
				continue;
			}

			if (image.getType() != BufferedImage.TYPE_4BYTE_ABGR)
				image = convertImageType(image);

			int width = image.getWidth() / Integer.parseInt(cols, 10);
			int height = rows.equals("*") ? width : (image.getHeight() / Integer.parseInt(rows, 10));

			NodeList texNodes = (NodeList) xpath.evaluate("tex", fileNode, XPathConstants.NODESET);
			for (int j = 0; j < texNodes.getLength(); j++) {
				Node texNode = texNodes.item(j);
				String pos = Xml.getAttribute(texNode, "pos", "1,1");
				String texName = Xml.getAttribute(texNode, "name");
				String tint = Xml.getAttribute(texNode, "tint");
				boolean repeating = false;
				String repeating_str = Xml.getAttribute(texNode, "repeating");
				if (repeating_str != null)
					repeating = repeating_str.toLowerCase().equals("true");
				
				boolean luma = false;
				String luma_str = Xml.getAttribute(texNode, "luma");
				if (luma_str != null)
					luma = luma_str.toLowerCase().equals("true");
				
				if (texName == null)
					continue;

				if (texnameset.contains(texName)) {
					Log.info("Texture name " + texName + " was already used! Skipping!");
					continue;
				}

				texnameset.add(texName);

				String[] parts = pos.split("\\s*,\\s*");
				if (parts.length != 2)
					throw new Exception("In " + confFilePath + ": attribute 'pos' has invalid format.");
				int rowPos = Integer.parseInt(parts[0], 10) - 1;
				int colPos = Integer.parseInt(parts[1], 10) - 1;

				Log.info("Creating Buffer Image");
				BufferedImage texture = new BufferedImage(width, height, image.getType());
				
				
				image.getSubimage(colPos * width, rowPos * height, width, height).copyData(texture.getRaster());
				 
				if (tint != null && tint.length() > 0) {
					try {
						tintImage(texture, new Color(Integer.parseInt(tint, 16)));
					} catch (Exception e) {
						Log.info("Cannot tint image: " + texName + " (" + e.getMessage() + ")");
					}
				}

				if (scale != 1.0) {
					try {
						texture = scaleImage(texture, scale);
					} catch (Exception e) {
						Log.info("Cannot scale image: " + texName + " (" + e.getMessage() + ")");
					}
				}

				if(merging){
					ret.add(new Texture(texName, texture, repeating, luma));
				}
				else{
				
					Log.info("Creating Texture File");
					Texture texture2 = new Texture(texName, texture, repeating, luma);
					Log.info("Writing Texture File");
					ImageIO.write(texture2.image, "png", new File(destination, texture2.name + ".png"));
					
					try {
						Log.info("Trying normal map");
						BufferedImage imageN;
						
						Log.info("Searching for normal");
						if (source.equalsIgnoreCase("texturepack"))
							imageN = loadImageFromZip(zipfile, fileName.replace(".png", "_n.png"));
						else if (source.equalsIgnoreCase("distr"))
							imageN = loadImageFromFile(new File(Filesystem.getDatafilesDir(), fileName.replace(".png", "_n.png")));
						else
							imageN = loadImageFromFile(new File(fileName.replace(".png", "_n.png")));
						
						if (imageN.getType() != BufferedImage.TYPE_4BYTE_ABGR)
							imageN = convertImageType(imageN);
						
						Log.info("Found Normal. Creating Buffered Texture.");
						BufferedImage textureN = new BufferedImage(width, height, imageN.getType());
						imageN.getSubimage(colPos * width, rowPos * height, width, height).copyData(textureN.getRaster());
						if (scale != 1.0) {
							try {
								textureN = scaleImage(textureN, scale);
							} catch (Exception e) {
								Log.info("Cannot scale image: " + texName + "_n (" + e.getMessage() + ")");
							}
						}
						
						Log.info("Creating Texture.");
						Texture texture2N = new Texture(texName + "_n", textureN, repeating, false);
						Log.info("Writing Texture.");
						ImageIO.write(texture2N.image, "png", new File(destination, texture2N.name + ".png"));
						
					} catch (Exception e) {
						Log.info("Error loading image: " + e.getMessage());
						continue;
					}

					try {
						
						Log.info("Trying specular map");
						BufferedImage imageS;
						Log.info("Searching for spec");
						if (source.equalsIgnoreCase("texturepack"))
							imageS = loadImageFromZip(zipfile, fileName.replace(".png", "_s.png"));
						else if (source.equalsIgnoreCase("distr"))
							imageS = loadImageFromFile(new File(Filesystem.getDatafilesDir(), fileName.replace(".png", "_s.png")));
						else
							imageS = loadImageFromFile(new File(fileName.replace(".png", "_s.png")));
						
						if (imageS.getType() != BufferedImage.TYPE_4BYTE_ABGR)
							imageS = convertImageType(imageS);
						
						Log.info("Found Specular. Creating Buffered Texture.");
						BufferedImage textureS = new BufferedImage(width, height, imageS.getType());
						imageS.getSubimage(colPos * width, rowPos * height, width, height).copyData(textureS.getRaster());
						if (scale != 1.0) {
							try {
								textureS = scaleImage(textureS, scale);
							} catch (Exception e) {
								Log.info("Cannot scale image: " + texName + "_s (" + e.getMessage() + ")");
							}
						}
						Log.info("Creating Texture.");
						Texture texture2S = new Texture(texName + "_s", textureS, repeating, false);
						Log.info("Writing Texture.");
						ImageIO.write(texture2S.image, "png", new File(destination, texture2S.name + ".png"));
						
					} catch (Exception e) {
						Log.info("Error loading image: " + e.getMessage());
						continue;
					}
	
					if (alphas) {
						try {
							convertToAlpha(texture2.image);
							ImageIO.write(texture2.image, "png", new File(destination, texture2.name + "_a.png"));
						} catch (Exception e) {
							Log.info("Cannot save alpha for: " + texture2.name + " (" + e.getMessage() + ")");
						}
					}
					
				}

			}

			if (progress != null)
				progress.setProgress((i + 1) / (float) fileNodes.getLength());
		}
		
		if(merging)
			return ret;
		else
			return null;
	}

	/**
	 * Reads a Minecraft texture pack and splits the individual block textures
	 * into .png images.
	 * 
	 * @param destination
	 *            Directory to place the output files.
	 * @param texturePack
	 *            A Minecraft texture pack file. If null, will use minecraft's
	 *            default textures.
	 * @param scale
	 *            Scaling to apply to textures.
	 * @param alphas
	 *            Whether to export separate alpha masks.
	 * @param progress
	 *            If not null, the exporter will invoke this callback to inform
	 *            on the operation's progress.
	 * @throws Exception
	 *             if there is an error.
	 */
	public static void splitTextures(File destination, File texturePack, double scale, boolean alphas,
			ProgressCallback progress) throws Exception {
		if (destination == null)
			throw new IllegalArgumentException("destination cannot be null");

		Log.info("Exporting textures to \"" + destination + "\"");
		if (!destination.exists() || !destination.isDirectory()) {
			if (destination.exists())
				throw new RuntimeException("Cannot create texture directory! File is in the way!");
			if (!destination.mkdir())
				throw new RuntimeException("Cannot create texture directory!");
		}

/*		List<Texture> textures = */getTextures(texturePack, scale, progress, alphas, false, destination);

//		float texnum = textures.size();
//		float count = 0;
//
//		for (Texture texture : textures) {
//
//			ImageIO.write(texture.image, "png", new File(destination, texture.name + ".png"));
//
//			if (alphas) {
//				try {
//					convertToAlpha(texture.image);
//					ImageIO.write(texture.image, "png", new File(destination, texture.name + "_a.png"));
//				} catch (Exception e) {
//					Log.info("Cannot save alpha for: " + texture.name + " (" + e.getMessage() + ")");
//				}
//			}
//
//			if (progress != null)
//				progress.setProgress((++count) / texnum);
//		}
	}

	/**
	 * Reads a Minecraft texture pack and splits the individual block textures
	 * into separate images then merges them into a single file containing all
	 * the textures.
	 * 
	 * @param destination
	 *            Directory to place the output files. They will be called
	 *            "texture.png" and "texture_a.png"
	 * @param texturePack
	 *            A Minecraft texture pack file. If null, will use minecraft's
	 *            default textures.
	 * @param scale
	 *            Scaling to apply to textures.
	 * @param alphas
	 *            Whether to export separate alpha masks.
	 * @param progress
	 *            If not null, the exporter will invoke this callback to inform
	 *            on the operation's progress.
	 * @throws Exception
	 *             if there is an error.
	 */
	public static void mergeTextures(File destination, File texturePack, double scale, boolean alphas, boolean lumas,
			ProgressCallback progress) throws Exception {
		if (destination == null)
			throw new IllegalArgumentException("destination cannot be null");

		Log.info("Exporting textures to \"" + destination + "\"");
		if (!destination.exists() || !destination.isDirectory()) {
			if (destination.exists())
				throw new RuntimeException("Cannot create texture directory! File is in the way!");
			if (!destination.mkdir())
				throw new RuntimeException("Cannot create texture directory!");
		}

		Map<String, Rectangle> ret = new HashMap<String, Rectangle>();

		List<Texture> textures = getTextures(texturePack, scale, progress, alphas, true, destination);

		// calculate maxwidth so to keep the size of the final file more or less
		// square
		double surface = 0;
		for (Texture texture : textures) {
			if (texture.repeating)
				surface += texture.image.getWidth() * texture.image.getHeight() * 9.0;
			surface += texture.image.getWidth() * texture.image.getHeight();
		}
		int maxwidth = (int) Math.sqrt(surface);

		// calculate coordinates for placing the textures in the file
		int wused = 0, hused = 0, hcurr = 0, wmax = 0, hmax = 0;
		for (Texture texture : textures) {
			if (wused > maxwidth) {
				wused = 0;
				hused += hcurr;
				hcurr = 0;
			}

			int w = texture.image.getWidth();
			int h = texture.image.getHeight();

			if (texture.repeating) {
				ret.put(texture.name, new Rectangle(wused + w, hused + h, w, h));
				w *= 3;
				h *= 3;
			} else {
				ret.put(texture.name, new Rectangle(wused, hused, w, h));
			}

			wused += w;
			if (hcurr < h)
				hcurr = h;

			if (wmax < wused)
				wmax = wused;
			if (hmax < (hused + hcurr))
				hmax = hused + hcurr;
		}

		for (int x = 1; x < Short.MAX_VALUE; x *= 2)
			if (x >= wmax) {
				wmax = x;
				break;
			}
		for (int x = 1; x < Short.MAX_VALUE; x *= 2)
			if (x >= hmax) {
				hmax = x;
				break;
			}

		Document doc = Xml.newDocument();
		Element root = doc.createElement("textures");
		root.setAttribute("width", "" + wmax);
		root.setAttribute("height", "" + hmax);
		doc.appendChild(root);

		for (Entry<String, Rectangle> entry : ret.entrySet()) {
			Element el = doc.createElement("texture");
			el.setTextContent(entry.getKey());
			Rectangle rect = entry.getValue();
			el.setAttribute("u", "" + rect.x);
			el.setAttribute("v", "" + rect.y);
			el.setAttribute("w", "" + rect.width);
			el.setAttribute("h", "" + rect.height);
			root.appendChild(el);
		}

		Xml.saveDocument(doc, new File(destination, "texture.uv"));

		BufferedImage textureimage = new BufferedImage(wmax, hmax, BufferedImage.TYPE_4BYTE_ABGR);
		Graphics2D gtex = textureimage.createGraphics();
		
		BufferedImage lumaimage = new BufferedImage(wmax, hmax, BufferedImage.TYPE_4BYTE_ABGR);
		Graphics2D gtexluma = lumaimage.createGraphics();

		float texnum = textures.size();
		float count = 0;

		for (Texture texture : textures) {
			Rectangle rect = ret.get(texture.name);

			if (texture.repeating) {
				for (int x = -1; x <= 1; x++)
					for (int y = -1; y <= 1; y++) {
						int sx = rect.x + x * rect.width;
						int sy = rect.y + y * rect.height;
						gtex.drawImage(texture.image, sx, sy, sx + rect.width, sy + rect.height, 0, 0, rect.width,
								rect.height, null);
						
						if(lumas)
							if(texture.luma)
								gtexluma.drawImage(texture.image, sx, sy, sx + rect.width, sy + rect.height, 0, 0, rect.width,
									rect.height, null);
					}
			} else {
				gtex.drawImage(texture.image, rect.x, rect.y, rect.x + rect.width, rect.y + rect.height, 0, 0,
						rect.width, rect.height, null);
				if(lumas)
					if(texture.luma)
						gtexluma.drawImage(texture.image, rect.x, rect.y, rect.x + rect.width, rect.y + rect.height, 0, 0,
							rect.width, rect.height, null);
			}

			if (progress != null)
				progress.setProgress((++count) / texnum);
		}

		ImageIO.write(textureimage, "png", new File(destination, "texture.png"));
		if(lumas)
			ImageIO.write(lumaimage, "png", new File(destination, "texture_luma.png"));
		
		if (alphas) {
			try {
				convertToAlpha(textureimage);
				ImageIO.write(textureimage, "png", new File(destination, "texture_a.png"));
			} catch (Exception e) {
				Log.info("Cannot save alpha (" + e.getMessage() + ")");
			}
		}
	}

}
