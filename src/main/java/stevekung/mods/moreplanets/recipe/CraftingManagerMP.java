package stevekung.mods.moreplanets.recipe;

import java.util.HashMap;

import micdoodle8.mods.galacticraft.core.GCBlocks;
import micdoodle8.mods.galacticraft.core.GCItems;
import micdoodle8.mods.galacticraft.core.recipe.NasaWorkbenchRecipe;
import micdoodle8.mods.galacticraft.planets.asteroids.items.AsteroidsItems;
import micdoodle8.mods.galacticraft.planets.mars.items.MarsItems;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import stevekung.mods.moreplanets.init.MPBlocks;
import stevekung.mods.moreplanets.init.MPItems;
import stevekung.mods.moreplanets.module.planets.chalos.blocks.ChalosBlocks;
import stevekung.mods.moreplanets.module.planets.chalos.items.ChalosItems;
import stevekung.mods.moreplanets.module.planets.chalos.recipe.CraftingManagerChalos;
import stevekung.mods.moreplanets.module.planets.diona.items.DionaItems;
import stevekung.mods.moreplanets.module.planets.diona.recipe.CraftingManagerDiona;
import stevekung.mods.moreplanets.module.planets.fronos.items.FronosItems;
import stevekung.mods.moreplanets.module.planets.fronos.recipe.CraftingManagerFronos;
import stevekung.mods.moreplanets.module.planets.nibiru.blocks.NibiruBlocks;
import stevekung.mods.moreplanets.module.planets.nibiru.recipe.CraftingManagerNibiru;
import stevekung.mods.moreplanets.util.helper.RecipeHelper;

public class CraftingManagerMP
{
    public static void init()
    {
        CraftingManagerMP.addBlockRecipe();
        CraftingManagerMP.addItemRecipe();
        CraftingManagerMP.addOtherRecipe();
        CapsuleRecipes.init();

        CraftingManagerDiona.init();
        CraftingManagerChalos.init();
        CraftingManagerNibiru.init();
        CraftingManagerFronos.init();
    }

