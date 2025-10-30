package com.stevekung.moreplanets.core.handler;

import com.stevekung.lib.utils.BlockItemRemapper;
import com.stevekung.lib.utils.LoggerSL;
import com.stevekung.moreplanets.core.MorePlanetsMod;
import com.stevekung.moreplanets.init.MPBiomes;
import com.stevekung.moreplanets.init.MPBlocks;
import com.stevekung.moreplanets.init.MPItems;
import com.stevekung.moreplanets.init.MPPotions;
import com.stevekung.moreplanets.planets.diona.entity.*;
import com.stevekung.moreplanets.planets.diona.entity.projectile.EntityInfectedPurloniteArrow;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.potion.Potion;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.EntityEntry;
import net.minecraftforge.fml.common.registry.EntityRegistry;
import net.minecraftforge.registries.IForgeRegistryEntry;

public class MissingMappingHandler
{
    @SubscribeEvent
    public void onBlockMissingMappings(RegistryEvent.MissingMappings<Block> event)
    {
        BlockItemRemapper.remapBlock(event, MorePlanetsMod.MOD_ID, "infected_crystallized_web", MPBlocks.INFECTED_PURLONITE_COBWEB);
        BlockItemRemapper.remapBlock(event, MorePlanetsMod.MOD_ID, "large_infected_crystallized", MPBlocks.INFECTED_PURLONITE_CRYSTAL);

        BlockItemRemapper.remapBlock(event, MorePlanetsMod.MOD_ID, "infected_crystallized_segment", MPBlocks.INFECTED_PURLONITE_SEGMENT);
        BlockItemRemapper.remapBlock(event, MorePlanetsMod.MOD_ID, "infected_crystallized_eye_core", MPBlocks.INFECTED_PURLONITE_EYE_CORE);
        BlockItemRemapper.remapBlock(event, MorePlanetsMod.MOD_ID, "infected_crystallized_ender_core", MPBlocks.INFECTED_PURLONITE_ENDER_CORE);
        BlockItemRemapper.remapBlock(event, MorePlanetsMod.MOD_ID, "infected_crystallized_planks", MPBlocks.INFECTED_PURLONITE_PLANKS);
        BlockItemRemapper.remapBlock(event, MorePlanetsMod.MOD_ID, "infected_crystallized_fence", MPBlocks.INFECTED_PURLONITE_FENCE);
        BlockItemRemapper.remapBlock(event, MorePlanetsMod.MOD_ID, "infected_crystallized_cobweb", MPBlocks.INFECTED_PURLONITE_COBWEB);
        BlockItemRemapper.remapBlock(event, MorePlanetsMod.MOD_ID, "infected_crystallized_torch", MPBlocks.INFECTED_PURLONITE_TORCH);
        BlockItemRemapper.remapBlock(event, MorePlanetsMod.MOD_ID, "infected_crystallized_crystal", MPBlocks.INFECTED_PURLONITE_CRYSTAL);
        BlockItemRemapper.remapBlock(event, MorePlanetsMod.MOD_ID, "infected_crystallized_slime_block", MPBlocks.INFECTED_PURLONITE_SLIME_BLOCK);
        BlockItemRemapper.remapBlock(event, MorePlanetsMod.MOD_ID, "crystallized_water_fluid", MPBlocks.INFECTED_PURLONITE_WATER_FLUID_BLOCK);
        BlockItemRemapper.remapBlock(event, MorePlanetsMod.MOD_ID, "crystallized_lava_fluid", MPBlocks.INFECTED_PURLONITE_LAVA_FLUID_BLOCK);
    }

