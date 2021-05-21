package org.jmc.models;

import java.util.HashSet;

import org.jmc.geom.UV;
import org.jmc.threading.ChunkProcessor;
import org.jmc.threading.ThreadChunkDeligate;


/**
 * Model for fences.
 */
public class MultiMoulding extends BlockModel
{
	/** Blocks to which fences will connect */
	private static HashSet<Short> connectable;
	
	static
	{
		short[] ids = new short[] {
				179
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

		//Moulding (North)
		if (data == 0) {
			uvTop = new UV[] { new UV(6/16f, 6/16f), new UV(10/16f, 6/16f), new UV(10/16f, 10/16f), new UV(6/16f, 10/16f) };
			uvSide = new UV[] { new UV(6/16f, 0), new UV(10/16f, 0), new UV(10/16f, 1), new UV(6/16f, 1) };
			uvSides = new UV[][] { uvTop, uvSide, uvSide, uvSide, uvSide, uvTop };
			addBox(obj, x - 0.5f, y - 0.5f, z, x +0.5f, y, z - 0.5f, null, mtls, uvSides, null);			
		}
		//Moulding (East)
		if (data == 1) {
			uvTop = new UV[] { new UV(6/16f, 6/16f), new UV(10/16f, 6/16f), new UV(10/16f, 10/16f), new UV(6/16f, 10/16f) };
			uvSide = new UV[] { new UV(6/16f, 0), new UV(10/16f, 0), new UV(10/16f, 1), new UV(6/16f, 1) };
			uvSides = new UV[][] { uvTop, uvSide, uvSide, uvSide, uvSide, uvTop };
			addBox(obj, x, y - 0.5f, z-0.5f, x +0.5f, y, z + 0.5f, null, mtls, uvSides, null);
		}
		//Moulding (South)
		if (data == 2) {
			uvTop = new UV[] { new UV(6/16f, 6/16f), new UV(10/16f, 6/16f), new UV(10/16f, 10/16f), new UV(6/16f, 10/16f) };
			uvSide = new UV[] { new UV(6/16f, 0), new UV(10/16f, 0), new UV(10/16f, 1), new UV(6/16f, 1) };
			uvSides = new UV[][] { uvTop, uvSide, uvSide, uvSide, uvSide, uvTop };
			addBox(obj, x - 0.5f, y - 0.5f, z, x +0.5f, y, z + 0.5f, null, mtls, uvSides, null);
		}
		//Moulding (West)
		if (data == 3) {
			uvTop = new UV[] { new UV(6/16f, 6/16f), new UV(10/16f, 6/16f), new UV(10/16f, 10/16f), new UV(6/16f, 10/16f) };
			uvSide = new UV[] { new UV(6/16f, 0), new UV(10/16f, 0), new UV(10/16f, 1), new UV(6/16f, 1) };
			uvSides = new UV[][] { uvTop, uvSide, uvSide, uvSide, uvSide, uvTop };
			addBox(obj, x -0.5f, y - 0.5f, z-0.5f, x, y, z + 0.5f, null, mtls, uvSides, null);
		}
		//Moulding (Vertical, NorthWest)
		if (data == 7) {
			uvTop = new UV[] { new UV(6/16f, 6/16f), new UV(10/16f, 6/16f), new UV(10/16f, 10/16f), new UV(6/16f, 10/16f) };
			uvSide = new UV[] { new UV(6/16f, 0), new UV(10/16f, 0), new UV(10/16f, 1), new UV(6/16f, 1) };
			uvSides = new UV[][] { uvTop, uvSide, uvSide, uvSide, uvSide, uvTop };
			addBox(obj, x-0.5f,y-0.5f,z,x,y+0.5f,z+0.5f, null, mtls, uvSides, null);
		}
		//Moulding (Vertical, NorthEast)
		if (data == 6) {
			uvTop = new UV[] { new UV(6/16f, 6/16f), new UV(10/16f, 6/16f), new UV(10/16f, 10/16f), new UV(6/16f, 10/16f) };
			uvSide = new UV[] { new UV(6/16f, 0), new UV(10/16f, 0), new UV(10/16f, 1), new UV(6/16f, 1) };
			uvSides = new UV[][] { uvTop, uvSide, uvSide, uvSide, uvSide, uvTop };
			addBox(obj, x,y-0.5f,z,x+0.5f,y+0.5f,z+0.5f, null, mtls, uvSides, null);
		}
		//Moulding (Vertical, SouthEast)
		if (data == 5) {
			uvTop = new UV[] { new UV(6/16f, 6/16f), new UV(10/16f, 6/16f), new UV(10/16f, 10/16f), new UV(6/16f, 10/16f) };
			uvSide = new UV[] { new UV(6/16f, 0), new UV(10/16f, 0), new UV(10/16f, 1), new UV(6/16f, 1) };
			uvSides = new UV[][] { uvTop, uvSide, uvSide, uvSide, uvSide, uvTop };
			addBox(obj, x,y-0.5f,z-0.5f,x+0.5f,y+0.5f,z, null, mtls, uvSides, null);
		}
		//Moulding (Vertical, SouthWest)
		if (data == 4) {
			uvTop = new UV[] { new UV(6/16f, 6/16f), new UV(10/16f, 6/16f), new UV(10/16f, 10/16f), new UV(6/16f, 10/16f) };
			uvSide = new UV[] { new UV(6/16f, 0), new UV(10/16f, 0), new UV(10/16f, 1), new UV(6/16f, 1) };
			uvSides = new UV[][] { uvTop, uvSide, uvSide, uvSide, uvSide, uvTop };
			addBox(obj, x-0.5f,y-0.5f,z-0.5f,x,y+0.5f,z, null, mtls, uvSides, null);
		}
		//Moulding (Top, North)
		if (data == 8) {
			uvTop = new UV[] { new UV(6/16f, 6/16f), new UV(10/16f, 6/16f), new UV(10/16f, 10/16f), new UV(6/16f, 10/16f) };
			uvSide = new UV[] { new UV(6/16f, 0), new UV(10/16f, 0), new UV(10/16f, 1), new UV(6/16f, 1) };
			uvSides = new UV[][] { uvTop, uvSide, uvSide, uvSide, uvSide, uvTop };
			addBox(obj, x - 0.5f, y, z, x +0.5f, y+0.5f, z - 0.5f, null, mtls, uvSides, null);
		}
		//Moulding (Top, East)
		if (data == 9) {
			uvTop = new UV[] { new UV(6/16f, 6/16f), new UV(10/16f, 6/16f), new UV(10/16f, 10/16f), new UV(6/16f, 10/16f) };
			uvSide = new UV[] { new UV(6/16f, 0), new UV(10/16f, 0), new UV(10/16f, 1), new UV(6/16f, 1) };
			uvSides = new UV[][] { uvTop, uvSide, uvSide, uvSide, uvSide, uvTop };
			addBox(obj, x, y, z-0.5f, x +0.5f, y + 0.5f, z + 0.5f, null, mtls, uvSides, null);
		}
		//Moulding (Top, South)
		if (data == 10) {
			uvTop = new UV[] { new UV(6/16f, 6/16f), new UV(10/16f, 6/16f), new UV(10/16f, 10/16f), new UV(6/16f, 10/16f) };
			uvSide = new UV[] { new UV(6/16f, 0), new UV(10/16f, 0), new UV(10/16f, 1), new UV(6/16f, 1) };
			uvSides = new UV[][] { uvTop, uvSide, uvSide, uvSide, uvSide, uvTop };
			addBox(obj, x - 0.5f, y, z, x +0.5f, y + 0.5f, z + 0.5f, null, mtls, uvSides, null);
		}
		//Moulding (Top, West)
		if (data == 11) {
			uvTop = new UV[] { new UV(6/16f, 6/16f), new UV(10/16f, 6/16f), new UV(10/16f, 10/16f), new UV(6/16f, 10/16f) };
			uvSide = new UV[] { new UV(6/16f, 0), new UV(10/16f, 0), new UV(10/16f, 1), new UV(6/16f, 1) };
			uvSides = new UV[][] { uvTop, uvSide, uvSide, uvSide, uvSide, uvTop };
			addBox(obj, x -0.5f, y , z-0.5f, x, y+ 0.5f, z + 0.5f, null, mtls, uvSides, null);
		}
		//Column
		if (data == 12) {
			uvTop = new UV[] { new UV(6/16f, 6/16f), new UV(10/16f, 6/16f), new UV(10/16f, 10/16f), new UV(6/16f, 10/16f) };
			uvSide = new UV[] { new UV(6/16f, 0), new UV(10/16f, 0), new UV(10/16f, 1), new UV(6/16f, 1) };
			uvSides = new UV[][] { uvTop, uvSide, uvSide, uvSide, uvSide, uvTop };
			addBox(obj, x-5/16f,y-0.5f,z-5/16f,x+5/16f,y+0.5f,z+5/16f, null, mtls, uvSides, null);
		}
		//Pedestal (Up)
		if (data == 13) {
			uvTop = new UV[] { new UV(6/16f, 6/16f), new UV(10/16f, 6/16f), new UV(10/16f, 10/16f), new UV(6/16f, 10/16f) };
			uvSide = new UV[] { new UV(6/16f, 0), new UV(10/16f, 0), new UV(10/16f, 1), new UV(6/16f, 1) };
			uvSides = new UV[][] { uvTop, uvSide, uvSide, uvSide, uvSide, uvTop };
			addBox(obj, x-0.5f, y-0.5f, z-0.5f,  x+0.5f, y+0.25f, z+0.5f , null, mtls, uvSides, null);
			addBox(obj, x-0.5f+0.0625f, y+0.25f,z-0.5f+0.0625f, x+0.5f-0.0625f,y+0.25f+0.125f, z+0.5f-0.0625f,null, mtls,uvSides,null);
			addBox(obj, x-0.5f+0.125f, y+0.25f,z-0.5f+0.125f, x+0.5f-0.125f,y+0.5f, z+0.5f-0.125f,null, mtls,uvSides,null);
		}
		//Pedestal (Down)
		if (data == 14) {
			uvTop = new UV[] { new UV(6/16f, 6/16f), new UV(10/16f, 6/16f), new UV(10/16f, 10/16f), new UV(6/16f, 10/16f) };
			uvSide = new UV[] { new UV(6/16f, 0), new UV(10/16f, 0), new UV(10/16f, 1), new UV(6/16f, 1) };
			uvSides = new UV[][] { uvTop, uvSide, uvSide, uvSide, uvSide, uvTop };
			addBox(obj, x-0.5f, y+0.5f, z-0.5f, x+0.5f, y-0.25f, z+0.5f, null, mtls, uvSides, null);
			addBox(obj,x-0.5f+1/16f,y-0.25f,z-0.5f+1/16f,x+0.5f-1/16f,y-0.25f-0.125f,z+0.5f-1/16f, null, mtls, uvSides, null);
			addBox(obj,x-0.5f+2/16f,y-0.25f-0.125f,z-0.5f+2/16f,x+0.5f-2/16f,y-0.5f,z+0.5f-2/16f, null, mtls, uvSides, null);
		}
		//Table
		if (data == 15) {
			uvTop = new UV[] { new UV(6/16f, 6/16f), new UV(10/16f, 6/16f), new UV(10/16f, 10/16f), new UV(6/16f, 10/16f) };
			uvSide = new UV[] { new UV(6/16f, 0), new UV(10/16f, 0), new UV(10/16f, 1), new UV(6/16f, 1) };
			uvSides = new UV[][] { uvTop, uvSide, uvSide, uvSide, uvSide, uvTop };
			addBox(obj, x -0.125f, y-0.5f, z-0.125f, x+0.125f, y+0.5f-0.125f, z+0.125f, null, mtls, uvSides, null);
			addBox(obj, x -0.5f,y+0.5f-0.125f,z-0.5f,x+0.5f,y+0.5f,z+0.5f, null, mtls, uvSides, null);
		}
		
	}

}
