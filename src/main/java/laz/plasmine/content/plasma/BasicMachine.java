package laz.plasmine.content.plasma;

import laz.plasmine.content.base.plasma.BlockPlasmaMachineBase;
import net.minecraft.block.BlockState;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.IBlockReader;

public class BasicMachine extends BlockPlasmaMachineBase{

	public BasicMachine() {
	}
	
	@Override
	public TileEntity createTileEntity(BlockState state, IBlockReader world) {
		return new TileBasicMachine();
	}
	
}
