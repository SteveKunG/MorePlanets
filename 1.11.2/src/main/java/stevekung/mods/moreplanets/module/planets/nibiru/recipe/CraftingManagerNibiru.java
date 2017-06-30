package stevekung.mods.moreplanets.module.planets.nibiru.recipe;

import java.util.HashMap;

import com.google.common.collect.Maps;

import micdoodle8.mods.galacticraft.core.GCBlocks;
import micdoodle8.mods.galacticraft.core.GCItems;
import micdoodle8.mods.galacticraft.planets.asteroids.items.AsteroidsItems;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.EnumDyeColor;
import net.minecraft.item.ItemStack;
import stevekung.mods.moreplanets.init.MPItems;
import stevekung.mods.moreplanets.module.planets.chalos.items.ChalosItems;
import stevekung.mods.moreplanets.module.planets.diona.items.DionaItems;
import stevekung.mods.moreplanets.module.planets.nibiru.blocks.NibiruBlocks;
import stevekung.mods.moreplanets.module.planets.nibiru.items.NibiruItems;
import stevekung.mods.moreplanets.util.helper.RecipeHelper;

public class CraftingManagerNibiru
{
    public static void init()
    {
        CraftingManagerNibiru.addBlockRecipe();
        CraftingManagerNibiru.addItemRecipe();
        CraftingManagerNibiru.addBlockSmelting();
        CraftingManagerNibiru.addItemSmelting();
        CraftingManagerNibiru.addRocketRecipe();
    }