    private static void addBlockRecipe()
    {
        RecipeHelper.addRecipe(new ItemStack(MPBlocks.SPACE_WARP_PAD, 9), new Object[] { "PPP", "MMM", 'P', new ItemStack(AsteroidsItems.basicItem, 1, 5), 'M', new ItemStack(GCBlocks.basicBlock, 1, 12) });
        RecipeHelper.addRecipe(new ItemStack(MPBlocks.ROCKET_CRUSHER, 1), new Object[] { "PPP", "HAH", "WFW", 'P', Blocks.PISTON, 'H', new ItemStack(AsteroidsItems.basicItem, 1, 5), 'A', Blocks.ANVIL, 'W', new ItemStack(GCBlocks.aluminumWire, 1, 1), 'F', new ItemStack(GCItems.basicItem, 1, 14) });
        RecipeHelper.addRecipe(new ItemStack(MPBlocks.WHITE_TINTED_GLASS, 8), new Object[] {"III", "IDI", "III", 'I', new ItemStack(Blocks.GLASS), 'D', new ItemStack(MarsItems.marsItemBasic, 1, 5)});
        RecipeHelper.addRecipe(new ItemStack(MPBlocks.POLISHED_TIN_DECORATION_BLOCK, 4), new Object[] { "TT", "TT", 'T', new ItemStack(GCBlocks.basicBlock, 1, 4) });
        RecipeHelper.addRecipe(new ItemStack(MPBlocks.POLISHED_ALUMINUM_DECORATION_BLOCK, 8), new Object[] { "TTT", "TAT", "TTT", 'T', MPBlocks.POLISHED_TIN_DECORATION_BLOCK, 'A', new ItemStack(GCItems.basicItem, 1, 8) });
        RecipeHelper.addRecipe(new ItemStack(MPBlocks.DARK_ENERGY_RECEIVER), new Object[] { "HPH", "SCS", "ITI", 'H', new ItemStack(AsteroidsItems.basicItem, 1, 5), 'P', new ItemStack(GCItems.flagPole), 'S', new ItemStack(GCItems.basicItem, 1, 1), 'C', MPItems.SPACE_WARPER_CORE, 'I', Items.IRON_INGOT, 'T', new ItemStack(AsteroidsItems.basicItem, 1, 5) });
        RecipeHelper.addRecipe(new ItemStack(MPBlocks.DARK_ENERGY_STORAGE_CLUSTER), new Object[] { "EAE", "AWA", "EAE", 'E', new ItemStack(GCBlocks.machineTiered, 1, 8), 'A', new ItemStack(DionaItems.DIONA_ITEM, 1, 5), 'W', new ItemStack(GCItems.basicItem, 1, 14) });
        RecipeHelper.addRecipe(new ItemStack(MPBlocks.NUCLEAR_WASTE_ENERGY_STORAGE_CLUSTER), new Object[] { "EAE", "ANA", "EAE", 'E', new ItemStack(MPBlocks.DARK_ENERGY_STORAGE_CLUSTER), 'A', new ItemStack(DionaItems.DIONA_ITEM, 1, 5), 'N', NibiruBlocks.NUCLEAR_WASTE_TANK });
        RecipeHelper.addRecipe(new ItemStack(MPBlocks.SPACE_PORTAL), new Object[] { "OFO", "OOO", "OOO", 'O', Blocks.OBSIDIAN, 'F', Items.FLINT_AND_STEEL });

        //        RecipeHelper.addRecipe(new ItemStack(MPBlocks.HALF_DUNGEON_BRICK_SLAB_1, 6, 0), new Object[] { "XXX", 'X', new ItemStack(DionaBlocks.DIONA_BLOCK, 1, 11) });
        //        RecipeHelper.addRecipe(new ItemStack(MPBlocks.HALF_DUNGEON_BRICK_SLAB_1, 6, 1), new Object[] { "XXX", 'X', new ItemStack(ChalosBlocks.CHALOS_BLOCK, 1, 11) });
        RecipeHelper.addRecipe(new ItemStack(MPBlocks.HALF_DUNGEON_BRICK_SLAB_1, 6, 2), new Object[] { "XXX", 'X', new ItemStack(NibiruBlocks.NIBIRU_BLOCK, 1, 8) });

        //        RecipeHelper.addRecipe(new ItemStack(MPBlocks.HALF_COBBLESTONE_SLAB_1, 6, 0), new Object[] { "XXX", 'X', new ItemStack(DionaBlocks.DIONA_BLOCK, 1, 3) });
        //        RecipeHelper.addRecipe(new ItemStack(MPBlocks.HALF_COBBLESTONE_SLAB_1, 6, 1), new Object[] { "XXX", 'X', new ItemStack(ChalosBlocks.CHALOS_BLOCK, 1, 1) });
        RecipeHelper.addRecipe(new ItemStack(MPBlocks.HALF_COBBLESTONE_SLAB_1, 6, 2), new Object[] { "XXX", 'X', new ItemStack(NibiruBlocks.NIBIRU_BLOCK, 1, 1) });

        //        RecipeHelper.addRecipe(new ItemStack(MPBlocks.DUNGEON_BRICK_WALL, 6, 0), new Object[] { "XXX", "XXX", 'X', new ItemStack(DionaBlocks.DIONA_BLOCK, 1, 11) });
        //        RecipeHelper.addRecipe(new ItemStack(MPBlocks.DUNGEON_BRICK_WALL, 6, 1), new Object[] { "XXX", "XXX", 'X', new ItemStack(ChalosBlocks.CHALOS_BLOCK, 1, 11) });
        RecipeHelper.addRecipe(new ItemStack(MPBlocks.DUNGEON_BRICK_WALL, 6, 2), new Object[] { "XXX", "XXX", 'X', new ItemStack(NibiruBlocks.NIBIRU_BLOCK, 1, 8) });

        //        RecipeHelper.addRecipe(new ItemStack(MPBlocks.COBBLESTONE_WALL, 6, 0), new Object[] { "XXX", "XXX", 'X', new ItemStack(DionaBlocks.DIONA_BLOCK, 1, 3) });
        //        RecipeHelper.addRecipe(new ItemStack(MPBlocks.COBBLESTONE_WALL, 6, 1), new Object[] { "XXX", "XXX", 'X', new ItemStack(ChalosBlocks.CHALOS_BLOCK, 1, 1) });
        RecipeHelper.addRecipe(new ItemStack(MPBlocks.COBBLESTONE_WALL, 6, 2), new Object[] { "XXX", "XXX", 'X', new ItemStack(NibiruBlocks.NIBIRU_BLOCK, 1, 1) });

        RecipeHelper.addRecipe(new ItemStack(MPBlocks.HALF_WOODEN_SLAB_1, 6, 0), new Object[] { "XXX", 'X', new ItemStack(ChalosBlocks.CHEESE_SPORE_PLANKS) });
        RecipeHelper.addRecipe(new ItemStack(MPBlocks.HALF_WOODEN_SLAB_1, 6, 1), new Object[] { "XXX", 'X', new ItemStack(NibiruBlocks.NIBIRU_PLANKS, 1, 0) });
        RecipeHelper.addRecipe(new ItemStack(MPBlocks.HALF_WOODEN_SLAB_1, 6, 2), new Object[] { "XXX", 'X', new ItemStack(NibiruBlocks.NIBIRU_PLANKS, 1, 1) });
        RecipeHelper.addRecipe(new ItemStack(MPBlocks.HALF_WOODEN_SLAB_1, 6, 3), new Object[] { "XXX", 'X', new ItemStack(NibiruBlocks.NIBIRU_PLANKS, 1, 2) });

        for (int i = 0; i < 16; ++i)
        {
            if (i != 15)
            {
                //                RecipeHelper.addRecipe(new ItemStack(MPBlocks.TINTED_GLASS, 8, 15 - i), new Object[] {"III", "IDI", "III", 'I', new ItemStack(MPBlocks.TINTED_GLASS, 1, 0), 'D', new ItemStack(Items.DYE, 1, i)});
            }
            //            RecipeHelper.addRecipe(new ItemStack(MPBlocks.TINTED_GLASS_PANE, 16, i), new Object[] {"GGG", "GGG", 'G', new ItemStack(MPBlocks.TINTED_GLASS, 1, i)});
        }
    }

