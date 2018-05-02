package stevekung.mods.moreplanets.module.planets.fronos.recipe;

import micdoodle8.mods.galacticraft.core.GCItems;
import micdoodle8.mods.galacticraft.planets.venus.VenusItems;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import stevekung.mods.moreplanets.module.planets.fronos.blocks.FronosBlocks;
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
        RecipeHelper.addRecipe(new ItemStack(FronosBlocks.FRONOS_STONE_BRICKS, 4), new Object[] { "XX", "XX", 'X', FronosBlocks.FRONOS_STONE });

        RecipeHelper.addRecipe(new ItemStack(FronosBlocks.GRAPE_JELLY_BLOCK), "jelly_block", new Object[] { "XXX", "XXX", "XXX", 'X', FronosItems.GRAPE_JELLY });
        RecipeHelper.addRecipe(new ItemStack(FronosBlocks.RASPBERRY_JELLY_BLOCK), "jelly_block", new Object[] { "XXX", "XXX", "XXX", 'X', FronosItems.RASPBERRY_JELLY });
        RecipeHelper.addRecipe(new ItemStack(FronosBlocks.STRAWBERRY_JELLY_BLOCK), "jelly_block", new Object[] { "XXX", "XXX", "XXX", 'X', FronosItems.STRAWBERRY_JELLY });
        RecipeHelper.addRecipe(new ItemStack(FronosBlocks.BERRY_JELLY_BLOCK), "jelly_block", new Object[] { "XXX", "XXX", "XXX", 'X', FronosItems.BERRY_JELLY });
        RecipeHelper.addRecipe(new ItemStack(FronosBlocks.LIME_JELLY_BLOCK), "jelly_block", new Object[] { "XXX", "XXX", "XXX", 'X', FronosItems.LIME_JELLY });
        RecipeHelper.addRecipe(new ItemStack(FronosBlocks.ORANGE_JELLY_BLOCK), "jelly_block", new Object[] { "XXX", "XXX", "XXX", 'X', FronosItems.ORANGE_JELLY });
        RecipeHelper.addRecipe(new ItemStack(FronosBlocks.GREEN_JELLY_BLOCK), "jelly_block", new Object[] { "XXX", "XXX", "XXX", 'X', FronosItems.GREEN_JELLY });
        RecipeHelper.addRecipe(new ItemStack(FronosBlocks.LEMON_JELLY_BLOCK), "jelly_block", new Object[] { "XXX", "XXX", "XXX", 'X', FronosItems.LEMON_JELLY });

        RecipeHelper.addRecipe(new ItemStack(FronosBlocks.RED_CANDY_CANE), "candy_cane_block", new Object[] { "XXX", "XXX", "XXX", 'X', FronosItems.RED_CANDY_CANE_STICK });
        RecipeHelper.addRecipe(new ItemStack(FronosBlocks.GREEN_CANDY_CANE), "candy_cane_block", new Object[] { "XXX", "XXX", "XXX", 'X', FronosItems.GREEN_CANDY_CANE_STICK });
        RecipeHelper.addRecipe(new ItemStack(FronosBlocks.BLUE_CANDY_CANE), "candy_cane_block", new Object[] { "XXX", "XXX", "XXX", 'X', FronosItems.BLUE_CANDY_CANE_STICK });
        RecipeHelper.addRecipe(new ItemStack(FronosBlocks.ORANGE_CANDY_CANE), "candy_cane_block", new Object[] { "XXX", "XXX", "XXX", 'X', FronosItems.ORANGE_CANDY_CANE_STICK });
        RecipeHelper.addRecipe(new ItemStack(FronosBlocks.PINK_CANDY_CANE), "candy_cane_block", new Object[] { "XXX", "XXX", "XXX", 'X', FronosItems.PINK_CANDY_CANE_STICK });
        RecipeHelper.addRecipe(new ItemStack(FronosBlocks.YELLOW_CANDY_CANE), "candy_cane_block", new Object[] { "XXX", "XXX", "XXX", 'X', FronosItems.YELLOW_CANDY_CANE_STICK });
        RecipeHelper.addRecipe(new ItemStack(FronosBlocks.PURPLE_CANDY_CANE), "candy_cane_block", new Object[] { "XXX", "XXX", "XXX", 'X', FronosItems.PURPLE_CANDY_CANE_STICK });
        RecipeHelper.addRecipe(new ItemStack(FronosBlocks.RAINBOW_CANDY_CANE), "candy_cane_block", new Object[] { "XXX", "XXX", "XXX", 'X', FronosItems.RAINBOW_CANDY_CANE_STICK });

        RecipeHelper.addShapelessRecipe(new ItemStack(FronosBlocks.FRONOS_MOSSY_STONE_BRICKS), FronosBlocks.FRONOS_STONE_BRICKS, "vine" );
    }

    private static void addItemRecipe()
    {
        RecipeHelper.addShapelessRecipe(new ItemStack(FronosItems.RED_CANDY_CANE_STICK, 9), "candy_cane_stick", FronosBlocks.RED_CANDY_CANE);
        RecipeHelper.addShapelessRecipe(new ItemStack(FronosItems.GREEN_CANDY_CANE_STICK, 9), "candy_cane_stick", FronosBlocks.GREEN_CANDY_CANE);
        RecipeHelper.addShapelessRecipe(new ItemStack(FronosItems.BLUE_CANDY_CANE_STICK, 9), "candy_cane_stick", FronosBlocks.BLUE_CANDY_CANE);
        RecipeHelper.addShapelessRecipe(new ItemStack(FronosItems.ORANGE_CANDY_CANE_STICK, 9), "candy_cane_stick", FronosBlocks.ORANGE_CANDY_CANE);
        RecipeHelper.addShapelessRecipe(new ItemStack(FronosItems.PINK_CANDY_CANE_STICK, 9), "candy_cane_stick", FronosBlocks.PINK_CANDY_CANE);
        RecipeHelper.addShapelessRecipe(new ItemStack(FronosItems.YELLOW_CANDY_CANE_STICK, 9), "candy_cane_stick", FronosBlocks.YELLOW_CANDY_CANE);
        RecipeHelper.addShapelessRecipe(new ItemStack(FronosItems.PURPLE_CANDY_CANE_STICK, 9), "candy_cane_stick", FronosBlocks.PURPLE_CANDY_CANE);
        RecipeHelper.addShapelessRecipe(new ItemStack(FronosItems.RAINBOW_CANDY_CANE_STICK, 9), "candy_cane_stick", FronosBlocks.RAINBOW_CANDY_CANE);

        RecipeHelper.addShapelessRecipe(new ItemStack(FronosItems.GRAPE_JELLY, 9), "jelly", FronosBlocks.GRAPE_JELLY_BLOCK);
        RecipeHelper.addShapelessRecipe(new ItemStack(FronosItems.RASPBERRY_JELLY, 9), "jelly", FronosBlocks.RASPBERRY_JELLY_BLOCK);
        RecipeHelper.addShapelessRecipe(new ItemStack(FronosItems.STRAWBERRY_JELLY, 9), "jelly", FronosBlocks.STRAWBERRY_JELLY_BLOCK);
        RecipeHelper.addShapelessRecipe(new ItemStack(FronosItems.BERRY_JELLY, 9), "jelly", FronosBlocks.BERRY_JELLY_BLOCK);
        RecipeHelper.addShapelessRecipe(new ItemStack(FronosItems.LIME_JELLY, 9), "jelly", FronosBlocks.LIME_JELLY_BLOCK);
        RecipeHelper.addShapelessRecipe(new ItemStack(FronosItems.ORANGE_JELLY, 9), "jelly", FronosBlocks.ORANGE_JELLY_BLOCK);
        RecipeHelper.addShapelessRecipe(new ItemStack(FronosItems.GREEN_JELLY, 9), "jelly", FronosBlocks.GREEN_JELLY_BLOCK);
        RecipeHelper.addShapelessRecipe(new ItemStack(FronosItems.LEMON_JELLY, 9), "jelly", FronosBlocks.LEMON_JELLY_BLOCK);
    }

    private static void addBlockSmelting()
    {
        RecipeHelper.addSmeltingRecipe(new ItemStack(FronosBlocks.FRONOS_COBBLESTONE), new ItemStack(FronosBlocks.FRONOS_STONE), 0.1F);
        RecipeHelper.addSmeltingRecipe(new ItemStack(FronosBlocks.FRONOS_STONE_BRICKS), new ItemStack(FronosBlocks.FRONOS_CRACKED_STONE_BRICKS), 0.1F);
    }

    private static void addItemSmelting()
    {
        RecipeHelper.addSmeltingRecipe(new ItemStack(FronosBlocks.FRONOS_IRON_ORE), new ItemStack(Items.IRON_INGOT), 0.7F);
        RecipeHelper.addSmeltingRecipe(new ItemStack(FronosBlocks.FRONOS_GOLD_ORE), new ItemStack(Items.GOLD_INGOT), 1.0F);
        RecipeHelper.addSmeltingRecipe(new ItemStack(FronosBlocks.FRONOS_TIN_ORE), new ItemStack(GCItems.basicItem, 1, 4), 0.7F);
        RecipeHelper.addSmeltingRecipe(new ItemStack(FronosBlocks.FRONOS_COPPER_ORE), new ItemStack(GCItems.basicItem, 1, 3), 0.7F);
        RecipeHelper.addSmeltingRecipe(new ItemStack(FronosBlocks.FRONOS_ALUMINUM_ORE), new ItemStack(GCItems.basicItem, 1, 5), 0.7F);
        RecipeHelper.addSmeltingRecipe(new ItemStack(FronosBlocks.FRONOS_LEAD_ORE), new ItemStack(VenusItems.basicItem, 1, 1), 0.8F);
        RecipeHelper.addSmeltingRecipe(new ItemStack(FronosBlocks.FRONOS_COAL_ORE), new ItemStack(Items.COAL), 0.1F);
        RecipeHelper.addSmeltingRecipe(new ItemStack(FronosBlocks.FRONOS_LAPIS_ORE), new ItemStack(Items.DYE, 1, 4), 0.2F);
        RecipeHelper.addSmeltingRecipe(new ItemStack(FronosBlocks.FRONOS_DIAMOND_ORE), new ItemStack(Items.DIAMOND), 1.0F);
        RecipeHelper.addSmeltingRecipe(new ItemStack(FronosBlocks.FRONOS_EMERALD_ORE), new ItemStack(Items.EMERALD), 1.0F);
        RecipeHelper.addSmeltingRecipe(new ItemStack(FronosBlocks.FRONOS_REDSTONE_ORE), new ItemStack(Items.REDSTONE), 0.7F);
        RecipeHelper.addSmeltingRecipe(new ItemStack(FronosBlocks.FRONOS_SILICON_ORE), new ItemStack(GCItems.basicItem, 1, 2), 0.7F);
        RecipeHelper.addSmeltingRecipe(new ItemStack(FronosBlocks.FRONOS_QUARTZ_ORE), new ItemStack(Items.QUARTZ), 0.2F);
        RecipeHelper.addSmeltingRecipe(new ItemStack(FronosBlocks.EXTRAILONITE_ORE), new ItemStack(FronosItems.EXTRAILONITE_INGOT), 1.0F);
        RecipeHelper.addSmeltingRecipe(new ItemStack(FronosItems.MARSHMALLOW), new ItemStack(FronosItems.COOKED_MARSHMALLOW), 0.25F);
    }
}