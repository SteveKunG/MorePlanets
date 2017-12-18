/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 *
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.planets.pluto.recipe;

import cpw.mods.fml.common.registry.GameRegistry;
import micdoodle8.mods.galacticraft.core.items.GCItems;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import stevekung.mods.moreplanets.planets.diona.items.DionaItems;
import stevekung.mods.moreplanets.planets.kapteynb.items.KapteynBItems;
import stevekung.mods.moreplanets.planets.mercury.items.MercuryItems;
import stevekung.mods.moreplanets.planets.pluto.blocks.PlutoBlocks;
import stevekung.mods.moreplanets.planets.pluto.items.PlutoItems;

public class CraftingRecipesPluto
{
    public static void loadRecipes()
    {
        CraftingRecipesPluto.addBlockRecipes();
        CraftingRecipesPluto.addItemRecipes();
        CraftingRecipesPluto.addBlockSmelting();
        CraftingRecipesPluto.addItemSmelting();
    }

    private static void addBlockRecipes()
    {
        // Blocks
        GameRegistry.addRecipe(new ItemStack(PlutoBlocks.xeonium_torch, 4), new Object[] { "X", "S", 'X', new ItemStack(PlutoItems.xeonium_dust), 'S', new ItemStack(Items.stick) });
        GameRegistry.addRecipe(new ItemStack(PlutoBlocks.xeonium_glowstone), new Object[] { "DD", "DD", 'D', new ItemStack(PlutoItems.xeonium_dust) });

        // Nibiru Cobblestone Stairs
        //		GameRegistry.addRecipe(new ItemStack(NibiruBlocks.nibiru_cobblestone_stairs, 4), new Object[] { "  X", " XX", "XXX", 'X', new ItemStack(NibiruBlocks.nibiru_block, 1, 3) });
        //		GameRegistry.addRecipe(new ItemStack(NibiruBlocks.nibiru_cobblestone_stairs, 4), new Object[] { "X  ", "XX ", "XXX", 'X', new ItemStack(NibiruBlocks.nibiru_block, 1, 3) });

        // Slabs
        //		GameRegistry.addRecipe(new ItemStack(MPBlocks.stone_slab_half, 6, 4), new Object[] { "XXX", 'X', new ItemStack(NibiruBlocks.nibiru_block, 1, 3) });
        //		GameRegistry.addRecipe(new ItemStack(MPBlocks.dungeon_brick_slab_half, 6, 2), new Object[] { "XXX", 'X', new ItemStack(NibiruBlocks.nibiru_block, 1, 12) });
        //		GameRegistry.addRecipe(new ItemStack(MPBlocks.wooden_slab_half, 6, 0), new Object[] { "XXX", 'X', new ItemStack(NibiruBlocks.nibiru_wooden_planks, 1, 0) });
        //		GameRegistry.addRecipe(new ItemStack(MPBlocks.wooden_slab_half, 6, 1), new Object[] { "XXX", 'X', new ItemStack(NibiruBlocks.nibiru_wooden_planks, 1, 1) });

        // Walls
        //		GameRegistry.addRecipe(new ItemStack(MPBlocks.stone_wall, 6, 4), new Object[] { "XXX", "XXX", 'X', new ItemStack(NibiruBlocks.nibiru_block, 1, 3) });
        //		GameRegistry.addRecipe(new ItemStack(MPBlocks.dungeon_brick_wall, 6, 2), new Object[] { "XXX", "XXX", 'X', new ItemStack(NibiruBlocks.nibiru_block, 1, 12) });
    }

