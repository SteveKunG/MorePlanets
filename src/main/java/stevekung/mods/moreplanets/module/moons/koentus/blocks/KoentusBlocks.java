package stevekung.mods.moreplanets.module.moons.koentus.blocks;

import net.minecraft.block.Block;
import net.minecraft.item.EnumDyeColor;
import stevekung.mods.moreplanets.module.moons.koentus.itemblocks.ItemBlockKoentus;
import stevekung.mods.moreplanets.utils.helper.CommonRegisterHelper;
import stevekung.mods.stevekunglib.utils.BlockUtils;
import stevekung.mods.stevekunglib.utils.EnumHarvestLevel;

public class KoentusBlocks
{
    public static Block KOENTUS_BLOCK;
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

        KoentusBlocks.KOENTUS_BLOCK = new BlockKoentus("koentus_block");
        KoentusBlocks.FALLEN_KOENTUS_METEOR = new BlockFallenKoentusMeteor("fallen_koentus_meteor");
        KoentusBlocks.KOENTUS_ICE = new BlockKoentusIce("koentus_ice");

        /**************************************************************/
        /************************REGISTER STUFF************************/
        /**************************************************************/

        // Glowing Hardened Ice
        CommonRegisterHelper.registerBlock(KoentusBlocks.WHITE_GLOWING_HARDENED_ICE);
        CommonRegisterHelper.registerBlock(KoentusBlocks.ORANGE_GLOWING_HARDENED_ICE);
        CommonRegisterHelper.registerBlock(KoentusBlocks.MAGENTA_GLOWING_HARDENED_ICE);
        CommonRegisterHelper.registerBlock(KoentusBlocks.LIGHT_BLUE_GLOWING_HARDENED_ICE);
        CommonRegisterHelper.registerBlock(KoentusBlocks.YELLOW_GLOWING_HARDENED_ICE);
        CommonRegisterHelper.registerBlock(KoentusBlocks.LIME_GLOWING_HARDENED_ICE);
        CommonRegisterHelper.registerBlock(KoentusBlocks.PINK_GLOWING_HARDENED_ICE);
        CommonRegisterHelper.registerBlock(KoentusBlocks.GRAY_GLOWING_HARDENED_ICE);
        CommonRegisterHelper.registerBlock(KoentusBlocks.SILVER_GLOWING_HARDENED_ICE);
        CommonRegisterHelper.registerBlock(KoentusBlocks.CYAN_GLOWING_HARDENED_ICE);
        CommonRegisterHelper.registerBlock(KoentusBlocks.PURPLE_GLOWING_HARDENED_ICE);
        CommonRegisterHelper.registerBlock(KoentusBlocks.BLUE_GLOWING_HARDENED_ICE);
        CommonRegisterHelper.registerBlock(KoentusBlocks.BROWN_GLOWING_HARDENED_ICE);
        CommonRegisterHelper.registerBlock(KoentusBlocks.GREEN_GLOWING_HARDENED_ICE);
        CommonRegisterHelper.registerBlock(KoentusBlocks.RED_GLOWING_HARDENED_ICE);
        CommonRegisterHelper.registerBlock(KoentusBlocks.BLACK_GLOWING_HARDENED_ICE);

        CommonRegisterHelper.registerBlock(KoentusBlocks.KOENTUS_BLOCK, ItemBlockKoentus::new);
        CommonRegisterHelper.registerBlock(KoentusBlocks.FALLEN_KOENTUS_METEOR);
        CommonRegisterHelper.registerBlock(KoentusBlocks.KOENTUS_ICE);

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

        for (int i = 0; i < BlockKoentus.BlockType.valuesCached().length; i++)
        {
            if (i == 4 || i == 5)
            {
                BlockUtils.setBlockHarvestLevel(KoentusBlocks.KOENTUS_BLOCK, EnumHarvestLevel.PICKAXE, 2, i);
            }
            else
            {
                BlockUtils.setBlockHarvestLevel(KoentusBlocks.KOENTUS_BLOCK, EnumHarvestLevel.PICKAXE, 1, i);
            }
        }
        BlockUtils.setBlockHarvestLevel(KoentusBlocks.KOENTUS_ICE, EnumHarvestLevel.PICKAXE, 1);
        BlockUtils.setBlockHarvestLevel(KoentusBlocks.FALLEN_KOENTUS_METEOR, EnumHarvestLevel.PICKAXE, 2);

        /**************************************************************/
        /************************FIRE BURN STUFF***********************/
        /**************************************************************/


    }
}