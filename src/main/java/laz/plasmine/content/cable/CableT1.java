package laz.plasmine.content.cable;

import laz.plasmine.content.base.cable.CableBase;
import net.minecraft.block.BlockState;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.IBlockReader;

public class CableT1 extends CableBase {

	public CableT1() {
		super();
	}
	
	@Override
	public TileEntity createTileEntity(BlockState state, IBlockReader world) {
		return new TileCableT1();
	}
}
