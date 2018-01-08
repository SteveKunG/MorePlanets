package stevekung.mods.moreplanets.module.moons.koentus.blocks;

import net.minecraft.block.Block;
import stevekung.mods.moreplanets.module.moons.koentus.itemblocks.ItemBlockKoentus;
import stevekung.mods.moreplanets.util.EnumHarvestLevel;
import stevekung.mods.moreplanets.util.helper.CommonRegisterHelper;
import stevekung.mods.moreplanets.util.itemblocks.ItemBlockColoredMP;

public class KoentusBlocks
{
    public static Block KOENTUS_BLOCK;
    public static Block FALLEN_KOENTUS_METEOR;
    public static Block GLOWING_HARDENED_ICE;
    public static Block KOENTUS_ICE;

    public static void init()
    {
        /**************************************************************/
        /*************************INITIAL STUFF************************/
        /**************************************************************/

        KoentusBlocks.KOENTUS_BLOCK = new BlockKoentus("koentus_block");
        KoentusBlocks.FALLEN_KOENTUS_METEOR = new BlockFallenKoentusMeteor("fallen_koentus_meteor");
        KoentusBlocks.GLOWING_HARDENED_ICE = new BlockGlowingHardenedIce("glowing_hardened_ice");
        KoentusBlocks.KOENTUS_ICE = new BlockKoentusIce("koentus_ice");

        /**************************************************************/
        /************************REGISTER STUFF************************/
        /**************************************************************/

        CommonRegisterHelper.registerBlock(KoentusBlocks.KOENTUS_BLOCK, ItemBlockKoentus::new);
        CommonRegisterHelper.registerBlock(KoentusBlocks.FALLEN_KOENTUS_METEOR);
        CommonRegisterHelper.registerBlock(KoentusBlocks.GLOWING_HARDENED_ICE, ItemBlockColoredMP::new);
        CommonRegisterHelper.registerBlock(KoentusBlocks.KOENTUS_ICE);

        /**************************************************************/
        /**********************HARVEST LEVEL STUFF*********************/
        /**************************************************************/

        for (int i = 0; i < BlockKoentus.BlockType.valuesCached().length; i++)
        {
            if (i == 4 || i == 5)
            {
                CommonRegisterHelper.setBlockHarvestLevel(KoentusBlocks.KOENTUS_BLOCK, EnumHarvestLevel.PICKAXE, 2, i);
            }
            else
            {
                CommonRegisterHelper.setBlockHarvestLevel(KoentusBlocks.KOENTUS_BLOCK, EnumHarvestLevel.PICKAXE, 1, i);
            }
        }
        CommonRegisterHelper.setBlockHarvestLevel(KoentusBlocks.KOENTUS_ICE, EnumHarvestLevel.PICKAXE, 1);
        CommonRegisterHelper.setBlockHarvestLevel(KoentusBlocks.FALLEN_KOENTUS_METEOR, EnumHarvestLevel.PICKAXE, 2);
        CommonRegisterHelper.setBlockHarvestLevel(KoentusBlocks.GLOWING_HARDENED_ICE, EnumHarvestLevel.PICKAXE, 2);

        /**************************************************************/
        /************************FIRE BURN STUFF***********************/
        /**************************************************************/


    }
}