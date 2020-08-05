package laz.plasmine.util.direction;

import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;

public class DirectionUtils {

	public static BlockPos getPosDirection(BlockPos pos, Direction dir) {
		 return pos.add(dir.getDirectionVec());
	}
	
}
