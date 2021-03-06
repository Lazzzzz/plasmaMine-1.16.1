package laz.plasmine.registry;

import static laz.plasmine.Plasmine.ITEM_GROUP;
import static laz.plasmine.Plasmine.MOD_ID;

import java.util.function.Function;
import java.util.function.Supplier;

import laz.plasmine.registry.init.PMBlocksInit;
import laz.plasmine.registry.init.PMContainersInit;
import laz.plasmine.registry.init.PMItemsInit;
import laz.plasmine.registry.init.PMRecipesSerializer;
import laz.plasmine.registry.init.PMSoundInit;
import laz.plasmine.registry.init.PMTilesInit;
import net.minecraft.block.Block;
import net.minecraft.entity.EntityType;
import net.minecraft.inventory.container.ContainerType;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Food;
import net.minecraft.item.Item;
import net.minecraft.item.SpawnEggItem;
import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.SoundEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class PMRegistry {

	public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, MOD_ID);
	public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, MOD_ID);
	public static final DeferredRegister<TileEntityType<?>> TILE_ENTITIES = DeferredRegister
			.create(ForgeRegistries.TILE_ENTITIES, MOD_ID);
	public static final DeferredRegister<SoundEvent> SOUNDS = DeferredRegister.create(ForgeRegistries.SOUND_EVENTS, MOD_ID);
	public static final DeferredRegister<ContainerType<?>> PM_CONTAINER = DeferredRegister
			.create(ForgeRegistries.CONTAINERS, MOD_ID);
	public static final DeferredRegister<IRecipeSerializer<?>> PM_SERIALIZER = DeferredRegister
			.create(ForgeRegistries.RECIPE_SERIALIZERS, MOD_ID);

	public static int SIMPLE_INT = 0;
	public static int ITEMLIST_INT = 0;
	public static int BIOMES_INT = 0;
	public static int TILE_INT = 0;

	@SuppressWarnings("unchecked")
	public static final BlockRegistryObjectGroup<Block, BlockItem, ?>[] SIMPLE = new BlockRegistryObjectGroup[2000];
	@SuppressWarnings("unchecked")
	public static final RegistryObject<Item>[] ITEMLIST = new RegistryObject[1000];
	@SuppressWarnings("unchecked")
	public static final BlockRegistryObjectGroup<Block, BlockItem, TileEntity>[] TILES = new BlockRegistryObjectGroup[500];

	public static void register(IEventBus eventBus) {
		BLOCKS.register(eventBus);
		ITEMS.register(eventBus);
		TILE_ENTITIES.register(eventBus);
		PM_CONTAINER.register(eventBus);
		SOUNDS.register(eventBus);
		PM_SERIALIZER.register(eventBus);
	}

	public static void init(IEventBus eventBus) {
		PMContainersInit.init();
		PMItemsInit.init();
		PMBlocksInit.init();
		PMTilesInit.init();
		PMSoundInit.init();
		PMRecipesSerializer.init();
		register(eventBus);
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
	
	public static BlockRegistryObjectGroup<Block, BlockItem, TileEntity> addTileEntityNoItem(String name,
			Supplier<Block> blockSupplier, Supplier<TileEntity> tileSupplier) {
		TILE_INT = ++TILE_INT;
		return TILES[TILE_INT - 1] = new BlockRegistryObjectGroup<>(name, blockSupplier, blockItemCreatorNoTab(),
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
