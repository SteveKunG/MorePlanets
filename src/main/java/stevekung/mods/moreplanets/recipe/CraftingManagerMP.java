package stevekung.mods.moreplanets.recipe;

import java.util.HashMap;

import micdoodle8.mods.galacticraft.core.GCBlocks;
import micdoodle8.mods.galacticraft.core.GCItems;
import micdoodle8.mods.galacticraft.core.recipe.NasaWorkbenchRecipe;
import micdoodle8.mods.galacticraft.planets.asteroids.items.AsteroidsItems;
import micdoodle8.mods.galacticraft.planets.mars.items.MarsItems;
import micdoodle8.mods.galacticraft.planets.venus.VenusItems;
import net.minecraft.init.Blocks;
import net.minecraft.init.Enchantments;
import net.minecraft.init.Items;
import net.minecraft.init.PotionTypes;
import net.minecraft.item.EnumDyeColor;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionUtils;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidUtil;
import stevekung.mods.moreplanets.init.MPBlocks;
import stevekung.mods.moreplanets.init.MPItems;
import stevekung.mods.moreplanets.utils.debug.JSONRecipe;
import stevekung.mods.moreplanets.utils.debug.JSONTags;
import stevekung.mods.moreplanets.utils.helper.RecipeHelper;

public class CraftingManagerMP
{
    public static void init()
    {
        CraftingManagerMP.addOtherRecipe();

        if (JSONRecipe.ENABLE)
        {
            CraftingManagerMP.addBlockRecipe();
            CraftingManagerMP.addItemRecipe();
        }
        JSONTags.init();
        JSONRecipe.generateConstants();
    }

