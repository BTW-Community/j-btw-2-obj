package org.jmc.models;

import org.jmc.geom.UV;
import org.jmc.threading.ChunkProcessor;
import org.jmc.threading.ThreadChunkDeligate;


/**
 * Generic model for cube blocks.
 */
public class Bellows extends BlockModel
{
	
	@Override
	public void addModel(ChunkProcessor obj, ThreadChunkDeligate chunks, int x, int y, int z, byte data, byte biome)
	{
		UV[] uvTop, uvSide;
		UV[][] uvSides;
		
		
		
		if (data < 4) { //full
		addBox(obj,
				x - 0.5f, y - 0.5f, z - 0.5f,
				x + 0.5f, y + 0.5f, z + 0.5f, 
				null, 
				getMtlSides(data,biome), 
				null, 
				drawSides(chunks, x, y, z));
		}
		else { //half
			
			uvTop = new UV[] { new UV(0/16f, 0/16f), new UV(16/16f, 0/16f), new UV(16/16f, 16/16f), new UV(0/16f, 16/16f) };
			uvSide = new UV[] { new UV(0/16f, 0/16f), new UV(16/16f, 0/16f), new UV(16/16f, 11/16f), new UV(0/16f, 11/16f) };
			uvSides = new UV[][] { uvTop, uvSide, uvSide, uvSide, uvSide, uvTop };
			
			addBox(obj,
					x - 0.5f, y - 0.5f, z - 0.5f,
					x + 0.5f, y + 3/16f, z + 0.5f, 
					null, 
					getMtlSides(data,biome), 
					uvSides, 
					drawSides(chunks, x, y, z));
			}
	}

}
