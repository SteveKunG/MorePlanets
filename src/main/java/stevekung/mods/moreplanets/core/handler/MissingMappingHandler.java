package stevekung.mods.moreplanets.core.handler;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.potion.Potion;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.EntityEntry;
import net.minecraftforge.fml.common.registry.EntityRegistry;
import stevekung.mods.moreplanets.core.MorePlanetsMod;
import stevekung.mods.moreplanets.init.MPBlocks;
import stevekung.mods.moreplanets.init.MPItems;
import stevekung.mods.moreplanets.init.MPPotions;
import stevekung.mods.moreplanets.planets.diona.entity.*;
import stevekung.mods.moreplanets.planets.diona.entity.projectile.EntityInfectedPurloniteArrow;
import stevekung.mods.stevekunglib.utils.BlockItemRemapper;
import stevekung.mods.stevekunglib.utils.LoggerSL;

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
        MissingMappingHandler.remapPotion(event, MorePlanetsMod.MOD_ID, "infected_crystallized", MPPotions.INFECTED_PURLONITE);
    }

    @SubscribeEvent
    public void onEntityMissingMappings(RegistryEvent.MissingMappings<EntityEntry> event)
    {
        MissingMappingHandler.remapEntity(event, MorePlanetsMod.MOD_ID, "infected_crystallized_spider", EntityRegistry.getEntry(EntityInfectedPurloniteSpider.class));
        MissingMappingHandler.remapEntity(event, MorePlanetsMod.MOD_ID, "infected_crystallized_worm", EntityRegistry.getEntry(EntityInfectedPurloniteWorm.class));
        MissingMappingHandler.remapEntity(event, MorePlanetsMod.MOD_ID, "infected_crystallized_slime_boss", EntityRegistry.getEntry(EntityInfectedPurloniteSlimeBoss.class));
        MissingMappingHandler.remapEntity(event, MorePlanetsMod.MOD_ID, "infected_crystallized_slime_minion", EntityRegistry.getEntry(EntityInfectedPurloniteSlimeMinion.class));
        MissingMappingHandler.remapEntity(event, MorePlanetsMod.MOD_ID, "infected_crystallized_tentacle", EntityRegistry.getEntry(EntityInfectedPurloniteTentacle.class));
        MissingMappingHandler.remapEntity(event, MorePlanetsMod.MOD_ID, "infected_crystallized_bomb", EntityRegistry.getEntry(EntityInfectedPurloniteBomb.class));
        MissingMappingHandler.remapEntity(event, MorePlanetsMod.MOD_ID, "infected_crystallized_arrow", EntityRegistry.getEntry(EntityInfectedPurloniteArrow.class));
    }

    private static void remapPotion(RegistryEvent.MissingMappings<Potion> event, String modid, String oldName, Potion potion)
    {
        event.getMappings().forEach(mappings ->
        {
            if (mappings.key.getNamespace().equals(modid) && mappings.key.getPath().equals(oldName))
            {
                mappings.remap(potion);
                LoggerSL.info("Remapping 'Potion' from {} to {}", mappings.key, potion.getRegistryName());
            }
        });
    }

    private static void remapEntity(RegistryEvent.MissingMappings<EntityEntry> event, String modid, String oldName, EntityEntry entity)
    {
        event.getMappings().forEach(mappings ->
        {
            if (mappings.key.getNamespace().equals(modid) && mappings.key.getPath().equals(oldName))
            {
                mappings.remap(entity);
                LoggerSL.info("Remapping 'Entity' from {} to {}", mappings.key, entity.getRegistryName());
            }
        });
    }
}