/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 *
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.planets.polongnius.recipe;

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
import stevekung.mods.moreplanets.planets.polongnius.blocks.PolongniusBlocks;
import stevekung.mods.moreplanets.planets.polongnius.items.PolongniusItems;
import stevekung.mods.moreplanets.planets.polongnius.items.armor.PolongniusArmorItems;
import stevekung.mods.moreplanets.planets.polongnius.items.tools.PolongniusToolsItems;

public class CraftingRecipesPolongnius
{
    public static void loadRecipes()
    {
        CraftingRecipesPolongnius.addBlockRecipes();
        CraftingRecipesPolongnius.addItemRecipes();
        CraftingRecipesPolongnius.addBlockSmelting();
        CraftingRecipesPolongnius.addItemSmelting();

        if (ConfigManagerMP.enableTier5RocketRecipe)
        {
            CraftingRecipesPolongnius.addTier5RocketRecipes();
            CraftingRecipesPolongnius.addTier5RocketNoFlagRecipes();
        }

        CraftingRecipesPolongnius.addOreDictionary();
    }

    private static void addBlockRecipes()
    {
        // Blocks
        GameRegistry.addRecipe(new ItemStack(PolongniusBlocks.polongnius_block, 1, 10), new Object[] { "MMM", "MMM", "MMM", 'M', new ItemStack(PolongniusItems.polongnius_item, 1, 4) });
        GameRegistry.addRecipe(new ItemStack(PolongniusBlocks.polongnius_block, 1, 11), new Object[] { "CCC", "CCC", "CCC", 'C', new ItemStack(PolongniusItems.polongnius_item, 1, 1) });
        GameRegistry.addRecipe(new ItemStack(PolongniusBlocks.polongnius_block, 1, 12), new Object[] { "PPP", "PPP", "PPP", 'P', new ItemStack(PolongniusItems.polongnius_item, 1, 5) });
        GameRegistry.addRecipe(new ItemStack(PolongniusBlocks.cheese_slime_block), new Object[] { "SSS", "SSS", "SSS", 'S', new ItemStack(PolongniusItems.cheese_slimeball) });
        GameRegistry.addRecipe(new ItemStack(PolongniusBlocks.flonium_torch, 4), new Object[] { "F", "S", 'F', new ItemStack(PolongniusItems.polongnius_item, 1, 0), 'S', new ItemStack(Items.stick) });
        GameRegistry.addRecipe(new ItemStack(PolongniusBlocks.ultra_violet_solar_panel), new Object[] { "TST", "TPT", "AWA", 'S', new ItemStack(PolongniusItems.purple_crystal_solar_module, 1, 3), 'A', new ItemStack(GCBlocks.aluminumWire, 1, 1), 'T', new ItemStack(AsteroidsItems.basicItem, 1, 0), 'P', new ItemStack(GCItems.flagPole), 'W', new ItemStack(PolongniusItems.purple_crystal_solar_module, 1, 0) });
        GameRegistry.addRecipe(new ItemStack(PolongniusBlocks.cheese_of_milk_cake), new Object[] { "CCC", "CMC", "CCC", 'C', new ItemStack(PolongniusItems.polongnius_food, 1, 0), 'M', new ItemStack(Items.milk_bucket) });

        // Polongnius Cobblestone Stairs
        GameRegistry.addRecipe(new ItemStack(PolongniusBlocks.polongnius_cobblestone_stairs, 4), new Object[] { "  X", " XX", "XXX", 'X', new ItemStack(PolongniusBlocks.polongnius_block, 1, 3) });
        GameRegistry.addRecipe(new ItemStack(PolongniusBlocks.polongnius_cobblestone_stairs, 4), new Object[] { "X  ", "XX ", "XXX", 'X', new ItemStack(PolongniusBlocks.polongnius_block, 1, 3) });

        // Polongnius Dungeon Bricks Stairs
        GameRegistry.addRecipe(new ItemStack(PolongniusBlocks.polongnius_dungeon_brick_stairs, 4), new Object[] { "  X", " XX", "XXX", 'X', new ItemStack(PolongniusBlocks.polongnius_block, 1, 14) });
        GameRegistry.addRecipe(new ItemStack(PolongniusBlocks.polongnius_dungeon_brick_stairs, 4), new Object[] { "X  ", "XX ", "XXX", 'X', new ItemStack(PolongniusBlocks.polongnius_block, 1, 14) });

        // Slabs
        GameRegistry.addRecipe(new ItemStack(MPBlocks.stone_slab_half, 6, 3), new Object[] { "XXX", 'X', new ItemStack(PolongniusBlocks.polongnius_block, 1, 3) });
        GameRegistry.addRecipe(new ItemStack(MPBlocks.dungeon_brick_slab_half, 6, 1), new Object[] { "XXX", 'X', new ItemStack(PolongniusBlocks.polongnius_block, 1, 14) });

        // Walls
        GameRegistry.addRecipe(new ItemStack(MPBlocks.stone_wall, 6, 3), new Object[] { "XXX", "XXX", 'X', new ItemStack(PolongniusBlocks.polongnius_block, 1, 3) });
        GameRegistry.addRecipe(new ItemStack(MPBlocks.dungeon_brick_wall, 6, 1), new Object[] { "XXX", "XXX", 'X', new ItemStack(PolongniusBlocks.polongnius_block, 1, 14) });
    }