    private static void addItemRecipe()
    {
        RecipeHelper.addRecipe(new ItemStack(MPItems.SPACE_WARPER_CORE), new Object[] { "PDP", "DED", "PDP", 'P', new ItemStack(MarsItems.marsItemBasic, 1, 5), 'D', Items.DIAMOND, 'E', Items.ENDER_EYE });
        RecipeHelper.addRecipe(new ItemStack(MPItems.SPACE_BOW), new Object[] { " XS", "X S", " XS", 'S', Items.STRING, 'X', new ItemStack(MarsItems.marsItemBasic, 1, 5) });
        RecipeHelper.addRecipe(new ItemStack(MPItems.SPACE_FISHING_ROD), new Object[] {"  S", " SX", "S X", 'S', new ItemStack(MarsItems.marsItemBasic, 1, 5), 'X', Items.STRING});
        RecipeHelper.addRecipe(new ItemStack(MPItems.LASER_BULLET, 8, 0), new Object[] { " R", "I ", 'I', new ItemStack(Items.IRON_INGOT), 'R', Items.REDSTONE });
        RecipeHelper.addRecipe(new ItemStack(MPItems.LASER_BULLET, 8, 1), new Object[] { " R", "I ", 'I', new ItemStack(Items.IRON_INGOT), 'R', new ItemStack(DionaItems.DIONA_ITEM, 1, 4) });
        RecipeHelper.addRecipe(new ItemStack(MPItems.LASER_GUN), new Object[] { "C  ", " DT", "  D", 'C', new ItemStack(AsteroidsItems.basicItem, 1, 8), 'D', new ItemStack(MarsItems.marsItemBasic, 1, 5), 'T', new ItemStack(AsteroidsItems.basicItem, 1, 6) });
        RecipeHelper.addRecipe(new ItemStack(MPItems.ALIEN_DEFENDER_REINFORCEMENT), new Object[] { "C", "I", "I", 'I', new ItemStack(DionaItems.DIONA_ITEM, 1, 6), 'C', new ItemStack(MPItems.SPACE_WARPER_CORE) });
    }

