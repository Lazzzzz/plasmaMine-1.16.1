package laz.plasmine.content.test;

import laz.plasmine.content.base.cable.TileCableBase;
import laz.plasmine.registry.init.PMTilesInit;

public class TileCableTest extends TileCableBase {

	public TileCableTest() {
		super(PMTilesInit.CABLE.getTileEntityType(), 100, 1, 0.75f, 10, true);
	}

}
