package laz.plasmine.util.interfaces;

import java.util.ArrayList;
import java.util.List;

import laz.plasmine.api.PlasmaHelper;
import laz.plasmine.api.base.cable.TileCableBase;
import laz.plasmine.util.direction.DirectionUtils;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public interface IPlasmaGenerator {

	PlasmaHelper getPlasmaHelper();

	default int sendEnergy(World world, BlockPos pos, int amount) {
		if (amount > 0) {
			List<BlockPos> connectedTile = new ArrayList<BlockPos>();
			for (int i = 0; i < 6; i++) {
				TileEntity tile = world.getTileEntity(DirectionUtils.getPosDirection(pos, Direction.byIndex(i)));
				if (tile != null && tile instanceof ICable)
					connectedTile = ((TileCableBase) tile).getCableAround(Direction.byIndex(i), 0,
							new ArrayList<BlockPos>(), new ArrayList<BlockPos>());

			}
			if (connectedTile.size() > 0) {
				int amountEach = amount / (connectedTile.size());
				for (int i = 0; i < connectedTile.size(); i++) {
					amount -= ((IPlasmaMachine) world.getTileEntity(connectedTile.get(i))).receiveEnergy(amountEach);
				}
			}
		}
		return amount;
	}

	int produceEnergy();

	void setWorkingState(boolean working);
}