    private static void addOtherRecipe()
    {
        HashMap<Integer, ItemStack> input = new HashMap<>();

        // compressor
        RecipeHelper.addCompressorRecipe(new ItemStack(DionaItems.DIONA_ITEM, 1, 3), "XXX", "XXX", "XXX", 'X', new ItemStack(DionaItems.DIONA_ITEM, 1, 1));
        RecipeHelper.addShapelessCompressorRecipe(new ItemStack(DionaItems.DIONA_ITEM, 1, 2), new ItemStack(DionaItems.DIONA_ITEM, 1, 0), new ItemStack(DionaItems.DIONA_ITEM, 1, 0));
        RecipeHelper.addShapelessCompressorRecipe(new ItemStack(ChalosItems.CHALOS_ITEM, 1, 2), new ItemStack(ChalosItems.CHALOS_ITEM, 1, 0), new ItemStack(ChalosItems.CHALOS_ITEM, 1, 0));
        RecipeHelper.addShapelessCompressorRecipe(new ItemStack(ChalosItems.CHALOS_ITEM, 1, 3), new ItemStack(ChalosItems.CHALOS_ITEM, 1, 1), new ItemStack(ChalosItems.CHALOS_ITEM, 1, 1));
        RecipeHelper.addShapelessCompressorRecipe(new ItemStack(FronosItems.FRONOS_ITEM, 1, 1), new ItemStack(FronosItems.FRONOS_ITEM, 1, 0), new ItemStack(FronosItems.FRONOS_ITEM, 1, 0));

        /*// rocket crusher
        for (int i = 0; i < 4; i++)
        {
            RecipeHelper.addRocketCrusherRecipe(new ItemStack(DionaItems.TIER_4_ROCKET_PART, 21, 0), "DDD", "DRD", "DDD", 'D', new ItemStack(AsteroidsItems.basicItem, 1, 5), 'R', new ItemStack(AsteroidsItems.tier3Rocket, 1, i));
            RecipeHelper.addRocketCrusherRecipe(new ItemStack(ChalosItems.TIER_5_ROCKET_PART, 21, 0), "III", "DRD", "SSS", 'D', new ItemStack(DionaItems.TIER_4_ROCKET_PART, 1, 0), 'I', new ItemStack(DionaItems.DIONA_ITEM, 1, 2), 'S', new ItemStack(DionaItems.DIONA_ITEM, 1, 3), 'R', new ItemStack(DionaItems.TIER_4_ROCKET, 1, i));
            RecipeHelper.addRocketCrusherRecipe(new ItemStack(NibiruItems.NIBIRU_ITEM, 21, 4), "III", "DRD", "SSS", 'D', new ItemStack(ChalosItems.TIER_5_ROCKET_PART, 1, 0), 'I', new ItemStack(ChalosItems.CHALOS_ITEM, 1, 2), 'S', new ItemStack(ChalosItems.CHALOS_ITEM, 1, 3), 'R', new ItemStack(ChalosItems.TIER_5_ROCKET, 1, i));
        }*/

        /*// deconstructor
        TileEntityDeconstructor.addSalvage(new ItemStack(DionaItems.TIER_4_ROCKET_PART, 1, 0)); //tier 3 plate
        TileEntityDeconstructor.addSalvage(new ItemStack(DionaItems.TIER_4_ROCKET_PART, 1, 2));
        TileEntityDeconstructor.addSalvage(new ItemStack(DionaItems.TIER_4_ROCKET_PART, 1, 3));
        TileEntityDeconstructor.addSalvage(new ItemStack(ChalosItems.TIER_5_ROCKET_PART)); //tier 4 plate
        TileEntityDeconstructor.addSalvage(new ItemStack(NibiruItems.NIBIRU_ITEM, 1, 4)); //tier 5 plate
        TileEntityDeconstructor.addSalvage(new ItemStack(NibiruItems.NIBIRU_ITEM, 1, 2));
        TileEntityDeconstructor.addSalvage(new ItemStack(NibiruItems.NIBIRU_ITEM, 1, 3));*/

        // dark energy transform
        input.put(0, new ItemStack(Items.ENDER_PEARL));
        input.put(1, new ItemStack(DionaItems.DIONA_ITEM, 4, 5));
        DarkEnergyRecipeData.registerRecipe(input, new ItemStack(DionaItems.DARK_ENERGY_PEARL), 120);

        input = new HashMap<>();
        input.put(0, new ItemStack(Items.IRON_INGOT));
        input.put(1, new ItemStack(DionaItems.DIONA_ITEM, 2, 5));
        DarkEnergyRecipeData.registerRecipe(input, new ItemStack(DionaItems.DIONA_ITEM, 1, 6), 80);

        // black hole storage
        input = new HashMap<>();
        input.put(1, new ItemStack(DionaItems.DIONA_ITEM, 1, 7));
        input.put(2, new ItemStack(DionaItems.DIONA_ITEM, 1, 7));
        input.put(3, new ItemStack(DionaItems.DIONA_ITEM, 1, 7));
        input.put(4, new ItemStack(DionaItems.DIONA_ITEM, 1, 7));
        input.put(5, new ItemStack(DionaItems.DIONA_ITEM, 1, 7));
        input.put(6, new ItemStack(DionaItems.DIONA_ITEM, 1, 7));
        input.put(7, new ItemStack(DionaItems.DIONA_ITEM, 1, 7));
        input.put(8, new ItemStack(DionaItems.DIONA_ITEM, 1, 7));
        input.put(9, new ItemStack(DionaItems.DIONA_ITEM, 1, 7));
        input.put(10, new ItemStack(GCItems.flagPole));
        input.put(11, new ItemStack(GCItems.flagPole));
        input.put(12, new ItemStack(GCItems.flagPole));
        input.put(13, new ItemStack(DionaItems.DIONA_ITEM, 1, 6));
        input.put(14, new ItemStack(DionaItems.TIER_4_ROCKET_PART, 1, 0));
        input.put(15, new ItemStack(DionaItems.DIONA_ITEM, 1, 6));
        input.put(16, new ItemStack(DionaItems.TIER_4_ROCKET_PART, 1, 0));
        input.put(17, new ItemStack(DionaItems.DIONA_ITEM, 1, 6));
        input.put(18, new ItemStack(DionaItems.DIONA_ITEM, 1, 6));
        input.put(19, new ItemStack(DionaItems.TIER_4_ROCKET_PART, 1, 0));
        input.put(20, new ItemStack(Items.ENDER_EYE));
        input.put(21, new ItemStack(DionaItems.TIER_4_ROCKET_PART, 1, 0));
        input.put(22, new ItemStack(DionaItems.DIONA_ITEM, 1, 6));
        BlackHoleStorageRecipes.recipes.add(new NasaWorkbenchRecipe(new ItemStack(MPBlocks.BLACK_HOLE_STORAGE), input));
    }
}