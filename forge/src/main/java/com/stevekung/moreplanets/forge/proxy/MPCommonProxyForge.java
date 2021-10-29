package com.stevekung.moreplanets.forge.proxy;

import com.stevekung.stevekungslib.forge.utils.ForgeCommonUtils;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;

public class MPCommonProxyForge
{
    public void init()
    {
        ForgeCommonUtils.registerModEventBus(this);
        ForgeCommonUtils.addModListener(this::commonSetup);
        ForgeCommonUtils.addModListener(this::clientSetup);
    }

    public void commonSetup(FMLCommonSetupEvent event)
    {
    }

    public void clientSetup(FMLClientSetupEvent event)
    {
    }
}