package laz.plasmine.content.block.slaves.coils;

import laz.plasmine.base.multiblock.BlockCoilBase;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.IBlockReader;

public class BlockLisiumCoil extends BlockCoilBase {

	public BlockLisiumCoil() {
		super(Properties.from(Blocks.GOLD_BLOCK), 25000, 500);

	}

	@Override
	public TileEntity createTileEntity(BlockState state, IBlockReader world) {
		return new TileLisiumCoil();
	}
	
}
