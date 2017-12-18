/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 *
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.planets.fronos.recipe;

import java.util.HashMap;

import cpw.mods.fml.common.registry.GameRegistry;
import micdoodle8.mods.galacticraft.core.items.GCItems;
import micdoodle8.mods.galacticraft.core.util.RecipeUtil;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;
import stevekung.mods.moreplanets.core.config.ConfigManagerMP;
import stevekung.mods.moreplanets.core.init.MPBlocks;
import stevekung.mods.moreplanets.core.recipe.CandyExtractorRecipes;
import stevekung.mods.moreplanets.planets.fronos.blocks.FronosBlocks;
import stevekung.mods.moreplanets.planets.fronos.items.FronosItems;
import stevekung.mods.moreplanets.planets.fronos.items.armor.FronosArmorItems;
import stevekung.mods.moreplanets.planets.fronos.items.tools.FronosToolsItems;
import stevekung.mods.moreplanets.planets.nibiru.items.NibiruItems;

public class CraftingRecipesFronos
{
    public static void loadRecipes()
    {
        CraftingRecipesFronos.addBlockRecipes();
        CraftingRecipesFronos.addItemRecipes();
        CraftingRecipesFronos.addBlockSmelting();
        CraftingRecipesFronos.addItemSmelting();
        CraftingRecipesFronos.addOreDictRecipe();
        CraftingRecipesFronos.addExtractingRecipe();
        CraftingRecipesFronos.addTier7RocketRecipes();
    }

