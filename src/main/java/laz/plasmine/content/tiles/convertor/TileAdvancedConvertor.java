package laz.plasmine.content.tiles.convertor;

import laz.plasmine.base.convertor.TileConvertorBase;
import laz.plasmine.registry.init.PMTilesInit;

public class TileAdvancedConvertor extends TileConvertorBase {

	public TileAdvancedConvertor(int rate, float efficiency, int temp) {
		super(PMTilesInit.ADVANCED_CONVERTOR.getTileEntityType(), 5000, rate, efficiency, temp);
	}
	
}
