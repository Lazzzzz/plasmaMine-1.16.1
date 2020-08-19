package laz.plasmine.content.tiles.storage;

import java.util.Objects;

import laz.plasmine.registry.init.PMContainersInit;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketBuffer;
import net.minecraft.tileentity.TileEntity;

public class ContainerPlasmaStorage extends Container {

	public TilePlasmaStorage tile;

	public ContainerPlasmaStorage(final int windowId, final PlayerInventory playerInventory, final PacketBuffer data) {
		this(windowId, getTileEntity(playerInventory, data), playerInventory);
	}

	public ContainerPlasmaStorage(int id, final TilePlasmaStorage tile, PlayerInventory inv) {
		super(PMContainersInit.PLASMA_STORAGE_CONTAINER.get(), id);
		this.tile = tile;
		for (int i = 0; i < 3; ++i) {
			for (int j = 0; j < 9; ++j) {
				this.addSlot(new Slot(inv, j + i * 9 + 9, 8 + j * 18, 84 + i * 18));
			}
		}

		for (int k = 0; k < 9; ++k) {
			this.addSlot(new Slot(inv, k, 8 + k * 18, 142));
		}
	}

	@Override
	public boolean canInteractWith(PlayerEntity playerIn) {
		return true;
	}

	public TilePlasmaStorage getTile() {
		return tile;
	}

	protected static TilePlasmaStorage getTileEntity(final PlayerInventory playerInventory, final PacketBuffer data) {
		Objects.requireNonNull(playerInventory, "playerInventory cannot be null");
		Objects.requireNonNull(data, "data cannot be null");
		final TileEntity tileAtPos = playerInventory.player.world.getTileEntity(data.readBlockPos());
		if (tileAtPos instanceof TilePlasmaStorage) {
			return (TilePlasmaStorage) tileAtPos;
		}
		throw new IllegalStateException("Tile entity is not correct! " + tileAtPos);
	}

	@Override
	public ItemStack transferStackInSlot(PlayerEntity playerIn, int index) {
		return ItemStack.EMPTY;
	}
}
