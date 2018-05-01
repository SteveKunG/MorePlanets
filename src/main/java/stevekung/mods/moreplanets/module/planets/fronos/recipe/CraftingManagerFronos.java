package stevekung.mods.moreplanets.module.planets.fronos.recipe;

import net.minecraft.item.ItemStack;
import stevekung.mods.moreplanets.module.planets.fronos.items.FronosItems;
import stevekung.mods.moreplanets.utils.helper.RecipeHelper;

public class CraftingManagerFronos
{
    public static void init()
    {
        CraftingManagerFronos.addBlockRecipe();
        CraftingManagerFronos.addItemRecipe();
        CraftingManagerFronos.addBlockSmelting();
        CraftingManagerFronos.addItemSmelting();
    }

    private static void addBlockRecipe()
    {
        //        RecipeHelper.addRecipe(new ItemStack(FronosBlocks.FRONOS_BLOCK, 4, 2), new Object[] { "XX", "XX", 'X', new ItemStack(FronosBlocks.FRONOS_BLOCK, 1, 0) });

        for (int i = 0; i < 8; i++)
        {
            //            RecipeHelper.addRecipe(new ItemStack(FronosBlocks.JELLY_BLOCK, 1, i), new Object[] { "XXX", "XXX", "XXX", 'X', new ItemStack(FronosItems.JELLY, 1, i) });
        }
        for (int i = 0; i < 4; i++)
        {
            //            RecipeHelper.addRecipe(new ItemStack(FronosBlocks.CANDY_CANE_1, 1, i), new Object[] { "XXX", "XXX", "XXX", 'X', new ItemStack(FronosItems.CANDY_CANE, 1, i) });
            //            RecipeHelper.addRecipe(new ItemStack(FronosBlocks.CANDY_CANE_2, 1, i), new Object[] { "XXX", "XXX", "XXX", 'X', new ItemStack(FronosItems.CANDY_CANE, 1, i + 4) });
        }

        //        RecipeHelper.addRecipe(new ItemStack(FronosBlocks.CHALOS_COBBLESTONE_STAIRS, 4), new Object[] { "X  ", "XX ", "XXX", 'X', new ItemStack(FronosBlocks.CHALOS_BLOCK, 1, 1) });
        //        RecipeHelper.addRecipe(new ItemStack(FronosBlocks.CHALOS_DUNGEON_BRICK_STAIRS, 4), new Object[] { "X  ", "XX ", "XXX", 'X', new ItemStack(FronosBlocks.CHALOS_BLOCK, 1, 11) });
        //        RecipeHelper.addRecipe(new ItemStack(FronosBlocks.CHEESE_SPORE_STAIRS, 4), new Object[] { "X  ", "XX ", "XXX", 'X', new ItemStack(FronosBlocks.CHEESE_SPORE_PLANKS) });

        //        RecipeHelper.addShapelessRecipe(new ItemStack(FronosBlocks.FRONOS_BLOCK, 1, 3), new ItemStack(FronosBlocks.FRONOS_BLOCK, 1, 2), Blocks.VINE );
    }

