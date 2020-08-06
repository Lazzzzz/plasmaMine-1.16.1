package laz.plasmine.content.convertor;

import laz.plasmine.content.base.convertor.TileConvertorBase;
import laz.plasmine.content.base.plasma.TilePlasmaMachineBase;
import laz.plasmine.registry.init.PMTilesInit;
import net.minecraft.block.Blocks;

public class TileBasicConvertor extends TileConvertorBase{

	public TileBasicConvertor() {
		super(PMTilesInit.BASIC_CONVERTOR.getTileEntityType(), 1000, 1000, 0.8f, 20);
	}
	
}
