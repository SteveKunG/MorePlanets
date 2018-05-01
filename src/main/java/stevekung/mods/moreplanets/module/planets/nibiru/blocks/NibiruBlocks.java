package stevekung.mods.moreplanets.module.planets.nibiru.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.BlockFire;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraftforge.fluids.Fluid;
import stevekung.mods.moreplanets.blocks.decoration.BlockAllDoubleSlab;
import stevekung.mods.moreplanets.blocks.decoration.BlockAllHalfSlab;
import stevekung.mods.moreplanets.core.MorePlanetsMod;
import stevekung.mods.moreplanets.module.planets.chalos.blocks.BlockPlaceableBushMP;
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
import stevekung.mods.moreplanets.utils.itemblocks.ItemBlockSingleLeaves;
import stevekung.mods.moreplanets.utils.itemblocks.ItemBlockSlabMP;
import stevekung.mods.stevekunglib.utils.BlockUtils;
import stevekung.mods.stevekunglib.utils.EnumHarvestLevel;

public class NibiruBlocks
{
    public static Block INFECTED_GRASS_BLOCK;
    public static Block INFECTED_DIRT;
    public static Block INFECTED_COARSE_DIRT;

    public static Block INFECTED_GRASS;
    public static Block INFECTED_FERN;
    public static Block GREEN_VEIN_GRASS;

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

    public static Block INFECTED_COAL_ORE;
    public static Block INFECTED_IRON_ORE;
    public static Block INFECTED_GOLD_ORE;
    public static Block INFECTED_DIAMOND_ORE;
    public static Block INFECTED_REDSTONE_ORE;
    public static Block INFECTED_LAPIS_ORE;
    public static Block INFECTED_EMERALD_ORE;
    public static Block INFECTED_ALUMINUM_ORE;
    public static Block INFECTED_COPPER_ORE;
    public static Block INFECTED_TIN_ORE;
    public static Block INFECTED_SILICON_ORE;
    public static Block INFERUMITE_CRYSTAL_ORE;

    public static Block INFECTED_OAK_LOG;
    public static Block INFECTED_JUNGLE_LOG;
    public static Block ALIEN_BERRY_OAK_LOG;

    public static Block INFECTED_OAK_PLANKS;
    public static Block ALIEN_BERRY_OAK_PLANKS;

    public static Block INFECTED_OAK_LEAVES;
    public static Block INFECTED_JUNGLE_LEAVES;
    public static Block ALIEN_BERRY_OAK_LEAVES;

    public static Block INFECTED_OAK_SAPLING;
    public static Block INFECTED_JUNGLE_SAPLING;
    public static Block ALIEN_BERRY_OAK_SAPLING;

    public static Block PURE_HERB;
    public static Block TERRAPUFF_HERB;
    public static Block BATASIA_DANDELION;
    public static Block PYOLONIA;
    public static Block PHILIPY;
    public static Block WHITE_TAIL;
    public static Block VEALIUM_VINES;
    public static Block TERRASHROOM;

    public static Block INFECTED_CRAFTING_TABLE;
    public static Block ALIEN_BERRY_CRAFTING_TABLE;

    public static Block INFECTED_OAK_BOOKSHELF;
    public static Block ALIEN_BERRY_OAK_BOOKSHELF;

    public static Block INFECTED_OAK_FENCE;
    public static Block ALIEN_BERRY_OAK_FENCE;

    public static Block INFECTED_GRASS_PATH;
    public static Block GREEN_VEIN_GRASS_PATH;

    public static Block INFECTED_FARMLAND;
    public static Block INFECTED_SAND;
    public static Block INFECTED_CACTUS;
    public static Block INFECTED_WATER_FLUID_BLOCK;
    public static Block INFECTED_VINES;
    public static Block SPORELILY;
    public static Block INFECTED_OAK_FENCE_GATE;
    public static BlockDoorMP INFECTED_OAK_DOOR_BLOCK;
    public static BlockDoorMP ALIEN_BERRY_OAK_DOOR_BLOCK;
    public static Block NIBIRU_COBBLESTONE_STAIRS;
    public static Block NIBIRU_STONE_BRICKS_STAIRS;
    public static Block NIBIRU_ANCIENT_CHEST;
    public static Block INFECTED_SANDSTONE_STAIRS;
    public static Block INFECTED_ICE;
    public static Block INFECTED_PACKED_ICE;
    public static Block INFECTED_SNOW;
    public static Block INFECTED_SNOW_LAYER;
    public static Block GREEN_VEIN_GRASS_BLOCK;
    public static Block INFECTED_MELON_BLOCK;
    public static Block INFECTED_WHEAT_BLOCK;
    public static Block INFECTED_GRAVEL;
    public static Block INFECTED_CLAY;
    public static BlockFire ELECTRICAL_FIRE;
    public static Block NIBIRU_TREASURE_CHEST;
    public static BlockChestMP INFECTED_CHEST;
    public static Block MULTALIC_CRYSTAL;
    public static Block INFECTED_SUGAR_CANE_BLOCK;
    public static Block INFECTED_PRISMARINE;
    public static Block INFECTED_PRISMARINE_BRICKS;
    public static Block INFECTED_DARK_PRISMARINE;
    public static Block INFECTED_SEA_LANTERN;
    public static Block INFECTED_SPONGE;
    public static Block INFECTED_WET_SPONGE;
    public static BlockChestMP ALIEN_BERRY_CHEST;
    public static Block INFECTED_SEAWEED;
    public static Block OIL_ORE;
    public static Block HELIUM_GAS_BLOCK;
    public static Block INFECTED_VINES_DIRT;
    public static Block INFECTED_TORCH;
    public static Block INFECTED_FURNACE;
    public static Block TERRASTONE_FURNACE;
    public static Block JUICER_EGG;
    public static Block INFECTED_MELON_STEM;
    public static Block NIBIRU_DUNGEON_BRICK_STAIRS;
    public static Block INFECTED_OAK_STAIRS;
    public static Block ALIEN_BERRY_OAK_STAIRS;
    public static Block INFECTED_PRISMARINE_STAIRS;
    public static Block INFECTED_PRISMARINE_BRICKS_STAIRS;
    public static Block INFECTED_DARK_PRISMARINE_STAIRS;
    public static Block INFECTED_VEIN_STONE_BRICKS_STAIRS;
    public static Block CRACKED_INFECTED_STONE_BRICKS_STAIRS;
    public static Block INFECTED_CUT_SANDSTONE_STAIRS;
    public static Block NUCLEAR_WASTE_TANK;
    public static Block NUCLEAR_WASTE_FLUID_BLOCK;
    public static Block VEIN_FRAME;
    public static Block VEIN_PORTAL;
    public static Block NUCLEAR_WASTE_GENERATOR;
    public static Block ALIEN_BERRY_OAK_FENCE_GATE;
    public static Block MULTALIC_CRYSTAL_BLOCK;
    public static Block TERRASTONE;
    public static Block PURIFIED_WATER_FLUID_BLOCK;
    public static Block PURIFIED_GRAVEL;
    public static Block TERRABERRY_BLOCK;
    public static Block HUGE_TERRASHROOM_BLOCK;
    public static Block TERRASTONE_STAIRS;
    public static Block SEALABLE_NUCLEAR_WASTE_ROD;

    public static BlockAllHalfSlab INFECTED_PRISMARINE_SLAB;
    public static BlockAllHalfSlab INFECTED_PRISMARINE_BRICKS_SLAB;
    public static BlockAllHalfSlab INFECTED_DARK_PRISMARINE_SLAB;
    public static BlockAllHalfSlab INFECTED_STONE_BRICKS_SLAB;
    public static BlockAllHalfSlab INFECTED_VEIN_STONE_BRICKS_SLAB;
    public static BlockAllHalfSlab INFECTED_CRACKED_STONE_BRICKS_SLAB;
    public static BlockAllHalfSlab TERRASTONE_SLAB;
    public static BlockAllHalfSlab INFECTED_SANDSTONE_SLAB;
    public static BlockAllHalfSlab INFECTED_CUT_SANDSTONE_SLAB;

