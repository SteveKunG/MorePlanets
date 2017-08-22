package stevekung.mods.moreplanets.module.planets.nibiru.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.BlockFire;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.oredict.OreDictionary;
import stevekung.mods.moreplanets.module.planets.nibiru.blocks.fluid.FluidHeliumGas;
import stevekung.mods.moreplanets.module.planets.nibiru.itemblocks.ItemBlockInfectedSnow;
import stevekung.mods.moreplanets.module.planets.nibiru.itemblocks.ItemBlockNuclearWasteTank;
import stevekung.mods.moreplanets.module.planets.nibiru.itemblocks.ItemBlockSporelily;
import stevekung.mods.moreplanets.module.planets.nibiru.items.NibiruItems;
import stevekung.mods.moreplanets.util.EnumHarvestLevel;
import stevekung.mods.moreplanets.util.blocks.*;
import stevekung.mods.moreplanets.util.blocks.BlockStairsMP.EnumStairsType;
import stevekung.mods.moreplanets.util.blocks.fluid.FluidMP;
import stevekung.mods.moreplanets.util.helper.CommonRegisterHelper;
import stevekung.mods.moreplanets.util.itemblocks.ItemBlockDescription;
import stevekung.mods.moreplanets.util.itemblocks.ItemBlockMultiVariant;
import stevekung.mods.moreplanets.util.itemblocks.ItemBlockMultiVariantLeaves;
import stevekung.mods.moreplanets.util.itemblocks.ItemBlockSlabMP;

public class NibiruBlocks
{
    public static Block INFECTED_GRASS;
    public static Block INFECTED_DIRT;
    public static Block INFECTED_FARMLAND;
    public static BlockBushMP NIBIRU_TALL_GRASS;
    public static Block INFECTED_SAND;
    public static BlockBushMP INFECTED_CACTUS;
    public static Block NIBIRU_BLOCK;
    public static Block NIBIRU_ORE;
    public static Block NIBIRU_SANDSTONE;
    public static Block INFECTED_WATER_FLUID_BLOCK;
    public static Block NIBIRU_LOG;
    public static Block NIBIRU_PLANKS;
    public static Block NIBIRU_LEAVES;
    public static Block NIBIRU_SAPLING;
    public static Block INFECTED_VINES;
    public static BlockBushMP SPORELILY;
    public static BlockBushMP NIBIRU_FLOWER;
    public static Block NIBIRU_CRAFTING_TABLE;
    public static Block NIBIRU_SILVERFISH_STONE;
    public static Block NIBIRU_BOOKSHELF;
    public static Block NIBIRU_FENCE;
    public static BlockNibiruDoublePlant NIBIRU_DOUBLE_PLANT;
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
    public static Block GREEN_VEIN_GRASS;
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
    public static Block NIBIRU_SEAWEED;
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

