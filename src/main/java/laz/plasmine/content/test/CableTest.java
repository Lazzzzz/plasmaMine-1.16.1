package laz.plasmine.content.test;

import laz.plasmine.content.base.cable.CableBase;
import net.minecraft.block.BlockState;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.IBlockReader;

public class CableTest extends CableBase {

	public CableTest() {
		super();
	}
	
	@Override
	public TileEntity createTileEntity(BlockState state, IBlockReader world) {
		return new TileCableTest();
	}
	
}
