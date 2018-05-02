package stevekung.mods.moreplanets.module.planets.chalos.recipe;

import micdoodle8.mods.galacticraft.core.GCItems;
import micdoodle8.mods.galacticraft.planets.mars.items.MarsItems;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidUtil;
import stevekung.mods.moreplanets.module.planets.chalos.blocks.ChalosBlocks;
import stevekung.mods.moreplanets.module.planets.chalos.items.ChalosItems;
import stevekung.mods.moreplanets.utils.helper.RecipeHelper;

public class CraftingManagerChalos
{
    public static void init()
    {
        CraftingManagerChalos.addBlockRecipe();
        CraftingManagerChalos.addItemRecipe();
        CraftingManagerChalos.addBlockSmelting();
        CraftingManagerChalos.addItemSmelting();
    }

    private static void addBlockRecipe()
    {
        RecipeHelper.addRecipe(new ItemStack(ChalosBlocks.DIREMSIUM_BLOCK), new Object[] { "XXX", "XXX", "XXX", 'X', ChalosItems.DIREMSIUM_INGOT });
        RecipeHelper.addRecipe(new ItemStack(ChalosBlocks.ZYPTORIUM_BLOCK), new Object[] { "XXX", "XXX", "XXX", 'X', ChalosItems.ZYPTORIUM_INGOT });
        RecipeHelper.addRecipe(new ItemStack(ChalosBlocks.CHEESE_MILK_CAKE), new Object[] { "MMM", "CCC", "CCC", 'C', ChalosItems.CHEESE_MILK_CURD, 'M', FluidUtil.getFilledBucket(new FluidStack(ChalosBlocks.CHEESE_MILK_FLUID, 1000)) });
        RecipeHelper.addRecipe(new ItemStack(ChalosBlocks.CHEESE_SPORE_CRAFTING_TABLE), "crafting_table", new Object[] { "XX", "XX", 'X', ChalosBlocks.CHEESE_SPORE_PLANKS });
        RecipeHelper.addRecipe(new ItemStack(ChalosBlocks.CHEESE_SLIME_BLOCK), new Object[] { "XXX", "XXX", "XXX", 'X', ChalosItems.CHEESE_SLIMEBALL });
        RecipeHelper.addRecipe(new ItemStack(ChalosBlocks.CHEESE_COARSE_DIRT, 4), new Object[] { "DG", "GD", 'D', ChalosBlocks.CHEESE_DIRT, 'G', "gravel" });
        RecipeHelper.addRecipe(new ItemStack(ChalosBlocks.CHEESE_SPORE_CHEST), new Object[] { "XXX", "X X", "XXX", 'X', ChalosBlocks.CHEESE_SPORE_PLANKS });
        RecipeHelper.addRecipe(new ItemStack(ChalosBlocks.CHEESE_SPORE_FENCE, 3), "wooden_fence", new Object[] { "XSX", "XSX", 'X', ChalosBlocks.CHEESE_SPORE_PLANKS, 'S', "stickWood" });
        RecipeHelper.addRecipe(new ItemStack(ChalosBlocks.CHEESE_SPORE_FENCE_GATE), "wooden_fence_gate", new Object[] { "SXS", "SXS", 'X', ChalosBlocks.CHEESE_SPORE_PLANKS, 'S', "stickWood" });
        RecipeHelper.addRecipe(new ItemStack(ChalosBlocks.CHALOS_COBBLESTONE_STAIRS, 4), "cobblestone_stairs", new Object[] { "X  ", "XX ", "XXX", 'X', ChalosBlocks.CHALOS_COBBLESTONE });
        RecipeHelper.addRecipe(new ItemStack(ChalosBlocks.CHALOS_DUNGEON_BRICK_STAIRS, 4), "dungeon_brick_stairs", new Object[] { "X  ", "XX ", "XXX", 'X', ChalosBlocks.CHALOS_DUNGEON_BRICK });
        RecipeHelper.addRecipe(new ItemStack(ChalosBlocks.CHEESE_SPORE_STAIRS, 4), "wooden_stairs", new Object[] { "X  ", "XX ", "XXX", 'X', ChalosBlocks.CHEESE_SPORE_PLANKS });
        RecipeHelper.addShapelessRecipe(new ItemStack(ChalosBlocks.CHEESE_SPORE_PLANKS, 4), "planks", ChalosBlocks.CHEESE_SPORE_STEM );
    }

