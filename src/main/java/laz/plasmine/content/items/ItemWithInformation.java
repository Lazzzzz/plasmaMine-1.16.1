package laz.plasmine.content.items;

import static laz.plasmine.Plasmine.ITEM_GROUP;

import java.util.Arrays;
import java.util.List;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.world.World;

public class ItemWithInformation extends Item{

	private List<String> info;
	
	public ItemWithInformation(int size, String info) {
		super(new Item.Properties().group(ITEM_GROUP).maxStackSize(size));
		this.info = Arrays.asList(info);
	}
	
	public ItemWithInformation(int size, List<String> info) {
		super(new Item.Properties().group(ITEM_GROUP).maxStackSize(size));
		this.info = info;
	}
	
	@Override
	public void addInformation(ItemStack stack, World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
		for (int i = 0; i < info.size(); i++)
		tooltip.add(new StringTextComponent(info.get(i)));
	}

}
