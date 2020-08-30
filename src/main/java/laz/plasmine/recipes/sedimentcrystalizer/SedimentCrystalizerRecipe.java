package laz.plasmine.recipes.sedimentcrystalizer;

import java.util.ArrayList;
import java.util.List;

import laz.plasmine.recipes.AbstractPlasmineRecipe;
import laz.plasmine.registry.init.PMRecipesSerializer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.item.crafting.RecipeManager;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

public class SedimentCrystalizerRecipe extends AbstractPlasmineRecipe {

    public final Ingredient itemIn;
    private final ItemStack itemOut;

    public SedimentCrystalizerRecipe(ResourceLocation id, Ingredient itemIn, ItemStack itemOut, int temp, int time) {
        super(PMRecipesSerializer.SEDIMENT_CRYSTALIZER_TYPE, id, temp, time);
        this.itemIn = itemIn;
        this.itemOut = itemOut;
    }
    
    public ItemStack [] getItemIn() {
    	return itemIn.getMatchingStacks();
    }

    public ItemStack getItemOut() {
        return itemOut.copy();
    }

    @Override
    public IRecipeSerializer<?> getSerializer() {
        return PMRecipesSerializer.SEDIMENT_CRYSTALIZER_SERIALIZER.get();
    }
    
}
