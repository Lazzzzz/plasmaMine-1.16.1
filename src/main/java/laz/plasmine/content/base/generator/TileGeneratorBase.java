package laz.plasmine.content.base.generator;

import laz.plasmine.api.PlasmaHelper;
import laz.plasmine.util.IConnection;
import laz.plasmine.util.IPlasmaGenerator;
import net.minecraft.block.BlockState;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;

public class TileGeneratorBase extends TileEntity implements IConnection, ITickableTileEntity, IPlasmaGenerator{

	protected PlasmaHelper plasmaHelper;
	protected boolean [] connected = new boolean[6];
	protected int send;
	
	public TileGeneratorBase(TileEntityType<?> tileEntityTypeIn, int maxCapacity, int maxSend) {
		super(tileEntityTypeIn);
		plasmaHelper = new PlasmaHelper(maxCapacity);
		send = maxSend;
	}

	@Override
	public void tick() {
		if (!world.isRemote) {
			if (world.getDayTime() % 40 == 0)
				connectedTo(world, pos, connected);
			sendEnergy(world, pos, send);
		}
	}

	@Override
	public PlasmaHelper getHelper() {
		return plasmaHelper;
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
