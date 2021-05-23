package org.jmc.models;

import org.jmc.geom.UV;
import org.jmc.threading.ChunkProcessor;
import org.jmc.threading.ThreadChunkDeligate;


/**
 * Generic model for cube blocks.
 */
public class Rope extends BlockModel
{
	
	@Override
	public void addModel(ChunkProcessor obj, ThreadChunkDeligate chunks, int x, int y, int z, byte data, byte biome)
	{
		UV[] uvTop, uvSide;
		UV[][] uvSides;
		
		uvTop = new UV[] { new UV(7/16f, 7/16f), new UV(9/16f, 7/16f), new UV(7/16f, 9/16f), new UV(9/16f, 9/16f) };
		uvSide = new UV[] { new UV(7/16f, 0/16f), new UV(9/16f, 0/16f), new UV(7/16f, 16/16f), new UV(9/16f, 16/16f) };
		uvSides = new UV[][] { uvTop, uvSide, uvSide, uvSide, uvSide, uvTop };
		
		addBox(obj,
				x - 1/16f, y - 0.5f, z - 1/16f,
				x + 1/16f, y + 0.5f, z + 1/16f, 
				null, 
				getMtlSides(data,biome), 
				uvSides, 
				drawSides(chunks, x, y, z));
		
		int anchorID = chunks.getBlockID(x, y +1, z);
		
		if (anchorID == 244) {
			addBox(obj,
					x - 1/16f, y + 0.5f, z - 1/16f,
					x + 1/16f, y + 0.5f + 6/16f, z + 1/16f, 
					null, 
					getMtlSides(data,biome), 
					uvSides, 
					drawSides(chunks, x, y, z));
		}
	}

}
