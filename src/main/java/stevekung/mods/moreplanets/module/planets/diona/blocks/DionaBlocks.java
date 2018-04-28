package stevekung.mods.moreplanets.module.planets.diona.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraftforge.fluids.Fluid;
import stevekung.mods.moreplanets.core.MorePlanetsMod;
import stevekung.mods.moreplanets.module.planets.diona.itemblocks.ItemBlockDarkEnergyGenerator;
import stevekung.mods.moreplanets.util.blocks.*;
import stevekung.mods.moreplanets.util.blocks.BlockStairsMP.EnumStairsType;
import stevekung.mods.moreplanets.util.blocks.fluid.FluidMP;
import stevekung.mods.moreplanets.util.helper.CommonRegisterHelper;
import stevekung.mods.moreplanets.util.itemblocks.ItemBlockDescription;
import stevekung.mods.stevekunglib.utils.BlockUtils;
import stevekung.mods.stevekunglib.utils.EnumHarvestLevel;

public class DionaBlocks
{
    // Diona Block
    public static Block DIONA_SURFACE_ROCK;
    public static Block DIONA_SUB_SURFACE_ROCK;
    public static BlockCobblestoneDrop DIONA_ROCK;
    public static Block DIONA_COBBLESTONE;
    public static Block SETRORIUM_ORE;
    public static Block ILLENIUM_ORE;
    public static Block DIONA_TIN_ORE;
    public static Block DIONA_COPPER_ORE;
    public static Block DIONA_ALUMINUM_ORE;
    public static Block SETRORIUM_BLOCK;
    public static Block ILLENIUM_BLOCK;
    public static Block DIONA_DUNGEON_BRICK;

    public static Block INFECTED_CRYSTALLIZED_SEGMENT;
    public static Block INFECTED_CRYSTALLIZED_EYE_CORE;
    public static Block INFECTED_CRYSTALLIZED_ENDER_CORE;

    public static Block ALBETIUS_WORM_EGG_ROCK;
    public static Block DIONA_ANCIENT_CHEST;
    public static Block DIONA_TREASURE_CHEST;
    public static Block INFECTED_CRYSTALLIZED_PLANKS;
    public static Block INFECTED_CRYSTALLIZED_FENCE;
    public static Block INFECTED_CRYSTALLIZED_WEB;
    public static Block INFECTED_CRYSTALLIZED_TORCH;
    public static Block ZELIUS_EGG;
    public static Block LARGE_INFECTED_CRYSTALLIZED;
    public static Block ALIEN_MINER_BLOOD;
    public static Block INFECTED_CRYSTALLIZED_SLIME_BLOCK;
    public static Block DARK_ENERGY_CORE;
    public static Block CRYSTALLIZED_WATER_FLUID_BLOCK;
    public static Block CRYSTALLIZED_LAVA_FLUID_BLOCK;
    public static Block DIONA_COBBLESTONE_STAIRS;
    public static Block DIONA_DUNGEON_BRICK_STAIRS;
    public static Block DARK_ENERGY_GENERATOR;
    public static Block GLOWING_IRON_BLOCK;
    public static Block CRASHED_ALIEN_PROBE;

    public static Fluid CRYSTALLIZED_LAVA_FLUID;
    public static Fluid CRYSTALLIZED_WATER_FLUID;

