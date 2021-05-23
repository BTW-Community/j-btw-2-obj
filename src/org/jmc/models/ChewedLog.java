package org.jmc.models;

import org.jmc.Chunk;
import org.jmc.threading.ChunkProcessor;
import org.jmc.threading.ThreadChunkDeligate;


/**
 * Generic model for cube blocks.
 */
public class ChewedLog extends BlockModel
{
	
	
	private void addYLip(ChunkProcessor obj, ThreadChunkDeligate chunks, int x, int y, int z, byte data, byte biome, int lipcount, int position)
	{
			
			if (lipcount > 0) {
				float xMin = x - 7/16f; 
				float xMax = x + 7/16f;
				
				float yMin = y - 8/16f;
				float yMax = y - 8/16f + 3/16f;
				
				if (position == 1) {
					yMin = y + 8/16f;
					yMax = y + 8/16f - 3/16f;
				}
				
				float zMin = z - 7/16f;
				float zMax = z + 7/16f;
				
				addBox(obj,
						xMin, yMin, zMin,
						xMax, yMax, zMax,
						null, 
						getMtlSides(data,biome), 
						null, 
						drawSides(chunks, x, y, z));
				if (lipcount > 1) {
					xMin = x - 6/16f; 
					xMax = x + 6/16f;
					
					yMin = y - 8/16f;
					yMax = y - 8/16f + 5/16f;
					
					if (position == 1) {
						yMin = y + 8/16f;
						yMax = y + 8/16f - 5/16f;
					}
					
					zMin = z - 6/16f;
					zMax = z + 6/16f;
					addBox(obj,
							xMin, yMin, zMin,
							xMax, yMax, zMax,
							null, 
							getMtlSides(data,biome), 
							null, 
							drawSides(chunks, x, y, z));
					if (lipcount > 2) {
						xMin = x - 5/16f; 
						xMax = x + 5/16f;
						
						yMin = y - 8/16f;
						yMax = y - 8/16f + 7/16f;
						
						if (position == 1) {
							yMin = y + 8/16f;
							yMax = y + 8/16f - 7/16f;
						}
						
						zMin = z - 5/16f;
						zMax = z + 5/16f;
						addBox(obj,
								xMin, yMin, zMin,
								xMax, yMax, zMax,
								null, 
								getMtlSides(data,biome), 
								null, 
								drawSides(chunks, x, y, z));
					}
				}
			}
			
			

	}

	private void addZLip(ChunkProcessor obj, ThreadChunkDeligate chunks, int x, int y, int z, byte data, byte biome, int lipcount, int position)
	{
			
			if (lipcount > 0) {
				float xMin = x - 7/16f; 
				float xMax = x + 7/16f;
				
				float zMin = z - 8/16f;
				float zMax = z - 8/16f + 3/16f;
				
				if (position == 1) {
					zMin = z + 8/16f;
					zMax = z + 8/16f - 3/16f;
				}
				
				float yMin = y - 7/16f;
				float yMax = y + 7/16f;
				
				addBox(obj,
						xMin, yMin, zMin,
						xMax, yMax, zMax,
						null, 
						getMtlSides(data,biome), 
						null, 
						drawSides(chunks, x, y, z));
				if (lipcount > 1) {
					xMin = x - 6/16f; 
					xMax = x + 6/16f;
					
					zMin = z - 8/16f;
					zMax = z - 8/16f + 5/16f;
					
					if (position == 1) {
						zMin = z + 8/16f;
						zMax = z + 8/16f - 5/16f;
					}
					
					yMin = y - 6/16f;
					yMax = y + 6/16f;
					addBox(obj,
							xMin, yMin, zMin,
							xMax, yMax, zMax,
							null, 
							getMtlSides(data,biome), 
							null, 
							drawSides(chunks, x, y, z));
					if (lipcount > 2) {
						xMin = x - 5/16f; 
						xMax = x + 5/16f;
						
						zMin = z - 8/16f;
						zMax = z - 8/16f + 7/16f;
						
						if (position == 1) {
							zMin = z + 8/16f;
							zMax = z + 8/16f - 7/16f;
						}
						
						yMin = y - 5/16f;
						yMax = y + 5/16f;
						addBox(obj,
								xMin, yMin, zMin,
								xMax, yMax, zMax,
								null, 
								getMtlSides(data,biome), 
								null, 
								drawSides(chunks, x, y, z));
					}
				}
			}
			
			

	}