    private static void addBlockRecipes()
    {
        // Blocks
        GameRegistry.addRecipe(new ItemStack(FronosBlocks.fronos_block, 4, 11), new Object[] { "SS", "SS", 'S', new ItemStack(FronosBlocks.fronos_block, 1, 0) });
        GameRegistry.addRecipe(new ItemStack(FronosBlocks.cookie_block), new Object[] { "CC", "CC", 'C', new ItemStack(Items.cookie) });
        GameRegistry.addRecipe(new ItemStack(FronosBlocks.cream_block, 1, 0), new Object[] { "VV", "VV", 'V', new ItemStack(FronosItems.cream_ball, 1, 0) });
        GameRegistry.addRecipe(new ItemStack(FronosBlocks.cream_block, 1, 1), new Object[] { "CC", "CC", 'C', new ItemStack(FronosItems.cream_ball, 1, 1) });
        GameRegistry.addRecipe(new ItemStack(FronosBlocks.cream_block, 1, 2), new Object[] { "SS", "SS", 'S', new ItemStack(FronosItems.cream_ball, 1, 2) });
        GameRegistry.addRecipe(new ItemStack(FronosBlocks.cream_block, 1, 3), new Object[] { "OO", "OO", 'O', new ItemStack(FronosItems.cream_ball, 1, 3) });
        GameRegistry.addRecipe(new ItemStack(FronosBlocks.cream_block, 1, 4), new Object[] { "TT", "TT", 'T', new ItemStack(FronosItems.cream_ball, 1, 4) });
        GameRegistry.addRecipe(new ItemStack(FronosBlocks.cream_block, 1, 5), new Object[] { "LL", "LL", 'L', new ItemStack(FronosItems.cream_ball, 1, 5) });
        GameRegistry.addRecipe(new ItemStack(FronosBlocks.vanilla_cream_layer, 6), new Object[] { "VVV", 'V', new ItemStack(FronosBlocks.cream_block, 1, 0) });
        GameRegistry.addRecipe(new ItemStack(FronosBlocks.chocolate_cream_layer, 6), new Object[] { "CCC", 'C', new ItemStack(FronosBlocks.cream_block, 1, 1) });
        GameRegistry.addRecipe(new ItemStack(FronosBlocks.strawberry_cream_layer, 6), new Object[] { "SSS", 'S', new ItemStack(FronosBlocks.cream_block, 1, 2) });
        GameRegistry.addRecipe(new ItemStack(FronosBlocks.orange_cream_layer, 6), new Object[] { "OOO", 'O', new ItemStack(FronosBlocks.cream_block, 1, 3) });
        GameRegistry.addRecipe(new ItemStack(FronosBlocks.tea_cream_layer, 6), new Object[] { "TTT", 'T', new ItemStack(FronosBlocks.cream_block, 1, 4) });
        GameRegistry.addRecipe(new ItemStack(FronosBlocks.lemon_cream_layer, 6), new Object[] { "LLL", 'L', new ItemStack(FronosBlocks.cream_block, 1, 5) });
        GameRegistry.addShapelessRecipe(new ItemStack(FronosBlocks.fronos_wooden_planks, 4, 0), new ItemStack(FronosBlocks.fronos_log, 1, 0) );
        GameRegistry.addShapelessRecipe(new ItemStack(FronosBlocks.fronos_wooden_planks, 4, 1), new ItemStack(FronosBlocks.fronos_log, 1, 1) );
        GameRegistry.addRecipe(new ItemStack(FronosBlocks.ovantine_block), new Object[] { "OO", "OO", 'O', new ItemStack(FronosItems.fronos_food2, 1, 0) });
        GameRegistry.addRecipe(new ItemStack(FronosBlocks.chocolate_block), new Object[] { "CC", "CC", 'C', new ItemStack(FronosItems.fronos_food2, 1, 1) });
        GameRegistry.addRecipe(new ItemStack(FronosBlocks.pink_candy_torch, 4), new Object[] { "C", "S", 'C', Items.coal, 'S', new ItemStack(FronosItems.candy_cane, 1, 0) });
        GameRegistry.addRecipe(new ItemStack(FronosBlocks.orange_candy_torch, 4), new Object[] { "C", "S", 'C', Items.coal, 'S', new ItemStack(FronosItems.candy_cane, 1, 1) });
        GameRegistry.addRecipe(new ItemStack(FronosBlocks.green_candy_torch, 4), new Object[] { "C", "S", 'C', Items.coal, 'S', new ItemStack(FronosItems.candy_cane, 1, 2) });
        GameRegistry.addRecipe(new ItemStack(FronosBlocks.yellow_candy_torch, 4), new Object[] { "C", "S", 'C',Items.coal, 'S', new ItemStack(FronosItems.candy_cane, 1, 3) });
        GameRegistry.addRecipe(new ItemStack(FronosBlocks.light_blue_candy_torch, 4), new Object[] { "C", "S", 'C', Items.coal, 'S', new ItemStack(FronosItems.candy_cane, 1, 4) });
        GameRegistry.addRecipe(new ItemStack(FronosBlocks.blue_candy_torch, 4), new Object[] { "C", "S", 'C', Items.coal, 'S', new ItemStack(FronosItems.candy_cane, 1, 5) });
        GameRegistry.addRecipe(new ItemStack(FronosBlocks.red_candy_torch, 4), new Object[] { "C", "S", 'C',Items.coal, 'S', new ItemStack(FronosItems.candy_cane, 1, 6) });
        GameRegistry.addRecipe(new ItemStack(FronosBlocks.purple_candy_torch, 4), new Object[] { "C", "S", 'C', Items.coal, 'S', new ItemStack(FronosItems.candy_cane, 1, 7) });
        GameRegistry.addRecipe(new ItemStack(FronosBlocks.pink_candy_torch, 4), new Object[] { "C", "S", 'C', new ItemStack(Items.coal, 1, 1), 'S', new ItemStack(FronosItems.candy_cane, 1, 0) });
        GameRegistry.addRecipe(new ItemStack(FronosBlocks.orange_candy_torch, 4), new Object[] { "C", "S", 'C', new ItemStack(Items.coal, 1, 1), 'S', new ItemStack(FronosItems.candy_cane, 1, 1) });
        GameRegistry.addRecipe(new ItemStack(FronosBlocks.green_candy_torch, 4), new Object[] { "C", "S", 'C', new ItemStack(Items.coal, 1, 1), 'S', new ItemStack(FronosItems.candy_cane, 1, 2) });
        GameRegistry.addRecipe(new ItemStack(FronosBlocks.yellow_candy_torch, 4), new Object[] { "C", "S", 'C',new ItemStack(Items.coal, 1, 1), 'S', new ItemStack(FronosItems.candy_cane, 1, 3) });
        GameRegistry.addRecipe(new ItemStack(FronosBlocks.light_blue_candy_torch, 4), new Object[] { "C", "S", 'C', new ItemStack(Items.coal, 1, 1), 'S', new ItemStack(FronosItems.candy_cane, 1, 4) });
        GameRegistry.addRecipe(new ItemStack(FronosBlocks.blue_candy_torch, 4), new Object[] { "C", "S", 'C', new ItemStack(Items.coal, 1, 1), 'S', new ItemStack(FronosItems.candy_cane, 1, 5) });
        GameRegistry.addRecipe(new ItemStack(FronosBlocks.red_candy_torch, 4), new Object[] { "C", "S", 'C',new ItemStack(Items.coal, 1, 1), 'S', new ItemStack(FronosItems.candy_cane, 1, 6) });
        GameRegistry.addRecipe(new ItemStack(FronosBlocks.purple_candy_torch, 4), new Object[] { "C", "S", 'C', new ItemStack(Items.coal, 1, 1), 'S', new ItemStack(FronosItems.candy_cane, 1, 7) });
        GameRegistry.addShapelessRecipe(new ItemStack(FronosBlocks.cream_golem_head, 2, 0), new ItemStack(FronosBlocks.cream_block, 1, 0) );
        GameRegistry.addShapelessRecipe(new ItemStack(FronosBlocks.cream_golem_head, 2, 1), new ItemStack(FronosBlocks.cream_block, 1, 1) );
        GameRegistry.addShapelessRecipe(new ItemStack(FronosBlocks.cream_golem_head, 2, 2), new ItemStack(FronosBlocks.cream_block, 1, 2) );
        GameRegistry.addShapelessRecipe(new ItemStack(FronosBlocks.cream_golem_head, 2, 3), new ItemStack(FronosBlocks.cream_block, 1, 3) );
        GameRegistry.addShapelessRecipe(new ItemStack(FronosBlocks.cream_golem_head, 2, 4), new ItemStack(FronosBlocks.cream_block, 1, 4) );
        GameRegistry.addShapelessRecipe(new ItemStack(FronosBlocks.cream_golem_head, 2, 5), new ItemStack(FronosBlocks.cream_block, 1, 5) );
        GameRegistry.addRecipe(new ItemStack(FronosBlocks.jelly_block, 1, 0), new Object[] { "CC", "CC", 'C', new ItemStack(FronosItems.jelly, 1, 0) });
        GameRegistry.addRecipe(new ItemStack(FronosBlocks.jelly_block, 1, 1), new Object[] { "CC", "CC", 'C', new ItemStack(FronosItems.jelly, 1, 1) });
        GameRegistry.addRecipe(new ItemStack(FronosBlocks.jelly_block, 1, 2), new Object[] { "CC", "CC", 'C', new ItemStack(FronosItems.jelly, 1, 2) });
        GameRegistry.addRecipe(new ItemStack(FronosBlocks.jelly_block, 1, 3), new Object[] { "CC", "CC", 'C', new ItemStack(FronosItems.jelly, 1, 3) });
        GameRegistry.addRecipe(new ItemStack(FronosBlocks.jelly_block, 1, 4), new Object[] { "CC", "CC", 'C', new ItemStack(FronosItems.jelly, 1, 4) });
        GameRegistry.addRecipe(new ItemStack(FronosBlocks.jelly_block, 1, 5), new Object[] { "CC", "CC", 'C', new ItemStack(FronosItems.jelly, 1, 5) });
        GameRegistry.addRecipe(new ItemStack(FronosBlocks.jelly_block, 1, 6), new Object[] { "CC", "CC", 'C', new ItemStack(FronosItems.jelly, 1, 6) });
        GameRegistry.addRecipe(new ItemStack(FronosBlocks.jelly_block, 1, 7), new Object[] { "CC", "CC", 'C', new ItemStack(FronosItems.jelly, 1, 7) });
        GameRegistry.addRecipe(new ItemStack(FronosBlocks.fronos_fence, 3, 0), new Object[] { "CSC", "CSC", 'S', new ItemStack(Items.stick), 'C', new ItemStack(FronosBlocks.fronos_wooden_planks, 1, 0) });
        GameRegistry.addRecipe(new ItemStack(FronosBlocks.fronos_fence, 3, 1), new Object[] { "RSR", "RSR", 'S', new ItemStack(Items.stick), 'R', new ItemStack(FronosBlocks.fronos_wooden_planks, 1, 1) });
        GameRegistry.addRecipe(new ItemStack(FronosBlocks.coconut_fence_gate), new Object[] { "SAS", "SAS", 'A', new ItemStack(FronosBlocks.fronos_wooden_planks, 1, 0), 'S', new ItemStack(Items.stick) });
        GameRegistry.addRecipe(new ItemStack(FronosBlocks.maple_fence_gate), new Object[] { "SOS", "SOS", 'O', new ItemStack(FronosBlocks.fronos_wooden_planks, 1, 1), 'S', new ItemStack(Items.stick) });
        GameRegistry.addRecipe(new ItemStack(FronosBlocks.fronos_block, 1, 13), new Object[] { "S", "S", 'S', new ItemStack(MPBlocks.stone_slab_half2, 1, 1) });
        GameRegistry.addRecipe(new ItemStack(FronosBlocks.cheese_glass_pane, 16), new Object[] { "CCC", "CCC", 'C', new ItemStack(FronosBlocks.cheese_glass) });
        GameRegistry.addRecipe(new ItemStack(FronosBlocks.ore_block, 1, 0), new Object[] { "III", "III", "III", 'I', new ItemStack(FronosItems.fronos_item, 1, 3) });
        GameRegistry.addRecipe(new ItemStack(FronosBlocks.ore_block, 1, 1), new Object[] { "DDD", "DDD", "DDD", 'D', new ItemStack(FronosItems.fronos_item, 1, 2) });
        GameRegistry.addRecipe(new ItemStack(FronosBlocks.caramel_block), new Object[] { "CCC", "CCC", "CCC", 'C', new ItemStack(FronosItems.fronos_food2, 1, 2) });
        GameRegistry.addShapelessRecipe(new ItemStack(FronosBlocks.mossy_fronos_cobblestone), new ItemStack(FronosBlocks.fronos_block, 1, 1), new ItemStack(Blocks.vine) );
        GameRegistry.addRecipe(new ItemStack(FronosBlocks.fronos_dirt, 4, 1), new Object[] { "DG", "GD", 'D', new ItemStack(FronosBlocks.fronos_dirt, 1, 0), 'G', new ItemStack(Blocks.gravel) });
        GameRegistry.addRecipe(new ItemStack(FronosBlocks.fronos_sandstone, 4, 0), new Object[] { "SS", "SS", 'S', new ItemStack(FronosBlocks.fronos_sand, 1, 0) });
        GameRegistry.addRecipe(new ItemStack(FronosBlocks.fronos_sandstone, 4, 3), new Object[] { "SS", "SS", 'S', new ItemStack(FronosBlocks.fronos_sand, 1, 1) });
        GameRegistry.addRecipe(new ItemStack(FronosBlocks.fronos_sandstone, 4, 6), new Object[] { "SS", "SS", 'S', new ItemStack(FronosBlocks.fronos_sand, 1, 2) });
        GameRegistry.addRecipe(new ItemStack(FronosBlocks.fronos_sandstone, 4, 2), new Object[] { "SS", "SS", 'S', new ItemStack(FronosBlocks.fronos_sandstone, 1, 0) });
        GameRegistry.addRecipe(new ItemStack(FronosBlocks.fronos_sandstone, 4, 5), new Object[] { "SS", "SS", 'S', new ItemStack(FronosBlocks.fronos_sandstone, 1, 3) });
        GameRegistry.addRecipe(new ItemStack(FronosBlocks.fronos_sandstone, 4, 8), new Object[] { "SS", "SS", 'S', new ItemStack(FronosBlocks.fronos_sandstone, 1, 6) });
        GameRegistry.addRecipe(new ItemStack(FronosBlocks.fronos_sandstone, 1, 1), new Object[] { "S", "S", 'S', new ItemStack(FronosBlocks.half_fronos_sandstone_slab, 1, 0) });
        GameRegistry.addRecipe(new ItemStack(FronosBlocks.fronos_sandstone, 1, 4), new Object[] { "S", "S", 'S', new ItemStack(FronosBlocks.half_fronos_sandstone_slab, 1, 1) });
        GameRegistry.addRecipe(new ItemStack(FronosBlocks.fronos_sandstone, 1, 7), new Object[] { "S", "S", 'S', new ItemStack(FronosBlocks.half_fronos_sandstone_slab, 1, 2) });
        RecipeUtil.addRecipe(new ItemStack(FronosBlocks.half_fronos_sandstone_slab, 6, 0), new Object[] { "SSS", 'S', "fronosSandstone" });
        RecipeUtil.addRecipe(new ItemStack(FronosBlocks.half_fronos_sandstone_slab, 6, 1), new Object[] { "SSS", 'S', "whiteSandstone" });
        RecipeUtil.addRecipe(new ItemStack(FronosBlocks.half_fronos_sandstone_slab, 6, 2), new Object[] { "SSS", 'S', "cheeseSandstone" });
        RecipeUtil.addRecipe(new ItemStack(FronosBlocks.candy_extractor_idle), new Object[] { "CCC", "CFC", "MDM", 'C', new ItemStack(FronosBlocks.fronos_block, 1, 1), 'F', Blocks.furnace, 'D', "candyCane", 'M', new ItemStack(FronosItems.fronos_item) });

        // Coconut Wood Stairs
        GameRegistry.addRecipe(new ItemStack(FronosBlocks.coconut_wood_stairs, 4), new Object[] { "  X", " XX", "XXX", 'X', new ItemStack(FronosBlocks.fronos_wooden_planks, 1, 0) });
        GameRegistry.addRecipe(new ItemStack(FronosBlocks.coconut_wood_stairs, 4), new Object[] { "X  ", "XX ", "XXX", 'X', new ItemStack(FronosBlocks.fronos_wooden_planks, 1, 0) });

        // Maple Wood Stairs
        GameRegistry.addRecipe(new ItemStack(FronosBlocks.maple_wood_stairs, 4), new Object[] { "  X", " XX", "XXX", 'X', new ItemStack(FronosBlocks.fronos_wooden_planks, 1, 1) });
        GameRegistry.addRecipe(new ItemStack(FronosBlocks.maple_wood_stairs, 4), new Object[] { "X  ", "XX ", "XXX", 'X', new ItemStack(FronosBlocks.fronos_wooden_planks, 1, 1) });

        // Fronos Cobblestone Stairs
        GameRegistry.addRecipe(new ItemStack(FronosBlocks.fronos_cobblestone_stairs, 4), new Object[] { "  X", " XX", "XXX", 'X', new ItemStack(FronosBlocks.fronos_block, 1, 1) });
        GameRegistry.addRecipe(new ItemStack(FronosBlocks.fronos_cobblestone_stairs, 4), new Object[] { "X  ", "XX ", "XXX", 'X', new ItemStack(FronosBlocks.fronos_block, 1, 1) });

        // Fronos Stone Brick Stairs
        GameRegistry.addRecipe(new ItemStack(FronosBlocks.fronos_stone_brick_stairs, 4), new Object[] { "  X", " XX", "XXX", 'X', new ItemStack(FronosBlocks.fronos_block, 1, 11) });
        GameRegistry.addRecipe(new ItemStack(FronosBlocks.fronos_stone_brick_stairs, 4), new Object[] { "X  ", "XX ", "XXX", 'X', new ItemStack(FronosBlocks.fronos_block, 1, 11) });

        // Cracked Fronos Stone Brick Stairs
        GameRegistry.addRecipe(new ItemStack(FronosBlocks.cracked_fronos_stone_brick_stairs, 4), new Object[] { "  X", " XX", "XXX", 'X', new ItemStack(FronosBlocks.fronos_block, 1, 12) });
        GameRegistry.addRecipe(new ItemStack(FronosBlocks.cracked_fronos_stone_brick_stairs, 4), new Object[] { "X  ", "XX ", "XXX", 'X', new ItemStack(FronosBlocks.fronos_block, 1, 12) });

        // Fronos Dungeon Brick Stairs
        GameRegistry.addRecipe(new ItemStack(FronosBlocks.fronos_dungeon_brick_stairs, 4), new Object[] { "  X", " XX", "XXX", 'X', new ItemStack(FronosBlocks.fronos_block, 1, 14) });
        GameRegistry.addRecipe(new ItemStack(FronosBlocks.fronos_dungeon_brick_stairs, 4), new Object[] { "X  ", "XX ", "XXX", 'X', new ItemStack(FronosBlocks.fronos_block, 1, 14) });

        // Fronos Sandstone Stairs
        GameRegistry.addRecipe(new ItemStack(FronosBlocks.fronos_sandstone_stairs, 4), new Object[] { "  X", " XX", "XXX", 'X', new ItemStack(FronosBlocks.fronos_sandstone, 1, 0) });
        GameRegistry.addRecipe(new ItemStack(FronosBlocks.fronos_sandstone_stairs, 4), new Object[] { "X  ", "XX ", "XXX", 'X', new ItemStack(FronosBlocks.fronos_sandstone, 1, 0) });

        // White Sandstone Stairs
        GameRegistry.addRecipe(new ItemStack(FronosBlocks.white_sandstone_stairs, 4), new Object[] { "  X", " XX", "XXX", 'X', new ItemStack(FronosBlocks.fronos_sandstone, 1, 3) });
        GameRegistry.addRecipe(new ItemStack(FronosBlocks.white_sandstone_stairs, 4), new Object[] { "X  ", "XX ", "XXX", 'X', new ItemStack(FronosBlocks.fronos_sandstone, 1, 3) });

        // Cheese Sandstone Stairs
        GameRegistry.addRecipe(new ItemStack(FronosBlocks.cheese_sandstone_stairs, 4), new Object[] { "  X", " XX", "XXX", 'X', new ItemStack(FronosBlocks.fronos_sandstone, 1, 6) });
        GameRegistry.addRecipe(new ItemStack(FronosBlocks.cheese_sandstone_stairs, 4), new Object[] { "X  ", "XX ", "XXX", 'X', new ItemStack(FronosBlocks.fronos_sandstone, 1, 6) });

        // Slabs
        GameRegistry.addRecipe(new ItemStack(MPBlocks.wooden_slab_half, 6, 2), new Object[] { "CCC", 'C', new ItemStack(FronosBlocks.fronos_wooden_planks, 1, 0) });
        GameRegistry.addRecipe(new ItemStack(MPBlocks.wooden_slab_half, 6, 3), new Object[] { "RRR", 'R', new ItemStack(FronosBlocks.fronos_wooden_planks, 1, 1) });
        GameRegistry.addRecipe(new ItemStack(MPBlocks.stone_slab_half2, 6, 0), new Object[] { "SSS", 'S', new ItemStack(FronosBlocks.fronos_block, 1, 1) });
        GameRegistry.addRecipe(new ItemStack(MPBlocks.stone_slab_half2, 6, 1), new Object[] { "SSS", 'S', new ItemStack(FronosBlocks.fronos_block, 1, 11) });
        GameRegistry.addRecipe(new ItemStack(MPBlocks.stone_slab_half2, 6, 2), new Object[] { "SSS", 'S', new ItemStack(FronosBlocks.fronos_block, 1, 12) });
        GameRegistry.addRecipe(new ItemStack(MPBlocks.dungeon_brick_slab_half, 6, 4), new Object[] { "SSS", 'S', new ItemStack(FronosBlocks.fronos_block, 1, 14) });

        // Walls
        GameRegistry.addRecipe(new ItemStack(MPBlocks.stone_wall, 6, 8), new Object[] { "CCC", "CCC", 'C', new ItemStack(FronosBlocks.fronos_block, 1, 1) });
        GameRegistry.addRecipe(new ItemStack(MPBlocks.stone_wall, 6, 9), new Object[] { "CCC", "CCC", 'C', new ItemStack(FronosBlocks.fronos_block, 1, 11) });
        GameRegistry.addRecipe(new ItemStack(MPBlocks.stone_wall, 6, 10), new Object[] { "CCC", "CCC", 'C', new ItemStack(FronosBlocks.fronos_block, 1, 12) });
        GameRegistry.addRecipe(new ItemStack(MPBlocks.dungeon_brick_wall, 6, 4), new Object[] { "CCC", "CCC", 'C', new ItemStack(FronosBlocks.fronos_block, 1, 14) });
    }

