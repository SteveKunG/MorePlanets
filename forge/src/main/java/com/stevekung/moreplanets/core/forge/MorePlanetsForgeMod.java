package com.stevekung.moreplanets.core.forge;

import com.stevekung.moreplanets.core.MorePlanetsMod;
import com.stevekung.stevekungslib.utils.forge.ForgeCommonUtils;
import com.stevekung.stevekungslib.utils.forge.ModVersionChecker;
import dev.architectury.platform.forge.EventBuses;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
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
        ForgeCommonUtils.addModListener(this::loadComplete);
        ForgeCommonUtils.addModListener(this::clientSetup);
    }

    private void loadComplete(FMLLoadCompleteEvent event)
    {
        MorePlanetsForgeMod.CHECKER.startCheck();
    }

    private void clientSetup(FMLClientSetupEvent event)
    {
        MorePlanetsMod.initClient();
    }
}