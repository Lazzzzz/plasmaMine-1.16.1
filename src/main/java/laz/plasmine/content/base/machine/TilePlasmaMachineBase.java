package laz.plasmine.content.base.machine;

import laz.plasmine.api.PlasmaHelper;
import laz.plasmine.util.IConnection;
import laz.plasmine.util.IPlasmaMachine;
import net.minecraft.block.BlockState;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;

public class TilePlasmaMachineBase extends TileEntity implements ITickableTileEntity, IPlasmaMachine, IConnection {

	protected PlasmaHelper plasmaHelper;
	protected boolean [] connected = new boolean[6];
	
	public TilePlasmaMachineBase(TileEntityType<?> tileEntityTypeIn, int maxCapacity) {
		super(tileEntityTypeIn);
		plasmaHelper = new PlasmaHelper(maxCapacity);
	}

	@Override
	public void tick() {}

	
	public PlasmaHelper getHelper() {
		return plasmaHelper;
	}

	@Override
	public int receiveEnergy(int amount) {
		return plasmaHelper.addPlasma(amount);
	}

	@Override
	public int spaceLeft() {
		return plasmaHelper.getCapacityLeft();
	}

	@Override
	public CompoundNBT write(CompoundNBT compound) {
		plasmaHelper.write(compound);
		return super.write(compound);
	}

	@Override
	public void func_230337_a_(BlockState p_230337_1_, CompoundNBT p_230337_2_) {
		plasmaHelper.read(p_230337_2_);
		super.func_230337_a_(p_230337_1_, p_230337_2_);
	}

}
