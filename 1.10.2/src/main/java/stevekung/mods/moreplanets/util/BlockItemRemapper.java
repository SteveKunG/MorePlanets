package stevekung.mods.moreplanets.util;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraftforge.fml.common.event.FMLMissingMappingsEvent.MissingMapping;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class BlockItemRemapper
{
    public static void remapBlock(MissingMapping mapping, String oldName, Block block)
    {
        if (mapping.name.equals("moreplanets:" + oldName))
        {
            if (mapping.type == GameRegistry.Type.BLOCK)
            {
                mapping.remap(block);
                MPLog.info("Remapping Block Complete (From %s to %s)", mapping.name, block.getRegistryName());
            }
            else
            {
                Item item = Item.getItemFromBlock(block);
                mapping.remap(item);
                MPLog.info("Remapping ItemBlock Complete (From %s to %s)", mapping.name, item.getRegistryName());
            }
        }
    }
}