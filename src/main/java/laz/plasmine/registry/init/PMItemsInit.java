package laz.plasmine.registry.init;

import static laz.plasmine.registry.PMRegistry.addItemClass;
import static laz.plasmine.registry.PMRegistry.addSimpleItem;

import laz.plasmine.api.MaterialGroup;
import laz.plasmine.content.items.ItemWithInformation;
import net.minecraft.item.Item;
import net.minecraftforge.fml.RegistryObject;

public class PMItemsInit {

	public static RegistryObject<Item> WRENCH;
	public static RegistryObject<Item> GOLD_WIRE;
	public static RegistryObject<Item> MAGNETIC_WIRE;
	public static RegistryObject<Item> RAPESEED_FRUIT;

	public static MaterialGroup LISIUM;
	public static MaterialGroup KETIUM;
	public static MaterialGroup ROSIUM;

	public static void init() {
		WRENCH 	   = addItemClass("wrench", () -> new ItemWithInformation(1, "Can rotate and remove machine"));
		
		GOLD_WIRE = addSimpleItem("gold_wire", 64);
		MAGNETIC_WIRE = addSimpleItem("magnetic_wire", 64);
		RAPESEED_FRUIT = addSimpleItem("rapeseed_fruit", 64);

		LISIUM = initMaterial("lisium", 1);
		KETIUM = initMaterial("ketium", 2);
		ROSIUM = initMaterial("rosium", 3);

	}

	public static MaterialGroup initMaterial(String name, int tier) {
		String info = "\u00A7b - Tier :\u00A74 " + tier;

		RegistryObject<Item> sediment = addItemClass(name + "_sediment", () -> new ItemWithInformation(64, info));
		RegistryObject<Item> chunk = addItemClass(name + "_chunk", () -> new ItemWithInformation(64, info));
		RegistryObject<Item> ingot = addItemClass(name + "_ingot", () -> new ItemWithInformation(64, info));

		return new MaterialGroup(sediment, chunk, ingot);
	}

}
