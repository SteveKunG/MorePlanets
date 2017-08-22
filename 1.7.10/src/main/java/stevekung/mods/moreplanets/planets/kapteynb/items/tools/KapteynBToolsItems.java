/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 *
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.planets.kapteynb.items.tools;

import net.minecraft.item.Item;
import net.minecraft.item.Item.ToolMaterial;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraftforge.common.util.EnumHelper;
import stevekung.mods.moreplanets.core.items.tools.*;
import stevekung.mods.moreplanets.core.util.RegisterHelper;

public class KapteynBToolsItems
{
    public static Item frozen_iron_pickaxe;
    public static Item frozen_iron_axe;
    public static Item frozen_iron_hoe;
    public static Item frozen_iron_shovel;
    public static Item frozen_iron_sword;
    public static Item uranium_pickaxe;
    public static Item uranium_axe;
    public static Item uranium_hoe;
    public static Item uranium_shovel;
    public static Item uranium_sword;
    public static Item ice_crystal_pickaxe;
    public static Item ice_crystal_axe;
    public static Item ice_crystal_hoe;
    public static Item ice_crystal_shovel;
    public static Item ice_crystal_sword;

    /**Name,HarvestLevel,MaxUse,Efficiency,Damage,Enchantability**/
    public static ToolMaterial frozen_iron = EnumHelper.addToolMaterial("frozen_iron", 4, 1254, 9.75F, 4.75F, 8);
    public static ToolMaterial uranium = EnumHelper.addToolMaterial("uranium", 4, 1764, 11.0F, 5.0F, 8);
    public static ToolMaterial ice_crystal = EnumHelper.addToolMaterial("ice_crystal", 4, 1824, 12.0F, 5.5F, 8);

    public static void init()
    {
        KapteynBToolsItems.initItems();
        KapteynBToolsItems.registerItems();
        KapteynBToolsItems.registerHarvestLevels();
    }

    private static void initItems()
    {
        KapteynBToolsItems.frozen_iron_pickaxe = new ItemElectricPickaxeMP("frozen_iron_pickaxe", KapteynBToolsItems.frozen_iron, 22500.0F, living -> living.addPotionEffect(new PotionEffect(Potion.moveSlowdown.id, 60))).setTextureName("kapteynb:frozen_iron_pickaxe");
        KapteynBToolsItems.frozen_iron_axe = new ItemElectricAxeMP("frozen_iron_axe", KapteynBToolsItems.frozen_iron, 22500.0F, living -> living.addPotionEffect(new PotionEffect(Potion.moveSlowdown.id, 60))).setTextureName("kapteynb:frozen_iron_axe");
        KapteynBToolsItems.frozen_iron_hoe = new ItemElectricHoeMP("frozen_iron_hoe", KapteynBToolsItems.frozen_iron, 22500.0F, living -> living.addPotionEffect(new PotionEffect(Potion.moveSlowdown.id, 60))).setTextureName("kapteynb:frozen_iron_hoe");
        KapteynBToolsItems.frozen_iron_shovel = new ItemElectricShovelMP("frozen_iron_spade", KapteynBToolsItems.frozen_iron, 22500.0F, living -> living.addPotionEffect(new PotionEffect(Potion.moveSlowdown.id, 60))).setTextureName("kapteynb:frozen_iron_shovel");
        KapteynBToolsItems.frozen_iron_sword = new ItemElectricSwordMP("frozen_iron_sword", KapteynBToolsItems.frozen_iron, 22500.0F, living -> living.addPotionEffect(new PotionEffect(Potion.moveSlowdown.id, 60))).setTextureName("kapteynb:frozen_iron_sword");
        KapteynBToolsItems.uranium_pickaxe = new ItemUraniumPickaxe("uranium_pickaxe", KapteynBToolsItems.uranium).setTextureName("kapteynb:uranium_pickaxe");
        KapteynBToolsItems.uranium_axe = new ItemUraniumAxe("uranium_axe", KapteynBToolsItems.uranium).setTextureName("kapteynb:uranium_axe");
        KapteynBToolsItems.uranium_hoe = new ItemUraniumHoe("uranium_hoe", KapteynBToolsItems.uranium).setTextureName("kapteynb:uranium_hoe");
        KapteynBToolsItems.uranium_shovel = new ItemUraniumShovel("uranium_spade", KapteynBToolsItems.uranium).setTextureName("kapteynb:uranium_shovel");
        KapteynBToolsItems.uranium_sword = new ItemUraniumSword("uranium_sword", KapteynBToolsItems.uranium).setTextureName("kapteynb:uranium_sword");
        KapteynBToolsItems.ice_crystal_pickaxe = new ItemIceCrystalPickaxe("ice_crystal_pickaxe", KapteynBToolsItems.ice_crystal);
        KapteynBToolsItems.ice_crystal_axe = new ItemIceCrystalAxe("ice_crystal_axe", KapteynBToolsItems.ice_crystal);
        KapteynBToolsItems.ice_crystal_hoe = new ItemIceCrystalHoe("ice_crystal_hoe", KapteynBToolsItems.ice_crystal);
        KapteynBToolsItems.ice_crystal_shovel = new ItemIceCrystalShovel("ice_crystal_shovel", KapteynBToolsItems.ice_crystal);
        KapteynBToolsItems.ice_crystal_sword = new ItemIceCrystalSword("ice_crystal_sword", KapteynBToolsItems.ice_crystal);
    }

