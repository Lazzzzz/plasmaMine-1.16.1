package laz.plasmine.base.generator;

import java.util.List;
import java.util.Random;

import laz.plasmine.api.information.GeneratorInformationBase;
import laz.plasmine.base.BlockRotationBase;
import laz.plasmine.util.interfaces.ICanWrench;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.inventory.InventoryHelper;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.item.ItemStack;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.state.BooleanProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraftforge.common.ToolType;

public class BlockGeneratorBase extends BlockRotationBase implements ICanWrench {

	public static final BooleanProperty WORKING = BooleanProperty.create("working");

	public int maxCapacity;
	public int rate;
	public int production;

	public BlockGeneratorBase(int maxCapacity, int rate, int production) {
		super(Block.Properties.create(Material.ROCK).harvestTool(ToolType.PICKAXE).hardnessAndResistance(3, 15)
				.sound(SoundType.METAL).harvestLevel(0));
		this.setDefaultState(
				this.stateContainer.getBaseState().with(FACING, Direction.NORTH).with(WORKING, Boolean.valueOf(false)));
		this.maxCapacity = maxCapacity;
		this.rate = rate;
		this.production = production;
	}

	protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder) {
		builder.add(FACING, WORKING);
	}

	@Override
	public BlockState getStateForPlacement(BlockItemUseContext context) {
		return this.getDefaultState().with(FACING, context.getPlacementHorizontalFacing().getOpposite());
	}

	@Override
	public boolean hasTileEntity(BlockState state) {
		return true;
	}

	@Override
	public void animateTick(BlockState stateIn, World worldIn, BlockPos pos, Random rand) {
		if (stateIn.get(WORKING))
			worldIn.addParticle(ParticleTypes.CRIT, pos.getX() + rand.nextFloat(), pos.getY() + rand.nextFloat(),
					pos.getZ() + rand.nextFloat(), 0, 0, 0);
	}

	@Override
	public void addInformation(ItemStack stack, IBlockReader worldIn, List<ITextComponent> tooltip,
			ITooltipFlag flagIn) {
		GeneratorInformationBase.info(maxCapacity, rate, production, tooltip);
	}

	@Override
	public void onReplaced(BlockState state, World worldIn, BlockPos pos, BlockState newState, boolean isMoving) {
		if (state.getBlock() != newState.getBlock()) {
			TileEntity te = worldIn.getTileEntity(pos);
			if (te instanceof TileGeneratorBase) {
				InventoryHelper.dropInventoryItems(worldIn, pos, ((TileGeneratorBase) te));
			}
		}
		super.onReplaced(state, worldIn, pos, newState, isMoving);
		
	}

}