        NibiruBlocks.INFECTED_GRASS = new BlockInfectedGrass("infected_grass");
        NibiruBlocks.INFECTED_DIRT = new BlockInfectedDirt("infected_dirt");
        NibiruBlocks.INFECTED_FARMLAND = new BlockInfectedFarmland("infected_farmland");
        NibiruBlocks.INFECTED_SAND = new BlockFallingMP("infected_sand").setSoundType(SoundType.SAND).setHardness(0.5F);
        NibiruBlocks.NIBIRU_TALL_GRASS = new BlockNibiruTallGrass("nibiru_tall_grass");
        NibiruBlocks.INFECTED_CACTUS = new BlockInfectedCactus("infected_cactus");
        NibiruBlocks.NIBIRU_BLOCK = new BlockNibiru("nibiru_block");
        NibiruBlocks.NIBIRU_ORE = new BlockNibiruOre("nibiru_ore");
        NibiruBlocks.NIBIRU_SANDSTONE = new BlockNibiruSandstone("nibiru_sandstone");
        NibiruBlocks.NIBIRU_LOG = new BlockNibiruLog("nibiru_log");
        NibiruBlocks.NIBIRU_PLANKS = new BlockNibiruPlanks("nibiru_planks");
        NibiruBlocks.NIBIRU_LEAVES = new BlockNibiruLeaves("nibiru_leaves");
        NibiruBlocks.NIBIRU_SAPLING = new BlockNibiruSapling("nibiru_sapling");
        NibiruBlocks.INFECTED_VINES = new BlockInfectedVines("infected_vines");
        NibiruBlocks.SPORELILY = new BlockSporelily("sporelily");
        NibiruBlocks.NIBIRU_FLOWER = new BlockNibiruFlower("nibiru_flower");
        NibiruBlocks.NIBIRU_CRAFTING_TABLE = new BlockNibiruCraftingTable("nibiru_crafting_table");
        NibiruBlocks.NIBIRU_SILVERFISH_STONE = new BlockNibiruSilverfish("nibiru_silverfish_stone");
        NibiruBlocks.NIBIRU_BOOKSHELF = new BlockNibiruBookshelf("nibiru_bookshelf");
        NibiruBlocks.NIBIRU_FENCE = new BlockNibiruFence("nibiru_fence");
        NibiruBlocks.INFECTED_FENCE_GATE = new BlockFenceGateMP("infected_fence_gate");
        NibiruBlocks.INFECTED_DEAD_OAK_FENCE_GATE = new BlockFenceGateMP("infected_dead_oak_fence_gate");
        NibiruBlocks.INFECTED_DOOR_BLOCK = new BlockDoorMP("infected_door_block");
        NibiruBlocks.INFECTED_DEAD_OAK_DOOR_BLOCK = new BlockDoorMP("infected_dead_oak_door_block");
        NibiruBlocks.ALIEN_BERRY_OAK_DOOR_BLOCK = new BlockDoorMP("alien_berry_oak_door_block");
        NibiruBlocks.NIBIRU_DOUBLE_PLANT = new BlockNibiruDoublePlant("nibiru_double_plant");
        NibiruBlocks.NIBIRU_COBBLESTONE_STAIRS = new BlockStairsMP("nibiru_cobblestone_stairs", EnumStairsType.COBBLESTONE);
        NibiruBlocks.NIBIRU_STONE_BRICKS_STAIRS = new BlockStairsMP("nibiru_stone_bricks_stairs", EnumStairsType.STONE_BRICK);
        NibiruBlocks.NIBIRU_ANCIENT_CHEST = new BlockNibiruAncientChest("nibiru_ancient_chest");
        NibiruBlocks.NIBIRU_SANDSTONE_STAIRS = new BlockStairsMP("nibiru_sandstone_stairs", EnumStairsType.SANDSTONE);
        NibiruBlocks.INFECTED_SNOW = new BlockInfectedSnow("infected_snow");
        NibiruBlocks.INFECTED_ICE = new BlockInfectedIce("infected_ice");
        NibiruBlocks.INFECTED_PACKED_ICE = new BlockInfectedPackedIce("infected_packed_ice");
        NibiruBlocks.INFECTED_SNOW_LAYER = new BlockSnowLayerMP("infected_snow_layer", NibiruItems.INFECTED_SNOWBALL, 0);
        NibiruBlocks.GREEN_VEIN_GRASS = new BlockGreenVeinGrass("green_vein_grass");
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
        NibiruBlocks.NIBIRU_SEAWEED = new BlockNibiruSeaweed("nibiru_seaweed");
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
        CommonRegisterHelper.registerFluid(NibiruBlocks.INFECTED_WATER_FLUID);
        CommonRegisterHelper.registerFluid(NibiruBlocks.HELIUM_GAS);
        CommonRegisterHelper.registerFluid(NibiruBlocks.NUCLEAR_WASTE_FLUID);
        CommonRegisterHelper.registerFluid(NibiruBlocks.PURIFY_WATER_FLUID);
        NibiruBlocks.INFECTED_WATER_FLUID_BLOCK = new BlockFluidInfectedWater("infected_water_fluid");
        NibiruBlocks.HELIUM_GAS_BLOCK = new BlockGasHelium("helium_gas");
        NibiruBlocks.NUCLEAR_WASTE_FLUID_BLOCK = new BlockFluidNuclearWaste("nuclear_waste_fluid");
        NibiruBlocks.PURIFY_WATER_FLUID_BLOCK = new BlockFluidPurifyWater("purify_water_fluid");

        /**************************************************************/
        /************************REGISTER STUFF************************/
        /**************************************************************/

