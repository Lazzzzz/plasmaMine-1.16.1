package laz.plasmine.content.tiles.generator.electromagneticgenerator;

import laz.plasmine.base.container.ContainerPlasma;
import laz.plasmine.base.generator.TileGeneratorBase;
import laz.plasmine.registry.init.PMContainersInit;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.network.PacketBuffer;

public class ContainerEMGenerator extends ContainerPlasma {

	public ContainerEMGenerator(final int windowId, final PlayerInventory playerInventory, final PacketBuffer data) {
		this(windowId, playerInventory, getTileEntity(playerInventory, data));
	}

	public ContainerEMGenerator(int id, PlayerInventory inv, final TileGeneratorBase tile) {
		super(PMContainersInit.EM_CONTAINER.get(), id, tile, inv);
		
	}
}