    private static void addItemRecipe()
    {
        for (int i = 0; i < 8; i++)
        {
            //            RecipeHelper.addShapelessRecipe(new ItemStack(FronosItems.JELLY, 9, i), new ItemStack(FronosBlocks.JELLY_BLOCK, 1, i));
        }
        for (int i = 0; i < 4; i++)
        {
            //            RecipeHelper.addShapelessRecipe(new ItemStack(FronosItems.CANDY_CANE, 9, i), new ItemStack(FronosBlocks.CANDY_CANE_1, 1, i));
            //            RecipeHelper.addShapelessRecipe(new ItemStack(FronosItems.CANDY_CANE, 9, i + 4), new ItemStack(FronosBlocks.CANDY_CANE_2, 1, i));
        }

        //        RecipeHelper.addShapelessRecipe(new ItemStack(ChalosItems.CHALOS_ITEM, 9, 1), new ItemStack(FronosBlocks.CHALOS_BLOCK, 1, 10));
        //        RecipeHelper.addShapelessRecipe(new ItemStack(ChalosItems.CHEESE_SLIMEBALL, 9), new ItemStack(FronosBlocks.CHEESE_SLIME_BLOCK));

        //        RecipeHelper.addRecipe(new ItemStack(ChalosItems.DIREMSIUM_HELMET, 1, 0), new Object[] { "XXX", "X X", 'X', new ItemStack(ChalosItems.CHALOS_ITEM, 1, 2) });
        //        RecipeHelper.addRecipe(new ItemStack(ChalosItems.DIREMSIUM_CHESTPLATE, 1, 0), new Object[] { "X X", "XXX", "XXX", 'X', new ItemStack(ChalosItems.CHALOS_ITEM, 1, 2) });
        //        RecipeHelper.addRecipe(new ItemStack(ChalosItems.DIREMSIUM_LEGGINGS, 1, 0), new Object[] { "XXX", "X X", "X X", 'X', new ItemStack(ChalosItems.CHALOS_ITEM, 1, 2) });
        //        RecipeHelper.addRecipe(new ItemStack(ChalosItems.DIREMSIUM_BOOTS, 1, 0), new Object[] { "X X", "X X", 'X', new ItemStack(ChalosItems.CHALOS_ITEM, 1, 2) });
        //        RecipeHelper.addRecipe(new ItemStack(ChalosItems.BREATHABLE_DIREMSIUM_HELMET, 1, 0), new Object[] { "XXX", "XYX", 'Y', new ItemStack(GCItems.oxMask), 'X', new ItemStack(ChalosItems.CHALOS_ITEM, 1, 2) });
        //        RecipeHelper.addRecipe(new ItemStack(ChalosItems.CHEESE_SPORE_DOOR, 3), new Object[] { "XX", "XX", "XX", 'X', FronosBlocks.CHEESE_SPORE_PLANKS });

        //        RecipeHelper.addRecipe(new ItemStack(ChalosItems.DIREMSIUM_HOE), new Object[] { "XX", " Y", " Y", 'X', new ItemStack(ChalosItems.CHALOS_ITEM, 1, 2), 'Y', new ItemStack(MarsItems.marsItemBasic, 1, 1) });
        //        RecipeHelper.addRecipe(new ItemStack(ChalosItems.DIREMSIUM_AXE), new Object[] { "XX", "XY", " Y", 'X', new ItemStack(ChalosItems.CHALOS_ITEM, 1, 2), 'Y', new ItemStack(MarsItems.marsItemBasic, 1, 1) });
        //        RecipeHelper.addRecipe(new ItemStack(ChalosItems.DIREMSIUM_PICKAXE), new Object[] { "XXX", " Y ", " Y ", 'X', new ItemStack(ChalosItems.CHALOS_ITEM, 1, 2), 'Y', new ItemStack(MarsItems.marsItemBasic, 1, 1) });
        //        RecipeHelper.addRecipe(new ItemStack(ChalosItems.DIREMSIUM_SWORD), new Object[] { "X", "X", "Y", 'X', new ItemStack(ChalosItems.CHALOS_ITEM, 1, 2), 'Y', new ItemStack(MarsItems.marsItemBasic, 1, 1) });
        //        RecipeHelper.addRecipe(new ItemStack(ChalosItems.DIREMSIUM_SHOVEL), new Object[] { "X", "Y", "Y", 'X', new ItemStack(ChalosItems.CHALOS_ITEM, 1, 2), 'Y', new ItemStack(MarsItems.marsItemBasic, 1, 1) });
        //        RecipeHelper.addRecipe(new ItemStack(ChalosItems.CHEESE_SPORE_WOOD_HOE), new Object[] { "XX", " Y", " Y", 'X', FronosBlocks.CHEESE_SPORE_PLANKS, 'Y', Items.stick });
        //        RecipeHelper.addRecipe(new ItemStack(ChalosItems.CHEESE_SPORE_WOOD_AXE), new Object[] { "XX", "XY", " Y", 'X', FronosBlocks.CHEESE_SPORE_PLANKS, 'Y', Items.stick });
        //        RecipeHelper.addRecipe(new ItemStack(ChalosItems.CHEESE_SPORE_WOOD_PICKAXE), new Object[] { "XXX", " Y ", " Y ", 'X', FronosBlocks.CHEESE_SPORE_PLANKS, 'Y', Items.stick });
        //        RecipeHelper.addRecipe(new ItemStack(ChalosItems.CHEESE_SPORE_WOOD_SWORD), new Object[] { "X", "X", "Y", 'X', FronosBlocks.CHEESE_SPORE_PLANKS, 'Y', Items.stick });
        //        RecipeHelper.addRecipe(new ItemStack(ChalosItems.CHEESE_SPORE_WOOD_SHOVEL), new Object[] { "X", "Y", "Y", 'X', FronosBlocks.CHEESE_SPORE_PLANKS, 'Y', Items.stick });
    }

