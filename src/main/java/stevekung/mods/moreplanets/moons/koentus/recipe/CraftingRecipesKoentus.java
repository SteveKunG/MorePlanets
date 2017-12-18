/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 *
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.moons.koentus.recipe;

import cpw.mods.fml.common.registry.GameRegistry;
import micdoodle8.mods.galacticraft.core.items.GCItems;
import net.minecraft.block.BlockColored;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;
import stevekung.mods.moreplanets.core.init.MPBlocks;
import stevekung.mods.moreplanets.moons.koentus.blocks.KoentusBlocks;
import stevekung.mods.moreplanets.moons.koentus.items.KoentusItems;
import stevekung.mods.moreplanets.moons.koentus.items.armor.KoentusArmorItems;
import stevekung.mods.moreplanets.moons.koentus.items.tools.KoentusToolsItems;
import stevekung.mods.moreplanets.planets.diona.items.DionaItems;

public class CraftingRecipesKoentus
{
    public static void loadRecipes()
    {
        CraftingRecipesKoentus.addBlockRecipes();
        CraftingRecipesKoentus.addItemRecipes();
        CraftingRecipesKoentus.addBlockSmelting();
        CraftingRecipesKoentus.addItemSmelting();
        CraftingRecipesKoentus.addOreDictionary();
    }

