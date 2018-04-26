package stevekung.mods.moreplanets.util;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraftforge.event.RegistryEvent;
import stevekung.mods.moreplanets.core.MorePlanetsMod;

public class BlockItemRemapper
{
    public static void remapBlock(RegistryEvent.MissingMappings<Block> event, String oldName, Block block)
    {
        for (RegistryEvent.MissingMappings.Mapping<Block> mappings : event.getMappings())
        {
            if (mappings.key.getResourceDomain().equals(MorePlanetsMod.MOD_ID) && mappings.key.getResourcePath().equals(oldName))
            {
                mappings.remap(block);
                MPLog.info("Remapping Block Complete (From {} to {})", mappings.key, block.getRegistryName());
            }
        }
    }

    public static void remapItem(RegistryEvent.MissingMappings<Item> event, String oldName, Block block)
    {
        for (RegistryEvent.MissingMappings.Mapping<Item> mappings : event.getMappings())
        {
            if (mappings.key.getResourceDomain().equals(MorePlanetsMod.MOD_ID) && mappings.key.getResourcePath().equals(oldName))
            {
                mappings.remap(Item.getItemFromBlock(block));
                MPLog.info("Remapping Block Complete (From {} to {})", mappings.key, block.getRegistryName());
            }
        }
    }

    public static void remapItem(RegistryEvent.MissingMappings<Item> event, String oldName, Item item)
    {
        for (RegistryEvent.MissingMappings.Mapping<Item> mappings : event.getMappings())
        {
            if (mappings.key.getResourceDomain().equals(MorePlanetsMod.MOD_ID) && mappings.key.getResourcePath().equals(oldName))
            {
                mappings.remap(item);
                MPLog.info("Remapping Block Complete (From {} to {})", mappings.key, item.getRegistryName());
            }
        }
    }
}