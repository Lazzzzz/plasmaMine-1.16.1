	package laz.plasmine.content.tiles.convertor;

import java.util.List;

import laz.plasmine.base.convertor.BlockConvertorBase;
import net.minecraft.block.BlockState;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.world.IBlockReader;

public class BlockSolarConvertor extends BlockConvertorBase {

	public BlockSolarConvertor() {
		super(1, 0.5f, 50);
	}
	
	@Override
	public TileEntity createTileEntity(BlockState state, IBlockReader world) {
		return new TileSolarConvertor(this.rate, this.efficiency, this.temp);
	}
	
	@Override
	public void addInformation(ItemStack stack, IBlockReader worldIn, List<ITextComponent> tooltip,
			ITooltipFlag flagIn) {
		super.addInformation(stack, worldIn, tooltip, flagIn);
		tooltip.add(new StringTextComponent("Produce heat from the sun"));
		tooltip.add(new StringTextComponent("Low tier heat producer"));
		tooltip.add(new StringTextComponent("Useless on processing machine"));
	}

}
