/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 *
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.planets.nibiru.recipe;

import java.util.HashMap;

import cpw.mods.fml.common.registry.GameRegistry;
import micdoodle8.mods.galacticraft.core.blocks.GCBlocks;
import micdoodle8.mods.galacticraft.core.items.GCItems;
import micdoodle8.mods.galacticraft.planets.asteroids.items.AsteroidsItems;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;
import stevekung.mods.moreplanets.core.config.ConfigManagerMP;
import stevekung.mods.moreplanets.core.init.MPBlocks;
import stevekung.mods.moreplanets.planets.diona.items.DionaItems;
import stevekung.mods.moreplanets.planets.nibiru.blocks.NibiruBlocks;
import stevekung.mods.moreplanets.planets.nibiru.items.NibiruItems;
import stevekung.mods.moreplanets.planets.nibiru.items.armor.NibiruArmorItems;
import stevekung.mods.moreplanets.planets.nibiru.items.tools.NibiruToolsItems;
import stevekung.mods.moreplanets.planets.polongnius.items.PolongniusItems;

public class CraftingRecipesNibiru
{
    public static void loadRecipes()
    {
        CraftingRecipesNibiru.addBlockRecipes();
        CraftingRecipesNibiru.addItemRecipes();
        CraftingRecipesNibiru.addBlockSmelting();
        CraftingRecipesNibiru.addItemSmelting();
        CraftingRecipesNibiru.addOreDictRecipe();

        if (ConfigManagerMP.enableTier6RocketRecipe)
        {
            CraftingRecipesNibiru.addTier6RocketRecipes();
            CraftingRecipesNibiru.addTier6RocketNoFlagRecipes();
        }
    }

