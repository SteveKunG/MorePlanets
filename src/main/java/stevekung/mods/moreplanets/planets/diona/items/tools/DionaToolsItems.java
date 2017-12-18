/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 *
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.planets.diona.items.tools;

import net.minecraft.item.Item;
import net.minecraft.item.Item.ToolMaterial;
import net.minecraftforge.common.util.EnumHelper;
import stevekung.mods.moreplanets.core.items.tools.*;
import stevekung.mods.moreplanets.core.util.RegisterHelper;

public class DionaToolsItems
{
    public static Item quontonium_sword;
    public static Item quontonium_shovel;
    public static Item quontonium_pickaxe;
    public static Item quontonium_axe;
    public static Item quontonium_hoe;
    public static Item fronisium_sword;
    public static Item fronisium_shovel;
    public static Item fronisium_pickaxe;
    public static Item fronisium_axe;
    public static Item fronisium_hoe;

    /**Name,HarvestLevel,MaxUse,Efficiency,Damage,Enchantability**/
    public static ToolMaterial quontonium = EnumHelper.addToolMaterial("quontonium", 4, 1648, 9.5F, 3.5F, 8);
    public static ToolMaterial fronisium = EnumHelper.addToolMaterial("fronisium", 4, 1680, 9.5F, 3.75F, 8);

    public static void init()
    {
        DionaToolsItems.initItems();
        DionaToolsItems.registerItems();
        DionaToolsItems.registerHarvestLevels();
    }

    private static void initItems()
    {
        DionaToolsItems.quontonium_sword = new ItemElectricSwordMP("quontonium_sword", DionaToolsItems.quontonium, 15000.0F, null).setTextureName("diona:quontonium_sword");
        DionaToolsItems.quontonium_shovel = new ItemElectricShovelMP("quontonium_spade", DionaToolsItems.quontonium, 15000.0F, null).setTextureName("diona:quontonium_shovel");
        DionaToolsItems.quontonium_pickaxe = new ItemElectricPickaxeMP("quontonium_pickaxe", DionaToolsItems.quontonium, 15000.0F, null).setTextureName("diona:quontonium_pickaxe");
        DionaToolsItems.quontonium_axe = new ItemElectricAxeMP("quontonium_axe", DionaToolsItems.quontonium, 15000.0F, null).setTextureName("diona:quontonium_axe");
        DionaToolsItems.quontonium_hoe = new ItemElectricHoeMP("quontonium_hoe", DionaToolsItems.quontonium, 15000.0F, null).setTextureName("diona:quontonium_hoe");
        DionaToolsItems.fronisium_sword = new ItemElectricSwordMP("fronisium_sword", DionaToolsItems.fronisium, 15000.0F, null).setTextureName("diona:fronisium_sword");
        DionaToolsItems.fronisium_shovel = new ItemElectricShovelMP("fronisium_spade", DionaToolsItems.fronisium, 15000.0F, null).setTextureName("diona:fronisium_shovel");
        DionaToolsItems.fronisium_pickaxe = new ItemElectricPickaxeMP("fronisium_pickaxe", DionaToolsItems.fronisium, 15000.0F, null).setTextureName("diona:fronisium_pickaxe");
        DionaToolsItems.fronisium_axe = new ItemElectricAxeMP("fronisium_axe", DionaToolsItems.fronisium, 15000.0F, null).setTextureName("diona:fronisium_axe");
        DionaToolsItems.fronisium_hoe = new ItemElectricHoeMP("fronisium_hoe", DionaToolsItems.fronisium, 15000.0F, null).setTextureName("diona:fronisium_hoe");
    }

    private static void registerHarvestLevels()
    {
        DionaToolsItems.quontonium_shovel.setHarvestLevel("shovel", 4);
        DionaToolsItems.quontonium_pickaxe.setHarvestLevel("pickaxe", 4);
        DionaToolsItems.quontonium_axe.setHarvestLevel("axe", 4);
        DionaToolsItems.fronisium_shovel.setHarvestLevel("shovel", 4);
        DionaToolsItems.fronisium_pickaxe.setHarvestLevel("pickaxe", 4);
        DionaToolsItems.fronisium_axe.setHarvestLevel("axe", 4);
    }

    private static void registerItems()
    {
        RegisterHelper.registerItem(DionaToolsItems.quontonium_sword);
        RegisterHelper.registerItem(DionaToolsItems.quontonium_shovel);
        RegisterHelper.registerItem(DionaToolsItems.quontonium_pickaxe);
        RegisterHelper.registerItem(DionaToolsItems.quontonium_axe);
        RegisterHelper.registerItem(DionaToolsItems.quontonium_hoe);
        RegisterHelper.registerItem(DionaToolsItems.fronisium_sword);
        RegisterHelper.registerItem(DionaToolsItems.fronisium_shovel);
        RegisterHelper.registerItem(DionaToolsItems.fronisium_pickaxe);
        RegisterHelper.registerItem(DionaToolsItems.fronisium_axe);
        RegisterHelper.registerItem(DionaToolsItems.fronisium_hoe);
    }
}