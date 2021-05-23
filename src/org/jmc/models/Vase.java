package org.jmc.models;

import org.jmc.threading.ChunkProcessor;
import org.jmc.threading.ThreadChunkDeligate;


/**
 * Generic model for cube blocks.
 */
public class Vase extends BlockModel
{
	
	@Override
	public void addModel(ChunkProcessor obj, ThreadChunkDeligate chunks, int x, int y, int z, byte data, byte biome)
	{
		addBox(obj,
				x - 4/16f, y - 8/16f, z - 4/16f,
				x + 4/16f, y - 7/16f, z + 4/16f, 
				null, 
				getMtlSides(data,biome), 
				null, 
				drawSides(chunks, x, y, z));
		addBox(obj,
				x - 5/16f, y - 7/16f, z - 5/16f,
				x + 5/16f, y - 1/16f, z + 5/16f, 
				null, 
				getMtlSides(data,biome), 
				null, 
				drawSides(chunks, x, y, z));
		addBox(obj,
				x - 4/16f, y - 1/16f, z - 4/16f,
				x + 4/16f, y - 0/16f, z + 4/16f, 
				null, 
				getMtlSides(data,biome), 
				null, 
				drawSides(chunks, x, y, z));
		addBox(obj,
				x - 2/16f, y - 0/16f, z - 2/16f,
				x + 2/16f, y + 7/16f, z + 2/16f, 
				null, 
				getMtlSides(data,biome), 
				null, 
				drawSides(chunks, x, y, z));
		addBox(obj,
				x - 3/16f, y + 7/16f, z - 3/16f,
				x + 3/16f, y + 8/16f, z + 3/16f, 
				null, 
				getMtlSides(data,biome), 
				null, 
				drawSides(chunks, x, y, z));
	}

}
