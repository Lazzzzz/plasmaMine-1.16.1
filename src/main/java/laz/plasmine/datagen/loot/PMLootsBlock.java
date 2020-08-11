package laz.plasmine.datagen.loot;

import java.util.Optional;
import java.util.stream.Collectors;

import javax.annotation.Nonnull;

import laz.plasmine.Plasmine;
import net.minecraft.block.Block;
import net.minecraft.data.loot.BlockLootTables;
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
	protected void addTables() {}
}