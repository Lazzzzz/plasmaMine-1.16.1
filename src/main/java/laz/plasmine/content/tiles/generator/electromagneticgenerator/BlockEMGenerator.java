package laz.plasmine.content.tiles.generator.electromagneticgenerator;

import java.util.List;

import laz.plasmine.base.generator.BlockGeneratorBase;
import laz.plasmine.registry.init.PMItemsInit;
import laz.plasmine.util.DirectionUtils;
import laz.plasmine.util.interfaces.IMaster;
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
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraftforge.fml.network.NetworkHooks;

public class BlockEMGenerator extends BlockGeneratorBase {

	public BlockEMGenerator() {
		super(7500, 100, 25);
	}

	@Override
	public TileEntity createTileEntity(BlockState state, IBlockReader world) {
		return new TileEMGenerator(this.maxCapacity, this.rate, this.production);
	}

	@Override
	public ActionResultType onBlockActivated(BlockState state, World worldIn, BlockPos pos, PlayerEntity player,
			Hand handIn, BlockRayTraceResult hit) {
		if (!worldIn.isRemote && player.getHeldItemMainhand().getItem() != PMItemsInit.WRENCH.get()) {
			TileEntity te = worldIn.getTileEntity(pos);
			if (te instanceof TileEMGenerator) {
				NetworkHooks.openGui((ServerPlayerEntity) player, (TileEMGenerator) te, pos);
				return ActionResultType.SUCCESS;
			}
		}
		return ActionResultType.FAIL;

	}

	@Override
	public void addInformation(ItemStack stack, IBlockReader worldIn, List<ITextComponent> tooltip,
			ITooltipFlag flagIn) {
		super.addInformation(stack, worldIn, tooltip, flagIn);
		tooltip.add(new StringTextComponent("Produce plasma with rapeseed fruits"));
	}

	@Override
	public void onWrenchAction(PlayerEntity player, World world, BlockPos pos, BlockState state, Direction dir) {
		super.onWrenchAction(player, world, pos, state, dir);
	}

	@Override
	public void onReplaced(BlockState state, World worldIn, BlockPos pos, BlockState newState, boolean isMoving) {
		if (!worldIn.isRemote) {
			if (state.getBlock() != newState.getBlock()) {
				IMaster tile = (IMaster) worldIn.getTileEntity(pos);
				Direction dir = state.get(FACING).getOpposite();
				BlockPos p = DirectionUtils.getPosDirection(pos, dir, 2).down();
				tile.sendStructureUnBind(p, dir);
			}
		}

		super.onReplaced(state, worldIn, pos, newState, isMoving);
	}

}