	private void addXLip(ChunkProcessor obj, ThreadChunkDeligate chunks, int x, int y, int z, byte data, byte biome, int lipcount, int position)
	{
			
			if (lipcount > 0) {
				float zMin = z - 7/16f; 
				float zMax = z + 7/16f;
				
				float xMin = x - 8/16f;
				float xMax = x - 8/16f + 3/16f;
				
				if (position == 1) {
					xMin = x + 8/16f;
					xMax = x + 8/16f - 3/16f;
				}
				
				float yMin = y - 7/16f;
				float yMax = y + 7/16f;
				
				addBox(obj,
						xMin, yMin, zMin,
						xMax, yMax, zMax,
						null, 
						getMtlSides(data,biome), 
						null, 
						drawSides(chunks, x, y, z));
				if (lipcount > 1) {
					zMin = z - 6/16f; 
					zMax = z + 6/16f;
					
					xMin = x - 8/16f;
					xMax = x - 8/16f + 5/16f;
					
					if (position == 1) {
						xMin = x + 8/16f;
						xMax = x + 8/16f - 5/16f;
					}
					
					yMin = y - 6/16f;
					yMax = y + 6/16f;
					addBox(obj,
							xMin, yMin, zMin,
							xMax, yMax, zMax,
							null, 
							getMtlSides(data,biome), 
							null, 
							drawSides(chunks, x, y, z));
					if (lipcount > 2) {
						zMin = z - 5/16f; 
						zMax = z + 5/16f;
						
						xMin = x - 8/16f;
						xMax = x - 8/16f + 7/16f;
						
						if (position == 1) {
							xMin = x + 8/16f;
							xMax = x + 8/16f - 7/16f;
						}
						
						yMin = y - 5/16f;
						yMax = y + 5/16f;
						addBox(obj,
								xMin, yMin, zMin,
								xMax, yMax, zMax,
								null, 
								getMtlSides(data,biome), 
								null, 
								drawSides(chunks, x, y, z));
					}
				}
			}
			
			

	}

