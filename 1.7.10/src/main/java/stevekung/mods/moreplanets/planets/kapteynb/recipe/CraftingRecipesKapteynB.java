/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 *
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.planets.kapteynb.recipe;

import java.util.HashMap;

import cpw.mods.fml.common.registry.GameRegistry;
import micdoodle8.mods.galacticraft.core.items.GCItems;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import stevekung.mods.moreplanets.core.init.MPBlocks;
import stevekung.mods.moreplanets.planets.fronos.items.FronosItems;
import stevekung.mods.moreplanets.planets.kapteynb.blocks.KapteynBBlocks;
import stevekung.mods.moreplanets.planets.kapteynb.items.KapteynBItems;
import stevekung.mods.moreplanets.planets.kapteynb.items.armor.KapteynBArmorItems;
import stevekung.mods.moreplanets.planets.kapteynb.items.tools.KapteynBToolsItems;
import stevekung.mods.moreplanets.planets.nibiru.items.NibiruItems;

public class CraftingRecipesKapteynB
{
    public static void loadRecipes()
    {
        CraftingRecipesKapteynB.addBlockRecipes();
        CraftingRecipesKapteynB.addItemRecipes();
        CraftingRecipesKapteynB.addBlockSmelting();
        CraftingRecipesKapteynB.addItemSmelting();
        CraftingRecipesKapteynB.addTier8RocketRecipes();
    }

    private static void addBlockRecipes()
    {
        // Blocks
        GameRegistry.addRecipe(new ItemStack(KapteynBBlocks.kapteyn_b_block, 1, 11), new Object[] { "NNN", "NNN", "NNN", 'N', new ItemStack(KapteynBItems.namerium_crystal, 1, 0) });
        GameRegistry.addRecipe(new ItemStack(KapteynBBlocks.kapteyn_b_block, 1, 12), new Object[] { "III", "III", "III", 'I', new ItemStack(KapteynBItems.kapteyn_b_item, 1, 0) });
        GameRegistry.addRecipe(new ItemStack(KapteynBBlocks.kapteyn_b_block, 1, 13), new Object[] { "UUU", "UUU", "UUU", 'U', new ItemStack(KapteynBItems.kapteyn_b_item, 1, 1) });
        GameRegistry.addRecipe(new ItemStack(KapteynBBlocks.uranium_bomb), new Object[] { "GUG", "UGU", "GUG", 'U', new ItemStack(KapteynBBlocks.kapteyn_b_block, 1, 13), 'G', new ItemStack(Items.gunpowder) });

        // Walls
        GameRegistry.addRecipe(new ItemStack(MPBlocks.stone_wall, 6, 11), new Object[] { "CCC", "CCC", 'C', new ItemStack(KapteynBBlocks.kapteyn_b_block, 1, 3) });
        GameRegistry.addRecipe(new ItemStack(MPBlocks.dungeon_brick_wall, 6, 5), new Object[] { "CCC", "CCC", 'C', new ItemStack(KapteynBBlocks.kapteyn_b_block, 1, 14) });

        // Slabs
        GameRegistry.addRecipe(new ItemStack(MPBlocks.stone_slab_half2, 6, 3), new Object[] { "CCC", 'C', new ItemStack(KapteynBBlocks.kapteyn_b_block, 1, 3) });
        GameRegistry.addRecipe(new ItemStack(MPBlocks.dungeon_brick_slab_half, 6, 5), new Object[] { "CCC", 'C', new ItemStack(KapteynBBlocks.kapteyn_b_block, 1, 14) });

        // Kapteyn B Cobblestone Stairs
        GameRegistry.addRecipe(new ItemStack(KapteynBBlocks.kapteyn_b_cobblestone_stairs, 4), new Object[] { "  X", " XX", "XXX", 'X', new ItemStack(KapteynBBlocks.kapteyn_b_block, 1, 3) });
        GameRegistry.addRecipe(new ItemStack(KapteynBBlocks.kapteyn_b_cobblestone_stairs, 4), new Object[] { "X  ", "XX ", "XXX", 'X', new ItemStack(KapteynBBlocks.kapteyn_b_block, 1, 3) });

        // Kapteyn B Dungeon Brick Stairs
        GameRegistry.addRecipe(new ItemStack(KapteynBBlocks.kapteyn_b_dungeon_brick_stairs, 4), new Object[] { "  X", " XX", "XXX", 'X', new ItemStack(KapteynBBlocks.kapteyn_b_block, 1, 14) });
        GameRegistry.addRecipe(new ItemStack(KapteynBBlocks.kapteyn_b_dungeon_brick_stairs, 4), new Object[] { "X  ", "XX ", "XXX", 'X', new ItemStack(KapteynBBlocks.kapteyn_b_block, 1, 14) });
    }

