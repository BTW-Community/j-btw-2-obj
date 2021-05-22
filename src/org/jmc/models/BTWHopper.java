package org.jmc.models;

import org.jmc.threading.ChunkProcessor;
import org.jmc.threading.ThreadChunkDeligate;


import org.jmc.NBT.NBT_Tag;
import org.jmc.NBT.TAG_Byte;
import org.jmc.NBT.TAG_Compound;
import org.jmc.NBT.TAG_List;
import org.jmc.NBT.TAG_Short;
import org.jmc.NBT.TAG_String;

import org.jmc.geom.UV;
/**
 * Custom model for btw hopper.
 */
public class BTWHopper extends BlockModel
{
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
	
	private String[] getFilterSides(byte data, byte biome, int filterID)
	{
		String[] abbrMtls = materials.get(data,biome);

		String[] mtlSides = new String[6];
		mtlSides[0] = abbrMtls[3]; //top
		
		if (filterID == 101) { 			//iron bars
			mtlSides[0] = abbrMtls[8]; 	//top
		}
		else if (filterID == 1051) {	//ladder
			mtlSides[0] = abbrMtls[9]; 	//top
		}
		else if (filterID == 1084) { 	//wicker
			mtlSides[0] = abbrMtls[10]; //top
		}
		else if (filterID == 1085) { 	//grate
			mtlSides[0] = abbrMtls[11]; //top
		}
		else if (filterID == 1086) { 	//slats
			mtlSides[0] = abbrMtls[12]; //top
		}
		else if (filterID == 88) { 		//soul sand
			mtlSides[0] = abbrMtls[13]; //top
		}
		mtlSides[1] = abbrMtls[7]; //front
		mtlSides[2] = abbrMtls[7]; //back
		mtlSides[3] = abbrMtls[7]; //left
		mtlSides[4] = abbrMtls[7]; //right
		mtlSides[5] = abbrMtls[7]; //bottom
		return mtlSides;
	}
	
	
	
	@Override
	public void addModel(ChunkProcessor obj, ThreadChunkDeligate chunks, int x, int y, int z, byte data, byte biome)
	{	
		UV[] uvTop, uvSide;
		UV[][] uvSides;
		
		TAG_Compound te = chunks.getTileEntity(x, y, z);
        int filterID = 0;
        int itemCount = 0;
		
        //Figure out the number of filled item slots in the hopper and if it has a filter in it
		if("Hopper".equals( ((TAG_String)te.getElement("id")).value) ) {
            //System.out.println("Tile Entity: Hopper"); 
            
            TAG_List items = (TAG_List) te.getElement("Items");
            if(items != null) {
                itemCount = items.elements.length;
                //System.out.println("This Hopper has " + itemCount + " items in it."); 
                
                for (NBT_Tag tag: items.elements) {
                    TAG_Compound itemStack = (TAG_Compound) tag;
                    TAG_Byte slot    = (TAG_Byte)  itemStack.getElement("Slot");
                    TAG_Short slotID = (TAG_Short) itemStack.getElement("id");
                    
                    if (slot.value == 18) {
                        //System.out.println("\tfilter " + slotID.value + " in slot 18.");
                        filterID = slotID.value;
                        //We don't want to count the filter as an item in the inventory
                        itemCount--;
                    } else {
                        //System.out.println("\titem " + slotID.value + " in slot " + slot.value);
                    }
                }
            }
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
		
		if (itemCount > 0) {
			//Contents
			uvTop = new UV[] { new UV(0/16f, 0/16f), new UV(16/16f, 0/16f), new UV(16/16f, 16/16f), new UV(0/16f, 16/16f) };
			uvSide = new UV[] { new UV(0/16f, 0/16f), new UV(16/16f, 0/16f), new UV(16/16f, 16/16f), new UV(0/16f, 16/16f) };
			uvSides = new UV[][] { uvTop, uvSide, uvSide, uvSide, uvSide, uvTop };
			addBox(obj,
					x - 6/16f, y - 3/16f, z - 6/16f, //min
					x + 6/16f, y - 3/16f + (itemCount/2f)/16f, z + 6/16f, //max
					null, getContentsSides(data,biome), uvSides, null);
		}
		
		if (filterID != 0) {
            uvTop = new UV[] { new UV(2/16f, 2/16f), new UV(14/16f, 2/16f), new UV(14/16f, 14/16f), new UV(2/16f, 14/16f) };
            uvSide = new UV[] { new UV(0/16f, 0/16f), new UV(16/16f, 0/16f), new UV(16/16f, 16/16f), new UV(0/16f, 16/16f) };
            uvSides = new UV[][] { uvTop, uvSide, uvSide, uvSide, uvSide, uvTop };
            addBox(obj,
                    x - 6/16f, y + 7/16f, z - 6/16f, //min
                    x + 6/16f, y + 7/16f, z + 6/16f, //max
                    null, getFilterSides(data,biome,filterID), uvSides, null);
		}
	}
}