package stevekung.mods.moreplanets.module.planets.chalos.recipe;

import java.util.HashMap;

import com.google.common.collect.Maps;

import micdoodle8.mods.galacticraft.core.GCItems;
import micdoodle8.mods.galacticraft.planets.asteroids.items.AsteroidsItems;
import micdoodle8.mods.galacticraft.planets.mars.items.MarsItems;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import stevekung.mods.moreplanets.module.planets.chalos.blocks.ChalosBlocks;
import stevekung.mods.moreplanets.module.planets.chalos.items.ChalosItems;
import stevekung.mods.moreplanets.module.planets.diona.items.DionaItems;
import stevekung.mods.moreplanets.util.helper.RecipeHelper;

public class CraftingManagerChalos
{
    public static void init()
    {
        CraftingManagerChalos.addBlockRecipe();
        CraftingManagerChalos.addItemRecipe();
        CraftingManagerChalos.addBlockSmelting();
        CraftingManagerChalos.addItemSmelting();
        CraftingManagerChalos.addRocketRecipe();
    }

    private static void addBlockRecipe()
    {
        RecipeHelper.addRecipe(new ItemStack(ChalosBlocks.CHALOS_BLOCK, 1, 9), new Object[] { "XXX", "XXX", "XXX", 'X', new ItemStack(ChalosItems.CHALOS_ITEM, 1, 0) });
        RecipeHelper.addRecipe(new ItemStack(ChalosBlocks.CHALOS_BLOCK, 1, 10), new Object[] { "XXX", "XXX", "XXX", 'X', new ItemStack(ChalosItems.CHALOS_ITEM, 1, 1) });
        RecipeHelper.addRecipe(new ItemStack(ChalosBlocks.CHEESE_OF_MILK_CAKE), new Object[] { "MMM", "CCC", "CCC", 'C', new ItemStack(ChalosItems.CHEESE_FOOD), 'M', ChalosItems.CHEESE_OF_MILK_FLUID_BUCKET });
        RecipeHelper.addRecipe(new ItemStack(ChalosBlocks.CHALOS_CRAFTING_TABLE), new Object[] { "XX", "XX", 'X', ChalosBlocks.CHEESE_SPORE_PLANKS });
        RecipeHelper.addRecipe(new ItemStack(ChalosBlocks.CHEESE_SLIME_BLOCK), new Object[] { "XXX", "XXX", "XXX", 'X', ChalosItems.CHEESE_SLIMEBALL });
        RecipeHelper.addRecipe(new ItemStack(ChalosBlocks.CHEESE_DIRT, 4, 1), new Object[] { "DG", "GD", 'D', new ItemStack(ChalosBlocks.CHEESE_DIRT, 1, 0), 'G', Blocks.GRAVEL });
        RecipeHelper.addRecipe(new ItemStack(ChalosBlocks.CHEESE_SPORE_CHEST), new Object[] { "XXX", "X X", "XXX", 'X', ChalosBlocks.CHEESE_SPORE_PLANKS });
        RecipeHelper.addRecipe(new ItemStack(ChalosBlocks.CHEESE_SPORE_FENCE, 3), new Object[] { "XSX", "XSX", 'X', ChalosBlocks.CHEESE_SPORE_PLANKS, 'S', Items.STICK });
        RecipeHelper.addRecipe(new ItemStack(ChalosBlocks.CHEESE_SPORE_FENCE_GATE), new Object[] { "SXS", "SXS", 'X', ChalosBlocks.CHEESE_SPORE_PLANKS, 'S', Items.STICK });

        RecipeHelper.addRecipe(new ItemStack(ChalosBlocks.CHALOS_COBBLESTONE_STAIRS, 4), new Object[] { "X  ", "XX ", "XXX", 'X', new ItemStack(ChalosBlocks.CHALOS_BLOCK, 1, 1) });
        RecipeHelper.addRecipe(new ItemStack(ChalosBlocks.CHALOS_DUNGEON_BRICK_STAIRS, 4), new Object[] { "X  ", "XX ", "XXX", 'X', new ItemStack(ChalosBlocks.CHALOS_BLOCK, 1, 11) });
        RecipeHelper.addRecipe(new ItemStack(ChalosBlocks.CHEESE_SPORE_STAIRS, 4), new Object[] { "X  ", "XX ", "XXX", 'X', new ItemStack(ChalosBlocks.CHEESE_SPORE_PLANKS) });

        RecipeHelper.addShapelessRecipe(new ItemStack(ChalosBlocks.CHEESE_SPORE_PLANKS, 4), new ItemStack(ChalosBlocks.CHEESE_SPORE_STEM, 1) );
    }

