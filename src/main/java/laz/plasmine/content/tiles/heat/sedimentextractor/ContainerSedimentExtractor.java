package laz.plasmine.content.tiles.heat.sedimentextractor;

import laz.plasmine.api.base.container.ContainerHeat;
import laz.plasmine.api.base.heat.TileHeatMachineBase;
import laz.plasmine.api.container.OutputSlot;
import laz.plasmine.registry.init.PMContainersInit;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketBuffer;

public class ContainerSedimentExtractor extends ContainerHeat {

	public ContainerSedimentExtractor(final int windowId, final PlayerInventory playerInventory,
			final PacketBuffer data) {
		this(windowId, playerInventory, getTileEntity(playerInventory, data));
	}

	public ContainerSedimentExtractor(int id, PlayerInventory inv, final TileHeatMachineBase tile) {
		super(PMContainersInit.SEDIMENT_EXTRACTOR_CONTAINER.get(), id, tile, inv);
		this.addSlot(new Slot(tile, 0, 44, 21));
		this.addSlot(new Slot(tile, 1, 44, 40));
		this.addSlot(new OutputSlot(tile, 2, 116, 31));
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
