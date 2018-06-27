package stevekung.mods.moreplanets.core.event;

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
    }

    @SubscribeEvent
    public void onItemMissingMappings(RegistryEvent.MissingMappings<Item> event)
    {
        BlockItemRemapper.remapItem(event, MorePlanetsMod.MOD_ID, "infected_crystallized_web", MPBlocks.INFECTED_CRYSTALLIZED_COBWEB);
    }
}