/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 *
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.planets.venus.items;

import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor.ArmorMaterial;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.util.EnumHelper;
import net.minecraftforge.oredict.OreDictionary;
import stevekung.mods.moreplanets.core.util.RegisterHelper;

public class VenusItems
{
    public static Item venus_item;
    public static Item sulfur_battery;
    public static Item jetpack;
    public static Item venus_dungeon_key;

    public static ArmorMaterial jetpackArmor = EnumHelper.addArmorMaterial("jetpack", 0, new int[] { 0, 0, 0, 0 }, 0);

    public static void init()
    {
        VenusItems.initItems();
        VenusItems.registerItems();
    }

    private static void initItems()
    {
        VenusItems.venus_item = new ItemBasicVenus("venus_item");
        VenusItems.sulfur_battery = new ItemSulfurBattery("sulfur_battery");
        VenusItems.jetpack = new ItemJetpack("jetpack", VenusItems.jetpackArmor, 7, 1);
        VenusItems.venus_dungeon_key = new ItemVenusDungeonKey("venus_dungeon_key");
    }

    private static void registerItems()
    {
        RegisterHelper.registerItem(VenusItems.venus_item);
        RegisterHelper.registerItem(VenusItems.sulfur_battery);
        RegisterHelper.registerItem(VenusItems.jetpack);
        RegisterHelper.registerItem(VenusItems.venus_dungeon_key);

        OreDictionary.registerOre("ingotLead", new ItemStack(VenusItems.venus_item, 1, 0));
    }
}