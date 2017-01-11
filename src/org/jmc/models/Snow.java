package org.jmc.models;

import org.jmc.geom.UV;
import org.jmc.threading.ChunkProcessor;
import org.jmc.threading.ThreadChunkDeligate;


/**
 * Model for ground snow.
 */
public class Snow extends BlockModel
{

	@Override
	public void addModel(ChunkProcessor obj, ThreadChunkDeligate chunks, int x, int y, int z, byte data, byte biome)
	{
		boolean[] drawSides = drawSides(chunks, x, y, z);
		drawSides[0] = true;
		
		if (data > 8)
			data -= 8;
		float height = (data+1) / 8.0f;
		
		//Check to see if there's a slab beneath the snow layer.
		short BlockBelowID = chunks.getBlockID(x, y+1, z);
		byte BlockBelowData = chunks.getBlockData(x, y+1, z);
		boolean isLowSlab = false;
		// VMC Stone Slabs       VMC Wood Slabs
		if(BlockBelowID == 44 || BlockBelowID == 126)
			if((BlockBelowData & 0x8) == 0)
				isLowSlab = true;
		
		//Gravel/Sand 			  Wool Slabs (Bottom)
		else if(BlockBelowID == 175 || BlockBelowID == 220)
			isLowSlab = true;
		
		//Companion Slab
		else if(BlockBelowID == 237)
			if(BlockBelowData == 1)
				isLowSlab = true;
			
		
		//Wicker/WhiteCobble
		else if(BlockBelowID == 216)
			if(BlockBelowData == 0x5 || BlockBelowData == 0x10)
				isLowSlab = true;
		
		UV[] uvSide = new UV[] { new UV(0,0), new UV(1,0), new UV(1,height), new UV(0,height) };
		UV[][] uvSides = new UV[][] { null, uvSide, uvSide, uvSide, uvSide, null };
		
		//If the slab is the bottom variety, drop the snow layer 1/2 blocks.
		if(isLowSlab)
			y -= .5;
		// LongGrass/Shrubs		   Yellow Flower		 Rose
		else if(BlockBelowData == 31 || BlockBelowID == 37 || BlockBelowID == 38 ||
		//      Brown Mushroom			Red Mushroom
				BlockBelowData == 39 || BlockBelowID == 40 )
			y -= 1;
		
		addBox(obj,
				x-0.5f, y-0.5f, z-0.5f,
				x+0.5f, y-0.5f+height, z+0.5f, 
				null, 
				getMtlSides(data,biome), 
				uvSides, 
				drawSides);
	}

}
