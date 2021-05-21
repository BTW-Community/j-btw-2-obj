package org.jmc.models;

import java.util.HashSet;

import org.jmc.geom.UV;
import org.jmc.threading.ChunkProcessor;
import org.jmc.threading.ThreadChunkDeligate;


/**
 * Model for fences.
 */
public class Candle extends BlockModel
{
	
	private String[] getMtlSidesCandle(byte data, byte biome)
	{
		String[] abbrMtls = materials.get(data,biome);

		String[] mtlSides = new String[6];
		mtlSides[0] = abbrMtls[0];
		mtlSides[1] = abbrMtls[0];
		mtlSides[2] = abbrMtls[0];
		mtlSides[3] = abbrMtls[0];
		mtlSides[4] = abbrMtls[0];
		mtlSides[5] = abbrMtls[0];
		return mtlSides;
	}

	private String[] getMtlSidesWick(byte data, byte biome)
	{
		String[] abbrMtls = materials.get(data,biome);

		String[] mtlSides = new String[6];
		mtlSides[0] = abbrMtls[1];
		mtlSides[1] = abbrMtls[1];
		mtlSides[2] = abbrMtls[1];
		mtlSides[3] = abbrMtls[1];
		mtlSides[4] = abbrMtls[1];
		mtlSides[5] = abbrMtls[1];
		return mtlSides;
	}
	
	@Override
	public void addModel(ChunkProcessor obj, ThreadChunkDeligate chunks, int x, int y, int z, byte data, byte biome)
	{
		UV[] uvTop, uvSide;
		UV[][] uvSides;
		

		uvTop = new UV[] { new UV(6/16f, 6/16f), new UV(10/16f, 6/16f), new UV(10/16f, 10/16f), new UV(6/16f, 10/16f) };
		uvSide = new UV[] { new UV(6/16f, 0), new UV(10/16f, 0), new UV(10/16f, 1), new UV(6/16f, 1) };
		uvSides = new UV[][] { uvTop, uvSide, uvSide, uvSide, uvSide, uvTop };
		addBox(obj, x-1/16f,y-8/16f,z-1/16f,x+1/16f,y-1/16f,z+1/16f, null, getMtlSidesCandle(data,biome), uvSides, null);
		addBox(obj, x+1/64f, y-1/16f, z+1/64f ,x-1/64f ,y, z-1/64f ,null, getMtlSidesWick(data,biome), uvSides, null);
	}

}
