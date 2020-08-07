		package laz.plasmine.content.tiles.convertor;

import laz.plasmine.content.base.convertor.TileConvertorBase;
import laz.plasmine.registry.init.PMTilesInit;

public class TileBasicConvertor extends TileConvertorBase{

	public TileBasicConvertor() {
		super(PMTilesInit.BASIC_CONVERTOR.getTileEntityType(), 1000, 1000, 0.8f, 20);
	}
	
}
