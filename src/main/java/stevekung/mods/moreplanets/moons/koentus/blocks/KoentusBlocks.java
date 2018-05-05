package stevekung.mods.moreplanets.moons.koentus.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.item.EnumDyeColor;
import stevekung.mods.moreplanets.moons.koentus.itemblocks.ItemBlockAntiGravity;
import stevekung.mods.moreplanets.utils.BlocksItemsRegistry;
import stevekung.mods.moreplanets.utils.blocks.*;
import stevekung.mods.stevekunglib.utils.BlockUtils;
import stevekung.mods.stevekunglib.utils.EnumHarvestLevel;

public class KoentusBlocks
{
    public static Block KOENTUS_REGOLITH;
    public static Block KOENTUS_FINE_REGOLITH;
    public static Block KOENTUS_ROCK;
    public static Block KOENTUS_COBBLESTONE;
    public static Block ANTI_GRAVITY_ORE;
    public static Block GOLDENITE_CRYSTALS_ORE;
    public static Block KOENTUS_TIN_ORE;
    public static Block KOENTUS_COPPER_ORE;
    public static Block KOENTUS_ALUMINUM_ORE;
    public static Block KOENTUS_IRON_ORE;
    public static Block ANTI_GRAVITY_FRAGMENTS_BLOCK;
    public static Block GOLDENITE_CRYSTALS_BLOCK;
    public static Block KOENTUS_DUNGEON_BRICK;

    public static Block FALLEN_KOENTUS_METEOR;
    public static Block KOENTUS_ICE;

    // Glowing Hardened Ice
    public static Block WHITE_GLOWING_HARDENED_ICE;
    public static Block ORANGE_GLOWING_HARDENED_ICE;
    public static Block MAGENTA_GLOWING_HARDENED_ICE;
    public static Block LIGHT_BLUE_GLOWING_HARDENED_ICE;
    public static Block YELLOW_GLOWING_HARDENED_ICE;
    public static Block LIME_GLOWING_HARDENED_ICE;
    public static Block PINK_GLOWING_HARDENED_ICE;
    public static Block GRAY_GLOWING_HARDENED_ICE;
    public static Block SILVER_GLOWING_HARDENED_ICE;
    public static Block CYAN_GLOWING_HARDENED_ICE;
    public static Block PURPLE_GLOWING_HARDENED_ICE;
    public static Block BLUE_GLOWING_HARDENED_ICE;
    public static Block BROWN_GLOWING_HARDENED_ICE;
    public static Block GREEN_GLOWING_HARDENED_ICE;
    public static Block RED_GLOWING_HARDENED_ICE;
    public static Block BLACK_GLOWING_HARDENED_ICE;

