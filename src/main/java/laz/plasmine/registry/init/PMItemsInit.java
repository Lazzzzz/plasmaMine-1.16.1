package laz.plasmine.registry.init;

import static laz.plasmine.registry.PMRegistry.addItemClass;

import laz.plasmine.content.items.WrenchItem;
import net.minecraft.item.Item;
import net.minecraftforge.fml.RegistryObject;

public class PMItemsInit {

	public static RegistryObject<Item> WRENCH;
	
	public static void init() {
		WRENCH = addItemClass("wrench", WrenchItem::new);	
		
	}
	
}
