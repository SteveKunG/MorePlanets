package stevekung.mods.moreplanets.module.planets.diona.blocks;

import net.minecraft.block.Block;
import net.minecraftforge.fluids.Fluid;
import stevekung.mods.moreplanets.module.planets.diona.itemblocks.ItemBlockDarkEnergyGenerator;
import stevekung.mods.moreplanets.util.blocks.BlockFenceMP;
import stevekung.mods.moreplanets.util.blocks.BlockStairsMP;
import stevekung.mods.moreplanets.util.blocks.BlockStairsMP.EnumStairsType;
import stevekung.mods.moreplanets.util.blocks.EnumSortCategoryBlock;
import stevekung.mods.moreplanets.util.blocks.fluid.FluidMP;
import stevekung.mods.moreplanets.util.helper.CommonRegisterHelper;
import stevekung.mods.moreplanets.util.itemblocks.ItemBlockDescription;
import stevekung.mods.moreplanets.util.itemblocks.ItemBlockMultiVariant;
import stevekung.mods.stevekunglib.utils.EnumHarvestLevel;

public class DionaBlocks
{
    public static Block DIONA_BLOCK;
    public static Block ALBETIUS_WORM_EGG_ROCK;
    public static Block DIONA_ANCIENT_CHEST;
    public static Block DIONA_TREASURE_CHEST;
    public static Block INFECTED_CRYSTALLIZE_PLANKS;
    public static Block INFECTED_CRYSTALLIZE_FENCE;
    public static Block INFECTED_CRYSTALLIZE_WEB;
    public static Block INFECTED_CRYSTALLIZE_TORCH;
    public static Block ZELIUS_EGG;
    public static Block INFECTED_CRYSTALLIZE_PART;
    public static Block LARGE_INFECTED_CRYSTALLIZE;
    public static Block ALIEN_MINER_BLOOD;
    public static Block INFECTED_CRYSTALLIZE_SLIME_BLOCK;
    public static Block DARK_ENERGY_CORE;
    public static Block CRYSTALLIZE_WATER_FLUID_BLOCK;
    public static Block CRYSTALLIZE_LAVA_FLUID_BLOCK;
    public static Block DIONA_COBBLESTONE_STAIRS;
    public static Block DIONA_DUNGEON_BRICK_STAIRS;
    public static Block DARK_ENERGY_GENERATOR;
    public static Block GLOWING_IRON_BLOCK;
    public static Block CRASHED_ALIEN_PROBE;

    public static Fluid CRYSTALLIZE_LAVA_FLUID;
    public static Fluid CRYSTALLIZE_WATER_FLUID;

