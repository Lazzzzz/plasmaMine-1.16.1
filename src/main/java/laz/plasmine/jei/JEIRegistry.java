package laz.plasmine.jei;

//@JeiPlugin
//public class JEIRegistry implements IModPlugin {
//
//	public JEIRegistry() {}
//	
//	@Override
//	public ResourceLocation getPluginUid() {
//		return new ResourceLocation(Plasmine.MOD_ID, "default");
//	}
//
//	@Override
//	public void registerCategories(IRecipeCategoryRegistration registry) {
//		registry.addRecipeCategories(new CrystalizerJEI());
//	}
//
//	@Override
//	public void registerRecipes(IRecipeRegistration registration) {
//		if (Minecraft.getInstance().world != null) {
//			registration.addRecipes(getRecipes(Minecraft.getInstance().world,
//					PMRecipesSerializer.SEDIMENT_CRYSTALIZER_TYPE), CrystalizerJEI.ID);
//		}
//		System.out.println("--- JEI RECIPES DONE ---");
//	}
//	
//	public static <T extends IRecipe<?>> Collection<T> getRecipes(World world, IRecipeType<T> recipeType) {
//		Map<IRecipeType<?>, Map<ResourceLocation, IRecipe<?>>> recipes = ObfuscationReflectionHelper
//				.getPrivateValue(RecipeManager.class, world.getRecipeManager(), "field_199522_d");
//		if (recipes != null) {
//			Map<ResourceLocation, IRecipe<?>> typedRecipes = recipes.get(recipeType);
//			if (typedRecipes != null) {
//				return (Collection<T>) typedRecipes.values();
//			}
//		}
//		return new ArrayList<>();
//	}
//}