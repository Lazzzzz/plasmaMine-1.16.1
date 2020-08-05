package laz.plasmine.content.base;

import laz.plasmine.registry.init.PMItemsInit;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraftforge.common.ToolType;

public class PlasmaMachineBlockBase extends Block {

	private final TileEntityType<?> tileEntityTypeIn;
	private final int maxCapacity;
	private final int rate;
	private final float efficiency;
	private final int time;
	private final boolean transfer;

	public PlasmaMachineBlockBase(TileEntityType<?> tileEntityTypeIn, int maxCapacity, int rate, int efficiency,
			int time, boolean transfer) {
		super(Block.Properties.create(Material.ROCK).harvestTool(ToolType.PICKAXE).hardnessAndResistance(3, 15)
				.sound(SoundType.METAL).harvestLevel(0));

		this.maxCapacity = maxCapacity;
		this.rate = rate;
		this.efficiency = efficiency;
		this.time = time;
		this.transfer = transfer;
		this.tileEntityTypeIn = tileEntityTypeIn;
	}

	@Override
	public boolean hasTileEntity(BlockState state) {
		return true;
	}

	@Override
	public TileEntity createTileEntity(BlockState state, IBlockReader world) {
		return new TilePlasmaMachineBase(tileEntityTypeIn, maxCapacity, rate, efficiency, time, transfer);
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
