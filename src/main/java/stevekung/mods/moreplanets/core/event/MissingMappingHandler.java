package stevekung.mods.moreplanets.core.event;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import stevekung.mods.moreplanets.module.planets.chalos.blocks.ChalosBlocks;
import stevekung.mods.moreplanets.util.BlockItemRemapper;

public class MissingMappingHandler
{
    @SubscribeEvent
    public void onBlockMissingMappings(RegistryEvent.MissingMappings<Block> event)
    {
        BlockItemRemapper.remapBlock(event, "cheese_double_tall_grass", ChalosBlocks.CHALOS_DOUBLE_PLANT);
    }

    @SubscribeEvent
    public void onItemMissingMappings(RegistryEvent.MissingMappings<Item> event)
    {
        BlockItemRemapper.remapItem(event, "cheese_double_tall_grass", ChalosBlocks.CHALOS_DOUBLE_PLANT);
    }
}