package com.stevekung.moreplanets.core;

import com.stevekung.moreplanets.proxy.ClientProxyMP;
import com.stevekung.moreplanets.proxy.CommonProxyMP;
import com.stevekung.moreplanets.world.item.MPItems;
import com.stevekung.moreplanets.world.level.block.MPBlocks;
import com.stevekung.stevekungslib.utils.CommonRegistryUtils;
import com.stevekung.stevekungslib.utils.LoggerBase;
import me.shedaniel.architectury.registry.CreativeTabs;
import me.shedaniel.architectury.registry.RenderTypes;
import me.shedaniel.architectury.utils.EnvExecutor;
import net.minecraft.client.renderer.RenderType;
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
        MPItems.init();
    }

    public static void initClient()
    {
        RenderTypes.register(RenderType.translucent(), MPBlocks.PURLONITE_BLOCK);
        RenderTypes.register(RenderType.translucent(), MPBlocks.BUDDING_PURLONITE);
        RenderTypes.register(RenderType.translucent(), MPBlocks.PURLONITE_CLUSTER);//TODO Fix render type
        RenderTypes.register(RenderType.translucent(), MPBlocks.LARGE_PURLONITE_BUD);
        RenderTypes.register(RenderType.translucent(), MPBlocks.MEDIUM_PURLONITE_BUD);
        RenderTypes.register(RenderType.translucent(), MPBlocks.SMALL_PURLONITE_BUD);
        RenderTypes.register(RenderType.translucent(), MPBlocks.PURLONITE_CRYSTAL_LANTERN);
    }
}