    private static void addItemRecipes()
    {
        // Items
        GameRegistry.addRecipe(new ItemStack(KapteynBItems.uranium_battery, 1, 99), new Object[] { " I ", "IUI", "IGI", 'I', new ItemStack(KapteynBItems.kapteyn_b_item, 1, 2), 'U', new ItemStack(Items.redstone), 'G', new ItemStack(KapteynBItems.kapteyn_b_item, 1, 1) });
        GameRegistry.addShapelessRecipe(new ItemStack(KapteynBItems.namerium_crystal, 9, 0), new ItemStack(KapteynBBlocks.kapteyn_b_block, 1, 11) );
        GameRegistry.addShapelessRecipe(new ItemStack(KapteynBItems.kapteyn_b_item, 9, 0), new ItemStack(KapteynBBlocks.kapteyn_b_block, 1, 12) );
        GameRegistry.addShapelessRecipe(new ItemStack(KapteynBItems.kapteyn_b_item, 9, 1), new ItemStack(KapteynBBlocks.kapteyn_b_block, 1, 13) );
        GameRegistry.addRecipe(new ItemStack(KapteynBItems.kapteyn_b_item, 2, 4), new Object[] { "I", "I", 'I', new ItemStack(KapteynBItems.kapteyn_b_item, 1, 0) });
        GameRegistry.addRecipe(new ItemStack(KapteynBItems.kapteyn_b_item, 2, 3), new Object[] { "U", "U", 'U', new ItemStack(KapteynBItems.kapteyn_b_item, 1, 1) });
        GameRegistry.addRecipe(new ItemStack(KapteynBItems.ice_crystal_meteor_chunk, 3), new Object[] { "MM", "MM", 'M', new ItemStack(KapteynBItems.kapteyn_b_item, 1, 5) });

        // Armor
        GameRegistry.addRecipe(new ItemStack(KapteynBArmorItems.uranium_helmet), new Object[] { "QQQ", "Q Q", 'Q', new ItemStack(KapteynBItems.kapteyn_b_item, 1, 1) });
        GameRegistry.addRecipe(new ItemStack(KapteynBArmorItems.uranium_chestplate), new Object[] { "Q Q", "QQQ", "QQQ", 'Q', new ItemStack(KapteynBItems.kapteyn_b_item, 1, 1) });
        GameRegistry.addRecipe(new ItemStack(KapteynBArmorItems.uranium_leggings), new Object[] { "QQQ", "Q Q", "Q Q", 'Q', new ItemStack(KapteynBItems.kapteyn_b_item, 1, 1) });
        GameRegistry.addRecipe(new ItemStack(KapteynBArmorItems.uranium_boots), new Object[] { "Q Q", "Q Q", 'Q', new ItemStack(KapteynBItems.kapteyn_b_item, 1, 1) });
        GameRegistry.addRecipe(new ItemStack(KapteynBArmorItems.frozen_iron_helmet), new Object[] { "QQQ", "Q Q", 'Q', new ItemStack(KapteynBItems.kapteyn_b_item, 1, 2) });
        GameRegistry.addRecipe(new ItemStack(KapteynBArmorItems.frozen_iron_chestplate), new Object[] { "Q Q", "QQQ", "QQQ", 'Q', new ItemStack(KapteynBItems.kapteyn_b_item, 1, 2) });
        GameRegistry.addRecipe(new ItemStack(KapteynBArmorItems.frozen_iron_leggings), new Object[] { "QQQ", "Q Q", "Q Q", 'Q', new ItemStack(KapteynBItems.kapteyn_b_item, 1, 2) });
        GameRegistry.addRecipe(new ItemStack(KapteynBArmorItems.frozen_iron_boots), new Object[] { "Q Q", "Q Q", 'Q', new ItemStack(KapteynBItems.kapteyn_b_item, 1, 2) });
        GameRegistry.addRecipe(new ItemStack(KapteynBArmorItems.breathable_uranium_helmet), new Object[] { "QQQ", "QOQ", 'Q', new ItemStack(KapteynBItems.kapteyn_b_item, 1, 1), 'O', new ItemStack(GCItems.oxMask) });
        GameRegistry.addRecipe(new ItemStack(KapteynBArmorItems.breathable_frozen_iron_helmet), new Object[] { "QQQ", "QOQ", 'Q', new ItemStack(KapteynBItems.kapteyn_b_item, 1, 2), 'O', new ItemStack(GCItems.oxMask) });

        // Tools
        GameRegistry.addRecipe(new ItemStack(KapteynBToolsItems.uranium_hoe), new Object[] { "XX", " Y", " Y", 'X', new ItemStack(KapteynBItems.kapteyn_b_item, 1, 1), 'Y', new ItemStack(KapteynBItems.kapteyn_b_item, 1, 3) });
        GameRegistry.addRecipe(new ItemStack(KapteynBToolsItems.uranium_hoe), new Object[] { "XX", "Y ", "Y ", 'X', new ItemStack(KapteynBItems.kapteyn_b_item, 1, 1), 'Y', new ItemStack(KapteynBItems.kapteyn_b_item, 1, 3) });
        GameRegistry.addRecipe(new ItemStack(KapteynBToolsItems.uranium_axe), new Object[] { "XX", "XY", " Y", 'X', new ItemStack(KapteynBItems.kapteyn_b_item, 1, 1), 'Y', new ItemStack(KapteynBItems.kapteyn_b_item, 1, 3) });
        GameRegistry.addRecipe(new ItemStack(KapteynBToolsItems.uranium_axe), new Object[] { "XX", "YX", "Y ", 'X', new ItemStack(KapteynBItems.kapteyn_b_item, 1, 1), 'Y', new ItemStack(KapteynBItems.kapteyn_b_item, 1, 3) });
        GameRegistry.addRecipe(new ItemStack(KapteynBToolsItems.uranium_pickaxe), new Object[] { "XXX", " Y ", " Y ", 'X', new ItemStack(KapteynBItems.kapteyn_b_item, 1, 1), 'Y', new ItemStack(KapteynBItems.kapteyn_b_item, 1, 3) });
        GameRegistry.addRecipe(new ItemStack(KapteynBToolsItems.uranium_sword), new Object[] { "X", "X", "Y", 'X', new ItemStack(KapteynBItems.kapteyn_b_item, 1, 1), 'Y', new ItemStack(KapteynBItems.kapteyn_b_item, 1, 3) });
        GameRegistry.addRecipe(new ItemStack(KapteynBToolsItems.uranium_shovel), new Object[] { "X", "Y", "Y", 'X', new ItemStack(KapteynBItems.kapteyn_b_item, 1, 1), 'Y', new ItemStack(KapteynBItems.kapteyn_b_item, 1, 3) });
        GameRegistry.addRecipe(new ItemStack(KapteynBToolsItems.frozen_iron_hoe), new Object[] { "XX", " Y", " Y", 'X', new ItemStack(KapteynBItems.kapteyn_b_item, 1, 2), 'Y', new ItemStack(KapteynBItems.kapteyn_b_item, 1, 4) });
        GameRegistry.addRecipe(new ItemStack(KapteynBToolsItems.frozen_iron_hoe), new Object[] { "XX", "Y ", "Y ", 'X', new ItemStack(KapteynBItems.kapteyn_b_item, 1, 2), 'Y', new ItemStack(KapteynBItems.kapteyn_b_item, 1, 4) });
        GameRegistry.addRecipe(new ItemStack(KapteynBToolsItems.frozen_iron_axe), new Object[] { "XX", "XY", " Y", 'X', new ItemStack(KapteynBItems.kapteyn_b_item, 1, 2), 'Y', new ItemStack(KapteynBItems.kapteyn_b_item, 1, 4) });
        GameRegistry.addRecipe(new ItemStack(KapteynBToolsItems.frozen_iron_axe), new Object[] { "XX", "YX", "Y ", 'X', new ItemStack(KapteynBItems.kapteyn_b_item, 1, 2), 'Y', new ItemStack(KapteynBItems.kapteyn_b_item, 1, 4) });
        GameRegistry.addRecipe(new ItemStack(KapteynBToolsItems.frozen_iron_pickaxe), new Object[] { "XXX", " Y ", " Y ", 'X', new ItemStack(KapteynBItems.kapteyn_b_item, 1, 2), 'Y', new ItemStack(KapteynBItems.kapteyn_b_item, 1, 4) });
        GameRegistry.addRecipe(new ItemStack(KapteynBToolsItems.frozen_iron_sword), new Object[] { "X", "X", "Y", 'X', new ItemStack(KapteynBItems.kapteyn_b_item, 1, 2), 'Y', new ItemStack(KapteynBItems.kapteyn_b_item, 1, 4) });
        GameRegistry.addRecipe(new ItemStack(KapteynBToolsItems.frozen_iron_shovel), new Object[] { "X", "Y", "Y", 'X', new ItemStack(KapteynBItems.kapteyn_b_item, 1, 2), 'Y', new ItemStack(KapteynBItems.kapteyn_b_item, 1, 4) });
    }

