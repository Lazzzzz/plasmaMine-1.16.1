package laz.plasmine.client.information;

import java.util.List;

import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;

public class HeatInformationBase {

	public static void info(int maxCapcity, float rate, List<ITextComponent> tooltip) {
		tooltip.add(new StringTextComponent("\u00A7b - Max celcius cap :"));
		tooltip.add(new StringTextComponent("  \u00A74" + maxCapcity + " °C"));
		tooltip.add(new StringTextComponent("\u00A7b - Thermo Conductivity per tick :"));
		tooltip.add(new StringTextComponent("  \u00A76" + rate + " °C"));
	}
	
}
