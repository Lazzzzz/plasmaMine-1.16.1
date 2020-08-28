package laz.plasmine.base.generator;

import java.util.List;

import laz.plasmine.api.PlasmaHelper;
import laz.plasmine.network.PacketHandler;
import laz.plasmine.network.helpers.PlasmaHelperPacket;
import laz.plasmine.util.interfaces.IConnection;
import laz.plasmine.util.interfaces.IPlasmaGenerator;
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
import net.minecraft.util.text.ITextComponent;
import net.minecraftforge.fml.network.NetworkDirection;

public class TileGeneratorBase extends TileEntity
		implements IConnection, ITickableTileEntity, IPlasmaGenerator, INamedContainerProvider, IInventory {

	protected PlasmaHelper plasmaHelper;
	protected boolean[] connected = new boolean[6];
	protected int generation;
	protected int size;
	public NonNullList<ItemStack> content;
	public int livingtick = 0;

	public TileGeneratorBase(TileEntityType<?> tileEntityTypeIn, int maxCapacity, int maxSend, int generate, int size) {
		super(tileEntityTypeIn);
		plasmaHelper = new PlasmaHelper(maxCapacity, maxSend);
		generation = generate;
		this.size = size; 
		content = NonNullList.withSize(size, ItemStack.EMPTY);
	}

	@Override
	public void tick() {
		livingtick ++;
		if (!world.isRemote) {
			sendData();
			if (livingtick % 40 == 0)
				connectedTo(world, pos, connected);
			int energy;
			if (world.isBlockPowered(pos)) energy = 0;
			else energy	= produceEnergy();
			
			setWorkingState(energy > 0);
			plasmaHelper.addPlasma(energy);
			int sendit = sendEnergy(world, pos, plasmaHelper.sendPlasma());
			plasmaHelper.removePlasma(sendit);
			markDirty();
		}
	}

	@Override
	public PlasmaHelper getPlasmaHelper() {
		return plasmaHelper;
	}

	@Override
	public CompoundNBT write(CompoundNBT compound) {
		plasmaHelper.write(compound);
		ItemStackHelper.saveAllItems(compound, content);
		return super.write(compound);
	}

	@Override
	public void read(BlockState p_230337_1_, CompoundNBT p_230337_2_) {
		super.read(p_230337_1_, p_230337_2_);
		plasmaHelper.read(p_230337_2_);
		ItemStackHelper.loadAllItems(p_230337_2_, content);
	}

	@Override
	public int produceEnergy() {
		return 0;
	}

	@Override
	public void setWorkingState(boolean working) {
		BlockState state = world.getBlockState(pos);
		if (working) {
			if (state.get(BlockGeneratorBase.WORKING) == false)
				world.setBlockState(pos, state.with(BlockGeneratorBase.WORKING, true));
		} else {
			if (state.get(BlockGeneratorBase.WORKING) == true)
				world.setBlockState(pos, state.with(BlockGeneratorBase.WORKING, false));
		}
	}

	@Override
	public Container createMenu(int id, PlayerInventory playerInv, PlayerEntity player) {
		return null;
	}

	@Override
	public ITextComponent getDisplayName() {
		return null;
	}

	public void receiveData(int amount, int cap) {
		plasmaHelper.setCapacity(amount);
		plasmaHelper.setMaxCapacity(cap);
	}

	protected void sendData() {
		List<? extends PlayerEntity> players = world.getPlayers();
		for (int i = 0; i < players.size(); i++) {
			PacketHandler.INSTANCE.sendTo(new PlasmaHelperPacket(pos, plasmaHelper.getCapacity(), plasmaHelper.getMaxCapacity()),
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
		if (index > size - 1)
			return ItemStack.EMPTY;
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
}