    @Deprecated //TEMP
    protected static void addBlockRecipe()
    {
        RecipeHelper.addRecipe(new ItemStack(MPBlocks.SPACE_WARP_PAD, 9), new Object[] { "PPP", "MMM", 'P', new ItemStack(AsteroidsItems.basicItem, 1, 5), 'M', new ItemStack(GCBlocks.basicBlock, 1, 12) });
        RecipeHelper.addRecipe(new ItemStack(MPBlocks.TINTED_GLASS, 8), "tinted_glass", new Object[] {"III", "IDI", "III", 'I', "glass", 'D', "compressedDesh"});
        RecipeHelper.addRecipe(new ItemStack(MPBlocks.POLISHED_TIN_DECORATION_BLOCK, 4), new Object[] { "TT", "TT", 'T', new ItemStack(GCBlocks.basicBlock, 1, 4) });
        RecipeHelper.addRecipe(new ItemStack(MPBlocks.POLISHED_ALUMINUM_DECORATION_BLOCK, 8), new Object[] { "TTT", "TAT", "TTT", 'T', MPBlocks.POLISHED_TIN_DECORATION_BLOCK, 'A', new ItemStack(GCItems.basicItem, 1, 8) });
        RecipeHelper.addRecipe(new ItemStack(MPBlocks.DARK_ENERGY_RECEIVER), new Object[] { "HPH", "SCS", "ITI", 'H', new ItemStack(AsteroidsItems.basicItem, 1, 5), 'P', GCItems.flagPole, 'S', new ItemStack(GCItems.basicItem, 1, 1), 'C', MPItems.SPACE_WARPER_CORE, 'I', "ingotIron", 'T', new ItemStack(AsteroidsItems.basicItem, 1, 5) });
        RecipeHelper.addRecipe(new ItemStack(MPBlocks.DARK_ENERGY_STORAGE_CLUSTER), new Object[] { "EAE", "AWA", "EAE", 'E', new ItemStack(GCBlocks.machineTiered, 1, 8), 'A', MPItems.ALIEN_MINER_PART, 'W', new ItemStack(GCItems.basicItem, 1, 14) });
        RecipeHelper.addRecipe(new ItemStack(MPBlocks.NUCLEAR_WASTE_ENERGY_STORAGE_CLUSTER), new Object[] { "EAE", "ANA", "EAE", 'E', MPBlocks.DARK_ENERGY_STORAGE_CLUSTER, 'A', MPItems.ALIEN_MINER_PART, 'N', MPBlocks.NUCLEAR_WASTE_TANK });
        RecipeHelper.addRecipe(new ItemStack(MPBlocks.SPACE_PORTAL), new Object[] { "OFO", "OOO", "OOO", 'O', "obsidian", 'F', Items.FLINT_AND_STEEL });

        RecipeHelper.addRecipe(new ItemStack(MPBlocks.DIONA_COBBLESTONE), "block_from_slab", "diona_cobblestone_from_slab", new Object[] { "X", "X", 'X', MPBlocks.DIONA_COBBLESTONE_SLAB });
        RecipeHelper.addRecipe(new ItemStack(MPBlocks.CHALOS_COBBLESTONE), "block_from_slab", "chalos_cobblestone_from_slab", new Object[] { "X", "X", 'X', MPBlocks.CHALOS_COBBLESTONE_SLAB });
        RecipeHelper.addRecipe(new ItemStack(MPBlocks.NIBIRU_COBBLESTONE), "block_from_slab", "nibiru_cobblestone_from_slab", new Object[] { "X", "X", 'X', MPBlocks.NIBIRU_COBBLESTONE_SLAB });
        RecipeHelper.addRecipe(new ItemStack(MPBlocks.DIONA_DUNGEON_BRICK), "block_from_slab", "diona_dungeon_brick_from_slab", new Object[] { "X", "X", 'X', MPBlocks.DIONA_DUNGEON_BRICK_SLAB });
        RecipeHelper.addRecipe(new ItemStack(MPBlocks.CHALOS_DUNGEON_BRICK), "block_from_slab", "chalos_dungeon_brick_from_slab", new Object[] { "X", "X", 'X', MPBlocks.CHALOS_DUNGEON_BRICK_SLAB });
        RecipeHelper.addRecipe(new ItemStack(MPBlocks.NIBIRU_DUNGEON_BRICK), "block_from_slab", "nibiru_dungeon_brick_from_slab", new Object[] { "X", "X", 'X', MPBlocks.NIBIRU_DUNGEON_BRICK_SLAB });
        RecipeHelper.addRecipe(new ItemStack(MPBlocks.CHEESE_SPORE_PLANKS), "block_from_slab", "cheese_spore_planks_from_slab", new Object[] { "X", "X", 'X', MPBlocks.CHEESE_SPORE_SLAB });
        RecipeHelper.addRecipe(new ItemStack(MPBlocks.INFECTED_OAK_PLANKS), "block_from_slab", "infected_oak_planks_from_slab", new Object[] { "X", "X", 'X', MPBlocks.INFECTED_OAK_SLAB });
        RecipeHelper.addRecipe(new ItemStack(MPBlocks.ALIEN_BERRY_OAK_PLANKS), "block_from_slab", "alien_berry_oak_planks_from_slab", new Object[] { "X", "X", 'X', MPBlocks.ALIEN_BERRY_OAK_SLAB });
        RecipeHelper.addRecipe(new ItemStack(MPBlocks.INFECTED_PRISMARINE), "block_from_slab", "infected_prismarine_from_slab", new Object[] { "X", "X", 'X', MPBlocks.INFECTED_PRISMARINE_SLAB });
        RecipeHelper.addRecipe(new ItemStack(MPBlocks.INFECTED_PRISMARINE_BRICKS), "block_from_slab", "infected_prismarine_bricks_from_slab", new Object[] { "X", "X", 'X', MPBlocks.INFECTED_PRISMARINE_BRICK_SLAB });
        RecipeHelper.addRecipe(new ItemStack(MPBlocks.INFECTED_DARK_PRISMARINE), "block_from_slab", "infected_dark_prismarine_from_slab", new Object[] { "X", "X", 'X', MPBlocks.INFECTED_DARK_PRISMARINE_SLAB });
        RecipeHelper.addRecipe(new ItemStack(MPBlocks.INFECTED_VEIN_STONE_BRICKS), "block_from_slab", "infected_vein_stone_bricks_from_slab", new Object[] { "X", "X", 'X', MPBlocks.INFECTED_VEIN_STONE_BRICKS_SLAB });
        RecipeHelper.addRecipe(new ItemStack(MPBlocks.INFECTED_CRACKED_STONE_BRICKS), "block_from_slab", "infected_cracked_stone_bricks_from_slab", new Object[] { "X", "X", 'X', MPBlocks.INFECTED_CRACKED_STONE_BRICKS_SLAB });
        RecipeHelper.addRecipe(new ItemStack(MPBlocks.TERRASTONE), "block_from_slab", "terrastone_from_slab", new Object[] { "X", "X", 'X', MPBlocks.TERRASTONE_SLAB });
        RecipeHelper.addRecipe(new ItemStack(MPBlocks.INFECTED_CUT_SANDSTONE), "block_from_slab", "infected_cut_sandstone_from_slab", new Object[] { "X", "X", 'X', MPBlocks.INFECTED_CUT_SANDSTONE_SLAB });
        RecipeHelper.addRecipe(new ItemStack(MPBlocks.ALIEN_SHIP_DECORATION_0), "block_from_slab", "alien_ship_decoration_from_slab", new Object[] { "X", "X", 'X', MPBlocks.ALIEN_SHIP_DECORATION_SLAB });

        RecipeHelper.addRecipe(new ItemStack(MPBlocks.DIONA_COBBLESTONE_SLAB, 6), "cobblestone_slab", new Object[] { "XXX", 'X', MPBlocks.DIONA_COBBLESTONE });
        RecipeHelper.addRecipe(new ItemStack(MPBlocks.CHALOS_COBBLESTONE_SLAB, 6), "cobblestone_slab", new Object[] { "XXX", 'X', MPBlocks.CHALOS_COBBLESTONE });
        RecipeHelper.addRecipe(new ItemStack(MPBlocks.NIBIRU_COBBLESTONE_SLAB, 6), "cobblestone_slab", new Object[] { "XXX", 'X', MPBlocks.NIBIRU_COBBLESTONE });
        RecipeHelper.addRecipe(new ItemStack(MPBlocks.DIONA_DUNGEON_BRICK_SLAB, 6), "dungeon_brick_slab", new Object[] { "XXX", 'X', MPBlocks.DIONA_DUNGEON_BRICK });
        RecipeHelper.addRecipe(new ItemStack(MPBlocks.CHALOS_DUNGEON_BRICK_SLAB, 6), "dungeon_brick_slab", new Object[] { "XXX", 'X', MPBlocks.CHALOS_DUNGEON_BRICK });
        RecipeHelper.addRecipe(new ItemStack(MPBlocks.NIBIRU_DUNGEON_BRICK_SLAB, 6), "dungeon_brick_slab", new Object[] { "XXX", 'X', MPBlocks.NIBIRU_DUNGEON_BRICK });

        RecipeHelper.addRecipe(new ItemStack(MPBlocks.DIONA_COBBLESTONE_WALL, 6), "cobblestone_wall", new Object[] { "XXX", "XXX", 'X', MPBlocks.DIONA_COBBLESTONE });
        RecipeHelper.addRecipe(new ItemStack(MPBlocks.CHALOS_COBBLESTONE_WALL, 6), "cobblestone_wall", new Object[] { "XXX", "XXX", 'X', MPBlocks.CHALOS_COBBLESTONE });
        RecipeHelper.addRecipe(new ItemStack(MPBlocks.NIBIRU_COBBLESTONE_WALL, 6), "cobblestone_wall", new Object[] { "XXX", "XXX", 'X', MPBlocks.NIBIRU_COBBLESTONE });
        RecipeHelper.addRecipe(new ItemStack(MPBlocks.DIONA_DUNGEON_BRICK_WALL, 6), "dungeon_brick_wall", new Object[] { "XXX", "XXX", 'X', MPBlocks.DIONA_DUNGEON_BRICK });
        RecipeHelper.addRecipe(new ItemStack(MPBlocks.CHALOS_DUNGEON_BRICK_WALL, 6), "dungeon_brick_wall", new Object[] { "XXX", "XXX", 'X', MPBlocks.CHALOS_DUNGEON_BRICK });
        RecipeHelper.addRecipe(new ItemStack(MPBlocks.NIBIRU_DUNGEON_BRICK_WALL, 6), "dungeon_brick_wall", new Object[] { "XXX", "XXX", 'X', MPBlocks.NIBIRU_DUNGEON_BRICK });

        RecipeHelper.addRecipe(new ItemStack(MPBlocks.CHEESE_SPORE_SLAB, 6), "wooden_slab", new Object[] { "XXX", 'X', MPBlocks.CHEESE_SPORE_PLANKS });
        RecipeHelper.addRecipe(new ItemStack(MPBlocks.INFECTED_OAK_SLAB, 6), "wooden_slab", new Object[] { "XXX", 'X', MPBlocks.INFECTED_OAK_PLANKS });
        RecipeHelper.addRecipe(new ItemStack(MPBlocks.ALIEN_BERRY_OAK_SLAB, 6), "wooden_slab", new Object[] { "XXX", 'X', MPBlocks.ALIEN_BERRY_OAK_PLANKS });

        RecipeHelper.addRecipe(new ItemStack(MPBlocks.WHITE_TINTED_GLASS, 8), "tinted_glass", new Object[] {"III", "IDI", "III", 'I', MPBlocks.TINTED_GLASS, 'D', "dyeWhite"});
        RecipeHelper.addRecipe(new ItemStack(MPBlocks.ORANGE_TINTED_GLASS, 8), "tinted_glass", new Object[] {"III", "IDI", "III", 'I', MPBlocks.TINTED_GLASS, 'D', "dyeOrange"});
        RecipeHelper.addRecipe(new ItemStack(MPBlocks.MAGENTA_TINTED_GLASS, 8), "tinted_glass", new Object[] {"III", "IDI", "III", 'I', MPBlocks.TINTED_GLASS, 'D', "dyeMagenta"});
        RecipeHelper.addRecipe(new ItemStack(MPBlocks.LIGHT_BLUE_TINTED_GLASS, 8), "tinted_glass", new Object[] {"III", "IDI", "III", 'I', MPBlocks.TINTED_GLASS, 'D', "dyeLightBlue"});
        RecipeHelper.addRecipe(new ItemStack(MPBlocks.YELLOW_TINTED_GLASS, 8), "tinted_glass", new Object[] {"III", "IDI", "III", 'I', MPBlocks.TINTED_GLASS, 'D', "dyeYellow"});
        RecipeHelper.addRecipe(new ItemStack(MPBlocks.LIME_TINTED_GLASS, 8), "tinted_glass", new Object[] {"III", "IDI", "III", 'I', MPBlocks.TINTED_GLASS, 'D', "dyeLime"});
        RecipeHelper.addRecipe(new ItemStack(MPBlocks.PINK_TINTED_GLASS, 8), "tinted_glass", new Object[] {"III", "IDI", "III", 'I', MPBlocks.TINTED_GLASS, 'D', "dyePink"});
        RecipeHelper.addRecipe(new ItemStack(MPBlocks.GRAY_TINTED_GLASS, 8), "tinted_glass", new Object[] {"III", "IDI", "III", 'I', MPBlocks.TINTED_GLASS, 'D', "dyeGray"});
        RecipeHelper.addRecipe(new ItemStack(MPBlocks.SILVER_TINTED_GLASS, 8), "tinted_glass", new Object[] {"III", "IDI", "III", 'I', MPBlocks.TINTED_GLASS, 'D', "dyeLightGray"});
        RecipeHelper.addRecipe(new ItemStack(MPBlocks.CYAN_TINTED_GLASS, 8), "tinted_glass", new Object[] {"III", "IDI", "III", 'I', MPBlocks.TINTED_GLASS, 'D', "dyeCyan"});
        RecipeHelper.addRecipe(new ItemStack(MPBlocks.PURPLE_TINTED_GLASS, 8), "tinted_glass", new Object[] {"III", "IDI", "III", 'I', MPBlocks.TINTED_GLASS, 'D', "dyePurple"});
        RecipeHelper.addRecipe(new ItemStack(MPBlocks.BLUE_TINTED_GLASS, 8), "tinted_glass", new Object[] {"III", "IDI", "III", 'I', MPBlocks.TINTED_GLASS, 'D', "dyeBlue"});
        RecipeHelper.addRecipe(new ItemStack(MPBlocks.BROWN_TINTED_GLASS, 8), "tinted_glass", new Object[] {"III", "IDI", "III", 'I', MPBlocks.TINTED_GLASS, 'D', "dyeBrown"});
        RecipeHelper.addRecipe(new ItemStack(MPBlocks.GREEN_TINTED_GLASS, 8), "tinted_glass", new Object[] {"III", "IDI", "III", 'I', MPBlocks.TINTED_GLASS, 'D', "dyeGreen"});
        RecipeHelper.addRecipe(new ItemStack(MPBlocks.RED_TINTED_GLASS, 8), "tinted_glass", new Object[] {"III", "IDI", "III", 'I', MPBlocks.TINTED_GLASS, 'D', "dyeRed"});
        RecipeHelper.addRecipe(new ItemStack(MPBlocks.BLACK_TINTED_GLASS, 8), "tinted_glass", new Object[] {"III", "IDI", "III", 'I', MPBlocks.TINTED_GLASS, 'D', "dyeBlack"});

        RecipeHelper.addRecipe(new ItemStack(MPBlocks.TINTED_GLASS_PANE, 16), "tinted_glass_pane", new Object[] {"GGG", "GGG", 'G', MPBlocks.TINTED_GLASS});
        RecipeHelper.addRecipe(new ItemStack(MPBlocks.WHITE_TINTED_GLASS_PANE, 16), "tinted_glass_pane", new Object[] {"GGG", "GGG", 'G', MPBlocks.WHITE_TINTED_GLASS});
        RecipeHelper.addRecipe(new ItemStack(MPBlocks.ORANGE_TINTED_GLASS_PANE, 16), "tinted_glass_pane", new Object[] {"GGG", "GGG", 'G', MPBlocks.ORANGE_TINTED_GLASS});
        RecipeHelper.addRecipe(new ItemStack(MPBlocks.MAGENTA_TINTED_GLASS_PANE, 16), "tinted_glass_pane", new Object[] {"GGG", "GGG", 'G', MPBlocks.MAGENTA_TINTED_GLASS});
        RecipeHelper.addRecipe(new ItemStack(MPBlocks.LIGHT_BLUE_TINTED_GLASS_PANE, 16), "tinted_glass_pane", new Object[] {"GGG", "GGG", 'G', MPBlocks.LIGHT_BLUE_TINTED_GLASS});
        RecipeHelper.addRecipe(new ItemStack(MPBlocks.YELLOW_TINTED_GLASS_PANE, 16), "tinted_glass_pane", new Object[] {"GGG", "GGG", 'G', MPBlocks.YELLOW_TINTED_GLASS});
        RecipeHelper.addRecipe(new ItemStack(MPBlocks.LIME_TINTED_GLASS_PANE, 16), "tinted_glass_pane", new Object[] {"GGG", "GGG", 'G', MPBlocks.LIME_TINTED_GLASS});
        RecipeHelper.addRecipe(new ItemStack(MPBlocks.PINK_TINTED_GLASS_PANE, 16), "tinted_glass_pane", new Object[] {"GGG", "GGG", 'G', MPBlocks.PINK_TINTED_GLASS});
        RecipeHelper.addRecipe(new ItemStack(MPBlocks.GRAY_TINTED_GLASS_PANE, 16), "tinted_glass_pane", new Object[] {"GGG", "GGG", 'G', MPBlocks.GRAY_TINTED_GLASS});
        RecipeHelper.addRecipe(new ItemStack(MPBlocks.SILVER_TINTED_GLASS_PANE, 16), "tinted_glass_pane", new Object[] {"GGG", "GGG", 'G', MPBlocks.SILVER_TINTED_GLASS});
        RecipeHelper.addRecipe(new ItemStack(MPBlocks.CYAN_TINTED_GLASS_PANE, 16), "tinted_glass_pane", new Object[] {"GGG", "GGG", 'G', MPBlocks.CYAN_TINTED_GLASS});
        RecipeHelper.addRecipe(new ItemStack(MPBlocks.PURPLE_TINTED_GLASS_PANE, 16), "tinted_glass_pane", new Object[] {"GGG", "GGG", 'G', MPBlocks.PURPLE_TINTED_GLASS});
        RecipeHelper.addRecipe(new ItemStack(MPBlocks.BLUE_TINTED_GLASS_PANE, 16), "tinted_glass_pane", new Object[] {"GGG", "GGG", 'G', MPBlocks.BLUE_TINTED_GLASS});
        RecipeHelper.addRecipe(new ItemStack(MPBlocks.BROWN_TINTED_GLASS_PANE, 16), "tinted_glass_pane", new Object[] {"GGG", "GGG", 'G', MPBlocks.BROWN_TINTED_GLASS});
        RecipeHelper.addRecipe(new ItemStack(MPBlocks.GREEN_TINTED_GLASS_PANE, 16), "tinted_glass_pane", new Object[] {"GGG", "GGG", 'G', MPBlocks.GREEN_TINTED_GLASS});
        RecipeHelper.addRecipe(new ItemStack(MPBlocks.RED_TINTED_GLASS_PANE, 16), "tinted_glass_pane", new Object[] {"GGG", "GGG", 'G', MPBlocks.RED_TINTED_GLASS});
        RecipeHelper.addRecipe(new ItemStack(MPBlocks.BLACK_TINTED_GLASS_PANE, 16), "tinted_glass_pane", new Object[] {"GGG", "GGG", 'G', MPBlocks.BLACK_TINTED_GLASS});

        RecipeHelper.addRecipe(new ItemStack(MPItems.EMPTY_CAPSULE), "capsule", new Object[] { " C", "X ", 'C', new ItemStack(GCItems.canister), 'X', "ingotIron" });
        RecipeHelper.addShapelessRecipe(new ItemStack(MPItems.INFECTED_SPORE_PROTECTION_CAPSULE), "capsule", MPItems.EMPTY_CAPSULE, MPItems.CHEESE_SPORE_BERRY, MPBlocks.CHEESE_SPORE_FLOWER, Blocks.BROWN_MUSHROOM);
        RecipeHelper.addShapelessRecipe(new ItemStack(MPItems.INFECTED_SPORE_PROTECTION_CAPSULE), "capsule", "infected_spore_protection_capsule_from_herb", MPItems.EMPTY_CAPSULE, MPBlocks.PURE_HERB, MPItems.INFERUMITE_CRYSTAL);
        RecipeHelper.addShapelessRecipe(new ItemStack(MPItems.DARK_ENERGY_PROTECTION_CAPSULE), "capsule", MPItems.EMPTY_CAPSULE, MPItems.INFECTED_PURLONITE_SHARD, new ItemStack(MarsItems.marsItemBasic, 1, 0));
        RecipeHelper.addShapelessRecipe(new ItemStack(MPItems.DARK_ENERGY_PROTECTION_CAPSULE), "capsule", "dark_energy_protection_capsule_herb", MPItems.EMPTY_CAPSULE, MPBlocks.TERRAPUFF_HERB, MPItems.INFERUMITE_CRYSTAL, new ItemStack(MarsItems.marsItemBasic, 1, 0));

        RecipeHelper.addRecipe(new ItemStack(MPBlocks.ILLENIUM_BLOCK), new Object[] { "III", "III", "III", 'I', MPItems.ILLENIUM_INGOT });
        RecipeHelper.addRecipe(new ItemStack(MPBlocks.SETRORIUM_BLOCK), new Object[] { "SSS", "SSS", "SSS", 'S', MPItems.SETRORIUM_SHARD });
        RecipeHelper.addRecipe(new ItemStack(MPBlocks.GLOWING_IRON_BLOCK), new Object[] { "SSS", "SSS", "SSS", 'S', MPItems.GLOWING_IRON_INGOT });
        RecipeHelper.addRecipe(new ItemStack(MPBlocks.INFECTED_PURLONITE_SLIME_BLOCK), new Object[] { "SSS", "SSS", "SSS", 'S', MPItems.INFECTED_PURLONITE_SLIMEBALL });
        RecipeHelper.addRecipe(new ItemStack(MPBlocks.INFECTED_PURLONITE_SEGMENT), new Object[] { "BSB", "BSB", "BSB", 'S', MPItems.INFECTED_PURLONITE_SHARD, 'B', MPItems.INFECTED_PURLONITE_SLIMEBALL });
        RecipeHelper.addRecipe(new ItemStack(MPBlocks.DARK_ENERGY_GENERATOR), new Object[] { "HPH", "ACA", "WFW", 'H', new ItemStack(AsteroidsItems.basicItem, 1, 5), 'P', MPItems.DARK_ENERGY_PEARL, 'A', MPItems.ALIEN_MINER_PART, 'C', MPItems.MULTALIC_CRYSTAL_PIECES, 'F', new ItemStack(GCItems.basicItem, 1, 14), 'W', new ItemStack(GCBlocks.aluminumWire, 1, 1) });

        RecipeHelper.addRecipe(new ItemStack(MPBlocks.DIONA_COBBLESTONE_STAIRS, 4), "cobblestone_stairs", new Object[] { "X  ", "XX ", "XXX", 'X', MPBlocks.DIONA_COBBLESTONE });
        RecipeHelper.addRecipe(new ItemStack(MPBlocks.DIONA_DUNGEON_BRICK_STAIRS, 4), "dungeon_brick_stairs", new Object[] { "X  ", "XX ", "XXX", 'X', MPBlocks.DIONA_DUNGEON_BRICK });

        RecipeHelper.addRecipe(new ItemStack(MPBlocks.DIREMSIUM_BLOCK), new Object[] { "XXX", "XXX", "XXX", 'X', MPItems.DIREMSIUM_INGOT });
        RecipeHelper.addRecipe(new ItemStack(MPBlocks.ZYPTORIUM_BLOCK), new Object[] { "XXX", "XXX", "XXX", 'X', MPItems.ZYPTORIUM_INGOT });
        RecipeHelper.addRecipe(new ItemStack(MPBlocks.CHEESE_MILK_CAKE), new Object[] { "MMM", "CCC", "CCC", 'C', MPItems.CHEESE_MILK_CURD, 'M', FluidUtil.getFilledBucket(new FluidStack(MPBlocks.CHEESE_MILK_FLUID, 1000)) });
        RecipeHelper.addRecipe(new ItemStack(MPBlocks.CHEESE_SPORE_CRAFTING_TABLE), "crafting_table", new Object[] { "XX", "XX", 'X', MPBlocks.CHEESE_SPORE_PLANKS });
        RecipeHelper.addRecipe(new ItemStack(MPBlocks.CHEESE_SLIME_BLOCK), new Object[] { "XXX", "XXX", "XXX", 'X', MPItems.CHEESE_SLIMEBALL });
        RecipeHelper.addRecipe(new ItemStack(MPBlocks.CHEESE_COARSE_DIRT, 4), "coarse_dirt", new Object[] { "DG", "GD", 'D', MPBlocks.CHEESE_DIRT, 'G', "gravel" });
        RecipeHelper.addRecipe(new ItemStack(MPBlocks.CHEESE_SPORE_CHEST), new Object[] { "XXX", "X X", "XXX", 'X', MPBlocks.CHEESE_SPORE_PLANKS });
        RecipeHelper.addRecipe(new ItemStack(MPBlocks.CHEESE_SPORE_FENCE, 3), "wooden_fence", new Object[] { "XSX", "XSX", 'X', MPBlocks.CHEESE_SPORE_PLANKS, 'S', "stickWood" });
        RecipeHelper.addRecipe(new ItemStack(MPBlocks.CHEESE_SPORE_FENCE_GATE), "wooden_fence_gate", new Object[] { "SXS", "SXS", 'X', MPBlocks.CHEESE_SPORE_PLANKS, 'S', "stickWood" });
        RecipeHelper.addRecipe(new ItemStack(MPBlocks.CHALOS_COBBLESTONE_STAIRS, 4), "cobblestone_stairs", new Object[] { "X  ", "XX ", "XXX", 'X', MPBlocks.CHALOS_COBBLESTONE });
        RecipeHelper.addRecipe(new ItemStack(MPBlocks.CHALOS_DUNGEON_BRICK_STAIRS, 4), "dungeon_brick_stairs", new Object[] { "X  ", "XX ", "XXX", 'X', MPBlocks.CHALOS_DUNGEON_BRICK });
        RecipeHelper.addRecipe(new ItemStack(MPBlocks.CHEESE_SPORE_STAIRS, 4), "wooden_stairs", new Object[] { "X  ", "XX ", "XXX", 'X', MPBlocks.CHEESE_SPORE_PLANKS });
        RecipeHelper.addShapelessRecipe(new ItemStack(MPBlocks.CHEESE_SPORE_PLANKS, 4), "planks", MPBlocks.CHEESE_SPORE_STEM );

        RecipeHelper.addRecipe(new ItemStack(MPBlocks.INFECTED_OAK_PRESSURE_PLATE), new Object[] { "XX", 'X', "infectedPlank" });
        RecipeHelper.addRecipe(new ItemStack(MPBlocks.ALIEN_BERRY_OAK_PRESSURE_PLATE), new Object[] { "XX", 'X', MPBlocks.ALIEN_BERRY_OAK_PLANKS });
        RecipeHelper.addRecipe(new ItemStack(MPBlocks.INFERUMITE_BLOCK), new Object[] { "XXX", "XXX", "XXX", 'X', MPItems.INFERUMITE_CRYSTAL });
        RecipeHelper.addRecipe(new ItemStack(MPBlocks.INFECTED_CRAFTING_TABLE), "crafting_table", new Object[] { "XX", "XX", 'X', "infectedPlank" });
        RecipeHelper.addRecipe(new ItemStack(MPBlocks.ALIEN_BERRY_CRAFTING_TABLE), "crafting_table", new Object[] { "XX", "XX", 'X', MPBlocks.ALIEN_BERRY_OAK_PLANKS });
        RecipeHelper.addRecipe(new ItemStack(MPBlocks.INFECTED_COARSE_DIRT, 4), "coarse_dirt", new Object[] { "DG", "GD", 'D', MPBlocks.INFECTED_DIRT, 'G', "gravel" });
        RecipeHelper.addRecipe(new ItemStack(MPBlocks.INFECTED_STONE_BRICKS, 4), new Object[] { "XX", "XX", 'X', MPBlocks.NIBIRU_ROCK });
        RecipeHelper.addRecipe(new ItemStack(MPBlocks.INFECTED_SANDSTONE, 4), new Object[] { "XX", "XX", 'X', MPBlocks.INFECTED_SAND });
        RecipeHelper.addRecipe(new ItemStack(MPBlocks.INFECTED_CUT_SANDSTONE, 4), new Object[] { "XX", "XX", 'X', MPBlocks.INFECTED_SANDSTONE });
        RecipeHelper.addRecipe(new ItemStack(MPBlocks.INFECTED_OAK_BOOKSHELF), new Object[] { "WWW", "BBB", "WWW", 'W', MPBlocks.INFECTED_OAK_PLANKS, 'B', Items.BOOK });
        RecipeHelper.addRecipe(new ItemStack(MPBlocks.ALIEN_BERRY_OAK_BOOKSHELF), new Object[] { "WWW", "BBB", "WWW", 'W', MPBlocks.ALIEN_BERRY_OAK_PLANKS, 'B', Items.BOOK });
        RecipeHelper.addRecipe(new ItemStack(MPBlocks.INFECTED_SNOW), new Object[] { "XX", "XX", 'X', MPItems.INFECTED_SNOWBALL });
        RecipeHelper.addRecipe(new ItemStack(MPBlocks.PURIFIED_SNOW), new Object[] { "XX", "XX", 'X', MPItems.PURIFIED_SNOWBALL });
        RecipeHelper.addRecipe(new ItemStack(MPBlocks.INFECTED_MELON), new Object[] { "XXX", "XXX", "XXX", 'X', MPItems.INFECTED_MELON_SLICE });
        RecipeHelper.addRecipe(new ItemStack(MPBlocks.INFECTED_CLAY), new Object[] { "XX", "XX", 'X', MPItems.INFECTED_CLAY_BALL });
        RecipeHelper.addRecipe(new ItemStack(MPBlocks.INFECTED_PRISMARINE, 4), new Object[] { "SS", "SS", 'S', MPItems.INFECTED_PRISMARINE_SHARD });
        RecipeHelper.addRecipe(new ItemStack(MPBlocks.INFECTED_PRISMARINE_BRICKS, 1), new Object[] { "SSS", "SSS", "SSS", 'S', MPItems.INFECTED_PRISMARINE_SHARD });
        RecipeHelper.addRecipe(new ItemStack(MPBlocks.INFECTED_DARK_PRISMARINE, 1), new Object[] { "SSS", "SIS", "SSS", 'S', MPItems.INFECTED_PRISMARINE_SHARD, 'I', new ItemStack(Items.DYE, 1, EnumDyeColor.BLACK.getDyeDamage()) });
        RecipeHelper.addRecipe(new ItemStack(MPBlocks.INFECTED_SEA_LANTERN), new Object[] { "PSP", "SSS", "PSP", 'P', MPItems.INFECTED_PRISMARINE_SHARD, 'S', MPItems.INFECTED_PRISMARINE_CRYSTALS });
        RecipeHelper.addRecipe(new ItemStack(MPBlocks.INFECTED_OAK_FENCE, 3), "wooden_fence", new Object[] { "XSX", "XSX", 'X', MPBlocks.INFECTED_OAK_PLANKS, 'S', "stickWood" });
        RecipeHelper.addRecipe(new ItemStack(MPBlocks.ALIEN_BERRY_OAK_FENCE, 3), "wooden_fence", new Object[] { "XSX", "XSX", 'X', MPBlocks.ALIEN_BERRY_OAK_PLANKS, 'S', "stickWood" });
        RecipeHelper.addRecipe(new ItemStack(MPBlocks.INFECTED_OAK_FENCE_GATE), "wooden_fence_gate", new Object[] { "SXS", "SXS", 'X', MPBlocks.INFECTED_OAK_PLANKS, 'S', "stickWood" });
        RecipeHelper.addRecipe(new ItemStack(MPBlocks.ALIEN_BERRY_OAK_FENCE_GATE), "wooden_fence_gate", new Object[] { "SXS", "SXS", 'X', MPBlocks.ALIEN_BERRY_OAK_PLANKS, 'S', "stickWood" });
        RecipeHelper.addRecipe(new ItemStack(MPBlocks.INFECTED_CHEST), new Object[] { "XXX", "X X", "XXX", 'X', MPBlocks.INFECTED_OAK_PLANKS });
        RecipeHelper.addRecipe(new ItemStack(MPBlocks.ALIEN_BERRY_CHEST), new Object[] { "XXX", "X X", "XXX", 'X', MPBlocks.ALIEN_BERRY_OAK_PLANKS });
        RecipeHelper.addRecipe(new ItemStack(MPBlocks.INFECTED_SNOW_LAYER, 6), new Object[] { "XXX", 'X', MPBlocks.INFECTED_SNOW });
        RecipeHelper.addRecipe(new ItemStack(MPBlocks.PURIFIED_SNOW_LAYER, 6), new Object[] { "XXX", 'X', MPBlocks.PURIFIED_SNOW });
        RecipeHelper.addRecipe(new ItemStack(MPBlocks.INFECTED_TORCH, 4), new Object[] { "C", "S", 'S', "stickWood", 'C', MPItems.INFECTED_COAL});
        RecipeHelper.addRecipe(new ItemStack(MPBlocks.INFECTED_TORCH, 4), null, "infected_torch_from_charcoal", new Object[] { "C", "S", 'S', "stickWood", 'C', MPItems.INFECTED_CHARCOAL});
        RecipeHelper.addRecipe(new ItemStack(MPBlocks.INFECTED_FURNACE), new Object[] { "XXX", "X X", "XXX", 'X', MPBlocks.NIBIRU_COBBLESTONE});
        RecipeHelper.addRecipe(new ItemStack(MPBlocks.INFECTED_PRISMARINE_SLAB, 6), new Object[] { "XXX", 'X', MPBlocks.INFECTED_PRISMARINE });
        RecipeHelper.addRecipe(new ItemStack(MPBlocks.INFECTED_PRISMARINE_BRICK_SLAB, 6), new Object[] { "XXX", 'X', MPBlocks.INFECTED_PRISMARINE_BRICKS });
        RecipeHelper.addRecipe(new ItemStack(MPBlocks.INFECTED_DARK_PRISMARINE_SLAB, 6), new Object[] { "XXX", 'X', MPBlocks.INFECTED_DARK_PRISMARINE });
        RecipeHelper.addRecipe(new ItemStack(MPBlocks.INFECTED_STONE_BRICKS_SLAB, 6), new Object[] { "XXX", 'X', MPBlocks.INFECTED_STONE_BRICKS });
        RecipeHelper.addRecipe(new ItemStack(MPBlocks.INFECTED_VEIN_STONE_BRICKS_SLAB, 6), new Object[] { "XXX", 'X', MPBlocks.INFECTED_VEIN_STONE_BRICKS });
        RecipeHelper.addRecipe(new ItemStack(MPBlocks.INFECTED_CRACKED_STONE_BRICKS_SLAB, 6), new Object[] { "XXX", 'X', MPBlocks.INFECTED_CRACKED_STONE_BRICKS });
        RecipeHelper.addRecipe(new ItemStack(MPBlocks.TERRASTONE_SLAB, 6), new Object[] { "XXX", 'X', MPBlocks.TERRASTONE });
        RecipeHelper.addRecipe(new ItemStack(MPBlocks.INFECTED_CHISELED_STONE_BRICKS), new Object[] { "X", "X", 'X', MPBlocks.INFECTED_STONE_BRICKS_SLAB });
        RecipeHelper.addRecipe(new ItemStack(MPBlocks.INFECTED_SANDSTONE_SLAB, 6), new Object[] { "XXX", 'X', "sandstoneNibiru" });
        RecipeHelper.addRecipe(new ItemStack(MPBlocks.INFECTED_CUT_SANDSTONE_SLAB, 6), new Object[] { "XXX", 'X', MPBlocks.INFECTED_CUT_SANDSTONE });
        RecipeHelper.addRecipe(new ItemStack(MPBlocks.INFECTED_CHISELED_SANDSTONE), new Object[] { "X", "X", 'X', MPBlocks.INFECTED_SANDSTONE_SLAB });
        RecipeHelper.addRecipe(new ItemStack(MPBlocks.NUCLEAR_WASTE_GENERATOR), new Object[] { "PXP", "ATA", "IWI", 'W', new ItemStack(GCItems.basicItem, 1, 14), 'I', new ItemStack(GCBlocks.aluminumWire, 1, 1), 'T', MPBlocks.NUCLEAR_WASTE_TANK, 'A', MPItems.ALIEN_MINER_PART, 'P', new ItemStack(AsteroidsItems.basicItem, 1, 5), 'X', MPItems.MULTALIC_CRYSTAL_PIECES });
        RecipeHelper.addRecipe(new ItemStack(MPBlocks.MULTALIC_CRYSTAL_BLOCK), new Object[] { "XXX", "XXX", "XXX", 'X', MPItems.MULTALIC_CRYSTAL_PIECES });
        RecipeHelper.addRecipe(new ItemStack(MPBlocks.TERRASTONE_FURNACE), new Object[] { "XXX", "X X", "XXX", 'X', MPBlocks.TERRASTONE});
        RecipeHelper.addRecipe(new ItemStack(MPBlocks.SEALABLE_NUCLEAR_WASTE_ROD, 16), new Object[] { "TGT", "GWG", "TGT", 'T', new ItemStack(GCBlocks.basicBlock, 1, 4), 'G', "blockGlassLightGray", 'W', MPItems.NUCLEAR_WASTE_ROD});
        RecipeHelper.addRecipe(new ItemStack(MPBlocks.NIBIRU_COBBLESTONE_STAIRS, 4), "cobblestone_stairs", new Object[] { "X  ", "XX ", "XXX", 'X', MPBlocks.NIBIRU_COBBLESTONE });
        RecipeHelper.addRecipe(new ItemStack(MPBlocks.NIBIRU_DUNGEON_BRICK_STAIRS, 4), "dungeon_brick_stairs", new Object[] { "X  ", "XX ", "XXX", 'X', MPBlocks.NIBIRU_DUNGEON_BRICK });
        RecipeHelper.addRecipe(new ItemStack(MPBlocks.INFECTED_STONE_BRICKS_STAIRS, 4), new Object[] { "X  ", "XX ", "XXX", 'X', MPBlocks.INFECTED_STONE_BRICKS });
        RecipeHelper.addRecipe(new ItemStack(MPBlocks.INFECTED_OAK_STAIRS, 4), "wooden_stairs", new Object[] { "X  ", "XX ", "XXX", 'X', MPBlocks.INFECTED_OAK_PLANKS });
        RecipeHelper.addRecipe(new ItemStack(MPBlocks.ALIEN_BERRY_OAK_STAIRS, 4), "wooden_stairs", new Object[] { "X  ", "XX ", "XXX", 'X', MPBlocks.ALIEN_BERRY_OAK_PLANKS });
        RecipeHelper.addRecipe(new ItemStack(MPBlocks.INFECTED_PRISMARINE_STAIRS, 4), new Object[] { "X  ", "XX ", "XXX", 'X', MPBlocks.INFECTED_PRISMARINE });
        RecipeHelper.addRecipe(new ItemStack(MPBlocks.INFECTED_PRISMARINE_BRICK_STAIRS, 4), new Object[] { "X  ", "XX ", "XXX", 'X', MPBlocks.INFECTED_PRISMARINE_BRICKS });
        RecipeHelper.addRecipe(new ItemStack(MPBlocks.INFECTED_DARK_PRISMARINE_STAIRS, 4), new Object[] { "X  ", "XX ", "XXX", 'X', MPBlocks.INFECTED_DARK_PRISMARINE });
        RecipeHelper.addRecipe(new ItemStack(MPBlocks.INFECTED_VEIN_STONE_BRICKS_STAIRS, 4), new Object[] { "X  ", "XX ", "XXX", 'X', MPBlocks.INFECTED_VEIN_STONE_BRICKS });
        RecipeHelper.addRecipe(new ItemStack(MPBlocks.CRACKED_INFECTED_STONE_BRICKS_STAIRS, 4), new Object[] { "X  ", "XX ", "XXX", 'X', MPBlocks.INFECTED_CRACKED_STONE_BRICKS });
        RecipeHelper.addRecipe(new ItemStack(MPBlocks.INFECTED_CUT_SANDSTONE_STAIRS, 4), new Object[] { "X  ", "XX ", "XXX", 'X', MPBlocks.INFECTED_CUT_SANDSTONE });
        RecipeHelper.addRecipe(new ItemStack(MPBlocks.TERRASTONE_STAIRS, 4), new Object[] { "X  ", "XX ", "XXX", 'X', MPBlocks.TERRASTONE });
        RecipeHelper.addRecipe(new ItemStack(MPBlocks.INFECTED_SANDSTONE_STAIRS, 4), new Object[] { "X  ", "XX ", "XXX", 'X', "sandstoneNibiru" });

        RecipeHelper.addShapelessRecipe(new ItemStack(MPBlocks.INFECTED_OAK_PLANKS, 4), "planks", MPBlocks.INFECTED_OAK_LOG );
        RecipeHelper.addShapelessRecipe(new ItemStack(MPBlocks.INFECTED_OAK_PLANKS, 4), "planks", "infected_oak_planks_from_jungle_log", MPBlocks.INFECTED_JUNGLE_LOG );
        RecipeHelper.addShapelessRecipe(new ItemStack(MPBlocks.ALIEN_BERRY_OAK_PLANKS, 4), "planks", MPBlocks.ALIEN_BERRY_OAK_LOG );
        RecipeHelper.addShapelessRecipe(new ItemStack(MPBlocks.NIBIRU_VEIN_COBBLESTONE), MPBlocks.NIBIRU_COBBLESTONE, MPBlocks.INFECTED_VINES );
        RecipeHelper.addShapelessRecipe(new ItemStack(MPBlocks.INFECTED_VEIN_STONE_BRICKS), MPBlocks.INFECTED_STONE_BRICKS, MPBlocks.INFECTED_VINES );
        RecipeHelper.addShapelessRecipe(new ItemStack(Items.DYE, 1, EnumDyeColor.GREEN.getDyeDamage()), null, "green_dye_from_pure_herb", MPBlocks.PURE_HERB );
        RecipeHelper.addShapelessRecipe(new ItemStack(Items.DYE, 1, EnumDyeColor.LIGHT_BLUE.getDyeDamage()), null, "light_blue_dye_from_terrapuff_herb", MPBlocks.TERRAPUFF_HERB );
        RecipeHelper.addShapelessRecipe(new ItemStack(Items.DYE, 1, EnumDyeColor.PINK.getDyeDamage()), null, "pink_dye_from_batasia_dandelion", MPBlocks.BATASIA_DANDELION );
        RecipeHelper.addShapelessRecipe(new ItemStack(Items.DYE, 1, EnumDyeColor.ORANGE.getDyeDamage()), null, "orange_dye_from_pyolonia", MPBlocks.PYOLONIA );
        RecipeHelper.addShapelessRecipe(new ItemStack(MPItems.BLUE_DYE), null, "blue_dye_from_philipy", MPBlocks.PHILIPY );
        RecipeHelper.addShapelessRecipe(new ItemStack(Items.DYE, 2, EnumDyeColor.ORANGE.getDyeDamage()), null, "orange_dye_from_orange_rose_bush", MPBlocks.INFECTED_ORANGE_ROSE_BUSH );
        RecipeHelper.addShapelessRecipe(new ItemStack(MPItems.NIBIRU_DUNGEON_KEY), MPItems.NIBIRU_DUNGEON_KEY_BLADE, MPItems.NIBIRU_DUNGEON_KEY_BOW );

        RecipeHelper.addRecipe(new ItemStack(MPBlocks.FRONOS_STONE_BRICKS, 4), new Object[] { "XX", "XX", 'X', MPBlocks.FRONOS_STONE });

        RecipeHelper.addRecipe(new ItemStack(MPBlocks.GRAPE_JELLY_BLOCK), "jelly_block", new Object[] { "XXX", "XXX", "XXX", 'X', MPItems.GRAPE_JELLY });
        RecipeHelper.addRecipe(new ItemStack(MPBlocks.RASPBERRY_JELLY_BLOCK), "jelly_block", new Object[] { "XXX", "XXX", "XXX", 'X', MPItems.RASPBERRY_JELLY });
        RecipeHelper.addRecipe(new ItemStack(MPBlocks.STRAWBERRY_JELLY_BLOCK), "jelly_block", new Object[] { "XXX", "XXX", "XXX", 'X', MPItems.STRAWBERRY_JELLY });
        RecipeHelper.addRecipe(new ItemStack(MPBlocks.BERRY_JELLY_BLOCK), "jelly_block", new Object[] { "XXX", "XXX", "XXX", 'X', MPItems.BERRY_JELLY });
        RecipeHelper.addRecipe(new ItemStack(MPBlocks.LIME_JELLY_BLOCK), "jelly_block", new Object[] { "XXX", "XXX", "XXX", 'X', MPItems.LIME_JELLY });
        RecipeHelper.addRecipe(new ItemStack(MPBlocks.ORANGE_JELLY_BLOCK), "jelly_block", new Object[] { "XXX", "XXX", "XXX", 'X', MPItems.ORANGE_JELLY });
        RecipeHelper.addRecipe(new ItemStack(MPBlocks.GREEN_JELLY_BLOCK), "jelly_block", new Object[] { "XXX", "XXX", "XXX", 'X', MPItems.GREEN_JELLY });
        RecipeHelper.addRecipe(new ItemStack(MPBlocks.LEMON_JELLY_BLOCK), "jelly_block", new Object[] { "XXX", "XXX", "XXX", 'X', MPItems.LEMON_JELLY });

        RecipeHelper.addRecipe(new ItemStack(MPBlocks.RED_CANDY_CANE), "candy_cane_block", new Object[] { "XXX", "XXX", "XXX", 'X', MPItems.RED_CANDY_CANE_STICK });
        RecipeHelper.addRecipe(new ItemStack(MPBlocks.GREEN_CANDY_CANE), "candy_cane_block", new Object[] { "XXX", "XXX", "XXX", 'X', MPItems.GREEN_CANDY_CANE_STICK });
        RecipeHelper.addRecipe(new ItemStack(MPBlocks.BLUE_CANDY_CANE), "candy_cane_block", new Object[] { "XXX", "XXX", "XXX", 'X', MPItems.BLUE_CANDY_CANE_STICK });
        RecipeHelper.addRecipe(new ItemStack(MPBlocks.ORANGE_CANDY_CANE), "candy_cane_block", new Object[] { "XXX", "XXX", "XXX", 'X', MPItems.ORANGE_CANDY_CANE_STICK });
        RecipeHelper.addRecipe(new ItemStack(MPBlocks.PINK_CANDY_CANE), "candy_cane_block", new Object[] { "XXX", "XXX", "XXX", 'X', MPItems.PINK_CANDY_CANE_STICK });
        RecipeHelper.addRecipe(new ItemStack(MPBlocks.YELLOW_CANDY_CANE), "candy_cane_block", new Object[] { "XXX", "XXX", "XXX", 'X', MPItems.YELLOW_CANDY_CANE_STICK });
        RecipeHelper.addRecipe(new ItemStack(MPBlocks.PURPLE_CANDY_CANE), "candy_cane_block", new Object[] { "XXX", "XXX", "XXX", 'X', MPItems.PURPLE_CANDY_CANE_STICK });
        RecipeHelper.addRecipe(new ItemStack(MPBlocks.RAINBOW_CANDY_CANE), "candy_cane_block", new Object[] { "XXX", "XXX", "XXX", 'X', MPItems.RAINBOW_CANDY_CANE_STICK });

        RecipeHelper.addShapelessRecipe(new ItemStack(MPBlocks.FRONOS_MOSSY_STONE_BRICKS), MPBlocks.FRONOS_STONE_BRICKS, "vine" );
        RecipeHelper.addRecipe(new ItemStack(MPBlocks.FRONOS_COARSE_DIRT, 4), "coarse_dirt", new Object[] { "DG", "GD", 'D', MPBlocks.FRONOS_DIRT, 'G', "gravel" });
        RecipeHelper.addRecipe(new ItemStack(MPBlocks.ALIEN_SHIP_DECORATION_1, 4), new Object[] { "XX", "XX", 'X', MPBlocks.ALIEN_SHIP_DECORATION_0 });
        RecipeHelper.addRecipe(new ItemStack(MPBlocks.ALIEN_CHEST), new Object[] { "XXX", "X X", "XXX", 'X', MPBlocks.ALIEN_SHIP_DECORATION_1 });
    }

