package laz.plasmine.api;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.item.Item;
import net.minecraftforge.fml.RegistryObject;

public class MaterialGroup {

	private List<RegistryObject<Item>> elements = new ArrayList<RegistryObject<Item>>();

	public MaterialGroup(RegistryObject<Item> a, RegistryObject<Item> b, RegistryObject<Item> c) {

		elements.add(a);
		elements.add(b);
		elements.add(c);
	}

	public Item getSediment() {
		return elements.get(0).get();
	}

	public Item getChunk() {
		return elements.get(1).get();
	}

	public Item getIngot() {
		return elements.get(2).get();
	}
}
