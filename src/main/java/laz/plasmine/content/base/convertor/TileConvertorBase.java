package laz.plasmine.content.base.convertor;

import static laz.plasmine.api.PlasmaToHeatConvertion.transformPlasmaToHeat;
import static laz.plasmine.util.direction.DirectionUtils.getPosDirection;

import laz.plasmine.api.HeatHelper;
import laz.plasmine.content.base.plasma.TilePlasmaMachineBase;
import laz.plasmine.util.IHeatMachine;
import net.minecraft.block.BlockState;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.Direction;

public class TileConvertorBase extends TilePlasmaMachineBase implements IHeatMachine {

	protected HeatHelper heatHelper;
	protected int amountToConvertPerTick;

	public TileConvertorBase(TileEntityType<?> tileEntityTypeIn, int maxCapacity, int maxCelcius, float thermo,
			int convert) {
		super(tileEntityTypeIn, maxCapacity);
		heatHelper = new HeatHelper(maxCelcius, thermo);
		amountToConvertPerTick = convert;

	}

	@Override
	public void tick() {
		if (!world.isRemote) {
			heatHelper.addCelcius(transformPlasmaToHeat(plasmaHelper.removePlasma(amountToConvertPerTick)));
			heatAround();
			heatHelper.coolDown(world, pos);
			if (heatHelper.isOverHeating())
				onOverHeat();
		}
	}

	@Override
	public CompoundNBT write(CompoundNBT compound) {
		heatHelper.write(compound);
		return super.write(compound);
	}

	public void heatAround() {
		Direction dir = heatInOut(world.getBlockState(pos));
		TileEntity tile = world.getTileEntity(getPosDirection(pos, dir));
		if (tile instanceof IHeatMachine) {
			getHeatHelper().transferHeat(((IHeatMachine) tile).getHeatHelper());
			;
		}
	}

	@Override
	public void func_230337_a_(BlockState p_230337_1_, CompoundNBT p_230337_2_) {
		heatHelper.read(p_230337_2_);
		super.func_230337_a_(p_230337_1_, p_230337_2_);
	}

	@Override
	public boolean getConnectionFace(Direction face) {
		return face != heatInOut(world.getBlockState(pos));
	}

	@Override
	public void onOverHeat() {
	}
	
	@Override
	public Direction heatInOut(BlockState state) {
		return state.get(BlockConvertorBase.FACING);
	}

	@Override
	public HeatHelper getHeatHelper() {
		return heatHelper;
	}

}
