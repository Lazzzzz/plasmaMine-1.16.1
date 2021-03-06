package laz.plasmine.content.tiles.heat.sedimentcrystalizer;

import laz.plasmine.api.container.OutputSlot;
import laz.plasmine.base.container.ContainerHeat;
import laz.plasmine.base.heat.TileHeatMachineBase;
import laz.plasmine.registry.init.PMContainersInit;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketBuffer;

public class ContainerSedimentCrystalizer extends ContainerHeat {

	public ContainerSedimentCrystalizer(final int windowId, final PlayerInventory playerInventory,
			final PacketBuffer data) {
		this(windowId, playerInventory, getTileEntity(playerInventory, data));
	}

	public ContainerSedimentCrystalizer(int id, PlayerInventory inv, final TileHeatMachineBase tile) {
		super(PMContainersInit.SEDIMENT_CRYSTALIZER_CONTAINER.get(), id, tile, inv);
		this.addSlot(new Slot(tile, 0, 45, 36));
		this.addSlot(new OutputSlot(tile, 1, 117, 36));
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
			if (!this.mergeItemStack(slot.getStack(), 36, 37, false)) {
				return itemstack;
			}
		}
		
		return itemstack;
	}
}
