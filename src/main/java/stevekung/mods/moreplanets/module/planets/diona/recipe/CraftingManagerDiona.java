package stevekung.mods.moreplanets.module.planets.diona.recipe;

import net.minecraft.item.ItemStack;
import stevekung.mods.moreplanets.module.planets.diona.blocks.DionaBlocks;
import stevekung.mods.moreplanets.module.planets.diona.items.DionaItems;
import stevekung.mods.moreplanets.utils.helper.RecipeHelper;

public class CraftingManagerDiona
{
    public static void init()
    {
        CraftingManagerDiona.addBlockRecipe();
        CraftingManagerDiona.addItemRecipe();
        CraftingManagerDiona.addBlockSmelting();
        CraftingManagerDiona.addItemSmelting();
    }

    private static void addBlockRecipe()
    {
        //        RecipeHelper.addRecipe(new ItemStack(DionaBlocks.DIONA_BLOCK, 1, 9), new Object[] { "SSS", "SSS", "SSS", 'S', new ItemStack(DionaItems.DIONA_ITEM, 1, 1) });
        //        RecipeHelper.addRecipe(new ItemStack(DionaBlocks.DIONA_BLOCK, 1, 10), new Object[] { "III", "III", "III", 'I', new ItemStack(DionaItems.DIONA_ITEM, 1, 0) });
        //        RecipeHelper.addRecipe(new ItemStack(DionaBlocks.GLOWING_IRON_BLOCK), new Object[] { "SSS", "SSS", "SSS", 'S', new ItemStack(DionaItems.DIONA_ITEM, 1, 6) });
        RecipeHelper.addRecipe(new ItemStack(DionaBlocks.INFECTED_CRYSTALLIZED_SLIME_BLOCK), new Object[] { "SSS", "SSS", "SSS", 'S', DionaItems.INFECTED_CRYSTALLIZED_SLIMEBALL });
        //        RecipeHelper.addRecipe(new ItemStack(DionaBlocks.INFECTED_CRYSTALLIZED_SEGMENT), new Object[] { "BSB", "BSB", "BSB", 'S', new ItemStack(DionaItems.DIONA_ITEM, 1, 4), 'B', DionaItems.INFECTED_CRYSTALLIZED_SLIMEBALL });
        //        RecipeHelper.addRecipe(new ItemStack(DionaBlocks.DARK_ENERGY_GENERATOR), new Object[] { "HPH", "ACA", "WFW", 'H', new ItemStack(DionaItems.TIER_4_ROCKET_PART, 1, 0), 'P', GCItems.flagPole, 'A', new ItemStack(DionaItems.DIONA_ITEM, 1, 5), 'C', new ItemStack(NibiruItems.NIBIRU_ITEM, 1, 1), 'F', new ItemStack(GCItems.basicItem, 1, 14), 'W', new ItemStack(GCBlocks.aluminumWire, 1, 1) });

        //        RecipeHelper.addRecipe(new ItemStack(DionaBlocks.DIONA_COBBLESTONE_STAIRS, 4), new Object[] { "X  ", "XX ", "XXX", 'X', new ItemStack(DionaBlocks.DIONA_BLOCK, 1, 2) });
        //        RecipeHelper.addRecipe(new ItemStack(DionaBlocks.DIONA_DUNGEON_BRICK_STAIRS, 4), new Object[] { "X  ", "XX ", "XXX", 'X', new ItemStack(DionaBlocks.DIONA_BLOCK, 1, 11) });
    }