    private static void addBlockRecipes()
    {
        // Blocks
        GameRegistry.addShapelessRecipe(new ItemStack(NibiruBlocks.nibiru_wooden_planks, 4, 0), new ItemStack(NibiruBlocks.nibiru_log, 1, 0) );
        GameRegistry.addShapelessRecipe(new ItemStack(NibiruBlocks.nibiru_wooden_planks, 4, 1), new ItemStack(NibiruBlocks.nibiru_log, 1, 1) );
        GameRegistry.addRecipe(new ItemStack(NibiruBlocks.power_crystal_generator), new Object[] { "AAA", "TFT", "TWT", 'A', new ItemStack(AsteroidsItems.basicItem, 1, 6), 'F', new ItemStack(Blocks.furnace), 'W', new ItemStack(GCBlocks.aluminumWire, 1, 1), 'T', new ItemStack(AsteroidsItems.basicItem, 1, 0) });
        GameRegistry.addRecipe(new ItemStack(NibiruBlocks.ichorius_torch, 4), new Object[] { "I", "S", 'I', new ItemStack(NibiruItems.power_crystal), 'S', new ItemStack(Items.stick) });
        GameRegistry.addRecipe(new ItemStack(NibiruBlocks.nibiru_block, 1, 9), new Object[] { "III", "III", "III", 'I', new ItemStack(NibiruItems.power_crystal) });
        GameRegistry.addRecipe(new ItemStack(NibiruBlocks.nibiru_block, 1, 10), new Object[] { "NNN", "NNN", "NNN", 'N', new ItemStack(NibiruItems.nibiru_item, 1, 1) });
        GameRegistry.addRecipe(new ItemStack(NibiruBlocks.nibiru_block, 1, 11), new Object[] { "GGG", "GGG", "GGG", 'G', new ItemStack(NibiruItems.nibiru_item, 1, 0) });
        GameRegistry.addRecipe(new ItemStack(NibiruBlocks.nibiru_fence, 3, 0), new Object[] { "ASA", "ASA", 'A', new ItemStack(NibiruBlocks.nibiru_wooden_planks, 1, 0), 'S', new ItemStack(Items.stick) });
        GameRegistry.addRecipe(new ItemStack(NibiruBlocks.nibiru_fence, 3, 1), new Object[] { "OSO", "OSO", 'O', new ItemStack(NibiruBlocks.nibiru_wooden_planks, 1, 1), 'S', new ItemStack(Items.stick) });
        GameRegistry.addRecipe(new ItemStack(NibiruBlocks.ancient_dark_fence_gate), new Object[] { "SAS", "SAS", 'A', new ItemStack(NibiruBlocks.nibiru_wooden_planks, 1, 0), 'S', new ItemStack(Items.stick) });
        GameRegistry.addRecipe(new ItemStack(NibiruBlocks.orange_fence_gate), new Object[] { "SOS", "SOS", 'O', new ItemStack(NibiruBlocks.nibiru_wooden_planks, 1, 1), 'S', new ItemStack(Items.stick) });
        GameRegistry.addRecipe(new ItemStack(NibiruBlocks.infected_dirt, 4, 1), new Object[] { "DG", "GD", 'D', new ItemStack(NibiruBlocks.infected_dirt, 1, 0), 'G', new ItemStack(Blocks.gravel) });

        // Nibiru Cobblestone Stairs
        GameRegistry.addRecipe(new ItemStack(NibiruBlocks.nibiru_cobblestone_stairs, 4), new Object[] { "  X", " XX", "XXX", 'X', new ItemStack(NibiruBlocks.nibiru_block, 1, 3) });
        GameRegistry.addRecipe(new ItemStack(NibiruBlocks.nibiru_cobblestone_stairs, 4), new Object[] { "X  ", "XX ", "XXX", 'X', new ItemStack(NibiruBlocks.nibiru_block, 1, 3) });

        // Nibiru Dungeon Brick Stairs
        GameRegistry.addRecipe(new ItemStack(NibiruBlocks.nibiru_dungeon_brick_stairs, 4), new Object[] { "  X", " XX", "XXX", 'X', new ItemStack(NibiruBlocks.nibiru_block, 1, 12) });
        GameRegistry.addRecipe(new ItemStack(NibiruBlocks.nibiru_dungeon_brick_stairs, 4), new Object[] { "X  ", "XX ", "XXX", 'X', new ItemStack(NibiruBlocks.nibiru_block, 1, 12) });

        // Ancient Dark Wood Stairs
        GameRegistry.addRecipe(new ItemStack(NibiruBlocks.ancient_dark_wood_stairs, 4), new Object[] { "  X", " XX", "XXX", 'X', new ItemStack(NibiruBlocks.nibiru_wooden_planks, 1, 0) });
        GameRegistry.addRecipe(new ItemStack(NibiruBlocks.ancient_dark_wood_stairs, 4), new Object[] { "X  ", "XX ", "XXX", 'X', new ItemStack(NibiruBlocks.nibiru_wooden_planks, 1, 0) });

        // Orange Wood Stairs
        GameRegistry.addRecipe(new ItemStack(NibiruBlocks.orange_wood_stairs, 4), new Object[] { "  X", " XX", "XXX", 'X', new ItemStack(NibiruBlocks.nibiru_wooden_planks, 1, 1) });
        GameRegistry.addRecipe(new ItemStack(NibiruBlocks.orange_wood_stairs, 4), new Object[] { "X  ", "XX ", "XXX", 'X', new ItemStack(NibiruBlocks.nibiru_wooden_planks, 1, 1) });

        // Slabs
        GameRegistry.addRecipe(new ItemStack(MPBlocks.stone_slab_half, 6, 4), new Object[] { "XXX", 'X', new ItemStack(NibiruBlocks.nibiru_block, 1, 3) });
        GameRegistry.addRecipe(new ItemStack(MPBlocks.dungeon_brick_slab_half, 6, 2), new Object[] { "XXX", 'X', new ItemStack(NibiruBlocks.nibiru_block, 1, 12) });
        GameRegistry.addRecipe(new ItemStack(MPBlocks.wooden_slab_half, 6, 0), new Object[] { "XXX", 'X', new ItemStack(NibiruBlocks.nibiru_wooden_planks, 1, 0) });
        GameRegistry.addRecipe(new ItemStack(MPBlocks.wooden_slab_half, 6, 1), new Object[] { "XXX", 'X', new ItemStack(NibiruBlocks.nibiru_wooden_planks, 1, 1) });

        // Walls
        GameRegistry.addRecipe(new ItemStack(MPBlocks.stone_wall, 6, 4), new Object[] { "XXX", "XXX", 'X', new ItemStack(NibiruBlocks.nibiru_block, 1, 3) });
        GameRegistry.addRecipe(new ItemStack(MPBlocks.dungeon_brick_wall, 6, 2), new Object[] { "XXX", "XXX", 'X', new ItemStack(NibiruBlocks.nibiru_block, 1, 12) });
    }

