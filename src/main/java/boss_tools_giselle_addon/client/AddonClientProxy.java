package boss_tools_giselle_addon.client;

import boss_tools_giselle_addon.client.gui.FuelLoaderScreen;
import boss_tools_giselle_addon.client.gui.GravityNormalizerScreen;
import boss_tools_giselle_addon.client.renderer.tileentity.GravityNormalizerRenderer;
import boss_tools_giselle_addon.common.inventory.container.AddonContainers;
import boss_tools_giselle_addon.common.tile.AddonTiles;
import net.minecraft.client.gui.ScreenManager;
import net.minecraft.inventory.container.ContainerType;
import net.minecraftforge.client.event.TextureStitchEvent;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

public class AddonClientProxy
{
	public AddonClientProxy()
	{
		IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();
		bus.addListener(this::onClientSetup);
		bus.addListener(this::onAtlasPreStitch);
		bus.addGenericListener(ContainerType.class, this::registerContainer);
	}

	public void onClientSetup(FMLClientSetupEvent event)
	{
		ClientRegistry.bindTileEntityRenderer(AddonTiles.GRAVITY_NORMALIZER.get(), GravityNormalizerRenderer::new);
	}

	public void registerContainer(RegistryEvent.Register<ContainerType<?>> event)
	{
		ScreenManager.register(AddonContainers.FUEL_LOADER.get(), FuelLoaderScreen::new);
		ScreenManager.register(AddonContainers.GRAVITY_NORMALIZER.get(), GravityNormalizerScreen::new);
	}

	public void onAtlasPreStitch(TextureStitchEvent.Pre event)
	{
		event.addSprite(RenderHelper.TILE_SURFACE);
	}

}
