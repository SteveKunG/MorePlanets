/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 *
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.planets.fronos.items.tools;

import net.minecraft.item.Item;
import net.minecraft.item.Item.ToolMaterial;
import net.minecraftforge.common.util.EnumHelper;
import stevekung.mods.moreplanets.core.items.tools.*;
import stevekung.mods.moreplanets.core.util.RegisterHelper;
import stevekung.mods.moreplanets.planets.fronos.blocks.FronosBlocks;
import stevekung.mods.moreplanets.planets.fronos.items.FronosItems;

public class FronosToolsItems
{
    public static Item candy_pickaxe;
    public static Item candy_axe;
    public static Item candy_hoe;
    public static Item candy_shovel;
    public static Item candy_sword;
    public static Item black_diamond_pickaxe;
    public static Item black_diamond_axe;
    public static Item black_diamond_hoe;
    public static Item black_diamond_shovel;
    public static Item black_diamond_sword;
    public static Item iridium_pickaxe;
    public static Item iridium_axe;
    public static Item iridium_hoe;
    public static Item iridium_shovel;
    public static Item iridium_sword;
    public static Item fronos_rock_pickaxe;
    public static Item fronos_rock_axe;
    public static Item fronos_rock_hoe;
    public static Item fronos_rock_shovel;
    public static Item fronos_rock_sword;

    // Name,HarvestLevel,MaxUse,Efficiency,Damage,Enchantability
    public static ToolMaterial candy = EnumHelper.addToolMaterial("candy", 1, 248, 5.5F, 1F, 8);
    public static ToolMaterial black_diamond = EnumHelper.addToolMaterial("black_diamond", 4, 1748, 11.0F, 4.5F, 8);
    public static ToolMaterial iridium = EnumHelper.addToolMaterial("iridium", 4, 1732, 10.75F, 4.25F, 8);
    public static ToolMaterial fronos_rock = EnumHelper.addToolMaterial("fronos_rock", 1, 247, 5.0F, 1.25F, 8);

    public static void init()
    {
        FronosToolsItems.initItems();
        FronosToolsItems.registerItems();
        FronosToolsItems.registerHarvestLevels();
    }

    private static void initItems()
    {
        for (int i = 0; i < 8; ++i)
        {
            FronosToolsItems.candy_pickaxe = new ItemPickaxeMP("candy_pickaxe", FronosToolsItems.candy, FronosItems.candy_cane, i, "fronos:candy_pickaxe");
            FronosToolsItems.candy_axe = new ItemAxeMP("candy_axe", FronosToolsItems.candy, FronosItems.candy_cane, i, "fronos:candy_axe");
            FronosToolsItems.candy_hoe = new ItemHoeMP("candy_hoe", FronosToolsItems.candy, FronosItems.candy_cane, i, "fronos:candy_hoe");
            FronosToolsItems.candy_shovel = new ItemShovelMP("candy_spade", FronosToolsItems.candy, FronosItems.candy_cane, i, "fronos:candy_shovel");
            FronosToolsItems.candy_sword = new ItemSwordMP("candy_sword", FronosToolsItems.candy, FronosItems.candy_cane, i, "fronos:candy_sword");
        }

        FronosToolsItems.black_diamond_pickaxe = new ItemPickaxeMP("black_diamond_pickaxe", FronosToolsItems.black_diamond, FronosItems.fronos_item, 4, "fronos:black_diamond_pickaxe");
        FronosToolsItems.black_diamond_axe = new ItemAxeMP("black_diamond_axe", FronosToolsItems.black_diamond, FronosItems.fronos_item, 4, "fronos:black_diamond_axe");
        FronosToolsItems.black_diamond_hoe = new ItemHoeMP("black_diamond_hoe", FronosToolsItems.black_diamond, FronosItems.fronos_item, 4, "fronos:black_diamond_hoe");
        FronosToolsItems.black_diamond_shovel = new ItemShovelMP("black_diamond_spade", FronosToolsItems.black_diamond, FronosItems.fronos_item, 4, "fronos:black_diamond_shovel");
        FronosToolsItems.black_diamond_sword = new ItemSwordMP("black_diamond_sword", FronosToolsItems.black_diamond, FronosItems.fronos_item, 4, "fronos:black_diamond_sword");
        FronosToolsItems.iridium_pickaxe = new ItemPickaxeMP("iridium_pickaxe", FronosToolsItems.iridium, FronosItems.fronos_item, 5, "fronos:iridium_pickaxe");
        FronosToolsItems.iridium_axe = new ItemAxeMP("iridium_axe", FronosToolsItems.iridium, FronosItems.fronos_item, 5, "fronos:iridium_axe");
        FronosToolsItems.iridium_hoe = new ItemHoeMP("iridium_hoe", FronosToolsItems.iridium, FronosItems.fronos_item, 5, "fronos:iridium_hoe");
        FronosToolsItems.iridium_shovel = new ItemShovelMP("iridium_spade", FronosToolsItems.iridium, FronosItems.fronos_item, 5, "fronos:iridium_shovel");
        FronosToolsItems.iridium_sword = new ItemSwordMP("iridium_sword", FronosToolsItems.iridium, FronosItems.fronos_item, 5, "fronos:iridium_sword");
        FronosToolsItems.fronos_rock_pickaxe = new ItemPickaxeMP("fronos_rock_pickaxe", FronosToolsItems.fronos_rock, Item.getItemFromBlock(FronosBlocks.fronos_block), 1, "fronos:fronos_rock_pickaxe");
        FronosToolsItems.fronos_rock_axe = new ItemAxeMP("fronos_rock_axe", FronosToolsItems.fronos_rock, Item.getItemFromBlock(FronosBlocks.fronos_block), 1, "fronos:fronos_rock_axe");
        FronosToolsItems.fronos_rock_hoe = new ItemHoeMP("fronos_rock_hoe", FronosToolsItems.fronos_rock, Item.getItemFromBlock(FronosBlocks.fronos_block), 1, "fronos:fronos_rock_hoe");
        FronosToolsItems.fronos_rock_shovel = new ItemShovelMP("fronos_rock_shovel", FronosToolsItems.fronos_rock, Item.getItemFromBlock(FronosBlocks.fronos_block), 1, "fronos:fronos_rock_shovel");
        FronosToolsItems.fronos_rock_sword = new ItemSwordMP("fronos_rock_sword", FronosToolsItems.fronos_rock, Item.getItemFromBlock(FronosBlocks.fronos_block), 1, "fronos:fronos_rock_sword");
    }