    private static void addItemRecipes()
    {
        // Items
        GameRegistry.addRecipe(new ItemStack(NibiruItems.nibiru_item, 2, 4), new Object[] { "M", "M", 'M', new ItemStack(NibiruItems.nibiru_item, 1, 0) });
        GameRegistry.addRecipe(new ItemStack(NibiruItems.nibiru_item, 2, 5), new Object[] { "P", "P", 'P', new ItemStack(NibiruItems.nibiru_item, 1, 1) });
        GameRegistry.addRecipe(new ItemStack(NibiruItems.space_fruits, 1, 2), new Object[] { "O", "B", 'O', new ItemStack(NibiruItems.space_fruits, 1, 1), 'B', new ItemStack(Items.potionitem, 1, 0) });
        GameRegistry.addRecipe(new ItemStack(NibiruItems.tier7_rocket_module, 1, 0), new Object[] { " SB", "TCT", "TAT", 'B', new ItemStack(Blocks.stone_button), 'S', new ItemStack(Items.flint_and_steel), 'T', new ItemStack(NibiruItems.tier7_rocket_module, 1, 2), 'A', new ItemStack(GCItems.oxygenVent), 'C', new ItemStack(GCItems.canister, 1, 0) });
        GameRegistry.addRecipe(new ItemStack(NibiruItems.tier7_rocket_module, 1, 0), new Object[] { "BS ", "TCT", "TAT", 'B', new ItemStack(Blocks.stone_button), 'S', new ItemStack(Items.flint_and_steel), 'T', new ItemStack(NibiruItems.tier7_rocket_module, 1, 2), 'A', new ItemStack(GCItems.oxygenVent), 'C', new ItemStack(GCItems.canister, 1, 0) });
        GameRegistry.addRecipe(new ItemStack(NibiruItems.tier7_rocket_module, 1, 1), new Object[] { "PDP", "PCP", "NON", 'D', new ItemStack(NibiruBlocks.nibiru_block, 1, 10), 'P', new ItemStack(NibiruItems.nibiru_item, 1, 2), 'C', new ItemStack(GCItems.fuelCanister, 1, 1), 'N', new ItemStack(NibiruItems.tier7_rocket_module, 1, 2), 'O', new ItemStack(GCItems.oxygenVent) });
        GameRegistry.addShapelessRecipe(new ItemStack(NibiruItems.power_crystal, 9), new ItemStack(NibiruBlocks.nibiru_block, 1, 9) );
        GameRegistry.addShapelessRecipe(new ItemStack(NibiruItems.nibiru_item, 9, 1), new ItemStack(NibiruBlocks.nibiru_block, 1, 10) );
        GameRegistry.addShapelessRecipe(new ItemStack(NibiruItems.nibiru_item, 9, 0), new ItemStack(NibiruBlocks.nibiru_block, 1, 11) );
        GameRegistry.addRecipe(new ItemStack(NibiruItems.ancient_dark_door, 3), new Object[] { "AA", "AA", "AA", 'A', new ItemStack(NibiruBlocks.nibiru_wooden_planks, 1, 0) });
        GameRegistry.addRecipe(new ItemStack(NibiruItems.orange_door, 3), new Object[] { "OO", "OO", "OO", 'O', new ItemStack(NibiruBlocks.nibiru_wooden_planks, 1, 1) });
        GameRegistry.addRecipe(new ItemStack(NibiruItems.tier7_rocket_module, 1, 4), new Object[] { " Y ", " X ", "X X", 'X', new ItemStack(NibiruItems.tier7_rocket_module, 1, 2), 'Y', new ItemStack(DionaItems.diona_item, 1, 12) });
        GameRegistry.addRecipe(new ItemStack(NibiruItems.tier7_rocket_module, 1, 3), new Object[] { " Y ", "XYX", "X X", 'X', new ItemStack(NibiruItems.tier7_rocket_module, 1, 2), 'Y', new ItemStack(PolongniusItems.tier6_rocket_module, 1, 2) });

        // Armor
        GameRegistry.addRecipe(new ItemStack(NibiruArmorItems.red_gem_helmet), new Object[] { "QQQ", "Q Q", 'Q', new ItemStack(NibiruItems.nibiru_item, 1, 2) });
        GameRegistry.addRecipe(new ItemStack(NibiruArmorItems.red_gem_chestplate), new Object[] { "Q Q", "QQQ", "QQQ", 'Q', new ItemStack(NibiruItems.nibiru_item, 1, 2) });
        GameRegistry.addRecipe(new ItemStack(NibiruArmorItems.red_gem_leggings), new Object[] { "QQQ", "Q Q", "Q Q", 'Q', new ItemStack(NibiruItems.nibiru_item, 1, 2) });
        GameRegistry.addRecipe(new ItemStack(NibiruArmorItems.red_gem_boots), new Object[] { "Q Q", "Q Q", 'Q', new ItemStack(NibiruItems.nibiru_item, 1, 2) });
        GameRegistry.addRecipe(new ItemStack(NibiruArmorItems.norium_helmet), new Object[] { "QQQ", "Q Q", 'Q', new ItemStack(NibiruItems.nibiru_item, 1, 3) });
        GameRegistry.addRecipe(new ItemStack(NibiruArmorItems.norium_chestplate), new Object[] { "Q Q", "QQQ", "QQQ", 'Q', new ItemStack(NibiruItems.nibiru_item, 1, 3) });
        GameRegistry.addRecipe(new ItemStack(NibiruArmorItems.norium_leggings), new Object[] { "QQQ", "Q Q", "Q Q", 'Q', new ItemStack(NibiruItems.nibiru_item, 1, 3) });
        GameRegistry.addRecipe(new ItemStack(NibiruArmorItems.norium_boots), new Object[] { "Q Q", "Q Q", 'Q', new ItemStack(NibiruItems.nibiru_item, 1, 3) });
        GameRegistry.addRecipe(new ItemStack(NibiruArmorItems.breathable_red_gem_helmet), new Object[] { "RRR", "ROR", 'R', new ItemStack(NibiruItems.nibiru_item, 1, 2), 'O', new ItemStack(GCItems.oxMask) });
        GameRegistry.addRecipe(new ItemStack(NibiruArmorItems.breathable_norium_helmet), new Object[] { "NNN", "NON", 'N', new ItemStack(NibiruItems.nibiru_item, 1, 3), 'O', new ItemStack(GCItems.oxMask) });

        // Tools
        GameRegistry.addRecipe(new ItemStack(NibiruToolsItems.red_gem_hoe), new Object[] { "XX", " Y", " Y", 'X', new ItemStack(NibiruItems.nibiru_item, 1, 2), 'Y', new ItemStack(NibiruItems.nibiru_item, 1, 4) });
        GameRegistry.addRecipe(new ItemStack(NibiruToolsItems.red_gem_hoe), new Object[] { "XX", "Y ", "Y ", 'X', new ItemStack(NibiruItems.nibiru_item, 1, 2), 'Y', new ItemStack(NibiruItems.nibiru_item, 1, 4) });
        GameRegistry.addRecipe(new ItemStack(NibiruToolsItems.red_gem_axe), new Object[] { "XX", "XY", " Y", 'X', new ItemStack(NibiruItems.nibiru_item, 1, 2), 'Y', new ItemStack(NibiruItems.nibiru_item, 1, 4) });
        GameRegistry.addRecipe(new ItemStack(NibiruToolsItems.red_gem_axe), new Object[] { "XX", "YX", "Y ", 'X', new ItemStack(NibiruItems.nibiru_item, 1, 2), 'Y', new ItemStack(NibiruItems.nibiru_item, 1, 4) });
        GameRegistry.addRecipe(new ItemStack(NibiruToolsItems.red_gem_pickaxe), new Object[] { "XXX", " Y ", " Y ", 'X', new ItemStack(NibiruItems.nibiru_item, 1, 2), 'Y', new ItemStack(NibiruItems.nibiru_item, 1, 4) });
        GameRegistry.addRecipe(new ItemStack(NibiruToolsItems.red_gem_sword), new Object[] { "X", "X", "Y", 'X', new ItemStack(NibiruItems.nibiru_item, 1, 2), 'Y', new ItemStack(NibiruItems.nibiru_item, 1, 4) });
        GameRegistry.addRecipe(new ItemStack(NibiruToolsItems.red_gem_shovel), new Object[] { "X", "Y", "Y", 'X', new ItemStack(NibiruItems.nibiru_item, 1, 2), 'Y', new ItemStack(NibiruItems.nibiru_item, 1, 4) });
        GameRegistry.addRecipe(new ItemStack(NibiruToolsItems.norium_hoe), new Object[] { "XX", " Y", " Y", 'X', new ItemStack(NibiruItems.nibiru_item, 1, 3), 'Y', new ItemStack(NibiruItems.nibiru_item, 1, 5) });
        GameRegistry.addRecipe(new ItemStack(NibiruToolsItems.norium_hoe), new Object[] { "XX", "Y ", "Y ", 'X', new ItemStack(NibiruItems.nibiru_item, 1, 3), 'Y', new ItemStack(NibiruItems.nibiru_item, 1, 5) });
        GameRegistry.addRecipe(new ItemStack(NibiruToolsItems.norium_axe), new Object[] { "XX", "XY", " Y", 'X', new ItemStack(NibiruItems.nibiru_item, 1, 3), 'Y', new ItemStack(NibiruItems.nibiru_item, 1, 5) });
        GameRegistry.addRecipe(new ItemStack(NibiruToolsItems.norium_axe), new Object[] { "XX", "YX", "Y ", 'X', new ItemStack(NibiruItems.nibiru_item, 1, 3), 'Y', new ItemStack(NibiruItems.nibiru_item, 1, 5) });
        GameRegistry.addRecipe(new ItemStack(NibiruToolsItems.norium_pickaxe), new Object[] { "XXX", " Y ", " Y ", 'X', new ItemStack(NibiruItems.nibiru_item, 1, 3), 'Y', new ItemStack(NibiruItems.nibiru_item, 1, 5) });
        GameRegistry.addRecipe(new ItemStack(NibiruToolsItems.norium_sword), new Object[] { "X", "X", "Y", 'X', new ItemStack(NibiruItems.nibiru_item, 1, 3), 'Y', new ItemStack(NibiruItems.nibiru_item, 1, 5) });
        GameRegistry.addRecipe(new ItemStack(NibiruToolsItems.norium_shovel), new Object[] { "X", "Y", "Y", 'X', new ItemStack(NibiruItems.nibiru_item, 1, 3), 'Y', new ItemStack(NibiruItems.nibiru_item, 1, 5) });
    }

