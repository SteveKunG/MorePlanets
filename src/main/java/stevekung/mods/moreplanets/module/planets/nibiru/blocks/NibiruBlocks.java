package stevekung.mods.moreplanets.module.planets.nibiru.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.BlockFire;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraftforge.fluids.Fluid;
import stevekung.mods.moreplanets.core.MorePlanetsMod;
import stevekung.mods.moreplanets.module.planets.chalos.blocks.BlockGrassMP;
import stevekung.mods.moreplanets.module.planets.nibiru.blocks.fluid.FluidHeliumGas;
import stevekung.mods.moreplanets.module.planets.nibiru.itemblocks.ItemBlockInfectedSnow;
import stevekung.mods.moreplanets.module.planets.nibiru.itemblocks.ItemBlockNuclearWasteTank;
import stevekung.mods.moreplanets.module.planets.nibiru.itemblocks.ItemBlockSporelily;
import stevekung.mods.moreplanets.module.planets.nibiru.items.NibiruItems;
import stevekung.mods.moreplanets.utils.BlocksItemsRegistry;
import stevekung.mods.moreplanets.utils.blocks.*;
import stevekung.mods.moreplanets.utils.blocks.BlockStairsMP.EnumStairsType;
import stevekung.mods.moreplanets.utils.blocks.fluid.FluidMP;
import stevekung.mods.moreplanets.utils.itemblocks.ItemBlockDescription;
import stevekung.mods.moreplanets.utils.itemblocks.ItemBlockFlower;
import stevekung.mods.moreplanets.utils.itemblocks.ItemBlockMultiVariant;
import stevekung.mods.moreplanets.utils.itemblocks.ItemBlockSlabMP;
import stevekung.mods.stevekunglib.utils.BlockUtils;
import stevekung.mods.stevekunglib.utils.EnumHarvestLevel;

public class NibiruBlocks
{
    public static Block INFECTED_GRASS_BLOCK;
    public static Block INFECTED_DIRT;
    public static Block INFECTED_COARSE_DIRT;

    public static BlockBushMP INFECTED_GRASS;
    public static BlockBushMP INFECTED_FERN;
    public static BlockBushMP GREEN_VEIN_GRASS;

    public static BlockDoublePlantMP INFECTED_ORANGE_ROSE_BUSH;
    public static BlockDoublePlantMP INFECTED_TALL_GRASS;
    public static BlockDoublePlantMP INFECTED_LARGE_FERN;
    public static BlockDoublePlantMP GREEN_VEIN_TALL_GRASS;

    public static Block INFECTED_SANDSTONE;
    public static Block INFECTED_CHISELED_SANDSTONE;
    public static Block INFECTED_CUT_SANDSTONE;

    public static BlockCobblestoneDrop NIBIRU_ROCK;
    public static Block NIBIRU_COBBLESTONE;
    public static Block NIBIRU_VEIN_COBBLESTONE;
    public static Block INFECTED_STONE_BRICKS;
    public static Block INFECTED_VEIN_STONE_BRICKS;
    public static Block INFECTED_CRACKED_STONE_BRICKS;
    public static Block INFECTED_CHISELED_STONE_BRICKS;
    public static Block INFERUMITE_BLOCK;
    public static Block NIBIRU_DUNGEON_BRICK;
    public static Block MOSSY_NIBIRU_DUNGEON_BRICK;

    public static Block INFESTED_NIBIRU_ROCK;
    public static Block INFESTED_NIBIRU_COBBLESTONE;
    public static Block INFESTED_NIBIRU_VEIN_COBBLESTONE;
    public static Block INFESTED_INFECTED_STONE_BRICKS;
    public static Block INFESTED_INFECTED_VEIN_STONE_BRICKS;
    public static Block INFESTED_INFECTED_CRACKED_STONE_BRICKS;
    public static Block INFESTED_INFECTED_CHISELED_STONE_BRICKS;

    public static Block INFECTED_FARMLAND;
    public static Block INFECTED_SAND;
    public static BlockBushMP INFECTED_CACTUS;
    public static Block NIBIRU_ORE;
    public static Block INFECTED_WATER_FLUID_BLOCK;
    public static Block NIBIRU_LOG;
    public static Block NIBIRU_PLANKS;
    public static Block NIBIRU_LEAVES;
    public static Block NIBIRU_SAPLING;
    public static Block INFECTED_VINES;
    public static BlockBushMP SPORELILY;
    public static BlockBushMP NIBIRU_FLOWER;
    public static Block NIBIRU_CRAFTING_TABLE;
    public static Block NIBIRU_BOOKSHELF;
    public static Block NIBIRU_FENCE;
    public static Block INFECTED_FENCE_GATE;
    public static Block INFECTED_DEAD_OAK_FENCE_GATE;
    public static BlockDoorMP INFECTED_DOOR_BLOCK;
    public static BlockDoorMP INFECTED_DEAD_OAK_DOOR_BLOCK;
    public static BlockDoorMP ALIEN_BERRY_OAK_DOOR_BLOCK;
    public static Block NIBIRU_COBBLESTONE_STAIRS;
    public static Block NIBIRU_STONE_BRICKS_STAIRS;
    public static Block NIBIRU_ANCIENT_CHEST;
    public static Block NIBIRU_SANDSTONE_STAIRS;
    public static Block INFECTED_ICE;
    public static Block INFECTED_PACKED_ICE;
    public static Block INFECTED_SNOW;
    public static Block INFECTED_SNOW_LAYER;
    public static Block GREEN_VEIN_GRASS_BLOCK;
    public static Block INFECTED_MELON_BLOCK;
    public static BlockBushMP INFECTED_WHEAT_BLOCK;
    public static Block INFECTED_GRAVEL;
    public static Block INFECTED_CLAY;
    public static BlockFire ELECTRICAL_FIRE;
    public static Block NIBIRU_TREASURE_CHEST;
    public static BlockChestMP INFECTED_CHEST;
    public static Block MULTALIC_CRYSTAL;
    public static BlockBushMP INFECTED_SUGAR_CANE_BLOCK;
    public static Block INFECTED_PRISMARINE;
    public static Block INFECTED_SEA_LANTERN;
    public static Block INFECTED_SPONGE;
    public static BlockChestMP ALIEN_BERRY_CHEST;
    public static Block INFECTED_SEAWEED;
    public static Block OIL_ORE;
    public static Block HELIUM_GAS_BLOCK;
    public static Block INFECTED_VINES_DIRT;
    public static Block INFECTED_TORCH;
    public static Block NIBIRU_FURNACE;
    public static Block NIBIRU_LIT_FURNACE;
    public static Block JUICER_EGG;
    public static BlockBushMP INFECTED_MELON_STEM;
    public static Block NIBIRU_DUNGEON_BRICK_STAIRS;
    public static Block INFECTED_OAK_STAIRS;
    public static Block INFECTED_DEAD_OAK_STAIRS;
    public static Block ALIEN_BERRY_OAK_STAIRS;
    public static Block INFECTED_PRISMARINE_STAIRS;
    public static Block INFECTED_PRISMARINE_BRICK_STAIRS;
    public static Block INFECTED_DARK_PRISMARINE_STAIRS;
    public static Block INFECTED_VEIN_STONE_BRICKS_STAIRS;
    public static Block CRACKED_INFECTED_STONE_BRICKS_STAIRS;
    public static Block NIBIRU_SMOOTH_SANDSTONE_STAIRS;
    public static Block NUCLEAR_WASTE_TANK;
    public static Block NUCLEAR_WASTE_FLUID_BLOCK;
    public static Block VEIN_FRAME;
    public static Block VEIN_PORTAL;
    public static Block NUCLEAR_WASTE_GENERATOR;
    public static Block ALIEN_BERRY_OAK_FENCE_GATE;
    public static BlockSlabMP HALF_INFECTED_PRISMARINE_SLAB;
    public static BlockSlabMP DOUBLE_INFECTED_PRISMARINE_SLAB;
    public static BlockSlabMP HALF_INFECTED_STONE_BRICKS_SLAB;
    public static BlockSlabMP DOUBLE_INFECTED_STONE_BRICKS_SLAB;
    public static BlockSlabMP HALF_NIBIRU_SANDSTONE_SLAB;
    public static BlockSlabMP DOUBLE_NIBIRU_SANDSTONE_SLAB;
    public static Block MULTALIC_CRYSTAL_BLOCK;
    public static Block TERRASTONE;
    public static Block PURIFY_WATER_FLUID_BLOCK;
    public static Block PURIFY_GRAVEL;
    public static BlockBushMP TERRABERRY_BLOCK;
    public static Block HUGE_TERRASHROOM_BLOCK;
    public static Block TERRASTONE_STAIRS;
    public static Block TERRASTONE_FURNACE;
    public static Block TERRASTONE_LIT_FURNACE;
    public static Block SEALABLE_NUCLEAR_WASTE_ROD;
    public static Block NIBIRU_GRASS_PATH;

