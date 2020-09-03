package laz.plasmine.api.information;

import java.util.List;

import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;

public class ConvertorInformationBase {

	public static void info(float efficiency, int tick, int temp, List<ITextComponent> tooltip) {
		tooltip.add(new StringTextComponent("Will convert plasma to heat but will"));
		tooltip.add(new StringTextComponent("cool as fast as it heat"));
		tooltip.add(new StringTextComponent("\u00A7b - Efficiency :"));
		tooltip.add(new StringTextComponent("  \u00A74" + (int) (efficiency * 100) + " %"));
		tooltip.add(new StringTextComponent("\u00A7b - Consomation per tick :"));
		tooltip.add(new StringTextComponent("  \u00A76" + tick + " PU"));
		tooltip.add(new StringTextComponent("\u00A7b - Max temp :"));
		tooltip.add(new StringTextComponent("  \u00A7a" + temp));
	}
	
}
