package org.jmc.models;

import org.jmc.geom.Transform;
import org.jmc.threading.ChunkProcessor;
import org.jmc.threading.ThreadChunkDeligate;


/**
 * Generic model for cube blocks.
 */
public class Brick extends BlockModel
{
	
	@Override
	public void addModel(ChunkProcessor obj, ThreadChunkDeligate chunks, int x, int y, int z, byte data, byte biome)
	{
		byte[] rotated = new byte[] { 1,3,5,7,9,11,13,15 };
		byte[] notRotated = new byte[] { 0,2,4,6,8,10,12,14 };
				
		for (int i = 0; i < rotated.length; i++) {
			if (data == rotated[i]) {
				addBox(obj,
						x - 6/16f, y - 8/16f, z - 3/16f,
						x + 6/16f, y - 4/16f, z + 3/16f, 
						null, 
						getMtlSides(data,biome), 
						null, 
						drawSides(chunks, x, y, z));
			}
		}
		for (int i = 0; i < notRotated.length; i++) {
			if (data == notRotated[i]) {
				addBox(obj,
						x - 3/16f, y - 8/16f, z - 6/16f,
						x + 3/16f, y - 4/16f, z + 6/16f, 
						null, 
						getMtlSides(data,biome), 
						null, 
						drawSides(chunks, x, y, z));
			}
		}
		
	}

}
