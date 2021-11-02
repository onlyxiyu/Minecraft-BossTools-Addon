package boss_tools_giselle_addon;

import boss_tools_giselle_addon.common.adapter.FuelAdapterBossToolsEntity;
import boss_tools_giselle_addon.common.adapter.FuelAdapterCreateEntityEvent;
import boss_tools_giselle_addon.common.adapter.OxygenStorageAdapterBossToolsSpaceArmor;
import boss_tools_giselle_addon.common.adapter.OxygenStorageAdapterItemStackCreateEvent;
import net.minecraft.entity.Entity;
import net.minecraft.item.Item;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.mrscauthd.boss_tools.block.FuelBlock;
import net.mrscauthd.boss_tools.entity.RocketEntity;
import net.mrscauthd.boss_tools.entity.RocketTier2Entity;
import net.mrscauthd.boss_tools.entity.RocketTier3Entity;
import net.mrscauthd.boss_tools.entity.RoverEntity;
import net.mrscauthd.boss_tools.item.FuelBucketBigItem;
import net.mrscauthd.boss_tools.item.NetheriteSpaceArmorItem;
import net.mrscauthd.boss_tools.item.SpaceArmorItem;

public class EventListener
{
	@SubscribeEvent
	public void onFuelAdapterCreateEntity(FuelAdapterCreateEntityEvent e)
	{
		Entity taget = e.getTaget();

		if (taget instanceof RoverEntity.CustomEntity)
		{
			e.setAdapter(FuelAdapterBossToolsEntity::new, FuelBlock.bucket);
		}
		else if (taget instanceof RocketEntity.CustomEntity)
		{
			e.setAdapter(FuelAdapterBossToolsEntity::new, FuelBlock.bucket);
		}
		else if (taget instanceof RocketTier2Entity.CustomEntity)
		{
			e.setAdapter(FuelAdapterBossToolsEntity::new, FuelBucketBigItem.block);
		}
		else if (taget instanceof RocketTier3Entity.CustomEntity)
		{
			e.setAdapter(FuelAdapterBossToolsEntity::new, FuelBucketBigItem.block);
		}

	}

	@SubscribeEvent
	public void onOxygenStorageAdapterItemStackCreate(OxygenStorageAdapterItemStackCreateEvent e)
	{
		Item item = e.getTaget().getItem();

		if (item == SpaceArmorItem.body || item == NetheriteSpaceArmorItem.body)
		{
			e.setAdapter(OxygenStorageAdapterBossToolsSpaceArmor::new);
		}

	}

}
