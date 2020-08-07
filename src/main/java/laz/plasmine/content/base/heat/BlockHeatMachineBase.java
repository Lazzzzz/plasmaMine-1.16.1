package laz.plasmine.content.base.heat;

import laz.plasmine.util.ICanWrench;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraftforge.common.ToolType;

public class BlockHeatMachineBase extends Block implements ICanWrench {

	public BlockHeatMachineBase() {
		super(Block.Properties.create(Material.ROCK).harvestTool(ToolType.PICKAXE).hardnessAndResistance(3, 15)
				.sound(SoundType.METAL).harvestLevel(0));
	}

	@Override
	public boolean hasTileEntity(BlockState state) {
		return true;
	}
	
}
