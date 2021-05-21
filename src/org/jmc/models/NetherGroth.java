package org.jmc.models;

import org.jmc.geom.UV;
import org.jmc.threading.ChunkProcessor;
import org.jmc.threading.ThreadChunkDeligate;


/**
 * Model for slabs (aka half-blocks).
 */
public class NetherGroth extends BlockModel
{

	@Override
	public void addModel(ChunkProcessor obj, ThreadChunkDeligate chunks, int x, int y, int z, byte data, byte biome)
	{

		String[] mtls = getMtlSides(data, biome);
		UV[] uvTop, uvSide;
		UV[][] uvSides;
		
		
		
		for (int i = 0; i < 8; i++) {
			if (data == i) {
				uvTop = new UV[] { new UV(0/16f, 0/16f), new UV(16/16f, 0/16f), new UV(16/16f, 16/16f), new UV(0/16f, 16/16f) };
				uvSide = new UV[] { new UV(0/16f, 0/16f), new UV(16/16f, 0/16f), new UV(16/16f, (i+1)/16f), new UV(0/16f, (i+1)/16f) };
				uvSides = new UV[][] { uvTop, uvSide, uvSide, uvSide, uvSide, uvTop };
				
				addBox(obj,x-0.5f,y-0.5f,z-0.5f,x+0.5f,y-0.5f+((i+1)/16f),z+0.5f, null, mtls, uvSides, null);
			}

		}

	}

}
