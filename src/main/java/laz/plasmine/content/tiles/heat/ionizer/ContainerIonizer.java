package laz.plasmine.content.tiles.heat.ionizer;

import laz.plasmine.api.container.OutputSlot;
import laz.plasmine.base.container.ContainerHeat;
import laz.plasmine.base.heat.TileHeatMachineBase;
import laz.plasmine.registry.init.PMContainersInit;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketBuffer;

public class ContainerIonizer extends ContainerHeat {

	public ContainerIonizer(final int windowId, final PlayerInventory playerInventory,
			final PacketBuffer data) {
		this(windowId, playerInventory, getTileEntity(playerInventory, data));
	}

	public ContainerIonizer(int id, PlayerInventory inv, final TileHeatMachineBase tile) {
		super(PMContainersInit.IONIZER_CONTAINER.get(), id, tile, inv);
		this.addSlot(new Slot(tile, 0, 45, 17));
		this.addSlot(new Slot(tile, 1, 45, 47));
		this.addSlot(new OutputSlot(tile, 2, 117, 32));
		
	}

	@Override
	public ItemStack transferStackInSlot(PlayerEntity playerIn, int index) {
		ItemStack itemstack = ItemStack.EMPTY;
		Slot slot = this.inventorySlots.get(index);
		
		if (index > 35) {
			if (!this.mergeItemStack(slot.getStack(), 0, 36, false)) {
				return itemstack;
			}
		}else {
			if (!this.mergeItemStack(slot.getStack(), 36, 38, false)) {
				return itemstack;
			}
		}
		return itemstack;
	}
}
