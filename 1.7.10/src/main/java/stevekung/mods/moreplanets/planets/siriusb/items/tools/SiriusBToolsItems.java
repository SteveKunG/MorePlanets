/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 *
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.planets.siriusb.items.tools;

import net.minecraft.item.Item;
import net.minecraft.item.Item.ToolMaterial;
import net.minecraftforge.common.util.EnumHelper;
import stevekung.mods.moreplanets.core.items.tools.*;
import stevekung.mods.moreplanets.core.util.RegisterHelper;

public class SiriusBToolsItems
{
    public static Item sulfur_pickaxe;
    public static Item sulfur_axe;
    public static Item sulfur_hoe;
    public static Item sulfur_shovel;
    public static Item sulfur_sword;

    // Name,HarvestLevel,MaxUse,Efficiency,Damage,Enchantability
    public static ToolMaterial sulfur = EnumHelper.addToolMaterial("sulfur", 3, 1364, 9.75F, 4.0F, 8);

    public static void init()
    {
        SiriusBToolsItems.initItems();
        SiriusBToolsItems.registerItems();
        SiriusBToolsItems.registerHarvestLevels();
    }

    private static void initItems()
    {
        SiriusBToolsItems.sulfur_pickaxe = new ItemElectricPickaxeMP("sulfur_pickaxe", SiriusBToolsItems.sulfur, 30000.0F, null).setTextureName("siriusb:sulfur_pickaxe");
        SiriusBToolsItems.sulfur_axe = new ItemElectricAxeMP("sulfur_axe", SiriusBToolsItems.sulfur, 30000.0F, null).setTextureName("siriusb:sulfur_axe");
        SiriusBToolsItems.sulfur_hoe = new ItemElectricHoeMP("sulfur_hoe", SiriusBToolsItems.sulfur, 30000.0F, null).setTextureName("siriusb:sulfur_hoe");
        SiriusBToolsItems.sulfur_shovel = new ItemElectricShovelMP("sulfur_shovel", SiriusBToolsItems.sulfur, 30000.0F, null).setTextureName("siriusb:sulfur_shovel");
        SiriusBToolsItems.sulfur_sword = new ItemElectricSwordMP("sulfur_sword", SiriusBToolsItems.sulfur, 30000.0F, null).setTextureName("siriusb:sulfur_sword");
    }

    private static void registerHarvestLevels()
    {
        SiriusBToolsItems.sulfur_pickaxe.setHarvestLevel("pickaxe", 3);
        SiriusBToolsItems.sulfur_axe.setHarvestLevel("axe", 3);
        SiriusBToolsItems.sulfur_shovel.setHarvestLevel("shovel", 3);
    }

    private static void registerItems()
    {
        RegisterHelper.registerItem(SiriusBToolsItems.sulfur_sword);
        RegisterHelper.registerItem(SiriusBToolsItems.sulfur_shovel);
        RegisterHelper.registerItem(SiriusBToolsItems.sulfur_pickaxe);
        RegisterHelper.registerItem(SiriusBToolsItems.sulfur_axe);
        RegisterHelper.registerItem(SiriusBToolsItems.sulfur_hoe);
    }
}