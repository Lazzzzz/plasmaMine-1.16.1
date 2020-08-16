package laz.plasmine.util;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.math.BlockPos;

public class BlockPosUtil {

	public static boolean areSame(BlockPos p1, BlockPos p2) {
		if (p1 == null && p2 == null)
			return true;
		if (p1 == null && p2 != null)
			return false;
		if (p2 == null && p1 != null)
			return false;
		return (p1.getX() == p2.getX() && p1.getY() == p2.getY() && p1.getZ() == p2.getZ());

	}

	public static CompoundNBT writeBlockPos(CompoundNBT compound, BlockPos pos, String id) {
		if (pos == null) return compound;
		
		compound.putInt(id + "_posx", pos.getX());
		compound.putInt(id + "_posy", pos.getY());
		compound.putInt(id + "_posz", pos.getZ());
		
		return compound;
	}

	public static BlockPos readBlockPos(CompoundNBT compound, String id) {
		int x = compound.getInt(id + "_posx");
		int y = compound.getInt(id + "_posy");
		int z = compound.getInt(id + "_posz");

		return new BlockPos(x, y, z);
	}
	
	public static CompoundNBT writeListBlockPos(CompoundNBT compound, List<BlockPos> l, String id) {
		compound.putInt(id + "_size", l.size());
		for (int i = 0; i < l.size(); i++) {
			compound = writeBlockPos(compound, l.get(i), id + "_" + i);
		}
		return compound;
	}
	
	public static  List<BlockPos> readListBlockPos(CompoundNBT compound, String id) {
		int size = compound.getInt(id + "_size");
		List<BlockPos> l = new ArrayList<BlockPos>();
		for (int i = 0; i < size; i++) {
			BlockPos pos = readBlockPos(compound, id + "_" + i);
			l.add(pos);
		}
		return l;
	}

}
