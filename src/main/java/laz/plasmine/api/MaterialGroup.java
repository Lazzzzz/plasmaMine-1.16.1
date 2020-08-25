package laz.plasmine.api;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.item.Item;
import net.minecraftforge.fml.RegistryObject;

public class MaterialGroup {

	private List<RegistryObject<Item>> elements = new ArrayList<RegistryObject<Item>>();
	private boolean [] hasItem = new boolean [3];
	
	public MaterialGroup(boolean a,boolean b, boolean c) {
		hasItem[0] = a;
		hasItem[1] = b;
		hasItem[2] = c;
	}
	
	public MaterialGroup setSediment(RegistryObject<Item> a) {
		elements.add(a);
		return this;
	}

	public MaterialGroup setChunk(RegistryObject<Item> a) {
		elements.add(a);
		return this;
	}
	
	public MaterialGroup setIngot(RegistryObject<Item> a) {
		elements.add(a);
		return this;
	}
	
	public Item getSediment() {
		if (hasItem[0]) return elements.get(0).get();
		return null;
	}

	public Item getChunk() {
		if (hasItem[1]) return elements.get(1).get();
		return null;
	}

	public Item getIngot() {
		if (hasItem[2]) return elements.get(2).get();
		return null;
	}
}
