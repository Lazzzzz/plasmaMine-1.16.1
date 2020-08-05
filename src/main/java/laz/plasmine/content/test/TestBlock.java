package laz.plasmine.content.test;

import laz.plasmine.content.base.PlasmaMachineBlockBase;
import net.minecraft.block.BlockState;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.IBlockReader;

public class TestBlock extends PlasmaMachineBlockBase {

	public TestBlock() {
		super();
	}

	@Override
	public TileEntity createTileEntity(BlockState state, IBlockReader world) {
		return new TestMachine();
	}
	
}
