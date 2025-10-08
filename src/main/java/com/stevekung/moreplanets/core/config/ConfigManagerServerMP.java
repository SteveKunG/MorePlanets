package com.stevekung.moreplanets.core.config;

import com.stevekung.moreplanets.core.MorePlanetsMod;

import net.minecraftforge.common.config.Config;

@Config(modid = MorePlanetsMod.MOD_ID, name = MorePlanetsMod.MOD_ID + "_server")
public class ConfigManagerServerMP
{
    @Config.Name(value = "Survival Planet Dimension Name")
    @Config.Comment("Choose a Survival Planet to start when on the server, For example: \"planet.moon\"")
    @Config.RequiresMcRestart
    public static String survivalPlanetDimensionName = "";
}