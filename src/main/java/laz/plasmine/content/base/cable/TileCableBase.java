package laz.plasmine.content.base.cable;

import laz.plasmine.content.base.machine.TilePlasmaMachineBase;
import laz.plasmine.registry.init.PMTilesInit;
import net.minecraft.block.BlockState;
import net.minecraft.tileentity.TileEntityType;

public class TileCableBase extends TilePlasmaMachineBase {

	public TileCableBase(TileEntityType<?> tileEntityTypeIn, int maxCapacity, int rate, float efficiency, int time,
			boolean transfer) {
		super(tileEntityTypeIn, maxCapacity, rate, efficiency, time, transfer);
	}

	@Override
	public void getConnectedMachine() {
		
		super.getConnectedMachine();
		
		BlockState state = world.getBlockState(pos);
		boolean[] side = new boolean[6];
		for (int i = 0; i < 6; i++) {
			if (connected[i] != null)
				side[i] = true;
		}
		
		BlockState newState = state.with(CableBase.DOWN, side[0]).with(CableBase.UP, side[1])
				.with(CableBase.NORTH, side[2]).with(CableBase.SOUTH, side[3]).with(CableBase.WEST, side[4])
				.with(CableBase.EAST, side[5]);
		
		if (state != newState)
			world.setBlockState(pos, newState);
	}
}
