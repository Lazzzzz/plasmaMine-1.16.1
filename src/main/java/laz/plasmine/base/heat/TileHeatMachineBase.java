package laz.plasmine.base.heat;

import java.util.List;

import laz.plasmine.api.HeatHelper;
import laz.plasmine.base.convertor.TileConvertorBase;
import laz.plasmine.network.PacketHandler;
import laz.plasmine.network.packets.HeatHelperPacket;
import laz.plasmine.util.DirectionUtils;
import laz.plasmine.util.interfaces.IHeatMachine;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.ItemStackHelper;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.INamedContainerProvider;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.ITextComponent;
import net.minecraftforge.fml.network.NetworkDirection;

public class TileHeatMachineBase extends TileEntity
		implements ITickableTileEntity, IHeatMachine, INamedContainerProvider, IInventory {

	protected HeatHelper heatHelper;
	protected int size;
	public NonNullList<ItemStack> content;
	public int livingtick = 0;
	
	public TileHeatMachineBase(TileEntityType<?> tileEntityTypeIn, float maxCelcius, float thermo, int size) {
		super(tileEntityTypeIn);
		heatHelper = new HeatHelper(maxCelcius, thermo);
		this.size = size;
		content = NonNullList.withSize(size, ItemStack.EMPTY);
	}

	@Override
	public void tick() {
		livingtick++;
		if (!world.isRemote) {
			sendData();
			
			setWorkingState(world, pos, world.getBlockState(pos), false);
			updatePoweredState(doPower());
			
			if (heatHelper.isWorkingCelcius(world, pos)) onWorking();
			
			if (!isConvertorHere()) heatHelper.coolDown(world, pos);
			
			if (heatHelper.isOverHeating())
				onOverHeat();
			
			markDirty();
		}
	}
	
	private boolean isConvertorHere() {
		BlockPos conv = DirectionUtils.getPosDirection(pos, heatInOut(world.getBlockState(pos)));
		if (world.getTileEntity(conv) instanceof TileConvertorBase) return true;
		return false;
	}

	@Override
	public CompoundNBT write(CompoundNBT compound) {
		heatHelper.write(compound);
		ItemStackHelper.saveAllItems(compound, content);
		return super.write(compound);
	}

	@Override
	public void read(BlockState p_230337_1_, CompoundNBT p_230337_2_) {
		super.read(p_230337_1_, p_230337_2_);
		heatHelper.read(p_230337_2_);
		ItemStackHelper.loadAllItems(p_230337_2_, content);
	}

	@Override
	public void onOverHeat() {
	}

	@Override
	public HeatHelper getHeatHelper() {
		return heatHelper;
	}

	@Override
	public Container createMenu(int id, PlayerInventory playerInv, PlayerEntity player) {
		return null;
	}

	@Override
	public ITextComponent getDisplayName() {
		return null;
	}

	public void receiveData(float amount) {
		heatHelper.setCelcius(amount);
	}

	private void sendData() {
		List<? extends PlayerEntity> players = world.getPlayers();
		for (int i = 0; i < players.size(); i++) {
			PacketHandler.INSTANCE.sendTo(new HeatHelperPacket(pos, heatHelper.getCelcius()),
					((ServerPlayerEntity) players.get(i)).connection.netManager, NetworkDirection.PLAY_TO_CLIENT);
		}
	}

	@Override
	public void clear() {
		content.clear();
	}

	@Override
	public int getSizeInventory() {
		return size;
	}

	@Override
	public boolean isEmpty() {
		return false;
	}

	@Override
	public ItemStack getStackInSlot(int index) {
		if (index > size - 1)
			return ItemStack.EMPTY;
		return content.get(index);
	}

	@Override
	public ItemStack decrStackSize(int index, int count) {
		if (index > size - 1)
			return ItemStack.EMPTY;
		ItemStack stack = content.get(index);
		if (count >= stack.getCount())
			return removeStackFromSlot(index);
		else {
			stack.shrink(count);
			return new ItemStack(stack.getItem(), count);
		}
	}

	@Override
	public ItemStack removeStackFromSlot(int index) {
		if (index > size - 1) return ItemStack.EMPTY;
		ItemStack stack = content.get(index).copy();
		content.set(index, ItemStack.EMPTY);
		return stack;
	}

	@Override
	public void setInventorySlotContents(int index, ItemStack stack) {
		if (index > size - 1)
			return;
		content.set(index, stack);
	}

	@Override
	public boolean isUsableByPlayer(PlayerEntity player) {
		if (this.world.getTileEntity(this.pos) != this) {
			return false;
		} else {
			return !(player.getDistanceSq((double) this.pos.getX() + 0.5D, (double) this.pos.getY() + 0.5D,
					(double) this.pos.getZ() + 0.5D) > 64.0D);
		}
	}
	
	protected void updatePoweredState(boolean powered) {
		BlockState state = world.getBlockState(pos);
		if (powered) {
			if (state.get(BlockHeatMachineBase.POWER) == false)
				world.setBlockState(pos, state.with(BlockHeatMachineBase.POWER, true));
		} else {
			if (state.get(BlockHeatMachineBase.POWER) == true)
				world.setBlockState(pos, state.with(BlockHeatMachineBase.POWER, false));
		}
	}

	@Override
	public double speedFactor() {
		return ((heatHelper.getCelcius() / heatHelper.getMaxCelcius()) * 5);
	}
	
	public boolean doPower() {
		return false;
	}
	
	public double getProgress() {
		return 0;
	}
	
	public double getMaxProgress() {
		return 0;
	}
}
