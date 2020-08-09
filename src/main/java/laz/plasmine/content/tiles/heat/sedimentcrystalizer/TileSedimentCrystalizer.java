package laz.plasmine.content.tiles.heat.sedimentcrystalizer;

import laz.plasmine.api.base.heat.TileHeatMachineBase;
import laz.plasmine.registry.init.PMTilesInit;

public class TileSedimentCrystalizer extends TileHeatMachineBase {

	public TileSedimentCrystalizer(int maxCelcius, float thermo) {
		super(PMTilesInit.SEDIMENT_CRYSTALIZER.getTileEntityType(), maxCelcius, thermo, 2);
	}

	
	
}