    private static void addItemRecipes()
    {
        // Items
        GameRegistry.addRecipe(new ItemStack(PolongniusItems.polongnius_item, 2, 8), new Object[] { "M", "M", 'M', new ItemStack(PolongniusItems.polongnius_item, 1, 4) });
        GameRegistry.addRecipe(new ItemStack(PolongniusItems.polongnius_item, 2, 9), new Object[] { "P", "P", 'P', new ItemStack(PolongniusItems.polongnius_item, 1, 5) });
        GameRegistry.addShapelessRecipe(new ItemStack(PolongniusItems.polongnius_item, 9, 4), new ItemStack(PolongniusBlocks.polongnius_block, 1, 10) );
        GameRegistry.addShapelessRecipe(new ItemStack(PolongniusItems.polongnius_item, 9, 1), new ItemStack(PolongniusBlocks.polongnius_block, 1, 11) );
        GameRegistry.addShapelessRecipe(new ItemStack(PolongniusItems.polongnius_item, 9, 5), new ItemStack(PolongniusBlocks.polongnius_block, 1, 12) );
        GameRegistry.addRecipe(new ItemStack(PolongniusItems.purple_crystal_solar_module, 2, 2), new Object[] { "GGG", "WWW", "AAA", 'G', new ItemStack(Blocks.glass), 'W', new ItemStack(PolongniusItems.purple_crystal_solar_module, 1, 1), 'A', new ItemStack(GCBlocks.aluminumWire, 1, 1) });
        GameRegistry.addRecipe(new ItemStack(PolongniusItems.purple_crystal_solar_module, 1, 3), new Object[] { "SSS", "AAA", "SSS", 'S', new ItemStack(PolongniusItems.purple_crystal_solar_module, 1, 2), 'A', new ItemStack(GCBlocks.aluminumWire, 1, 1) });
        GameRegistry.addShapelessRecipe(new ItemStack(PolongniusItems.cheese_slimeball, 9), new ItemStack(PolongniusBlocks.cheese_slime_block) );
        GameRegistry.addRecipe(new ItemStack(PolongniusItems.tier6_rocket_module, 1, 0), new Object[] { " SB", "TCT", "TAT", 'B', new ItemStack(Blocks.stone_button), 'S', new ItemStack(Items.flint_and_steel), 'T', new ItemStack(PolongniusItems.tier6_rocket_module, 1, 2), 'A', new ItemStack(GCItems.oxygenVent), 'C', new ItemStack(GCItems.canister, 1, 0) });
        GameRegistry.addRecipe(new ItemStack(PolongniusItems.tier6_rocket_module, 1, 0), new Object[] { "BS ", "TCT", "TAT", 'B', new ItemStack(Blocks.stone_button), 'S', new ItemStack(Items.flint_and_steel), 'T', new ItemStack(PolongniusItems.tier6_rocket_module, 1, 2), 'A', new ItemStack(GCItems.oxygenVent), 'C', new ItemStack(GCItems.canister, 1, 0) });
        GameRegistry.addRecipe(new ItemStack(PolongniusItems.tier6_rocket_module, 1, 1), new Object[] { "DQD", "DCD", "POP", 'Q', new ItemStack(PolongniusBlocks.polongnius_block, 1, 11), 'P', new ItemStack(PolongniusItems.tier6_rocket_module, 1, 2), 'C', new ItemStack(GCItems.fuelCanister, 1, 1), 'D', new ItemStack(PolongniusItems.polongnius_item, 1, 7), 'O', new ItemStack(GCItems.oxygenVent) });
        GameRegistry.addShapelessRecipe(new ItemStack(PolongniusItems.polongnius_meteor_chunk, 3), new ItemStack(PolongniusItems.polongnius_item, 1, 2) );

        // Armor
        GameRegistry.addRecipe(new ItemStack(PolongniusArmorItems.cheese_leather_helmet), new Object[] { "QQQ", "Q Q", 'Q', new ItemStack(PolongniusItems.polongnius_item, 1, 10) });
        GameRegistry.addRecipe(new ItemStack(PolongniusArmorItems.cheese_leather_chestplate), new Object[] { "Q Q", "QQQ", "QQQ", 'Q', new ItemStack(PolongniusItems.polongnius_item, 1, 10) });
        GameRegistry.addRecipe(new ItemStack(PolongniusArmorItems.cheese_leather_leggings), new Object[] { "QQQ", "Q Q", "Q Q", 'Q', new ItemStack(PolongniusItems.polongnius_item, 1, 10) });
        GameRegistry.addRecipe(new ItemStack(PolongniusArmorItems.cheese_leather_boots), new Object[] { "Q Q", "Q Q", 'Q', new ItemStack(PolongniusItems.polongnius_item, 1, 10) });
        GameRegistry.addRecipe(new ItemStack(PolongniusArmorItems.polongnius_meteoric_iron_helmet), new Object[] { "QQQ", "Q Q", 'Q', new ItemStack(PolongniusItems.polongnius_item, 1, 6) });
        GameRegistry.addRecipe(new ItemStack(PolongniusArmorItems.polongnius_meteoric_iron_chestplate), new Object[] { "Q Q", "QQQ", "QQQ", 'Q', new ItemStack(PolongniusItems.polongnius_item, 1, 6) });
        GameRegistry.addRecipe(new ItemStack(PolongniusArmorItems.polongnius_meteoric_iron_leggings), new Object[] { "QQQ", "Q Q", "Q Q", 'Q', new ItemStack(PolongniusItems.polongnius_item, 1, 6) });
        GameRegistry.addRecipe(new ItemStack(PolongniusArmorItems.polongnius_meteoric_iron_boots), new Object[] { "Q Q", "Q Q", 'Q', new ItemStack(PolongniusItems.polongnius_item, 1, 6) });
        GameRegistry.addRecipe(new ItemStack(PolongniusArmorItems.palladium_helmet), new Object[] { "QQQ", "Q Q", 'Q', new ItemStack(PolongniusItems.polongnius_item, 1, 7) });
        GameRegistry.addRecipe(new ItemStack(PolongniusArmorItems.palladium_chestplate), new Object[] { "Q Q", "QQQ", "QQQ", 'Q', new ItemStack(PolongniusItems.polongnius_item, 1, 7) });
        GameRegistry.addRecipe(new ItemStack(PolongniusArmorItems.palladium_leggings), new Object[] { "QQQ", "Q Q", "Q Q", 'Q', new ItemStack(PolongniusItems.polongnius_item, 1, 7) });
        GameRegistry.addRecipe(new ItemStack(PolongniusArmorItems.palladium_boots), new Object[] { "Q Q", "Q Q", 'Q', new ItemStack(PolongniusItems.polongnius_item, 1, 7) });
        GameRegistry.addRecipe(new ItemStack(PolongniusArmorItems.purple_crystal_helmet), new Object[] { "QQQ", "Q Q", 'Q', new ItemStack(PolongniusItems.polongnius_item, 1, 1) });
        GameRegistry.addRecipe(new ItemStack(PolongniusArmorItems.purple_crystal_chestplate), new Object[] { "Q Q", "QQQ", "QQQ", 'Q', new ItemStack(PolongniusItems.polongnius_item, 1, 1) });
        GameRegistry.addRecipe(new ItemStack(PolongniusArmorItems.purple_crystal_leggings), new Object[] { "QQQ", "Q Q", "Q Q", 'Q', new ItemStack(PolongniusItems.polongnius_item, 1, 1) });
        GameRegistry.addRecipe(new ItemStack(PolongniusArmorItems.purple_crystal_boots), new Object[] { "Q Q", "Q Q", 'Q', new ItemStack(PolongniusItems.polongnius_item, 1, 1) });
        GameRegistry.addRecipe(new ItemStack(PolongniusArmorItems.breathable_polongnius_meteor_helmet), new Object[] { "MMM", "MOM", 'O', new ItemStack(GCItems.oxMask), 'M', new ItemStack(PolongniusItems.polongnius_item, 1, 6) });
        GameRegistry.addRecipe(new ItemStack(PolongniusArmorItems.breathable_palladium_helmet), new Object[] { "PPP", "POP", 'O', new ItemStack(GCItems.oxMask), 'P', new ItemStack(PolongniusItems.polongnius_item, 1, 7) });

        // Tools
        GameRegistry.addRecipe(new ItemStack(PolongniusToolsItems.polongnius_meteoric_iron_hoe), new Object[] { "XX", " Y", " Y", 'X', new ItemStack(PolongniusItems.polongnius_item, 1, 6), 'Y', new ItemStack(PolongniusItems.polongnius_item, 1, 8) });
        GameRegistry.addRecipe(new ItemStack(PolongniusToolsItems.polongnius_meteoric_iron_hoe), new Object[] { "XX", "Y ", "Y ", 'X', new ItemStack(PolongniusItems.polongnius_item, 1, 6), 'Y', new ItemStack(PolongniusItems.polongnius_item, 1, 8) });
        GameRegistry.addRecipe(new ItemStack(PolongniusToolsItems.polongnius_meteoric_iron_axe), new Object[] { "XX", "XY", " Y", 'X', new ItemStack(PolongniusItems.polongnius_item, 1, 6), 'Y', new ItemStack(PolongniusItems.polongnius_item, 1, 8) });
        GameRegistry.addRecipe(new ItemStack(PolongniusToolsItems.polongnius_meteoric_iron_axe), new Object[] { "XX", "YX", "Y ", 'X', new ItemStack(PolongniusItems.polongnius_item, 1, 6), 'Y', new ItemStack(PolongniusItems.polongnius_item, 1, 8) });
        GameRegistry.addRecipe(new ItemStack(PolongniusToolsItems.polongnius_meteoric_iron_pickaxe), new Object[] { "XXX", " Y ", " Y ", 'X', new ItemStack(PolongniusItems.polongnius_item, 1, 6), 'Y', new ItemStack(PolongniusItems.polongnius_item, 1, 8) });
        GameRegistry.addRecipe(new ItemStack(PolongniusToolsItems.polongnius_meteoric_iron_sword), new Object[] { "X", "X", "Y", 'X', new ItemStack(PolongniusItems.polongnius_item, 1, 6), 'Y', new ItemStack(PolongniusItems.polongnius_item, 1, 8) });
        GameRegistry.addRecipe(new ItemStack(PolongniusToolsItems.polongnius_meteoric_iron_shovel), new Object[] { "X", "Y", "Y", 'X', new ItemStack(PolongniusItems.polongnius_item, 1, 6), 'Y', new ItemStack(PolongniusItems.polongnius_item, 1, 8) });
        GameRegistry.addRecipe(new ItemStack(PolongniusToolsItems.palladium_hoe), new Object[] { "XX", " Y", " Y", 'X', new ItemStack(PolongniusItems.polongnius_item, 1, 7), 'Y', new ItemStack(PolongniusItems.polongnius_item, 1, 9) });
        GameRegistry.addRecipe(new ItemStack(PolongniusToolsItems.palladium_hoe), new Object[] { "XX", "Y ", "Y ", 'X', new ItemStack(PolongniusItems.polongnius_item, 1, 7), 'Y', new ItemStack(PolongniusItems.polongnius_item, 1, 9) });
        GameRegistry.addRecipe(new ItemStack(PolongniusToolsItems.palladium_axe), new Object[] { "XX", "XY", " Y", 'X', new ItemStack(PolongniusItems.polongnius_item, 1, 7), 'Y', new ItemStack(PolongniusItems.polongnius_item, 1, 9) });
        GameRegistry.addRecipe(new ItemStack(PolongniusToolsItems.palladium_axe), new Object[] { "XX", "YX", "Y ", 'X', new ItemStack(PolongniusItems.polongnius_item, 1, 7), 'Y', new ItemStack(PolongniusItems.polongnius_item, 1, 9) });
        GameRegistry.addRecipe(new ItemStack(PolongniusToolsItems.palladium_pickaxe), new Object[] { "XXX", " Y ", " Y ", 'X', new ItemStack(PolongniusItems.polongnius_item, 1, 7), 'Y', new ItemStack(PolongniusItems.polongnius_item, 1, 9) });
        GameRegistry.addRecipe(new ItemStack(PolongniusToolsItems.palladium_sword), new Object[] { "X", "X", "Y", 'X', new ItemStack(PolongniusItems.polongnius_item, 1, 7), 'Y', new ItemStack(PolongniusItems.polongnius_item, 1, 9) });
        GameRegistry.addRecipe(new ItemStack(PolongniusToolsItems.palladium_shovel), new Object[] { "X", "Y", "Y", 'X', new ItemStack(PolongniusItems.polongnius_item, 1, 7), 'Y', new ItemStack(PolongniusItems.polongnius_item, 1, 9) });
        GameRegistry.addRecipe(new ItemStack(PolongniusToolsItems.purple_crystal_hoe), new Object[] { "XX", " Y", " Y", 'X', new ItemStack(PolongniusItems.polongnius_item, 1, 1), 'Y', new ItemStack(PolongniusItems.polongnius_item, 1, 9) });
        GameRegistry.addRecipe(new ItemStack(PolongniusToolsItems.purple_crystal_hoe), new Object[] { "XX", "Y ", "Y ", 'X', new ItemStack(PolongniusItems.polongnius_item, 1, 1), 'Y', new ItemStack(PolongniusItems.polongnius_item, 1, 9) });
        GameRegistry.addRecipe(new ItemStack(PolongniusToolsItems.purple_crystal_axe), new Object[] { "XX", "XY", " Y", 'X', new ItemStack(PolongniusItems.polongnius_item, 1, 1), 'Y', new ItemStack(PolongniusItems.polongnius_item, 1, 9) });
        GameRegistry.addRecipe(new ItemStack(PolongniusToolsItems.purple_crystal_axe), new Object[] { "XX", "YX", "Y ", 'X', new ItemStack(PolongniusItems.polongnius_item, 1, 1), 'Y', new ItemStack(PolongniusItems.polongnius_item, 1, 9) });
        GameRegistry.addRecipe(new ItemStack(PolongniusToolsItems.purple_crystal_pickaxe), new Object[] { "XXX", " Y ", " Y ", 'X', new ItemStack(PolongniusItems.polongnius_item, 1, 1), 'Y', new ItemStack(PolongniusItems.polongnius_item, 1, 9) });
        GameRegistry.addRecipe(new ItemStack(PolongniusToolsItems.purple_crystal_sword), new Object[] { "X", "X", "Y", 'X', new ItemStack(PolongniusItems.polongnius_item, 1, 1), 'Y', new ItemStack(PolongniusItems.polongnius_item, 1, 9) });
        GameRegistry.addRecipe(new ItemStack(PolongniusToolsItems.purple_crystal_shovel), new Object[] { "X", "Y", "Y", 'X', new ItemStack(PolongniusItems.polongnius_item, 1, 1), 'Y', new ItemStack(PolongniusItems.polongnius_item, 1, 9) });
    }

