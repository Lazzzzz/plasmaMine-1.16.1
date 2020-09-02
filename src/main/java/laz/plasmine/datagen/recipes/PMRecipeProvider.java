package laz.plasmine.datagen.recipes;

import java.util.function.Consumer;

import laz.plasmine.registry.init.PMItemsInit;
import laz.plasmine.registry.init.PMTilesInit;
import net.minecraft.block.Blocks;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.IFinishedRecipe;
import net.minecraft.data.RecipeProvider;
import net.minecraft.data.ShapedRecipeBuilder;
import net.minecraft.data.ShapelessRecipeBuilder;
import net.minecraft.item.Items;
import net.minecraft.util.ResourceLocation;

public class PMRecipeProvider extends RecipeProvider {

	public PMRecipeProvider(DataGenerator generatorIn) {
		super(generatorIn);
	}
	
	@Override
	protected void registerRecipes(Consumer<IFinishedRecipe> consumer) {
		
		ShapedRecipeBuilder.shapedRecipe(PMItemsInit.PLASMA_CONSUMER_CORE.get())
		.key('X', Items.GOLD_INGOT)
		.key('Y', Items.IRON_INGOT)
		.key('Z', Items.DIAMOND)
		.patternLine("XYX")
		.patternLine("YZY")
		.patternLine("XYX")
		.addCriterion("has_material", RecipeProvider.hasItem(Items.DIAMOND))
		.build(consumer);
		
		ShapedRecipeBuilder.shapedRecipe(PMItemsInit.PLASMA_PRODUCER_CORE.get())
		.key('X', Items.GOLD_INGOT)
		.key('Y', Items.IRON_INGOT)
		.key('Z', Blocks.LAPIS_BLOCK)
		.patternLine("XYX")
		.patternLine("YZY")
		.patternLine("XYX").setGroup("plasmine")
		.addCriterion("has_material", RecipeProvider.hasItem(Blocks.LAPIS_BLOCK))
		.build(consumer);
		
		ShapedRecipeBuilder.shapedRecipe(PMItemsInit.HEAT_CONSUMER_CORE.get())
		.key('X', Items.GOLD_INGOT)
		.key('Y', Items.IRON_INGOT)
		.key('Z', Blocks.REDSTONE_BLOCK)
		.patternLine("XYX")
		.patternLine("YZY")
		.patternLine("XYX").setGroup("plasmine")
		.addCriterion("has_material", RecipeProvider.hasItem(Blocks.REDSTONE_BLOCK))
		.build(consumer);
		
		ShapedRecipeBuilder.shapedRecipe(PMTilesInit.SEDIMENT_COLLECTOR.get())
		.key('X', Items.LAPIS_LAZULI)
		.key('Y', Items.IRON_INGOT)
		.key('Z', PMItemsInit.HEAT_CONSUMER_CORE.get())
		.key('A', Blocks.IRON_BARS)
		.key('B', Blocks.IRON_BLOCK)
		.patternLine("XYX")
		.patternLine("AZA")
		.patternLine("ABA").setGroup("plasmine")
		.addCriterion("has_material", RecipeProvider.hasItem(Blocks.IRON_BLOCK))
		.build(consumer);
		
		ShapedRecipeBuilder.shapedRecipe(PMTilesInit.SEDIMENT_EXTRACTOR.get())
		.key('X', Items.GOLD_INGOT)
		.key('Y', Items.IRON_INGOT)
		.key('Z', PMItemsInit.HEAT_CONSUMER_CORE.get())
		.key('A', Blocks.IRON_BARS)
		.key('B', Blocks.IRON_BLOCK)
		.patternLine("XYX")
		.patternLine("AZA")
		.patternLine("ABA").setGroup("plasmine")
		.addCriterion("has_material", RecipeProvider.hasItem(Blocks.IRON_BLOCK))
		.build(consumer);
		
		ShapedRecipeBuilder.shapedRecipe(PMTilesInit.SEDIMENT_CRYSTALIZER.get())
		.key('X', Items.DIAMOND)
		.key('Y', Items.IRON_INGOT)
		.key('Z', PMItemsInit.HEAT_CONSUMER_CORE.get())
		.key('A', Blocks.IRON_BARS)
		.key('B', Blocks.IRON_BLOCK)
		.patternLine("XYX")
		.patternLine("AZA")
		.patternLine("ABA").setGroup("plasmine")
		.addCriterion("has_material", RecipeProvider.hasItem(Blocks.IRON_BLOCK))
		.build(consumer);
		
		ShapedRecipeBuilder.shapedRecipe(PMTilesInit.FURNACE.get())
		.key('Y', Items.GOLD_INGOT)
		.key('X', Items.IRON_INGOT)
		.key('Z', PMItemsInit.HEAT_CONSUMER_CORE.get())
		.key('A', Blocks.IRON_BARS)
		.key('B', Blocks.FURNACE)
		.patternLine("XYX")
		.patternLine("AZA")
		.patternLine("ABA").setGroup("plasmine")
		.addCriterion("has_material", RecipeProvider.hasItem(Blocks.FURNACE))
		.build(consumer);
		
		ShapedRecipeBuilder.shapedRecipe(PMTilesInit.CRUSHER.get())
		.key('Y', Items.GOLD_INGOT)
		.key('X', Items.IRON_INGOT)
		.key('Z', PMItemsInit.HEAT_CONSUMER_CORE.get())
		.key('A', Blocks.IRON_BARS)
		.key('B', PMTilesInit.FURNACE.get())
		.patternLine("XYX")
		.patternLine("AZA")
		.patternLine("ABA").setGroup("plasmine")
		.addCriterion("has_material", RecipeProvider.hasItem(PMTilesInit.FURNACE.get()))
		.build(consumer);
		
		ShapedRecipeBuilder.shapedRecipe(PMTilesInit.IONIZER.get())
		.key('X', PMItemsInit.KETIUM.getIngot())
		.key('Z', PMItemsInit.HEAT_CONSUMER_CORE.get())
		.key('A', Blocks.IRON_BARS)
		.key('B', Blocks.IRON_BLOCK)
		.patternLine("XXX")
		.patternLine("AZA")
		.patternLine("ABA").setGroup("plasmine")
		.addCriterion("has_material", RecipeProvider.hasItem(PMItemsInit.KETIUM.getIngot()))
		.build(consumer);
		
		ShapedRecipeBuilder.shapedRecipe(PMTilesInit.CROP_BLOWER.get())
		.key('X', Items.IRON_INGOT)
		.key('Z', PMItemsInit.HEAT_CONSUMER_CORE.get())
		.key('A', Blocks.IRON_BARS)
		.key('B', Blocks.IRON_BLOCK)
		.patternLine("AAX")
		.patternLine("BZX")
		.patternLine("AAX").setGroup("plasmine")
		.addCriterion("has_material", RecipeProvider.hasItem(Blocks.IRON_BLOCK))
		.build(consumer);
		
		ShapedRecipeBuilder.shapedRecipe(PMTilesInit.CROP_HEATER.get())
		.key('X', PMItemsInit.KETIUM.getIngot())
		.key('Z', PMItemsInit.HEAT_CONSUMER_CORE.get())
		.key('A', Blocks.IRON_BARS)
		.key('B', Blocks.IRON_BLOCK)
		.patternLine("XZX")
		.patternLine("XZX")
		.patternLine("ABA").setGroup("plasmine")
		.addCriterion("has_material", RecipeProvider.hasItem(PMItemsInit.KETIUM.getIngot()))
		.build(consumer);
		
		ShapedRecipeBuilder.shapedRecipe(PMTilesInit.SOLAR_CONVERTOR.get())
		.key('X', PMItemsInit.KETIUM.getIngot())
		.key('Z', PMItemsInit.PLASMA_CONSUMER_CORE.get())
		.key('Q', PMItemsInit.LISIUM.getIngot())
		.key('A', Blocks.IRON_BARS)
		.key('B', Blocks.IRON_BLOCK)
		.patternLine("AX ")
		.patternLine("ZBQ")
		.patternLine("AX ").setGroup("plasmine")
		.addCriterion("has_material", RecipeProvider.hasItem(PMItemsInit.KETIUM.getIngot()))
		.build(consumer);
		
		ShapedRecipeBuilder.shapedRecipe(PMTilesInit.BASIC_CONVERTOR.get())
		.key('X', Items.IRON_INGOT)
		.key('Z', PMItemsInit.PLASMA_CONSUMER_CORE.get())
		.key('A', Blocks.IRON_BARS)
		.key('B', Blocks.IRON_BLOCK)
		.patternLine("AX ")
		.patternLine("ZBX")
		.patternLine("AX ").setGroup("plasmine")
		.addCriterion("has_material", RecipeProvider.hasItem(Items.IRON_INGOT))
		.build(consumer);
		
		ShapedRecipeBuilder.shapedRecipe(PMTilesInit.ADVANCED_CONVERTOR.get())
		.key('X', PMItemsInit.LISIUM.getIngot())
		.key('Z', PMItemsInit.PLASMA_CONSUMER_CORE.get())
		.key('A', Blocks.IRON_BARS)
		.key('B', Blocks.IRON_BLOCK)
		.patternLine("AX ")
		.patternLine("ZBX")
		.patternLine("AX ").setGroup("plasmine")
		.addCriterion("has_material", RecipeProvider.hasItem(PMItemsInit.LISIUM.getIngot()))
		.build(consumer);
		
		ShapedRecipeBuilder.shapedRecipe(PMTilesInit.SUPER_ADVANCED_CONVERTOR.get())
		.key('X', PMItemsInit.KETIUM.getIngot())
		.key('Z', PMItemsInit.PLASMA_CONSUMER_CORE.get())
		.key('A', Blocks.IRON_BARS)
		.key('B', Blocks.IRON_BLOCK)
		.patternLine("AX ")
		.patternLine("ZBX")
		.patternLine("AX ").setGroup("plasmine")
		.addCriterion("has_material", RecipeProvider.hasItem(PMItemsInit.KETIUM.getIngot()))
		.build(consumer);
		
		ShapedRecipeBuilder.shapedRecipe(PMTilesInit.ULTIMATE_CONVERTOR.get())
		.key('X', PMItemsInit.ROSIUM.getIngot())
		.key('Z', PMItemsInit.PLASMA_CONSUMER_CORE.get())
		.key('A', Blocks.IRON_BARS)
		.key('B', Blocks.IRON_BLOCK)
		.patternLine("AX ")
		.patternLine("ZBX")
		.patternLine("AX ").setGroup("plasmine")
		.addCriterion("has_material", RecipeProvider.hasItem(PMItemsInit.ROSIUM.getIngot()))
		.build(consumer);
		
		ShapedRecipeBuilder.shapedRecipe(PMTilesInit.MACHINE_BLOCK.get())
		.key('X', PMItemsInit.LISIUM.getIngot())
		.key('Y', Items.IRON_INGOT)
		.patternLine("YXY")
		.patternLine("X X")
		.patternLine("YXY").setGroup("plasmine")
		.addCriterion("has_material", RecipeProvider.hasItem(PMItemsInit.LISIUM.getIngot()))
		.build(consumer, PMTilesInit.MACHINE_BLOCK.get().getRegistryName() + "_1");
		
		ShapedRecipeBuilder.shapedRecipe(PMTilesInit.MACHINE_BLOCK.get(), 2)
		.key('X', PMItemsInit.KETIUM.getIngot())
		.key('Y', Items.IRON_INGOT)
		.patternLine("YXY")
		.patternLine("X X")
		.patternLine("YXY").setGroup("plasmine")
		.addCriterion("has_material", RecipeProvider.hasItem(PMItemsInit.KETIUM.getIngot()))
		.build(consumer , PMTilesInit.MACHINE_BLOCK.get().getRegistryName() + "_2");
		
		ShapedRecipeBuilder.shapedRecipe(PMTilesInit.MACHINE_BLOCK.get(), 4)
		.key('X', PMItemsInit.ROSIUM.getIngot())
		.key('Y', Items.IRON_INGOT)
		.patternLine("YXY")
		.patternLine("X X")
		.patternLine("YXY").setGroup("plasmine")
		.addCriterion("has_material", RecipeProvider.hasItem(PMItemsInit.ROSIUM.getIngot()))
		.build(consumer, PMTilesInit.MACHINE_BLOCK.get().getRegistryName() + "_3");
		
		ShapedRecipeBuilder.shapedRecipe(PMTilesInit.LISIUM_COIL.get(), 2)
		.key('X', PMItemsInit.LISIUM.getIngot())
		.key('Y', Items.IRON_INGOT)
		.patternLine("YXY")
		.patternLine("X X")
		.patternLine("YXY").setGroup("plasmine")
		.addCriterion("has_material", RecipeProvider.hasItem(PMItemsInit.LISIUM.getIngot()))
		.build(consumer);
		
		ShapedRecipeBuilder.shapedRecipe(PMTilesInit.KETIUM_COIL.get(), 1)
		.key('X', PMItemsInit.KETIUM.getIngot())
		.key('A', PMTilesInit.LISIUM_COIL.get())
		.patternLine(" X ")
		.patternLine("XAX")
		.patternLine(" X ").setGroup("plasmine")
		.addCriterion("has_material", RecipeProvider.hasItem(PMItemsInit.KETIUM.getIngot()))
		.build(consumer);
		
		ShapedRecipeBuilder.shapedRecipe(PMTilesInit.ROSIUM_COIL.get(), 1)
		.key('X', PMItemsInit.ROSIUM.getIngot())
		.key('A', PMTilesInit.KETIUM_COIL.get())
		.patternLine(" X ")
		.patternLine("XAX")
		.patternLine(" X ").setGroup("plasmine")
		.addCriterion("has_material", RecipeProvider.hasItem(PMItemsInit.ROSIUM.getIngot()))
		.build(consumer);
		
		ShapedRecipeBuilder.shapedRecipe(PMItemsInit.GOLD_WIRE.get(), 4)
		.key('X', Items.GOLD_INGOT)
		.key('A', Items.STICK)
		.patternLine(" X ")
		.patternLine("XAX")
		.patternLine(" X ").setGroup("plasmine")
		.addCriterion("has_material", RecipeProvider.hasItem(Items.GOLD_INGOT))
		.build(consumer);
		
		ShapedRecipeBuilder.shapedRecipe(PMItemsInit.MAGNETIC_WIRE.get(), 2)
		.key('X', Items.IRON_INGOT)
		.key('A', PMItemsInit.GOLD_WIRE.get())
		.key('Y', Items.REDSTONE)
		.patternLine("YYY")
		.patternLine("XAX")
		.patternLine("YYY").setGroup("plasmine")
		.addCriterion("has_material", RecipeProvider.hasItem(PMItemsInit.GOLD_WIRE.get()))
		.build(consumer);
		
		ShapedRecipeBuilder.shapedRecipe(PMTilesInit.MAGNETIC_COIL.get(), 1)
		.key('X', PMItemsInit.MAGNETIC_WIRE.get())
		.patternLine("XXX")
		.patternLine("XXX")
		.patternLine("XXX").setGroup("plasmine")
		.addCriterion("has_material", RecipeProvider.hasItem(PMItemsInit.MAGNETIC_WIRE.get()))
		.build(consumer);
		
		ShapelessRecipeBuilder.shapelessRecipe(PMTilesInit.HUPPER.get())
		.addIngredient(Blocks.HOPPER)
		.addCriterion("has_material", RecipeProvider.hasItem(Blocks.HOPPER))
		.build(consumer);
		
		ShapelessRecipeBuilder.shapelessRecipe(PMTilesInit.PLASMA_INPUT.get())
		.addIngredient(PMTilesInit.MACHINE_BLOCK.get())
		.addIngredient(PMItemsInit.PLASMA_CONSUMER_CORE.get())		
		.addCriterion("has_material", RecipeProvider.hasItem(PMTilesInit.MACHINE_BLOCK.get()))
		.build(consumer);
		
		ShapelessRecipeBuilder.shapelessRecipe(PMTilesInit.PLASMA_OUTPUT.get())
		.addIngredient(PMTilesInit.MACHINE_BLOCK.get())
		.addIngredient(PMItemsInit.PLASMA_PRODUCER_CORE.get())		
		.addCriterion("has_material", RecipeProvider.hasItem(PMTilesInit.MACHINE_BLOCK.get()))
		.build(consumer);
		
		ShapelessRecipeBuilder.shapelessRecipe(PMTilesInit.ITEM_INPUT.get())
		.addIngredient(PMTilesInit.MACHINE_BLOCK.get())
		.addIngredient(Blocks.CHEST)		
		.addCriterion("has_material", RecipeProvider.hasItem(PMTilesInit.MACHINE_BLOCK.get()))
		.build(consumer);
		
		ShapedRecipeBuilder.shapedRecipe(PMItemsInit.WRENCH.get())
		.key('X', Items.IRON_INGOT)
		.key('Y', Items.LAPIS_LAZULI)
		.patternLine(" XY")
		.patternLine(" XX")
		.patternLine("X  ").setGroup("plasmine")
		.addCriterion("has_material", RecipeProvider.hasItem(Items.LAPIS_LAZULI))
		.build(consumer);
		
		ShapedRecipeBuilder.shapedRecipe(PMTilesInit.BASIC_PLASMA_CABLE.get(), 4)
		.key('X', Items.IRON_INGOT)
		.key('Y', Items.DIAMOND)
		.key('Z', Items.REDSTONE)
		.patternLine("XXX")
		.patternLine("ZYZ")
		.patternLine("XXX").setGroup("plasmine")
		.addCriterion("has_material", RecipeProvider.hasItem(Items.DIAMOND))
		.build(consumer, PMTilesInit.BASIC_PLASMA_CABLE.get().getRegistryName() + "_1");
		
		ShapedRecipeBuilder.shapedRecipe(PMTilesInit.BASIC_PLASMA_CABLE.get(), 8)
		.key('X', PMItemsInit.LISIUM.getIngot())
		.key('Y', Items.DIAMOND)
		.key('Z', Items.REDSTONE)
		.patternLine("XXX")
		.patternLine("ZYZ")
		.patternLine("XXX").setGroup("plasmine")
		.addCriterion("has_material", RecipeProvider.hasItem(PMItemsInit.LISIUM.getIngot()))
		.build(consumer, PMTilesInit.BASIC_PLASMA_CABLE.get().getRegistryName() + "_2");
		
		ShapedRecipeBuilder.shapedRecipe(PMTilesInit.BASIC_PLASMA_CABLE.get(), 12)
		.key('X', PMItemsInit.KETIUM.getIngot())
		.key('Y', Items.DIAMOND)
		.key('Z', Items.REDSTONE)
		.patternLine("XXX")
		.patternLine("ZYZ")
		.patternLine("XXX").setGroup("plasmine")
		.addCriterion("has_material", RecipeProvider.hasItem(PMItemsInit.KETIUM.getIngot()))
		.build(consumer, PMTilesInit.BASIC_PLASMA_CABLE.get().getRegistryName() + "_3");
		
		ShapedRecipeBuilder.shapedRecipe(PMTilesInit.BASIC_PLASMA_CABLE.get(), 24)
		.key('X', PMItemsInit.ROSIUM.getIngot())
		.key('Y', Items.DIAMOND)
		.key('Z', Items.REDSTONE)
		.patternLine("XXX")
		.patternLine("ZYZ")
		.patternLine("XXX").setGroup("plasmine")
		.addCriterion("has_material", RecipeProvider.hasItem(PMItemsInit.ROSIUM.getIngot()))
		.build(consumer, PMTilesInit.BASIC_PLASMA_CABLE.get().getRegistryName() + "_4");
		
		ShapedRecipeBuilder.shapedRecipe(PMTilesInit.BASIC_GENERATOR.get())
		.key('X', Items.IRON_INGOT)
		.key('Y', Blocks.GOLD_BLOCK)
		.key('Z', PMItemsInit.PLASMA_PRODUCER_CORE.get())
		.patternLine(" X ")
		.patternLine("XYX")
		.patternLine("XZX").setGroup("plasmine")
		.addCriterion("has_material", RecipeProvider.hasItem(PMItemsInit.PLASMA_PRODUCER_CORE.get()))
		.build(consumer);
		
		ShapedRecipeBuilder.shapedRecipe(PMTilesInit.EM_GENERATOR.get())
		.key('X', Items.DIAMOND)
		.key('Y', PMTilesInit.MAGNETIC_COIL.get())
		.key('Z', PMItemsInit.PLASMA_PRODUCER_CORE.get())
		.key('C', PMItemsInit.LISIUM.getIngot())
		.patternLine("CYC")
		.patternLine("CYC")
		.patternLine("XZX").setGroup("plasmine")
		.addCriterion("has_material", RecipeProvider.hasItem(PMItemsInit.LISIUM.getIngot()))
		.build(consumer);
		
		ShapedRecipeBuilder.shapedRecipe(PMTilesInit.PLASMA_STORAGE.get())
		.key('X', PMTilesInit.MAGNETIC_COIL.get())
		.key('Y', PMItemsInit.LISIUM.getIngot())
		.key('Z', PMTilesInit.MACHINE_BLOCK.get())
		.key('C', Items.IRON_INGOT)
		.patternLine("CYC")
		.patternLine("XYX")
		.patternLine("CZC").setGroup("plasmine")
		.addCriterion("has_material", RecipeProvider.hasItem(PMItemsInit.LISIUM.getIngot()))
		.build(consumer);
		
	}

}
