package laz.plasmine.util;

import java.util.ArrayList;

import laz.plasmine.api.PlasmaHelper;
import laz.plasmine.content.base.cable.TileCableBase;
import laz.plasmine.util.direction.DirectionUtils;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public interface IPlasmaGenerator {

	PlasmaHelper getPlasmaHelper();

	default int sendEnergy(World world, BlockPos pos, int amount) {
		int rest = amount;
		for (int i = 0; i < 6; i++) {
			TileEntity tile = world.getTileEntity(DirectionUtils.getPosDirection(pos, Direction.byIndex(i)));
			if (tile != null && tile instanceof ICable)
				rest = ((TileCableBase) tile).getCableAround(Direction.byIndex(i), amount, 0,
						new ArrayList<BlockPos>());

		}
		return amount - rest;
	}
}
