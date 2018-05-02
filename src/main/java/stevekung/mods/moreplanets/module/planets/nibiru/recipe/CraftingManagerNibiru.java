package stevekung.mods.moreplanets.module.planets.nibiru.recipe;

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
import stevekung.mods.moreplanets.utils.helper.RecipeHelper;

public class CraftingManagerNibiru
{
    public static void init()
    {
        CraftingManagerNibiru.addBlockRecipe();
        CraftingManagerNibiru.addItemRecipe();
        CraftingManagerNibiru.addBlockSmelting();
        CraftingManagerNibiru.addItemSmelting();
    }

    private static void addBlockRecipe()
    {
        RecipeHelper.addRecipe(new ItemStack(NibiruBlocks.INFERUMITE_BLOCK), new Object[] { "XXX", "XXX", "XXX", 'X', NibiruItems.INFERUMITE_CRYSTAL });
        RecipeHelper.addRecipe(new ItemStack(NibiruBlocks.INFECTED_CRAFTING_TABLE), "crafting_table", new Object[] { "XX", "XX", 'X', NibiruBlocks.INFECTED_OAK_PLANKS });
        RecipeHelper.addRecipe(new ItemStack(NibiruBlocks.ALIEN_BERRY_CRAFTING_TABLE), "crafting_table", new Object[] { "XX", "XX", 'X', NibiruBlocks.ALIEN_BERRY_OAK_PLANKS });
        RecipeHelper.addOreRecipe(new ItemStack(NibiruBlocks.INFECTED_COARSE_DIRT, 4), new Object[] { "DG", "GD", 'D', NibiruBlocks.INFECTED_DIRT, 'G', "gravel" });
        RecipeHelper.addRecipe(new ItemStack(NibiruBlocks.INFECTED_STONE_BRICKS, 4), new Object[] { "XX", "XX", 'X', NibiruBlocks.NIBIRU_ROCK });
        RecipeHelper.addRecipe(new ItemStack(NibiruBlocks.INFECTED_SANDSTONE, 4), new Object[] { "XX", "XX", 'X', NibiruBlocks.INFECTED_SAND });
        RecipeHelper.addRecipe(new ItemStack(NibiruBlocks.INFECTED_CUT_SANDSTONE, 4), new Object[] { "XX", "XX", 'X', NibiruBlocks.INFECTED_SANDSTONE });
        RecipeHelper.addRecipe(new ItemStack(NibiruBlocks.INFECTED_OAK_BOOKSHELF), new Object[] { "WWW", "BBB", "WWW", 'W', NibiruBlocks.INFECTED_OAK_PLANKS, 'B', Items.BOOK });
        RecipeHelper.addRecipe(new ItemStack(NibiruBlocks.ALIEN_BERRY_OAK_BOOKSHELF), new Object[] { "WWW", "BBB", "WWW", 'W', NibiruBlocks.ALIEN_BERRY_OAK_PLANKS, 'B', Items.BOOK });
        RecipeHelper.addRecipe(new ItemStack(NibiruBlocks.INFECTED_SNOW), new Object[] { "XX", "XX", 'X', NibiruItems.INFECTED_SNOWBALL });
        RecipeHelper.addRecipe(new ItemStack(NibiruBlocks.INFECTED_MELON_BLOCK), new Object[] { "XXX", "XXX", "XXX", 'X', NibiruItems.INFECTED_MELON });
        RecipeHelper.addRecipe(new ItemStack(NibiruBlocks.INFECTED_CLAY), new Object[] { "XX", "XX", 'X', NibiruItems.INFECTED_CLAY_BALL });
        RecipeHelper.addRecipe(new ItemStack(NibiruBlocks.INFECTED_PRISMARINE, 4), new Object[] { "SS", "SS", 'S', NibiruItems.INFECTED_PRISMARINE_SHARD });
        RecipeHelper.addRecipe(new ItemStack(NibiruBlocks.INFECTED_PRISMARINE_BRICKS, 1), new Object[] { "SSS", "SSS", "SSS", 'S', NibiruItems.INFECTED_PRISMARINE_SHARD });
        RecipeHelper.addRecipe(new ItemStack(NibiruBlocks.INFECTED_DARK_PRISMARINE, 1), new Object[] { "SSS", "SIS", "SSS", 'S', NibiruItems.INFECTED_PRISMARINE_SHARD, 'I', new ItemStack(Items.DYE, 1, EnumDyeColor.BLACK.getDyeDamage()) });
        RecipeHelper.addRecipe(new ItemStack(NibiruBlocks.INFECTED_SEA_LANTERN), new Object[] { "PSP", "SSS", "PSP", 'P', NibiruItems.INFECTED_PRISMARINE_SHARD, 'S', NibiruItems.INFECTED_PRISMARINE_CRYSTALS });
        RecipeHelper.addRecipe(new ItemStack(NibiruBlocks.INFECTED_OAK_FENCE, 3), "wooden_fence", new Object[] { "XSX", "XSX", 'X', NibiruBlocks.INFECTED_OAK_PLANKS, 'S', "stickWood" });
        RecipeHelper.addRecipe(new ItemStack(NibiruBlocks.ALIEN_BERRY_OAK_FENCE, 3), "wooden_fence", new Object[] { "XSX", "XSX", 'X', NibiruBlocks.ALIEN_BERRY_OAK_PLANKS, 'S', "stickWood" });
        RecipeHelper.addRecipe(new ItemStack(NibiruBlocks.INFECTED_OAK_FENCE_GATE), "wooden_fence_gate", new Object[] { "SXS", "SXS", 'X', NibiruBlocks.INFECTED_OAK_PLANKS, 'S', "stickWood" });
        RecipeHelper.addRecipe(new ItemStack(NibiruBlocks.ALIEN_BERRY_OAK_FENCE_GATE), "wooden_fence_gate", new Object[] { "SXS", "SXS", 'X', NibiruBlocks.ALIEN_BERRY_OAK_PLANKS, 'S', "stickWood" });
        RecipeHelper.addRecipe(new ItemStack(NibiruBlocks.INFECTED_CHEST), new Object[] { "XXX", "X X", "XXX", 'X', NibiruBlocks.INFECTED_OAK_PLANKS });
        RecipeHelper.addRecipe(new ItemStack(NibiruBlocks.ALIEN_BERRY_CHEST), new Object[] { "XXX", "X X", "XXX", 'X', NibiruBlocks.ALIEN_BERRY_OAK_PLANKS });
        RecipeHelper.addRecipe(new ItemStack(NibiruBlocks.INFECTED_SNOW_LAYER, 6), new Object[] { "XXX", 'X', NibiruBlocks.INFECTED_SNOW });
        RecipeHelper.addRecipe(new ItemStack(NibiruBlocks.INFECTED_TORCH, 4), new Object[] { "C", "S", 'S', "stickWood", 'C', NibiruItems.INFECTED_COAL});
        RecipeHelper.addRecipe(new ItemStack(NibiruBlocks.INFECTED_TORCH, 4), null, "infected_torch_from_charcoal", new Object[] { "C", "S", 'S', "stickWood", 'C', NibiruItems.INFECTED_CHARCOAL});
        RecipeHelper.addRecipe(new ItemStack(NibiruBlocks.INFECTED_FURNACE), new Object[] { "XXX", "X X", "XXX", 'X', NibiruBlocks.NIBIRU_COBBLESTONE});
        RecipeHelper.addRecipe(new ItemStack(NibiruBlocks.INFECTED_PRISMARINE_SLAB, 6), new Object[] { "XXX", 'X', NibiruBlocks.INFECTED_PRISMARINE });
        RecipeHelper.addRecipe(new ItemStack(NibiruBlocks.INFECTED_PRISMARINE_BRICKS_SLAB, 6), new Object[] { "XXX", 'X', NibiruBlocks.INFECTED_PRISMARINE_BRICKS });
        RecipeHelper.addRecipe(new ItemStack(NibiruBlocks.INFECTED_DARK_PRISMARINE_SLAB, 6), new Object[] { "XXX", 'X', NibiruBlocks.INFECTED_DARK_PRISMARINE });
        RecipeHelper.addRecipe(new ItemStack(NibiruBlocks.INFECTED_STONE_BRICKS_SLAB, 6), new Object[] { "XXX", 'X', NibiruBlocks.INFECTED_STONE_BRICKS });
        RecipeHelper.addRecipe(new ItemStack(NibiruBlocks.INFECTED_VEIN_STONE_BRICKS_SLAB, 6), new Object[] { "XXX", 'X', NibiruBlocks.INFECTED_VEIN_STONE_BRICKS });
        RecipeHelper.addRecipe(new ItemStack(NibiruBlocks.INFECTED_CRACKED_STONE_BRICKS_SLAB, 6), new Object[] { "XXX", 'X', NibiruBlocks.INFECTED_CRACKED_STONE_BRICKS });
        RecipeHelper.addRecipe(new ItemStack(NibiruBlocks.TERRASTONE_SLAB, 6), new Object[] { "XXX", 'X', NibiruBlocks.TERRASTONE });
        RecipeHelper.addRecipe(new ItemStack(NibiruBlocks.INFECTED_CHISELED_STONE_BRICKS), new Object[] { "X", "X", 'X', NibiruBlocks.INFECTED_STONE_BRICKS_SLAB });
        RecipeHelper.addRecipe(new ItemStack(NibiruBlocks.INFECTED_SANDSTONE_SLAB, 6), new Object[] { "XXX", 'X', NibiruBlocks.INFECTED_SANDSTONE });
        RecipeHelper.addRecipe(new ItemStack(NibiruBlocks.INFECTED_CUT_SANDSTONE_SLAB, 6), new Object[] { "XXX", 'X', NibiruBlocks.INFECTED_CUT_SANDSTONE });
        RecipeHelper.addRecipe(new ItemStack(NibiruBlocks.INFECTED_CHISELED_SANDSTONE), new Object[] { "X", "X", 'X', NibiruBlocks.INFECTED_SANDSTONE });
        RecipeHelper.addRecipe(new ItemStack(NibiruBlocks.NUCLEAR_WASTE_GENERATOR), new Object[] { "PXP", "ATA", "IWI", 'W', new ItemStack(GCItems.basicItem, 1, 14), 'I', new ItemStack(GCBlocks.aluminumWire, 1, 1), 'T', NibiruBlocks.NUCLEAR_WASTE_TANK, 'A', DionaItems.ALIEN_MINER_PART, 'P', new ItemStack(AsteroidsItems.basicItem, 1, 5), 'X', NibiruItems.MULTALIC_CRYSTAL_PIECES });
        RecipeHelper.addRecipe(new ItemStack(NibiruBlocks.MULTALIC_CRYSTAL_BLOCK), new Object[] { "XXX", "XXX", "XXX", 'X', NibiruItems.MULTALIC_CRYSTAL_PIECES });
        RecipeHelper.addRecipe(new ItemStack(NibiruBlocks.TERRASTONE_FURNACE), new Object[] { "XXX", "X X", "XXX", 'X', NibiruBlocks.TERRASTONE});
        RecipeHelper.addRecipe(new ItemStack(NibiruBlocks.SEALABLE_NUCLEAR_WASTE_ROD, 16), new Object[] { "TGT", "GWG", "TGT", 'T', new ItemStack(GCBlocks.basicBlock, 1, 4), 'G', "blockGlassLightGray", 'W', NibiruItems.NUCLEAR_WASTE_ROD});
        RecipeHelper.addRecipe(new ItemStack(NibiruBlocks.NIBIRU_COBBLESTONE_STAIRS, 4), "cobblestone_stairs", new Object[] { "X  ", "XX ", "XXX", 'X', NibiruBlocks.NIBIRU_COBBLESTONE });
        RecipeHelper.addRecipe(new ItemStack(NibiruBlocks.NIBIRU_DUNGEON_BRICK_STAIRS, 4), "dungeon_brick_stairs", new Object[] { "X  ", "XX ", "XXX", 'X', NibiruBlocks.NIBIRU_DUNGEON_BRICK });
        RecipeHelper.addRecipe(new ItemStack(NibiruBlocks.INFECTED_STONE_BRICKS_STAIRS, 4), new Object[] { "X  ", "XX ", "XXX", 'X', NibiruBlocks.INFECTED_STONE_BRICKS });
        RecipeHelper.addRecipe(new ItemStack(NibiruBlocks.INFECTED_OAK_STAIRS, 4), "wooden_stairs", new Object[] { "X  ", "XX ", "XXX", 'X', NibiruBlocks.INFECTED_OAK_PLANKS });
        RecipeHelper.addRecipe(new ItemStack(NibiruBlocks.ALIEN_BERRY_OAK_STAIRS, 4), "wooden_stairs", new Object[] { "X  ", "XX ", "XXX", 'X', NibiruBlocks.ALIEN_BERRY_OAK_PLANKS });
        RecipeHelper.addRecipe(new ItemStack(NibiruBlocks.INFECTED_PRISMARINE_STAIRS, 4), new Object[] { "X  ", "XX ", "XXX", 'X', NibiruBlocks.INFECTED_PRISMARINE });
        RecipeHelper.addRecipe(new ItemStack(NibiruBlocks.INFECTED_PRISMARINE_BRICKS_STAIRS, 4), new Object[] { "X  ", "XX ", "XXX", 'X', NibiruBlocks.INFECTED_PRISMARINE_BRICKS });
        RecipeHelper.addRecipe(new ItemStack(NibiruBlocks.INFECTED_DARK_PRISMARINE_STAIRS, 4), new Object[] { "X  ", "XX ", "XXX", 'X', NibiruBlocks.INFECTED_DARK_PRISMARINE });
        RecipeHelper.addRecipe(new ItemStack(NibiruBlocks.INFECTED_VEIN_STONE_BRICKS_STAIRS, 4), new Object[] { "X  ", "XX ", "XXX", 'X', NibiruBlocks.INFECTED_VEIN_STONE_BRICKS });
        RecipeHelper.addRecipe(new ItemStack(NibiruBlocks.CRACKED_INFECTED_STONE_BRICKS_STAIRS, 4), new Object[] { "X  ", "XX ", "XXX", 'X', NibiruBlocks.INFECTED_CRACKED_STONE_BRICKS });
        RecipeHelper.addRecipe(new ItemStack(NibiruBlocks.INFECTED_CUT_SANDSTONE_STAIRS, 4), new Object[] { "X  ", "XX ", "XXX", 'X', NibiruBlocks.INFECTED_CUT_SANDSTONE });
        RecipeHelper.addRecipe(new ItemStack(NibiruBlocks.TERRASTONE_STAIRS, 4), new Object[] { "X  ", "XX ", "XXX", 'X', NibiruBlocks.TERRASTONE });
        RecipeHelper.addOreRecipe(new ItemStack(NibiruBlocks.INFECTED_SANDSTONE_STAIRS, 4), new Object[] { "X  ", "XX ", "XXX", 'X', "nibiruSandstone" });

        RecipeHelper.addShapelessRecipe(new ItemStack(NibiruBlocks.INFECTED_OAK_PLANKS, 4), "planks", NibiruBlocks.INFECTED_OAK_LOG );
        RecipeHelper.addShapelessRecipe(new ItemStack(NibiruBlocks.INFECTED_OAK_PLANKS, 4), "planks", "infected_oak_planks_from_jungle_log", NibiruBlocks.INFECTED_JUNGLE_LOG );
        RecipeHelper.addShapelessRecipe(new ItemStack(NibiruBlocks.ALIEN_BERRY_OAK_PLANKS, 4), "planks", NibiruBlocks.ALIEN_BERRY_OAK_LOG );
        RecipeHelper.addShapelessRecipe(new ItemStack(NibiruBlocks.NIBIRU_VEIN_COBBLESTONE), NibiruBlocks.NIBIRU_COBBLESTONE, NibiruBlocks.INFECTED_VINES );
        RecipeHelper.addShapelessRecipe(new ItemStack(NibiruBlocks.INFECTED_VEIN_STONE_BRICKS), NibiruBlocks.INFECTED_STONE_BRICKS, NibiruBlocks.INFECTED_VINES );
        RecipeHelper.addShapelessRecipe(new ItemStack(Items.DYE, 1, EnumDyeColor.GREEN.getDyeDamage()), null, "green_dye_from_pure_herb", NibiruBlocks.PURE_HERB );
        RecipeHelper.addShapelessRecipe(new ItemStack(Items.DYE, 1, EnumDyeColor.LIGHT_BLUE.getDyeDamage()), null, "light_blue_dye_from_terrapuff_herb", NibiruBlocks.TERRAPUFF_HERB );
        RecipeHelper.addShapelessRecipe(new ItemStack(Items.DYE, 1, EnumDyeColor.PINK.getDyeDamage()), null, "pink_dye_from_batasia_dandelion", NibiruBlocks.BATASIA_DANDELION );
        RecipeHelper.addShapelessRecipe(new ItemStack(Items.DYE, 1, EnumDyeColor.ORANGE.getDyeDamage()), null, "orange_dye_from_pyolonia", NibiruBlocks.PYOLONIA );
        RecipeHelper.addShapelessRecipe(new ItemStack(MPItems.BLUE_DYE), null, "blue_dye_from_philipy", NibiruBlocks.PHILIPY );
        RecipeHelper.addShapelessRecipe(new ItemStack(Items.DYE, 2, EnumDyeColor.ORANGE.getDyeDamage()), null, "orange_dye_from_orange_rose_bush", NibiruBlocks.INFECTED_ORANGE_ROSE_BUSH );
        RecipeHelper.addShapelessRecipe(new ItemStack(NibiruItems.NIBIRU_DUNGEON_KEY), NibiruItems.NIBIRU_DUNGEON_KEY_BLADE, NibiruItems.NIBIRU_DUNGEON_KEY_BOW );
    }

