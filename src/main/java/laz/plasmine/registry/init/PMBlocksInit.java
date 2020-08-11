package laz.plasmine.registry.init;
import static laz.plasmine.registry.PMRegistry.addBlockClass;

import laz.plasmine.content.block.RapeSeedCrop;
import laz.plasmine.registry.BlockRegistryObjectGroup;
import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;

public class PMBlocksInit {

	public static BlockRegistryObjectGroup<Block, BlockItem, ?> RAPESEED_CROP;

	
	public static void init() {
		RAPESEED_CROP = addBlockClass("rapeseed_crop", RapeSeedCrop::new);
	}
}
