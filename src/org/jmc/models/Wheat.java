package org.jmc.models;

import org.jmc.geom.Vertex;
import org.jmc.threading.ChunkProcessor;
import org.jmc.threading.ThreadChunkDeligate;


/**
 * Generic model for blocks rendered as 4 crossed polygons, like crops.
 */
public class Wheat extends BlockModel
{

	@Override
	public void addModel(ChunkProcessor obj, ThreadChunkDeligate chunks, int x, int y, int z, byte data, byte biome)
	{
		Vertex[] vertices = new Vertex[4];
		String mtl = materials.get(data, biome)[0];
		byte dataAbove = chunks.getBlockData(x, y +1, z);
		
		if (data == 7) {
			if (chunks.getBlockID(x, y +1, z) == 1090) { //if it has wheat top
				if (dataAbove == 0 || dataAbove == 8) {
					mtl = "crops_wheat_7_0";
				} else if (dataAbove == 1 || dataAbove == 9) {
					mtl = "crops_wheat_7_1";
				} else if (dataAbove == 2 || dataAbove == 10) {
					mtl = "crops_wheat_7_2";
				} else if (dataAbove == 3 || dataAbove == 11) {
					mtl = "crops_wheat_7_3";
				}
			} else {
				mtl = materials.get(data, biome)[0];
			}
		};
		
		// front
		vertices[0] = new Vertex(x+0.5f, y-0.5f - 1/16f, z-0.25f);
		vertices[1] = new Vertex(x-0.5f, y-0.5f - 1/16f, z-0.25f);
		vertices[2] = new Vertex(x-0.5f, y+0.5f - 1/16f, z-0.25f);
		vertices[3] = new Vertex(x+0.5f, y+0.5f - 1/16f, z-0.25f);
		obj.addFace(vertices, null, null, mtl);

		// back
		vertices[0] = new Vertex(x-0.5f, y-0.5f - 1/16f, z+0.25f);
		vertices[1] = new Vertex(x+0.5f, y-0.5f - 1/16f, z+0.25f);
		vertices[2] = new Vertex(x+0.5f, y+0.5f - 1/16f, z+0.25f);
		vertices[3] = new Vertex(x-0.5f, y+0.5f - 1/16f, z+0.25f);
		obj.addFace(vertices, null, null, mtl);

		// left
		vertices[0] = new Vertex(x-0.25f, y-0.5f - 1/16f, z-0.5f);
		vertices[1] = new Vertex(x-0.25f, y-0.5f - 1/16f, z+0.5f);
		vertices[2] = new Vertex(x-0.25f, y+0.5f - 1/16f, z+0.5f);
		vertices[3] = new Vertex(x-0.25f, y+0.5f - 1/16f, z-0.5f);
		obj.addFace(vertices, null, null, mtl);

		// right
		vertices[0] = new Vertex(x+0.25f, y-0.5f - 1/16f, z-0.5f);
		vertices[1] = new Vertex(x+0.25f, y-0.5f - 1/16f, z+0.5f);
		vertices[2] = new Vertex(x+0.25f, y+0.5f - 1/16f, z+0.5f);
		vertices[3] = new Vertex(x+0.25f, y+0.5f - 1/16f, z-0.5f);
		obj.addFace(vertices, null, null, mtl);
	}

}