    private static void addBlockSmelting()
    {
        GameRegistry.addSmelting(new ItemStack(KapteynBBlocks.kapteyn_b_block, 1, 3), new ItemStack(KapteynBBlocks.kapteyn_b_block, 1, 2), 0.5F);
    }

    private static void addItemSmelting()
    {
        GameRegistry.addSmelting(new ItemStack(KapteynBBlocks.kapteyn_b_block, 1, 4), new ItemStack(KapteynBItems.namerium_crystal, 1, 0), 0.8F);
        GameRegistry.addSmelting(new ItemStack(KapteynBBlocks.kapteyn_b_block, 1, 5), new ItemStack(KapteynBItems.kapteyn_b_item, 1, 0), 0.8F);
        GameRegistry.addSmelting(new ItemStack(KapteynBBlocks.kapteyn_b_block, 1, 6), new ItemStack(KapteynBItems.kapteyn_b_item, 1, 1), 0.8F);
        GameRegistry.addSmelting(new ItemStack(KapteynBBlocks.kapteyn_b_block, 1, 7), new ItemStack(GCItems.basicItem, 1, 4), 0.8F);
        GameRegistry.addSmelting(new ItemStack(KapteynBBlocks.kapteyn_b_block, 1, 8), new ItemStack(GCItems.basicItem, 1, 3), 0.8F);
        GameRegistry.addSmelting(new ItemStack(KapteynBBlocks.kapteyn_b_block, 1, 9), new ItemStack(Items.redstone), 0.8F);
        GameRegistry.addSmelting(new ItemStack(KapteynBBlocks.kapteyn_b_block, 1, 10), new ItemStack(Items.redstone), 0.8F);
    }

