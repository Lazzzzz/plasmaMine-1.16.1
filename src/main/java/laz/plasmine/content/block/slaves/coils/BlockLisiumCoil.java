package laz.plasmine.content.block.slaves.coils;

import java.util.List;

import laz.plasmine.util.interfaces.IMaster;
import laz.plasmine.util.interfaces.ISlave;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;

public class BlockLisiumCoil extends BlockCoilBase {

	public BlockLisiumCoil() {
		super(Properties.from(Blocks.GOLD_BLOCK), 100000, 1000);

	}

	@Override
	public TileEntity createTileEntity(BlockState state, IBlockReader world) {
		return new TileLisiumCoil();
	}
	
	@Override
	public void addInformation(ItemStack stack, IBlockReader worldIn, List<ITextComponent> tooltip,
			ITooltipFlag flagIn) {
		tooltip.add(new StringTextComponent("Can provide 100 000PU storage and an output of 1000PU per tick"));
	}
}
