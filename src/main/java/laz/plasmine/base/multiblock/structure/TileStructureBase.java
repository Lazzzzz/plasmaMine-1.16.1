package laz.plasmine.base.multiblock.structure;

import laz.plasmine.base.multiblock.TileBlockSlave;
import laz.plasmine.registry.init.PMTilesInit;

public class TileStructureBase extends TileBlockSlave {
	public TileStructureBase() {
		super(PMTilesInit.STRUCTURE_BLOCK.getTileEntityType());
	}
	
	@Override
	public void unbindToMaster() {
		world.destroyBlock(pos, false);
	}

}
