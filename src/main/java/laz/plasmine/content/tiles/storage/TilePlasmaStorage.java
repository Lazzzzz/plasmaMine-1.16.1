package laz.plasmine.content.tiles.storage;

import java.util.ArrayList;
import java.util.List;

import laz.plasmine.api.PlasmaHelper;
import laz.plasmine.content.block.slaves.coils.BlockCoilBase;
import laz.plasmine.content.tiles.storage.put.TilePlasmaInput;
import laz.plasmine.content.tiles.storage.put.TilePlasmaOutput;
import laz.plasmine.registry.init.PMTilesInit;
import laz.plasmine.util.BlockPosUtil;
import laz.plasmine.util.DirectionUtils;
import laz.plasmine.util.interfaces.IMaster;
import laz.plasmine.util.interfaces.ISlave;
import net.minecraft.block.BlockState;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;

public class TilePlasmaStorage extends TileEntity implements IMaster, ITickableTileEntity {

	private List<BlockPos> connectedBlock = new ArrayList<BlockPos>();

	private BlockPos input;
	private BlockPos output;

	private int maxOutput = -1;
	private int maxStorage = -1;

	private boolean isFormed = false;
	private PlasmaHelper plasma;

	public TilePlasmaStorage() {
		super(PMTilesInit.PLASMA_STORAGE.getTileEntityType());
		input = pos;
		output = pos;
	}

	@Override
	public List<BlockPos> getConnectedBlock() {
		return connectedBlock;
	}

	@Override
	public CompoundNBT write(CompoundNBT compound) {
		compound.putBoolean("isformed", isFormed);
		compound = BlockPosUtil.writeListBlockPos(compound, connectedBlock, "connected");

		compound = BlockPosUtil.writeBlockPos(compound, input, "input");
		compound = BlockPosUtil.writeBlockPos(compound, output, "output");

		compound.putInt("maxOutput", maxOutput);
		compound.putInt("maxStorage", maxStorage);

		return super.write(compound);
	}

	@Override
	public void func_230337_a_(BlockState p_230337_1_, CompoundNBT compound) {
		isFormed = compound.getBoolean("isformed");
		connectedBlock = BlockPosUtil.readListBlockPos(compound, "connected");

		input = BlockPosUtil.readBlockPos(compound, "input");
		output = BlockPosUtil.readBlockPos(compound, "output");

		maxOutput = compound.getInt("maxOutput");
		maxStorage = compound.getInt("maxStorage");

		super.func_230337_a_(p_230337_1_, compound);
	}

	@Override
	public boolean checkIsFormed() {
		Direction dir = world.getBlockState(pos).get(BlockPlasmaStorage.FACING).getOpposite();
		BlockPos p = DirectionUtils.getPosDirection(pos, dir);

		if (!checkStructure(p, dir))
			return false;

		sendStructureBind(p, dir);
		return true;
	}

	private boolean checkStructure(BlockPos p, Direction dir) {
		maxOutput = -1;
		maxStorage = -1;
		// COILS AND TOP
		for (int i = -1; i < 2; i++) {
			for (int j = -1; j < 2; j++) {
				for (int k = 1; k < 4; k++) {
					BlockState state = world.getBlockState(p.add(i, k, j));
					if (k == 3) {
						if (state != PMTilesInit.MACHINE_BLOCK.getDefaultState()) {
							return false;
						}
					} else {
						if (!(state.getBlock() instanceof BlockCoilBase)) {
							return false;
						} else {
							if (maxOutput == -1 && maxStorage == -1) {
								maxOutput = ((BlockCoilBase) state.getBlock()).output;
								maxStorage = ((BlockCoilBase) state.getBlock()).maxstorage;
							} else {
								if (((BlockCoilBase) state.getBlock()).output != maxOutput)
									return false;

								if (((BlockCoilBase) state.getBlock()).maxstorage != maxStorage)
									return false;

							}
						}
					}
					if (((ISlave) world.getTileEntity(p.add(i, k, j))).isBind()) {
						return false;
					}
				}
			}
		}
		// INPUT OUTPUT
		if (!checkForInputOutput(p, dir))
			return false;

		// BOTTOM
		for (int i = -1; i < 2; i++) {
			for (int j = -1; j < 2; j++) {
				BlockState state = world.getBlockState(p.add(i, 0, j));
				if (state.getBlock() != PMTilesInit.PLASMA_INPUT.getBlock()
						&& state.getBlock() != PMTilesInit.PLASMA_OUTPUT.getBlock()
						&& state.getBlock() != PMTilesInit.PLASMA_STORAGE.getBlock()) {
					if (state != PMTilesInit.MACHINE_BLOCK.getDefaultState())
						return false;
				}
			}
		}

		return true;
	}

