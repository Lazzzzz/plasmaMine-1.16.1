package laz.plasmine.base.multiblock;

import java.util.List;

import laz.plasmine.util.interfaces.IMaster;
import laz.plasmine.util.interfaces.ISlave;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;

public class BlockCoilBase extends Block {

	public int maxstorage = 0;
	public int output = 0;

	public BlockCoilBase(Properties properties, int maxStorage, int output) {
		super(properties);
		this.maxstorage = maxStorage;
		this.output = output;
	}

	@Override
	public boolean hasTileEntity(BlockState state) {
		return true;
	}

	@Override
	public void onReplaced(BlockState state, World worldIn, BlockPos pos, BlockState newState, boolean isMoving) {
		if (!worldIn.isRemote) {
			ISlave tile = (ISlave) worldIn.getTileEntity(pos);
			if (tile.isBind()) {
				IMaster master = (IMaster) worldIn.getTileEntity(tile.getBlockPosMaster());
				tile.sendMasterDestroy(pos, master);
			}
		}
		super.onReplaced(state, worldIn, pos, newState, isMoving);
	}

	@Override
	public void addInformation(ItemStack stack, IBlockReader worldIn, List<ITextComponent> tooltip,
			ITooltipFlag flagIn) {
		tooltip.add(new StringTextComponent("Can provide " + maxstorage + "PU storage and"));
		tooltip.add(new StringTextComponent("an output of " + output + " per tick"));
	}

}
