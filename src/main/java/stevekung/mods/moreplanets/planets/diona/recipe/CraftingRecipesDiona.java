/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 *
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.planets.diona.recipe;

import java.util.HashMap;

import cpw.mods.fml.common.registry.GameRegistry;
import micdoodle8.mods.galacticraft.core.blocks.GCBlocks;
import micdoodle8.mods.galacticraft.core.items.GCItems;
import micdoodle8.mods.galacticraft.planets.asteroids.items.AsteroidsItems;
import micdoodle8.mods.galacticraft.planets.mars.items.MarsItems;
import net.minecraft.block.BlockColored;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import stevekung.mods.moreplanets.asteroids.darkasteroids.blocks.DarkAsteroidsBlocks;
import stevekung.mods.moreplanets.core.config.ConfigManagerMP;
import stevekung.mods.moreplanets.core.init.MPBlocks;
import stevekung.mods.moreplanets.core.init.MPItems;
import stevekung.mods.moreplanets.moons.europa.blocks.EuropaBlocks;
import stevekung.mods.moreplanets.moons.koentus.blocks.KoentusBlocks;
import stevekung.mods.moreplanets.moons.koentus.items.KoentusItems;
import stevekung.mods.moreplanets.planets.diona.blocks.DionaBlocks;
import stevekung.mods.moreplanets.planets.diona.items.DionaItems;
import stevekung.mods.moreplanets.planets.diona.items.armor.DionaArmorItems;
import stevekung.mods.moreplanets.planets.diona.items.tools.DionaToolsItems;
import stevekung.mods.moreplanets.planets.fronos.blocks.FronosBlocks;
import stevekung.mods.moreplanets.planets.kapteynb.items.KapteynBItems;
import stevekung.mods.moreplanets.planets.nibiru.blocks.NibiruBlocks;
import stevekung.mods.moreplanets.planets.polongnius.items.PolongniusItems;

public class CraftingRecipesDiona
{
    public static void loadRecipes()
    {
        if (ConfigManagerMP.enableTier4RocketRecipe)
        {
            CraftingRecipesDiona.addTier4RocketRecipes();
            CraftingRecipesDiona.addTier4RocketNoFlagRecipes();
        }
        CraftingRecipesDiona.addBlockRecipes();
        CraftingRecipesDiona.addItemRecipes();
        CraftingRecipesDiona.addBlockSmelting();
        CraftingRecipesDiona.addItemSmelting();
    }

