package org.jmc.models;

import org.jmc.geom.UV;
import org.jmc.threading.ChunkProcessor;
import org.jmc.threading.ThreadChunkDeligate;


/**
 * Model for slabs (aka half-blocks).
 */
public class WoolSlab extends BlockModel
{

	@Override
	public void addModel(ChunkProcessor obj, ThreadChunkDeligate chunks, int x, int y, int z, byte data, byte biome)
	{
		boolean[] drawSides = drawSides(chunks, x, y, z);
		UV[] uvSide = null;
		UV[][] uvSides;
		float ys = 0, ye = 0;
		
		if (chunks.getBlockID(x, y, z) == 220 ) 
		{
			// slab occupies the lower half
			drawSides[0] = true;
			uvSide = new UV[] { new UV(0,0), new UV(1,0), new UV(1,0.5f), new UV(0,0.5f) };
			ys = -0.5f;
			ye = 0.0f;
		}
		else if (chunks.getBlockID(x, y, z) == 205 ) 
		{
			// slab occupies the upper half
			drawSides[5] = true;
			uvSide = new UV[] { new UV(0,0.5f), new UV(1,0.5f), new UV(1,1), new UV(0,1) };
			ys = 0.0f;
			ye = 0.5f;
			
			data = (byte)(data & 0x7);
		}

		uvSides = new UV[][] { null, uvSide, uvSide, uvSide, uvSide, null };
		
		addBox(obj,
				x - 0.5f, y + ys, z - 0.5f,
				x + 0.5f, y + ye, z + 0.5f, 
				null, 
				getMtlSides(data,biome),
				uvSides, 
				drawSides);
	}

}