    public static void init()
    {
        /**************************************************************/
        /*************************INITIAL STUFF************************/
        /**************************************************************/

        // Diona Block
        DionaBlocks.DIONA_SURFACE_ROCK = new BlockTerraformable("diona_surface_rock").setHardness(1.25F);
        DionaBlocks.DIONA_SUB_SURFACE_ROCK = new BlockTerraformable("diona_sub_surface_rock").setHardness(1.25F);
        DionaBlocks.DIONA_ROCK = new BlockCobblestoneDrop("diona_rock").setHardness(1.5F);
        DionaBlocks.DIONA_COBBLESTONE = new BlockBaseMP("diona_cobblestone", Material.ROCK).setHardness(2.0F);
        DionaBlocks.SETRORIUM_ORE = new BlockDropableOre("setrorium_ore", BlockDropableOre.BlockType.SETRORIUM_ORE).setHardness(3.0F);
        DionaBlocks.ILLENIUM_ORE = new BlockMineableOre("illenium_ore").setHardness(3.0F);
        DionaBlocks.DIONA_TIN_ORE = new BlockMineableOre("diona_tin_ore").setHardness(3.0F);
        DionaBlocks.DIONA_COPPER_ORE = new BlockMineableOre("diona_copper_ore").setHardness(3.0F);
        DionaBlocks.DIONA_ALUMINUM_ORE = new BlockMineableOre("diona_aluminum_ore").setHardness(3.0F);
        DionaBlocks.SETRORIUM_BLOCK = new BlockCompressedMetal("setrorium_block").setSoundType(SoundType.METAL).setHardness(5.0F);
        DionaBlocks.ILLENIUM_BLOCK = new BlockCompressedMetal("illenium_block").setSoundType(SoundType.METAL).setHardness(5.0F);
        DionaBlocks.DIONA_DUNGEON_BRICK = new BlockBaseMP("diona_dungeon_brick", Material.ROCK).setSortCategory(EnumSortCategoryBlock.DUNGEON_BRICK).setHardness(4.0F).setResistance(40.0F);

        DionaBlocks.INFECTED_CRYSTALLIZED_SEGMENT = new BlockInfectedCrystallizedSegment("infected_crystallized_segment", BlockInfectedCrystallizedSegment.BlockType.INFECTED_CRYSTALLIZED_SEGMENT);
        DionaBlocks.INFECTED_CRYSTALLIZED_EYE_CORE = new BlockInfectedCrystallizedSegment("infected_crystallized_eye_core", BlockInfectedCrystallizedSegment.BlockType.INFECTED_CRYSTALLIZED_EYE_CORE);
        DionaBlocks.INFECTED_CRYSTALLIZED_ENDER_CORE = new BlockInfectedCrystallizedSegment("infected_crystallized_ender_core", BlockInfectedCrystallizedSegment.BlockType.INFECTED_CRYSTALLIZED_ENDER_CORE);

        DionaBlocks.ALBETIUS_WORM_EGG_ROCK = new BlockAlbetiusWormEgg("albetius_worm_egg_rock");
        DionaBlocks.DIONA_ANCIENT_CHEST = new BlockDionaAncientChest("diona_ancient_chest");
        DionaBlocks.DIONA_TREASURE_CHEST = new BlockDionaTreasureChest("diona_treasure_chest");
        DionaBlocks.INFECTED_CRYSTALLIZED_PLANKS = new BlockInfectedCrystallizedPlanks("infected_crystallized_planks");
        DionaBlocks.INFECTED_CRYSTALLIZED_FENCE = new BlockFenceMP("infected_crystallized_fence");
        DionaBlocks.INFECTED_CRYSTALLIZED_WEB = new BlockInfectedCrystallizedWeb("infected_crystallized_web");
        DionaBlocks.INFECTED_CRYSTALLIZED_TORCH = new BlockCrystallizedTorch("infected_crystallized_torch");
        DionaBlocks.ZELIUS_EGG = new BlockZeliusEgg("zelius_egg");
        DionaBlocks.LARGE_INFECTED_CRYSTALLIZED = new BlockLargeInfectedCrystallized("large_infected_crystallized");
        DionaBlocks.ALIEN_MINER_BLOOD = new BlockAlienMinerBlood("alien_miner_blood");
        DionaBlocks.INFECTED_CRYSTALLIZED_SLIME_BLOCK = new BlockInfectedCrystallizedSlime("infected_crystallized_slime_block");
        DionaBlocks.DARK_ENERGY_CORE = new BlockDarkEnergyCore("dark_energy_core");
        DionaBlocks.DIONA_COBBLESTONE_STAIRS = new BlockStairsMP("diona_cobblestone_stairs", EnumStairsType.COBBLESTONE);
        DionaBlocks.DIONA_DUNGEON_BRICK_STAIRS = new BlockStairsMP("diona_dungeon_brick_stairs", EnumStairsType.DUNGEON_BRICK).setSortCategory(EnumSortCategoryBlock.STAIRS_DUNGEON_BRICK);
        DionaBlocks.DARK_ENERGY_GENERATOR = new BlockDarkEnergyGenerator("dark_energy_generator");
        DionaBlocks.GLOWING_IRON_BLOCK = new BlockGlowingIronBlock("glowing_iron_block");
        DionaBlocks.CRASHED_ALIEN_PROBE = new BlockCrashedAlienProbe("crashed_alien_probe");

        DionaBlocks.CRYSTALLIZED_WATER_FLUID = new FluidMP("crystallized_water_fluid").setBlock(DionaBlocks.CRYSTALLIZED_WATER_FLUID_BLOCK);
        DionaBlocks.CRYSTALLIZED_LAVA_FLUID = new FluidMP("crystallized_lava_fluid").setBlock(DionaBlocks.CRYSTALLIZED_LAVA_FLUID_BLOCK).setLuminosity(15).setDensity(3000).setViscosity(6000).setTemperature(1300);
        MorePlanetsMod.COMMON_REGISTRY.registerFluid(DionaBlocks.CRYSTALLIZED_WATER_FLUID);
        MorePlanetsMod.COMMON_REGISTRY.registerFluid(DionaBlocks.CRYSTALLIZED_LAVA_FLUID);
        DionaBlocks.CRYSTALLIZED_WATER_FLUID_BLOCK = new BlockFluidCrystallizedWater("crystallized_water_fluid");
        DionaBlocks.CRYSTALLIZED_LAVA_FLUID_BLOCK = new BlockFluidCrystallizedLava("crystallized_lava_fluid");

        /**************************************************************/
        /************************REGISTER STUFF************************/
        /**************************************************************/

        // Diona Block
        CommonRegisterHelper.registerBlock(DionaBlocks.DIONA_SURFACE_ROCK);
        CommonRegisterHelper.registerBlock(DionaBlocks.DIONA_SUB_SURFACE_ROCK);
        CommonRegisterHelper.registerBlock(DionaBlocks.DIONA_ROCK);
        CommonRegisterHelper.registerBlock(DionaBlocks.DIONA_COBBLESTONE);
        CommonRegisterHelper.registerBlock(DionaBlocks.SETRORIUM_ORE);
        CommonRegisterHelper.registerBlock(DionaBlocks.ILLENIUM_ORE);
        CommonRegisterHelper.registerBlock(DionaBlocks.DIONA_TIN_ORE);
        CommonRegisterHelper.registerBlock(DionaBlocks.DIONA_COPPER_ORE);
        CommonRegisterHelper.registerBlock(DionaBlocks.DIONA_ALUMINUM_ORE);
        CommonRegisterHelper.registerBlock(DionaBlocks.SETRORIUM_BLOCK);
        CommonRegisterHelper.registerBlock(DionaBlocks.ILLENIUM_BLOCK);
        CommonRegisterHelper.registerBlock(DionaBlocks.DIONA_DUNGEON_BRICK);

        CommonRegisterHelper.registerBlock(DionaBlocks.INFECTED_CRYSTALLIZED_SEGMENT);
        CommonRegisterHelper.registerBlock(DionaBlocks.INFECTED_CRYSTALLIZED_EYE_CORE);
        CommonRegisterHelper.registerBlock(DionaBlocks.INFECTED_CRYSTALLIZED_ENDER_CORE);

        CommonRegisterHelper.registerBlock(DionaBlocks.ALBETIUS_WORM_EGG_ROCK);
        CommonRegisterHelper.registerBlock(DionaBlocks.INFECTED_CRYSTALLIZED_PLANKS);
        CommonRegisterHelper.registerBlock(DionaBlocks.INFECTED_CRYSTALLIZED_FENCE);
        CommonRegisterHelper.registerBlock(DionaBlocks.DIONA_ANCIENT_CHEST);
        CommonRegisterHelper.registerBlock(DionaBlocks.DIONA_TREASURE_CHEST);
        CommonRegisterHelper.registerBlock(DionaBlocks.ZELIUS_EGG);
        CommonRegisterHelper.registerBlock(DionaBlocks.INFECTED_CRYSTALLIZED_WEB);
        CommonRegisterHelper.registerBlock(DionaBlocks.INFECTED_CRYSTALLIZED_TORCH);
        CommonRegisterHelper.registerBlock(DionaBlocks.LARGE_INFECTED_CRYSTALLIZED);
        CommonRegisterHelper.registerBlock(DionaBlocks.ALIEN_MINER_BLOOD);
        CommonRegisterHelper.registerBlock(DionaBlocks.INFECTED_CRYSTALLIZED_SLIME_BLOCK);
        CommonRegisterHelper.registerBlock(DionaBlocks.DARK_ENERGY_CORE, ItemBlockDescription::new);
        CommonRegisterHelper.registerBlock(DionaBlocks.CRYSTALLIZED_WATER_FLUID_BLOCK);
        CommonRegisterHelper.registerBlock(DionaBlocks.CRYSTALLIZED_LAVA_FLUID_BLOCK);
        CommonRegisterHelper.registerBlock(DionaBlocks.DIONA_COBBLESTONE_STAIRS);
        CommonRegisterHelper.registerBlock(DionaBlocks.DIONA_DUNGEON_BRICK_STAIRS);
        CommonRegisterHelper.registerBlock(DionaBlocks.DARK_ENERGY_GENERATOR, ItemBlockDarkEnergyGenerator::new);
        CommonRegisterHelper.registerBlock(DionaBlocks.GLOWING_IRON_BLOCK);
        CommonRegisterHelper.registerBlock(DionaBlocks.CRASHED_ALIEN_PROBE);

        /**************************************************************/
        /**********************HARVEST LEVEL STUFF*********************/
        /**************************************************************/

        BlockUtils.setBlockHarvestLevel(DionaBlocks.DIONA_SURFACE_ROCK, EnumHarvestLevel.PICKAXE, 0);
        BlockUtils.setBlockHarvestLevel(DionaBlocks.DIONA_SUB_SURFACE_ROCK, EnumHarvestLevel.PICKAXE, 0);
        BlockUtils.setBlockHarvestLevel(DionaBlocks.DIONA_ROCK, EnumHarvestLevel.PICKAXE, 0);
        BlockUtils.setBlockHarvestLevel(DionaBlocks.DIONA_COBBLESTONE, EnumHarvestLevel.PICKAXE, 0);
        BlockUtils.setBlockHarvestLevel(DionaBlocks.SETRORIUM_ORE, EnumHarvestLevel.PICKAXE, 2);
        BlockUtils.setBlockHarvestLevel(DionaBlocks.ILLENIUM_ORE, EnumHarvestLevel.PICKAXE, 2);
        BlockUtils.setBlockHarvestLevel(DionaBlocks.DIONA_TIN_ORE, EnumHarvestLevel.PICKAXE, 1);
        BlockUtils.setBlockHarvestLevel(DionaBlocks.DIONA_COPPER_ORE, EnumHarvestLevel.PICKAXE, 1);
        BlockUtils.setBlockHarvestLevel(DionaBlocks.DIONA_ALUMINUM_ORE, EnumHarvestLevel.PICKAXE, 1);
        BlockUtils.setBlockHarvestLevel(DionaBlocks.SETRORIUM_BLOCK, EnumHarvestLevel.PICKAXE, 1);
        BlockUtils.setBlockHarvestLevel(DionaBlocks.ILLENIUM_BLOCK, EnumHarvestLevel.PICKAXE, 1);
        BlockUtils.setBlockHarvestLevel(DionaBlocks.DIONA_DUNGEON_BRICK, EnumHarvestLevel.PICKAXE, 1);

        BlockUtils.setBlockHarvestLevel(DionaBlocks.ALBETIUS_WORM_EGG_ROCK, EnumHarvestLevel.PICKAXE, 0);
        BlockUtils.setBlockHarvestLevel(DionaBlocks.ALIEN_MINER_BLOOD, EnumHarvestLevel.PICKAXE, 0);
        BlockUtils.setBlockHarvestLevel(DionaBlocks.DIONA_COBBLESTONE_STAIRS, EnumHarvestLevel.PICKAXE, 0);
        BlockUtils.setBlockHarvestLevel(DionaBlocks.DIONA_DUNGEON_BRICK_STAIRS, EnumHarvestLevel.PICKAXE, 1);
        BlockUtils.setBlockHarvestLevel(DionaBlocks.CRASHED_ALIEN_PROBE, EnumHarvestLevel.PICKAXE, 2);
        BlockUtils.setBlockHarvestLevel(DionaBlocks.GLOWING_IRON_BLOCK, EnumHarvestLevel.PICKAXE, 2);
        BlockUtils.setBlockHarvestLevel(DionaBlocks.DARK_ENERGY_GENERATOR, EnumHarvestLevel.PICKAXE, 2);
        BlockUtils.setBlockHarvestLevel(DionaBlocks.LARGE_INFECTED_CRYSTALLIZED, EnumHarvestLevel.PICKAXE, 3);
        BlockUtils.setBlockHarvestLevel(DionaBlocks.INFECTED_CRYSTALLIZED_PLANKS, EnumHarvestLevel.AXE, 0);
        BlockUtils.setBlockHarvestLevel(DionaBlocks.INFECTED_CRYSTALLIZED_FENCE, EnumHarvestLevel.AXE, 0);
        BlockUtils.setBlockHarvestLevel(DionaBlocks.DIONA_ANCIENT_CHEST, EnumHarvestLevel.AXE, 0);

        /**************************************************************/
        /************************FIRE BURN STUFF***********************/
        /**************************************************************/

        BlockUtils.setFireBurn(DionaBlocks.INFECTED_CRYSTALLIZED_PLANKS, 5, 20);
        BlockUtils.setFireBurn(DionaBlocks.INFECTED_CRYSTALLIZED_FENCE, 5, 20);

        DionaBlocks.DIONA_ROCK.setDrop(DionaBlocks.DIONA_COBBLESTONE);
    }
}