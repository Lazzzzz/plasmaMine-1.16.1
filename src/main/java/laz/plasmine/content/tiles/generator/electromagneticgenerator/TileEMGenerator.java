package laz.plasmine.content.tiles.generator.electromagneticgenerator;

import java.util.ArrayList;
import java.util.List;

import laz.plasmine.base.BlockRotationBase;
import laz.plasmine.base.generator.TileGeneratorBase;
import laz.plasmine.base.multiblock.input.TileItemInput;
import laz.plasmine.base.multiblock.output.TilePlasmaOutput;
import laz.plasmine.content.tiles.storage.BlockPlasmaStorage;
import laz.plasmine.network.PacketHandler;
import laz.plasmine.network.helpers.PlasmaHelperPacket;
import laz.plasmine.registry.init.PMItemsInit;
import laz.plasmine.registry.init.PMTilesInit;
import laz.plasmine.util.BlockPosUtil;
import laz.plasmine.util.DirectionUtils;
import laz.plasmine.util.interfaces.IMaster;
import laz.plasmine.util.interfaces.ISlave;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.inventory.container.Container;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraftforge.fml.network.NetworkDirection;

public class TileEMGenerator extends TileGeneratorBase implements IMaster {

	private List<BlockPos> connectedBlock = new ArrayList<BlockPos>();
	private int maxCooking = 20 * 4;
	private int cooking = 0;
	private boolean isFormed;
	private BlockPos input;
	private BlockPos output;

	public TileEMGenerator(int maxCapacity, int rate, int production) {
		super(PMTilesInit.EM_GENERATOR.getTileEntityType(), maxCapacity, rate, production, 1);
	}

	@Override
	public void tick() {
		if (!world.isRemote) {
			sendData();
			if (!isFormed) {
				setWorkingState(false);
				if (checkIsFormed()) isFormed = true;
				else reset();
			} else process();
		}
	}
	
	@Override
	protected void sendData() {
		int arg0 = -1;
		int arg1 = -1;
		if (isFormed) {
			arg0 = plasmaHelper.getCapacity();
			arg1 = plasmaHelper.getMaxCapacity();
		}
		
		List<? extends PlayerEntity> players = world.getPlayers();
		for (int i = 0; i < players.size(); i++) {
			PacketHandler.INSTANCE.sendTo(new PlasmaHelperPacket(pos, arg0, arg1),
					((ServerPlayerEntity) players.get(i)).connection.netManager, NetworkDirection.PLAY_TO_CLIENT);
		}
	}
	
	private void process() {
		if (cooking > 0) {
			cooking --;
			plasmaHelper.addPlasma(generation);
		}else {
			ItemStack stack = getItemFromInput();
			if (stack != ItemStack.EMPTY) {
				cooking = maxCooking;
				setWorkingState(true);
			}else {
				setWorkingState(false);
			}
		}
		TileEntity tile = world.getTileEntity(output);
		if (tile instanceof TilePlasmaOutput) {
			((TilePlasmaOutput) tile).setGeneration(generation);
			((TilePlasmaOutput) tile).setPlasmaHelper(plasmaHelper);
		}
	}
	
	private ItemStack getItemFromInput() {
		if (plasmaHelper.getCapacityLeft() == 0) return ItemStack.EMPTY;
		
		TileItemInput tile = (TileItemInput) world.getTileEntity(input);
		if (tile != null) {
			for (int i = 0; i < tile.getSizeInventory(); i++) {
				ItemStack stack = tile.getStackInSlot(i);
				if (!stack.isEmpty() && stack.getItem() == PMItemsInit.IONIZED_RAPESEED.get()) {
					tile.decrStackSize(i, 1);
					return stack;
				}
			}
		}		
		return ItemStack.EMPTY;
	}
	
	private void reset() {
		input = null;
		output = null;
		cooking = 0;
		
		Direction dir = world.getBlockState(pos).get(BlockPlasmaStorage.FACING).getOpposite();
		BlockPos p = DirectionUtils.getPosDirection(pos, dir, 2).down();
		
		sendStructureUnBind(p, dir);
	}

	@Override
	public boolean getConnectionFace(Direction face) {
		return false;
	}

