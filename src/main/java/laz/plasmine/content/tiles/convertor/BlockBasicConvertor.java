	package laz.plasmine.content.tiles.convertor;

import laz.plasmine.base.convertor.BlockConvertorBase;
import net.minecraft.block.BlockState;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.IBlockReader;

public class BlockBasicConvertor extends BlockConvertorBase {
	public BlockBasicConvertor() {
		super(2, 0.3f, 150);
	}
	
	@Override
	public TileEntity createTileEntity(BlockState state, IBlockReader world) {
		return new TileBasicConvertor(this.rate, this.efficiency, this.temp);
	}

}
