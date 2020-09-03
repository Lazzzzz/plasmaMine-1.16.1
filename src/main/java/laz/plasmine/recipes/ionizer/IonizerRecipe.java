package laz.plasmine.recipes.ionizer;

import laz.plasmine.recipes.AbstractPlasmineRecipe;
import laz.plasmine.registry.init.PMRecipesSerializer;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.ResourceLocation;

public class IonizerRecipe extends AbstractPlasmineRecipe {

    public final Ingredient itemIn1;
    public final Ingredient itemIn2;
    private final ItemStack itemOut;

    public IonizerRecipe(ResourceLocation id, Ingredient itemIn1, Ingredient itemIn2, ItemStack itemOut, int temp, int time) {
        super(PMRecipesSerializer.IONIZER_TYPE, id, temp, time);
        this.itemIn1 = itemIn1;
        this.itemIn2 = itemIn2;
        this.itemOut = itemOut;
    }
    
    public ItemStack [] getItemIn1() {
    	return itemIn1.getMatchingStacks();
    }
    
    public ItemStack [] getItemIn2() {
    	return itemIn2.getMatchingStacks();
    }
    
    public ItemStack getItemOut() {
        return itemOut.copy();
    }


    @Override
    public IRecipeSerializer<?> getSerializer() {
        return PMRecipesSerializer.IONIZER_SERIALIZER.get();
    }

}
