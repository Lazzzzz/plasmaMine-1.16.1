package laz.plasmine.recipes.sedimentcollector;

import laz.plasmine.recipes.AbstractPlasmineRecipe;
import laz.plasmine.registry.init.PMRecipesSerializer;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraft.util.ResourceLocation;

public class SedimentCollectorRecipe extends AbstractPlasmineRecipe {

    private final float chance;
    private final ItemStack itemOut;

    public SedimentCollectorRecipe(ResourceLocation id, float chance, ItemStack itemOut, int temp, int time) {
        super(PMRecipesSerializer.SEDIMENT_COLLECTOR_TYPE, id, temp, time);
        this.chance = chance;
        this.itemOut = itemOut;
    }
    
    public float getChance() {
    	return this.chance;
    }

    public ItemStack getItemOut() {
        return itemOut.copy();
    }

    @Override
    public IRecipeSerializer<?> getSerializer() {
        return PMRecipesSerializer.SEDIMENT_COLLECTOR_SERIALIZER.get();
    }
    
}
