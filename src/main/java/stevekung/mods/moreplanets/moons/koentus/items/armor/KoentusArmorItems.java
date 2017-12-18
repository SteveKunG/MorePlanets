/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 *
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.moons.koentus.items.armor;

import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor.ArmorMaterial;
import net.minecraftforge.common.util.EnumHelper;
import stevekung.mods.moreplanets.core.util.RegisterHelper;

public class KoentusArmorItems
{
    public static Item koentus_meteoric_iron_helmet;
    public static Item koentus_meteoric_iron_chestplate;
    public static Item koentus_meteoric_iron_leggings;
    public static Item koentus_meteoric_iron_boots;
    public static Item white_crystal_helmet;
    public static Item white_crystal_chestplate;
    public static Item white_crystal_leggings;
    public static Item white_crystal_boots;
    public static Item breathableKoentusMeteorHelmet;
    public static Item breathableWhiteCrystalHelmet;

    /**Name,Durability,ReductionAmounts[Helm,Chest,Leg,Boot],Enchantability**/
    public static ArmorMaterial koentus_meteoric_iron = EnumHelper.addArmorMaterial("koentus_meteoric_iron", 30, new int[] { 8, 11, 9, 6 }, 16);
    public static ArmorMaterial white_crystal = EnumHelper.addArmorMaterial("white_crystal", 29, new int[] { 7, 10, 8, 5 }, 16);

    public static void init()
    {
        KoentusArmorItems.initItems();
        KoentusArmorItems.registerItems();
    }

    private static void initItems()
    {
        KoentusArmorItems.koentus_meteoric_iron_helmet = new ArmorKoentusMeteor("koentus_meteor_helmet", KoentusArmorItems.koentus_meteoric_iron, 7, 0).setTextureName("koentus:koentus_meteoric_iron_helmet");
        KoentusArmorItems.koentus_meteoric_iron_chestplate = new ArmorKoentusMeteor("koentus_meteor_chestplate", KoentusArmorItems.koentus_meteoric_iron, 7, 1).setTextureName("koentus:koentus_meteoric_iron_chestplate");
        KoentusArmorItems.koentus_meteoric_iron_leggings = new ArmorKoentusMeteor("koentus_meteor_leggings", KoentusArmorItems.koentus_meteoric_iron, 7, 2).setTextureName("koentus:koentus_meteoric_iron_leggings");
        KoentusArmorItems.koentus_meteoric_iron_boots = new ArmorKoentusMeteor("koentus_meteor_boots", KoentusArmorItems.koentus_meteoric_iron, 7, 3).setTextureName("koentus:koentus_meteoric_iron_boots");
        KoentusArmorItems.white_crystal_helmet = new ArmorWhiteCrystal("white_crystal_helmet", KoentusArmorItems.white_crystal, 7, 0);
        KoentusArmorItems.white_crystal_chestplate = new ArmorWhiteCrystal("white_crystal_chestplate", KoentusArmorItems.white_crystal, 7, 1);
        KoentusArmorItems.white_crystal_leggings = new ArmorWhiteCrystal("white_crystal_leggings", KoentusArmorItems.white_crystal, 7, 2);
        KoentusArmorItems.white_crystal_boots = new ArmorWhiteCrystal("white_crystal_boots", KoentusArmorItems.white_crystal, 7, 3);
        KoentusArmorItems.breathableKoentusMeteorHelmet = new ArmorBreathableKoentusMeteor("breathable_koentus_meteor_helmet", KoentusArmorItems.koentus_meteoric_iron, 7, 0);
        KoentusArmorItems.breathableWhiteCrystalHelmet = new ArmorBreathableWhiteCrystal("breathable_white_crystal_helmet", KoentusArmorItems.white_crystal, 7, 0);
    }

    private static void registerItems()
    {
        RegisterHelper.registerItem(KoentusArmorItems.koentus_meteoric_iron_helmet);
        RegisterHelper.registerItem(KoentusArmorItems.koentus_meteoric_iron_chestplate);
        RegisterHelper.registerItem(KoentusArmorItems.koentus_meteoric_iron_leggings);
        RegisterHelper.registerItem(KoentusArmorItems.koentus_meteoric_iron_boots);
        RegisterHelper.registerItem(KoentusArmorItems.white_crystal_helmet);
        RegisterHelper.registerItem(KoentusArmorItems.white_crystal_chestplate);
        RegisterHelper.registerItem(KoentusArmorItems.white_crystal_leggings);
        RegisterHelper.registerItem(KoentusArmorItems.white_crystal_boots);
        RegisterHelper.registerItem(KoentusArmorItems.breathableKoentusMeteorHelmet);
        RegisterHelper.registerItem(KoentusArmorItems.breathableWhiteCrystalHelmet);
    }
}