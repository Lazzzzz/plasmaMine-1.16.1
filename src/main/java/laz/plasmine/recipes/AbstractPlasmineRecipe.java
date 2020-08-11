package laz.plasmine.recipes;

import net.minecraft.inventory.IInventory;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.item.crafting.IRecipeType;
import net.minecraft.util.ResourceLocation;

public abstract class AbstractPlasmineRecipe implements IRecipe<IInventory> {

    protected final IRecipeType<?> type;
    protected final ResourceLocation id;
    protected final int temp;
    protected final int time;


    public AbstractPlasmineRecipe(IRecipeType<?> type, ResourceLocation id, int temp, int time) {
        this.type = type;
        this.id = id;
        this.time = time;
        this.temp = temp;
    }

    public int getTemp() {
        return temp;
    }

    @Override
    public ResourceLocation getId() {
        return id;
    }

    @Override
    public IRecipeType<?> getType() {
        return type;
    }

    public int getCookTime() {
        return time;
    }
}
