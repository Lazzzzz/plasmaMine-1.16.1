package laz.plasmine.content.tiles.convertor;

import laz.plasmine.base.convertor.TileConvertorBase;
import laz.plasmine.registry.init.PMTilesInit;

public class TileUltimateConvertor extends TileConvertorBase {

	public TileUltimateConvertor(int rate, float efficiency, int temp) {
		super(PMTilesInit.ULTIMATE_CONVERTOR.getTileEntityType(), 10000, rate, efficiency, temp);
	}
	
}