    private static void addItemRecipe()
    {
        //        RecipeHelper.addRecipe(new ItemStack(DionaBlocks.INFECTED_CRYSTALLIZED_TORCH, 4), new Object[] { "I", "S", 'I', new ItemStack(DionaItems.DIONA_ITEM, 1, 4), 'S', Items.STICK });
        //        RecipeHelper.addShapelessRecipe(new ItemStack(DionaItems.DIONA_ITEM, 9, 0), new ItemStack(DionaBlocks.DIONA_BLOCK, 1, 10));
        //        RecipeHelper.addShapelessRecipe(new ItemStack(DionaItems.DIONA_ITEM, 9, 1), new ItemStack(DionaBlocks.DIONA_BLOCK, 1, 9));
        //        RecipeHelper.addShapelessRecipe(new ItemStack(DionaItems.DIONA_ITEM, 9, 6), DionaBlocks.GLOWING_IRON_BLOCK);
        RecipeHelper.addShapelessRecipe(new ItemStack(DionaItems.INFECTED_CRYSTALLIZED_SLIMEBALL, 9), DionaBlocks.INFECTED_CRYSTALLIZED_SLIME_BLOCK);

        {
            //            RecipeHelper.addRecipe(new ItemStack(DionaItems.TIER_4_ROCKET_PART, 1, 1), new Object[] { "VY ", "XWX", "XZX", 'V', Blocks.STONE_BUTTON, 'W', new ItemStack(GCItems.canister, 1, OreDictionary.WILDCARD_VALUE), 'X', new ItemStack(DionaItems.TIER_4_ROCKET_PART, 1, 0), 'Y', Items.FLINT_AND_STEEL, 'Z', GCItems.oxygenVent });
            //            RecipeHelper.addRecipe(new ItemStack(DionaItems.TIER_4_ROCKET_PART, 1, 2), new Object[] { " Y ", " X ", "X X", 'X', new ItemStack(DionaItems.TIER_4_ROCKET_PART, 1, 0), 'Y', Blocks.REDSTONE_TORCH });
            //            RecipeHelper.addRecipe(new ItemStack(DionaItems.TIER_4_ROCKET_PART, 1, 3), new Object[] { "ZYZ", "ZWZ", "XVX", 'V', GCItems.oxygenVent, 'W', new ItemStack(GCItems.fuelCanister, 1, 1), 'X', new ItemStack(DionaItems.TIER_4_ROCKET_PART, 1, 0), 'Y', new ItemStack(MarsBlocks.marsBlock, 1, 8), 'Z', new ItemStack(MarsItems.marsItemBasic, 1, 5) });
        }

        //        RecipeHelper.addRecipe(new ItemStack(DionaItems.INFECTED_CRYSTALLIZED_BOMB, 4), new Object[] { "III", "IGI", "III", 'I', new ItemStack(DionaItems.DIONA_ITEM, 1, 4), 'G', Items.GUNPOWDER });
        //        RecipeHelper.addRecipe(new ItemStack(DionaItems.INFECTED_CRYSTALLIZED_ARROW, 6), new Object[] { "X", "S", "Y", 'Y', Items.FEATHER, 'X', new ItemStack(DionaItems.DIONA_ITEM, 1, 4), 'S', new ItemStack(MarsItems.marsItemBasic, 1, 1) });
        //
        //        RecipeHelper.addRecipe(new ItemStack(DionaItems.ILLENIUM_HELMET, 1, 0), new Object[] { "XXX", "X X", 'X', new ItemStack(DionaItems.DIONA_ITEM, 1, 2) });
        //        RecipeHelper.addRecipe(new ItemStack(DionaItems.ILLENIUM_CHESTPLATE, 1, 0), new Object[] { "X X", "XXX", "XXX", 'X', new ItemStack(DionaItems.DIONA_ITEM, 1, 2) });
        //        RecipeHelper.addRecipe(new ItemStack(DionaItems.ILLENIUM_LEGGINGS, 1, 0), new Object[] { "XXX", "X X", "X X", 'X', new ItemStack(DionaItems.DIONA_ITEM, 1, 2) });
        //        RecipeHelper.addRecipe(new ItemStack(DionaItems.ILLENIUM_BOOTS, 1, 0), new Object[] { "X X", "X X", 'X', new ItemStack(DionaItems.DIONA_ITEM, 1, 2) });
        //        RecipeHelper.addRecipe(new ItemStack(DionaItems.BREATHABLE_ILLENIUM_HELMET, 1, 0), new Object[] { "XXX", "XYX", 'Y', new ItemStack(GCItems.oxMask), 'X', new ItemStack(DionaItems.DIONA_ITEM, 1, 2) });
        //
        //        RecipeHelper.addRecipe(new ItemStack(DionaItems.ILLENIUM_HOE), new Object[] { "XX", " Y", " Y", 'X', new ItemStack(DionaItems.DIONA_ITEM, 1, 2), 'Y', new ItemStack(MarsItems.marsItemBasic, 1, 1) });
        //        RecipeHelper.addRecipe(new ItemStack(DionaItems.ILLENIUM_AXE), new Object[] { "XX", "XY", " Y", 'X', new ItemStack(DionaItems.DIONA_ITEM, 1, 2), 'Y', new ItemStack(MarsItems.marsItemBasic, 1, 1) });
        //        RecipeHelper.addRecipe(new ItemStack(DionaItems.ILLENIUM_PICKAXE), new Object[] { "XXX", " Y ", " Y ", 'X', new ItemStack(DionaItems.DIONA_ITEM, 1, 2), 'Y', new ItemStack(MarsItems.marsItemBasic, 1, 1) });
        //        RecipeHelper.addRecipe(new ItemStack(DionaItems.ILLENIUM_SWORD), new Object[] { "X", "X", "Y", 'X', new ItemStack(DionaItems.DIONA_ITEM, 1, 2), 'Y', new ItemStack(MarsItems.marsItemBasic, 1, 1) });
        //        RecipeHelper.addRecipe(new ItemStack(DionaItems.ILLENIUM_SHOVEL), new Object[] { "X", "Y", "Y", 'X', new ItemStack(DionaItems.DIONA_ITEM, 1, 2), 'Y', new ItemStack(MarsItems.marsItemBasic, 1, 1) });
    }

    private static void addBlockSmelting()
    {
        //        RecipeHelper.addSmeltingRecipe(new ItemStack(DionaBlocks.DIONA_BLOCK, 1, 3), new ItemStack(DionaBlocks.DIONA_BLOCK, 1, 2), 0.1F);
    }

    private static void addItemSmelting()
    {
        //        RecipeHelper.addSmeltingRecipe(new ItemStack(DionaBlocks.DIONA_BLOCK, 1, 4), new ItemStack(DionaItems.DIONA_ITEM, 1, 1), 0.8F);
        //        RecipeHelper.addSmeltingRecipe(new ItemStack(DionaBlocks.DIONA_BLOCK, 1, 5), new ItemStack(DionaItems.DIONA_ITEM, 1, 0), 0.8F);
        //        RecipeHelper.addSmeltingRecipe(new ItemStack(DionaBlocks.DIONA_BLOCK, 1, 6), new ItemStack(GCItems.basicItem, 1, 4), 0.8F);
        //        RecipeHelper.addSmeltingRecipe(new ItemStack(DionaBlocks.DIONA_BLOCK, 1, 7), new ItemStack(GCItems.basicItem, 1, 3), 0.8F);
        //        RecipeHelper.addSmeltingRecipe(new ItemStack(DionaBlocks.DIONA_BLOCK, 1, 8), new ItemStack(GCItems.basicItem, 1, 5), 0.8F);
    }
}