    private static void addBlockRecipes()
    {
        // Blocks
        GameRegistry.addRecipe(new ItemStack(KoentusBlocks.koentus_block, 1, 9), new Object[] { "CCC", "CCC", "CCC", 'C', new ItemStack(KoentusItems.koentus_item, 1, 0) });
        GameRegistry.addRecipe(new ItemStack(KoentusBlocks.koentus_block, 1, 10), new Object[] { "MMM", "MMM", "MMM", 'M', new ItemStack(KoentusItems.koentus_item, 1, 1) });
        GameRegistry.addRecipe(new ItemStack(KoentusBlocks.koentus_block, 1, 13), new Object[] { "SS", "SS", 'S', new ItemStack(KoentusBlocks.koentus_block, 1, 12) });
        GameRegistry.addRecipe(new ItemStack(KoentusBlocks.white_crystal_torch, 4), new Object[] { "W", "S", 'W', new ItemStack(KoentusItems.koentus_item, 1, 0), 'S', new ItemStack(Items.stick) });
        GameRegistry.addRecipe(new ItemStack(KoentusBlocks.crystal_fence, 3, 0), new Object[] { "CSC", "CSC", 'S', new ItemStack(Items.stick), 'C', new ItemStack(KoentusBlocks.crystal_wooden_planks, 1, 0) });
        GameRegistry.addRecipe(new ItemStack(KoentusBlocks.crystal_fence_gate), new Object[] { "SAS", "SAS", 'A', new ItemStack(KoentusBlocks.crystal_wooden_planks, 1, 0), 'S', new ItemStack(Items.stick) });
        GameRegistry.addShapelessRecipe(new ItemStack(KoentusBlocks.crystal_wooden_planks, 4, 0), new ItemStack(KoentusBlocks.crystal_log, 1, 0) );
        GameRegistry.addRecipe(new ItemStack(KoentusBlocks.koentus_block, 1, 15), new Object[] { "III", "III", "III" ,'I', new ItemStack(KoentusItems.koentus_item, 1, 4) });
        GameRegistry.addRecipe(new ItemStack(KoentusBlocks.crystal_dirt, 4, 1), new Object[] { "DG", "GD", 'D', new ItemStack(KoentusBlocks.crystal_dirt, 1, 0), 'G', new ItemStack(Blocks.gravel) });

        // Koentus Cobblestone Stairs
        GameRegistry.addRecipe(new ItemStack(KoentusBlocks.koentus_cobblestone_stairs, 4), new Object[] { "  X", " XX", "XXX", 'X', new ItemStack(KoentusBlocks.koentus_block, 1, 3) });
        GameRegistry.addRecipe(new ItemStack(KoentusBlocks.koentus_cobblestone_stairs, 4), new Object[] { "X  ", "XX ", "XXX", 'X', new ItemStack(KoentusBlocks.koentus_block, 1, 3) });

        // Koentus Dungeon Brick Stairs
        GameRegistry.addRecipe(new ItemStack(KoentusBlocks.koentus_dungeon_brick_stairs, 4), new Object[] { "  X", " XX", "XXX", 'X', new ItemStack(KoentusBlocks.koentus_block, 1, 11) });
        GameRegistry.addRecipe(new ItemStack(KoentusBlocks.koentus_dungeon_brick_stairs, 4), new Object[] { "X  ", "XX ", "XXX", 'X', new ItemStack(KoentusBlocks.koentus_block, 1, 11) });

        // Koentus Ancient Stone Stairs
        GameRegistry.addRecipe(new ItemStack(KoentusBlocks.koentus_ancient_stone_stairs, 4), new Object[] { "  X", " XX", "XXX", 'X', new ItemStack(KoentusBlocks.koentus_block, 1, 12) });
        GameRegistry.addRecipe(new ItemStack(KoentusBlocks.koentus_ancient_stone_stairs, 4), new Object[] { "X  ", "XX ", "XXX", 'X', new ItemStack(KoentusBlocks.koentus_block, 1, 12) });

        // Koentus Ancient Brick Stairs
        GameRegistry.addRecipe(new ItemStack(KoentusBlocks.koentus_ancient_brick_stairs, 4), new Object[] { "  X", " XX", "XXX", 'X', new ItemStack(KoentusBlocks.koentus_block, 1, 13) });
        GameRegistry.addRecipe(new ItemStack(KoentusBlocks.koentus_ancient_brick_stairs, 4), new Object[] { "X  ", "XX ", "XXX", 'X', new ItemStack(KoentusBlocks.koentus_block, 1, 13) });

        // Crystal Wood Stairs
        GameRegistry.addRecipe(new ItemStack(KoentusBlocks.crystal_wood_stairs, 4), new Object[] { "  X", " XX", "XXX", 'X', new ItemStack(KoentusBlocks.crystal_wooden_planks, 1, 0) });
        GameRegistry.addRecipe(new ItemStack(KoentusBlocks.crystal_wood_stairs, 4), new Object[] { "X  ", "XX ", "XXX", 'X', new ItemStack(KoentusBlocks.crystal_wooden_planks, 1, 0) });

        // Slabs
        GameRegistry.addRecipe(new ItemStack(MPBlocks.stone_slab_half, 6, 5), new Object[] { "XXX", 'X', new ItemStack(KoentusBlocks.koentus_block, 1, 3) });
        GameRegistry.addRecipe(new ItemStack(MPBlocks.stone_slab_half, 6, 6), new Object[] { "XXX", 'X', new ItemStack(KoentusBlocks.koentus_block, 1, 12) });
        GameRegistry.addRecipe(new ItemStack(MPBlocks.stone_slab_half, 6, 7), new Object[] { "XXX", 'X', new ItemStack(KoentusBlocks.koentus_block, 1, 13) });
        GameRegistry.addRecipe(new ItemStack(MPBlocks.wooden_slab_half, 6, 4), new Object[] { "CCC", 'C', new ItemStack(KoentusBlocks.crystal_wooden_planks, 1, 0) });
        GameRegistry.addRecipe(new ItemStack(MPBlocks.dungeon_brick_slab_half, 6, 3), new Object[] { "XXX", 'X', new ItemStack(KoentusBlocks.koentus_block, 1, 11) });

        // Walls
        GameRegistry.addRecipe(new ItemStack(MPBlocks.stone_wall, 6, 5), new Object[] { "XXX", "XXX", 'X', new ItemStack(KoentusBlocks.koentus_block, 1, 3) });
        GameRegistry.addRecipe(new ItemStack(MPBlocks.stone_wall, 6, 6), new Object[] { "XXX", "XXX", 'X', new ItemStack(KoentusBlocks.koentus_block, 1, 12) });
        GameRegistry.addRecipe(new ItemStack(MPBlocks.stone_wall, 6, 7), new Object[] { "XXX", "XXX", 'X', new ItemStack(KoentusBlocks.koentus_block, 1, 13) });
        GameRegistry.addRecipe(new ItemStack(MPBlocks.dungeon_brick_wall, 6, 3), new Object[] { "XXX", "XXX", 'X', new ItemStack(KoentusBlocks.koentus_block, 1, 11) });

        for (int i = 0; i < 16; ++i)
        {
            GameRegistry.addRecipe(new ItemStack(KoentusBlocks.glowing_ice_stone, 8, BlockColored.func_150032_b(i)), new Object[] {"III", "IDI", "III", 'I', new ItemStack(KoentusBlocks.koentus_ice, 1, 1), 'D', new ItemStack(Items.dye, 1, i)});
        }
    }

