package stevekung.mods.stevekunglib.utils;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraftforge.event.RegistryEvent;

public class BlockItemRemapper
{
    public static void remapBlock(RegistryEvent.MissingMappings<Block> event, String modid, String oldName, Block block)
    {
        event.getMappings().forEach(mappings ->
        {
            if (mappings.key.getResourceDomain().equals(modid) && mappings.key.getResourcePath().equals(oldName))
            {
                mappings.remap(block);
                ModLogger.info("Remapping Block Complete (From {} to {})", mappings.key, block.getRegistryName());
            }
        });
    }

    public static void remapItem(RegistryEvent.MissingMappings<Item> event, String modid, String oldName, Block block)
    {
        event.getMappings().forEach(mappings ->
        {
            if (mappings.key.getResourceDomain().equals(modid) && mappings.key.getResourcePath().equals(oldName))
            {
                mappings.remap(Item.getItemFromBlock(block));
                ModLogger.info("Remapping Block Complete (From {} to {})", mappings.key, block.getRegistryName());
            }
        });
    }

    public static void remapItem(RegistryEvent.MissingMappings<Item> event, String modid, String oldName, Item item)
    {
        event.getMappings().forEach(mappings ->
        {
            if (mappings.key.getResourceDomain().equals(modid) && mappings.key.getResourcePath().equals(oldName))
            {
                mappings.remap(item);
                ModLogger.info("Remapping Block Complete (From {} to {})", mappings.key, item.getRegistryName());
            }
        });
    }
}