    private static void addTier8RocketRecipes()
    {
        HashMap<Integer, ItemStack> input = new HashMap<Integer, ItemStack>();
        input.put(1, new ItemStack(NibiruItems.tier7_rocket_module, 1, 4));//Nose Cone
        input.put(2, new ItemStack(FronosItems.tier8_rocket_module, 1, 2));
        input.put(3, new ItemStack(FronosItems.tier8_rocket_module, 1, 2));
        input.put(4, new ItemStack(FronosItems.tier8_rocket_module, 1, 2));
        input.put(5, new ItemStack(FronosItems.tier8_rocket_module, 1, 2));
        input.put(6, new ItemStack(FronosItems.tier8_rocket_module, 1, 2));
        input.put(7, new ItemStack(FronosItems.tier8_rocket_module, 1, 2));
        input.put(8, new ItemStack(FronosItems.tier8_rocket_module, 1, 2));
        input.put(9, new ItemStack(FronosItems.tier8_rocket_module, 1, 2));
        input.put(10, new ItemStack(FronosItems.tier8_rocket_module, 1, 2));
        input.put(11, new ItemStack(FronosItems.tier8_rocket_module, 1, 2));//Plate
        input.put(12, new ItemStack(FronosItems.tier8_rocket_module, 1, 1));//Booster
        input.put(13, new ItemStack(NibiruItems.tier7_rocket_module, 1, 3));
        input.put(14, new ItemStack(NibiruItems.tier7_rocket_module, 1, 3));
        input.put(15, new ItemStack(FronosItems.tier8_rocket_module, 1, 0));//Engine
        input.put(16, new ItemStack(FronosItems.tier8_rocket_module, 1, 1));
        input.put(17, new ItemStack(NibiruItems.tier7_rocket_module, 1, 3));
        input.put(18, new ItemStack(NibiruItems.tier7_rocket_module, 1, 3));
        input.put(19, null);
        input.put(20, null);
        input.put(21, null);
        Tier8RocketRecipes.addTier8RocketRecipe(new ItemStack(KapteynBItems.tier8_rocket, 1, 0), input);

        HashMap<Integer, ItemStack> input2 = new HashMap<Integer, ItemStack>(input);
        input2.put(19, new ItemStack(Blocks.chest));
        input2.put(20, null);
        input2.put(21, null);
        Tier8RocketRecipes.addTier8RocketRecipe(new ItemStack(KapteynBItems.tier8_rocket, 1, 1), input2);

        input2 = new HashMap<Integer, ItemStack>(input);
        input2.put(19, null);
        input2.put(20, new ItemStack(Blocks.chest));
        input2.put(21, null);
        Tier8RocketRecipes.addTier8RocketRecipe(new ItemStack(KapteynBItems.tier8_rocket, 1, 1), input2);

        input2 = new HashMap<Integer, ItemStack>(input);
        input2.put(19, null);
        input2.put(20, null);
        input2.put(21, new ItemStack(Blocks.chest));
        Tier8RocketRecipes.addTier8RocketRecipe(new ItemStack(KapteynBItems.tier8_rocket, 1, 1), input2);

        input2 = new HashMap<Integer, ItemStack>(input);
        input2.put(19, new ItemStack(Blocks.chest));
        input2.put(20, new ItemStack(Blocks.chest));
        input2.put(21, null);
        Tier8RocketRecipes.addTier8RocketRecipe(new ItemStack(KapteynBItems.tier8_rocket, 1, 2), input2);

        input2 = new HashMap<Integer, ItemStack>(input);
        input2.put(19, new ItemStack(Blocks.chest));
        input2.put(20, null);
        input2.put(21, new ItemStack(Blocks.chest));
        Tier8RocketRecipes.addTier8RocketRecipe(new ItemStack(KapteynBItems.tier8_rocket, 1, 2), input2);

        input2 = new HashMap<Integer, ItemStack>(input);
        input2.put(19, null);
        input2.put(20, new ItemStack(Blocks.chest));
        input2.put(21, new ItemStack(Blocks.chest));
        Tier8RocketRecipes.addTier8RocketRecipe(new ItemStack(KapteynBItems.tier8_rocket, 1, 2), input2);

        input2 = new HashMap<Integer, ItemStack>(input);
        input2.put(19, new ItemStack(Blocks.chest));
        input2.put(20, new ItemStack(Blocks.chest));
        input2.put(21, new ItemStack(Blocks.chest));
        Tier8RocketRecipes.addTier8RocketRecipe(new ItemStack(KapteynBItems.tier8_rocket, 1, 3), input2);
    }
}