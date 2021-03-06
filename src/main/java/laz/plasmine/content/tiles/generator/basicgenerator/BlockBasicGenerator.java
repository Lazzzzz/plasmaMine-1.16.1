package laz.plasmine.content.tiles.generator.basicgenerator;

import java.util.List;

import laz.plasmine.base.generator.BlockGeneratorBase;
import laz.plasmine.registry.init.PMItemsInit;
import net.minecraft.block.BlockState;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraftforge.fml.network.NetworkHooks;

public class BlockBasicGenerator extends BlockGeneratorBase {
	
	public BlockBasicGenerator() {
		super(2000, 20, 3);
	}

	@Override
	public TileEntity createTileEntity(BlockState state, IBlockReader world) {
		return new TileBasicGenerator(this.maxCapacity, this.rate, this.production);
	}

	@Override
	public ActionResultType onBlockActivated(BlockState state, World worldIn, BlockPos pos, PlayerEntity player,
			Hand handIn, BlockRayTraceResult hit) {
		if (!worldIn.isRemote && player.getHeldItemMainhand().getItem() != PMItemsInit.WRENCH.get()) {
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
		tooltip.add(new StringTextComponent("Produce plasma with rapeseed fruits"));
	}


}
