package laz.plasmine.base.multiblock;

import laz.plasmine.util.interfaces.ICanWrench;
import laz.plasmine.util.interfaces.IMaster;
import laz.plasmine.util.interfaces.ISlave;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class BlockSlave extends Block implements ICanWrench {

	public BlockSlave(Properties properties) {
		super(properties);
	}
	
	@Override
	public boolean hasTileEntity(BlockState state) {
		return true;
	}

	@Override
	public void onReplaced(BlockState state, World worldIn, BlockPos pos, BlockState newState, boolean isMoving) {
		if (!worldIn.isRemote) {
			ISlave tile = (ISlave) worldIn.getTileEntity(pos);
			if (tile != null && tile.isBind() && state.getBlock() != newState.getBlock()) {
				TileEntity master = worldIn.getTileEntity(tile.getBlockPosMaster());
				if (master instanceof IMaster) {
					tile.sendMasterDestroy(pos, (IMaster) master);
				}
			}
		}
		super.onReplaced(state, worldIn, pos, newState, isMoving);
	}
	
	
	
}
