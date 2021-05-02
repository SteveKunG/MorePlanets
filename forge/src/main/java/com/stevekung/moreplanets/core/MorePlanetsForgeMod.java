package com.stevekung.moreplanets.core;

import com.stevekung.stevekungslib.utils.ForgeCommonUtils;
import com.stevekung.stevekungslib.utils.ModVersionChecker;
import me.shedaniel.architectury.platform.forge.EventBuses;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLLoadCompleteEvent;

@Mod(MorePlanetsMod.MOD_ID)
public class MorePlanetsForgeMod
{
    public static final ModVersionChecker CHECKER = new ModVersionChecker(MorePlanetsMod.MOD_ID);

    public MorePlanetsForgeMod()
    {
        EventBuses.registerModEventBus(MorePlanetsMod.MOD_ID, ForgeCommonUtils.getModEventBus());
        MorePlanetsMod.init();
//        ForgeCommonUtils.registerConfig(ModConfig.Type.COMMON, SkyBlockcatiaConfig.GENERAL_BUILDER);
//        ForgeCommonUtils.registerConfigScreen(() -> (mc, screen) -> ForgeCommonUtils.openConfigFile(screen, MorePlanetsMod.MOD_ID, ModConfig.Type.MOD_ID));

//        ForgeCommonUtils.registerModEventBus(SkyBlockcatiaConfig.class);
        ForgeCommonUtils.addModListener(this::phaseOne);
        ForgeCommonUtils.addModListener(this::loadComplete);
    }

    private void phaseOne(FMLCommonSetupEvent event)
    {
        ForgeCommonUtils.registerEventHandler(this);
    }

    private void loadComplete(FMLLoadCompleteEvent event)
    {
        MorePlanetsForgeMod.CHECKER.startCheck();
    }
}