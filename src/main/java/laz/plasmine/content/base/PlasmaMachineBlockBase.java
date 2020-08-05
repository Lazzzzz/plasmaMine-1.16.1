package laz.plasmine.content.base;

import laz.plasmine.registry.init.PMItemsInit;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.world.World;
import net.minecraftforge.common.ToolType;

public class PlasmaMachineBlockBase extends Block {


	public PlasmaMachineBlockBase() {
		super(Block.Properties.create(Material.ROCK).harvestTool(ToolType.PICKAXE).hardnessAndResistance(3, 15)
				.sound(SoundType.METAL).harvestLevel(0));
	}

	@Override
	public boolean hasTileEntity(BlockState state) {
		return true;
	}

	@Override
	public ActionResultType onBlockActivated(BlockState state, World worldIn, BlockPos pos, PlayerEntity player,
			Hand handIn, BlockRayTraceResult hit) {

		if (!worldIn.isRemote) {
			if (player.getHeldItemMainhand().getItem() == PMItemsInit.DEBUG.get()) {
				((TilePlasmaMachineBase) worldIn.getTileEntity(pos)).getHelper().displayInfo(player);
			}
		}
		return super.onBlockActivated(state, worldIn, pos, player, handIn, hit);
	}

}