    private static void addItemRecipes()
    {
        // Items
        GameRegistry.addRecipe(new ItemStack(MercuryItems.mercury_item, 1, 7), new Object[] { "MCM", "CAC", "MCM", 'M', new ItemStack(MercuryItems.mercury_item, 1, 5), 'C', new ItemStack(MercuryItems.mercury_item, 1, 6), 'A', new ItemStack(GCItems.basicItem, 1, 14) });
        GameRegistry.addRecipe(new ItemStack(MercuryItems.mercury_item, 1, 6), new Object[] { "PRP", "RDR", "PRP", 'P', new ItemStack(MercuryItems.mercury_item, 1, 4), 'R', new ItemStack(Items.redstone), 'D', new ItemStack(Items.diamond) });
        GameRegistry.addRecipe(new ItemStack(MercuryItems.mercury_item, 1, 6), new Object[] { "PRP", "RDR", "PRP", 'P', new ItemStack(MercuryItems.mercury_item, 1, 5), 'R', new ItemStack(Items.redstone), 'D', new ItemStack(Items.diamond) });
        GameRegistry.addRecipe(new ItemStack(PlutoItems.gravity_boots), new Object[] { "P P", "G G", 'P', new ItemStack(MercuryItems.mercury_item, 1, 4), 'G', new ItemStack(MercuryItems.mercury_item, 1, 7) });
        GameRegistry.addRecipe(new ItemStack(PlutoItems.gravity_boots), new Object[] { "P P", "G G", 'P', new ItemStack(MercuryItems.mercury_item, 1, 5), 'G', new ItemStack(MercuryItems.mercury_item, 1, 7) });
        GameRegistry.addRecipe(new ItemStack(DionaItems.laser_charge, 8, 4), new Object[] { " E", "I ", 'I', new ItemStack(Items.iron_ingot), 'E', new ItemStack(KapteynBItems.kapteyn_b_item, 1, 5) });
        GameRegistry.addShapelessRecipe(new ItemStack(PlutoItems.xeonium_dust, 4), new ItemStack(PlutoItems.pluto_item, 1, 0) );

        // Armor
        //		GameRegistry.addRecipe(new ItemStack(NibiruArmorItems.red_gem_helmet), new Object[] { "QQQ", "Q Q", 'Q', new ItemStack(NibiruItems.nibiru_item, 1, 2) });
        //		GameRegistry.addRecipe(new ItemStack(NibiruArmorItems.red_gem_chestplate), new Object[] { "Q Q", "QQQ", "QQQ", 'Q', new ItemStack(NibiruItems.nibiru_item, 1, 2) });
        //		GameRegistry.addRecipe(new ItemStack(NibiruArmorItems.red_gem_leggings), new Object[] { "QQQ", "Q Q", "Q Q", 'Q', new ItemStack(NibiruItems.nibiru_item, 1, 2) });
        //		GameRegistry.addRecipe(new ItemStack(NibiruArmorItems.red_gem_boots), new Object[] { "Q Q", "Q Q", 'Q', new ItemStack(NibiruItems.nibiru_item, 1, 2) });
        //		GameRegistry.addRecipe(new ItemStack(NibiruArmorItems.norium_helmet), new Object[] { "QQQ", "Q Q", 'Q', new ItemStack(NibiruItems.nibiru_item, 1, 3) });
        //		GameRegistry.addRecipe(new ItemStack(NibiruArmorItems.norium_chestplate), new Object[] { "Q Q", "QQQ", "QQQ", 'Q', new ItemStack(NibiruItems.nibiru_item, 1, 3) });
        //		GameRegistry.addRecipe(new ItemStack(NibiruArmorItems.norium_leggings), new Object[] { "QQQ", "Q Q", "Q Q", 'Q', new ItemStack(NibiruItems.nibiru_item, 1, 3) });
        //		GameRegistry.addRecipe(new ItemStack(NibiruArmorItems.norium_boots), new Object[] { "Q Q", "Q Q", 'Q', new ItemStack(NibiruItems.nibiru_item, 1, 3) });
        //		GameRegistry.addRecipe(new ItemStack(NibiruArmorItems.breathable_red_gem_helmet), new Object[] { "RRR", "ROR", 'R', new ItemStack(NibiruItems.nibiru_item, 1, 2), 'O', new ItemStack(GCItems.oxMask) });
        //		GameRegistry.addRecipe(new ItemStack(NibiruArmorItems.breathable_norium_helmet), new Object[] { "NNN", "NON", 'N', new ItemStack(NibiruItems.nibiru_item, 1, 3), 'O', new ItemStack(GCItems.oxMask) });

        // Tools
        //		GameRegistry.addRecipe(new ItemStack(NibiruToolsItems.red_gem_hoe), new Object[] { "XX", " Y", " Y", 'X', new ItemStack(NibiruItems.nibiru_item, 1, 2), 'Y', new ItemStack(NibiruItems.nibiru_item, 1, 4) });
        //		GameRegistry.addRecipe(new ItemStack(NibiruToolsItems.red_gem_hoe), new Object[] { "XX", "Y ", "Y ", 'X', new ItemStack(NibiruItems.nibiru_item, 1, 2), 'Y', new ItemStack(NibiruItems.nibiru_item, 1, 4) });
        //		GameRegistry.addRecipe(new ItemStack(NibiruToolsItems.red_gem_axe), new Object[] { "XX", "XY", " Y", 'X', new ItemStack(NibiruItems.nibiru_item, 1, 2), 'Y', new ItemStack(NibiruItems.nibiru_item, 1, 4) });
        //		GameRegistry.addRecipe(new ItemStack(NibiruToolsItems.red_gem_axe), new Object[] { "XX", "YX", "Y ", 'X', new ItemStack(NibiruItems.nibiru_item, 1, 2), 'Y', new ItemStack(NibiruItems.nibiru_item, 1, 4) });
        //		GameRegistry.addRecipe(new ItemStack(NibiruToolsItems.red_gem_pickaxe), new Object[] { "XXX", " Y ", " Y ", 'X', new ItemStack(NibiruItems.nibiru_item, 1, 2), 'Y', new ItemStack(NibiruItems.nibiru_item, 1, 4) });
        //		GameRegistry.addRecipe(new ItemStack(NibiruToolsItems.red_gem_sword), new Object[] { "X", "X", "Y", 'X', new ItemStack(NibiruItems.nibiru_item, 1, 2), 'Y', new ItemStack(NibiruItems.nibiru_item, 1, 4) });
        //		GameRegistry.addRecipe(new ItemStack(NibiruToolsItems.red_gem_shovel), new Object[] { "X", "Y", "Y", 'X', new ItemStack(NibiruItems.nibiru_item, 1, 2), 'Y', new ItemStack(NibiruItems.nibiru_item, 1, 4) });
        //		GameRegistry.addRecipe(new ItemStack(NibiruToolsItems.norium_hoe), new Object[] { "XX", " Y", " Y", 'X', new ItemStack(NibiruItems.nibiru_item, 1, 3), 'Y', new ItemStack(NibiruItems.nibiru_item, 1, 5) });
        //		GameRegistry.addRecipe(new ItemStack(NibiruToolsItems.norium_hoe), new Object[] { "XX", "Y ", "Y ", 'X', new ItemStack(NibiruItems.nibiru_item, 1, 3), 'Y', new ItemStack(NibiruItems.nibiru_item, 1, 5) });
        //		GameRegistry.addRecipe(new ItemStack(NibiruToolsItems.norium_axe), new Object[] { "XX", "XY", " Y", 'X', new ItemStack(NibiruItems.nibiru_item, 1, 3), 'Y', new ItemStack(NibiruItems.nibiru_item, 1, 5) });
        //		GameRegistry.addRecipe(new ItemStack(NibiruToolsItems.norium_axe), new Object[] { "XX", "YX", "Y ", 'X', new ItemStack(NibiruItems.nibiru_item, 1, 3), 'Y', new ItemStack(NibiruItems.nibiru_item, 1, 5) });
        //		GameRegistry.addRecipe(new ItemStack(NibiruToolsItems.norium_pickaxe), new Object[] { "XXX", " Y ", " Y ", 'X', new ItemStack(NibiruItems.nibiru_item, 1, 3), 'Y', new ItemStack(NibiruItems.nibiru_item, 1, 5) });
        //		GameRegistry.addRecipe(new ItemStack(NibiruToolsItems.norium_sword), new Object[] { "X", "X", "Y", 'X', new ItemStack(NibiruItems.nibiru_item, 1, 3), 'Y', new ItemStack(NibiruItems.nibiru_item, 1, 5) });
        //		GameRegistry.addRecipe(new ItemStack(NibiruToolsItems.norium_shovel), new Object[] { "X", "Y", "Y", 'X', new ItemStack(NibiruItems.nibiru_item, 1, 3), 'Y', new ItemStack(NibiruItems.nibiru_item, 1, 5) });
    }

    private static void addBlockSmelting()
    {
        GameRegistry.addSmelting(new ItemStack(PlutoBlocks.pluto_block, 1, 3), new ItemStack(PlutoBlocks.pluto_block, 1, 2), 0.4F);
    }

    private static void addItemSmelting()
    {
        GameRegistry.addSmelting(new ItemStack(PlutoBlocks.pluto_block, 1, 4), new ItemStack(GCItems.meteoricIronIngot), 0.8F);
        GameRegistry.addSmelting(new ItemStack(PlutoBlocks.pluto_block, 1, 5), new ItemStack(KapteynBItems.kapteyn_b_item, 1, 0), 0.8F);
        GameRegistry.addSmelting(new ItemStack(PlutoBlocks.pluto_block, 1, 6), new ItemStack(Items.iron_ingot), 0.8F);
        GameRegistry.addSmelting(new ItemStack(PlutoBlocks.pluto_block, 1, 7), new ItemStack(PlutoItems.pluto_item, 1, 0), 0.8F);
        GameRegistry.addSmelting(new ItemStack(PlutoItems.space_potato, 1, 0), new ItemStack(PlutoItems.space_potato, 1, 1), 0.5F);
    }
}