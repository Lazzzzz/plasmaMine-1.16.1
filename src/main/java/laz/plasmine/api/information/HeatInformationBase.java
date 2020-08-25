package laz.plasmine.api.information;

import java.util.List;

import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;

public class HeatInformationBase {

	public static void info(int maxCapcity, List<ITextComponent> tooltip) {
		tooltip.add(new StringTextComponent("\u00A7b - Max celcius cap :"));
		tooltip.add(new StringTextComponent("  \u00A74" + maxCapcity + " °C"));
	}
	
}
