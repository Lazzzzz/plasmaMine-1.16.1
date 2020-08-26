package laz.plasmine.content.tiles.heat.ionizer;

import laz.plasmine.base.heat.TileHeatMachineBase;
import laz.plasmine.recipes.ionizer.IonizerRecipe;
import laz.plasmine.registry.init.PMTilesInit;
import laz.plasmine.util.RecipiesUtils;
import net.minecraft.block.BlockState;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.Direction;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;

public class TileIonizer extends TileHeatMachineBase implements ISidedInventory {

	private ItemStack result = ItemStack.EMPTY;
	private double timer = 0;
	private int currentMaxTimer = 0;

	public TileIonizer(int maxCelcius, float thermo) {
		super(PMTilesInit.IONIZER.getTileEntityType(), maxCelcius, thermo, 3);
	}

	@Override
	public Container createMenu(int id, PlayerInventory playerInv, PlayerEntity player) {
		return new ContainerIonizer(id, playerInv, this);
	}

	@Override
	public ITextComponent getDisplayName() {
		return new StringTextComponent("Ionizer");
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
	public void onWorking() {
		setWorkingState(world, pos, world.getBlockState(pos), true);

		if (result == ItemStack.EMPTY)
			world.getRecipeManager().getRecipes().stream().filter(recipe -> recipe instanceof IonizerRecipe)
					.forEach(e -> start((IonizerRecipe) e));
		else {
			timer += speedFactor();
			heatHelper.removeHeat(consumeHeat());
			if (timer >= currentMaxTimer) {
				ItemStack stack = getStackInSlot(2);
				if (stack == ItemStack.EMPTY) {
					setInventorySlotContents(2, result);
				} else if (stack.getCount() == stack.getMaxStackSize())
					world.addEntity(new ItemEntity(world, pos.getX(), pos.getY(), pos.getZ(), result));
				else
					stack.grow(1);
				reset();
			}
		}
	}

	private void reset() {
		result = ItemStack.EMPTY;
		timer = 0;
		currentMaxTimer = 0;
	}

	private void start(IonizerRecipe recipe) {
		ItemStack in1 = content.get(0);
		ItemStack in2 = content.get(1);
		ItemStack out = getStackInSlot(2);
		if (RecipiesUtils.isSameTag(in1, recipe.getItemIn1()) && RecipiesUtils.isSameTag(in2, recipe.getItemIn2())
				&& recipe.getTemp() < heatHelper.getCelcius()) {
			if (out.isEmpty())
				init(recipe, in1, in2);
			else if (out.getCount() < out.getMaxStackSize() && out.getItem() == recipe.getItemOut().getItem())
				init(recipe, in1, in2);
		}
	}

	public void init(IonizerRecipe recipe, ItemStack in1, ItemStack in2) {
		in1.shrink(1);
		in2.shrink(1);
		result = recipe.getItemOut();
		timer = 0;
	}

	@Override
	public float consumeHeat() {
		return heatHelper.getThermoConductivity();
	}

	@Override
	public boolean canExtractItem(int arg0, ItemStack arg1, Direction arg2) {
		if (arg0 == 2)
			return true;
		return false;
	}

	@Override
	public boolean canInsertItem(int arg0, ItemStack arg1, Direction arg2) {
		if (arg0 == 0 || arg0 == 1)
			return true;
		return false;
	}

	@Override
	public int[] getSlotsForFace(Direction arg0) {
		return new int[] { 0, 1, 2 };
	}
}
