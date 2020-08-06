package laz.plasmine.content.generator;

import laz.plasmine.content.base.generator.TileGeneratorBase;
import laz.plasmine.registry.init.PMTilesInit;

public class TileBasicGenerator extends TileGeneratorBase {

	public TileBasicGenerator() {
		super(PMTilesInit.BASIC_GENERATOR.getTileEntityType(), 10000, 10);
	}

}
