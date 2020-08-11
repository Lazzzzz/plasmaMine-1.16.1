package laz.plasmine.content.tiles.heat.sedimentcrystalizer;

import laz.plasmine.api.base.heat.BlockHeatMachineBase;
import laz.plasmine.registry.init.PMItemsInit;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraftforge.fml.network.NetworkHooks;

public class BlockSedimentCrystalizer extends BlockHeatMachineBase {

	public BlockSedimentCrystalizer() {
		super(700, 0.2f);
	}

	@Override
	public TileEntity createTileEntity(BlockState state, IBlockReader world) {
		return new TileSedimentCrystalizer(maxCelcius, thermo);
	}
	
	@Override
	public ActionResultType onBlockActivated(BlockState state, World worldIn, BlockPos pos, PlayerEntity player,
			Hand handIn, BlockRayTraceResult hit) {
		if (!worldIn.isRemote && player.getHeldItemMainhand().getItem() != PMItemsInit.WRENCH.get()) {
			TileEntity te = worldIn.getTileEntity(pos);
			if (te instanceof TileSedimentCrystalizer) {
				NetworkHooks.openGui((ServerPlayerEntity) player, (TileSedimentCrystalizer) te, pos);
				return ActionResultType.SUCCESS;
			}
		}
		return ActionResultType.FAIL;

	}
	
}
