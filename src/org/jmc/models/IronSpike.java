package org.jmc.models;

import org.jmc.geom.Transform;
import org.jmc.geom.UV;
import org.jmc.threading.ChunkProcessor;
import org.jmc.threading.ThreadChunkDeligate;


/**
 * Model for Iron Spike and Lightning Rod
 */
public class IronSpike extends BlockModel
{
	
	
	
	@Override
	public void addModel(ChunkProcessor obj, ThreadChunkDeligate chunks, int x, int y, int z, byte data, byte biome)
	{
		String[] mtlSides = getMtlSides(data, biome);
		UV[] uvTop, uvSide;
		UV[][] uvSides;
		
		uvTop = new UV[] { new UV(0/16f, 0/16f), new UV(16/16f, 0/16f), new UV(16/16f, 16/16f), new UV(0/16f, 16/16f) };
		uvSide = new UV[] { new UV(0/16f, 0), new UV(16/16f, 0), new UV(16/16f, 1), new UV(0/16f, 1) };
		uvSides = new UV[][] { uvTop, uvSide, uvSide, uvSide, uvSide, uvTop };
		
		short thisBlockID = chunks.getBlockID(x, y, z);
		byte thisData = chunks.getBlockData(x, y, z);
		
		short blockIDAbove = chunks.getBlockID(x, y + 1, z);
		short blockIDBelow = chunks.getBlockID(x, y - 1, z);
		
		short blockIDNorth = chunks.getBlockID(x, y, z - 1);
		short blockIDSouth = chunks.getBlockID(x, y, z + 1);
		short blockIDWest = chunks.getBlockID(x - 1, y, z);
		short blockIDEast = chunks.getBlockID(x + 1, y, z);
		
		byte dataAbove = chunks.getBlockData(x, y + 1, z);
		byte dataBelow = chunks.getBlockData(x, y - 1, z);
		byte dataNorth = chunks.getBlockData(x, y, z - 1);
		byte dataSouth = chunks.getBlockData(x, y, z + 1);
		
		byte centerNub[] = { 2, 3, 4, 5 };
		byte topNub[] = { 0, 6 };
		
		if (blockIDAbove == thisBlockID)
		
		
		if (blockIDSouth == thisBlockID && dataSouth > 1) 
		{
			//Side Bar (South)
			addBox(obj, 
					x - 1/32f, y - 1/16f, z + 0/16f,
					x + 1/32f, y + 1/16f, z + 8/16f,
					null, mtlSides, null, null);
			
			
		}
		
		for (int i = 0; i < centerNub.length; i++) {
			if (data == centerNub[i]) {
				//Nub (Center)
				addBox(obj, 
						x - 3/32f, y - 2/16f, z - 3/32f,
						x + 3/32f, y + 2/16f, z + 3/32f,
						null, mtlSides, null, null);
			}
		}
		
		for (int i = 0; i < topNub.length; i++) {
			if (data == topNub[i]) {
				//Nub (top)
				addBox(obj, 
						x - 3/32f, y + 2/16f, z - 3/32f,
						x + 3/32f, y + 5/16f, z + 3/32f,
						null, mtlSides, null, null);	
			}
		}
		
		//Rod
		addBox(obj, 
				x - 1/32f, y - 8/16f, z - 1/32f,
				x + 1/32f, y + 8/16f, z + 1/32f,
				null, mtlSides, null, null);	

		
		if (data == 0) {
	
		//Foot (bottom)
		addBox(obj, 
				x - 2/16f, y - 8/16f, z - 2/16f,
				x + 2/16f, y - 7/16f, z + 2/16f,
				null, mtlSides, null, null);	

		}	
		if (data == 1) {
			
			//Foot (top)
			addBox(obj, 
					x - 2/16f, y + 7/16f, z - 2/16f,
					x + 2/16f, y + 8/16f, z + 2/16f,
					null, mtlSides, null, null);	
			
			//Nub (bottom)
			addBox(obj, 
					x - 3/32f, y - 2/16f, z - 3/32f,
					x + 3/32f, y - 5/16f, z + 3/32f,
					null, mtlSides, null, null);	
		}
		
		

		
		
		if (data == 2) {
			//Side Bar (North)
			addBox(obj, 
					x - 1/32f, y - 1/16f, z + 0/16f,
					x + 1/32f, y + 1/16f, z - 8/16f,
					null, mtlSides, null, null);
		}
		
		if (data == 3) {
			//Side Bar (South)
			addBox(obj, 
					x - 1/32f, y - 1/16f, z + 0/16f,
					x + 1/32f, y + 1/16f, z + 8/16f,
					null, mtlSides, null, null);
		}
		
		if (data == 4) {
			//Side Bar (West)
			addBox(obj, 
					x - 8/16f, y - 1/16f, z - 1/32f,
					x + 0/16f, y + 1/16f, z + 1/32f,
					null, mtlSides, null, null);
		}
		
		if (data == 5) {
			//Side Bar (East)
			addBox(obj, 
					x - 0/16f, y - 1/16f, z - 1/32f,
					x + 8/16f, y + 1/16f, z + 1/32f,
					null, mtlSides, null, null);
		}
		
		if (data == 6) {
			//Nub (top)
			addBox(obj, 
					x - 3/32f, y + 2/16f, z - 3/32f,
					x + 3/32f, y + 5/16f, z + 3/32f,
					null, mtlSides, null, null);
		}
		
		
	}
}
