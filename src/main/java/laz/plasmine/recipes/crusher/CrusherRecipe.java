package laz.plasmine.recipes.crusher;

import laz.plasmine.recipes.AbstractPlasmineRecipe;
import laz.plasmine.registry.init.PMRecipesSerializer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

public class CrusherRecipe extends AbstractPlasmineRecipe {

    public final Ingredient itemIn;
    private final ItemStack itemOut;

    public CrusherRecipe(ResourceLocation id, Ingredient itemIn, ItemStack itemOut, int temp, int time) {
        super(PMRecipesSerializer.CRUSHER_TYPE, id, temp, time);
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
        return PMRecipesSerializer.CRUSHER_SERIALIZER.get();
    }

}
