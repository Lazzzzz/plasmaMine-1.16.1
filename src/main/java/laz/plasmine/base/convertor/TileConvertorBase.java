package laz.plasmine.base.convertor;

import static laz.plasmine.api.PlasmaToHeatConvertion.transformPlasmaToHeat;
import static laz.plasmine.util.DirectionUtils.getPosDirection;

import laz.plasmine.api.HeatHelper;
import laz.plasmine.base.plasma.TilePlasmaMachineBase;
import laz.plasmine.registry.init.PMSoundInit;
import laz.plasmine.util.interfaces.IHeatMachine;
import net.minecraft.block.BlockState;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.Direction;
import net.minecraft.util.SoundCategory;

public class TileConvertorBase extends TilePlasmaMachineBase implements IHeatMachine {

	protected int amountToConvertPerTick;
	protected float efficiency;

	public TileConvertorBase(TileEntityType<?> tileEntityTypeIn, int maxCapacity,
			int convert, float efficiency) {
		super(tileEntityTypeIn, maxCapacity);
		this.amountToConvertPerTick = convert;
		this.efficiency = efficiency;

	}

	@Override
	public void tick() {
		livingtick ++;
		if (!world.isRemote) {
			boolean isWorking = plasmaHelper.getCapacity() > amountToConvertPerTick && world.isBlockPowered(pos);
			setWorkingState(world, pos, world.getBlockState(pos), isWorking);
			
			float heat = 0;
			if (isTileConnect() && world.isBlockPowered(pos)) {
				HeatHelper helper = heatAround();
				if (helper != null) {
					if (plasmaHelper.getCapacity() > amountToConvertPerTick) heat = transformPlasmaToHeat(helper, plasmaHelper.removePlasma(amountToConvertPerTick), efficiency);
					helper.addCelcius(heat);
				}
				
				if (isWorking) {
					if (world.rand.nextInt(100) == 0) world.playSound(null, pos, PMSoundInit.CONVERTOR_RUNNING.get(), SoundCategory.MASTER, 0.5f, 1f);
				}
			}
		}

	}

	public HeatHelper heatAround() {
		Direction dir = heatInOut(world.getBlockState(pos));
		TileEntity tile = world.getTileEntity(getPosDirection(pos, dir));
		if (tile instanceof IHeatMachine) return ((IHeatMachine) tile).getHeatHelper();
		return null;
	}

	public boolean isTileConnect() {
		TileEntity tile = world.getTileEntity(getPosDirection(pos, heatInOut(world.getBlockState(pos))));
		return tile instanceof IHeatMachine;
	}

	@Override
	public boolean getConnectionFace(Direction face) {
		return face == heatInOut(world.getBlockState(pos)).getOpposite();
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
		return null;
	}

}
