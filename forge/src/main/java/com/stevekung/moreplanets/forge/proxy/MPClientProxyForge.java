package com.stevekung.moreplanets.forge.proxy;

import com.stevekung.moreplanets.client.entity.models.DarkEnergyBallModel;
import com.stevekung.moreplanets.client.models.geom.MPModelLayers;
import com.stevekung.moreplanets.core.MorePlanetsMod;
import com.stevekung.stevekungslib.forge.utils.ForgeCommonUtils;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;

public class MPClientProxyForge extends MPCommonProxyForge
{
    @Override
    public void init()
    {
        super.init();
        ForgeCommonUtils.registerEventHandler(this);
        //ForgeCommonUtils.registerConfigScreen((mc, parent) -> ForgeCommonUtils.openConfigFile(parent, SteveKunGLib.MOD_ID, ModConfig.Type.COMMON));
    }

    @Override
    public void commonSetup(FMLCommonSetupEvent event)
    {
        super.commonSetup(event);
    }

    @Override
    public void clientSetup(FMLClientSetupEvent event)
    {
        super.clientSetup(event);
        MorePlanetsMod.initClient();
    }

    @SubscribeEvent
    public void registerLayerDefinitions(EntityRenderersEvent.RegisterLayerDefinitions event)
    {
        MPModelLayers.init();
        event.registerLayerDefinition(MPModelLayers.DARK_ENERGY_BALL, DarkEnergyBallModel::createBodyLayer);
    }
}