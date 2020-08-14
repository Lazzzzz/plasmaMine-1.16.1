package laz.plasmine.base.multiblock;

import laz.plasmine.util.BlockPosUtil;
import laz.plasmine.util.interfaces.ISlave;
import net.minecraft.block.BlockState;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.math.BlockPos;

public class TileBlockSlave extends TileEntity implements ISlave {

	private BlockPos bind;
	
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
		compound = BlockPosUtil.writeBlockPos(compound, bind, "bind");
		return super.write(compound);
	}
	
	@Override
	public void func_230337_a_(BlockState p_230337_1_, CompoundNBT p_230337_2_) {
		bind = BlockPosUtil.readBlockPos(p_230337_2_, "bind");
		super.func_230337_a_(p_230337_1_, p_230337_2_);
	}

	@Override
	public boolean isBind() {
		return bind != null;
	}

	@Override
	public void unbindToMaster() {
		bind = null;
	}

}
