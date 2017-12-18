/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 *
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.planets.mercury.items;

import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;
import stevekung.mods.moreplanets.core.util.RegisterHelper;
import stevekung.mods.moreplanets.planets.mercury.blocks.MercuryBlocks;

public class MercuryItems
{
    public static Item mercury_item;
    public static Item dirty_water_bucket;

    public static void init()
    {
        MercuryItems.initItems();
        MercuryItems.registerItems();
        MercuryItems.registerFluidContainer();
    }

    private static void initItems()
    {
        MercuryItems.mercury_item = new ItemBasicMercury("mercury_item");
        MercuryItems.dirty_water_bucket = new ItemDirtyWaterBucket("dirty_water_bucket");
    }

    private static void registerItems()
    {
        RegisterHelper.registerItem(MercuryItems.mercury_item);
        RegisterHelper.registerItem(MercuryItems.dirty_water_bucket);

        OreDictionary.registerOre("ingotMetallic", new ItemStack(MercuryItems.mercury_item, 1, 2));
        OreDictionary.registerOre("ingotMetalMeteoricIron", new ItemStack(MercuryItems.mercury_item, 1, 3));
        OreDictionary.registerOre("compressedMetallic", new ItemStack(MercuryItems.mercury_item, 1, 4));
        OreDictionary.registerOre("compressedMetalMeteoricIron", new ItemStack(MercuryItems.mercury_item, 1, 5));
    }

    private static void registerFluidContainer()
    {
        RegisterHelper.registerFluidContainer(MercuryBlocks.dirty_water_fluid, new ItemStack(MercuryItems.dirty_water_bucket), new ItemStack(Items.bucket));
    }
}