    private static void addBlockRecipes()
    {
        // Blocks
        GameRegistry.addRecipe(new ItemStack(DionaBlocks.diona_block, 1, 10), new Object[] { "QQQ", "QQQ", "QQQ", 'Q', new ItemStack(DionaItems.diona_item, 1, 0) });
        GameRegistry.addRecipe(new ItemStack(DionaBlocks.fronisium_block), new Object[] { "FFF", "FFF", "FFF", 'F', new ItemStack(DionaItems.diona_item, 1, 1) });
        GameRegistry.addRecipe(new ItemStack(DionaBlocks.diona_block, 4, 11), new Object[] { "   ", " S ", " Q ", 'S', new ItemStack(Blocks.stone), 'Q', new ItemStack(DionaItems.diona_item, 1, 2) });
        GameRegistry.addRecipe(new ItemStack(DionaBlocks.diona_block, 4, 12), new Object[] { "QQ", "QQ", 'Q', new ItemStack(DionaBlocks.diona_block, 1, 11) });
        GameRegistry.addRecipe(new ItemStack(DionaBlocks.fronisium_tnt, 1), new Object[] { "GFG", "FGF", "GFG", 'G', new ItemStack(Items.gunpowder), 'F', new ItemStack(DionaItems.diona_item, 1, 3) });
        GameRegistry.addRecipe(new ItemStack(DionaBlocks.diona_block, 1, 13), new Object[] { "Q", "Q", 'Q', new ItemStack(MPBlocks.stone_slab_half, 1, 1) });
        GameRegistry.addRecipe(new ItemStack(MPBlocks.space_decoration_block, 4, 0), new Object[] { "TT", "TT", 'T', new ItemStack(GCBlocks.basicBlock, 1, 4) });
        GameRegistry.addRecipe(new ItemStack(MPBlocks.space_decoration_block, 8, 1), new Object[] { "TTT", "TAT", "TTT", 'T', new ItemStack(MPBlocks.space_decoration_block, 1, 0), 'A', new ItemStack(GCItems.basicItem, 1, 8) });

        // Diona Cobblestone Stairs
        GameRegistry.addRecipe(new ItemStack(DionaBlocks.diona_cobblestone_stairs, 4), new Object[] { "  X", " XX", "XXX", 'X', new ItemStack(DionaBlocks.diona_block, 1, 3) });
        GameRegistry.addRecipe(new ItemStack(DionaBlocks.diona_cobblestone_stairs, 4), new Object[] { "X  ", "XX ", "XXX", 'X', new ItemStack(DionaBlocks.diona_block, 1, 3) });

        // Chiseled Quontonium Stairs
        GameRegistry.addRecipe(new ItemStack(DionaBlocks.chiseled_quontonium_stairs, 4), new Object[] { "  X", " XX", "XXX", 'X', new ItemStack(DionaBlocks.diona_block, 1, 13) });
        GameRegistry.addRecipe(new ItemStack(DionaBlocks.chiseled_quontonium_stairs, 4), new Object[] { "X  ", "XX ", "XXX", 'X', new ItemStack(DionaBlocks.diona_block, 1, 13) });

        // Quontonium Brick Stairs
        GameRegistry.addRecipe(new ItemStack(DionaBlocks.quontonium_brick_stairs, 4), new Object[] { "  X", " XX", "XXX", 'X', new ItemStack(DionaBlocks.diona_block, 1, 12) });
        GameRegistry.addRecipe(new ItemStack(DionaBlocks.quontonium_brick_stairs, 4), new Object[] { "X  ", "XX ", "XXX", 'X', new ItemStack(DionaBlocks.diona_block, 1, 12) });

        // Diona Dungeon Brick Stairs
        GameRegistry.addRecipe(new ItemStack(DionaBlocks.diona_dungeon_brick_stairs, 4), new Object[] { "  X", " XX", "XXX", 'X', new ItemStack(DionaBlocks.diona_block, 1, 14) });
        GameRegistry.addRecipe(new ItemStack(DionaBlocks.diona_dungeon_brick_stairs, 4), new Object[] { "X  ", "XX ", "XXX", 'X', new ItemStack(DionaBlocks.diona_block, 1, 14) });

        // Slabs
        GameRegistry.addRecipe(new ItemStack(MPBlocks.stone_slab_half, 6, 0), new Object[] { "XXX", 'X', new ItemStack(DionaBlocks.diona_block, 1, 3) });
        GameRegistry.addRecipe(new ItemStack(MPBlocks.stone_slab_half, 6, 1), new Object[] { "XXX", 'X', new ItemStack(DionaBlocks.diona_block, 1, 12) });
        GameRegistry.addRecipe(new ItemStack(MPBlocks.stone_slab_half, 6, 2), new Object[] { "XXX", 'X', new ItemStack(DionaBlocks.diona_block, 1, 13) });
        GameRegistry.addRecipe(new ItemStack(MPBlocks.dungeon_brick_slab_half, 6, 0), new Object[] { "XXX", 'X', new ItemStack(DionaBlocks.diona_block, 1, 14) });

        // Walls
        GameRegistry.addRecipe(new ItemStack(MPBlocks.stone_wall, 6, 0), new Object[] { "XXX", "XXX", 'X', new ItemStack(DionaBlocks.diona_block, 1, 3) });
        GameRegistry.addRecipe(new ItemStack(MPBlocks.stone_wall, 6, 1), new Object[] { "XXX", "XXX", 'X', new ItemStack(DionaBlocks.diona_block, 1, 12) });
        GameRegistry.addRecipe(new ItemStack(MPBlocks.stone_wall, 6, 2), new Object[] { "XXX", "XXX", 'X', new ItemStack(DionaBlocks.diona_block, 1, 13) });
        GameRegistry.addRecipe(new ItemStack(MPBlocks.dungeon_brick_wall, 6, 0), new Object[] { "XXX", "XXX", 'X', new ItemStack(DionaBlocks.diona_block, 1, 14) });

        for (int i = 0; i < 16; ++i)
        {
            if (i != 15)
            {
                GameRegistry.addRecipe(new ItemStack(MPBlocks.tinted_glass, 8, BlockColored.func_150032_b(i)), new Object[] {"III", "IDI", "III", 'I', new ItemStack(MPBlocks.tinted_glass, 1, 0), 'D', new ItemStack(Items.dye, 1, i)});
            }
            GameRegistry.addRecipe(new ItemStack(MPBlocks.tinted_glass_pane, 16, i), new Object[] {"GGG", "GGG", 'G', new ItemStack(MPBlocks.tinted_glass, 1, i)});
        }
        GameRegistry.addRecipe(new ItemStack(MPBlocks.tinted_glass, 8, 0), new Object[] {"III", "IDI", "III", 'I', new ItemStack(Blocks.glass), 'D', new ItemStack(MarsItems.marsItemBasic, 1, 5)});
    }

