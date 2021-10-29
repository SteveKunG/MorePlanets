package com.stevekung.moreplanets.forge.core;

import com.stevekung.moreplanets.core.MorePlanetsMod;
import com.stevekung.moreplanets.forge.proxy.MPClientProxyForge;
import com.stevekung.moreplanets.forge.proxy.MPCommonProxyForge;
import com.stevekung.stevekungslib.forge.utils.ForgeCommonUtils;
import com.stevekung.stevekungslib.forge.utils.ModVersionChecker;
import dev.architectury.platform.forge.EventBuses;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLLoadCompleteEvent;

@Mod(MorePlanetsMod.MOD_ID)
public class MorePlanetsForgeMod
{
    public static final ModVersionChecker CHECKER = new ModVersionChecker(MorePlanetsMod.MOD_ID);
    public static MPCommonProxyForge PROXY;

    public MorePlanetsForgeMod()
    {
        EventBuses.registerModEventBus(MorePlanetsMod.MOD_ID, ForgeCommonUtils.getModEventBus());
        MorePlanetsMod.init();
        //ForgeCommonUtils.registerConfig(ModConfig.Type.COMMON, SkyBlockcatiaConfig.GENERAL_BUILDER);
        //ForgeCommonUtils.registerModEventBus(SkyBlockcatiaConfig.class);
        ForgeCommonUtils.addModListener(this::loadComplete);

        PROXY = DistExecutor.safeRunForDist(() -> MPClientProxyForge::new, () -> MPCommonProxyForge::new);
        PROXY.init();
    }

    private void loadComplete(FMLLoadCompleteEvent event)
    {
        MorePlanetsForgeMod.CHECKER.startCheck();
    }
}