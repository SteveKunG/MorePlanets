package com.stevekung.moreplanets.core;

import com.stevekung.moreplanets.client.renderer.blockentity.DarkEnergyCoreRenderer;
import com.stevekung.moreplanets.world.item.MPItems;
import com.stevekung.moreplanets.world.level.block.MPBlocks;
import com.stevekung.moreplanets.world.level.block.MPSoundEvents;
import com.stevekung.moreplanets.world.level.block.entity.MPBlockEntities;
import com.stevekung.stevekungslib.utils.CommonRegistryUtils;
import com.stevekung.stevekungslib.utils.LoggerBase;
import me.shedaniel.architectury.event.events.TextureStitchEvent;
import me.shedaniel.architectury.registry.BlockEntityRenderers;
import me.shedaniel.architectury.registry.CreativeTabs;
import me.shedaniel.architectury.registry.RenderTypes;
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
        MPBlockEntities.init();
        MPSoundEvents.init();
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
        RenderTypes.register(RenderType.translucent(), MPBlocks.DARK_CRYSTAL_LANTERN);
        RenderTypes.register(RenderType.cutout(), MPBlocks.DARK_ENERGY_CORE);
        RenderTypes.register(RenderType.cutout(), MPBlocks.ZELIUS_EGG);
        RenderTypes.register(RenderType.cutout(), MPBlocks.DARK_ENERGY_GENERATOR);

        BlockEntityRenderers.registerRenderer(MPBlockEntities.DARK_ENERGY_CORE, DarkEnergyCoreRenderer::new);

        TextureStitchEvent.PRE.register((atlas, consumer) -> consumer.accept(new ResourceLocation(MOD_ID, "entity/dark_energy_ball")));
        TextureStitchEvent.PRE.register((atlas, consumer) -> consumer.accept(new ResourceLocation(MOD_ID, "entity/dark_energy_egg")));
    }
}