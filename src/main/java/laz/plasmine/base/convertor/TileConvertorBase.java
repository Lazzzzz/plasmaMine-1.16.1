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
import net.minecraft.util.math.BlockPos;

public class TileConvertorBase extends TilePlasmaMachineBase implements IHeatMachine {

	protected int amountToConvertPerTick;
	protected float efficiency;
	protected int maxTemp;

	public TileConvertorBase(TileEntityType<?> tileEntityTypeIn, int maxCapacity, int convert, float efficiency,
			int maxTemp) {
		super(tileEntityTypeIn, maxCapacity);
		this.amountToConvertPerTick = convert;
		this.efficiency = efficiency;
		this.maxTemp = maxTemp;
	}

	@Override
	public int receiveEnergy(int amount) {
		return plasmaHelper.addPlasma(amount);
	}

	@Override
	public void tick() {
		livingtick++;
		if (!world.isRemote) {
			HeatHelper helper = heatAround();
			boolean isWorking = world.isBlockPowered(pos) && plasmaHelper.getCapacity() >= amountToConvertPerTick && helper != null;
			setWorkingState(world, pos, world.getBlockState(pos), isWorking);
			float heat = 0;
			if (helper != null) {
				if (isTileConnect() && isWorking && helper.getCelcius() <= maxTemp) {
					if (plasmaHelper.getCapacity() >= amountToConvertPerTick)
						heat = transformPlasmaToHeat(plasmaHelper.removePlasma(amountToConvertPerTick), efficiency,
								helper.getCelcius(), Math.min(helper.getMaxCelcius(), maxTemp), world, pos);

					if (helper.getCelcius() < maxTemp) {
						helper.addCelcius(heat);
					}
				}else {
					float cool = transformPlasmaToHeat(amountToConvertPerTick, efficiency * 1.1f,
							helper.getCelcius(), Math.min(helper.getMaxCelcius(), maxTemp), world, pos);
					helper.coolDown(world, pos, cool);
					
				}
				if (isWorking) {
					if (world.rand.nextInt(100) == 0)
						world.playSound(null, pos, PMSoundInit.CONVERTOR_RUNNING.get(), SoundCategory.MASTER, 0.5f, 1f);
				}
			} 
			markDirty();
		}
		super.tick();
	}

	public HeatHelper heatAround() {
		Direction dir = heatInOut(world.getBlockState(pos));
		BlockPos tilePos = getPosDirection(pos, dir);
		TileEntity tile = world.getTileEntity(tilePos);
		if (tile instanceof IHeatMachine
				&& ((IHeatMachine) tile).heatInOut(world.getBlockState(tilePos)) == dir.getOpposite())
			return ((IHeatMachine) tile).getHeatHelper();
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

	public int getConsomationPerTick() {
		return amountToConvertPerTick;
	}

	@Override
	public HeatHelper getHeatHelper() {
		return null;
	}

	@Override
	public double speedFactor() {
		return 0;
	}
}
