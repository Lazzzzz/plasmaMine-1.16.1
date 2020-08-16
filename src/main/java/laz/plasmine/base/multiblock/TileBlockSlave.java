package laz.plasmine.base.multiblock;

import laz.plasmine.util.BlockPosUtil;
import laz.plasmine.util.interfaces.ISlave;
import net.minecraft.block.BlockState;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class TileBlockSlave extends TileEntity implements ISlave {

	private BlockPos bind = pos;
	
	public TileBlockSlave(TileEntityType<?> tileEntityTypeIn) {
		super(tileEntityTypeIn);
	}

	@Override
	public void setWorldAndPos(World p_226984_1_, BlockPos p_226984_2_) {
		super.setWorldAndPos(p_226984_1_, p_226984_2_);
		if (BlockPosUtil.areSame(bind, pos)) bind = pos;
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
		super.func_230337_a_(p_230337_1_, p_230337_2_);
		bind = BlockPosUtil.readBlockPos(p_230337_2_, "bind");
	}

	@Override
	public boolean isBind() {
		return !BlockPosUtil.areSame(bind, pos);
	}

	@Override
	public void unbindToMaster() {
		bind = pos;
	}

}
