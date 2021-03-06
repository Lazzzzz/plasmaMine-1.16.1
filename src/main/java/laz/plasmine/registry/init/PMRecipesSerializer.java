package laz.plasmine.registry.init;

import static laz.plasmine.registry.PMRegistry.PM_SERIALIZER;

import laz.plasmine.Plasmine;
import laz.plasmine.recipes.basicgenerator.BasicGeneratorRecipe;
import laz.plasmine.recipes.basicgenerator.BasicGeneratorSerializer;
import laz.plasmine.recipes.crusher.CrusherRecipe;
import laz.plasmine.recipes.crusher.CrusherSerializer;
import laz.plasmine.recipes.emgenerator.EmRecipe;
import laz.plasmine.recipes.emgenerator.EmSerializer;
import laz.plasmine.recipes.ionizer.IonizerRecipe;
import laz.plasmine.recipes.ionizer.IonizerSerializer;
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
    public static RegistryObject<IRecipeSerializer<IonizerRecipe>> IONIZER_SERIALIZER;
    public static RegistryObject<IRecipeSerializer<CrusherRecipe>> CRUSHER_SERIALIZER;
    public static RegistryObject<IRecipeSerializer<BasicGeneratorRecipe>> BG_SERIALIZER;
    public static RegistryObject<IRecipeSerializer<EmRecipe>> EM_SERIALIZER;

    public static IRecipeType<SedimentCollectorRecipe> SEDIMENT_COLLECTOR_TYPE;
    public static IRecipeType<SedimentExtractorRecipe> SEDIMENT_EXTRACTOR_TYPE;
    public static IRecipeType<SedimentCrystalizerRecipe> SEDIMENT_CRYSTALIZER_TYPE;
    public static IRecipeType<IonizerRecipe> IONIZER_TYPE;
    public static IRecipeType<CrusherRecipe> CRUSHER_TYPE;
    public static IRecipeType<BasicGeneratorRecipe> BG_TYPE;
    public static IRecipeType<EmRecipe> EM_TYPE;
	
	public static void init() {
		
		SEDIMENT_COLLECTOR_TYPE = IRecipeType.register(setRegistreName("sediment_collector"));
		SEDIMENT_EXTRACTOR_TYPE = IRecipeType.register(setRegistreName("sediment_extractor"));
		SEDIMENT_CRYSTALIZER_TYPE = IRecipeType.register(setRegistreName("sediment_crystalizer"));
		IONIZER_TYPE = IRecipeType.register(setRegistreName("ionizer"));
		CRUSHER_TYPE = IRecipeType.register(setRegistreName("crusher"));
		BG_TYPE = IRecipeType.register(setRegistreName("basic_generator"));
		EM_TYPE = IRecipeType.register(setRegistreName("em"));
		
		SEDIMENT_COLLECTOR_SERIALIZER = PM_SERIALIZER.register("sediment_collector", SedimentCollectorSerializer::new);
		SEDIMENT_EXTRACTOR_SERIALIZER = PM_SERIALIZER.register("sediment_extractor", SedimentExtractorSerializer::new);
		SEDIMENT_CRYSTALIZER_SERIALIZER = PM_SERIALIZER.register("sediment_crystalizer", SedimentCrystalizerSerializer::new);
		IONIZER_SERIALIZER = PM_SERIALIZER.register("ionizer", IonizerSerializer::new);
		CRUSHER_SERIALIZER = PM_SERIALIZER.register("crusher", CrusherSerializer::new);
		BG_SERIALIZER = PM_SERIALIZER.register("basic_generator", BasicGeneratorSerializer::new);	
		EM_SERIALIZER = PM_SERIALIZER.register("em", EmSerializer::new);
		
	}

	private static String setRegistreName(String name) {
		return Plasmine.MOD_ID + ":" + name;
	}
}
