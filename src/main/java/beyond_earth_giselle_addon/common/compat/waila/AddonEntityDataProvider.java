package beyond_earth_giselle_addon.common.compat.waila;

import java.util.ArrayList;
import java.util.List;

import beyond_earth_giselle_addon.common.event.EntityGaugeValueFetchEvent;
import mcp.mobius.waila.api.EntityAccessor;
import mcp.mobius.waila.api.IEntityComponentProvider;
import mcp.mobius.waila.api.IServerDataProvider;
import mcp.mobius.waila.api.ITooltip;
import mcp.mobius.waila.api.config.IPluginConfig;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.Level;
import net.minecraftforge.common.MinecraftForge;
import net.mrscauthd.beyond_earth.gauge.IGaugeValue;

public class AddonEntityDataProvider implements IServerDataProvider<Entity>, IEntityComponentProvider
{
	public static final AddonEntityDataProvider INSTANCE = new AddonEntityDataProvider();

	@Override
	public void appendServerData(CompoundTag data, ServerPlayer player, Level level, Entity entity, boolean b)
	{
		List<IGaugeValue> list = new ArrayList<>();
		EntityGaugeValueFetchEvent event = new EntityGaugeValueFetchEvent(entity);
		MinecraftForge.EVENT_BUS.post(event);
		list.addAll(event.getValues());
		AddonWailaPlugin.put(data, AddonWailaPlugin.write(list));
	}

	@Override
	public void appendTooltip(ITooltip tooltip, EntityAccessor accessor, IPluginConfig config)
	{
		AddonWailaPlugin.appendTooltip(tooltip, accessor.getServerData());
	}

}