    public static void init()
    {
        /**************************************************************/
        /*************************INITIAL STUFF************************/
        /**************************************************************/

        // Glowing Hardened Ice
        KoentusBlocks.WHITE_GLOWING_HARDENED_ICE = new BlockGlowingHardenedIce("white_glowing_hardened_ice", EnumDyeColor.WHITE);
        KoentusBlocks.ORANGE_GLOWING_HARDENED_ICE = new BlockGlowingHardenedIce("orange_glowing_hardened_ice", EnumDyeColor.ORANGE);
        KoentusBlocks.MAGENTA_GLOWING_HARDENED_ICE = new BlockGlowingHardenedIce("magenta_glowing_hardened_ice", EnumDyeColor.MAGENTA);
        KoentusBlocks.LIGHT_BLUE_GLOWING_HARDENED_ICE = new BlockGlowingHardenedIce("light_blue_glowing_hardened_ice", EnumDyeColor.LIGHT_BLUE);
        KoentusBlocks.YELLOW_GLOWING_HARDENED_ICE = new BlockGlowingHardenedIce("yellow_glowing_hardened_ice", EnumDyeColor.YELLOW);
        KoentusBlocks.LIME_GLOWING_HARDENED_ICE = new BlockGlowingHardenedIce("lime_glowing_hardened_ice", EnumDyeColor.LIME);
        KoentusBlocks.PINK_GLOWING_HARDENED_ICE = new BlockGlowingHardenedIce("pink_glowing_hardened_ice", EnumDyeColor.PINK);
        KoentusBlocks.GRAY_GLOWING_HARDENED_ICE = new BlockGlowingHardenedIce("gray_glowing_hardened_ice", EnumDyeColor.GRAY);
        KoentusBlocks.SILVER_GLOWING_HARDENED_ICE = new BlockGlowingHardenedIce("silver_glowing_hardened_ice", EnumDyeColor.SILVER);
        KoentusBlocks.CYAN_GLOWING_HARDENED_ICE = new BlockGlowingHardenedIce("cyan_glowing_hardened_ice", EnumDyeColor.CYAN);
        KoentusBlocks.PURPLE_GLOWING_HARDENED_ICE = new BlockGlowingHardenedIce("purple_glowing_hardened_ice", EnumDyeColor.PURPLE);
        KoentusBlocks.BLUE_GLOWING_HARDENED_ICE = new BlockGlowingHardenedIce("blue_glowing_hardened_ice", EnumDyeColor.BLUE);
        KoentusBlocks.BROWN_GLOWING_HARDENED_ICE = new BlockGlowingHardenedIce("brown_glowing_hardened_ice", EnumDyeColor.BROWN);
        KoentusBlocks.GREEN_GLOWING_HARDENED_ICE = new BlockGlowingHardenedIce("green_glowing_hardened_ice", EnumDyeColor.GREEN);
        KoentusBlocks.RED_GLOWING_HARDENED_ICE = new BlockGlowingHardenedIce("red_glowing_hardened_ice", EnumDyeColor.RED);
        KoentusBlocks.BLACK_GLOWING_HARDENED_ICE = new BlockGlowingHardenedIce("black_glowing_hardened_ice", EnumDyeColor.BLACK);

        KoentusBlocks.KOENTUS_REGOLITH = new BlockTerraformable("koentus_regolith").setHardness(1.25F);
        KoentusBlocks.KOENTUS_FINE_REGOLITH = new BlockTerraformable("koentus_fine_regolith").setHardness(1.25F);
        KoentusBlocks.KOENTUS_ROCK = new BlockCobblestoneDrop("koentus_rock").setHardness(1.5F);
        KoentusBlocks.KOENTUS_COBBLESTONE = new BlockBaseMP("koentus_cobblestone", Material.ROCK).setHardness(2.0F);
        KoentusBlocks.ANTI_GRAVITY_ORE = new BlockAntiGravityOre("anti_gravity_ore");
        KoentusBlocks.GOLDENITE_CRYSTALS_ORE = new BlockDropableOre("goldenite_crystals_ore", BlockDropableOre.BlockType.GOLDENITE_CRYSTALS_ORE).setHardness(3.0F);
        KoentusBlocks.KOENTUS_TIN_ORE = new BlockMineableOre("koentus_tin_ore").setHardness(3.0F);
        KoentusBlocks.KOENTUS_COPPER_ORE = new BlockMineableOre("koentus_copper_ore").setHardness(3.0F);
        KoentusBlocks.KOENTUS_ALUMINUM_ORE = new BlockMineableOre("koentus_aluminum_ore").setHardness(3.0F);
        KoentusBlocks.KOENTUS_IRON_ORE = new BlockMineableOre("koentus_iron_ore").setHardness(3.0F);
        KoentusBlocks.ANTI_GRAVITY_FRAGMENTS_BLOCK = new BlockCompressedMetal("anti_gravity_fragments_block").setSoundType(SoundType.METAL).setHardness(5.0F);
        KoentusBlocks.GOLDENITE_CRYSTALS_BLOCK = new BlockCompressedMetal("goldenite_crystals_block").setSoundType(SoundType.METAL).setHardness(5.0F);
        KoentusBlocks.KOENTUS_DUNGEON_BRICK = new BlockBaseMP("koentus_dungeon_brick", Material.ROCK).setSortCategory(EnumSortCategoryBlock.DUNGEON_BRICK).setHardness(4.0F).setResistance(40.0F);

        KoentusBlocks.FALLEN_KOENTUS_METEOR = new BlockFallenKoentusMeteor("fallen_koentus_meteor");
        KoentusBlocks.KOENTUS_ICE = new BlockKoentusIce("koentus_ice");

        /**************************************************************/
        /************************REGISTER STUFF************************/
        /**************************************************************/

        // Glowing Hardened Ice
        BlocksItemsRegistry.registerBlock(KoentusBlocks.WHITE_GLOWING_HARDENED_ICE);
        BlocksItemsRegistry.registerBlock(KoentusBlocks.ORANGE_GLOWING_HARDENED_ICE);
        BlocksItemsRegistry.registerBlock(KoentusBlocks.MAGENTA_GLOWING_HARDENED_ICE);
        BlocksItemsRegistry.registerBlock(KoentusBlocks.LIGHT_BLUE_GLOWING_HARDENED_ICE);
        BlocksItemsRegistry.registerBlock(KoentusBlocks.YELLOW_GLOWING_HARDENED_ICE);
        BlocksItemsRegistry.registerBlock(KoentusBlocks.LIME_GLOWING_HARDENED_ICE);
        BlocksItemsRegistry.registerBlock(KoentusBlocks.PINK_GLOWING_HARDENED_ICE);
        BlocksItemsRegistry.registerBlock(KoentusBlocks.GRAY_GLOWING_HARDENED_ICE);
        BlocksItemsRegistry.registerBlock(KoentusBlocks.SILVER_GLOWING_HARDENED_ICE);
        BlocksItemsRegistry.registerBlock(KoentusBlocks.CYAN_GLOWING_HARDENED_ICE);
        BlocksItemsRegistry.registerBlock(KoentusBlocks.PURPLE_GLOWING_HARDENED_ICE);
        BlocksItemsRegistry.registerBlock(KoentusBlocks.BLUE_GLOWING_HARDENED_ICE);
        BlocksItemsRegistry.registerBlock(KoentusBlocks.BROWN_GLOWING_HARDENED_ICE);
        BlocksItemsRegistry.registerBlock(KoentusBlocks.GREEN_GLOWING_HARDENED_ICE);
        BlocksItemsRegistry.registerBlock(KoentusBlocks.RED_GLOWING_HARDENED_ICE);
        BlocksItemsRegistry.registerBlock(KoentusBlocks.BLACK_GLOWING_HARDENED_ICE);

        BlocksItemsRegistry.registerBlock(KoentusBlocks.KOENTUS_REGOLITH);
        BlocksItemsRegistry.registerBlock(KoentusBlocks.KOENTUS_FINE_REGOLITH);
        BlocksItemsRegistry.registerBlock(KoentusBlocks.KOENTUS_ROCK);
        BlocksItemsRegistry.registerBlock(KoentusBlocks.KOENTUS_COBBLESTONE);
        BlocksItemsRegistry.registerBlock(KoentusBlocks.ANTI_GRAVITY_ORE, ItemBlockAntiGravity::new);
        BlocksItemsRegistry.registerBlock(KoentusBlocks.GOLDENITE_CRYSTALS_ORE);
        BlocksItemsRegistry.registerBlock(KoentusBlocks.KOENTUS_TIN_ORE);
        BlocksItemsRegistry.registerBlock(KoentusBlocks.KOENTUS_COPPER_ORE);
        BlocksItemsRegistry.registerBlock(KoentusBlocks.KOENTUS_ALUMINUM_ORE);
        BlocksItemsRegistry.registerBlock(KoentusBlocks.KOENTUS_IRON_ORE);
        BlocksItemsRegistry.registerBlock(KoentusBlocks.ANTI_GRAVITY_FRAGMENTS_BLOCK, ItemBlockAntiGravity::new);
        BlocksItemsRegistry.registerBlock(KoentusBlocks.GOLDENITE_CRYSTALS_BLOCK);
        BlocksItemsRegistry.registerBlock(KoentusBlocks.KOENTUS_DUNGEON_BRICK);

        BlocksItemsRegistry.registerBlock(KoentusBlocks.FALLEN_KOENTUS_METEOR);
        BlocksItemsRegistry.registerBlock(KoentusBlocks.KOENTUS_ICE);

        /**************************************************************/
        /**********************HARVEST LEVEL STUFF*********************/
        /**************************************************************/

        // Glowing Hardened Ice
        BlockUtils.setBlockHarvestLevel(KoentusBlocks.WHITE_GLOWING_HARDENED_ICE, EnumHarvestLevel.PICKAXE, 2);
        BlockUtils.setBlockHarvestLevel(KoentusBlocks.ORANGE_GLOWING_HARDENED_ICE, EnumHarvestLevel.PICKAXE, 2);
        BlockUtils.setBlockHarvestLevel(KoentusBlocks.MAGENTA_GLOWING_HARDENED_ICE, EnumHarvestLevel.PICKAXE, 2);
        BlockUtils.setBlockHarvestLevel(KoentusBlocks.LIGHT_BLUE_GLOWING_HARDENED_ICE, EnumHarvestLevel.PICKAXE, 2);
        BlockUtils.setBlockHarvestLevel(KoentusBlocks.YELLOW_GLOWING_HARDENED_ICE, EnumHarvestLevel.PICKAXE, 2);
        BlockUtils.setBlockHarvestLevel(KoentusBlocks.LIME_GLOWING_HARDENED_ICE, EnumHarvestLevel.PICKAXE, 2);
        BlockUtils.setBlockHarvestLevel(KoentusBlocks.PINK_GLOWING_HARDENED_ICE, EnumHarvestLevel.PICKAXE, 2);
        BlockUtils.setBlockHarvestLevel(KoentusBlocks.GRAY_GLOWING_HARDENED_ICE, EnumHarvestLevel.PICKAXE, 2);
        BlockUtils.setBlockHarvestLevel(KoentusBlocks.SILVER_GLOWING_HARDENED_ICE, EnumHarvestLevel.PICKAXE, 2);
        BlockUtils.setBlockHarvestLevel(KoentusBlocks.CYAN_GLOWING_HARDENED_ICE, EnumHarvestLevel.PICKAXE, 2);
        BlockUtils.setBlockHarvestLevel(KoentusBlocks.PURPLE_GLOWING_HARDENED_ICE, EnumHarvestLevel.PICKAXE, 2);
        BlockUtils.setBlockHarvestLevel(KoentusBlocks.BLUE_GLOWING_HARDENED_ICE, EnumHarvestLevel.PICKAXE, 2);
        BlockUtils.setBlockHarvestLevel(KoentusBlocks.BROWN_GLOWING_HARDENED_ICE, EnumHarvestLevel.PICKAXE, 2);
        BlockUtils.setBlockHarvestLevel(KoentusBlocks.GREEN_GLOWING_HARDENED_ICE, EnumHarvestLevel.PICKAXE, 2);
        BlockUtils.setBlockHarvestLevel(KoentusBlocks.RED_GLOWING_HARDENED_ICE, EnumHarvestLevel.PICKAXE, 2);
        BlockUtils.setBlockHarvestLevel(KoentusBlocks.BLACK_GLOWING_HARDENED_ICE, EnumHarvestLevel.PICKAXE, 2);

        BlockUtils.setBlockHarvestLevel(KoentusBlocks.ANTI_GRAVITY_ORE, EnumHarvestLevel.PICKAXE, 2);
        BlockUtils.setBlockHarvestLevel(KoentusBlocks.GOLDENITE_CRYSTALS_ORE, EnumHarvestLevel.PICKAXE, 2);
        BlockUtils.setBlockHarvestLevel(KoentusBlocks.KOENTUS_REGOLITH, EnumHarvestLevel.PICKAXE, 1);
        BlockUtils.setBlockHarvestLevel(KoentusBlocks.KOENTUS_FINE_REGOLITH, EnumHarvestLevel.PICKAXE, 1);
        BlockUtils.setBlockHarvestLevel(KoentusBlocks.KOENTUS_ROCK, EnumHarvestLevel.PICKAXE, 1);
        BlockUtils.setBlockHarvestLevel(KoentusBlocks.KOENTUS_COBBLESTONE, EnumHarvestLevel.PICKAXE, 1);
        BlockUtils.setBlockHarvestLevel(KoentusBlocks.KOENTUS_TIN_ORE, EnumHarvestLevel.PICKAXE, 1);
        BlockUtils.setBlockHarvestLevel(KoentusBlocks.KOENTUS_COPPER_ORE, EnumHarvestLevel.PICKAXE, 1);
        BlockUtils.setBlockHarvestLevel(KoentusBlocks.KOENTUS_ALUMINUM_ORE, EnumHarvestLevel.PICKAXE, 1);
        BlockUtils.setBlockHarvestLevel(KoentusBlocks.KOENTUS_IRON_ORE, EnumHarvestLevel.PICKAXE, 1);
        BlockUtils.setBlockHarvestLevel(KoentusBlocks.ANTI_GRAVITY_FRAGMENTS_BLOCK, EnumHarvestLevel.PICKAXE, 1);
        BlockUtils.setBlockHarvestLevel(KoentusBlocks.GOLDENITE_CRYSTALS_BLOCK, EnumHarvestLevel.PICKAXE, 1);
        BlockUtils.setBlockHarvestLevel(KoentusBlocks.KOENTUS_DUNGEON_BRICK, EnumHarvestLevel.PICKAXE, 1);

        BlockUtils.setBlockHarvestLevel(KoentusBlocks.KOENTUS_ICE, EnumHarvestLevel.PICKAXE, 1);
        BlockUtils.setBlockHarvestLevel(KoentusBlocks.FALLEN_KOENTUS_METEOR, EnumHarvestLevel.PICKAXE, 2);

        /**************************************************************/
        /************************FIRE BURN STUFF***********************/
        /**************************************************************/


    }
}