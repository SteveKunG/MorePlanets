package stevekung.mods.moreplanets.utils;

import micdoodle8.mods.galacticraft.core.util.WorldUtil;
import net.minecraft.util.StringUtils;
import net.minecraft.world.WorldProvider;
import net.minecraftforge.fml.common.FMLCommonHandler;
import stevekung.mods.moreplanets.core.config.ConfigManagerServerMP;
import stevekung.mods.moreplanets.core.event.WorldTickEventHandler;

public class SurvivalPlanetUtils
{
    public static boolean hasSurvivalPlanetData()
    {
        return WorldTickEventHandler.survivalPlanetData != null && WorldTickEventHandler.survivalPlanetData.hasSurvivalPlanetData;
    }

    public static boolean hasSurvivalPlanetDataForServer()
    {
        String dimensionName = ConfigManagerServerMP.survivalPlanetDimensionName.replace("\"", "");
        return FMLCommonHandler.instance().getSide().isServer() && !StringUtils.isNullOrEmpty(dimensionName);
    }

    public static WorldProvider getSurvivalPlanetProvider(String dimensionName)
    {
        return WorldUtil.getProviderForNameServer(dimensionName);
    }
}