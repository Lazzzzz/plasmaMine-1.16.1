package laz.plasmine.content.tiles.cable;

import laz.plasmine.api.base.cable.BlockCableBase;
import net.minecraft.block.BlockState;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.IBlockReader;

public class BlockBasicPlasmaCable extends BlockCableBase {

	public BlockBasicPlasmaCable() {
		super();
	}
	
	@Override
	public TileEntity createTileEntity(BlockState state, IBlockReader world) {
		return new TileBasicPlasmaCable();
	}
}
