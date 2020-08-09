package laz.plasmine.registry.init;

import static laz.plasmine.registry.PMRegistry.addItemClass;
import static laz.plasmine.registry.PMRegistry.addSimpleItem;

import laz.plasmine.api.MaterialGroup;
import laz.plasmine.content.items.WrenchItem;
import net.minecraft.item.Item;
import net.minecraftforge.fml.RegistryObject;

public class PMItemsInit {

	public static RegistryObject<Item> WRENCH;
	public static RegistryObject<Item> GOLD_WIRE;
	public static RegistryObject<Item> MAGNETIC_WIRE;
	public static RegistryObject<Item> RAPESEED_FRUIT;
	public static RegistryObject<Item> RAPESEED_SEED;
	
	public static MaterialGroup LISIUM;
	public static MaterialGroup KETIUM;
	public static MaterialGroup ROSIUM;

	
	public static void init() {
		WRENCH = addItemClass("wrench", WrenchItem::new);	
		GOLD_WIRE = addSimpleItem("gold_wire", 64);
		MAGNETIC_WIRE = addSimpleItem("magnetic_wire", 64);
		RAPESEED_FRUIT = addSimpleItem("rapeseed_fruit", 64);
		RAPESEED_SEED = addSimpleItem("rapeseed_seed", 64);
		
		LISIUM = initMaterial("lisium");
		KETIUM = initMaterial("ketium");
		ROSIUM = initMaterial("rosium");
		
	}

	public static MaterialGroup initMaterial(String name) {
		RegistryObject<Item> sediment = addSimpleItem(name + "_sediment", 64);
		RegistryObject<Item> chunk = addSimpleItem(name + "_chunk", 64);
		RegistryObject<Item> ingot = addSimpleItem(name + "_ingot", 64);
	
		return new MaterialGroup(sediment, chunk, ingot);
	}
	
}
