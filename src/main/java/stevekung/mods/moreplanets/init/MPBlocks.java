package stevekung.mods.moreplanets.init;

import net.minecraft.block.Block;
import net.minecraft.block.BlockFire;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.item.EnumDyeColor;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidRegistry;
import stevekung.mods.moreplanets.blocks.*;
import stevekung.mods.moreplanets.blocks.decoration.BlockAllDoubleSlab;
import stevekung.mods.moreplanets.blocks.decoration.BlockAllHalfSlab;
import stevekung.mods.moreplanets.blocks.decoration.BlockAllWall;
import stevekung.mods.moreplanets.core.MorePlanetsMod;
import stevekung.mods.moreplanets.itemblocks.*;
import stevekung.mods.moreplanets.moons.koentus.blocks.*;
import stevekung.mods.moreplanets.moons.koentus.itemblocks.ItemBlockAntiGravity;
import stevekung.mods.moreplanets.planets.chalos.blocks.*;
import stevekung.mods.moreplanets.planets.chalos.blocks.fluid.FluidGaseousCheeseMilk;
import stevekung.mods.moreplanets.planets.diona.blocks.*;
import stevekung.mods.moreplanets.planets.diona.itemblocks.ItemBlockDarkEnergyGenerator;
import stevekung.mods.moreplanets.planets.fronos.blocks.BlockFronosFarmland;
import stevekung.mods.moreplanets.planets.fronos.blocks.BlockFronosGrass;
import stevekung.mods.moreplanets.planets.fronos.blocks.BlockJelly;
import stevekung.mods.moreplanets.planets.nibiru.blocks.*;
import stevekung.mods.moreplanets.planets.nibiru.blocks.fluid.FluidHeliumGas;
import stevekung.mods.moreplanets.planets.nibiru.itemblocks.ItemBlockInfectedSnow;
import stevekung.mods.moreplanets.planets.nibiru.itemblocks.ItemBlockNuclearWasteTank;
import stevekung.mods.moreplanets.planets.nibiru.itemblocks.ItemBlockSporelily;
import stevekung.mods.moreplanets.utils.BlocksItemsRegistry;
import stevekung.mods.moreplanets.utils.CompatibilityManagerMP;
import stevekung.mods.moreplanets.utils.blocks.*;
import stevekung.mods.moreplanets.utils.blocks.BlockStairsMP.EnumStairsType;
import stevekung.mods.moreplanets.utils.blocks.fluid.FluidMP;
import stevekung.mods.moreplanets.utils.itemblocks.*;
import stevekung.mods.stevekunglib.utils.BlockUtils;
import stevekung.mods.stevekunglib.utils.ColorUtils;
import stevekung.mods.stevekunglib.utils.enums.EnumHarvestLevel;

public class MPBlocks
{
    // Dummy
    public static BlockDummy WARP_PAD_DUMMY;
    public static BlockDummy DER_SOLAR1_DUMMY;
    public static BlockDummy DER_SOLAR2_DUMMY;
    public static BlockDummy DER_SOLAR3_DUMMY;
    public static BlockDummy DER_SOLAR4_DUMMY;
    public static BlockDummy NWT_MIDDLE_DUMMY;
    public static BlockDummy NWT_TOP_DUMMY;
    public static BlockDummy SHIELD_GENERATOR_DUMMY;

    // Boss Spawner
    public static Block DIONA_DUNGEON_SPAWNER;
    public static Block CHALOS_DUNGEON_SPAWNER;
    public static Block NIBIRU_DUNGEON_SPAWNER;

    // Slab
    public static BlockAllHalfSlab DIONA_COBBLESTONE_SLAB;
    public static BlockAllHalfSlab CHALOS_COBBLESTONE_SLAB;
    public static BlockAllHalfSlab NIBIRU_COBBLESTONE_SLAB;
    public static BlockAllHalfSlab DIONA_DUNGEON_BRICK_SLAB;
    public static BlockAllHalfSlab CHALOS_DUNGEON_BRICK_SLAB;
    public static BlockAllHalfSlab NIBIRU_DUNGEON_BRICK_SLAB;
    public static BlockAllHalfSlab ALIEN_SHIP_DECORATION_SLAB;
    public static BlockAllHalfSlab INFECTED_PRISMARINE_SLAB;
    public static BlockAllHalfSlab INFECTED_PRISMARINE_BRICK_SLAB;
    public static BlockAllHalfSlab INFECTED_DARK_PRISMARINE_SLAB;
    public static BlockAllHalfSlab INFECTED_STONE_BRICKS_SLAB;
    public static BlockAllHalfSlab INFECTED_VEIN_STONE_BRICKS_SLAB;
    public static BlockAllHalfSlab INFECTED_CRACKED_STONE_BRICKS_SLAB;
    public static BlockAllHalfSlab TERRASTONE_SLAB;
    public static BlockAllHalfSlab INFECTED_SANDSTONE_SLAB;
    public static BlockAllHalfSlab INFECTED_CUT_SANDSTONE_SLAB;

    // Slab Wood
    public static BlockAllHalfSlab CHEESE_SPORE_SLAB;
    public static BlockAllHalfSlab INFECTED_OAK_SLAB;
    public static BlockAllHalfSlab ALIEN_BERRY_OAK_SLAB;

    @Deprecated public static BlockAllHalfSlab DOUBLE_DIONA_COBBLESTONE_SLAB;
    @Deprecated public static BlockAllHalfSlab DOUBLE_CHALOS_COBBLESTONE_SLAB;
    @Deprecated public static BlockAllHalfSlab DOUBLE_NIBIRU_COBBLESTONE_SLAB;
    @Deprecated public static BlockAllHalfSlab DOUBLE_DIONA_DUNGEON_BRICK_SLAB;
    @Deprecated public static BlockAllHalfSlab DOUBLE_CHALOS_DUNGEON_BRICK_SLAB;
    @Deprecated public static BlockAllHalfSlab DOUBLE_NIBIRU_DUNGEON_BRICK_SLAB;
    @Deprecated public static BlockAllHalfSlab DOUBLE_ALIEN_SHIP_DECORATION_SLAB;
    @Deprecated public static BlockAllHalfSlab DOUBLE_INFECTED_PRISMARINE_SLAB;
    @Deprecated public static BlockAllHalfSlab DOUBLE_INFECTED_PRISMARINE_BRICK_SLAB;
    @Deprecated public static BlockAllHalfSlab DOUBLE_INFECTED_DARK_PRISMARINE_SLAB;
    @Deprecated public static BlockAllHalfSlab DOUBLE_INFECTED_STONE_BRICKS_SLAB;
    @Deprecated public static BlockAllHalfSlab DOUBLE_INFECTED_VEIN_STONE_BRICKS_SLAB;
    @Deprecated public static BlockAllHalfSlab DOUBLE_INFECTED_CRACKED_STONE_BRICKS_SLAB;
    @Deprecated public static BlockAllHalfSlab DOUBLE_TERRASTONE_SLAB;
    @Deprecated public static BlockAllHalfSlab DOUBLE_INFECTED_SANDSTONE_SLAB;
    @Deprecated public static BlockAllHalfSlab DOUBLE_INFECTED_CUT_SANDSTONE_SLAB;

    @Deprecated public static BlockAllHalfSlab DOUBLE_CHEESE_SPORE_SLAB;
    @Deprecated public static BlockAllHalfSlab DOUBLE_INFECTED_OAK_SLAB;
    @Deprecated public static BlockAllHalfSlab DOUBLE_ALIEN_BERRY_OAK_SLAB;

    // Pressure Plate
    public static Block INFECTED_OAK_PRESSURE_PLATE;
    public static Block ALIEN_BERRY_OAK_PRESSURE_PLATE;

    // Energy Storage
    public static Block DARK_ENERGY_STORAGE_CLUSTER;
    public static Block NUCLEAR_WASTE_ENERGY_STORAGE_CLUSTER;

    // Machine
    public static Block SPACE_WARP_PAD;
    public static Block SPACE_WARP_PAD_FULL;
    public static Block DARK_ENERGY_RECEIVER;
    public static Block SPACE_PORTAL;
    public static Block BLACK_HOLE_STORAGE;
    public static Block ALIEN_DEFENDER_BEACON;
    public static Block SHIELD_GENERATOR;

    // Alien Ship
    public static Block ALIEN_SHIP_BOOSTER;
    public static Block ALIEN_SHIP_DECORATION_0;
    public static Block ALIEN_SHIP_DECORATION_1;
    public static Block ALIEN_CHEST;
    public static Block ALIEN_SHIP_DECORATION_STAIRS;

    // Tinted Glass
    public static Block TINTED_GLASS;
    public static Block WHITE_TINTED_GLASS;
    public static Block ORANGE_TINTED_GLASS;
    public static Block MAGENTA_TINTED_GLASS;
    public static Block LIGHT_BLUE_TINTED_GLASS;
    public static Block YELLOW_TINTED_GLASS;
    public static Block LIME_TINTED_GLASS;
    public static Block PINK_TINTED_GLASS;
    public static Block GRAY_TINTED_GLASS;
    public static Block SILVER_TINTED_GLASS;
    public static Block CYAN_TINTED_GLASS;
    public static Block PURPLE_TINTED_GLASS;
    public static Block BLUE_TINTED_GLASS;
    public static Block BROWN_TINTED_GLASS;
    public static Block GREEN_TINTED_GLASS;
    public static Block RED_TINTED_GLASS;
    public static Block BLACK_TINTED_GLASS;

    // Tinted Glass Pane
    public static Block TINTED_GLASS_PANE;
    public static Block WHITE_TINTED_GLASS_PANE;
    public static Block ORANGE_TINTED_GLASS_PANE;
    public static Block MAGENTA_TINTED_GLASS_PANE;
    public static Block LIGHT_BLUE_TINTED_GLASS_PANE;
    public static Block YELLOW_TINTED_GLASS_PANE;
    public static Block LIME_TINTED_GLASS_PANE;
    public static Block PINK_TINTED_GLASS_PANE;
    public static Block GRAY_TINTED_GLASS_PANE;
    public static Block SILVER_TINTED_GLASS_PANE;
    public static Block CYAN_TINTED_GLASS_PANE;
    public static Block PURPLE_TINTED_GLASS_PANE;
    public static Block BLUE_TINTED_GLASS_PANE;
    public static Block BROWN_TINTED_GLASS_PANE;
    public static Block GREEN_TINTED_GLASS_PANE;
    public static Block RED_TINTED_GLASS_PANE;
    public static Block BLACK_TINTED_GLASS_PANE;

    // Others
    public static Block DUNGEON_GLOWSTONE;
    public static Block POLISHED_TIN_DECORATION_BLOCK;
    public static Block POLISHED_ALUMINUM_DECORATION_BLOCK;
    public static Block DESH_FRAME;

    // Wall
    public static Block DIONA_COBBLESTONE_WALL;
    public static Block CHALOS_COBBLESTONE_WALL;
    public static Block NIBIRU_COBBLESTONE_WALL;
    public static Block DIONA_DUNGEON_BRICK_WALL;
    public static Block CHALOS_DUNGEON_BRICK_WALL;
    public static Block NIBIRU_DUNGEON_BRICK_WALL;

    //////////////////////// FLUID STUFF ////////////////////////

    public static Fluid FLUID_XP;
    public static Fluid INFECTED_PURLONITE_LAVA_FLUID;
    public static Fluid INFECTED_PURLONITE_WATER_FLUID;
    public static Fluid CHEESE_MILK_FLUID;
    public static Fluid GASEOUS_CHEESE_MILK;
    public static Fluid INFECTED_WATER_FLUID;
    public static Fluid HELIUM_GAS;
    public static Fluid NUCLEAR_WASTE_FLUID;
    public static Fluid PURIFIED_WATER_FLUID;

    //////////////////////// DIONA STUFF ////////////////////////

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

    // Purlonite Segment
    public static Block INFECTED_PURLONITE_SEGMENT;
    public static Block INFECTED_PURLONITE_EYE_CORE;
    public static Block INFECTED_PURLONITE_ENDER_CORE;

    // Others
    public static Block ALBETIUS_WORM_EGG_ROCK;
    public static Block DIONA_ANCIENT_CHEST;
    public static Block DIONA_TREASURE_CHEST;
    public static Block INFECTED_PURLONITE_PLANKS;
    public static Block INFECTED_PURLONITE_FENCE;
    public static Block INFECTED_PURLONITE_COBWEB;
    public static Block INFECTED_PURLONITE_TORCH;
    public static Block ZELIUS_EGG;
    public static Block INFECTED_PURLONITE_CRYSTAL;
    public static Block ALIEN_MINER_BLOOD;
    public static Block INFECTED_PURLONITE_SLIME_BLOCK;
    public static Block DARK_ENERGY_CORE;
    public static Block INFECTED_PURLONITE_WATER_FLUID_BLOCK;
    public static Block INFECTED_PURLONITE_LAVA_FLUID_BLOCK;
    public static Block DIONA_COBBLESTONE_STAIRS;
    public static Block DIONA_DUNGEON_BRICK_STAIRS;
    public static Block DARK_ENERGY_GENERATOR;
    public static Block GLOWING_IRON_BLOCK;
    public static Block CRASHED_ALIEN_PROBE;

    //////////////////////// KOENTUS STUFF ////////////////////////

    // Koentus Block
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

    // Creep Block
    public static Block CREEP_BLOCK;
    public static Block GRAVITY_CREEP_BLOCK;
    public static Block GRAVITY_CREEP_EXTRACTOR;
    public static Block GRAVITY_CREEP_VINES;

    //////////////////////// KOENTUS STUFF ////////////////////////

    // Chalos Block
    public static BlockCobblestoneDrop CHALOS_ROCK;
    public static Block CHALOS_COBBLESTONE;
    public static Block DIREMSIUM_ORE;
    public static Block ZYPTORIUM_ORE;
    public static Block CHEESE_MILK_ORE;
    public static Block CHALOS_IRON_ORE;
    public static Block CHALOS_TIN_ORE;
    public static Block CHALOS_COPPER_ORE;
    public static Block CHALOS_ALUMINUM_ORE;
    public static Block DIREMSIUM_BLOCK;
    public static Block ZYPTORIUM_BLOCK;
    public static Block CHALOS_DUNGEON_BRICK;

    // Others
    public static Block CHEESE_DIRT;
    public static Block CHEESE_COARSE_DIRT;
    public static Block CHEESE_GRASS_BLOCK;
    public static Block CHEESE_FARMLAND;
    public static Block CHEESE_SLIME_BLOCK;
    public static Block CHEESE_MILK_CAKE;
    public static Block CHEESE_SPORE_FLOWER;
    public static Block CHALOS_ANCIENT_CHEST;
    public static Block CHALOS_TREASURE_CHEST;
    public static Block CHEESE_MILK_FLUID_BLOCK;
    public static Block GASEOUS_CHEESE_MILK_BLOCK;
    public static Block CHEESE_SPORE;
    public static Block CHEESE_SPORE_STEM;
    public static Block CHEESE_SPORE_PLANKS;
    public static Block CHEESE_GRASS;
    public static Block CHEESE_SPORE_BERRY;
    public static BlockDoublePlantMP CHEESE_TALL_GRASS;
    public static Block CHEESE_SPORE_CRAFTING_TABLE;
    public static Block CHEESE_SPORE_CHEST;
    public static Block CHALOS_COBBLESTONE_STAIRS;
    public static Block CHALOS_DUNGEON_BRICK_STAIRS;
    public static Block CHEESE_SPORE_STAIRS;
    public static Block CHEESE_SPORE_FENCE;
    public static Block CHEESE_SPORE_FENCE_GATE;
    public static BlockDoorMP CHEESE_SPORE_DOOR;

    //////////////////////// NIBIRU STUFF ////////////////////////

    // Nibiru Block
    public static Block INFECTED_GRASS_BLOCK;
    public static Block INFECTED_DIRT;
    public static Block INFECTED_COARSE_DIRT;
    public static Block INFECTED_PODZOL;
    public static Block INFECTED_GRASS;
    public static Block INFECTED_FERN;
    public static Block GREEN_VEIN_GRASS;

    // Double Plant
    public static BlockDoublePlantMP INFECTED_ORANGE_ROSE_BUSH;
    public static BlockDoublePlantMP INFECTED_TALL_GRASS;
    public static BlockDoublePlantMP INFECTED_LARGE_FERN;
    public static BlockDoublePlantMP GREEN_VEIN_TALL_GRASS;

    // Nibiru Rock
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
    public static Block INFECTED_SANDSTONE;
    public static Block INFECTED_CHISELED_SANDSTONE;
    public static Block INFECTED_CUT_SANDSTONE;

    // Infested Block
    public static Block INFESTED_NIBIRU_ROCK;
    public static Block INFESTED_NIBIRU_COBBLESTONE;
    public static Block INFESTED_NIBIRU_VEIN_COBBLESTONE;
    public static Block INFESTED_INFECTED_STONE_BRICKS;
    public static Block INFESTED_INFECTED_VEIN_STONE_BRICKS;
    public static Block INFESTED_INFECTED_CRACKED_STONE_BRICKS;
    public static Block INFESTED_INFECTED_CHISELED_STONE_BRICKS;

    // Ore Block
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

    // Log Planks Leaves Sapling
    public static Block INFECTED_OAK_LOG;
    public static Block INFECTED_DEADWOOD_LOG;
    public static Block INFECTED_SPRUCE_LOG;
    public static Block INFECTED_JUNGLE_LOG;
    public static Block ALIEN_BERRY_OAK_LOG;

    public static Block INFECTED_OAK_PLANKS;
    public static Block INFECTED_SPRUCE_PLANKS;
    public static Block ALIEN_BERRY_OAK_PLANKS;

    public static Block INFECTED_OAK_LEAVES;
    public static Block INFECTED_SPRUCE_LEAVES;
    public static Block INFECTED_JUNGLE_LEAVES;
    public static Block ALIEN_BERRY_OAK_LEAVES;

    public static Block INFECTED_OAK_SAPLING;
    public static Block INFECTED_SPRUCE_SAPLING;
    public static Block INFECTED_JUNGLE_SAPLING;
    public static Block ALIEN_BERRY_OAK_SAPLING;

    // Flower
    public static Block PURE_HERB;
    public static Block TERRAPUFF_HERB;
    public static Block BATASIA_DANDELION;
    public static Block PYOLONIA;
    public static Block PHILIPY;
    public static Block WHITE_TAIL;
    public static Block VEALIUM_VINES;
    public static Block TERRASHROOM;

    // Others
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
    public static BlockDoorMP INFECTED_OAK_DOOR;
    public static BlockDoorMP ALIEN_BERRY_OAK_DOOR;
    public static Block NIBIRU_COBBLESTONE_STAIRS;
    public static Block INFECTED_STONE_BRICKS_STAIRS;
    public static Block NIBIRU_ANCIENT_CHEST;
    public static Block INFECTED_SANDSTONE_STAIRS;
    public static Block INFECTED_ICE;
    public static Block INFECTED_PACKED_ICE;
    public static Block INFECTED_SNOW;
    public static BlockSnowLayerMP INFECTED_SNOW_LAYER;
    public static Block PURIFIED_SNOW;
    public static BlockSnowLayerMP PURIFIED_SNOW_LAYER;
    public static Block GREEN_VEIN_GRASS_BLOCK;
    public static Block INFECTED_MELON;
    public static Block INFECTED_WHEAT;
    public static Block INFECTED_GRAVEL;
    public static Block INFECTED_CLAY;
    public static BlockFire ELECTRICAL_FIRE;
    public static Block NIBIRU_TREASURE_CHEST;
    public static BlockChestMP INFECTED_CHEST;
    public static Block MULTALIC_CRYSTAL;
    public static Block INFECTED_SUGAR_CANE;
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
    public static Block TERRABERRY;
    public static Block HUGE_TERRASHROOM_BLOCK;
    public static Block SEALABLE_NUCLEAR_WASTE_ROD;

    // Stairs
    public static Block NIBIRU_DUNGEON_BRICK_STAIRS;
    public static Block INFECTED_OAK_STAIRS;
    public static Block ALIEN_BERRY_OAK_STAIRS;
    public static Block INFECTED_PRISMARINE_STAIRS;
    public static Block INFECTED_PRISMARINE_BRICK_STAIRS;
    public static Block INFECTED_DARK_PRISMARINE_STAIRS;
    public static Block INFECTED_VEIN_STONE_BRICKS_STAIRS;
    public static Block CRACKED_INFECTED_STONE_BRICKS_STAIRS;
    public static Block INFECTED_CUT_SANDSTONE_STAIRS;
    public static Block TERRASTONE_STAIRS;

    //////////////////////// FRONOS STUFF ////////////////////////

    // Fronos Block
    public static Block FRONOS_GRASS_BLOCK;
    public static Block FRONOS_DIRT;
    public static Block FRONOS_COARSE_DIRT;
    public static Block FRONOS_FARMLAND;
    public static BlockCobblestoneDrop FRONOS_STONE;
    public static Block FRONOS_COBBLESTONE;
    public static Block FRONOS_STONE_BRICKS;
    public static Block FRONOS_MOSSY_STONE_BRICKS;
    public static Block FRONOS_CRACKED_STONE_BRICKS;
    public static Block FRONOS_CHISELED_STONE_BRICKS;
    public static Block FRONOS_DUNGEON_BRICK;

    // Fronos Ore
    public static Block FRONOS_IRON_ORE;
    public static Block FRONOS_GOLD_ORE;
    public static Block FRONOS_TIN_ORE;
    public static Block FRONOS_COPPER_ORE;
    public static Block FRONOS_ALUMINUM_ORE;
    public static Block FRONOS_LEAD_ORE;
    public static Block FRONOS_COAL_ORE;
    public static Block FRONOS_LAPIS_ORE;
    public static Block FRONOS_DIAMOND_ORE;
    public static Block FRONOS_EMERALD_ORE;
    public static Block FRONOS_REDSTONE_ORE;
    public static Block FRONOS_SILICON_ORE;
    public static Block FRONOS_QUARTZ_ORE;
    public static Block EXTRAILONITE_ORE;

    // Candy Cane
    public static Block RED_CANDY_CANE;
    public static Block GREEN_CANDY_CANE;
    public static Block BLUE_CANDY_CANE;
    public static Block ORANGE_CANDY_CANE;
    public static Block PINK_CANDY_CANE;
    public static Block YELLOW_CANDY_CANE;
    public static Block PURPLE_CANDY_CANE;
    public static Block RAINBOW_CANDY_CANE;

    // Jelly Block
    public static Block GRAPE_JELLY_BLOCK;
    public static Block RASPBERRY_JELLY_BLOCK;
    public static Block STRAWBERRY_JELLY_BLOCK;
    public static Block BERRY_JELLY_BLOCK;
    public static Block LIME_JELLY_BLOCK;
    public static Block ORANGE_JELLY_BLOCK;
    public static Block GREEN_JELLY_BLOCK;
    public static Block LEMON_JELLY_BLOCK;

