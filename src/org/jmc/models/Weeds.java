package org.jmc.models;

import org.jmc.geom.Vertex;
import org.jmc.threading.ChunkProcessor;
import org.jmc.threading.ThreadChunkDeligate;


/**
 * Generic model for blocks rendered as 4 crossed polygons, like crops.
 */
public class Weeds extends BlockModel
{

	@Override
	public void addModel(ChunkProcessor obj, ThreadChunkDeligate chunks, int x, int y, int z, byte data, byte biome)
	{
		Vertex[] vertices = new Vertex[4];
		String mtl = getMtlSides(data, biome)[0];
		short blockBelow = chunks.getBlockID(x, y-1, z);
		byte metaBelow = chunks.getBlockData(x, y-1, z);
		
		byte stage1[] = { 2, 3 };
		byte stage2[] = { 4, 5, 6, 7 };
		byte stage3[] = { 8, 9, 10, 11 };
		byte stage4[] = { 12, 13, 14, 15 };
		
		if (blockBelow == 1087 || blockBelow == 1088) {
			
			// front
			vertices[0] = new Vertex(x+0.5f, y-0.5f, z-6/16f);
			vertices[1] = new Vertex(x-0.5f, y-0.5f, z-6/16f);
			vertices[2] = new Vertex(x-0.5f, y+0.5f, z-6/16f);
			vertices[3] = new Vertex(x+0.5f, y+0.5f, z-6/16f);
			obj.addFace(vertices, null, null, mtl);
	
			// back
			vertices[0] = new Vertex(x-0.5f, y-0.5f, z+6/16f);
			vertices[1] = new Vertex(x+0.5f, y-0.5f, z+6/16f);
			vertices[2] = new Vertex(x+0.5f, y+0.5f, z+6/16f);
			vertices[3] = new Vertex(x-0.5f, y+0.5f, z+6/16f);
			obj.addFace(vertices, null, null, mtl);
	
			// left
			vertices[0] = new Vertex(x-6/16f, y-0.5f, z-0.5f);
			vertices[1] = new Vertex(x-6/16f, y-0.5f, z+0.5f);
			vertices[2] = new Vertex(x-6/16f, y+0.5f, z+0.5f);
			vertices[3] = new Vertex(x-6/16f, y+0.5f, z-0.5f);
			obj.addFace(vertices, null, null, mtl);
	
			// right
			vertices[0] = new Vertex(x+6/16f, y-0.5f, z-0.5f);
			vertices[1] = new Vertex(x+6/16f, y-0.5f, z+0.5f);
			vertices[2] = new Vertex(x+6/16f, y+0.5f, z+0.5f);
			vertices[3] = new Vertex(x+6/16f, y+0.5f, z-0.5f);
			obj.addFace(vertices, null, null, mtl);
		}
	}
		
		
	

}
