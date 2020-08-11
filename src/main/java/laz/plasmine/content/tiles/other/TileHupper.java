package laz.plasmine.content.tiles.other;

import laz.plasmine.registry.init.PMTilesInit;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.ItemStackHelper;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.tileentity.HopperTileEntity;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.NonNullList;

public class TileHupper extends TileEntity implements ITickableTileEntity, IInventory {

	int size = 1;
	public NonNullList<ItemStack> content;

	public TileHupper() {
		super(PMTilesInit.HUPPER.getTileEntityType());

		content = NonNullList.withSize(size, ItemStack.EMPTY);
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
	public CompoundNBT write(CompoundNBT compound) {
		ItemStackHelper.saveAllItems(compound, content);
		return super.write(compound);
	}
	
	@Override
	public void func_230337_a_(BlockState p_230337_1_, CompoundNBT p_230337_2_) {
		content = NonNullList.withSize(this.getSizeInventory(), ItemStack.EMPTY);
		super.func_230337_a_(p_230337_1_, p_230337_2_);
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

	@Override
	public void tick() {
		if (!world.isRemote) {
			if (!getStackInSlot(0).isEmpty() && !world.isBlockPowered(pos)) {
				TileEntity tile = world.getTileEntity(pos.up());
				if (tile instanceof IInventory) {
					transferItem((IInventory) tile);
				}
			}
		}	
	}
	
	private void transferItem(IInventory inventory) {
		ItemStack stack = getStackInSlot(0);
		for (int i = 0; i < inventory.getSizeInventory(); i++) {
			ItemStack inv = inventory.getStackInSlot(i);
			if (inv.isEmpty()) {
				inventory.setInventorySlotContents(i, new ItemStack(stack.getItem()));
				stack.shrink(1);
				break;
			}else if (inv.getItem() == stack.getItem() && inv.getCount() < inv.getMaxStackSize()) {
				inv.grow(1);
				stack.shrink(1);
				break;
			}
		}
	}
}
