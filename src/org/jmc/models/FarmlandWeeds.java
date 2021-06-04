package org.jmc.models;

import org.jmc.geom.UV;
import org.jmc.geom.Vertex;
import org.jmc.threading.ChunkProcessor;
import org.jmc.threading.ThreadChunkDeligate;


/**
 * Model for farmland.
 */
public class FarmlandWeeds extends BlockModel
{
	
	@Override
	protected String[] getMtlSides(byte data, byte biome)
	{
		String[] abbrMtls = materials.get(data,biome);

		String[] mtlSides = new String[6];
		mtlSides[0] = data == 0 ? abbrMtls[0] : abbrMtls[1];
		mtlSides[1] = abbrMtls[2];
		mtlSides[2] = abbrMtls[2];
		mtlSides[3] = abbrMtls[2];
		mtlSides[4] = abbrMtls[2];
		mtlSides[5] = abbrMtls[2];
		return mtlSides;
	}
	

	@Override
	public void addModel(ChunkProcessor obj, ThreadChunkDeligate chunks, int x, int y, int z, byte data, byte biome)
	{
		boolean[] drawSides = drawSides(chunks, x, y, z);
		
		UV[] uvSide = new UV[] { new UV(0,0), new UV(1,0), new UV(1,15/16f), new UV(0,15/16f) };
		UV[][] uvSides = new UV[][] { null, uvSide, uvSide, uvSide, uvSide, null };

		addBox(obj,
				x-0.5f, y-0.5f, z-0.5f,
				x+0.5f, y+0.4375f, z+0.5f, 
				null, 
				getMtlSides(data,biome), 
				uvSides, 
				drawSides);
		
		byte metaWeeds1[] = { 2, 3 };
		byte metaWeeds2[] = { 4, 5, 6, 7 };
		byte metaWeeds3[] = { 8, 9, 10, 11 };
		byte metaWeeds4[] = { 12, 13, 14, 15 };
		
		String mtl = "weeds_0";
		
		for (int i = 0; i < metaWeeds1.length; i++) {
			if (data == metaWeeds1[i]) {
				mtl = "weeds_0";
			}
			
		}
		
		for (int i = 0; i < metaWeeds2.length; i++) {
			if (data == metaWeeds2[i]) {
				mtl = "weeds_1";
			}
			
		}
		
		for (int i = 0; i < metaWeeds3.length; i++) {
			if (data == metaWeeds3[i]) {
				mtl = "weeds_2";
			}
			
		}
		
		for (int i = 0; i < metaWeeds4.length; i++) {
			if (data == metaWeeds4[i]) {
				mtl = "weeds_2";
			}
			
		}
		
		if (data > 1) {
			Vertex[] vertices = new Vertex[4];
			
			// front
			vertices[0] = new Vertex(x+0.5f, y-0.5f +1.0f - 1/16f, z-6/16f);
			vertices[1] = new Vertex(x-0.5f, y-0.5f +1.0f - 1/16f, z-6/16f);
			vertices[2] = new Vertex(x-0.5f, y+0.5f +1.0f - 1/16f, z-6/16f);
			vertices[3] = new Vertex(x+0.5f, y+0.5f +1.0f - 1/16f, z-6/16f);
			obj.addFace(vertices, null, null, mtl);
			
			// back
			vertices[0] = new Vertex(x-0.5f, y-0.5f +1.0f - 1/16f, z+6/16f);
			vertices[1] = new Vertex(x+0.5f, y-0.5f +1.0f - 1/16f, z+6/16f);
			vertices[2] = new Vertex(x+0.5f, y+0.5f +1.0f - 1/16f, z+6/16f);
			vertices[3] = new Vertex(x-0.5f, y+0.5f +1.0f - 1/16f, z+6/16f);
			obj.addFace(vertices, null, null, mtl);
			
			// left
			vertices[0] = new Vertex(x-6/16f, y-0.5f +1.0f - 1/16f, z-0.5f);
			vertices[1] = new Vertex(x-6/16f, y-0.5f +1.0f - 1/16f, z+0.5f);
			vertices[2] = new Vertex(x-6/16f, y+0.5f +1.0f - 1/16f, z+0.5f);
			vertices[3] = new Vertex(x-6/16f, y+0.5f +1.0f - 1/16f, z-0.5f);
			obj.addFace(vertices, null, null, mtl);
			
			// right
			vertices[0] = new Vertex(x+6/16f, y-0.5f +1.0f - 1/16f, z-0.5f);
			vertices[1] = new Vertex(x+6/16f, y-0.5f +1.0f - 1/16f, z+0.5f);
			vertices[2] = new Vertex(x+6/16f, y+0.5f +1.0f - 1/16f, z+0.5f);
			vertices[3] = new Vertex(x+6/16f, y+0.5f +1.0f - 1/16f, z-0.5f);
			obj.addFace(vertices, null, null, mtl);
		}
	}

}
