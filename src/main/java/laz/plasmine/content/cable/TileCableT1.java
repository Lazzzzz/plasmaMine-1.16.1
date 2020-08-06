package laz.plasmine.content.cable;

import laz.plasmine.content.base.cable.BlockCableBase;
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
			.with(BlockCableBase.DOWN,  connected[0])
			.with(BlockCableBase.UP,    connected[1])
			.with(BlockCableBase.NORTH, connected[2])
			.with(BlockCableBase.SOUTH, connected[3])
			.with(BlockCableBase.WEST,  connected[4])
			.with(BlockCableBase.EAST,  connected[5]);

		if (state != newState)
			world.setBlockState(pos, newState);

	}
}
