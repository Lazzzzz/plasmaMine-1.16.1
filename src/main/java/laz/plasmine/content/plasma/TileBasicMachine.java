package laz.plasmine.content.plasma;

import laz.plasmine.content.base.plasma.TilePlasmaMachineBase;
import laz.plasmine.registry.init.PMTilesInit;
import net.minecraft.block.Blocks;

public class TileBasicMachine extends TilePlasmaMachineBase{

	public TileBasicMachine() {
		super(PMTilesInit.BASIC_MACHINE.getTileEntityType(), 1000);
	}
	
}
