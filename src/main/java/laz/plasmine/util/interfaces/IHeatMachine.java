package laz.plasmine.util.interfaces;

import laz.plasmine.api.HeatHelper;
import laz.plasmine.base.generator.BlockGeneratorBase;
import net.minecraft.block.BlockState;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public interface IHeatMachine {

	HeatHelper getHeatHelper();

	void onOverHeat();

	default float consumeHeat() {
		return 0;
	}
	
	default void onWorking() {}

	default Direction heatInOut(BlockState state) {
		return Direction.NORTH;
	}

	default void putHeat(float amount) {
	}
	

	default void setWorkingState(World world, BlockPos pos, BlockState state, boolean working) {
		if (working) {
			if (state.get(BlockGeneratorBase.WORKING) == false)
				world.setBlockState(pos, state.with(BlockGeneratorBase.WORKING, true));
		} else {
			if (state.get(BlockGeneratorBase.WORKING) == true)
				world.setBlockState(pos, state.with(BlockGeneratorBase.WORKING, false));
		}
	}
	
	double speedFactor();
}
