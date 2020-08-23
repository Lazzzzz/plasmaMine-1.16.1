package laz.plasmine.base.multiblock;

import laz.plasmine.api.PlasmaHelper;
import laz.plasmine.base.BlockRotationBase;
import laz.plasmine.base.generator.TileGeneratorBase;
import laz.plasmine.registry.init.PMTilesInit;
import laz.plasmine.util.BlockPosUtil;
import laz.plasmine.util.interfaces.ISlave;
import net.minecraft.block.BlockState;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;

public class TilePlasmaOutput extends TileGeneratorBase implements ISlave {

	private BlockPos bind = null;

	public TilePlasmaOutput() {
		super(PMTilesInit.PLASMA_OUTPUT.getTileEntityType(), 0, 0, 0, 0);

	}

	@Override
	public void bindToMaster(BlockPos masterPos) {
		bind = masterPos;
	}

	@Override
	public void tick() {
		super.tick();
	}

	@Override
	public BlockPos getBlockPosMaster() {
		return bind;
	}

	public void setGeneration(int amount) {
		generation = amount;
	}

	@Override
	public CompoundNBT write(CompoundNBT compound) {
		if (bind != null)
			compound = BlockPosUtil.writeBlockPos(compound, bind, "bind");
		return super.write(compound);
	}

	@Override
	public void func_230337_a_(BlockState p_230337_1_, CompoundNBT p_230337_2_) {
		super.func_230337_a_(p_230337_1_, p_230337_2_);
		if (BlockPosUtil.containsBlockPos(p_230337_2_, "bind"))
			bind = BlockPosUtil.readBlockPos(p_230337_2_, "bind");
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

	public void setPlasmaHelper(PlasmaHelper plasma) {
		this.plasmaHelper = plasma;
	}

}
