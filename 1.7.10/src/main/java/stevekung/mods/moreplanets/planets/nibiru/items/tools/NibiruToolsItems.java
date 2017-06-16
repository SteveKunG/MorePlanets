/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 *
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.planets.nibiru.items.tools;

import net.minecraft.item.Item;
import net.minecraft.item.Item.ToolMaterial;
import net.minecraftforge.common.util.EnumHelper;
import stevekung.mods.moreplanets.core.items.tools.*;
import stevekung.mods.moreplanets.core.util.RegisterHelper;

public class NibiruToolsItems
{
    public static Item red_gem_pickaxe;
    public static Item red_gem_axe;
    public static Item red_gem_hoe;
    public static Item red_gem_shovel;
    public static Item red_gem_sword;
    public static Item norium_pickaxe;
    public static Item norium_axe;
    public static Item norium_hoe;
    public static Item norium_shovel;
    public static Item norium_sword;

    /**Name,HarvestLevel,MaxUse,Efficiency,Damage,Enchantability**/
    public static ToolMaterial red_gem = EnumHelper.addToolMaterial("red_gem", 4, 1716, 10.25F, 3.75F, 8);
    public static ToolMaterial norium = EnumHelper.addToolMaterial("norium", 4, 1712, 10.5F, 4.0F, 8);

    public static void init()
    {
        NibiruToolsItems.initItems();
        NibiruToolsItems.registerItems();
        NibiruToolsItems.registerHarvestLevels();
    }

    private static void initItems()
    {
        NibiruToolsItems.red_gem_pickaxe = new ItemElectricPickaxeMP("red_gem_pickaxe", NibiruToolsItems.red_gem, 20000.0F, null).setTextureName("nibiru:red_gem_pickaxe");
        NibiruToolsItems.red_gem_axe = new ItemElectricAxeMP("red_gem_axe", NibiruToolsItems.red_gem, 20000.0F, null).setTextureName("nibiru:red_gem_axe");
        NibiruToolsItems.red_gem_hoe = new ItemElectricHoeMP("red_gem_hoe", NibiruToolsItems.red_gem, 20000.0F, null).setTextureName("nibiru:red_gem_hoe");
        NibiruToolsItems.red_gem_shovel = new ItemElectricShovelMP("red_gem_spade", NibiruToolsItems.red_gem, 20000.0F, null).setTextureName("nibiru:red_gem_shovel");
        NibiruToolsItems.red_gem_sword = new ItemElectricSwordMP("red_gem_sword", NibiruToolsItems.red_gem, 20000.0F, null).setTextureName("nibiru:red_gem_sword");
        NibiruToolsItems.norium_pickaxe = new ItemElectricPickaxeMP("norium_pickaxe", NibiruToolsItems.norium, 20000.0F, null).setTextureName("nibiru:norium_pickaxe");
        NibiruToolsItems.norium_axe = new ItemElectricAxeMP("norium_axe", NibiruToolsItems.norium, 20000.0F, null).setTextureName("nibiru:norium_axe");
        NibiruToolsItems.norium_hoe = new ItemElectricHoeMP("norium_hoe", NibiruToolsItems.norium, 20000.0F, null).setTextureName("nibiru:norium_hoe");
        NibiruToolsItems.norium_shovel = new ItemElectricShovelMP("norium_spade", NibiruToolsItems.norium, 20000.0F, null).setTextureName("nibiru:norium_shovel");
        NibiruToolsItems.norium_sword = new ItemElectricSwordMP("norium_sword", NibiruToolsItems.norium, 20000.0F, null).setTextureName("nibiru:norium_sword");
    }

    private static void registerHarvestLevels()
    {
        NibiruToolsItems.red_gem_pickaxe.setHarvestLevel("pickaxe", 4);
        NibiruToolsItems.red_gem_axe.setHarvestLevel("axe", 4);
        NibiruToolsItems.red_gem_shovel.setHarvestLevel("shovel", 4);
        NibiruToolsItems.norium_pickaxe.setHarvestLevel("pickaxe", 4);
        NibiruToolsItems.norium_axe.setHarvestLevel("axe", 4);
        NibiruToolsItems.norium_shovel.setHarvestLevel("shovel", 4);
    }

    private static void registerItems()
    {
        RegisterHelper.registerItem(NibiruToolsItems.red_gem_pickaxe);
        RegisterHelper.registerItem(NibiruToolsItems.red_gem_axe);
        RegisterHelper.registerItem(NibiruToolsItems.red_gem_hoe);
        RegisterHelper.registerItem(NibiruToolsItems.red_gem_shovel);
        RegisterHelper.registerItem(NibiruToolsItems.red_gem_sword);
        RegisterHelper.registerItem(NibiruToolsItems.norium_pickaxe);
        RegisterHelper.registerItem(NibiruToolsItems.norium_axe);
        RegisterHelper.registerItem(NibiruToolsItems.norium_hoe);
        RegisterHelper.registerItem(NibiruToolsItems.norium_shovel);
        RegisterHelper.registerItem(NibiruToolsItems.norium_sword);
    }
}