    private static void addItemRecipes()
    {
        // Items
        GameRegistry.addShapelessRecipe(new ItemStack(KoentusItems.koentus_item, 9, 0), new ItemStack(KoentusBlocks.koentus_block, 1, 9) );
        GameRegistry.addShapelessRecipe(new ItemStack(KoentusItems.koentus_item, 9, 1), new ItemStack(KoentusBlocks.koentus_block, 1, 10) );
        GameRegistry.addShapelessRecipe(new ItemStack(KoentusItems.koentus_item, 9, 4), new ItemStack(KoentusBlocks.koentus_block, 1, 15) );
        GameRegistry.addShapelessRecipe(new ItemStack(KoentusItems.koentus_meteor_chunk, 3), new ItemStack(KoentusItems.koentus_item, 1, 3) );
        GameRegistry.addRecipe(new ItemStack(KoentusItems.crystal_door, 3), new Object[] { "CC ", "CC ", "CC ", 'C', new ItemStack(KoentusBlocks.crystal_wooden_planks) });

        // Armor
        GameRegistry.addRecipe(new ItemStack(KoentusArmorItems.koentus_meteoric_iron_helmet), new Object[] { "QQQ", "Q Q", 'Q', new ItemStack(KoentusItems.koentus_item, 1, 6) });
        GameRegistry.addRecipe(new ItemStack(KoentusArmorItems.koentus_meteoric_iron_chestplate), new Object[] { "Q Q", "QQQ", "QQQ", 'Q', new ItemStack(KoentusItems.koentus_item, 1, 6) });
        GameRegistry.addRecipe(new ItemStack(KoentusArmorItems.koentus_meteoric_iron_leggings), new Object[] { "QQQ", "Q Q", "Q Q", 'Q', new ItemStack(KoentusItems.koentus_item, 1, 6) });
        GameRegistry.addRecipe(new ItemStack(KoentusArmorItems.koentus_meteoric_iron_boots), new Object[] { "Q Q", "Q Q", 'Q', new ItemStack(KoentusItems.koentus_item, 1, 6) });
        GameRegistry.addRecipe(new ItemStack(KoentusArmorItems.white_crystal_helmet), new Object[] { "QQQ", "Q Q", 'Q', new ItemStack(KoentusItems.koentus_item, 1, 5) });
        GameRegistry.addRecipe(new ItemStack(KoentusArmorItems.white_crystal_chestplate), new Object[] { "Q Q", "QQQ", "QQQ", 'Q', new ItemStack(KoentusItems.koentus_item, 1, 5) });
        GameRegistry.addRecipe(new ItemStack(KoentusArmorItems.white_crystal_leggings), new Object[] { "QQQ", "Q Q", "Q Q", 'Q', new ItemStack(KoentusItems.koentus_item, 1, 5) });
        GameRegistry.addRecipe(new ItemStack(KoentusArmorItems.white_crystal_boots), new Object[] { "Q Q", "Q Q", 'Q', new ItemStack(KoentusItems.koentus_item, 1, 5) });
        GameRegistry.addRecipe(new ItemStack(KoentusArmorItems.breathableKoentusMeteorHelmet), new Object[] { "QQQ", "QOQ", 'Q', new ItemStack(KoentusItems.koentus_item, 1, 6), 'O', new ItemStack(GCItems.oxMask) });
        GameRegistry.addRecipe(new ItemStack(KoentusArmorItems.breathableWhiteCrystalHelmet), new Object[] { "QQQ", "QOQ", 'Q', new ItemStack(KoentusItems.koentus_item, 1, 5), 'O', new ItemStack(GCItems.oxMask) });

        // Tools
        GameRegistry.addRecipe(new ItemStack(KoentusToolsItems.koentus_meteoric_iron_hoe), new Object[] { "XX", " Y", " Y", 'X', new ItemStack(KoentusItems.koentus_item, 1, 6), 'Y', new ItemStack(DionaItems.diona_item, 1, 10) });
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
        GameRegistry.addRecipe(new ItemStack(KoentusToolsItems.white_crystal_shovel), new Object[] { "X", "Y", "Y", 'X', new ItemStack(KoentusItems.koentus_item, 1, 5), 'Y', new ItemStack(DionaItems.diona_item, 1, 10) });
    }

