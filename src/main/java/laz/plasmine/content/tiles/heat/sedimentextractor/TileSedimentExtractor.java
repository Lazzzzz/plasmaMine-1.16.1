package laz.plasmine.content.tiles.heat.sedimentextractor;

import laz.plasmine.content.base.heat.TileHeatMachineBase;
import laz.plasmine.registry.init.PMTilesInit;

public class TileSedimentExtractor extends TileHeatMachineBase {

	public TileSedimentExtractor() {
		super(PMTilesInit.SEDIMENT_EXTRACTOR.getTileEntityType(), 500, 0.4f);
	}

	
	
}
