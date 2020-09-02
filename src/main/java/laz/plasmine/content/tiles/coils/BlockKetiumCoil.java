package laz.plasmine.content.tiles.coils;

import laz.plasmine.base.multiblock.BlockCoilBase;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.IBlockReader;

public class BlockKetiumCoil extends BlockCoilBase {

	public BlockKetiumCoil() {
		super(Properties.from(Blocks.GOLD_BLOCK), 150000, 750);

	}

	@Override
	public TileEntity createTileEntity(BlockState state, IBlockReader world) {
		return new TileKetiumCoil();
	}
	
}
