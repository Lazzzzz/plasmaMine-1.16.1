package laz.plasmine.base.multiblock;

import laz.plasmine.util.BlockPosUtil;
import laz.plasmine.util.interfaces.ISlave;
import net.minecraft.block.BlockState;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.math.BlockPos;

public class TileBlockSlave extends TileEntity implements ISlave {

	private BlockPos bind = null;
	
	public TileBlockSlave(TileEntityType<?> tileEntityTypeIn) {
		super(tileEntityTypeIn);
	}

	@Override
	public void bindToMaster(BlockPos masterPos) {
		bind = masterPos;
	}
	

	@Override
	public BlockPos getBlockPosMaster() {
		return bind;
	}
	
	@Override
	public CompoundNBT write(CompoundNBT compound) {
		if (bind != null) compound = BlockPosUtil.writeBlockPos(compound, bind, "bind");
		return super.write(compound);
	}
	
	@Override
	public void read(BlockState p_230337_1_, CompoundNBT p_230337_2_) {
		super.read(p_230337_1_, p_230337_2_);
		if (BlockPosUtil.containsBlockPos(p_230337_2_, "bind")) bind = BlockPosUtil.readBlockPos(p_230337_2_, "bind");
	}

	@Override
	public boolean isBind() {
		markDirty();
		return bind != null;
	}

	@Override
	public void unbindToMaster() {
		markDirty();
		bind = null;
	}

}