    @Deprecated public static BlockAllHalfSlab DOUBLE_INFECTED_PRISMARINE_SLAB;
    @Deprecated public static BlockAllHalfSlab DOUBLE_INFECTED_PRISMARINE_BRICKS_SLAB;
    @Deprecated public static BlockAllHalfSlab DOUBLE_INFECTED_DARK_PRISMARINE_SLAB;
    @Deprecated public static BlockAllHalfSlab DOUBLE_INFECTED_STONE_BRICKS_SLAB;
    @Deprecated public static BlockAllHalfSlab DOUBLE_INFECTED_VEIN_STONE_BRICKS_SLAB;
    @Deprecated public static BlockAllHalfSlab DOUBLE_INFECTED_CRACKED_STONE_BRICKS_SLAB;
    @Deprecated public static BlockAllHalfSlab DOUBLE_TERRASTONE_SLAB;
    @Deprecated public static BlockAllHalfSlab DOUBLE_INFECTED_SANDSTONE_SLAB;
    @Deprecated public static BlockAllHalfSlab DOUBLE_INFECTED_CUT_SANDSTONE_SLAB;

    public static Fluid INFECTED_WATER_FLUID;
    public static Fluid HELIUM_GAS;
    public static Fluid NUCLEAR_WASTE_FLUID;
    public static Fluid PURIFIED_WATER_FLUID;

