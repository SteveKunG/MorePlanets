package stevekung.mods.moreplanets.core.config;

import net.minecraftforge.common.config.Config;
import stevekung.mods.moreplanets.core.MorePlanetsMod;

@Config(modid = MorePlanetsMod.MOD_ID, name = MorePlanetsMod.MOD_ID + "_server")
public class ConfigManagerServerMP
{
    @Config.Name(value = "Survival Planet Dimension Name")
    @Config.Comment("Choose a Survival Planet to start when on the server, For example: \"planet.moon\"")
    @Config.RequiresMcRestart
    public static String survivalPlanetDimensionName = "";
}