package com.stevekung.moreplanets.core;

import com.stevekung.moreplanets.world.item.MPItems;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.ArmorRenderingRegistry;
import net.minecraft.resources.ResourceLocation;

public class MorePlanetsFabricClientMod implements ClientModInitializer
{
    @Override
    public void onInitializeClient()
    {
        ArmorRenderingRegistry.registerSimpleTexture(new ResourceLocation(MorePlanetsMod.MOD_ID, "glowing_iron"), MPItems.GLOWING_IRON_HELMET, MPItems.GLOWING_IRON_CHESTPLATE);
        ArmorRenderingRegistry.registerSimpleTexture(new ResourceLocation(MorePlanetsMod.MOD_ID, "glowing_iron"), MPItems.GLOWING_IRON_LEGGINGS, MPItems.GLOWING_IRON_BOOTS);
        MorePlanetsMod.initClient();
    }
}