    private static void addItemRecipe()
    {
        RecipeHelper.addShapelessRecipe(new ItemStack(ChalosItems.CHALOS_ITEM, 9, 0), new ItemStack(ChalosBlocks.CHALOS_BLOCK, 1, 9));
        RecipeHelper.addShapelessRecipe(new ItemStack(ChalosItems.CHALOS_ITEM, 9, 1), new ItemStack(ChalosBlocks.CHALOS_BLOCK, 1, 10));
        RecipeHelper.addShapelessRecipe(new ItemStack(ChalosItems.CHEESE_SLIMEBALL, 9), new ItemStack(ChalosBlocks.CHEESE_SLIME_BLOCK));

        RecipeHelper.addRecipe(new ItemStack(ChalosItems.DIREMSIUM_HELMET, 1, 0), new Object[] { "XXX", "X X", 'X', new ItemStack(ChalosItems.CHALOS_ITEM, 1, 2) });
        RecipeHelper.addRecipe(new ItemStack(ChalosItems.DIREMSIUM_CHESTPLATE, 1, 0), new Object[] { "X X", "XXX", "XXX", 'X', new ItemStack(ChalosItems.CHALOS_ITEM, 1, 2) });
        RecipeHelper.addRecipe(new ItemStack(ChalosItems.DIREMSIUM_LEGGINGS, 1, 0), new Object[] { "XXX", "X X", "X X", 'X', new ItemStack(ChalosItems.CHALOS_ITEM, 1, 2) });
        RecipeHelper.addRecipe(new ItemStack(ChalosItems.DIREMSIUM_BOOTS, 1, 0), new Object[] { "X X", "X X", 'X', new ItemStack(ChalosItems.CHALOS_ITEM, 1, 2) });
        RecipeHelper.addRecipe(new ItemStack(ChalosItems.BREATHABLE_DIREMSIUM_HELMET, 1, 0), new Object[] { "XXX", "XYX", 'Y', new ItemStack(GCItems.oxMask), 'X', new ItemStack(ChalosItems.CHALOS_ITEM, 1, 2) });
        RecipeHelper.addRecipe(new ItemStack(ChalosItems.CHEESE_SPORE_DOOR, 3), new Object[] { "XX", "XX", "XX", 'X', ChalosBlocks.CHEESE_SPORE_PLANKS });

        RecipeHelper.addRecipe(new ItemStack(ChalosItems.DIREMSIUM_HOE), new Object[] { "XX", " Y", " Y", 'X', new ItemStack(ChalosItems.CHALOS_ITEM, 1, 2), 'Y', new ItemStack(MarsItems.marsItemBasic, 1, 1) });
        RecipeHelper.addRecipe(new ItemStack(ChalosItems.DIREMSIUM_AXE), new Object[] { "XX", "XY", " Y", 'X', new ItemStack(ChalosItems.CHALOS_ITEM, 1, 2), 'Y', new ItemStack(MarsItems.marsItemBasic, 1, 1) });
        RecipeHelper.addRecipe(new ItemStack(ChalosItems.DIREMSIUM_PICKAXE), new Object[] { "XXX", " Y ", " Y ", 'X', new ItemStack(ChalosItems.CHALOS_ITEM, 1, 2), 'Y', new ItemStack(MarsItems.marsItemBasic, 1, 1) });
        RecipeHelper.addRecipe(new ItemStack(ChalosItems.DIREMSIUM_SWORD), new Object[] { "X", "X", "Y", 'X', new ItemStack(ChalosItems.CHALOS_ITEM, 1, 2), 'Y', new ItemStack(MarsItems.marsItemBasic, 1, 1) });
        RecipeHelper.addRecipe(new ItemStack(ChalosItems.DIREMSIUM_SHOVEL), new Object[] { "X", "Y", "Y", 'X', new ItemStack(ChalosItems.CHALOS_ITEM, 1, 2), 'Y', new ItemStack(MarsItems.marsItemBasic, 1, 1) });
        RecipeHelper.addRecipe(new ItemStack(ChalosItems.CHEESE_SPORE_WOOD_HOE), new Object[] { "XX", " Y", " Y", 'X', ChalosBlocks.CHEESE_SPORE_PLANKS, 'Y', Items.STICK });
        RecipeHelper.addRecipe(new ItemStack(ChalosItems.CHEESE_SPORE_WOOD_AXE), new Object[] { "XX", "XY", " Y", 'X', ChalosBlocks.CHEESE_SPORE_PLANKS, 'Y', Items.STICK });
        RecipeHelper.addRecipe(new ItemStack(ChalosItems.CHEESE_SPORE_WOOD_PICKAXE), new Object[] { "XXX", " Y ", " Y ", 'X', ChalosBlocks.CHEESE_SPORE_PLANKS, 'Y', Items.STICK });
        RecipeHelper.addRecipe(new ItemStack(ChalosItems.CHEESE_SPORE_WOOD_SWORD), new Object[] { "X", "X", "Y", 'X', ChalosBlocks.CHEESE_SPORE_PLANKS, 'Y', Items.STICK });
        RecipeHelper.addRecipe(new ItemStack(ChalosItems.CHEESE_SPORE_WOOD_SHOVEL), new Object[] { "X", "Y", "Y", 'X', ChalosBlocks.CHEESE_SPORE_PLANKS, 'Y', Items.STICK });
    }