    private static void addItemRecipes()
    {
        // Items
        GameRegistry.addShapelessRecipe(new ItemStack(FronosItems.bearry_egg), new ItemStack(FronosItems.fronos_food, 1, 0), new ItemStack(Items.egg) );
        GameRegistry.addRecipe(new ItemStack(FronosItems.fronos_food, 1, 4), new Object[] { "V", "B", 'V', new ItemStack(FronosItems.cream_ball, 1, 0), 'B', new ItemStack(Items.bowl) });
        GameRegistry.addRecipe(new ItemStack(FronosItems.fronos_food, 1, 5), new Object[] { "C", "B", 'C', new ItemStack(FronosItems.cream_ball, 1, 1), 'B', new ItemStack(Items.bowl) });
        GameRegistry.addRecipe(new ItemStack(FronosItems.fronos_food, 1, 6), new Object[] { "S", "B", 'S', new ItemStack(FronosItems.cream_ball, 1, 2), 'B', new ItemStack(Items.bowl) });
        GameRegistry.addRecipe(new ItemStack(FronosItems.fronos_food, 1, 7), new Object[] { "S", "B", 'S', new ItemStack(FronosBlocks.cloud_block), 'B', new ItemStack(Items.bowl) });
        GameRegistry.addRecipe(new ItemStack(FronosItems.fronos_food, 1, 8), new Object[] { "O", "B", 'O', new ItemStack(FronosItems.cream_ball, 1, 3), 'B', new ItemStack(Items.bowl) });
        GameRegistry.addRecipe(new ItemStack(FronosItems.fronos_food, 1, 9), new Object[] { "WWW", 'W', new ItemStack(FronosItems.fronos_item, 1, 6) });
        GameRegistry.addRecipe(new ItemStack(FronosItems.fronos_food, 1, 11), new Object[] { "T", "B", 'T', new ItemStack(FronosItems.cream_ball, 1, 4), 'B', new ItemStack(Items.bowl) });
        GameRegistry.addRecipe(new ItemStack(FronosItems.fronos_food, 1, 15), new Object[] { "L", "B", 'L', new ItemStack(FronosItems.cream_ball, 1, 5), 'B', new ItemStack(Items.bowl) });
        GameRegistry.addShapelessRecipe(new ItemStack(FronosItems.strawberry_seed), new ItemStack(FronosItems.fronos_food, 1, 0) );
        GameRegistry.addRecipe(new ItemStack(Items.cookie, 4), new Object[] { "C", 'C', new ItemStack(FronosBlocks.cookie_block) });
        GameRegistry.addRecipe(new ItemStack(FronosItems.cup, 4), new Object[] { "C C", "CWC", "CCC", 'C', new ItemStack(Blocks.stained_hardened_clay, 1, 0), 'W', new ItemStack(Items.dye, 1, 15) });
        GameRegistry.addShapelessRecipe(new ItemStack(FronosItems.fronos_item, 4, 7), new ItemStack(FronosBlocks.fronos_block, 1, 1) );
        GameRegistry.addShapelessRecipe(new ItemStack(FronosItems.fronos_item, 2, 1), new ItemStack(FronosItems.fronos_item, 1, 0) );
        GameRegistry.addRecipe(new ItemStack(FronosItems.cream_golem, 1, 0), new Object[] { "H", "C", "C", 'H', new ItemStack(FronosBlocks.cream_golem_head, 1, 0), 'C', new ItemStack(FronosBlocks.cream_block, 1, 0) });
        GameRegistry.addRecipe(new ItemStack(FronosItems.cream_golem, 1, 1), new Object[] { "H", "C", "C", 'H', new ItemStack(FronosBlocks.cream_golem_head, 1, 1), 'C', new ItemStack(FronosBlocks.cream_block, 1, 1) });
        GameRegistry.addRecipe(new ItemStack(FronosItems.cream_golem, 1, 2), new Object[] { "H", "C", "C", 'H', new ItemStack(FronosBlocks.cream_golem_head, 1, 2), 'C', new ItemStack(FronosBlocks.cream_block, 1, 2) });
        GameRegistry.addRecipe(new ItemStack(FronosItems.cream_golem, 1, 3), new Object[] { "H", "C", "C", 'H', new ItemStack(FronosBlocks.cream_golem_head, 1, 3), 'C', new ItemStack(FronosBlocks.cream_block, 1, 3) });
        GameRegistry.addRecipe(new ItemStack(FronosItems.cream_golem, 1, 4), new Object[] { "H", "C", "C", 'H', new ItemStack(FronosBlocks.cream_golem_head, 1, 4), 'C', new ItemStack(FronosBlocks.cream_block, 1, 4) });
        GameRegistry.addRecipe(new ItemStack(FronosItems.cream_golem, 1, 5), new Object[] { "H", "C", "C", 'H', new ItemStack(FronosBlocks.cream_golem_head, 1, 5), 'C', new ItemStack(FronosBlocks.cream_block, 1, 5) });
        GameRegistry.addRecipe(new ItemStack(FronosItems.coconut_door, 3), new Object[] { "CC", "CC", "CC", 'C', new ItemStack(FronosBlocks.fronos_wooden_planks, 1, 0) });
        GameRegistry.addRecipe(new ItemStack(FronosItems.maple_door, 3), new Object[] { "MM", "MM", "MM", 'M', new ItemStack(FronosBlocks.fronos_wooden_planks, 1, 1) });
        GameRegistry.addRecipe(new ItemStack(FronosItems.fruits_juice, 1, 0), new Object[] { "S", "W", 'S', new ItemStack(FronosItems.fronos_food, 1, 0), 'W', new ItemStack(Items.potionitem, 1, 0) });
        GameRegistry.addRecipe(new ItemStack(FronosItems.fruits_juice, 1, 1), new Object[] { "B", "W", 'B', new ItemStack(FronosItems.fronos_food, 1, 1), 'W', new ItemStack(Items.potionitem, 1, 0) });
        GameRegistry.addRecipe(new ItemStack(FronosItems.fruits_juice, 1, 2), new Object[] { "K", "W", 'K', new ItemStack(FronosItems.fruits, 1, 0), 'W', new ItemStack(Items.potionitem, 1, 0) });
        GameRegistry.addRecipe(new ItemStack(FronosItems.fruits_juice, 1, 3), new Object[] { "L", "W", 'L', new ItemStack(FronosItems.fruits, 1, 1), 'W', new ItemStack(Items.potionitem, 1, 0) });
        GameRegistry.addShapelessRecipe(new ItemStack(FronosItems.fronos_food, 1, 12), new ItemStack(FronosItems.fronos_food, 1, 0), new ItemStack(FronosItems.fronos_food, 1, 1), new ItemStack(Items.bowl));
        GameRegistry.addShapelessRecipe(new ItemStack(Items.dye, 1, 14), new ItemStack(FronosBlocks.dandelion, 1, 3));
        GameRegistry.addShapelessRecipe(new ItemStack(Items.dye, 1, 9), new ItemStack(FronosBlocks.dandelion, 1, 4));
        GameRegistry.addShapelessRecipe(new ItemStack(Items.dye, 1, 5), new ItemStack(FronosBlocks.dandelion, 1, 5));
        GameRegistry.addShapelessRecipe(new ItemStack(Items.dye, 1, 9), new ItemStack(FronosBlocks.fronos_flower, 1, 0));
        GameRegistry.addShapelessRecipe(new ItemStack(Items.dye, 1, 14), new ItemStack(FronosBlocks.fronos_flower, 1, 1));
        GameRegistry.addShapelessRecipe(new ItemStack(Items.dye, 1, 11), new ItemStack(FronosBlocks.fronos_flower, 1, 2));
        GameRegistry.addShapelessRecipe(new ItemStack(FronosItems.fronos_food, 2, 10), new ItemStack(FronosBlocks.fronos_flower, 1, 3));
        GameRegistry.addShapelessRecipe(new ItemStack(Items.dye, 1, 12), new ItemStack(FronosBlocks.fronos_flower, 1, 4));
        GameRegistry.addShapelessRecipe(new ItemStack(Items.dye, 1, 5), new ItemStack(FronosBlocks.fronos_flower, 1, 5));
        GameRegistry.addShapelessRecipe(new ItemStack(Items.dye, 1, 7), new ItemStack(FronosBlocks.poppy, 1, 0));
        GameRegistry.addShapelessRecipe(new ItemStack(Items.dye, 1, 14), new ItemStack(FronosBlocks.poppy, 1, 1));
        GameRegistry.addShapelessRecipe(new ItemStack(Items.dye, 1, 5), new ItemStack(FronosBlocks.poppy, 1, 2));
        GameRegistry.addShapelessRecipe(new ItemStack(FronosItems.fronos_food2, 9, 2), new ItemStack(FronosBlocks.caramel_block));
        GameRegistry.addShapelessRecipe(new ItemStack(FronosItems.fronos_item, 9, 2), new ItemStack(FronosBlocks.ore_block, 1, 1) );
        GameRegistry.addShapelessRecipe(new ItemStack(FronosItems.fronos_item, 9, 3), new ItemStack(FronosBlocks.ore_block, 1, 0) );
        GameRegistry.addRecipe(new ItemStack(FronosItems.tier8_rocket_module, 1, 0), new Object[] { " SB", "TCT", "TAT", 'B', new ItemStack(Blocks.stone_button), 'S', new ItemStack(Items.flint_and_steel), 'T', new ItemStack(FronosItems.tier8_rocket_module, 1, 2), 'A', new ItemStack(GCItems.oxygenVent), 'C', new ItemStack(GCItems.canister, 1, 0) });
        GameRegistry.addRecipe(new ItemStack(FronosItems.tier8_rocket_module, 1, 0), new Object[] { "BS ", "TCT", "TAT", 'B', new ItemStack(Blocks.stone_button), 'S', new ItemStack(Items.flint_and_steel), 'T', new ItemStack(FronosItems.tier8_rocket_module, 1, 2), 'A', new ItemStack(GCItems.oxygenVent), 'C', new ItemStack(GCItems.canister, 1, 0) });
        GameRegistry.addRecipe(new ItemStack(FronosItems.tier8_rocket_module, 1, 1), new Object[] { "PDP", "PCP", "NON", 'D', new ItemStack(FronosBlocks.ore_block, 1, 0), 'P', new ItemStack(FronosItems.fronos_item, 1, 4), 'C', new ItemStack(GCItems.fuelCanister, 1, 1), 'N', new ItemStack(FronosItems.tier8_rocket_module, 1, 2), 'O', new ItemStack(GCItems.oxygenVent) });
        GameRegistry.addRecipe(new ItemStack(FronosItems.fronos_food, 1, 13), new Object[] { "SSS", " B ", 'S', new ItemStack(FronosBlocks.fronos_flower, 1, 4), 'B', new ItemStack(Items.bowl) });
        GameRegistry.addRecipe(new ItemStack(FronosItems.fronos_food, 1, 14), new Object[] { "R", "B", 'R', new ItemStack(FronosBlocks.cloud_block, 1, 1), 'B', new ItemStack(Items.bowl) });

        for (int i = 0; i < 8; ++i)
        {
            GameRegistry.addShapelessRecipe(new ItemStack(FronosItems.candy_cane, 1, i), new ItemStack(FronosBlocks.candy_flower, 1, i) );
        }

        if (!ConfigManagerMP.enableMultiCandyCaneRecipe)
        {
            // Candy Bow
            GameRegistry.addRecipe(new ItemStack(FronosItems.candy_bow), new Object[] { " CS", "C S", " CS", 'C', new ItemStack(FronosItems.candy_cane, 1, 0), 'S', new ItemStack(FronosItems.fronos_item, 1, 9) });
            GameRegistry.addRecipe(new ItemStack(FronosItems.poison_arrow, 4), new Object[] { "P", "C", "S", 'C', new ItemStack(FronosItems.candy_cane, 1, 0), 'S', new ItemStack(FronosBlocks.cloud_block), 'P', new ItemStack(FronosBlocks.fronos_flower, 1, 5) });
            GameRegistry.addRecipe(new ItemStack(FronosItems.poison_arrow, 4), new Object[] { "P", "C", "S", 'C', new ItemStack(FronosItems.candy_cane, 1, 0), 'S', new ItemStack(FronosBlocks.cloud_block), 'P', new ItemStack(FronosBlocks.fronos_flower, 1, 7) });
            GameRegistry.addRecipe(new ItemStack(FronosItems.poison_arrow, 4), new Object[] { "P", "C", "S", 'C', new ItemStack(FronosItems.candy_cane, 1, 0), 'S', new ItemStack(FronosItems.fronos_item, 1, 8), 'P', new ItemStack(FronosBlocks.fronos_flower, 1, 5) });
            GameRegistry.addRecipe(new ItemStack(FronosItems.poison_arrow, 4), new Object[] { "P", "C", "S", 'C', new ItemStack(FronosItems.candy_cane, 1, 0), 'S', new ItemStack(FronosItems.fronos_item, 1, 8), 'P', new ItemStack(FronosBlocks.fronos_flower, 1, 7) });

            // Candy Tools
            GameRegistry.addRecipe(new ItemStack(FronosToolsItems.candy_hoe), new Object[] { "XX", " Y", " Y", 'X', new ItemStack(FronosBlocks.candy_cane1, 1, 0), 'Y', new ItemStack(FronosItems.candy_cane, 1, 0) });
            GameRegistry.addRecipe(new ItemStack(FronosToolsItems.candy_hoe), new Object[] { "XX", "Y ", "Y ", 'X', new ItemStack(FronosBlocks.candy_cane1, 1, 0), 'Y', new ItemStack(FronosItems.candy_cane, 1, 0) });
            GameRegistry.addRecipe(new ItemStack(FronosToolsItems.candy_axe), new Object[] { "XX", "XY", " Y", 'X', new ItemStack(FronosBlocks.candy_cane1, 1, 0), 'Y', new ItemStack(FronosItems.candy_cane, 1, 0) });
            GameRegistry.addRecipe(new ItemStack(FronosToolsItems.candy_axe), new Object[] { "XX", "YX", "Y ", 'X', new ItemStack(FronosBlocks.candy_cane1, 1, 0), 'Y', new ItemStack(FronosItems.candy_cane, 1, 0) });
            GameRegistry.addRecipe(new ItemStack(FronosToolsItems.candy_pickaxe), new Object[] { "XXX", " Y ", " Y ", 'X', new ItemStack(FronosBlocks.candy_cane1, 1, 0), 'Y', new ItemStack(FronosItems.candy_cane, 1, 0) });
            GameRegistry.addRecipe(new ItemStack(FronosToolsItems.candy_sword), new Object[] { "X", "X", "Y", 'X', new ItemStack(FronosBlocks.candy_cane1, 1, 0), 'Y', new ItemStack(FronosItems.candy_cane, 1, 0) });
            GameRegistry.addRecipe(new ItemStack(FronosToolsItems.candy_shovel), new Object[] { "X", "Y", "Y", 'X', new ItemStack(FronosBlocks.candy_cane1, 1, 0), 'Y', new ItemStack(FronosItems.candy_cane, 1, 0) });
        }
        else
        {
            // Candy Bow
            RecipeUtil.addRecipe(new ItemStack(FronosItems.candy_bow), new Object[] { " CS", "C S", " CS", 'C', "candy", 'S', new ItemStack(FronosItems.fronos_item, 1, 9) });
            RecipeUtil.addRecipe(new ItemStack(FronosItems.poison_arrow, 4), new Object[] { "P", "C", "S", 'C', "candy", 'S', new ItemStack(FronosBlocks.cloud_block), 'P', new ItemStack(FronosBlocks.fronos_flower, 1, 5) });
            RecipeUtil.addRecipe(new ItemStack(FronosItems.poison_arrow, 4), new Object[] { "P", "C", "S", 'C', "candy", 'S', new ItemStack(FronosBlocks.cloud_block), 'P', new ItemStack(FronosBlocks.fronos_flower, 1, 7) });
            RecipeUtil.addRecipe(new ItemStack(FronosItems.poison_arrow, 4), new Object[] { "P", "C", "S", 'C', "candy", 'S', new ItemStack(FronosItems.fronos_item, 1, 8), 'P', new ItemStack(FronosBlocks.fronos_flower, 1, 5) });
            RecipeUtil.addRecipe(new ItemStack(FronosItems.poison_arrow, 4), new Object[] { "P", "C", "S", 'C', "candy", 'S', new ItemStack(FronosItems.fronos_item, 1, 8), 'P', new ItemStack(FronosBlocks.fronos_flower, 1, 7) });

            // Candy Tools
            RecipeUtil.addRecipe(new ItemStack(FronosToolsItems.candy_hoe), new Object[] { "XX", " Y", " Y", 'X', "candyCane", 'Y', "candy" });
            RecipeUtil.addRecipe(new ItemStack(FronosToolsItems.candy_hoe), new Object[] { "XX", "Y ", "Y ", 'X', "candyCane", 'Y', "candy" });
            RecipeUtil.addRecipe(new ItemStack(FronosToolsItems.candy_axe), new Object[] { "XX", "XY", " Y", 'X', "candyCane", 'Y', "candy" });
            RecipeUtil.addRecipe(new ItemStack(FronosToolsItems.candy_axe), new Object[] { "XX", "YX", "Y ", 'X', "candyCane", 'Y', "candy" });
            RecipeUtil.addRecipe(new ItemStack(FronosToolsItems.candy_pickaxe), new Object[] { "XXX", " Y ", " Y ", 'X', "candyCane", 'Y', "candy" });
            RecipeUtil.addRecipe(new ItemStack(FronosToolsItems.candy_sword), new Object[] { "X", "X", "Y", 'X', "candyCane", 'Y', "candy" });
            RecipeUtil.addRecipe(new ItemStack(FronosToolsItems.candy_shovel), new Object[] { "X", "Y", "Y", 'X', "candyCane", 'Y', "candy" });
        }

        // Armor
        GameRegistry.addRecipe(new ItemStack(FronosArmorItems.black_diamond_helmet), new Object[] { "QQQ", "Q Q", 'Q', new ItemStack(FronosItems.fronos_item, 1, 2) });
        GameRegistry.addRecipe(new ItemStack(FronosArmorItems.black_diamond_chestplate), new Object[] { "Q Q", "QQQ", "QQQ", 'Q', new ItemStack(FronosItems.fronos_item, 1, 2) });
        GameRegistry.addRecipe(new ItemStack(FronosArmorItems.black_diamond_leggings), new Object[] { "QQQ", "Q Q", "Q Q", 'Q', new ItemStack(FronosItems.fronos_item, 1, 2) });
        GameRegistry.addRecipe(new ItemStack(FronosArmorItems.black_diamond_boots), new Object[] { "Q Q", "Q Q", 'Q', new ItemStack(FronosItems.fronos_item, 1, 2) });
        GameRegistry.addRecipe(new ItemStack(FronosArmorItems.iridium_helmet), new Object[] { "QQQ", "Q Q", 'Q', new ItemStack(FronosItems.fronos_item, 1, 5) });
        GameRegistry.addRecipe(new ItemStack(FronosArmorItems.iridium_chestplate), new Object[] { "Q Q", "QQQ", "QQQ", 'Q', new ItemStack(FronosItems.fronos_item, 1, 5) });
        GameRegistry.addRecipe(new ItemStack(FronosArmorItems.iridium_leggings), new Object[] { "QQQ", "Q Q", "Q Q", 'Q', new ItemStack(FronosItems.fronos_item, 1, 5) });
        GameRegistry.addRecipe(new ItemStack(FronosArmorItems.iridium_boots), new Object[] { "Q Q", "Q Q", 'Q', new ItemStack(FronosItems.fronos_item, 1, 5) });
        GameRegistry.addRecipe(new ItemStack(FronosArmorItems.breathable_black_diamond_helmet), new Object[] { "DDD", "DOD", 'D', new ItemStack(FronosItems.fronos_item, 1, 4), 'O', new ItemStack(GCItems.oxMask) });
        GameRegistry.addRecipe(new ItemStack(FronosArmorItems.breathable_iridium_helmet), new Object[] { "III", "IOI", 'I', new ItemStack(FronosItems.fronos_item, 1, 5), 'O', new ItemStack(GCItems.oxMask) });

        // Tools
        GameRegistry.addRecipe(new ItemStack(FronosToolsItems.black_diamond_hoe), new Object[] { "XX", " Y", " Y", 'X', new ItemStack(FronosItems.fronos_item, 1, 2), 'Y', new ItemStack(Items.stick) });
        GameRegistry.addRecipe(new ItemStack(FronosToolsItems.black_diamond_hoe), new Object[] { "XX", "Y ", "Y ", 'X', new ItemStack(FronosItems.fronos_item, 1, 2), 'Y', new ItemStack(Items.stick) });
        GameRegistry.addRecipe(new ItemStack(FronosToolsItems.black_diamond_axe), new Object[] { "XX", "XY", " Y", 'X', new ItemStack(FronosItems.fronos_item, 1, 2), 'Y', new ItemStack(Items.stick) });
        GameRegistry.addRecipe(new ItemStack(FronosToolsItems.black_diamond_axe), new Object[] { "XX", "YX", "Y ", 'X', new ItemStack(FronosItems.fronos_item, 1, 2), 'Y', new ItemStack(Items.stick) });
        GameRegistry.addRecipe(new ItemStack(FronosToolsItems.black_diamond_pickaxe), new Object[] { "XXX", " Y ", " Y ", 'X', new ItemStack(FronosItems.fronos_item, 1, 2), 'Y', new ItemStack(Items.stick) });
        GameRegistry.addRecipe(new ItemStack(FronosToolsItems.black_diamond_sword), new Object[] { "X", "X", "Y", 'X', new ItemStack(FronosItems.fronos_item, 1, 2), 'Y', new ItemStack(Items.stick) });
        GameRegistry.addRecipe(new ItemStack(FronosToolsItems.black_diamond_shovel), new Object[] { "X", "Y", "Y", 'X', new ItemStack(FronosItems.fronos_item, 1, 2), 'Y', new ItemStack(Items.stick) });
        GameRegistry.addRecipe(new ItemStack(FronosToolsItems.iridium_hoe), new Object[] { "XX", " Y", " Y", 'X', new ItemStack(FronosItems.fronos_item, 1, 5), 'Y', new ItemStack(Items.stick) });
        GameRegistry.addRecipe(new ItemStack(FronosToolsItems.iridium_hoe), new Object[] { "XX", "Y ", "Y ", 'X', new ItemStack(FronosItems.fronos_item, 1, 5), 'Y', new ItemStack(Items.stick) });
        GameRegistry.addRecipe(new ItemStack(FronosToolsItems.iridium_axe), new Object[] { "XX", "XY", " Y", 'X', new ItemStack(FronosItems.fronos_item, 1, 5), 'Y', new ItemStack(Items.stick) });
        GameRegistry.addRecipe(new ItemStack(FronosToolsItems.iridium_axe), new Object[] { "XX", "YX", "Y ", 'X', new ItemStack(FronosItems.fronos_item, 1, 5), 'Y', new ItemStack(Items.stick) });
        GameRegistry.addRecipe(new ItemStack(FronosToolsItems.iridium_pickaxe), new Object[] { "XXX", " Y ", " Y ", 'X', new ItemStack(FronosItems.fronos_item, 1, 5), 'Y', new ItemStack(Items.stick) });
        GameRegistry.addRecipe(new ItemStack(FronosToolsItems.iridium_sword), new Object[] { "X", "X", "Y", 'X', new ItemStack(FronosItems.fronos_item, 1, 5), 'Y', new ItemStack(Items.stick) });
        GameRegistry.addRecipe(new ItemStack(FronosToolsItems.iridium_shovel), new Object[] { "X", "Y", "Y", 'X', new ItemStack(FronosItems.fronos_item, 1, 5), 'Y', new ItemStack(Items.stick) });
        GameRegistry.addRecipe(new ItemStack(FronosToolsItems.fronos_rock_hoe), new Object[] { "XX", " Y", " Y", 'X', new ItemStack(FronosBlocks.fronos_block, 1, 1), 'Y', new ItemStack(Items.stick) });
        GameRegistry.addRecipe(new ItemStack(FronosToolsItems.fronos_rock_hoe), new Object[] { "XX", "Y ", "Y ", 'X', new ItemStack(FronosBlocks.fronos_block, 1, 1), 'Y', new ItemStack(Items.stick) });
        GameRegistry.addRecipe(new ItemStack(FronosToolsItems.fronos_rock_axe), new Object[] { "XX", "XY", " Y", 'X', new ItemStack(FronosBlocks.fronos_block, 1, 1), 'Y', new ItemStack(Items.stick) });
        GameRegistry.addRecipe(new ItemStack(FronosToolsItems.fronos_rock_axe), new Object[] { "XX", "YX", "Y ", 'X', new ItemStack(FronosBlocks.fronos_block, 1, 1), 'Y', new ItemStack(Items.stick) });
        GameRegistry.addRecipe(new ItemStack(FronosToolsItems.fronos_rock_pickaxe), new Object[] { "XXX", " Y ", " Y ", 'X', new ItemStack(FronosBlocks.fronos_block, 1, 1), 'Y', new ItemStack(Items.stick) });
        GameRegistry.addRecipe(new ItemStack(FronosToolsItems.fronos_rock_sword), new Object[] { "X", "X", "Y", 'X', new ItemStack(FronosBlocks.fronos_block, 1, 1), 'Y', new ItemStack(Items.stick) });
        GameRegistry.addRecipe(new ItemStack(FronosToolsItems.fronos_rock_shovel), new Object[] { "X", "Y", "Y", 'X', new ItemStack(FronosBlocks.fronos_block, 1, 1), 'Y', new ItemStack(Items.stick) });
    }

