/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 *
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.moons.koentus.items.tools;

import net.minecraft.item.Item;
import net.minecraft.item.Item.ToolMaterial;
import net.minecraftforge.common.util.EnumHelper;
import stevekung.mods.moreplanets.core.items.tools.*;
import stevekung.mods.moreplanets.core.util.RegisterHelper;

public class KoentusToolsItems
{
    public static Item white_crystal_pickaxe;
    public static Item white_crystal_axe;
    public static Item white_crystal_hoe;
    public static Item white_crystal_shovel;
    public static Item white_crystal_sword;
    public static Item koentus_meteoric_iron_pickaxe;
    public static Item koentus_meteoric_iron_axe;
    public static Item koentus_meteoric_iron_hoe;
    public static Item koentus_meteoric_iron_shovel;
    public static Item koentus_meteoric_iron_sword;

    /**Name,HarvestLevel,MaxUse,Efficiency,Damage,Enchantability**/
    public static ToolMaterial white_crystal = EnumHelper.addToolMaterial("white_crystal", 4, 1612, 9.25F, 3.25F, 8);
    public static ToolMaterial koentus_meteoric_iron = EnumHelper.addToolMaterial("koentus_meteor", 4, 1654, 9.5F, 3.75F, 8);

    public static void init()
    {
        KoentusToolsItems.initItems();
        KoentusToolsItems.registerItems();
        KoentusToolsItems.registerHarvestLevels();
    }

    private static void initItems()
    {
        KoentusToolsItems.white_crystal_pickaxe = new ItemElectricPickaxeMP("white_crystal_pickaxe", KoentusToolsItems.white_crystal, 18500.0F, null).setTextureName("koentus:white_crystal_pickaxe");
        KoentusToolsItems.white_crystal_axe = new ItemElectricAxeMP("white_crystal_axe", KoentusToolsItems.white_crystal, 18500.0F, null).setTextureName("koentus:white_crystal_axe");
        KoentusToolsItems.white_crystal_hoe = new ItemElectricHoeMP("white_crystal_hoe", KoentusToolsItems.white_crystal, 18500.0F, null).setTextureName("koentus:white_crystal_hoe");
        KoentusToolsItems.white_crystal_shovel = new ItemElectricShovelMP("white_crystal_spade", KoentusToolsItems.white_crystal, 18500.0F, null).setTextureName("koentus:white_crystal_shovel");
        KoentusToolsItems.white_crystal_sword = new ItemElectricSwordMP("white_crystal_sword", KoentusToolsItems.white_crystal, 18500.0F, null).setTextureName("koentus:white_crystal_sword");
        KoentusToolsItems.koentus_meteoric_iron_pickaxe = new ItemElectricPickaxeMP("koentus_meteor_pickaxe", KoentusToolsItems.koentus_meteoric_iron, 18000.0F, null).setTextureName("koentus:koentus_meteoric_iron_pickaxe");
        KoentusToolsItems.koentus_meteoric_iron_axe = new ItemElectricAxeMP("koentus_meteor_axe", KoentusToolsItems.koentus_meteoric_iron, 18000.0F, null).setTextureName("koentus:koentus_meteoric_iron_axe");
        KoentusToolsItems.koentus_meteoric_iron_hoe = new ItemElectricHoeMP("koentus_meteor_hoe", KoentusToolsItems.koentus_meteoric_iron, 18000.0F, null).setTextureName("koentus:koentus_meteoric_iron_hoe");
        KoentusToolsItems.koentus_meteoric_iron_shovel = new ItemElectricShovelMP("koentus_meteor_spade", KoentusToolsItems.koentus_meteoric_iron, 18000.0F, null).setTextureName("koentus:koentus_meteoric_iron_shovel");
        KoentusToolsItems.koentus_meteoric_iron_sword = new ItemElectricSwordMP("koentus_meteor_sword", KoentusToolsItems.koentus_meteoric_iron, 18000.0F, null).setTextureName("koentus:koentus_meteoric_iron_sword");
    }

    private static void registerHarvestLevels()
    {
        KoentusToolsItems.white_crystal_pickaxe.setHarvestLevel("pickaxe", 4);
        KoentusToolsItems.white_crystal_axe.setHarvestLevel("axe", 4);
        KoentusToolsItems.white_crystal_shovel.setHarvestLevel("shovel", 4);
        KoentusToolsItems.koentus_meteoric_iron_pickaxe.setHarvestLevel("pickaxe", 4);
        KoentusToolsItems.koentus_meteoric_iron_axe.setHarvestLevel("axe", 4);
        KoentusToolsItems.koentus_meteoric_iron_shovel.setHarvestLevel("shovel", 4);
    }

    private static void registerItems()
    {
        RegisterHelper.registerItem(KoentusToolsItems.white_crystal_pickaxe);
        RegisterHelper.registerItem(KoentusToolsItems.white_crystal_axe);
        RegisterHelper.registerItem(KoentusToolsItems.white_crystal_hoe);
        RegisterHelper.registerItem(KoentusToolsItems.white_crystal_shovel);
        RegisterHelper.registerItem(KoentusToolsItems.white_crystal_sword);
        RegisterHelper.registerItem(KoentusToolsItems.koentus_meteoric_iron_pickaxe);
        RegisterHelper.registerItem(KoentusToolsItems.koentus_meteoric_iron_axe);
        RegisterHelper.registerItem(KoentusToolsItems.koentus_meteoric_iron_hoe);
        RegisterHelper.registerItem(KoentusToolsItems.koentus_meteoric_iron_shovel);
        RegisterHelper.registerItem(KoentusToolsItems.koentus_meteoric_iron_sword);
    }
}