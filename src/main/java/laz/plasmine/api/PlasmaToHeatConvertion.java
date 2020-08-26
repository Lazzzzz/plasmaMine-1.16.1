package laz.plasmine.api;

import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class PlasmaToHeatConvertion {

	public static float transformPlasmaToHeat(int amount, float efficiency, float temp, float maxTemp, World world, BlockPos pos) {
		if (amount != 0) return amount * efficiency * (1f - (temp / maxTemp)) * 0.5f;
		return efficiency * Math.max(0.01f, (1 - temp / maxTemp)) * 3f;
	}


	public static float getMinTemp(World world, BlockPos pos) {
		return world.getBiome(pos).getTemperature(pos) * 20;
	}
	
}
