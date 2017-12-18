/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 *
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.planets.siriusb.items.armor;

import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor.ArmorMaterial;
import net.minecraftforge.common.util.EnumHelper;
import stevekung.mods.moreplanets.core.util.RegisterHelper;

public class SiriusBArmorItems
{
    public static Item sulfur_helmet;
    public static Item sulfur_chestplate;
    public static Item sulfur_leggings;
    public static Item sulfur_boots;
    public static Item breathable_sulfur_helmet;

    // Name,Durability,ReductionAmounts[Helm,Chest,Leg,Boot],Enchantability
    public static ArmorMaterial sulfur = EnumHelper.addArmorMaterial("sulfur", 26, new int[] { 6, 9, 8, 4 }, 16);

    public static void init()
    {
        SiriusBArmorItems.initItems();
        SiriusBArmorItems.registerItems();
    }

    private static void initItems()
    {
        SiriusBArmorItems.sulfur_helmet = new ArmorSulfur("sulfur_helmet", SiriusBArmorItems.sulfur, 7, 0);
        SiriusBArmorItems.sulfur_chestplate = new ArmorSulfur("sulfur_chestplate", SiriusBArmorItems.sulfur, 7, 1);
        SiriusBArmorItems.sulfur_leggings = new ArmorSulfur("sulfur_leggings", SiriusBArmorItems.sulfur, 7, 2);
        SiriusBArmorItems.sulfur_boots = new ArmorSulfur("sulfur_boots", SiriusBArmorItems.sulfur, 7, 3);
        SiriusBArmorItems.breathable_sulfur_helmet = new ArmorBreathableSulfur("breathable_sulfur_helmet", SiriusBArmorItems.sulfur, 7, 0);
    }

    private static void registerItems()
    {
        RegisterHelper.registerItem(SiriusBArmorItems.sulfur_helmet);
        RegisterHelper.registerItem(SiriusBArmorItems.sulfur_chestplate);
        RegisterHelper.registerItem(SiriusBArmorItems.sulfur_leggings);
        RegisterHelper.registerItem(SiriusBArmorItems.sulfur_boots);
        RegisterHelper.registerItem(SiriusBArmorItems.breathable_sulfur_helmet);
    }
}