        CommonRegisterHelper.registerBlock(NibiruBlocks.INFECTED_GRASS);
        CommonRegisterHelper.registerBlock(NibiruBlocks.INFECTED_DIRT, ItemBlockMultiVariant::new);
        CommonRegisterHelper.registerBlock(NibiruBlocks.INFECTED_FARMLAND);
        CommonRegisterHelper.registerBlock(NibiruBlocks.NIBIRU_TALL_GRASS, ItemBlockMultiVariant::new);
        CommonRegisterHelper.registerBlock(NibiruBlocks.INFECTED_SAND);
        CommonRegisterHelper.registerBlock(NibiruBlocks.INFECTED_CACTUS);
        CommonRegisterHelper.registerBlock(NibiruBlocks.NIBIRU_BLOCK, ItemBlockMultiVariant::new);
        CommonRegisterHelper.registerBlock(NibiruBlocks.NIBIRU_ORE, ItemBlockMultiVariant::new);
        CommonRegisterHelper.registerBlock(NibiruBlocks.NIBIRU_SANDSTONE, ItemBlockMultiVariant::new);
        CommonRegisterHelper.registerBlock(NibiruBlocks.NIBIRU_LOG, ItemBlockMultiVariant::new);
        CommonRegisterHelper.registerBlock(NibiruBlocks.NIBIRU_PLANKS, ItemBlockMultiVariant::new);
        CommonRegisterHelper.registerBlock(NibiruBlocks.NIBIRU_LEAVES, ItemBlockMultiVariantLeaves::new);
        CommonRegisterHelper.registerBlock(NibiruBlocks.NIBIRU_SAPLING, ItemBlockMultiVariant::new);
        CommonRegisterHelper.registerBlock(NibiruBlocks.INFECTED_WATER_FLUID_BLOCK);
        CommonRegisterHelper.registerBlock(NibiruBlocks.INFECTED_VINES);
        CommonRegisterHelper.registerBlock(NibiruBlocks.SPORELILY, ItemBlockSporelily::new);
        CommonRegisterHelper.registerBlock(NibiruBlocks.NIBIRU_FLOWER, ItemBlockMultiVariant::new);
        CommonRegisterHelper.registerBlock(NibiruBlocks.NIBIRU_CRAFTING_TABLE, ItemBlockMultiVariant::new);
        CommonRegisterHelper.registerBlock(NibiruBlocks.NIBIRU_SILVERFISH_STONE, ItemBlockMultiVariant::new);
        CommonRegisterHelper.registerBlock(NibiruBlocks.NIBIRU_BOOKSHELF, ItemBlockMultiVariant::new);
        CommonRegisterHelper.registerBlock(NibiruBlocks.NIBIRU_FENCE, ItemBlockMultiVariant::new);
        CommonRegisterHelper.registerBlock(NibiruBlocks.INFECTED_FENCE_GATE);
        CommonRegisterHelper.registerBlock(NibiruBlocks.INFECTED_DEAD_OAK_FENCE_GATE);
        CommonRegisterHelper.registerBlock(NibiruBlocks.INFECTED_DOOR_BLOCK, null);
        CommonRegisterHelper.registerBlock(NibiruBlocks.INFECTED_DEAD_OAK_DOOR_BLOCK, null);
        CommonRegisterHelper.registerBlock(NibiruBlocks.ALIEN_BERRY_OAK_DOOR_BLOCK, null);
        CommonRegisterHelper.registerBlock(NibiruBlocks.NIBIRU_DOUBLE_PLANT, ItemBlockMultiVariant::new);
        CommonRegisterHelper.registerBlock(NibiruBlocks.NIBIRU_COBBLESTONE_STAIRS);
        CommonRegisterHelper.registerBlock(NibiruBlocks.NIBIRU_STONE_BRICKS_STAIRS);
        CommonRegisterHelper.registerBlock(NibiruBlocks.NIBIRU_ANCIENT_CHEST);
        CommonRegisterHelper.registerBlock(NibiruBlocks.NIBIRU_SANDSTONE_STAIRS);
        CommonRegisterHelper.registerBlock(NibiruBlocks.INFECTED_SNOW);
        CommonRegisterHelper.registerBlock(NibiruBlocks.INFECTED_ICE);
        CommonRegisterHelper.registerBlock(NibiruBlocks.INFECTED_PACKED_ICE);
        CommonRegisterHelper.registerBlock(NibiruBlocks.INFECTED_SNOW_LAYER, ItemBlockInfectedSnow::new);
        CommonRegisterHelper.registerBlock(NibiruBlocks.GREEN_VEIN_GRASS);
        CommonRegisterHelper.registerBlock(NibiruBlocks.INFECTED_MELON_BLOCK);
        CommonRegisterHelper.registerBlock(NibiruBlocks.INFECTED_WHEAT_BLOCK, null);
        CommonRegisterHelper.registerBlock(NibiruBlocks.INFECTED_GRAVEL);
        CommonRegisterHelper.registerBlock(NibiruBlocks.INFECTED_CLAY);
        CommonRegisterHelper.registerBlock(NibiruBlocks.ELECTRICAL_FIRE, null);
        CommonRegisterHelper.registerBlock(NibiruBlocks.NIBIRU_TREASURE_CHEST);
        CommonRegisterHelper.registerBlock(NibiruBlocks.INFECTED_CHEST);
        CommonRegisterHelper.registerBlock(NibiruBlocks.ALIEN_BERRY_CHEST);
        CommonRegisterHelper.registerBlock(NibiruBlocks.MULTALIC_CRYSTAL);
        CommonRegisterHelper.registerBlock(NibiruBlocks.INFECTED_SUGAR_CANE_BLOCK, null);
        CommonRegisterHelper.registerBlock(NibiruBlocks.INFECTED_PRISMARINE, ItemBlockMultiVariant::new);
        CommonRegisterHelper.registerBlock(NibiruBlocks.INFECTED_SEA_LANTERN);
        CommonRegisterHelper.registerBlock(NibiruBlocks.INFECTED_SPONGE, ItemBlockMultiVariant::new);
        CommonRegisterHelper.registerBlock(NibiruBlocks.NIBIRU_SEAWEED, ItemBlockMultiVariant::new);
        CommonRegisterHelper.registerBlock(NibiruBlocks.OIL_ORE);
        CommonRegisterHelper.registerBlock(NibiruBlocks.HELIUM_GAS_BLOCK);
        CommonRegisterHelper.registerBlock(NibiruBlocks.INFECTED_VINES_DIRT);
        CommonRegisterHelper.registerBlock(NibiruBlocks.INFECTED_TORCH);
        CommonRegisterHelper.registerBlock(NibiruBlocks.NIBIRU_FURNACE);
        CommonRegisterHelper.registerBlock(NibiruBlocks.NIBIRU_LIT_FURNACE, null);
        CommonRegisterHelper.registerBlock(NibiruBlocks.JUICER_EGG);
        CommonRegisterHelper.registerBlock(NibiruBlocks.INFECTED_MELON_STEM, null);
        CommonRegisterHelper.registerBlock(NibiruBlocks.NIBIRU_DUNGEON_BRICK_STAIRS);
        CommonRegisterHelper.registerBlock(NibiruBlocks.INFECTED_OAK_STAIRS);
        CommonRegisterHelper.registerBlock(NibiruBlocks.INFECTED_DEAD_OAK_STAIRS);
        CommonRegisterHelper.registerBlock(NibiruBlocks.ALIEN_BERRY_OAK_STAIRS);
        CommonRegisterHelper.registerBlock(NibiruBlocks.INFECTED_PRISMARINE_STAIRS);
        CommonRegisterHelper.registerBlock(NibiruBlocks.INFECTED_PRISMARINE_BRICK_STAIRS);
        CommonRegisterHelper.registerBlock(NibiruBlocks.INFECTED_DARK_PRISMARINE_STAIRS);
        CommonRegisterHelper.registerBlock(NibiruBlocks.INFECTED_VEIN_STONE_BRICKS_STAIRS);
        CommonRegisterHelper.registerBlock(NibiruBlocks.CRACKED_INFECTED_STONE_BRICKS_STAIRS);
        CommonRegisterHelper.registerBlock(NibiruBlocks.NIBIRU_SMOOTH_SANDSTONE_STAIRS);
        CommonRegisterHelper.registerBlock(NibiruBlocks.NUCLEAR_WASTE_TANK, ItemBlockNuclearWasteTank::new);
        CommonRegisterHelper.registerBlock(NibiruBlocks.NUCLEAR_WASTE_FLUID_BLOCK);
        CommonRegisterHelper.registerBlock(NibiruBlocks.VEIN_FRAME);
        CommonRegisterHelper.registerBlock(NibiruBlocks.VEIN_PORTAL, null);
        CommonRegisterHelper.registerBlock(NibiruBlocks.NUCLEAR_WASTE_GENERATOR, ItemBlockDescription::new);
        CommonRegisterHelper.registerBlock(NibiruBlocks.ALIEN_BERRY_OAK_FENCE_GATE);
        CommonRegisterHelper.registerBlock(NibiruBlocks.HALF_INFECTED_PRISMARINE_SLAB, ItemBlockSlabMP::new);
        CommonRegisterHelper.registerBlock(NibiruBlocks.DOUBLE_INFECTED_PRISMARINE_SLAB, null);
        CommonRegisterHelper.registerBlock(NibiruBlocks.HALF_INFECTED_STONE_BRICKS_SLAB, ItemBlockSlabMP::new);
        CommonRegisterHelper.registerBlock(NibiruBlocks.DOUBLE_INFECTED_STONE_BRICKS_SLAB, null);
        CommonRegisterHelper.registerBlock(NibiruBlocks.HALF_NIBIRU_SANDSTONE_SLAB, ItemBlockSlabMP::new);
        CommonRegisterHelper.registerBlock(NibiruBlocks.DOUBLE_NIBIRU_SANDSTONE_SLAB, null);
        CommonRegisterHelper.registerBlock(NibiruBlocks.MULTALIC_CRYSTAL_BLOCK);
        CommonRegisterHelper.registerBlock(NibiruBlocks.TERRASTONE);
        CommonRegisterHelper.registerBlock(NibiruBlocks.PURIFY_WATER_FLUID_BLOCK);
        CommonRegisterHelper.registerBlock(NibiruBlocks.PURIFY_GRAVEL);
        CommonRegisterHelper.registerBlock(NibiruBlocks.TERRABERRY_BLOCK, null);
        CommonRegisterHelper.registerBlock(NibiruBlocks.HUGE_TERRASHROOM_BLOCK);
        CommonRegisterHelper.registerBlock(NibiruBlocks.TERRASTONE_STAIRS);
        CommonRegisterHelper.registerBlock(NibiruBlocks.TERRASTONE_FURNACE);
        CommonRegisterHelper.registerBlock(NibiruBlocks.TERRASTONE_LIT_FURNACE, null);
        CommonRegisterHelper.registerBlock(NibiruBlocks.SEALABLE_NUCLEAR_WASTE_ROD);
        CommonRegisterHelper.registerBlock(NibiruBlocks.NIBIRU_GRASS_PATH, ItemBlockMultiVariant::new);