    public static Fluid INFECTED_WATER_FLUID;
    public static Fluid HELIUM_GAS;
    public static Fluid NUCLEAR_WASTE_FLUID;
    public static Fluid PURIFY_WATER_FLUID;

    public static void init()
    {
        /**************************************************************/
        /*************************INITIAL STUFF************************/
        /**************************************************************/

        NibiruBlocks.INFECTED_GRASS_BLOCK = new BlockInfectedGrassBlock("infected_grass_block");
        NibiruBlocks.INFECTED_DIRT = new BlockTerraformable("infected_dirt", Material.GROUND).setSoundType(SoundType.GROUND).setHardness(0.55F);
        NibiruBlocks.INFECTED_COARSE_DIRT = new BlockTerraformable("infected_coarse_dirt", Material.GROUND).setSoundType(SoundType.GROUND).setHardness(0.55F);

        NibiruBlocks.INFECTED_GRASS = new BlockGrassMP("infected_grass", BlockGrassMP.BlockType.INFECTED_GRASS);
        NibiruBlocks.INFECTED_FERN = new BlockGrassMP("infected_fern", BlockGrassMP.BlockType.INFECTED_FERN);
        NibiruBlocks.GREEN_VEIN_GRASS = new BlockGrassMP("green_vein_grass", BlockGrassMP.BlockType.GREEN_VEIN_GRASS);

        NibiruBlocks.INFECTED_ORANGE_ROSE_BUSH = new BlockDoublePlantMP("infected_orange_rose_bush", BlockDoublePlantMP.BlockType.INFECTED_ORANGE_ROSE_BUSH);
        NibiruBlocks.INFECTED_TALL_GRASS = new BlockDoublePlantMP("infected_tall_grass", BlockDoublePlantMP.BlockType.INFECTED_TALL_GRASS);
        NibiruBlocks.INFECTED_LARGE_FERN = new BlockDoublePlantMP("infected_large_fern", BlockDoublePlantMP.BlockType.INFECTED_LARGE_FERN);
        NibiruBlocks.GREEN_VEIN_TALL_GRASS = new BlockDoublePlantMP("green_vein_tall_grass", BlockDoublePlantMP.BlockType.GREEN_VEIN_TALL_GRASS);

        NibiruBlocks.INFECTED_SANDSTONE = new BlockBaseMP("infected_sandstone", Material.ROCK).setHardness(0.8F);
        NibiruBlocks.INFECTED_CHISELED_SANDSTONE = new BlockBaseMP("infected_chiseled_sandstone", Material.ROCK).setHardness(0.8F);
        NibiruBlocks.INFECTED_CUT_SANDSTONE = new BlockBaseMP("infected_cut_sandstone", Material.ROCK).setHardness(0.8F);

        NibiruBlocks.NIBIRU_ROCK = new BlockCobblestoneDrop("nibiru_rock").setHardness(1.5F);
        NibiruBlocks.NIBIRU_COBBLESTONE = new BlockBaseMP("nibiru_cobblestone", Material.ROCK).setHardness(2.0F);
        NibiruBlocks.NIBIRU_VEIN_COBBLESTONE = new BlockBaseMP("nibiru_vein_cobblestone", Material.ROCK).setHardness(2.0F);
        NibiruBlocks.INFECTED_STONE_BRICKS = new BlockBaseMP("infected_stone_bricks", Material.ROCK).setHardness(1.5F);
        NibiruBlocks.INFECTED_VEIN_STONE_BRICKS = new BlockBaseMP("infected_vein_stone_bricks", Material.ROCK).setHardness(1.5F);
        NibiruBlocks.INFECTED_CRACKED_STONE_BRICKS = new BlockBaseMP("infected_cracked_stone_bricks", Material.ROCK).setHardness(1.5F);
        NibiruBlocks.INFECTED_CHISELED_STONE_BRICKS = new BlockBaseMP("infected_chiseled_stone_bricks", Material.ROCK).setHardness(1.5F);
        //        NibiruBlocks.ZYPTORIUM_ORE = new BlockMineableOre("zyptorium_ore").setHardness(3.0F);
        //        NibiruBlocks.CHEESE_MILK_ORE = new BlockDropableOre("cheese_milk_ore", BlockDropableOre.BlockType.CHEESE_MILK_ORE);
        //        NibiruBlocks.CHALOS_IRON_ORE = new BlockMineableOre("chalos_iron_ore").setHardness(3.0F);
        //        NibiruBlocks.CHALOS_TIN_ORE = new BlockMineableOre("chalos_tin_ore").setHardness(3.0F);
        //        NibiruBlocks.CHALOS_COPPER_ORE = new BlockMineableOre("chalos_copper_ore").setHardness(3.0F);
        //        NibiruBlocks.CHALOS_ALUMINUM_ORE = new BlockMineableOre("chalos_aluminum_ore").setHardness(3.0F);
        NibiruBlocks.INFERUMITE_BLOCK = new BlockCompressedMetal("inferumite_block").setSoundType(SoundType.METAL).setHardness(5.0F);
        NibiruBlocks.NIBIRU_DUNGEON_BRICK = new BlockBaseMP("nibiru_dungeon_brick", Material.ROCK).setSortCategory(EnumSortCategoryBlock.DUNGEON_BRICK).setHardness(4.0F).setResistance(40.0F);
        NibiruBlocks.MOSSY_NIBIRU_DUNGEON_BRICK = new BlockBaseMP("mossy_nibiru_dungeon_brick", Material.ROCK).setSortCategory(EnumSortCategoryBlock.DUNGEON_BRICK).setHardness(4.0F).setResistance(40.0F);

        NibiruBlocks.INFESTED_NIBIRU_ROCK = new BlockNibiruInfested("infested_nibiru_rock", BlockNibiruInfested.BlockType.NIBIRU_ROCK);
        NibiruBlocks.INFESTED_NIBIRU_COBBLESTONE = new BlockNibiruInfested("infested_nibiru_cobblestone", BlockNibiruInfested.BlockType.NIBIRU_COBBLESTONE);
        NibiruBlocks.INFESTED_NIBIRU_VEIN_COBBLESTONE = new BlockNibiruInfested("infested_nibiru_vein_cobblestone", BlockNibiruInfested.BlockType.NIBIRU_VEIN_COBBLESTONE);
        NibiruBlocks.INFESTED_INFECTED_STONE_BRICKS = new BlockNibiruInfested("infested_infected_stone_bricks", BlockNibiruInfested.BlockType.INFECTED_STONE_BRICKS);
        NibiruBlocks.INFESTED_INFECTED_VEIN_STONE_BRICKS = new BlockNibiruInfested("infested_infected_vein_stone_bricks", BlockNibiruInfested.BlockType.INFECTED_VEIN_STONE_BRICKS);
        NibiruBlocks.INFESTED_INFECTED_CRACKED_STONE_BRICKS = new BlockNibiruInfested("infested_infected_cracked_stone_bricks", BlockNibiruInfested.BlockType.INFECTED_CRACKED_STONE_BRICKS);
        NibiruBlocks.INFESTED_INFECTED_CHISELED_STONE_BRICKS = new BlockNibiruInfested("infested_infected_chiseled_stone_bricks", BlockNibiruInfested.BlockType.INFECTED_CHISELED_STONE_BRICKS);

        NibiruBlocks.INFECTED_FARMLAND = new BlockInfectedFarmland("infected_farmland");
        NibiruBlocks.INFECTED_SAND = new BlockFallingMP("infected_sand").setSoundType(SoundType.SAND).setHardness(0.5F);
        NibiruBlocks.INFECTED_CACTUS = new BlockInfectedCactus("infected_cactus");
        NibiruBlocks.NIBIRU_ORE = new BlockNibiruOre("nibiru_ore");
        NibiruBlocks.NIBIRU_LOG = new BlockNibiruLog("nibiru_log");
        NibiruBlocks.NIBIRU_PLANKS = new BlockNibiruPlanks("nibiru_planks");
        NibiruBlocks.NIBIRU_LEAVES = new BlockNibiruLeaves("nibiru_leaves");
        NibiruBlocks.NIBIRU_SAPLING = new BlockNibiruSapling("nibiru_sapling");
        NibiruBlocks.INFECTED_VINES = new BlockInfectedVines("infected_vines");
        NibiruBlocks.SPORELILY = new BlockSporelily("sporelily");
        NibiruBlocks.NIBIRU_FLOWER = new BlockNibiruFlower("nibiru_flower");
        NibiruBlocks.NIBIRU_CRAFTING_TABLE = new BlockNibiruCraftingTable("nibiru_crafting_table");
        NibiruBlocks.NIBIRU_BOOKSHELF = new BlockNibiruBookshelf("nibiru_bookshelf");
        NibiruBlocks.NIBIRU_FENCE = new BlockNibiruFence("nibiru_fence");
        NibiruBlocks.INFECTED_FENCE_GATE = new BlockFenceGateMP("infected_fence_gate");
        NibiruBlocks.INFECTED_DEAD_OAK_FENCE_GATE = new BlockFenceGateMP("infected_dead_oak_fence_gate");
        NibiruBlocks.INFECTED_DOOR_BLOCK = new BlockDoorMP("infected_door_block");
        NibiruBlocks.INFECTED_DEAD_OAK_DOOR_BLOCK = new BlockDoorMP("infected_dead_oak_door_block");
        NibiruBlocks.ALIEN_BERRY_OAK_DOOR_BLOCK = new BlockDoorMP("alien_berry_oak_door_block");
        NibiruBlocks.NIBIRU_COBBLESTONE_STAIRS = new BlockStairsMP("nibiru_cobblestone_stairs", EnumStairsType.COBBLESTONE);
        NibiruBlocks.NIBIRU_STONE_BRICKS_STAIRS = new BlockStairsMP("nibiru_stone_bricks_stairs", EnumStairsType.STONE_BRICK);
        NibiruBlocks.NIBIRU_ANCIENT_CHEST = new BlockNibiruAncientChest("nibiru_ancient_chest");
        NibiruBlocks.NIBIRU_SANDSTONE_STAIRS = new BlockStairsMP("nibiru_sandstone_stairs", EnumStairsType.SANDSTONE);
        NibiruBlocks.INFECTED_SNOW = new BlockInfectedSnow("infected_snow");
        NibiruBlocks.INFECTED_ICE = new BlockInfectedIce("infected_ice");
        NibiruBlocks.INFECTED_PACKED_ICE = new BlockInfectedPackedIce("infected_packed_ice");
        NibiruBlocks.INFECTED_SNOW_LAYER = new BlockSnowLayerMP("infected_snow_layer", NibiruItems.INFECTED_SNOWBALL, 0);
        NibiruBlocks.GREEN_VEIN_GRASS_BLOCK = new BlockGreenVeinGrassBlock("green_vein_grass_block");
        NibiruBlocks.INFECTED_MELON_BLOCK = new BlockInfectedMelon("infected_melon_block");
        NibiruBlocks.INFECTED_WHEAT_BLOCK = new BlockInfectedWheatCrops("infected_wheat_block");
        NibiruBlocks.INFECTED_GRAVEL = new BlockInfectedGravel("infected_gravel");
        NibiruBlocks.INFECTED_CLAY = new BlockInfectedClay("infected_clay");
        NibiruBlocks.ELECTRICAL_FIRE = new BlockElectricalFire("electrical_fire");
        NibiruBlocks.NIBIRU_TREASURE_CHEST = new BlockNibiruTreasureChest("nibiru_treasure_chest");
        NibiruBlocks.INFECTED_CHEST = new BlockInfectedChest("infected_chest");
        NibiruBlocks.MULTALIC_CRYSTAL = new BlockMultalicCrystal("multalic_crystal");
        NibiruBlocks.INFECTED_SUGAR_CANE_BLOCK = new BlockInfectedSugarCane("infected_sugar_cane_block");
        NibiruBlocks.INFECTED_PRISMARINE = new BlockInfectedPrismarine("infected_prismarine");
        NibiruBlocks.INFECTED_SEA_LANTERN = new BlockInfectedSeaLantern("infected_sea_lantern");
        NibiruBlocks.INFECTED_SPONGE = new BlockInfectedSponge("infected_sponge");
        NibiruBlocks.ALIEN_BERRY_CHEST = new BlockAlienBerryChest("alien_berry_chest");
        NibiruBlocks.INFECTED_SEAWEED = new BlockInfectedSeaweed("infected_seaweed");
        NibiruBlocks.OIL_ORE = new BlockOilOre("oil_ore");
        NibiruBlocks.INFECTED_VINES_DIRT = new BlockInfectedVinesDirt("infected_vines_dirt");
        NibiruBlocks.INFECTED_TORCH = new BlockInfectedTorch("infected_torch");
        NibiruBlocks.NIBIRU_FURNACE = new BlockNibiruFurnace("nibiru_furnace", false);
        NibiruBlocks.NIBIRU_LIT_FURNACE = new BlockNibiruFurnace("nibiru_lit_furnace", true);
        NibiruBlocks.JUICER_EGG = new BlockJuicerEgg("juicer_egg");
        NibiruBlocks.INFECTED_MELON_STEM = new BlockStemMP("infected_melon_stem", NibiruBlocks.INFECTED_MELON_BLOCK);
        NibiruBlocks.NIBIRU_DUNGEON_BRICK_STAIRS = new BlockStairsMP("nibiru_dungeon_brick_stairs", EnumStairsType.DUNGEON_BRICK).setSortCategory(EnumSortCategoryBlock.STAIRS_DUNGEON_BRICK);
        NibiruBlocks.INFECTED_OAK_STAIRS = new BlockStairsMP("infected_oak_stairs", EnumStairsType.WOODEN).setSortCategory(EnumSortCategoryBlock.STAIRS_WOODEN);
        NibiruBlocks.INFECTED_DEAD_OAK_STAIRS = new BlockStairsMP("infected_dead_oak_stairs", EnumStairsType.WOODEN).setSortCategory(EnumSortCategoryBlock.STAIRS_WOODEN);
        NibiruBlocks.ALIEN_BERRY_OAK_STAIRS = new BlockStairsMP("alien_berry_oak_stairs", EnumStairsType.WOODEN).setSortCategory(EnumSortCategoryBlock.STAIRS_WOODEN);
        NibiruBlocks.INFECTED_PRISMARINE_STAIRS = new BlockStairsMP("infected_prismarine_stairs", EnumStairsType.STONE_BRICK);
        NibiruBlocks.INFECTED_PRISMARINE_BRICK_STAIRS = new BlockStairsMP("infected_prismarine_brick_stairs", EnumStairsType.STONE_BRICK);
        NibiruBlocks.INFECTED_DARK_PRISMARINE_STAIRS = new BlockStairsMP("infected_dark_prismarine_stairs", EnumStairsType.STONE_BRICK);
        NibiruBlocks.INFECTED_VEIN_STONE_BRICKS_STAIRS = new BlockStairsMP("infected_vein_stone_bricks_stairs", EnumStairsType.STONE_BRICK);
        NibiruBlocks.CRACKED_INFECTED_STONE_BRICKS_STAIRS = new BlockStairsMP("cracked_infected_stone_bricks_stairs", EnumStairsType.STONE_BRICK);
        NibiruBlocks.NIBIRU_SMOOTH_SANDSTONE_STAIRS = new BlockStairsMP("nibiru_smooth_sandstone_stairs", EnumStairsType.SANDSTONE);
        NibiruBlocks.NUCLEAR_WASTE_TANK = new BlockNuclearWasteTank("nuclear_waste_tank");
        NibiruBlocks.VEIN_FRAME = new BlockVeinFrame("vein_frame");
        NibiruBlocks.VEIN_PORTAL = new BlockVeinPortal("vein_portal");
        NibiruBlocks.NUCLEAR_WASTE_GENERATOR = new BlockNuclearWasteGenerator("nuclear_waste_generator");
        NibiruBlocks.ALIEN_BERRY_OAK_FENCE_GATE = new BlockFenceGateMP("alien_berry_oak_fence_gate");
        NibiruBlocks.HALF_INFECTED_PRISMARINE_SLAB = new BlockHalfInfectedPrismarineSlab("half_infected_prismarine_slab");
        NibiruBlocks.DOUBLE_INFECTED_PRISMARINE_SLAB = new BlockDoubleInfectedPrismarineSlab("double_infected_prismarine_slab");
        NibiruBlocks.HALF_INFECTED_STONE_BRICKS_SLAB = new BlockHalfInfectedStoneBricksSlab("half_infected_stone_bricks_slab");
        NibiruBlocks.DOUBLE_INFECTED_STONE_BRICKS_SLAB = new BlockDoubleInfectedStoneBricksSlab("double_infected_stone_bricks_slab");
        NibiruBlocks.HALF_NIBIRU_SANDSTONE_SLAB = new BlockHalfNibiruSandstoneSlab("half_nibiru_sandstone_slab");
        NibiruBlocks.DOUBLE_NIBIRU_SANDSTONE_SLAB = new BlockDoubleNibiruSandstoneSlab("double_nibiru_sandstone_slab");
        NibiruBlocks.MULTALIC_CRYSTAL_BLOCK = new BlockMultalicCrystalBlock("multalic_crystal_block");
        NibiruBlocks.TERRASTONE = new BlockBaseMP("terrastone", Material.ROCK).setHardness(1.5F).setResistance(10.0F);
        NibiruBlocks.PURIFY_GRAVEL = new BlockPurifyGravel("purify_gravel");
        NibiruBlocks.TERRABERRY_BLOCK = new BlockTerraberryCrops("terraberry_block");
        NibiruBlocks.HUGE_TERRASHROOM_BLOCK = new BlockHugeTerrashroom("huge_terrashroom_block");
        NibiruBlocks.TERRASTONE_STAIRS = new BlockStairsMP("terrastone_stairs", EnumStairsType.STONE_BRICK);
        NibiruBlocks.TERRASTONE_FURNACE = new BlockTerrastoneFurnace("terrastone_furnace", false);
        NibiruBlocks.TERRASTONE_LIT_FURNACE = new BlockTerrastoneFurnace("terrastone_lit_furnace", true);
        NibiruBlocks.SEALABLE_NUCLEAR_WASTE_ROD = new BlockSealableNuclearWasteRod("sealable_nuclear_waste_rod");
        NibiruBlocks.NIBIRU_GRASS_PATH = new BlockNibiruGrassPath("nibiru_grass_path");

        NibiruBlocks.INFECTED_WATER_FLUID = new FluidMP("infected_water_fluid").setBlock(NibiruBlocks.INFECTED_WATER_FLUID_BLOCK);
        NibiruBlocks.HELIUM_GAS = new FluidHeliumGas("helium_gas", "moreplanets:blocks/helium_gas", "moreplanets:blocks/helium_gas").setBlock(NibiruBlocks.HELIUM_GAS_BLOCK);
        NibiruBlocks.NUCLEAR_WASTE_FLUID = new FluidMP("nuclear_waste_fluid").setBlock(NibiruBlocks.NUCLEAR_WASTE_FLUID_BLOCK).setLuminosity(15).setDensity(3000).setViscosity(8000).setTemperature(2600);
        NibiruBlocks.PURIFY_WATER_FLUID = new FluidMP("purify_water_fluid").setBlock(NibiruBlocks.PURIFY_WATER_FLUID_BLOCK);
        MorePlanetsMod.COMMON_REGISTRY.registerFluid(NibiruBlocks.INFECTED_WATER_FLUID);
        MorePlanetsMod.COMMON_REGISTRY.registerFluid(NibiruBlocks.HELIUM_GAS);
        MorePlanetsMod.COMMON_REGISTRY.registerFluid(NibiruBlocks.NUCLEAR_WASTE_FLUID);
        MorePlanetsMod.COMMON_REGISTRY.registerFluid(NibiruBlocks.PURIFY_WATER_FLUID);
        NibiruBlocks.INFECTED_WATER_FLUID_BLOCK = new BlockFluidInfectedWater("infected_water_fluid");
        NibiruBlocks.HELIUM_GAS_BLOCK = new BlockGasHelium("helium_gas");
        NibiruBlocks.NUCLEAR_WASTE_FLUID_BLOCK = new BlockFluidNuclearWaste("nuclear_waste_fluid");
        NibiruBlocks.PURIFY_WATER_FLUID_BLOCK = new BlockFluidPurifyWater("purify_water_fluid");

        /**************************************************************/
        /************************REGISTER STUFF************************/
        /**************************************************************/

        BlocksItemsRegistry.registerBlock(NibiruBlocks.INFECTED_GRASS_BLOCK);
        BlocksItemsRegistry.registerBlock(NibiruBlocks.INFECTED_DIRT);
        BlocksItemsRegistry.registerBlock(NibiruBlocks.INFECTED_COARSE_DIRT);

        BlocksItemsRegistry.registerBlock(NibiruBlocks.INFECTED_GRASS);
        BlocksItemsRegistry.registerBlock(NibiruBlocks.INFECTED_FERN);
        BlocksItemsRegistry.registerBlock(NibiruBlocks.GREEN_VEIN_GRASS);

        BlocksItemsRegistry.registerBlock(NibiruBlocks.INFECTED_ORANGE_ROSE_BUSH);
        BlocksItemsRegistry.registerBlock(NibiruBlocks.INFECTED_TALL_GRASS);
        BlocksItemsRegistry.registerBlock(NibiruBlocks.INFECTED_LARGE_FERN);
        BlocksItemsRegistry.registerBlock(NibiruBlocks.GREEN_VEIN_TALL_GRASS);

        BlocksItemsRegistry.registerBlock(NibiruBlocks.INFECTED_SANDSTONE);
        BlocksItemsRegistry.registerBlock(NibiruBlocks.INFECTED_CHISELED_SANDSTONE);
        BlocksItemsRegistry.registerBlock(NibiruBlocks.INFECTED_CUT_SANDSTONE);

        BlocksItemsRegistry.registerBlock(NibiruBlocks.NIBIRU_ROCK);
        BlocksItemsRegistry.registerBlock(NibiruBlocks.NIBIRU_COBBLESTONE);
        BlocksItemsRegistry.registerBlock(NibiruBlocks.NIBIRU_VEIN_COBBLESTONE);
        BlocksItemsRegistry.registerBlock(NibiruBlocks.INFECTED_STONE_BRICKS);
        BlocksItemsRegistry.registerBlock(NibiruBlocks.INFECTED_VEIN_STONE_BRICKS);
        BlocksItemsRegistry.registerBlock(NibiruBlocks.INFECTED_CRACKED_STONE_BRICKS);
        BlocksItemsRegistry.registerBlock(NibiruBlocks.INFECTED_CHISELED_STONE_BRICKS);
        BlocksItemsRegistry.registerBlock(NibiruBlocks.INFERUMITE_BLOCK);
        BlocksItemsRegistry.registerBlock(NibiruBlocks.NIBIRU_DUNGEON_BRICK);
        BlocksItemsRegistry.registerBlock(NibiruBlocks.MOSSY_NIBIRU_DUNGEON_BRICK);

        BlocksItemsRegistry.registerBlock(NibiruBlocks.INFESTED_NIBIRU_ROCK);
        BlocksItemsRegistry.registerBlock(NibiruBlocks.INFESTED_NIBIRU_COBBLESTONE);
        BlocksItemsRegistry.registerBlock(NibiruBlocks.INFESTED_NIBIRU_VEIN_COBBLESTONE);
        BlocksItemsRegistry.registerBlock(NibiruBlocks.INFESTED_INFECTED_STONE_BRICKS);
        BlocksItemsRegistry.registerBlock(NibiruBlocks.INFESTED_INFECTED_VEIN_STONE_BRICKS);
        BlocksItemsRegistry.registerBlock(NibiruBlocks.INFESTED_INFECTED_CRACKED_STONE_BRICKS);
        BlocksItemsRegistry.registerBlock(NibiruBlocks.INFESTED_INFECTED_CHISELED_STONE_BRICKS);

        BlocksItemsRegistry.registerBlock(NibiruBlocks.INFECTED_FARMLAND);
        BlocksItemsRegistry.registerBlock(NibiruBlocks.INFECTED_SAND);
        BlocksItemsRegistry.registerBlock(NibiruBlocks.INFECTED_CACTUS, ItemBlockFlower::new);
        BlocksItemsRegistry.registerBlock(NibiruBlocks.NIBIRU_ORE, ItemBlockMultiVariant::new);
        BlocksItemsRegistry.registerBlock(NibiruBlocks.NIBIRU_LOG, ItemBlockMultiVariant::new);
        BlocksItemsRegistry.registerBlock(NibiruBlocks.NIBIRU_PLANKS, ItemBlockMultiVariant::new);
        BlocksItemsRegistry.registerBlock(NibiruBlocks.NIBIRU_LEAVES, ItemBlockMultiVariant::new);
        BlocksItemsRegistry.registerBlock(NibiruBlocks.NIBIRU_SAPLING, ItemBlockMultiVariant::new);
        BlocksItemsRegistry.registerBlock(NibiruBlocks.INFECTED_WATER_FLUID_BLOCK);
        BlocksItemsRegistry.registerBlock(NibiruBlocks.INFECTED_VINES);
        BlocksItemsRegistry.registerBlock(NibiruBlocks.SPORELILY, ItemBlockSporelily::new);
        BlocksItemsRegistry.registerBlock(NibiruBlocks.NIBIRU_FLOWER, ItemBlockMultiVariant::new);
        BlocksItemsRegistry.registerBlock(NibiruBlocks.NIBIRU_CRAFTING_TABLE, ItemBlockMultiVariant::new);
        BlocksItemsRegistry.registerBlock(NibiruBlocks.NIBIRU_BOOKSHELF, ItemBlockMultiVariant::new);
        BlocksItemsRegistry.registerBlock(NibiruBlocks.NIBIRU_FENCE, ItemBlockMultiVariant::new);
        BlocksItemsRegistry.registerBlock(NibiruBlocks.INFECTED_FENCE_GATE);
        BlocksItemsRegistry.registerBlock(NibiruBlocks.INFECTED_DEAD_OAK_FENCE_GATE);
        BlocksItemsRegistry.registerBlock(NibiruBlocks.INFECTED_DOOR_BLOCK, null);
        BlocksItemsRegistry.registerBlock(NibiruBlocks.INFECTED_DEAD_OAK_DOOR_BLOCK, null);
        BlocksItemsRegistry.registerBlock(NibiruBlocks.ALIEN_BERRY_OAK_DOOR_BLOCK, null);
        BlocksItemsRegistry.registerBlock(NibiruBlocks.NIBIRU_COBBLESTONE_STAIRS);
        BlocksItemsRegistry.registerBlock(NibiruBlocks.NIBIRU_STONE_BRICKS_STAIRS);
        BlocksItemsRegistry.registerBlock(NibiruBlocks.NIBIRU_ANCIENT_CHEST);
        BlocksItemsRegistry.registerBlock(NibiruBlocks.NIBIRU_SANDSTONE_STAIRS);
        BlocksItemsRegistry.registerBlock(NibiruBlocks.INFECTED_SNOW);
        BlocksItemsRegistry.registerBlock(NibiruBlocks.INFECTED_ICE);
        BlocksItemsRegistry.registerBlock(NibiruBlocks.INFECTED_PACKED_ICE);
        BlocksItemsRegistry.registerBlock(NibiruBlocks.INFECTED_SNOW_LAYER, ItemBlockInfectedSnow::new);
        BlocksItemsRegistry.registerBlock(NibiruBlocks.GREEN_VEIN_GRASS_BLOCK);
        BlocksItemsRegistry.registerBlock(NibiruBlocks.INFECTED_MELON_BLOCK);
        BlocksItemsRegistry.registerBlock(NibiruBlocks.INFECTED_WHEAT_BLOCK, null);
        BlocksItemsRegistry.registerBlock(NibiruBlocks.INFECTED_GRAVEL);
        BlocksItemsRegistry.registerBlock(NibiruBlocks.INFECTED_CLAY);
        BlocksItemsRegistry.registerBlock(NibiruBlocks.ELECTRICAL_FIRE, null);
        BlocksItemsRegistry.registerBlock(NibiruBlocks.NIBIRU_TREASURE_CHEST);
        BlocksItemsRegistry.registerBlock(NibiruBlocks.INFECTED_CHEST);
        BlocksItemsRegistry.registerBlock(NibiruBlocks.ALIEN_BERRY_CHEST);
        BlocksItemsRegistry.registerBlock(NibiruBlocks.MULTALIC_CRYSTAL);
        BlocksItemsRegistry.registerBlock(NibiruBlocks.INFECTED_SUGAR_CANE_BLOCK, null);
        BlocksItemsRegistry.registerBlock(NibiruBlocks.INFECTED_PRISMARINE, ItemBlockMultiVariant::new);
        BlocksItemsRegistry.registerBlock(NibiruBlocks.INFECTED_SEA_LANTERN);
        BlocksItemsRegistry.registerBlock(NibiruBlocks.INFECTED_SPONGE, ItemBlockMultiVariant::new);
        BlocksItemsRegistry.registerBlock(NibiruBlocks.INFECTED_SEAWEED, ItemBlockMultiVariant::new);
        BlocksItemsRegistry.registerBlock(NibiruBlocks.OIL_ORE);
        BlocksItemsRegistry.registerBlock(NibiruBlocks.HELIUM_GAS_BLOCK);
        BlocksItemsRegistry.registerBlock(NibiruBlocks.INFECTED_VINES_DIRT);
        BlocksItemsRegistry.registerBlock(NibiruBlocks.INFECTED_TORCH);
        BlocksItemsRegistry.registerBlock(NibiruBlocks.NIBIRU_FURNACE);
        BlocksItemsRegistry.registerBlock(NibiruBlocks.NIBIRU_LIT_FURNACE, null);
        BlocksItemsRegistry.registerBlock(NibiruBlocks.JUICER_EGG);
        BlocksItemsRegistry.registerBlock(NibiruBlocks.INFECTED_MELON_STEM, null);
        BlocksItemsRegistry.registerBlock(NibiruBlocks.NIBIRU_DUNGEON_BRICK_STAIRS);
        BlocksItemsRegistry.registerBlock(NibiruBlocks.INFECTED_OAK_STAIRS);
        BlocksItemsRegistry.registerBlock(NibiruBlocks.INFECTED_DEAD_OAK_STAIRS);
        BlocksItemsRegistry.registerBlock(NibiruBlocks.ALIEN_BERRY_OAK_STAIRS);
        BlocksItemsRegistry.registerBlock(NibiruBlocks.INFECTED_PRISMARINE_STAIRS);
        BlocksItemsRegistry.registerBlock(NibiruBlocks.INFECTED_PRISMARINE_BRICK_STAIRS);
        BlocksItemsRegistry.registerBlock(NibiruBlocks.INFECTED_DARK_PRISMARINE_STAIRS);
        BlocksItemsRegistry.registerBlock(NibiruBlocks.INFECTED_VEIN_STONE_BRICKS_STAIRS);
        BlocksItemsRegistry.registerBlock(NibiruBlocks.CRACKED_INFECTED_STONE_BRICKS_STAIRS);
        BlocksItemsRegistry.registerBlock(NibiruBlocks.NIBIRU_SMOOTH_SANDSTONE_STAIRS);
        BlocksItemsRegistry.registerBlock(NibiruBlocks.NUCLEAR_WASTE_TANK, ItemBlockNuclearWasteTank::new);
        BlocksItemsRegistry.registerBlock(NibiruBlocks.NUCLEAR_WASTE_FLUID_BLOCK);
        BlocksItemsRegistry.registerBlock(NibiruBlocks.VEIN_FRAME);
        BlocksItemsRegistry.registerBlock(NibiruBlocks.VEIN_PORTAL, null);
        BlocksItemsRegistry.registerBlock(NibiruBlocks.NUCLEAR_WASTE_GENERATOR, ItemBlockDescription::new);
        BlocksItemsRegistry.registerBlock(NibiruBlocks.ALIEN_BERRY_OAK_FENCE_GATE);
        BlocksItemsRegistry.registerBlock(NibiruBlocks.HALF_INFECTED_PRISMARINE_SLAB, ItemBlockSlabMP::new);
        BlocksItemsRegistry.registerBlock(NibiruBlocks.DOUBLE_INFECTED_PRISMARINE_SLAB, null);
        BlocksItemsRegistry.registerBlock(NibiruBlocks.HALF_INFECTED_STONE_BRICKS_SLAB, ItemBlockSlabMP::new);
        BlocksItemsRegistry.registerBlock(NibiruBlocks.DOUBLE_INFECTED_STONE_BRICKS_SLAB, null);
        BlocksItemsRegistry.registerBlock(NibiruBlocks.HALF_NIBIRU_SANDSTONE_SLAB, ItemBlockSlabMP::new);
        BlocksItemsRegistry.registerBlock(NibiruBlocks.DOUBLE_NIBIRU_SANDSTONE_SLAB, null);
        BlocksItemsRegistry.registerBlock(NibiruBlocks.MULTALIC_CRYSTAL_BLOCK);
        BlocksItemsRegistry.registerBlock(NibiruBlocks.TERRASTONE);
        BlocksItemsRegistry.registerBlock(NibiruBlocks.PURIFY_WATER_FLUID_BLOCK);
        BlocksItemsRegistry.registerBlock(NibiruBlocks.PURIFY_GRAVEL);
        BlocksItemsRegistry.registerBlock(NibiruBlocks.TERRABERRY_BLOCK, null);
        BlocksItemsRegistry.registerBlock(NibiruBlocks.HUGE_TERRASHROOM_BLOCK);
        BlocksItemsRegistry.registerBlock(NibiruBlocks.TERRASTONE_STAIRS);
        BlocksItemsRegistry.registerBlock(NibiruBlocks.TERRASTONE_FURNACE);
        BlocksItemsRegistry.registerBlock(NibiruBlocks.TERRASTONE_LIT_FURNACE, null);
        BlocksItemsRegistry.registerBlock(NibiruBlocks.SEALABLE_NUCLEAR_WASTE_ROD, ItemBlockDescription::new);
        BlocksItemsRegistry.registerBlock(NibiruBlocks.NIBIRU_GRASS_PATH, ItemBlockMultiVariant::new);

        /**************************************************************/
        /**********************HARVEST LEVEL STUFF*********************/
        /**************************************************************/

        BlockUtils.setBlockHarvestLevel(NibiruBlocks.INFECTED_GRASS_BLOCK, EnumHarvestLevel.SHOVEL, 0);
        BlockUtils.setBlockHarvestLevel(NibiruBlocks.INFECTED_DIRT, EnumHarvestLevel.SHOVEL, 0);
        BlockUtils.setBlockHarvestLevel(NibiruBlocks.INFECTED_COARSE_DIRT, EnumHarvestLevel.SHOVEL, 0);
        BlockUtils.setBlockHarvestLevel(NibiruBlocks.INFECTED_FARMLAND, EnumHarvestLevel.SHOVEL, 0);
        BlockUtils.setBlockHarvestLevel(NibiruBlocks.INFECTED_SAND, EnumHarvestLevel.SHOVEL, 0);
        BlockUtils.setBlockHarvestLevel(NibiruBlocks.INFECTED_SNOW, EnumHarvestLevel.SHOVEL, 0);
        BlockUtils.setBlockHarvestLevel(NibiruBlocks.INFECTED_SNOW_LAYER, EnumHarvestLevel.SHOVEL, 0);
        BlockUtils.setBlockHarvestLevel(NibiruBlocks.GREEN_VEIN_GRASS_BLOCK, EnumHarvestLevel.SHOVEL, 0);
        BlockUtils.setBlockHarvestLevel(NibiruBlocks.INFECTED_GRAVEL, EnumHarvestLevel.SHOVEL, 0);
        BlockUtils.setBlockHarvestLevel(NibiruBlocks.INFECTED_CLAY, EnumHarvestLevel.SHOVEL, 0);
        BlockUtils.setBlockHarvestLevel(NibiruBlocks.PURIFY_GRAVEL, EnumHarvestLevel.SHOVEL, 0);
        BlockUtils.setBlockHarvestLevel(NibiruBlocks.NIBIRU_GRASS_PATH, EnumHarvestLevel.SHOVEL, 0);
        BlockUtils.setBlockHarvestLevel(NibiruBlocks.INFECTED_SANDSTONE, EnumHarvestLevel.PICKAXE, 0);
        BlockUtils.setBlockHarvestLevel(NibiruBlocks.INFECTED_CHISELED_SANDSTONE, EnumHarvestLevel.PICKAXE, 0);
        BlockUtils.setBlockHarvestLevel(NibiruBlocks.INFECTED_CUT_SANDSTONE, EnumHarvestLevel.PICKAXE, 0);
        BlockUtils.setBlockHarvestLevel(NibiruBlocks.NIBIRU_COBBLESTONE_STAIRS, EnumHarvestLevel.PICKAXE, 0);
        BlockUtils.setBlockHarvestLevel(NibiruBlocks.NIBIRU_STONE_BRICKS_STAIRS, EnumHarvestLevel.PICKAXE, 0);
        BlockUtils.setBlockHarvestLevel(NibiruBlocks.NIBIRU_SANDSTONE_STAIRS, EnumHarvestLevel.PICKAXE, 0);
        BlockUtils.setBlockHarvestLevel(NibiruBlocks.INFECTED_PACKED_ICE, EnumHarvestLevel.PICKAXE, 0);
        BlockUtils.setBlockHarvestLevel(NibiruBlocks.INFECTED_PRISMARINE, EnumHarvestLevel.PICKAXE, 0);
        BlockUtils.setBlockHarvestLevel(NibiruBlocks.NIBIRU_FURNACE, EnumHarvestLevel.PICKAXE, 0);
        BlockUtils.setBlockHarvestLevel(NibiruBlocks.NIBIRU_LIT_FURNACE, EnumHarvestLevel.PICKAXE, 0);
        BlockUtils.setBlockHarvestLevel(NibiruBlocks.OIL_ORE, EnumHarvestLevel.PICKAXE, 0);
        BlockUtils.setBlockHarvestLevel(NibiruBlocks.INFECTED_PRISMARINE_STAIRS, EnumHarvestLevel.PICKAXE, 0);
        BlockUtils.setBlockHarvestLevel(NibiruBlocks.INFECTED_PRISMARINE_BRICK_STAIRS, EnumHarvestLevel.PICKAXE, 0);
        BlockUtils.setBlockHarvestLevel(NibiruBlocks.INFECTED_DARK_PRISMARINE_STAIRS, EnumHarvestLevel.PICKAXE, 0);
        BlockUtils.setBlockHarvestLevel(NibiruBlocks.INFECTED_VEIN_STONE_BRICKS_STAIRS, EnumHarvestLevel.PICKAXE, 0);
        BlockUtils.setBlockHarvestLevel(NibiruBlocks.CRACKED_INFECTED_STONE_BRICKS_STAIRS, EnumHarvestLevel.PICKAXE, 0);
        BlockUtils.setBlockHarvestLevel(NibiruBlocks.NIBIRU_SMOOTH_SANDSTONE_STAIRS, EnumHarvestLevel.PICKAXE, 0);
        BlockUtils.setBlockHarvestLevel(NibiruBlocks.HALF_INFECTED_PRISMARINE_SLAB, EnumHarvestLevel.PICKAXE, 0);
        BlockUtils.setBlockHarvestLevel(NibiruBlocks.DOUBLE_INFECTED_PRISMARINE_SLAB, EnumHarvestLevel.PICKAXE, 0);
        BlockUtils.setBlockHarvestLevel(NibiruBlocks.HALF_INFECTED_STONE_BRICKS_SLAB, EnumHarvestLevel.PICKAXE, 0);
        BlockUtils.setBlockHarvestLevel(NibiruBlocks.DOUBLE_INFECTED_STONE_BRICKS_SLAB, EnumHarvestLevel.PICKAXE, 0);
        BlockUtils.setBlockHarvestLevel(NibiruBlocks.HALF_NIBIRU_SANDSTONE_SLAB, EnumHarvestLevel.PICKAXE, 0);
        BlockUtils.setBlockHarvestLevel(NibiruBlocks.DOUBLE_NIBIRU_SANDSTONE_SLAB, EnumHarvestLevel.PICKAXE, 0);
        BlockUtils.setBlockHarvestLevel(NibiruBlocks.TERRASTONE, EnumHarvestLevel.PICKAXE, 0);
        BlockUtils.setBlockHarvestLevel(NibiruBlocks.TERRASTONE_STAIRS, EnumHarvestLevel.PICKAXE, 0);
        BlockUtils.setBlockHarvestLevel(NibiruBlocks.TERRASTONE_FURNACE, EnumHarvestLevel.PICKAXE, 0);
        BlockUtils.setBlockHarvestLevel(NibiruBlocks.TERRASTONE_LIT_FURNACE, EnumHarvestLevel.PICKAXE, 0);
        BlockUtils.setBlockHarvestLevel(NibiruBlocks.SEALABLE_NUCLEAR_WASTE_ROD, EnumHarvestLevel.PICKAXE, 0);
        BlockUtils.setBlockHarvestLevel(NibiruBlocks.MULTALIC_CRYSTAL_BLOCK, EnumHarvestLevel.PICKAXE, 1);
        BlockUtils.setBlockHarvestLevel(NibiruBlocks.NIBIRU_DUNGEON_BRICK_STAIRS, EnumHarvestLevel.PICKAXE, 1);
        BlockUtils.setBlockHarvestLevel(NibiruBlocks.NUCLEAR_WASTE_GENERATOR, EnumHarvestLevel.PICKAXE, 2);
        BlockUtils.setBlockHarvestLevel(NibiruBlocks.NUCLEAR_WASTE_TANK, EnumHarvestLevel.PICKAXE, 2);
        BlockUtils.setBlockHarvestLevel(NibiruBlocks.MULTALIC_CRYSTAL, EnumHarvestLevel.PICKAXE, 2);
        BlockUtils.setBlockHarvestLevel(NibiruBlocks.NIBIRU_LOG, EnumHarvestLevel.AXE, 0);
        BlockUtils.setBlockHarvestLevel(NibiruBlocks.NIBIRU_PLANKS, EnumHarvestLevel.AXE, 0);
        BlockUtils.setBlockHarvestLevel(NibiruBlocks.NIBIRU_CRAFTING_TABLE, EnumHarvestLevel.AXE, 0);
        BlockUtils.setBlockHarvestLevel(NibiruBlocks.NIBIRU_BOOKSHELF, EnumHarvestLevel.AXE, 0);
        BlockUtils.setBlockHarvestLevel(NibiruBlocks.INFECTED_FENCE_GATE, EnumHarvestLevel.AXE, 0);
        BlockUtils.setBlockHarvestLevel(NibiruBlocks.INFECTED_DEAD_OAK_FENCE_GATE, EnumHarvestLevel.AXE, 0);
        BlockUtils.setBlockHarvestLevel(NibiruBlocks.NIBIRU_FENCE, EnumHarvestLevel.AXE, 0);
        BlockUtils.setBlockHarvestLevel(NibiruBlocks.INFECTED_DOOR_BLOCK, EnumHarvestLevel.AXE, 0);
        BlockUtils.setBlockHarvestLevel(NibiruBlocks.INFECTED_DEAD_OAK_DOOR_BLOCK, EnumHarvestLevel.AXE, 0);
        BlockUtils.setBlockHarvestLevel(NibiruBlocks.ALIEN_BERRY_OAK_DOOR_BLOCK, EnumHarvestLevel.AXE, 0);
        BlockUtils.setBlockHarvestLevel(NibiruBlocks.NIBIRU_ANCIENT_CHEST, EnumHarvestLevel.AXE, 0);
        BlockUtils.setBlockHarvestLevel(NibiruBlocks.INFECTED_MELON_BLOCK, EnumHarvestLevel.AXE, 0);
        BlockUtils.setBlockHarvestLevel(NibiruBlocks.INFECTED_CHEST, EnumHarvestLevel.AXE, 0);
        BlockUtils.setBlockHarvestLevel(NibiruBlocks.ALIEN_BERRY_CHEST, EnumHarvestLevel.AXE, 0);
        BlockUtils.setBlockHarvestLevel(NibiruBlocks.INFECTED_OAK_STAIRS, EnumHarvestLevel.AXE, 0);
        BlockUtils.setBlockHarvestLevel(NibiruBlocks.INFECTED_DEAD_OAK_STAIRS, EnumHarvestLevel.AXE, 0);
        BlockUtils.setBlockHarvestLevel(NibiruBlocks.ALIEN_BERRY_OAK_STAIRS, EnumHarvestLevel.AXE, 0);
        BlockUtils.setBlockHarvestLevel(NibiruBlocks.ALIEN_BERRY_OAK_FENCE_GATE, EnumHarvestLevel.AXE, 0);
        BlockUtils.setBlockHarvestLevel(NibiruBlocks.HUGE_TERRASHROOM_BLOCK, EnumHarvestLevel.AXE, 0);

        BlockUtils.setBlockHarvestLevel(NibiruBlocks.NIBIRU_ROCK, EnumHarvestLevel.PICKAXE, 0);
        BlockUtils.setBlockHarvestLevel(NibiruBlocks.NIBIRU_COBBLESTONE, EnumHarvestLevel.PICKAXE, 0);
        BlockUtils.setBlockHarvestLevel(NibiruBlocks.NIBIRU_VEIN_COBBLESTONE, EnumHarvestLevel.PICKAXE, 0);
        BlockUtils.setBlockHarvestLevel(NibiruBlocks.INFECTED_STONE_BRICKS, EnumHarvestLevel.PICKAXE, 0);
        BlockUtils.setBlockHarvestLevel(NibiruBlocks.INFECTED_VEIN_STONE_BRICKS, EnumHarvestLevel.PICKAXE, 0);
        BlockUtils.setBlockHarvestLevel(NibiruBlocks.INFECTED_CRACKED_STONE_BRICKS, EnumHarvestLevel.PICKAXE, 0);
        BlockUtils.setBlockHarvestLevel(NibiruBlocks.INFECTED_CHISELED_STONE_BRICKS, EnumHarvestLevel.PICKAXE, 0);
        BlockUtils.setBlockHarvestLevel(NibiruBlocks.INFERUMITE_BLOCK, EnumHarvestLevel.PICKAXE, 0);
        BlockUtils.setBlockHarvestLevel(NibiruBlocks.NIBIRU_DUNGEON_BRICK, EnumHarvestLevel.PICKAXE, 0);
        BlockUtils.setBlockHarvestLevel(NibiruBlocks.MOSSY_NIBIRU_DUNGEON_BRICK, EnumHarvestLevel.PICKAXE, 0);

        for (int i = 0; i < BlockNibiruOre.BlockType.valuesCached().length; i++)
        {
            if (i == 0)
            {
                BlockUtils.setBlockHarvestLevel(NibiruBlocks.NIBIRU_ORE, EnumHarvestLevel.PICKAXE, 0, i);
            }
            else if (i == 1 || i == 5 || i >= 7)
            {
                BlockUtils.setBlockHarvestLevel(NibiruBlocks.NIBIRU_ORE, EnumHarvestLevel.PICKAXE, 1, i);
            }
            else
            {
                BlockUtils.setBlockHarvestLevel(NibiruBlocks.NIBIRU_ORE, EnumHarvestLevel.PICKAXE, 2, i);
            }
        }

        /**************************************************************/
        /************************FIRE BURN STUFF***********************/
        /**************************************************************/

        BlockUtils.setFireBurn(NibiruBlocks.INFECTED_GRASS, 60, 100);
        BlockUtils.setFireBurn(NibiruBlocks.INFECTED_FERN, 60, 100);
        BlockUtils.setFireBurn(NibiruBlocks.GREEN_VEIN_GRASS, 60, 100);
        BlockUtils.setFireBurn(NibiruBlocks.NIBIRU_SAPLING, 60, 100);
        BlockUtils.setFireBurn(NibiruBlocks.NIBIRU_FLOWER, 60, 100);
        BlockUtils.setFireBurn(NibiruBlocks.NIBIRU_LOG, 5, 20);
        BlockUtils.setFireBurn(NibiruBlocks.NIBIRU_PLANKS, 5, 20);
        BlockUtils.setFireBurn(NibiruBlocks.NIBIRU_BOOKSHELF, 5, 20);
        BlockUtils.setFireBurn(NibiruBlocks.NIBIRU_FENCE, 5, 20);
        BlockUtils.setFireBurn(NibiruBlocks.INFECTED_FENCE_GATE, 5, 20);
        BlockUtils.setFireBurn(NibiruBlocks.INFECTED_DEAD_OAK_FENCE_GATE, 5, 20);
        BlockUtils.setFireBurn(NibiruBlocks.NIBIRU_LEAVES, 60, 100);
        BlockUtils.setFireBurn(NibiruBlocks.INFECTED_VINES, 15, 100);
        BlockUtils.setFireBurn(NibiruBlocks.INFECTED_OAK_STAIRS, 5, 20);
        BlockUtils.setFireBurn(NibiruBlocks.INFECTED_DEAD_OAK_STAIRS, 5, 20);
        BlockUtils.setFireBurn(NibiruBlocks.ALIEN_BERRY_OAK_STAIRS, 5, 20);
        BlockUtils.setFireBurn(NibiruBlocks.ALIEN_BERRY_OAK_FENCE_GATE, 5, 20);

        NibiruBlocks.NIBIRU_ROCK.setDrop(NibiruBlocks.NIBIRU_COBBLESTONE);
    }
}