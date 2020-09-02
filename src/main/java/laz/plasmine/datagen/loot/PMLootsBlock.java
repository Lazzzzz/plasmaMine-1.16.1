package laz.plasmine.datagen.loot;

import java.util.Optional;
import java.util.stream.Collectors;

import javax.annotation.Nonnull;

import laz.plasmine.Plasmine;
import laz.plasmine.registry.init.PMBlocksInit;
import laz.plasmine.registry.init.PMItemsInit;
import laz.plasmine.registry.init.PMTilesInit;
import net.minecraft.advancements.criterion.StatePropertiesPredicate;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.CropsBlock;
import net.minecraft.data.loot.BlockLootTables;
import net.minecraft.item.Items;
import net.minecraft.loot.conditions.BlockStateProperty;
import net.minecraft.loot.conditions.ILootCondition;
import net.minecraftforge.registries.ForgeRegistries;

public class PMLootsBlock extends BlockLootTables {

	@Override
	@Nonnull
	protected Iterable<Block> getKnownBlocks() {
		return ForgeRegistries.BLOCKS.getValues().stream()
				.filter(block -> Optional.ofNullable(block.getRegistryName())
						.filter(registryName -> registryName.getNamespace().equals(Plasmine.MOD_ID)).isPresent())
				.collect(Collectors.toList());
	}

	@Override
	protected void addTables() {
		ILootCondition.IBuilder ilootcondition$ibuilder1 = BlockStateProperty.builder(PMBlocksInit.RAPESEED_CROP.get())
				.fromProperties(StatePropertiesPredicate.Builder.newBuilder().withIntProp(CropsBlock.AGE, 3));

		this.registerLootTable(PMBlocksInit.RAPESEED_CROP.get(),
				droppingAndBonusWhen(PMBlocksInit.RAPESEED_CROP.get(), PMItemsInit.RAPESEED_FRUIT.get(), PMBlocksInit.RAPESEED_CROP.getItem(), ilootcondition$ibuilder1));

		this.registerDropSelfLootTable(PMBlocksInit.CONVEYOR.get());
		this.registerDropSelfLootTable(PMBlocksInit.CONVEYOR_UP.get());
		this.registerDropSelfLootTable(PMTilesInit.ADVANCED_CONVERTOR.get());
		this.registerDropSelfLootTable(PMTilesInit.BASIC_CONVERTOR.get());
		this.registerDropSelfLootTable(PMTilesInit.BASIC_GENERATOR.get());
		this.registerDropSelfLootTable(PMTilesInit.BASIC_PLASMA_CABLE.get());
		this.registerDropSelfLootTable(PMTilesInit.CROP_BLOWER.get());
		this.registerDropSelfLootTable(PMTilesInit.CROP_HEATER.get());
		this.registerDropSelfLootTable(PMTilesInit.CRUSHER.get());
		this.registerDropSelfLootTable(PMTilesInit.EM_GENERATOR.get());
		this.registerDropSelfLootTable(PMTilesInit.FURNACE.get());
		this.registerDropSelfLootTable(PMTilesInit.HUPPER.get());
		this.registerDropSelfLootTable(PMTilesInit.IONIZER.get());
		this.registerDropSelfLootTable(PMTilesInit.ITEM_INPUT.get());
		this.registerDropSelfLootTable(PMTilesInit.KETIUM_COIL.get());
		this.registerDropSelfLootTable(PMTilesInit.LISIUM_COIL.get());
		this.registerDropSelfLootTable(PMTilesInit.MACHINE_BLOCK.get());
		this.registerDropSelfLootTable(PMTilesInit.MAGNETIC_COIL.get());
		this.registerDropSelfLootTable(PMTilesInit.MINER.get());
		this.registerDropSelfLootTable(PMTilesInit.PLASMA_INPUT.get());
		this.registerDropSelfLootTable(PMTilesInit.PLASMA_OUTPUT.get());
		this.registerDropSelfLootTable(PMTilesInit.PLASMA_STORAGE.get());
		this.registerDropSelfLootTable(PMTilesInit.ROSIUM_COIL.get());
		this.registerDropSelfLootTable(PMTilesInit.SEDIMENT_COLLECTOR.get());
		this.registerDropSelfLootTable(PMTilesInit.SEDIMENT_CRYSTALIZER.get());
		this.registerDropSelfLootTable(PMTilesInit.SEDIMENT_EXTRACTOR.get());
		this.registerDropSelfLootTable(PMTilesInit.SOLAR_CONVERTOR.get());
		this.registerDropSelfLootTable(PMTilesInit.SUPER_ADVANCED_CONVERTOR.get());
		this.registerDropSelfLootTable(PMTilesInit.STRUCTURE_BLOCK.get());
		this.registerDropSelfLootTable(PMTilesInit.ULTIMATE_CONVERTOR.get());
	}
}