    private static void addBlockSmelting()
    {
        RecipeHelper.addSmeltingRecipe(new ItemStack(ChalosBlocks.CHALOS_BLOCK, 1, 1), new ItemStack(ChalosBlocks.CHALOS_BLOCK, 1, 0), 0.1F);
    }

    private static void addItemSmelting()
    {
        RecipeHelper.addSmeltingRecipe(new ItemStack(ChalosBlocks.CHALOS_BLOCK, 1, 2), new ItemStack(ChalosItems.CHALOS_ITEM, 1, 0), 0.8F);
        RecipeHelper.addSmeltingRecipe(new ItemStack(ChalosBlocks.CHALOS_BLOCK, 1, 3), new ItemStack(ChalosItems.CHALOS_ITEM, 1, 1), 0.8F);
        RecipeHelper.addSmeltingRecipe(new ItemStack(ChalosBlocks.CHALOS_BLOCK, 1, 4), new ItemStack(ChalosItems.CHEESE_FOOD, 2), 0.8F);
        RecipeHelper.addSmeltingRecipe(new ItemStack(ChalosBlocks.CHALOS_BLOCK, 1, 5), new ItemStack(Items.IRON_INGOT), 0.8F);
        RecipeHelper.addSmeltingRecipe(new ItemStack(ChalosBlocks.CHALOS_BLOCK, 1, 6), new ItemStack(GCItems.basicItem, 1, 4), 0.8F);
        RecipeHelper.addSmeltingRecipe(new ItemStack(ChalosBlocks.CHALOS_BLOCK, 1, 7), new ItemStack(GCItems.basicItem, 1, 3), 0.8F);
        RecipeHelper.addSmeltingRecipe(new ItemStack(ChalosBlocks.CHALOS_BLOCK, 1, 8), new ItemStack(GCItems.basicItem, 1, 5), 0.8F);
        RecipeHelper.addSmeltingRecipe(new ItemStack(ChalosItems.CHEESE_FOOD, 1, 1), new ItemStack(ChalosItems.CHEESE_FOOD, 1, 2), 0.35F);
        RecipeHelper.addSmeltingRecipe(new ItemStack(ChalosBlocks.CHEESE_SPORE_STEM), new ItemStack(Items.COAL, 1, 1), 0.15F);
    }

