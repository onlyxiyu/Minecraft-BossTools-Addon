package beyond_earth_giselle_addon.common;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import beyond_earth_giselle_addon.client.AddonClientProxy;
import beyond_earth_giselle_addon.common.block.AddonBlocks;
import beyond_earth_giselle_addon.common.block.entity.AddonBlockEntities;
import beyond_earth_giselle_addon.common.compat.AddonCompatibleManager;
import beyond_earth_giselle_addon.common.config.AddonConfigs;
import beyond_earth_giselle_addon.common.inventory.AddonMenuTypes;
import beyond_earth_giselle_addon.common.item.AddonItems;
import beyond_earth_giselle_addon.common.item.crafting.AddonRecipes;
import beyond_earth_giselle_addon.common.network.AddonNetwork;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.mrscauthd.beyond_earth.BeyondEarthMod;

@Mod(BeyondEarthAddon.MODID)
public class BeyondEarthAddon
{
	public static final String PMODID = BeyondEarthMod.MODID;
	public static final String MODID = "beyond_earth_giselle_addon";
	public static final Logger LOGGER = LogManager.getLogger();

	public BeyondEarthAddon()
	{
		ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, AddonConfigs.CommonSpec);

		IEventBus fml_bus = FMLJavaModLoadingContext.get().getModEventBus();
		AddonBlocks.BLOCKS.register(fml_bus);
		AddonItems.ITEMS.register(fml_bus);
		AddonRecipes.RECIPE_SERIALIZERS.register(fml_bus);
		AddonBlockEntities.BLOCK_ENTITIES.register(fml_bus);
		AddonMenuTypes.MENU_TYPES.register(fml_bus);
		AddonNetwork.registerAll();

		DistExecutor.safeRunWhenOn(Dist.CLIENT, () -> AddonClientProxy::new);

		IEventBus forgeEventBus = MinecraftForge.EVENT_BUS;
		forgeEventBus.register(EventListenerFuelAdapter.class);
		forgeEventBus.register(EventListenerGauge.class);
		forgeEventBus.register(EventListenerGravity.class);
		forgeEventBus.register(EventListenerFlagEdit.class);
		forgeEventBus.register(EventListenerReload.class);

		AddonCompatibleManager.visit();
	}

	public static ResourceLocation rl(String path)
	{
		return new ResourceLocation(MODID, path);
	}

	public static String tl(String category, String path)
	{
		return category + "." + MODID + "." + path;
	}

	public static String tl(String category, ResourceLocation rl)
	{
		return category + "." + rl.getNamespace() + "." + rl.getPath();
	}

	public static String tl(String category, ResourceLocation rl, String path)
	{
		return tl(category, rl) + "." + path;
	}

	public static ResourceLocation prl(String path)
	{
		return new ResourceLocation(PMODID, path);
	}

}
