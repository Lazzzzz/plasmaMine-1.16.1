package laz.plasmine;

import laz.plasmine.registry.init.PMItemsInit;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;

public class PMGroup extends ItemGroup {

    public PMGroup(String name) {
        super(name);
    }

    @Override
    public ItemStack createIcon() {
        return new ItemStack(PMItemsInit.WRENCH.get());
    }
    
}
