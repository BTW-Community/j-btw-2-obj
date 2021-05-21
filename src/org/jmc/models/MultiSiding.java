package org.jmc.models;

import java.util.HashSet;

import org.jmc.geom.UV;
import org.jmc.threading.ChunkProcessor;
import org.jmc.threading.ThreadChunkDeligate;


/**
 * Model for fences.
 */
public class MultiSiding extends BlockModel
{
	/** Blocks to which fences will connect */
	private static HashSet<Short> connectable;
	
	static
	{
		short[] ids = new short[] {
				1,2,3,4,5,7,12,13,14,15,16,17,19,21,22,23,24,25,35,41,42,43,45,47,48,49,56,57,58,
				60,61,62,73,74,80,82,84,85,86,87,88,91,97,98,107,110,112,113,121,123,124,129,178,183,184,
				185,186,187,188,189,190,191,192
			};
		
		connectable = new HashSet<Short>(ids.length);
		for (int i = 0; i < ids.length; i++)
			connectable.add(ids[i]);
	}
	
	
	@Override
	public void addModel(ChunkProcessor obj, ThreadChunkDeligate chunks, int x, int y, int z, byte data, byte biome)
	{
		String[] mtls = getMtlSides(data, biome);
		UV[] uvTop, uvSide;
		UV[][] uvSides;
		
		//Siding (Top)
		if (data == 0) {
			uvTop = new UV[] { new UV(6/16f, 6/16f), new UV(10/16f, 6/16f), new UV(10/16f, 10/16f), new UV(6/16f, 10/16f) };
			uvSide = new UV[] { new UV(6/16f, 0), new UV(10/16f, 0), new UV(10/16f, 1), new UV(6/16f, 1) };
			uvSides = new UV[][] { uvTop, uvSide, uvSide, uvSide, uvSide, uvTop };
			addBox(obj, x - 0.5f, y, z - 0.5f, x + 0.5f, y + 0.5f, z +0.5f, null, mtls, uvSides, null);
		}
		//Corner (Bottom, NorthWest)
		if (data == 1) {
			uvTop = new UV[] { new UV(6/16f, 6/16f), new UV(10/16f, 6/16f), new UV(10/16f, 10/16f), new UV(6/16f, 10/16f) };
			uvSide = new UV[] { new UV(6/16f, 0), new UV(10/16f, 0), new UV(10/16f, 1), new UV(6/16f, 1) };
			uvSides = new UV[][] { uvTop, uvSide, uvSide, uvSide, uvSide, uvTop };
			addBox(obj, x - 0.5f, y - 0.5f, z - 0.5f, x , y, z, null, mtls, uvSides, null);
		}
		//Siding (Bottom)
		if (data == 2) {
			uvTop = new UV[] { new UV(6/16f, 6/16f), new UV(10/16f, 6/16f), new UV(10/16f, 10/16f), new UV(6/16f, 10/16f) };
			uvSide = new UV[] { new UV(6/16f, 0), new UV(10/16f, 0), new UV(10/16f, 1), new UV(6/16f, 1) };
			uvSides = new UV[][] { uvTop, uvSide, uvSide, uvSide, uvSide, uvTop };
			addBox(obj, x - 0.5f, y - 0.5f, z - 0.5f, x + 0.5f, y, z +0.5f, null, mtls, uvSides, null);
		}
		//Corner (Bottom, SouthWest)
		if (data == 3) {
			uvTop = new UV[] { new UV(6/16f, 6/16f), new UV(10/16f, 6/16f), new UV(10/16f, 10/16f), new UV(6/16f, 10/16f) };
			uvSide = new UV[] { new UV(6/16f, 0), new UV(10/16f, 0), new UV(10/16f, 1), new UV(6/16f, 1) };
			uvSides = new UV[][] { uvTop, uvSide, uvSide, uvSide, uvSide, uvTop };
			addBox(obj, x - 0.5f, y - 0.5f, z, x , y, z +0.5f, null, mtls, uvSides, null);
		}
		//Siding (South)
		if (data == 4) {
			uvTop = new UV[] { new UV(6/16f, 6/16f), new UV(10/16f, 6/16f), new UV(10/16f, 10/16f), new UV(6/16f, 10/16f) };
			uvSide = new UV[] { new UV(6/16f, 0), new UV(10/16f, 0), new UV(10/16f, 1), new UV(6/16f, 1) };
			uvSides = new UV[][] { uvTop, uvSide, uvSide, uvSide, uvSide, uvTop };
			addBox(obj, x - 0.5f, y - 0.5f, z, x + 0.5f, y + 0.5f, z + 0.5f , null, mtls, uvSides, null);
		}
		
		//Corner (Top, NorthWest)
		if (data == 5) {
			uvTop = new UV[] { new UV(6/16f, 6/16f), new UV(10/16f, 6/16f), new UV(10/16f, 10/16f), new UV(6/16f, 10/16f) };
			uvSide = new UV[] { new UV(6/16f, 0), new UV(10/16f, 0), new UV(10/16f, 1), new UV(6/16f, 1) };
			uvSides = new UV[][] { uvTop, uvSide, uvSide, uvSide, uvSide, uvTop };
			addBox(obj, x - 0.5f, y, z - 0.5f, x , y + 0.5f, z, null, mtls, uvSides, null);
		}
		//Siding (North)
		if (data == 6) {
			uvTop = new UV[] { new UV(6/16f, 6/16f), new UV(10/16f, 6/16f), new UV(10/16f, 10/16f), new UV(6/16f, 10/16f) };
			uvSide = new UV[] { new UV(6/16f, 0), new UV(10/16f, 0), new UV(10/16f, 1), new UV(6/16f, 1) };
			uvSides = new UV[][] { uvTop, uvSide, uvSide, uvSide, uvSide, uvTop };
			addBox(obj, x - 0.5f, y - 0.5f, z - 0.5f, x + 0.5f, y + 0.5f, z , null, mtls, uvSides, null);
		}
		//Corner (Top, SouthWest)
		if (data == 7) {
			uvTop = new UV[] { new UV(6/16f, 6/16f), new UV(10/16f, 6/16f), new UV(10/16f, 10/16f), new UV(6/16f, 10/16f) };
			uvSide = new UV[] { new UV(6/16f, 0), new UV(10/16f, 0), new UV(10/16f, 1), new UV(6/16f, 1) };
			uvSides = new UV[][] { uvTop, uvSide, uvSide, uvSide, uvSide, uvTop };
			addBox(obj, x - 0.5f, y, z, x , y+0.5f, z +0.5f, null, mtls, uvSides, null);
		}
		//Siding (East)
		if (data == 8) {
			uvTop = new UV[] { new UV(6/16f, 6/16f), new UV(10/16f, 6/16f), new UV(10/16f, 10/16f), new UV(6/16f, 10/16f) };
			uvSide = new UV[] { new UV(6/16f, 0), new UV(10/16f, 0), new UV(10/16f, 1), new UV(6/16f, 1) };
			uvSides = new UV[][] { uvTop, uvSide, uvSide, uvSide, uvSide, uvTop };
			addBox(obj, x, y - 0.5f, z - 0.5f, x + 0.5f, y + 0.5f, z + 0.5f , null, mtls, uvSides, null);
		}
		//Corner (Bottom, NorthEast)
		if (data == 9) {
			uvTop = new UV[] { new UV(6/16f, 6/16f), new UV(10/16f, 6/16f), new UV(10/16f, 10/16f), new UV(6/16f, 10/16f) };
			uvSide = new UV[] { new UV(6/16f, 0), new UV(10/16f, 0), new UV(10/16f, 1), new UV(6/16f, 1) };
			uvSides = new UV[][] { uvTop, uvSide, uvSide, uvSide, uvSide, uvTop };
			addBox(obj, x, y - 0.5f, z - 0.5f, x +0.5f , y, z, null, mtls, uvSides, null);
		}
		//Siding (West)
		if (data == 10) {
			uvTop = new UV[] { new UV(6/16f, 6/16f), new UV(10/16f, 6/16f), new UV(10/16f, 10/16f), new UV(6/16f, 10/16f) };
			uvSide = new UV[] { new UV(6/16f, 0), new UV(10/16f, 0), new UV(10/16f, 1), new UV(6/16f, 1) };
			uvSides = new UV[][] { uvTop, uvSide, uvSide, uvSide, uvSide, uvTop };
			addBox(obj, x - 0.5f, y - 0.5f, z - 0.5f, x, y + 0.5f, z + 0.5f , null, mtls, uvSides, null);
		}
		//Corner (Bottom, SouthEast)
		if (data == 11) {
			uvTop = new UV[] { new UV(6/16f, 6/16f), new UV(10/16f, 6/16f), new UV(10/16f, 10/16f), new UV(6/16f, 10/16f) };
			uvSide = new UV[] { new UV(6/16f, 0), new UV(10/16f, 0), new UV(10/16f, 1), new UV(6/16f, 1) };
			uvSides = new UV[][] { uvTop, uvSide, uvSide, uvSide, uvSide, uvTop };
			addBox(obj, x, y - 0.5f, z - 0.5f, x +0.5f , y, z, null, mtls, uvSides, null);
		}
		//Bench
		if (data == 12) {
			uvTop = new UV[] { new UV(6/16f, 6/16f), new UV(10/16f, 6/16f), new UV(10/16f, 10/16f), new UV(6/16f, 10/16f) };
			uvSide = new UV[] { new UV(6/16f, 0), new UV(10/16f, 0), new UV(10/16f, 1), new UV(6/16f, 1) };
			uvSides = new UV[][] { uvTop, uvSide, uvSide, uvSide, uvSide, uvTop };
			addBox(obj, x-0.125f, y-0.5f, z-0.125f, x+0.125f, y, z+0.125f, null, mtls, uvSides, null);
			addBox(obj, x-0.5f, y, z-0.5f, x+0.5f, y + 0.125f, z+0.5f, null, mtls, uvSides, null);
		}
		
		//Corner (Top, NorthEast)
		if (data == 13) {
			uvTop = new UV[] { new UV(6/16f, 6/16f), new UV(10/16f, 6/16f), new UV(10/16f, 10/16f), new UV(6/16f, 10/16f) };
			uvSide = new UV[] { new UV(6/16f, 0), new UV(10/16f, 0), new UV(10/16f, 1), new UV(6/16f, 1) };
			uvSides = new UV[][] { uvTop, uvSide, uvSide, uvSide, uvSide, uvTop };
			addBox(obj, x, y, z - 0.5f, x +0.5f , y + 0.5f, z, null, mtls, uvSides, null);
		}
		
		//Fence
		if (data == 14) {
			uvTop = new UV[] { new UV(6/16f, 6/16f), new UV(10/16f, 6/16f), new UV(10/16f, 10/16f), new UV(6/16f, 10/16f) };
			uvSide = new UV[] { new UV(6/16f, 0), new UV(10/16f, 0), new UV(10/16f, 1), new UV(6/16f, 1) };
			uvSides = new UV[][] { uvTop, uvSide, uvSide, uvSide, uvSide, uvTop };
			addBox(obj, x-0.125f, y-0.5f, z-0.125f, x+0.125f, y+0.5f, z+0.125f, null, mtls, uvSides, null);
		}
		
		//Corner (Top, SouthEast)
		if (data == 15) {
			uvTop = new UV[] { new UV(6/16f, 6/16f), new UV(10/16f, 6/16f), new UV(10/16f, 10/16f), new UV(6/16f, 10/16f) };
			uvSide = new UV[] { new UV(6/16f, 0), new UV(10/16f, 0), new UV(10/16f, 1), new UV(6/16f, 1) };
			uvSides = new UV[][] { uvTop, uvSide, uvSide, uvSide, uvSide, uvTop };
			addBox(obj, x, y, z, x +0.5f , y+0.5f, z+0.5f, null, mtls, uvSides, null);
		}
		
		// north connector
		if (data == 14 && connectable.contains(chunks.getBlockID(x, y, z-1)))
		{
			uvTop = new UV[] { new UV(7/16f, 10/16f), new UV(9/16f, 10/16f), new UV(9/16f, 1), new UV(7/16f, 1) };
			uvSide = new UV[] { new UV(10/16f, 12/16f), new UV(1, 12/16f), new UV(1, 15/16f), new UV(10/16f, 15/16f) };
			uvSides = new UV[][] { uvTop, uvSide, uvSide, uvSide, uvSide, uvTop };
			addBox(obj, x-0.0625f, y+0.250f, z-0.5f, x+0.0625f, y+0.4375f, z-0.125f, null, mtls, uvSides, null);

			uvSide = new UV[] { new UV(10/16f, 6/16f), new UV(1, 6/16f), new UV(1, 9/16f), new UV(10/16f, 9/16f) };
			uvSides = new UV[][] { uvTop, uvSide, uvSide, uvSide, uvSide, uvTop };
			addBox(obj, x-0.0625f, y-0.125f, z-0.5f, x+0.0625f, y+0.0625f, z-0.125f, null, mtls, uvSides, null);
		}
		// south connector
		if (data == 14 && connectable.contains(chunks.getBlockID(x, y, z+1)))
		{
			uvTop = new UV[] { new UV(7/16f, 0), new UV(9/16f, 0), new UV(9/16f, 6/16f), new UV(7/16f, 6/16f) };
			uvSide = new UV[] { new UV(0, 12/16f), new UV(6/16f, 12/16f), new UV(6/16f, 15/16f), new UV(0, 15/16f) };
			uvSides = new UV[][] { uvTop, uvSide, uvSide, uvSide, uvSide, uvTop };
			addBox(obj, x-0.0625f, y+0.250f, z+0.125f, x+0.0625f, y+0.4375f, z+0.5f, null, mtls, uvSides, null);

			uvSide = new UV[] { new UV(0, 6/16f), new UV(6/16f, 6/16f), new UV(6/16f, 9/16f), new UV(0, 9/16f) };
			uvSides = new UV[][] { uvTop, uvSide, uvSide, uvSide, uvSide, uvTop };
			addBox(obj, x-0.0625f, y-0.125f, z+0.125f, x+0.0625f, y+0.0625f, z+0.5f, null, mtls, uvSides, null);
		}
		// east connector
		if (data == 14 && connectable.contains(chunks.getBlockID(x-1, y, z)))
		{
			uvTop = new UV[] { new UV(10/16f, 7/16f), new UV(1, 7/16f), new UV(1, 9/16f), new UV(10/16f, 9/16f) };
			uvSide = new UV[] { new UV(10/16f, 12/16f), new UV(1, 12/16f), new UV(1, 15/16f), new UV(10/16f, 15/16f) };
			uvSides = new UV[][] { uvTop, uvSide, uvSide, uvSide, uvSide, uvTop };
			addBox(obj, x-0.5f, y+0.250f, z-0.0625f, x-0.125f, y+0.4375f, z+0.0625f, null, mtls, uvSides, null);

			uvSide = new UV[] { new UV(10/16f, 6/16f), new UV(1, 6/16f), new UV(1, 9/16f), new UV(10/16f, 9/16f) };
			uvSides = new UV[][] { uvTop, uvSide, uvSide, uvSide, uvSide, uvTop };
			addBox(obj, x-0.5f, y-0.125f, z-0.0625f, x-0.125f, y+0.0625f, z+0.0625f, null, mtls, uvSides, null);
		}
		// west connector
		if (data == 14 && connectable.contains(chunks.getBlockID(x+1, y, z)))
		{
			uvTop = new UV[] { new UV(0, 7/16f), new UV(6/16f, 7/16f), new UV(6/16f, 9/16f), new UV(0, 9/16f) };
			uvSide = new UV[] { new UV(0, 12/16f), new UV(6/16f, 12/16f), new UV(6/16f, 15/16f), new UV(0, 15/16f) };
			uvSides = new UV[][] { uvTop, uvSide, uvSide, uvSide, uvSide, uvTop };
			addBox(obj, x+0.125f, y+0.250f, z-0.0625f, x+0.5f, y+0.4375f, z+0.0625f, null, mtls, uvSides, null);

			uvSide = new UV[] { new UV(0, 6/16f), new UV(6/16f, 6/16f), new UV(6/16f, 9/16f), new UV(0, 9/16f) };
			uvSides = new UV[][] { uvTop, uvSide, uvSide, uvSide, uvSide, uvTop };
			addBox(obj, x+0.125f, y-0.125f, z-0.0625f, x+0.5f, y+0.0625f, z+0.0625f, null, mtls, uvSides, null);
		}
	}

}
