package laz.plasmine.registry;

import static laz.plasmine.Plasmine.ITEM_GROUP;
import static laz.plasmine.Plasmine.MOD_ID;

import java.util.function.Function;
import java.util.function.Supplier;

import laz.plasmine.registry.init.PMBlocksInit;
import laz.plasmine.registry.init.PMItemsInit;
import laz.plasmine.registry.init.PMTilesInit;
import net.minecraft.block.Block;
import net.minecraft.entity.EntityType;
import net.minecraft.inventory.container.ContainerType;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Food;
import net.minecraft.item.Item;
import net.minecraft.item.SpawnEggItem;
import net.minecraft.particles.ParticleType;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.feature.Feature;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class PMRegistry {

	public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, MOD_ID);
	public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, MOD_ID);
	public static final DeferredRegister<TileEntityType<?>> TILE_ENTITIES = DeferredRegister
			.create(ForgeRegistries.TILE_ENTITIES, MOD_ID);
	public static final DeferredRegister<ParticleType<?>> PARTICLE_TYPES = DeferredRegister
			.create(ForgeRegistries.PARTICLE_TYPES, MOD_ID);
	public static final DeferredRegister<Feature<?>> FEATURES = DeferredRegister.create(ForgeRegistries.FEATURES,
			MOD_ID);
	public static final DeferredRegister<ContainerType<?>> PM_CONTAINER = DeferredRegister
			.create(ForgeRegistries.CONTAINERS, MOD_ID);

	public static int SIMPLE_INT = 0;
	public static int ITEMLIST_INT = 0;
	public static int BIOMES_INT = 0;
	public static int TILE_INT = 0;

	public static final BlockRegistryObjectGroup<Block, BlockItem, ?>[] SIMPLE = new BlockRegistryObjectGroup[2000];
	public static final RegistryObject<Item>[] ITEMLIST = new RegistryObject[1000];
	public static final RegistryObject<Biome>[] BIOMELIST = new RegistryObject[150];
	public static final BlockRegistryObjectGroup<Block, BlockItem, TileEntity>[] TILES = new BlockRegistryObjectGroup[500];

	public static void register(IEventBus eventBus) {
		BLOCKS.register(eventBus);
		ITEMS.register(eventBus);
		TILE_ENTITIES.register(eventBus);
		FEATURES.register(eventBus);
		PARTICLE_TYPES.register(eventBus);
		PM_CONTAINER.register(eventBus);

	}

	public static void init(IEventBus eventBus) {
		PMBlocksInit.init();
		PMItemsInit.init();
		PMTilesInit.init();
		//register(eventBus);

	}

	public static BlockRegistryObjectGroup<Block, BlockItem, ?> addCubedBlock(String name,
			Block.Properties properties) {
		SIMPLE_INT = ++SIMPLE_INT;
		return SIMPLE[SIMPLE_INT - 1] = new BlockRegistryObjectGroup<>(name, () -> new Block(properties),
				blockItemCreator()).register(BLOCKS, ITEMS);
	}

	public static BlockRegistryObjectGroup<Block, BlockItem, ?> addBlockClass(String name,
			Supplier<Block> blockSupplier) {
		SIMPLE_INT = ++SIMPLE_INT;
		return SIMPLE[SIMPLE_INT - 1] = new BlockRegistryObjectGroup<>(name, blockSupplier, blockItemCreator())
				.register(BLOCKS, ITEMS);
	}

	public static BlockRegistryObjectGroup<Block, BlockItem, ?> addOnlyBlockClass(String name,
			Supplier<Block> blockSupplier) {
		SIMPLE_INT = ++SIMPLE_INT;
		return SIMPLE[SIMPLE_INT - 1] = new BlockRegistryObjectGroup<>(name, blockSupplier, blockItemCreatorNoTab())
				.register(BLOCKS, ITEMS);
	}

	private static <B extends Block> Function<B, BlockItem> blockItemCreator() {
		return block -> new BlockItem(block, new Item.Properties().group(ITEM_GROUP));
	}

	private static <B extends Block> Function<B, BlockItem> blockItemCreatorNoTab() {
		return block -> new BlockItem(block, new Item.Properties());
	}

	public static RegistryObject<Item> addItemClass(String name, Supplier<Item> itemSupplier) {
		ITEMLIST_INT = ++ITEMLIST_INT;
		return ITEMLIST[ITEMLIST_INT - 1] = ITEMS.register(name, itemSupplier);
	}

	public static RegistryObject<Item> addSimpleItem(String name, int size) {
		ITEMLIST_INT = ++ITEMLIST_INT;
		return ITEMLIST[ITEMLIST_INT - 1] = ITEMS.register(name,
				() -> new Item(new Item.Properties().group(ITEM_GROUP).maxStackSize(size)));
	}

	public static RegistryObject<Item> addSpawnEggs(EntityType<?> type, int color1, int color2, String name) {
		ITEMLIST_INT = ++ITEMLIST_INT;
		return ITEMLIST[ITEMLIST_INT - 1] = ITEMS.register(name,
				() -> new SpawnEggItem(type, color1, color2, new Item.Properties().group(ITEM_GROUP).maxStackSize(1)));
	}

	public static RegistryObject<Item> addSimpleFood(String name, int food) {
		ITEMLIST_INT = ++ITEMLIST_INT;
		return ITEMLIST[ITEMLIST_INT - 1] = ITEMS.register(name, () -> new Item(new Item.Properties().group(ITEM_GROUP)
				.food(new Food.Builder().hunger(food).saturation(food).build())));
	}

	public static BlockRegistryObjectGroup<Block, BlockItem, TileEntity> addTileEntity(String name,
			Supplier<Block> blockSupplier, Supplier<TileEntity> tileSupplier) {
		TILE_INT = ++TILE_INT;
		return TILES[TILE_INT - 1] = new BlockRegistryObjectGroup<>(name, blockSupplier, tileItemCreator(1),
				tileSupplier).register(BLOCKS, ITEMS, TILE_ENTITIES);
	}

	public static BlockRegistryObjectGroup<Block, BlockItem, TileEntity> addTileEntityWStackSize(String name, int size,
			Supplier<Block> blockSupplier, Supplier<TileEntity> tileSupplier) {
		TILE_INT = ++TILE_INT;
		return TILES[TILE_INT - 1] = new BlockRegistryObjectGroup<>(name, blockSupplier, tileItemCreator(size),
				tileSupplier).register(BLOCKS, ITEMS, TILE_ENTITIES);
	}

	private static <B extends Block> Function<B, BlockItem> tileItemCreator(int size) {
		return block -> new BlockItem(block, new Item.Properties().maxStackSize(size).group(ITEM_GROUP));
	}
}