    private static void addItemRecipes()
    {
        // Items
        GameRegistry.addRecipe(new ItemStack(DionaItems.diona_item, 3, 5), new Object[] { "RRR", "CCC", "RRR", 'C', new ItemStack(GCItems.canvas), 'R', new ItemStack(Items.dye, 1, 1) });
        GameRegistry.addRecipe(new ItemStack(DionaItems.diona_item, 3, 6), new Object[] { "WWW", "CCC", "WWW", 'C', new ItemStack(GCItems.canvas), 'W', new ItemStack(Items.dye, 1, 15) });
        GameRegistry.addRecipe(new ItemStack(DionaItems.diona_item, 3, 7), new Object[] { "BBB", "CCC", "BBB", 'C', new ItemStack(GCItems.canvas), 'B', new ItemStack(Items.dye, 1, 4) });
        GameRegistry.addRecipe(new ItemStack(DionaItems.diona_item, 1, 8), new Object[] { "RRR", "WWW", "BBB", 'R', new ItemStack(DionaItems.diona_item, 1, 5), 'W', new ItemStack(DionaItems.diona_item, 1, 6), 'B', new ItemStack(DionaItems.diona_item, 1, 7) });
        GameRegistry.addRecipe(new ItemStack(DionaItems.diona_item, 1, 9), new Object[] { "BBB", "WWW", "RRR", 'R', new ItemStack(DionaItems.diona_item, 1, 5), 'W', new ItemStack(DionaItems.diona_item, 1, 6), 'B', new ItemStack(DionaItems.diona_item, 1, 7) });
        GameRegistry.addRecipe(new ItemStack(DionaItems.diona_item, 2, 10), new Object[] { "Q", "Q", 'Q', new ItemStack(DionaItems.diona_item, 1, 0) });
        GameRegistry.addRecipe(new ItemStack(DionaItems.diona_item, 2, 11), new Object[] { "F", "F", 'F', new ItemStack(DionaItems.diona_item, 1, 1) });
        GameRegistry.addRecipe(new ItemStack(MPItems.flag, 1), new Object[] { "PTT", "PBB", "P  ", 'P', new ItemStack(GCItems.flagPole, 1, 0), 'T', new ItemStack(DionaItems.diona_item, 1, 8), 'B', new ItemStack(DionaItems.diona_item, 1, 9) });
        GameRegistry.addRecipe(new ItemStack(DionaItems.tier4_rocket_module, 1, 0), new Object[] { "F", "C", 'F', new ItemStack(MPItems.flag, 1, 0), 'C', new ItemStack(DionaItems.tier4_rocket_module, 1, 4) });
        GameRegistry.addRecipe(new ItemStack(DionaItems.tier4_rocket_module, 1, 3), new Object[] { "DMD", "DCD", "TAT", 'M', new ItemStack(Blocks.diamond_block), 'D', new ItemStack(AsteroidsItems.basicItem, 1, 6), 'C', new ItemStack(GCItems.fuelCanister, 1, 1), 'T', new ItemStack(DionaItems.tier4_rocket_module, 1, 1), 'A', new ItemStack(GCItems.oxygenVent) });
        GameRegistry.addRecipe(new ItemStack(DionaItems.tier4_rocket_module, 1, 2), new Object[] { "BS ", "TCT", "TAT", 'B', new ItemStack(Blocks.stone_button), 'S', new ItemStack(Items.flint_and_steel), 'T', new ItemStack(DionaItems.tier4_rocket_module, 1, 1), 'A', new ItemStack(GCItems.oxygenVent), 'C', new ItemStack(GCItems.canister, 1, 0) });
        GameRegistry.addRecipe(new ItemStack(DionaItems.tier4_rocket_module, 1, 2), new Object[] { " SB", "TCT", "TAT", 'B', new ItemStack(Blocks.stone_button), 'S', new ItemStack(Items.flint_and_steel), 'T', new ItemStack(DionaItems.tier4_rocket_module, 1, 1), 'A', new ItemStack(GCItems.oxygenVent), 'C', new ItemStack(GCItems.canister, 1, 0) });
        GameRegistry.addRecipe(new ItemStack(DionaItems.tier4_rocket_module, 1, 4), new Object[] { " T ", " D ", "D D", 'T', new ItemStack(DionaItems.diona_item, 1, 12), 'D', new ItemStack(DionaItems.tier4_rocket_module, 1, 1) });
        GameRegistry.addShapelessRecipe(new ItemStack(DionaItems.diona_item, 9, 0), new ItemStack(DionaBlocks.diona_block, 1, 10) );
        GameRegistry.addShapelessRecipe(new ItemStack(DionaItems.diona_item, 9, 1), new ItemStack(DionaBlocks.fronisium_block) );
        GameRegistry.addRecipe(new ItemStack(DionaItems.laser_charge, 8, 0), new Object[] { " R", "I ", 'I', new ItemStack(Items.iron_ingot), 'R', new ItemStack(Items.redstone) });
        GameRegistry.addRecipe(new ItemStack(DionaItems.laser_charge, 8, 1), new Object[] { " P", "I ", 'I', new ItemStack(Items.iron_ingot), 'P', new ItemStack(PolongniusItems.polongnius_item, 1, 1) });
        GameRegistry.addRecipe(new ItemStack(DionaItems.laser_charge, 8, 2), new Object[] { " E", "I ", 'I', new ItemStack(Items.iron_ingot), 'E', new ItemStack(KoentusItems.koentus_item, 1, 1) });
        GameRegistry.addRecipe(new ItemStack(DionaItems.laser_charge, 12, 3), new Object[] { " U", "I ", 'I', new ItemStack(Items.iron_ingot), 'U', new ItemStack(KapteynBItems.kapteyn_b_item, 1, 1) });
        GameRegistry.addRecipe(new ItemStack(DionaItems.laser_gun), new Object[] { "C  ", " DT", "  D", 'C', new ItemStack(AsteroidsItems.basicItem, 1, 8), 'D', new ItemStack(MarsItems.marsItemBasic, 1, 5), 'T', new ItemStack(AsteroidsItems.basicItem, 1, 6) });
        GameRegistry.addRecipe(new ItemStack(DionaItems.diona_item, 1, 12), new Object[] { "G", "T", 'T', new ItemStack(Blocks.redstone_torch), 'G', new ItemStack(Items.dye, 1, 10) });
        GameRegistry.addRecipe(new ItemStack(DionaItems.tier4_rocket_module, 1, 5), new Object[] { "BS ", "TCT", "TAT", 'B', new ItemStack(Blocks.stone_button), 'S', new ItemStack(Items.flint_and_steel), 'T', new ItemStack(DionaItems.diona_item, 1, 4), 'A', new ItemStack(GCItems.oxygenVent), 'C', new ItemStack(GCItems.canister, 1, 0) });
        GameRegistry.addRecipe(new ItemStack(DionaItems.tier4_rocket_module, 1, 5), new Object[] { " SB", "TCT", "TAT", 'B', new ItemStack(Blocks.stone_button), 'S', new ItemStack(Items.flint_and_steel), 'T', new ItemStack(DionaItems.diona_item, 1, 4), 'A', new ItemStack(GCItems.oxygenVent), 'C', new ItemStack(GCItems.canister, 1, 0) });
        GameRegistry.addRecipe(new ItemStack(DionaItems.tier4_rocket_module, 1, 6), new Object[] { "DMD", "DCD", "TAT", 'M', new ItemStack(DionaBlocks.diona_block, 1, 10), 'D', new ItemStack(DionaItems.diona_item, 1, 2), 'C', new ItemStack(GCItems.fuelCanister, 1, 1), 'T', new ItemStack(DionaItems.diona_item, 1, 4), 'A', new ItemStack(GCItems.oxygenVent) });
        GameRegistry.addRecipe(new ItemStack(MPItems.tier_2_thermal_padding, 1, 0), new Object[] { "XXX", "X X", 'X', new ItemStack(MPItems.desh_thermal_cloth) });
        GameRegistry.addRecipe(new ItemStack(MPItems.tier_2_thermal_padding, 1, 1), new Object[] { "X X", "XXX", "XXX", 'X', new ItemStack(MPItems.desh_thermal_cloth) });
        GameRegistry.addRecipe(new ItemStack(MPItems.tier_2_thermal_padding, 1, 2), new Object[] { "XXX", "X X", "X X", 'X', new ItemStack(MPItems.desh_thermal_cloth) });
        GameRegistry.addRecipe(new ItemStack(MPItems.tier_2_thermal_padding, 1, 3), new Object[] { "X X", "X X", 'X', new ItemStack(MPItems.desh_thermal_cloth) });
        GameRegistry.addRecipe(new ItemStack(MPItems.desh_thermal_cloth), new Object[] { "WXW", "XRX", "WXW", 'X', new ItemStack(MarsItems.marsItemBasic, 1, 5), 'R', Items.redstone, 'W', Blocks.wool });

        // Armor
        GameRegistry.addRecipe(new ItemStack(DionaArmorItems.quontonium_helmet, 1, 0), new Object[] { "QQQ", "Q Q", 'Q', new ItemStack(DionaItems.diona_item, 1, 2) });
        GameRegistry.addRecipe(new ItemStack(DionaArmorItems.quontonium_chestplate, 1, 0), new Object[] { "Q Q", "QQQ", "QQQ", 'Q', new ItemStack(DionaItems.diona_item, 1, 2) });
        GameRegistry.addRecipe(new ItemStack(DionaArmorItems.quontonium_leggings, 1, 0), new Object[] { "QQQ", "Q Q", "Q Q", 'Q', new ItemStack(DionaItems.diona_item, 1, 2) });
        GameRegistry.addRecipe(new ItemStack(DionaArmorItems.quontonium_boots, 1, 0), new Object[] { "Q Q", "Q Q", 'Q', new ItemStack(DionaItems.diona_item, 1, 2) });
        GameRegistry.addRecipe(new ItemStack(DionaArmorItems.fronisium_helmet, 1, 0), new Object[] { "QQQ", "Q Q", 'Q', new ItemStack(DionaItems.diona_item, 1, 3) });
        GameRegistry.addRecipe(new ItemStack(DionaArmorItems.fronisium_chestplate, 1, 0), new Object[] { "Q Q", "QQQ", "QQQ", 'Q', new ItemStack(DionaItems.diona_item, 1, 3) });
        GameRegistry.addRecipe(new ItemStack(DionaArmorItems.fronisium_leggings, 1, 0), new Object[] { "QQQ", "Q Q", "Q Q", 'Q', new ItemStack(DionaItems.diona_item, 1, 3) });
        GameRegistry.addRecipe(new ItemStack(DionaArmorItems.fronisium_boots, 1, 0), new Object[] { "Q Q", "Q Q", 'Q', new ItemStack(DionaItems.diona_item, 1, 3) });
        GameRegistry.addRecipe(new ItemStack(DionaArmorItems.breathable_quontonium_helmet, 1, 0), new Object[] { "QQQ", "QOQ", 'O', new ItemStack(GCItems.oxMask), 'Q', new ItemStack(DionaItems.diona_item, 1, 2) });
        GameRegistry.addRecipe(new ItemStack(DionaArmorItems.breathable_fronisium_helmet, 1, 0), new Object[] { "FFF", "FOF", 'O', new ItemStack(GCItems.oxMask), 'F', new ItemStack(DionaItems.diona_item, 1, 3) });

        // Tools
        GameRegistry.addRecipe(new ItemStack(DionaToolsItems.quontonium_hoe), new Object[] { "XX", " Y", " Y", 'X', new ItemStack(DionaItems.diona_item, 1, 2), 'Y', new ItemStack(DionaItems.diona_item, 1, 10) });
        GameRegistry.addRecipe(new ItemStack(DionaToolsItems.quontonium_hoe), new Object[] { "XX", "Y ", "Y ", 'X', new ItemStack(DionaItems.diona_item, 1, 2), 'Y', new ItemStack(DionaItems.diona_item, 1, 10) });
        GameRegistry.addRecipe(new ItemStack(DionaToolsItems.quontonium_axe), new Object[] { "XX", "XY", " Y", 'X', new ItemStack(DionaItems.diona_item, 1, 2), 'Y', new ItemStack(DionaItems.diona_item, 1, 10) });
        GameRegistry.addRecipe(new ItemStack(DionaToolsItems.quontonium_axe), new Object[] { "XX", "YX", "Y ", 'X', new ItemStack(DionaItems.diona_item, 1, 2), 'Y', new ItemStack(DionaItems.diona_item, 1, 10) });
        GameRegistry.addRecipe(new ItemStack(DionaToolsItems.quontonium_pickaxe), new Object[] { "XXX", " Y ", " Y ", 'X', new ItemStack(DionaItems.diona_item, 1, 2), 'Y', new ItemStack(DionaItems.diona_item, 1, 10) });
        GameRegistry.addRecipe(new ItemStack(DionaToolsItems.quontonium_sword), new Object[] { "X", "X", "Y", 'X', new ItemStack(DionaItems.diona_item, 1, 2), 'Y', new ItemStack(DionaItems.diona_item, 1, 10) });
        GameRegistry.addRecipe(new ItemStack(DionaToolsItems.quontonium_shovel), new Object[] { "X", "Y", "Y", 'X', new ItemStack(DionaItems.diona_item, 1, 2), 'Y', new ItemStack(DionaItems.diona_item, 1, 10) });
        GameRegistry.addRecipe(new ItemStack(DionaToolsItems.fronisium_hoe), new Object[] { "XX", " Y", " Y", 'X', new ItemStack(DionaItems.diona_item, 1, 3), 'Y', new ItemStack(DionaItems.diona_item, 1, 11) });
        GameRegistry.addRecipe(new ItemStack(DionaToolsItems.fronisium_hoe), new Object[] { "XX", "Y ", "Y ", 'X', new ItemStack(DionaItems.diona_item, 1, 3), 'Y', new ItemStack(DionaItems.diona_item, 1, 11) });
        GameRegistry.addRecipe(new ItemStack(DionaToolsItems.fronisium_axe), new Object[] { "XX", "XY", " Y", 'X', new ItemStack(DionaItems.diona_item, 1, 3), 'Y', new ItemStack(DionaItems.diona_item, 1, 11) });
        GameRegistry.addRecipe(new ItemStack(DionaToolsItems.fronisium_axe), new Object[] { "XX", "YX", "Y ", 'X', new ItemStack(DionaItems.diona_item, 1, 3), 'Y', new ItemStack(DionaItems.diona_item, 1, 11) });
        GameRegistry.addRecipe(new ItemStack(DionaToolsItems.fronisium_pickaxe), new Object[] { "XXX", " Y ", " Y ", 'X', new ItemStack(DionaItems.diona_item, 1, 3), 'Y', new ItemStack(DionaItems.diona_item, 1, 11) });
        GameRegistry.addRecipe(new ItemStack(DionaToolsItems.fronisium_sword), new Object[] { "X", "X", "Y", 'X', new ItemStack(DionaItems.diona_item, 1, 3), 'Y', new ItemStack(DionaItems.diona_item, 1, 11) });
        GameRegistry.addRecipe(new ItemStack(DionaToolsItems.fronisium_shovel), new Object[] { "X", "Y", "Y", 'X', new ItemStack(DionaItems.diona_item, 1, 3), 'Y', new ItemStack(DionaItems.diona_item, 1, 11) });
    }

