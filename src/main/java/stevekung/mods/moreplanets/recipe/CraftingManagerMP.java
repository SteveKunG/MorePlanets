package stevekung.mods.moreplanets.recipe;

import java.util.HashMap;

import micdoodle8.mods.galacticraft.core.GCBlocks;
import micdoodle8.mods.galacticraft.core.GCItems;
import micdoodle8.mods.galacticraft.core.recipe.NasaWorkbenchRecipe;
import micdoodle8.mods.galacticraft.planets.asteroids.items.AsteroidsItems;
import micdoodle8.mods.galacticraft.planets.mars.items.MarsItems;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import stevekung.mods.moreplanets.init.MPBlocks;
import stevekung.mods.moreplanets.init.MPItems;
import stevekung.mods.moreplanets.planets.chalos.items.ChalosItems;
import stevekung.mods.moreplanets.planets.diona.items.DionaItems;
import stevekung.mods.moreplanets.planets.fronos.items.FronosItems;
import stevekung.mods.moreplanets.utils.debug.JSONRecipe;
import stevekung.mods.moreplanets.utils.helper.RecipeHelper;

public class CraftingManagerMP
{
    public static void init()
    {
        //CraftingManagerMP.addBlockRecipe();
        //CraftingManagerMP.addItemRecipe();
        CraftingManagerMP.addOtherRecipe();
        //CapsuleRecipes.init();

        /*CraftingManagerDiona.init();
        CraftingManagerChalos.init();
        CraftingManagerNibiru.init();
        CraftingManagerFronos.init();*/
        JSONRecipe.generateConstants();
    }

