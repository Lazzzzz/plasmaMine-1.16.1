package laz.plasmine.content.tiles.heat.sedimentextractor;

import laz.plasmine.base.heat.TileHeatMachineBase;
import laz.plasmine.recipes.sediementextractor.SedimentExtractorRecipe;
import laz.plasmine.registry.init.PMTilesInit;
import laz.plasmine.util.DirectionUtils;
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

public class TileSedimentExtractor extends TileHeatMachineBase implements ISidedInventory {

	private int maxTime = 20 * 20;
	private double timer = 0;
	private ItemStack result = ItemStack.EMPTY;

	public TileSedimentExtractor(int maxCelcius, float thermo) {
		super(PMTilesInit.SEDIMENT_EXTRACTOR.getTileEntityType(), maxCelcius, thermo, 3);
	}

	@Override
	public Container createMenu(int id, PlayerInventory playerInv, PlayerEntity player) {
		return new ContainerSedimentExtractor(id, playerInv, this);
	}

	@Override
	public ITextComponent getDisplayName() {
		return new StringTextComponent("sediment extractor");
	}

	@Override
	public CompoundNBT write(CompoundNBT compound) {
		compound.put("result", result.serializeNBT());
		compound.putDouble("recipetimer", timer);
		return super.write(compound);
	}

	@Override
	public void func_230337_a_(BlockState p_230337_1_, CompoundNBT compound) {
		result.deserializeNBT(compound.getCompound("result"));
		timer = compound.getDouble("recipetimer");
		super.func_230337_a_(p_230337_1_, compound);
	}

	@Override
	public void onWorking() {
		setWorkingState(world, pos, world.getBlockState(pos), true);
		if (result == ItemStack.EMPTY)
			world.getRecipeManager().getRecipes().stream().filter(recipe -> recipe instanceof SedimentExtractorRecipe)
					.forEach(e -> start((SedimentExtractorRecipe) e));
		else {
			timer += speedFactor();
			if (timer >= maxTime) {
				ItemStack stack = getStackInSlot(2);
				if (stack.isEmpty()) {
					setInventorySlotContents(2, result);
				} else if (stack.getCount() == stack.getMaxStackSize())
					world.addEntity(new ItemEntity(world, pos.getX(), pos.getY(), pos.getZ(), result));
				else
					stack.grow(1);
				reset();
			}
		}
	}

	private void start(SedimentExtractorRecipe recipe) {
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

	public void init(SedimentExtractorRecipe recipe, ItemStack in1, ItemStack in2) {
		in1.shrink(1);
		in2.shrink(1);
		result = recipe.getItemOut();
		timer = 0;
	}

	@Override
	public float consumeHeat() {
		return heatHelper.getThermoConductivity() / 4;
	}

	private void reset() {
		result = ItemStack.EMPTY;
		timer = 0;
	}

	@Override
	public int[] getSlotsForFace(Direction side) {
		return new int[] { 0, 1, 2 };
	}

	@Override
	public boolean canInsertItem(int index, ItemStack itemStackIn, Direction direction) {
		if (index == 0 && direction == Direction.UP)
			return true;
		else if (index == 1 && DirectionUtils.isSide(direction))
			return true;
		return false;
	}

	@Override
	public boolean canExtractItem(int index, ItemStack stack, Direction direction) {
		if (index == 2)
			return true;
		return false;
	}
}