	@Override
	public void addModel(ChunkProcessor obj, ThreadChunkDeligate chunks, int x, int y, int z, byte data, byte biome)
	{
		int blockAbove = chunks.getBlockID(x, y + 1, z);
		int blockBelow = chunks.getBlockID(x, y - 1, z);
		int blockNorth = chunks.getBlockID(x, y, z + 1);
		int blockSouth = chunks.getBlockID(x, y, z - 1);
		int blockEast = chunks.getBlockID(x + 1, y, z);
		int blockWest = chunks.getBlockID(x - 1, y, z);
		
		int logID = 17; //Id of log
		
		
		byte[] metaY = new byte[] { //metadata where the log is vertical
				1,2,3
			};
		
		byte[] metaX = new byte[] { //metadata where the log is EW
				5,6,7
			};
		
		byte[] metaZ = new byte[] { //metadata where the log is NS
				9,10,11
			};
		
		for (int i = 0; i < metaY.length; i++) {
			if (data == metaY[i]) {
				//System.out.println(metaY[i]);
				if (blockBelow == logID) {
					addYLip(obj, chunks, x, y, z, data, biome, i + 1, 0);
				}
				if (blockAbove == logID) {
					addYLip(obj, chunks, x, y, z, data, biome, i + 1, 1);
				}
				
			}
			
		}
		
		for (int i = 0; i < metaX.length; i++) {
			if (data == i + 5) {
				//System.out.println(metaX[i]);
				
				if (blockEast == logID) {
					addXLip(obj, chunks, x, y, z, data, biome, i + 1, 1);
					//System.out.println("There is a block east");
				}
				if (blockWest == logID) {
					addXLip(obj, chunks, x, y, z, data, biome, i + 1, 0);
					//System.out.println("There is a block west");
				}
				
			}
			
		}
		
		for (int i = 0; i < metaZ.length; i++) {
			if (data == i + 9) {
				//System.out.println(metaZ[i]);
				System.out.println("BlocksZ");
				
				if (blockNorth == logID) {
					addZLip(obj, chunks, x, y, z, data, biome, i + 1, 1);
					System.out.println("There is a block nortj");
				}
				if (blockSouth == logID) {
					addZLip(obj, chunks, x, y, z, data, biome, i + 1, 0);
					System.out.println("There is a block south");
				}
				
			}
			
		}
		
		
		if (data == 0)
		{
			// Barkless Log (Up)
			addBox(obj,
					x - 7/16f, y - 8/16f, z - 7/16f,
					x + 7/16f, y + 8/16f, z + 7/16f, 
					null, 
					getMtlSides(data,biome), 
					null, 
					drawSides(chunks, x, y, z));
		}
		if (data == 1)
		{
			// -2 Layers Log (Up)
			addBox(obj,
					x - 6/16f, y - 8/16f, z - 6/16f,
					x + 6/16f, y + 8/16f, z + 6/16f, 
					null, 
					getMtlSides(data,biome), 
					null, 
					drawSides(chunks, x, y, z));
			
		}
		if (data == 2)
		{
			// -3 Layers Log (Up)
			addBox(obj,
					x - 5/16f, y - 8/16f, z - 5/16f,
					x + 5/16f, y + 8/16f, z + 5/16f, 
					null, 
					getMtlSides(data,biome), 
					null, 
					drawSides(chunks, x, y, z));
		}
		if (data == 3)
		{
			// -4 Layers Log (Up)
			addBox(obj,
					x - 4/16f, y - 8/16f, z - 4/16f,
					x + 4/16f, y + 8/16f, z + 4/16f, 
					null, 
					getMtlSides(data,biome), 
					null, 
					drawSides(chunks, x, y, z));
		}
		if (data == 4)
		{
			// Barkless Log (X)
			addBox(obj,
					x - 8/16f, y - 7/16f, z - 7/16f,
					x + 8/16f, y + 7/16f, z + 7/16f, 
					null, 
					getMtlSides(data,biome), 
					null, 
					drawSides(chunks, x, y, z));
		}
		if (data == 5)
		{
			// -2 Layers Log (X)
			addBox(obj,
					x - 8/16f, y - 6/16f, z - 6/16f,
					x + 8/16f, y + 6/16f, z + 6/16f, 
					null, 
					getMtlSides(data,biome), 
					null, 
					drawSides(chunks, x, y, z));
		}
		if (data == 6)
		{
			// -3 Layers Log (X)
			addBox(obj,
					x - 8/16f, y - 5/16f, z - 5/16f,
					x + 8/16f, y + 5/16f, z + 5/16f, 
					null, 
					getMtlSides(data,biome), 
					null, 
					drawSides(chunks, x, y, z));
		}
		if (data == 7)
		{
			// -4 Layers Log (X)
			addBox(obj,
					x - 8/16f, y - 4/16f, z - 4/16f,
					x + 8/16f, y + 4/16f, z + 4/16f, 
					null, 
					getMtlSides(data,biome), 
					null, 
					drawSides(chunks, x, y, z));
		}
		if (data == 8)
		{
			// Barkless Log (Z)
			System.out.println("Barkless Z");
		
			addBox(obj,
					x - 7/16f, y - 7/16f, z - 8/16f,
					x + 7/16f, y + 7/16f, z + 8/16f, 
					null, 
					getMtlSides(data,biome), 
					null, 
					drawSides(chunks, x, y, z));
		}
		if (data == 9)
		{
			System.out.println("-2 Layers Z");
			// -2 Layers Log (Z)
			addBox(obj,
					x - 6/16f, y - 6/16f, z - 8/16f,
					x + 6/16f, y + 6/16f, z + 8/16f, 
					null, 
					getMtlSides(data,biome), 
					null, 
					drawSides(chunks, x, y, z));
		}
		if (data == 10)
		{
			System.out.println("-3 Layers Z");
			// -3 Layers Log (Z)
			addBox(obj,
					x - 5/16f, y - 5/16f, z - 8/16f,
					x + 5/16f, y + 5/16f, z + 8/16f, 
					null, 
					getMtlSides(data,biome), 
					null, 
					drawSides(chunks, x, y, z));
		}
		if (data == 11)
		{
			System.out.println("-4 Layers Z");
			// -4 Layers Log (Z)
			addBox(obj,
					x - 4/16f, y - 4/16f, z - 8/16f,
					x + 4/16f, y + 4/16f, z + 8/16f, 
					null, 
					getMtlSides(data,biome), 
					null, 
					drawSides(chunks, x, y, z));
		}
	}

}
