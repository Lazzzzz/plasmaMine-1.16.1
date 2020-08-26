	package laz.plasmine.content.tiles.convertor;

import laz.plasmine.base.convertor.BlockConvertorBase;
import net.minecraft.block.BlockState;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.IBlockReader;

public class BlockSuperAdvancedConvertor extends BlockConvertorBase {

	public BlockSuperAdvancedConvertor() {
		super(15, 0.75f, 350);
	}
	
	@Override
	public TileEntity createTileEntity(BlockState state, IBlockReader world) {
		return new TileSuperAdvancedConvertor(this.rate, this.efficiency, this.temp);
	}
}
