package laz.plasmine.registry.init;
import static laz.plasmine.registry.PMRegistry.addBlockClass;

import laz.plasmine.content.block.ConveyorBelt;
import laz.plasmine.content.block.ConveyorBeltUp;
import laz.plasmine.content.block.RapeSeedCrop;
import laz.plasmine.registry.BlockRegistryObjectGroup;
import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;

public class PMBlocksInit {

	public static BlockRegistryObjectGroup<Block, BlockItem, ?> RAPESEED_CROP;
	public static BlockRegistryObjectGroup<Block, BlockItem, ?> CONVEYOR;
	public static BlockRegistryObjectGroup<Block, BlockItem, ?> CONVEYOR_UP;
	
	public static void init() {
		RAPESEED_CROP = addBlockClass("rapeseed_crop", RapeSeedCrop::new);
		CONVEYOR = addBlockClass("conveyor", ConveyorBelt::new);
		CONVEYOR_UP = addBlockClass("conveyor_up", ConveyorBeltUp::new);
	}
}