    private static void addBlockRecipe()
    {
        RecipeHelper.addRecipe(new ItemStack(NibiruBlocks.NIBIRU_BLOCK, 1, 7), new Object[] { "XXX", "XXX", "XXX", 'X', new ItemStack(NibiruItems.NIBIRU_ITEM, 1, 0) });
        RecipeHelper.addRecipe(new ItemStack(NibiruBlocks.NIBIRU_CRAFTING_TABLE, 1, 0), new Object[] { "XX", "XX", 'X', new ItemStack(NibiruBlocks.NIBIRU_PLANKS, 1, 0) });
        RecipeHelper.addRecipe(new ItemStack(NibiruBlocks.NIBIRU_CRAFTING_TABLE, 1, 0), new Object[] { "XX", "XX", 'X', new ItemStack(NibiruBlocks.NIBIRU_PLANKS, 1, 1) });
        RecipeHelper.addRecipe(new ItemStack(NibiruBlocks.NIBIRU_CRAFTING_TABLE, 1, 1), new Object[] { "XX", "XX", 'X', new ItemStack(NibiruBlocks.NIBIRU_PLANKS, 1, 2) });
        RecipeHelper.addRecipe(new ItemStack(NibiruBlocks.INFECTED_DIRT, 4, 1), new Object[] { "DG", "GD", 'D', new ItemStack(NibiruBlocks.INFECTED_DIRT, 1, 0), 'G', Blocks.GRAVEL });
        RecipeHelper.addRecipe(new ItemStack(NibiruBlocks.INFECTED_DIRT, 4, 1), new Object[] { "DG", "GD", 'D', new ItemStack(NibiruBlocks.INFECTED_DIRT, 1, 0), 'G', NibiruBlocks.INFECTED_GRAVEL });
        RecipeHelper.addRecipe(new ItemStack(NibiruBlocks.NIBIRU_BLOCK, 4, 3), new Object[] { "XX", "XX", 'X', new ItemStack(NibiruBlocks.NIBIRU_BLOCK, 1, 0) });
        RecipeHelper.addRecipe(new ItemStack(NibiruBlocks.NIBIRU_SANDSTONE, 4, 0), new Object[] { "XX", "XX", 'X', NibiruBlocks.INFECTED_SAND });
        RecipeHelper.addRecipe(new ItemStack(NibiruBlocks.NIBIRU_SANDSTONE, 4, 2), new Object[] { "XX", "XX", 'X', new ItemStack(NibiruBlocks.NIBIRU_SANDSTONE, 4, 0) });
        RecipeHelper.addRecipe(new ItemStack(NibiruBlocks.NIBIRU_BOOKSHELF, 1, 0), new Object[] { "WWW", "BBB", "WWW", 'W', new ItemStack(NibiruBlocks.NIBIRU_PLANKS, 1, 0), 'B', Items.BOOK });
        RecipeHelper.addRecipe(new ItemStack(NibiruBlocks.NIBIRU_BOOKSHELF, 1, 0), new Object[] { "WWW", "BBB", "WWW", 'W', new ItemStack(NibiruBlocks.NIBIRU_PLANKS, 1, 1), 'B', Items.BOOK });
        RecipeHelper.addRecipe(new ItemStack(NibiruBlocks.NIBIRU_BOOKSHELF, 1, 1), new Object[] { "WWW", "BBB", "WWW", 'W', new ItemStack(NibiruBlocks.NIBIRU_PLANKS, 1, 2), 'B', Items.BOOK });
        RecipeHelper.addRecipe(new ItemStack(NibiruBlocks.INFECTED_SNOW), new Object[] { "XX", "XX", 'X', NibiruItems.INFECTED_SNOWBALL });
        RecipeHelper.addRecipe(new ItemStack(NibiruBlocks.INFECTED_MELON_BLOCK), new Object[] { "XXX", "XXX", "XXX", 'X', new ItemStack(NibiruItems.NIBIRU_FRUITS, 1, 3) });
        RecipeHelper.addRecipe(new ItemStack(NibiruBlocks.INFECTED_CLAY), new Object[] { "XX", "XX", 'X', NibiruItems.INFECTED_CLAY_BALL });
        RecipeHelper.addRecipe(new ItemStack(NibiruBlocks.INFECTED_PRISMARINE, 4, 0), new Object[] { "SS", "SS", 'S', new ItemStack(NibiruItems.INFECTED_PRISMARINE, 1, 0) });
        RecipeHelper.addRecipe(new ItemStack(NibiruBlocks.INFECTED_PRISMARINE, 1, 1), new Object[] { "SSS", "SSS", "SSS", 'S', new ItemStack(NibiruItems.INFECTED_PRISMARINE, 1, 0) });
        RecipeHelper.addRecipe(new ItemStack(NibiruBlocks.INFECTED_PRISMARINE, 1, 2), new Object[] { "SSS", "SIS", "SSS", 'S', new ItemStack(NibiruItems.INFECTED_PRISMARINE, 1, 0), 'I', new ItemStack(Items.DYE, 1, EnumDyeColor.BLACK.getDyeDamage()) });
        RecipeHelper.addRecipe(new ItemStack(NibiruBlocks.INFECTED_SEA_LANTERN), new Object[] { "PSP", "SSS", "PSP", 'P', new ItemStack(NibiruItems.INFECTED_PRISMARINE, 1, 0), 'S', new ItemStack(NibiruItems.INFECTED_PRISMARINE, 1, 1) });
        RecipeHelper.addRecipe(new ItemStack(NibiruBlocks.NIBIRU_FENCE, 3, 0), new Object[] { "XSX", "XSX", 'X', new ItemStack(NibiruBlocks.NIBIRU_PLANKS, 1, 0), 'S', Items.STICK });
        RecipeHelper.addRecipe(new ItemStack(NibiruBlocks.NIBIRU_FENCE, 3, 1), new Object[] { "XSX", "XSX", 'X', new ItemStack(NibiruBlocks.NIBIRU_PLANKS, 1, 1), 'S', Items.STICK });
        RecipeHelper.addRecipe(new ItemStack(NibiruBlocks.NIBIRU_FENCE, 3, 2), new Object[] { "XSX", "XSX", 'X', new ItemStack(NibiruBlocks.NIBIRU_PLANKS, 1, 2), 'S', Items.STICK });
        RecipeHelper.addRecipe(new ItemStack(NibiruBlocks.INFECTED_FENCE_GATE), new Object[] { "SXS", "SXS", 'X', new ItemStack(NibiruBlocks.NIBIRU_PLANKS, 1, 0), 'S', Items.STICK });
        RecipeHelper.addRecipe(new ItemStack(NibiruBlocks.INFECTED_DEAD_OAK_FENCE_GATE), new Object[] { "SXS", "SXS", 'X', new ItemStack(NibiruBlocks.NIBIRU_PLANKS, 1, 1), 'S', Items.STICK });
        RecipeHelper.addRecipe(new ItemStack(NibiruBlocks.ALIEN_BERRY_OAK_FENCE_GATE), new Object[] { "SXS", "SXS", 'X', new ItemStack(NibiruBlocks.NIBIRU_PLANKS, 1, 2), 'S', Items.STICK });
        RecipeHelper.addRecipe(new ItemStack(NibiruBlocks.INFECTED_CHEST), new Object[] { "XXX", "X X", "XXX", 'X', new ItemStack(NibiruBlocks.NIBIRU_PLANKS, 1, 0) });
        RecipeHelper.addRecipe(new ItemStack(NibiruBlocks.INFECTED_CHEST), new Object[] { "XXX", "X X", "XXX", 'X', new ItemStack(NibiruBlocks.NIBIRU_PLANKS, 1, 1) });
        RecipeHelper.addRecipe(new ItemStack(NibiruBlocks.ALIEN_BERRY_CHEST), new Object[] { "XXX", "X X", "XXX", 'X', new ItemStack(NibiruBlocks.NIBIRU_PLANKS, 1, 2) });
        RecipeHelper.addRecipe(new ItemStack(NibiruBlocks.INFECTED_SNOW_LAYER, 6), new Object[] { "XXX", 'X', NibiruBlocks.INFECTED_SNOW });
        RecipeHelper.addRecipe(new ItemStack(NibiruBlocks.INFECTED_TORCH, 4), new Object[] { "C", "S", 'S', Items.STICK, 'C', new ItemStack(NibiruItems.NIBIRU_ITEM, 1, 2)});
        RecipeHelper.addRecipe(new ItemStack(NibiruBlocks.NIBIRU_FURNACE), new Object[] { "XXX", "X X", "XXX", 'X', new ItemStack(NibiruBlocks.NIBIRU_BLOCK, 1, 1)});
        RecipeHelper.addRecipe(new ItemStack(NibiruBlocks.HALF_INFECTED_PRISMARINE_SLAB, 6, 0), new Object[] { "XXX", 'X', new ItemStack(NibiruBlocks.INFECTED_PRISMARINE, 1, 0) });
        RecipeHelper.addRecipe(new ItemStack(NibiruBlocks.HALF_INFECTED_PRISMARINE_SLAB, 6, 1), new Object[] { "XXX", 'X', new ItemStack(NibiruBlocks.INFECTED_PRISMARINE, 1, 1) });
        RecipeHelper.addRecipe(new ItemStack(NibiruBlocks.HALF_INFECTED_PRISMARINE_SLAB, 6, 2), new Object[] { "XXX", 'X', new ItemStack(NibiruBlocks.INFECTED_PRISMARINE, 1, 2) });
        RecipeHelper.addRecipe(new ItemStack(NibiruBlocks.HALF_INFECTED_STONE_BRICKS_SLAB, 6, 0), new Object[] { "XXX", 'X', new ItemStack(NibiruBlocks.NIBIRU_BLOCK, 1, 3) });
        RecipeHelper.addRecipe(new ItemStack(NibiruBlocks.HALF_INFECTED_STONE_BRICKS_SLAB, 6, 1), new Object[] { "XXX", 'X', new ItemStack(NibiruBlocks.NIBIRU_BLOCK, 1, 4) });
        RecipeHelper.addRecipe(new ItemStack(NibiruBlocks.HALF_INFECTED_STONE_BRICKS_SLAB, 6, 2), new Object[] { "XXX", 'X', new ItemStack(NibiruBlocks.NIBIRU_BLOCK, 1, 5) });
        RecipeHelper.addRecipe(new ItemStack(NibiruBlocks.HALF_INFECTED_STONE_BRICKS_SLAB, 6, 3), new Object[] { "XXX", 'X', NibiruBlocks.TERRASTONE });
        RecipeHelper.addRecipe(new ItemStack(NibiruBlocks.HALF_INFECTED_STONE_BRICKS_SLAB, 6, 0), new Object[] { "XXX", 'X', new ItemStack(NibiruBlocks.NIBIRU_BLOCK, 1, 6) });
        RecipeHelper.addRecipe(new ItemStack(NibiruBlocks.NIBIRU_BLOCK, 1, 6), new Object[] { "X", "X", 'X', NibiruBlocks.HALF_INFECTED_STONE_BRICKS_SLAB });
        RecipeHelper.addRecipe(new ItemStack(NibiruBlocks.HALF_NIBIRU_SANDSTONE_SLAB, 6, 0), new Object[] { "XXX", 'X', new ItemStack(NibiruBlocks.NIBIRU_SANDSTONE, 1, 0) });
        RecipeHelper.addRecipe(new ItemStack(NibiruBlocks.HALF_NIBIRU_SANDSTONE_SLAB, 6, 1), new Object[] { "XXX", 'X', new ItemStack(NibiruBlocks.NIBIRU_SANDSTONE, 1, 2) });
        RecipeHelper.addRecipe(new ItemStack(NibiruBlocks.NIBIRU_SANDSTONE, 1, 1), new Object[] { "X", "X", 'X', NibiruBlocks.HALF_NIBIRU_SANDSTONE_SLAB });
        RecipeHelper.addRecipe(new ItemStack(NibiruBlocks.NUCLEAR_WASTE_GENERATOR), new Object[] { "PXP", "ATA", "IWI", 'W', new ItemStack(GCItems.basicItem, 1, 14), 'I', new ItemStack(GCBlocks.aluminumWire, 1, 1), 'T', NibiruBlocks.NUCLEAR_WASTE_TANK, 'A', new ItemStack(DionaItems.DIONA_ITEM, 1, 5), 'P', new ItemStack(DionaItems.TIER_4_ROCKET_PART, 1, 0), 'X', new ItemStack(NibiruItems.NIBIRU_ITEM, 1, 1) });
        RecipeHelper.addRecipe(new ItemStack(NibiruBlocks.MULTALIC_CRYSTAL_BLOCK), new Object[] { "XXX", "XXX", "XXX", 'X', new ItemStack(NibiruItems.NIBIRU_ITEM, 1, 1) });
        RecipeHelper.addRecipe(new ItemStack(NibiruBlocks.TERRASTONE_FURNACE), new Object[] { "XXX", "X X", "XXX", 'X', NibiruBlocks.TERRASTONE});
        RecipeHelper.addRecipe(new ItemStack(NibiruBlocks.SEALABLE_NUCLEAR_WASTE_ROD, 16), new Object[] { "TGT", "GWG", "TGT", 'T', new ItemStack(GCBlocks.basicBlock, 1, 4), 'G', new ItemStack(Blocks.STAINED_GLASS, 1, 8), 'W', NibiruItems.NUCLEAR_WASTE_ROD});

        RecipeHelper.addRecipe(new ItemStack(NibiruBlocks.NIBIRU_COBBLESTONE_STAIRS, 4), new Object[] { "X  ", "XX ", "XXX", 'X', new ItemStack(NibiruBlocks.NIBIRU_BLOCK, 1, 1) });
        RecipeHelper.addRecipe(new ItemStack(NibiruBlocks.NIBIRU_DUNGEON_BRICK_STAIRS, 4), new Object[] { "X  ", "XX ", "XXX", 'X', new ItemStack(NibiruBlocks.NIBIRU_BLOCK, 1, 8) });
        RecipeHelper.addRecipe(new ItemStack(NibiruBlocks.NIBIRU_STONE_BRICKS_STAIRS, 4), new Object[] { "X  ", "XX ", "XXX", 'X', new ItemStack(NibiruBlocks.NIBIRU_BLOCK, 1, 2) });
        RecipeHelper.addRecipe(new ItemStack(NibiruBlocks.INFECTED_OAK_STAIRS, 4), new Object[] { "X  ", "XX ", "XXX", 'X', new ItemStack(NibiruBlocks.NIBIRU_PLANKS, 1, 0) });
        RecipeHelper.addRecipe(new ItemStack(NibiruBlocks.INFECTED_DEAD_OAK_STAIRS, 4), new Object[] { "X  ", "XX ", "XXX", 'X', new ItemStack(NibiruBlocks.NIBIRU_PLANKS, 1, 1) });
        RecipeHelper.addRecipe(new ItemStack(NibiruBlocks.ALIEN_BERRY_OAK_STAIRS, 4), new Object[] { "X  ", "XX ", "XXX", 'X', new ItemStack(NibiruBlocks.NIBIRU_PLANKS, 1, 2) });
        RecipeHelper.addRecipe(new ItemStack(NibiruBlocks.INFECTED_PRISMARINE_STAIRS, 4), new Object[] { "X  ", "XX ", "XXX", 'X', new ItemStack(NibiruBlocks.INFECTED_PRISMARINE, 1, 0) });
        RecipeHelper.addRecipe(new ItemStack(NibiruBlocks.INFECTED_PRISMARINE_BRICK_STAIRS, 4), new Object[] { "X  ", "XX ", "XXX", 'X', new ItemStack(NibiruBlocks.INFECTED_PRISMARINE, 1, 1) });
        RecipeHelper.addRecipe(new ItemStack(NibiruBlocks.INFECTED_DARK_PRISMARINE_STAIRS, 4), new Object[] { "X  ", "XX ", "XXX", 'X', new ItemStack(NibiruBlocks.INFECTED_PRISMARINE, 1, 2) });
        RecipeHelper.addRecipe(new ItemStack(NibiruBlocks.INFECTED_VEIN_STONE_BRICKS_STAIRS, 4), new Object[] { "X  ", "XX ", "XXX", 'X', new ItemStack(NibiruBlocks.NIBIRU_BLOCK, 1, 4) });
        RecipeHelper.addRecipe(new ItemStack(NibiruBlocks.CRACKED_INFECTED_STONE_BRICKS_STAIRS, 4), new Object[] { "X  ", "XX ", "XXX", 'X', new ItemStack(NibiruBlocks.NIBIRU_BLOCK, 1, 5) });
        RecipeHelper.addRecipe(new ItemStack(NibiruBlocks.NIBIRU_SMOOTH_SANDSTONE_STAIRS, 4), new Object[] { "X  ", "XX ", "XXX", 'X', new ItemStack(NibiruBlocks.NIBIRU_SANDSTONE, 1, 2) });
        RecipeHelper.addRecipe(new ItemStack(NibiruBlocks.TERRASTONE_STAIRS, 4), new Object[] { "X  ", "XX ", "XXX", 'X', NibiruBlocks.TERRASTONE });
        RecipeHelper.addOreRecipe(new ItemStack(NibiruBlocks.NIBIRU_SANDSTONE_STAIRS, 4), new Object[] { "X  ", "XX ", "XXX", 'X', "nibiruSandstone" });

        RecipeHelper.addShapelessRecipe(new ItemStack(NibiruBlocks.NIBIRU_PLANKS, 4, 0), new ItemStack(NibiruBlocks.NIBIRU_LOG, 1, 0) );
        RecipeHelper.addShapelessRecipe(new ItemStack(NibiruBlocks.NIBIRU_PLANKS, 4, 1), new ItemStack(NibiruBlocks.NIBIRU_LOG, 1, 1) );
        RecipeHelper.addShapelessRecipe(new ItemStack(NibiruBlocks.NIBIRU_PLANKS, 4, 0), new ItemStack(NibiruBlocks.NIBIRU_LOG, 1, 2) );
        RecipeHelper.addShapelessRecipe(new ItemStack(NibiruBlocks.NIBIRU_PLANKS, 4, 2), new ItemStack(NibiruBlocks.NIBIRU_LOG, 1, 3) );
        RecipeHelper.addShapelessRecipe(new ItemStack(NibiruBlocks.NIBIRU_BLOCK, 1, 2), new ItemStack(NibiruBlocks.NIBIRU_BLOCK, 1, 1), NibiruBlocks.INFECTED_VINES );
        RecipeHelper.addShapelessRecipe(new ItemStack(NibiruBlocks.NIBIRU_BLOCK, 1, 4), new ItemStack(NibiruBlocks.NIBIRU_BLOCK, 1, 3), NibiruBlocks.INFECTED_VINES );
        RecipeHelper.addShapelessRecipe(new ItemStack(Items.DYE, 1, EnumDyeColor.GREEN.getDyeDamage()), new ItemStack(NibiruBlocks.NIBIRU_FLOWER, 1, 0) );
        RecipeHelper.addShapelessRecipe(new ItemStack(Items.DYE, 1, EnumDyeColor.LIGHT_BLUE.getDyeDamage()), new ItemStack(NibiruBlocks.NIBIRU_FLOWER, 1, 1) );
        RecipeHelper.addShapelessRecipe(new ItemStack(Items.DYE, 1, EnumDyeColor.PINK.getDyeDamage()), new ItemStack(NibiruBlocks.NIBIRU_FLOWER, 1, 2) );
        RecipeHelper.addShapelessRecipe(new ItemStack(Items.DYE, 1, EnumDyeColor.ORANGE.getDyeDamage()), new ItemStack(NibiruBlocks.NIBIRU_FLOWER, 1, 3) );
        RecipeHelper.addShapelessRecipe(new ItemStack(MPItems.DYE), new ItemStack(NibiruBlocks.NIBIRU_FLOWER, 1, 4) );
        RecipeHelper.addShapelessRecipe(new ItemStack(Items.DYE, 2, EnumDyeColor.ORANGE.getDyeDamage()), new ItemStack(NibiruBlocks.NIBIRU_DOUBLE_PLANT, 1, 0) );
        RecipeHelper.addShapelessRecipe(new ItemStack(NibiruItems.NIBIRU_DUNGEON_KEY, 1, 0), new ItemStack(NibiruItems.NIBIRU_DUNGEON_KEY, 1, 1), new ItemStack(NibiruItems.NIBIRU_DUNGEON_KEY, 1, 2) );
    }

