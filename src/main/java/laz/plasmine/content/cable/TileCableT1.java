package laz.plasmine.content.cable;

import laz.plasmine.content.base.cable.CableBase;
import laz.plasmine.content.base.cable.TileCableBase;
import laz.plasmine.registry.init.PMTilesInit;
import net.minecraft.block.BlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class TileCableT1 extends TileCableBase {

	public TileCableT1() {
		super(PMTilesInit.PLASMA_CABLE_T1.getTileEntityType());
	}

	@Override
	public void updateState(World world, BlockPos pos) {
		BlockState state = world.getBlockState(pos);
		BlockState newState = state
			.with(CableBase.DOWN,  connected[0])
			.with(CableBase.UP,    connected[1])
			.with(CableBase.NORTH, connected[2])
			.with(CableBase.SOUTH, connected[3])
			.with(CableBase.WEST,  connected[4])
			.with(CableBase.EAST,  connected[5]);

		if (state != newState)
			world.setBlockState(pos, newState);

	}
}
