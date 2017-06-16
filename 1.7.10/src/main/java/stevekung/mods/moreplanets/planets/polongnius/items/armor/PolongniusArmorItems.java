/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 *
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.planets.polongnius.items.armor;

import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor.ArmorMaterial;
import net.minecraftforge.common.util.EnumHelper;
import stevekung.mods.moreplanets.core.util.RegisterHelper;

public class PolongniusArmorItems
{
    public static Item cheese_leather_helmet;
    public static Item cheese_leather_chestplate;
    public static Item cheese_leather_leggings;
    public static Item cheese_leather_boots;
    public static Item polongnius_meteoric_iron_helmet;
    public static Item polongnius_meteoric_iron_chestplate;
    public static Item polongnius_meteoric_iron_leggings;
    public static Item polongnius_meteoric_iron_boots;
    public static Item palladium_helmet;
    public static Item palladium_chestplate;
    public static Item palladium_leggings;
    public static Item palladium_boots;
    public static Item purple_crystal_helmet;
    public static Item purple_crystal_chestplate;
    public static Item purple_crystal_leggings;
    public static Item purple_crystal_boots;
    public static Item breathable_polongnius_meteor_helmet;
    public static Item breathable_palladium_helmet;

    /**Name,Durability,ReductionAmounts[Helm,Chest,Leg,Boot],Enchantability**/
    public static ArmorMaterial cheese_leather = EnumHelper.addArmorMaterial("cheese_leather", 12, new int[] { 3, 6, 4, 2 }, 16);
    public static ArmorMaterial polongnius_meteoric_iron = EnumHelper.addArmorMaterial("polongnius_meteoric_iron", 36, new int[] { 9, 13, 10, 7 }, 16);
    public static ArmorMaterial palladium = EnumHelper.addArmorMaterial("palladium", 36, new int[] { 9, 13, 10, 7 }, 16);
    public static ArmorMaterial purple_crystal = EnumHelper.addArmorMaterial("purple_crystal", 30, new int[] { 7, 10, 8, 6 }, 16);

    public static void init()
    {
        PolongniusArmorItems.initItems();
        PolongniusArmorItems.registerItems();
    }

    private static void initItems()
    {
        PolongniusArmorItems.cheese_leather_helmet = new ArmorCheeseLeather("cheese_leather_helmet", PolongniusArmorItems.cheese_leather, 7, 0);
        PolongniusArmorItems.cheese_leather_chestplate = new ArmorCheeseLeather("cheese_leather_chestplate", PolongniusArmorItems.cheese_leather, 7, 1);
        PolongniusArmorItems.cheese_leather_leggings = new ArmorCheeseLeather("cheese_leather_leggings", PolongniusArmorItems.cheese_leather, 7, 2);
        PolongniusArmorItems.cheese_leather_boots = new ArmorCheeseLeather("cheese_leather_boots", PolongniusArmorItems.cheese_leather, 7, 3);
        PolongniusArmorItems.polongnius_meteoric_iron_helmet = new ArmorPolongniusMeteor("polongnius_meteor_helmet", PolongniusArmorItems.polongnius_meteoric_iron, 7, 0).setTextureName("polongnius:polongnius_meteoric_iron_helmet");
        PolongniusArmorItems.polongnius_meteoric_iron_chestplate = new ArmorPolongniusMeteor("polongnius_meteor_chestplate", PolongniusArmorItems.polongnius_meteoric_iron, 7, 1).setTextureName("polongnius:polongnius_meteoric_iron_chestplate");
        PolongniusArmorItems.polongnius_meteoric_iron_leggings = new ArmorPolongniusMeteor("polongnius_meteor_leggings", PolongniusArmorItems.polongnius_meteoric_iron, 7, 2).setTextureName("polongnius:polongnius_meteoric_iron_leggings");
        PolongniusArmorItems.polongnius_meteoric_iron_boots = new ArmorPolongniusMeteor("polongnius_meteor_boots", PolongniusArmorItems.polongnius_meteoric_iron, 7, 3).setTextureName("polongnius:polongnius_meteoric_iron_boots");
        PolongniusArmorItems.palladium_helmet = new ArmorPalladium("palladium_helmet", PolongniusArmorItems.palladium, 7, 0);
        PolongniusArmorItems.palladium_chestplate = new ArmorPalladium("palladium_chestplate", PolongniusArmorItems.palladium, 7, 1);
        PolongniusArmorItems.palladium_leggings = new ArmorPalladium("palladium_leggings", PolongniusArmorItems.palladium, 7, 2);
        PolongniusArmorItems.palladium_boots = new ArmorPalladium("palladium_boots", PolongniusArmorItems.palladium, 7, 3);
        PolongniusArmorItems.purple_crystal_helmet = new ArmorPurpleCrystal("purple_crystal_helmet", PolongniusArmorItems.purple_crystal, 7, 0);
        PolongniusArmorItems.purple_crystal_chestplate = new ArmorPurpleCrystal("purple_crystal_chestplate", PolongniusArmorItems.purple_crystal, 7, 1);
        PolongniusArmorItems.purple_crystal_leggings = new ArmorPurpleCrystal("purple_crystal_leggings", PolongniusArmorItems.purple_crystal, 7, 2);
        PolongniusArmorItems.purple_crystal_boots = new ArmorPurpleCrystal("purple_crystal_boots", PolongniusArmorItems.purple_crystal, 7, 3);
        PolongniusArmorItems.breathable_polongnius_meteor_helmet = new ArmorBreathablePolongniusMeteor("breathable_polongnius_meteor_helmet", PolongniusArmorItems.polongnius_meteoric_iron, 7, 0);
        PolongniusArmorItems.breathable_palladium_helmet = new ArmorBreathablePalladium("breathable_palladium_helmet", PolongniusArmorItems.palladium, 7, 0);
    }

    private static void registerItems()
    {
        RegisterHelper.registerItem(PolongniusArmorItems.cheese_leather_helmet);
        RegisterHelper.registerItem(PolongniusArmorItems.cheese_leather_chestplate);
        RegisterHelper.registerItem(PolongniusArmorItems.cheese_leather_leggings);
        RegisterHelper.registerItem(PolongniusArmorItems.cheese_leather_boots);
        RegisterHelper.registerItem(PolongniusArmorItems.polongnius_meteoric_iron_helmet);
        RegisterHelper.registerItem(PolongniusArmorItems.polongnius_meteoric_iron_chestplate);
        RegisterHelper.registerItem(PolongniusArmorItems.polongnius_meteoric_iron_leggings);
        RegisterHelper.registerItem(PolongniusArmorItems.polongnius_meteoric_iron_boots);
        RegisterHelper.registerItem(PolongniusArmorItems.palladium_helmet);
        RegisterHelper.registerItem(PolongniusArmorItems.palladium_chestplate);
        RegisterHelper.registerItem(PolongniusArmorItems.palladium_leggings);
        RegisterHelper.registerItem(PolongniusArmorItems.palladium_boots);
        RegisterHelper.registerItem(PolongniusArmorItems.purple_crystal_helmet);
        RegisterHelper.registerItem(PolongniusArmorItems.purple_crystal_chestplate);
        RegisterHelper.registerItem(PolongniusArmorItems.purple_crystal_leggings);
        RegisterHelper.registerItem(PolongniusArmorItems.purple_crystal_boots);
        RegisterHelper.registerItem(PolongniusArmorItems.breathable_polongnius_meteor_helmet);
        RegisterHelper.registerItem(PolongniusArmorItems.breathable_palladium_helmet);
    }
}