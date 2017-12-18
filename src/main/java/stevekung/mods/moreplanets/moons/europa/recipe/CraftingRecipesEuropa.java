/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 *
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.moons.europa.recipe;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import stevekung.mods.moreplanets.core.init.MPBlocks;
import stevekung.mods.moreplanets.moons.europa.blocks.EuropaBlocks;
import stevekung.mods.moreplanets.moons.europa.items.EuropaItems;

public class CraftingRecipesEuropa
{
    public static void loadRecipes()
    {
        // Block recipe
        GameRegistry.addRecipe(new ItemStack(EuropaBlocks.europa_fence, 3), new Object[] { "CSC", "CSC", 'S', new ItemStack(Items.stick), 'C', new ItemStack(EuropaBlocks.europa_planks) });
        GameRegistry.addRecipe(new ItemStack(EuropaBlocks.europa_fence_gate), new Object[] { "SAS", "SAS", 'A', new ItemStack(EuropaBlocks.europa_planks), 'S', new ItemStack(Items.stick) });
        GameRegistry.addShapelessRecipe(new ItemStack(EuropaBlocks.europa_planks, 4), new ItemStack(EuropaBlocks.europa_log) );
        GameRegistry.addRecipe(new ItemStack(EuropaBlocks.europa_snow_layer, 6), new Object[] { "SSS", 'S', new ItemStack(EuropaBlocks.europa_snow_block) });
        GameRegistry.addRecipe(new ItemStack(EuropaBlocks.europa_sandstone, 4, 0), new Object[] { "SS", "SS", 'S', new ItemStack(EuropaBlocks.europa_sand) });
        GameRegistry.addRecipe(new ItemStack(EuropaBlocks.europa_sandstone, 1, 1), new Object[] { "S", "S", 'S', new ItemStack(EuropaBlocks.half_europa_sandstone_slab) });
        GameRegistry.addRecipe(new ItemStack(EuropaBlocks.europa_sandstone, 4, 2), new Object[] { "SS", "SS", 'S', new ItemStack(EuropaBlocks.europa_sandstone, 4, 0) });
        GameRegistry.addRecipe(new ItemStack(EuropaBlocks.europa_water_bomb), new Object[] { "GSG", "SGS", "GSG", 'S', new ItemStack(EuropaBlocks.europa_sand), 'G', new ItemStack(EuropaItems.europa_gunpowder) });

        GameRegistry.addRecipe(new ItemStack(EuropaBlocks.europa_wood_stairs, 4), new Object[] { "  X", " XX", "XXX", 'X', new ItemStack(EuropaBlocks.europa_planks) });
        GameRegistry.addRecipe(new ItemStack(EuropaBlocks.europa_wood_stairs, 4), new Object[] { "X  ", "XX ", "XXX", 'X', new ItemStack(EuropaBlocks.europa_planks) });

        GameRegistry.addRecipe(new ItemStack(EuropaBlocks.europa_sandstone_stairs, 4), new Object[] { "  X", " XX", "XXX", 'X', EuropaBlocks.europa_sandstone });
        GameRegistry.addRecipe(new ItemStack(EuropaBlocks.europa_sandstone_stairs, 4), new Object[] { "X  ", "XX ", "XXX", 'X', EuropaBlocks.europa_sandstone });

        GameRegistry.addRecipe(new ItemStack(MPBlocks.wooden_slab_half, 6, 5), new Object[] { "CCC", 'C', new ItemStack(EuropaBlocks.europa_planks) });
        GameRegistry.addRecipe(new ItemStack(EuropaBlocks.half_europa_sandstone_slab, 6), new Object[] { "SSS", 'S', EuropaBlocks.europa_sandstone });

        // Item recipe
        GameRegistry.addRecipe(new ItemStack(EuropaItems.europa_door, 3), new Object[] { "EE", "EE", "EE", 'E', new ItemStack(EuropaBlocks.europa_planks) });

        // Block smelting
        GameRegistry.addSmelting(new ItemStack(EuropaBlocks.europa_sand), new ItemStack(Blocks.stained_glass, 1, 9), 0.3F);
    }
}