    private static void addBlockSmelting()
    {
        GameRegistry.addSmelting(new ItemStack(PolongniusBlocks.polongnius_block, 1, 3), new ItemStack(PolongniusBlocks.polongnius_block, 1, 2), 0.4F);
        GameRegistry.addSmelting(new ItemStack(PolongniusBlocks.polongnius_block, 1, 13), new ItemStack(PolongniusBlocks.polongnius_block, 1, 14), 0.3F);
    }

    private static void addItemSmelting()
    {
        GameRegistry.addSmelting(new ItemStack(PolongniusBlocks.polongnius_block, 1, 4), new ItemStack(GCItems.basicItem, 1, 3), 0.7F);
        GameRegistry.addSmelting(new ItemStack(PolongniusBlocks.polongnius_block, 1, 5), new ItemStack(GCItems.basicItem, 1, 4), 0.7F);
        GameRegistry.addSmelting(new ItemStack(PolongniusBlocks.polongnius_block, 1, 6), new ItemStack(Items.iron_ingot), 0.7F);
        GameRegistry.addSmelting(new ItemStack(PolongniusBlocks.polongnius_block, 1, 7), new ItemStack(PolongniusItems.polongnius_item, 1, 5), 0.7F);
        GameRegistry.addSmelting(new ItemStack(PolongniusBlocks.polongnius_block, 1, 8), new ItemStack(PolongniusItems.polongnius_item, 1, 0), 0.7F);
        GameRegistry.addSmelting(new ItemStack(PolongniusBlocks.polongnius_block, 1, 9), new ItemStack(PolongniusItems.polongnius_item, 1, 1), 0.6F);
        GameRegistry.addSmelting(new ItemStack(PolongniusItems.polongnius_item, 1, 3), new ItemStack(PolongniusItems.polongnius_item, 1, 5), 0.7F);
        GameRegistry.addSmelting(new ItemStack(PolongniusBlocks.fallen_polongnius_meteor), new ItemStack(PolongniusItems.polongnius_item, 1, 4), 0.7F);
        GameRegistry.addSmelting(new ItemStack(PolongniusItems.polongnius_item, 1, 2), new ItemStack(PolongniusItems.polongnius_item, 1, 4), 0.7F);
        GameRegistry.addSmelting(new ItemStack(PolongniusItems.polongnius_food, 1, 1), new ItemStack(PolongniusItems.polongnius_food, 1, 2), 0.6F);
    }

