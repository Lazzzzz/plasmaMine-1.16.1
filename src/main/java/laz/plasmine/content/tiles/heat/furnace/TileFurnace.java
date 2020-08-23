package laz.plasmine.content.tiles.heat.furnace;

import laz.plasmine.base.heat.TileHeatMachineBase;
import laz.plasmine.registry.init.PMTilesInit;
import net.minecraft.block.BlockState;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.FurnaceRecipe;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.Direction;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;

public class TileFurnace extends TileHeatMachineBase implements ISidedInventory {

	private ItemStack result = ItemStack.EMPTY;
	private double timer = 0;
	private int currentMaxTimer = 0;

	public TileFurnace(int maxCelcius, float thermo) {
		super(PMTilesInit.FURNACE.getTileEntityType(), maxCelcius, thermo, 2);
	}

	@Override
	public Container createMenu(int id, PlayerInventory playerInv, PlayerEntity player) {
		return new ContainerFurnace(id, playerInv, this);
	}

	@Override
	public ITextComponent getDisplayName() {
		return new StringTextComponent("furnace");
	}
	
	@Override
	public CompoundNBT write(CompoundNBT compound) {
		compound.put("result", result.serializeNBT());
		compound.putDouble("recipetimer", timer);
		compound.putInt("recipemaxtimer", currentMaxTimer);
		return super.write(compound);
	}

	@Override
	public void func_230337_a_(BlockState p_230337_1_, CompoundNBT compound) {
		result.deserializeNBT(compound.getCompound("result"));
		timer = compound.getDouble("recipetimer");
		currentMaxTimer = compound.getInt("recipemaxtimer");
		super.func_230337_a_(p_230337_1_, compound);
	}

	@Override
	public float consumeHeat() {
		return heatHelper.getThermoConductivity() / 4;
	}

	@Override
	public void onWorking() {
		setWorkingState(world, pos, world.getBlockState(pos), true);
		
		if (result == ItemStack.EMPTY)
			world.getRecipeManager().getRecipes().stream().filter(recipe -> recipe instanceof FurnaceRecipe)
					.forEach(e -> start((FurnaceRecipe) e));
		else {
			timer += speedFactor();
			if (timer >= currentMaxTimer) {
				ItemStack stack = getStackInSlot(1);
				if (stack == ItemStack.EMPTY) {
					setInventorySlotContents(1, result);
				}
				else if (stack.getCount() == stack.getMaxStackSize()) world.addEntity(new ItemEntity(world, pos.getX(), pos.getY(), pos.getZ(), result));
				else stack.grow(1);
				
				reset();
			}
		}
	}

	private void start(FurnaceRecipe recipe) {
		ItemStack in = content.get(0);
		ItemStack out = getStackInSlot(1);
		if (recipe.matches(this, world)) {
			if (out == ItemStack.EMPTY || out.getCount() < out.getMaxStackSize() && heatHelper.getCelcius() > 90) {
				in.shrink(1);
				result = recipe.getRecipeOutput().copy();
				timer = 0;
				currentMaxTimer = 100;

			}
		}
	}

	private void reset() {
		result = ItemStack.EMPTY;
		timer = 0;
		currentMaxTimer = 0;
	}

	@Override
	public int[] getSlotsForFace(Direction side) {
		return new int[] { 0, 1 };
	}

	@Override
	public boolean canInsertItem(int index, ItemStack itemStackIn, Direction direction) {
		if (index == 0)
			return true;
		return false;
	}

	@Override
	public boolean canExtractItem(int index, ItemStack stack, Direction direction) {
		if (index == 1)
			return true;
		return false;
	}
}
