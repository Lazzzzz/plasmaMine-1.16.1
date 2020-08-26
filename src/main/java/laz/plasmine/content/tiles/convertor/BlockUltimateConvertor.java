	package laz.plasmine.content.tiles.convertor;

import laz.plasmine.base.convertor.BlockConvertorBase;
import net.minecraft.block.BlockState;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.IBlockReader;

public class BlockUltimateConvertor extends BlockConvertorBase {

	public BlockUltimateConvertor() {
		super(25, 0.95f, 750);
	}
	
	@Override
	public TileEntity createTileEntity(BlockState state, IBlockReader world) {
		return new TileUltimateConvertor(this.rate, this.efficiency, this.temp);
	}
}
