/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 *
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.planets.diona.items.armor;

import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor.ArmorMaterial;
import net.minecraftforge.common.util.EnumHelper;
import stevekung.mods.moreplanets.core.util.RegisterHelper;

public class DionaArmorItems
{
    public static Item quontonium_helmet;
    public static Item quontonium_chestplate;
    public static Item quontonium_leggings;
    public static Item quontonium_boots;
    public static Item fronisium_helmet;
    public static Item fronisium_chestplate;
    public static Item fronisium_leggings;
    public static Item fronisium_boots;
    public static Item breathable_quontonium_helmet;
    public static Item breathable_fronisium_helmet;

    // Name,Durability,ReductionAmounts[Helm,Chest,Leg,Boot],Enchantability
    public static ArmorMaterial quontonium = EnumHelper.addArmorMaterial("quontonium", 32, new int[] { 8, 12, 11, 6 }, 16);
    public static ArmorMaterial fronisium = EnumHelper.addArmorMaterial("fronisium", 32, new int[] { 9, 13, 10, 7 }, 16);

    public static void init()
    {
        DionaArmorItems.initItems();
        DionaArmorItems.registerItems();
    }

    private static void initItems()
    {
        DionaArmorItems.quontonium_helmet = new ItemArmorQuontonium("quontonium_helmet", DionaArmorItems.quontonium, 7, 0);
        DionaArmorItems.quontonium_chestplate = new ItemArmorQuontonium("quontonium_chestplate", DionaArmorItems.quontonium, 7, 1);
        DionaArmorItems.quontonium_leggings = new ItemArmorQuontonium("quontonium_leggings", DionaArmorItems.quontonium, 7, 2);
        DionaArmorItems.quontonium_boots = new ItemArmorQuontonium("quontonium_boots", DionaArmorItems.quontonium, 7, 3);
        DionaArmorItems.fronisium_helmet = new ItemArmorFronisium("fronisium_helmet", DionaArmorItems.fronisium, 7, 0);
        DionaArmorItems.fronisium_chestplate = new ItemArmorFronisium("fronisium_chestplate", DionaArmorItems.fronisium, 7, 1);
        DionaArmorItems.fronisium_leggings = new ItemArmorFronisium("fronisium_leggings", DionaArmorItems.fronisium, 7, 2);
        DionaArmorItems.fronisium_boots = new ItemArmorFronisium("fronisium_boots", DionaArmorItems.fronisium, 7, 3);
        DionaArmorItems.breathable_quontonium_helmet = new ItemBreathableQuontonium("breathable_quontonium_helmet", DionaArmorItems.quontonium, 7, 0);
        DionaArmorItems.breathable_fronisium_helmet = new ItemBreathableFronisium("breathable_fronisium_helmet", DionaArmorItems.fronisium, 7, 0);
    }

    private static void registerItems()
    {
        RegisterHelper.registerItem(DionaArmorItems.quontonium_helmet);
        RegisterHelper.registerItem(DionaArmorItems.quontonium_chestplate);
        RegisterHelper.registerItem(DionaArmorItems.quontonium_leggings);
        RegisterHelper.registerItem(DionaArmorItems.quontonium_boots);
        RegisterHelper.registerItem(DionaArmorItems.fronisium_helmet);
        RegisterHelper.registerItem(DionaArmorItems.fronisium_chestplate);
        RegisterHelper.registerItem(DionaArmorItems.fronisium_leggings);
        RegisterHelper.registerItem(DionaArmorItems.fronisium_boots);
        RegisterHelper.registerItem(DionaArmorItems.breathable_quontonium_helmet);
        RegisterHelper.registerItem(DionaArmorItems.breathable_fronisium_helmet);
    }
}