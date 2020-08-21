package laz.plasmine.content.tiles.coils;

import laz.plasmine.base.multiblock.TileBlockSlave;
import laz.plasmine.registry.init.PMTilesInit;

public class TileMagneticCoil extends TileBlockSlave {

	public TileMagneticCoil() {
		super(PMTilesInit.MAGNETIC_COIL.getTileEntityType());
	}

}
