package laz.plasmine.content.block.slaves;

import laz.plasmine.base.multiblock.TileBlockSlave;
import laz.plasmine.registry.init.PMTilesInit;

public class TileMachine extends TileBlockSlave{

	public TileMachine() {
		super(PMTilesInit.MACHINE_BLOCK.getTileEntityType());
	}

}
