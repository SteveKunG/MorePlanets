/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 *
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.planets.siriusb.recipe;

import cpw.mods.fml.common.registry.GameRegistry;
import micdoodle8.mods.galacticraft.core.items.GCItems;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import stevekung.mods.moreplanets.core.init.MPBlocks;
import stevekung.mods.moreplanets.planets.siriusb.blocks.SiriusBBlocks;
import stevekung.mods.moreplanets.planets.siriusb.items.SiriusBItems;
import stevekung.mods.moreplanets.planets.siriusb.items.armor.SiriusBArmorItems;
import stevekung.mods.moreplanets.planets.siriusb.items.tools.SiriusBToolsItems;

public class CraftingRecipesSiriusB
{
    public static void loadRecipes()
    {
        CraftingRecipesSiriusB.addBlockRecipes();
        CraftingRecipesSiriusB.addItemRecipes();
        CraftingRecipesSiriusB.addBlockSmelting();
        CraftingRecipesSiriusB.addItemSmelting();
    }

    private static void addBlockRecipes()
    {
        // Blocks
        GameRegistry.addRecipe(new ItemStack(SiriusBBlocks.sirius_b_block, 1, 8), new Object[] { "SSS", "SSS", "SSS", 'S', new ItemStack(SiriusBItems.sirius_b_item, 1, 3) });
        GameRegistry.addRecipe(new ItemStack(SiriusBBlocks.sirius_glowstone), new Object[] { "GG", "GG", 'G', new ItemStack(SiriusBItems.sirius_glowstone_dust) });
        GameRegistry.addRecipe(new ItemStack(SiriusBBlocks.sirius_redstone_lamp_off), new Object[] { " R ", "RGR", " R ", 'G', new ItemStack(SiriusBBlocks.sirius_glowstone), 'R', new ItemStack(Items.redstone) });

        // Walls
        GameRegistry.addRecipe(new ItemStack(MPBlocks.stone_wall, 6, 12), new Object[] { "CCC", "CCC", 'C', new ItemStack(SiriusBBlocks.sirius_b_block, 1, 3) });
        GameRegistry.addRecipe(new ItemStack(MPBlocks.dungeon_brick_wall, 6, 6), new Object[] { "CCC", "CCC", 'C', new ItemStack(SiriusBBlocks.sirius_b_block, 1, 9) });

        // Slabs
        GameRegistry.addRecipe(new ItemStack(MPBlocks.stone_slab_half2, 6, 4), new Object[] { "CCC", 'C', new ItemStack(SiriusBBlocks.sirius_b_block, 1, 3) });
        GameRegistry.addRecipe(new ItemStack(MPBlocks.dungeon_brick_slab_half, 6, 6), new Object[] { "CCC", 'C', new ItemStack(SiriusBBlocks.sirius_b_block, 1, 9) });

        // Sirius B Cobblestone Stairs
        GameRegistry.addRecipe(new ItemStack(SiriusBBlocks.sirius_cobblestone_stairs, 4), new Object[] { "  X", " XX", "XXX", 'X', new ItemStack(SiriusBBlocks.sirius_b_block, 1, 3) });
        GameRegistry.addRecipe(new ItemStack(SiriusBBlocks.sirius_cobblestone_stairs, 4), new Object[] { "X  ", "XX ", "XXX", 'X', new ItemStack(SiriusBBlocks.sirius_b_block, 1, 3) });

        // Sirius B Dungeon Brick Stairs
        GameRegistry.addRecipe(new ItemStack(SiriusBBlocks.sirius_dungeon_brick_stairs, 4), new Object[] { "  X", " XX", "XXX", 'X', new ItemStack(SiriusBBlocks.sirius_b_block, 1, 9) });
        GameRegistry.addRecipe(new ItemStack(SiriusBBlocks.sirius_dungeon_brick_stairs, 4), new Object[] { "X  ", "XX ", "XXX", 'X', new ItemStack(SiriusBBlocks.sirius_b_block, 1, 9) });
    }

