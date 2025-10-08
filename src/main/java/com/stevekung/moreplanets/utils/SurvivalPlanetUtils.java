package com.stevekung.moreplanets.utils;

import com.stevekung.moreplanets.core.config.ConfigManagerServerMP;
import com.stevekung.moreplanets.core.event.WorldTickEventHandler;

import net.minecraft.util.StringUtils;
import net.minecraft.world.WorldProvider;
import net.minecraftforge.fml.common.FMLCommonHandler;

import micdoodle8.mods.galacticraft.core.util.WorldUtil;

public class SurvivalPlanetUtils
{
    public static boolean hasSurvivalPlanetData()
    {
        return WorldTickEventHandler.survivalPlanetData != null && WorldTickEventHandler.survivalPlanetData.hasSurvivalPlanetData;
    }

    public static boolean hasSurvivalPlanetDataForServer()
    {
        return FMLCommonHandler.instance().getSide().isServer() && !StringUtils.isNullOrEmpty(getFormattedDimensionName());
    }

    public static WorldProvider getSurvivalPlanetProvider(String dimensionName)
    {
        return WorldUtil.getProviderForNameServer(dimensionName);
    }

    public static int getSurvivalPlanetDimension(int defaultDim)
    {
        if (SurvivalPlanetUtils.hasSurvivalPlanetData())
        {
            return SurvivalPlanetUtils.getSurvivalPlanetProvider(WorldTickEventHandler.survivalPlanetData.survivalPlanetName).getDimension();
        }
        else if (SurvivalPlanetUtils.hasSurvivalPlanetDataForServer())
        {
            return SurvivalPlanetUtils.getSurvivalPlanetProvider(getFormattedDimensionName()).getDimension();
        }
        return defaultDim;
    }

    public static String getFormattedDimensionName()
    {
        return ConfigManagerServerMP.survivalPlanetDimensionName.replace("\"", "");
    }
}