package laz.plasmine.api;

import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class HeatHelper {

	private float maxCelcius = 0;
	private float celcius = 0;
	private float thermo_conductivity = 0;

	public HeatHelper(float maxCelcius, float thermo_conductivity) {
		this.maxCelcius = maxCelcius;
		this.thermo_conductivity = thermo_conductivity;
	}

	public void addCelcius(float amount) {
		celcius += amount * getThermalFactor();
	}

	public void setCelcius(float amount) {
		celcius = amount;
	}
	
	public boolean isOverHeating() {
		return celcius > maxCelcius;
	}

	private void removeHeat(float amount) {
		celcius -= amount;
	}

	public void transferHeat(HeatHelper helper) {
		float amount = thermo_conductivity * getThermalFactor() * 20;
		if (celcius > helper.celcius + amount) {
			removeHeat(amount - 0.1f);
			helper.addCelcius(amount);
		}
	}

	public float getThermalFactor() {
		return 1 - Math.max(0.3f, celcius / maxCelcius * 2);
	}

	public void coolDown(World world, BlockPos pos) {
		float minTemp = getMinTemp(world, pos);
		if (world.getDayTime() % 5 == 0) {
			removeHeat(thermo_conductivity);
			if (celcius < minTemp)
				celcius = minTemp;
		}
	}

	public float getMinTemp(World world, BlockPos pos) {
		return world.getBiome(pos).getTemperature(pos) * 20;
	}

	public void write(CompoundNBT compound) {
		compound.putFloat("celcius", celcius);
	}

	public void read(CompoundNBT compoundNBT) {
		celcius = compoundNBT.getFloat("celcius");
	}

	public boolean isWorkingCelcius(World world, BlockPos pos) {
		return celcius > getMinTemp(world, pos) + 20;
	}

	public float getWorkingCelcius(World world, BlockPos pos) {
		return getMinTemp(world, pos) + 20;
	}

	public float getCelcius() {
		return celcius;
	}
	
	public float getMaxCelcius() {
		return maxCelcius;
	}

}
