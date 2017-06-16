/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 *
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.planets.mercury.recipe;

import cpw.mods.fml.common.registry.GameRegistry;
import micdoodle8.mods.galacticraft.core.items.GCItems;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import stevekung.mods.moreplanets.core.init.MPBlocks;
import stevekung.mods.moreplanets.planets.mercury.blocks.MercuryBlocks;
import stevekung.mods.moreplanets.planets.mercury.items.MercuryItems;

public class CraftingRecipesMercury
{
    public static void loadRecipes()
    {
        CraftingRecipesMercury.addBlockRecipes();
        CraftingRecipesMercury.addItemRecipes();
        CraftingRecipesMercury.addBlockSmelting();
        CraftingRecipesMercury.addItemSmelting();
    }

    private static void addBlockRecipes()
    {
        GameRegistry.addRecipe(new ItemStack(MercuryBlocks.mercury_block, 1, 10), new Object[] { "CCC", "CCC", "CCC", 'C', new ItemStack(MercuryItems.mercury_item, 1, 3) });

        // Mercury Cobblestone Stairs
        GameRegistry.addRecipe(new ItemStack(MercuryBlocks.mercury_cobblestone_stairs, 4), new Object[] { "  X", " XX", "XXX", 'X', new ItemStack(MercuryBlocks.mercury_block, 1, 3) });
        GameRegistry.addRecipe(new ItemStack(MercuryBlocks.mercury_cobblestone_stairs, 4), new Object[] { "X  ", "XX ", "XXX", 'X', new ItemStack(MercuryBlocks.mercury_block, 1, 3) });

        // Mercury Dungeon Brick Stairs
        GameRegistry.addRecipe(new ItemStack(MercuryBlocks.mercury_dungeon_brick_stairs, 4), new Object[] { "  X", " XX", "XXX", 'X', new ItemStack(MercuryBlocks.mercury_block, 1, 11) });
        GameRegistry.addRecipe(new ItemStack(MercuryBlocks.mercury_dungeon_brick_stairs, 4), new Object[] { "X  ", "XX ", "XXX", 'X', new ItemStack(MercuryBlocks.mercury_block, 1, 11) });

        // Slabs
        GameRegistry.addRecipe(new ItemStack(MPBlocks.stone_slab_half2, 6, 5), new Object[] { "XXX", 'X', new ItemStack(MercuryBlocks.mercury_block, 1, 3) });
        GameRegistry.addRecipe(new ItemStack(MPBlocks.dungeon_brick_slab_half, 6, 7), new Object[] { "XXX", 'X', new ItemStack(MercuryBlocks.mercury_block, 1, 11) });

        // Walls
        GameRegistry.addRecipe(new ItemStack(MPBlocks.stone_wall, 6, 13), new Object[] { "XXX", "XXX", 'X', new ItemStack(MercuryBlocks.mercury_block, 1, 3) });
        GameRegistry.addRecipe(new ItemStack(MPBlocks.dungeon_brick_wall, 6, 7), new Object[] { "XXX", "XXX", 'X', new ItemStack(MercuryBlocks.mercury_block, 1, 11) });
    }

