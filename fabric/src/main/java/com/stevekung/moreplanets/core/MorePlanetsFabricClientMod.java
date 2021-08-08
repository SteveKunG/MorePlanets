package com.stevekung.moreplanets.core;

import net.fabricmc.api.ClientModInitializer;

public class MorePlanetsFabricClientMod implements ClientModInitializer
{
    @Override
    public void onInitializeClient()
    {
        //TODO ArmorRenderingRegistry.registerSimpleTexture(new ResourceLocation(MorePlanetsMod.MOD_ID, "glowing_iron"), MPItems.GLOWING_IRON_HELMET, MPItems.GLOWING_IRON_CHESTPLATE, MPItems.GLOWING_IRON_LEGGINGS, MPItems.GLOWING_IRON_BOOTS);
        MorePlanetsMod.initClient();
    }
}