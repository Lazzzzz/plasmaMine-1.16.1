package laz.plasmine.content.tiles;

import laz.plasmine.base.multiblock.BlockSlave;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.IBlockReader;

public class BlockMachine extends BlockSlave {

	public BlockMachine() {
		super(Properties.from(Blocks.IRON_BLOCK));
	}

	@Override
	public TileEntity createTileEntity(BlockState state, IBlockReader world) {
		return new TileMachine();
	}

}
