package laz.plasmine.content.tiles.storage;

import java.util.ArrayList;
import java.util.List;

import laz.plasmine.registry.init.PMTilesInit;
import laz.plasmine.util.BlockPosUtil;
import laz.plasmine.util.DirectionUtils;
import laz.plasmine.util.interfaces.IMaster;
import laz.plasmine.util.interfaces.ISlave;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;

public class TileEnergyStorage extends TileEntity implements IMaster, ITickableTileEntity {

	private List<BlockPos> connectedBlock = new ArrayList<BlockPos>();

	private boolean isFormed = false;

	public TileEnergyStorage() {
		super(PMTilesInit.ENERGY_STORAGE.getTileEntityType());
	}

	@Override
	public List<BlockPos> getConnectedBlock() {
		return connectedBlock;
	}

	@Override
	public CompoundNBT write(CompoundNBT compound) {
		compound.putBoolean("isformed", isFormed);
		compound = BlockPosUtil.writeListBlockPos(compound, connectedBlock, "connected");
		return super.write(compound);
	}
	
	@Override
	public void func_230337_a_(BlockState p_230337_1_, CompoundNBT compound) {
		isFormed = compound.getBoolean("isformed");
		connectedBlock = BlockPosUtil.readListBlockPos(compound, "connected");
		super.func_230337_a_(p_230337_1_, compound);
	}
	
	@Override
	public boolean checkIsFormed() {
		BlockPos p = DirectionUtils.getPosDirection(pos,
				world.getBlockState(pos).get(BlockEnergyStorage.FACING).getOpposite());
		if (!checkStructure(p))
			return false;
		sendStructureBind(p);
		return true;
	}

	private boolean checkStructure(BlockPos p) {
		// COILS
		for (int i = -1; i < 2; i++) {
			for (int j = -1; j < 2; j++) {
				for (int k = 1; k < 3; k++) {
					if (world.getBlockState(p.add(i, k, j)) != PMTilesInit.LISIUM_COIL.getDefaultState()) {
						return false;
					}

					if (((ISlave) world.getTileEntity(p.add(i, k, j))).isBind())
						return false;
				}
			}
		}
		// TOP
		for (int i = -1; i < 2; i++) {
			for (int j = -1; j < 2; j++) {
				for (int k = 1; k < 3; k++) {
					if (world.getBlockState(p.add(i, k, j)) != PMTilesInit.MACHINE_BLOCK.getDefaultState()) {
						return false;
					}

					if (((ISlave) world.getTileEntity(p.add(i, k, j))).isBind())
						return false;
				}
			}
		}
		
		//BOTTOM INPUT OUTPUT AND OTHER

		return true;
	}

	private void sendStructureBind(BlockPos p) {
		for (int i = -1; i < 2; i++) {
			for (int j = -1; j < 2; j++) {
				for (int k = 0; k < 4; k++) {
					TileEntity slave = world.getTileEntity(p.add(i, k, j));
					if (slave instanceof ISlave)
						((ISlave) slave).bindToMaster(pos);
				}
			}
		}
	}

	private void sendStructureUnBind(BlockPos p) {
		for (int i = -1; i < 2; i++) {
			for (int j = -1; j < 2; j++) {
				for (int k = 1; k < 3; k++) {
					TileEntity slave = world.getTileEntity(p.add(i, k, j));
					if (slave instanceof ISlave)
						((ISlave) slave).unbindToMaster();
				}
			}
		}
	}

	@Override
	public void tick() {
		if (!world.isRemote) {
			if (!isFormed && checkIsFormed())
				isFormed = true;

		}
	}

	@Override
	public void receiveDestroy(BlockPos pos, List<BlockPos> connectedBlock) {
		BlockPos p = DirectionUtils.getPosDirection(this.pos,
				world.getBlockState(this.pos).get(BlockEnergyStorage.FACING).getOpposite());
		isFormed = false;
		sendStructureUnBind(p);
		IMaster.super.receiveDestroy(pos, connectedBlock);
	}
}
