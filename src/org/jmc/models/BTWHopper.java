package org.jmc.models;

import org.jmc.threading.ChunkProcessor;
import org.jmc.threading.ThreadChunkDeligate;
import org.jmc.util.Log;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.jmc.OBJInputFile;
import org.jmc.NBT.NBT_Tag;
import org.jmc.NBT.TAG_Byte;
import org.jmc.NBT.TAG_Byte_Array;
import org.jmc.NBT.TAG_Compound;
import org.jmc.NBT.TAG_Int;
import org.jmc.NBT.TAG_List;
import org.jmc.NBT.TAG_Short;
import org.jmc.NBT.TAG_String;
import org.jmc.OBJInputFile.OBJGroup;
import org.jmc.geom.Transform;
import org.jmc.geom.UV;
/**
 * Generic model for temp slab blocks.
 */
public class BTWHopper extends BlockModel
{
	private TAG_Compound te;
	
	private void getHopperFilter(ChunkProcessor obj, ThreadChunkDeligate chunks, int x, int y, int z, byte data, byte biome)
	{
		te = chunks.getTileEntity(x, y, z);
		
		String hopper = ((TAG_String)te.getElement("id")).value;	 //is "Hopper" 
		TAG_List items = (TAG_List) te.getElement("Items");
		
		TAG_Compound itemStack = (TAG_Compound) items.getElement(0);
		TAG_Byte slot = null;
		TAG_Short slotID = null;
		
		try {
			 slot = (TAG_Byte) itemStack.getElement("Slot");
			 slotID = (TAG_Short) itemStack.getElement("id");
		}
		catch (Exception e) {
			 Log.info("No slot found in this Hopper! - that seams ok - it may be empty!");
		}
		
		
		
		int itemCount = items.elements.length;
		System.out.println("Tile Entity: " + hopper); 
		
		
		if (hopper != null && items.elements.length > 0) {
			System.out.println("This Hopper has " + itemCount + " items in it."); 
			
			if (slot.value == 18) {
				System.out.println("This Hopper has filter " + slotID.value + " in slot 18.");
			}
			
			for (int i = 0; i < items.elements.length; i++) {
				System.out.println("This Hopper has an item in slot: " + slot.value);
				
			}
			
			
		}
		else {
			System.out.println("This Hopper is empty."); 
		}

	}
	
	private String[] getNozzleSides(byte data, byte biome)
	{
		String[] abbrMtls = materials.get(data,biome);

		String[] mtlSides = new String[6];
		mtlSides[0] = abbrMtls[2]; //top
		mtlSides[1] = abbrMtls[1]; //front
		mtlSides[2] = abbrMtls[1]; //back
		mtlSides[3] = abbrMtls[1]; //left
		mtlSides[4] = abbrMtls[1]; //right
		mtlSides[5] = abbrMtls[2]; //bottom
		return mtlSides;
	}
	
	private String[] getBinSides(byte data, byte biome)
	{
		String[] abbrMtls = materials.get(data,biome);

		String[] mtlSides = new String[6];
		mtlSides[0] = abbrMtls[3]; //top
		mtlSides[1] = abbrMtls[4]; //front
		mtlSides[2] = abbrMtls[4]; //back
		mtlSides[3] = abbrMtls[4]; //left
		mtlSides[4] = abbrMtls[4]; //right
		mtlSides[5] = abbrMtls[5]; //bottom
		return mtlSides;
	}
	
	private String[] getInsideSides(byte data, byte biome)
	{
		String[] abbrMtls = materials.get(data,biome);

		String[] mtlSides = new String[6];
		mtlSides[0] = abbrMtls[3]; //top
		mtlSides[1] = abbrMtls[6]; //front
		mtlSides[2] = abbrMtls[6]; //back
		mtlSides[3] = abbrMtls[6]; //left
		mtlSides[4] = abbrMtls[6]; //right
		mtlSides[5] = abbrMtls[5]; //bottom
		return mtlSides;
	}
	
	private String[] getContentsSides(byte data, byte biome)
	{
		String[] abbrMtls = materials.get(data,biome);

		String[] mtlSides = new String[6];
		mtlSides[0] = abbrMtls[7]; //top
		mtlSides[1] = abbrMtls[7]; //front
		mtlSides[2] = abbrMtls[7]; //back
		mtlSides[3] = abbrMtls[7]; //left
		mtlSides[4] = abbrMtls[7]; //right
		mtlSides[5] = abbrMtls[7]; //bottom
		return mtlSides;
	}
	
