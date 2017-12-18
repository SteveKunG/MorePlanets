/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 *
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.planets.venus.recipe;

import cpw.mods.fml.common.registry.GameRegistry;
import micdoodle8.mods.galacticraft.core.items.GCItems;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import stevekung.mods.moreplanets.core.init.MPBlocks;
import stevekung.mods.moreplanets.planets.siriusb.items.SiriusBItems;
import stevekung.mods.moreplanets.planets.venus.blocks.VenusBlocks;
import stevekung.mods.moreplanets.planets.venus.items.VenusItems;

public class CraftingRecipesVenus
{
    public static void loadRecipes()
    {
        CraftingRecipesVenus.addBlockRecipes();
        CraftingRecipesVenus.addItemRecipes();
        CraftingRecipesVenus.addBlockSmelting();
        CraftingRecipesVenus.addItemSmelting();
    }

    private static void addBlockRecipes()
    {
        GameRegistry.addRecipe(new ItemStack(MPBlocks.chondrite_block, 4, 1), new Object[] { "CC", "CC", 'C', new ItemStack(MPBlocks.chondrite_block, 1, 0) });
        GameRegistry.addRecipe(new ItemStack(VenusBlocks.sulfur_torch, 4), new Object[] { "D", "S", 'D', new ItemStack(SiriusBItems.sirius_b_item, 1, 2), 'S', new ItemStack(Items.stick) });
        GameRegistry.addRecipe(new ItemStack(VenusBlocks.venus_block, 4, 12), new Object[] { "SS", "SS", 'S', new ItemStack(VenusBlocks.venus_block, 1, 2) });
        GameRegistry.addRecipe(new ItemStack(VenusBlocks.venus_block, 1, 11), new Object[] { "AAA", "AAA", "AAA" ,'A', new ItemStack(VenusItems.venus_item, 1, 0) });
        GameRegistry.addRecipe(new ItemStack(Blocks.tnt), new Object[] { "GSG", "SGS", "GSG" ,'G', new ItemStack(Items.gunpowder), 'S', new ItemStack(VenusBlocks.venus_sand) });
        GameRegistry.addRecipe(new ItemStack(VenusBlocks.venus_sandstone, 4, 0), new Object[] { "SS", "SS", 'S', new ItemStack(VenusBlocks.venus_sand) });
        GameRegistry.addRecipe(new ItemStack(VenusBlocks.venus_sandstone, 1, 1), new Object[] { "S", "S", 'S', new ItemStack(VenusBlocks.half_venus_sandstone_slab) });
        GameRegistry.addRecipe(new ItemStack(VenusBlocks.venus_sandstone, 4, 2), new Object[] { "SS", "SS", 'S', new ItemStack(VenusBlocks.venus_sandstone, 4, 0) });
        GameRegistry.addRecipe(new ItemStack(VenusBlocks.half_venus_sandstone_slab, 6), new Object[] { "SSS", 'S', VenusBlocks.venus_sandstone });

        GameRegistry.addRecipe(new ItemStack(VenusBlocks.venus_sandstone_stairs, 4), new Object[] { "  X", " XX", "XXX", 'X', VenusBlocks.venus_sandstone });
        GameRegistry.addRecipe(new ItemStack(VenusBlocks.venus_sandstone_stairs, 4), new Object[] { "X  ", "XX ", "XXX", 'X', VenusBlocks.venus_sandstone });
    }

    private static void addItemRecipes()
    {
        GameRegistry.addShapelessRecipe(new ItemStack(VenusItems.venus_item, 9, 0), new ItemStack(VenusBlocks.venus_block, 1, 11) );
        GameRegistry.addRecipe(new ItemStack(VenusItems.sulfur_battery, 1, 99), new Object[] { " I ", "IUI", "IGI", 'I', new ItemStack(GCItems.basicItem, 1, 7), 'U', new ItemStack(Items.redstone), 'G', new ItemStack(SiriusBItems.sirius_b_item, 1, 2) });
    }

    private static void addBlockSmelting()
    {
        GameRegistry.addSmelting(new ItemStack(VenusBlocks.venus_block, 1, 3), new ItemStack(VenusBlocks.venus_block, 1, 2), 0.4F);
        GameRegistry.addSmelting(new ItemStack(VenusBlocks.venus_block, 1, 12), new ItemStack(VenusBlocks.venus_block, 1, 13), 0.4F);
    }

    private static void addItemSmelting()
    {
        GameRegistry.addSmelting(new ItemStack(MPBlocks.chondrite_block, 1, 0), new ItemStack(MPBlocks.chondrite_block, 1, 2), 0.4F);
        GameRegistry.addSmelting(new ItemStack(VenusBlocks.venus_block, 1, 4), new ItemStack(SiriusBItems.sirius_b_item, 1, 3), 0.8F);
        GameRegistry.addSmelting(new ItemStack(VenusBlocks.venus_block, 1, 5), new ItemStack(VenusItems.venus_item, 1, 0), 0.8F);
        GameRegistry.addSmelting(new ItemStack(VenusBlocks.venus_block, 1, 6), new ItemStack(GCItems.basicItem, 1, 4), 0.8F);
        GameRegistry.addSmelting(new ItemStack(VenusBlocks.venus_block, 1, 7), new ItemStack(GCItems.basicItem, 1, 3), 0.8F);
        GameRegistry.addSmelting(new ItemStack(VenusBlocks.venus_block, 1, 8), new ItemStack(Items.coal), 0.8F);
        GameRegistry.addSmelting(new ItemStack(VenusBlocks.venus_block, 1, 9), new ItemStack(Items.iron_ingot), 0.8F);
        GameRegistry.addSmelting(new ItemStack(VenusBlocks.venus_block, 1, 10), new ItemStack(Items.gold_ingot), 0.8F);
        GameRegistry.addSmelting(new ItemStack(VenusBlocks.venus_redstone_ore), new ItemStack(Items.redstone), 0.7F);
        GameRegistry.addSmelting(new ItemStack(VenusBlocks.venus_sand), new ItemStack(Blocks.glass), 0.5F);
    }
}