	@Override
	public CompoundNBT write(CompoundNBT compound) {
		compound.putInt("cooking", cooking);

		compound.putBoolean("isformed", isFormed);
		compound = BlockPosUtil.writeListBlockPos(compound, connectedBlock, "connected");

		if (input != null)
			compound = BlockPosUtil.writeBlockPos(compound, input, "input");
		if (output != null)
			compound = BlockPosUtil.writeBlockPos(compound, output, "output");

		return super.write(compound);
	}

	@Override
	public void read(BlockState p_230337_1_, CompoundNBT compound) {
		cooking = compound.getInt("cooking");

		isFormed = compound.getBoolean("isformed");
		connectedBlock = BlockPosUtil.readListBlockPos(compound, "connected");

		if (BlockPosUtil.containsBlockPos(compound, "input"))
			input = BlockPosUtil.readBlockPos(compound, "input");
		if (BlockPosUtil.containsBlockPos(compound, "output"))
			output = BlockPosUtil.readBlockPos(compound, "output");

		super.read(p_230337_1_, compound);
	}

	@Override
	public Container createMenu(int id, PlayerInventory playerInv, PlayerEntity player) {
		return new ContainerEMGenerator(id, playerInv, this);
	}

	@Override
	public ITextComponent getDisplayName() {
		return new StringTextComponent("EM generator");
	}

	@Override
	public List<BlockPos> getConnectedBlock() {
		return connectedBlock;
	}

	@Override
	public boolean checkIsFormed() {
		if (livingtick % 50 != 0) return false;
		
		Direction dir = world.getBlockState(pos).get(BlockPlasmaStorage.FACING).getOpposite();
		BlockPos p = DirectionUtils.getPosDirection(pos, dir, 2).down();

		if (!checkStructure(p, dir))
			return false;

		sendStructureBind(p, dir);
		return true;
	}

	private boolean checkStructure(BlockPos p, Direction dir) {
		// COILS AND TOP
		for (int i = -1; i < 2; i++) {
			for (int j = -1; j < 2; j++) {
				for (int k = 0; k < 5; k++) {
					BlockPos pos = p.add(i, k, j);
					if (k == 0 || k == 4) {
						if (world.getBlockState(pos) != PMTilesInit.MACHINE_BLOCK.getDefaultState()) return false;
					} else if (world.getBlockState(pos) != PMTilesInit.MAGNETIC_COIL.getDefaultState())
						return false;

					if (((ISlave) world.getTileEntity(pos)).isBind()) {
						return false;
					}
				}
			}
		}
		// INPUT OUTPUT
		if (!checkForInputOutput(p.up(), dir)) return false;
		

		// EXTRA
		BlockPos pos;
		for (int i = 0; i < 2; i++) {
			pos = DirectionUtils.getPosDirection(p.up(i), dir, 2);
			if (world.getBlockState(pos) != PMTilesInit.MACHINE_BLOCK.getDefaultState())
				return false;
			else if (((ISlave) world.getTileEntity(pos)).isBind())
				return false;
		}

		if (world.getBlockState(DirectionUtils.getPosDirection(p, dir.getOpposite(), 2)) != PMTilesInit.MACHINE_BLOCK
				.getDefaultState())
			return false;
		else if (((ISlave) world.getTileEntity(DirectionUtils.getPosDirection(p, dir.getOpposite(), 2))).isBind())
			return false;

		if (world.getBlockState(DirectionUtils.getPosDirection(p, dir.rotateY(), 2)) != PMTilesInit.MACHINE_BLOCK
				.getDefaultState())
			return false;
		else if (((ISlave) world.getTileEntity(DirectionUtils.getPosDirection(p, dir.rotateY(), 2))).isBind())
			return false;

		if (world.getBlockState(DirectionUtils.getPosDirection(p, dir.rotateYCCW(), 2)) != PMTilesInit.MACHINE_BLOCK
				.getDefaultState())
			return false;
		else if (((ISlave) world.getTileEntity(DirectionUtils.getPosDirection(p, dir.rotateYCCW(), 2))).isBind())
			return false;

		return true;
	}