        /**************************************************************/
        /**********************HARVEST LEVEL STUFF*********************/
        /**************************************************************/

        CommonRegisterHelper.setBlockHarvestLevel(NibiruBlocks.INFECTED_GRASS, EnumHarvestLevel.SHOVEL, 0);
        CommonRegisterHelper.setBlockHarvestLevel(NibiruBlocks.INFECTED_DIRT, EnumHarvestLevel.SHOVEL, 0);
        CommonRegisterHelper.setBlockHarvestLevel(NibiruBlocks.INFECTED_FARMLAND, EnumHarvestLevel.SHOVEL, 0);
        CommonRegisterHelper.setBlockHarvestLevel(NibiruBlocks.INFECTED_SAND, EnumHarvestLevel.SHOVEL, 0);
        CommonRegisterHelper.setBlockHarvestLevel(NibiruBlocks.INFECTED_SNOW, EnumHarvestLevel.SHOVEL, 0);
        CommonRegisterHelper.setBlockHarvestLevel(NibiruBlocks.INFECTED_SNOW_LAYER, EnumHarvestLevel.SHOVEL, 0);
        CommonRegisterHelper.setBlockHarvestLevel(NibiruBlocks.GREEN_VEIN_GRASS, EnumHarvestLevel.SHOVEL, 0);
        CommonRegisterHelper.setBlockHarvestLevel(NibiruBlocks.INFECTED_GRAVEL, EnumHarvestLevel.SHOVEL, 0);
        CommonRegisterHelper.setBlockHarvestLevel(NibiruBlocks.INFECTED_CLAY, EnumHarvestLevel.SHOVEL, 0);
        CommonRegisterHelper.setBlockHarvestLevel(NibiruBlocks.INFECTED_DIRT, EnumHarvestLevel.SHOVEL, 0);
        CommonRegisterHelper.setBlockHarvestLevel(NibiruBlocks.PURIFY_GRAVEL, EnumHarvestLevel.SHOVEL, 0);
        CommonRegisterHelper.setBlockHarvestLevel(NibiruBlocks.NIBIRU_GRASS_PATH, EnumHarvestLevel.SHOVEL, 0);
        CommonRegisterHelper.setBlockHarvestLevel(NibiruBlocks.NIBIRU_SANDSTONE, EnumHarvestLevel.PICKAXE, 0);
        CommonRegisterHelper.setBlockHarvestLevel(NibiruBlocks.NIBIRU_BLOCK, EnumHarvestLevel.PICKAXE, 0);
        CommonRegisterHelper.setBlockHarvestLevel(NibiruBlocks.NIBIRU_COBBLESTONE_STAIRS, EnumHarvestLevel.PICKAXE, 0);
        CommonRegisterHelper.setBlockHarvestLevel(NibiruBlocks.NIBIRU_STONE_BRICKS_STAIRS, EnumHarvestLevel.PICKAXE, 0);
        CommonRegisterHelper.setBlockHarvestLevel(NibiruBlocks.NIBIRU_SANDSTONE_STAIRS, EnumHarvestLevel.PICKAXE, 0);
        CommonRegisterHelper.setBlockHarvestLevel(NibiruBlocks.INFECTED_PACKED_ICE, EnumHarvestLevel.PICKAXE, 0);
        CommonRegisterHelper.setBlockHarvestLevel(NibiruBlocks.INFECTED_PRISMARINE, EnumHarvestLevel.PICKAXE, 0);
        CommonRegisterHelper.setBlockHarvestLevel(NibiruBlocks.NIBIRU_FURNACE, EnumHarvestLevel.PICKAXE, 0);
        CommonRegisterHelper.setBlockHarvestLevel(NibiruBlocks.NIBIRU_LIT_FURNACE, EnumHarvestLevel.PICKAXE, 0);
        CommonRegisterHelper.setBlockHarvestLevel(NibiruBlocks.OIL_ORE, EnumHarvestLevel.PICKAXE, 0);
        CommonRegisterHelper.setBlockHarvestLevel(NibiruBlocks.INFECTED_PRISMARINE_STAIRS, EnumHarvestLevel.PICKAXE, 0);
        CommonRegisterHelper.setBlockHarvestLevel(NibiruBlocks.INFECTED_PRISMARINE_BRICK_STAIRS, EnumHarvestLevel.PICKAXE, 0);
        CommonRegisterHelper.setBlockHarvestLevel(NibiruBlocks.INFECTED_DARK_PRISMARINE_STAIRS, EnumHarvestLevel.PICKAXE, 0);
        CommonRegisterHelper.setBlockHarvestLevel(NibiruBlocks.INFECTED_VEIN_STONE_BRICKS_STAIRS, EnumHarvestLevel.PICKAXE, 0);
        CommonRegisterHelper.setBlockHarvestLevel(NibiruBlocks.CRACKED_INFECTED_STONE_BRICKS_STAIRS, EnumHarvestLevel.PICKAXE, 0);
        CommonRegisterHelper.setBlockHarvestLevel(NibiruBlocks.NIBIRU_SMOOTH_SANDSTONE_STAIRS, EnumHarvestLevel.PICKAXE, 0);
        CommonRegisterHelper.setBlockHarvestLevel(NibiruBlocks.HALF_INFECTED_PRISMARINE_SLAB, EnumHarvestLevel.PICKAXE, 0);
        CommonRegisterHelper.setBlockHarvestLevel(NibiruBlocks.DOUBLE_INFECTED_PRISMARINE_SLAB, EnumHarvestLevel.PICKAXE, 0);
        CommonRegisterHelper.setBlockHarvestLevel(NibiruBlocks.HALF_INFECTED_STONE_BRICKS_SLAB, EnumHarvestLevel.PICKAXE, 0);
        CommonRegisterHelper.setBlockHarvestLevel(NibiruBlocks.DOUBLE_INFECTED_STONE_BRICKS_SLAB, EnumHarvestLevel.PICKAXE, 0);
        CommonRegisterHelper.setBlockHarvestLevel(NibiruBlocks.HALF_NIBIRU_SANDSTONE_SLAB, EnumHarvestLevel.PICKAXE, 0);
        CommonRegisterHelper.setBlockHarvestLevel(NibiruBlocks.DOUBLE_NIBIRU_SANDSTONE_SLAB, EnumHarvestLevel.PICKAXE, 0);
        CommonRegisterHelper.setBlockHarvestLevel(NibiruBlocks.TERRASTONE, EnumHarvestLevel.PICKAXE, 0);
        CommonRegisterHelper.setBlockHarvestLevel(NibiruBlocks.TERRASTONE_STAIRS, EnumHarvestLevel.PICKAXE, 0);
        CommonRegisterHelper.setBlockHarvestLevel(NibiruBlocks.TERRASTONE_FURNACE, EnumHarvestLevel.PICKAXE, 0);
        CommonRegisterHelper.setBlockHarvestLevel(NibiruBlocks.TERRASTONE_LIT_FURNACE, EnumHarvestLevel.PICKAXE, 0);
        CommonRegisterHelper.setBlockHarvestLevel(NibiruBlocks.SEALABLE_NUCLEAR_WASTE_ROD, EnumHarvestLevel.PICKAXE, 0);
        CommonRegisterHelper.setBlockHarvestLevel(NibiruBlocks.MULTALIC_CRYSTAL_BLOCK, EnumHarvestLevel.PICKAXE, 1);
        CommonRegisterHelper.setBlockHarvestLevel(NibiruBlocks.NIBIRU_DUNGEON_BRICK_STAIRS, EnumHarvestLevel.PICKAXE, 1);
        CommonRegisterHelper.setBlockHarvestLevel(NibiruBlocks.NUCLEAR_WASTE_GENERATOR, EnumHarvestLevel.PICKAXE, 2);
        CommonRegisterHelper.setBlockHarvestLevel(NibiruBlocks.NUCLEAR_WASTE_TANK, EnumHarvestLevel.PICKAXE, 2);
        CommonRegisterHelper.setBlockHarvestLevel(NibiruBlocks.MULTALIC_CRYSTAL, EnumHarvestLevel.PICKAXE, 2);
        CommonRegisterHelper.setBlockHarvestLevel(NibiruBlocks.NIBIRU_LOG, EnumHarvestLevel.AXE, 0);
        CommonRegisterHelper.setBlockHarvestLevel(NibiruBlocks.NIBIRU_PLANKS, EnumHarvestLevel.AXE, 0);
        CommonRegisterHelper.setBlockHarvestLevel(NibiruBlocks.NIBIRU_CRAFTING_TABLE, EnumHarvestLevel.AXE, 0);
        CommonRegisterHelper.setBlockHarvestLevel(NibiruBlocks.NIBIRU_BOOKSHELF, EnumHarvestLevel.AXE, 0);
        CommonRegisterHelper.setBlockHarvestLevel(NibiruBlocks.INFECTED_FENCE_GATE, EnumHarvestLevel.AXE, 0);
        CommonRegisterHelper.setBlockHarvestLevel(NibiruBlocks.INFECTED_DEAD_OAK_FENCE_GATE, EnumHarvestLevel.AXE, 0);
        CommonRegisterHelper.setBlockHarvestLevel(NibiruBlocks.NIBIRU_FENCE, EnumHarvestLevel.AXE, 0);
        CommonRegisterHelper.setBlockHarvestLevel(NibiruBlocks.INFECTED_DOOR_BLOCK, EnumHarvestLevel.AXE, 0);
        CommonRegisterHelper.setBlockHarvestLevel(NibiruBlocks.INFECTED_DEAD_OAK_DOOR_BLOCK, EnumHarvestLevel.AXE, 0);
        CommonRegisterHelper.setBlockHarvestLevel(NibiruBlocks.ALIEN_BERRY_OAK_DOOR_BLOCK, EnumHarvestLevel.AXE, 0);
        CommonRegisterHelper.setBlockHarvestLevel(NibiruBlocks.NIBIRU_ANCIENT_CHEST, EnumHarvestLevel.AXE, 0);
        CommonRegisterHelper.setBlockHarvestLevel(NibiruBlocks.INFECTED_MELON_BLOCK, EnumHarvestLevel.AXE, 0);
        CommonRegisterHelper.setBlockHarvestLevel(NibiruBlocks.INFECTED_CHEST, EnumHarvestLevel.AXE, 0);
        CommonRegisterHelper.setBlockHarvestLevel(NibiruBlocks.ALIEN_BERRY_CHEST, EnumHarvestLevel.AXE, 0);
        CommonRegisterHelper.setBlockHarvestLevel(NibiruBlocks.INFECTED_OAK_STAIRS, EnumHarvestLevel.AXE, 0);
        CommonRegisterHelper.setBlockHarvestLevel(NibiruBlocks.INFECTED_DEAD_OAK_STAIRS, EnumHarvestLevel.AXE, 0);
        CommonRegisterHelper.setBlockHarvestLevel(NibiruBlocks.ALIEN_BERRY_OAK_STAIRS, EnumHarvestLevel.AXE, 0);
        CommonRegisterHelper.setBlockHarvestLevel(NibiruBlocks.ALIEN_BERRY_OAK_FENCE_GATE, EnumHarvestLevel.AXE, 0);
        CommonRegisterHelper.setBlockHarvestLevel(NibiruBlocks.HUGE_TERRASHROOM_BLOCK, EnumHarvestLevel.AXE, 0);