    private static void addItemRecipe()
    {
        RecipeHelper.addRecipe(new ItemStack(Items.PAPER, 3), null, "bread_from_infected_sugar_cane", new Object[] { "XXX", 'X', NibiruItems.INFECTED_SUGAR_CANE });
        RecipeHelper.addRecipe(new ItemStack(NibiruItems.INFECTED_GOLDEN_APPLE), new Object[] {"XXX", "XAX", "XXX", 'X', "ingotGold", 'A', NibiruItems.INFECTED_APPLE});
        RecipeHelper.addRecipe(new ItemStack(NibiruItems.ENCHANTED_INFECTED_GOLDEN_APPLE), new Object[] {"XXX", "XAX", "XXX", 'X', "blockGold", 'A', NibiruItems.INFECTED_APPLE});
        RecipeHelper.addRecipe(new ItemStack(NibiruItems.GOLDEN_ALIEN_BERRY), new Object[] {"XXX", "XAX", "XXX", 'X', "ingotGold", 'A', NibiruItems.ALIEN_BERRY});
        RecipeHelper.addRecipe(new ItemStack(Items.BREAD), null, "bread_from_infected_wheat", new Object[] { "XXX", 'X', NibiruItems.INFECTED_WHEAT });
        RecipeHelper.addRecipe(new ItemStack(NibiruItems.VEIN_EYE), new Object[] { " V ", "VXV", " V ", 'X', Items.ENDER_EYE, 'V', NibiruBlocks.INFECTED_VINES });
        RecipeHelper.addRecipe(new ItemStack(NibiruItems.INFECTED_OAK_DOOR, 3), "wooden_door", new Object[] { "XX", "XX", "XX", 'X', NibiruBlocks.INFECTED_OAK_PLANKS });
        RecipeHelper.addRecipe(new ItemStack(NibiruItems.ALIEN_BERRY_OAK_DOOR, 3), "wooden_door", new Object[] { "XX", "XX", "XX", 'X', NibiruBlocks.ALIEN_BERRY_OAK_PLANKS });
        RecipeHelper.addRecipe(new ItemStack(NibiruItems.WASTE_ROD_PICKER), new Object[] { " TP", " TT", "T  ", 'T', NibiruItems.SHLIME_TAIL, 'P', ChalosItems.COMPRESSED_ZYPTORIUM });
        RecipeHelper.addShapelessRecipe(new ItemStack(NibiruItems.INFERUMITE_CRYSTAL, 9), NibiruBlocks.INFERUMITE_BLOCK);
        RecipeHelper.addShapelessRecipe(new ItemStack(NibiruItems.MULTALIC_CRYSTAL_PIECES, 9), NibiruBlocks.MULTALIC_CRYSTAL_BLOCK);
        RecipeHelper.addShapelessRecipe(new ItemStack(Items.SUGAR), null, "sugar_from_infected_sugar_cane", NibiruItems.INFECTED_SUGAR_CANE);

        RecipeHelper.addRecipe(new ItemStack(NibiruItems.MULTALIC_CRYSTAL_HELMET, 1, 0), new Object[] { "XXX", "X X", 'X', NibiruItems.MULTALIC_CRYSTAL_PIECES });
        RecipeHelper.addRecipe(new ItemStack(NibiruItems.MULTALIC_CRYSTAL_CHESTPLATE, 1, 0), new Object[] { "X X", "XXX", "XXX", 'X', NibiruItems.MULTALIC_CRYSTAL_PIECES });
        RecipeHelper.addRecipe(new ItemStack(NibiruItems.MULTALIC_CRYSTAL_LEGGINGS, 1, 0), new Object[] { "XXX", "X X", "X X", 'X', NibiruItems.MULTALIC_CRYSTAL_PIECES });
        RecipeHelper.addRecipe(new ItemStack(NibiruItems.MULTALIC_CRYSTAL_BOOTS, 1, 0), new Object[] { "X X", "X X", 'X', NibiruItems.MULTALIC_CRYSTAL_PIECES });
        RecipeHelper.addRecipe(new ItemStack(NibiruItems.BREATHABLE_MULTALIC_CRYSTAL_HELMET, 1, 0), new Object[] { "XXX", "XYX", 'Y', new ItemStack(GCItems.oxMask), 'X', NibiruItems.MULTALIC_CRYSTAL_PIECES });

        RecipeHelper.addRecipe(new ItemStack(NibiruItems.INFECTED_WOOD_HOE), new Object[] { "XX", " Y", " Y", 'X', NibiruBlocks.INFECTED_OAK_PLANKS, 'Y', "stickWood" });
        RecipeHelper.addRecipe(new ItemStack(NibiruItems.INFECTED_WOOD_AXE), new Object[] { "XX", "XY", " Y", 'X', NibiruBlocks.INFECTED_OAK_PLANKS, 'Y', "stickWood" });
        RecipeHelper.addRecipe(new ItemStack(NibiruItems.INFECTED_WOOD_PICKAXE), new Object[] { "XXX", " Y ", " Y ", 'X', NibiruBlocks.INFECTED_OAK_PLANKS, 'Y', "stickWood" });
        RecipeHelper.addRecipe(new ItemStack(NibiruItems.INFECTED_WOOD_SWORD), new Object[] { "X", "X", "Y", 'X', NibiruBlocks.INFECTED_OAK_PLANKS, 'Y', "stickWood" });
        RecipeHelper.addRecipe(new ItemStack(NibiruItems.INFECTED_WOOD_SHOVEL), new Object[] { "X", "Y", "Y", 'X', NibiruBlocks.INFECTED_OAK_PLANKS, 'Y', "stickWood" });
        RecipeHelper.addRecipe(new ItemStack(NibiruItems.ALIEN_BERRY_WOOD_HOE), new Object[] { "XX", " Y", " Y", 'X', NibiruBlocks.ALIEN_BERRY_OAK_PLANKS, 'Y', "stickWood" });
        RecipeHelper.addRecipe(new ItemStack(NibiruItems.ALIEN_BERRY_WOOD_AXE), new Object[] { "XX", "XY", " Y", 'X', NibiruBlocks.ALIEN_BERRY_OAK_PLANKS, 'Y', "stickWood" });
        RecipeHelper.addRecipe(new ItemStack(NibiruItems.ALIEN_BERRY_WOOD_PICKAXE), new Object[] { "XXX", " Y ", " Y ", 'X', NibiruBlocks.ALIEN_BERRY_OAK_PLANKS, 'Y', "stickWood" });
        RecipeHelper.addRecipe(new ItemStack(NibiruItems.ALIEN_BERRY_WOOD_SWORD), new Object[] { "X", "X", "Y", 'X', NibiruBlocks.ALIEN_BERRY_OAK_PLANKS, 'Y', "stickWood" });
        RecipeHelper.addRecipe(new ItemStack(NibiruItems.ALIEN_BERRY_WOOD_SHOVEL), new Object[] { "X", "Y", "Y", 'X', NibiruBlocks.ALIEN_BERRY_OAK_PLANKS, 'Y', "stickWood" });
        RecipeHelper.addRecipe(new ItemStack(NibiruItems.NIBIRU_STONE_HOE), new Object[] { "XX", " Y", " Y", 'X', NibiruBlocks.NIBIRU_COBBLESTONE, 'Y', "stickWood" });
        RecipeHelper.addRecipe(new ItemStack(NibiruItems.NIBIRU_STONE_AXE), new Object[] { "XX", "XY", " Y", 'X', NibiruBlocks.NIBIRU_COBBLESTONE, 'Y', "stickWood" });
        RecipeHelper.addRecipe(new ItemStack(NibiruItems.NIBIRU_STONE_PICKAXE), new Object[] { "XXX", " Y ", " Y ", 'X', NibiruBlocks.NIBIRU_COBBLESTONE, 'Y', "stickWood" });
        RecipeHelper.addRecipe(new ItemStack(NibiruItems.NIBIRU_STONE_SWORD), new Object[] { "X", "X", "Y", 'X', NibiruBlocks.NIBIRU_COBBLESTONE, 'Y', "stickWood" });
        RecipeHelper.addRecipe(new ItemStack(NibiruItems.NIBIRU_STONE_SHOVEL), new Object[] { "X", "Y", "Y", 'X', NibiruBlocks.NIBIRU_COBBLESTONE, 'Y', "stickWood" });
        RecipeHelper.addRecipe(new ItemStack(NibiruItems.MULTALIC_CRYSTAL_HOE), new Object[] { "XX", " Y", " Y", 'X', NibiruItems.MULTALIC_CRYSTAL_PIECES, 'Y', "stickWood" });
        RecipeHelper.addRecipe(new ItemStack(NibiruItems.MULTALIC_CRYSTAL_AXE), new Object[] { "XX", "XY", " Y", 'X', NibiruItems.MULTALIC_CRYSTAL_PIECES, 'Y', "stickWood" });
        RecipeHelper.addRecipe(new ItemStack(NibiruItems.MULTALIC_CRYSTAL_PICKAXE), new Object[] { "XXX", " Y ", " Y ", 'X', NibiruItems.MULTALIC_CRYSTAL_PIECES, 'Y', "stickWood" });
        RecipeHelper.addRecipe(new ItemStack(NibiruItems.MULTALIC_CRYSTAL_SWORD), new Object[] { "X", "X", "Y", 'X', NibiruItems.MULTALIC_CRYSTAL_PIECES, 'Y', "stickWood" });
        RecipeHelper.addRecipe(new ItemStack(NibiruItems.MULTALIC_CRYSTAL_SHOVEL), new Object[] { "X", "Y", "Y", 'X', NibiruItems.MULTALIC_CRYSTAL_PIECES, 'Y', "stickWood" });
    }

