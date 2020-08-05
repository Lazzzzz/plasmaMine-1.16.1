package laz.plasmine;

import net.minecraft.block.Blocks;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;

public class PMGroup extends ItemGroup {

    public PMGroup(String name) {
        super(name);
    }

    @Override
    public ItemStack createIcon() {
        return new ItemStack(Blocks.IRON_BLOCK);
    }
}
