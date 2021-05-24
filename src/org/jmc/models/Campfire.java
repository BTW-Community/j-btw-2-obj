package org.jmc.models;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.jmc.OBJInputFile;
import org.jmc.OBJInputFile.OBJGroup;
import org.jmc.geom.Transform;
import org.jmc.models.Mesh.MeshData;
import org.jmc.threading.ChunkProcessor;
import org.jmc.threading.ThreadChunkDeligate;
import org.jmc.util.Log;


/**
 * Generic model for cube blocks.
 */
public class Campfire extends BlockModel
{
	private static Map<String,OBJInputFile> files=null;
	
	@SuppressWarnings("unused")
	private String obj_str;
	private OBJInputFile objin_file;
	private OBJGroup group;

	private List<Mesh> objects;
	public MeshData mesh_data;

	public Campfire()
	{
		if(files==null) files=new HashMap<String, OBJInputFile>();
		objects=new LinkedList<Mesh>();
		mesh_data=new MeshData();

		objin_file=null;
		group=null;
		obj_str="";
	}

	
	@Override
	public void addModel(ChunkProcessor obj, ThreadChunkDeligate chunks, int x, int y, int z, byte data, byte biome)
	{
		/** Reference of addObj
		 * addObj("models/filename.obj#object", 
				"material_name",  //is ignored
				obj, 
				x, 
				y,
				z,
				scale,
				rotation_angle,
				rotation_direction); //0 = yaw, 1 = pitch, 2 = roll
		 */
		int thisBlock = chunks.getBlockID(x, y, z);
		byte[] spit = new byte[] { 2,3,6,7,10,11,13,15 };
		byte[] campfire = new byte[] { 0,1,2,3,12,13,14,15 };
		byte[] burnedOut = new byte[] { 4,5,6,7 };
		byte[] smoldering = new byte[] { 8,9,10,11 };
		byte[] smallFlame = new byte[] { 0,1,2,3 };
		byte[] mediumFlame = new byte[] { 0,1,2,3 };
		byte[] largeFlame = new byte[] { 0,1,2,3 };
		byte[] rotated = new byte[] { 1,3,5,7,9,11,13,15 };
		
		int rotation = 0;
		

		//each campfire that needs rotating is listed in rotated[] and will get a new rotation value
		for (int r = 0; r < rotated.length; r++) {
			//System.out.println("Rotating campfire for data: " + rotated[i]);
			if (data == rotated[r]) {
				rotation = 90;
			}
		}
		
		//not burning campfire - see campfire[] for meta values
		for (int c = 0; c < campfire.length; c++) {
			if (data == campfire[c]) {
				//System.out.println("Adding campfire for data: " + campfire[i]);
				addObj("models/btw/campfire.obj#campfire",
						"",
						obj, 
						x, 
						y,
						z,
						1,  // scale
						rotation, // rotation_angle in deg
						0); //rotation_direction 0 = yaw, 1 = pitch, 2 = roll
			}
		}
			
			
		//spit - see spit[] for meta values that have spit
		for (int s = 0; s < spit.length; s++) {
			if (data == spit[s]) {
				//System.out.println("Adding spit for data: " + spit[i]);
				addObj("models/btw/campfire.obj#spit",
						"",
						obj, 
						x, 
						y,
						z,
						1,  // scale
						rotation, // rotation_angle in deg
						0); //rotation_direction 0 = yaw, 1 = pitch, 2 = roll
			}
		}
		

		if (thisBlock == 1013) {
			//burnedout campfire - see campfire[] for meta values
			for (int b = 0; b < burnedOut.length; b++) {
				//System.out.println("Adding burned campfire for data: " + burnedOut[i]);
				if (data == burnedOut[b]) {
					addObj("models/btw/campfire.obj#campfire_burned",
							"",
							obj, 
							x, 
							y,
							z,
							1,  // scale
							rotation, // rotation_angle in deg
							0); // rotation_direction 0 = yaw, 1 = pitch, 2 = roll
				}
			}
			//smoldering campfire
			for (int j = 0; j < smoldering.length; j++) {
				if (data == smoldering[j]) {
					//System.out.println("Adding smoldering campfire for data: " + smoldering[i]);
					addObj("models/btw/campfire.obj#campfire_smoldering",
							"",
							obj, 
							x, 
							y,
							z,
							1,  // scale
							rotation, // rotation_angle in deg
							0); // rotation_direction 0 = yaw, 1 = pitch, 2 = roll
				}
			}
		}
		
		if (thisBlock == 1014) {
			//small flame campfire
			for (int sm = 0; sm < smallFlame.length; sm++) {
				if (data == smallFlame[sm]) {
					//System.out.println("Adding small flame for data: " + smallFlame[i]);
					addObj("models/btw/campfire.obj#small_fire",
							"",
							obj, 
							x, 
							y,
							z,
							1,  // scale
							rotation, // rotation_angle in deg
							0); // rotation_direction 0 = yaw, 1 = pitch, 2 = roll
				}
			}
		}
		if (thisBlock == 1015) {
			//medium flame campfire
			for (int me = 0; me < mediumFlame.length; me++) {
				if (data == mediumFlame[me]) {
					//System.out.println("Adding medium flame for data: " + mediumFlame[i]);
					addObj("models/btw/campfire.obj#medium_fire",
							"",
							obj, 
							x, 
							y,
							z,
							1,  // scale
							rotation, // rotation_angle in deg
							0); // rotation_direction 0 = yaw, 1 = pitch, 2 = roll
				}
			}
		}
		if (thisBlock == 1016) {
			//large flame campfire
			for (int la = 0; la < largeFlame.length; la++) {
				if (data == largeFlame[la]) {
					//System.out.println("Adding large flame for data: " + largeFlame[i]);
					addObj("models/btw/campfire.obj#large_fire",
							"",
							obj, 
							x, 
							y,
							z,
							1,  // scale
							rotation, // rotation_angle in deg
							0); // rotation_direction 0 = yaw, 1 = pitch, 2 = roll
				}
			}
		}
		
	}
	