    private static void addOreDictionary()
    {
        OreDictionary.registerOre("plankWood", new ItemStack(KoentusBlocks.crystal_wooden_planks, 1, OreDictionary.WILDCARD_VALUE));
        OreDictionary.registerOre("stairWood", new ItemStack(KoentusBlocks.crystal_wood_stairs));
        OreDictionary.registerOre("treeSapling", new ItemStack(KoentusBlocks.crystal_sapling, 1, OreDictionary.WILDCARD_VALUE));
        OreDictionary.registerOre("treeLeaves", new ItemStack(KoentusBlocks.crystal_leaves, 1, OreDictionary.WILDCARD_VALUE));
        OreDictionary.registerOre("logWood", new ItemStack(KoentusBlocks.crystal_log, 1, OreDictionary.WILDCARD_VALUE));
    }

    private static void addBlockSmelting()
    {
        GameRegistry.addSmelting(new ItemStack(KoentusBlocks.koentus_block, 1, 3), new ItemStack(KoentusBlocks.koentus_block, 1, 2), 0.4F);
    }

    private static void addItemSmelting()
    {
        GameRegistry.addSmelting(new ItemStack(KoentusBlocks.koentus_block, 1, 4), new ItemStack(GCItems.basicItem, 1, 4), 0.8F);
        GameRegistry.addSmelting(new ItemStack(KoentusBlocks.koentus_block, 1, 5), new ItemStack(GCItems.basicItem, 1, 3), 0.8F);
        GameRegistry.addSmelting(new ItemStack(KoentusBlocks.koentus_block, 1, 6), new ItemStack(KoentusItems.koentus_item, 1, 0), 0.8F);
        GameRegistry.addSmelting(new ItemStack(KoentusBlocks.koentus_block, 1, 7), new ItemStack(KoentusItems.koentus_item, 1, 1), 0.8F);
        GameRegistry.addSmelting(new ItemStack(KoentusBlocks.koentus_block, 1, 8), new ItemStack(KoentusItems.koentus_item, 1, 2), 0.8F);
        GameRegistry.addSmelting(new ItemStack(KoentusBlocks.fallen_koentus_meteor), new ItemStack(KoentusItems.koentus_item, 1, 4), 0.8F);
        GameRegistry.addSmelting(new ItemStack(KoentusItems.koentus_item, 1, 3), new ItemStack(KoentusItems.koentus_item, 1, 4), 0.8F);
    }
}