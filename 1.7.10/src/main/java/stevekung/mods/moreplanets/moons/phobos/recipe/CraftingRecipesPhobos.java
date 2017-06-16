/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 *
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.moons.phobos.recipe;

import cpw.mods.fml.common.registry.GameRegistry;
import micdoodle8.mods.galacticraft.core.items.GCItems;
import micdoodle8.mods.galacticraft.planets.mars.items.MarsItems;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import stevekung.mods.moreplanets.moons.phobos.blocks.PhobosBlocks;

public class CraftingRecipesPhobos
{
    public static void loadRecipes()
    {
        CraftingRecipesPhobos.addBlockRecipes();
        CraftingRecipesPhobos.addItemRecipes();
        CraftingRecipesPhobos.addBlockSmelting();
        CraftingRecipesPhobos.addItemSmelting();
    }

    private static void addBlockRecipes()
    {
        //		// Blocks
        //		GameRegistry.addRecipe(new ItemStack(SiriusBBlocks.sirius_b_block, 1, 8), new Object[] { "SSS", "SSS", "SSS", 'S', new ItemStack(SiriusBItems.sirius_b_item, 1, 3) });
        //		GameRegistry.addRecipe(new ItemStack(SiriusBBlocks.sirius_glowstone), new Object[] { "GG", "GG", 'G', new ItemStack(SiriusBItems.sirius_glowstone_dust) });
        //		GameRegistry.addRecipe(new ItemStack(SiriusBBlocks.sirius_redstone_lamp_off), new Object[] { " R ", "RGR", " R ", 'G', new ItemStack(SiriusBBlocks.sirius_glowstone), 'R', new ItemStack(Items.redstone) });
        //
        //		// Walls
        //		GameRegistry.addRecipe(new ItemStack(MPBlocks.stone_wall, 6, 12), new Object[] { "CCC", "CCC", 'C', new ItemStack(SiriusBBlocks.sirius_b_block, 1, 3) });
        //		GameRegistry.addRecipe(new ItemStack(MPBlocks.dungeon_brick_wall, 6, 6), new Object[] { "CCC", "CCC", 'C', new ItemStack(SiriusBBlocks.sirius_b_block, 1, 9) });
        //
        //		// Slabs
        //		GameRegistry.addRecipe(new ItemStack(MPBlocks.stone_slab_half2, 6, 4), new Object[] { "CCC", 'C', new ItemStack(SiriusBBlocks.sirius_b_block, 1, 3) });
        //		GameRegistry.addRecipe(new ItemStack(MPBlocks.dungeon_brick_slab_half, 6, 6), new Object[] { "CCC", 'C', new ItemStack(SiriusBBlocks.sirius_b_block, 1, 9) });
        //
        //		// Sirius B Cobblestone Stairs
        //		GameRegistry.addRecipe(new ItemStack(SiriusBBlocks.sirius_cobblestone_stairs, 4), new Object[] { "  X", " XX", "XXX", 'X', new ItemStack(SiriusBBlocks.sirius_b_block, 1, 3) });
        //		GameRegistry.addRecipe(new ItemStack(SiriusBBlocks.sirius_cobblestone_stairs, 4), new Object[] { "X  ", "XX ", "XXX", 'X', new ItemStack(SiriusBBlocks.sirius_b_block, 1, 3) });
        //
        //		// Sirius B Dungeon Brick Stairs
        //		GameRegistry.addRecipe(new ItemStack(SiriusBBlocks.sirius_dungeon_brick_stairs, 4), new Object[] { "  X", " XX", "XXX", 'X', new ItemStack(SiriusBBlocks.sirius_b_block, 1, 9) });
        //		GameRegistry.addRecipe(new ItemStack(SiriusBBlocks.sirius_dungeon_brick_stairs, 4), new Object[] { "X  ", "XX ", "XXX", 'X', new ItemStack(SiriusBBlocks.sirius_b_block, 1, 9) });
    }