    private static void addBlockSmelting()
    {
        GameRegistry.addSmelting(new ItemStack(DionaBlocks.diona_block, 1, 3), new ItemStack(DionaBlocks.diona_block, 1, 2), 0.4F);
    }

    private static void addItemSmelting()
    {
        GameRegistry.addSmelting(new ItemStack(DionaBlocks.diona_block, 1, 4), new ItemStack(DionaItems.diona_item, 1, 0), 0.8F);
        GameRegistry.addSmelting(new ItemStack(DionaBlocks.diona_block, 1, 5), new ItemStack(DionaItems.diona_item, 1, 1), 0.8F);
        GameRegistry.addSmelting(new ItemStack(DionaBlocks.diona_block, 1, 6), new ItemStack(GCItems.basicItem, 1, 4), 0.8F);
        GameRegistry.addSmelting(new ItemStack(DionaBlocks.diona_block, 1, 7), new ItemStack(GCItems.basicItem, 1, 3), 0.8F);
        GameRegistry.addSmelting(new ItemStack(DionaBlocks.diona_block, 1, 8), new ItemStack(GCItems.basicItem, 1, 2), 0.6F);
        GameRegistry.addSmelting(new ItemStack(DionaBlocks.diona_block, 1, 9), new ItemStack(GCItems.basicItem, 1, 5), 0.8F);
        GameRegistry.addSmelting(NibiruBlocks.nibiru_log, new ItemStack(Items.coal, 1, 1), 0.15F);
        GameRegistry.addSmelting(KoentusBlocks.crystal_log, new ItemStack(Items.coal, 1, 1), 0.15F);
        GameRegistry.addSmelting(FronosBlocks.fronos_log, new ItemStack(Items.coal, 1, 1), 0.15F);
        GameRegistry.addSmelting(EuropaBlocks.europa_log, new ItemStack(Items.coal, 1, 1), 0.15F);
        GameRegistry.addSmelting(DarkAsteroidsBlocks.alien_log, new ItemStack(Items.coal, 1, 1), 0.15F);
    }