    private static void addTier5RocketRecipes()
    {
        HashMap<Integer, ItemStack> input = new HashMap<Integer, ItemStack>();
        input.put(1, new ItemStack(DionaItems.tier4_rocket_module, 1, 0));
        input.put(2, new ItemStack(DionaItems.diona_item, 1, 4));
        input.put(3, new ItemStack(DionaItems.diona_item, 1, 4));
        input.put(4, new ItemStack(DionaItems.diona_item, 1, 4));
        input.put(5, new ItemStack(DionaItems.diona_item, 1, 4));
        input.put(6, new ItemStack(DionaItems.diona_item, 1, 4));
        input.put(7, new ItemStack(DionaItems.diona_item, 1, 4));
        input.put(8, new ItemStack(DionaItems.diona_item, 1, 4));
        input.put(9, new ItemStack(DionaItems.diona_item, 1, 4));
        input.put(10, new ItemStack(DionaItems.diona_item, 1, 4));
        input.put(11, new ItemStack(DionaItems.diona_item, 1, 4));
        input.put(12, new ItemStack(DionaItems.tier4_rocket_module, 1, 6));
        input.put(13, new ItemStack(AsteroidsItems.basicItem, 1, 2));
        input.put(14, new ItemStack(AsteroidsItems.basicItem, 1, 2));
        input.put(15, new ItemStack(DionaItems.tier4_rocket_module, 1, 5));
        input.put(16, new ItemStack(DionaItems.tier4_rocket_module, 1, 6));
        input.put(17, new ItemStack(AsteroidsItems.basicItem, 1, 2));
        input.put(18, new ItemStack(AsteroidsItems.basicItem, 1, 2));
        input.put(19, null);
        input.put(20, null);
        input.put(21, null);
        Tier5RocketRecipes.addTier5RocketBenchRecipe(new ItemStack(PolongniusItems.tier5_rocket, 1, 0), input);

        HashMap<Integer, ItemStack> input2 = new HashMap<Integer, ItemStack>(input);
        input2.put(19, new ItemStack(Blocks.chest));
        input2.put(20, null);
        input2.put(21, null);
        Tier5RocketRecipes.addTier5RocketBenchRecipe(new ItemStack(PolongniusItems.tier5_rocket, 1, 1), input2);

        input2 = new HashMap<Integer, ItemStack>(input);
        input2.put(19, null);
        input2.put(20, new ItemStack(Blocks.chest));
        input2.put(21, null);
        Tier5RocketRecipes.addTier5RocketBenchRecipe(new ItemStack(PolongniusItems.tier5_rocket, 1, 1), input2);

        input2 = new HashMap<Integer, ItemStack>(input);
        input2.put(19, null);
        input2.put(20, null);
        input2.put(21, new ItemStack(Blocks.chest));
        Tier5RocketRecipes.addTier5RocketBenchRecipe(new ItemStack(PolongniusItems.tier5_rocket, 1, 1), input2);

        input2 = new HashMap<Integer, ItemStack>(input);
        input2.put(19, new ItemStack(Blocks.chest));
        input2.put(20, new ItemStack(Blocks.chest));
        input2.put(21, null);
        Tier5RocketRecipes.addTier5RocketBenchRecipe(new ItemStack(PolongniusItems.tier5_rocket, 1, 2), input2);

        input2 = new HashMap<Integer, ItemStack>(input);
        input2.put(19, new ItemStack(Blocks.chest));
        input2.put(20, null);
        input2.put(21, new ItemStack(Blocks.chest));
        Tier5RocketRecipes.addTier5RocketBenchRecipe(new ItemStack(PolongniusItems.tier5_rocket, 1, 2), input2);

        input2 = new HashMap<Integer, ItemStack>(input);
        input2.put(19, null);
        input2.put(20, new ItemStack(Blocks.chest));
        input2.put(21, new ItemStack(Blocks.chest));
        Tier5RocketRecipes.addTier5RocketBenchRecipe(new ItemStack(PolongniusItems.tier5_rocket, 1, 2), input2);

        input2 = new HashMap<Integer, ItemStack>(input);
        input2.put(19, new ItemStack(Blocks.chest));
        input2.put(20, new ItemStack(Blocks.chest));
        input2.put(21, new ItemStack(Blocks.chest));
        Tier5RocketRecipes.addTier5RocketBenchRecipe(new ItemStack(PolongniusItems.tier5_rocket, 1, 3), input2);
    }

