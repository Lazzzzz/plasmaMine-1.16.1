package laz.plasmine.recipes.sediementextractor;

import laz.plasmine.recipes.AbstractPlasmineRecipe;
import laz.plasmine.registry.init.PMRecipesSerializer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

public class SedimentExtractorRecipe extends AbstractPlasmineRecipe {

    private final ItemStack itemIn1;
    private final ItemStack itemIn2;
    private final ItemStack itemOut;

    public SedimentExtractorRecipe(ResourceLocation id, ItemStack itemIn1, ItemStack itemIn2, ItemStack itemOut, int temp, int time) {
        super(PMRecipesSerializer.SEDIMENT_EXTRACTOR_TYPE, id, temp, time);
        this.itemIn1 = itemIn1;
        this.itemIn2 = itemIn2;
        this.itemOut = itemOut;
    }
    
    public ItemStack getItemIn1() {
    	return itemIn1;
    }
    
    public ItemStack getItemIn2() {
    	return itemIn2;
    }

    public ItemStack getItemOut() {
        return itemOut;
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
        return PMRecipesSerializer.SEDIMENT_EXTRACTOR_SERIALIZER.get();
    }

}
