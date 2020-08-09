package laz.plasmine.client.information;

import java.util.List;

import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;

public class ConvertorInformationBase {

	public static void info(int maxCapcity, int rate, int tick, List<ITextComponent> tooltip) {
		tooltip.add(new StringTextComponent("\u00A7b - Max capacity :"));
		tooltip.add(new StringTextComponent("  \u00A74" + maxCapcity + " PU"));
		tooltip.add(new StringTextComponent("\u00A7b - Transfer rate :"));
		tooltip.add(new StringTextComponent("  \u00A76" + rate + " PU"));
		tooltip.add(new StringTextComponent("\u00A7b - Production per tick :"));
		tooltip.add(new StringTextComponent("  \u00A7a" + tick + " PU"));	
	}
	
}
