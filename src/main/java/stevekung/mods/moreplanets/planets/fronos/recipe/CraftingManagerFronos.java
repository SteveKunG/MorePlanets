package stevekung.mods.moreplanets.planets.fronos.recipe;

import micdoodle8.mods.galacticraft.core.GCItems;
import micdoodle8.mods.galacticraft.planets.venus.VenusItems;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import stevekung.mods.moreplanets.init.MPBlocks;
import stevekung.mods.moreplanets.planets.fronos.items.FronosItems;
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
        RecipeHelper.addRecipe(new ItemStack(MPBlocks.FRONOS_STONE_BRICKS, 4), new Object[] { "XX", "XX", 'X', MPBlocks.FRONOS_STONE });

        RecipeHelper.addRecipe(new ItemStack(MPBlocks.GRAPE_JELLY_BLOCK), "jelly_block", new Object[] { "XXX", "XXX", "XXX", 'X', FronosItems.GRAPE_JELLY });
        RecipeHelper.addRecipe(new ItemStack(MPBlocks.RASPBERRY_JELLY_BLOCK), "jelly_block", new Object[] { "XXX", "XXX", "XXX", 'X', FronosItems.RASPBERRY_JELLY });
        RecipeHelper.addRecipe(new ItemStack(MPBlocks.STRAWBERRY_JELLY_BLOCK), "jelly_block", new Object[] { "XXX", "XXX", "XXX", 'X', FronosItems.STRAWBERRY_JELLY });
        RecipeHelper.addRecipe(new ItemStack(MPBlocks.BERRY_JELLY_BLOCK), "jelly_block", new Object[] { "XXX", "XXX", "XXX", 'X', FronosItems.BERRY_JELLY });
        RecipeHelper.addRecipe(new ItemStack(MPBlocks.LIME_JELLY_BLOCK), "jelly_block", new Object[] { "XXX", "XXX", "XXX", 'X', FronosItems.LIME_JELLY });
        RecipeHelper.addRecipe(new ItemStack(MPBlocks.ORANGE_JELLY_BLOCK), "jelly_block", new Object[] { "XXX", "XXX", "XXX", 'X', FronosItems.ORANGE_JELLY });
        RecipeHelper.addRecipe(new ItemStack(MPBlocks.GREEN_JELLY_BLOCK), "jelly_block", new Object[] { "XXX", "XXX", "XXX", 'X', FronosItems.GREEN_JELLY });
        RecipeHelper.addRecipe(new ItemStack(MPBlocks.LEMON_JELLY_BLOCK), "jelly_block", new Object[] { "XXX", "XXX", "XXX", 'X', FronosItems.LEMON_JELLY });

        RecipeHelper.addRecipe(new ItemStack(MPBlocks.RED_CANDY_CANE), "candy_cane_block", new Object[] { "XXX", "XXX", "XXX", 'X', FronosItems.RED_CANDY_CANE_STICK });
        RecipeHelper.addRecipe(new ItemStack(MPBlocks.GREEN_CANDY_CANE), "candy_cane_block", new Object[] { "XXX", "XXX", "XXX", 'X', FronosItems.GREEN_CANDY_CANE_STICK });
        RecipeHelper.addRecipe(new ItemStack(MPBlocks.BLUE_CANDY_CANE), "candy_cane_block", new Object[] { "XXX", "XXX", "XXX", 'X', FronosItems.BLUE_CANDY_CANE_STICK });
        RecipeHelper.addRecipe(new ItemStack(MPBlocks.ORANGE_CANDY_CANE), "candy_cane_block", new Object[] { "XXX", "XXX", "XXX", 'X', FronosItems.ORANGE_CANDY_CANE_STICK });
        RecipeHelper.addRecipe(new ItemStack(MPBlocks.PINK_CANDY_CANE), "candy_cane_block", new Object[] { "XXX", "XXX", "XXX", 'X', FronosItems.PINK_CANDY_CANE_STICK });
        RecipeHelper.addRecipe(new ItemStack(MPBlocks.YELLOW_CANDY_CANE), "candy_cane_block", new Object[] { "XXX", "XXX", "XXX", 'X', FronosItems.YELLOW_CANDY_CANE_STICK });
        RecipeHelper.addRecipe(new ItemStack(MPBlocks.PURPLE_CANDY_CANE), "candy_cane_block", new Object[] { "XXX", "XXX", "XXX", 'X', FronosItems.PURPLE_CANDY_CANE_STICK });
        RecipeHelper.addRecipe(new ItemStack(MPBlocks.RAINBOW_CANDY_CANE), "candy_cane_block", new Object[] { "XXX", "XXX", "XXX", 'X', FronosItems.RAINBOW_CANDY_CANE_STICK });

        RecipeHelper.addShapelessRecipe(new ItemStack(MPBlocks.FRONOS_MOSSY_STONE_BRICKS), MPBlocks.FRONOS_STONE_BRICKS, "vine" );
    }

    private static void addItemRecipe()
    {
        RecipeHelper.addShapelessRecipe(new ItemStack(FronosItems.RED_CANDY_CANE_STICK, 9), "candy_cane_stick", MPBlocks.RED_CANDY_CANE);
        RecipeHelper.addShapelessRecipe(new ItemStack(FronosItems.GREEN_CANDY_CANE_STICK, 9), "candy_cane_stick", MPBlocks.GREEN_CANDY_CANE);
        RecipeHelper.addShapelessRecipe(new ItemStack(FronosItems.BLUE_CANDY_CANE_STICK, 9), "candy_cane_stick", MPBlocks.BLUE_CANDY_CANE);
        RecipeHelper.addShapelessRecipe(new ItemStack(FronosItems.ORANGE_CANDY_CANE_STICK, 9), "candy_cane_stick", MPBlocks.ORANGE_CANDY_CANE);
        RecipeHelper.addShapelessRecipe(new ItemStack(FronosItems.PINK_CANDY_CANE_STICK, 9), "candy_cane_stick", MPBlocks.PINK_CANDY_CANE);
        RecipeHelper.addShapelessRecipe(new ItemStack(FronosItems.YELLOW_CANDY_CANE_STICK, 9), "candy_cane_stick", MPBlocks.YELLOW_CANDY_CANE);
        RecipeHelper.addShapelessRecipe(new ItemStack(FronosItems.PURPLE_CANDY_CANE_STICK, 9), "candy_cane_stick", MPBlocks.PURPLE_CANDY_CANE);
        RecipeHelper.addShapelessRecipe(new ItemStack(FronosItems.RAINBOW_CANDY_CANE_STICK, 9), "candy_cane_stick", MPBlocks.RAINBOW_CANDY_CANE);

        RecipeHelper.addShapelessRecipe(new ItemStack(FronosItems.GRAPE_JELLY, 9), "jelly", MPBlocks.GRAPE_JELLY_BLOCK);
        RecipeHelper.addShapelessRecipe(new ItemStack(FronosItems.RASPBERRY_JELLY, 9), "jelly", MPBlocks.RASPBERRY_JELLY_BLOCK);
        RecipeHelper.addShapelessRecipe(new ItemStack(FronosItems.STRAWBERRY_JELLY, 9), "jelly", MPBlocks.STRAWBERRY_JELLY_BLOCK);
        RecipeHelper.addShapelessRecipe(new ItemStack(FronosItems.BERRY_JELLY, 9), "jelly", MPBlocks.BERRY_JELLY_BLOCK);
        RecipeHelper.addShapelessRecipe(new ItemStack(FronosItems.LIME_JELLY, 9), "jelly", MPBlocks.LIME_JELLY_BLOCK);
        RecipeHelper.addShapelessRecipe(new ItemStack(FronosItems.ORANGE_JELLY, 9), "jelly", MPBlocks.ORANGE_JELLY_BLOCK);
        RecipeHelper.addShapelessRecipe(new ItemStack(FronosItems.GREEN_JELLY, 9), "jelly", MPBlocks.GREEN_JELLY_BLOCK);
        RecipeHelper.addShapelessRecipe(new ItemStack(FronosItems.LEMON_JELLY, 9), "jelly", MPBlocks.LEMON_JELLY_BLOCK);
    }

    private static void addBlockSmelting()
    {
        RecipeHelper.addSmeltingRecipe(new ItemStack(MPBlocks.FRONOS_COBBLESTONE), new ItemStack(MPBlocks.FRONOS_STONE), 0.1F);
        RecipeHelper.addSmeltingRecipe(new ItemStack(MPBlocks.FRONOS_STONE_BRICKS), new ItemStack(MPBlocks.FRONOS_CRACKED_STONE_BRICKS), 0.1F);
    }

    private static void addItemSmelting()
    {
        RecipeHelper.addSmeltingRecipe(new ItemStack(MPBlocks.FRONOS_IRON_ORE), new ItemStack(Items.IRON_INGOT), 0.7F);
        RecipeHelper.addSmeltingRecipe(new ItemStack(MPBlocks.FRONOS_GOLD_ORE), new ItemStack(Items.GOLD_INGOT), 1.0F);
        RecipeHelper.addSmeltingRecipe(new ItemStack(MPBlocks.FRONOS_TIN_ORE), new ItemStack(GCItems.basicItem, 1, 4), 0.7F);
        RecipeHelper.addSmeltingRecipe(new ItemStack(MPBlocks.FRONOS_COPPER_ORE), new ItemStack(GCItems.basicItem, 1, 3), 0.7F);
        RecipeHelper.addSmeltingRecipe(new ItemStack(MPBlocks.FRONOS_ALUMINUM_ORE), new ItemStack(GCItems.basicItem, 1, 5), 0.7F);
        RecipeHelper.addSmeltingRecipe(new ItemStack(MPBlocks.FRONOS_LEAD_ORE), new ItemStack(VenusItems.basicItem, 1, 1), 0.8F);
        RecipeHelper.addSmeltingRecipe(new ItemStack(MPBlocks.FRONOS_COAL_ORE), new ItemStack(Items.COAL), 0.1F);
        RecipeHelper.addSmeltingRecipe(new ItemStack(MPBlocks.FRONOS_LAPIS_ORE), new ItemStack(Items.DYE, 1, 4), 0.2F);
        RecipeHelper.addSmeltingRecipe(new ItemStack(MPBlocks.FRONOS_DIAMOND_ORE), new ItemStack(Items.DIAMOND), 1.0F);
        RecipeHelper.addSmeltingRecipe(new ItemStack(MPBlocks.FRONOS_EMERALD_ORE), new ItemStack(Items.EMERALD), 1.0F);
        RecipeHelper.addSmeltingRecipe(new ItemStack(MPBlocks.FRONOS_REDSTONE_ORE), new ItemStack(Items.REDSTONE), 0.7F);
        RecipeHelper.addSmeltingRecipe(new ItemStack(MPBlocks.FRONOS_SILICON_ORE), new ItemStack(GCItems.basicItem, 1, 2), 0.7F);
        RecipeHelper.addSmeltingRecipe(new ItemStack(MPBlocks.FRONOS_QUARTZ_ORE), new ItemStack(Items.QUARTZ), 0.2F);
        RecipeHelper.addSmeltingRecipe(new ItemStack(MPBlocks.EXTRAILONITE_ORE), new ItemStack(FronosItems.EXTRAILONITE_INGOT), 1.0F);
        RecipeHelper.addSmeltingRecipe(new ItemStack(FronosItems.MARSHMALLOW), new ItemStack(FronosItems.COOKED_MARSHMALLOW), 0.25F);
    }
}