	public boolean checkForInputOutput(BlockPos p, Direction dir) {
		Direction storageDir = dir.getOpposite();
		input = null;
		output = null;
		if (storageDir == Direction.WEST || storageDir == Direction.EAST) {
			BlockState state = world.getBlockState(p.add(0, 0, 2));
			setInputOutput(state, p.add(0, 0, 2));
			state = world.getBlockState(p.add(0, 0, -2));
			setInputOutput(state, p.add(0, 0, -2));
		} else {
			BlockState state = world.getBlockState(p.add(2, 0, 0));
			setInputOutput(state, p.add(2, 0, 0));
			state = world.getBlockState(p.add(-2, 0, 0));
			setInputOutput(state, p.add(-2, 0, 0));
		}

		return input != null && output != null;
	}

	public void setInputOutput(BlockState state, BlockPos pos) {

		if (state.getBlock() == PMTilesInit.ITEM_INPUT.getBlock())
			input = pos;

		else if (state.getBlock() == PMTilesInit.PLASMA_OUTPUT.getBlock())
			output = pos;
	}

	@Override
	public void sendStructureBind(BlockPos p, Direction dir) {
		for (int i = -1; i < 2; i++) {
			for (int j = -1; j < 2; j++) {
				for (int k = 0; k < 5; k++) {
					BlockPos pp = p.add(i, k, j);
					((ISlave) world.getTileEntity(pp)).bindToMaster(pos);
				}
			}
		}

		for (int i = 0; i < 2; i++) {
			BlockPos pos0 = DirectionUtils.getPosDirection(p.up(i), dir, 2);
			BlockPos pos1 = DirectionUtils.getPosDirection(p.up(i), dir.rotateY(), 2);
			BlockPos pos2 = DirectionUtils.getPosDirection(p.up(i), dir.rotateYCCW(), 2);
			((ISlave) world.getTileEntity(pos0)).bindToMaster(pos);
			((ISlave) world.getTileEntity(pos1)).bindToMaster(pos);
			((ISlave) world.getTileEntity(pos2)).bindToMaster(pos);
		}

		((ISlave) world.getTileEntity(DirectionUtils.getPosDirection(p, dir.getOpposite(), 2))).bindToMaster(pos);

	}

	@Override
	public void sendStructureUnBind(BlockPos p, Direction dir) {
		for (int i = -1; i < 2; i++) {
			for (int j = -1; j < 2; j++) {
				for (int k = 0; k < 5; k++) {
					BlockPos pp = p.add(i, k, j);
					TileEntity tile = world.getTileEntity(pp);
					if (tile instanceof ISlave) ((ISlave) tile).unbindToMaster();
				}
			}
		}

		for (int i = 0; i < 2; i++) {
			BlockPos pos0 = DirectionUtils.getPosDirection(p.up(i), dir, 2);
			BlockPos pos1 = DirectionUtils.getPosDirection(p.up(i), dir.rotateY(), 2);
			BlockPos pos2 = DirectionUtils.getPosDirection(p.up(i), dir.rotateYCCW(), 2);
			TileEntity tile0 = world.getTileEntity(pos0);
			TileEntity tile1 = world.getTileEntity(pos1);
			TileEntity tile2 = world.getTileEntity(pos2);
			if (tile0 instanceof ISlave) ((ISlave) tile0).unbindToMaster();
			if (tile1 instanceof ISlave) ((ISlave) tile1).unbindToMaster();
			if (tile2 instanceof ISlave) ((ISlave) tile2).unbindToMaster();
		}
		TileEntity tile = world.getTileEntity(DirectionUtils.getPosDirection(p, dir.getOpposite(), 2));
		if (tile instanceof ISlave) ((ISlave) tile).unbindToMaster();
	}
	
	@Override
	public void receiveDestroy(BlockPos pos, List<BlockPos> connectedBlock) {
		Direction dir = world.getBlockState(this.pos).get(BlockRotationBase.FACING).getOpposite();
		BlockPos p = DirectionUtils.getPosDirection(this.pos, dir, 2).down();
		isFormed = false;
		sendStructureUnBind(p, dir);
		IMaster.super.receiveDestroy(pos, connectedBlock);
	}

}