    private static void addBlockSmelting()
    {
        RecipeHelper.addSmeltingRecipe(new ItemStack(NibiruBlocks.NIBIRU_COBBLESTONE), new ItemStack(NibiruBlocks.NIBIRU_ROCK), 0.1F);
        RecipeHelper.addSmeltingRecipe(new ItemStack(NibiruBlocks.INFECTED_STONE_BRICKS), new ItemStack(NibiruBlocks.INFECTED_CRACKED_STONE_BRICKS), 0.1F);
        RecipeHelper.addSmeltingRecipe(new ItemStack(NibiruBlocks.INFECTED_WET_SPONGE), new ItemStack(NibiruBlocks.INFECTED_SPONGE), 0.15F);
        RecipeHelper.addSmeltingRecipe(new ItemStack(NibiruBlocks.INFECTED_CLAY), new ItemStack(Blocks.HARDENED_CLAY), 0.35F);
    }

    private static void addItemSmelting()
    {
        RecipeHelper.addSmeltingRecipe(new ItemStack(NibiruBlocks.INFECTED_COAL_ORE), new ItemStack(NibiruItems.INFECTED_COAL), 0.1F);
        RecipeHelper.addSmeltingRecipe(new ItemStack(NibiruBlocks.INFECTED_IRON_ORE), new ItemStack(Items.IRON_INGOT), 0.7F);
        RecipeHelper.addSmeltingRecipe(new ItemStack(NibiruBlocks.INFECTED_GOLD_ORE), new ItemStack(Items.GOLD_INGOT), 1.0F);
        RecipeHelper.addSmeltingRecipe(new ItemStack(NibiruBlocks.INFECTED_DIAMOND_ORE), new ItemStack(Items.DIAMOND), 1.0F);
        RecipeHelper.addSmeltingRecipe(new ItemStack(NibiruBlocks.INFECTED_REDSTONE_ORE), new ItemStack(Items.REDSTONE), 0.7F);
        RecipeHelper.addSmeltingRecipe(new ItemStack(NibiruBlocks.INFECTED_LAPIS_ORE), new ItemStack(Items.DYE, 1, 4), 0.2F);
        RecipeHelper.addSmeltingRecipe(new ItemStack(NibiruBlocks.INFECTED_EMERALD_ORE), new ItemStack(Items.EMERALD), 1.0F);
        RecipeHelper.addSmeltingRecipe(new ItemStack(NibiruBlocks.INFECTED_ALUMINUM_ORE), new ItemStack(GCItems.basicItem, 1, 5), 0.7F);
        RecipeHelper.addSmeltingRecipe(new ItemStack(NibiruBlocks.INFECTED_COPPER_ORE), new ItemStack(GCItems.basicItem, 1, 3), 0.7F);
        RecipeHelper.addSmeltingRecipe(new ItemStack(NibiruBlocks.INFECTED_TIN_ORE), new ItemStack(GCItems.basicItem, 1, 4), 0.7F);
        RecipeHelper.addSmeltingRecipe(new ItemStack(NibiruBlocks.INFECTED_SILICON_ORE), new ItemStack(GCItems.basicItem, 1, 2), 0.7F);
        RecipeHelper.addSmeltingRecipe(new ItemStack(NibiruBlocks.INFERUMITE_CRYSTAL_ORE), new ItemStack(NibiruItems.INFERUMITE_CRYSTAL), 1.0F);
        RecipeHelper.addSmeltingRecipe(new ItemStack(NibiruBlocks.INFECTED_CACTUS), new ItemStack(Items.DYE, 1, EnumDyeColor.GREEN.getDyeDamage()), 0.2F);
        RecipeHelper.addSmeltingRecipe(new ItemStack(NibiruItems.INFECTED_CLAY_BALL), new ItemStack(Items.BRICK), 0.3F);
        RecipeHelper.addSmeltingRecipe(new ItemStack(NibiruBlocks.INFECTED_OAK_LOG), new ItemStack(NibiruItems.INFECTED_CHARCOAL), 0.15F);
        RecipeHelper.addSmeltingRecipe(new ItemStack(NibiruBlocks.INFECTED_JUNGLE_LOG), new ItemStack(NibiruItems.INFECTED_CHARCOAL), 0.15F);
        RecipeHelper.addSmeltingRecipe(new ItemStack(NibiruBlocks.ALIEN_BERRY_OAK_LOG), new ItemStack(Items.COAL, 1, 1), 0.15F);
        RecipeHelper.addSmeltingRecipe(new ItemStack(NibiruItems.RAW_SHLIME_MEAT), new ItemStack(NibiruItems.COOKED_SHLIME_MEAT), 0.35F);
    }
}