        for (int i = 0; i < BlockNibiru.BlockType.valuesCached().length; i++)
        {
            if (i >= 7)
            {
                CommonRegisterHelper.setBlockHarvestLevel(NibiruBlocks.NIBIRU_BLOCK, EnumHarvestLevel.PICKAXE, 1, i);
            }
            else
            {
                CommonRegisterHelper.setBlockHarvestLevel(NibiruBlocks.NIBIRU_BLOCK, EnumHarvestLevel.PICKAXE, 0, i);
            }
        }

        for (int i = 0; i < BlockNibiruOre.BlockType.valuesCached().length; i++)
        {
            if (i == 0)
            {
                CommonRegisterHelper.setBlockHarvestLevel(NibiruBlocks.NIBIRU_ORE, EnumHarvestLevel.PICKAXE, 0, i);
            }
            else if (i == 1 || i == 5 || i >= 7)
            {
                CommonRegisterHelper.setBlockHarvestLevel(NibiruBlocks.NIBIRU_ORE, EnumHarvestLevel.PICKAXE, 1, i);
            }
            else
            {
                CommonRegisterHelper.setBlockHarvestLevel(NibiruBlocks.NIBIRU_ORE, EnumHarvestLevel.PICKAXE, 2, i);
            }
        }

        /**************************************************************/
        /************************FIRE BURN STUFF***********************/
        /**************************************************************/

