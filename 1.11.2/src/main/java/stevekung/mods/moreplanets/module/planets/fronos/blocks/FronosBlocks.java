package stevekung.mods.moreplanets.module.planets.fronos.blocks;

import net.minecraft.block.Block;
import stevekung.mods.moreplanets.util.EnumHarvestLevel;
import stevekung.mods.moreplanets.util.helper.CommonRegisterHelper;
import stevekung.mods.moreplanets.util.itemblocks.ItemBlockMultiVariant;

public class FronosBlocks
{
    public static Block FRONOS_GRASS;
    public static Block FRONOS_DIRT;
    public static Block FRONOS_FARMLAND;
    public static Block FRONOS_BLOCK;
    public static Block FRONOS_ORE;
    public static Block CANDY_CANE_1;
    public static Block CANDY_CANE_2;
    public static Block JELLY_BLOCK;

    public static void init()
    {
        /**************************************************************/
        /*************************INITIAL STUFF************************/
        /**************************************************************/

        FronosBlocks.FRONOS_GRASS = new BlockFronosGrass("fronos_grass");
        FronosBlocks.FRONOS_DIRT = new BlockFronosDirt("fronos_dirt");
        FronosBlocks.FRONOS_FARMLAND = new BlockFronosFarmland("fronos_farmland");
        FronosBlocks.FRONOS_BLOCK = new BlockFronos("fronos_block");
        FronosBlocks.FRONOS_ORE = new BlockFronosOre("fronos_ore");
        FronosBlocks.CANDY_CANE_1 = new BlockCandyCane1("candy_cane_1");
        FronosBlocks.CANDY_CANE_2 = new BlockCandyCane2("candy_cane_2");
        FronosBlocks.JELLY_BLOCK = new BlockJelly("jelly_block");

        /**************************************************************/
        /************************REGISTER STUFF************************/
        /**************************************************************/

        CommonRegisterHelper.registerBlock(FronosBlocks.FRONOS_GRASS);
        CommonRegisterHelper.registerBlock(FronosBlocks.FRONOS_DIRT, ItemBlockMultiVariant::new);
        CommonRegisterHelper.registerBlock(FronosBlocks.FRONOS_FARMLAND);
        CommonRegisterHelper.registerBlock(FronosBlocks.FRONOS_BLOCK, ItemBlockMultiVariant::new);
        CommonRegisterHelper.registerBlock(FronosBlocks.FRONOS_ORE, ItemBlockMultiVariant::new);
        CommonRegisterHelper.registerBlock(FronosBlocks.CANDY_CANE_1, ItemBlockMultiVariant::new);
        CommonRegisterHelper.registerBlock(FronosBlocks.CANDY_CANE_2, ItemBlockMultiVariant::new);
        CommonRegisterHelper.registerBlock(FronosBlocks.JELLY_BLOCK, ItemBlockMultiVariant::new);

        /**************************************************************/
        /**********************HARVEST LEVEL STUFF*********************/
        /**************************************************************/

        for (int i = 0; i < BlockFronosOre.BlockType.valuesCached().length; i++)
        {
            if (i == 6 || i == 12)
            {
                CommonRegisterHelper.setBlockHarvestLevel(FronosBlocks.FRONOS_ORE, EnumHarvestLevel.PICKAXE, 0, i);
            }
            else if (i == 0 || i >= 2 && i <= 5 || i == 7)
            {
                CommonRegisterHelper.setBlockHarvestLevel(FronosBlocks.FRONOS_ORE, EnumHarvestLevel.PICKAXE, 1, i);
            }
            else
            {
                CommonRegisterHelper.setBlockHarvestLevel(FronosBlocks.FRONOS_ORE, EnumHarvestLevel.PICKAXE, 2, i);
            }
        }

        for (int i = 0; i < BlockFronos.BlockType.valuesCached().length; i++)
        {
            if (i == 6)
            {
                CommonRegisterHelper.setBlockHarvestLevel(FronosBlocks.FRONOS_BLOCK, EnumHarvestLevel.PICKAXE, 1, i);
            }
            else
            {
                CommonRegisterHelper.setBlockHarvestLevel(FronosBlocks.FRONOS_BLOCK, EnumHarvestLevel.PICKAXE, 0, i);
            }
        }

        CommonRegisterHelper.setBlockHarvestLevel(FronosBlocks.FRONOS_GRASS, EnumHarvestLevel.SHOVEL, 0);
        CommonRegisterHelper.setBlockHarvestLevel(FronosBlocks.FRONOS_DIRT, EnumHarvestLevel.SHOVEL, 0);
        CommonRegisterHelper.setBlockHarvestLevel(FronosBlocks.FRONOS_FARMLAND, EnumHarvestLevel.SHOVEL, 0);

        /**************************************************************/
        /************************FIRE BURN STUFF***********************/
        /**************************************************************/

        CommonRegisterHelper.setFireBurn(FronosBlocks.CANDY_CANE_1, 60, 100);
        CommonRegisterHelper.setFireBurn(FronosBlocks.CANDY_CANE_2, 60, 100);

        /**************************************************************/
        /********************ORE DICTIONARY STUFF**********************/
        /**************************************************************/

        CommonRegisterHelper.registerOreDictionary("blockCandy", FronosBlocks.CANDY_CANE_1);
        CommonRegisterHelper.registerOreDictionary("blockCandy", FronosBlocks.CANDY_CANE_2);
    }
}