    protected static void addBlockRecipe()
    {
        RecipeHelper.addRecipe(new ItemStack(MPBlocks.SPACE_WARP_PAD, 9), new Object[] { "PPP", "MMM", 'P', new ItemStack(AsteroidsItems.basicItem, 1, 5), 'M', new ItemStack(GCBlocks.basicBlock, 1, 12) });
        RecipeHelper.addRecipe(new ItemStack(MPBlocks.TINTED_GLASS, 8), "tinted_glass", new Object[] {"III", "IDI", "III", 'I', "glass", 'D', new ItemStack(MarsItems.marsItemBasic, 1, 5)});
        RecipeHelper.addRecipe(new ItemStack(MPBlocks.POLISHED_TIN_DECORATION_BLOCK, 4), new Object[] { "TT", "TT", 'T', new ItemStack(GCBlocks.basicBlock, 1, 4) });
        RecipeHelper.addRecipe(new ItemStack(MPBlocks.POLISHED_ALUMINUM_DECORATION_BLOCK, 8), new Object[] { "TTT", "TAT", "TTT", 'T', MPBlocks.POLISHED_TIN_DECORATION_BLOCK, 'A', new ItemStack(GCItems.basicItem, 1, 8) });
        RecipeHelper.addRecipe(new ItemStack(MPBlocks.DARK_ENERGY_RECEIVER), new Object[] { "HPH", "SCS", "ITI", 'H', new ItemStack(AsteroidsItems.basicItem, 1, 5), 'P', GCItems.flagPole, 'S', new ItemStack(GCItems.basicItem, 1, 1), 'C', MPItems.SPACE_WARPER_CORE, 'I', "ingotIron", 'T', new ItemStack(AsteroidsItems.basicItem, 1, 5) });
        RecipeHelper.addRecipe(new ItemStack(MPBlocks.DARK_ENERGY_STORAGE_CLUSTER), new Object[] { "EAE", "AWA", "EAE", 'E', new ItemStack(GCBlocks.machineTiered, 1, 8), 'A', DionaItems.ALIEN_MINER_PART, 'W', new ItemStack(GCItems.basicItem, 1, 14) });
        RecipeHelper.addRecipe(new ItemStack(MPBlocks.NUCLEAR_WASTE_ENERGY_STORAGE_CLUSTER), new Object[] { "EAE", "ANA", "EAE", 'E', new ItemStack(MPBlocks.DARK_ENERGY_STORAGE_CLUSTER), 'A', DionaItems.ALIEN_MINER_PART, 'N', MPBlocks.NUCLEAR_WASTE_TANK });
        RecipeHelper.addRecipe(new ItemStack(MPBlocks.SPACE_PORTAL), new Object[] { "OFO", "OOO", "OOO", 'O', "obsidian", 'F', Items.FLINT_AND_STEEL });

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

        RecipeHelper.addRecipe(new ItemStack(MPBlocks.WHITE_TINTED_GLASS, 8), "tinted_glass", new Object[] {"III", "IDI", "III", 'I', MPBlocks.TINTED_GLASS, 'D', new ItemStack(Items.DYE, 1, 15)});
        RecipeHelper.addRecipe(new ItemStack(MPBlocks.ORANGE_TINTED_GLASS, 8), "tinted_glass", new Object[] {"III", "IDI", "III", 'I', MPBlocks.TINTED_GLASS, 'D', new ItemStack(Items.DYE, 1, 14)});
        RecipeHelper.addRecipe(new ItemStack(MPBlocks.MAGENTA_TINTED_GLASS, 8), "tinted_glass", new Object[] {"III", "IDI", "III", 'I', MPBlocks.TINTED_GLASS, 'D', new ItemStack(Items.DYE, 1, 13)});
        RecipeHelper.addRecipe(new ItemStack(MPBlocks.LIGHT_BLUE_TINTED_GLASS, 8), "tinted_glass", new Object[] {"III", "IDI", "III", 'I', MPBlocks.TINTED_GLASS, 'D', new ItemStack(Items.DYE, 1, 12)});
        RecipeHelper.addRecipe(new ItemStack(MPBlocks.YELLOW_TINTED_GLASS, 8), "tinted_glass", new Object[] {"III", "IDI", "III", 'I', MPBlocks.TINTED_GLASS, 'D', new ItemStack(Items.DYE, 1, 11)});
        RecipeHelper.addRecipe(new ItemStack(MPBlocks.LIME_TINTED_GLASS, 8), "tinted_glass", new Object[] {"III", "IDI", "III", 'I', MPBlocks.TINTED_GLASS, 'D', new ItemStack(Items.DYE, 1, 10)});
        RecipeHelper.addRecipe(new ItemStack(MPBlocks.PINK_TINTED_GLASS, 8), "tinted_glass", new Object[] {"III", "IDI", "III", 'I', MPBlocks.TINTED_GLASS, 'D', new ItemStack(Items.DYE, 1, 9)});
        RecipeHelper.addRecipe(new ItemStack(MPBlocks.GRAY_TINTED_GLASS, 8), "tinted_glass", new Object[] {"III", "IDI", "III", 'I', MPBlocks.TINTED_GLASS, 'D', new ItemStack(Items.DYE, 1, 8)});
        RecipeHelper.addRecipe(new ItemStack(MPBlocks.SILVER_TINTED_GLASS, 8), "tinted_glass", new Object[] {"III", "IDI", "III", 'I', MPBlocks.TINTED_GLASS, 'D', new ItemStack(Items.DYE, 1, 7)});
        RecipeHelper.addRecipe(new ItemStack(MPBlocks.CYAN_TINTED_GLASS, 8), "tinted_glass", new Object[] {"III", "IDI", "III", 'I', MPBlocks.TINTED_GLASS, 'D', new ItemStack(Items.DYE, 1, 6)});
        RecipeHelper.addRecipe(new ItemStack(MPBlocks.PURPLE_TINTED_GLASS, 8), "tinted_glass", new Object[] {"III", "IDI", "III", 'I', MPBlocks.TINTED_GLASS, 'D', new ItemStack(Items.DYE, 1, 5)});
        RecipeHelper.addRecipe(new ItemStack(MPBlocks.BLUE_TINTED_GLASS, 8), "tinted_glass", new Object[] {"III", "IDI", "III", 'I', MPBlocks.TINTED_GLASS, 'D', new ItemStack(Items.DYE, 1, 4)});
        RecipeHelper.addRecipe(new ItemStack(MPBlocks.BROWN_TINTED_GLASS, 8), "tinted_glass", new Object[] {"III", "IDI", "III", 'I', MPBlocks.TINTED_GLASS, 'D', new ItemStack(Items.DYE, 1, 3)});
        RecipeHelper.addRecipe(new ItemStack(MPBlocks.GREEN_TINTED_GLASS, 8), "tinted_glass", new Object[] {"III", "IDI", "III", 'I', MPBlocks.TINTED_GLASS, 'D', new ItemStack(Items.DYE, 1, 2)});
        RecipeHelper.addRecipe(new ItemStack(MPBlocks.RED_TINTED_GLASS, 8), "tinted_glass", new Object[] {"III", "IDI", "III", 'I', MPBlocks.TINTED_GLASS, 'D', new ItemStack(Items.DYE, 1, 1)});
        RecipeHelper.addRecipe(new ItemStack(MPBlocks.BLACK_TINTED_GLASS, 8), "tinted_glass", new Object[] {"III", "IDI", "III", 'I', MPBlocks.TINTED_GLASS, 'D', new ItemStack(Items.DYE, 1, 0)});

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
    }

    protected static void addItemRecipe()
    {
        RecipeHelper.addRecipe(new ItemStack(MPItems.SPACE_WARPER_CORE), new Object[] { "PDP", "DED", "PDP", 'P', new ItemStack(MarsItems.marsItemBasic, 1, 5), 'D', "gemDiamond", 'E', Items.ENDER_EYE });
        RecipeHelper.addRecipe(new ItemStack(MPItems.SPACE_BOW), new Object[] { " XS", "X S", " XS", 'S', "string", 'X', new ItemStack(MarsItems.marsItemBasic, 1, 5) });
        RecipeHelper.addRecipe(new ItemStack(MPItems.SPACE_FISHING_ROD), new Object[] {"  S", " SX", "S X", 'S', new ItemStack(MarsItems.marsItemBasic, 1, 5), 'X', "string"});
        RecipeHelper.addRecipe(new ItemStack(MPItems.LASER_BULLET, 8), "laser_bullet", new Object[] { " R", "I ", 'I', "ingotIron", 'R', "dustRedstone" });
        RecipeHelper.addRecipe(new ItemStack(MPItems.INFECTED_CRYSTALLIZED_LASER_BULLET, 8), "laser_bullet", new Object[] { " R", "I ", 'I', "ingotIron", 'R', DionaItems.INFECTED_CRYSTALLIZED_SHARD });
        RecipeHelper.addRecipe(new ItemStack(MPItems.LASER_GUN), new Object[] { "C  ", " DT", "  D", 'C', new ItemStack(AsteroidsItems.basicItem, 1, 8), 'D', new ItemStack(MarsItems.marsItemBasic, 1, 5), 'T', new ItemStack(AsteroidsItems.basicItem, 1, 6) });
        RecipeHelper.addRecipe(new ItemStack(MPItems.ALIEN_DEFENDER_REINFORCEMENT), new Object[] { "C", "I", "I", 'I', DionaItems.GLOWING_IRON_INGOT, 'C', MPItems.SPACE_WARPER_CORE });
    }