    private static void addBlockSmelting()
    {
        GameRegistry.addSmelting(new ItemStack(FronosBlocks.fronos_block, 1, 1), new ItemStack(FronosBlocks.fronos_block, 1, 0), 0.4F);
        GameRegistry.addSmelting(new ItemStack(FronosBlocks.fronos_sand, 1, 0), new ItemStack(Blocks.stained_glass, 1, 3), 0.45F);
        GameRegistry.addSmelting(new ItemStack(FronosBlocks.fronos_sand, 1, 1), new ItemStack(Blocks.stained_glass, 1, 0), 0.45F);
        GameRegistry.addSmelting(new ItemStack(FronosBlocks.fronos_sand, 1, 2), new ItemStack(FronosBlocks.cheese_glass), 0.5F);
        GameRegistry.addSmelting(new ItemStack(FronosBlocks.fronos_block, 1, 11), new ItemStack(FronosBlocks.fronos_block, 1, 12), 0.45F);
    }

    private static void addItemSmelting()
    {
        GameRegistry.addSmelting(new ItemStack(FronosItems.fronos_food, 1, 2), new ItemStack(FronosItems.fronos_food, 1, 3), 0.5F);
        GameRegistry.addSmelting(new ItemStack(FronosBlocks.jelly_ore, 1, 0), new ItemStack(FronosItems.jelly, 1, 0), 0.4F);
        GameRegistry.addSmelting(new ItemStack(FronosBlocks.jelly_ore, 1, 1), new ItemStack(FronosItems.jelly, 1, 1), 0.4F);
        GameRegistry.addSmelting(new ItemStack(FronosBlocks.jelly_ore, 1, 2), new ItemStack(FronosItems.jelly, 1, 2), 0.4F);
        GameRegistry.addSmelting(new ItemStack(FronosBlocks.jelly_ore, 1, 3), new ItemStack(FronosItems.jelly, 1, 3), 0.4F);
        GameRegistry.addSmelting(new ItemStack(FronosBlocks.jelly_ore, 1, 4), new ItemStack(FronosItems.jelly, 1, 4), 0.4F);
        GameRegistry.addSmelting(new ItemStack(FronosBlocks.jelly_ore, 1, 5), new ItemStack(FronosItems.jelly, 1, 5), 0.4F);
        GameRegistry.addSmelting(new ItemStack(FronosBlocks.jelly_ore, 1, 6), new ItemStack(FronosItems.jelly, 1, 6), 0.4F);
        GameRegistry.addSmelting(new ItemStack(FronosBlocks.jelly_ore, 1, 7), new ItemStack(FronosItems.jelly, 1, 7), 0.4F);

        GameRegistry.addSmelting(new ItemStack(FronosBlocks.fronos_block, 1, 2), new ItemStack(Items.iron_ingot), 0.8F);
        GameRegistry.addSmelting(new ItemStack(FronosBlocks.fronos_block, 1, 3), new ItemStack(Items.coal), 0.8F);
        GameRegistry.addSmelting(new ItemStack(FronosBlocks.fronos_block, 1, 4), new ItemStack(GCItems.basicItem, 1, 5), 0.8F);
        GameRegistry.addSmelting(new ItemStack(FronosBlocks.fronos_block, 1, 5), new ItemStack(GCItems.basicItem, 1, 4), 0.8F);
        GameRegistry.addSmelting(new ItemStack(FronosBlocks.fronos_block, 1, 6), new ItemStack(GCItems.basicItem, 1, 3), 0.8F);
        GameRegistry.addSmelting(new ItemStack(FronosBlocks.fronos_block, 1, 7), new ItemStack(Items.dye, 1, 4), 0.8F);
        GameRegistry.addSmelting(new ItemStack(FronosBlocks.fronos_block, 1, 8), new ItemStack(FronosItems.fronos_item, 1, 0), 0.8F);
        GameRegistry.addSmelting(new ItemStack(FronosBlocks.fronos_block, 1, 9), new ItemStack(FronosItems.fronos_item, 1, 2), 0.8F);
        GameRegistry.addSmelting(new ItemStack(FronosBlocks.fronos_block, 1, 10), new ItemStack(FronosItems.fronos_item, 1, 3), 0.8F);
        GameRegistry.addSmelting(new ItemStack(FronosBlocks.fronos_block_1, 1, 0), new ItemStack(Items.gold_ingot), 0.8F);
        GameRegistry.addSmelting(new ItemStack(FronosBlocks.fronos_block_1, 1, 1), new ItemStack(Items.diamond), 0.8F);
        GameRegistry.addSmelting(new ItemStack(FronosBlocks.fronos_block_1, 1, 2), new ItemStack(Items.emerald), 0.8F);
        GameRegistry.addSmelting(new ItemStack(FronosBlocks.fronos_block_1, 1, 3), new ItemStack(GCItems.basicItem, 1, 2), 0.8F);
    }

