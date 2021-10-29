package com.stevekung.moreplanets.core;

import com.stevekung.moreplanets.client.entity.models.DarkEnergyBallModel;
import com.stevekung.moreplanets.client.models.geom.MPModelLayers;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry;

public class MorePlanetsFabricClientMod implements ClientModInitializer
{
    @Override
    public void onInitializeClient()
    {
        //TODO ArmorRenderingRegistry.registerSimpleTexture(new ResourceLocation(MorePlanetsMod.MOD_ID, "glowing_iron"), MPItems.GLOWING_IRON_HELMET, MPItems.GLOWING_IRON_CHESTPLATE, MPItems.GLOWING_IRON_LEGGINGS, MPItems.GLOWING_IRON_BOOTS);
        MorePlanetsMod.initClient();
        EntityModelLayerRegistry.registerModelLayer(MPModelLayers.DARK_ENERGY_BALL, DarkEnergyBallModel::createBodyLayer);
    }
}