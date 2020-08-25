	package laz.plasmine.content.tiles.convertor;

import laz.plasmine.base.convertor.BlockConvertorBase;
import net.minecraft.block.BlockState;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.world.IBlockReader;

public class BlockAdvancedConvertor extends BlockConvertorBase {	
	public BlockAdvancedConvertor() {
		super(5, 0.5f, 250);
	}
	
	@Override
	public TileEntity createTileEntity(BlockState state, IBlockReader world) {
		return new TileAdvancedConvertor(this.rate, this.efficiency, this.temp);
	}
}
