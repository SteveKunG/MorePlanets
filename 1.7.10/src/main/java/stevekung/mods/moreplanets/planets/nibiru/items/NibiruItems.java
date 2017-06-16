/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 *
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.planets.nibiru.items;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;
import stevekung.mods.moreplanets.core.blocks.BlockDoorMP.DoorType;
import stevekung.mods.moreplanets.core.items.ItemDoorMP;
import stevekung.mods.moreplanets.core.util.RegisterHelper;

public class NibiruItems
{
    public static Item nibiru_item;
    public static Item space_fruits;
    public static Item nibiru_dungeon_key;
    public static Item power_crystal;
    public static Item tier6_rocket;
    public static Item tier7_rocket_module;
    public static Item tier6_rocket_schematic;
    public static Item ancient_dark_door;
    public static Item orange_door;

    public static void init()
    {
        NibiruItems.initItems();
        NibiruItems.registerItems();
    }

    private static void initItems()
    {
        NibiruItems.nibiru_item = new ItemBasicNibiru("nibiru_item");
        NibiruItems.space_fruits = new ItemSpaceFruits("nibiru_food");
        NibiruItems.nibiru_dungeon_key = new ItemNibiruDungeonKey("nibiru_dungeon_key");
        NibiruItems.power_crystal = new ItemPowerCrystal("power_crystal");
        NibiruItems.tier6_rocket = new ItemTier6Rocket("tier_6_rocket");
        NibiruItems.tier7_rocket_module = new ItemTier7RocketModule("tier_7_rocket_module");
        NibiruItems.tier6_rocket_schematic = new ItemTier6Schematic("tier_6_rocket_schematic");
        NibiruItems.ancient_dark_door = new ItemDoorMP("ancient_dark_door", DoorType.ANCIENT_DARK);
        NibiruItems.orange_door = new ItemDoorMP("orange_door", DoorType.ORANGE);
    }

    private static void registerItems()
    {
        RegisterHelper.registerItem(NibiruItems.nibiru_item);
        RegisterHelper.registerItem(NibiruItems.power_crystal);
        RegisterHelper.registerItem(NibiruItems.tier6_rocket_schematic);
        RegisterHelper.registerItem(NibiruItems.tier7_rocket_module);
        RegisterHelper.registerItem(NibiruItems.ancient_dark_door);
        RegisterHelper.registerItem(NibiruItems.orange_door);
        RegisterHelper.registerItem(NibiruItems.space_fruits);
        RegisterHelper.registerItem(NibiruItems.tier6_rocket);
        RegisterHelper.registerItem(NibiruItems.nibiru_dungeon_key);

        OreDictionary.registerOre("ingotNorium", new ItemStack(NibiruItems.nibiru_item, 1, 1));
        OreDictionary.registerOre("compressedNorium", new ItemStack(NibiruItems.nibiru_item, 1, 3));
        OreDictionary.registerOre("compressedRedGem", new ItemStack(NibiruItems.nibiru_item, 1, 2));
    }
}