    private static void addRocketRecipe()
    {
        HashMap<Integer, ItemStack> input = Maps.newHashMap();
        input.put(1, new ItemStack(DionaItems.TIER_4_ROCKET_PART, 1, 2));
        input.put(2, new ItemStack(ChalosItems.TIER_5_ROCKET_PART, 1, 0));
        input.put(3, new ItemStack(ChalosItems.TIER_5_ROCKET_PART, 1, 0));
        input.put(4, new ItemStack(ChalosItems.TIER_5_ROCKET_PART, 1, 0));
        input.put(5, new ItemStack(ChalosItems.TIER_5_ROCKET_PART, 1, 0));
        input.put(6, new ItemStack(ChalosItems.TIER_5_ROCKET_PART, 1, 0));
        input.put(7, new ItemStack(ChalosItems.TIER_5_ROCKET_PART, 1, 0));
        input.put(8, new ItemStack(ChalosItems.TIER_5_ROCKET_PART, 1, 0));
        input.put(9, new ItemStack(ChalosItems.TIER_5_ROCKET_PART, 1, 0));
        input.put(10, new ItemStack(ChalosItems.TIER_5_ROCKET_PART, 1, 0));
        input.put(11, new ItemStack(ChalosItems.TIER_5_ROCKET_PART, 1, 0));
        input.put(12, new ItemStack(DionaItems.TIER_4_ROCKET_PART, 1, 3));
        input.put(13, new ItemStack(AsteroidsItems.basicItem, 1, 2));
        input.put(14, new ItemStack(AsteroidsItems.basicItem, 1, 2));
        input.put(15, new ItemStack(DionaItems.TIER_4_ROCKET_PART, 1, 1));
        input.put(16, new ItemStack(DionaItems.TIER_4_ROCKET_PART, 1, 3));
        input.put(17, new ItemStack(AsteroidsItems.basicItem, 1, 2));
        input.put(18, new ItemStack(AsteroidsItems.basicItem, 1, 2));
        input.put(19, null);
        input.put(20, null);
        input.put(21, null);
        Tier5RocketRecipes.addRocketRecipe(new ItemStack(ChalosItems.TIER_5_ROCKET, 1, 0), input);

        HashMap<Integer, ItemStack> input2 = Maps.newHashMap(input);
        input2.put(19, new ItemStack(Blocks.CHEST));
        input2.put(20, null);
        input2.put(21, null);
        Tier5RocketRecipes.addRocketRecipe(new ItemStack(ChalosItems.TIER_5_ROCKET, 1, 1), input2);

        input2 = Maps.newHashMap(input);
        input2.put(19, null);
        input2.put(20, new ItemStack(Blocks.CHEST));
        input2.put(21, null);
        Tier5RocketRecipes.addRocketRecipe(new ItemStack(ChalosItems.TIER_5_ROCKET, 1, 1), input2);

        input2 = Maps.newHashMap(input);
        input2.put(19, null);
        input2.put(20, null);
        input2.put(21, new ItemStack(Blocks.CHEST));
        Tier5RocketRecipes.addRocketRecipe(new ItemStack(ChalosItems.TIER_5_ROCKET, 1, 1), input2);

        input2 = Maps.newHashMap(input);
        input2.put(19, new ItemStack(Blocks.CHEST));
        input2.put(20, new ItemStack(Blocks.CHEST));
        input2.put(21, null);
        Tier5RocketRecipes.addRocketRecipe(new ItemStack(ChalosItems.TIER_5_ROCKET, 1, 2), input2);

        input2 = Maps.newHashMap(input);
        input2.put(19, new ItemStack(Blocks.CHEST));
        input2.put(20, null);
        input2.put(21, new ItemStack(Blocks.CHEST));
        Tier5RocketRecipes.addRocketRecipe(new ItemStack(ChalosItems.TIER_5_ROCKET, 1, 2), input2);

        input2 = Maps.newHashMap(input);
        input2.put(19, null);
        input2.put(20, new ItemStack(Blocks.CHEST));
        input2.put(21, new ItemStack(Blocks.CHEST));
        Tier5RocketRecipes.addRocketRecipe(new ItemStack(ChalosItems.TIER_5_ROCKET, 1, 2), input2);

        input2 = Maps.newHashMap(input);
        input2.put(19, new ItemStack(Blocks.CHEST));
        input2.put(20, new ItemStack(Blocks.CHEST));
        input2.put(21, new ItemStack(Blocks.CHEST));
        Tier5RocketRecipes.addRocketRecipe(new ItemStack(ChalosItems.TIER_5_ROCKET, 1, 3), input2);
    }
}