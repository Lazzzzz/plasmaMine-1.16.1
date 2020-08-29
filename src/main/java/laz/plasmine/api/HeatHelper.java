package laz.plasmine.api;

import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class HeatHelper {

	private float maxCelcius = 0;
	private float celcius = 0;
	private float thermoConductivity = 0;

	public HeatHelper(float maxCelcius, float thermo_conductivity) {
		this.maxCelcius = maxCelcius;
		this.thermoConductivity = thermo_conductivity;
	}

	public void addCelcius(float amount) {
		celcius += amount;
	}

	public void setCelcius(float amount) {
		celcius = amount;
	}

	public boolean isOverHeating() {
		return celcius > maxCelcius;
	}

	public void removeHeat(float amount) {
		celcius -= amount;
	}

	public void transferHeat(HeatHelper helper) {
		float amount = thermoConductivity * getThermalFactor() * 20;
		if (celcius > helper.celcius + amount) {
			removeHeat(amount - 0.1f);
			helper.addCelcius(amount);
		}
	}

	public float getThermalFactor() {
		return 1 - Math.max(0.3f, celcius / maxCelcius * 2);
	}

	public void coolDown(World world, BlockPos pos) {
		float minTemp = PlasmaToHeatConvertion.getMinTemp(world, pos);
		removeHeat(thermoConductivity / 5);
		if (celcius < minTemp)
			celcius = minTemp;
	}
	
	public void coolDown(World world, BlockPos pos, float amount) {
		float minTemp = PlasmaToHeatConvertion.getMinTemp(world, pos);
		removeHeat(amount);
		if (celcius < minTemp)
			celcius = minTemp;
	}


	public void write(CompoundNBT compound) {
		compound.putFloat("celcius", celcius);
	}

	public void read(CompoundNBT compoundNBT) {
		celcius = compoundNBT.getFloat("celcius");
	}

	public boolean isWorkingCelcius(World world, BlockPos pos) {
		return celcius > getWorkingCelcius(world, pos);
	}

	public float getWorkingCelcius(World world, BlockPos pos) {
		return PlasmaToHeatConvertion.getMinTemp(world, pos);
	}

	public float getCelcius() {
		return celcius;
	}

	public float getMaxCelcius() {
		return maxCelcius;
	}
	
	public float getThermoConductivity() {
		return thermoConductivity;
	}

}
