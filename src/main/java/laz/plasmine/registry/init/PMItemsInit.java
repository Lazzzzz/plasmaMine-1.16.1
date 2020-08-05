package laz.plasmine.registry.init;

import static laz.plasmine.registry.PMRegistry.addItemClass;

import laz.plasmine.content.debug.DebugStick;
import net.minecraft.item.Item;
import net.minecraftforge.fml.RegistryObject;

public class PMItemsInit {

	public static RegistryObject<Item> DEBUG;
	
	public static void init() {
		DEBUG = addItemClass("debug", DebugStick::new);	
		
	}
	
}
