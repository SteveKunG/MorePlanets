package com.stevekung.moreplanets.core;

import org.slf4j.Logger;

import com.mojang.logging.LogUtils;
import com.stevekung.moreplanets.registry.MPBlockEntities;
import com.stevekung.moreplanets.registry.MPBlocks;
import com.stevekung.moreplanets.registry.MPItems;
import com.stevekung.moreplanets.registry.MPSoundEvents;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;

public class MorePlanets implements ModInitializer
{
    public static final String MOD_ID = "moreplanets";
    public static final Logger LOGGER = LogUtils.getLogger();
    public static final CreativeModeTab TAB = FabricItemGroupBuilder.build(new ResourceLocation(MorePlanets.MOD_ID, "main"), () -> new ItemStack(MPBlocks.DIONA_REGOLITH));

    @Override
    public void onInitialize()
    {
        MPBlocks.init();
        MPItems.init();
        MPBlockEntities.init();
        MPSoundEvents.init();
    }
}