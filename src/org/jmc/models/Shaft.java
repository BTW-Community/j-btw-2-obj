package org.jmc.models;

import org.jmc.threading.ChunkProcessor;
import org.jmc.threading.ThreadChunkDeligate;


/**
 * Generic model for cube blocks.
 */
public class Shaft extends BlockModel
{
	
	@Override
	public void addModel(ChunkProcessor obj, ThreadChunkDeligate chunks, int x, int y, int z, byte data, byte biome)
	{
		int blockAbove = chunks.getBlockID(x, y + 1, z);
		
		if (data==0 && blockAbove == 144) { //is skull
			addBox(obj, 
					x - 1/16f, y + 4/16f, z - 1/16f,
					x + 1/16f, y + 8/16f, z + 1/16f,
					null, 
					getMtlSides(data,biome), 
					null, 
					drawSides(chunks, x, y, z));
			
		}
		if (data==0) {	//up
			addBox(obj, 
					x - 1/16f, y - 8/16f, z - 1/16f,
					x + 1/16f, y + 4/16f, z + 1/16f,
					null, 
					getMtlSides(data,biome), 
					null, 
					drawSides(chunks, x, y, z));
		}
		if (data==1) {	//down
			addBox(obj, 
					x - 1/16f, y - 4/16f, z - 1/16f,
					x + 1/16f, y + 8/16f, z + 1/16f,
					null, 
					getMtlSides(data,biome), 
					null, 
					drawSides(chunks, x, y, z));
		}
		if (data==4) {	//east
			addBox(obj, 
					x - 8/16f, y - 1/16f, z - 1/16f,
					x + 4/16f, y + 1/16f, z + 1/16f,
					null, 
					getMtlSides(data,biome), 
					null, 
					drawSides(chunks, x, y, z));
		}
		if (data==5) {	//west
			addBox(obj, 
					x - 4/16f, y - 1/16f, z - 1/16f,
					x + 8/16f, y + 1/16f, z + 1/16f,
					null, 
					getMtlSides(data,biome), 
					null, 
					drawSides(chunks, x, y, z));
		}
		if (data==2) {	//north
			addBox(obj, 
					x - 1/16f, y - 1/16f, z - 8/16f,
					x + 1/16f, y + 1/16f, z + 4/16f,
					null, 
					getMtlSides(data,biome), 
					null, 
					drawSides(chunks, x, y, z));
		}
		if (data==3) {	//south
			addBox(obj, 
					x - 1/16f, y - 1/16f, z - 4/16f,
					x + 1/16f, y + 1/16f, z + 8/16f,
					null, 
					getMtlSides(data,biome), 
					null, 
					drawSides(chunks, x, y, z));
		}
	}

}
