package org.jmc.models;

import org.jmc.geom.UV;
import org.jmc.threading.ChunkProcessor;
import org.jmc.threading.ThreadChunkDeligate;


/**
 * Model for slabs (aka half-blocks).
 */
public class Urn extends BlockModel
{

	@Override
	public void addModel(ChunkProcessor obj, ThreadChunkDeligate chunks, int x, int y, int z, byte data, byte biome)
	{

		String[] mtls = getMtlSides(data, biome);
		UV[] uvTop, uvSide;
		UV[][] uvSides;
		
		if (data == 0) {
			uvTop = new UV[] { new UV(0/16f, 0/16f), new UV(16/16f, 0/16f), new UV(16/16f, 16/16f), new UV(0/16f, 16/16f) };
			uvSide = new UV[] { new UV(0/16f, 0/16f), new UV(16/16f, 0/16f), new UV(16/16f, 16/16f), new UV(0/16f, 16/16f) };
			uvSides = new UV[][] { uvTop, uvSide, uvSide, uvSide, uvSide, uvTop };
			
			if (chunks.getBlockID(x, y+1, z) == 225) //if block above is hopper
			{ 
				addBox(obj,x-2/16f,y-2/16f,z-2/16f,x+2/16f,y+0.5f,z+1/16f, null, mtls, uvSides, null);
				addBox(obj,x-3/16f,y-1/16f,z-3/16f,x+3/16f,y+0.5f-3/16f,z+3/16f, null, mtls, uvSides, null);
				addBox(obj,x-3/16f,y+0.5f-2/16f,z-3/16f,x+3/16f,y+0.5f-1/16f,z+3/16f, null, mtls, uvSides, null);
			}
			else {
				addBox(obj,x-2/16f,y-0.5f,z-2/16f,x+2/16f,y+2/16f,z+1/16f, null, mtls, uvSides, null);
				addBox(obj,x-3/16f,y-0.5f+1/16f,z-3/16f,x+3/16f,y-1/16f,z+3/16f, null, mtls, uvSides, null);
				addBox(obj,x-3/16f,y,z-3/16f,x+3/16f,y+1/16f,z+3/16f, null, mtls, uvSides, null);
			}
		}

		//Column
		if (data == 1) {
			uvTop = new UV[] { new UV(0/16f, 0/16f), new UV(16/16f, 0/16f), new UV(16/16f, 16/16f), new UV(0/16f, 16/16f) };
			uvSide = new UV[] { new UV(0/16f, 0/16f), new UV(16/16f, 0/16f), new UV(16/16f, 16/16f), new UV(0/16f, 16/16f) };
			uvSides = new UV[][] { uvTop, uvSide, uvSide, uvSide, uvSide, uvTop };
			addBox(obj, x-5/16f,y-0.5f,z-5/16f,x+5/16f,y+0.5f,z+5/16f, null, mtls, uvSides, null);
		}
		//Pedestal (2)
		if (data == 13) {
			uvTop = new UV[] { new UV(0/16f, 0/16f), new UV(16/16f, 0/16f), new UV(16/16f, 16/16f), new UV(0/16f, 16/16f) };
			uvSide = new UV[] { new UV(0/16f, 0/16f), new UV(16/16f, 0/16f), new UV(16/16f, 16/16f), new UV(0/16f, 16/16f) };
			uvSides = new UV[][] { uvTop, uvSide, uvSide, uvSide, uvSide, uvTop };
			addBox(obj, x-0.5f, y-0.5f, z-0.5f,  x+0.5f, y+0.25f, z+0.5f , null, mtls, uvSides, null);
			addBox(obj, x-0.5f+0.0625f, y+0.25f,z-0.5f+0.0625f, x+0.5f-0.0625f,y+0.25f+0.125f, z+0.5f-0.0625f,null, mtls,uvSides,null);
			addBox(obj, x-0.5f+0.125f, y+0.25f,z-0.5f+0.125f, x+0.5f-0.125f,y+0.5f, z+0.5f-0.125f,null, mtls,uvSides,null);
		}
		//Pedestal (Down)
		if (data == 3) {
			uvTop = new UV[] { new UV(0/16f, 0/16f), new UV(16/16f, 0/16f), new UV(16/16f, 16/16f), new UV(0/16f, 16/16f) };
			uvSide = new UV[] { new UV(0/16f, 0/16f), new UV(16/16f, 0/16f), new UV(16/16f, 16/16f), new UV(0/16f, 16/16f) };
			uvSides = new UV[][] { uvTop, uvSide, uvSide, uvSide, uvSide, uvTop };
			addBox(obj, x-0.5f, y+0.5f, z-0.5f, x+0.5f, y-0.25f, z+0.5f, null, mtls, uvSides, null);
			addBox(obj,x-0.5f+1/16f,y-0.25f,z-0.5f+1/16f,x+0.5f-1/16f,y-0.25f-0.125f,z+0.5f-1/16f, null, mtls, uvSides, null);
			addBox(obj,x-0.5f+2/16f,y-0.25f-0.125f,z-0.5f+2/16f,x+0.5f-2/16f,y-0.5f,z+0.5f-2/16f, null, mtls, uvSides, null);
		}
		//Table
		if (data == 4) {
			uvTop = new UV[] { new UV(0/16f, 0/16f), new UV(16/16f, 0/16f), new UV(16/16f, 16/16f), new UV(0/16f, 16/16f) };
			uvSide = new UV[] { new UV(0/16f, 0/16f), new UV(16/16f, 0/16f), new UV(16/16f, 16/16f), new UV(0/16f, 16/16f) };
			uvSides = new UV[][] { uvTop, uvSide, uvSide, uvSide, uvSide, uvTop };
			addBox(obj, x -0.125f, y-0.5f, z-0.125f, x+0.125f, y+0.5f-0.125f, z+0.125f, null, mtls, uvSides, null);
			addBox(obj, x -0.5f,y+0.5f-0.125f,z-0.5f,x+0.5f,y+0.5f,z+0.5f, null, mtls, uvSides, null);
		}
		//Slab (Bottom)
		if (data == 5 || data == 10) {
			uvTop = new UV[] { new UV(0/16f, 0/16f), new UV(16/16f, 0/16f), new UV(16/16f, 16/16f), new UV(0/16f, 16/16f) };
			uvSide = new UV[] { new UV(0/16f, 0/16f), new UV(16/16f, 0/16f), new UV(16/16f, 16/16f), new UV(0/16f, 16/16f) };
			uvSides = new UV[][] { uvTop, uvSide, uvSide, uvSide, uvSide, uvTop };
			addBox(obj, x - 0.5f, y - 0.5f, z - 0.5f, x + 0.5f, y, z +0.5f, null, mtls, uvSides, null);
		}
		//Slab (Top)
		if (data == 9 || data == 11) {
			uvTop = new UV[] { new UV(0/16f, 0/16f), new UV(16/16f, 0/16f), new UV(16/16f, 16/16f), new UV(0/16f, 16/16f) };
			uvSide = new UV[] { new UV(0/16f, 0/16f), new UV(16/16f, 0/16f), new UV(16/16f, 16/16f), new UV(0/16f, 16/16f) };
			uvSides = new UV[][] { uvTop, uvSide, uvSide, uvSide, uvSide, uvTop };
			addBox(obj, x - 0.5f, y, z - 0.5f, x + 0.5f, y + 0.5f, z +0.5f, null, mtls, uvSides, null);
		}
		//Pane
		if (data == 6 || data == 7 || data == 8) {
			uvTop = new UV[] { new UV(0/16f, 0/16f), new UV(16/16f, 0/16f), new UV(16/16f, 16/16f), new UV(0/16f, 16/16f) };
			uvSide = new UV[] { new UV(0/16f, 0/16f), new UV(16/16f, 0/16f), new UV(16/16f, 16/16f), new UV(0/16f, 16/16f) };
			uvSides = new UV[][] { uvTop, uvSide, uvSide, uvSide, uvSide, uvTop };
			addBox(obj, x-0.5f,y-0.5f,z-1/16f,x+0.5f,y+0.5f,z+1/16f, null, mtls, uvSides, null);
			addBox(obj, x-1/16f,y-0.5f,z-0.5f,x+1/16f,y+0.5f,z+0.5f, null, mtls, uvSides, null);
		}
		//Rod
		if (data == 12) {
			uvTop = new UV[] { new UV(0/16f, 0/16f), new UV(16/16f, 0/16f), new UV(16/16f, 16/16f), new UV(0/16f, 16/16f) };
			uvSide = new UV[] { new UV(0/16f, 0/16f), new UV(16/16f, 0/16f), new UV(16/16f, 16/16f), new UV(0/16f, 16/16f) };
			uvSides = new UV[][] { uvTop, uvSide, uvSide, uvSide, uvSide, uvTop };

			addBox(obj, x-2/16f,y-0.5f,z-2/16f,x+2/16f,y-0.5f+2/16f,z+2/16f, null, mtls, uvSides, null);
			addBox(obj, x-1/32f,y-0.5f,z-1/32f,x+1/32f,y+0.5f,z+1/32f, null, mtls, uvSides, null);
			addBox(obj, x-3/32f,y+0.5f-3/16f,z-3/32f,x+3/32f,y+2/16f,z+3/32f, null, mtls, uvSides, null);
		}

	}

}
