package laz.plasmine.registry.init;

import laz.plasmine.recipes.sedimentcollector.SedimentCollectorRecipe;
import laz.plasmine.recipes.sedimentcollector.SedimentCollectorSerializer;
import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraft.item.crafting.IRecipeType;
import net.minecraftforge.fml.RegistryObject;
import static laz.plasmine.registry.PMRegistry.PM_SERIALIZER;

public class PMRecipesSerializer {

    public static RegistryObject<IRecipeSerializer<SedimentCollectorRecipe>> SEDIMENT_COLLECTOR_SERIALIZER;

    public static IRecipeType<SedimentCollectorRecipe> SEDIMENT_COLLECTOR_TYPE;
	
	public static void init() {
		
		SEDIMENT_COLLECTOR_TYPE = IRecipeType.register("sediment_collector");
		
		SEDIMENT_COLLECTOR_SERIALIZER = PM_SERIALIZER.register("sediment_collector", SedimentCollectorSerializer::new);
		
	}
	
}
