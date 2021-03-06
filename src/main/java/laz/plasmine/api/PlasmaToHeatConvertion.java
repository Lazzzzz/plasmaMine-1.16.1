package laz.plasmine.api;

import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class PlasmaToHeatConvertion {

	public static float transformPlasmaToHeat(int amount, float efficiency, float temp, float maxTemp, World world, BlockPos pos) {
		return amount * efficiency * (1f - (temp / maxTemp)) * 0.2f;
	}


	public static float getMinTemp(World world, BlockPos pos) {
		return world.getBiome(pos).getTemperature(pos) * 20;
	}
	
}
