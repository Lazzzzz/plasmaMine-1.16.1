package laz.plasmine.content.tiles.coils;

import laz.plasmine.base.multiblock.BlockSlave;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.IBlockReader;

public class BlockMagneticCoil extends BlockSlave {

	public BlockMagneticCoil() {
		super(Properties.from(Blocks.IRON_BLOCK));

	}

	@Override
	public TileEntity createTileEntity(BlockState state, IBlockReader world) {
		return new TileMagneticCoil();
	}
	
}
