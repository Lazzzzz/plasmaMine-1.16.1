package laz.plasmine.content.tiles.convertor;

import laz.plasmine.base.convertor.TileConvertorBase;
import laz.plasmine.registry.init.PMTilesInit;

public class TileSuperAdvancedConvertor extends TileConvertorBase {

	public TileSuperAdvancedConvertor(int rate, float efficiency, int temp) {
		super(PMTilesInit.SUPER_ADVANCED_CONVERTOR.getTileEntityType(), 2500, rate, efficiency, temp);
	}
	
}
