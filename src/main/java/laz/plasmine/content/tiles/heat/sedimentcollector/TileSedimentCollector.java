package laz.plasmine.content.tiles.heat.sedimentcollector;

import laz.plasmine.content.base.heat.TileHeatMachineBase;
import laz.plasmine.registry.init.PMTilesInit;

public class TileSedimentCollector extends TileHeatMachineBase {

	public TileSedimentCollector() {
		super(PMTilesInit.SEDIMENT_COLLECTOR.getTileEntityType(), 500, 0.4f);
	}

	
	
}