    private static void addOreDictRecipe()
    {
        OreDictionary.registerOre("plankWood", new ItemStack(FronosBlocks.fronos_wooden_planks, 1, OreDictionary.WILDCARD_VALUE));
        OreDictionary.registerOre("stairWood", new ItemStack(FronosBlocks.coconut_wood_stairs));
        OreDictionary.registerOre("stairWood", new ItemStack(FronosBlocks.maple_wood_stairs));
        OreDictionary.registerOre("treeSapling", new ItemStack(FronosBlocks.fronos_sapling, 1, OreDictionary.WILDCARD_VALUE));
        OreDictionary.registerOre("treeLeaves", new ItemStack(FronosBlocks.fronos_colorized_leaves, 1, OreDictionary.WILDCARD_VALUE));
        OreDictionary.registerOre("treeLeaves", new ItemStack(FronosBlocks.fronos_leaves, 1, OreDictionary.WILDCARD_VALUE));
        OreDictionary.registerOre("logWood", new ItemStack(FronosBlocks.fronos_log, 1, OreDictionary.WILDCARD_VALUE));
        OreDictionary.registerOre("candy", new ItemStack(FronosItems.candy_cane, 1, 0));
        OreDictionary.registerOre("candy", new ItemStack(FronosItems.candy_cane, 1, 1));
        OreDictionary.registerOre("candy", new ItemStack(FronosItems.candy_cane, 1, 2));
        OreDictionary.registerOre("candy", new ItemStack(FronosItems.candy_cane, 1, 3));
        OreDictionary.registerOre("candy", new ItemStack(FronosItems.candy_cane, 1, 4));
        OreDictionary.registerOre("candy", new ItemStack(FronosItems.candy_cane, 1, 5));
        OreDictionary.registerOre("candy", new ItemStack(FronosItems.candy_cane, 1, 6));
        OreDictionary.registerOre("candy", new ItemStack(FronosItems.candy_cane, 1, 7));
        OreDictionary.registerOre("candyCane", new ItemStack(FronosBlocks.candy_cane1, 1, 0));
        OreDictionary.registerOre("candyCane", new ItemStack(FronosBlocks.candy_cane1, 1, 1));
        OreDictionary.registerOre("candyCane", new ItemStack(FronosBlocks.candy_cane1, 1, 2));
        OreDictionary.registerOre("candyCane", new ItemStack(FronosBlocks.candy_cane1, 1, 3));
        OreDictionary.registerOre("candyCane", new ItemStack(FronosBlocks.candy_cane2, 1, 0));
        OreDictionary.registerOre("candyCane", new ItemStack(FronosBlocks.candy_cane2, 1, 1));
        OreDictionary.registerOre("candyCane", new ItemStack(FronosBlocks.candy_cane2, 1, 2));
        OreDictionary.registerOre("candyCane", new ItemStack(FronosBlocks.candy_cane2, 1, 3));
        OreDictionary.registerOre("fronosSandstone", new ItemStack(FronosBlocks.fronos_sandstone, 1, 0));
        OreDictionary.registerOre("fronosSandstone", new ItemStack(FronosBlocks.fronos_sandstone, 1, 1));
        OreDictionary.registerOre("fronosSandstone", new ItemStack(FronosBlocks.fronos_sandstone, 1, 2));
        OreDictionary.registerOre("whiteSandstone", new ItemStack(FronosBlocks.fronos_sandstone, 1, 3));
        OreDictionary.registerOre("whiteSandstone", new ItemStack(FronosBlocks.fronos_sandstone, 1, 4));
        OreDictionary.registerOre("whiteSandstone", new ItemStack(FronosBlocks.fronos_sandstone, 1, 5));
        OreDictionary.registerOre("cheeseSandstone", new ItemStack(FronosBlocks.fronos_sandstone, 1, 6));
        OreDictionary.registerOre("cheeseSandstone", new ItemStack(FronosBlocks.fronos_sandstone, 1, 7));
        OreDictionary.registerOre("cheeseSandstone", new ItemStack(FronosBlocks.fronos_sandstone, 1, 8));
        OreDictionary.registerOre("sand", FronosBlocks.fronos_sand);
        OreDictionary.registerOre("gemDiamond", new ItemStack(FronosItems.fronos_item, 1, 2));
        OreDictionary.registerOre("ingotIridium", new ItemStack(FronosItems.fronos_item, 1, 3));
    }

