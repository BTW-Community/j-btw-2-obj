package org.jmc.entities;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.jmc.OBJInputFile;
import org.jmc.NBT.NBT_Tag;
import org.jmc.NBT.TAG_Byte;
import org.jmc.NBT.TAG_Compound;
import org.jmc.NBT.TAG_Double;
import org.jmc.NBT.TAG_Float;
import org.jmc.NBT.TAG_Int;
import org.jmc.NBT.TAG_List;
import org.jmc.NBT.TAG_String;
import org.jmc.OBJInputFile.OBJGroup;
import org.jmc.geom.Transform;
import org.jmc.models.Mesh;
import org.jmc.models.Mesh.MeshData;
import org.jmc.threading.ChunkProcessor;
import org.jmc.util.Log;

public class PaintingKZ extends Entity {
	
	private static Map<String,OBJInputFile> files=null;
	
	@SuppressWarnings("unused")
	private String obj_str;
	private OBJInputFile objin_file;
	private OBJGroup group;
	TAG_Compound entity;
	private List<Mesh> objects;
	public MeshData mesh_data;
	
	public PaintingKZ()
	{
		if(files==null) files=new HashMap<String, OBJInputFile>();
		objects=new LinkedList<Mesh>();
		mesh_data=new MeshData();

		objin_file=null;
		group=null;
		obj_str="";
	}
	
	@Override
	public void addEntity(ChunkProcessor obj, TAG_Compound entity) {
		
		int x=((TAG_Int)entity.getElement("TileX")).value;
		int y=((TAG_Int)entity.getElement("TileY")).value;
		int z=((TAG_Int)entity.getElement("TileZ")).value;
		
		byte direction = ((TAG_Byte)entity.getElement("Direction")).value;
		
		TAG_List rot = (TAG_List) entity.getElement("Rotation");
		
		TAG_Float rotation  = ((TAG_Float)  rot.getElement(0));
        
		String motiv=((TAG_String)entity.getElement("Motive")).value;
		
		System.out.println(direction);
		System.out.println(motiv);
		System.out.println(rotation);
		
		if (rotation.value == 0.0) {
			addObj("models/btw/painting.obj#" + motiv,
					"",
					obj, 
					x, 
					y,
					z,
					1,  // scale
					0, // rotation_angle in deg
					1); // rotation_direction 0 = yaw, 1 = pitch, 2 = roll
		}
		if (rotation.value == 90.0){
			addObj("models/btw/painting.obj#" + motiv,
					"",
					obj, 
					x, 
					y,
					z,
					1,  // scale
					90, // rotation_angle in deg
					1); // rotation_direction 0 = yaw, 1 = pitch, 2 = roll
		}
		if (rotation.value == 180.0){
			addObj("models/btw/painting.obj#" + motiv,
					"",
					obj, 
					x, 
					y,
					z,
					1,  // scale
					180, // rotation_angle in deg
					1); // rotation_direction 0 = yaw, 1 = pitch, 2 = roll
		}
		if (rotation.value == 270.0){
			addObj("models/btw/painting.obj#" + motiv,
					"",
					obj, 
					x, 
					y,
					z,
					1,  // scale
					270, // rotation_angle in deg
					1); // rotation_direction 0 = yaw, 1 = pitch, 2 = roll
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
	
	private Transform getRotate(float offset, double rotdir) {
		
		float yaw=0;
		float pitch=0;
		float roll =0;
		
		Transform rotate = new Transform();
		if (rotdir == 0) {
			rotate.rotate2(yaw+offset, pitch ,roll);
		} if (rotdir == 1) {
			rotate.rotate2(yaw, pitch+offset ,roll);
		} if (rotdir == 2) {
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
		
		Transform translate = getTranslate((float)x, (float)y, (float)z, (float)scale).multiply(getRotate((float)angle, rotation));
		
		objin_file.addObjectToOutput(group, translate, obj);
	}

}