    private static void addItemRecipe()
    {
        RecipeHelper.addShapelessRecipe(new ItemStack(ChalosItems.DIREMSIUM_INGOT, 9), ChalosBlocks.DIREMSIUM_BLOCK);
        RecipeHelper.addShapelessRecipe(new ItemStack(ChalosItems.ZYPTORIUM_INGOT, 9), ChalosBlocks.ZYPTORIUM_BLOCK);
        RecipeHelper.addShapelessRecipe(new ItemStack(ChalosItems.CHEESE_SLIMEBALL, 9), ChalosBlocks.CHEESE_SLIME_BLOCK);

        RecipeHelper.addRecipe(new ItemStack(ChalosItems.DIREMSIUM_HELMET, 1, 0), new Object[] { "XXX", "X X", 'X', ChalosItems.COMPRESSED_DIREMSIUM });
        RecipeHelper.addRecipe(new ItemStack(ChalosItems.DIREMSIUM_CHESTPLATE, 1, 0), new Object[] { "X X", "XXX", "XXX", 'X', ChalosItems.COMPRESSED_DIREMSIUM });
        RecipeHelper.addRecipe(new ItemStack(ChalosItems.DIREMSIUM_LEGGINGS, 1, 0), new Object[] { "XXX", "X X", "X X", 'X', ChalosItems.COMPRESSED_DIREMSIUM });
        RecipeHelper.addRecipe(new ItemStack(ChalosItems.DIREMSIUM_BOOTS, 1, 0), new Object[] { "X X", "X X", 'X', ChalosItems.COMPRESSED_DIREMSIUM });
        RecipeHelper.addRecipe(new ItemStack(ChalosItems.BREATHABLE_DIREMSIUM_HELMET, 1, 0), new Object[] { "XXX", "XYX", 'Y', GCItems.oxMask, 'X', ChalosItems.COMPRESSED_DIREMSIUM });
        RecipeHelper.addRecipe(new ItemStack(ChalosItems.CHEESE_SPORE_DOOR, 3), "wooden_door", new Object[] { "XX", "XX", "XX", 'X', ChalosBlocks.CHEESE_SPORE_PLANKS });

        RecipeHelper.addRecipe(new ItemStack(ChalosItems.DIREMSIUM_HOE), new Object[] { "XX", " Y", " Y", 'X', ChalosItems.COMPRESSED_DIREMSIUM, 'Y', new ItemStack(MarsItems.marsItemBasic, 1, 1) });
        RecipeHelper.addRecipe(new ItemStack(ChalosItems.DIREMSIUM_AXE), new Object[] { "XX", "XY", " Y", 'X', ChalosItems.COMPRESSED_DIREMSIUM, 'Y', new ItemStack(MarsItems.marsItemBasic, 1, 1) });
        RecipeHelper.addRecipe(new ItemStack(ChalosItems.DIREMSIUM_PICKAXE), new Object[] { "XXX", " Y ", " Y ", 'X', ChalosItems.COMPRESSED_DIREMSIUM, 'Y', new ItemStack(MarsItems.marsItemBasic, 1, 1) });
        RecipeHelper.addRecipe(new ItemStack(ChalosItems.DIREMSIUM_SWORD), new Object[] { "X", "X", "Y", 'X', ChalosItems.COMPRESSED_DIREMSIUM, 'Y', new ItemStack(MarsItems.marsItemBasic, 1, 1) });
        RecipeHelper.addRecipe(new ItemStack(ChalosItems.DIREMSIUM_SHOVEL), new Object[] { "X", "Y", "Y", 'X', ChalosItems.COMPRESSED_DIREMSIUM, 'Y', new ItemStack(MarsItems.marsItemBasic, 1, 1) });
        RecipeHelper.addRecipe(new ItemStack(ChalosItems.CHEESE_SPORE_WOOD_HOE), new Object[] { "XX", " Y", " Y", 'X', ChalosBlocks.CHEESE_SPORE_PLANKS, 'Y', "stickWood" });
        RecipeHelper.addRecipe(new ItemStack(ChalosItems.CHEESE_SPORE_WOOD_AXE), new Object[] { "XX", "XY", " Y", 'X', ChalosBlocks.CHEESE_SPORE_PLANKS, 'Y', "stickWood" });
        RecipeHelper.addRecipe(new ItemStack(ChalosItems.CHEESE_SPORE_WOOD_PICKAXE), new Object[] { "XXX", " Y ", " Y ", 'X', ChalosBlocks.CHEESE_SPORE_PLANKS, 'Y', "stickWood" });
        RecipeHelper.addRecipe(new ItemStack(ChalosItems.CHEESE_SPORE_WOOD_SWORD), new Object[] { "X", "X", "Y", 'X', ChalosBlocks.CHEESE_SPORE_PLANKS, 'Y', "stickWood" });
        RecipeHelper.addRecipe(new ItemStack(ChalosItems.CHEESE_SPORE_WOOD_SHOVEL), new Object[] { "X", "Y", "Y", 'X', ChalosBlocks.CHEESE_SPORE_PLANKS, 'Y', "stickWood" });
    }

