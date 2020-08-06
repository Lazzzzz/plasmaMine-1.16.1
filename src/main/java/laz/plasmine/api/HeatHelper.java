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

	public boolean isOverHeating() {
		return celcius > maxCelcius;
	}

	private void removeHeat(float amount) {
		celcius -= amount;
	}

	public void transferHeat(HeatHelper helper) {
		float amount = thermo_conductivity * getThermalFactor() * 10;
		if (celcius > helper.celcius + amount) {
			removeHeat(amount - 0.1f);
			helper.addCelcius(amount);
		}
	}
	
	private float getThermalFactor() {
		return (float) Math.max(1 - (maxCelcius / celcius), 0.1);	
	}

	public void coolDown(World world, BlockPos pos) {
		if (world.getDayTime() % 40 == 0) {
			float minTemp = world.getBiome(pos).getTemperature(pos) * 20;
			removeHeat(thermo_conductivity * 2f);
			if (celcius < minTemp)
				celcius = minTemp;
		}
	}

	public void write(CompoundNBT compound) {
		compound.putFloat("celcius", celcius);
	}

	public void read(CompoundNBT compoundNBT) {
		celcius = compoundNBT.getFloat("celcius");
	}
}
