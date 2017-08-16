/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 *
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.planets.siriusb.items;

import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;
import stevekung.mods.moreplanets.core.util.RegisterHelper;
import stevekung.mods.moreplanets.planets.siriusb.blocks.SiriusBBlocks;

public class SiriusBItems
{
    public static Item sirius_b_item;
    public static Item sirius_b_dungeon_key;
    public static Item sirius_fire_charge;
    public static Item sirius_obsidian_bucket;
    public static Item sirius_lava_bucket;
    public static Item sirius_glowstone_dust;

    public static void init()
    {
        SiriusBItems.initItems();
        SiriusBItems.registerItems();
        SiriusBItems.registerFluidContainer();
    }

    private static void initItems()
    {
        SiriusBItems.sirius_b_item = new ItemBasicSiriusB("sirius-b_item");
        SiriusBItems.sirius_b_dungeon_key = new ItemSiriusBDungeonKey("sirius-b_dungeon_key");
        SiriusBItems.sirius_fire_charge = new ItemSiriusFireCharge("sirius_fire_charge");
        SiriusBItems.sirius_obsidian_bucket = new ItemSiriusObsidianBucket(Blocks.air, "sirius_obsidian_bucket", "siriusb:sirius_obsidian_bucket");
        SiriusBItems.sirius_lava_bucket = new ItemSiriusObsidianBucket(SiriusBBlocks.sirius_lava, "sirius_lava_bucket", "siriusb:sirius_lava_bucket");
        SiriusBItems.sirius_glowstone_dust = new ItemSiriusGlowstoneDust("sirius_glowstone_dust");
    }

    private static void registerItems()
    {
        RegisterHelper.registerItem(SiriusBItems.sirius_b_item);
        RegisterHelper.registerItem(SiriusBItems.sirius_glowstone_dust);
        RegisterHelper.registerItem(SiriusBItems.sirius_fire_charge);
        RegisterHelper.registerItem(SiriusBItems.sirius_obsidian_bucket);
        RegisterHelper.registerItem(SiriusBItems.sirius_lava_bucket);
        RegisterHelper.registerItem(SiriusBItems.sirius_b_dungeon_key);

        OreDictionary.registerOre("ingotSulfur", new ItemStack(SiriusBItems.sirius_b_item, 1, 3));
        OreDictionary.registerOre("compressedSulfur", new ItemStack(SiriusBItems.sirius_b_item, 1, 4));
    }

    private static void registerFluidContainer()
    {
        RegisterHelper.registerFluidContainer(SiriusBBlocks.sirius_lava_fluid, new ItemStack(SiriusBItems.sirius_lava_bucket), new ItemStack(SiriusBItems.sirius_obsidian_bucket));
    }
}