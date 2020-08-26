package laz.plasmine.base.multiblock.input;

import laz.plasmine.base.BlockRotationBase;
import laz.plasmine.base.plasma.TilePlasmaMachineBase;
import laz.plasmine.registry.init.PMTilesInit;
import laz.plasmine.util.BlockPosUtil;
import laz.plasmine.util.interfaces.ISlave;
import net.minecraft.block.BlockState;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;

public class TilePlasmaInput extends TilePlasmaMachineBase implements ISlave {

	private BlockPos bind = null;

	public TilePlasmaInput() {
		super(PMTilesInit.PLASMA_INPUT.getTileEntityType(), 0);
	}

	@Override
	public void bindToMaster(BlockPos masterPos) {
		bind = masterPos;
	}

	@Override
	public int receiveEnergy(int amount) {
		markDirty();
		if (isBind())
			return plasmaHelper.addPlasma(amount);
		return amount;
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
	public void func_230337_a_(BlockState p_230337_1_, CompoundNBT p_230337_2_) {
		super.func_230337_a_(p_230337_1_, p_230337_2_);
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

	@Override
	public boolean getConnectionFace(Direction face) {
		return world.getBlockState(pos).get(BlockRotationBase.FACING) == face;
	}

	@Override
	public int getConsomationPerTick() {
		return 999999999;
	}

}
