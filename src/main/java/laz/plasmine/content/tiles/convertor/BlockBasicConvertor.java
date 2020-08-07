	package laz.plasmine.content.tiles.convertor;

import laz.plasmine.content.base.convertor.BlockConvertorBase;
import net.minecraft.block.BlockState;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.IBlockReader;

public class BlockBasicConvertor extends BlockConvertorBase {

	public BlockBasicConvertor() {
		super();
	}

	
	@Override
	public TileEntity createTileEntity(BlockState state, IBlockReader world) {
		return new TileBasicConvertor();
	}
	

}
