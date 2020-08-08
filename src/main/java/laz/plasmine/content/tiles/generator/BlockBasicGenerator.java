package laz.plasmine.content.tiles.generator;

import java.util.List;

import laz.plasmine.content.base.generator.BlockGeneratorBase;
import net.minecraft.block.BlockRenderType;
import net.minecraft.block.BlockState;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Direction;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraftforge.fml.network.NetworkHooks;

public class BlockBasicGenerator extends BlockGeneratorBase {
	
	VoxelShape SHAPE = VoxelShapes.or(VoxelShapes.create(0.0625f, 0, 0.0625f, 1f - 0.0625f, 0.25f, 1f - 0.0625f),
			VoxelShapes.create(0.0625f * 2, 0.25f, 0.0625f * 2, 1 - 0.0625f * 2, 1 - 0.0625f, 1 - 0.0625f * 2));

	public BlockBasicGenerator() {
		super(2000, 20, 1);
	}

	@Override
	public TileEntity createTileEntity(BlockState state, IBlockReader world) {
		return new TileBasicGenerator(2000, 20, 1);
	}

	@Override
	public ActionResultType onBlockActivated(BlockState state, World worldIn, BlockPos pos, PlayerEntity player,
			Hand handIn, BlockRayTraceResult hit) {
		if (!worldIn.isRemote) {
			TileEntity te = worldIn.getTileEntity(pos);
			if (te instanceof TileBasicGenerator) {
				NetworkHooks.openGui((ServerPlayerEntity) player, (TileBasicGenerator) te, pos);
				return ActionResultType.SUCCESS;
			}
		}
		return ActionResultType.FAIL;

	}
	
	@Override
	public void addInformation(ItemStack stack, IBlockReader worldIn, List<ITextComponent> tooltip,
			ITooltipFlag flagIn) {
		super.addInformation(stack, worldIn, tooltip, flagIn);
		tooltip.add(new StringTextComponent("Produce plasma when on top of lava and has iron nuggets"));
	}
	
	@Override
	public BlockRenderType getRenderType(BlockState state) {
		return BlockRenderType.INVISIBLE;
	}

	@Override
	public VoxelShape getRenderShape(BlockState state, IBlockReader worldIn, BlockPos pos) {
		return SHAPE;
	}

	@Override
	public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
		return SHAPE;
	}

	@Override
	public VoxelShape getCollisionShape(BlockState state, IBlockReader worldIn, BlockPos pos,
			ISelectionContext context) {
		return SHAPE;
	}

	@Override
	public VoxelShape getRaytraceShape(BlockState state, IBlockReader worldIn, BlockPos pos) {
		return SHAPE;
	}
	
	@Override
	public void onWrenchAction(PlayerEntity player, World world, BlockPos pos, BlockState state, Direction dir) {
		super.onWrenchAction(player, world, pos, state, dir);
	}

}
