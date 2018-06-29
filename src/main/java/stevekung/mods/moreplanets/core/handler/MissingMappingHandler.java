package stevekung.mods.moreplanets.core.handler;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import stevekung.mods.moreplanets.core.MorePlanetsMod;
import stevekung.mods.moreplanets.init.MPBlocks;
import stevekung.mods.stevekunglib.utils.BlockItemRemapper;

public class MissingMappingHandler
{
    @SubscribeEvent
    public void onBlockMissingMappings(RegistryEvent.MissingMappings<Block> event)
    {
        BlockItemRemapper.remapBlock(event, MorePlanetsMod.MOD_ID, "infected_crystallized_web", MPBlocks.INFECTED_CRYSTALLIZED_COBWEB);
        BlockItemRemapper.remapBlock(event, MorePlanetsMod.MOD_ID, "large_infected_crystallized", MPBlocks.INFECTED_CRYSTALLIZED_CRYSTAL);
    }

    @SubscribeEvent
    public void onItemMissingMappings(RegistryEvent.MissingMappings<Item> event)
    {
        BlockItemRemapper.remapItem(event, MorePlanetsMod.MOD_ID, "infected_crystallized_web", MPBlocks.INFECTED_CRYSTALLIZED_COBWEB);
        BlockItemRemapper.remapItem(event, MorePlanetsMod.MOD_ID, "large_infected_crystallized", MPBlocks.INFECTED_CRYSTALLIZED_CRYSTAL);
    }
}