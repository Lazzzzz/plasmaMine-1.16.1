package laz.plasmine.util;

import laz.plasmine.api.HeatHelper;
import net.minecraft.block.BlockState;
import net.minecraft.util.Direction;

public interface IHeatMachine {
	
	HeatHelper getHeatHelper();
	
	void onOverHeat();
	
	default Direction heatInOut(BlockState state) {
		return Direction.NORTH;
	}
	
	default void putHeat(float amount) {}
}