    @Deprecated //TEMP
    protected static void addItemRecipe()
    {
        RecipeHelper.addRecipe(new ItemStack(MPItems.SPACE_WARPER_CORE), new Object[] { "PDP", "DED", "PDP", 'P', "compressedDesh", 'D', "gemDiamond", 'E', Items.ENDER_EYE });
        RecipeHelper.addRecipe(new ItemStack(MPItems.SPACE_BOW), new Object[] { " XS", "X S", " XS", 'S', "string", 'X', "compressedDesh" });
        RecipeHelper.addRecipe(new ItemStack(MPItems.SPACE_FISHING_ROD), new Object[] {"  S", " SX", "S X", 'S', "compressedDesh", 'X', "string"});
        RecipeHelper.addRecipe(new ItemStack(MPItems.LASER_BULLET, 8), "laser_bullet", new Object[] { " R", "I ", 'I', "ingotIron", 'R', "dustRedstone" });
        RecipeHelper.addRecipe(new ItemStack(MPItems.INFECTED_PURLONITE_LASER_BULLET, 8), "laser_bullet", new Object[] { " R", "I ", 'I', "ingotIron", 'R', MPItems.INFECTED_PURLONITE_SHARD });
        RecipeHelper.addRecipe(new ItemStack(MPItems.LASER_GUN), new Object[] { "C  ", " DT", "  D", 'C', new ItemStack(AsteroidsItems.basicItem, 1, 8), 'D', "compressedDesh", 'T', "compressedTitanium" });
        RecipeHelper.addRecipe(new ItemStack(MPItems.ALIEN_DEFENDER_REINFORCEMENT), new Object[] { "C", "I", "I", 'I', MPItems.GLOWING_IRON_INGOT, 'C', MPItems.SPACE_WARPER_CORE });

        RecipeHelper.addRecipe(new ItemStack(MPBlocks.INFECTED_PURLONITE_TORCH, 4), new Object[] { "I", "S", 'I', MPItems.INFECTED_PURLONITE_SHARD, 'S', "stickWood" });
        RecipeHelper.addShapelessRecipe(new ItemStack(MPItems.ILLENIUM_INGOT, 9), MPBlocks.ILLENIUM_BLOCK);
        RecipeHelper.addShapelessRecipe(new ItemStack(MPItems.SETRORIUM_SHARD, 9), MPBlocks.SETRORIUM_BLOCK);
        RecipeHelper.addShapelessRecipe(new ItemStack(MPItems.GLOWING_IRON_INGOT, 9), MPBlocks.GLOWING_IRON_BLOCK);
        RecipeHelper.addShapelessRecipe(new ItemStack(MPItems.INFECTED_PURLONITE_SLIMEBALL, 9), MPBlocks.INFECTED_PURLONITE_SLIME_BLOCK);

        RecipeHelper.addRecipe(new ItemStack(MPItems.INFECTED_PURLONITE_BOMB, 4), new Object[] { "III", "IGI", "III", 'I', MPItems.INFECTED_PURLONITE_SHARD, 'G', "gunpowder" });
        RecipeHelper.addRecipe(new ItemStack(MPItems.INFECTED_PURLONITE_ARROW, 6), new Object[] { "X", "S", "Y", 'Y', "feather", 'X', MPItems.INFECTED_PURLONITE_SHARD, 'S', new ItemStack(MarsItems.marsItemBasic, 1, 1) });

        RecipeHelper.addRecipe(new ItemStack(MPItems.ILLENIUM_HELMET, 1, 0), new Object[] { "XXX", "X X", 'X', MPItems.COMPRESSED_ILLENIUM });
        RecipeHelper.addRecipe(new ItemStack(MPItems.ILLENIUM_CHESTPLATE, 1, 0), new Object[] { "X X", "XXX", "XXX", 'X', MPItems.COMPRESSED_ILLENIUM });
        RecipeHelper.addRecipe(new ItemStack(MPItems.ILLENIUM_LEGGINGS, 1, 0), new Object[] { "XXX", "X X", "X X", 'X', MPItems.COMPRESSED_ILLENIUM });
        RecipeHelper.addRecipe(new ItemStack(MPItems.ILLENIUM_BOOTS, 1, 0), new Object[] { "X X", "X X", 'X', MPItems.COMPRESSED_ILLENIUM });
        RecipeHelper.addRecipe(new ItemStack(MPItems.BREATHABLE_ILLENIUM_HELMET, 1, 0), new Object[] { "XXX", "XYX", 'Y', GCItems.oxMask, 'X', MPItems.COMPRESSED_ILLENIUM });

        RecipeHelper.addRecipe(new ItemStack(MPItems.ILLENIUM_HOE), new Object[] { "XX", " Y", " Y", 'X', MPItems.COMPRESSED_ILLENIUM, 'Y', new ItemStack(MarsItems.marsItemBasic, 1, 1) });
        RecipeHelper.addRecipe(new ItemStack(MPItems.ILLENIUM_AXE), new Object[] { "XX", "XY", " Y", 'X', MPItems.COMPRESSED_ILLENIUM, 'Y', new ItemStack(MarsItems.marsItemBasic, 1, 1) });
        RecipeHelper.addRecipe(new ItemStack(MPItems.ILLENIUM_PICKAXE), new Object[] { "XXX", " Y ", " Y ", 'X', MPItems.COMPRESSED_ILLENIUM, 'Y', new ItemStack(MarsItems.marsItemBasic, 1, 1) });
        RecipeHelper.addRecipe(new ItemStack(MPItems.ILLENIUM_SWORD), new Object[] { "X", "X", "Y", 'X', MPItems.COMPRESSED_ILLENIUM, 'Y', new ItemStack(MarsItems.marsItemBasic, 1, 1) });
        RecipeHelper.addRecipe(new ItemStack(MPItems.ILLENIUM_SHOVEL), new Object[] { "X", "Y", "Y", 'X', MPItems.COMPRESSED_ILLENIUM, 'Y', new ItemStack(MarsItems.marsItemBasic, 1, 1) });

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

        RecipeHelper.addRecipe(new ItemStack(Items.PAPER, 3), null, "bread_from_infected_sugar_cane", new Object[] { "XXX", 'X', MPItems.INFECTED_SUGAR_CANE });
        RecipeHelper.addRecipe(new ItemStack(MPItems.INFECTED_GOLDEN_APPLE), new Object[] {"XXX", "XAX", "XXX", 'X', "ingotGold", 'A', MPItems.INFECTED_APPLE});
        RecipeHelper.addRecipe(new ItemStack(MPItems.ENCHANTED_INFECTED_GOLDEN_APPLE), new Object[] {"XXX", "XAX", "XXX", 'X', "blockGold", 'A', MPItems.INFECTED_APPLE});
        RecipeHelper.addRecipe(new ItemStack(MPItems.GOLDEN_ALIEN_BERRY), new Object[] {"XXX", "XAX", "XXX", 'X', "ingotGold", 'A', MPItems.ALIEN_BERRY});
        RecipeHelper.addRecipe(new ItemStack(Items.BREAD), null, "bread_from_infected_wheat", new Object[] { "XXX", 'X', MPItems.INFECTED_WHEAT });
        RecipeHelper.addRecipe(new ItemStack(MPItems.VEIN_EYE), new Object[] { " V ", "VXV", " V ", 'X', Items.ENDER_EYE, 'V', MPBlocks.INFECTED_VINES });
        RecipeHelper.addRecipe(new ItemStack(MPItems.INFECTED_OAK_DOOR, 3), "wooden_door", new Object[] { "XX", "XX", "XX", 'X', MPBlocks.INFECTED_OAK_PLANKS });
        RecipeHelper.addRecipe(new ItemStack(MPItems.ALIEN_BERRY_OAK_DOOR, 3), "wooden_door", new Object[] { "XX", "XX", "XX", 'X', MPBlocks.ALIEN_BERRY_OAK_PLANKS });
        RecipeHelper.addRecipe(new ItemStack(MPItems.WASTE_ROD_PICKER), new Object[] { " TP", " TT", "T  ", 'T', MPItems.SHLIME_TAIL, 'P', MPItems.COMPRESSED_ZYPTORIUM });
        RecipeHelper.addShapelessRecipe(new ItemStack(MPItems.INFERUMITE_CRYSTAL, 9), MPBlocks.INFERUMITE_BLOCK);
        RecipeHelper.addShapelessRecipe(new ItemStack(MPItems.MULTALIC_CRYSTAL_PIECES, 9), MPBlocks.MULTALIC_CRYSTAL_BLOCK);
        RecipeHelper.addShapelessRecipe(new ItemStack(Items.SUGAR), null, "sugar_from_infected_sugar_cane", MPItems.INFECTED_SUGAR_CANE);

        RecipeHelper.addRecipe(new ItemStack(MPItems.MULTALIC_CRYSTAL_HELMET, 1, 0), new Object[] { "XXX", "X X", 'X', MPItems.MULTALIC_CRYSTAL_PIECES });
        RecipeHelper.addRecipe(new ItemStack(MPItems.MULTALIC_CRYSTAL_CHESTPLATE, 1, 0), new Object[] { "X X", "XXX", "XXX", 'X', MPItems.MULTALIC_CRYSTAL_PIECES });
        RecipeHelper.addRecipe(new ItemStack(MPItems.MULTALIC_CRYSTAL_LEGGINGS, 1, 0), new Object[] { "XXX", "X X", "X X", 'X', MPItems.MULTALIC_CRYSTAL_PIECES });
        RecipeHelper.addRecipe(new ItemStack(MPItems.MULTALIC_CRYSTAL_BOOTS, 1, 0), new Object[] { "X X", "X X", 'X', MPItems.MULTALIC_CRYSTAL_PIECES });
        RecipeHelper.addRecipe(new ItemStack(MPItems.BREATHABLE_MULTALIC_CRYSTAL_HELMET, 1, 0), new Object[] { "XXX", "XYX", 'Y', GCItems.oxMask, 'X', MPItems.MULTALIC_CRYSTAL_PIECES });

        RecipeHelper.addRecipe(new ItemStack(MPItems.INFECTED_WOOD_HOE), new Object[] { "XX", " Y", " Y", 'X', MPBlocks.INFECTED_OAK_PLANKS, 'Y', "stickWood" });
        RecipeHelper.addRecipe(new ItemStack(MPItems.INFECTED_WOOD_AXE), new Object[] { "XX", "XY", " Y", 'X', MPBlocks.INFECTED_OAK_PLANKS, 'Y', "stickWood" });
        RecipeHelper.addRecipe(new ItemStack(MPItems.INFECTED_WOOD_PICKAXE), new Object[] { "XXX", " Y ", " Y ", 'X', MPBlocks.INFECTED_OAK_PLANKS, 'Y', "stickWood" });
        RecipeHelper.addRecipe(new ItemStack(MPItems.INFECTED_WOOD_SWORD), new Object[] { "X", "X", "Y", 'X', MPBlocks.INFECTED_OAK_PLANKS, 'Y', "stickWood" });
        RecipeHelper.addRecipe(new ItemStack(MPItems.INFECTED_WOOD_SHOVEL), new Object[] { "X", "Y", "Y", 'X', MPBlocks.INFECTED_OAK_PLANKS, 'Y', "stickWood" });
        RecipeHelper.addRecipe(new ItemStack(MPItems.ALIEN_BERRY_WOOD_HOE), new Object[] { "XX", " Y", " Y", 'X', MPBlocks.ALIEN_BERRY_OAK_PLANKS, 'Y', "stickWood" });
        RecipeHelper.addRecipe(new ItemStack(MPItems.ALIEN_BERRY_WOOD_AXE), new Object[] { "XX", "XY", " Y", 'X', MPBlocks.ALIEN_BERRY_OAK_PLANKS, 'Y', "stickWood" });
        RecipeHelper.addRecipe(new ItemStack(MPItems.ALIEN_BERRY_WOOD_PICKAXE), new Object[] { "XXX", " Y ", " Y ", 'X', MPBlocks.ALIEN_BERRY_OAK_PLANKS, 'Y', "stickWood" });
        RecipeHelper.addRecipe(new ItemStack(MPItems.ALIEN_BERRY_WOOD_SWORD), new Object[] { "X", "X", "Y", 'X', MPBlocks.ALIEN_BERRY_OAK_PLANKS, 'Y', "stickWood" });
        RecipeHelper.addRecipe(new ItemStack(MPItems.ALIEN_BERRY_WOOD_SHOVEL), new Object[] { "X", "Y", "Y", 'X', MPBlocks.ALIEN_BERRY_OAK_PLANKS, 'Y', "stickWood" });
        RecipeHelper.addRecipe(new ItemStack(MPItems.NIBIRU_STONE_HOE), new Object[] { "XX", " Y", " Y", 'X', MPBlocks.NIBIRU_COBBLESTONE, 'Y', "stickWood" });
        RecipeHelper.addRecipe(new ItemStack(MPItems.NIBIRU_STONE_AXE), new Object[] { "XX", "XY", " Y", 'X', MPBlocks.NIBIRU_COBBLESTONE, 'Y', "stickWood" });
        RecipeHelper.addRecipe(new ItemStack(MPItems.NIBIRU_STONE_PICKAXE), new Object[] { "XXX", " Y ", " Y ", 'X', MPBlocks.NIBIRU_COBBLESTONE, 'Y', "stickWood" });
        RecipeHelper.addRecipe(new ItemStack(MPItems.NIBIRU_STONE_SWORD), new Object[] { "X", "X", "Y", 'X', MPBlocks.NIBIRU_COBBLESTONE, 'Y', "stickWood" });
        RecipeHelper.addRecipe(new ItemStack(MPItems.NIBIRU_STONE_SHOVEL), new Object[] { "X", "Y", "Y", 'X', MPBlocks.NIBIRU_COBBLESTONE, 'Y', "stickWood" });
        RecipeHelper.addRecipe(new ItemStack(MPItems.MULTALIC_CRYSTAL_HOE), new Object[] { "XX", " Y", " Y", 'X', MPItems.MULTALIC_CRYSTAL_PIECES, 'Y', "stickWood" });
        RecipeHelper.addRecipe(new ItemStack(MPItems.MULTALIC_CRYSTAL_AXE), new Object[] { "XX", "XY", " Y", 'X', MPItems.MULTALIC_CRYSTAL_PIECES, 'Y', "stickWood" });
        RecipeHelper.addRecipe(new ItemStack(MPItems.MULTALIC_CRYSTAL_PICKAXE), new Object[] { "XXX", " Y ", " Y ", 'X', MPItems.MULTALIC_CRYSTAL_PIECES, 'Y', "stickWood" });
        RecipeHelper.addRecipe(new ItemStack(MPItems.MULTALIC_CRYSTAL_SWORD), new Object[] { "X", "X", "Y", 'X', MPItems.MULTALIC_CRYSTAL_PIECES, 'Y', "stickWood" });
        RecipeHelper.addRecipe(new ItemStack(MPItems.MULTALIC_CRYSTAL_SHOVEL), new Object[] { "X", "Y", "Y", 'X', MPItems.MULTALIC_CRYSTAL_PIECES, 'Y', "stickWood" });

        RecipeHelper.addShapelessRecipe(new ItemStack(MPItems.RED_CANDY_CANE_STICK, 9), "candy_cane_stick", MPBlocks.RED_CANDY_CANE);
        RecipeHelper.addShapelessRecipe(new ItemStack(MPItems.GREEN_CANDY_CANE_STICK, 9), "candy_cane_stick", MPBlocks.GREEN_CANDY_CANE);
        RecipeHelper.addShapelessRecipe(new ItemStack(MPItems.BLUE_CANDY_CANE_STICK, 9), "candy_cane_stick", MPBlocks.BLUE_CANDY_CANE);
        RecipeHelper.addShapelessRecipe(new ItemStack(MPItems.ORANGE_CANDY_CANE_STICK, 9), "candy_cane_stick", MPBlocks.ORANGE_CANDY_CANE);
        RecipeHelper.addShapelessRecipe(new ItemStack(MPItems.PINK_CANDY_CANE_STICK, 9), "candy_cane_stick", MPBlocks.PINK_CANDY_CANE);
        RecipeHelper.addShapelessRecipe(new ItemStack(MPItems.YELLOW_CANDY_CANE_STICK, 9), "candy_cane_stick", MPBlocks.YELLOW_CANDY_CANE);
        RecipeHelper.addShapelessRecipe(new ItemStack(MPItems.PURPLE_CANDY_CANE_STICK, 9), "candy_cane_stick", MPBlocks.PURPLE_CANDY_CANE);
        RecipeHelper.addShapelessRecipe(new ItemStack(MPItems.RAINBOW_CANDY_CANE_STICK, 9), "candy_cane_stick", MPBlocks.RAINBOW_CANDY_CANE);

        RecipeHelper.addShapelessRecipe(new ItemStack(MPItems.GRAPE_JELLY, 9), "jelly", MPBlocks.GRAPE_JELLY_BLOCK);
        RecipeHelper.addShapelessRecipe(new ItemStack(MPItems.RASPBERRY_JELLY, 9), "jelly", MPBlocks.RASPBERRY_JELLY_BLOCK);
        RecipeHelper.addShapelessRecipe(new ItemStack(MPItems.STRAWBERRY_JELLY, 9), "jelly", MPBlocks.STRAWBERRY_JELLY_BLOCK);
        RecipeHelper.addShapelessRecipe(new ItemStack(MPItems.BERRY_JELLY, 9), "jelly", MPBlocks.BERRY_JELLY_BLOCK);
        RecipeHelper.addShapelessRecipe(new ItemStack(MPItems.LIME_JELLY, 9), "jelly", MPBlocks.LIME_JELLY_BLOCK);
        RecipeHelper.addShapelessRecipe(new ItemStack(MPItems.ORANGE_JELLY, 9), "jelly", MPBlocks.ORANGE_JELLY_BLOCK);
        RecipeHelper.addShapelessRecipe(new ItemStack(MPItems.GREEN_JELLY, 9), "jelly", MPBlocks.GREEN_JELLY_BLOCK);
        RecipeHelper.addShapelessRecipe(new ItemStack(MPItems.LEMON_JELLY, 9), "jelly", MPBlocks.LEMON_JELLY_BLOCK);

        RecipeHelper.addRecipe(new ItemStack(MPItems.UPGRADE_TEMPLATE), "upgrade", new Object[] { " D ", "DTD", " D ", 'D', "compressedDesh", 'T', "compressedTitanium" });
        ItemStack diamondSword = new ItemStack(Items.DIAMOND_SWORD);
        diamondSword.addEnchantment(Enchantments.SHARPNESS, 5);
        diamondSword.getTagCompound().setInteger("RepairCost", 1);
        RecipeHelper.addRecipe(new ItemStack(MPItems.SHIELD_DAMAGE_UPGRADE), "upgrade", new Object[] { " S ", "STS", " S ", 'S', diamondSword, 'T', MPItems.UPGRADE_TEMPLATE });
        RecipeHelper.addRecipe(new ItemStack(MPItems.SHIELD_SIZE_UPGRADE), "upgrade", new Object[] { "FFF", "FTF", "FFF", 'F', new ItemStack(Items.FISH, 1, 3), 'T', MPItems.UPGRADE_TEMPLATE });
        RecipeHelper.addRecipe(new ItemStack(MPItems.SHIELD_CAPACITY_UPGRADE), "upgrade", new Object[] { "SAS", "ATA", "SAS", 'A', VenusItems.atomicBattery, 'S', "netherStar", 'T', MPItems.UPGRADE_TEMPLATE });
        ItemStack speedPotion = PotionUtils.addPotionToItemStack(new ItemStack(Items.POTIONITEM), PotionTypes.STRONG_SWIFTNESS);
        RecipeHelper.addRecipe(new ItemStack(MPItems.MACHINE_SPEED_UPGRADE), "upgrade", new Object[] { "RSR", "STS", "RSR", 'R', "blockRedstone", 'S', speedPotion, 'T', MPItems.UPGRADE_TEMPLATE });
    }