	private Transform getTranslate(float x, float y, float z, float scale) {

		float ex=(float)x;				
		float ey=(float)y;
		float ez=(float)z;
		Transform translate = new Transform();
		translate.translate(ex, ey, ez);
		
		Transform tScale = new Transform();
		tScale.scale(scale, scale, scale);
		
		translate = translate.multiply(tScale);
		
		return translate;
	}
	
	private Transform getRotate(float offset, int rotdir) {
		float yaw=0;				
		float pitch=0;
		float roll =0;
		Transform rotate = new Transform();
		if (rotdir == 0) {
			rotate.rotate2(yaw+offset, pitch ,roll);
		}
		if (rotdir == 1) {
			rotate.rotate2(yaw, pitch+offset ,roll);
		}
		if (rotdir == 2) {
			rotate.rotate2(yaw, pitch ,roll+offset);
		}
			
		return rotate;
	}
	
	public void addObj(String objectstr, String material, ChunkProcessor obj, double x, double y, double z, double scale, double rotation, int angle) {
		
		
		
		obj_str=objectstr;
		String [] tok=objectstr.trim().split("#");
		String filename=tok[0];

		if(files.containsKey(filename))
			objin_file=files.get(filename);
		else
		{
			objin_file=new OBJInputFile();

			try {
				objin_file.loadFile(new File("conf",filename));
			} catch (IOException e) {
				Log.error("Cannot load mesh file!", e);
				return;
			}

			files.put(filename, objin_file);
		}

		if(tok.length>1)
			group=objin_file.getObject(tok[1]);
		else
			group=objin_file.getDefaultObject();

		if(group==null)
		{
			Log.info("Cannot find "+ objectstr +" object!");
			return;
		}
		
		Transform translate = getTranslate((float)x, (float)y, (float)z, (float)scale).multiply(getRotate((float) rotation, angle));
		
		objin_file.addObjectToOutput(group, translate, obj);
	}

}
