package laz.plasmine.content.machine;

import laz.plasmine.content.base.generator.GeneratorBlockBase;
import laz.plasmine.content.base.machine.PlasmaMachineBlockBase;
import net.minecraft.block.BlockState;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.IBlockReader;

public class BasicMachine extends PlasmaMachineBlockBase{

	public BasicMachine() {
	}
	
	@Override
	public TileEntity createTileEntity(BlockState state, IBlockReader world) {
		return new TileBasicMachine();
	}
	
}