    public static void init()
    {
        // Dummy
        MPBlocks.WARP_PAD_DUMMY = new BlockDummy("warp_pad_dummy", BlockDummy.BlockType.WARP_PAD);
        MPBlocks.DER_SOLAR1_DUMMY = new BlockDummy("der_solar1_dummy", BlockDummy.BlockType.DARK_ENERGY_SOLAR1);
        MPBlocks.DER_SOLAR2_DUMMY = new BlockDummy("der_solar2_dummy", BlockDummy.BlockType.DARK_ENERGY_SOLAR2);
        MPBlocks.DER_SOLAR3_DUMMY = new BlockDummy("der_solar3_dummy", BlockDummy.BlockType.DARK_ENERGY_SOLAR3);
        MPBlocks.DER_SOLAR4_DUMMY = new BlockDummy("der_solar4_dummy", BlockDummy.BlockType.DARK_ENERGY_SOLAR4);
        MPBlocks.NWT_MIDDLE_DUMMY = new BlockDummy("nwt_middle_dummy", BlockDummy.BlockType.NUCLEAR_WASTE_TANK_MIDDLE);
        MPBlocks.NWT_TOP_DUMMY = new BlockDummy("nwt_top_dummy", BlockDummy.BlockType.NUCLEAR_WASTE_TANK_TOP);
        MPBlocks.SHIELD_GENERATOR_DUMMY = new BlockDummy("shield_generator_dummy", BlockDummy.BlockType.SHIELD_GENERATOR_TOP);

        // Boss Spawner
        MPBlocks.DIONA_DUNGEON_SPAWNER = new BlockSpaceDungeonSpawner("diona_dungeon_spawner", BlockSpaceDungeonSpawner.DungeonType.DIONA);
        MPBlocks.CHALOS_DUNGEON_SPAWNER = new BlockSpaceDungeonSpawner("chalos_dungeon_spawner", BlockSpaceDungeonSpawner.DungeonType.CHALOS);
        MPBlocks.NIBIRU_DUNGEON_SPAWNER = new BlockSpaceDungeonSpawner("nibiru_dungeon_spawner", BlockSpaceDungeonSpawner.DungeonType.NIBIRU);

        // Slab
        MPBlocks.DIONA_COBBLESTONE_SLAB = new BlockAllHalfSlab("diona_cobblestone_slab", BlockAllHalfSlab.BlockType.DIONA_COBBLESTONE_SLAB);
        MPBlocks.CHALOS_COBBLESTONE_SLAB = new BlockAllHalfSlab("chalos_cobblestone_slab", BlockAllHalfSlab.BlockType.CHALOS_COBBLESTONE_SLAB);
        MPBlocks.NIBIRU_COBBLESTONE_SLAB = new BlockAllHalfSlab("nibiru_cobblestone_slab", BlockAllHalfSlab.BlockType.NIBIRU_COBBLESTONE_SLAB);
        MPBlocks.DIONA_DUNGEON_BRICK_SLAB = new BlockAllHalfSlab("diona_dungeon_brick_slab", BlockAllHalfSlab.BlockType.DIONA_DUNGEON_BRICK_SLAB);
        MPBlocks.CHALOS_DUNGEON_BRICK_SLAB = new BlockAllHalfSlab("chalos_dungeon_brick_slab", BlockAllHalfSlab.BlockType.CHALOS_DUNGEON_BRICK_SLAB);
        MPBlocks.NIBIRU_DUNGEON_BRICK_SLAB = new BlockAllHalfSlab("nibiru_dungeon_brick_slab", BlockAllHalfSlab.BlockType.NIBIRU_DUNGEON_BRICK_SLAB);
        MPBlocks.ALIEN_SHIP_DECORATION_SLAB = new BlockAllHalfSlab("alien_ship_decoration_slab", BlockAllHalfSlab.BlockType.ALIEN_SHIP_SLAB).setSoundType(SoundType.METAL);

        // Slab Wood
        MPBlocks.CHEESE_SPORE_SLAB = new BlockAllHalfSlab("cheese_spore_slab", BlockAllHalfSlab.BlockType.CHEESE_SPORE_SLAB, Material.WOOD);
        MPBlocks.INFECTED_OAK_SLAB = new BlockAllHalfSlab("infected_oak_slab", BlockAllHalfSlab.BlockType.INFECTED_OAK_SLAB, Material.WOOD);
        MPBlocks.ALIEN_BERRY_OAK_SLAB = new BlockAllHalfSlab("alien_berry_oak_slab", BlockAllHalfSlab.BlockType.ALIEN_BERRY_OAK_SLAB, Material.WOOD);

        MPBlocks.DOUBLE_DIONA_COBBLESTONE_SLAB = new BlockAllDoubleSlab("double_diona_cobblestone_slab", BlockAllHalfSlab.BlockType.DIONA_COBBLESTONE_SLAB);
        MPBlocks.DOUBLE_CHALOS_COBBLESTONE_SLAB = new BlockAllDoubleSlab("double_chalos_cobblestone_slab", BlockAllHalfSlab.BlockType.CHALOS_COBBLESTONE_SLAB);
        MPBlocks.DOUBLE_NIBIRU_COBBLESTONE_SLAB = new BlockAllDoubleSlab("double_nibiru_cobblestone_slab", BlockAllHalfSlab.BlockType.NIBIRU_COBBLESTONE_SLAB);
        MPBlocks.DOUBLE_DIONA_DUNGEON_BRICK_SLAB = new BlockAllDoubleSlab("double_diona_dungeon_brick_slab", BlockAllHalfSlab.BlockType.DIONA_DUNGEON_BRICK_SLAB);
        MPBlocks.DOUBLE_CHALOS_DUNGEON_BRICK_SLAB = new BlockAllDoubleSlab("double_chalos_dungeon_brick_slab", BlockAllHalfSlab.BlockType.CHALOS_DUNGEON_BRICK_SLAB);
        MPBlocks.DOUBLE_NIBIRU_DUNGEON_BRICK_SLAB = new BlockAllDoubleSlab("double_nibiru_dungeon_brick_slab", BlockAllHalfSlab.BlockType.NIBIRU_DUNGEON_BRICK_SLAB);
        MPBlocks.DOUBLE_ALIEN_SHIP_DECORATION_SLAB = new BlockAllDoubleSlab("double_alien_ship_decoration_slab", BlockAllHalfSlab.BlockType.ALIEN_SHIP_SLAB).setSoundType(SoundType.METAL);

        MPBlocks.DOUBLE_CHEESE_SPORE_SLAB = new BlockAllDoubleSlab("double_cheese_spore_slab", BlockAllHalfSlab.BlockType.CHEESE_SPORE_SLAB, Material.WOOD);
        MPBlocks.DOUBLE_INFECTED_OAK_SLAB = new BlockAllDoubleSlab("double_infected_oak_slab", BlockAllHalfSlab.BlockType.INFECTED_OAK_SLAB, Material.WOOD);
        MPBlocks.DOUBLE_ALIEN_BERRY_OAK_SLAB = new BlockAllDoubleSlab("double_alien_berry_oak_slab", BlockAllHalfSlab.BlockType.ALIEN_BERRY_OAK_SLAB, Material.WOOD);

        // Pressure Plate
        MPBlocks.INFECTED_OAK_PRESSURE_PLATE = new BlockPressurePlateMP("infected_oak_pressure_plate");
        MPBlocks.ALIEN_BERRY_OAK_PRESSURE_PLATE = new BlockPressurePlateMP("alien_berry_oak_pressure_plate");

        // Energy Storage
        MPBlocks.DARK_ENERGY_STORAGE_CLUSTER = new BlockTieredEnergyStorageCluster("dark_energy_storage_cluster", BlockTieredEnergyStorageCluster.BlockType.DARK_ENERGY_STORAGE_CLUSTER);
        MPBlocks.NUCLEAR_WASTE_ENERGY_STORAGE_CLUSTER = new BlockTieredEnergyStorageCluster("nuclear_waste_energy_storage_cluster", BlockTieredEnergyStorageCluster.BlockType.NUCLEAR_WASTE_ENERGY_STORAGE_CLUSTER);

        // Machine
        MPBlocks.SPACE_WARP_PAD = new BlockSpaceWarpPad("space_warp_pad");
        MPBlocks.SPACE_WARP_PAD_FULL = new BlockSpaceWarpPadFull("space_warp_pad_full");
        MPBlocks.DARK_ENERGY_RECEIVER = new BlockDarkEnergyReceiver("dark_energy_receiver");
        MPBlocks.SPACE_PORTAL = new BlockSpacePortal("space_portal");
        MPBlocks.BLACK_HOLE_STORAGE = new BlockBlackHoleStorage("black_hole_storage");
        MPBlocks.ALIEN_DEFENDER_BEACON = new BlockAlienDefenderBeacon("alien_defender_beacon");
        MPBlocks.SHIELD_GENERATOR = new BlockShieldGenerator("shield_generator");

        // Alien Ship
        MPBlocks.ALIEN_SHIP_BOOSTER = new BlockCTMGlowingDirectional("alien_ship_booster", Material.IRON).setRarityRGB(ColorUtils.stringToRGB(IItemRarity.ALIEN)).setSortCategory(EnumSortCategoryBlock.DECORATION_BLOCK).setSoundType(SoundType.METAL).setHardness(2.0F);
        MPBlocks.ALIEN_SHIP_DECORATION_0 = new BlockBaseMP("alien_ship_decoration_0", Material.IRON).setRarityRGB(ColorUtils.stringToRGB(IItemRarity.ALIEN)).setSortCategory(EnumSortCategoryBlock.DECORATION_BLOCK).setSoundType(SoundType.METAL).setHardness(2.0F);
        MPBlocks.ALIEN_SHIP_DECORATION_1 = new BlockBaseMP("alien_ship_decoration_1", Material.IRON).setRarityRGB(ColorUtils.stringToRGB(IItemRarity.ALIEN)).setSortCategory(EnumSortCategoryBlock.DECORATION_BLOCK).setSoundType(SoundType.METAL).setHardness(2.0F);
        MPBlocks.ALIEN_CHEST = new BlockAlienChest("alien_chest");
        MPBlocks.ALIEN_SHIP_DECORATION_STAIRS = new BlockStairsMP("alien_ship_decoration_stairs", EnumStairsType.ALIEN_SHIP);

        // Tinted Glass
        MPBlocks.TINTED_GLASS = new BlockTintedGlass("tinted_glass", EnumDyeColor.WHITE);
        MPBlocks.WHITE_TINTED_GLASS = new BlockTintedGlass("white_tinted_glass", EnumDyeColor.WHITE);
        MPBlocks.ORANGE_TINTED_GLASS = new BlockTintedGlass("orange_tinted_glass", EnumDyeColor.ORANGE);
        MPBlocks.MAGENTA_TINTED_GLASS = new BlockTintedGlass("magenta_tinted_glass", EnumDyeColor.MAGENTA);
        MPBlocks.LIGHT_BLUE_TINTED_GLASS = new BlockTintedGlass("light_blue_tinted_glass", EnumDyeColor.LIGHT_BLUE);
        MPBlocks.YELLOW_TINTED_GLASS = new BlockTintedGlass("yellow_tinted_glass", EnumDyeColor.YELLOW);
        MPBlocks.LIME_TINTED_GLASS = new BlockTintedGlass("lime_tinted_glass", EnumDyeColor.LIME);
        MPBlocks.PINK_TINTED_GLASS = new BlockTintedGlass("pink_tinted_glass", EnumDyeColor.PINK);
        MPBlocks.GRAY_TINTED_GLASS = new BlockTintedGlass("gray_tinted_glass", EnumDyeColor.GRAY);
        MPBlocks.SILVER_TINTED_GLASS = new BlockTintedGlass("silver_tinted_glass", EnumDyeColor.SILVER);
        MPBlocks.CYAN_TINTED_GLASS = new BlockTintedGlass("cyan_tinted_glass", EnumDyeColor.CYAN);
        MPBlocks.PURPLE_TINTED_GLASS = new BlockTintedGlass("purple_tinted_glass", EnumDyeColor.PURPLE);
        MPBlocks.BLUE_TINTED_GLASS = new BlockTintedGlass("blue_tinted_glass", EnumDyeColor.BLUE);
        MPBlocks.BROWN_TINTED_GLASS = new BlockTintedGlass("brown_tinted_glass", EnumDyeColor.BROWN);
        MPBlocks.GREEN_TINTED_GLASS = new BlockTintedGlass("green_tinted_glass", EnumDyeColor.GREEN);
        MPBlocks.RED_TINTED_GLASS = new BlockTintedGlass("red_tinted_glass", EnumDyeColor.RED);
        MPBlocks.BLACK_TINTED_GLASS = new BlockTintedGlass("black_tinted_glass", EnumDyeColor.BLACK);

        // Tinted Glass Pane
        MPBlocks.TINTED_GLASS_PANE = new BlockTintedGlassPane("tinted_glass_pane", EnumDyeColor.WHITE);
        MPBlocks.WHITE_TINTED_GLASS_PANE = new BlockTintedGlassPane("white_tinted_glass_pane", EnumDyeColor.WHITE);
        MPBlocks.ORANGE_TINTED_GLASS_PANE = new BlockTintedGlassPane("orange_tinted_glass_pane", EnumDyeColor.ORANGE);
        MPBlocks.MAGENTA_TINTED_GLASS_PANE = new BlockTintedGlassPane("magenta_tinted_glass_pane", EnumDyeColor.MAGENTA);
        MPBlocks.LIGHT_BLUE_TINTED_GLASS_PANE = new BlockTintedGlassPane("light_blue_tinted_glass_pane", EnumDyeColor.LIGHT_BLUE);
        MPBlocks.YELLOW_TINTED_GLASS_PANE = new BlockTintedGlassPane("yellow_tinted_glass_pane", EnumDyeColor.YELLOW);
        MPBlocks.LIME_TINTED_GLASS_PANE = new BlockTintedGlassPane("lime_tinted_glass_pane", EnumDyeColor.LIME);
        MPBlocks.PINK_TINTED_GLASS_PANE = new BlockTintedGlassPane("pink_tinted_glass_pane", EnumDyeColor.PINK);
        MPBlocks.GRAY_TINTED_GLASS_PANE = new BlockTintedGlassPane("gray_tinted_glass_pane", EnumDyeColor.GRAY);
        MPBlocks.SILVER_TINTED_GLASS_PANE = new BlockTintedGlassPane("silver_tinted_glass_pane", EnumDyeColor.SILVER);
        MPBlocks.CYAN_TINTED_GLASS_PANE = new BlockTintedGlassPane("cyan_tinted_glass_pane", EnumDyeColor.CYAN);
        MPBlocks.PURPLE_TINTED_GLASS_PANE = new BlockTintedGlassPane("purple_tinted_glass_pane", EnumDyeColor.PURPLE);
        MPBlocks.BLUE_TINTED_GLASS_PANE = new BlockTintedGlassPane("blue_tinted_glass_pane", EnumDyeColor.BLUE);
        MPBlocks.BROWN_TINTED_GLASS_PANE = new BlockTintedGlassPane("brown_tinted_glass_pane", EnumDyeColor.BROWN);
        MPBlocks.GREEN_TINTED_GLASS_PANE = new BlockTintedGlassPane("green_tinted_glass_pane", EnumDyeColor.GREEN);
        MPBlocks.RED_TINTED_GLASS_PANE = new BlockTintedGlassPane("red_tinted_glass_pane", EnumDyeColor.RED);
        MPBlocks.BLACK_TINTED_GLASS_PANE = new BlockTintedGlassPane("black_tinted_glass_pane", EnumDyeColor.BLACK);

        // Others
        MPBlocks.POLISHED_TIN_DECORATION_BLOCK = new BlockBaseMP("polished_tin_decoration_block", Material.ROCK).setHardness(1.5F);
        MPBlocks.POLISHED_ALUMINUM_DECORATION_BLOCK = new BlockBaseMP("polished_aluminum_decoration_block", Material.ROCK).setHardness(1.5F);
        MPBlocks.DESH_FRAME = new BlockDeshFrame("desh_frame");
        MPBlocks.DUNGEON_GLOWSTONE = new BlockBaseMP("dungeon_glowstone", Material.GLASS).setRarityRGB(ColorUtils.stringToRGB(IItemRarity.COMMON)).setSoundType(SoundType.GLASS).setResistance(100.0F).setHardness(0.3F).setLightLevel(1.0F);

        // Wall
        MPBlocks.DIONA_COBBLESTONE_WALL = new BlockAllWall("diona_cobblestone_wall", BlockAllWall.BlockType.DIONA_COBBLESTONE_WALL);
        MPBlocks.CHALOS_COBBLESTONE_WALL = new BlockAllWall("chalos_cobblestone_wall", BlockAllWall.BlockType.CHALOS_COBBLESTONE_WALL);
        MPBlocks.NIBIRU_COBBLESTONE_WALL = new BlockAllWall("nibiru_cobblestone_wall", BlockAllWall.BlockType.NIBIRU_COBBLESTONE_WALL);
        MPBlocks.DIONA_DUNGEON_BRICK_WALL = new BlockAllWall("diona_dungeon_brick_wall", BlockAllWall.BlockType.DIONA_DUNGEON_BRICK_WALL);
        MPBlocks.CHALOS_DUNGEON_BRICK_WALL = new BlockAllWall("chalos_dungeon_brick_wall", BlockAllWall.BlockType.CHALOS_DUNGEON_BRICK_WALL);
        MPBlocks.NIBIRU_DUNGEON_BRICK_WALL = new BlockAllWall("nibiru_dungeon_brick_wall", BlockAllWall.BlockType.NIBIRU_DUNGEON_BRICK_WALL);

        //////////////////////// DIONA STUFF ////////////////////////

        // Diona Block
        MPBlocks.DIONA_SURFACE_ROCK = new BlockTerraformable("diona_surface_rock").setHardness(1.25F);
        MPBlocks.DIONA_SUB_SURFACE_ROCK = new BlockTerraformable("diona_sub_surface_rock").setHardness(1.25F);
        MPBlocks.DIONA_ROCK = new BlockCobblestoneDrop("diona_rock").setHardness(1.5F);
        MPBlocks.DIONA_COBBLESTONE = new BlockBaseMP("diona_cobblestone", Material.ROCK).setHardness(2.0F);
        MPBlocks.SETRORIUM_ORE = new BlockDropableOre("setrorium_ore", BlockDropableOre.BlockType.SETRORIUM_ORE).setHardness(3.0F);
        MPBlocks.ILLENIUM_ORE = new BlockMineableOre("illenium_ore").setHardness(3.0F);
        MPBlocks.DIONA_TIN_ORE = new BlockMineableOre("diona_tin_ore").setHardness(3.0F);
        MPBlocks.DIONA_COPPER_ORE = new BlockMineableOre("diona_copper_ore").setHardness(3.0F);
        MPBlocks.DIONA_ALUMINUM_ORE = new BlockMineableOre("diona_aluminum_ore").setHardness(3.0F);
        MPBlocks.SETRORIUM_BLOCK = new BlockCompressedMetal("setrorium_block").setSoundType(SoundType.METAL).setHardness(5.0F);
        MPBlocks.ILLENIUM_BLOCK = new BlockCompressedMetal("illenium_block").setSoundType(SoundType.METAL).setHardness(5.0F);
        MPBlocks.DIONA_DUNGEON_BRICK = new BlockBaseMP("diona_dungeon_brick", Material.ROCK).setSortCategory(EnumSortCategoryBlock.DUNGEON_BRICK).setHardness(4.0F).setResistance(40.0F);

        // Purlonite Segment
        MPBlocks.INFECTED_PURLONITE_SEGMENT = new BlockInfectedPurloniteSegment("infected_purlonite_segment", BlockInfectedPurloniteSegment.BlockType.INFECTED_PURLONITE_SEGMENT);
        MPBlocks.INFECTED_PURLONITE_EYE_CORE = new BlockInfectedPurloniteSegment("infected_purlonite_eye_core", BlockInfectedPurloniteSegment.BlockType.INFECTED_PURLONITE_EYE_CORE);
        MPBlocks.INFECTED_PURLONITE_ENDER_CORE = new BlockInfectedPurloniteSegment("infected_purlonite_ender_core", BlockInfectedPurloniteSegment.BlockType.INFECTED_PURLONITE_ENDER_CORE);

        // Others
        MPBlocks.ALBETIUS_WORM_EGG_ROCK = new BlockAlbetiusWormEgg("albetius_worm_egg_rock").setRarityRGB(ColorUtils.stringToRGB(IItemRarity.ALIEN));
        MPBlocks.DIONA_ANCIENT_CHEST = new BlockDionaAncientChest("diona_ancient_chest");
        MPBlocks.DIONA_TREASURE_CHEST = new BlockDionaTreasureChest("diona_treasure_chest");
        MPBlocks.INFECTED_PURLONITE_PLANKS = new BlockCTMGlowing("infected_purlonite_planks", Material.WOOD).setRarityRGB(ColorUtils.stringToRGB(IItemRarity.ALIEN)).setSoundType(SoundType.WOOD).setHardness(2.0F).setResistance(5.0F);
        MPBlocks.INFECTED_PURLONITE_FENCE = new BlockFenceMP("infected_purlonite_fence");
        MPBlocks.INFECTED_PURLONITE_COBWEB = new BlockInfectedPurloniteCobweb("infected_purlonite_cobweb");
        MPBlocks.INFECTED_PURLONITE_TORCH = new BlockInfectedPurloniteTorch("infected_purlonite_torch");
        MPBlocks.ZELIUS_EGG = new BlockZeliusEgg("zelius_egg");
        MPBlocks.INFECTED_PURLONITE_CRYSTAL = new BlockInfectedPurloniteCrystal("infected_purlonite_crystal");
        MPBlocks.ALIEN_MINER_BLOOD = new BlockAlienMinerBlood("alien_miner_blood");
        MPBlocks.INFECTED_PURLONITE_SLIME_BLOCK = new BlockInfectedPurloniteSlime("infected_purlonite_slime_block");
        MPBlocks.DARK_ENERGY_CORE = new BlockDarkEnergyCore("dark_energy_core");
        MPBlocks.DIONA_COBBLESTONE_STAIRS = new BlockStairsMP("diona_cobblestone_stairs", EnumStairsType.COBBLESTONE);
        MPBlocks.DIONA_DUNGEON_BRICK_STAIRS = new BlockStairsMP("diona_dungeon_brick_stairs", EnumStairsType.DUNGEON_BRICK).setSortCategory(EnumSortCategoryBlock.STAIRS_DUNGEON_BRICK);
        MPBlocks.DARK_ENERGY_GENERATOR = new BlockDarkEnergyGenerator("dark_energy_generator");
        MPBlocks.GLOWING_IRON_BLOCK = new BlockGlowingIronBlock("glowing_iron_block");
        MPBlocks.CRASHED_ALIEN_PROBE = new BlockCrashedAlienProbe("crashed_alien_probe");

        //////////////////////// KOENTUS STUFF ////////////////////////

        // Koentus Block
        MPBlocks.KOENTUS_REGOLITH = new BlockTerraformable("koentus_regolith").setHardness(1.25F);
        MPBlocks.KOENTUS_FINE_REGOLITH = new BlockTerraformable("koentus_fine_regolith").setHardness(1.25F);
        MPBlocks.KOENTUS_ROCK = new BlockCobblestoneDrop("koentus_rock").setHardness(1.5F);
        MPBlocks.KOENTUS_COBBLESTONE = new BlockBaseMP("koentus_cobblestone", Material.ROCK).setHardness(2.0F);
        MPBlocks.ANTI_GRAVITY_ORE = new BlockAntiGravityOre("anti_gravity_ore");
        MPBlocks.GOLDENITE_CRYSTALS_ORE = new BlockDropableOre("goldenite_crystals_ore", BlockDropableOre.BlockType.GOLDENITE_CRYSTALS_ORE).setHardness(3.0F);
        MPBlocks.KOENTUS_TIN_ORE = new BlockMineableOre("koentus_tin_ore").setHardness(3.0F);
        MPBlocks.KOENTUS_COPPER_ORE = new BlockMineableOre("koentus_copper_ore").setHardness(3.0F);
        MPBlocks.KOENTUS_ALUMINUM_ORE = new BlockMineableOre("koentus_aluminum_ore").setHardness(3.0F);
        MPBlocks.KOENTUS_IRON_ORE = new BlockMineableOre("koentus_iron_ore").setHardness(3.0F);
        MPBlocks.ANTI_GRAVITY_FRAGMENTS_BLOCK = new BlockCompressedMetal("anti_gravity_fragments_block").setSoundType(SoundType.METAL).setHardness(5.0F);
        MPBlocks.GOLDENITE_CRYSTALS_BLOCK = new BlockCompressedMetal("goldenite_crystals_block").setSoundType(SoundType.METAL).setHardness(5.0F);
        MPBlocks.KOENTUS_DUNGEON_BRICK = new BlockBaseMP("koentus_dungeon_brick", Material.ROCK).setSortCategory(EnumSortCategoryBlock.DUNGEON_BRICK).setHardness(4.0F).setResistance(40.0F);
        MPBlocks.FALLEN_KOENTUS_METEOR = new BlockFallenKoentusMeteor("fallen_koentus_meteor");
        MPBlocks.KOENTUS_ICE = new BlockKoentusIce("koentus_ice");

        // Glowing Hardened Ice
        MPBlocks.WHITE_GLOWING_HARDENED_ICE = new BlockGlowingHardenedIce("white_glowing_hardened_ice", EnumDyeColor.WHITE);
        MPBlocks.ORANGE_GLOWING_HARDENED_ICE = new BlockGlowingHardenedIce("orange_glowing_hardened_ice", EnumDyeColor.ORANGE);
        MPBlocks.MAGENTA_GLOWING_HARDENED_ICE = new BlockGlowingHardenedIce("magenta_glowing_hardened_ice", EnumDyeColor.MAGENTA);
        MPBlocks.LIGHT_BLUE_GLOWING_HARDENED_ICE = new BlockGlowingHardenedIce("light_blue_glowing_hardened_ice", EnumDyeColor.LIGHT_BLUE);
        MPBlocks.YELLOW_GLOWING_HARDENED_ICE = new BlockGlowingHardenedIce("yellow_glowing_hardened_ice", EnumDyeColor.YELLOW);
        MPBlocks.LIME_GLOWING_HARDENED_ICE = new BlockGlowingHardenedIce("lime_glowing_hardened_ice", EnumDyeColor.LIME);
        MPBlocks.PINK_GLOWING_HARDENED_ICE = new BlockGlowingHardenedIce("pink_glowing_hardened_ice", EnumDyeColor.PINK);
        MPBlocks.GRAY_GLOWING_HARDENED_ICE = new BlockGlowingHardenedIce("gray_glowing_hardened_ice", EnumDyeColor.GRAY);
        MPBlocks.SILVER_GLOWING_HARDENED_ICE = new BlockGlowingHardenedIce("silver_glowing_hardened_ice", EnumDyeColor.SILVER);
        MPBlocks.CYAN_GLOWING_HARDENED_ICE = new BlockGlowingHardenedIce("cyan_glowing_hardened_ice", EnumDyeColor.CYAN);
        MPBlocks.PURPLE_GLOWING_HARDENED_ICE = new BlockGlowingHardenedIce("purple_glowing_hardened_ice", EnumDyeColor.PURPLE);
        MPBlocks.BLUE_GLOWING_HARDENED_ICE = new BlockGlowingHardenedIce("blue_glowing_hardened_ice", EnumDyeColor.BLUE);
        MPBlocks.BROWN_GLOWING_HARDENED_ICE = new BlockGlowingHardenedIce("brown_glowing_hardened_ice", EnumDyeColor.BROWN);
        MPBlocks.GREEN_GLOWING_HARDENED_ICE = new BlockGlowingHardenedIce("green_glowing_hardened_ice", EnumDyeColor.GREEN);
        MPBlocks.RED_GLOWING_HARDENED_ICE = new BlockGlowingHardenedIce("red_glowing_hardened_ice", EnumDyeColor.RED);
        MPBlocks.BLACK_GLOWING_HARDENED_ICE = new BlockGlowingHardenedIce("black_glowing_hardened_ice", EnumDyeColor.BLACK);

        // Creep Block
        MPBlocks.CREEP_BLOCK = new BlockCreep("creep_block", BlockCreep.BlockType.CREEP_BLOCK);
        MPBlocks.GRAVITY_CREEP_BLOCK = new BlockCreep("gravity_creep_block", BlockCreep.BlockType.GRAVITY_CREEP_BLOCK);
        MPBlocks.GRAVITY_CREEP_EXTRACTOR = new BlockCreep("gravity_creep_extractor", BlockCreep.BlockType.GRAVITY_CREEP_EXTRACTOR);
        MPBlocks.GRAVITY_CREEP_VINES = new BlockPlaceableBushMP("gravity_creep_vines", BlockPlaceableBushMP.BlockType.CREEP_VINES);

        //////////////////////// CHALOS STUFF ////////////////////////

        // Chalos Block
        MPBlocks.CHALOS_ROCK = new BlockCobblestoneDrop("chalos_rock").setHardness(1.5F);
        MPBlocks.CHALOS_COBBLESTONE = new BlockBaseMP("chalos_cobblestone", Material.ROCK).setHardness(2.0F);
        MPBlocks.DIREMSIUM_ORE = new BlockMineableOre("diremsium_ore").setHardness(3.0F);
        MPBlocks.ZYPTORIUM_ORE = new BlockMineableOre("zyptorium_ore").setHardness(3.0F);
        MPBlocks.CHEESE_MILK_ORE = new BlockDropableOre("cheese_milk_ore", BlockDropableOre.BlockType.CHEESE_MILK_ORE);
        MPBlocks.CHALOS_IRON_ORE = new BlockMineableOre("chalos_iron_ore").setHardness(3.0F);
        MPBlocks.CHALOS_TIN_ORE = new BlockMineableOre("chalos_tin_ore").setHardness(3.0F);
        MPBlocks.CHALOS_COPPER_ORE = new BlockMineableOre("chalos_copper_ore").setHardness(3.0F);
        MPBlocks.CHALOS_ALUMINUM_ORE = new BlockMineableOre("chalos_aluminum_ore").setHardness(3.0F);
        MPBlocks.DIREMSIUM_BLOCK = new BlockCompressedMetal("diremsium_block").setSoundType(SoundType.METAL).setHardness(5.0F);
        MPBlocks.ZYPTORIUM_BLOCK = new BlockCompressedMetal("zyptorium_block").setSoundType(SoundType.METAL).setHardness(5.0F);
        MPBlocks.CHALOS_DUNGEON_BRICK = new BlockBaseMP("chalos_dungeon_brick", Material.ROCK).setSortCategory(EnumSortCategoryBlock.DUNGEON_BRICK).setHardness(4.0F).setResistance(40.0F);

        // Others
        MPBlocks.CHEESE_DIRT = new BlockTerraformable("cheese_dirt", Material.GROUND).setSoundType(SoundType.GROUND).setHardness(0.55F);
        MPBlocks.CHEESE_COARSE_DIRT = new BlockTerraformable("cheese_coarse_dirt", Material.GROUND).setSoundType(SoundType.GROUND).setHardness(0.55F);
        MPBlocks.CHEESE_GRASS_BLOCK = new BlockCheeseGrassBlock("cheese_grass_block");
        MPBlocks.CHEESE_FARMLAND = new BlockCheeseFarmland("cheese_farmland");
        MPBlocks.CHEESE_SLIME_BLOCK = new BlockCheeseSlime("cheese_slime_block");
        MPBlocks.CHEESE_MILK_CAKE = new BlockCheeseMilkCake("cheese_milk_cake");
        MPBlocks.CHEESE_SPORE_FLOWER = new BlockSaplingMP("cheese_spore_flower", BlockSaplingMP.BlockType.CHEESE_SPORE_FLOWER);
        MPBlocks.CHALOS_ANCIENT_CHEST = new BlockChalosAncientChest("chalos_ancient_chest");
        MPBlocks.CHALOS_TREASURE_CHEST = new BlockChalosTreasureChest("chalos_treasure_chest");
        MPBlocks.CHEESE_SPORE = new BlockCheeseSpore("cheese_spore_block");
        MPBlocks.CHEESE_SPORE_STEM = new BlockLogMP("cheese_spore_stem");
        MPBlocks.CHEESE_SPORE_PLANKS = new BlockBaseMP("cheese_spore_planks", Material.WOOD).setSoundType(SoundType.WOOD).setHardness(2.0F).setResistance(5.0F);
        MPBlocks.CHEESE_GRASS = new BlockPlaceableBushMP("cheese_grass", BlockPlaceableBushMP.BlockType.CHEESE_GRASS);
        MPBlocks.CHEESE_SPORE_BERRY = new BlockCheeseSporeBerry("cheese_spore_berry");
        MPBlocks.CHEESE_TALL_GRASS = new BlockDoublePlantMP("cheese_tall_grass", BlockDoublePlantMP.BlockType.CHEESE_TALL_GRASS);
        MPBlocks.CHEESE_SPORE_CRAFTING_TABLE = new BlockCraftingTableMP("cheese_spore_crafting_table");
        MPBlocks.CHEESE_SPORE_CHEST = new BlockCheeseSporeChest("cheese_spore_chest");
        MPBlocks.CHALOS_COBBLESTONE_STAIRS = new BlockStairsMP("chalos_cobblestone_stairs", EnumStairsType.COBBLESTONE);
        MPBlocks.CHALOS_DUNGEON_BRICK_STAIRS = new BlockStairsMP("chalos_dungeon_brick_stairs", EnumStairsType.DUNGEON_BRICK).setSortCategory(EnumSortCategoryBlock.STAIRS_DUNGEON_BRICK);
        MPBlocks.CHEESE_SPORE_STAIRS = new BlockStairsMP("cheese_spore_stairs", EnumStairsType.WOODEN).setSortCategory(EnumSortCategoryBlock.STAIRS_WOODEN);
        MPBlocks.CHEESE_SPORE_FENCE = new BlockFenceMP("cheese_spore_fence");
        MPBlocks.CHEESE_SPORE_FENCE_GATE = new BlockFenceGateMP("cheese_spore_fence_gate");
        MPBlocks.CHEESE_SPORE_DOOR = new BlockDoorMP("cheese_spore_door");

        //////////////////////// NIBIRU STUFF ////////////////////////

        // Nibiru Block
        MPBlocks.INFECTED_GRASS_BLOCK = new BlockInfectedGrassBlock("infected_grass_block");
        MPBlocks.INFECTED_DIRT = new BlockTerraformable("infected_dirt", Material.GROUND).setSoundType(SoundType.GROUND).setHardness(0.55F);
        MPBlocks.INFECTED_COARSE_DIRT = new BlockTerraformable("infected_coarse_dirt", Material.GROUND).setSoundType(SoundType.GROUND).setHardness(0.55F);
        MPBlocks.INFECTED_PODZOL = new BlockInfectedPodzol("infected_podzol");
        MPBlocks.INFECTED_GRASS = new BlockPlaceableBushMP("infected_grass", BlockPlaceableBushMP.BlockType.INFECTED_GRASS);
        MPBlocks.INFECTED_FERN = new BlockPlaceableBushMP("infected_fern", BlockPlaceableBushMP.BlockType.INFECTED_FERN);
        MPBlocks.GREEN_VEIN_GRASS = new BlockPlaceableBushMP("green_vein_grass", BlockPlaceableBushMP.BlockType.GREEN_VEIN_GRASS);

        // Double Plant
        MPBlocks.INFECTED_ORANGE_ROSE_BUSH = new BlockDoublePlantMP("infected_orange_rose_bush", BlockDoublePlantMP.BlockType.INFECTED_ORANGE_ROSE_BUSH);
        MPBlocks.INFECTED_TALL_GRASS = new BlockDoublePlantMP("infected_tall_grass", BlockDoublePlantMP.BlockType.INFECTED_TALL_GRASS);
        MPBlocks.INFECTED_LARGE_FERN = new BlockDoublePlantMP("infected_large_fern", BlockDoublePlantMP.BlockType.INFECTED_LARGE_FERN);
        MPBlocks.GREEN_VEIN_TALL_GRASS = new BlockDoublePlantMP("green_vein_tall_grass", BlockDoublePlantMP.BlockType.GREEN_VEIN_TALL_GRASS);

        // Nibiru Rock
        MPBlocks.NIBIRU_ROCK = new BlockCobblestoneDrop("nibiru_rock").setHardness(1.5F);
        MPBlocks.NIBIRU_COBBLESTONE = new BlockBaseMP("nibiru_cobblestone", Material.ROCK).setHardness(2.0F);
        MPBlocks.NIBIRU_VEIN_COBBLESTONE = new BlockBaseMP("nibiru_vein_cobblestone", Material.ROCK).setHardness(2.0F);
        MPBlocks.INFECTED_STONE_BRICKS = new BlockBaseMP("infected_stone_bricks", Material.ROCK).setHardness(1.5F);
        MPBlocks.INFECTED_VEIN_STONE_BRICKS = new BlockBaseMP("infected_vein_stone_bricks", Material.ROCK).setHardness(1.5F);
        MPBlocks.INFECTED_CRACKED_STONE_BRICKS = new BlockBaseMP("infected_cracked_stone_bricks", Material.ROCK).setHardness(1.5F);
        MPBlocks.INFECTED_CHISELED_STONE_BRICKS = new BlockBaseMP("infected_chiseled_stone_bricks", Material.ROCK).setHardness(1.5F);
        MPBlocks.INFERUMITE_BLOCK = new BlockCompressedMetal("inferumite_block").setSoundType(SoundType.METAL).setHardness(5.0F);
        MPBlocks.NIBIRU_DUNGEON_BRICK = new BlockBaseMP("nibiru_dungeon_brick", Material.ROCK).setSortCategory(EnumSortCategoryBlock.DUNGEON_BRICK).setHardness(4.0F).setResistance(40.0F);
        MPBlocks.MOSSY_NIBIRU_DUNGEON_BRICK = new BlockBaseMP("mossy_nibiru_dungeon_brick", Material.ROCK).setSortCategory(EnumSortCategoryBlock.DUNGEON_BRICK).setHardness(4.0F).setResistance(40.0F);
        MPBlocks.INFECTED_SANDSTONE = new BlockBaseMP("infected_sandstone", Material.ROCK).setHardness(0.8F);
        MPBlocks.INFECTED_CHISELED_SANDSTONE = new BlockBaseMP("infected_chiseled_sandstone", Material.ROCK).setHardness(0.8F);
        MPBlocks.INFECTED_CUT_SANDSTONE = new BlockBaseMP("infected_cut_sandstone", Material.ROCK).setHardness(0.8F);

        // Infested Block
        MPBlocks.INFESTED_NIBIRU_ROCK = new BlockNibiruInfested("infested_nibiru_rock", BlockNibiruInfested.BlockType.NIBIRU_ROCK);
        MPBlocks.INFESTED_NIBIRU_COBBLESTONE = new BlockNibiruInfested("infested_nibiru_cobblestone", BlockNibiruInfested.BlockType.NIBIRU_COBBLESTONE);
        MPBlocks.INFESTED_NIBIRU_VEIN_COBBLESTONE = new BlockNibiruInfested("infested_nibiru_vein_cobblestone", BlockNibiruInfested.BlockType.NIBIRU_VEIN_COBBLESTONE);
        MPBlocks.INFESTED_INFECTED_STONE_BRICKS = new BlockNibiruInfested("infested_infected_stone_bricks", BlockNibiruInfested.BlockType.INFECTED_STONE_BRICKS);
        MPBlocks.INFESTED_INFECTED_VEIN_STONE_BRICKS = new BlockNibiruInfested("infested_infected_vein_stone_bricks", BlockNibiruInfested.BlockType.INFECTED_VEIN_STONE_BRICKS);
        MPBlocks.INFESTED_INFECTED_CRACKED_STONE_BRICKS = new BlockNibiruInfested("infested_infected_cracked_stone_bricks", BlockNibiruInfested.BlockType.INFECTED_CRACKED_STONE_BRICKS);
        MPBlocks.INFESTED_INFECTED_CHISELED_STONE_BRICKS = new BlockNibiruInfested("infested_infected_chiseled_stone_bricks", BlockNibiruInfested.BlockType.INFECTED_CHISELED_STONE_BRICKS);

        // Ore Block
        MPBlocks.INFECTED_IRON_ORE = new BlockMineableOre("infected_iron_ore").setHardness(3.0F);
        MPBlocks.INFECTED_GOLD_ORE = new BlockMineableOre("infected_gold_ore").setHardness(3.0F);
        MPBlocks.INFECTED_TIN_ORE = new BlockMineableOre("infected_tin_ore").setHardness(3.0F);
        MPBlocks.INFECTED_COPPER_ORE = new BlockMineableOre("infected_copper_ore").setHardness(3.0F);
        MPBlocks.INFECTED_ALUMINUM_ORE = new BlockMineableOre("infected_aluminum_ore").setHardness(3.0F);
        MPBlocks.INFECTED_COAL_ORE = new BlockDropableOre("infected_coal_ore", BlockDropableOre.BlockType.INFECTED_COAL_ORE).setHardness(3.0F);
        MPBlocks.INFECTED_LAPIS_ORE = new BlockDropableOre("infected_lapis_ore", BlockDropableOre.BlockType.LAPIS_ORE).setHardness(3.0F);
        MPBlocks.INFECTED_DIAMOND_ORE = new BlockDropableOre("infected_diamond_ore", BlockDropableOre.BlockType.DIAMOND_ORE).setHardness(3.0F);
        MPBlocks.INFECTED_EMERALD_ORE = new BlockDropableOre("infected_emerald_ore", BlockDropableOre.BlockType.EMERALD_ORE).setHardness(3.0F);
        MPBlocks.INFECTED_REDSTONE_ORE = new BlockDropableLitOre("infected_redstone_ore", BlockDropableLitOre.BlockType.REDSTONE_ORE).setHardness(3.0F);
        MPBlocks.INFECTED_SILICON_ORE = new BlockDropableOre("infected_silicon_ore", BlockDropableOre.BlockType.SILICON_ORE).setHardness(3.0F);
        MPBlocks.INFERUMITE_CRYSTAL_ORE = new BlockDropableOre("inferumite_crystal_ore", BlockDropableOre.BlockType.INFERUMITE_CRYSTAL_ORE).setHardness(3.0F);

        // Log Planks Leaves Sapling
        MPBlocks.INFECTED_OAK_LOG = new BlockLogMP("infected_oak_log");
        MPBlocks.INFECTED_DEADWOOD_LOG = new BlockLogMP("infected_deadwood_log");
        MPBlocks.INFECTED_SPRUCE_LOG = new BlockLogMP("infected_spruce_log");
        MPBlocks.INFECTED_JUNGLE_LOG = new BlockLogMP("infected_jungle_log");
        MPBlocks.ALIEN_BERRY_OAK_LOG = new BlockLogMP("alien_berry_oak_log");

        MPBlocks.INFECTED_OAK_PLANKS = new BlockBaseMP("infected_oak_planks", Material.WOOD).setSoundType(SoundType.WOOD).setHardness(2.0F).setResistance(5.0F);
        MPBlocks.INFECTED_SPRUCE_PLANKS = new BlockBaseMP("infected_spruce_planks", Material.WOOD).setSoundType(SoundType.WOOD).setHardness(2.0F).setResistance(5.0F);
        MPBlocks.ALIEN_BERRY_OAK_PLANKS = new BlockBaseMP("alien_berry_oak_planks", Material.WOOD).setSoundType(SoundType.WOOD).setHardness(2.0F).setResistance(5.0F);

        MPBlocks.INFECTED_OAK_LEAVES = new BlockLeavesMP("infected_oak_leaves", BlockLeavesMP.BlockType.INFECTED_OAK_LEAVES);
        MPBlocks.INFECTED_SPRUCE_LEAVES = new BlockLeavesMP("infected_spruce_leaves", BlockLeavesMP.BlockType.INFECTED_SPRUCE_LEAVES);
        MPBlocks.INFECTED_JUNGLE_LEAVES = new BlockLeavesMP("infected_jungle_leaves", BlockLeavesMP.BlockType.INFECTED_JUNGLE_LEAVES);
        MPBlocks.ALIEN_BERRY_OAK_LEAVES = new BlockLeavesMP("alien_berry_oak_leaves", BlockLeavesMP.BlockType.ALIEN_BERRY_OAK_LEAVES);

        MPBlocks.INFECTED_OAK_SAPLING = new BlockSaplingMP("infected_oak_sapling", BlockSaplingMP.BlockType.INFECTED_OAK_SAPLING);
        MPBlocks.INFECTED_SPRUCE_SAPLING = new BlockSaplingMP("infected_spruce_sapling", BlockSaplingMP.BlockType.INFECTED_SPRUCE_SAPLING);
        MPBlocks.INFECTED_JUNGLE_SAPLING = new BlockSaplingMP("infected_jungle_sapling", BlockSaplingMP.BlockType.INFECTED_JUNGLE_SAPLING);
        MPBlocks.ALIEN_BERRY_OAK_SAPLING = new BlockSaplingMP("alien_berry_oak_sapling", BlockSaplingMP.BlockType.ALIEN_BERRY_OAK_SAPLING);

        // Flower
        MPBlocks.PURE_HERB = new BlockPlaceableBushMP("pure_herb", BlockPlaceableBushMP.BlockType.PURE_HERB);
        MPBlocks.TERRAPUFF_HERB = new BlockPlaceableBushMP("terrapuff_herb", BlockPlaceableBushMP.BlockType.TERRAPUFF_HERB);
        MPBlocks.BATASIA_DANDELION = new BlockPlaceableBushMP("batasia_dandelion", BlockPlaceableBushMP.BlockType.BATASIA_DANDELION);
        MPBlocks.PYOLONIA = new BlockPlaceableBushMP("pyolonia", BlockPlaceableBushMP.BlockType.PYOLONIA);
        MPBlocks.PHILIPY = new BlockPlaceableBushMP("philipy", BlockPlaceableBushMP.BlockType.PHILIPY);
        MPBlocks.WHITE_TAIL = new BlockPlaceableBushMP("white_tail", BlockPlaceableBushMP.BlockType.WHITE_TAIL);
        MPBlocks.VEALIUM_VINES = new BlockPlaceableBushMP("vealium_vines", BlockPlaceableBushMP.BlockType.VEALIUM_VINES);
        MPBlocks.TERRASHROOM = new BlockPlaceableBushMP("terrashroom", BlockPlaceableBushMP.BlockType.TERRASHROOM);

        // Others
        MPBlocks.INFECTED_CRAFTING_TABLE = new BlockCraftingTableMP("infected_crafting_table");
        MPBlocks.ALIEN_BERRY_CRAFTING_TABLE = new BlockCraftingTableMP("alien_berry_crafting_table");
        MPBlocks.INFECTED_OAK_BOOKSHELF = new BlockBookshelfMP("infected_oak_bookshelf");
        MPBlocks.ALIEN_BERRY_OAK_BOOKSHELF = new BlockBookshelfMP("alien_berry_oak_bookshelf");
        MPBlocks.INFECTED_OAK_FENCE = new BlockFenceMP("infected_oak_fence");
        MPBlocks.ALIEN_BERRY_OAK_FENCE = new BlockFenceMP("alien_berry_oak_fence");
        MPBlocks.INFECTED_FARMLAND = new BlockInfectedFarmland("infected_farmland");
        MPBlocks.INFECTED_SAND = new BlockFallingMP("infected_sand").setSoundType(SoundType.SAND).setHardness(0.5F);
        MPBlocks.INFECTED_CACTUS = new BlockInfectedCactus("infected_cactus");
        MPBlocks.INFECTED_VINES = new BlockInfectedVines("infected_vines");
        MPBlocks.SPORELILY = new BlockSporelily("sporelily");
        MPBlocks.INFECTED_OAK_FENCE_GATE = new BlockFenceGateMP("infected_oak_fence_gate");
        MPBlocks.INFECTED_OAK_DOOR = new BlockDoorMP("infected_oak_door");
        MPBlocks.ALIEN_BERRY_OAK_DOOR = new BlockDoorMP("alien_berry_oak_door");
        MPBlocks.NIBIRU_COBBLESTONE_STAIRS = new BlockStairsMP("nibiru_cobblestone_stairs", EnumStairsType.COBBLESTONE);
        MPBlocks.INFECTED_STONE_BRICKS_STAIRS = new BlockStairsMP("infected_stone_bricks_stairs", EnumStairsType.STONE_BRICK);
        MPBlocks.NIBIRU_ANCIENT_CHEST = new BlockNibiruAncientChest("nibiru_ancient_chest");
        MPBlocks.INFECTED_SANDSTONE_STAIRS = new BlockStairsMP("infected_sandstone_stairs", EnumStairsType.SANDSTONE);
        MPBlocks.INFECTED_SNOW = new BlockInfectedSnow("infected_snow", false);
        MPBlocks.INFECTED_ICE = new BlockInfectedIce("infected_ice");
        MPBlocks.INFECTED_PACKED_ICE = new BlockInfectedPackedIce("infected_packed_ice");
        MPBlocks.INFECTED_SNOW_LAYER = new BlockSnowLayerMP("infected_snow_layer");
        MPBlocks.PURIFIED_SNOW = new BlockInfectedSnow("purified_snow", true);
        MPBlocks.PURIFIED_SNOW_LAYER = new BlockSnowLayerMP("purified_snow_layer");
        MPBlocks.GREEN_VEIN_GRASS_BLOCK = new BlockGreenVeinGrassBlock("green_vein_grass_block");
        MPBlocks.INFECTED_MELON = new BlockInfectedMelon("infected_melon");
        MPBlocks.INFECTED_WHEAT = new BlockInfectedWheat("infected_wheat");
        MPBlocks.INFECTED_GRAVEL = new BlockInfectedGravel("infected_gravel");
        MPBlocks.INFECTED_CLAY = new BlockInfectedClay("infected_clay");
        MPBlocks.ELECTRICAL_FIRE = new BlockElectricalFire("electrical_fire");
        MPBlocks.NIBIRU_TREASURE_CHEST = new BlockNibiruTreasureChest("nibiru_treasure_chest");
        MPBlocks.INFECTED_CHEST = new BlockInfectedChest("infected_chest");
        MPBlocks.MULTALIC_CRYSTAL = new BlockMultalicCrystal("multalic_crystal");
        MPBlocks.INFECTED_SUGAR_CANE = new BlockInfectedSugarCane("infected_sugar_cane");
        MPBlocks.INFECTED_PRISMARINE = new BlockBaseMP("infected_prismarine", Material.ROCK).setHardness(1.5F).setResistance(10.0F);
        MPBlocks.INFECTED_PRISMARINE_BRICKS = new BlockBaseMP("infected_prismarine_bricks", Material.ROCK).setHardness(1.5F).setResistance(10.0F);
        MPBlocks.INFECTED_DARK_PRISMARINE = new BlockBaseMP("infected_dark_prismarine", Material.ROCK).setHardness(1.5F).setResistance(10.0F);
        MPBlocks.INFECTED_SEA_LANTERN = new BlockInfectedSeaLantern("infected_sea_lantern");
        MPBlocks.INFECTED_SPONGE = new BlockInfectedSponge("infected_sponge", false);
        MPBlocks.INFECTED_WET_SPONGE = new BlockInfectedSponge("infected_wet_sponge", true);
        MPBlocks.ALIEN_BERRY_CHEST = new BlockAlienBerryChest("alien_berry_chest");
        MPBlocks.INFECTED_SEAWEED = new BlockInfectedSeaweed("infected_seaweed");
        MPBlocks.OIL_ORE = new BlockOilOre("oil_ore");
        MPBlocks.INFECTED_VINES_DIRT = new BlockInfectedVinesDirt("infected_vines_dirt");
        MPBlocks.INFECTED_TORCH = new BlockInfectedTorch("infected_torch");
        MPBlocks.INFECTED_FURNACE = new BlockFurnaceMP("infected_furnace", BlockFurnaceMP.BlockType.INFECTED);
        MPBlocks.JUICER_EGG = new BlockJuicerEgg("juicer_egg");
        MPBlocks.INFECTED_MELON_STEM = new BlockStemMP("infected_melon_stem", MPBlocks.INFECTED_MELON);
        MPBlocks.NUCLEAR_WASTE_TANK = new BlockNuclearWasteTank("nuclear_waste_tank");
        MPBlocks.VEIN_FRAME = new BlockVeinFrame("vein_frame");
        MPBlocks.VEIN_PORTAL = new BlockVeinPortal("vein_portal");
        MPBlocks.NUCLEAR_WASTE_GENERATOR = new BlockNuclearWasteGenerator("nuclear_waste_generator");
        MPBlocks.ALIEN_BERRY_OAK_FENCE_GATE = new BlockFenceGateMP("alien_berry_oak_fence_gate");
        MPBlocks.MULTALIC_CRYSTAL_BLOCK = new BlockMultalicCrystalBlock("multalic_crystal_block");
        MPBlocks.TERRASTONE = new BlockBaseMP("terrastone", Material.ROCK).setHardness(1.5F).setResistance(10.0F);
        MPBlocks.PURIFIED_GRAVEL = new BlockPurifiedGravel("purified_gravel");
        MPBlocks.TERRABERRY = new BlockTerraberry("terraberry");
        MPBlocks.HUGE_TERRASHROOM_BLOCK = new BlockHugeTerrashroom("huge_terrashroom_block");
        MPBlocks.TERRASTONE_STAIRS = new BlockStairsMP("terrastone_stairs", EnumStairsType.STONE_BRICK);
        MPBlocks.TERRASTONE_FURNACE = new BlockFurnaceMP("terrastone_furnace", BlockFurnaceMP.BlockType.TERRASTONE);
        MPBlocks.SEALABLE_NUCLEAR_WASTE_ROD = new BlockSealableNuclearWasteRod("sealable_nuclear_waste_rod");
        MPBlocks.INFECTED_GRASS_PATH = new BlockNibiruGrassPath("infected_grass_path");
        MPBlocks.GREEN_VEIN_GRASS_PATH = new BlockNibiruGrassPath("green_vein_grass_path");

        // Stairs
        MPBlocks.NIBIRU_DUNGEON_BRICK_STAIRS = new BlockStairsMP("nibiru_dungeon_brick_stairs", EnumStairsType.DUNGEON_BRICK).setSortCategory(EnumSortCategoryBlock.STAIRS_DUNGEON_BRICK);
        MPBlocks.INFECTED_OAK_STAIRS = new BlockStairsMP("infected_oak_stairs", EnumStairsType.WOODEN).setSortCategory(EnumSortCategoryBlock.STAIRS_WOODEN);
        MPBlocks.ALIEN_BERRY_OAK_STAIRS = new BlockStairsMP("alien_berry_oak_stairs", EnumStairsType.WOODEN).setSortCategory(EnumSortCategoryBlock.STAIRS_WOODEN);
        MPBlocks.INFECTED_PRISMARINE_STAIRS = new BlockStairsMP("infected_prismarine_stairs", EnumStairsType.STONE_BRICK);
        MPBlocks.INFECTED_PRISMARINE_BRICK_STAIRS = new BlockStairsMP("infected_prismarine_brick_stairs", EnumStairsType.STONE_BRICK);
        MPBlocks.INFECTED_DARK_PRISMARINE_STAIRS = new BlockStairsMP("infected_dark_prismarine_stairs", EnumStairsType.STONE_BRICK);
        MPBlocks.INFECTED_VEIN_STONE_BRICKS_STAIRS = new BlockStairsMP("infected_vein_stone_bricks_stairs", EnumStairsType.STONE_BRICK);
        MPBlocks.CRACKED_INFECTED_STONE_BRICKS_STAIRS = new BlockStairsMP("cracked_infected_stone_bricks_stairs", EnumStairsType.STONE_BRICK);
        MPBlocks.INFECTED_CUT_SANDSTONE_STAIRS = new BlockStairsMP("infected_cut_sandstone_stairs", EnumStairsType.SANDSTONE);

        // Slab
        MPBlocks.INFECTED_PRISMARINE_SLAB = new BlockAllHalfSlab("infected_prismarine_slab", BlockAllHalfSlab.BlockType.INFECTED_PRISMARINE_SLAB);
        MPBlocks.INFECTED_PRISMARINE_BRICK_SLAB = new BlockAllHalfSlab("infected_prismarine_brick_slab", BlockAllHalfSlab.BlockType.INFECTED_PRISMARINE_BRICKS_SLAB);
        MPBlocks.INFECTED_DARK_PRISMARINE_SLAB = new BlockAllHalfSlab("infected_dark_prismarine_slab", BlockAllHalfSlab.BlockType.INFECTED_DARK_PRISMARINE_SLAB);
        MPBlocks.INFECTED_STONE_BRICKS_SLAB = new BlockAllHalfSlab("infected_stone_bricks_slab", BlockAllHalfSlab.BlockType.INFECTED_STONE_BRICKS_SLAB);
        MPBlocks.INFECTED_VEIN_STONE_BRICKS_SLAB = new BlockAllHalfSlab("infected_vein_stone_bricks_slab", BlockAllHalfSlab.BlockType.INFECTED_VEIN_STONE_BRICKS_SLAB);
        MPBlocks.INFECTED_CRACKED_STONE_BRICKS_SLAB = new BlockAllHalfSlab("infected_cracked_stone_bricks_slab", BlockAllHalfSlab.BlockType.INFECTED_CRACKED_STONE_BRICKS_SLAB);
        MPBlocks.TERRASTONE_SLAB = new BlockAllHalfSlab("terrastone_slab", BlockAllHalfSlab.BlockType.TERRASTONE_SLAB);
        MPBlocks.INFECTED_SANDSTONE_SLAB = new BlockAllHalfSlab("infected_sandstone_slab", BlockAllHalfSlab.BlockType.INFECTED_SANDSTONE_SLAB);
        MPBlocks.INFECTED_CUT_SANDSTONE_SLAB = new BlockAllHalfSlab("infected_cut_sandstone_slab", BlockAllHalfSlab.BlockType.INFECTED_CUT_SANDSTONE_SLAB);

        MPBlocks.DOUBLE_INFECTED_PRISMARINE_SLAB = new BlockAllDoubleSlab("double_infected_prismarine_slab", BlockAllHalfSlab.BlockType.INFECTED_PRISMARINE_SLAB);
        MPBlocks.DOUBLE_INFECTED_PRISMARINE_BRICK_SLAB = new BlockAllDoubleSlab("double_infected_prismarine_brick_slab", BlockAllHalfSlab.BlockType.INFECTED_PRISMARINE_BRICKS_SLAB);
        MPBlocks.DOUBLE_INFECTED_DARK_PRISMARINE_SLAB = new BlockAllDoubleSlab("double_infected_dark_prismarine_slab", BlockAllHalfSlab.BlockType.INFECTED_DARK_PRISMARINE_SLAB);
        MPBlocks.DOUBLE_INFECTED_STONE_BRICKS_SLAB = new BlockAllDoubleSlab("double_infected_stone_bricks_slab", BlockAllHalfSlab.BlockType.INFECTED_STONE_BRICKS_SLAB);
        MPBlocks.DOUBLE_INFECTED_VEIN_STONE_BRICKS_SLAB = new BlockAllDoubleSlab("double_infected_vein_stone_bricks_slab", BlockAllHalfSlab.BlockType.INFECTED_VEIN_STONE_BRICKS_SLAB);
        MPBlocks.DOUBLE_INFECTED_CRACKED_STONE_BRICKS_SLAB = new BlockAllDoubleSlab("double_infected_cracked_stone_bricks_slab", BlockAllHalfSlab.BlockType.INFECTED_CRACKED_STONE_BRICKS_SLAB);
        MPBlocks.DOUBLE_TERRASTONE_SLAB = new BlockAllDoubleSlab("double_terrastone_slab", BlockAllHalfSlab.BlockType.TERRASTONE_SLAB);
        MPBlocks.DOUBLE_INFECTED_SANDSTONE_SLAB = new BlockAllDoubleSlab("double_infected_sandstone_slab", BlockAllHalfSlab.BlockType.INFECTED_SANDSTONE_SLAB);
        MPBlocks.DOUBLE_INFECTED_CUT_SANDSTONE_SLAB = new BlockAllDoubleSlab("double_infected_cut_sandstone_slab", BlockAllHalfSlab.BlockType.INFECTED_CUT_SANDSTONE_SLAB);

        //////////////////////// FRONOS STUFF ////////////////////////

        // Fronos Block
        MPBlocks.FRONOS_GRASS_BLOCK = new BlockFronosGrass("fronos_grass_block");
        MPBlocks.FRONOS_DIRT = new BlockTerraformable("fronos_dirt", Material.GROUND).setSoundType(SoundType.GROUND).setHardness(0.55F);
        MPBlocks.FRONOS_COARSE_DIRT = new BlockTerraformable("fronos_coarse_dirt", Material.GROUND).setSoundType(SoundType.GROUND).setHardness(0.55F);
        MPBlocks.FRONOS_FARMLAND = new BlockFronosFarmland("fronos_farmland");
        MPBlocks.FRONOS_STONE = new BlockCobblestoneDrop("fronos_stone").setHardness(1.5F);
        MPBlocks.FRONOS_COBBLESTONE = new BlockBaseMP("fronos_cobblestone", Material.ROCK).setHardness(2.0F);
        MPBlocks.FRONOS_STONE_BRICKS = new BlockBaseMP("fronos_stone_bricks", Material.ROCK).setHardness(1.5F);
        MPBlocks.FRONOS_MOSSY_STONE_BRICKS = new BlockBaseMP("fronos_mossy_stone_bricks", Material.ROCK).setHardness(1.5F);
        MPBlocks.FRONOS_CRACKED_STONE_BRICKS = new BlockBaseMP("fronos_cracked_stone_bricks", Material.ROCK).setHardness(1.5F);
        MPBlocks.FRONOS_CHISELED_STONE_BRICKS = new BlockBaseMP("fronos_chiseled_stone_bricks", Material.ROCK).setHardness(1.5F);
        MPBlocks.FRONOS_DUNGEON_BRICK = new BlockBaseMP("fronos_dungeon_brick", Material.ROCK).setSortCategory(EnumSortCategoryBlock.DUNGEON_BRICK).setHardness(4.0F).setResistance(40.0F);

        // Fronos Ore
        MPBlocks.FRONOS_IRON_ORE = new BlockMineableOre("fronos_iron_ore").setHardness(3.0F);
        MPBlocks.FRONOS_GOLD_ORE = new BlockMineableOre("fronos_gold_ore").setHardness(3.0F);
        MPBlocks.FRONOS_TIN_ORE = new BlockMineableOre("fronos_tin_ore").setHardness(3.0F);
        MPBlocks.FRONOS_COPPER_ORE = new BlockMineableOre("fronos_copper_ore").setHardness(3.0F);
        MPBlocks.FRONOS_ALUMINUM_ORE = new BlockMineableOre("fronos_aluminum_ore").setHardness(3.0F);
        MPBlocks.FRONOS_LEAD_ORE = new BlockMineableOre("fronos_lead_ore").setHardness(3.0F);
        MPBlocks.FRONOS_COAL_ORE = new BlockDropableOre("fronos_coal_ore", BlockDropableOre.BlockType.COAL_ORE).setHardness(3.0F);
        MPBlocks.FRONOS_LAPIS_ORE = new BlockDropableOre("fronos_lapis_ore", BlockDropableOre.BlockType.LAPIS_ORE).setHardness(3.0F);
        MPBlocks.FRONOS_DIAMOND_ORE = new BlockDropableOre("fronos_diamond_ore", BlockDropableOre.BlockType.DIAMOND_ORE).setHardness(3.0F);
        MPBlocks.FRONOS_EMERALD_ORE = new BlockDropableOre("fronos_emerald_ore", BlockDropableOre.BlockType.EMERALD_ORE).setHardness(3.0F);
        MPBlocks.FRONOS_REDSTONE_ORE = new BlockDropableLitOre("fronos_redstone_ore", BlockDropableLitOre.BlockType.REDSTONE_ORE).setHardness(3.0F);
        MPBlocks.FRONOS_SILICON_ORE = new BlockDropableOre("fronos_silicon_ore", BlockDropableOre.BlockType.SILICON_ORE).setHardness(3.0F);
        MPBlocks.FRONOS_QUARTZ_ORE = new BlockDropableOre("fronos_quartz_ore", BlockDropableOre.BlockType.QUARTZ_ORE).setHardness(3.0F);
        MPBlocks.EXTRAILONITE_ORE = new BlockMineableOre("extrailonite_ore").setHardness(5.0F);

        // Candy Cane
        MPBlocks.RED_CANDY_CANE = new BlockHorizontalMP("red_candy_cane", Material.CLOTH).setSoundType(SoundType.CLOTH).setHardness(0.55F).setResistance(3.0F);
        MPBlocks.GREEN_CANDY_CANE = new BlockHorizontalMP("green_candy_cane", Material.CLOTH).setSoundType(SoundType.CLOTH).setHardness(0.55F).setResistance(3.0F);
        MPBlocks.BLUE_CANDY_CANE = new BlockHorizontalMP("blue_candy_cane", Material.CLOTH).setSoundType(SoundType.CLOTH).setHardness(0.55F).setResistance(3.0F);
        MPBlocks.ORANGE_CANDY_CANE = new BlockHorizontalMP("orange_candy_cane", Material.CLOTH).setSoundType(SoundType.CLOTH).setHardness(0.55F).setResistance(3.0F);
        MPBlocks.PINK_CANDY_CANE = new BlockHorizontalMP("pink_candy_cane", Material.CLOTH).setSoundType(SoundType.CLOTH).setHardness(0.55F).setResistance(3.0F);
        MPBlocks.YELLOW_CANDY_CANE = new BlockHorizontalMP("yellow_candy_cane", Material.CLOTH).setSoundType(SoundType.CLOTH).setHardness(0.55F).setResistance(3.0F);
        MPBlocks.PURPLE_CANDY_CANE = new BlockHorizontalMP("purple_candy_cane", Material.CLOTH).setSoundType(SoundType.CLOTH).setHardness(0.55F).setResistance(3.0F);
        MPBlocks.RAINBOW_CANDY_CANE = new BlockHorizontalMP("rainbow_candy_cane", Material.CLOTH).setSoundType(SoundType.CLOTH).setHardness(0.55F).setResistance(3.0F);

        // Jelly Block
        MPBlocks.GRAPE_JELLY_BLOCK = new BlockJelly("grape_jelly_block", BlockJelly.BlockType.GRAPE_JELLY_BLOCK);
        MPBlocks.RASPBERRY_JELLY_BLOCK = new BlockJelly("raspberry_jelly_block", BlockJelly.BlockType.RASPBERRY_JELLY_BLOCK);
        MPBlocks.STRAWBERRY_JELLY_BLOCK = new BlockJelly("strawberry_jelly_block", BlockJelly.BlockType.STRAWBERRY_JELLY_BLOCK);
        MPBlocks.BERRY_JELLY_BLOCK = new BlockJelly("berry_jelly_block", BlockJelly.BlockType.BERRY_JELLY_BLOCK);
        MPBlocks.LIME_JELLY_BLOCK = new BlockJelly("lime_jelly_block", BlockJelly.BlockType.LIME_JELLY_BLOCK);
        MPBlocks.ORANGE_JELLY_BLOCK = new BlockJelly("orange_jelly_block", BlockJelly.BlockType.ORANGE_JELLY_BLOCK);
        MPBlocks.GREEN_JELLY_BLOCK = new BlockJelly("green_jelly_block", BlockJelly.BlockType.GREEN_JELLY_BLOCK);
        MPBlocks.LEMON_JELLY_BLOCK = new BlockJelly("lemon_jelly_block", BlockJelly.BlockType.LEMON_JELLY_BLOCK);

        //////////////////////// FLUID STUFF ////////////////////////

        if (CompatibilityManagerMP.isModAddedXpFluid())
        {
            MPBlocks.FLUID_XP = new FluidMP("xpjuice").setLuminosity(10).setDensity(800).setViscosity(1500);
            FluidRegistry.addBucketForFluid(MPBlocks.FLUID_XP);
        }

        MPBlocks.INFECTED_PURLONITE_WATER_FLUID = new FluidMP("infected_purlonite_water_fluid").setBlock(MPBlocks.INFECTED_PURLONITE_WATER_FLUID_BLOCK);
        MPBlocks.INFECTED_PURLONITE_LAVA_FLUID = new FluidMP("infected_purlonite_lava_fluid").setBlock(MPBlocks.INFECTED_PURLONITE_LAVA_FLUID_BLOCK).setLuminosity(15).setDensity(3000).setViscosity(6000).setTemperature(1300);
        MPBlocks.CHEESE_MILK_FLUID = new FluidMP("cheese_milk_fluid").setBlock(MPBlocks.CHEESE_MILK_FLUID_BLOCK).setViscosity(1000);
        MPBlocks.GASEOUS_CHEESE_MILK = new FluidGaseousCheeseMilk("gaseous_cheese_milk", "moreplanets:blocks/gaseous_cheese_milk", "moreplanets:blocks/gaseous_cheese_milk").setBlock(MPBlocks.GASEOUS_CHEESE_MILK_BLOCK);
        MPBlocks.INFECTED_WATER_FLUID = new FluidMP("infected_water_fluid_mp", "moreplanets:blocks/infected_water_still", "moreplanets:blocks/infected_water_flowing").setBlock(MPBlocks.INFECTED_WATER_FLUID_BLOCK);
        MPBlocks.HELIUM_GAS = new FluidHeliumGas("helium_gas", "moreplanets:blocks/helium_gas", "moreplanets:blocks/helium_gas").setBlock(MPBlocks.HELIUM_GAS_BLOCK);
        MPBlocks.NUCLEAR_WASTE_FLUID = new FluidMP("nuclear_waste_fluid").setBlock(MPBlocks.NUCLEAR_WASTE_FLUID_BLOCK).setLuminosity(15).setDensity(3000).setViscosity(8000).setTemperature(2600);
        MPBlocks.PURIFIED_WATER_FLUID = new FluidMP("purified_water_fluid").setBlock(MPBlocks.PURIFIED_WATER_FLUID_BLOCK);
        MorePlanetsMod.COMMON_REGISTRY.registerFluid(MPBlocks.INFECTED_PURLONITE_WATER_FLUID);
        MorePlanetsMod.COMMON_REGISTRY.registerFluid(MPBlocks.INFECTED_PURLONITE_LAVA_FLUID);
        MorePlanetsMod.COMMON_REGISTRY.registerFluid(MPBlocks.CHEESE_MILK_FLUID);
        MorePlanetsMod.COMMON_REGISTRY.registerFluid(MPBlocks.GASEOUS_CHEESE_MILK);
        MorePlanetsMod.COMMON_REGISTRY.registerFluid(MPBlocks.INFECTED_WATER_FLUID);
        MorePlanetsMod.COMMON_REGISTRY.registerFluid(MPBlocks.HELIUM_GAS);
        MorePlanetsMod.COMMON_REGISTRY.registerFluid(MPBlocks.NUCLEAR_WASTE_FLUID);
        MorePlanetsMod.COMMON_REGISTRY.registerFluid(MPBlocks.PURIFIED_WATER_FLUID);
        MPBlocks.INFECTED_PURLONITE_WATER_FLUID_BLOCK = new BlockFluidInfectedPurloniteWater("infected_purlonite_water_fluid");
        MPBlocks.INFECTED_PURLONITE_LAVA_FLUID_BLOCK = new BlockFluidInfectedPurloniteLava("infected_purlonite_lava_fluid");
        MPBlocks.GASEOUS_CHEESE_MILK_BLOCK = new BlockGaseousCheeseMilk("gaseous_cheese_milk");
        MPBlocks.CHEESE_MILK_FLUID_BLOCK = new BlockFluidCheeseMilk("cheese_milk_fluid");
        MPBlocks.INFECTED_WATER_FLUID_BLOCK = new BlockFluidInfectedWater("infected_water_fluid_mp");
        MPBlocks.HELIUM_GAS_BLOCK = new BlockGasHelium("helium_gas");
        MPBlocks.NUCLEAR_WASTE_FLUID_BLOCK = new BlockFluidNuclearWaste("nuclear_waste_fluid");
        MPBlocks.PURIFIED_WATER_FLUID_BLOCK = new BlockFluidPurifyWater("purified_water_fluid");

        MPBlocks.preRegister();
        MPBlocks.register();
        MPBlocks.postRegister();
    }

