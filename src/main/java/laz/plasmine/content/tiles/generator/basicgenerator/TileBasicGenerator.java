package laz.plasmine.content.tiles.generator.basicgenerator;

import laz.plasmine.base.generator.TileGeneratorBase;
import laz.plasmine.recipes.basicgenerator.BasicGeneratorRecipe;
import laz.plasmine.recipes.emgenerator.EmRecipe;
import laz.plasmine.registry.init.PMItemsInit;
import laz.plasmine.registry.init.PMTilesInit;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.Direction;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;

public class TileBasicGenerator extends TileGeneratorBase {

	private int maxCooking = 0;
	private int cooking = 0;

	public TileBasicGenerator(int maxCapacity, int rate, int production) {
		super(PMTilesInit.BASIC_GENERATOR.getTileEntityType(), maxCapacity, rate, production, 1);
	}

	@Override
	public void tick() {
		super.tick();
		if (!world.isRemote) {
			if (cooking > 0)
				cooking--;
		}
	}

	@Override
	public boolean getConnectionFace(Direction face) {
		return face.getOpposite() == world.getBlockState(pos).get(BlockBasicGenerator.FACING);
	}

	@Override
	public int produceEnergy() {
		if (plasmaHelper.getCapacity() < plasmaHelper.getMaxCapacity()) {
			if (cooking > 0)
				return generation;
			else {
				maxCooking = 0;
				if (!getStackInSlot(0).isEmpty())
					world.getRecipeManager().getRecipes().stream().filter(recipe -> recipe instanceof BasicGeneratorRecipe)
							.forEach(e -> start((BasicGeneratorRecipe) e));
				if (maxCooking != 0) return generation;
			}
		}
		return 0;

	}

	private void start(BasicGeneratorRecipe recipe) {
		if (getStackInSlot(0).getItem() == recipe.getfuel().getItem()) {
			getStackInSlot(0).shrink(1);
			maxCooking = recipe.getCookTime();
			cooking = maxCooking;
		}
	}
	@Override
	public CompoundNBT write(CompoundNBT compound) {
		compound.putInt("cooking", cooking);
		return super.write(compound);
	}

	@Override
	public void read(BlockState p_230337_1_, CompoundNBT p_230337_2_) {
		cooking = p_230337_2_.getInt("cooking");
		super.read(p_230337_1_, p_230337_2_);
	}

	@Override
	public Container createMenu(int id, PlayerInventory playerInv, PlayerEntity player) {
		return new ContainerBasicGenerator(id, playerInv, this);
	}

	@Override
	public ITextComponent getDisplayName() {
		return new StringTextComponent("basic generator");
	}

}
