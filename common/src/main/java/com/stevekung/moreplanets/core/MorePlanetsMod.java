package com.stevekung.moreplanets.core;

import com.stevekung.moreplanets.client.renderer.blockentity.DarkEnergyCoreRenderer;
import com.stevekung.moreplanets.world.item.MPItems;
import com.stevekung.moreplanets.world.level.block.MPBlocks;
import com.stevekung.moreplanets.world.level.block.MPSoundEvents;
import com.stevekung.moreplanets.world.level.block.entity.MPBlockEntities;
import com.stevekung.stevekunglib.utils.CommonRegistryUtils;
import com.stevekung.stevekunglib.utils.LoggerBase;
import com.stevekung.stevekunglib.utils.client.ClientRegistryUtils;
import dev.architectury.event.events.client.ClientTextureStitchEvent;
import dev.architectury.registry.CreativeTabRegistry;
import dev.architectury.registry.client.rendering.RenderTypeRegistry;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;

public class MorePlanetsMod
{
    public static final String MOD_ID = "moreplanets";
    public static final LoggerBase LOGGER = new LoggerBase("MorePlanets");
    public static final CommonRegistryUtils COMMON = new CommonRegistryUtils(MOD_ID);

    public static final CreativeModeTab TAB = CreativeTabRegistry.create(new ResourceLocation(MOD_ID, "main"), () -> new ItemStack(MPBlocks.DIONA_REGOLITH));

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
        RenderTypeRegistry.register(RenderType.translucent(), MPBlocks.PURLONITE_BLOCK);
        RenderTypeRegistry.register(RenderType.translucent(), MPBlocks.BUDDING_PURLONITE);
        RenderTypeRegistry.register(RenderType.translucent(), MPBlocks.PURLONITE_CLUSTER);//TODO Fix render type
        RenderTypeRegistry.register(RenderType.translucent(), MPBlocks.LARGE_PURLONITE_BUD);
        RenderTypeRegistry.register(RenderType.translucent(), MPBlocks.MEDIUM_PURLONITE_BUD);
        RenderTypeRegistry.register(RenderType.translucent(), MPBlocks.SMALL_PURLONITE_BUD);
        RenderTypeRegistry.register(RenderType.translucent(), MPBlocks.PURLONITE_CRYSTAL_LANTERN);
        RenderTypeRegistry.register(RenderType.translucent(), MPBlocks.DARK_CRYSTAL_LANTERN);
        RenderTypeRegistry.register(RenderType.translucent(), MPBlocks.COMPACTED_PURLONITE_BLOCK);
        RenderTypeRegistry.register(RenderType.cutout(), MPBlocks.DARK_ENERGY_CORE);
        RenderTypeRegistry.register(RenderType.cutout(), MPBlocks.ZELIUS_EGG);
        RenderTypeRegistry.register(RenderType.cutout(), MPBlocks.DARK_ENERGY_GENERATOR);
        RenderTypeRegistry.register(RenderType.cutout(), MPBlocks.DISPLAY_JAR);
        RenderTypeRegistry.register(RenderType.cutout(), MPBlocks.PURLONITE_WORM_JAR);
        RenderTypeRegistry.register(RenderType.cutout(), MPBlocks.CHALOS_SPORE_JAR);

        ClientRegistryUtils.registerTileEntityRendering(MPBlockEntities.DARK_ENERGY_CORE, DarkEnergyCoreRenderer::new);

        ClientTextureStitchEvent.PRE.register((atlas, consumer) -> consumer.accept(new ResourceLocation(MOD_ID, "entity/dark_energy_ball")));
        ClientTextureStitchEvent.PRE.register((atlas, consumer) -> consumer.accept(new ResourceLocation(MOD_ID, "entity/dark_energy_egg")));
    }
}