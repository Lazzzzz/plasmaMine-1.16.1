package laz.plasmine.content.base.machine;

import static laz.plasmine.util.direction.DirectionUtils.getPosDirection;

import laz.plasmine.api.PlasmaHelper;
import net.minecraft.block.BlockState;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;

public class TilePlasmaMachineBase extends TileEntity implements ITickableTileEntity {

	private PlasmaHelper plasma;
	private int timeTransfere;
	private boolean canTransfer;

	protected BlockPos[] connected = new BlockPos[6];

	public TilePlasmaMachineBase(TileEntityType<?> tileEntityTypeIn, int maxCapacity, int rate, float efficiency,
			int time, boolean transfer) {
		super(tileEntityTypeIn);
		plasma = new PlasmaHelper(maxCapacity, rate, efficiency);
		timeTransfere = time;
		canTransfer = transfer;
	}

	@Override
	public void tick() {
		if (!world.isRemote && canTransfer) {
			if (world.getDayTime() % timeTransfere == 0) {
				getConnectedMachine();
				transferAround();
			}
		}
	}
	
	public void getConnectedMachine() {
		for (int i = 0; i < 6; i++) {
			connected[i] = null;
			BlockPos aroundPos = getPosDirection(pos, Direction.byIndex(i));
			TileEntity tile = world.getTileEntity(aroundPos);
			if (tile != null && tile instanceof TilePlasmaMachineBase)
				connected[i] = aroundPos;
		}
	}

	private void transferAround() {
		for (int i = 0; i < 6; i++) {
			if (connected[i] != null) {
				TilePlasmaMachineBase tile = (TilePlasmaMachineBase) world.getTileEntity(connected[i]);
				PlasmaHelper helper = tile.getHelper();
				if (tile.canTransfer) {
					if (plasma.asMoreThan(helper))
						plasma.transferOut(tile.getHelper());
				} else plasma.transferOut(tile.getHelper());
			}
		}
	}

	public PlasmaHelper getHelper() {
		return plasma;
	}

	@Override
	public CompoundNBT write(CompoundNBT compound) {
		plasma.write(compound);
		return super.write(compound);
	}

	@Override
	public void func_230337_a_(BlockState p_230337_1_, CompoundNBT p_230337_2_) {
		plasma.read(p_230337_2_);
		super.func_230337_a_(p_230337_1_, p_230337_2_);
	}
}
