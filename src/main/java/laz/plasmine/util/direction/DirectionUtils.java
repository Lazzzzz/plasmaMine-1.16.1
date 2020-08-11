package laz.plasmine.util.direction;

import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.vector.Vector3d;

public class DirectionUtils {

	public static BlockPos getPosDirection(BlockPos pos, Direction dir) {
		return pos.add(dir.getDirectionVec());
	}

	public static BlockPos getPosFromRot(BlockPos pos, Direction dir, int i, int j, int marge) {
		if (dir == Direction.EAST)
			return pos.add(i + marge, 0, j);
		else if (dir == Direction.WEST)
			return pos.add(i - marge, 0, j);
		else if (dir == Direction.NORTH)
			return pos.add(i, 0, j - marge);
		return pos.add(i, 0, j + marge);
	}
	
	public static BlockPos getPosFromRot(BlockPos pos, Direction dir, int i, int marge) {
		if (dir == Direction.EAST)
			return pos.add(2, 0, i + marge);
		else if (dir == Direction.WEST)
			return pos.add(0, 0, i + marge);
		else if (dir == Direction.NORTH)
			return pos.add(i + marge, 0, 0);
		return pos.add(i + marge, 0, 2);
	}

	public static Vector3d getMotion(Direction dir, float speed) {
		if (dir == Direction.EAST)
			return new Vector3d(speed, 0, 0);
		else if (dir == Direction.WEST)
			return new Vector3d(-speed, 0, 0);
		else if (dir == Direction.NORTH)
			return new Vector3d(0f, 0, -speed);
		return new Vector3d(0f, 0, speed);

	}
}