    private static void register()
    {
        // Diona Block
        BlocksItemsRegistry.registerBlock(MPBlocks.DIONA_SURFACE_ROCK);
        BlocksItemsRegistry.registerBlock(MPBlocks.DIONA_SUB_SURFACE_ROCK);
        BlocksItemsRegistry.registerBlock(MPBlocks.DIONA_ROCK);
        BlocksItemsRegistry.registerBlock(MPBlocks.DIONA_COBBLESTONE);
        BlocksItemsRegistry.registerBlock(MPBlocks.SETRORIUM_ORE);
        BlocksItemsRegistry.registerBlock(MPBlocks.ILLENIUM_ORE);
        BlocksItemsRegistry.registerBlock(MPBlocks.DIONA_TIN_ORE);
        BlocksItemsRegistry.registerBlock(MPBlocks.DIONA_COPPER_ORE);
        BlocksItemsRegistry.registerBlock(MPBlocks.DIONA_ALUMINUM_ORE);
        BlocksItemsRegistry.registerBlock(MPBlocks.SETRORIUM_BLOCK);
        BlocksItemsRegistry.registerBlock(MPBlocks.ILLENIUM_BLOCK);
        BlocksItemsRegistry.registerBlock(MPBlocks.DIONA_DUNGEON_BRICK);

        // Purlonite Segment
        BlocksItemsRegistry.registerBlock(MPBlocks.INFECTED_PURLONITE_SEGMENT);
        BlocksItemsRegistry.registerBlock(MPBlocks.INFECTED_PURLONITE_EYE_CORE);
        BlocksItemsRegistry.registerBlock(MPBlocks.INFECTED_PURLONITE_ENDER_CORE);

        // Others
        BlocksItemsRegistry.registerBlock(MPBlocks.ALBETIUS_WORM_EGG_ROCK);
        BlocksItemsRegistry.registerBlock(MPBlocks.INFECTED_PURLONITE_PLANKS);
        BlocksItemsRegistry.registerBlock(MPBlocks.INFECTED_PURLONITE_FENCE);
        BlocksItemsRegistry.registerBlock(MPBlocks.DIONA_ANCIENT_CHEST, ItemBlockTESRMP::new);
        BlocksItemsRegistry.registerBlock(MPBlocks.DIONA_TREASURE_CHEST, ItemBlockTESRMP::new);
        BlocksItemsRegistry.registerBlock(MPBlocks.ZELIUS_EGG, ItemBlockTESRMP::new);
        BlocksItemsRegistry.registerBlock(MPBlocks.INFECTED_PURLONITE_COBWEB);
        BlocksItemsRegistry.registerBlock(MPBlocks.INFECTED_PURLONITE_TORCH);
        BlocksItemsRegistry.registerBlock(MPBlocks.INFECTED_PURLONITE_CRYSTAL, ItemBlockTESRMP::new);
        BlocksItemsRegistry.registerBlock(MPBlocks.ALIEN_MINER_BLOOD);
        BlocksItemsRegistry.registerBlock(MPBlocks.INFECTED_PURLONITE_SLIME_BLOCK);
        BlocksItemsRegistry.registerBlock(MPBlocks.DARK_ENERGY_CORE, ItemBlockDescriptionTESR::new);
        BlocksItemsRegistry.registerBlock(MPBlocks.INFECTED_PURLONITE_WATER_FLUID_BLOCK);
        BlocksItemsRegistry.registerBlock(MPBlocks.INFECTED_PURLONITE_LAVA_FLUID_BLOCK);
        BlocksItemsRegistry.registerBlock(MPBlocks.DIONA_COBBLESTONE_STAIRS);
        BlocksItemsRegistry.registerBlock(MPBlocks.DIONA_DUNGEON_BRICK_STAIRS);
        BlocksItemsRegistry.registerBlock(MPBlocks.DARK_ENERGY_GENERATOR, ItemBlockDarkEnergyGenerator::new);
        BlocksItemsRegistry.registerBlock(MPBlocks.GLOWING_IRON_BLOCK);
        BlocksItemsRegistry.registerBlock(MPBlocks.CRASHED_ALIEN_PROBE);
        BlocksItemsRegistry.registerBlock(MPBlocks.ALIEN_SHIP_DECORATION_STAIRS);

        // Koentus Block
        BlocksItemsRegistry.registerBlock(MPBlocks.KOENTUS_REGOLITH);
        BlocksItemsRegistry.registerBlock(MPBlocks.KOENTUS_FINE_REGOLITH);
        BlocksItemsRegistry.registerBlock(MPBlocks.KOENTUS_ROCK);
        BlocksItemsRegistry.registerBlock(MPBlocks.KOENTUS_COBBLESTONE);
        BlocksItemsRegistry.registerBlock(MPBlocks.ANTI_GRAVITY_ORE, ItemBlockAntiGravity::new);
        BlocksItemsRegistry.registerBlock(MPBlocks.GOLDENITE_CRYSTALS_ORE);
        BlocksItemsRegistry.registerBlock(MPBlocks.KOENTUS_TIN_ORE);
        BlocksItemsRegistry.registerBlock(MPBlocks.KOENTUS_COPPER_ORE);
        BlocksItemsRegistry.registerBlock(MPBlocks.KOENTUS_ALUMINUM_ORE);
        BlocksItemsRegistry.registerBlock(MPBlocks.KOENTUS_IRON_ORE);
        BlocksItemsRegistry.registerBlock(MPBlocks.ANTI_GRAVITY_FRAGMENTS_BLOCK, ItemBlockAntiGravity::new);
        BlocksItemsRegistry.registerBlock(MPBlocks.GOLDENITE_CRYSTALS_BLOCK);
        BlocksItemsRegistry.registerBlock(MPBlocks.KOENTUS_DUNGEON_BRICK);
        BlocksItemsRegistry.registerBlock(MPBlocks.FALLEN_KOENTUS_METEOR);
        BlocksItemsRegistry.registerBlock(MPBlocks.KOENTUS_ICE);

        // Glowing Hardened Ice
        BlocksItemsRegistry.registerBlock(MPBlocks.WHITE_GLOWING_HARDENED_ICE);
        BlocksItemsRegistry.registerBlock(MPBlocks.ORANGE_GLOWING_HARDENED_ICE);
        BlocksItemsRegistry.registerBlock(MPBlocks.MAGENTA_GLOWING_HARDENED_ICE);
        BlocksItemsRegistry.registerBlock(MPBlocks.LIGHT_BLUE_GLOWING_HARDENED_ICE);
        BlocksItemsRegistry.registerBlock(MPBlocks.YELLOW_GLOWING_HARDENED_ICE);
        BlocksItemsRegistry.registerBlock(MPBlocks.LIME_GLOWING_HARDENED_ICE);
        BlocksItemsRegistry.registerBlock(MPBlocks.PINK_GLOWING_HARDENED_ICE);
        BlocksItemsRegistry.registerBlock(MPBlocks.GRAY_GLOWING_HARDENED_ICE);
        BlocksItemsRegistry.registerBlock(MPBlocks.SILVER_GLOWING_HARDENED_ICE);
        BlocksItemsRegistry.registerBlock(MPBlocks.CYAN_GLOWING_HARDENED_ICE);
        BlocksItemsRegistry.registerBlock(MPBlocks.PURPLE_GLOWING_HARDENED_ICE);
        BlocksItemsRegistry.registerBlock(MPBlocks.BLUE_GLOWING_HARDENED_ICE);
        BlocksItemsRegistry.registerBlock(MPBlocks.BROWN_GLOWING_HARDENED_ICE);
        BlocksItemsRegistry.registerBlock(MPBlocks.GREEN_GLOWING_HARDENED_ICE);
        BlocksItemsRegistry.registerBlock(MPBlocks.RED_GLOWING_HARDENED_ICE);
        BlocksItemsRegistry.registerBlock(MPBlocks.BLACK_GLOWING_HARDENED_ICE);

        // Creep Block
        BlocksItemsRegistry.registerBlock(MPBlocks.CREEP_BLOCK);
        BlocksItemsRegistry.registerBlock(MPBlocks.GRAVITY_CREEP_BLOCK, ItemBlockAntiGravity::new);
        BlocksItemsRegistry.registerBlock(MPBlocks.GRAVITY_CREEP_EXTRACTOR, ItemBlockAntiGravity::new);
        BlocksItemsRegistry.registerBlock(MPBlocks.GRAVITY_CREEP_VINES, ItemBlockAntiGravity::new);

        // Chalos Block
        BlocksItemsRegistry.registerBlock(MPBlocks.CHALOS_ROCK);
        BlocksItemsRegistry.registerBlock(MPBlocks.CHALOS_COBBLESTONE);
        BlocksItemsRegistry.registerBlock(MPBlocks.DIREMSIUM_ORE);
        BlocksItemsRegistry.registerBlock(MPBlocks.ZYPTORIUM_ORE);
        BlocksItemsRegistry.registerBlock(MPBlocks.CHEESE_MILK_ORE);
        BlocksItemsRegistry.registerBlock(MPBlocks.CHALOS_IRON_ORE);
        BlocksItemsRegistry.registerBlock(MPBlocks.CHALOS_TIN_ORE);
        BlocksItemsRegistry.registerBlock(MPBlocks.CHALOS_COPPER_ORE);
        BlocksItemsRegistry.registerBlock(MPBlocks.CHALOS_ALUMINUM_ORE);
        BlocksItemsRegistry.registerBlock(MPBlocks.DIREMSIUM_BLOCK);
        BlocksItemsRegistry.registerBlock(MPBlocks.ZYPTORIUM_BLOCK);
        BlocksItemsRegistry.registerBlock(MPBlocks.CHALOS_DUNGEON_BRICK);

        // Others
        BlocksItemsRegistry.registerBlock(MPBlocks.CHEESE_GRASS_BLOCK);
        BlocksItemsRegistry.registerBlock(MPBlocks.CHEESE_DIRT);
        BlocksItemsRegistry.registerBlock(MPBlocks.CHEESE_COARSE_DIRT);
        BlocksItemsRegistry.registerBlock(MPBlocks.CHEESE_SPORE_STEM);
        BlocksItemsRegistry.registerBlock(MPBlocks.CHEESE_SPORE);
        BlocksItemsRegistry.registerBlock(MPBlocks.CHEESE_SPORE_PLANKS);
        BlocksItemsRegistry.registerBlock(MPBlocks.CHEESE_SLIME_BLOCK);
        BlocksItemsRegistry.registerBlock(MPBlocks.CHALOS_ANCIENT_CHEST, ItemBlockTESRMP::new);
        BlocksItemsRegistry.registerBlock(MPBlocks.CHALOS_TREASURE_CHEST, ItemBlockTESRMP::new);
        BlocksItemsRegistry.registerBlock(MPBlocks.CHEESE_MILK_CAKE, ItemBlockDescription::new);
        BlocksItemsRegistry.registerBlock(MPBlocks.CHEESE_SPORE_FLOWER);
        BlocksItemsRegistry.registerBlock(MPBlocks.CHEESE_GRASS);
        BlocksItemsRegistry.registerBlock(MPBlocks.CHEESE_FARMLAND);
        BlocksItemsRegistry.registerBlock(MPBlocks.CHEESE_MILK_FLUID_BLOCK);
        BlocksItemsRegistry.registerBlock(MPBlocks.GASEOUS_CHEESE_MILK_BLOCK);
        BlocksItemsRegistry.registerBlock(MPBlocks.CHEESE_SPORE_BERRY, null);
        BlocksItemsRegistry.registerBlock(MPBlocks.CHEESE_TALL_GRASS);
        BlocksItemsRegistry.registerBlock(MPBlocks.CHEESE_SPORE_CRAFTING_TABLE);
        BlocksItemsRegistry.registerBlock(MPBlocks.CHEESE_SPORE_CHEST, ItemBlockTESRMP::new);
        BlocksItemsRegistry.registerBlock(MPBlocks.CHALOS_COBBLESTONE_STAIRS);
        BlocksItemsRegistry.registerBlock(MPBlocks.CHALOS_DUNGEON_BRICK_STAIRS);
        BlocksItemsRegistry.registerBlock(MPBlocks.CHEESE_SPORE_STAIRS);
        BlocksItemsRegistry.registerBlock(MPBlocks.CHEESE_SPORE_FENCE);
        BlocksItemsRegistry.registerBlock(MPBlocks.CHEESE_SPORE_FENCE_GATE);
        BlocksItemsRegistry.registerBlock(MPBlocks.CHEESE_SPORE_DOOR, null);

        // Nibiru Block
        BlocksItemsRegistry.registerBlock(MPBlocks.INFECTED_GRASS_BLOCK);
        BlocksItemsRegistry.registerBlock(MPBlocks.INFECTED_DIRT);
        BlocksItemsRegistry.registerBlock(MPBlocks.INFECTED_COARSE_DIRT);
        BlocksItemsRegistry.registerBlock(MPBlocks.INFECTED_PODZOL);
        BlocksItemsRegistry.registerBlock(MPBlocks.INFECTED_GRASS);
        BlocksItemsRegistry.registerBlock(MPBlocks.INFECTED_FERN);
        BlocksItemsRegistry.registerBlock(MPBlocks.GREEN_VEIN_GRASS);

        // Double Plant
        BlocksItemsRegistry.registerBlock(MPBlocks.INFECTED_ORANGE_ROSE_BUSH);
        BlocksItemsRegistry.registerBlock(MPBlocks.INFECTED_TALL_GRASS);
        BlocksItemsRegistry.registerBlock(MPBlocks.INFECTED_LARGE_FERN);
        BlocksItemsRegistry.registerBlock(MPBlocks.GREEN_VEIN_TALL_GRASS);

        // Nibiru Rock
        BlocksItemsRegistry.registerBlock(MPBlocks.NIBIRU_ROCK);
        BlocksItemsRegistry.registerBlock(MPBlocks.NIBIRU_COBBLESTONE);
        BlocksItemsRegistry.registerBlock(MPBlocks.NIBIRU_VEIN_COBBLESTONE);
        BlocksItemsRegistry.registerBlock(MPBlocks.INFECTED_STONE_BRICKS);
        BlocksItemsRegistry.registerBlock(MPBlocks.INFECTED_VEIN_STONE_BRICKS);
        BlocksItemsRegistry.registerBlock(MPBlocks.INFECTED_CRACKED_STONE_BRICKS);
        BlocksItemsRegistry.registerBlock(MPBlocks.INFECTED_CHISELED_STONE_BRICKS);
        BlocksItemsRegistry.registerBlock(MPBlocks.INFERUMITE_BLOCK);
        BlocksItemsRegistry.registerBlock(MPBlocks.NIBIRU_DUNGEON_BRICK);
        BlocksItemsRegistry.registerBlock(MPBlocks.MOSSY_NIBIRU_DUNGEON_BRICK);
        BlocksItemsRegistry.registerBlock(MPBlocks.INFECTED_SANDSTONE);
        BlocksItemsRegistry.registerBlock(MPBlocks.INFECTED_CHISELED_SANDSTONE);
        BlocksItemsRegistry.registerBlock(MPBlocks.INFECTED_CUT_SANDSTONE);

        // Infested Block
        BlocksItemsRegistry.registerBlock(MPBlocks.INFESTED_NIBIRU_ROCK);
        BlocksItemsRegistry.registerBlock(MPBlocks.INFESTED_NIBIRU_COBBLESTONE);
        BlocksItemsRegistry.registerBlock(MPBlocks.INFESTED_NIBIRU_VEIN_COBBLESTONE);
        BlocksItemsRegistry.registerBlock(MPBlocks.INFESTED_INFECTED_STONE_BRICKS);
        BlocksItemsRegistry.registerBlock(MPBlocks.INFESTED_INFECTED_VEIN_STONE_BRICKS);
        BlocksItemsRegistry.registerBlock(MPBlocks.INFESTED_INFECTED_CRACKED_STONE_BRICKS);
        BlocksItemsRegistry.registerBlock(MPBlocks.INFESTED_INFECTED_CHISELED_STONE_BRICKS);

        // Ore Block
        BlocksItemsRegistry.registerBlock(MPBlocks.INFECTED_IRON_ORE);
        BlocksItemsRegistry.registerBlock(MPBlocks.INFECTED_GOLD_ORE);
        BlocksItemsRegistry.registerBlock(MPBlocks.INFECTED_TIN_ORE);
        BlocksItemsRegistry.registerBlock(MPBlocks.INFECTED_COPPER_ORE);
        BlocksItemsRegistry.registerBlock(MPBlocks.INFECTED_ALUMINUM_ORE);
        BlocksItemsRegistry.registerBlock(MPBlocks.INFECTED_COAL_ORE);
        BlocksItemsRegistry.registerBlock(MPBlocks.INFECTED_LAPIS_ORE);
        BlocksItemsRegistry.registerBlock(MPBlocks.INFECTED_DIAMOND_ORE);
        BlocksItemsRegistry.registerBlock(MPBlocks.INFECTED_EMERALD_ORE);
        BlocksItemsRegistry.registerBlock(MPBlocks.INFECTED_REDSTONE_ORE);
        BlocksItemsRegistry.registerBlock(MPBlocks.INFECTED_SILICON_ORE);
        BlocksItemsRegistry.registerBlock(MPBlocks.INFERUMITE_CRYSTAL_ORE);

        // Log Planks Leaves Sapling
        BlocksItemsRegistry.registerBlock(MPBlocks.INFECTED_OAK_LOG);
        BlocksItemsRegistry.registerBlock(MPBlocks.INFECTED_DEADWOOD_LOG);
        BlocksItemsRegistry.registerBlock(MPBlocks.INFECTED_SPRUCE_LOG);
        BlocksItemsRegistry.registerBlock(MPBlocks.INFECTED_JUNGLE_LOG);
        BlocksItemsRegistry.registerBlock(MPBlocks.ALIEN_BERRY_OAK_LOG);

        BlocksItemsRegistry.registerBlock(MPBlocks.INFECTED_OAK_PLANKS);
        BlocksItemsRegistry.registerBlock(MPBlocks.INFECTED_SPRUCE_PLANKS);
        BlocksItemsRegistry.registerBlock(MPBlocks.ALIEN_BERRY_OAK_PLANKS);

        BlocksItemsRegistry.registerBlock(MPBlocks.INFECTED_OAK_LEAVES, ItemBlockSingleLeaves::new);
        BlocksItemsRegistry.registerBlock(MPBlocks.INFECTED_SPRUCE_LEAVES, ItemBlockSingleLeaves::new);
        BlocksItemsRegistry.registerBlock(MPBlocks.INFECTED_JUNGLE_LEAVES, ItemBlockSingleLeaves::new);
        BlocksItemsRegistry.registerBlock(MPBlocks.ALIEN_BERRY_OAK_LEAVES, ItemBlockSingleLeaves::new);

        BlocksItemsRegistry.registerBlock(MPBlocks.INFECTED_OAK_SAPLING);
        BlocksItemsRegistry.registerBlock(MPBlocks.INFECTED_SPRUCE_SAPLING);
        BlocksItemsRegistry.registerBlock(MPBlocks.INFECTED_JUNGLE_SAPLING);
        BlocksItemsRegistry.registerBlock(MPBlocks.ALIEN_BERRY_OAK_SAPLING);

        // Flower
        BlocksItemsRegistry.registerBlock(MPBlocks.PURE_HERB);
        BlocksItemsRegistry.registerBlock(MPBlocks.TERRAPUFF_HERB);
        BlocksItemsRegistry.registerBlock(MPBlocks.BATASIA_DANDELION);
        BlocksItemsRegistry.registerBlock(MPBlocks.PYOLONIA);
        BlocksItemsRegistry.registerBlock(MPBlocks.PHILIPY);
        BlocksItemsRegistry.registerBlock(MPBlocks.WHITE_TAIL);
        BlocksItemsRegistry.registerBlock(MPBlocks.VEALIUM_VINES);
        BlocksItemsRegistry.registerBlock(MPBlocks.TERRASHROOM);

        // Other
        BlocksItemsRegistry.registerBlock(MPBlocks.INFECTED_CRAFTING_TABLE);
        BlocksItemsRegistry.registerBlock(MPBlocks.ALIEN_BERRY_CRAFTING_TABLE);
        BlocksItemsRegistry.registerBlock(MPBlocks.INFECTED_OAK_BOOKSHELF);
        BlocksItemsRegistry.registerBlock(MPBlocks.ALIEN_BERRY_OAK_BOOKSHELF);
        BlocksItemsRegistry.registerBlock(MPBlocks.INFECTED_OAK_FENCE);
        BlocksItemsRegistry.registerBlock(MPBlocks.ALIEN_BERRY_OAK_FENCE);
        BlocksItemsRegistry.registerBlock(MPBlocks.INFECTED_GRASS_PATH);
        BlocksItemsRegistry.registerBlock(MPBlocks.GREEN_VEIN_GRASS_PATH);
        BlocksItemsRegistry.registerBlock(MPBlocks.INFECTED_FARMLAND);
        BlocksItemsRegistry.registerBlock(MPBlocks.INFECTED_SAND);
        BlocksItemsRegistry.registerBlock(MPBlocks.INFECTED_CACTUS);
        BlocksItemsRegistry.registerBlock(MPBlocks.INFECTED_WATER_FLUID_BLOCK);
        BlocksItemsRegistry.registerBlock(MPBlocks.INFECTED_VINES);
        BlocksItemsRegistry.registerBlock(MPBlocks.SPORELILY, ItemBlockSporelily::new);
        BlocksItemsRegistry.registerBlock(MPBlocks.INFECTED_OAK_FENCE_GATE);
        BlocksItemsRegistry.registerBlock(MPBlocks.INFECTED_OAK_DOOR, null);
        BlocksItemsRegistry.registerBlock(MPBlocks.ALIEN_BERRY_OAK_DOOR, null);
        BlocksItemsRegistry.registerBlock(MPBlocks.NIBIRU_ANCIENT_CHEST, ItemBlockTESRMP::new);
        BlocksItemsRegistry.registerBlock(MPBlocks.INFECTED_SNOW);
        BlocksItemsRegistry.registerBlock(MPBlocks.PURIFIED_SNOW);
        BlocksItemsRegistry.registerBlock(MPBlocks.INFECTED_ICE);
        BlocksItemsRegistry.registerBlock(MPBlocks.INFECTED_PACKED_ICE);
        BlocksItemsRegistry.registerBlock(MPBlocks.INFECTED_SNOW_LAYER, ItemBlockInfectedSnow::new);
        BlocksItemsRegistry.registerBlock(MPBlocks.PURIFIED_SNOW_LAYER, ItemBlockInfectedSnow::new);
        BlocksItemsRegistry.registerBlock(MPBlocks.GREEN_VEIN_GRASS_BLOCK);
        BlocksItemsRegistry.registerBlock(MPBlocks.INFECTED_MELON);
        BlocksItemsRegistry.registerBlock(MPBlocks.INFECTED_WHEAT, null);
        BlocksItemsRegistry.registerBlock(MPBlocks.INFECTED_GRAVEL);
        BlocksItemsRegistry.registerBlock(MPBlocks.INFECTED_CLAY);
        BlocksItemsRegistry.registerBlock(MPBlocks.ELECTRICAL_FIRE, null);
        BlocksItemsRegistry.registerBlock(MPBlocks.NIBIRU_TREASURE_CHEST, ItemBlockTESRMP::new);
        BlocksItemsRegistry.registerBlock(MPBlocks.INFECTED_CHEST, ItemBlockTESRMP::new);
        BlocksItemsRegistry.registerBlock(MPBlocks.ALIEN_BERRY_CHEST, ItemBlockTESRMP::new);
        BlocksItemsRegistry.registerBlock(MPBlocks.MULTALIC_CRYSTAL, ItemBlockTESRMP::new);
        BlocksItemsRegistry.registerBlock(MPBlocks.INFECTED_SUGAR_CANE, null);
        BlocksItemsRegistry.registerBlock(MPBlocks.INFECTED_PRISMARINE);
        BlocksItemsRegistry.registerBlock(MPBlocks.INFECTED_PRISMARINE_BRICKS);
        BlocksItemsRegistry.registerBlock(MPBlocks.INFECTED_DARK_PRISMARINE);
        BlocksItemsRegistry.registerBlock(MPBlocks.INFECTED_SEA_LANTERN);
        BlocksItemsRegistry.registerBlock(MPBlocks.INFECTED_SPONGE);
        BlocksItemsRegistry.registerBlock(MPBlocks.INFECTED_WET_SPONGE);
        BlocksItemsRegistry.registerBlock(MPBlocks.INFECTED_SEAWEED);
        BlocksItemsRegistry.registerBlock(MPBlocks.OIL_ORE);
        BlocksItemsRegistry.registerBlock(MPBlocks.HELIUM_GAS_BLOCK);
        BlocksItemsRegistry.registerBlock(MPBlocks.INFECTED_VINES_DIRT);
        BlocksItemsRegistry.registerBlock(MPBlocks.INFECTED_TORCH);
        BlocksItemsRegistry.registerBlock(MPBlocks.INFECTED_FURNACE);
        BlocksItemsRegistry.registerBlock(MPBlocks.TERRASTONE_FURNACE);
        BlocksItemsRegistry.registerBlock(MPBlocks.JUICER_EGG, ItemBlockTESRMP::new);
        BlocksItemsRegistry.registerBlock(MPBlocks.INFECTED_MELON_STEM, null);
        BlocksItemsRegistry.registerBlock(MPBlocks.NUCLEAR_WASTE_TANK, ItemBlockNuclearWasteTank::new);
        BlocksItemsRegistry.registerBlock(MPBlocks.NUCLEAR_WASTE_FLUID_BLOCK);
        BlocksItemsRegistry.registerBlock(MPBlocks.VEIN_FRAME, ItemBlockTESRMP::new);
        BlocksItemsRegistry.registerBlock(MPBlocks.VEIN_PORTAL, null);
        BlocksItemsRegistry.registerBlock(MPBlocks.NUCLEAR_WASTE_GENERATOR, ItemBlockDescriptionTESR::new);
        BlocksItemsRegistry.registerBlock(MPBlocks.ALIEN_BERRY_OAK_FENCE_GATE);
        BlocksItemsRegistry.registerBlock(MPBlocks.MULTALIC_CRYSTAL_BLOCK);
        BlocksItemsRegistry.registerBlock(MPBlocks.TERRASTONE);
        BlocksItemsRegistry.registerBlock(MPBlocks.PURIFIED_WATER_FLUID_BLOCK);
        BlocksItemsRegistry.registerBlock(MPBlocks.PURIFIED_GRAVEL);
        BlocksItemsRegistry.registerBlock(MPBlocks.TERRABERRY, null);
        BlocksItemsRegistry.registerBlock(MPBlocks.HUGE_TERRASHROOM_BLOCK);
        BlocksItemsRegistry.registerBlock(MPBlocks.SEALABLE_NUCLEAR_WASTE_ROD, ItemBlockDescription::new);

        // Stairs
        BlocksItemsRegistry.registerBlock(MPBlocks.NIBIRU_DUNGEON_BRICK_STAIRS);
        BlocksItemsRegistry.registerBlock(MPBlocks.INFECTED_OAK_STAIRS);
        BlocksItemsRegistry.registerBlock(MPBlocks.ALIEN_BERRY_OAK_STAIRS);
        BlocksItemsRegistry.registerBlock(MPBlocks.INFECTED_PRISMARINE_STAIRS);
        BlocksItemsRegistry.registerBlock(MPBlocks.INFECTED_PRISMARINE_BRICK_STAIRS);
        BlocksItemsRegistry.registerBlock(MPBlocks.INFECTED_DARK_PRISMARINE_STAIRS);
        BlocksItemsRegistry.registerBlock(MPBlocks.INFECTED_VEIN_STONE_BRICKS_STAIRS);
        BlocksItemsRegistry.registerBlock(MPBlocks.CRACKED_INFECTED_STONE_BRICKS_STAIRS);
        BlocksItemsRegistry.registerBlock(MPBlocks.INFECTED_CUT_SANDSTONE_STAIRS);
        BlocksItemsRegistry.registerBlock(MPBlocks.NIBIRU_COBBLESTONE_STAIRS);
        BlocksItemsRegistry.registerBlock(MPBlocks.INFECTED_STONE_BRICKS_STAIRS);
        BlocksItemsRegistry.registerBlock(MPBlocks.INFECTED_SANDSTONE_STAIRS);
        BlocksItemsRegistry.registerBlock(MPBlocks.TERRASTONE_STAIRS);

        // Slab
        BlocksItemsRegistry.registerBlock(MPBlocks.INFECTED_PRISMARINE_SLAB, ItemBlockSlabMP::new);
        BlocksItemsRegistry.registerBlock(MPBlocks.INFECTED_PRISMARINE_BRICK_SLAB, ItemBlockSlabMP::new);
        BlocksItemsRegistry.registerBlock(MPBlocks.INFECTED_DARK_PRISMARINE_SLAB, ItemBlockSlabMP::new);
        BlocksItemsRegistry.registerBlock(MPBlocks.INFECTED_STONE_BRICKS_SLAB, ItemBlockSlabMP::new);
        BlocksItemsRegistry.registerBlock(MPBlocks.INFECTED_VEIN_STONE_BRICKS_SLAB, ItemBlockSlabMP::new);
        BlocksItemsRegistry.registerBlock(MPBlocks.INFECTED_CRACKED_STONE_BRICKS_SLAB, ItemBlockSlabMP::new);
        BlocksItemsRegistry.registerBlock(MPBlocks.TERRASTONE_SLAB, ItemBlockSlabMP::new);
        BlocksItemsRegistry.registerBlock(MPBlocks.INFECTED_SANDSTONE_SLAB, ItemBlockSlabMP::new);
        BlocksItemsRegistry.registerBlock(MPBlocks.INFECTED_CUT_SANDSTONE_SLAB, ItemBlockSlabMP::new);

        BlocksItemsRegistry.registerBlock(MPBlocks.DOUBLE_INFECTED_PRISMARINE_SLAB, null);
        BlocksItemsRegistry.registerBlock(MPBlocks.DOUBLE_INFECTED_PRISMARINE_BRICK_SLAB, null);
        BlocksItemsRegistry.registerBlock(MPBlocks.DOUBLE_INFECTED_DARK_PRISMARINE_SLAB, null);
        BlocksItemsRegistry.registerBlock(MPBlocks.DOUBLE_INFECTED_STONE_BRICKS_SLAB, null);
        BlocksItemsRegistry.registerBlock(MPBlocks.DOUBLE_INFECTED_VEIN_STONE_BRICKS_SLAB, null);
        BlocksItemsRegistry.registerBlock(MPBlocks.DOUBLE_INFECTED_CRACKED_STONE_BRICKS_SLAB, null);
        BlocksItemsRegistry.registerBlock(MPBlocks.DOUBLE_TERRASTONE_SLAB, null);
        BlocksItemsRegistry.registerBlock(MPBlocks.DOUBLE_INFECTED_SANDSTONE_SLAB, null);
        BlocksItemsRegistry.registerBlock(MPBlocks.DOUBLE_INFECTED_CUT_SANDSTONE_SLAB, null);
    }