    private static void addBlockSmelting()
    {
        //        RecipeHelper.addSmeltingRecipe(new ItemStack(FronosBlocks.FRONOS_BLOCK, 1, 1), new ItemStack(FronosBlocks.FRONOS_BLOCK, 1, 0), 0.1F);
        //        RecipeHelper.addSmeltingRecipe(new ItemStack(FronosBlocks.FRONOS_BLOCK, 1, 2), new ItemStack(FronosBlocks.FRONOS_BLOCK, 1, 4), 0.1F);
    }

    private static void addItemSmelting()
    {
        //        RecipeHelper.addSmeltingRecipe(new ItemStack(FronosBlocks.FRONOS_ORE, 1, 0), new ItemStack(Items.IRON_INGOT), 0.7F);
        //        RecipeHelper.addSmeltingRecipe(new ItemStack(FronosBlocks.FRONOS_ORE, 1, 1), new ItemStack(Items.GOLD_INGOT), 1.0F);
        //        RecipeHelper.addSmeltingRecipe(new ItemStack(FronosBlocks.FRONOS_ORE, 1, 2), new ItemStack(GCItems.basicItem, 1, 4), 0.7F);
        //        RecipeHelper.addSmeltingRecipe(new ItemStack(FronosBlocks.FRONOS_ORE, 1, 3), new ItemStack(GCItems.basicItem, 1, 3), 0.7F);
        //        RecipeHelper.addSmeltingRecipe(new ItemStack(FronosBlocks.FRONOS_ORE, 1, 4), new ItemStack(GCItems.basicItem, 1, 5), 0.7F);
        //        RecipeHelper.addSmeltingRecipe(new ItemStack(FronosBlocks.FRONOS_ORE, 1, 5), new ItemStack(VenusItems.basicItem, 1, 1), 0.8F);
        //        RecipeHelper.addSmeltingRecipe(new ItemStack(FronosBlocks.FRONOS_ORE, 1, 6), new ItemStack(Items.COAL), 0.1F);
        //        RecipeHelper.addSmeltingRecipe(new ItemStack(FronosBlocks.FRONOS_ORE, 1, 7), new ItemStack(Items.DYE, 1, 4), 0.2F);
        //        RecipeHelper.addSmeltingRecipe(new ItemStack(FronosBlocks.FRONOS_ORE, 1, 8), new ItemStack(Items.DIAMOND), 1.0F);
        //        RecipeHelper.addSmeltingRecipe(new ItemStack(FronosBlocks.FRONOS_ORE, 1, 9), new ItemStack(Items.EMERALD), 1.0F);
        //        RecipeHelper.addSmeltingRecipe(new ItemStack(FronosBlocks.FRONOS_ORE, 1, 10), new ItemStack(Items.REDSTONE), 0.7F);
        //        RecipeHelper.addSmeltingRecipe(new ItemStack(FronosBlocks.FRONOS_ORE, 1, 11), new ItemStack(GCItems.basicItem, 1, 2), 0.7F);
        //        RecipeHelper.addSmeltingRecipe(new ItemStack(FronosBlocks.FRONOS_ORE, 1, 12), new ItemStack(Items.QUARTZ), 0.2F);
        //        RecipeHelper.addSmeltingRecipe(new ItemStack(FronosBlocks.FRONOS_ORE, 1, 13), new ItemStack(FronosItems.EXTRAILONITE_INGOT), 1.0F);
        RecipeHelper.addSmeltingRecipe(new ItemStack(FronosItems.MARSHMALLOW), new ItemStack(FronosItems.COOKED_MARSHMALLOW), 0.25F);
    }
}