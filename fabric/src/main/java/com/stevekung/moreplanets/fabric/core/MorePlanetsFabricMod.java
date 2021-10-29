package com.stevekung.moreplanets.fabric.core;

import com.stevekung.moreplanets.core.MorePlanetsMod;
import net.fabricmc.api.ModInitializer;

public class MorePlanetsFabricMod implements ModInitializer
{
    @Override
    public void onInitialize()
    {
        MorePlanetsMod.init();
    }
}