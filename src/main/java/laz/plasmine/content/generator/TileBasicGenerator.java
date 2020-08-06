package laz.plasmine.content.generator;

import laz.plasmine.content.base.generator.TileGeneratorBase;
import laz.plasmine.registry.init.PMTilesInit;
import net.minecraft.block.Blocks;
import net.minecraft.block.TorchBlock;

public class TileBasicGenerator extends TileGeneratorBase {

	public TileBasicGenerator() {
		super(PMTilesInit.BASIC_GENERATOR.getTileEntityType(), 10000, 20);
	}
	
	@Override
	public void tick() {
		if (!world.isRemote) {
			if (world.getBlockState(pos.down()) == Blocks.LAVA.getDefaultState()) plasmaHelper.addPlasma(2);
			else if (world.getBlockState(pos.down()).getBlock() instanceof TorchBlock) plasmaHelper.addPlasma(1);
		}
		super.tick();
	}

}