    private static void addBlockSmelting()
    {
        RecipeHelper.addSmeltingRecipe(new ItemStack(ChalosBlocks.CHALOS_COBBLESTONE), new ItemStack(ChalosBlocks.CHALOS_ROCK), 0.1F);
    }

    private static void addItemSmelting()
    {
        RecipeHelper.addSmeltingRecipe(new ItemStack(ChalosBlocks.DIREMSIUM_ORE), new ItemStack(ChalosItems.DIREMSIUM_INGOT), 0.8F);
        RecipeHelper.addSmeltingRecipe(new ItemStack(ChalosBlocks.ZYPTORIUM_ORE), new ItemStack(ChalosItems.ZYPTORIUM_INGOT), 0.8F);
        RecipeHelper.addSmeltingRecipe(new ItemStack(ChalosBlocks.CHEESE_MILK_ORE), new ItemStack(ChalosItems.CHEESE_MILK_CURD), 0.8F);
        RecipeHelper.addSmeltingRecipe(new ItemStack(ChalosBlocks.CHALOS_IRON_ORE), new ItemStack(Items.IRON_INGOT), 0.8F);
        RecipeHelper.addSmeltingRecipe(new ItemStack(ChalosBlocks.CHALOS_TIN_ORE), new ItemStack(GCItems.basicItem, 1, 4), 0.8F);
        RecipeHelper.addSmeltingRecipe(new ItemStack(ChalosBlocks.CHALOS_COPPER_ORE), new ItemStack(GCItems.basicItem, 1, 3), 0.8F);
        RecipeHelper.addSmeltingRecipe(new ItemStack(ChalosBlocks.CHALOS_ALUMINUM_ORE), new ItemStack(GCItems.basicItem, 1, 5), 0.8F);
        RecipeHelper.addSmeltingRecipe(new ItemStack(ChalosItems.RAW_CHEESE_BEEF), new ItemStack(ChalosItems.COOKED_CHEESE_BEEF), 0.35F);
        RecipeHelper.addSmeltingRecipe(new ItemStack(ChalosBlocks.CHEESE_SPORE_STEM), new ItemStack(Items.COAL, 1, 1), 0.15F);
    }
}