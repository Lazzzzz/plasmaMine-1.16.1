package laz.plasmine.content.tiles.heat.sedimentcollector;

import laz.plasmine.api.base.heat.TileHeatMachineBase;
import laz.plasmine.recipes.sedimentcollector.SedimentCollectorRecipe;
import laz.plasmine.registry.init.PMTilesInit;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;

public class TileSedimentCollector extends TileHeatMachineBase {

	public TileSedimentCollector(int maxCelcius, float thermo) {
		super(PMTilesInit.SEDIMENT_COLLECTOR.getTileEntityType(), maxCelcius, thermo, 24);
	}

	@Override
	public Container createMenu(int id, PlayerInventory playerInv, PlayerEntity player) {
		return new ContainerSedimentCollector(id, playerInv, this);
	}

	@Override
	public ITextComponent getDisplayName() {
		return new StringTextComponent("sediment collector");
	}

	@Override
	public void tick() {
		super.tick();
	}

	@Override
	public void onWorking() {
		if (world.getDayTime() % 20 == 0) {
			world.getRecipeManager().getRecipes().stream().filter(recipe -> recipe instanceof IRecipe)
					.forEach(e -> System.out.println(e));
		}
	}

	@Override
	public float consumeHeat() {
		return heatHelper.getThermoConductivity() / 10;
	}

}