    private static void addBlockSmelting()
    {
        GameRegistry.addSmelting(new ItemStack(NibiruBlocks.nibiru_block, 1, 3), new ItemStack(NibiruBlocks.nibiru_block, 1, 2), 0.4F);
    }

    private static void addItemSmelting()
    {
        GameRegistry.addSmelting(new ItemStack(NibiruBlocks.nibiru_block, 1, 4), new ItemStack(NibiruItems.power_crystal), 0.8F);
        GameRegistry.addSmelting(new ItemStack(NibiruBlocks.nibiru_block, 1, 5), new ItemStack(NibiruItems.nibiru_item, 1, 1), 0.8F);
        GameRegistry.addSmelting(new ItemStack(NibiruBlocks.nibiru_block, 1, 6), new ItemStack(Items.diamond), 0.8F);
        GameRegistry.addSmelting(new ItemStack(NibiruBlocks.nibiru_block, 1, 7), new ItemStack(Items.coal), 0.65F);
        GameRegistry.addSmelting(new ItemStack(NibiruBlocks.nibiru_block, 1, 8), new ItemStack(NibiruItems.nibiru_item, 1, 0), 0.8F);
    }

    private static void addOreDictRecipe()
    {
        OreDictionary.registerOre("plankWood", new ItemStack(NibiruBlocks.nibiru_wooden_planks, 1, OreDictionary.WILDCARD_VALUE));
        OreDictionary.registerOre("slabWood", new ItemStack(MPBlocks.wooden_slab_half, 1, OreDictionary.WILDCARD_VALUE));
        OreDictionary.registerOre("stairWood", new ItemStack(NibiruBlocks.ancient_dark_wood_stairs));
        OreDictionary.registerOre("stairWood", new ItemStack(NibiruBlocks.orange_wood_stairs));
        OreDictionary.registerOre("treeSapling", new ItemStack(NibiruBlocks.nibiru_sapling, 1, OreDictionary.WILDCARD_VALUE));
        OreDictionary.registerOre("treeLeaves", new ItemStack(NibiruBlocks.ancient_dark_leaves, 1, OreDictionary.WILDCARD_VALUE));
        OreDictionary.registerOre("treeLeaves", new ItemStack(NibiruBlocks.orange_leaves, 1, OreDictionary.WILDCARD_VALUE));
        OreDictionary.registerOre("logWood", new ItemStack(NibiruBlocks.nibiru_log, 1, OreDictionary.WILDCARD_VALUE));
    }

