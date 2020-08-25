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
	public static RegistryObject<Item> PLASMA_PRODUCER_CORE;
	public static RegistryObject<Item> PLASMA_CONSUMER_CORE;
	
	public static RegistryObject<Item> RAPESEED_FRUIT;
	public static RegistryObject<Item> IONIZED_RAPESEED;
	
	public static MaterialGroup IRON;
	public static MaterialGroup GOLD;
	
	public static MaterialGroup LISIUM;
	public static MaterialGroup KETIUM;
	public static MaterialGroup ROSIUM;
	
	

	public static void init() {
		WRENCH 	   = addItemClass("wrench", () -> new ItemWithInformation(1, "Can rotate and remove machine"));
		
		GOLD_WIRE = addSimpleItem("gold_wire", 64);
		MAGNETIC_WIRE = addSimpleItem("magnetic_wire", 64);
		RAPESEED_FRUIT = addSimpleItem("rapeseed_fruit", 64);
		PLASMA_PRODUCER_CORE = addSimpleItem("plasma_producer_core", 64);
		PLASMA_CONSUMER_CORE = addSimpleItem("plasma_consumer_core", 64);
		
		IONIZED_RAPESEED = addSimpleItem("ionized_rapeseed", 64);

		IRON = initMaterialNoIngot("iron");
		GOLD = initMaterialNoIngot("gold");
		
		LISIUM = initMaterialAll("lisium", 1);
		KETIUM = initMaterialAll("ketium", 2);
		ROSIUM = initMaterialAll("rosium", 3);

	}

	public static MaterialGroup initMaterialAll(String name, int tier) {
		String info = "\u00A7b - Tier :\u00A74 " + tier;

		RegistryObject<Item> sediment = addItemClass(name + "_sediment", () -> new ItemWithInformation(64, info));
		RegistryObject<Item> chunk = addItemClass(name + "_chunk", () -> new ItemWithInformation(64, info));
		RegistryObject<Item> ingot = addItemClass(name + "_ingot", () -> new ItemWithInformation(64, info));

		return new MaterialGroup(true, true, true).setSediment(sediment).setChunk(chunk).setIngot(ingot);
	}
	
	public static MaterialGroup initMaterialNoIngot(String name) {
		RegistryObject<Item> sediment = addItemClass(name + "_sediment", () -> new ItemWithInformation(64, ""));
		RegistryObject<Item> chunk = addItemClass(name + "_chunk", () -> new ItemWithInformation(64, ""));

		return new MaterialGroup(true, true, false).setSediment(sediment).setChunk(chunk);
	}

}