	@Override
	public void sendStructureBind(BlockPos p, Direction dir) {
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

	@Override
	public void sendStructureUnBind(BlockPos p) {
		for (int i = -1; i < 2; i++) {
			for (int j = -1; j < 2; j++) {
				for (int k = 0; k < 4; k++) {
					TileEntity slave = world.getTileEntity(p.add(i, k, j));
					if (slave instanceof ISlave)
						((ISlave) slave).unbindToMaster();
				}
			}
		}
	}

	@Override
	public void receiveDestroy(BlockPos pos, List<BlockPos> connectedBlock) {
		BlockPos p = DirectionUtils.getPosDirection(this.pos,
				world.getBlockState(this.pos).get(BlockPlasmaStorage.FACING).getOpposite());
		isFormed = false;
		sendStructureUnBind(p);
		IMaster.super.receiveDestroy(pos, connectedBlock);
	}

	public boolean checkForInputOutput(BlockPos p, Direction dir) {
		Direction storageDir = dir.getOpposite();
		input = pos;
		output = pos;
		if (storageDir == Direction.WEST || storageDir == Direction.EAST) {
			BlockState state = world.getBlockState(p.add(0, 0, 1));
			setInputOutput(state, p.add(0, 0, 1));
			state = world.getBlockState(p.add(0, 0, -1));
			setInputOutput(state, p.add(0, 0, -1));
		} else {
			BlockState state = world.getBlockState(p.add(1, 0, 0));
			setInputOutput(state, p.add(1, 0, 0));
			state = world.getBlockState(p.add(-1, 0, 0));
			setInputOutput(state, p.add(-1, 0, 0));
		}

		return input != pos && output != pos;
	}

	public void setInputOutput(BlockState state, BlockPos pos) {
		if (state.getBlock() == PMTilesInit.PLASMA_INPUT.getBlock())
			input = pos;
		else if (state.getBlock() == PMTilesInit.PLASMA_OUTPUT.getBlock())
			output = pos;
	}

	private void reset() {
		maxOutput = -1;
		maxStorage = -1;
		TilePlasmaInput tile = (TilePlasmaInput) world.getTileEntity(input);
		if (tile != null) {
			tile.getPlasmaHelper().setMaxCapacity(0);
			tile.getPlasmaHelper().setMaxSend(0);
		}
		input = pos;
		output = pos;
		plasma = null;
	}

	private void sendDataToInputOutput() {
		if (!BlockPosUtil.areSame(input, pos)) {
			TilePlasmaInput tile = (TilePlasmaInput) world.getTileEntity(input);
			if (tile != null) {
				tile.getPlasmaHelper().setMaxCapacity(maxStorage);
				tile.getPlasmaHelper().setMaxSend(maxOutput);
			}
		}

		if (!BlockPosUtil.areSame(output, pos)) {
			TilePlasmaOutput tile = (TilePlasmaOutput) world.getTileEntity(output);
			if (tile != null)
				tile.setGeneration(maxOutput);
		}
	}

	@Override
	public void tick() {
		if (!world.isRemote) {
			if (!isFormed) {
				if (checkIsFormed())
					isFormed = true;
				else
					reset();
			} else {
				sendDataToInputOutput();
				TilePlasmaInput tile = (TilePlasmaInput) world.getTileEntity(input);
				if (tile != null) {
					plasma = tile.getPlasmaHelper();
					((TilePlasmaOutput) world.getTileEntity(output)).setPlasmaHelper(plasma);
				}
			}
		}
	}

	public PlasmaHelper getHelper() {
		return plasma;
	}

}
