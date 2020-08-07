package laz.plasmine.content.items;

import static laz.plasmine.Plasmine.ITEM_GROUP;

import net.minecraft.item.Item;

public class WrenchItem extends Item{

	public WrenchItem() {
		super(new Item.Properties().group(ITEM_GROUP).maxDamage(1));
	}

}