    private static void addTier4RocketRecipes()
    {
        HashMap<Integer, ItemStack> input = new HashMap<Integer, ItemStack>();
        input.put(1, new ItemStack(DionaItems.tier4_rocket_module, 1, 0));
        input.put(2, new ItemStack(DionaItems.tier4_rocket_module, 1, 1));
        input.put(3, new ItemStack(DionaItems.tier4_rocket_module, 1, 1));
        input.put(4, new ItemStack(DionaItems.tier4_rocket_module, 1, 1));
        input.put(5, new ItemStack(DionaItems.tier4_rocket_module, 1, 1));
        input.put(6, new ItemStack(DionaItems.tier4_rocket_module, 1, 1));
        input.put(7, new ItemStack(DionaItems.tier4_rocket_module, 1, 1));
        input.put(8, new ItemStack(DionaItems.tier4_rocket_module, 1, 1));
        input.put(9, new ItemStack(DionaItems.tier4_rocket_module, 1, 1));
        input.put(10, new ItemStack(DionaItems.tier4_rocket_module, 1, 1));
        input.put(11, new ItemStack(DionaItems.tier4_rocket_module, 1, 1));
        input.put(12, new ItemStack(DionaItems.tier4_rocket_module, 1, 3));
        input.put(13, new ItemStack(AsteroidsItems.basicItem, 1, 2));
        input.put(14, new ItemStack(AsteroidsItems.basicItem, 1, 2));
        input.put(15, new ItemStack(DionaItems.tier4_rocket_module, 1, 2));
        input.put(16, new ItemStack(DionaItems.tier4_rocket_module, 1, 3));
        input.put(17, new ItemStack(AsteroidsItems.basicItem, 1, 2));
        input.put(18, new ItemStack(AsteroidsItems.basicItem, 1, 2));
        input.put(19, null);
        input.put(20, null);
        input.put(21, null);
        Tier4RocketRecipes.addTier4RocketRecipe(new ItemStack(DionaItems.tier4_rocket, 1, 0), input);

        HashMap<Integer, ItemStack> input2 = new HashMap<Integer, ItemStack>(input);
        input2.put(19, new ItemStack(Blocks.chest));
        input2.put(20, null);
        input2.put(21, null);
        Tier4RocketRecipes.addTier4RocketRecipe(new ItemStack(DionaItems.tier4_rocket, 1, 1), input2);

        input2 = new HashMap<Integer, ItemStack>(input);
        input2.put(19, null);
        input2.put(20, new ItemStack(Blocks.chest));
        input2.put(21, null);
        Tier4RocketRecipes.addTier4RocketRecipe(new ItemStack(DionaItems.tier4_rocket, 1, 1), input2);

        input2 = new HashMap<Integer, ItemStack>(input);
        input2.put(19, null);
        input2.put(20, null);
        input2.put(21, new ItemStack(Blocks.chest));
        Tier4RocketRecipes.addTier4RocketRecipe(new ItemStack(DionaItems.tier4_rocket, 1, 1), input2);

        input2 = new HashMap<Integer, ItemStack>(input);
        input2.put(19, new ItemStack(Blocks.chest));
        input2.put(20, new ItemStack(Blocks.chest));
        input2.put(21, null);
        Tier4RocketRecipes.addTier4RocketRecipe(new ItemStack(DionaItems.tier4_rocket, 1, 2), input2);

        input2 = new HashMap<Integer, ItemStack>(input);
        input2.put(19, new ItemStack(Blocks.chest));
        input2.put(20, null);
        input2.put(21, new ItemStack(Blocks.chest));
        Tier4RocketRecipes.addTier4RocketRecipe(new ItemStack(DionaItems.tier4_rocket, 1, 2), input2);

        input2 = new HashMap<Integer, ItemStack>(input);
        input2.put(19, null);
        input2.put(20, new ItemStack(Blocks.chest));
        input2.put(21, new ItemStack(Blocks.chest));
        Tier4RocketRecipes.addTier4RocketRecipe(new ItemStack(DionaItems.tier4_rocket, 1, 2), input2);

        input2 = new HashMap<Integer, ItemStack>(input);
        input2.put(19, new ItemStack(Blocks.chest));
        input2.put(20, new ItemStack(Blocks.chest));
        input2.put(21, new ItemStack(Blocks.chest));
        Tier4RocketRecipes.addTier4RocketRecipe(new ItemStack(DionaItems.tier4_rocket, 1, 3), input2);
    }