    private static void addTier5RocketNoFlagRecipes()
    {
        HashMap<Integer, ItemStack> input = new HashMap<Integer, ItemStack>();
        input.put(1, new ItemStack(DionaItems.tier4_rocket_module, 1, 4));
        input.put(2, new ItemStack(DionaItems.diona_item, 1, 4));
        input.put(3, new ItemStack(DionaItems.diona_item, 1, 4));
        input.put(4, new ItemStack(DionaItems.diona_item, 1, 4));
        input.put(5, new ItemStack(DionaItems.diona_item, 1, 4));
        input.put(6, new ItemStack(DionaItems.diona_item, 1, 4));
        input.put(7, new ItemStack(DionaItems.diona_item, 1, 4));
        input.put(8, new ItemStack(DionaItems.diona_item, 1, 4));
        input.put(9, new ItemStack(DionaItems.diona_item, 1, 4));
        input.put(10, new ItemStack(DionaItems.diona_item, 1, 4));
        input.put(11, new ItemStack(DionaItems.diona_item, 1, 4));
        input.put(12, new ItemStack(DionaItems.tier4_rocket_module, 1, 6));
        input.put(13, new ItemStack(AsteroidsItems.basicItem, 1, 2));
        input.put(14, new ItemStack(AsteroidsItems.basicItem, 1, 2));
        input.put(15, new ItemStack(DionaItems.tier4_rocket_module, 1, 5));
        input.put(16, new ItemStack(DionaItems.tier4_rocket_module, 1, 6));
        input.put(17, new ItemStack(AsteroidsItems.basicItem, 1, 2));
        input.put(18, new ItemStack(AsteroidsItems.basicItem, 1, 2));
        input.put(19, null);
        input.put(20, null);
        input.put(21, null);
        Tier5RocketRecipes.addTier5RocketBenchNoFlagRecipe(new ItemStack(PolongniusItems.tier5_rocket, 1, 10), input);

        HashMap<Integer, ItemStack> input2 = new HashMap<Integer, ItemStack>(input);
        input2.put(19, new ItemStack(Blocks.chest));
        input2.put(20, null);
        input2.put(21, null);
        Tier5RocketRecipes.addTier5RocketBenchNoFlagRecipe(new ItemStack(PolongniusItems.tier5_rocket, 1, 11), input2);

        input2 = new HashMap<Integer, ItemStack>(input);
        input2.put(19, null);
        input2.put(20, new ItemStack(Blocks.chest));
        input2.put(21, null);
        Tier5RocketRecipes.addTier5RocketBenchNoFlagRecipe(new ItemStack(PolongniusItems.tier5_rocket, 1, 11), input2);

        input2 = new HashMap<Integer, ItemStack>(input);
        input2.put(19, null);
        input2.put(20, null);
        input2.put(21, new ItemStack(Blocks.chest));
        Tier5RocketRecipes.addTier5RocketBenchNoFlagRecipe(new ItemStack(PolongniusItems.tier5_rocket, 1, 11), input2);

        input2 = new HashMap<Integer, ItemStack>(input);
        input2.put(19, new ItemStack(Blocks.chest));
        input2.put(20, new ItemStack(Blocks.chest));
        input2.put(21, null);
        Tier5RocketRecipes.addTier5RocketBenchNoFlagRecipe(new ItemStack(PolongniusItems.tier5_rocket, 1, 12), input2);

        input2 = new HashMap<Integer, ItemStack>(input);
        input2.put(19, new ItemStack(Blocks.chest));
        input2.put(20, null);
        input2.put(21, new ItemStack(Blocks.chest));
        Tier5RocketRecipes.addTier5RocketBenchNoFlagRecipe(new ItemStack(PolongniusItems.tier5_rocket, 1, 12), input2);

        input2 = new HashMap<Integer, ItemStack>(input);
        input2.put(19, null);
        input2.put(20, new ItemStack(Blocks.chest));
        input2.put(21, new ItemStack(Blocks.chest));
        Tier5RocketRecipes.addTier5RocketBenchNoFlagRecipe(new ItemStack(PolongniusItems.tier5_rocket, 1, 12), input2);

        input2 = new HashMap<Integer, ItemStack>(input);
        input2.put(19, new ItemStack(Blocks.chest));
        input2.put(20, new ItemStack(Blocks.chest));
        input2.put(21, new ItemStack(Blocks.chest));
        Tier5RocketRecipes.addTier5RocketBenchNoFlagRecipe(new ItemStack(PolongniusItems.tier5_rocket, 1, 13), input2);
    }

    private static void addOreDictionary()
    {
        OreDictionary.registerOre("slimeball", PolongniusItems.cheese_slimeball);
    }
}