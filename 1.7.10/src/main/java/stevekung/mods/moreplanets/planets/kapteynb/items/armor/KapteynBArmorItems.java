/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 *
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.planets.kapteynb.items.armor;

import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor.ArmorMaterial;
import net.minecraftforge.common.util.EnumHelper;
import stevekung.mods.moreplanets.core.util.RegisterHelper;

public class KapteynBArmorItems
{
    public static Item uranium_helmet;
    public static Item uranium_chestplate;
    public static Item uranium_leggings;
    public static Item uranium_boots;
    public static Item frozen_iron_helmet;
    public static Item frozen_iron_chestplate;
    public static Item frozen_iron_leggings;
    public static Item frozen_iron_boots;
    public static Item ice_crystal_helmet;
    public static Item ice_crystal_chestplate;
    public static Item ice_crystal_leggings;
    public static Item ice_crystal_boots;
    public static Item breathable_uranium_helmet;
    public static Item breathable_frozen_iron_helmet;
    public static Item breathable_ice_crystal_helmet;

    /**Name,Durability,ReductionAmounts[Helm,Chest,Leg,Boot],Enchantability**/
    public static ArmorMaterial uranium = EnumHelper.addArmorMaterial("uranium", 46, new int[] { 13, 15, 11, 8 }, 16);
    public static ArmorMaterial frozen_iron = EnumHelper.addArmorMaterial("frozen_iron", 40, new int[] { 11, 12, 9, 7 }, 16);
    public static ArmorMaterial ice_crystal = EnumHelper.addArmorMaterial("ice_crystal", 48, new int[] { 14, 16, 12, 9 }, 16);

    public static void init()
    {
        KapteynBArmorItems.initItems();
        KapteynBArmorItems.registerItems();
    }

    private static void initItems()
    {
        KapteynBArmorItems.uranium_helmet = new ArmorUranium("uranium_helmet", KapteynBArmorItems.uranium, 7, 0);
        KapteynBArmorItems.uranium_chestplate = new ArmorUranium("uranium_chestplate", KapteynBArmorItems.uranium, 7, 1);
        KapteynBArmorItems.uranium_leggings = new ArmorUranium("uranium_leggings", KapteynBArmorItems.uranium, 7, 2);
        KapteynBArmorItems.uranium_boots = new ArmorUranium("uranium_boots", KapteynBArmorItems.uranium, 7, 3);
        KapteynBArmorItems.frozen_iron_helmet = new ArmorFrozenIron("frozen_iron_helmet", KapteynBArmorItems.frozen_iron, 7, 0);
        KapteynBArmorItems.frozen_iron_chestplate = new ArmorFrozenIron("frozen_iron_chestplate", KapteynBArmorItems.frozen_iron, 7, 1);
        KapteynBArmorItems.frozen_iron_leggings = new ArmorFrozenIron("frozen_iron_leggings", KapteynBArmorItems.frozen_iron, 7, 2);
        KapteynBArmorItems.frozen_iron_boots = new ArmorFrozenIron("frozen_iron_boots", KapteynBArmorItems.frozen_iron, 7, 3);
        KapteynBArmorItems.ice_crystal_helmet = new ArmorIceCrystal("ice_crystal_helmet", KapteynBArmorItems.ice_crystal, 7, 0);
        KapteynBArmorItems.ice_crystal_chestplate = new ArmorIceCrystal("ice_crystal_chestplate", KapteynBArmorItems.ice_crystal, 7, 1);
        KapteynBArmorItems.ice_crystal_leggings = new ArmorIceCrystal("ice_crystal_leggings", KapteynBArmorItems.ice_crystal, 7, 2);
        KapteynBArmorItems.ice_crystal_boots = new ArmorIceCrystal("ice_crystal_boots", KapteynBArmorItems.ice_crystal, 7, 3);
        KapteynBArmorItems.breathable_uranium_helmet = new ArmorBreathableUranium("breathable_uranium_helmet", KapteynBArmorItems.uranium, 7, 0);
        KapteynBArmorItems.breathable_frozen_iron_helmet = new ArmorBreathableFrozenIron("breathable_frozen_iron_helmet", KapteynBArmorItems.frozen_iron, 7, 0);
        KapteynBArmorItems.breathable_ice_crystal_helmet = new ArmorBreathableIceCrystal("breathable_ice_crystal_helmet", KapteynBArmorItems.ice_crystal, 7, 0);
    }

    private static void registerItems()
    {
        RegisterHelper.registerItem(KapteynBArmorItems.uranium_helmet);
        RegisterHelper.registerItem(KapteynBArmorItems.uranium_chestplate);
        RegisterHelper.registerItem(KapteynBArmorItems.uranium_leggings);
        RegisterHelper.registerItem(KapteynBArmorItems.uranium_boots);
        RegisterHelper.registerItem(KapteynBArmorItems.frozen_iron_helmet);
        RegisterHelper.registerItem(KapteynBArmorItems.frozen_iron_chestplate);
        RegisterHelper.registerItem(KapteynBArmorItems.frozen_iron_leggings);
        RegisterHelper.registerItem(KapteynBArmorItems.frozen_iron_boots);
        RegisterHelper.registerItem(KapteynBArmorItems.ice_crystal_helmet);
        RegisterHelper.registerItem(KapteynBArmorItems.ice_crystal_chestplate);
        RegisterHelper.registerItem(KapteynBArmorItems.ice_crystal_leggings);
        RegisterHelper.registerItem(KapteynBArmorItems.ice_crystal_boots);
        RegisterHelper.registerItem(KapteynBArmorItems.breathable_uranium_helmet);
        RegisterHelper.registerItem(KapteynBArmorItems.breathable_frozen_iron_helmet);
        RegisterHelper.registerItem(KapteynBArmorItems.breathable_ice_crystal_helmet);
    }
}