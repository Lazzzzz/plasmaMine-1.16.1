package laz.plasmine.content.generator;

import laz.plasmine.content.base.generator.GeneratorBlockBase;
import net.minecraft.block.BlockState;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.IBlockReader;

public class BasicGenerator extends GeneratorBlockBase {

	public BasicGenerator() {
		super();
	}
	
	@Override
	public TileEntity createTileEntity(BlockState state, IBlockReader world) {
		return new TileBasicGenerator();
	}
	
}
