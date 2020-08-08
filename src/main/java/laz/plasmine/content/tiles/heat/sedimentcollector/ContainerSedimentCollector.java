package laz.plasmine.content.tiles.heat.sedimentcollector;

import laz.plasmine.content.base.container.ContainerHeat;
import laz.plasmine.content.base.heat.TileHeatMachineBase;
import laz.plasmine.registry.init.PMContainersInit;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.network.PacketBuffer;

public class ContainerSedimentCollector extends ContainerHeat {

	public ContainerSedimentCollector(final int windowId, final PlayerInventory playerInventory, final PacketBuffer data) {
		this(windowId, playerInventory, getTileEntity(playerInventory, data));
	}
	
	
	public ContainerSedimentCollector(int id, PlayerInventory inv, final TileHeatMachineBase tile) {
		super(PMContainersInit.SEDIMENT_COLLECTOR_CONTAINER.get(), id, tile, inv);
	}
}
