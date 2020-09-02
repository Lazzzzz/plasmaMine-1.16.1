package laz.plasmine.recipes;

import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraft.item.crafting.IRecipeType;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

public class AbstralGeneratorRecipe implements IRecipe<IInventory> {
    
	protected final IRecipeType<?> type;
    protected final ResourceLocation id;
	protected ItemStack fuel;
    protected final int time;
    
    public AbstralGeneratorRecipe(IRecipeType<?> type, ResourceLocation id, ItemStack fuel, int time) {
        this.type = type;
        this.id = id;
    	this.fuel = fuel;
		this.time = time;
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

	@Override
	public boolean matches(IInventory inv, World worldIn) {
		return false;
	}

	@Override
	public ItemStack getCraftingResult(IInventory inv) {
		return ItemStack.EMPTY;
	}

	@Override
	public boolean canFit(int width, int height) {
		return false;
	}

	@Override
	public ItemStack getRecipeOutput() {
		return ItemStack.EMPTY;
	}

	@Override
	public IRecipeSerializer<?> getSerializer() {
		return null;
	}

	public ItemStack getfuel() {
		return fuel.copy();
	}

}
