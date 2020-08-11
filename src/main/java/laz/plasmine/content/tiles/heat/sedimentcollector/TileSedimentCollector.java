package laz.plasmine.content.tiles.heat.sedimentcollector;

import laz.plasmine.api.base.heat.TileHeatMachineBase;
import laz.plasmine.recipes.sedimentcollector.SedimentCollectorRecipe;
import laz.plasmine.registry.init.PMTilesInit;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.item.ItemStack;
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
	public void onWorking() {
		if (world.getDayTime() % 100 == 0) {
			float random = world.rand.nextFloat();
			world.getRecipeManager().getRecipes().stream().filter(recipe -> recipe instanceof SedimentCollectorRecipe)
					.forEach(e -> produceItem((SedimentCollectorRecipe) e, random));
		}
	}

	private void produceItem(SedimentCollectorRecipe recipe, float rand) {
		if (recipe.getChance() > rand && recipe.getTemp() < heatHelper.getCelcius()) {
			for (int i = 0; i < size; i++) {
				ItemStack stack = getStackInSlot(i);
				ItemStack out = recipe.getItemOut().copy();
				if (stack.isEmpty()) {
					setInventorySlotContents(i, out);
					break;
				} else if (stack.isItemEqual(out) && stack.getCount() < 64) {
					stack.grow(1);
					break;
				}
			}
		}
	}

	@Override
	public float consumeHeat() {
		return heatHelper.getThermoConductivity() / 5;
	}

}