    private static void addItemRecipes()
    {
        /**@ItemsRecipes**/
        /*GameRegistry.addRecipe(new ItemStack(KoentusItems.koentus_item, 9, 0), new Object[] { "C", 'C', new ItemStack(KoentusBlocks.koentus_block, 1, 9) });
		GameRegistry.addRecipe(new ItemStack(KoentusItems.koentus_item, 9, 1), new Object[] { "M", 'M', new ItemStack(KoentusBlocks.koentus_block, 1, 10) });
		GameRegistry.addRecipe(new ItemStack(KoentusItems.koentus_item, 9, 4), new Object[] { "M", 'M', new ItemStack(KoentusBlocks.koentus_block, 1, 15) });
		GameRegistry.addRecipe(new ItemStack(KoentusItems.koentus_meteor_chunk, 3), new Object[] { "M", 'M', new ItemStack(KoentusItems.koentus_item, 1, 3) });

		/**@ArmorRecipes**/
        /*GameRegistry.addRecipe(new ItemStack(KoentusArmorItems.koentus_meteoric_iron_helmet), new Object[] { "QQQ", "Q Q", 'Q', new ItemStack(KoentusItems.koentus_item, 1, 6) });
		GameRegistry.addRecipe(new ItemStack(KoentusArmorItems.koentus_meteoric_iron_chestplate), new Object[] { "Q Q", "QQQ", "QQQ", 'Q', new ItemStack(KoentusItems.koentus_item, 1, 6) });
		GameRegistry.addRecipe(new ItemStack(KoentusArmorItems.koentus_meteoric_iron_leggings), new Object[] { "QQQ", "Q Q", "Q Q", 'Q', new ItemStack(KoentusItems.koentus_item, 1, 6) });
		GameRegistry.addRecipe(new ItemStack(KoentusArmorItems.koentus_meteoric_iron_boots), new Object[] { "Q Q", "Q Q", 'Q', new ItemStack(KoentusItems.koentus_item, 1, 6) });
		GameRegistry.addRecipe(new ItemStack(KoentusArmorItems.white_crystal_helmet), new Object[] { "QQQ", "Q Q", 'Q', new ItemStack(KoentusItems.koentus_item, 1, 5) });
		GameRegistry.addRecipe(new ItemStack(KoentusArmorItems.white_crystal_chestplate), new Object[] { "Q Q", "QQQ", "QQQ", 'Q', new ItemStack(KoentusItems.koentus_item, 1, 5) });
		GameRegistry.addRecipe(new ItemStack(KoentusArmorItems.white_crystal_leggings), new Object[] { "QQQ", "Q Q", "Q Q", 'Q', new ItemStack(KoentusItems.koentus_item, 1, 5) });
		GameRegistry.addRecipe(new ItemStack(KoentusArmorItems.white_crystal_boots), new Object[] { "Q Q", "Q Q", 'Q', new ItemStack(KoentusItems.koentus_item, 1, 5) });
		GameRegistry.addRecipe(new ItemStack(KoentusArmorItems.breathableKoentusMeteorHelmet), new Object[] { "QQQ", "QOQ", 'Q', new ItemStack(KoentusItems.koentus_item, 1, 6), 'O', new ItemStack(GCItems.oxMask) });
		GameRegistry.addRecipe(new ItemStack(KoentusArmorItems.breathableWhiteCrystalHelmet), new Object[] { "QQQ", "QOQ", 'Q', new ItemStack(KoentusItems.koentus_item, 1, 5), 'O', new ItemStack(GCItems.oxMask) });

		/**@ToolsRecipes**/
        /*GameRegistry.addRecipe(new ItemStack(KoentusToolsItems.koentus_meteoric_iron_hoe), new Object[] { "XX", " Y", " Y", 'X', new ItemStack(KoentusItems.koentus_item, 1, 6), 'Y', new ItemStack(DionaItems.diona_item, 1, 10) });
		GameRegistry.addRecipe(new ItemStack(KoentusToolsItems.koentus_meteoric_iron_hoe), new Object[] { "XX", "Y ", "Y ", 'X', new ItemStack(KoentusItems.koentus_item, 1, 6), 'Y', new ItemStack(DionaItems.diona_item, 1, 10) });
		GameRegistry.addRecipe(new ItemStack(KoentusToolsItems.koentus_meteoric_iron_axe), new Object[] { "XX", "XY", " Y", 'X', new ItemStack(KoentusItems.koentus_item, 1, 6), 'Y', new ItemStack(DionaItems.diona_item, 1, 10) });
		GameRegistry.addRecipe(new ItemStack(KoentusToolsItems.koentus_meteoric_iron_axe), new Object[] { "XX", "YX", "Y ", 'X', new ItemStack(KoentusItems.koentus_item, 1, 6), 'Y', new ItemStack(DionaItems.diona_item, 1, 10) });
		GameRegistry.addRecipe(new ItemStack(KoentusToolsItems.koentus_meteoric_iron_pickaxe), new Object[] { "XXX", " Y ", " Y ", 'X', new ItemStack(KoentusItems.koentus_item, 1, 6), 'Y', new ItemStack(DionaItems.diona_item, 1, 10) });
		GameRegistry.addRecipe(new ItemStack(KoentusToolsItems.koentus_meteoric_iron_sword), new Object[] { "X", "X", "Y", 'X', new ItemStack(KoentusItems.koentus_item, 1, 6), 'Y', new ItemStack(DionaItems.diona_item, 1, 10) });
		GameRegistry.addRecipe(new ItemStack(KoentusToolsItems.koentus_meteoric_iron_shovel), new Object[] { "X", "Y", "Y", 'X', new ItemStack(KoentusItems.koentus_item, 1, 6), 'Y', new ItemStack(DionaItems.diona_item, 1, 10) });
		GameRegistry.addRecipe(new ItemStack(KoentusToolsItems.white_crystal_hoe), new Object[] { "XX", " Y", " Y", 'X', new ItemStack(KoentusItems.koentus_item, 1, 5), 'Y', new ItemStack(DionaItems.diona_item, 1, 10) });
		GameRegistry.addRecipe(new ItemStack(KoentusToolsItems.white_crystal_hoe), new Object[] { "XX", "Y ", "Y ", 'X', new ItemStack(KoentusItems.koentus_item, 1, 5), 'Y', new ItemStack(DionaItems.diona_item, 1, 10) });
		GameRegistry.addRecipe(new ItemStack(KoentusToolsItems.white_crystal_axe), new Object[] { "XX", "XY", " Y", 'X', new ItemStack(KoentusItems.koentus_item, 1, 5), 'Y', new ItemStack(DionaItems.diona_item, 1, 10) });
		GameRegistry.addRecipe(new ItemStack(KoentusToolsItems.white_crystal_axe), new Object[] { "XX", "YX", "Y ", 'X', new ItemStack(KoentusItems.koentus_item, 1, 5), 'Y', new ItemStack(DionaItems.diona_item, 1, 10) });
		GameRegistry.addRecipe(new ItemStack(KoentusToolsItems.white_crystal_pickaxe), new Object[] { "XXX", " Y ", " Y ", 'X', new ItemStack(KoentusItems.koentus_item, 1, 5), 'Y', new ItemStack(DionaItems.diona_item, 1, 10) });
		GameRegistry.addRecipe(new ItemStack(KoentusToolsItems.white_crystal_sword), new Object[] { "X", "X", "Y", 'X', new ItemStack(KoentusItems.koentus_item, 1, 5), 'Y', new ItemStack(DionaItems.diona_item, 1, 10) });
		GameRegistry.addRecipe(new ItemStack(KoentusToolsItems.white_crystal_shovel), new Object[] { "X", "Y", "Y", 'X', new ItemStack(KoentusItems.koentus_item, 1, 5), 'Y', new ItemStack(DionaItems.diona_item, 1, 10) });*/
    }

