package laz.plasmine.content.heat;

import laz.plasmine.content.base.heat.TileHeatMachineBase;
import laz.plasmine.content.base.plasma.TilePlasmaMachineBase;
import laz.plasmine.registry.init.PMTilesInit;
import net.minecraft.block.Blocks;

public class TileBasicHeatMachine extends TileHeatMachineBase{

	public TileBasicHeatMachine() {
		super(PMTilesInit.BASIC_HEAT_MACHINE.getTileEntityType(), 1000, 0.4f);
	}
	
	
	
}