    private static void addItemRecipe()
    {
        RecipeHelper.addRecipe(new ItemStack(Items.PAPER, 3), new Object[] { "XXX", 'X', NibiruItems.INFECTED_SUGAR_CANE });
        RecipeHelper.addRecipe(new ItemStack(NibiruItems.NIBIRU_FRUITS, 1, 1), new Object[] {"XXX", "XAX", "XXX", 'X', Items.GOLD_INGOT, 'A', new ItemStack(NibiruItems.NIBIRU_FRUITS, 1, 0)});
        RecipeHelper.addRecipe(new ItemStack(NibiruItems.NIBIRU_FRUITS, 1, 2), new Object[] {"XXX", "XAX", "XXX", 'X', Blocks.GOLD_BLOCK, 'A', new ItemStack(NibiruItems.NIBIRU_FRUITS, 1, 0)});
        RecipeHelper.addRecipe(new ItemStack(NibiruItems.NIBIRU_FRUITS, 1, 5), new Object[] {"XXX", "XAX", "XXX", 'X', Items.GOLD_INGOT, 'A', new ItemStack(NibiruItems.NIBIRU_FRUITS, 1, 4)});
        RecipeHelper.addRecipe(new ItemStack(Items.BREAD), new Object[] { "XXX", 'X', NibiruItems.INFECTED_WHEAT });
        RecipeHelper.addRecipe(new ItemStack(NibiruItems.VEIN_EYE), new Object[] { " V ", "VXV", " V ", 'X', Items.ENDER_EYE, 'V', NibiruBlocks.INFECTED_VINES });
        RecipeHelper.addRecipe(new ItemStack(NibiruItems.INFECTED_DOOR, 3), new Object[] { "XX", "XX", "XX", 'X', new ItemStack(NibiruBlocks.NIBIRU_PLANKS, 1, 0) });
        RecipeHelper.addRecipe(new ItemStack(NibiruItems.INFECTED_DEAD_OAK_DOOR, 3), new Object[] { "XX", "XX", "XX", 'X', new ItemStack(NibiruBlocks.NIBIRU_PLANKS, 1, 1) });
        RecipeHelper.addRecipe(new ItemStack(NibiruItems.ALIEN_BERRY_OAK_DOOR, 3), new Object[] { "XX", "XX", "XX", 'X', new ItemStack(NibiruBlocks.NIBIRU_PLANKS, 1, 2) });
        RecipeHelper.addRecipe(new ItemStack(NibiruItems.WASTE_ROD_PICKER), new Object[] { " TP", " TT", "T  ", 'T', new ItemStack(NibiruItems.NIBIRU_ITEM, 1, 3), 'P', new ItemStack(ChalosItems.CHALOS_ITEM, 1, 3) });
        RecipeHelper.addShapelessRecipe(new ItemStack(NibiruItems.NIBIRU_ITEM, 9, 0), new ItemStack(NibiruBlocks.NIBIRU_BLOCK, 1, 7));
        RecipeHelper.addShapelessRecipe(new ItemStack(NibiruItems.NIBIRU_ITEM, 9, 1), NibiruBlocks.MULTALIC_CRYSTAL_BLOCK);
        RecipeHelper.addShapelessRecipe(new ItemStack(Items.SUGAR), NibiruItems.INFECTED_SUGAR_CANE);

        RecipeHelper.addRecipe(new ItemStack(NibiruItems.MULTALIC_CRYSTAL_HELMET, 1, 0), new Object[] { "XXX", "X X", 'X', new ItemStack(NibiruItems.NIBIRU_ITEM, 1, 1) });
        RecipeHelper.addRecipe(new ItemStack(NibiruItems.MULTALIC_CRYSTAL_CHESTPLATE, 1, 0), new Object[] { "X X", "XXX", "XXX", 'X', new ItemStack(NibiruItems.NIBIRU_ITEM, 1, 1) });
        RecipeHelper.addRecipe(new ItemStack(NibiruItems.MULTALIC_CRYSTAL_LEGGINGS, 1, 0), new Object[] { "XXX", "X X", "X X", 'X', new ItemStack(NibiruItems.NIBIRU_ITEM, 1, 1) });
        RecipeHelper.addRecipe(new ItemStack(NibiruItems.MULTALIC_CRYSTAL_BOOTS, 1, 0), new Object[] { "X X", "X X", 'X', new ItemStack(NibiruItems.NIBIRU_ITEM, 1, 1) });
        RecipeHelper.addRecipe(new ItemStack(NibiruItems.BREATHABLE_MULTALIC_CRYSTAL_HELMET, 1, 0), new Object[] { "XXX", "XYX", 'Y', new ItemStack(GCItems.oxMask), 'X', new ItemStack(NibiruItems.NIBIRU_ITEM, 1, 1) });

        RecipeHelper.addRecipe(new ItemStack(NibiruItems.INFECTED_WOOD_HOE), new Object[] { "XX", " Y", " Y", 'X', new ItemStack(NibiruBlocks.NIBIRU_PLANKS, 1, 0), 'Y', Items.STICK });
        RecipeHelper.addRecipe(new ItemStack(NibiruItems.INFECTED_WOOD_AXE), new Object[] { "XX", "XY", " Y", 'X', new ItemStack(NibiruBlocks.NIBIRU_PLANKS, 1, 0), 'Y', Items.STICK });
        RecipeHelper.addRecipe(new ItemStack(NibiruItems.INFECTED_WOOD_PICKAXE), new Object[] { "XXX", " Y ", " Y ", 'X', new ItemStack(NibiruBlocks.NIBIRU_PLANKS, 1, 0), 'Y', Items.STICK });
        RecipeHelper.addRecipe(new ItemStack(NibiruItems.INFECTED_WOOD_SWORD), new Object[] { "X", "X", "Y", 'X', new ItemStack(NibiruBlocks.NIBIRU_PLANKS, 1, 0), 'Y', Items.STICK });
        RecipeHelper.addRecipe(new ItemStack(NibiruItems.INFECTED_WOOD_SHOVEL), new Object[] { "X", "Y", "Y", 'X', new ItemStack(NibiruBlocks.NIBIRU_PLANKS, 1, 0), 'Y', Items.STICK });
        RecipeHelper.addRecipe(new ItemStack(NibiruItems.INFECTED_WOOD_HOE), new Object[] { "XX", " Y", " Y", 'X', new ItemStack(NibiruBlocks.NIBIRU_PLANKS, 1, 1), 'Y', Items.STICK });
        RecipeHelper.addRecipe(new ItemStack(NibiruItems.INFECTED_WOOD_AXE), new Object[] { "XX", "XY", " Y", 'X', new ItemStack(NibiruBlocks.NIBIRU_PLANKS, 1, 1), 'Y', Items.STICK });
        RecipeHelper.addRecipe(new ItemStack(NibiruItems.INFECTED_WOOD_PICKAXE), new Object[] { "XXX", " Y ", " Y ", 'X', new ItemStack(NibiruBlocks.NIBIRU_PLANKS, 1, 1), 'Y', Items.STICK });
        RecipeHelper.addRecipe(new ItemStack(NibiruItems.INFECTED_WOOD_SWORD), new Object[] { "X", "X", "Y", 'X', new ItemStack(NibiruBlocks.NIBIRU_PLANKS, 1, 1), 'Y', Items.STICK });
        RecipeHelper.addRecipe(new ItemStack(NibiruItems.INFECTED_WOOD_SHOVEL), new Object[] { "X", "Y", "Y", 'X', new ItemStack(NibiruBlocks.NIBIRU_PLANKS, 1, 1), 'Y', Items.STICK });
        RecipeHelper.addRecipe(new ItemStack(NibiruItems.ALIEN_BERRY_WOOD_HOE), new Object[] { "XX", " Y", " Y", 'X', new ItemStack(NibiruBlocks.NIBIRU_PLANKS, 1, 2), 'Y', Items.STICK });
        RecipeHelper.addRecipe(new ItemStack(NibiruItems.ALIEN_BERRY_WOOD_AXE), new Object[] { "XX", "XY", " Y", 'X', new ItemStack(NibiruBlocks.NIBIRU_PLANKS, 1, 2), 'Y', Items.STICK });
        RecipeHelper.addRecipe(new ItemStack(NibiruItems.ALIEN_BERRY_WOOD_PICKAXE), new Object[] { "XXX", " Y ", " Y ", 'X', new ItemStack(NibiruBlocks.NIBIRU_PLANKS, 1, 2), 'Y', Items.STICK });
        RecipeHelper.addRecipe(new ItemStack(NibiruItems.ALIEN_BERRY_WOOD_SWORD), new Object[] { "X", "X", "Y", 'X', new ItemStack(NibiruBlocks.NIBIRU_PLANKS, 1, 2), 'Y', Items.STICK });
        RecipeHelper.addRecipe(new ItemStack(NibiruItems.ALIEN_BERRY_WOOD_SHOVEL), new Object[] { "X", "Y", "Y", 'X', new ItemStack(NibiruBlocks.NIBIRU_PLANKS, 1, 2), 'Y', Items.STICK });
        RecipeHelper.addRecipe(new ItemStack(NibiruItems.NIBIRU_STONE_HOE), new Object[] { "XX", " Y", " Y", 'X', new ItemStack(NibiruBlocks.NIBIRU_BLOCK, 1, 1), 'Y', Items.STICK });
        RecipeHelper.addRecipe(new ItemStack(NibiruItems.NIBIRU_STONE_AXE), new Object[] { "XX", "XY", " Y", 'X', new ItemStack(NibiruBlocks.NIBIRU_BLOCK, 1, 1), 'Y', Items.STICK });
        RecipeHelper.addRecipe(new ItemStack(NibiruItems.NIBIRU_STONE_PICKAXE), new Object[] { "XXX", " Y ", " Y ", 'X', new ItemStack(NibiruBlocks.NIBIRU_BLOCK, 1, 1), 'Y', Items.STICK });
        RecipeHelper.addRecipe(new ItemStack(NibiruItems.NIBIRU_STONE_SWORD), new Object[] { "X", "X", "Y", 'X', new ItemStack(NibiruBlocks.NIBIRU_BLOCK, 1, 1), 'Y', Items.STICK });
        RecipeHelper.addRecipe(new ItemStack(NibiruItems.NIBIRU_STONE_SHOVEL), new Object[] { "X", "Y", "Y", 'X', new ItemStack(NibiruBlocks.NIBIRU_BLOCK, 1, 1), 'Y', Items.STICK });
        RecipeHelper.addRecipe(new ItemStack(NibiruItems.MULTALIC_CRYSTAL_HOE), new Object[] { "XX", " Y", " Y", 'X', new ItemStack(NibiruItems.NIBIRU_ITEM, 1, 1), 'Y', Items.STICK });
        RecipeHelper.addRecipe(new ItemStack(NibiruItems.MULTALIC_CRYSTAL_AXE), new Object[] { "XX", "XY", " Y", 'X', new ItemStack(NibiruItems.NIBIRU_ITEM, 1, 1), 'Y', Items.STICK });
        RecipeHelper.addRecipe(new ItemStack(NibiruItems.MULTALIC_CRYSTAL_PICKAXE), new Object[] { "XXX", " Y ", " Y ", 'X', new ItemStack(NibiruItems.NIBIRU_ITEM, 1, 1), 'Y', Items.STICK });
        RecipeHelper.addRecipe(new ItemStack(NibiruItems.MULTALIC_CRYSTAL_SWORD), new Object[] { "X", "X", "Y", 'X', new ItemStack(NibiruItems.NIBIRU_ITEM, 1, 1), 'Y', Items.STICK });
        RecipeHelper.addRecipe(new ItemStack(NibiruItems.MULTALIC_CRYSTAL_SHOVEL), new Object[] { "X", "Y", "Y", 'X', new ItemStack(NibiruItems.NIBIRU_ITEM, 1, 1), 'Y', Items.STICK });
    }