    private static void registerHarvestLevels()
    {
        FronosToolsItems.candy_pickaxe.setHarvestLevel("pickaxe", 1);
        FronosToolsItems.candy_axe.setHarvestLevel("axe", 1);
        FronosToolsItems.candy_shovel.setHarvestLevel("shovel", 1);
        FronosToolsItems.black_diamond_pickaxe.setHarvestLevel("pickaxe", 4);
        FronosToolsItems.black_diamond_axe.setHarvestLevel("axe", 4);
        FronosToolsItems.black_diamond_shovel.setHarvestLevel("shovel", 4);
        FronosToolsItems.iridium_pickaxe.setHarvestLevel("pickaxe", 4);
        FronosToolsItems.iridium_axe.setHarvestLevel("axe", 4);
        FronosToolsItems.iridium_shovel.setHarvestLevel("shovel", 4);
        FronosToolsItems.fronos_rock_pickaxe.setHarvestLevel("pickaxe", 1);
        FronosToolsItems.fronos_rock_axe.setHarvestLevel("axe", 1);
        FronosToolsItems.fronos_rock_shovel.setHarvestLevel("shovel", 1);
    }

    private static void registerItems()
    {
        RegisterHelper.registerItem(FronosToolsItems.candy_pickaxe);
        RegisterHelper.registerItem(FronosToolsItems.candy_axe);
        RegisterHelper.registerItem(FronosToolsItems.candy_hoe);
        RegisterHelper.registerItem(FronosToolsItems.candy_shovel);
        RegisterHelper.registerItem(FronosToolsItems.candy_sword);
        RegisterHelper.registerItem(FronosToolsItems.black_diamond_pickaxe);
        RegisterHelper.registerItem(FronosToolsItems.black_diamond_axe);
        RegisterHelper.registerItem(FronosToolsItems.black_diamond_hoe);
        RegisterHelper.registerItem(FronosToolsItems.black_diamond_shovel);
        RegisterHelper.registerItem(FronosToolsItems.black_diamond_sword);
        RegisterHelper.registerItem(FronosToolsItems.iridium_pickaxe);
        RegisterHelper.registerItem(FronosToolsItems.iridium_axe);
        RegisterHelper.registerItem(FronosToolsItems.iridium_hoe);
        RegisterHelper.registerItem(FronosToolsItems.iridium_shovel);
        RegisterHelper.registerItem(FronosToolsItems.iridium_sword);
        RegisterHelper.registerItem(FronosToolsItems.fronos_rock_sword);
        RegisterHelper.registerItem(FronosToolsItems.fronos_rock_shovel);
        RegisterHelper.registerItem(FronosToolsItems.fronos_rock_pickaxe);
        RegisterHelper.registerItem(FronosToolsItems.fronos_rock_axe);
        RegisterHelper.registerItem(FronosToolsItems.fronos_rock_hoe);
    }
}