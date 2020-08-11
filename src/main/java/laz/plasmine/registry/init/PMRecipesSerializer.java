package laz.plasmine.registry.init;

import static laz.plasmine.registry.PMRegistry.PM_SERIALIZER;

import laz.plasmine.recipes.sediementextractor.SedimentExtractorRecipe;
import laz.plasmine.recipes.sediementextractor.SedimentExtractorSerializer;
import laz.plasmine.recipes.sedimentcollector.SedimentCollectorRecipe;
import laz.plasmine.recipes.sedimentcollector.SedimentCollectorSerializer;
import laz.plasmine.recipes.sedimentcrystalizer.SedimentCrystalizerRecipe;
import laz.plasmine.recipes.sedimentcrystalizer.SedimentCrystalizerSerializer;
import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraft.item.crafting.IRecipeType;
import net.minecraftforge.fml.RegistryObject;

public class PMRecipesSerializer {

    public static RegistryObject<IRecipeSerializer<SedimentCollectorRecipe>> SEDIMENT_COLLECTOR_SERIALIZER;
    public static RegistryObject<IRecipeSerializer<SedimentExtractorRecipe>> SEDIMENT_EXTRACTOR_SERIALIZER;
    public static RegistryObject<IRecipeSerializer<SedimentCrystalizerRecipe>> SEDIMENT_CRYSTALIZER_SERIALIZER;

    public static IRecipeType<SedimentCollectorRecipe> SEDIMENT_COLLECTOR_TYPE;
    public static IRecipeType<SedimentExtractorRecipe> SEDIMENT_EXTRACTOR_TYPE;
    public static IRecipeType<SedimentCrystalizerRecipe> SEDIMENT_CRYSTALIZER_TYPE;
	
	public static void init() {
		
		SEDIMENT_COLLECTOR_TYPE = IRecipeType.register("sediment_collector");
		
		SEDIMENT_COLLECTOR_SERIALIZER = PM_SERIALIZER.register("sediment_collector", SedimentCollectorSerializer::new);
		SEDIMENT_EXTRACTOR_SERIALIZER = PM_SERIALIZER.register("sediment_extractor", SedimentExtractorSerializer::new);
		SEDIMENT_CRYSTALIZER_SERIALIZER = PM_SERIALIZER.register("sediment_crystalizer", SedimentCrystalizerSerializer::new);
		
	}
	
}