    private static void addTier7RocketRecipes()
    {
        HashMap<Integer, ItemStack> input = new HashMap<Integer, ItemStack>();
        input.put(1, new ItemStack(NibiruItems.tier7_rocket_module, 1, 4));
        input.put(2, new ItemStack(NibiruItems.tier7_rocket_module, 1, 2));
        input.put(3, new ItemStack(NibiruItems.tier7_rocket_module, 1, 2));
        input.put(4, new ItemStack(NibiruItems.tier7_rocket_module, 1, 2));
        input.put(5, new ItemStack(NibiruItems.tier7_rocket_module, 1, 2));
        input.put(6, new ItemStack(NibiruItems.tier7_rocket_module, 1, 2));
        input.put(7, new ItemStack(NibiruItems.tier7_rocket_module, 1, 2));
        input.put(8, new ItemStack(NibiruItems.tier7_rocket_module, 1, 2));
        input.put(9, new ItemStack(NibiruItems.tier7_rocket_module, 1, 2));
        input.put(10, new ItemStack(NibiruItems.tier7_rocket_module, 1, 2));
        input.put(11, new ItemStack(NibiruItems.tier7_rocket_module, 1, 2));//Plate
        input.put(12, new ItemStack(NibiruItems.tier7_rocket_module, 1, 1));//Booster
        input.put(13, new ItemStack(NibiruItems.tier7_rocket_module, 1, 3));
        input.put(14, new ItemStack(NibiruItems.tier7_rocket_module, 1, 3));
        input.put(15, new ItemStack(NibiruItems.tier7_rocket_module, 1, 0));//Engine
        input.put(16, new ItemStack(NibiruItems.tier7_rocket_module, 1, 1));
        input.put(17, new ItemStack(NibiruItems.tier7_rocket_module, 1, 3));
        input.put(18, new ItemStack(NibiruItems.tier7_rocket_module, 1, 3));
        input.put(19, null);
        input.put(20, null);
        input.put(21, null);
        Tier7RocketRecipes.addTier7RocketRecipe(new ItemStack(FronosItems.tier7_rocket, 1, 0), input);

        HashMap<Integer, ItemStack> input2 = new HashMap<Integer, ItemStack>(input);
        input2.put(19, new ItemStack(Blocks.chest));
        input2.put(20, null);
        input2.put(21, null);
        Tier7RocketRecipes.addTier7RocketRecipe(new ItemStack(FronosItems.tier7_rocket, 1, 1), input2);

        input2 = new HashMap<Integer, ItemStack>(input);
        input2.put(19, null);
        input2.put(20, new ItemStack(Blocks.chest));
        input2.put(21, null);
        Tier7RocketRecipes.addTier7RocketRecipe(new ItemStack(FronosItems.tier7_rocket, 1, 1), input2);

        input2 = new HashMap<Integer, ItemStack>(input);
        input2.put(19, null);
        input2.put(20, null);
        input2.put(21, new ItemStack(Blocks.chest));
        Tier7RocketRecipes.addTier7RocketRecipe(new ItemStack(FronosItems.tier7_rocket, 1, 1), input2);

        input2 = new HashMap<Integer, ItemStack>(input);
        input2.put(19, new ItemStack(Blocks.chest));
        input2.put(20, new ItemStack(Blocks.chest));
        input2.put(21, null);
        Tier7RocketRecipes.addTier7RocketRecipe(new ItemStack(FronosItems.tier7_rocket, 1, 2), input2);

        input2 = new HashMap<Integer, ItemStack>(input);
        input2.put(19, new ItemStack(Blocks.chest));
        input2.put(20, null);
        input2.put(21, new ItemStack(Blocks.chest));
        Tier7RocketRecipes.addTier7RocketRecipe(new ItemStack(FronosItems.tier7_rocket, 1, 2), input2);

        input2 = new HashMap<Integer, ItemStack>(input);
        input2.put(19, null);
        input2.put(20, new ItemStack(Blocks.chest));
        input2.put(21, new ItemStack(Blocks.chest));
        Tier7RocketRecipes.addTier7RocketRecipe(new ItemStack(FronosItems.tier7_rocket, 1, 2), input2);

        input2 = new HashMap<Integer, ItemStack>(input);
        input2.put(19, new ItemStack(Blocks.chest));
        input2.put(20, new ItemStack(Blocks.chest));
        input2.put(21, new ItemStack(Blocks.chest));
        Tier7RocketRecipes.addTier7RocketRecipe(new ItemStack(FronosItems.tier7_rocket, 1, 3), input2);
    }