        CommonRegisterHelper.setFireBurn(NibiruBlocks.NIBIRU_TALL_GRASS, 60, 100);
        CommonRegisterHelper.setFireBurn(NibiruBlocks.NIBIRU_SAPLING, 60, 100);
        CommonRegisterHelper.setFireBurn(NibiruBlocks.NIBIRU_FLOWER, 60, 100);
        CommonRegisterHelper.setFireBurn(NibiruBlocks.NIBIRU_LOG, 5, 20);
        CommonRegisterHelper.setFireBurn(NibiruBlocks.NIBIRU_PLANKS, 5, 20);
        CommonRegisterHelper.setFireBurn(NibiruBlocks.NIBIRU_BOOKSHELF, 5, 20);
        CommonRegisterHelper.setFireBurn(NibiruBlocks.NIBIRU_FENCE, 5, 20);
        CommonRegisterHelper.setFireBurn(NibiruBlocks.INFECTED_FENCE_GATE, 5, 20);
        CommonRegisterHelper.setFireBurn(NibiruBlocks.INFECTED_DEAD_OAK_FENCE_GATE, 5, 20);
        CommonRegisterHelper.setFireBurn(NibiruBlocks.NIBIRU_LEAVES, 60, 100);
        CommonRegisterHelper.setFireBurn(NibiruBlocks.INFECTED_VINES, 15, 100);
        CommonRegisterHelper.setFireBurn(NibiruBlocks.INFECTED_OAK_STAIRS, 5, 20);
        CommonRegisterHelper.setFireBurn(NibiruBlocks.INFECTED_DEAD_OAK_STAIRS, 5, 20);
        CommonRegisterHelper.setFireBurn(NibiruBlocks.ALIEN_BERRY_OAK_STAIRS, 5, 20);
        CommonRegisterHelper.setFireBurn(NibiruBlocks.ALIEN_BERRY_OAK_FENCE_GATE, 5, 20);

