package laz.plasmine.api.base.convertor;

import java.util.List;
import java.util.Random;

import laz.plasmine.api.information.ConvertorInformationBase;
import laz.plasmine.util.interfaces.ICanWrench;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.HorizontalBlock;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.item.ItemStack;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.state.BooleanProperty;
import net.minecraft.state.DirectionProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraftforge.common.ToolType;

public class BlockConvertorBase extends Block implements ICanWrench {

	public static final DirectionProperty FACING = HorizontalBlock.HORIZONTAL_FACING;
	public static final BooleanProperty WORKING = BooleanProperty.create("working");

	public float efficiency;
	public int rate;
	public int tier;

	public BlockConvertorBase(int rate, float efficiency, int tier) {
		super(Block.Properties.create(Material.ROCK).harvestTool(ToolType.PICKAXE).hardnessAndResistance(3, 15)
				.sound(SoundType.METAL).harvestLevel(0));
		this.setDefaultState(
				this.stateContainer.getBaseState().with(FACING, Direction.NORTH).with(WORKING, Boolean.valueOf(false)));
		this.efficiency = efficiency;
		this.rate = rate;
		this.tier = tier;
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
	public void onWrenchAction(PlayerEntity player, World world, BlockPos pos, BlockState state, Direction dir) {
		ICanWrench.super.onWrenchAction(player, world, pos, state, dir);
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
		ConvertorInformationBase.info(efficiency, rate, tier, tooltip);
	}

}