    private static void registerHarvestLevels()
    {
        KapteynBToolsItems.frozen_iron_pickaxe.setHarvestLevel("pickaxe", 4);
        KapteynBToolsItems.frozen_iron_axe.setHarvestLevel("axe", 4);
        KapteynBToolsItems.frozen_iron_shovel.setHarvestLevel("shovel", 4);
        KapteynBToolsItems.uranium_pickaxe.setHarvestLevel("pickaxe", 4);
        KapteynBToolsItems.uranium_axe.setHarvestLevel("axe", 4);
        KapteynBToolsItems.uranium_shovel.setHarvestLevel("shovel", 4);
        KapteynBToolsItems.ice_crystal_pickaxe.setHarvestLevel("pickaxe", 4);
        KapteynBToolsItems.ice_crystal_axe.setHarvestLevel("axe", 4);
        KapteynBToolsItems.ice_crystal_shovel.setHarvestLevel("shovel", 4);
    }

    private static void registerItems()
    {
        RegisterHelper.registerItem(KapteynBToolsItems.frozen_iron_pickaxe);
        RegisterHelper.registerItem(KapteynBToolsItems.frozen_iron_axe);
        RegisterHelper.registerItem(KapteynBToolsItems.frozen_iron_hoe);
        RegisterHelper.registerItem(KapteynBToolsItems.frozen_iron_shovel);
        RegisterHelper.registerItem(KapteynBToolsItems.frozen_iron_sword);
        RegisterHelper.registerItem(KapteynBToolsItems.uranium_pickaxe);
        RegisterHelper.registerItem(KapteynBToolsItems.uranium_axe);
        RegisterHelper.registerItem(KapteynBToolsItems.uranium_hoe);
        RegisterHelper.registerItem(KapteynBToolsItems.uranium_shovel);
        RegisterHelper.registerItem(KapteynBToolsItems.uranium_sword);
        RegisterHelper.registerItem(KapteynBToolsItems.ice_crystal_sword);
        RegisterHelper.registerItem(KapteynBToolsItems.ice_crystal_shovel);
        RegisterHelper.registerItem(KapteynBToolsItems.ice_crystal_pickaxe);
        RegisterHelper.registerItem(KapteynBToolsItems.ice_crystal_axe);
        RegisterHelper.registerItem(KapteynBToolsItems.ice_crystal_hoe);
    }
}