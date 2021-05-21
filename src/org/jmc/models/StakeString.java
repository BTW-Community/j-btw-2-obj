package org.jmc.models;

import java.util.HashSet;

import org.jmc.geom.UV;
import org.jmc.threading.ChunkProcessor;
import org.jmc.threading.ThreadChunkDeligate;


/**
 * Model for cobblestone walls.
 */
public class StakeString extends BlockModel
{
	/** Blocks to which walls will connect */
	private static HashSet<Short> connectable;
	
	static
	{
		short[] ids = new short[] {
				194 //the stake itself, not the string
			};
		
		connectable = new HashSet<Short>(ids.length);
		for (int i = 0; i < ids.length; i++)
			connectable.add(ids[i]);
	}
	
	private boolean checkConnect(ThreadChunkDeligate chunks, byte data, int x, int y, int z)
	{
		int otherId = chunks.getBlockID(x, y, z);
		if (connectable.contains(otherId))
			return true;

		int otherData = chunks.getBlockData(x, y, z);
		return otherId == this.blockId && otherData == data;
	}
	
	
	@Override
	public void addModel(ChunkProcessor obj, ThreadChunkDeligate chunks, int x, int y, int z, byte data, byte biome)
	{
		String[] mtls = getMtlSides(data, biome);
		UV[] uvTop, uvSide;
		UV[][] uvSides;

		boolean conn_n = checkConnect(chunks, data, x, y, z-1);
		boolean conn_s = checkConnect(chunks, data, x, y, z+1);
		boolean conn_e = checkConnect(chunks, data, x-1, y, z);
		boolean conn_w = checkConnect(chunks, data, x+1, y, z);
		boolean conn_u = checkConnect(chunks, data, x, y+1, z);
		boolean conn_d = checkConnect(chunks, data, x, y-1, z);
		
		//1 E/W
		//2 Vertical
		//4 N/S
		
		//string E/W
		if (data == 1) {
			uvTop = new UV[] { new UV(4/16f, 4/16f), new UV(12/16f, 4/16f), new UV(12/16f, 12/16f), new UV(4/16f, 12/16f) };
			uvSide = new UV[] { new UV(4/16f, 0), new UV(12/16f, 0), new UV(12/16f, 1), new UV(4/16f, 1) };
			uvSides = new UV[][] { uvTop, uvSide, uvSide, uvSide, uvSide, uvTop };
			addBox(obj,x -0.5f,y-1/128f,z-1/128f,x+0.5f,y+1/128f,z+1/128f, null, mtls, uvSides, null);
			
			
		}
		if (conn_e && data == 1) {
			uvTop = new UV[] { new UV(4/16f, 4/16f), new UV(12/16f, 4/16f), new UV(12/16f, 12/16f), new UV(4/16f, 12/16f) };
			uvSide = new UV[] { new UV(4/16f, 0), new UV(12/16f, 0), new UV(12/16f, 1), new UV(4/16f, 1) };
			uvSides = new UV[][] { uvTop, uvSide, uvSide, uvSide, uvSide, uvTop };
			addBox(obj,x+0.5f,y-1/128f,z-1/128f,x+0.5f+6/16f,y+1/128f,z+1/128f, null, mtls, uvSides, null);
		}
		if (conn_w && data == 1) {
			uvTop = new UV[] { new UV(4/16f, 4/16f), new UV(12/16f, 4/16f), new UV(12/16f, 12/16f), new UV(4/16f, 12/16f) };
			uvSide = new UV[] { new UV(4/16f, 0), new UV(12/16f, 0), new UV(12/16f, 1), new UV(4/16f, 1) };
			uvSides = new UV[][] { uvTop, uvSide, uvSide, uvSide, uvSide, uvTop };
			addBox(obj,x-0.5f-6/16f,y-1/128f,z-1/128f,x-0.5f,y+1/128f,z+1/128f, null, mtls, uvSides, null);
		}
		//string NS
		if (data == 4) {
			
			uvTop = new UV[] { new UV(4/16f, 4/16f), new UV(12/16f, 4/16f), new UV(12/16f, 12/16f), new UV(4/16f, 12/16f) };
			uvSide = new UV[] { new UV(4/16f, 0), new UV(12/16f, 0), new UV(12/16f, 1), new UV(4/16f, 1) };
			uvSides = new UV[][] { uvTop, uvSide, uvSide, uvSide, uvSide, uvTop };
			addBox(obj,x -1/128f,y-1/128f,z-0.5f,x+1/128f,y+1/128f,z+0.5f, null, mtls, uvSides, null);
			
			
		}
		if (conn_n && data == 4) {
			uvTop = new UV[] { new UV(4/16f, 4/16f), new UV(12/16f, 4/16f), new UV(12/16f, 12/16f), new UV(4/16f, 12/16f) };
			uvSide = new UV[] { new UV(4/16f, 0), new UV(12/16f, 0), new UV(12/16f, 1), new UV(4/16f, 1) };
			uvSides = new UV[][] { uvTop, uvSide, uvSide, uvSide, uvSide, uvTop };
			addBox(obj,x-1/128f,y-1/128f,z+0.5f,x+1/128f,y+1/128f,z+0.5f+6/16f, null, mtls, uvSides, null);
		}
		if (conn_s && data == 4) {
			uvTop = new UV[] { new UV(4/16f, 4/16f), new UV(12/16f, 4/16f), new UV(12/16f, 12/16f), new UV(4/16f, 12/16f) };
			uvSide = new UV[] { new UV(4/16f, 0), new UV(12/16f, 0), new UV(12/16f, 1), new UV(4/16f, 1) };
			uvSides = new UV[][] { uvTop, uvSide, uvSide, uvSide, uvSide, uvTop };
			addBox(obj,x-1/128f,y-1/128f,z-0.5f,x+1/128f,y+1/128f,z-0.5f-6/16f, null, mtls, uvSides, null);
		}
		//string Vertical
		if (data == 2) {
			uvTop = new UV[] { new UV(4/16f, 4/16f), new UV(12/16f, 4/16f), new UV(12/16f, 12/16f), new UV(4/16f, 12/16f) };
			uvSide = new UV[] { new UV(4/16f, 0), new UV(12/16f, 0), new UV(12/16f, 1), new UV(4/16f, 1) };
			uvSides = new UV[][] { uvTop, uvSide, uvSide, uvSide, uvSide, uvTop };
			addBox(obj,x -1/128f,y-0.5f,z-1/128f,x+1/128f,y+0.5f,z+1/128f, null, mtls, uvSides, null);
			
			
		}
		if (conn_u && data == 2) {
			uvTop = new UV[] { new UV(4/16f, 4/16f), new UV(12/16f, 4/16f), new UV(12/16f, 12/16f), new UV(4/16f, 12/16f) };
			uvSide = new UV[] { new UV(4/16f, 0), new UV(12/16f, 0), new UV(12/16f, 1), new UV(4/16f, 1) };
			uvSides = new UV[][] { uvTop, uvSide, uvSide, uvSide, uvSide, uvTop };
			addBox(obj,x-1/128f,y+0.5f,z-1/128f,x+1/128f,y+0.5f+6/16f,z+1/128f, null, mtls, uvSides, null);
		}
		if (conn_d && data == 2) {
			uvTop = new UV[] { new UV(4/16f, 4/16f), new UV(12/16f, 4/16f), new UV(12/16f, 12/16f), new UV(4/16f, 12/16f) };
			uvSide = new UV[] { new UV(4/16f, 0), new UV(12/16f, 0), new UV(12/16f, 1), new UV(4/16f, 1) };
			uvSides = new UV[][] { uvTop, uvSide, uvSide, uvSide, uvSide, uvTop };
			addBox(obj,x-1/128f,y-0.5f,z-1/128f,x+1/128f,y-0.5f-6/16f,z+1/128f, null, mtls, uvSides, null);
		}

	}

}
