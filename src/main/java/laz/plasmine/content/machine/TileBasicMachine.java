package laz.plasmine.content.machine;

import laz.plasmine.content.base.machine.TilePlasmaMachineBase;
import laz.plasmine.registry.init.PMTilesInit;

public class TileBasicMachine extends TilePlasmaMachineBase{

	public TileBasicMachine() {
		super(PMTilesInit.BASIC_MACHINE.getTileEntityType(), 1000);
	}

}
