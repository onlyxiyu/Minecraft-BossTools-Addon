package beyond_earth_giselle_addon.common.compat.jaopca;

import beyond_earth_giselle_addon.common.compat.AddonCompatibleMod;
import net.minecraft.resources.ResourceLocation;

public class AddonJaopcaCompat extends AddonCompatibleMod
{
	public static final String MODID = "jaopca";

	public static ResourceLocation rl(String path)
	{
		return new ResourceLocation(MODID, path);
	}

	@Override
	public String getModID()
	{
		return MODID;
	}

	@Override
	protected void onLoad()
	{

	}

}
