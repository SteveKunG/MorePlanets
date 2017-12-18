/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 *
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.planets.diona.items;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;
import stevekung.mods.moreplanets.core.util.RegisterHelper;

public class DionaItems
{
    public static Item diona_item;
    public static Item tier4_rocket_schematic;
    public static Item tier4_rocket_module;
    public static Item laser_gun;
    public static Item laser_charge;
    public static Item tier4_rocket;
    public static Item diona_dungeon_key;

    public static void init()
    {
        DionaItems.initItems();
        DionaItems.registerItems();
    }

    private static void initItems()
    {
        DionaItems.diona_item = new ItemBasicDiona("diona_item");
        DionaItems.tier4_rocket_schematic = new ItemTier4Schematic("tier_4_rocket_schematic");
        DionaItems.tier4_rocket_module = new ItemTier4RocketModule("tier_4_rocket_module");
        DionaItems.laser_gun = new ItemLaserGun("laser_gun");
        DionaItems.laser_charge = new ItemLaserCharge("laser_charge");
        DionaItems.tier4_rocket = new ItemTier4Rocket("tier_4_rocket");
        DionaItems.diona_dungeon_key = new ItemDionaDungeonKey("diona_treasure_key");
    }

    private static void registerItems()
    {
        RegisterHelper.registerItem(DionaItems.diona_item);
        RegisterHelper.registerItem(DionaItems.tier4_rocket_schematic);
        RegisterHelper.registerItem(DionaItems.tier4_rocket_module);
        RegisterHelper.registerItem(DionaItems.laser_gun);
        RegisterHelper.registerItem(DionaItems.laser_charge);
        RegisterHelper.registerItem(DionaItems.tier4_rocket);
        RegisterHelper.registerItem(DionaItems.diona_dungeon_key);

        OreDictionary.registerOre("ingotQuontonium", new ItemStack(DionaItems.diona_item, 1, 0));
        OreDictionary.registerOre("ingotFronisium", new ItemStack(DionaItems.diona_item, 1, 1));
        OreDictionary.registerOre("compressedQuontonium", new ItemStack(DionaItems.diona_item, 1, 2));
        OreDictionary.registerOre("compressedFronisium", new ItemStack(DionaItems.diona_item, 1, 3));
    }
}