    private static void addTier4RocketNoFlagRecipes()
    {
        HashMap<Integer, ItemStack> input = new HashMap<Integer, ItemStack>();
        input.put(1, new ItemStack(DionaItems.tier4_rocket_module, 1, 4));
        input.put(2, new ItemStack(DionaItems.tier4_rocket_module, 1, 1));
        input.put(3, new ItemStack(DionaItems.tier4_rocket_module, 1, 1));
        input.put(4, new ItemStack(DionaItems.tier4_rocket_module, 1, 1));
        input.put(5, new ItemStack(DionaItems.tier4_rocket_module, 1, 1));
        input.put(6, new ItemStack(DionaItems.tier4_rocket_module, 1, 1));
        input.put(7, new ItemStack(DionaItems.tier4_rocket_module, 1, 1));
        input.put(8, new ItemStack(DionaItems.tier4_rocket_module, 1, 1));
        input.put(9, new ItemStack(DionaItems.tier4_rocket_module, 1, 1));
        input.put(10, new ItemStack(DionaItems.tier4_rocket_module, 1, 1));
        input.put(11, new ItemStack(DionaItems.tier4_rocket_module, 1, 1));
        input.put(12, new ItemStack(DionaItems.tier4_rocket_module, 1, 3));
        input.put(13, new ItemStack(AsteroidsItems.basicItem, 1, 2));
        input.put(14, new ItemStack(AsteroidsItems.basicItem, 1, 2));
        input.put(15, new ItemStack(DionaItems.tier4_rocket_module, 1, 2));
        input.put(16, new ItemStack(DionaItems.tier4_rocket_module, 1, 3));
        input.put(17, new ItemStack(AsteroidsItems.basicItem, 1, 2));
        input.put(18, new ItemStack(AsteroidsItems.basicItem, 1, 2));
        input.put(19, null);
        input.put(20, null);
        input.put(21, null);
        Tier4RocketRecipes.addTier4RocketNoFlagRecipe(new ItemStack(DionaItems.tier4_rocket, 1, 10), input);

        HashMap<Integer, ItemStack> input2 = new HashMap<Integer, ItemStack>(input);
        input2.put(19, new ItemStack(Blocks.chest));
        input2.put(20, null);
        input2.put(21, null);
        Tier4RocketRecipes.addTier4RocketNoFlagRecipe(new ItemStack(DionaItems.tier4_rocket, 1, 11), input2);

        input2 = new HashMap<Integer, ItemStack>(input);
        input2.put(19, null);
        input2.put(20, new ItemStack(Blocks.chest));
        input2.put(21, null);
        Tier4RocketRecipes.addTier4RocketNoFlagRecipe(new ItemStack(DionaItems.tier4_rocket, 1, 11), input2);

        input2 = new HashMap<Integer, ItemStack>(input);
        input2.put(19, null);
        input2.put(20, null);
        input2.put(21, new ItemStack(Blocks.chest));
        Tier4RocketRecipes.addTier4RocketNoFlagRecipe(new ItemStack(DionaItems.tier4_rocket, 1, 11), input2);

        input2 = new HashMap<Integer, ItemStack>(input);
        input2.put(19, new ItemStack(Blocks.chest));
        input2.put(20, new ItemStack(Blocks.chest));
        input2.put(21, null);
        Tier4RocketRecipes.addTier4RocketNoFlagRecipe(new ItemStack(DionaItems.tier4_rocket, 1, 12), input2);

        input2 = new HashMap<Integer, ItemStack>(input);
        input2.put(19, new ItemStack(Blocks.chest));
        input2.put(20, null);
        input2.put(21, new ItemStack(Blocks.chest));
        Tier4RocketRecipes.addTier4RocketNoFlagRecipe(new ItemStack(DionaItems.tier4_rocket, 1, 12), input2);

        input2 = new HashMap<Integer, ItemStack>(input);
        input2.put(19, null);
        input2.put(20, new ItemStack(Blocks.chest));
        input2.put(21, new ItemStack(Blocks.chest));
        Tier4RocketRecipes.addTier4RocketNoFlagRecipe(new ItemStack(DionaItems.tier4_rocket, 1, 12), input2);

        input2 = new HashMap<Integer, ItemStack>(input);
        input2.put(19, new ItemStack(Blocks.chest));
        input2.put(20, new ItemStack(Blocks.chest));
        input2.put(21, new ItemStack(Blocks.chest));
        Tier4RocketRecipes.addTier4RocketNoFlagRecipe(new ItemStack(DionaItems.tier4_rocket, 1, 13), input2);
    }
}