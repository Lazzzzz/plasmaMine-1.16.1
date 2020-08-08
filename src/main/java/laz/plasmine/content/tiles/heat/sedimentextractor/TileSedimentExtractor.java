package laz.plasmine.content.tiles.heat.sedimentextractor;

import laz.plasmine.content.base.heat.TileHeatMachineBase;
import laz.plasmine.registry.init.PMTilesInit;

public class TileSedimentExtractor extends TileHeatMachineBase {

	public TileSedimentExtractor(int maxCelcius, float thermo) {
		super(PMTilesInit.SEDIMENT_EXTRACTOR.getTileEntityType(), maxCelcius, thermo, 2);
	}

	
	
}
