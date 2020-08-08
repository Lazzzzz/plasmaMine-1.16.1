package laz.plasmine.content.tiles.generator;

import laz.plasmine.content.base.container.ContainerPlasma;
import laz.plasmine.registry.init.PMContainersInit;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.network.PacketBuffer;

public class ContainerBasicGenerator extends ContainerPlasma {

	public ContainerBasicGenerator(final int windowId, final PlayerInventory playerInventory, final PacketBuffer data) {
		this(windowId, playerInventory, getTileEntity(playerInventory, data));
	}

	public ContainerBasicGenerator(int id, PlayerInventory inv, final TileBasicGenerator tile) {
		super(PMContainersInit.BASIC_GENERATOR_CONTAINER.get(), id, tile, inv);
		this.addSlot(new Slot(tile, 0, 80, 35));
		
	}
	
	@Override
	public ItemStack transferStackInSlot(PlayerEntity playerIn, int index) {
		ItemStack itemstack = ItemStack.EMPTY;
		Slot slot = this.inventorySlots.get(index);
		System.out.println(index);
		if (index > 35) {
			if (!this.mergeItemStack(slot.getStack(), 0, 36, false)) {
				return itemstack;
			}
		} else if (slot.getStack().getItem() == Items.IRON_NUGGET){
			if (!this.mergeItemStack(slot.getStack(), 36, 37, false)) {
				return itemstack;
			}
		}
		return itemstack;
	}
}
