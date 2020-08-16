package laz.plasmine.content.block.slaves.coils;

import laz.plasmine.util.BlockPosUtil;
import laz.plasmine.util.interfaces.IMaster;
import laz.plasmine.util.interfaces.ISlave;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class BlockCoilBase extends Block {

	public int maxstorage = 0;
	public int output = 0;
	
	public BlockCoilBase(Properties properties, int maxStorage, int output) {
		super(properties);
		this.maxstorage = maxStorage;
		this.output = output;
	}
	
	@Override
	public boolean hasTileEntity(BlockState state) {
		return true;
	}
	
	@Override
	public void onReplaced(BlockState state, World worldIn, BlockPos pos, BlockState newState, boolean isMoving) {
		if (!worldIn.isRemote) {
			ISlave tile = (ISlave) worldIn.getTileEntity(pos);
			if (!BlockPosUtil.areSame(tile.getBlockPosMaster(), pos)) {
				IMaster master = (IMaster) worldIn.getTileEntity(tile.getBlockPosMaster());
				tile.sendMasterDestroy(pos, master);
			}
		}
		super.onReplaced(state, worldIn, pos, newState, isMoving);
	}

}