    private static void addOtherRecipe()
    {
        HashMap<Integer, ItemStack> input = new HashMap<>();

        // compressor
        RecipeHelper.addCompressorRecipe(new ItemStack(DionaItems.COMPRESSED_SETRORIUM), "XXX", "XXX", "XXX", 'X', DionaItems.SETRORIUM_SHARD);
        RecipeHelper.addShapelessCompressorRecipe(new ItemStack(DionaItems.COMPRESSED_ILLENIUM), DionaItems.ILLENIUM_INGOT, DionaItems.ILLENIUM_INGOT);
        RecipeHelper.addShapelessCompressorRecipe(new ItemStack(ChalosItems.COMPRESSED_DIREMSIUM), ChalosItems.DIREMSIUM_INGOT, ChalosItems.DIREMSIUM_INGOT);
        RecipeHelper.addShapelessCompressorRecipe(new ItemStack(ChalosItems.COMPRESSED_ZYPTORIUM), ChalosItems.ZYPTORIUM_INGOT, ChalosItems.ZYPTORIUM_INGOT);
        RecipeHelper.addShapelessCompressorRecipe(new ItemStack(FronosItems.COMPRESSED_EXTRAILONITE), FronosItems.EXTRAILONITE_INGOT, FronosItems.EXTRAILONITE_INGOT);

        // dark energy transform
        input.put(0, new ItemStack(Items.ENDER_PEARL));
        input.put(1, new ItemStack(DionaItems.ALIEN_MINER_PART, 4));
        DarkEnergyRecipeData.registerRecipe(input, new ItemStack(DionaItems.DARK_ENERGY_PEARL), 120);

        input = new HashMap<>();
        input.put(0, new ItemStack(Items.IRON_INGOT));
        input.put(1, new ItemStack(DionaItems.ALIEN_MINER_PART, 2));
        DarkEnergyRecipeData.registerRecipe(input, new ItemStack(DionaItems.GLOWING_IRON_INGOT), 80);

        // black hole storage
        input = new HashMap<>();
        input.put(1, new ItemStack(DionaItems.BLACK_HOLE_FRAGMENTS));
        input.put(2, new ItemStack(DionaItems.BLACK_HOLE_FRAGMENTS));
        input.put(3, new ItemStack(DionaItems.BLACK_HOLE_FRAGMENTS));
        input.put(4, new ItemStack(DionaItems.BLACK_HOLE_FRAGMENTS));
        input.put(5, new ItemStack(DionaItems.BLACK_HOLE_FRAGMENTS));
        input.put(6, new ItemStack(DionaItems.BLACK_HOLE_FRAGMENTS));
        input.put(7, new ItemStack(DionaItems.BLACK_HOLE_FRAGMENTS));
        input.put(8, new ItemStack(DionaItems.BLACK_HOLE_FRAGMENTS));
        input.put(9, new ItemStack(DionaItems.BLACK_HOLE_FRAGMENTS));
        input.put(10, new ItemStack(GCItems.flagPole));
        input.put(11, new ItemStack(GCItems.flagPole));
        input.put(12, new ItemStack(GCItems.flagPole));
        input.put(13, new ItemStack(DionaItems.GLOWING_IRON_INGOT));
        input.put(14, new ItemStack(AsteroidsItems.basicItem, 1, 5));
        input.put(15, new ItemStack(DionaItems.GLOWING_IRON_INGOT));
        input.put(16, new ItemStack(AsteroidsItems.basicItem, 1, 5));
        input.put(17, new ItemStack(DionaItems.GLOWING_IRON_INGOT));
        input.put(18, new ItemStack(DionaItems.GLOWING_IRON_INGOT));
        input.put(19, new ItemStack(AsteroidsItems.basicItem, 1, 5));
        input.put(20, new ItemStack(Items.ENDER_EYE));
        input.put(21, new ItemStack(AsteroidsItems.basicItem, 1, 5));
        input.put(22, new ItemStack(DionaItems.GLOWING_IRON_INGOT));
        BlackHoleStorageRecipes.recipes.add(new NasaWorkbenchRecipe(new ItemStack(MPBlocks.BLACK_HOLE_STORAGE), input));
    }
}