    private static void addTier6RocketRecipes()
    {
        HashMap<Integer, ItemStack> input = new HashMap<Integer, ItemStack>();
        input.put(1, new ItemStack(DionaItems.tier4_rocket_module, 1, 0));
        input.put(2, new ItemStack(PolongniusItems.tier6_rocket_module, 1, 2));
        input.put(3, new ItemStack(PolongniusItems.tier6_rocket_module, 1, 2));
        input.put(4, new ItemStack(PolongniusItems.tier6_rocket_module, 1, 2));
        input.put(5, new ItemStack(PolongniusItems.tier6_rocket_module, 1, 2));
        input.put(6, new ItemStack(PolongniusItems.tier6_rocket_module, 1, 2));
        input.put(7, new ItemStack(PolongniusItems.tier6_rocket_module, 1, 2));
        input.put(8, new ItemStack(PolongniusItems.tier6_rocket_module, 1, 2));
        input.put(9, new ItemStack(PolongniusItems.tier6_rocket_module, 1, 2));
        input.put(10, new ItemStack(PolongniusItems.tier6_rocket_module, 1, 2));
        input.put(11, new ItemStack(PolongniusItems.tier6_rocket_module, 1, 2));
        input.put(12, new ItemStack(PolongniusItems.tier6_rocket_module, 1, 1));
        input.put(13, new ItemStack(AsteroidsItems.basicItem, 1, 2));
        input.put(14, new ItemStack(AsteroidsItems.basicItem, 1, 2));
        input.put(15, new ItemStack(PolongniusItems.tier6_rocket_module, 1, 0));
        input.put(16, new ItemStack(PolongniusItems.tier6_rocket_module, 1, 1));
        input.put(17, new ItemStack(AsteroidsItems.basicItem, 1, 2));
        input.put(18, new ItemStack(AsteroidsItems.basicItem, 1, 2));
        input.put(19, null);
        input.put(20, null);
        input.put(21, null);
        Tier6RocketRecipes.addTier6RocketBenchRecipe(new ItemStack(NibiruItems.tier6_rocket, 1, 0), input);

        HashMap<Integer, ItemStack> input2 = new HashMap<Integer, ItemStack>(input);
        input2.put(19, new ItemStack(Blocks.chest));
        input2.put(20, null);
        input2.put(21, null);
        Tier6RocketRecipes.addTier6RocketBenchRecipe(new ItemStack(NibiruItems.tier6_rocket, 1, 1), input2);

        input2 = new HashMap<Integer, ItemStack>(input);
        input2.put(19, null);
        input2.put(20, new ItemStack(Blocks.chest));
        input2.put(21, null);
        Tier6RocketRecipes.addTier6RocketBenchRecipe(new ItemStack(NibiruItems.tier6_rocket, 1, 1), input2);

        input2 = new HashMap<Integer, ItemStack>(input);
        input2.put(19, null);
        input2.put(20, null);
        input2.put(21, new ItemStack(Blocks.chest));
        Tier6RocketRecipes.addTier6RocketBenchRecipe(new ItemStack(NibiruItems.tier6_rocket, 1, 1), input2);

        input2 = new HashMap<Integer, ItemStack>(input);
        input2.put(19, new ItemStack(Blocks.chest));
        input2.put(20, new ItemStack(Blocks.chest));
        input2.put(21, null);
        Tier6RocketRecipes.addTier6RocketBenchRecipe(new ItemStack(NibiruItems.tier6_rocket, 1, 2), input2);

        input2 = new HashMap<Integer, ItemStack>(input);
        input2.put(19, new ItemStack(Blocks.chest));
        input2.put(20, null);
        input2.put(21, new ItemStack(Blocks.chest));
        Tier6RocketRecipes.addTier6RocketBenchRecipe(new ItemStack(NibiruItems.tier6_rocket, 1, 2), input2);

        input2 = new HashMap<Integer, ItemStack>(input);
        input2.put(19, null);
        input2.put(20, new ItemStack(Blocks.chest));
        input2.put(21, new ItemStack(Blocks.chest));
        Tier6RocketRecipes.addTier6RocketBenchRecipe(new ItemStack(NibiruItems.tier6_rocket, 1, 2), input2);

        input2 = new HashMap<Integer, ItemStack>(input);
        input2.put(19, new ItemStack(Blocks.chest));
        input2.put(20, new ItemStack(Blocks.chest));
        input2.put(21, new ItemStack(Blocks.chest));
        Tier6RocketRecipes.addTier6RocketBenchRecipe(new ItemStack(NibiruItems.tier6_rocket, 1, 3), input2);
    }

