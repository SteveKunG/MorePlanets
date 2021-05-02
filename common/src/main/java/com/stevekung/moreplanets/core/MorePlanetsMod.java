package com.stevekung.moreplanets.core;

import com.stevekung.moreplanets.world.level.block.MPBlocks;
import com.stevekung.stevekungslib.utils.CommonRegistryUtils;
import com.stevekung.stevekungslib.utils.LoggerBase;
import me.shedaniel.architectury.registry.CreativeTabs;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;

public class MorePlanetsMod
{
    public static final String MOD_ID = "moreplanets";
    public static final LoggerBase LOGGER = new LoggerBase("MorePlanets");
    public static final CommonRegistryUtils COMMON = new CommonRegistryUtils(MOD_ID);

    public static final CreativeModeTab TAB = CreativeTabs.create(new ResourceLocation(MOD_ID, "main"), () -> new ItemStack(MPBlocks.DIONA_REGOLITH));

    public static void init()
    {
        COMMON.registerAll();
        MPBlocks.init();
    }
}