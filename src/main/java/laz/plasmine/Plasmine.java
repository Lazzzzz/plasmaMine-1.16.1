package laz.plasmine;

import static laz.plasmine.Plasmine.MOD_ID;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import laz.plasmine.network.PacketHandler;
import laz.plasmine.registry.PMRegistry;
import laz.plasmine.registry.render.BlockRender;
import laz.plasmine.registry.render.GuiRender;
import laz.plasmine.registry.render.TileEntityRender;
import net.minecraft.block.Block;
import net.minecraft.command.impl.ReloadCommand;
import net.minecraft.item.ItemGroup;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.lifecycle.InterModEnqueueEvent;
import net.minecraftforge.fml.event.lifecycle.InterModProcessEvent;
import net.minecraftforge.fml.event.server.FMLServerStartingEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(MOD_ID)
public class Plasmine {
	
	public static final String MOD_ID = "plasmine";
	public static final ItemGroup ITEM_GROUP = new PMGroup(MOD_ID + "_group");
	public static final Logger LOGGER = LogManager.getLogger();

	public Plasmine() {
		IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();
		FMLJavaModLoadingContext.get().getModEventBus().addListener(this::setup);
		FMLJavaModLoadingContext.get().getModEventBus().addListener(this::enqueueIMC);
		FMLJavaModLoadingContext.get().getModEventBus().addListener(this::processIMC);
		FMLJavaModLoadingContext.get().getModEventBus().addListener(this::doClientStuff);
		MinecraftForge.EVENT_BUS.register(this);
		PMRegistry.init(bus);
	}

	private void setup(final FMLCommonSetupEvent event) {
		PacketHandler.registerMessages();
	}

	private void doClientStuff(final FMLClientSetupEvent event) {
		TileEntityRender.init();
		GuiRender.init();
		BlockRender.init();
	}

	private void enqueueIMC(final InterModEnqueueEvent event) {
	}

	private void processIMC(final InterModProcessEvent event) {
	}

	@SubscribeEvent
	public void onServerStarting(FMLServerStartingEvent event) {
	}

	@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
	public static class RegistryEvents {
		@SubscribeEvent
		public static void onBlocksRegistry(final RegistryEvent.Register<Block> blockRegistryEvent) {
		}
	}
}
