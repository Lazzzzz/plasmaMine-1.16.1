package laz.plasmine.content.tiles.heat.sedimentcrystalizer;

import laz.plasmine.api.base.heat.TileHeatMachineBase;
import laz.plasmine.recipes.sedimentcrystalizer.SedimentCrystalizerRecipe;
import laz.plasmine.registry.init.PMTilesInit;
import net.minecraft.block.BlockState;
import net.minecraft.block.RedstoneBlock;
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

public class TileSedimentCrystalizer extends TileHeatMachineBase implements ISidedInventory {

	private ItemStack result = ItemStack.EMPTY;
	private int timer = 0;
	private int currentMaxTimer = 0;

	public TileSedimentCrystalizer(int maxCelcius, float thermo) {
		super(PMTilesInit.SEDIMENT_CRYSTALIZER.getTileEntityType(), maxCelcius, thermo, 2);
	}

	@Override
	public Container createMenu(int id, PlayerInventory playerInv, PlayerEntity player) {
		return new ContainerSedimentCrystalizer(id, playerInv, this);
	}

	@Override
	public void tick() {
		if (!world.isRemote)
			updatePoweredState(!getStackInSlot(0).isEmpty());
		super.tick();
	}

	@Override
	public ITextComponent getDisplayName() {
		return new StringTextComponent("sediment crystalizer");
	}

	@Override
	public CompoundNBT write(CompoundNBT compound) {
		compound.put("result", result.serializeNBT());
		compound.putInt("recipetimer", timer);
		compound.putInt("recipemaxtimer", currentMaxTimer);
		return super.write(compound);
	}

	@Override
	public void func_230337_a_(BlockState p_230337_1_, CompoundNBT compound) {
		result.deserializeNBT(compound.getCompound("result"));
		timer = compound.getInt("recipetimer");
		currentMaxTimer = compound.getInt("recipemaxtimer");
		super.func_230337_a_(p_230337_1_, compound);
	}

	@Override
	public float consumeHeat() {
		return heatHelper.getThermoConductivity() / 4;
	}

	@Override
	public void onWorking() {
		if (result == ItemStack.EMPTY)
			world.getRecipeManager().getRecipes().stream().filter(recipe -> recipe instanceof SedimentCrystalizerRecipe)
					.forEach(e -> start((SedimentCrystalizerRecipe) e));
		else {
			timer++;
			if (timer >= currentMaxTimer) {
				ItemStack stack = getStackInSlot(1);
				if (stack == ItemStack.EMPTY) {
					System.out.println("result");
					setInventorySlotContents(1, result);
				}
				else if (stack.getCount() == stack.getMaxStackSize()) world.addEntity(new ItemEntity(world, pos.getX(), pos.getY(), pos.getZ(), result));
				else stack.grow(1);
				
				reset();
			}
		}
	}

	private void start(SedimentCrystalizerRecipe recipe) {
		ItemStack in = content.get(0);
		ItemStack out = getStackInSlot(1);
		if (in.getItem() == recipe.getItemIn().getItem() && recipe.getTemp() < heatHelper.getCelcius()) {
			if (out == ItemStack.EMPTY || out.getCount() < out.getMaxStackSize()) {
				in.shrink(1);
				result = recipe.getItemOut();
				timer = 0;
				currentMaxTimer = recipe.getCookTime();

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