        /**************************************************************/
        /********************ORE DICTIONARY STUFF**********************/
        /**************************************************************/

        CommonRegisterHelper.registerOreDictionary("plankWood", new ItemStack(NibiruBlocks.NIBIRU_PLANKS, 1, OreDictionary.WILDCARD_VALUE));
        CommonRegisterHelper.registerOreDictionary("logWood", new ItemStack(NibiruBlocks.NIBIRU_LOG, 1, OreDictionary.WILDCARD_VALUE));
        CommonRegisterHelper.registerOreDictionary("treeLeaves", new ItemStack(NibiruBlocks.NIBIRU_LEAVES, 1, OreDictionary.WILDCARD_VALUE));
        CommonRegisterHelper.registerOreDictionary("treeSapling", new ItemStack(NibiruBlocks.NIBIRU_SAPLING, 1, OreDictionary.WILDCARD_VALUE));
        CommonRegisterHelper.registerOreDictionary("stairWood", NibiruBlocks.INFECTED_OAK_STAIRS);
        CommonRegisterHelper.registerOreDictionary("stairWood", NibiruBlocks.INFECTED_DEAD_OAK_STAIRS);
        CommonRegisterHelper.registerOreDictionary("stairWood", NibiruBlocks.ALIEN_BERRY_OAK_STAIRS);
        CommonRegisterHelper.registerOreDictionary("grass", NibiruBlocks.INFECTED_GRASS);
        CommonRegisterHelper.registerOreDictionary("grass", NibiruBlocks.GREEN_VEIN_GRASS);
        CommonRegisterHelper.registerOreDictionary("dirt", new ItemStack(NibiruBlocks.INFECTED_DIRT, 1, 0));
        CommonRegisterHelper.registerOreDictionary("stone", new ItemStack(NibiruBlocks.NIBIRU_BLOCK, 1, 0));
        CommonRegisterHelper.registerOreDictionary("cobblestone", new ItemStack(NibiruBlocks.NIBIRU_BLOCK, 1, 1));
        CommonRegisterHelper.registerOreDictionary("sand", NibiruBlocks.INFECTED_SAND);
        CommonRegisterHelper.registerOreDictionary("gravel", NibiruBlocks.INFECTED_GRAVEL);
        CommonRegisterHelper.registerOreDictionary("gravel", NibiruBlocks.PURIFY_GRAVEL);
        CommonRegisterHelper.registerOreDictionary("blockPrismarine", new ItemStack(NibiruBlocks.INFECTED_PRISMARINE, 1, 0));
        CommonRegisterHelper.registerOreDictionary("blockPrismarineBrick", new ItemStack(NibiruBlocks.INFECTED_PRISMARINE, 1, 1));
        CommonRegisterHelper.registerOreDictionary("blockPrismarineDark", new ItemStack(NibiruBlocks.INFECTED_PRISMARINE, 1, 2));
        CommonRegisterHelper.registerOreDictionary("oreCoal", new ItemStack(NibiruBlocks.NIBIRU_ORE, 1, 0));
        CommonRegisterHelper.registerOreDictionary("oreIron", new ItemStack(NibiruBlocks.NIBIRU_ORE, 1, 1));
        CommonRegisterHelper.registerOreDictionary("oreGold", new ItemStack(NibiruBlocks.NIBIRU_ORE, 1, 2));
        CommonRegisterHelper.registerOreDictionary("oreDiamond", new ItemStack(NibiruBlocks.NIBIRU_ORE, 1, 3));
        CommonRegisterHelper.registerOreDictionary("oreRedstone", new ItemStack(NibiruBlocks.NIBIRU_ORE, 1, 4));
        CommonRegisterHelper.registerOreDictionary("oreLapis", new ItemStack(NibiruBlocks.NIBIRU_ORE, 1, 5));
        CommonRegisterHelper.registerOreDictionary("oreEmerald", new ItemStack(NibiruBlocks.NIBIRU_ORE, 1, 6));
        CommonRegisterHelper.registerOreDictionary("workbench", new ItemStack(NibiruBlocks.NIBIRU_CRAFTING_TABLE, 1, OreDictionary.WILDCARD_VALUE));
        CommonRegisterHelper.registerOreDictionary("chestWood", NibiruBlocks.INFECTED_CHEST);
        CommonRegisterHelper.registerOreDictionary("chestWood", NibiruBlocks.ALIEN_BERRY_CHEST);
        CommonRegisterHelper.registerOreDictionary("chestWood", NibiruBlocks.NIBIRU_ANCIENT_CHEST);
        CommonRegisterHelper.registerOreDictionary("chest", NibiruBlocks.INFECTED_CHEST);
        CommonRegisterHelper.registerOreDictionary("chest", NibiruBlocks.ALIEN_BERRY_CHEST);
        CommonRegisterHelper.registerOreDictionary("chest", NibiruBlocks.NIBIRU_ANCIENT_CHEST);
        CommonRegisterHelper.registerOreDictionary("blockCactus", NibiruBlocks.INFECTED_CACTUS);
        CommonRegisterHelper.registerOreDictionary("vine", NibiruBlocks.INFECTED_VINES);
        CommonRegisterHelper.registerOreDictionary("torch", NibiruBlocks.INFECTED_TORCH);
        CommonRegisterHelper.registerOreDictionary("nibiruSandstone", new ItemStack(NibiruBlocks.NIBIRU_SANDSTONE, 1, 0));
        CommonRegisterHelper.registerOreDictionary("nibiruSandstone", new ItemStack(NibiruBlocks.NIBIRU_SANDSTONE, 1, 1));
    }
}