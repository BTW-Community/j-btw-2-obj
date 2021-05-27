package org.jmc.entities;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.jmc.OBJInputFile;
import org.jmc.NBT.TAG_Byte;
import org.jmc.NBT.TAG_Compound;
import org.jmc.NBT.TAG_Double;
import org.jmc.NBT.TAG_Float;
import org.jmc.NBT.TAG_Int;
import org.jmc.NBT.TAG_List;
import org.jmc.OBJInputFile.OBJGroup;
import org.jmc.geom.Transform;
import org.jmc.models.Mesh;
import org.jmc.models.Mesh.MeshData;
import org.jmc.threading.ChunkProcessor;
import org.jmc.util.Log;

public class WindMill extends Entity {
	
	private static Map<String,OBJInputFile> files=null;
	
	@SuppressWarnings("unused")
	private String obj_str;
	private OBJInputFile objin_file;
	private OBJGroup group;
	TAG_Compound entity;
	private List<Mesh> objects;
	public MeshData mesh_data;
	
	public WindMill()
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
		
		TAG_List pos = (TAG_List) entity.getElement("Pos");
		float ex=(float)((TAG_Double)pos.getElement(0)).value-0.5f;				
		float ey=(float)((TAG_Double)pos.getElement(1)).value-0.5f;
		float ez=(float)((TAG_Double)pos.getElement(2)).value-0.5f;
		
		TAG_Int blade0 = (TAG_Int) entity.getElement("iBladeColors0");
		TAG_Int blade1 = (TAG_Int) entity.getElement("iBladeColors1");
		TAG_Int blade2 = (TAG_Int) entity.getElement("iBladeColors2");
		TAG_Int blade3 = (TAG_Int) entity.getElement("iBladeColors3");
		
		int bladeColor0 = blade1.value;
		int bladeColor1 = blade0.value;
		int bladeColor2 = blade2.value;
		int bladeColor3 = blade3.value;
		
		String wingMaterial0 = "wool_white";
		String wingMaterial1 = "wool_white";
		String wingMaterial2 = "wool_white";
		String wingMaterial3 = "wool_white";
		
		
		
		
		  
		  if (bladeColor0 == 1) { wingMaterial0 = "wool_orange";}
		  if (bladeColor0 == 2) { wingMaterial0 = "wool_magenta";} 
		  if (bladeColor0 == 3) { wingMaterial0 = "wool_light_blue";} 
		  if (bladeColor0 == 4) { wingMaterial0 = "wool_yellow";} 
		  if (bladeColor0 == 5) { wingMaterial0 = "wool_lime";} 
		  if (bladeColor0 == 6) { wingMaterial0 = "wool_pink";} 
		  if (bladeColor0 == 7) { wingMaterial0 = "wool_dark_grey";} 
		  if (bladeColor0 == 8) { wingMaterial0 = "wool_light_gray";} 
		  if (bladeColor0 == 9) { wingMaterial0 = "wool_cyan";} 
		  if (bladeColor0 ==10) { wingMaterial0 = "wool_purple";} 
		  if (bladeColor0 ==11) { wingMaterial0 = "wool_blue";} 
		  if (bladeColor0 ==12) { wingMaterial0 = "wool_brown";} 
		  if (bladeColor0 ==13) { wingMaterial0 = "wool_green";} 
		  if (bladeColor0 ==14) { wingMaterial0 = "wool_red";} 
		  if (bladeColor0 ==15) { wingMaterial0 = "wool_black";} 
		
		
		
			  
			  if (bladeColor1 == 1) { wingMaterial1 = "wool_orange";}
			  if (bladeColor1 == 2) { wingMaterial1 = "wool_magenta";} 
			  if (bladeColor1 == 3) { wingMaterial1 = "wool_light_blue";} 
			  if (bladeColor1 == 4) { wingMaterial1 = "wool_yellow";} 
			  if (bladeColor1 == 5) { wingMaterial1 = "wool_lime";} 
			  if (bladeColor1 == 6) { wingMaterial1 = "wool_pink";} 
			  if (bladeColor1 == 7) { wingMaterial1 = "wool_dark_grey";} 
			  if (bladeColor1 == 8) { wingMaterial1 = "wool_light_gray";} 
			  if (bladeColor1 == 9) { wingMaterial1 = "wool_cyan";} 
			  if (bladeColor1 ==10) { wingMaterial1 = "wool_purple";} 
			  if (bladeColor1 ==11) { wingMaterial1 = "wool_blue";} 
			  if (bladeColor1 ==12) { wingMaterial1 = "wool_brown";} 
			  if (bladeColor1 ==13) { wingMaterial1 = "wool_green";} 
			  if (bladeColor1 ==14) { wingMaterial1 = "wool_red";} 
			  if (bladeColor1 ==15) { wingMaterial1 = "wool_black";} 
		
		
		
			 
			  if (bladeColor2 == 1) { wingMaterial2 = "wool_orange";}
			  if (bladeColor2 == 2) { wingMaterial2 = "wool_magenta";} 
			  if (bladeColor2 == 3) { wingMaterial2 = "wool_light_blue";} 
			  if (bladeColor2 == 4) { wingMaterial2 = "wool_yellow";} 
			  if (bladeColor2 == 5) { wingMaterial2 = "wool_lime";} 
			  if (bladeColor2 == 6) { wingMaterial2 = "wool_pink";} 
			  if (bladeColor2 == 7) { wingMaterial2 = "wool_dark_grey";} 
			  if (bladeColor2 == 8) { wingMaterial2 = "wool_light_gray";} 
			  if (bladeColor2 == 9) { wingMaterial2 = "wool_cyan";} 
			  if (bladeColor2 ==10) { wingMaterial2 = "wool_purple";} 
			  if (bladeColor2 ==11) { wingMaterial2 = "wool_blue";} 
			  if (bladeColor2 ==12) { wingMaterial2 = "wool_brown";} 
			  if (bladeColor2 ==13) { wingMaterial2 = "wool_green";} 
			  if (bladeColor2 ==14) { wingMaterial2 = "wool_red";} 
			  if (bladeColor2 ==15) { wingMaterial2 = "wool_black";} 
		
			  
			  