    private static void addBlockSmelting()
    {
        RecipeHelper.addSmeltingRecipe(new ItemStack(NibiruBlocks.NIBIRU_BLOCK, 1, 1), new ItemStack(NibiruBlocks.NIBIRU_BLOCK, 1, 0), 0.1F);
        RecipeHelper.addSmeltingRecipe(new ItemStack(NibiruBlocks.NIBIRU_BLOCK, 1, 3), new ItemStack(NibiruBlocks.NIBIRU_BLOCK, 1, 5), 0.1F);
        RecipeHelper.addSmeltingRecipe(new ItemStack(NibiruBlocks.INFECTED_SPONGE, 1, 1), new ItemStack(NibiruBlocks.INFECTED_SPONGE, 1, 0), 0.15F);
        RecipeHelper.addSmeltingRecipe(new ItemStack(NibiruBlocks.INFECTED_CLAY), new ItemStack(Blocks.HARDENED_CLAY), 0.35F);
    }

    private static void addItemSmelting()
    {
        RecipeHelper.addSmeltingRecipe(new ItemStack(NibiruBlocks.NIBIRU_ORE, 1, 0), new ItemStack(NibiruItems.NIBIRU_ITEM, 1, 2), 0.1F);
        RecipeHelper.addSmeltingRecipe(new ItemStack(NibiruBlocks.NIBIRU_ORE, 1, 1), new ItemStack(Items.IRON_INGOT), 0.7F);
        RecipeHelper.addSmeltingRecipe(new ItemStack(NibiruBlocks.NIBIRU_ORE, 1, 2), new ItemStack(Items.GOLD_INGOT), 1.0F);
        RecipeHelper.addSmeltingRecipe(new ItemStack(NibiruBlocks.NIBIRU_ORE, 1, 3), new ItemStack(Items.DIAMOND), 1.0F);
        RecipeHelper.addSmeltingRecipe(new ItemStack(NibiruBlocks.NIBIRU_ORE, 1, 4), new ItemStack(Items.REDSTONE), 0.7F);
        RecipeHelper.addSmeltingRecipe(new ItemStack(NibiruBlocks.NIBIRU_ORE, 1, 5), new ItemStack(Items.DYE, 1, 4), 0.2F);
        RecipeHelper.addSmeltingRecipe(new ItemStack(NibiruBlocks.NIBIRU_ORE, 1, 6), new ItemStack(Items.EMERALD), 1.0F);
        RecipeHelper.addSmeltingRecipe(new ItemStack(NibiruBlocks.NIBIRU_ORE, 1, 7), new ItemStack(GCItems.basicItem, 1, 5), 0.7F);
        RecipeHelper.addSmeltingRecipe(new ItemStack(NibiruBlocks.NIBIRU_ORE, 1, 8), new ItemStack(GCItems.basicItem, 1, 3), 0.7F);
        RecipeHelper.addSmeltingRecipe(new ItemStack(NibiruBlocks.NIBIRU_ORE, 1, 9), new ItemStack(GCItems.basicItem, 1, 4), 0.7F);
        RecipeHelper.addSmeltingRecipe(new ItemStack(NibiruBlocks.NIBIRU_ORE, 1, 10), new ItemStack(GCItems.basicItem, 1, 2), 0.7F);
        RecipeHelper.addSmeltingRecipe(new ItemStack(NibiruBlocks.NIBIRU_ORE, 1, 11), new ItemStack(NibiruItems.NIBIRU_ITEM, 1, 0), 1.0F);
        RecipeHelper.addSmeltingRecipe(new ItemStack(NibiruBlocks.INFECTED_CACTUS), new ItemStack(Items.DYE, 1, EnumDyeColor.GREEN.getDyeDamage()), 0.2F);
        RecipeHelper.addSmeltingRecipe(new ItemStack(NibiruItems.INFECTED_CLAY_BALL), new ItemStack(Items.BRICK), 0.3F);
        RecipeHelper.addSmeltingRecipe(new ItemStack(NibiruBlocks.NIBIRU_LOG, 1, 0), new ItemStack(NibiruItems.NIBIRU_ITEM, 1, 5), 0.15F);
        RecipeHelper.addSmeltingRecipe(new ItemStack(NibiruBlocks.NIBIRU_LOG, 1, 1), new ItemStack(NibiruItems.NIBIRU_ITEM, 1, 5), 0.15F);
        RecipeHelper.addSmeltingRecipe(new ItemStack(NibiruBlocks.NIBIRU_LOG, 1, 2), new ItemStack(NibiruItems.NIBIRU_ITEM, 1, 5), 0.15F);
        RecipeHelper.addSmeltingRecipe(new ItemStack(NibiruBlocks.NIBIRU_LOG, 1, 3), new ItemStack(NibiruItems.NIBIRU_ITEM, 1, 5), 0.15F);
        RecipeHelper.addSmeltingRecipe(new ItemStack(NibiruItems.NIBIRU_FOOD, 1, 0), new ItemStack(NibiruItems.NIBIRU_FOOD, 1, 1), 0.35F);
    }

