package laz.plasmine.content.tiles.convertor;

import laz.plasmine.api.base.convertor.TileConvertorBase;
import laz.plasmine.registry.init.PMTilesInit;

public class TileAdvancedConvertor extends TileConvertorBase {

	public TileAdvancedConvertor(int rate, float efficiency) {
		super(PMTilesInit.ADVANCED_CONVERTOR.getTileEntityType(), 5000, 1000, 1.4f, rate, efficiency);
	}
	
}