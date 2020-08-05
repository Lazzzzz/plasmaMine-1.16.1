package laz.plasmine.content.test;

import laz.plasmine.content.base.TilePlasmaMachineBase;
import laz.plasmine.registry.init.PMTilesInit;
import net.minecraft.tileentity.TileEntityType;

public class TestMachine extends TilePlasmaMachineBase{

	public TestMachine() {
		super(PMTilesInit.TEST.getTileEntityType(), 1000, 10, 1, 20, true);
	}

}
