package laz.plasmine.content.tiles.heat.sedimentcollector;

import laz.plasmine.api.container.OutputSlot;
import laz.plasmine.base.container.ContainerHeat;
import laz.plasmine.base.heat.TileHeatMachineBase;
import laz.plasmine.registry.init.PMContainersInit;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketBuffer;

public class ContainerSedimentCollector extends ContainerHeat {

	public ContainerSedimentCollector(final int windowId, final PlayerInventory playerInventory,
			final PacketBuffer data) {
		this(windowId, playerInventory, getTileEntity(playerInventory, data));
	}

	public ContainerSedimentCollector(int id, PlayerInventory inv, final TileHeatMachineBase tile) {
		super(PMContainersInit.SEDIMENT_COLLECTOR_CONTAINER.get(), id, tile, inv);

		for (int i = 0; i < 3; ++i) {
			for (int j = 0; j < 8; ++j) {
				this.addSlot(new OutputSlot(tile, j + i * 8, 27 + j * 18, 11 + i * 18));
			}
		}
	}

	@Override
	public ItemStack transferStackInSlot(PlayerEntity playerIn, int index) {
		ItemStack itemstack = ItemStack.EMPTY;
		Slot slot = this.inventorySlots.get(index);
		
		if (index > 35) {
			if (!this.mergeItemStack(slot.getStack(), 0, 36, false)) {
				return itemstack;
			}
		}
		return itemstack;
	}
}