    private static void addOtherRecipe()
    {
        HashMap<Integer, ItemStack> input = new HashMap<>();

        // compressor
        RecipeHelper.addCompressorRecipe(new ItemStack(MPItems.COMPRESSED_SETRORIUM), "XXX", "XXX", "XXX", 'X', MPItems.SETRORIUM_SHARD);
        RecipeHelper.addShapelessCompressorRecipe(new ItemStack(MPItems.COMPRESSED_ILLENIUM), MPItems.ILLENIUM_INGOT, MPItems.ILLENIUM_INGOT);
        RecipeHelper.addShapelessCompressorRecipe(new ItemStack(MPItems.COMPRESSED_DIREMSIUM), MPItems.DIREMSIUM_INGOT, MPItems.DIREMSIUM_INGOT);
        RecipeHelper.addShapelessCompressorRecipe(new ItemStack(MPItems.COMPRESSED_ZYPTORIUM), MPItems.ZYPTORIUM_INGOT, MPItems.ZYPTORIUM_INGOT);
        RecipeHelper.addShapelessCompressorRecipe(new ItemStack(MPItems.COMPRESSED_EXTRAILONITE), MPItems.EXTRAILONITE_INGOT, MPItems.EXTRAILONITE_INGOT);

        // dark energy transform
        input.put(0, new ItemStack(Items.ENDER_PEARL));
        input.put(1, new ItemStack(MPItems.ALIEN_MINER_PART, 4));
        DarkEnergyRecipeData.registerRecipe(input, new ItemStack(MPItems.DARK_ENERGY_PEARL), 120);

        input = new HashMap<>();
        input.put(0, new ItemStack(Items.IRON_INGOT));
        input.put(1, new ItemStack(MPItems.ALIEN_MINER_PART, 2));
        DarkEnergyRecipeData.registerRecipe(input, new ItemStack(MPItems.GLOWING_IRON_INGOT), 80);

        // black hole storage
        input = new HashMap<>();
        input.put(1, new ItemStack(MPItems.BLACK_HOLE_FRAGMENTS));
        input.put(2, new ItemStack(MPItems.BLACK_HOLE_FRAGMENTS));
        input.put(3, new ItemStack(MPItems.BLACK_HOLE_FRAGMENTS));
        input.put(4, new ItemStack(MPItems.BLACK_HOLE_FRAGMENTS));
        input.put(5, new ItemStack(MPItems.BLACK_HOLE_FRAGMENTS));
        input.put(6, new ItemStack(MPItems.BLACK_HOLE_FRAGMENTS));
        input.put(7, new ItemStack(MPItems.BLACK_HOLE_FRAGMENTS));
        input.put(8, new ItemStack(MPItems.BLACK_HOLE_FRAGMENTS));
        input.put(9, new ItemStack(MPItems.BLACK_HOLE_FRAGMENTS));
        input.put(10, new ItemStack(GCItems.flagPole));
        input.put(11, new ItemStack(GCItems.flagPole));
        input.put(12, new ItemStack(GCItems.flagPole));
        input.put(13, new ItemStack(MPItems.GLOWING_IRON_INGOT));
        input.put(14, new ItemStack(AsteroidsItems.basicItem, 1, 5));
        input.put(15, new ItemStack(MPItems.GLOWING_IRON_INGOT));
        input.put(16, new ItemStack(AsteroidsItems.basicItem, 1, 5));
        input.put(17, new ItemStack(MPItems.GLOWING_IRON_INGOT));
        input.put(18, new ItemStack(MPItems.GLOWING_IRON_INGOT));
        input.put(19, new ItemStack(AsteroidsItems.basicItem, 1, 5));
        input.put(20, new ItemStack(MPBlocks.ALIEN_CHEST));
        input.put(21, new ItemStack(AsteroidsItems.basicItem, 1, 5));
        input.put(22, new ItemStack(MPItems.GLOWING_IRON_INGOT));
        BlackHoleStorageRecipes.RECIPES.add(new NasaWorkbenchRecipe(new ItemStack(MPBlocks.BLACK_HOLE_STORAGE), input));
    }
}