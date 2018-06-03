package stevekung.mods.stevekunglib.utils;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraftforge.oredict.OreDictionary;

public class OreDictionaryUtils
{
    public static void registerOreDictionary(String name, Block block)
    {
        OreDictionary.registerOre(name, block);
    }

    public static void registerOreDictionary(String name, Item item)
    {
        OreDictionary.registerOre(name, item);
    }
}