    private static void addItemRecipes()
    {
        // Items
        //		GameRegistry.addRecipe(new ItemStack(Items.diamond), new Object[] { "LLL", "LLL", "LLL", 'L', new ItemStack(SiriusBItems.sirius_b_item, 1, 1) });
        //		GameRegistry.addRecipe(new ItemStack(SiriusBItems.sirius_obsidian_bucket), new Object[] { "O O", " O ", 'O', new ItemStack(SiriusBBlocks.sirius_obsidian) });
        //		GameRegistry.addShapelessRecipe(new ItemStack(SiriusBItems.sirius_b_item, 9, 3), new ItemStack(SiriusBBlocks.sirius_b_block, 1, 8) );

        /*// Armor Stuff
		GameRegistry.addRecipe(new ItemStack(PolongniusArmorItems.leatherOfCheeseHelmet.get(), 1, 0), new Object[] { "QQQ", "Q Q", 'Q', new ItemStack(PolongniusItems.polongniusBasicItem.get(), 1, 9) });
		GameRegistry.addRecipe(new ItemStack(PolongniusArmorItems.leatherOfCheeseChestplate.get(), 1, 0), new Object[] { "Q Q", "QQQ", "QQQ", 'Q', new ItemStack(PolongniusItems.polongniusBasicItem.get(), 1, 9) });
		GameRegistry.addRecipe(new ItemStack(PolongniusArmorItems.leatherOfCheeseLeggings.get(), 1, 0), new Object[] { "QQQ", "Q Q", "Q Q", 'Q', new ItemStack(PolongniusItems.polongniusBasicItem.get(), 1, 9) });
		GameRegistry.addRecipe(new ItemStack(PolongniusArmorItems.leatherOfCheeseBoots.get(), 1, 0), new Object[] { "Q Q", "Q Q", 'Q', new ItemStack(PolongniusItems.polongniusBasicItem.get(), 1, 9) });

		// Tools Stuff
		GameRegistry.addRecipe(new ItemStack(PolongniusToolsItems.polongniusHoe.get()), new Object[] { "XX", " Y", " Y", 'X', new ItemStack(PolongniusItems.polongniusBasicItem.get(), 1, 5), 'Y', new ItemStack(PolongniusItems.polongniusBasicItem.get(), 1, 7) });
		GameRegistry.addRecipe(new ItemStack(PolongniusToolsItems.polongniusHoe.get()), new Object[] { "XX", "Y ", "Y ", 'X', new ItemStack(PolongniusItems.polongniusBasicItem.get(), 1, 5), 'Y', new ItemStack(PolongniusItems.polongniusBasicItem.get(), 1, 7) });
		GameRegistry.addRecipe(new ItemStack(PolongniusToolsItems.polongniusAxe.get()), new Object[] { "XX", "XY", " Y", 'X', new ItemStack(PolongniusItems.polongniusBasicItem.get(), 1, 5), 'Y', new ItemStack(PolongniusItems.polongniusBasicItem.get(), 1, 7) });
		GameRegistry.addRecipe(new ItemStack(PolongniusToolsItems.polongniusAxe.get()), new Object[] { "XX", "YX", "Y ", 'X', new ItemStack(PolongniusItems.polongniusBasicItem.get(), 1, 5), 'Y', new ItemStack(PolongniusItems.polongniusBasicItem.get(), 1, 7) });
		GameRegistry.addRecipe(new ItemStack(PolongniusToolsItems.polongniusPickaxe.get()), new Object[] { "XXX", " Y ", " Y ", 'X', new ItemStack(PolongniusItems.polongniusBasicItem.get(), 1, 5), 'Y', new ItemStack(PolongniusItems.polongniusBasicItem.get(), 1, 7) });
		GameRegistry.addRecipe(new ItemStack(PolongniusToolsItems.polongniusSword.get()), new Object[] { "X", "X", "Y", 'X', new ItemStack(PolongniusItems.polongniusBasicItem.get(), 1, 5), 'Y', new ItemStack(PolongniusItems.polongniusBasicItem.get(), 1, 7) });
		GameRegistry.addRecipe(new ItemStack(PolongniusToolsItems.polongniusSpade.get()), new Object[] { "X", "Y", "Y", 'X', new ItemStack(PolongniusItems.polongniusBasicItem.get(), 1, 5), 'Y', new ItemStack(PolongniusItems.polongniusBasicItem.get(), 1, 7) });
		GameRegistry.addRecipe(new ItemStack(PolongniusToolsItems.palladiumHoe.get()), new Object[] { "XX", " Y", " Y", 'X', new ItemStack(PolongniusItems.polongniusBasicItem.get(), 1, 6), 'Y', new ItemStack(PolongniusItems.polongniusBasicItem.get(), 1, 8) });
		GameRegistry.addRecipe(new ItemStack(PolongniusToolsItems.palladiumHoe.get()), new Object[] { "XX", "Y ", "Y ", 'X', new ItemStack(PolongniusItems.polongniusBasicItem.get(), 1, 6), 'Y', new ItemStack(PolongniusItems.polongniusBasicItem.get(), 1, 8) });
		GameRegistry.addRecipe(new ItemStack(PolongniusToolsItems.palladiumAxe.get()), new Object[] { "XX", "XY", " Y", 'X', new ItemStack(PolongniusItems.polongniusBasicItem.get(), 1, 6), 'Y', new ItemStack(PolongniusItems.polongniusBasicItem.get(), 1, 8) });
		GameRegistry.addRecipe(new ItemStack(PolongniusToolsItems.palladiumAxe.get()), new Object[] { "XX", "YX", "Y ", 'X', new ItemStack(PolongniusItems.polongniusBasicItem.get(), 1, 6), 'Y', new ItemStack(PolongniusItems.polongniusBasicItem.get(), 1, 8) });
		GameRegistry.addRecipe(new ItemStack(PolongniusToolsItems.palladiumPickaxe.get()), new Object[] { "XXX", " Y ", " Y ", 'X', new ItemStack(PolongniusItems.polongniusBasicItem.get(), 1, 6), 'Y', new ItemStack(PolongniusItems.polongniusBasicItem.get(), 1, 8) });
		GameRegistry.addRecipe(new ItemStack(PolongniusToolsItems.palladiumSword.get()), new Object[] { "X", "X", "Y", 'X', new ItemStack(PolongniusItems.polongniusBasicItem.get(), 1, 6), 'Y', new ItemStack(PolongniusItems.polongniusBasicItem.get(), 1, 8) });
		GameRegistry.addRecipe(new ItemStack(PolongniusToolsItems.palladiumSpade.get()), new Object[] { "X", "Y", "Y", 'X', new ItemStack(PolongniusItems.polongniusBasicItem.get(), 1, 6), 'Y', new ItemStack(PolongniusItems.polongniusBasicItem.get(), 1, 8) });*/
    }

    private static void addBlockSmelting()
    {
        GameRegistry.addSmelting(new ItemStack(PhobosBlocks.phobos_block, 1, 3), new ItemStack(PhobosBlocks.phobos_block, 1, 2), 0.5F);
    }

    private static void addItemSmelting()
    {
        GameRegistry.addSmelting(new ItemStack(PhobosBlocks.phobos_block, 1, 4), new ItemStack(GCItems.basicItem, 1, 4), 0.7F);
        GameRegistry.addSmelting(new ItemStack(PhobosBlocks.phobos_block, 1, 5), new ItemStack(GCItems.basicItem, 1, 3), 0.7F);
        GameRegistry.addSmelting(new ItemStack(PhobosBlocks.phobos_block, 1, 6), new ItemStack(Items.iron_ingot), 0.8F);
        GameRegistry.addSmelting(new ItemStack(PhobosBlocks.phobos_block, 1, 7), new ItemStack(MarsItems.marsItemBasic, 1, 2), 0.8F);
    }
}