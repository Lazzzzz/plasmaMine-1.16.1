package laz.plasmine.content.convertor;

import laz.plasmine.content.base.convertor.BlockConvertorBase;
import laz.plasmine.content.base.heat.BlockHeatMachineBase;
import laz.plasmine.content.base.plasma.BlockPlasmaMachineBase;
import net.minecraft.block.BlockState;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.IBlockReader;

public class BasicConvertor extends BlockConvertorBase { 

	public BasicConvertor() {
		super();
	}

	@Override
	public TileEntity createTileEntity(BlockState state, IBlockReader world) {
		return new TileBasicConvertor();
	}
	
}
