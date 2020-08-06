package laz.plasmine.content.heat;

import laz.plasmine.content.base.heat.BlockHeatMachineBase;
import net.minecraft.block.BlockState;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.IBlockReader;

public class BasicHeatMachine extends BlockHeatMachineBase{

	public BasicHeatMachine() {
		super();
	}
	
	@Override
	public TileEntity createTileEntity(BlockState state, IBlockReader world) {
		return new TileBasicHeatMachine();
	}
	
}
