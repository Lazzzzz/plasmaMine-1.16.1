package laz.plasmine.content.tiles.coils;

import laz.plasmine.base.multiblock.BlockCoilBase;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.IBlockReader;

public class BlockRosiumCoil extends BlockCoilBase {

	public BlockRosiumCoil() {
		super(Properties.from(Blocks.GOLD_BLOCK), 500000, 1000);

	}

	@Override
	public TileEntity createTileEntity(BlockState state, IBlockReader world) {
		return new TileRosiumCoil();
	}
	
}
