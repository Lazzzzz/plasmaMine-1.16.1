package laz.plasmine.content.block.slaves;

import laz.plasmine.util.interfaces.IMaster;
import laz.plasmine.util.interfaces.ISlave;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;

public class BlockMachine extends Block {

	public BlockMachine() {
		super(Properties.from(Blocks.IRON_BLOCK));
	}

	@Override
	public boolean hasTileEntity(BlockState state) {
		return true;
	}

	@Override
	public TileEntity createTileEntity(BlockState state, IBlockReader world) {
		return new TileMachine();
	}

	@Override
	public void onReplaced(BlockState state, World worldIn, BlockPos pos, BlockState newState, boolean isMoving) {
		if (!worldIn.isRemote) {
			ISlave tile = (ISlave) worldIn.getTileEntity(pos);
			if (tile.isBind()) {
				IMaster master = (IMaster) worldIn.getTileEntity(tile.getBlockPosMaster());
				tile.sendMasterDestroy(pos, master);
			}
		}
		super.onReplaced(state, worldIn, pos, newState, isMoving);
	}

}
