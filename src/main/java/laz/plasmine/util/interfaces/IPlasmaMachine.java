package laz.plasmine.util.interfaces;

import laz.plasmine.api.PlasmaHelper;

public interface IPlasmaMachine {
	
	PlasmaHelper getPlasmaHelper();
	
	int receiveEnergy(int amount);
	
	int spaceLeft();
	
	int getConsomationPerTick();
	
}