    private static void addBlockSmelting()
    {
        GameRegistry.addSmelting(new ItemStack(MercuryBlocks.mercury_block, 1, 3), new ItemStack(MercuryBlocks.mercury_block, 1, 2), 0.4F);
    }

    private static void addItemSmelting()
    {
        GameRegistry.addSmelting(new ItemStack(MercuryBlocks.mercury_block, 1, 4), new ItemStack(GCItems.basicItem, 1, 4), 0.75F);
        GameRegistry.addSmelting(new ItemStack(MercuryBlocks.mercury_block, 1, 5), new ItemStack(GCItems.basicItem, 1, 3), 0.75F);
        GameRegistry.addSmelting(new ItemStack(MercuryBlocks.mercury_block, 1, 6), new ItemStack(GCItems.basicItem, 1, 5), 0.75F);
        GameRegistry.addSmelting(new ItemStack(MercuryBlocks.mercury_block, 1, 7), new ItemStack(Items.iron_ingot), 0.75F);
        GameRegistry.addSmelting(new ItemStack(MercuryBlocks.mercury_block, 1, 8), new ItemStack(MercuryItems.mercury_item, 1, 3), 0.75F);
        GameRegistry.addSmelting(new ItemStack(MercuryBlocks.metallic_rock), new ItemStack(MercuryItems.mercury_item, 1, 2), 0.75F);
        GameRegistry.addSmelting(new ItemStack(MercuryItems.mercury_item, 1, 0), new ItemStack(MercuryItems.mercury_item, 1, 2), 0.75F);
        GameRegistry.addSmelting(new ItemStack(MercuryItems.mercury_item, 1, 1), new ItemStack(MercuryItems.mercury_item, 1, 3), 0.75F);
    }
}