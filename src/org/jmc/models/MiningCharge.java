package org.jmc.models;

import org.jmc.geom.UV;
import org.jmc.threading.ChunkProcessor;
import org.jmc.threading.ThreadChunkDeligate;


/**
 * Model for slabs (aka half-blocks).
 */
public class MiningCharge extends BlockModel
{

	@Override
	public void addModel(ChunkProcessor obj, ThreadChunkDeligate chunks, int x, int y, int z, byte data, byte biome)
	{

		String[] mtls = getMtlSides(data, biome);
		UV[] uvTop, uvSide;
		UV[][] uvSides;
		
		if (data == 0) { //Bottom
			uvTop = new UV[] { new UV(0/16f, 0/16f), new UV(16/16f, 0/16f), new UV(16/16f, 16/16f), new UV(0/16f, 16/16f) };
			uvSide = new UV[] { new UV(0/16f, 0/16f), new UV(16/16f, 0/16f), new UV(16/16f, 16/16f), new UV(0/16f, 16/16f) };
			uvSides = new UV[][] { uvTop, uvSide, uvSide, uvSide, uvSide, uvTop };
			addBox(obj,x-0.5f,y-0.5f,z-0.5f,x+0.5f,y,z+0.5f, null, mtls, uvSides, null);	
		}
		if (data == 1) { //Top
			uvTop = new UV[] { new UV(0/16f, 0/16f), new UV(16/16f, 0/16f), new UV(16/16f, 16/16f), new UV(0/16f, 16/16f) };
			uvSide = new UV[] { new UV(0/16f, 0/16f), new UV(16/16f, 0/16f), new UV(16/16f, 16/16f), new UV(0/16f, 16/16f) };
			uvSides = new UV[][] { uvTop, uvSide, uvSide, uvSide, uvSide, uvTop };
			addBox(obj,x-0.5f,y,z-0.5f,x+0.5f,y+0.5f,z+0.5f, null, mtls, uvSides, null);	
		}
		if (data == 2) { //North
			uvTop = new UV[] { new UV(0/16f, 0/16f), new UV(16/16f, 0/16f), new UV(16/16f, 16/16f), new UV(0/16f, 16/16f) };
			uvSide = new UV[] { new UV(0/16f, 0/16f), new UV(16/16f, 0/16f), new UV(16/16f, 16/16f), new UV(0/16f, 16/16f) };
			uvSides = new UV[][] { uvTop, uvSide, uvSide, uvSide, uvSide, uvTop };
			addBox(obj,x-0.5f,y-0.5f,z-0.5f,x+0.5f,y+0.5f,z, null, mtls, uvSides, null);	
		}
		if (data == 3) { //South
			uvTop = new UV[] { new UV(0/16f, 0/16f), new UV(16/16f, 0/16f), new UV(16/16f, 16/16f), new UV(0/16f, 16/16f) };
			uvSide = new UV[] { new UV(0/16f, 0/16f), new UV(16/16f, 0/16f), new UV(16/16f, 16/16f), new UV(0/16f, 16/16f) };
			uvSides = new UV[][] { uvTop, uvSide, uvSide, uvSide, uvSide, uvTop };
			addBox(obj,x-0.5f,y-0.5f,z,x+0.5f,y+0.5f,z+0.5f, null, mtls, uvSides, null);	
		}
		if (data == 4) { //West
			uvTop = new UV[] { new UV(0/16f, 0/16f), new UV(16/16f, 0/16f), new UV(16/16f, 16/16f), new UV(0/16f, 16/16f) };
			uvSide = new UV[] { new UV(0/16f, 0/16f), new UV(16/16f, 0/16f), new UV(16/16f, 16/16f), new UV(0/16f, 16/16f) };
			uvSides = new UV[][] { uvTop, uvSide, uvSide, uvSide, uvSide, uvTop };
			addBox(obj,x-0.5f,y-0.5f,z-0.5f,x,y+0.5f,z+0.5f, null, mtls, uvSides, null);	
		}
		if (data == 5) { //East
			uvTop = new UV[] { new UV(0/16f, 0/16f), new UV(16/16f, 0/16f), new UV(16/16f, 16/16f), new UV(0/16f, 16/16f) };
			uvSide = new UV[] { new UV(0/16f, 0/16f), new UV(16/16f, 0/16f), new UV(16/16f, 16/16f), new UV(0/16f, 16/16f) };
			uvSides = new UV[][] { uvTop, uvSide, uvSide, uvSide, uvSide, uvTop };
			addBox(obj,x,y-0.5f,z-0.5f,x+0.5f,y+0.5f,z+0.5f, null, mtls, uvSides, null);	
		}

	}

}
