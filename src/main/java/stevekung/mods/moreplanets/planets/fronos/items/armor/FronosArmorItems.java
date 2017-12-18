/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 *
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.planets.fronos.items.armor;

import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor.ArmorMaterial;
import net.minecraftforge.common.util.EnumHelper;
import stevekung.mods.moreplanets.core.util.RegisterHelper;

public class FronosArmorItems
{
    public static Item black_diamond_helmet;
    public static Item black_diamond_chestplate;
    public static Item black_diamond_leggings;
    public static Item black_diamond_boots;
    public static Item iridium_helmet;
    public static Item iridium_chestplate;
    public static Item iridium_leggings;
    public static Item iridium_boots;
    public static Item breathable_black_diamond_helmet;
    public static Item breathable_iridium_helmet;

    /**Name,Durability,ReductionAmounts[Helm,Chest,Leg,Boot],Enchantability**/
    public static ArmorMaterial black_diamond = EnumHelper.addArmorMaterial("black_diamond", 44, new int[] { 12, 15, 12, 9 }, 16);
    public static ArmorMaterial iridium = EnumHelper.addArmorMaterial("iridium", 42, new int[] { 11, 14, 11, 8 }, 16);

    public static void init()
    {
        FronosArmorItems.initItems();
        FronosArmorItems.registerItems();
    }

    private static void initItems()
    {
        FronosArmorItems.black_diamond_helmet = new ItemArmorBlackDiamond("black_diamond_helmet", FronosArmorItems.black_diamond, 7, 0);
        FronosArmorItems.black_diamond_chestplate = new ItemArmorBlackDiamond("black_diamond_chestplate", FronosArmorItems.black_diamond, 7, 1);
        FronosArmorItems.black_diamond_leggings = new ItemArmorBlackDiamond("black_diamond_leggings", FronosArmorItems.black_diamond, 7, 2);
        FronosArmorItems.black_diamond_boots = new ItemArmorBlackDiamond("black_diamond_boots", FronosArmorItems.black_diamond, 7, 3);
        FronosArmorItems.iridium_helmet = new ItemArmorIridium("iridium_helmet", FronosArmorItems.iridium, 7, 0);
        FronosArmorItems.iridium_chestplate = new ItemArmorIridium("iridium_chestplate", FronosArmorItems.iridium, 7, 1);
        FronosArmorItems.iridium_leggings = new ItemArmorIridium("iridium_leggings", FronosArmorItems.iridium, 7, 2);
        FronosArmorItems.iridium_boots = new ItemArmorIridium("iridium_boots", FronosArmorItems.iridium, 7, 3);
        FronosArmorItems.breathable_black_diamond_helmet = new ItemBreathableBlackDiamond("breathable_black_diamond_helmet", FronosArmorItems.black_diamond, 7, 0);
        FronosArmorItems.breathable_iridium_helmet = new ItemBreathableIridium("breathable_iridium_helmet", FronosArmorItems.iridium, 7, 0);
    }

    private static void registerItems()
    {
        RegisterHelper.registerItem(FronosArmorItems.black_diamond_helmet);
        RegisterHelper.registerItem(FronosArmorItems.black_diamond_chestplate);
        RegisterHelper.registerItem(FronosArmorItems.black_diamond_leggings);
        RegisterHelper.registerItem(FronosArmorItems.black_diamond_boots);
        RegisterHelper.registerItem(FronosArmorItems.iridium_helmet);
        RegisterHelper.registerItem(FronosArmorItems.iridium_chestplate);
        RegisterHelper.registerItem(FronosArmorItems.iridium_leggings);
        RegisterHelper.registerItem(FronosArmorItems.iridium_boots);
        RegisterHelper.registerItem(FronosArmorItems.breathable_black_diamond_helmet);
        RegisterHelper.registerItem(FronosArmorItems.breathable_iridium_helmet);
    }
}