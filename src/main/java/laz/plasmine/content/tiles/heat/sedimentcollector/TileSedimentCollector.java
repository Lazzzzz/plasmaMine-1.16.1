package laz.plasmine.content.tiles.heat.sedimentcollector;

import laz.plasmine.content.base.heat.TileHeatMachineBase;
import laz.plasmine.registry.init.PMTilesInit;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;

public class TileSedimentCollector extends TileHeatMachineBase {

	public TileSedimentCollector(int maxCelcius, float thermo) {
		super(PMTilesInit.SEDIMENT_COLLECTOR.getTileEntityType(), maxCelcius, thermo, 15);
	}
	
	@Override
	public Container createMenu(int id, PlayerInventory playerInv, PlayerEntity player) {
		return new ContainerSedimentCollector(id, playerInv, this);
	}

	@Override
	public ITextComponent getDisplayName() {
		return new StringTextComponent("sediment collector");
	}


}
