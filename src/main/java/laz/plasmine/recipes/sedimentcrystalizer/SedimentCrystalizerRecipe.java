package laz.plasmine.recipes.sedimentcrystalizer;

import laz.plasmine.recipes.AbstractPlasmineRecipe;
import laz.plasmine.registry.init.PMRecipesSerializer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

public class SedimentCrystalizerRecipe extends AbstractPlasmineRecipe {

    private final ItemStack itemIn;
    private final ItemStack itemOut;

    public SedimentCrystalizerRecipe(ResourceLocation id, ItemStack itemIn, ItemStack itemOut, int temp, int time) {
        super(PMRecipesSerializer.SEDIMENT_CRYSTALIZER_TYPE, id, temp, time);
        this.itemIn = itemIn;
        this.itemOut = itemOut;
    }
    
    public ItemStack getItemIn() {
    	return itemIn.copy();
    }

    public ItemStack getItemOut() {
        return itemOut.copy();
    }

    @Override
    public boolean matches(IInventory inv, World worldIn) {
        return false;
    }

    @Override
    public ItemStack getCraftingResult(IInventory inv) {
        return null;
    }

    @Override
    public boolean canFit(int width, int height) {
        return false;
    }

    @Override
    public ItemStack getRecipeOutput() {
        return null;
    }

    @Override
    public IRecipeSerializer<?> getSerializer() {
        return PMRecipesSerializer.SEDIMENT_CRYSTALIZER_SERIALIZER.get();
    }

}