    private static void addTier6RocketNoFlagRecipes()
    {
        HashMap<Integer, ItemStack> input = new HashMap<Integer, ItemStack>();
        input.put(1, new ItemStack(DionaItems.tier4_rocket_module, 1, 4));
        input.put(2, new ItemStack(PolongniusItems.tier6_rocket_module, 1, 2));
        input.put(3, new ItemStack(PolongniusItems.tier6_rocket_module, 1, 2));
        input.put(4, new ItemStack(PolongniusItems.tier6_rocket_module, 1, 2));
        input.put(5, new ItemStack(PolongniusItems.tier6_rocket_module, 1, 2));
        input.put(6, new ItemStack(PolongniusItems.tier6_rocket_module, 1, 2));
        input.put(7, new ItemStack(PolongniusItems.tier6_rocket_module, 1, 2));
        input.put(8, new ItemStack(PolongniusItems.tier6_rocket_module, 1, 2));
        input.put(9, new ItemStack(PolongniusItems.tier6_rocket_module, 1, 2));
        input.put(10, new ItemStack(PolongniusItems.tier6_rocket_module, 1, 2));
        input.put(11, new ItemStack(PolongniusItems.tier6_rocket_module, 1, 2));
        input.put(12, new ItemStack(PolongniusItems.tier6_rocket_module, 1, 1));
        input.put(13, new ItemStack(AsteroidsItems.basicItem, 1, 2));
        input.put(14, new ItemStack(AsteroidsItems.basicItem, 1, 2));
        input.put(15, new ItemStack(PolongniusItems.tier6_rocket_module, 1, 0));
        input.put(16, new ItemStack(PolongniusItems.tier6_rocket_module, 1, 1));
        input.put(17, new ItemStack(AsteroidsItems.basicItem, 1, 2));
        input.put(18, new ItemStack(AsteroidsItems.basicItem, 1, 2));
        input.put(19, null);
        input.put(20, null);
        input.put(21, null);
        Tier6RocketRecipes.addTier6RocketBenchNoFlagRecipe(new ItemStack(NibiruItems.tier6_rocket, 1, 10), input);

        HashMap<Integer, ItemStack> input2 = new HashMap<Integer, ItemStack>(input);
        input2.put(19, new ItemStack(Blocks.chest));
        input2.put(20, null);
        input2.put(21, null);
        Tier6RocketRecipes.addTier6RocketBenchNoFlagRecipe(new ItemStack(NibiruItems.tier6_rocket, 1, 11), input2);

        input2 = new HashMap<Integer, ItemStack>(input);
        input2.put(19, null);
        input2.put(20, new ItemStack(Blocks.chest));
        input2.put(21, null);
        Tier6RocketRecipes.addTier6RocketBenchNoFlagRecipe(new ItemStack(NibiruItems.tier6_rocket, 1, 11), input2);

        input2 = new HashMap<Integer, ItemStack>(input);
        input2.put(19, null);
        input2.put(20, null);
        input2.put(21, new ItemStack(Blocks.chest));
        Tier6RocketRecipes.addTier6RocketBenchNoFlagRecipe(new ItemStack(NibiruItems.tier6_rocket, 1, 11), input2);

        input2 = new HashMap<Integer, ItemStack>(input);
        input2.put(19, new ItemStack(Blocks.chest));
        input2.put(20, new ItemStack(Blocks.chest));
        input2.put(21, null);
        Tier6RocketRecipes.addTier6RocketBenchNoFlagRecipe(new ItemStack(NibiruItems.tier6_rocket, 1, 12), input2);

        input2 = new HashMap<Integer, ItemStack>(input);
        input2.put(19, new ItemStack(Blocks.chest));
        input2.put(20, null);
        input2.put(21, new ItemStack(Blocks.chest));
        Tier6RocketRecipes.addTier6RocketBenchNoFlagRecipe(new ItemStack(NibiruItems.tier6_rocket, 1, 12), input2);

        input2 = new HashMap<Integer, ItemStack>(input);
        input2.put(19, null);
        input2.put(20, new ItemStack(Blocks.chest));
        input2.put(21, new ItemStack(Blocks.chest));
        Tier6RocketRecipes.addTier6RocketBenchNoFlagRecipe(new ItemStack(NibiruItems.tier6_rocket, 1, 12), input2);

        input2 = new HashMap<Integer, ItemStack>(input);
        input2.put(19, new ItemStack(Blocks.chest));
        input2.put(20, new ItemStack(Blocks.chest));
        input2.put(21, new ItemStack(Blocks.chest));
        Tier6RocketRecipes.addTier6RocketBenchNoFlagRecipe(new ItemStack(NibiruItems.tier6_rocket, 1, 13), input2);
    }
}