			  if (bladeColor3 == 1) { wingMaterial3 = "wool_orange";}
			  if (bladeColor3 == 2) { wingMaterial3 = "wool_magenta";} 
			  if (bladeColor3 == 3) { wingMaterial3 = "wool_light_blue";} 
			  if (bladeColor3 == 4) { wingMaterial3 = "wool_yellow";} 
			  if (bladeColor3 == 5) { wingMaterial3 = "wool_lime";} 
			  if (bladeColor3 == 6) { wingMaterial3 = "wool_pink";} 
			  if (bladeColor3 == 7) { wingMaterial3 = "wool_dark_grey";} 
			  if (bladeColor3 == 8) { wingMaterial3 = "wool_light_gray";} 
			  if (bladeColor3 == 9) { wingMaterial3 = "wool_cyan";} 
			  if (bladeColor3 ==10) { wingMaterial3 = "wool_purple";} 
			  if (bladeColor3 ==11) { wingMaterial3 = "wool_blue";} 
			  if (bladeColor3 ==12) { wingMaterial3 = "wool_brown";} 
			  if (bladeColor3 ==13) { wingMaterial3 = "wool_green";} 
			  if (bladeColor3 ==14) { wingMaterial3 = "wool_red";} 
			  if (bladeColor3 ==15) { wingMaterial3 = "wool_black";} 
		
		
		
		System.out.println("BladeColor: " + bladeColor0 + " Material: " + wingMaterial0);
		System.out.println("BladeColor: " + bladeColor1 + " Material: " + wingMaterial1);
		System.out.println("BladeColor: " + bladeColor2 + " Material: " + wingMaterial2);
		System.out.println("BladeColor: " + bladeColor3 + " Material: " + wingMaterial3);
		
		float rotation = 0;
		
		float rotationY = 0;
		float rotationP = 0;
		float rotationR = 0;
		
		int rot_dirY = 1; //0 = yaw, 1 = pitch, 2 = roll
		int rot_dirP = 1;
		int rot_dirR = 1;
		
		byte getRot=(byte)((TAG_Byte)entity.getElement("bWindMillIAligned")).value;
		float getTurn=(float)((TAG_Float)entity.getElement("fRotation")).value;
		
		System.out.println("getRot :" + getRot);
		System.out.println("getTurn :" + getTurn);
		
		if (getTurn != 0) {
			rotationR = getTurn;
		}
		
		if (getRot > 0) {
			rotationY = 45;
		}

		addObj("models/btw/windmill.obj#wood",
				"plank_oak",
				obj,
				ex, ey, ez,
				1,
				rotationY, rotationP, rotationR,
				rot_dirY, rot_dirP, rot_dirR);
		
		addObj("models/btw/windmill.obj#wing_0",
				wingMaterial0,
				obj,
				ex, ey, ez,
				1,
				rotationY, rotationP, rotationR,
				rot_dirY, rot_dirP, rot_dirR);
		
		addObj("models/btw/windmill.obj#wing_1",
				wingMaterial1,
				obj,
				ex, ey, ez,
				1,
				rotationY, rotationP, rotationR,
				rot_dirY, rot_dirP, rot_dirR);
		
		addObj("models/btw/windmill.obj#wing_2",
				wingMaterial2,
				obj,
				ex, ey, ez,
				1,
				rotationY, rotationP, rotationR,
				rot_dirY, rot_dirP, rot_dirR);
		
		addObj("models/btw/windmill.obj#wing_3",
				wingMaterial3,
				obj,
				ex, ey, ez,
				1,
				rotationY, rotationP, rotationR,
				rot_dirY, rot_dirP, rot_dirR);
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
	
	
	
	private Transform getRotate(float yawoffset, float pitchoffset, float rolloffset, int rotdirYaw, int rotdirPitch, int rotdirRoll) {
		
		
		
		float yaw=0;
		float pitch=0;
		float roll =0;
		
		
		
		Transform rotate = new Transform();

			rotate.rotate2(yaw+yawoffset, pitch ,roll);

			rotate.rotate2(yaw, pitch+pitchoffset ,roll);

			rotate.rotate2(yaw, pitch ,roll+rolloffset);
		
		return rotate;
	}
	
	public void addObj(String objectstr, String material, ChunkProcessor obj, double x, double y, double z, double scale, double rotationYaw, double rotationPitch, double rotationRoll, int angleYaw, int anglePitch, int angleRoll) {

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
		
		objin_file.overwriteMaterial(group, material);
		
		Transform translate = getTranslate((float)x, (float)y, (float)z, (float)scale).multiply(getRotate((float) rotationYaw, (float) rotationPitch, (float) rotationRoll, angleYaw, anglePitch, angleRoll));
		
		objin_file.addObjectToOutput(group, translate, obj);
	}

}