    private static void preRegister()
    {
        // Dummy
        BlocksItemsRegistry.registerBlock(MPBlocks.WARP_PAD_DUMMY, null);
        BlocksItemsRegistry.registerBlock(MPBlocks.DER_SOLAR1_DUMMY, null);
        BlocksItemsRegistry.registerBlock(MPBlocks.DER_SOLAR2_DUMMY, null);
        BlocksItemsRegistry.registerBlock(MPBlocks.DER_SOLAR3_DUMMY, null);
        BlocksItemsRegistry.registerBlock(MPBlocks.DER_SOLAR4_DUMMY, null);
        BlocksItemsRegistry.registerBlock(MPBlocks.NWT_MIDDLE_DUMMY, null);
        BlocksItemsRegistry.registerBlock(MPBlocks.NWT_TOP_DUMMY, null);
        BlocksItemsRegistry.registerBlock(MPBlocks.SHIELD_GENERATOR_DUMMY, null);

        // Boss Spawner
        BlocksItemsRegistry.registerBlock(MPBlocks.DIONA_DUNGEON_SPAWNER, null);
        BlocksItemsRegistry.registerBlock(MPBlocks.CHALOS_DUNGEON_SPAWNER, null);
        BlocksItemsRegistry.registerBlock(MPBlocks.NIBIRU_DUNGEON_SPAWNER, null);

        // Slab
        BlocksItemsRegistry.registerBlock(MPBlocks.DIONA_COBBLESTONE_SLAB, ItemBlockSlabMP::new);
        BlocksItemsRegistry.registerBlock(MPBlocks.CHALOS_COBBLESTONE_SLAB, ItemBlockSlabMP::new);
        BlocksItemsRegistry.registerBlock(MPBlocks.NIBIRU_COBBLESTONE_SLAB, ItemBlockSlabMP::new);
        BlocksItemsRegistry.registerBlock(MPBlocks.DIONA_DUNGEON_BRICK_SLAB, ItemBlockSlabMP::new);
        BlocksItemsRegistry.registerBlock(MPBlocks.CHALOS_DUNGEON_BRICK_SLAB, ItemBlockSlabMP::new);
        BlocksItemsRegistry.registerBlock(MPBlocks.NIBIRU_DUNGEON_BRICK_SLAB, ItemBlockSlabMP::new);
        BlocksItemsRegistry.registerBlock(MPBlocks.ALIEN_SHIP_DECORATION_SLAB, ItemBlockSlabMP::new);

        // Slab Wood
        BlocksItemsRegistry.registerBlock(MPBlocks.CHEESE_SPORE_SLAB, ItemBlockSlabMP::new);
        BlocksItemsRegistry.registerBlock(MPBlocks.INFECTED_OAK_SLAB, ItemBlockSlabMP::new);
        BlocksItemsRegistry.registerBlock(MPBlocks.ALIEN_BERRY_OAK_SLAB, ItemBlockSlabMP::new);

        BlocksItemsRegistry.registerBlock(MPBlocks.DOUBLE_DIONA_COBBLESTONE_SLAB, null);
        BlocksItemsRegistry.registerBlock(MPBlocks.DOUBLE_CHALOS_COBBLESTONE_SLAB, null);
        BlocksItemsRegistry.registerBlock(MPBlocks.DOUBLE_NIBIRU_COBBLESTONE_SLAB, null);
        BlocksItemsRegistry.registerBlock(MPBlocks.DOUBLE_DIONA_DUNGEON_BRICK_SLAB, null);
        BlocksItemsRegistry.registerBlock(MPBlocks.DOUBLE_CHALOS_DUNGEON_BRICK_SLAB, null);
        BlocksItemsRegistry.registerBlock(MPBlocks.DOUBLE_NIBIRU_DUNGEON_BRICK_SLAB, null);
        BlocksItemsRegistry.registerBlock(MPBlocks.DOUBLE_ALIEN_SHIP_DECORATION_SLAB, null);

        BlocksItemsRegistry.registerBlock(MPBlocks.DOUBLE_CHEESE_SPORE_SLAB, null);
        BlocksItemsRegistry.registerBlock(MPBlocks.DOUBLE_INFECTED_OAK_SLAB, null);
        BlocksItemsRegistry.registerBlock(MPBlocks.DOUBLE_ALIEN_BERRY_OAK_SLAB, null);

        // Pressure Plate
        BlocksItemsRegistry.registerBlock(MPBlocks.INFECTED_OAK_PRESSURE_PLATE);
        BlocksItemsRegistry.registerBlock(MPBlocks.ALIEN_BERRY_OAK_PRESSURE_PLATE);

        // Energy Storage
        BlocksItemsRegistry.registerBlock(MPBlocks.DARK_ENERGY_STORAGE_CLUSTER, ItemBlockDescription::new);
        BlocksItemsRegistry.registerBlock(MPBlocks.NUCLEAR_WASTE_ENERGY_STORAGE_CLUSTER, ItemBlockDescription::new);

        // Machine
        BlocksItemsRegistry.registerBlock(MPBlocks.SPACE_WARP_PAD, ItemBlockSpaceWarpPad::new);
        BlocksItemsRegistry.registerBlock(MPBlocks.SPACE_WARP_PAD_FULL, null);
        BlocksItemsRegistry.registerBlock(MPBlocks.DARK_ENERGY_RECEIVER, ItemBlockDarkEnergyReceiver::new);
        BlocksItemsRegistry.registerBlock(MPBlocks.SPACE_PORTAL, ItemBlockSpacePortal::new);
        BlocksItemsRegistry.registerBlock(MPBlocks.BLACK_HOLE_STORAGE, ItemBlockBlackHoleStorage::new);
        BlocksItemsRegistry.registerBlock(MPBlocks.ALIEN_DEFENDER_BEACON, ItemBlockTESRMP::new);
        BlocksItemsRegistry.registerBlock(MPBlocks.SHIELD_GENERATOR, ItemBlockShieldGenerator::new);

        // Alien Ship
        BlocksItemsRegistry.registerBlock(MPBlocks.ALIEN_SHIP_BOOSTER);
        BlocksItemsRegistry.registerBlock(MPBlocks.ALIEN_SHIP_DECORATION_0);
        BlocksItemsRegistry.registerBlock(MPBlocks.ALIEN_SHIP_DECORATION_1);
        BlocksItemsRegistry.registerBlock(MPBlocks.ALIEN_CHEST, ItemBlockTESRMP::new);

        // Tinted Glass
        BlocksItemsRegistry.registerBlock(MPBlocks.TINTED_GLASS);
        BlocksItemsRegistry.registerBlock(MPBlocks.WHITE_TINTED_GLASS);
        BlocksItemsRegistry.registerBlock(MPBlocks.ORANGE_TINTED_GLASS);
        BlocksItemsRegistry.registerBlock(MPBlocks.MAGENTA_TINTED_GLASS);
        BlocksItemsRegistry.registerBlock(MPBlocks.LIGHT_BLUE_TINTED_GLASS);
        BlocksItemsRegistry.registerBlock(MPBlocks.YELLOW_TINTED_GLASS);
        BlocksItemsRegistry.registerBlock(MPBlocks.LIME_TINTED_GLASS);
        BlocksItemsRegistry.registerBlock(MPBlocks.PINK_TINTED_GLASS);
        BlocksItemsRegistry.registerBlock(MPBlocks.GRAY_TINTED_GLASS);
        BlocksItemsRegistry.registerBlock(MPBlocks.SILVER_TINTED_GLASS);
        BlocksItemsRegistry.registerBlock(MPBlocks.CYAN_TINTED_GLASS);
        BlocksItemsRegistry.registerBlock(MPBlocks.PURPLE_TINTED_GLASS);
        BlocksItemsRegistry.registerBlock(MPBlocks.BLUE_TINTED_GLASS);
        BlocksItemsRegistry.registerBlock(MPBlocks.BROWN_TINTED_GLASS);
        BlocksItemsRegistry.registerBlock(MPBlocks.GREEN_TINTED_GLASS);
        BlocksItemsRegistry.registerBlock(MPBlocks.RED_TINTED_GLASS);
        BlocksItemsRegistry.registerBlock(MPBlocks.BLACK_TINTED_GLASS);

        // Tinted Glass Pane
        BlocksItemsRegistry.registerBlock(MPBlocks.TINTED_GLASS_PANE);
        BlocksItemsRegistry.registerBlock(MPBlocks.WHITE_TINTED_GLASS_PANE);
        BlocksItemsRegistry.registerBlock(MPBlocks.ORANGE_TINTED_GLASS_PANE);
        BlocksItemsRegistry.registerBlock(MPBlocks.MAGENTA_TINTED_GLASS_PANE);
        BlocksItemsRegistry.registerBlock(MPBlocks.LIGHT_BLUE_TINTED_GLASS_PANE);
        BlocksItemsRegistry.registerBlock(MPBlocks.YELLOW_TINTED_GLASS_PANE);
        BlocksItemsRegistry.registerBlock(MPBlocks.LIME_TINTED_GLASS_PANE);
        BlocksItemsRegistry.registerBlock(MPBlocks.PINK_TINTED_GLASS_PANE);
        BlocksItemsRegistry.registerBlock(MPBlocks.GRAY_TINTED_GLASS_PANE);
        BlocksItemsRegistry.registerBlock(MPBlocks.SILVER_TINTED_GLASS_PANE);
        BlocksItemsRegistry.registerBlock(MPBlocks.CYAN_TINTED_GLASS_PANE);
        BlocksItemsRegistry.registerBlock(MPBlocks.PURPLE_TINTED_GLASS_PANE);
        BlocksItemsRegistry.registerBlock(MPBlocks.BLUE_TINTED_GLASS_PANE);
        BlocksItemsRegistry.registerBlock(MPBlocks.BROWN_TINTED_GLASS_PANE);
        BlocksItemsRegistry.registerBlock(MPBlocks.GREEN_TINTED_GLASS_PANE);
        BlocksItemsRegistry.registerBlock(MPBlocks.RED_TINTED_GLASS_PANE);
        BlocksItemsRegistry.registerBlock(MPBlocks.BLACK_TINTED_GLASS_PANE);

        // Wall
        BlocksItemsRegistry.registerBlock(MPBlocks.DIONA_COBBLESTONE_WALL);
        BlocksItemsRegistry.registerBlock(MPBlocks.CHALOS_COBBLESTONE_WALL);
        BlocksItemsRegistry.registerBlock(MPBlocks.NIBIRU_COBBLESTONE_WALL);
        BlocksItemsRegistry.registerBlock(MPBlocks.DIONA_DUNGEON_BRICK_WALL);
        BlocksItemsRegistry.registerBlock(MPBlocks.CHALOS_DUNGEON_BRICK_WALL);
        BlocksItemsRegistry.registerBlock(MPBlocks.NIBIRU_DUNGEON_BRICK_WALL);

        // Others
        BlocksItemsRegistry.registerBlock(MPBlocks.POLISHED_TIN_DECORATION_BLOCK);
        BlocksItemsRegistry.registerBlock(MPBlocks.POLISHED_ALUMINUM_DECORATION_BLOCK);
        BlocksItemsRegistry.registerBlock(MPBlocks.DESH_FRAME);
        BlocksItemsRegistry.registerBlock(MPBlocks.DUNGEON_GLOWSTONE);

        // Fronos Block
        BlocksItemsRegistry.registerBlock(MPBlocks.FRONOS_GRASS_BLOCK);
        BlocksItemsRegistry.registerBlock(MPBlocks.FRONOS_DIRT);
        BlocksItemsRegistry.registerBlock(MPBlocks.FRONOS_COARSE_DIRT);
        BlocksItemsRegistry.registerBlock(MPBlocks.FRONOS_FARMLAND);
        BlocksItemsRegistry.registerBlock(MPBlocks.FRONOS_STONE);
        BlocksItemsRegistry.registerBlock(MPBlocks.FRONOS_COBBLESTONE);
        BlocksItemsRegistry.registerBlock(MPBlocks.FRONOS_STONE_BRICKS);
        BlocksItemsRegistry.registerBlock(MPBlocks.FRONOS_MOSSY_STONE_BRICKS);
        BlocksItemsRegistry.registerBlock(MPBlocks.FRONOS_CRACKED_STONE_BRICKS);
        BlocksItemsRegistry.registerBlock(MPBlocks.FRONOS_CHISELED_STONE_BRICKS);
        BlocksItemsRegistry.registerBlock(MPBlocks.FRONOS_DUNGEON_BRICK);

        // Fronos Ore
        BlocksItemsRegistry.registerBlock(MPBlocks.FRONOS_IRON_ORE);
        BlocksItemsRegistry.registerBlock(MPBlocks.FRONOS_GOLD_ORE);
        BlocksItemsRegistry.registerBlock(MPBlocks.FRONOS_TIN_ORE);
        BlocksItemsRegistry.registerBlock(MPBlocks.FRONOS_COPPER_ORE);
        BlocksItemsRegistry.registerBlock(MPBlocks.FRONOS_ALUMINUM_ORE);
        BlocksItemsRegistry.registerBlock(MPBlocks.FRONOS_LEAD_ORE);
        BlocksItemsRegistry.registerBlock(MPBlocks.FRONOS_COAL_ORE);
        BlocksItemsRegistry.registerBlock(MPBlocks.FRONOS_LAPIS_ORE);
        BlocksItemsRegistry.registerBlock(MPBlocks.FRONOS_DIAMOND_ORE);
        BlocksItemsRegistry.registerBlock(MPBlocks.FRONOS_EMERALD_ORE);
        BlocksItemsRegistry.registerBlock(MPBlocks.FRONOS_REDSTONE_ORE);
        BlocksItemsRegistry.registerBlock(MPBlocks.FRONOS_SILICON_ORE);
        BlocksItemsRegistry.registerBlock(MPBlocks.FRONOS_QUARTZ_ORE);
        BlocksItemsRegistry.registerBlock(MPBlocks.EXTRAILONITE_ORE);

        // Candy Cane
        BlocksItemsRegistry.registerBlock(MPBlocks.RED_CANDY_CANE);
        BlocksItemsRegistry.registerBlock(MPBlocks.GREEN_CANDY_CANE);
        BlocksItemsRegistry.registerBlock(MPBlocks.BLUE_CANDY_CANE);
        BlocksItemsRegistry.registerBlock(MPBlocks.ORANGE_CANDY_CANE);
        BlocksItemsRegistry.registerBlock(MPBlocks.PINK_CANDY_CANE);
        BlocksItemsRegistry.registerBlock(MPBlocks.YELLOW_CANDY_CANE);
        BlocksItemsRegistry.registerBlock(MPBlocks.PURPLE_CANDY_CANE);
        BlocksItemsRegistry.registerBlock(MPBlocks.RAINBOW_CANDY_CANE);

        // Jelly Block
        BlocksItemsRegistry.registerBlock(MPBlocks.GRAPE_JELLY_BLOCK);
        BlocksItemsRegistry.registerBlock(MPBlocks.RASPBERRY_JELLY_BLOCK);
        BlocksItemsRegistry.registerBlock(MPBlocks.STRAWBERRY_JELLY_BLOCK);
        BlocksItemsRegistry.registerBlock(MPBlocks.BERRY_JELLY_BLOCK);
        BlocksItemsRegistry.registerBlock(MPBlocks.LIME_JELLY_BLOCK);
        BlocksItemsRegistry.registerBlock(MPBlocks.ORANGE_JELLY_BLOCK);
        BlocksItemsRegistry.registerBlock(MPBlocks.GREEN_JELLY_BLOCK);
        BlocksItemsRegistry.registerBlock(MPBlocks.LEMON_JELLY_BLOCK);
    }