    public static void init()
    {
        /**************************************************************/
        /************************* INITIAL STUFF ************************/
        /**************************************************************/

        NibiruBlocks.INFECTED_GRASS_BLOCK = new BlockInfectedGrassBlock("infected_grass_block");
        NibiruBlocks.INFECTED_DIRT = new BlockTerraformable("infected_dirt", Material.GROUND).setSoundType(SoundType.GROUND).setHardness(0.55F);
        NibiruBlocks.INFECTED_COARSE_DIRT = new BlockTerraformable("infected_coarse_dirt", Material.GROUND).setSoundType(SoundType.GROUND).setHardness(0.55F);

        NibiruBlocks.INFECTED_GRASS = new BlockPlaceableBushMP("infected_grass", BlockPlaceableBushMP.BlockType.INFECTED_GRASS);
        NibiruBlocks.INFECTED_FERN = new BlockPlaceableBushMP("infected_fern", BlockPlaceableBushMP.BlockType.INFECTED_FERN);
        NibiruBlocks.GREEN_VEIN_GRASS = new BlockPlaceableBushMP("green_vein_grass", BlockPlaceableBushMP.BlockType.GREEN_VEIN_GRASS);

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

        NibiruBlocks.INFECTED_IRON_ORE = new BlockMineableOre("infected_iron_ore").setHardness(3.0F);
        NibiruBlocks.INFECTED_GOLD_ORE = new BlockMineableOre("infected_gold_ore").setHardness(3.0F);
        NibiruBlocks.INFECTED_TIN_ORE = new BlockMineableOre("infected_tin_ore").setHardness(3.0F);
        NibiruBlocks.INFECTED_COPPER_ORE = new BlockMineableOre("infected_copper_ore").setHardness(3.0F);
        NibiruBlocks.INFECTED_ALUMINUM_ORE = new BlockMineableOre("infected_aluminum_ore").setHardness(3.0F);
        NibiruBlocks.INFECTED_COAL_ORE = new BlockDropableOre("infected_coal_ore", BlockDropableOre.BlockType.INFECTED_COAL_ORE).setHardness(3.0F);
        NibiruBlocks.INFECTED_LAPIS_ORE = new BlockDropableOre("infected_lapis_ore", BlockDropableOre.BlockType.LAPIS_ORE).setHardness(3.0F);
        NibiruBlocks.INFECTED_DIAMOND_ORE = new BlockDropableOre("infected_diamond_ore", BlockDropableOre.BlockType.DIAMOND_ORE).setHardness(3.0F);
        NibiruBlocks.INFECTED_EMERALD_ORE = new BlockDropableOre("infected_emerald_ore", BlockDropableOre.BlockType.EMERALD_ORE).setHardness(3.0F);
        NibiruBlocks.INFECTED_REDSTONE_ORE = new BlockDropableLitOre("infected_redstone_ore", BlockDropableLitOre.BlockType.REDSTONE_ORE).setHardness(3.0F);
        NibiruBlocks.INFECTED_SILICON_ORE = new BlockDropableOre("infected_silicon_ore", BlockDropableOre.BlockType.SILICON_ORE).setHardness(3.0F);
        NibiruBlocks.INFERUMITE_CRYSTAL_ORE = new BlockDropableOre("inferumite_crystal_ore", BlockDropableOre.BlockType.INFERUMITE_CRYSTAL_ORE).setHardness(3.0F);

        NibiruBlocks.INFECTED_OAK_LOG = new BlockLogMP("infected_oak_log");
        NibiruBlocks.INFECTED_JUNGLE_LOG = new BlockLogMP("infected_jungle_log");
        NibiruBlocks.ALIEN_BERRY_OAK_LOG = new BlockLogMP("alien_berry_oak_log");

        NibiruBlocks.INFECTED_OAK_PLANKS = new BlockBaseMP("infected_oak_planks", Material.WOOD).setSoundType(SoundType.WOOD).setHardness(2.0F).setResistance(5.0F);
        NibiruBlocks.ALIEN_BERRY_OAK_PLANKS = new BlockBaseMP("alien_berry_oak_planks", Material.WOOD).setSoundType(SoundType.WOOD).setHardness(2.0F).setResistance(5.0F);

        NibiruBlocks.INFECTED_OAK_LEAVES = new BlockLeavesMP("infected_oak_leaves", BlockLeavesMP.BlockType.INFECTED_OAK_LEAVES);
        NibiruBlocks.INFECTED_JUNGLE_LEAVES = new BlockLeavesMP("infected_jungle_leaves", BlockLeavesMP.BlockType.INFECTED_JUNGLE_LEAVES);
        NibiruBlocks.ALIEN_BERRY_OAK_LEAVES = new BlockLeavesMP("alien_berry_oak_leaves", BlockLeavesMP.BlockType.ALIEN_BERRY_OAK_LEAVES);

        NibiruBlocks.INFECTED_OAK_SAPLING = new BlockSaplingMP("infected_oak_sapling", BlockSaplingMP.BlockType.INFECTED_OAK_SAPLING);
        NibiruBlocks.INFECTED_JUNGLE_SAPLING = new BlockSaplingMP("infected_jungle_sapling", BlockSaplingMP.BlockType.INFECTED_JUNGLE_SAPLING);
        NibiruBlocks.ALIEN_BERRY_OAK_SAPLING = new BlockSaplingMP("alien_berry_oak_sapling", BlockSaplingMP.BlockType.ALIEN_BERRY_OAK_SAPLING);

        NibiruBlocks.PURE_HERB = new BlockPlaceableBushMP("pure_herb", BlockPlaceableBushMP.BlockType.PURE_HERB);
        NibiruBlocks.TERRAPUFF_HERB = new BlockPlaceableBushMP("terrapuff_herb", BlockPlaceableBushMP.BlockType.TERRAPUFF_HERB);
        NibiruBlocks.BATASIA_DANDELION = new BlockPlaceableBushMP("batasia_dandelion", BlockPlaceableBushMP.BlockType.BATASIA_DANDELION);
        NibiruBlocks.PYOLONIA = new BlockPlaceableBushMP("pyolonia", BlockPlaceableBushMP.BlockType.PYOLONIA);
        NibiruBlocks.PHILIPY = new BlockPlaceableBushMP("philipy", BlockPlaceableBushMP.BlockType.PHILIPY);
        NibiruBlocks.WHITE_TAIL = new BlockPlaceableBushMP("white_tail", BlockPlaceableBushMP.BlockType.WHITE_TAIL);
        NibiruBlocks.VEALIUM_VINES = new BlockPlaceableBushMP("vealium_vines", BlockPlaceableBushMP.BlockType.VEALIUM_VINES);
        NibiruBlocks.TERRASHROOM = new BlockPlaceableBushMP("terrashroom", BlockPlaceableBushMP.BlockType.TERRASHROOM);

        NibiruBlocks.INFECTED_CRAFTING_TABLE = new BlockCraftingTableMP("infected_crafting_table");
        NibiruBlocks.ALIEN_BERRY_CRAFTING_TABLE = new BlockCraftingTableMP("alien_berry_crafting_table");

        NibiruBlocks.INFECTED_OAK_BOOKSHELF = new BlockBookshelfMP("infected_oak_bookshelf");
        NibiruBlocks.ALIEN_BERRY_OAK_BOOKSHELF = new BlockBookshelfMP("alien_berry_oak_bookshelf");

        NibiruBlocks.INFECTED_OAK_FENCE = new BlockFenceMP("infected_oak_fence");
        NibiruBlocks.ALIEN_BERRY_OAK_FENCE = new BlockFenceMP("alien_berry_oak_fence");

        NibiruBlocks.INFECTED_FARMLAND = new BlockInfectedFarmland("infected_farmland");
        NibiruBlocks.INFECTED_SAND = new BlockFallingMP("infected_sand").setSoundType(SoundType.SAND).setHardness(0.5F);
        NibiruBlocks.INFECTED_CACTUS = new BlockInfectedCactus("infected_cactus");
        NibiruBlocks.INFECTED_VINES = new BlockInfectedVines("infected_vines");
        NibiruBlocks.SPORELILY = new BlockSporelily("sporelily");
        NibiruBlocks.INFECTED_OAK_FENCE_GATE = new BlockFenceGateMP("infected_oak_fence_gate");
        NibiruBlocks.INFECTED_OAK_DOOR_BLOCK = new BlockDoorMP("infected_oak_door");
        NibiruBlocks.ALIEN_BERRY_OAK_DOOR_BLOCK = new BlockDoorMP("alien_berry_oak_door_block");
        NibiruBlocks.NIBIRU_COBBLESTONE_STAIRS = new BlockStairsMP("nibiru_cobblestone_stairs", EnumStairsType.COBBLESTONE);
        NibiruBlocks.NIBIRU_STONE_BRICKS_STAIRS = new BlockStairsMP("nibiru_stone_bricks_stairs", EnumStairsType.STONE_BRICK);
        NibiruBlocks.NIBIRU_ANCIENT_CHEST = new BlockNibiruAncientChest("nibiru_ancient_chest");
        NibiruBlocks.INFECTED_SANDSTONE_STAIRS = new BlockStairsMP("infected_sandstone_stairs", EnumStairsType.SANDSTONE);
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
        NibiruBlocks.INFECTED_PRISMARINE = new BlockBaseMP("infected_prismarine", Material.ROCK).setHardness(1.5F).setResistance(10.0F);
        NibiruBlocks.INFECTED_PRISMARINE_BRICKS = new BlockBaseMP("infected_prismarine_bricks", Material.ROCK).setHardness(1.5F).setResistance(10.0F);
        NibiruBlocks.INFECTED_DARK_PRISMARINE = new BlockBaseMP("infected_dark_prismarine", Material.ROCK).setHardness(1.5F).setResistance(10.0F);
        NibiruBlocks.INFECTED_SEA_LANTERN = new BlockInfectedSeaLantern("infected_sea_lantern");
        NibiruBlocks.INFECTED_SPONGE = new BlockInfectedSponge("infected_sponge", false);
        NibiruBlocks.INFECTED_WET_SPONGE = new BlockInfectedSponge("infected_wet_sponge", true);
        NibiruBlocks.ALIEN_BERRY_CHEST = new BlockAlienBerryChest("alien_berry_chest");
        NibiruBlocks.INFECTED_SEAWEED = new BlockInfectedSeaweed("infected_seaweed");
        NibiruBlocks.OIL_ORE = new BlockOilOre("oil_ore");
        NibiruBlocks.INFECTED_VINES_DIRT = new BlockInfectedVinesDirt("infected_vines_dirt");
        NibiruBlocks.INFECTED_TORCH = new BlockInfectedTorch("infected_torch");
        NibiruBlocks.INFECTED_FURNACE = new BlockFurnaceMP("infected_furnace", BlockFurnaceMP.BlockType.INFECTED);
        NibiruBlocks.JUICER_EGG = new BlockJuicerEgg("juicer_egg");
        NibiruBlocks.INFECTED_MELON_STEM = new BlockStemMP("infected_melon_stem", NibiruBlocks.INFECTED_MELON_BLOCK);
        NibiruBlocks.NIBIRU_DUNGEON_BRICK_STAIRS = new BlockStairsMP("nibiru_dungeon_brick_stairs", EnumStairsType.DUNGEON_BRICK).setSortCategory(EnumSortCategoryBlock.STAIRS_DUNGEON_BRICK);
        NibiruBlocks.INFECTED_OAK_STAIRS = new BlockStairsMP("infected_oak_stairs", EnumStairsType.WOODEN).setSortCategory(EnumSortCategoryBlock.STAIRS_WOODEN);
        NibiruBlocks.ALIEN_BERRY_OAK_STAIRS = new BlockStairsMP("alien_berry_oak_stairs", EnumStairsType.WOODEN).setSortCategory(EnumSortCategoryBlock.STAIRS_WOODEN);
        NibiruBlocks.INFECTED_PRISMARINE_STAIRS = new BlockStairsMP("infected_prismarine_stairs", EnumStairsType.STONE_BRICK);
        NibiruBlocks.INFECTED_PRISMARINE_BRICKS_STAIRS = new BlockStairsMP("infected_prismarine_bricks_stairs", EnumStairsType.STONE_BRICK);
        NibiruBlocks.INFECTED_DARK_PRISMARINE_STAIRS = new BlockStairsMP("infected_dark_prismarine_stairs", EnumStairsType.STONE_BRICK);
        NibiruBlocks.INFECTED_VEIN_STONE_BRICKS_STAIRS = new BlockStairsMP("infected_vein_stone_bricks_stairs", EnumStairsType.STONE_BRICK);
        NibiruBlocks.CRACKED_INFECTED_STONE_BRICKS_STAIRS = new BlockStairsMP("cracked_infected_stone_bricks_stairs", EnumStairsType.STONE_BRICK);
        NibiruBlocks.INFECTED_CUT_SANDSTONE_STAIRS = new BlockStairsMP("infected_cut_sandstone_stairs", EnumStairsType.SANDSTONE);
        NibiruBlocks.NUCLEAR_WASTE_TANK = new BlockNuclearWasteTank("nuclear_waste_tank");
        NibiruBlocks.VEIN_FRAME = new BlockVeinFrame("vein_frame");
        NibiruBlocks.VEIN_PORTAL = new BlockVeinPortal("vein_portal");
        NibiruBlocks.NUCLEAR_WASTE_GENERATOR = new BlockNuclearWasteGenerator("nuclear_waste_generator");
        NibiruBlocks.ALIEN_BERRY_OAK_FENCE_GATE = new BlockFenceGateMP("alien_berry_oak_fence_gate");
        NibiruBlocks.MULTALIC_CRYSTAL_BLOCK = new BlockMultalicCrystalBlock("multalic_crystal_block");
        NibiruBlocks.TERRASTONE = new BlockBaseMP("terrastone", Material.ROCK).setHardness(1.5F).setResistance(10.0F);
        NibiruBlocks.PURIFIED_GRAVEL = new BlockPurifiedGravel("purified_gravel");
        NibiruBlocks.TERRABERRY_BLOCK = new BlockTerraberryCrops("terraberry_block");
        NibiruBlocks.HUGE_TERRASHROOM_BLOCK = new BlockHugeTerrashroom("huge_terrashroom_block");
        NibiruBlocks.TERRASTONE_STAIRS = new BlockStairsMP("terrastone_stairs", EnumStairsType.STONE_BRICK);
        NibiruBlocks.TERRASTONE_FURNACE = new BlockFurnaceMP("terrastone_furnace", BlockFurnaceMP.BlockType.TERRASTONE);
        NibiruBlocks.SEALABLE_NUCLEAR_WASTE_ROD = new BlockSealableNuclearWasteRod("sealable_nuclear_waste_rod");
        NibiruBlocks.INFECTED_GRASS_PATH = new BlockNibiruGrassPath("infected_grass_path");
        NibiruBlocks.GREEN_VEIN_GRASS_PATH = new BlockNibiruGrassPath("green_vein_grass_path");

        NibiruBlocks.INFECTED_PRISMARINE_SLAB = new BlockAllHalfSlab("infected_prismarine_slab", BlockAllHalfSlab.BlockType.INFECTED_PRISMARINE_SLAB);
        NibiruBlocks.INFECTED_PRISMARINE_BRICKS_SLAB = new BlockAllHalfSlab("infected_prismarine_bricks_slab", BlockAllHalfSlab.BlockType.INFECTED_PRISMARINE_BRICKS_SLAB);
        NibiruBlocks.INFECTED_DARK_PRISMARINE_SLAB = new BlockAllHalfSlab("infected_dark_prismarine_slab", BlockAllHalfSlab.BlockType.INFECTED_DARK_PRISMARINE_SLAB);
        NibiruBlocks.INFECTED_STONE_BRICKS_SLAB = new BlockAllHalfSlab("infected_stone_bricks_slab", BlockAllHalfSlab.BlockType.INFECTED_STONE_BRICKS_SLAB);
        NibiruBlocks.INFECTED_VEIN_STONE_BRICKS_SLAB = new BlockAllHalfSlab("infected_vein_stone_bricks_slab", BlockAllHalfSlab.BlockType.INFECTED_VEIN_STONE_BRICKS_SLAB);
        NibiruBlocks.INFECTED_CRACKED_STONE_BRICKS_SLAB = new BlockAllHalfSlab("infected_cracked_stone_bricks_slab", BlockAllHalfSlab.BlockType.INFECTED_CRACKED_STONE_BRICKS_SLAB);
        NibiruBlocks.TERRASTONE_SLAB = new BlockAllHalfSlab("terrastone_slab", BlockAllHalfSlab.BlockType.TERRASTONE_SLAB);
        NibiruBlocks.INFECTED_SANDSTONE_SLAB = new BlockAllHalfSlab("infected_sandstone_slab", BlockAllHalfSlab.BlockType.INFECTED_SANDSTONE_SLAB);
        NibiruBlocks.INFECTED_CUT_SANDSTONE_SLAB = new BlockAllHalfSlab("infected_cut_sandstone_slab", BlockAllHalfSlab.BlockType.INFECTED_CUT_SANDSTONE_SLAB);

        NibiruBlocks.DOUBLE_INFECTED_PRISMARINE_SLAB = new BlockAllDoubleSlab("double_infected_prismarine_slab", BlockAllHalfSlab.BlockType.INFECTED_PRISMARINE_SLAB);
        NibiruBlocks.DOUBLE_INFECTED_PRISMARINE_BRICKS_SLAB = new BlockAllDoubleSlab("double_infected_prismarine_bricks_slab", BlockAllHalfSlab.BlockType.INFECTED_PRISMARINE_BRICKS_SLAB);
        NibiruBlocks.DOUBLE_INFECTED_DARK_PRISMARINE_SLAB = new BlockAllDoubleSlab("double_infected_dark_prismarine_slab", BlockAllHalfSlab.BlockType.INFECTED_DARK_PRISMARINE_SLAB);
        NibiruBlocks.DOUBLE_INFECTED_STONE_BRICKS_SLAB = new BlockAllDoubleSlab("double_infected_stone_bricks_slab", BlockAllHalfSlab.BlockType.INFECTED_STONE_BRICKS_SLAB);
        NibiruBlocks.DOUBLE_INFECTED_VEIN_STONE_BRICKS_SLAB = new BlockAllDoubleSlab("double_infected_vein_stone_bricks_slab", BlockAllHalfSlab.BlockType.INFECTED_VEIN_STONE_BRICKS_SLAB);
        NibiruBlocks.DOUBLE_INFECTED_CRACKED_STONE_BRICKS_SLAB = new BlockAllDoubleSlab("double_infected_cracked_stone_bricks_slab", BlockAllHalfSlab.BlockType.INFECTED_CRACKED_STONE_BRICKS_SLAB);
        NibiruBlocks.DOUBLE_TERRASTONE_SLAB = new BlockAllDoubleSlab("double_terrastone_slab", BlockAllHalfSlab.BlockType.TERRASTONE_SLAB);
        NibiruBlocks.DOUBLE_INFECTED_SANDSTONE_SLAB = new BlockAllDoubleSlab("double_infected_sandstone_slab", BlockAllHalfSlab.BlockType.INFECTED_SANDSTONE_SLAB);
        NibiruBlocks.DOUBLE_INFECTED_CUT_SANDSTONE_SLAB = new BlockAllDoubleSlab("double_infected_cut_sandstone_slab", BlockAllHalfSlab.BlockType.INFECTED_CUT_SANDSTONE_SLAB);

        NibiruBlocks.INFECTED_WATER_FLUID = new FluidMP("infected_water_fluid").setBlock(NibiruBlocks.INFECTED_WATER_FLUID_BLOCK);
        NibiruBlocks.HELIUM_GAS = new FluidHeliumGas("helium_gas", "moreplanets:blocks/helium_gas", "moreplanets:blocks/helium_gas").setBlock(NibiruBlocks.HELIUM_GAS_BLOCK);
        NibiruBlocks.NUCLEAR_WASTE_FLUID = new FluidMP("nuclear_waste_fluid").setBlock(NibiruBlocks.NUCLEAR_WASTE_FLUID_BLOCK).setLuminosity(15).setDensity(3000).setViscosity(8000).setTemperature(2600);
        NibiruBlocks.PURIFIED_WATER_FLUID = new FluidMP("purified_water_fluid").setBlock(NibiruBlocks.PURIFIED_WATER_FLUID_BLOCK);
        MorePlanetsMod.COMMON_REGISTRY.registerFluid(NibiruBlocks.INFECTED_WATER_FLUID);
        MorePlanetsMod.COMMON_REGISTRY.registerFluid(NibiruBlocks.HELIUM_GAS);
        MorePlanetsMod.COMMON_REGISTRY.registerFluid(NibiruBlocks.NUCLEAR_WASTE_FLUID);
        MorePlanetsMod.COMMON_REGISTRY.registerFluid(NibiruBlocks.PURIFIED_WATER_FLUID);
        NibiruBlocks.INFECTED_WATER_FLUID_BLOCK = new BlockFluidInfectedWater("infected_water_fluid");
        NibiruBlocks.HELIUM_GAS_BLOCK = new BlockGasHelium("helium_gas");
        NibiruBlocks.NUCLEAR_WASTE_FLUID_BLOCK = new BlockFluidNuclearWaste("nuclear_waste_fluid");
        NibiruBlocks.PURIFIED_WATER_FLUID_BLOCK = new BlockFluidPurifyWater("purified_water_fluid");

        /**************************************************************/
        /************************ REGISTER STUFF ************************/
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

        BlocksItemsRegistry.registerBlock(NibiruBlocks.INFECTED_IRON_ORE);
        BlocksItemsRegistry.registerBlock(NibiruBlocks.INFECTED_GOLD_ORE);
        BlocksItemsRegistry.registerBlock(NibiruBlocks.INFECTED_TIN_ORE);
        BlocksItemsRegistry.registerBlock(NibiruBlocks.INFECTED_COPPER_ORE);
        BlocksItemsRegistry.registerBlock(NibiruBlocks.INFECTED_ALUMINUM_ORE);
        BlocksItemsRegistry.registerBlock(NibiruBlocks.INFECTED_COAL_ORE);
        BlocksItemsRegistry.registerBlock(NibiruBlocks.INFECTED_LAPIS_ORE);
        BlocksItemsRegistry.registerBlock(NibiruBlocks.INFECTED_DIAMOND_ORE);
        BlocksItemsRegistry.registerBlock(NibiruBlocks.INFECTED_EMERALD_ORE);
        BlocksItemsRegistry.registerBlock(NibiruBlocks.INFECTED_REDSTONE_ORE);
        BlocksItemsRegistry.registerBlock(NibiruBlocks.INFECTED_SILICON_ORE);
        BlocksItemsRegistry.registerBlock(NibiruBlocks.INFERUMITE_CRYSTAL_ORE);

        BlocksItemsRegistry.registerBlock(NibiruBlocks.INFECTED_OAK_LOG);
        BlocksItemsRegistry.registerBlock(NibiruBlocks.INFECTED_JUNGLE_LOG);
        BlocksItemsRegistry.registerBlock(NibiruBlocks.ALIEN_BERRY_OAK_LOG);

        BlocksItemsRegistry.registerBlock(NibiruBlocks.INFECTED_OAK_PLANKS);
        BlocksItemsRegistry.registerBlock(NibiruBlocks.ALIEN_BERRY_OAK_PLANKS);

        BlocksItemsRegistry.registerBlock(NibiruBlocks.INFECTED_OAK_LEAVES, ItemBlockSingleLeaves::new);
        BlocksItemsRegistry.registerBlock(NibiruBlocks.INFECTED_JUNGLE_LEAVES, ItemBlockSingleLeaves::new);
        BlocksItemsRegistry.registerBlock(NibiruBlocks.ALIEN_BERRY_OAK_LEAVES, ItemBlockSingleLeaves::new);

        BlocksItemsRegistry.registerBlock(NibiruBlocks.INFECTED_OAK_SAPLING);
        BlocksItemsRegistry.registerBlock(NibiruBlocks.INFECTED_JUNGLE_SAPLING);
        BlocksItemsRegistry.registerBlock(NibiruBlocks.ALIEN_BERRY_OAK_SAPLING);

        BlocksItemsRegistry.registerBlock(NibiruBlocks.PURE_HERB);
        BlocksItemsRegistry.registerBlock(NibiruBlocks.TERRAPUFF_HERB);
        BlocksItemsRegistry.registerBlock(NibiruBlocks.BATASIA_DANDELION);
        BlocksItemsRegistry.registerBlock(NibiruBlocks.PYOLONIA);
        BlocksItemsRegistry.registerBlock(NibiruBlocks.PHILIPY);
        BlocksItemsRegistry.registerBlock(NibiruBlocks.WHITE_TAIL);
        BlocksItemsRegistry.registerBlock(NibiruBlocks.VEALIUM_VINES);
        BlocksItemsRegistry.registerBlock(NibiruBlocks.TERRASHROOM);

        BlocksItemsRegistry.registerBlock(NibiruBlocks.INFECTED_CRAFTING_TABLE);
        BlocksItemsRegistry.registerBlock(NibiruBlocks.ALIEN_BERRY_CRAFTING_TABLE);

        BlocksItemsRegistry.registerBlock(NibiruBlocks.INFECTED_OAK_BOOKSHELF);
        BlocksItemsRegistry.registerBlock(NibiruBlocks.ALIEN_BERRY_OAK_BOOKSHELF);

        BlocksItemsRegistry.registerBlock(NibiruBlocks.INFECTED_OAK_FENCE);
        BlocksItemsRegistry.registerBlock(NibiruBlocks.ALIEN_BERRY_OAK_FENCE);

        BlocksItemsRegistry.registerBlock(NibiruBlocks.INFECTED_GRASS_PATH);
        BlocksItemsRegistry.registerBlock(NibiruBlocks.GREEN_VEIN_GRASS_PATH);

        BlocksItemsRegistry.registerBlock(NibiruBlocks.INFECTED_FARMLAND);
        BlocksItemsRegistry.registerBlock(NibiruBlocks.INFECTED_SAND);
        BlocksItemsRegistry.registerBlock(NibiruBlocks.INFECTED_CACTUS);
        BlocksItemsRegistry.registerBlock(NibiruBlocks.INFECTED_WATER_FLUID_BLOCK);
        BlocksItemsRegistry.registerBlock(NibiruBlocks.INFECTED_VINES);
        BlocksItemsRegistry.registerBlock(NibiruBlocks.SPORELILY, ItemBlockSporelily::new);
        BlocksItemsRegistry.registerBlock(NibiruBlocks.INFECTED_OAK_FENCE_GATE);
        BlocksItemsRegistry.registerBlock(NibiruBlocks.INFECTED_OAK_DOOR_BLOCK, null);
        BlocksItemsRegistry.registerBlock(NibiruBlocks.ALIEN_BERRY_OAK_DOOR_BLOCK, null);
        BlocksItemsRegistry.registerBlock(NibiruBlocks.NIBIRU_COBBLESTONE_STAIRS);
        BlocksItemsRegistry.registerBlock(NibiruBlocks.NIBIRU_STONE_BRICKS_STAIRS);
        BlocksItemsRegistry.registerBlock(NibiruBlocks.NIBIRU_ANCIENT_CHEST);
        BlocksItemsRegistry.registerBlock(NibiruBlocks.INFECTED_SANDSTONE_STAIRS);
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
        BlocksItemsRegistry.registerBlock(NibiruBlocks.INFECTED_PRISMARINE);
        BlocksItemsRegistry.registerBlock(NibiruBlocks.INFECTED_PRISMARINE_BRICKS);
        BlocksItemsRegistry.registerBlock(NibiruBlocks.INFECTED_DARK_PRISMARINE);
        BlocksItemsRegistry.registerBlock(NibiruBlocks.INFECTED_SEA_LANTERN);
        BlocksItemsRegistry.registerBlock(NibiruBlocks.INFECTED_SPONGE);
        BlocksItemsRegistry.registerBlock(NibiruBlocks.INFECTED_WET_SPONGE);
        BlocksItemsRegistry.registerBlock(NibiruBlocks.INFECTED_SEAWEED);
        BlocksItemsRegistry.registerBlock(NibiruBlocks.OIL_ORE);
        BlocksItemsRegistry.registerBlock(NibiruBlocks.HELIUM_GAS_BLOCK);
        BlocksItemsRegistry.registerBlock(NibiruBlocks.INFECTED_VINES_DIRT);
        BlocksItemsRegistry.registerBlock(NibiruBlocks.INFECTED_TORCH);
        BlocksItemsRegistry.registerBlock(NibiruBlocks.INFECTED_FURNACE);
        BlocksItemsRegistry.registerBlock(NibiruBlocks.TERRASTONE_FURNACE);
        BlocksItemsRegistry.registerBlock(NibiruBlocks.JUICER_EGG);
        BlocksItemsRegistry.registerBlock(NibiruBlocks.INFECTED_MELON_STEM, null);
        BlocksItemsRegistry.registerBlock(NibiruBlocks.NIBIRU_DUNGEON_BRICK_STAIRS);
        BlocksItemsRegistry.registerBlock(NibiruBlocks.INFECTED_OAK_STAIRS);
        BlocksItemsRegistry.registerBlock(NibiruBlocks.ALIEN_BERRY_OAK_STAIRS);
        BlocksItemsRegistry.registerBlock(NibiruBlocks.INFECTED_PRISMARINE_STAIRS);
        BlocksItemsRegistry.registerBlock(NibiruBlocks.INFECTED_PRISMARINE_BRICKS_STAIRS);
        BlocksItemsRegistry.registerBlock(NibiruBlocks.INFECTED_DARK_PRISMARINE_STAIRS);
        BlocksItemsRegistry.registerBlock(NibiruBlocks.INFECTED_VEIN_STONE_BRICKS_STAIRS);
        BlocksItemsRegistry.registerBlock(NibiruBlocks.CRACKED_INFECTED_STONE_BRICKS_STAIRS);
        BlocksItemsRegistry.registerBlock(NibiruBlocks.INFECTED_CUT_SANDSTONE_STAIRS);
        BlocksItemsRegistry.registerBlock(NibiruBlocks.NUCLEAR_WASTE_TANK, ItemBlockNuclearWasteTank::new);
        BlocksItemsRegistry.registerBlock(NibiruBlocks.NUCLEAR_WASTE_FLUID_BLOCK);
        BlocksItemsRegistry.registerBlock(NibiruBlocks.VEIN_FRAME);
        BlocksItemsRegistry.registerBlock(NibiruBlocks.VEIN_PORTAL, null);
        BlocksItemsRegistry.registerBlock(NibiruBlocks.NUCLEAR_WASTE_GENERATOR, ItemBlockDescription::new);
        BlocksItemsRegistry.registerBlock(NibiruBlocks.ALIEN_BERRY_OAK_FENCE_GATE);

        BlocksItemsRegistry.registerBlock(NibiruBlocks.INFECTED_PRISMARINE_SLAB, ItemBlockSlabMP::new);
        BlocksItemsRegistry.registerBlock(NibiruBlocks.INFECTED_PRISMARINE_BRICKS_SLAB, ItemBlockSlabMP::new);
        BlocksItemsRegistry.registerBlock(NibiruBlocks.INFECTED_DARK_PRISMARINE_SLAB, ItemBlockSlabMP::new);
        BlocksItemsRegistry.registerBlock(NibiruBlocks.INFECTED_STONE_BRICKS_SLAB, ItemBlockSlabMP::new);
        BlocksItemsRegistry.registerBlock(NibiruBlocks.INFECTED_VEIN_STONE_BRICKS_SLAB, ItemBlockSlabMP::new);
        BlocksItemsRegistry.registerBlock(NibiruBlocks.INFECTED_CRACKED_STONE_BRICKS_SLAB, ItemBlockSlabMP::new);
        BlocksItemsRegistry.registerBlock(NibiruBlocks.TERRASTONE_SLAB, ItemBlockSlabMP::new);
        BlocksItemsRegistry.registerBlock(NibiruBlocks.INFECTED_SANDSTONE_SLAB, ItemBlockSlabMP::new);
        BlocksItemsRegistry.registerBlock(NibiruBlocks.INFECTED_CUT_SANDSTONE_SLAB, ItemBlockSlabMP::new);

        BlocksItemsRegistry.registerBlock(NibiruBlocks.DOUBLE_INFECTED_PRISMARINE_SLAB, null);
        BlocksItemsRegistry.registerBlock(NibiruBlocks.DOUBLE_INFECTED_PRISMARINE_BRICKS_SLAB, null);
        BlocksItemsRegistry.registerBlock(NibiruBlocks.DOUBLE_INFECTED_DARK_PRISMARINE_SLAB, null);
        BlocksItemsRegistry.registerBlock(NibiruBlocks.DOUBLE_INFECTED_STONE_BRICKS_SLAB, null);
        BlocksItemsRegistry.registerBlock(NibiruBlocks.DOUBLE_INFECTED_VEIN_STONE_BRICKS_SLAB, null);
        BlocksItemsRegistry.registerBlock(NibiruBlocks.DOUBLE_INFECTED_CRACKED_STONE_BRICKS_SLAB, null);
        BlocksItemsRegistry.registerBlock(NibiruBlocks.DOUBLE_TERRASTONE_SLAB, null);
        BlocksItemsRegistry.registerBlock(NibiruBlocks.DOUBLE_INFECTED_SANDSTONE_SLAB, null);
        BlocksItemsRegistry.registerBlock(NibiruBlocks.DOUBLE_INFECTED_CUT_SANDSTONE_SLAB, null);

        BlocksItemsRegistry.registerBlock(NibiruBlocks.MULTALIC_CRYSTAL_BLOCK);
        BlocksItemsRegistry.registerBlock(NibiruBlocks.TERRASTONE);
        BlocksItemsRegistry.registerBlock(NibiruBlocks.PURIFIED_WATER_FLUID_BLOCK);
        BlocksItemsRegistry.registerBlock(NibiruBlocks.PURIFIED_GRAVEL);
        BlocksItemsRegistry.registerBlock(NibiruBlocks.TERRABERRY_BLOCK, null);
        BlocksItemsRegistry.registerBlock(NibiruBlocks.HUGE_TERRASHROOM_BLOCK);
        BlocksItemsRegistry.registerBlock(NibiruBlocks.TERRASTONE_STAIRS);
        BlocksItemsRegistry.registerBlock(NibiruBlocks.SEALABLE_NUCLEAR_WASTE_ROD, ItemBlockDescription::new);

        /**************************************************************/
        /********************** HARVEST LEVEL STUFF *********************/
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
        BlockUtils.setBlockHarvestLevel(NibiruBlocks.PURIFIED_GRAVEL, EnumHarvestLevel.SHOVEL, 0);
        BlockUtils.setBlockHarvestLevel(NibiruBlocks.INFECTED_GRASS_PATH, EnumHarvestLevel.SHOVEL, 0);
        BlockUtils.setBlockHarvestLevel(NibiruBlocks.GREEN_VEIN_GRASS_PATH, EnumHarvestLevel.SHOVEL, 0);
        BlockUtils.setBlockHarvestLevel(NibiruBlocks.INFECTED_SANDSTONE, EnumHarvestLevel.PICKAXE, 0);
        BlockUtils.setBlockHarvestLevel(NibiruBlocks.INFECTED_CHISELED_SANDSTONE, EnumHarvestLevel.PICKAXE, 0);
        BlockUtils.setBlockHarvestLevel(NibiruBlocks.INFECTED_CUT_SANDSTONE, EnumHarvestLevel.PICKAXE, 0);
        BlockUtils.setBlockHarvestLevel(NibiruBlocks.NIBIRU_COBBLESTONE_STAIRS, EnumHarvestLevel.PICKAXE, 0);
        BlockUtils.setBlockHarvestLevel(NibiruBlocks.NIBIRU_STONE_BRICKS_STAIRS, EnumHarvestLevel.PICKAXE, 0);
        BlockUtils.setBlockHarvestLevel(NibiruBlocks.INFECTED_SANDSTONE_STAIRS, EnumHarvestLevel.PICKAXE, 0);
        BlockUtils.setBlockHarvestLevel(NibiruBlocks.INFECTED_PACKED_ICE, EnumHarvestLevel.PICKAXE, 0);
        BlockUtils.setBlockHarvestLevel(NibiruBlocks.INFECTED_PRISMARINE, EnumHarvestLevel.PICKAXE, 0);
        BlockUtils.setBlockHarvestLevel(NibiruBlocks.INFECTED_PRISMARINE_BRICKS, EnumHarvestLevel.PICKAXE, 0);
        BlockUtils.setBlockHarvestLevel(NibiruBlocks.INFECTED_DARK_PRISMARINE, EnumHarvestLevel.PICKAXE, 0);
        BlockUtils.setBlockHarvestLevel(NibiruBlocks.INFECTED_FURNACE, EnumHarvestLevel.PICKAXE, 0);
        BlockUtils.setBlockHarvestLevel(NibiruBlocks.OIL_ORE, EnumHarvestLevel.PICKAXE, 0);
        BlockUtils.setBlockHarvestLevel(NibiruBlocks.INFECTED_PRISMARINE_STAIRS, EnumHarvestLevel.PICKAXE, 0);
        BlockUtils.setBlockHarvestLevel(NibiruBlocks.INFECTED_PRISMARINE_BRICKS_STAIRS, EnumHarvestLevel.PICKAXE, 0);
        BlockUtils.setBlockHarvestLevel(NibiruBlocks.INFECTED_DARK_PRISMARINE_STAIRS, EnumHarvestLevel.PICKAXE, 0);
        BlockUtils.setBlockHarvestLevel(NibiruBlocks.INFECTED_VEIN_STONE_BRICKS_STAIRS, EnumHarvestLevel.PICKAXE, 0);
        BlockUtils.setBlockHarvestLevel(NibiruBlocks.CRACKED_INFECTED_STONE_BRICKS_STAIRS, EnumHarvestLevel.PICKAXE, 0);
        BlockUtils.setBlockHarvestLevel(NibiruBlocks.INFECTED_CUT_SANDSTONE_STAIRS, EnumHarvestLevel.PICKAXE, 0);
        BlockUtils.setBlockHarvestLevel(NibiruBlocks.INFECTED_PRISMARINE_SLAB, EnumHarvestLevel.PICKAXE, 0);
        BlockUtils.setBlockHarvestLevel(NibiruBlocks.INFECTED_PRISMARINE_BRICKS_SLAB, EnumHarvestLevel.PICKAXE, 0);
        BlockUtils.setBlockHarvestLevel(NibiruBlocks.INFECTED_DARK_PRISMARINE_SLAB, EnumHarvestLevel.PICKAXE, 0);
        BlockUtils.setBlockHarvestLevel(NibiruBlocks.INFECTED_STONE_BRICKS_SLAB, EnumHarvestLevel.PICKAXE, 0);
        BlockUtils.setBlockHarvestLevel(NibiruBlocks.INFECTED_VEIN_STONE_BRICKS_SLAB, EnumHarvestLevel.PICKAXE, 0);
        BlockUtils.setBlockHarvestLevel(NibiruBlocks.INFECTED_CRACKED_STONE_BRICKS_SLAB, EnumHarvestLevel.PICKAXE, 0);
        BlockUtils.setBlockHarvestLevel(NibiruBlocks.TERRASTONE_SLAB, EnumHarvestLevel.PICKAXE, 0);
        BlockUtils.setBlockHarvestLevel(NibiruBlocks.INFECTED_SANDSTONE_SLAB, EnumHarvestLevel.PICKAXE, 0);
        BlockUtils.setBlockHarvestLevel(NibiruBlocks.INFECTED_CUT_SANDSTONE_SLAB, EnumHarvestLevel.PICKAXE, 0);
        BlockUtils.setBlockHarvestLevel(NibiruBlocks.DOUBLE_INFECTED_PRISMARINE_SLAB, EnumHarvestLevel.PICKAXE, 0);
        BlockUtils.setBlockHarvestLevel(NibiruBlocks.DOUBLE_INFECTED_PRISMARINE_BRICKS_SLAB, EnumHarvestLevel.PICKAXE, 0);
        BlockUtils.setBlockHarvestLevel(NibiruBlocks.DOUBLE_INFECTED_DARK_PRISMARINE_SLAB, EnumHarvestLevel.PICKAXE, 0);
        BlockUtils.setBlockHarvestLevel(NibiruBlocks.DOUBLE_INFECTED_STONE_BRICKS_SLAB, EnumHarvestLevel.PICKAXE, 0);
        BlockUtils.setBlockHarvestLevel(NibiruBlocks.DOUBLE_INFECTED_VEIN_STONE_BRICKS_SLAB, EnumHarvestLevel.PICKAXE, 0);
        BlockUtils.setBlockHarvestLevel(NibiruBlocks.DOUBLE_INFECTED_CRACKED_STONE_BRICKS_SLAB, EnumHarvestLevel.PICKAXE, 0);
        BlockUtils.setBlockHarvestLevel(NibiruBlocks.DOUBLE_TERRASTONE_SLAB, EnumHarvestLevel.PICKAXE, 0);
        BlockUtils.setBlockHarvestLevel(NibiruBlocks.DOUBLE_INFECTED_SANDSTONE_SLAB, EnumHarvestLevel.PICKAXE, 0);
        BlockUtils.setBlockHarvestLevel(NibiruBlocks.DOUBLE_INFECTED_CUT_SANDSTONE_SLAB, EnumHarvestLevel.PICKAXE, 0);
        BlockUtils.setBlockHarvestLevel(NibiruBlocks.TERRASTONE, EnumHarvestLevel.PICKAXE, 0);
        BlockUtils.setBlockHarvestLevel(NibiruBlocks.TERRASTONE_STAIRS, EnumHarvestLevel.PICKAXE, 0);
        BlockUtils.setBlockHarvestLevel(NibiruBlocks.TERRASTONE_FURNACE, EnumHarvestLevel.PICKAXE, 0);
        BlockUtils.setBlockHarvestLevel(NibiruBlocks.SEALABLE_NUCLEAR_WASTE_ROD, EnumHarvestLevel.PICKAXE, 0);
        BlockUtils.setBlockHarvestLevel(NibiruBlocks.MULTALIC_CRYSTAL_BLOCK, EnumHarvestLevel.PICKAXE, 1);
        BlockUtils.setBlockHarvestLevel(NibiruBlocks.NIBIRU_DUNGEON_BRICK_STAIRS, EnumHarvestLevel.PICKAXE, 1);
        BlockUtils.setBlockHarvestLevel(NibiruBlocks.NUCLEAR_WASTE_GENERATOR, EnumHarvestLevel.PICKAXE, 2);
        BlockUtils.setBlockHarvestLevel(NibiruBlocks.NUCLEAR_WASTE_TANK, EnumHarvestLevel.PICKAXE, 2);
        BlockUtils.setBlockHarvestLevel(NibiruBlocks.MULTALIC_CRYSTAL, EnumHarvestLevel.PICKAXE, 2);
        BlockUtils.setBlockHarvestLevel(NibiruBlocks.INFECTED_OAK_LOG, EnumHarvestLevel.AXE, 0);
        BlockUtils.setBlockHarvestLevel(NibiruBlocks.INFECTED_JUNGLE_LOG, EnumHarvestLevel.AXE, 0);
        BlockUtils.setBlockHarvestLevel(NibiruBlocks.ALIEN_BERRY_OAK_LOG, EnumHarvestLevel.AXE, 0);
        BlockUtils.setBlockHarvestLevel(NibiruBlocks.INFECTED_OAK_PLANKS, EnumHarvestLevel.AXE, 0);
        BlockUtils.setBlockHarvestLevel(NibiruBlocks.ALIEN_BERRY_OAK_PLANKS, EnumHarvestLevel.AXE, 0);
        BlockUtils.setBlockHarvestLevel(NibiruBlocks.INFECTED_CRAFTING_TABLE, EnumHarvestLevel.AXE, 0);
        BlockUtils.setBlockHarvestLevel(NibiruBlocks.ALIEN_BERRY_CRAFTING_TABLE, EnumHarvestLevel.AXE, 0);
        BlockUtils.setBlockHarvestLevel(NibiruBlocks.INFECTED_OAK_BOOKSHELF, EnumHarvestLevel.AXE, 0);
        BlockUtils.setBlockHarvestLevel(NibiruBlocks.ALIEN_BERRY_OAK_BOOKSHELF, EnumHarvestLevel.AXE, 0);
        BlockUtils.setBlockHarvestLevel(NibiruBlocks.INFECTED_OAK_FENCE_GATE, EnumHarvestLevel.AXE, 0);
        BlockUtils.setBlockHarvestLevel(NibiruBlocks.INFECTED_OAK_FENCE, EnumHarvestLevel.AXE, 0);
        BlockUtils.setBlockHarvestLevel(NibiruBlocks.ALIEN_BERRY_OAK_FENCE, EnumHarvestLevel.AXE, 0);
        BlockUtils.setBlockHarvestLevel(NibiruBlocks.INFECTED_OAK_DOOR_BLOCK, EnumHarvestLevel.AXE, 0);
        BlockUtils.setBlockHarvestLevel(NibiruBlocks.ALIEN_BERRY_OAK_DOOR_BLOCK, EnumHarvestLevel.AXE, 0);
        BlockUtils.setBlockHarvestLevel(NibiruBlocks.NIBIRU_ANCIENT_CHEST, EnumHarvestLevel.AXE, 0);
        BlockUtils.setBlockHarvestLevel(NibiruBlocks.INFECTED_MELON_BLOCK, EnumHarvestLevel.AXE, 0);
        BlockUtils.setBlockHarvestLevel(NibiruBlocks.INFECTED_CHEST, EnumHarvestLevel.AXE, 0);
        BlockUtils.setBlockHarvestLevel(NibiruBlocks.ALIEN_BERRY_CHEST, EnumHarvestLevel.AXE, 0);
        BlockUtils.setBlockHarvestLevel(NibiruBlocks.INFECTED_OAK_STAIRS, EnumHarvestLevel.AXE, 0);
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

        BlockUtils.setBlockHarvestLevel(NibiruBlocks.INFECTED_COAL_ORE, EnumHarvestLevel.PICKAXE, 0);
        BlockUtils.setBlockHarvestLevel(NibiruBlocks.INFECTED_IRON_ORE, EnumHarvestLevel.PICKAXE, 1);
        BlockUtils.setBlockHarvestLevel(NibiruBlocks.INFECTED_TIN_ORE, EnumHarvestLevel.PICKAXE, 1);
        BlockUtils.setBlockHarvestLevel(NibiruBlocks.INFECTED_COPPER_ORE, EnumHarvestLevel.PICKAXE, 1);
        BlockUtils.setBlockHarvestLevel(NibiruBlocks.INFECTED_ALUMINUM_ORE, EnumHarvestLevel.PICKAXE, 1);
        BlockUtils.setBlockHarvestLevel(NibiruBlocks.INFECTED_LAPIS_ORE, EnumHarvestLevel.PICKAXE, 1);
        BlockUtils.setBlockHarvestLevel(NibiruBlocks.INFECTED_GOLD_ORE, EnumHarvestLevel.PICKAXE, 2);
        BlockUtils.setBlockHarvestLevel(NibiruBlocks.INFECTED_DIAMOND_ORE, EnumHarvestLevel.PICKAXE, 2);
        BlockUtils.setBlockHarvestLevel(NibiruBlocks.INFECTED_EMERALD_ORE, EnumHarvestLevel.PICKAXE, 2);
        BlockUtils.setBlockHarvestLevel(NibiruBlocks.INFECTED_REDSTONE_ORE, EnumHarvestLevel.PICKAXE, 2);
        BlockUtils.setBlockHarvestLevel(NibiruBlocks.INFECTED_SILICON_ORE, EnumHarvestLevel.PICKAXE, 2);
        BlockUtils.setBlockHarvestLevel(NibiruBlocks.INFERUMITE_CRYSTAL_ORE, EnumHarvestLevel.PICKAXE, 2);

        /**************************************************************/
        /************************ FIRE BURN STUFF ***********************/
        /**************************************************************/

        BlockUtils.setFireBurn(NibiruBlocks.INFECTED_GRASS, 60, 100);
        BlockUtils.setFireBurn(NibiruBlocks.INFECTED_FERN, 60, 100);
        BlockUtils.setFireBurn(NibiruBlocks.GREEN_VEIN_GRASS, 60, 100);
        BlockUtils.setFireBurn(NibiruBlocks.INFECTED_OAK_SAPLING, 60, 100);
        BlockUtils.setFireBurn(NibiruBlocks.INFECTED_JUNGLE_SAPLING, 60, 100);
        BlockUtils.setFireBurn(NibiruBlocks.ALIEN_BERRY_OAK_SAPLING, 60, 100);
        BlockUtils.setFireBurn(NibiruBlocks.PURE_HERB, 60, 100);
        BlockUtils.setFireBurn(NibiruBlocks.TERRAPUFF_HERB, 60, 100);
        BlockUtils.setFireBurn(NibiruBlocks.BATASIA_DANDELION, 60, 100);
        BlockUtils.setFireBurn(NibiruBlocks.PYOLONIA, 60, 100);
        BlockUtils.setFireBurn(NibiruBlocks.PHILIPY, 60, 100);
        BlockUtils.setFireBurn(NibiruBlocks.WHITE_TAIL, 60, 100);
        BlockUtils.setFireBurn(NibiruBlocks.VEALIUM_VINES, 60, 100);
        BlockUtils.setFireBurn(NibiruBlocks.TERRASHROOM, 60, 100);
        BlockUtils.setFireBurn(NibiruBlocks.INFECTED_OAK_LOG, 5, 20);
        BlockUtils.setFireBurn(NibiruBlocks.INFECTED_JUNGLE_LOG, 5, 20);
        BlockUtils.setFireBurn(NibiruBlocks.ALIEN_BERRY_OAK_LOG, 5, 20);
        BlockUtils.setFireBurn(NibiruBlocks.INFECTED_OAK_PLANKS, 5, 20);
        BlockUtils.setFireBurn(NibiruBlocks.ALIEN_BERRY_OAK_PLANKS, 5, 20);
        BlockUtils.setFireBurn(NibiruBlocks.INFECTED_OAK_BOOKSHELF, 5, 20);
        BlockUtils.setFireBurn(NibiruBlocks.ALIEN_BERRY_OAK_BOOKSHELF, 5, 20);
        BlockUtils.setFireBurn(NibiruBlocks.INFECTED_OAK_FENCE, 5, 20);
        BlockUtils.setFireBurn(NibiruBlocks.ALIEN_BERRY_OAK_FENCE, 5, 20);
        BlockUtils.setFireBurn(NibiruBlocks.INFECTED_OAK_FENCE_GATE, 5, 20);
        BlockUtils.setFireBurn(NibiruBlocks.INFECTED_OAK_LEAVES, 60, 100);
        BlockUtils.setFireBurn(NibiruBlocks.INFECTED_JUNGLE_LEAVES, 60, 100);
        BlockUtils.setFireBurn(NibiruBlocks.ALIEN_BERRY_OAK_LEAVES, 60, 100);
        BlockUtils.setFireBurn(NibiruBlocks.INFECTED_VINES, 15, 100);
        BlockUtils.setFireBurn(NibiruBlocks.INFECTED_OAK_STAIRS, 5, 20);
        BlockUtils.setFireBurn(NibiruBlocks.ALIEN_BERRY_OAK_STAIRS, 5, 20);
        BlockUtils.setFireBurn(NibiruBlocks.ALIEN_BERRY_OAK_FENCE_GATE, 5, 20);

        NibiruBlocks.NIBIRU_ROCK.setDrop(NibiruBlocks.NIBIRU_COBBLESTONE);

        NibiruBlocks.INFECTED_PRISMARINE_SLAB.setHalf(NibiruBlocks.INFECTED_PRISMARINE_SLAB);
        NibiruBlocks.INFECTED_PRISMARINE_BRICKS_SLAB.setHalf(NibiruBlocks.INFECTED_PRISMARINE_BRICKS_SLAB);
        NibiruBlocks.INFECTED_DARK_PRISMARINE_SLAB.setHalf(NibiruBlocks.INFECTED_DARK_PRISMARINE_SLAB);
        NibiruBlocks.INFECTED_STONE_BRICKS_SLAB.setHalf(NibiruBlocks.INFECTED_STONE_BRICKS_SLAB);
        NibiruBlocks.INFECTED_VEIN_STONE_BRICKS_SLAB.setHalf(NibiruBlocks.INFECTED_VEIN_STONE_BRICKS_SLAB);
        NibiruBlocks.INFECTED_CRACKED_STONE_BRICKS_SLAB.setHalf(NibiruBlocks.INFECTED_CRACKED_STONE_BRICKS_SLAB);
        NibiruBlocks.TERRASTONE_SLAB.setHalf(NibiruBlocks.TERRASTONE_SLAB);
        NibiruBlocks.INFECTED_SANDSTONE_SLAB.setHalf(NibiruBlocks.INFECTED_SANDSTONE_SLAB);
        NibiruBlocks.INFECTED_CUT_SANDSTONE_SLAB.setHalf(NibiruBlocks.INFECTED_CUT_SANDSTONE_SLAB);

        NibiruBlocks.INFECTED_PRISMARINE_SLAB.setDouble(NibiruBlocks.DOUBLE_INFECTED_PRISMARINE_SLAB);
        NibiruBlocks.INFECTED_PRISMARINE_BRICKS_SLAB.setDouble(NibiruBlocks.DOUBLE_INFECTED_PRISMARINE_BRICKS_SLAB);
        NibiruBlocks.INFECTED_DARK_PRISMARINE_SLAB.setDouble(NibiruBlocks.DOUBLE_INFECTED_DARK_PRISMARINE_SLAB);
        NibiruBlocks.INFECTED_STONE_BRICKS_SLAB.setDouble(NibiruBlocks.DOUBLE_INFECTED_STONE_BRICKS_SLAB);
        NibiruBlocks.INFECTED_VEIN_STONE_BRICKS_SLAB.setDouble(NibiruBlocks.DOUBLE_INFECTED_VEIN_STONE_BRICKS_SLAB);
        NibiruBlocks.INFECTED_CRACKED_STONE_BRICKS_SLAB.setDouble(NibiruBlocks.DOUBLE_INFECTED_CRACKED_STONE_BRICKS_SLAB);
        NibiruBlocks.TERRASTONE_SLAB.setDouble(NibiruBlocks.DOUBLE_TERRASTONE_SLAB);
        NibiruBlocks.INFECTED_SANDSTONE_SLAB.setDouble(NibiruBlocks.DOUBLE_INFECTED_SANDSTONE_SLAB);
        NibiruBlocks.INFECTED_CUT_SANDSTONE_SLAB.setDouble(NibiruBlocks.DOUBLE_INFECTED_CUT_SANDSTONE_SLAB);

        NibiruBlocks.DOUBLE_INFECTED_PRISMARINE_SLAB.setHalf(NibiruBlocks.INFECTED_PRISMARINE_SLAB);
        NibiruBlocks.DOUBLE_INFECTED_PRISMARINE_BRICKS_SLAB.setHalf(NibiruBlocks.INFECTED_PRISMARINE_BRICKS_SLAB);
        NibiruBlocks.DOUBLE_INFECTED_DARK_PRISMARINE_SLAB.setHalf(NibiruBlocks.INFECTED_DARK_PRISMARINE_SLAB);
        NibiruBlocks.DOUBLE_INFECTED_STONE_BRICKS_SLAB.setHalf(NibiruBlocks.INFECTED_STONE_BRICKS_SLAB);
        NibiruBlocks.DOUBLE_INFECTED_VEIN_STONE_BRICKS_SLAB.setHalf(NibiruBlocks.INFECTED_VEIN_STONE_BRICKS_SLAB);
        NibiruBlocks.DOUBLE_INFECTED_CRACKED_STONE_BRICKS_SLAB.setHalf(NibiruBlocks.INFECTED_CRACKED_STONE_BRICKS_SLAB);
        NibiruBlocks.DOUBLE_TERRASTONE_SLAB.setHalf(NibiruBlocks.TERRASTONE_SLAB);
        NibiruBlocks.DOUBLE_INFECTED_SANDSTONE_SLAB.setHalf(NibiruBlocks.INFECTED_SANDSTONE_SLAB);
        NibiruBlocks.DOUBLE_INFECTED_CUT_SANDSTONE_SLAB.setHalf(NibiruBlocks.INFECTED_CUT_SANDSTONE_SLAB);
    }
}