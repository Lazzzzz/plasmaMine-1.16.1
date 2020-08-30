package laz.plasmine.recipes.sediementextractor;

import java.util.ArrayList;
import java.util.List;

import laz.plasmine.recipes.AbstractPlasmineRecipe;
import laz.plasmine.recipes.sedimentcollector.SedimentCollectorRecipe;
import laz.plasmine.registry.init.PMRecipesSerializer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.item.crafting.RecipeManager;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

public class SedimentExtractorRecipe extends AbstractPlasmineRecipe {

    public final Ingredient itemIn1;
    public final Ingredient itemIn2;
    private final ItemStack itemOut;

    public SedimentExtractorRecipe(ResourceLocation id, Ingredient itemIn1, Ingredient itemIn2, ItemStack itemOut, int temp, int time) {
        super(PMRecipesSerializer.SEDIMENT_EXTRACTOR_TYPE, id, temp, time);
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
        return PMRecipesSerializer.SEDIMENT_EXTRACTOR_SERIALIZER.get();
    }

}
