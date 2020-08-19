package laz.plasmine.api;

public class PlasmaToHeatConvertion {

	public static float transformPlasmaToHeat(int amount, float efficiency, float temp, float maxTemp) {
		if (amount != 0) return amount * efficiency * (1 - (temp / maxTemp));
		return efficiency * (1 - temp / maxTemp * 2);
	}
}
