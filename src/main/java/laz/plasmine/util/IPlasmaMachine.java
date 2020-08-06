package laz.plasmine.util;

import laz.plasmine.api.PlasmaHelper;

public interface IPlasmaMachine {
	
	PlasmaHelper getHelper();
	
	int receiveEnergy(int amount);
	
	int spaceLeft();
	
}