    private static void postRegister()
    {
        BlockUtils.setBlockHarvestLevel(MPBlocks.WARP_PAD_DUMMY, EnumHarvestLevel.PICKAXE, 2);
        BlockUtils.setBlockHarvestLevel(MPBlocks.DER_SOLAR1_DUMMY, EnumHarvestLevel.PICKAXE, 2);
        BlockUtils.setBlockHarvestLevel(MPBlocks.DER_SOLAR2_DUMMY, EnumHarvestLevel.PICKAXE, 2);
        BlockUtils.setBlockHarvestLevel(MPBlocks.DER_SOLAR3_DUMMY, EnumHarvestLevel.PICKAXE, 2);
        BlockUtils.setBlockHarvestLevel(MPBlocks.DER_SOLAR4_DUMMY, EnumHarvestLevel.PICKAXE, 2);
        BlockUtils.setBlockHarvestLevel(MPBlocks.NWT_MIDDLE_DUMMY, EnumHarvestLevel.PICKAXE, 2);
        BlockUtils.setBlockHarvestLevel(MPBlocks.NWT_TOP_DUMMY, EnumHarvestLevel.PICKAXE, 2);
        BlockUtils.setBlockHarvestLevel(MPBlocks.SHIELD_GENERATOR_DUMMY, EnumHarvestLevel.PICKAXE, 2);
        BlockUtils.setBlockHarvestLevel(MPBlocks.DARK_ENERGY_STORAGE_CLUSTER, EnumHarvestLevel.PICKAXE, 2);
        BlockUtils.setBlockHarvestLevel(MPBlocks.NUCLEAR_WASTE_ENERGY_STORAGE_CLUSTER, EnumHarvestLevel.PICKAXE, 2);
        BlockUtils.setBlockHarvestLevel(MPBlocks.POLISHED_TIN_DECORATION_BLOCK, EnumHarvestLevel.PICKAXE, 0);
        BlockUtils.setBlockHarvestLevel(MPBlocks.POLISHED_ALUMINUM_DECORATION_BLOCK, EnumHarvestLevel.PICKAXE, 0);
        BlockUtils.setBlockHarvestLevel(MPBlocks.DESH_FRAME, EnumHarvestLevel.PICKAXE, 1);
        BlockUtils.setBlockHarvestLevel(MPBlocks.ALIEN_CHEST, EnumHarvestLevel.PICKAXE, 1);
        BlockUtils.setBlockHarvestLevel(MPBlocks.DIONA_COBBLESTONE_SLAB, EnumHarvestLevel.PICKAXE, 0);
        BlockUtils.setBlockHarvestLevel(MPBlocks.CHALOS_COBBLESTONE_SLAB, EnumHarvestLevel.PICKAXE, 0);
        BlockUtils.setBlockHarvestLevel(MPBlocks.NIBIRU_COBBLESTONE_SLAB, EnumHarvestLevel.PICKAXE, 0);
        BlockUtils.setBlockHarvestLevel(MPBlocks.DIONA_DUNGEON_BRICK_SLAB, EnumHarvestLevel.PICKAXE, 1);
        BlockUtils.setBlockHarvestLevel(MPBlocks.CHALOS_DUNGEON_BRICK_SLAB, EnumHarvestLevel.PICKAXE, 1);
        BlockUtils.setBlockHarvestLevel(MPBlocks.NIBIRU_DUNGEON_BRICK_SLAB, EnumHarvestLevel.PICKAXE, 1);
        BlockUtils.setBlockHarvestLevel(MPBlocks.ALIEN_SHIP_DECORATION_SLAB, EnumHarvestLevel.PICKAXE, 1);
        BlockUtils.setBlockHarvestLevel(MPBlocks.DOUBLE_DIONA_COBBLESTONE_SLAB, EnumHarvestLevel.PICKAXE, 0);
        BlockUtils.setBlockHarvestLevel(MPBlocks.DOUBLE_CHALOS_COBBLESTONE_SLAB, EnumHarvestLevel.PICKAXE, 0);
        BlockUtils.setBlockHarvestLevel(MPBlocks.DOUBLE_NIBIRU_COBBLESTONE_SLAB, EnumHarvestLevel.PICKAXE, 0);
        BlockUtils.setBlockHarvestLevel(MPBlocks.DOUBLE_DIONA_DUNGEON_BRICK_SLAB, EnumHarvestLevel.PICKAXE, 1);
        BlockUtils.setBlockHarvestLevel(MPBlocks.DOUBLE_CHALOS_DUNGEON_BRICK_SLAB, EnumHarvestLevel.PICKAXE, 1);
        BlockUtils.setBlockHarvestLevel(MPBlocks.DOUBLE_NIBIRU_DUNGEON_BRICK_SLAB, EnumHarvestLevel.PICKAXE, 1);
        BlockUtils.setBlockHarvestLevel(MPBlocks.DOUBLE_ALIEN_SHIP_DECORATION_SLAB, EnumHarvestLevel.PICKAXE, 1);
        BlockUtils.setBlockHarvestLevel(MPBlocks.CHEESE_SPORE_SLAB, EnumHarvestLevel.AXE, 0);
        BlockUtils.setBlockHarvestLevel(MPBlocks.INFECTED_OAK_SLAB, EnumHarvestLevel.AXE, 0);
        BlockUtils.setBlockHarvestLevel(MPBlocks.ALIEN_BERRY_OAK_SLAB, EnumHarvestLevel.AXE, 0);
        BlockUtils.setBlockHarvestLevel(MPBlocks.DOUBLE_CHEESE_SPORE_SLAB, EnumHarvestLevel.AXE, 0);
        BlockUtils.setBlockHarvestLevel(MPBlocks.DOUBLE_INFECTED_OAK_SLAB, EnumHarvestLevel.AXE, 0);
        BlockUtils.setBlockHarvestLevel(MPBlocks.DOUBLE_ALIEN_BERRY_OAK_SLAB, EnumHarvestLevel.AXE, 0);
        BlockUtils.setBlockHarvestLevel(MPBlocks.INFECTED_OAK_PRESSURE_PLATE, EnumHarvestLevel.AXE, 0);
        BlockUtils.setBlockHarvestLevel(MPBlocks.ALIEN_BERRY_OAK_PRESSURE_PLATE, EnumHarvestLevel.AXE, 0);
        BlockUtils.setBlockHarvestLevel(MPBlocks.DIONA_COBBLESTONE_WALL, EnumHarvestLevel.PICKAXE, 0);
        BlockUtils.setBlockHarvestLevel(MPBlocks.CHALOS_COBBLESTONE_WALL, EnumHarvestLevel.PICKAXE, 0);
        BlockUtils.setBlockHarvestLevel(MPBlocks.NIBIRU_COBBLESTONE_WALL, EnumHarvestLevel.PICKAXE, 0);
        BlockUtils.setBlockHarvestLevel(MPBlocks.DIONA_DUNGEON_BRICK_WALL, EnumHarvestLevel.PICKAXE, 1);
        BlockUtils.setBlockHarvestLevel(MPBlocks.CHALOS_DUNGEON_BRICK_WALL, EnumHarvestLevel.PICKAXE, 1);
        BlockUtils.setBlockHarvestLevel(MPBlocks.NIBIRU_DUNGEON_BRICK_WALL, EnumHarvestLevel.PICKAXE, 1);
        BlockUtils.setBlockHarvestLevel(MPBlocks.SPACE_WARP_PAD, EnumHarvestLevel.PICKAXE, 2);
        BlockUtils.setBlockHarvestLevel(MPBlocks.SPACE_WARP_PAD_FULL, EnumHarvestLevel.PICKAXE, 2);
        BlockUtils.setBlockHarvestLevel(MPBlocks.DARK_ENERGY_RECEIVER, EnumHarvestLevel.PICKAXE, 2);
        BlockUtils.setBlockHarvestLevel(MPBlocks.SHIELD_GENERATOR, EnumHarvestLevel.PICKAXE, 2);
        BlockUtils.setBlockHarvestLevel(MPBlocks.DIONA_SURFACE_ROCK, EnumHarvestLevel.PICKAXE, 0);
        BlockUtils.setBlockHarvestLevel(MPBlocks.DIONA_SUB_SURFACE_ROCK, EnumHarvestLevel.PICKAXE, 0);
        BlockUtils.setBlockHarvestLevel(MPBlocks.DIONA_ROCK, EnumHarvestLevel.PICKAXE, 0);
        BlockUtils.setBlockHarvestLevel(MPBlocks.DIONA_COBBLESTONE, EnumHarvestLevel.PICKAXE, 0);
        BlockUtils.setBlockHarvestLevel(MPBlocks.SETRORIUM_ORE, EnumHarvestLevel.PICKAXE, 2);
        BlockUtils.setBlockHarvestLevel(MPBlocks.ILLENIUM_ORE, EnumHarvestLevel.PICKAXE, 2);
        BlockUtils.setBlockHarvestLevel(MPBlocks.DIONA_TIN_ORE, EnumHarvestLevel.PICKAXE, 1);
        BlockUtils.setBlockHarvestLevel(MPBlocks.DIONA_COPPER_ORE, EnumHarvestLevel.PICKAXE, 1);
        BlockUtils.setBlockHarvestLevel(MPBlocks.DIONA_ALUMINUM_ORE, EnumHarvestLevel.PICKAXE, 1);
        BlockUtils.setBlockHarvestLevel(MPBlocks.SETRORIUM_BLOCK, EnumHarvestLevel.PICKAXE, 1);
        BlockUtils.setBlockHarvestLevel(MPBlocks.ILLENIUM_BLOCK, EnumHarvestLevel.PICKAXE, 1);
        BlockUtils.setBlockHarvestLevel(MPBlocks.DIONA_DUNGEON_BRICK, EnumHarvestLevel.PICKAXE, 1);
        BlockUtils.setBlockHarvestLevel(MPBlocks.ALBETIUS_WORM_EGG_ROCK, EnumHarvestLevel.PICKAXE, 0);
        BlockUtils.setBlockHarvestLevel(MPBlocks.ALIEN_MINER_BLOOD, EnumHarvestLevel.PICKAXE, 0);
        BlockUtils.setBlockHarvestLevel(MPBlocks.DIONA_COBBLESTONE_STAIRS, EnumHarvestLevel.PICKAXE, 0);
        BlockUtils.setBlockHarvestLevel(MPBlocks.DIONA_DUNGEON_BRICK_STAIRS, EnumHarvestLevel.PICKAXE, 1);
        BlockUtils.setBlockHarvestLevel(MPBlocks.ALIEN_SHIP_DECORATION_STAIRS, EnumHarvestLevel.PICKAXE, 1);
        BlockUtils.setBlockHarvestLevel(MPBlocks.CRASHED_ALIEN_PROBE, EnumHarvestLevel.PICKAXE, 2);
        BlockUtils.setBlockHarvestLevel(MPBlocks.GLOWING_IRON_BLOCK, EnumHarvestLevel.PICKAXE, 2);
        BlockUtils.setBlockHarvestLevel(MPBlocks.DARK_ENERGY_GENERATOR, EnumHarvestLevel.PICKAXE, 2);
        BlockUtils.setBlockHarvestLevel(MPBlocks.INFECTED_PURLONITE_CRYSTAL, EnumHarvestLevel.PICKAXE, 3);
        BlockUtils.setBlockHarvestLevel(MPBlocks.INFECTED_PURLONITE_PLANKS, EnumHarvestLevel.AXE, 0);
        BlockUtils.setBlockHarvestLevel(MPBlocks.INFECTED_PURLONITE_FENCE, EnumHarvestLevel.AXE, 0);
        BlockUtils.setBlockHarvestLevel(MPBlocks.DIONA_ANCIENT_CHEST, EnumHarvestLevel.AXE, 0);
        BlockUtils.setBlockHarvestLevel(MPBlocks.ANTI_GRAVITY_ORE, EnumHarvestLevel.PICKAXE, 2);
        BlockUtils.setBlockHarvestLevel(MPBlocks.GOLDENITE_CRYSTALS_ORE, EnumHarvestLevel.PICKAXE, 2);
        BlockUtils.setBlockHarvestLevel(MPBlocks.CREEP_BLOCK, EnumHarvestLevel.SHOVEL, 0);
        BlockUtils.setBlockHarvestLevel(MPBlocks.GRAVITY_CREEP_BLOCK, EnumHarvestLevel.SHOVEL, 0);
        BlockUtils.setBlockHarvestLevel(MPBlocks.GRAVITY_CREEP_EXTRACTOR, EnumHarvestLevel.SHOVEL, 0);
        BlockUtils.setBlockHarvestLevel(MPBlocks.KOENTUS_REGOLITH, EnumHarvestLevel.PICKAXE, 1);
        BlockUtils.setBlockHarvestLevel(MPBlocks.KOENTUS_FINE_REGOLITH, EnumHarvestLevel.PICKAXE, 1);
        BlockUtils.setBlockHarvestLevel(MPBlocks.KOENTUS_ROCK, EnumHarvestLevel.PICKAXE, 1);
        BlockUtils.setBlockHarvestLevel(MPBlocks.KOENTUS_COBBLESTONE, EnumHarvestLevel.PICKAXE, 1);
        BlockUtils.setBlockHarvestLevel(MPBlocks.KOENTUS_TIN_ORE, EnumHarvestLevel.PICKAXE, 1);
        BlockUtils.setBlockHarvestLevel(MPBlocks.KOENTUS_COPPER_ORE, EnumHarvestLevel.PICKAXE, 1);
        BlockUtils.setBlockHarvestLevel(MPBlocks.KOENTUS_ALUMINUM_ORE, EnumHarvestLevel.PICKAXE, 1);
        BlockUtils.setBlockHarvestLevel(MPBlocks.KOENTUS_IRON_ORE, EnumHarvestLevel.PICKAXE, 1);
        BlockUtils.setBlockHarvestLevel(MPBlocks.ANTI_GRAVITY_FRAGMENTS_BLOCK, EnumHarvestLevel.PICKAXE, 1);
        BlockUtils.setBlockHarvestLevel(MPBlocks.GOLDENITE_CRYSTALS_BLOCK, EnumHarvestLevel.PICKAXE, 1);
        BlockUtils.setBlockHarvestLevel(MPBlocks.KOENTUS_DUNGEON_BRICK, EnumHarvestLevel.PICKAXE, 1);
        BlockUtils.setBlockHarvestLevel(MPBlocks.KOENTUS_ICE, EnumHarvestLevel.PICKAXE, 1);
        BlockUtils.setBlockHarvestLevel(MPBlocks.FALLEN_KOENTUS_METEOR, EnumHarvestLevel.PICKAXE, 2);
        BlockUtils.setBlockHarvestLevel(MPBlocks.WHITE_GLOWING_HARDENED_ICE, EnumHarvestLevel.PICKAXE, 2);
        BlockUtils.setBlockHarvestLevel(MPBlocks.ORANGE_GLOWING_HARDENED_ICE, EnumHarvestLevel.PICKAXE, 2);
        BlockUtils.setBlockHarvestLevel(MPBlocks.MAGENTA_GLOWING_HARDENED_ICE, EnumHarvestLevel.PICKAXE, 2);
        BlockUtils.setBlockHarvestLevel(MPBlocks.LIGHT_BLUE_GLOWING_HARDENED_ICE, EnumHarvestLevel.PICKAXE, 2);
        BlockUtils.setBlockHarvestLevel(MPBlocks.YELLOW_GLOWING_HARDENED_ICE, EnumHarvestLevel.PICKAXE, 2);
        BlockUtils.setBlockHarvestLevel(MPBlocks.LIME_GLOWING_HARDENED_ICE, EnumHarvestLevel.PICKAXE, 2);
        BlockUtils.setBlockHarvestLevel(MPBlocks.PINK_GLOWING_HARDENED_ICE, EnumHarvestLevel.PICKAXE, 2);
        BlockUtils.setBlockHarvestLevel(MPBlocks.GRAY_GLOWING_HARDENED_ICE, EnumHarvestLevel.PICKAXE, 2);
        BlockUtils.setBlockHarvestLevel(MPBlocks.SILVER_GLOWING_HARDENED_ICE, EnumHarvestLevel.PICKAXE, 2);
        BlockUtils.setBlockHarvestLevel(MPBlocks.CYAN_GLOWING_HARDENED_ICE, EnumHarvestLevel.PICKAXE, 2);
        BlockUtils.setBlockHarvestLevel(MPBlocks.PURPLE_GLOWING_HARDENED_ICE, EnumHarvestLevel.PICKAXE, 2);
        BlockUtils.setBlockHarvestLevel(MPBlocks.BLUE_GLOWING_HARDENED_ICE, EnumHarvestLevel.PICKAXE, 2);
        BlockUtils.setBlockHarvestLevel(MPBlocks.BROWN_GLOWING_HARDENED_ICE, EnumHarvestLevel.PICKAXE, 2);
        BlockUtils.setBlockHarvestLevel(MPBlocks.GREEN_GLOWING_HARDENED_ICE, EnumHarvestLevel.PICKAXE, 2);
        BlockUtils.setBlockHarvestLevel(MPBlocks.RED_GLOWING_HARDENED_ICE, EnumHarvestLevel.PICKAXE, 2);
        BlockUtils.setBlockHarvestLevel(MPBlocks.BLACK_GLOWING_HARDENED_ICE, EnumHarvestLevel.PICKAXE, 2);
        BlockUtils.setBlockHarvestLevel(MPBlocks.CHALOS_ROCK, EnumHarvestLevel.PICKAXE, 0);
        BlockUtils.setBlockHarvestLevel(MPBlocks.CHALOS_COBBLESTONE, EnumHarvestLevel.PICKAXE, 0);
        BlockUtils.setBlockHarvestLevel(MPBlocks.DIREMSIUM_ORE, EnumHarvestLevel.PICKAXE, 2);
        BlockUtils.setBlockHarvestLevel(MPBlocks.ZYPTORIUM_ORE, EnumHarvestLevel.PICKAXE, 2);
        BlockUtils.setBlockHarvestLevel(MPBlocks.CHEESE_MILK_ORE, EnumHarvestLevel.PICKAXE, 1);
        BlockUtils.setBlockHarvestLevel(MPBlocks.CHALOS_IRON_ORE, EnumHarvestLevel.PICKAXE, 1);
        BlockUtils.setBlockHarvestLevel(MPBlocks.CHALOS_TIN_ORE, EnumHarvestLevel.PICKAXE, 1);
        BlockUtils.setBlockHarvestLevel(MPBlocks.CHALOS_COPPER_ORE, EnumHarvestLevel.PICKAXE, 1);
        BlockUtils.setBlockHarvestLevel(MPBlocks.CHALOS_ALUMINUM_ORE, EnumHarvestLevel.PICKAXE, 1);
        BlockUtils.setBlockHarvestLevel(MPBlocks.DIREMSIUM_BLOCK, EnumHarvestLevel.PICKAXE, 1);
        BlockUtils.setBlockHarvestLevel(MPBlocks.ZYPTORIUM_BLOCK, EnumHarvestLevel.PICKAXE, 1);
        BlockUtils.setBlockHarvestLevel(MPBlocks.CHALOS_DUNGEON_BRICK, EnumHarvestLevel.PICKAXE, 1);
        BlockUtils.setBlockHarvestLevel(MPBlocks.CHEESE_DIRT, EnumHarvestLevel.SHOVEL, 0);
        BlockUtils.setBlockHarvestLevel(MPBlocks.CHEESE_COARSE_DIRT, EnumHarvestLevel.SHOVEL, 0);
        BlockUtils.setBlockHarvestLevel(MPBlocks.CHALOS_COBBLESTONE_STAIRS, EnumHarvestLevel.PICKAXE, 0);
        BlockUtils.setBlockHarvestLevel(MPBlocks.CHALOS_DUNGEON_BRICK_STAIRS, EnumHarvestLevel.PICKAXE, 1);
        BlockUtils.setBlockHarvestLevel(MPBlocks.CHEESE_GRASS_BLOCK, EnumHarvestLevel.SHOVEL, 0);
        BlockUtils.setBlockHarvestLevel(MPBlocks.CHEESE_FARMLAND, EnumHarvestLevel.SHOVEL, 0);
        BlockUtils.setBlockHarvestLevel(MPBlocks.CHALOS_ANCIENT_CHEST, EnumHarvestLevel.AXE, 0);
        BlockUtils.setBlockHarvestLevel(MPBlocks.CHEESE_SPORE_STEM, EnumHarvestLevel.AXE, 0);
        BlockUtils.setBlockHarvestLevel(MPBlocks.CHEESE_SPORE_PLANKS, EnumHarvestLevel.AXE, 0);
        BlockUtils.setBlockHarvestLevel(MPBlocks.CHEESE_SPORE_CRAFTING_TABLE, EnumHarvestLevel.AXE, 0);
        BlockUtils.setBlockHarvestLevel(MPBlocks.CHEESE_SPORE_CHEST, EnumHarvestLevel.AXE, 0);
        BlockUtils.setBlockHarvestLevel(MPBlocks.CHEESE_SPORE_STAIRS, EnumHarvestLevel.AXE, 0);
        BlockUtils.setBlockHarvestLevel(MPBlocks.CHEESE_SPORE_FENCE, EnumHarvestLevel.AXE, 0);
        BlockUtils.setBlockHarvestLevel(MPBlocks.CHEESE_SPORE_FENCE_GATE, EnumHarvestLevel.AXE, 0);
        BlockUtils.setBlockHarvestLevel(MPBlocks.CHEESE_SPORE_DOOR, EnumHarvestLevel.AXE, 0);
        BlockUtils.setBlockHarvestLevel(MPBlocks.INFECTED_GRASS_BLOCK, EnumHarvestLevel.SHOVEL, 0);
        BlockUtils.setBlockHarvestLevel(MPBlocks.INFECTED_DIRT, EnumHarvestLevel.SHOVEL, 0);
        BlockUtils.setBlockHarvestLevel(MPBlocks.INFECTED_COARSE_DIRT, EnumHarvestLevel.SHOVEL, 0);
        BlockUtils.setBlockHarvestLevel(MPBlocks.INFECTED_PODZOL, EnumHarvestLevel.SHOVEL, 0);
        BlockUtils.setBlockHarvestLevel(MPBlocks.INFECTED_FARMLAND, EnumHarvestLevel.SHOVEL, 0);
        BlockUtils.setBlockHarvestLevel(MPBlocks.INFECTED_SAND, EnumHarvestLevel.SHOVEL, 0);
        BlockUtils.setBlockHarvestLevel(MPBlocks.INFECTED_SNOW, EnumHarvestLevel.SHOVEL, 0);
        BlockUtils.setBlockHarvestLevel(MPBlocks.PURIFIED_SNOW, EnumHarvestLevel.SHOVEL, 0);
        BlockUtils.setBlockHarvestLevel(MPBlocks.INFECTED_SNOW_LAYER, EnumHarvestLevel.SHOVEL, 0);
        BlockUtils.setBlockHarvestLevel(MPBlocks.PURIFIED_SNOW_LAYER, EnumHarvestLevel.SHOVEL, 0);
        BlockUtils.setBlockHarvestLevel(MPBlocks.GREEN_VEIN_GRASS_BLOCK, EnumHarvestLevel.SHOVEL, 0);
        BlockUtils.setBlockHarvestLevel(MPBlocks.INFECTED_GRAVEL, EnumHarvestLevel.SHOVEL, 0);
        BlockUtils.setBlockHarvestLevel(MPBlocks.INFECTED_CLAY, EnumHarvestLevel.SHOVEL, 0);
        BlockUtils.setBlockHarvestLevel(MPBlocks.PURIFIED_GRAVEL, EnumHarvestLevel.SHOVEL, 0);
        BlockUtils.setBlockHarvestLevel(MPBlocks.INFECTED_GRASS_PATH, EnumHarvestLevel.SHOVEL, 0);
        BlockUtils.setBlockHarvestLevel(MPBlocks.GREEN_VEIN_GRASS_PATH, EnumHarvestLevel.SHOVEL, 0);
        BlockUtils.setBlockHarvestLevel(MPBlocks.INFECTED_SANDSTONE, EnumHarvestLevel.PICKAXE, 0);
        BlockUtils.setBlockHarvestLevel(MPBlocks.INFECTED_CHISELED_SANDSTONE, EnumHarvestLevel.PICKAXE, 0);
        BlockUtils.setBlockHarvestLevel(MPBlocks.INFECTED_CUT_SANDSTONE, EnumHarvestLevel.PICKAXE, 0);
        BlockUtils.setBlockHarvestLevel(MPBlocks.NIBIRU_COBBLESTONE_STAIRS, EnumHarvestLevel.PICKAXE, 0);
        BlockUtils.setBlockHarvestLevel(MPBlocks.INFECTED_STONE_BRICKS_STAIRS, EnumHarvestLevel.PICKAXE, 0);
        BlockUtils.setBlockHarvestLevel(MPBlocks.INFECTED_SANDSTONE_STAIRS, EnumHarvestLevel.PICKAXE, 0);
        BlockUtils.setBlockHarvestLevel(MPBlocks.INFECTED_PACKED_ICE, EnumHarvestLevel.PICKAXE, 0);
        BlockUtils.setBlockHarvestLevel(MPBlocks.INFECTED_PRISMARINE, EnumHarvestLevel.PICKAXE, 0);
        BlockUtils.setBlockHarvestLevel(MPBlocks.INFECTED_PRISMARINE_BRICKS, EnumHarvestLevel.PICKAXE, 0);
        BlockUtils.setBlockHarvestLevel(MPBlocks.INFECTED_DARK_PRISMARINE, EnumHarvestLevel.PICKAXE, 0);
        BlockUtils.setBlockHarvestLevel(MPBlocks.INFECTED_FURNACE, EnumHarvestLevel.PICKAXE, 0);
        BlockUtils.setBlockHarvestLevel(MPBlocks.OIL_ORE, EnumHarvestLevel.PICKAXE, 0);
        BlockUtils.setBlockHarvestLevel(MPBlocks.INFECTED_PRISMARINE_STAIRS, EnumHarvestLevel.PICKAXE, 0);
        BlockUtils.setBlockHarvestLevel(MPBlocks.INFECTED_PRISMARINE_BRICK_STAIRS, EnumHarvestLevel.PICKAXE, 0);
        BlockUtils.setBlockHarvestLevel(MPBlocks.INFECTED_DARK_PRISMARINE_STAIRS, EnumHarvestLevel.PICKAXE, 0);
        BlockUtils.setBlockHarvestLevel(MPBlocks.INFECTED_VEIN_STONE_BRICKS_STAIRS, EnumHarvestLevel.PICKAXE, 0);
        BlockUtils.setBlockHarvestLevel(MPBlocks.CRACKED_INFECTED_STONE_BRICKS_STAIRS, EnumHarvestLevel.PICKAXE, 0);
        BlockUtils.setBlockHarvestLevel(MPBlocks.INFECTED_CUT_SANDSTONE_STAIRS, EnumHarvestLevel.PICKAXE, 0);
        BlockUtils.setBlockHarvestLevel(MPBlocks.INFECTED_PRISMARINE_SLAB, EnumHarvestLevel.PICKAXE, 0);
        BlockUtils.setBlockHarvestLevel(MPBlocks.INFECTED_PRISMARINE_BRICK_SLAB, EnumHarvestLevel.PICKAXE, 0);
        BlockUtils.setBlockHarvestLevel(MPBlocks.INFECTED_DARK_PRISMARINE_SLAB, EnumHarvestLevel.PICKAXE, 0);
        BlockUtils.setBlockHarvestLevel(MPBlocks.INFECTED_STONE_BRICKS_SLAB, EnumHarvestLevel.PICKAXE, 0);
        BlockUtils.setBlockHarvestLevel(MPBlocks.INFECTED_VEIN_STONE_BRICKS_SLAB, EnumHarvestLevel.PICKAXE, 0);
        BlockUtils.setBlockHarvestLevel(MPBlocks.INFECTED_CRACKED_STONE_BRICKS_SLAB, EnumHarvestLevel.PICKAXE, 0);
        BlockUtils.setBlockHarvestLevel(MPBlocks.TERRASTONE_SLAB, EnumHarvestLevel.PICKAXE, 0);
        BlockUtils.setBlockHarvestLevel(MPBlocks.INFECTED_SANDSTONE_SLAB, EnumHarvestLevel.PICKAXE, 0);
        BlockUtils.setBlockHarvestLevel(MPBlocks.INFECTED_CUT_SANDSTONE_SLAB, EnumHarvestLevel.PICKAXE, 0);
        BlockUtils.setBlockHarvestLevel(MPBlocks.DOUBLE_INFECTED_PRISMARINE_SLAB, EnumHarvestLevel.PICKAXE, 0);
        BlockUtils.setBlockHarvestLevel(MPBlocks.DOUBLE_INFECTED_PRISMARINE_BRICK_SLAB, EnumHarvestLevel.PICKAXE, 0);
        BlockUtils.setBlockHarvestLevel(MPBlocks.DOUBLE_INFECTED_DARK_PRISMARINE_SLAB, EnumHarvestLevel.PICKAXE, 0);
        BlockUtils.setBlockHarvestLevel(MPBlocks.DOUBLE_INFECTED_STONE_BRICKS_SLAB, EnumHarvestLevel.PICKAXE, 0);
        BlockUtils.setBlockHarvestLevel(MPBlocks.DOUBLE_INFECTED_VEIN_STONE_BRICKS_SLAB, EnumHarvestLevel.PICKAXE, 0);
        BlockUtils.setBlockHarvestLevel(MPBlocks.DOUBLE_INFECTED_CRACKED_STONE_BRICKS_SLAB, EnumHarvestLevel.PICKAXE, 0);
        BlockUtils.setBlockHarvestLevel(MPBlocks.DOUBLE_TERRASTONE_SLAB, EnumHarvestLevel.PICKAXE, 0);
        BlockUtils.setBlockHarvestLevel(MPBlocks.DOUBLE_INFECTED_SANDSTONE_SLAB, EnumHarvestLevel.PICKAXE, 0);
        BlockUtils.setBlockHarvestLevel(MPBlocks.DOUBLE_INFECTED_CUT_SANDSTONE_SLAB, EnumHarvestLevel.PICKAXE, 0);
        BlockUtils.setBlockHarvestLevel(MPBlocks.TERRASTONE, EnumHarvestLevel.PICKAXE, 0);
        BlockUtils.setBlockHarvestLevel(MPBlocks.TERRASTONE_STAIRS, EnumHarvestLevel.PICKAXE, 0);
        BlockUtils.setBlockHarvestLevel(MPBlocks.TERRASTONE_FURNACE, EnumHarvestLevel.PICKAXE, 0);
        BlockUtils.setBlockHarvestLevel(MPBlocks.SEALABLE_NUCLEAR_WASTE_ROD, EnumHarvestLevel.PICKAXE, 0);
        BlockUtils.setBlockHarvestLevel(MPBlocks.MULTALIC_CRYSTAL_BLOCK, EnumHarvestLevel.PICKAXE, 1);
        BlockUtils.setBlockHarvestLevel(MPBlocks.NIBIRU_DUNGEON_BRICK_STAIRS, EnumHarvestLevel.PICKAXE, 1);
        BlockUtils.setBlockHarvestLevel(MPBlocks.NUCLEAR_WASTE_GENERATOR, EnumHarvestLevel.PICKAXE, 2);
        BlockUtils.setBlockHarvestLevel(MPBlocks.NUCLEAR_WASTE_TANK, EnumHarvestLevel.PICKAXE, 2);
        BlockUtils.setBlockHarvestLevel(MPBlocks.MULTALIC_CRYSTAL, EnumHarvestLevel.PICKAXE, 2);
        BlockUtils.setBlockHarvestLevel(MPBlocks.INFECTED_OAK_LOG, EnumHarvestLevel.AXE, 0);
        BlockUtils.setBlockHarvestLevel(MPBlocks.INFECTED_DEADWOOD_LOG, EnumHarvestLevel.AXE, 0);
        BlockUtils.setBlockHarvestLevel(MPBlocks.INFECTED_JUNGLE_LOG, EnumHarvestLevel.AXE, 0);
        BlockUtils.setBlockHarvestLevel(MPBlocks.ALIEN_BERRY_OAK_LOG, EnumHarvestLevel.AXE, 0);
        BlockUtils.setBlockHarvestLevel(MPBlocks.INFECTED_OAK_PLANKS, EnumHarvestLevel.AXE, 0);
        BlockUtils.setBlockHarvestLevel(MPBlocks.ALIEN_BERRY_OAK_PLANKS, EnumHarvestLevel.AXE, 0);
        BlockUtils.setBlockHarvestLevel(MPBlocks.INFECTED_CRAFTING_TABLE, EnumHarvestLevel.AXE, 0);
        BlockUtils.setBlockHarvestLevel(MPBlocks.ALIEN_BERRY_CRAFTING_TABLE, EnumHarvestLevel.AXE, 0);
        BlockUtils.setBlockHarvestLevel(MPBlocks.INFECTED_OAK_BOOKSHELF, EnumHarvestLevel.AXE, 0);
        BlockUtils.setBlockHarvestLevel(MPBlocks.ALIEN_BERRY_OAK_BOOKSHELF, EnumHarvestLevel.AXE, 0);
        BlockUtils.setBlockHarvestLevel(MPBlocks.INFECTED_OAK_FENCE_GATE, EnumHarvestLevel.AXE, 0);
        BlockUtils.setBlockHarvestLevel(MPBlocks.INFECTED_OAK_FENCE, EnumHarvestLevel.AXE, 0);
        BlockUtils.setBlockHarvestLevel(MPBlocks.ALIEN_BERRY_OAK_FENCE, EnumHarvestLevel.AXE, 0);
        BlockUtils.setBlockHarvestLevel(MPBlocks.INFECTED_OAK_DOOR, EnumHarvestLevel.AXE, 0);
        BlockUtils.setBlockHarvestLevel(MPBlocks.ALIEN_BERRY_OAK_DOOR, EnumHarvestLevel.AXE, 0);
        BlockUtils.setBlockHarvestLevel(MPBlocks.NIBIRU_ANCIENT_CHEST, EnumHarvestLevel.AXE, 0);
        BlockUtils.setBlockHarvestLevel(MPBlocks.INFECTED_MELON, EnumHarvestLevel.AXE, 0);
        BlockUtils.setBlockHarvestLevel(MPBlocks.INFECTED_CHEST, EnumHarvestLevel.AXE, 0);
        BlockUtils.setBlockHarvestLevel(MPBlocks.ALIEN_BERRY_CHEST, EnumHarvestLevel.AXE, 0);
        BlockUtils.setBlockHarvestLevel(MPBlocks.INFECTED_OAK_STAIRS, EnumHarvestLevel.AXE, 0);
        BlockUtils.setBlockHarvestLevel(MPBlocks.ALIEN_BERRY_OAK_STAIRS, EnumHarvestLevel.AXE, 0);
        BlockUtils.setBlockHarvestLevel(MPBlocks.ALIEN_BERRY_OAK_FENCE_GATE, EnumHarvestLevel.AXE, 0);
        BlockUtils.setBlockHarvestLevel(MPBlocks.HUGE_TERRASHROOM_BLOCK, EnumHarvestLevel.AXE, 0);
        BlockUtils.setBlockHarvestLevel(MPBlocks.NIBIRU_ROCK, EnumHarvestLevel.PICKAXE, 0);
        BlockUtils.setBlockHarvestLevel(MPBlocks.NIBIRU_COBBLESTONE, EnumHarvestLevel.PICKAXE, 0);
        BlockUtils.setBlockHarvestLevel(MPBlocks.NIBIRU_VEIN_COBBLESTONE, EnumHarvestLevel.PICKAXE, 0);
        BlockUtils.setBlockHarvestLevel(MPBlocks.INFECTED_STONE_BRICKS, EnumHarvestLevel.PICKAXE, 0);
        BlockUtils.setBlockHarvestLevel(MPBlocks.INFECTED_VEIN_STONE_BRICKS, EnumHarvestLevel.PICKAXE, 0);
        BlockUtils.setBlockHarvestLevel(MPBlocks.INFECTED_CRACKED_STONE_BRICKS, EnumHarvestLevel.PICKAXE, 0);
        BlockUtils.setBlockHarvestLevel(MPBlocks.INFECTED_CHISELED_STONE_BRICKS, EnumHarvestLevel.PICKAXE, 0);
        BlockUtils.setBlockHarvestLevel(MPBlocks.INFERUMITE_BLOCK, EnumHarvestLevel.PICKAXE, 0);
        BlockUtils.setBlockHarvestLevel(MPBlocks.NIBIRU_DUNGEON_BRICK, EnumHarvestLevel.PICKAXE, 0);
        BlockUtils.setBlockHarvestLevel(MPBlocks.MOSSY_NIBIRU_DUNGEON_BRICK, EnumHarvestLevel.PICKAXE, 0);
        BlockUtils.setBlockHarvestLevel(MPBlocks.INFECTED_COAL_ORE, EnumHarvestLevel.PICKAXE, 0);
        BlockUtils.setBlockHarvestLevel(MPBlocks.INFECTED_IRON_ORE, EnumHarvestLevel.PICKAXE, 1);
        BlockUtils.setBlockHarvestLevel(MPBlocks.INFECTED_TIN_ORE, EnumHarvestLevel.PICKAXE, 1);
        BlockUtils.setBlockHarvestLevel(MPBlocks.INFECTED_COPPER_ORE, EnumHarvestLevel.PICKAXE, 1);
        BlockUtils.setBlockHarvestLevel(MPBlocks.INFECTED_ALUMINUM_ORE, EnumHarvestLevel.PICKAXE, 1);
        BlockUtils.setBlockHarvestLevel(MPBlocks.INFECTED_LAPIS_ORE, EnumHarvestLevel.PICKAXE, 1);
        BlockUtils.setBlockHarvestLevel(MPBlocks.INFECTED_GOLD_ORE, EnumHarvestLevel.PICKAXE, 2);
        BlockUtils.setBlockHarvestLevel(MPBlocks.INFECTED_DIAMOND_ORE, EnumHarvestLevel.PICKAXE, 2);
        BlockUtils.setBlockHarvestLevel(MPBlocks.INFECTED_EMERALD_ORE, EnumHarvestLevel.PICKAXE, 2);
        BlockUtils.setBlockHarvestLevel(MPBlocks.INFECTED_REDSTONE_ORE, EnumHarvestLevel.PICKAXE, 2);
        BlockUtils.setBlockHarvestLevel(MPBlocks.INFECTED_SILICON_ORE, EnumHarvestLevel.PICKAXE, 2);
        BlockUtils.setBlockHarvestLevel(MPBlocks.INFERUMITE_CRYSTAL_ORE, EnumHarvestLevel.PICKAXE, 2);
        BlockUtils.setBlockHarvestLevel(MPBlocks.FRONOS_COAL_ORE, EnumHarvestLevel.PICKAXE, 0);
        BlockUtils.setBlockHarvestLevel(MPBlocks.FRONOS_QUARTZ_ORE, EnumHarvestLevel.PICKAXE, 0);
        BlockUtils.setBlockHarvestLevel(MPBlocks.FRONOS_IRON_ORE, EnumHarvestLevel.PICKAXE, 1);
        BlockUtils.setBlockHarvestLevel(MPBlocks.FRONOS_TIN_ORE, EnumHarvestLevel.PICKAXE, 1);
        BlockUtils.setBlockHarvestLevel(MPBlocks.FRONOS_COPPER_ORE, EnumHarvestLevel.PICKAXE, 1);
        BlockUtils.setBlockHarvestLevel(MPBlocks.FRONOS_ALUMINUM_ORE, EnumHarvestLevel.PICKAXE, 1);
        BlockUtils.setBlockHarvestLevel(MPBlocks.FRONOS_LEAD_ORE, EnumHarvestLevel.PICKAXE, 1);
        BlockUtils.setBlockHarvestLevel(MPBlocks.FRONOS_LAPIS_ORE, EnumHarvestLevel.PICKAXE, 1);
        BlockUtils.setBlockHarvestLevel(MPBlocks.FRONOS_GOLD_ORE, EnumHarvestLevel.PICKAXE, 2);
        BlockUtils.setBlockHarvestLevel(MPBlocks.FRONOS_DIAMOND_ORE, EnumHarvestLevel.PICKAXE, 2);
        BlockUtils.setBlockHarvestLevel(MPBlocks.FRONOS_EMERALD_ORE, EnumHarvestLevel.PICKAXE, 2);
        BlockUtils.setBlockHarvestLevel(MPBlocks.FRONOS_REDSTONE_ORE, EnumHarvestLevel.PICKAXE, 2);
        BlockUtils.setBlockHarvestLevel(MPBlocks.FRONOS_SILICON_ORE, EnumHarvestLevel.PICKAXE, 2);
        BlockUtils.setBlockHarvestLevel(MPBlocks.EXTRAILONITE_ORE, EnumHarvestLevel.PICKAXE, 2);
        BlockUtils.setBlockHarvestLevel(MPBlocks.FRONOS_STONE, EnumHarvestLevel.PICKAXE, 0);
        BlockUtils.setBlockHarvestLevel(MPBlocks.FRONOS_COBBLESTONE, EnumHarvestLevel.PICKAXE, 0);
        BlockUtils.setBlockHarvestLevel(MPBlocks.FRONOS_STONE_BRICKS, EnumHarvestLevel.PICKAXE, 0);
        BlockUtils.setBlockHarvestLevel(MPBlocks.FRONOS_MOSSY_STONE_BRICKS, EnumHarvestLevel.PICKAXE, 0);
        BlockUtils.setBlockHarvestLevel(MPBlocks.FRONOS_CRACKED_STONE_BRICKS, EnumHarvestLevel.PICKAXE, 0);
        BlockUtils.setBlockHarvestLevel(MPBlocks.FRONOS_CHISELED_STONE_BRICKS, EnumHarvestLevel.PICKAXE, 0);
        BlockUtils.setBlockHarvestLevel(MPBlocks.FRONOS_DUNGEON_BRICK, EnumHarvestLevel.PICKAXE, 1);
        BlockUtils.setBlockHarvestLevel(MPBlocks.FRONOS_GRASS_BLOCK, EnumHarvestLevel.SHOVEL, 0);
        BlockUtils.setBlockHarvestLevel(MPBlocks.FRONOS_DIRT, EnumHarvestLevel.SHOVEL, 0);
        BlockUtils.setBlockHarvestLevel(MPBlocks.FRONOS_COARSE_DIRT, EnumHarvestLevel.SHOVEL, 0);
        BlockUtils.setBlockHarvestLevel(MPBlocks.FRONOS_FARMLAND, EnumHarvestLevel.SHOVEL, 0);

        MPBlocks.DIONA_COBBLESTONE_SLAB.setHalf(MPBlocks.DIONA_COBBLESTONE_SLAB);
        MPBlocks.CHALOS_COBBLESTONE_SLAB.setHalf(MPBlocks.CHALOS_COBBLESTONE_SLAB);
        MPBlocks.NIBIRU_COBBLESTONE_SLAB.setHalf(MPBlocks.NIBIRU_COBBLESTONE_SLAB);
        MPBlocks.DIONA_DUNGEON_BRICK_SLAB.setHalf(MPBlocks.DIONA_DUNGEON_BRICK_SLAB);
        MPBlocks.CHALOS_DUNGEON_BRICK_SLAB.setHalf(MPBlocks.CHALOS_DUNGEON_BRICK_SLAB);
        MPBlocks.NIBIRU_DUNGEON_BRICK_SLAB.setHalf(MPBlocks.NIBIRU_DUNGEON_BRICK_SLAB);
        MPBlocks.ALIEN_SHIP_DECORATION_SLAB.setHalf(MPBlocks.ALIEN_SHIP_DECORATION_SLAB);
        MPBlocks.DIONA_COBBLESTONE_SLAB.setDouble(MPBlocks.DOUBLE_DIONA_COBBLESTONE_SLAB);
        MPBlocks.CHALOS_COBBLESTONE_SLAB.setDouble(MPBlocks.DOUBLE_CHALOS_COBBLESTONE_SLAB);
        MPBlocks.NIBIRU_COBBLESTONE_SLAB.setDouble(MPBlocks.DOUBLE_NIBIRU_COBBLESTONE_SLAB);
        MPBlocks.DIONA_DUNGEON_BRICK_SLAB.setDouble(MPBlocks.DOUBLE_DIONA_DUNGEON_BRICK_SLAB);
        MPBlocks.CHALOS_DUNGEON_BRICK_SLAB.setDouble(MPBlocks.DOUBLE_CHALOS_DUNGEON_BRICK_SLAB);
        MPBlocks.NIBIRU_DUNGEON_BRICK_SLAB.setDouble(MPBlocks.DOUBLE_NIBIRU_DUNGEON_BRICK_SLAB);
        MPBlocks.ALIEN_SHIP_DECORATION_SLAB.setDouble(MPBlocks.DOUBLE_ALIEN_SHIP_DECORATION_SLAB);

        MPBlocks.CHEESE_SPORE_SLAB.setHalf(MPBlocks.CHEESE_SPORE_SLAB);
        MPBlocks.INFECTED_OAK_SLAB.setHalf(MPBlocks.INFECTED_OAK_SLAB);
        MPBlocks.ALIEN_BERRY_OAK_SLAB.setHalf(MPBlocks.ALIEN_BERRY_OAK_SLAB);
        MPBlocks.CHEESE_SPORE_SLAB.setDouble(MPBlocks.DOUBLE_CHEESE_SPORE_SLAB);
        MPBlocks.INFECTED_OAK_SLAB.setDouble(MPBlocks.DOUBLE_INFECTED_OAK_SLAB);
        MPBlocks.ALIEN_BERRY_OAK_SLAB.setDouble(MPBlocks.DOUBLE_ALIEN_BERRY_OAK_SLAB);

        MPBlocks.DOUBLE_DIONA_COBBLESTONE_SLAB.setHalf(MPBlocks.DIONA_COBBLESTONE_SLAB);
        MPBlocks.DOUBLE_CHALOS_COBBLESTONE_SLAB.setHalf(MPBlocks.CHALOS_COBBLESTONE_SLAB);
        MPBlocks.DOUBLE_NIBIRU_COBBLESTONE_SLAB.setHalf(MPBlocks.NIBIRU_COBBLESTONE_SLAB);
        MPBlocks.DOUBLE_DIONA_DUNGEON_BRICK_SLAB.setHalf(MPBlocks.DIONA_DUNGEON_BRICK_SLAB);
        MPBlocks.DOUBLE_CHALOS_DUNGEON_BRICK_SLAB.setHalf(MPBlocks.CHALOS_DUNGEON_BRICK_SLAB);
        MPBlocks.DOUBLE_NIBIRU_DUNGEON_BRICK_SLAB.setHalf(MPBlocks.NIBIRU_DUNGEON_BRICK_SLAB);
        MPBlocks.DOUBLE_ALIEN_SHIP_DECORATION_SLAB.setHalf(MPBlocks.ALIEN_SHIP_DECORATION_SLAB);

        MPBlocks.DOUBLE_CHEESE_SPORE_SLAB.setHalf(MPBlocks.CHEESE_SPORE_SLAB);
        MPBlocks.DOUBLE_INFECTED_OAK_SLAB.setHalf(MPBlocks.INFECTED_OAK_SLAB);
        MPBlocks.DOUBLE_ALIEN_BERRY_OAK_SLAB.setHalf(MPBlocks.ALIEN_BERRY_OAK_SLAB);

        MPBlocks.INFECTED_PRISMARINE_SLAB.setHalf(MPBlocks.INFECTED_PRISMARINE_SLAB);
        MPBlocks.INFECTED_PRISMARINE_BRICK_SLAB.setHalf(MPBlocks.INFECTED_PRISMARINE_BRICK_SLAB);
        MPBlocks.INFECTED_DARK_PRISMARINE_SLAB.setHalf(MPBlocks.INFECTED_DARK_PRISMARINE_SLAB);
        MPBlocks.INFECTED_STONE_BRICKS_SLAB.setHalf(MPBlocks.INFECTED_STONE_BRICKS_SLAB);
        MPBlocks.INFECTED_VEIN_STONE_BRICKS_SLAB.setHalf(MPBlocks.INFECTED_VEIN_STONE_BRICKS_SLAB);
        MPBlocks.INFECTED_CRACKED_STONE_BRICKS_SLAB.setHalf(MPBlocks.INFECTED_CRACKED_STONE_BRICKS_SLAB);
        MPBlocks.TERRASTONE_SLAB.setHalf(MPBlocks.TERRASTONE_SLAB);
        MPBlocks.INFECTED_SANDSTONE_SLAB.setHalf(MPBlocks.INFECTED_SANDSTONE_SLAB);
        MPBlocks.INFECTED_CUT_SANDSTONE_SLAB.setHalf(MPBlocks.INFECTED_CUT_SANDSTONE_SLAB);

        MPBlocks.INFECTED_PRISMARINE_SLAB.setDouble(MPBlocks.DOUBLE_INFECTED_PRISMARINE_SLAB);
        MPBlocks.INFECTED_PRISMARINE_BRICK_SLAB.setDouble(MPBlocks.DOUBLE_INFECTED_PRISMARINE_BRICK_SLAB);
        MPBlocks.INFECTED_DARK_PRISMARINE_SLAB.setDouble(MPBlocks.DOUBLE_INFECTED_DARK_PRISMARINE_SLAB);
        MPBlocks.INFECTED_STONE_BRICKS_SLAB.setDouble(MPBlocks.DOUBLE_INFECTED_STONE_BRICKS_SLAB);
        MPBlocks.INFECTED_VEIN_STONE_BRICKS_SLAB.setDouble(MPBlocks.DOUBLE_INFECTED_VEIN_STONE_BRICKS_SLAB);
        MPBlocks.INFECTED_CRACKED_STONE_BRICKS_SLAB.setDouble(MPBlocks.DOUBLE_INFECTED_CRACKED_STONE_BRICKS_SLAB);
        MPBlocks.TERRASTONE_SLAB.setDouble(MPBlocks.DOUBLE_TERRASTONE_SLAB);
        MPBlocks.INFECTED_SANDSTONE_SLAB.setDouble(MPBlocks.DOUBLE_INFECTED_SANDSTONE_SLAB);
        MPBlocks.INFECTED_CUT_SANDSTONE_SLAB.setDouble(MPBlocks.DOUBLE_INFECTED_CUT_SANDSTONE_SLAB);

        MPBlocks.DOUBLE_INFECTED_PRISMARINE_SLAB.setHalf(MPBlocks.INFECTED_PRISMARINE_SLAB);
        MPBlocks.DOUBLE_INFECTED_PRISMARINE_BRICK_SLAB.setHalf(MPBlocks.INFECTED_PRISMARINE_BRICK_SLAB);
        MPBlocks.DOUBLE_INFECTED_DARK_PRISMARINE_SLAB.setHalf(MPBlocks.INFECTED_DARK_PRISMARINE_SLAB);
        MPBlocks.DOUBLE_INFECTED_STONE_BRICKS_SLAB.setHalf(MPBlocks.INFECTED_STONE_BRICKS_SLAB);
        MPBlocks.DOUBLE_INFECTED_VEIN_STONE_BRICKS_SLAB.setHalf(MPBlocks.INFECTED_VEIN_STONE_BRICKS_SLAB);
        MPBlocks.DOUBLE_INFECTED_CRACKED_STONE_BRICKS_SLAB.setHalf(MPBlocks.INFECTED_CRACKED_STONE_BRICKS_SLAB);
        MPBlocks.DOUBLE_TERRASTONE_SLAB.setHalf(MPBlocks.TERRASTONE_SLAB);
        MPBlocks.DOUBLE_INFECTED_SANDSTONE_SLAB.setHalf(MPBlocks.INFECTED_SANDSTONE_SLAB);
        MPBlocks.DOUBLE_INFECTED_CUT_SANDSTONE_SLAB.setHalf(MPBlocks.INFECTED_CUT_SANDSTONE_SLAB);

        BlockUtils.setFireBurn(MPBlocks.INFECTED_PURLONITE_PLANKS, 5, 20);
        BlockUtils.setFireBurn(MPBlocks.INFECTED_PURLONITE_FENCE, 5, 20);
        BlockUtils.setFireBurn(MPBlocks.CHEESE_SPORE_FLOWER, 60, 100);
        BlockUtils.setFireBurn(MPBlocks.CHEESE_SPORE, 60, 100);
        BlockUtils.setFireBurn(MPBlocks.CHEESE_GRASS, 60, 100);
        BlockUtils.setFireBurn(MPBlocks.CHEESE_TALL_GRASS, 60, 100);
        BlockUtils.setFireBurn(MPBlocks.CHEESE_SPORE_STEM, 5, 20);
        BlockUtils.setFireBurn(MPBlocks.CHEESE_SPORE_PLANKS, 5, 20);
        BlockUtils.setFireBurn(MPBlocks.CHEESE_SPORE_STAIRS, 5, 20);
        BlockUtils.setFireBurn(MPBlocks.CHEESE_SPORE_FENCE, 5, 20);
        BlockUtils.setFireBurn(MPBlocks.CHEESE_SPORE_FENCE_GATE, 5, 20);
        BlockUtils.setFireBurn(MPBlocks.INFECTED_GRASS, 60, 100);
        BlockUtils.setFireBurn(MPBlocks.INFECTED_FERN, 60, 100);
        BlockUtils.setFireBurn(MPBlocks.GREEN_VEIN_GRASS, 60, 100);
        BlockUtils.setFireBurn(MPBlocks.INFECTED_OAK_SAPLING, 60, 100);
        BlockUtils.setFireBurn(MPBlocks.INFECTED_SPRUCE_SAPLING, 60, 100);
        BlockUtils.setFireBurn(MPBlocks.INFECTED_JUNGLE_SAPLING, 60, 100);
        BlockUtils.setFireBurn(MPBlocks.ALIEN_BERRY_OAK_SAPLING, 60, 100);
        BlockUtils.setFireBurn(MPBlocks.PURE_HERB, 60, 100);
        BlockUtils.setFireBurn(MPBlocks.TERRAPUFF_HERB, 60, 100);
        BlockUtils.setFireBurn(MPBlocks.BATASIA_DANDELION, 60, 100);
        BlockUtils.setFireBurn(MPBlocks.PYOLONIA, 60, 100);
        BlockUtils.setFireBurn(MPBlocks.PHILIPY, 60, 100);
        BlockUtils.setFireBurn(MPBlocks.WHITE_TAIL, 60, 100);
        BlockUtils.setFireBurn(MPBlocks.VEALIUM_VINES, 60, 100);
        BlockUtils.setFireBurn(MPBlocks.TERRASHROOM, 60, 100);
        BlockUtils.setFireBurn(MPBlocks.INFECTED_OAK_LOG, 5, 20);
        BlockUtils.setFireBurn(MPBlocks.INFECTED_DEADWOOD_LOG, 5, 20);
        BlockUtils.setFireBurn(MPBlocks.INFECTED_JUNGLE_LOG, 5, 20);
        BlockUtils.setFireBurn(MPBlocks.ALIEN_BERRY_OAK_LOG, 5, 20);
        BlockUtils.setFireBurn(MPBlocks.INFECTED_OAK_PLANKS, 5, 20);
        BlockUtils.setFireBurn(MPBlocks.ALIEN_BERRY_OAK_PLANKS, 5, 20);
        BlockUtils.setFireBurn(MPBlocks.INFECTED_OAK_BOOKSHELF, 5, 20);
        BlockUtils.setFireBurn(MPBlocks.ALIEN_BERRY_OAK_BOOKSHELF, 5, 20);
        BlockUtils.setFireBurn(MPBlocks.INFECTED_OAK_FENCE, 5, 20);
        BlockUtils.setFireBurn(MPBlocks.ALIEN_BERRY_OAK_FENCE, 5, 20);
        BlockUtils.setFireBurn(MPBlocks.INFECTED_OAK_FENCE_GATE, 5, 20);
        BlockUtils.setFireBurn(MPBlocks.INFECTED_OAK_LEAVES, 60, 100);
        BlockUtils.setFireBurn(MPBlocks.INFECTED_JUNGLE_LEAVES, 60, 100);
        BlockUtils.setFireBurn(MPBlocks.ALIEN_BERRY_OAK_LEAVES, 60, 100);
        BlockUtils.setFireBurn(MPBlocks.INFECTED_VINES, 15, 100);
        BlockUtils.setFireBurn(MPBlocks.INFECTED_OAK_STAIRS, 5, 20);
        BlockUtils.setFireBurn(MPBlocks.ALIEN_BERRY_OAK_STAIRS, 5, 20);
        BlockUtils.setFireBurn(MPBlocks.ALIEN_BERRY_OAK_FENCE_GATE, 5, 20);
        BlockUtils.setFireBurn(MPBlocks.RED_CANDY_CANE, 60, 100);
        BlockUtils.setFireBurn(MPBlocks.GREEN_CANDY_CANE, 60, 100);
        BlockUtils.setFireBurn(MPBlocks.BLUE_CANDY_CANE, 60, 100);
        BlockUtils.setFireBurn(MPBlocks.ORANGE_CANDY_CANE, 60, 100);
        BlockUtils.setFireBurn(MPBlocks.PINK_CANDY_CANE, 60, 100);
        BlockUtils.setFireBurn(MPBlocks.YELLOW_CANDY_CANE, 60, 100);
        BlockUtils.setFireBurn(MPBlocks.PURPLE_CANDY_CANE, 60, 100);
        BlockUtils.setFireBurn(MPBlocks.RAINBOW_CANDY_CANE, 60, 100);

        MPBlocks.DIONA_ROCK.setDrop(MPBlocks.DIONA_COBBLESTONE);
        MPBlocks.CHALOS_ROCK.setDrop(MPBlocks.CHALOS_COBBLESTONE);
        MPBlocks.NIBIRU_ROCK.setDrop(MPBlocks.NIBIRU_COBBLESTONE);
        MPBlocks.FRONOS_STONE.setDrop(MPBlocks.FRONOS_COBBLESTONE);
    }
}