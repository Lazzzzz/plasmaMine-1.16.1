package laz.plasmine.content.tiles.storage.put;

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
import net.minecraft.world.World;

public class TilePlasmaOutput extends TileGeneratorBase implements ISlave {

	private BlockPos bind = pos;

	public TilePlasmaOutput() {
		super(PMTilesInit.PLASMA_OUTPUT.getTileEntityType(), 0, 0, 0, 0);

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
	public void tick() {
		livingtick++;
		if (!world.isRemote) {
			sendData();
			if (livingtick % 40 == 0)
				connectedTo(world, pos, connected);
			int sendit = sendEnergy(world, pos, plasmaHelper.sendPlasma(generation));
			plasmaHelper.removePlasma(sendit);
			markDirty();
		}
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
		return !BlockPosUtil.areSame(bind, pos);
	}

	@Override
	public void unbindToMaster() {
		bind = pos;
	}

	@Override
	public boolean getConnectionFace(Direction face) {
		return world.getBlockState(pos).get(BlockRotationBase.FACING) == face;
	}

	public void setPlasmaHelper(PlasmaHelper plasma) {
		if (plasma != null)
			this.plasmaHelper = plasma;
	}

}
