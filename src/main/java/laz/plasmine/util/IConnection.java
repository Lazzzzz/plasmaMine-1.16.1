package laz.plasmine.util;

import static laz.plasmine.util.direction.DirectionUtils.getPosDirection;

import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public interface IConnection {

	default void updateState(World world, BlockPos pos) {
	}

	default boolean getConnectionFace(Direction face) {
		return true;
	}

	default void connectedTo(World world, BlockPos pos, boolean[] connected) {
		for (int i = 0; i < 6; i++) {
			connected[i] = false;
			BlockPos p = getPosDirection(pos, Direction.byIndex(i));
			TileEntity tile = world.getTileEntity(p);
			if (tile != null && tile instanceof IConnection)
				connected[i] = ((IConnection) tile).getConnectionFace(Direction.byIndex(i).getOpposite());
		}
		updateState(world, pos);
	}
}