    private static void addRocketRecipe()
    {
        HashMap<Integer, ItemStack> input = Maps.newHashMap();
        input.put(1, new ItemStack(DionaItems.TIER_4_ROCKET_PART, 1, 2));
        input.put(2, new ItemStack(NibiruItems.NIBIRU_ITEM, 1, 4));
        input.put(3, new ItemStack(NibiruItems.NIBIRU_ITEM, 1, 4));
        input.put(4, new ItemStack(NibiruItems.NIBIRU_ITEM, 1, 4));
        input.put(5, new ItemStack(NibiruItems.NIBIRU_ITEM, 1, 4));
        input.put(6, new ItemStack(NibiruItems.NIBIRU_ITEM, 1, 4));
        input.put(7, new ItemStack(NibiruItems.NIBIRU_ITEM, 1, 4));
        input.put(8, new ItemStack(NibiruItems.NIBIRU_ITEM, 1, 4));
        input.put(9, new ItemStack(NibiruItems.NIBIRU_ITEM, 1, 4));
        input.put(10, new ItemStack(NibiruItems.NIBIRU_ITEM, 1, 4));
        input.put(11, new ItemStack(NibiruItems.NIBIRU_ITEM, 1, 4));
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
        Tier6RocketRecipes.addRocketRecipe(new ItemStack(NibiruItems.TIER_6_ROCKET, 1, 0), input);

        HashMap<Integer, ItemStack> input2 = Maps.newHashMap(input);
        input2.put(19, new ItemStack(Blocks.CHEST));
        input2.put(20, null);
        input2.put(21, null);
        Tier6RocketRecipes.addRocketRecipe(new ItemStack(NibiruItems.TIER_6_ROCKET, 1, 1), input2);

        input2 = Maps.newHashMap(input);
        input2.put(19, null);
        input2.put(20, new ItemStack(Blocks.CHEST));
        input2.put(21, null);
        Tier6RocketRecipes.addRocketRecipe(new ItemStack(NibiruItems.TIER_6_ROCKET, 1, 1), input2);

        input2 = Maps.newHashMap(input);
        input2.put(19, null);
        input2.put(20, null);
        input2.put(21, new ItemStack(Blocks.CHEST));
        Tier6RocketRecipes.addRocketRecipe(new ItemStack(NibiruItems.TIER_6_ROCKET, 1, 1), input2);

        input2 = Maps.newHashMap(input);
        input2.put(19, new ItemStack(Blocks.CHEST));
        input2.put(20, new ItemStack(Blocks.CHEST));
        input2.put(21, null);
        Tier6RocketRecipes.addRocketRecipe(new ItemStack(NibiruItems.TIER_6_ROCKET, 1, 2), input2);

        input2 = Maps.newHashMap(input);
        input2.put(19, new ItemStack(Blocks.CHEST));
        input2.put(20, null);
        input2.put(21, new ItemStack(Blocks.CHEST));
        Tier6RocketRecipes.addRocketRecipe(new ItemStack(NibiruItems.TIER_6_ROCKET, 1, 2), input2);

        input2 = Maps.newHashMap(input);
        input2.put(19, null);
        input2.put(20, new ItemStack(Blocks.CHEST));
        input2.put(21, new ItemStack(Blocks.CHEST));
        Tier6RocketRecipes.addRocketRecipe(new ItemStack(NibiruItems.TIER_6_ROCKET, 1, 2), input2);

        input2 = Maps.newHashMap(input);
        input2.put(19, new ItemStack(Blocks.CHEST));
        input2.put(20, new ItemStack(Blocks.CHEST));
        input2.put(21, new ItemStack(Blocks.CHEST));
        Tier6RocketRecipes.addRocketRecipe(new ItemStack(NibiruItems.TIER_6_ROCKET, 1, 3), input2);
    }
}