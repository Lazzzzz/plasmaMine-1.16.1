package laz.plasmine.api;

public class PlasmaToHeatConvertion {

	public static float transformPlasmaToHeat(HeatHelper heatHelper, int amount, float efficiency) {
		return amount * heatHelper.getThermalFactor() * efficiency;
	}
	
}