    private static void addExtractingRecipe()
    {
        CandyExtractorRecipes.extracting().itemStackToItemStack(new ItemStack(FronosBlocks.candy_cane1, 1, 0), new ItemStack(FronosItems.candy_cane, 4, 0), 0.4F);
        CandyExtractorRecipes.extracting().itemStackToItemStack(new ItemStack(FronosBlocks.candy_cane1, 1, 1), new ItemStack(FronosItems.candy_cane, 4, 1), 0.4F);
        CandyExtractorRecipes.extracting().itemStackToItemStack(new ItemStack(FronosBlocks.candy_cane1, 1, 2), new ItemStack(FronosItems.candy_cane, 4, 2), 0.4F);
        CandyExtractorRecipes.extracting().itemStackToItemStack(new ItemStack(FronosBlocks.candy_cane1, 1, 3), new ItemStack(FronosItems.candy_cane, 4, 3), 0.4F);
        CandyExtractorRecipes.extracting().itemStackToItemStack(new ItemStack(FronosBlocks.candy_cane2, 1, 0), new ItemStack(FronosItems.candy_cane, 4, 4), 0.4F);
        CandyExtractorRecipes.extracting().itemStackToItemStack(new ItemStack(FronosBlocks.candy_cane2, 1, 1), new ItemStack(FronosItems.candy_cane, 4, 5), 0.4F);
        CandyExtractorRecipes.extracting().itemStackToItemStack(new ItemStack(FronosBlocks.candy_cane2, 1, 2), new ItemStack(FronosItems.candy_cane, 4, 6), 0.4F);
        CandyExtractorRecipes.extracting().itemStackToItemStack(new ItemStack(FronosBlocks.candy_cane2, 1, 3), new ItemStack(FronosItems.candy_cane, 4, 7), 0.4F);
        CandyExtractorRecipes.extracting().itemStackToItemStack(new ItemStack(FronosBlocks.frosted_cake, 1, 0), new ItemStack(FronosBlocks.cake_bread), 0.6F);
        CandyExtractorRecipes.extracting().itemStackToItemStack(new ItemStack(FronosBlocks.frosted_cake, 1, 1), new ItemStack(FronosBlocks.white_cake_bread), 0.6F);
        CandyExtractorRecipes.extracting().itemStackToItemStack(new ItemStack(FronosBlocks.frosted_cake, 1, 2), new ItemStack(FronosBlocks.chocolate_cake_bread), 0.6F);
        CandyExtractorRecipes.extracting().itemStackToItemStack(new ItemStack(FronosBlocks.frosted_cake, 1, 3), new ItemStack(Items.cake), 0.6F);
        CandyExtractorRecipes.extracting().itemStackToItemStack(new ItemStack(FronosBlocks.frosted_cake, 1, 4), new ItemStack(FronosBlocks.pink_cake), 0.6F);
        CandyExtractorRecipes.extracting().itemStackToItemStack(new ItemStack(FronosBlocks.frosted_cake, 1, 5), new ItemStack(FronosBlocks.white_cake), 0.6F);
        CandyExtractorRecipes.extracting().itemStackToItemStack(new ItemStack(FronosBlocks.frosted_cake, 1, 6), new ItemStack(FronosBlocks.chocolate_cake), 0.6F);
        CandyExtractorRecipes.extracting().itemStackToItemStack(new ItemStack(FronosBlocks.jelly_block, 1, 0), new ItemStack(FronosItems.jelly, 4, 0), 0.4F);
        CandyExtractorRecipes.extracting().itemStackToItemStack(new ItemStack(FronosBlocks.jelly_block, 1, 1), new ItemStack(FronosItems.jelly, 4, 1), 0.4F);
        CandyExtractorRecipes.extracting().itemStackToItemStack(new ItemStack(FronosBlocks.jelly_block, 1, 2), new ItemStack(FronosItems.jelly, 4, 2), 0.4F);
        CandyExtractorRecipes.extracting().itemStackToItemStack(new ItemStack(FronosBlocks.jelly_block, 1, 3), new ItemStack(FronosItems.jelly, 4, 3), 0.4F);
        CandyExtractorRecipes.extracting().itemStackToItemStack(new ItemStack(FronosBlocks.jelly_block, 1, 4), new ItemStack(FronosItems.jelly, 4, 4), 0.4F);
        CandyExtractorRecipes.extracting().itemStackToItemStack(new ItemStack(FronosBlocks.jelly_block, 1, 5), new ItemStack(FronosItems.jelly, 4, 5), 0.4F);
        CandyExtractorRecipes.extracting().itemStackToItemStack(new ItemStack(FronosBlocks.jelly_block, 1, 6), new ItemStack(FronosItems.jelly, 4, 6), 0.4F);
        CandyExtractorRecipes.extracting().itemStackToItemStack(new ItemStack(FronosBlocks.jelly_slime_egg, 1, 0), new ItemStack(FronosItems.jelly, 4, 0), 0.4F);
        CandyExtractorRecipes.extracting().itemStackToItemStack(new ItemStack(FronosBlocks.jelly_slime_egg, 1, 1), new ItemStack(FronosItems.jelly, 4, 1), 0.4F);
        CandyExtractorRecipes.extracting().itemStackToItemStack(new ItemStack(FronosBlocks.jelly_slime_egg, 1, 2), new ItemStack(FronosItems.jelly, 4, 2), 0.4F);
        CandyExtractorRecipes.extracting().itemStackToItemStack(new ItemStack(FronosBlocks.jelly_slime_egg, 1, 3), new ItemStack(FronosItems.jelly, 4, 3), 0.4F);
        CandyExtractorRecipes.extracting().itemStackToItemStack(new ItemStack(FronosBlocks.jelly_slime_egg, 1, 4), new ItemStack(FronosItems.jelly, 4, 4), 0.4F);
        CandyExtractorRecipes.extracting().itemStackToItemStack(new ItemStack(FronosBlocks.jelly_slime_egg, 1, 5), new ItemStack(FronosItems.jelly, 4, 5), 0.4F);
        CandyExtractorRecipes.extracting().itemStackToItemStack(new ItemStack(FronosBlocks.jelly_slime_egg, 1, 6), new ItemStack(FronosItems.jelly, 4, 6), 0.4F);
        CandyExtractorRecipes.extracting().itemStackToItemStack(new ItemStack(FronosBlocks.jelly_slime_egg, 1, 7), new ItemStack(FronosItems.jelly, 4, 7), 0.4F);
        CandyExtractorRecipes.extracting().itemStackToItemStack(new ItemStack(FronosBlocks.chocolate_block), new ItemStack(FronosItems.fronos_food2, 4, 1), 0.8F);
    }
}