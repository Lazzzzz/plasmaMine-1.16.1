package laz.plasmine.content.tiles.convertor;

import laz.plasmine.base.convertor.TileConvertorBase;
import laz.plasmine.registry.init.PMTilesInit;

public class TileBasicConvertor extends TileConvertorBase {

	public TileBasicConvertor(int rate, float efficiency, int temp) {
		super(PMTilesInit.BASIC_CONVERTOR.getTileEntityType(), 1000, rate, efficiency, temp);
	}

}
