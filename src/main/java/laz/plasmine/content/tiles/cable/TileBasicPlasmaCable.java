package laz.plasmine.content.tiles.cable;

import laz.plasmine.api.base.cable.TileCableBase;
import laz.plasmine.registry.init.PMTilesInit;

public class TileBasicPlasmaCable extends TileCableBase {

	public TileBasicPlasmaCable() {
		super(PMTilesInit.BASIC_PLASMA_CABLE.getTileEntityType());
	}
}
