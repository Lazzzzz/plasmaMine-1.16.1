package laz.plasmine.content.tiles.generator.basicgenerator;

import laz.plasmine.base.container.ContainerPlasma;
import laz.plasmine.base.generator.TileGeneratorBase;
import laz.plasmine.registry.init.PMContainersInit;
import laz.plasmine.registry.init.PMItemsInit;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketBuffer;

public class ContainerBasicGenerator extends ContainerPlasma {

	public ContainerBasicGenerator(final int windowId, final PlayerInventory playerInventory, final PacketBuffer data) {
		this(windowId, playerInventory, getTileEntity(playerInventory, data));
	}

	public ContainerBasicGenerator(int id, PlayerInventory inv, final TileGeneratorBase tile) {
		super(PMContainersInit.BASIC_GENERATOR_CONTAINER.get(), id, tile, inv);
		this.addSlot(new Slot(tile, 0, 81, 36));
		
	}
	
	@Override
	public ItemStack transferStackInSlot(PlayerEntity playerIn, int index) {
		ItemStack itemstack = ItemStack.EMPTY;
		Slot slot = this.inventorySlots.get(index);
		if (index > 35) {
			if (!this.mergeItemStack(slot.getStack(), 0, 36, false)) {
				return itemstack;
			}
		} else if (slot.getStack().getItem() == PMItemsInit.RAPESEED_FRUIT.get()){
			if (!this.mergeItemStack(slot.getStack(), 36, 37, false)) {
				return itemstack;
			}
		}
		return itemstack;
	}
}