    private static void addItemRecipes()
    {
        // Items
        GameRegistry.addRecipe(new ItemStack(SiriusBItems.sirius_b_item, 1, 1), new Object[] { "SSS", "SSS", "SSS", 'S', new ItemStack(SiriusBItems.sirius_b_item, 1, 0) });
        GameRegistry.addRecipe(new ItemStack(Items.diamond), new Object[] { "LLL", "LLL", "LLL", 'L', new ItemStack(SiriusBItems.sirius_b_item, 1, 1) });
        GameRegistry.addRecipe(new ItemStack(SiriusBItems.sirius_obsidian_bucket), new Object[] { "O O", " O ", 'O', new ItemStack(SiriusBBlocks.sirius_obsidian) });
        GameRegistry.addShapelessRecipe(new ItemStack(SiriusBItems.sirius_b_item, 9, 3), new ItemStack(SiriusBBlocks.sirius_b_block, 1, 8) );
        GameRegistry.addRecipe(new ItemStack(SiriusBItems.sirius_b_item, 2, 5), new Object[] { "S", "S", 'S', new ItemStack(SiriusBItems.sirius_b_item, 1, 3) });

        // Armor
        GameRegistry.addRecipe(new ItemStack(SiriusBArmorItems.sulfur_helmet, 1, 0), new Object[] { "XXX", "X X", 'X', new ItemStack(SiriusBItems.sirius_b_item, 1, 4) });
        GameRegistry.addRecipe(new ItemStack(SiriusBArmorItems.sulfur_chestplate, 1, 0), new Object[] { "X X", "XXX", "XXX", 'X', new ItemStack(SiriusBItems.sirius_b_item, 1, 4) });
        GameRegistry.addRecipe(new ItemStack(SiriusBArmorItems.sulfur_leggings, 1, 0), new Object[] { "XXX", "X X", "X X", 'X', new ItemStack(SiriusBItems.sirius_b_item, 1, 4) });
        GameRegistry.addRecipe(new ItemStack(SiriusBArmorItems.sulfur_boots, 1, 0), new Object[] { "X X", "X X", 'X', new ItemStack(SiriusBItems.sirius_b_item, 1, 4) });
        GameRegistry.addRecipe(new ItemStack(SiriusBArmorItems.breathable_sulfur_helmet), new Object[] { "PPP", "POP", 'O', new ItemStack(GCItems.oxMask), 'P', new ItemStack(SiriusBItems.sirius_b_item, 1, 4) });

        // Tools
        GameRegistry.addRecipe(new ItemStack(SiriusBToolsItems.sulfur_hoe), new Object[] { "XX", " Y", " Y", 'X', new ItemStack(SiriusBItems.sirius_b_item, 1, 4), 'Y', new ItemStack(SiriusBItems.sirius_b_item, 1, 5) });
        GameRegistry.addRecipe(new ItemStack(SiriusBToolsItems.sulfur_hoe), new Object[] { "XX", "Y ", "Y ", 'X', new ItemStack(SiriusBItems.sirius_b_item, 1, 4), 'Y', new ItemStack(SiriusBItems.sirius_b_item, 1, 5) });
        GameRegistry.addRecipe(new ItemStack(SiriusBToolsItems.sulfur_axe), new Object[] { "XX", "XY", " Y", 'X', new ItemStack(SiriusBItems.sirius_b_item, 1, 4), 'Y', new ItemStack(SiriusBItems.sirius_b_item, 1, 5) });
        GameRegistry.addRecipe(new ItemStack(SiriusBToolsItems.sulfur_axe), new Object[] { "XX", "YX", "Y ", 'X', new ItemStack(SiriusBItems.sirius_b_item, 1, 4), 'Y', new ItemStack(SiriusBItems.sirius_b_item, 1, 5) });
        GameRegistry.addRecipe(new ItemStack(SiriusBToolsItems.sulfur_pickaxe), new Object[] { "XXX", " Y ", " Y ", 'X', new ItemStack(SiriusBItems.sirius_b_item, 1, 4), 'Y', new ItemStack(SiriusBItems.sirius_b_item, 1, 5) });
        GameRegistry.addRecipe(new ItemStack(SiriusBToolsItems.sulfur_sword), new Object[] { "X", "X", "Y", 'X', new ItemStack(SiriusBItems.sirius_b_item, 1, 4), 'Y', new ItemStack(SiriusBItems.sirius_b_item, 1, 5) });
        GameRegistry.addRecipe(new ItemStack(SiriusBToolsItems.sulfur_shovel), new Object[] { "X", "Y", "Y", 'X', new ItemStack(SiriusBItems.sirius_b_item, 1, 4), 'Y', new ItemStack(SiriusBItems.sirius_b_item, 1, 5) });
    }

    private static void addBlockSmelting()
    {
        GameRegistry.addSmelting(new ItemStack(SiriusBBlocks.sirius_b_block, 1, 3), new ItemStack(SiriusBBlocks.sirius_b_block, 1, 2), 0.5F);
    }

    private static void addItemSmelting()
    {
        GameRegistry.addSmelting(new ItemStack(SiriusBItems.sirius_b_item, 1, 2), new ItemStack(SiriusBItems.sirius_b_item, 1, 3), 0.7F);
        GameRegistry.addSmelting(new ItemStack(SiriusBBlocks.sirius_b_block, 1, 4), new ItemStack(SiriusBItems.sirius_b_item, 1, 3), 0.7F);
        GameRegistry.addSmelting(new ItemStack(SiriusBBlocks.sirius_b_block, 1, 5), new ItemStack(Items.diamond), 0.7F);
        GameRegistry.addSmelting(new ItemStack(SiriusBBlocks.sirius_b_block, 1, 6), new ItemStack(SiriusBItems.sirius_glowstone_dust), 0.8F);
    }
}