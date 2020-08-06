package laz.plasmine.content.base.heat;

import laz.plasmine.api.HeatHelper;
import laz.plasmine.util.IHeatMachine;
import net.minecraft.block.BlockState;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;

public class TileHeatMachineBase extends TileEntity implements ITickableTileEntity, IHeatMachine {

	protected HeatHelper heatHelper;
	
	public TileHeatMachineBase(TileEntityType<?> tileEntityTypeIn, float maxCelcius, float thermo) {
		super(tileEntityTypeIn);
		heatHelper = new HeatHelper(maxCelcius, thermo);
	}
	
	@Override
	public void tick() {
		if (!world.isRemote) {
			heatHelper.coolDown(world, pos);
			if (heatHelper.isOverHeating()) onOverHeat();
		}
	}
	
	@Override
	public CompoundNBT write(CompoundNBT compound) {
		heatHelper.write(compound);
		return super.write(compound);
	}

	@Override
	public void func_230337_a_(BlockState p_230337_1_, CompoundNBT p_230337_2_) {
		heatHelper.read(p_230337_2_);
		super.func_230337_a_(p_230337_1_, p_230337_2_);
	}

	@Override
	public void onOverHeat() {
		
	}

	@Override
	public HeatHelper getHeatHelper() {
		return heatHelper;
	}

}
