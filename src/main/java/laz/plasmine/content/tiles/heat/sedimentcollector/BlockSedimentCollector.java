package laz.plasmine.content.tiles.heat.sedimentcollector;

import java.util.Random;

import laz.plasmine.api.base.heat.BlockHeatMachineBase;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.particles.RedstoneParticleData;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraftforge.fml.network.NetworkHooks;

public class BlockSedimentCollector extends BlockHeatMachineBase {

	public BlockSedimentCollector() {
		super(500, 0.4f);
	}

	@Override
	public TileEntity createTileEntity(BlockState state, IBlockReader world) {
		return new TileSedimentCollector(maxCelcius, thermo);
	}

	@Override
	public ActionResultType onBlockActivated(BlockState state, World worldIn, BlockPos pos, PlayerEntity player,
			Hand handIn, BlockRayTraceResult hit) {
		if (!worldIn.isRemote) {
			TileEntity te = worldIn.getTileEntity(pos);
			if (te instanceof TileSedimentCollector) {
				NetworkHooks.openGui((ServerPlayerEntity) player, (TileSedimentCollector) te, pos);
				return ActionResultType.SUCCESS;
			}
		}
		return ActionResultType.FAIL;

	}

	@Override
	public void animateTick(BlockState stateIn, World worldIn, BlockPos pos, Random rand) {
		if (stateIn.get(BlockHeatMachineBase.WORKING)) {
			int y = pos.getY();
			for (int i = -1; i < 2; i++) {
				for (int j = -1; j < 2; j++) {
					int x = pos.getX() + i;
					int z = pos.getZ() + j;
					worldIn.addParticle(new RedstoneParticleData(0f, 0f, 0f, 1f), x + rand.nextFloat(), y + 0.01f,
							z + rand.nextFloat(), 0, 0, 0);
				}
			}
		}
		super.animateTick(stateIn, worldIn, pos, rand);
	}
}
