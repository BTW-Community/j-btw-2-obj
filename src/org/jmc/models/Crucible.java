package org.jmc.models;

import java.io.File;
import java.io.IOException;

import org.jmc.OBJInputFile;
import org.jmc.NBT.NBT_Tag;
import org.jmc.NBT.TAG_Byte;
import org.jmc.NBT.TAG_Compound;
import org.jmc.NBT.TAG_Double;
import org.jmc.NBT.TAG_Float;
import org.jmc.NBT.TAG_List;
import org.jmc.NBT.TAG_Short;
import org.jmc.NBT.TAG_String;
import org.jmc.OBJInputFile.OBJGroup;
import org.jmc.geom.Transform;
import org.jmc.geom.UV;
import org.jmc.threading.ChunkProcessor;
import org.jmc.threading.ThreadChunkDeligate;
import org.jmc.util.Log;

public class Crucible extends BlockModel {
	
	private TAG_List pos;
	private TAG_List rot;
	
	

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
	
	private String[] getContentsSides(byte data, byte biome)
	{
		String[] abbrMtls = materials.get(data,biome);

		String[] mtlSides = new String[6];
		mtlSides[0] = abbrMtls[6]; //top
		mtlSides[1] = abbrMtls[6]; //front
		mtlSides[2] = abbrMtls[6]; //back
		mtlSides[3] = abbrMtls[6]; //left
		mtlSides[4] = abbrMtls[6]; //right
		mtlSides[5] = abbrMtls[6]; //bottom
		return mtlSides;
	}

	@Override
	public void addModel(ChunkProcessor obj, ThreadChunkDeligate chunks, int x, int y, int z, byte data, byte biome) {

		UV[] uvTop, uvSide;
		UV[][] uvSides;
		
		int filterID = 0;
		int itemCount = 0;
		
		TAG_Compound te = chunks.getTileEntity(x, y, z);
		if("Crucible".equals( ((TAG_String)te.getElement("id")).value) ) {
			TAG_List items = (TAG_List) te.getElement("Items");
			
			if(items != null) {
                itemCount = items.elements.length;
                //System.out.println("This Hopper has " + itemCount + " items in it."); 
                
                for (NBT_Tag tag: items.elements) {
                    TAG_Compound itemStack = (TAG_Compound) tag;
                    TAG_Byte slot    = (TAG_Byte)  itemStack.getElement("Slot");
                    TAG_Short slotID = (TAG_Short) itemStack.getElement("id");
                }
            }
		}
		if (data == 4) {
			addObj("conf/models/btw/crucible.obj", 
					"gold",  //is ignored
					obj, 
					x, 
					y,
					z,
					1,
					90, //rot angle
					1); //0 = yaw, 1 = pitch, 2 = roll
		}
		if (data == 5) {
			addObj("conf/models/btw/crucible.obj", 
					"gold",  //is ignored
					obj, 
					x, 
					y,
					z,
					1,
					-90, //rot angle
					1); //0 = yaw, 1 = pitch, 2 = roll
		}
		if (data == 6) {
			addObj("conf/models/btw/crucible.obj", 
					"gold",  //is ignored
					obj, 
					x, 
					y,
					z,
					1,
					-90, //rot angle
					2); //0 = yaw, 1 = pitch, 2 = roll
		}
		if (data == 7) {
			addObj("conf/models/btw/crucible.obj", 
					"gold",  //is ignored
					obj, 
					x, 
					y,
					z,
					1,
					90, //rot angle
					2); //0 = yaw, 1 = pitch, 2 = roll
		}
		if (data > 7 || data < 4 ) {
			addObj("conf/models/btw/crucible.obj", 
					"gold",  //is ignored
					obj, 
					x , 
					y,
					z,
					1,
					0, //rot angle
					0); //0 = yaw, 1 = pitch, 2 = roll
			
			uvTop = new UV[] { new UV(3/16f, 3/16f), new UV(13/16f, 3/16f), new UV(13/16f, 13/16f), new UV(3/16f, 13/16f) };
			uvSide = new UV[] { new UV(0/16f, 0/16f), new UV(16/16f, 0/16f), new UV(16/16f, 16/16f), new UV(0/16f, 16/16f) };
			uvSides = new UV[][] { uvTop, uvSide, uvSide, uvSide, uvSide, uvTop };
			
			addBox(obj, //contents, varies depending on fill level
					x - 5/16f, y - 4/16f, z - 5/16f, //min
					x + 5/16f, y + 1/16f + itemCount * (5/16f) / 27, z + 5/16f, //max
					null, getContentsSides(data,biome), uvSides, null);
		}
	}
	
	public void addObj(String objFileName, String material, ChunkProcessor obj, double x, double y, double z, double scale, double rotation, int angle) {
		
		OBJInputFile objFile = new OBJInputFile();
		File objMeshFile = new File(objFileName);
		
		
		
		try {
			objFile.loadFile(objMeshFile, material);
		} catch (IOException e) {
			Log.error("Cant read Obj", e, true);
		}
		
				
		OBJGroup myObjGroup = objFile.getDefaultObject();
		//objFile.overwriteMaterial(myObjGroup, material);
		// Log.info("myObjGroup: "+myObjGroup);
		
		
		Transform translate = getTranslate((float)x, (float)y, (float)z, (float)scale).multiply(getRotate((float) rotation, angle));
		
		objFile.addObjectToOutput(myObjGroup, translate, obj);
	}

}
