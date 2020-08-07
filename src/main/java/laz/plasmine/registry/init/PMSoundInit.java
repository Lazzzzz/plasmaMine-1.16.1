package laz.plasmine.registry.init;

import static laz.plasmine.Plasmine.MOD_ID;

import laz.plasmine.registry.PMRegistry;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraftforge.fml.RegistryObject;

public class PMSoundInit {

	public static RegistryObject<SoundEvent> WRENCH_USE;
	
	public static void init() {
		WRENCH_USE = PMRegistry.SOUNDS.register("wrench.use", () -> new SoundEvent(new ResourceLocation(MOD_ID, "wrench.use")));
		
	}
	
}