    public static void init()
    {
        /**************************************************************/
        /*************************INITIAL STUFF************************/
        /**************************************************************/

        DionaBlocks.DIONA_BLOCK = new BlockDiona("diona_block");
        DionaBlocks.ALBETIUS_WORM_EGG_ROCK = new BlockAlbetiusWormEgg("albetius_worm_egg_rock");
        DionaBlocks.DIONA_ANCIENT_CHEST = new BlockDionaAncientChest("diona_ancient_chest");
        DionaBlocks.DIONA_TREASURE_CHEST = new BlockDionaTreasureChest("diona_treasure_chest");
        DionaBlocks.INFECTED_CRYSTALLIZE_PLANKS = new BlockInfectedCrystallizePlanks("infected_crystallize_planks");
        DionaBlocks.INFECTED_CRYSTALLIZE_FENCE = new BlockFenceMP("infected_crystallize_fence");
        DionaBlocks.INFECTED_CRYSTALLIZE_WEB = new BlockInfectedCrystallizeWeb("infected_crystallize_web");
        DionaBlocks.INFECTED_CRYSTALLIZE_TORCH = new BlockCrystallizeTorch("infected_crystallize_torch");
        DionaBlocks.ZELIUS_EGG = new BlockZeliusEgg("zelius_egg");
        DionaBlocks.INFECTED_CRYSTALLIZE_PART = new BlockInfectedCrystallizePart("infected_crystallize_part");
        DionaBlocks.LARGE_INFECTED_CRYSTALLIZE = new BlockLargeInfectedCrystallize("large_infected_crystallize");
        DionaBlocks.ALIEN_MINER_BLOOD = new BlockAlienMinerBlood("alien_miner_blood");
        DionaBlocks.INFECTED_CRYSTALLIZE_SLIME_BLOCK = new BlockInfectedCrystallizeSlime("infected_crystallize_slime_block");
        DionaBlocks.DARK_ENERGY_CORE = new BlockDarkEnergyCore("dark_energy_core");
        DionaBlocks.DIONA_COBBLESTONE_STAIRS = new BlockStairsMP("diona_cobblestone_stairs", EnumStairsType.COBBLESTONE);
        DionaBlocks.DIONA_DUNGEON_BRICK_STAIRS = new BlockStairsMP("diona_dungeon_brick_stairs", EnumStairsType.DUNGEON_BRICK).setSortCategory(EnumSortCategoryBlock.STAIRS_DUNGEON_BRICK);
        DionaBlocks.DARK_ENERGY_GENERATOR = new BlockDarkEnergyGenerator("dark_energy_generator");
        DionaBlocks.GLOWING_IRON_BLOCK = new BlockGlowingIronBlock("glowing_iron_block");
        DionaBlocks.CRASHED_ALIEN_PROBE = new BlockCrashedAlienProbe("crashed_alien_probe");

        DionaBlocks.CRYSTALLIZE_WATER_FLUID = new FluidMP("crystallize_water_fluid").setBlock(DionaBlocks.CRYSTALLIZE_WATER_FLUID_BLOCK);
        DionaBlocks.CRYSTALLIZE_LAVA_FLUID = new FluidMP("crystallize_lava_fluid").setBlock(DionaBlocks.CRYSTALLIZE_LAVA_FLUID_BLOCK).setLuminosity(15).setDensity(3000).setViscosity(6000).setTemperature(1300);
        CommonRegisterHelper.registerFluid(DionaBlocks.CRYSTALLIZE_WATER_FLUID);
        CommonRegisterHelper.registerFluid(DionaBlocks.CRYSTALLIZE_LAVA_FLUID);
        DionaBlocks.CRYSTALLIZE_WATER_FLUID_BLOCK = new BlockFluidCrystallizeWater("crystallize_water_fluid");
        DionaBlocks.CRYSTALLIZE_LAVA_FLUID_BLOCK = new BlockFluidCrystallizeLava("crystallize_lava_fluid");

        /**************************************************************/
        /************************REGISTER STUFF************************/
        /**************************************************************/

        CommonRegisterHelper.registerBlock(DionaBlocks.DIONA_BLOCK, ItemBlockMultiVariant::new);
        CommonRegisterHelper.registerBlock(DionaBlocks.INFECTED_CRYSTALLIZE_PART, ItemBlockMultiVariant::new);
        CommonRegisterHelper.registerBlock(DionaBlocks.ALBETIUS_WORM_EGG_ROCK);
        CommonRegisterHelper.registerBlock(DionaBlocks.INFECTED_CRYSTALLIZE_PLANKS);
        CommonRegisterHelper.registerBlock(DionaBlocks.INFECTED_CRYSTALLIZE_FENCE);
        CommonRegisterHelper.registerBlock(DionaBlocks.DIONA_ANCIENT_CHEST);
        CommonRegisterHelper.registerBlock(DionaBlocks.DIONA_TREASURE_CHEST);
        CommonRegisterHelper.registerBlock(DionaBlocks.ZELIUS_EGG);
        CommonRegisterHelper.registerBlock(DionaBlocks.INFECTED_CRYSTALLIZE_WEB);
        CommonRegisterHelper.registerBlock(DionaBlocks.INFECTED_CRYSTALLIZE_TORCH);
        CommonRegisterHelper.registerBlock(DionaBlocks.LARGE_INFECTED_CRYSTALLIZE);
        CommonRegisterHelper.registerBlock(DionaBlocks.ALIEN_MINER_BLOOD);
        CommonRegisterHelper.registerBlock(DionaBlocks.INFECTED_CRYSTALLIZE_SLIME_BLOCK);
        CommonRegisterHelper.registerBlock(DionaBlocks.DARK_ENERGY_CORE, ItemBlockDescription::new);
        CommonRegisterHelper.registerBlock(DionaBlocks.CRYSTALLIZE_WATER_FLUID_BLOCK);
        CommonRegisterHelper.registerBlock(DionaBlocks.CRYSTALLIZE_LAVA_FLUID_BLOCK);
        CommonRegisterHelper.registerBlock(DionaBlocks.DIONA_COBBLESTONE_STAIRS);
        CommonRegisterHelper.registerBlock(DionaBlocks.DIONA_DUNGEON_BRICK_STAIRS);
        CommonRegisterHelper.registerBlock(DionaBlocks.DARK_ENERGY_GENERATOR, ItemBlockDarkEnergyGenerator::new);
        CommonRegisterHelper.registerBlock(DionaBlocks.GLOWING_IRON_BLOCK);
        CommonRegisterHelper.registerBlock(DionaBlocks.CRASHED_ALIEN_PROBE);

        /**************************************************************/
        /**********************HARVEST LEVEL STUFF*********************/
        /**************************************************************/

        for (int i = 0; i < BlockDiona.BlockType.valuesCached().length; i++)
        {
            if (i == 4 || i == 5)
            {
                CommonRegisterHelper.setBlockHarvestLevel(DionaBlocks.DIONA_BLOCK, EnumHarvestLevel.PICKAXE, 2, i);
            }
            else if (i >= 6 && i <= 10 || i == 11)
            {
                CommonRegisterHelper.setBlockHarvestLevel(DionaBlocks.DIONA_BLOCK, EnumHarvestLevel.PICKAXE, 1, i);
            }
            else
            {
                CommonRegisterHelper.setBlockHarvestLevel(DionaBlocks.DIONA_BLOCK, EnumHarvestLevel.PICKAXE, 0, i);
            }
        }

        CommonRegisterHelper.setBlockHarvestLevel(DionaBlocks.ALBETIUS_WORM_EGG_ROCK, EnumHarvestLevel.PICKAXE, 0);
        CommonRegisterHelper.setBlockHarvestLevel(DionaBlocks.ALIEN_MINER_BLOOD, EnumHarvestLevel.PICKAXE, 0);
        CommonRegisterHelper.setBlockHarvestLevel(DionaBlocks.DIONA_COBBLESTONE_STAIRS, EnumHarvestLevel.PICKAXE, 0);
        CommonRegisterHelper.setBlockHarvestLevel(DionaBlocks.DIONA_DUNGEON_BRICK_STAIRS, EnumHarvestLevel.PICKAXE, 1);
        CommonRegisterHelper.setBlockHarvestLevel(DionaBlocks.CRASHED_ALIEN_PROBE, EnumHarvestLevel.PICKAXE, 2);
        CommonRegisterHelper.setBlockHarvestLevel(DionaBlocks.GLOWING_IRON_BLOCK, EnumHarvestLevel.PICKAXE, 2);
        CommonRegisterHelper.setBlockHarvestLevel(DionaBlocks.DARK_ENERGY_GENERATOR, EnumHarvestLevel.PICKAXE, 2);
        CommonRegisterHelper.setBlockHarvestLevel(DionaBlocks.LARGE_INFECTED_CRYSTALLIZE, EnumHarvestLevel.PICKAXE, 3);
        CommonRegisterHelper.setBlockHarvestLevel(DionaBlocks.INFECTED_CRYSTALLIZE_PLANKS, EnumHarvestLevel.AXE, 0);
        CommonRegisterHelper.setBlockHarvestLevel(DionaBlocks.INFECTED_CRYSTALLIZE_FENCE, EnumHarvestLevel.AXE, 0);
        CommonRegisterHelper.setBlockHarvestLevel(DionaBlocks.DIONA_ANCIENT_CHEST, EnumHarvestLevel.AXE, 0);

        /**************************************************************/
        /************************FIRE BURN STUFF***********************/
        /**************************************************************/

        CommonRegisterHelper.setFireBurn(DionaBlocks.INFECTED_CRYSTALLIZE_PLANKS, 5, 20);
        CommonRegisterHelper.setFireBurn(DionaBlocks.INFECTED_CRYSTALLIZE_FENCE, 5, 20);
    }
}