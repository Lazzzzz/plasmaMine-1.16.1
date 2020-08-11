package laz.plasmine.content.tiles.heat.sedimentextractor;

import laz.plasmine.api.base.heat.TileHeatMachineBase;
import laz.plasmine.recipes.sediementextractor.SedimentExtractorRecipe;
import laz.plasmine.registry.init.PMTilesInit;
import laz.plasmine.util.direction.DirectionUtils;
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
	private int timer = 0;
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
	public void tick() {
		if (!world.isRemote) updatePoweredState(!getStackInSlot(0).isEmpty() && !getStackInSlot(1).isEmpty());
		super.tick();
	}
	
	@Override
	public CompoundNBT write(CompoundNBT compound) {
		compound.put("result", result.serializeNBT());
		compound.putInt("recipetimer", timer);
		return super.write(compound);
	}

	@Override
	public void func_230337_a_(BlockState p_230337_1_, CompoundNBT compound) {
		result.deserializeNBT(compound.getCompound("result"));
		timer = compound.getInt("recipetimer");
		super.func_230337_a_(p_230337_1_, compound);
	}

	@Override
	public void onWorking() {
		if (result == ItemStack.EMPTY)
			world.getRecipeManager().getRecipes().stream().filter(recipe -> recipe instanceof SedimentExtractorRecipe)
					.forEach(e -> start((SedimentExtractorRecipe) e));
		else {
			timer++;
			if (timer >= maxTime) {
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

	private void start(SedimentExtractorRecipe recipe) {
		ItemStack in1 = content.get(0);
		ItemStack in2 = content.get(1);
		ItemStack out = getStackInSlot(2);
		if (in1.getItem() == recipe.getItemIn1().getItem() && in2.getItem() == recipe.getItemIn2().getItem()
				&& recipe.getTemp() < heatHelper.getCelcius()) {
			if (out == ItemStack.EMPTY || out.getCount() < out.getMaxStackSize()) {
				in1.shrink(1);
				in2.shrink(1);
				result = recipe.getItemOut();
				timer = 0;
			}
		}
	}

	@Override
	public float consumeHeat() {
		return heatHelper.getThermoConductivity() / 8;
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
		if (index == 0 && direction == Direction.UP) return true;
		else if (index == 1 && DirectionUtils.isSide(direction)) return true; 
		return false;
	}

	@Override
	public boolean canExtractItem(int index, ItemStack stack, Direction direction) {
		if (index == 2)
			return true;
		return false;
	}
}