	private String[] getLadderSides(byte data, byte biome)
	{
		String[] abbrMtls = materials.get(data,biome);

		String[] mtlSides = new String[6];
		
		if (filterID == 101) { //ironbars
			mtlSides[0] = abbrMtls[8]; //top
		}
		else if (filterID == 1051) { //ladder
			mtlSides[0] = abbrMtls[9]; //top
		}
		else if (filterID == 1084) { //ladder
			mtlSides[0] = abbrMtls[10]; //top
		}
		else if (filterID == 1085) { //ladder
			mtlSides[0] = abbrMtls[11]; //top
		}
		else if (filterID == 1086) { //ladder
			mtlSides[0] = abbrMtls[12]; //top
		}
		else if (filterID == 88) { //ladder
			mtlSides[0] = abbrMtls[13]; //top
		}
		else {
			mtlSides[0] = abbrMtls[3]; //top
		}
		mtlSides[1] = abbrMtls[7]; //front
		mtlSides[2] = abbrMtls[7]; //back
		mtlSides[3] = abbrMtls[7]; //left
		mtlSides[4] = abbrMtls[7]; //right
		mtlSides[5] = abbrMtls[7]; //bottom
		return mtlSides;
	}
	
	int filterID;
	
	@Override
	public void addModel(ChunkProcessor obj, ThreadChunkDeligate chunks, int x, int y, int z, byte data, byte biome)
	{
		UV[] uvTop, uvSide;
		UV[][] uvSides;
		
		te = chunks.getTileEntity(x, y, z);
		
		String hopper = ((TAG_String)te.getElement("id")).value;	 //is "Hopper" 
		TAG_List items = (TAG_List) te.getElement("Items");
		
		TAG_Compound itemStack = (TAG_Compound) items.getElement(0);
		TAG_Byte slot = null;
		TAG_Short slotID = null;
		
		try {
			 slot = (TAG_Byte) itemStack.getElement("Slot");
			 slotID = (TAG_Short) itemStack.getElement("id");
		}
		catch (Exception e) {
			 //Log.info("No slot found in this Hopper! - that seems ok - it may be empty!");
		}
		
		filterID = slotID.value;
		
		int itemCount = items.elements.length;
		
		if (slot != null && slot.value == 18) {
			
			if (slotID.value == 101) {
				//Filter
				uvTop = new UV[] { new UV(2/16f, 2/16f), new UV(14/16f, 2/16f), new UV(14/16f, 14/16f), new UV(2/16f, 14/16f) };
				uvSide = new UV[] { new UV(0/16f, 0/16f), new UV(16/16f, 0/16f), new UV(16/16f, 16/16f), new UV(0/16f, 16/16f) };
				uvSides = new UV[][] { uvTop, uvSide, uvSide, uvSide, uvSide, uvTop };
				addBox(obj,
						x - 6/16f, y + 7/16f, z - 6/16f, //min
						x + 6/16f, y + 7/16f, z + 6/16f, //max
						null, getLadderSides(data,biome), uvSides, null);
			}
			if (slotID.value == 1051) {
				//Filter
				uvTop = new UV[] { new UV(2/16f, 2/16f), new UV(14/16f, 2/16f), new UV(14/16f, 14/16f), new UV(2/16f, 14/16f) };
				uvSide = new UV[] { new UV(0/16f, 0/16f), new UV(16/16f, 0/16f), new UV(16/16f, 16/16f), new UV(0/16f, 16/16f) };
				uvSides = new UV[][] { uvTop, uvSide, uvSide, uvSide, uvSide, uvTop };
				addBox(obj,
						x - 6/16f, y + 7/16f, z - 6/16f, //min
						x + 6/16f, y + 7/16f, z + 6/16f, //max
						null, getLadderSides(data,biome), uvSides, null);
			}
			if (slotID.value == 1084) {
				//Filter
				uvTop = new UV[] { new UV(2/16f, 2/16f), new UV(14/16f, 2/16f), new UV(14/16f, 14/16f), new UV(2/16f, 14/16f) };
				uvSide = new UV[] { new UV(0/16f, 0/16f), new UV(16/16f, 0/16f), new UV(16/16f, 16/16f), new UV(0/16f, 16/16f) };
				uvSides = new UV[][] { uvTop, uvSide, uvSide, uvSide, uvSide, uvTop };
				addBox(obj,
						x - 6/16f, y + 7/16f, z - 6/16f, //min
						x + 6/16f, y + 7/16f, z + 6/16f, //max
						null, getLadderSides(data,biome), uvSides, null);
			}
			if (slotID.value == 1085) {
				//Filter
				uvTop = new UV[] { new UV(2/16f, 2/16f), new UV(14/16f, 2/16f), new UV(14/16f, 14/16f), new UV(2/16f, 14/16f) };
				uvSide = new UV[] { new UV(0/16f, 0/16f), new UV(16/16f, 0/16f), new UV(16/16f, 16/16f), new UV(0/16f, 16/16f) };
				uvSides = new UV[][] { uvTop, uvSide, uvSide, uvSide, uvSide, uvTop };
				addBox(obj,
						x - 6/16f, y + 7/16f, z - 6/16f, //min
						x + 6/16f, y + 7/16f, z + 6/16f, //max
						null, getLadderSides(data,biome), uvSides, null);
			}
			if (slotID.value == 1086) {
				//Filter
				uvTop = new UV[] { new UV(2/16f, 2/16f), new UV(14/16f, 2/16f), new UV(14/16f, 14/16f), new UV(2/16f, 14/16f) };
				uvSide = new UV[] { new UV(0/16f, 0/16f), new UV(16/16f, 0/16f), new UV(16/16f, 16/16f), new UV(0/16f, 16/16f) };
				uvSides = new UV[][] { uvTop, uvSide, uvSide, uvSide, uvSide, uvTop };
				addBox(obj,
						x - 6/16f, y + 7/16f, z - 6/16f, //min
						x + 6/16f, y + 7/16f, z + 6/16f, //max
						null, getLadderSides(data,biome), uvSides, null);
			}
			if (slotID.value == 88) {
				//Filter
				uvTop = new UV[] { new UV(2/16f, 2/16f), new UV(14/16f, 2/16f), new UV(14/16f, 14/16f), new UV(2/16f, 14/16f) };
				uvSide = new UV[] { new UV(0/16f, 0/16f), new UV(16/16f, 0/16f), new UV(16/16f, 16/16f), new UV(0/16f, 16/16f) };
				uvSides = new UV[][] { uvTop, uvSide, uvSide, uvSide, uvSide, uvTop };
				addBox(obj,
						x - 6/16f, y + 7/16f, z - 6/16f, //min
						x + 6/16f, y + 7/16f, z + 6/16f, //max
						null, getLadderSides(data,biome), uvSides, null);
			}
		}
		
		if (itemCount > 0) {
			//Contents
			uvTop = new UV[] { new UV(0/16f, 0/16f), new UV(16/16f, 0/16f), new UV(16/16f, 16/16f), new UV(0/16f, 16/16f) };
			uvSide = new UV[] { new UV(0/16f, 0/16f), new UV(16/16f, 0/16f), new UV(16/16f, 16/16f), new UV(0/16f, 16/16f) };
			uvSides = new UV[][] { uvTop, uvSide, uvSide, uvSide, uvSide, uvTop };
			addBox(obj,
					x - 6/16f, y - 3/16f, z - 6/16f, //min
					x + 6/16f, y - 3/16f + (itemCount/2)/16f, z + 6/16f, //max
					null, getContentsSides(data,biome), uvSides, null);
		}
		
		//Nozzle
		uvTop = new UV[] { new UV(0/16f, 0/16f), new UV(16/16f, 0/16f), new UV(16/16f, 16/16f), new UV(0/16f, 16/16f) };
		uvSide = new UV[] { new UV(0/16f, 0/16f), new UV(16/16f, 0/16f), new UV(16/16f, 16/16f), new UV(0/16f, 16/16f) };
		uvSides = new UV[][] { uvTop, uvSide, uvSide, uvSide, uvSide, uvTop };
		addBox(obj,
				x - 3/16f, y - 8/16f, z - 3/16f, //min
				x + 3/16f, y - 4/16f, z + 3/16f, //max
				null, getNozzleSides(data,biome), uvSides, null);
		
		//Bin
		uvTop = new UV[] { new UV(0/16f, 0/16f), new UV(16/16f, 0/16f), new UV(16/16f, 16/16f), new UV(0/16f, 16/16f) };
		uvSide = new UV[] { new UV(0/16f, 0/16f), new UV(16/16f, 0/16f), new UV(16/16f, 16/16f), new UV(0/16f, 16/16f) };
		uvSides = new UV[][] { uvTop, uvSide, uvSide, uvSide, uvSide, uvTop };
		addBox(obj,
				x - 8/16f, y - 4/16f, z - 8/16f, //min
				x + 8/16f, y + 8/16f, z + 8/16f, //max
				null, getBinSides(data,biome), uvSides, null);
		
		//Inside
		uvTop = new UV[] { new UV(2/16f, 2/16f), new UV(14/16f, 2/16f), new UV(14/16f, 14/16f), new UV(2/16f, 14/16f) };
		uvSide = new UV[] { new UV(0/16f, 0/16f), new UV(16/16f, 0/16f), new UV(16/16f, 16/16f), new UV(0/16f, 16/16f) };
		uvSides = new UV[][] { uvTop, uvSide, uvSide, uvSide, uvSide, uvTop };
		addBox(obj,
				x - 6/16f, y - 3/16f, z - 6/16f, //min
				x + 6/16f, y + 8/16f, z + 6/16f, //max
				null, getInsideSides(data,biome), uvSides, null);
		

	}
	

}
