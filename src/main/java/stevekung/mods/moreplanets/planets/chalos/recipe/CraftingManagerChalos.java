package stevekung.mods.moreplanets.planets.chalos.recipe;

import micdoodle8.mods.galacticraft.core.GCItems;
import micdoodle8.mods.galacticraft.planets.mars.items.MarsItems;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidUtil;
import stevekung.mods.moreplanets.init.MPBlocks;
import stevekung.mods.moreplanets.init.MPItems;
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
        RecipeHelper.addRecipe(new ItemStack(MPBlocks.DIREMSIUM_BLOCK), new Object[] { "XXX", "XXX", "XXX", 'X', MPItems.DIREMSIUM_INGOT });
        RecipeHelper.addRecipe(new ItemStack(MPBlocks.ZYPTORIUM_BLOCK), new Object[] { "XXX", "XXX", "XXX", 'X', MPItems.ZYPTORIUM_INGOT });
        RecipeHelper.addRecipe(new ItemStack(MPBlocks.CHEESE_MILK_CAKE), new Object[] { "MMM", "CCC", "CCC", 'C', MPItems.CHEESE_MILK_CURD, 'M', FluidUtil.getFilledBucket(new FluidStack(MPBlocks.CHEESE_MILK_FLUID, 1000)) });
        RecipeHelper.addRecipe(new ItemStack(MPBlocks.CHEESE_SPORE_CRAFTING_TABLE), "crafting_table", new Object[] { "XX", "XX", 'X', MPBlocks.CHEESE_SPORE_PLANKS });
        RecipeHelper.addRecipe(new ItemStack(MPBlocks.CHEESE_SLIME_BLOCK), new Object[] { "XXX", "XXX", "XXX", 'X', MPItems.CHEESE_SLIMEBALL });
        RecipeHelper.addRecipe(new ItemStack(MPBlocks.CHEESE_COARSE_DIRT, 4), new Object[] { "DG", "GD", 'D', MPBlocks.CHEESE_DIRT, 'G', "gravel" });
        RecipeHelper.addRecipe(new ItemStack(MPBlocks.CHEESE_SPORE_CHEST), new Object[] { "XXX", "X X", "XXX", 'X', MPBlocks.CHEESE_SPORE_PLANKS });
        RecipeHelper.addRecipe(new ItemStack(MPBlocks.CHEESE_SPORE_FENCE, 3), "wooden_fence", new Object[] { "XSX", "XSX", 'X', MPBlocks.CHEESE_SPORE_PLANKS, 'S', "stickWood" });
        RecipeHelper.addRecipe(new ItemStack(MPBlocks.CHEESE_SPORE_FENCE_GATE), "wooden_fence_gate", new Object[] { "SXS", "SXS", 'X', MPBlocks.CHEESE_SPORE_PLANKS, 'S', "stickWood" });
        RecipeHelper.addRecipe(new ItemStack(MPBlocks.CHALOS_COBBLESTONE_STAIRS, 4), "cobblestone_stairs", new Object[] { "X  ", "XX ", "XXX", 'X', MPBlocks.CHALOS_COBBLESTONE });
        RecipeHelper.addRecipe(new ItemStack(MPBlocks.CHALOS_DUNGEON_BRICK_STAIRS, 4), "dungeon_brick_stairs", new Object[] { "X  ", "XX ", "XXX", 'X', MPBlocks.CHALOS_DUNGEON_BRICK });
        RecipeHelper.addRecipe(new ItemStack(MPBlocks.CHEESE_SPORE_STAIRS, 4), "wooden_stairs", new Object[] { "X  ", "XX ", "XXX", 'X', MPBlocks.CHEESE_SPORE_PLANKS });
        RecipeHelper.addShapelessRecipe(new ItemStack(MPBlocks.CHEESE_SPORE_PLANKS, 4), "planks", MPBlocks.CHEESE_SPORE_STEM );
    }

    private static void addItemRecipe()
    {
        RecipeHelper.addShapelessRecipe(new ItemStack(MPItems.DIREMSIUM_INGOT, 9), MPBlocks.DIREMSIUM_BLOCK);
        RecipeHelper.addShapelessRecipe(new ItemStack(MPItems.ZYPTORIUM_INGOT, 9), MPBlocks.ZYPTORIUM_BLOCK);
        RecipeHelper.addShapelessRecipe(new ItemStack(MPItems.CHEESE_SLIMEBALL, 9), MPBlocks.CHEESE_SLIME_BLOCK);

        RecipeHelper.addRecipe(new ItemStack(MPItems.DIREMSIUM_HELMET, 1, 0), new Object[] { "XXX", "X X", 'X', MPItems.COMPRESSED_DIREMSIUM });
        RecipeHelper.addRecipe(new ItemStack(MPItems.DIREMSIUM_CHESTPLATE, 1, 0), new Object[] { "X X", "XXX", "XXX", 'X', MPItems.COMPRESSED_DIREMSIUM });
        RecipeHelper.addRecipe(new ItemStack(MPItems.DIREMSIUM_LEGGINGS, 1, 0), new Object[] { "XXX", "X X", "X X", 'X', MPItems.COMPRESSED_DIREMSIUM });
        RecipeHelper.addRecipe(new ItemStack(MPItems.DIREMSIUM_BOOTS, 1, 0), new Object[] { "X X", "X X", 'X', MPItems.COMPRESSED_DIREMSIUM });
        RecipeHelper.addRecipe(new ItemStack(MPItems.BREATHABLE_DIREMSIUM_HELMET, 1, 0), new Object[] { "XXX", "XYX", 'Y', GCItems.oxMask, 'X', MPItems.COMPRESSED_DIREMSIUM });
        RecipeHelper.addRecipe(new ItemStack(MPItems.CHEESE_SPORE_DOOR, 3), "wooden_door", new Object[] { "XX", "XX", "XX", 'X', MPBlocks.CHEESE_SPORE_PLANKS });

        RecipeHelper.addRecipe(new ItemStack(MPItems.DIREMSIUM_HOE), new Object[] { "XX", " Y", " Y", 'X', MPItems.COMPRESSED_DIREMSIUM, 'Y', new ItemStack(MarsItems.marsItemBasic, 1, 1) });
        RecipeHelper.addRecipe(new ItemStack(MPItems.DIREMSIUM_AXE), new Object[] { "XX", "XY", " Y", 'X', MPItems.COMPRESSED_DIREMSIUM, 'Y', new ItemStack(MarsItems.marsItemBasic, 1, 1) });
        RecipeHelper.addRecipe(new ItemStack(MPItems.DIREMSIUM_PICKAXE), new Object[] { "XXX", " Y ", " Y ", 'X', MPItems.COMPRESSED_DIREMSIUM, 'Y', new ItemStack(MarsItems.marsItemBasic, 1, 1) });
        RecipeHelper.addRecipe(new ItemStack(MPItems.DIREMSIUM_SWORD), new Object[] { "X", "X", "Y", 'X', MPItems.COMPRESSED_DIREMSIUM, 'Y', new ItemStack(MarsItems.marsItemBasic, 1, 1) });
        RecipeHelper.addRecipe(new ItemStack(MPItems.DIREMSIUM_SHOVEL), new Object[] { "X", "Y", "Y", 'X', MPItems.COMPRESSED_DIREMSIUM, 'Y', new ItemStack(MarsItems.marsItemBasic, 1, 1) });
        RecipeHelper.addRecipe(new ItemStack(MPItems.CHEESE_SPORE_WOOD_HOE), new Object[] { "XX", " Y", " Y", 'X', MPBlocks.CHEESE_SPORE_PLANKS, 'Y', "stickWood" });
        RecipeHelper.addRecipe(new ItemStack(MPItems.CHEESE_SPORE_WOOD_AXE), new Object[] { "XX", "XY", " Y", 'X', MPBlocks.CHEESE_SPORE_PLANKS, 'Y', "stickWood" });
        RecipeHelper.addRecipe(new ItemStack(MPItems.CHEESE_SPORE_WOOD_PICKAXE), new Object[] { "XXX", " Y ", " Y ", 'X', MPBlocks.CHEESE_SPORE_PLANKS, 'Y', "stickWood" });
        RecipeHelper.addRecipe(new ItemStack(MPItems.CHEESE_SPORE_WOOD_SWORD), new Object[] { "X", "X", "Y", 'X', MPBlocks.CHEESE_SPORE_PLANKS, 'Y', "stickWood" });
        RecipeHelper.addRecipe(new ItemStack(MPItems.CHEESE_SPORE_WOOD_SHOVEL), new Object[] { "X", "Y", "Y", 'X', MPBlocks.CHEESE_SPORE_PLANKS, 'Y', "stickWood" });
    }

    private static void addBlockSmelting()
    {
        RecipeHelper.addSmeltingRecipe(new ItemStack(MPBlocks.CHALOS_COBBLESTONE), new ItemStack(MPBlocks.CHALOS_ROCK), 0.1F);
    }

    private static void addItemSmelting()
    {
        RecipeHelper.addSmeltingRecipe(new ItemStack(MPBlocks.DIREMSIUM_ORE), new ItemStack(MPItems.DIREMSIUM_INGOT), 0.8F);
        RecipeHelper.addSmeltingRecipe(new ItemStack(MPBlocks.ZYPTORIUM_ORE), new ItemStack(MPItems.ZYPTORIUM_INGOT), 0.8F);
        RecipeHelper.addSmeltingRecipe(new ItemStack(MPBlocks.CHEESE_MILK_ORE), new ItemStack(MPItems.CHEESE_MILK_CURD), 0.8F);
        RecipeHelper.addSmeltingRecipe(new ItemStack(MPBlocks.CHALOS_IRON_ORE), new ItemStack(Items.IRON_INGOT), 0.8F);
        RecipeHelper.addSmeltingRecipe(new ItemStack(MPBlocks.CHALOS_TIN_ORE), new ItemStack(GCItems.basicItem, 1, 4), 0.8F);
        RecipeHelper.addSmeltingRecipe(new ItemStack(MPBlocks.CHALOS_COPPER_ORE), new ItemStack(GCItems.basicItem, 1, 3), 0.8F);
        RecipeHelper.addSmeltingRecipe(new ItemStack(MPBlocks.CHALOS_ALUMINUM_ORE), new ItemStack(GCItems.basicItem, 1, 5), 0.8F);
        RecipeHelper.addSmeltingRecipe(new ItemStack(MPItems.RAW_CHEESE_BEEF), new ItemStack(MPItems.COOKED_CHEESE_BEEF), 0.35F);
        RecipeHelper.addSmeltingRecipe(new ItemStack(MPBlocks.CHEESE_SPORE_STEM), new ItemStack(Items.COAL, 1, 1), 0.15F);
    }
}