    @SubscribeEvent
    public void onItemMissingMappings(RegistryEvent.MissingMappings<Item> event)
    {
        BlockItemRemapper.remapItem(event, MorePlanetsMod.MOD_ID, "infected_crystallized_web", MPBlocks.INFECTED_PURLONITE_COBWEB);
        BlockItemRemapper.remapItem(event, MorePlanetsMod.MOD_ID, "large_infected_crystallized", MPBlocks.INFECTED_PURLONITE_CRYSTAL);

        BlockItemRemapper.remapItem(event, MorePlanetsMod.MOD_ID, "infected_crystallized_segment", MPBlocks.INFECTED_PURLONITE_SEGMENT);
        BlockItemRemapper.remapItem(event, MorePlanetsMod.MOD_ID, "infected_crystallized_eye_core", MPBlocks.INFECTED_PURLONITE_EYE_CORE);
        BlockItemRemapper.remapItem(event, MorePlanetsMod.MOD_ID, "infected_crystallized_ender_core", MPBlocks.INFECTED_PURLONITE_ENDER_CORE);
        BlockItemRemapper.remapItem(event, MorePlanetsMod.MOD_ID, "infected_crystallized_planks", MPBlocks.INFECTED_PURLONITE_PLANKS);
        BlockItemRemapper.remapItem(event, MorePlanetsMod.MOD_ID, "infected_crystallized_fence", MPBlocks.INFECTED_PURLONITE_FENCE);
        BlockItemRemapper.remapItem(event, MorePlanetsMod.MOD_ID, "infected_crystallized_cobweb", MPBlocks.INFECTED_PURLONITE_COBWEB);
        BlockItemRemapper.remapItem(event, MorePlanetsMod.MOD_ID, "infected_crystallized_torch", MPBlocks.INFECTED_PURLONITE_TORCH);
        BlockItemRemapper.remapItem(event, MorePlanetsMod.MOD_ID, "infected_crystallized_crystal", MPBlocks.INFECTED_PURLONITE_CRYSTAL);
        BlockItemRemapper.remapItem(event, MorePlanetsMod.MOD_ID, "infected_crystallized_slime_block", MPBlocks.INFECTED_PURLONITE_SLIME_BLOCK);
        BlockItemRemapper.remapItem(event, MorePlanetsMod.MOD_ID, "crystallized_water_fluid", MPBlocks.INFECTED_PURLONITE_WATER_FLUID_BLOCK);
        BlockItemRemapper.remapItem(event, MorePlanetsMod.MOD_ID, "crystallized_lava_fluid", MPBlocks.INFECTED_PURLONITE_LAVA_FLUID_BLOCK);

        BlockItemRemapper.remapItem(event, MorePlanetsMod.MOD_ID, "infected_crystallized_laser_bullet", MPItems.INFECTED_PURLONITE_LASER_BULLET);
        BlockItemRemapper.remapItem(event, MorePlanetsMod.MOD_ID, "infected_crystallized_shard", MPItems.INFECTED_PURLONITE_SHARD);
        BlockItemRemapper.remapItem(event, MorePlanetsMod.MOD_ID, "infected_crystallized_bomb", MPItems.INFECTED_PURLONITE_BOMB);
        BlockItemRemapper.remapItem(event, MorePlanetsMod.MOD_ID, "infected_crystallized_slimeball", MPItems.INFECTED_PURLONITE_SLIMEBALL);
        BlockItemRemapper.remapItem(event, MorePlanetsMod.MOD_ID, "infected_crystallized_arrow", MPItems.INFECTED_PURLONITE_ARROW);
    }

    @SubscribeEvent
    public void onPotionMissingMappings(RegistryEvent.MissingMappings<Potion> event)
    {
        remapObject(event, "infected_crystallized", MPPotions.INFECTED_PURLONITE);
    }

    @SubscribeEvent
    public void onEntityMissingMappings(RegistryEvent.MissingMappings<EntityEntry> event)
    {
        remapObject(event, "infected_crystallized_spider", EntityRegistry.getEntry(EntityInfectedPurloniteSpider.class));
        remapObject(event, "infected_crystallized_worm", EntityRegistry.getEntry(EntityInfectedPurloniteWorm.class));
        remapObject(event, "infected_crystallized_slime_boss", EntityRegistry.getEntry(EntityInfectedPurloniteSlimeBoss.class));
        remapObject(event, "infected_crystallized_slime_minion", EntityRegistry.getEntry(EntityInfectedPurloniteSlimeMinion.class));
        remapObject(event, "infected_crystallized_tentacle", EntityRegistry.getEntry(EntityInfectedPurloniteTentacle.class));
        remapObject(event, "infected_crystallized_bomb", EntityRegistry.getEntry(EntityInfectedPurloniteBomb.class));
        remapObject(event, "infected_crystallized_arrow", EntityRegistry.getEntry(EntityInfectedPurloniteArrow.class));
    }

    @SubscribeEvent
    public void onBiomeMissingMappings(RegistryEvent.MissingMappings<Biome> event)
    {
        remapObject(event, "chalos_moutains", MPBiomes.CHALOS_MOUNTAINS);
        remapObject(event, "cold_green_vein_moutains", MPBiomes.COLD_GREEN_VEIN_MOUNTAINS);
    }

    private static <T extends IForgeRegistryEntry<T>> void remapObject(RegistryEvent.MissingMappings<T> event, String oldName, T object)
    {
        event.getMappings().forEach(mappings ->
        {
            if (mappings.key.getNamespace().equals(MorePlanetsMod.MOD_ID) && mappings.key.getPath().equals(oldName))
            {
                mappings.remap(object);
                LoggerSL.info("Remapping from '{}' to '{}'", mappings.key, object.getRegistryName());
            }
        });
    }
}