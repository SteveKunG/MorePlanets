package stevekung.mods.moreplanets.init;

import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.item.EnumDyeColor;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidRegistry;
import stevekung.mods.moreplanets.blocks.*;
import stevekung.mods.moreplanets.blocks.decoration.BlockAllDoubleSlab;
import stevekung.mods.moreplanets.blocks.decoration.BlockAllHalfSlab;
import stevekung.mods.moreplanets.blocks.decoration.BlockAllWall;
import stevekung.mods.moreplanets.itemblocks.ItemBlockBlackHoleStorage;
import stevekung.mods.moreplanets.itemblocks.ItemBlockDarkEnergyReceiver;
import stevekung.mods.moreplanets.itemblocks.ItemBlockShieldGenerator;
import stevekung.mods.moreplanets.itemblocks.ItemBlockSpaceWarpPad;
import stevekung.mods.moreplanets.module.moons.koentus.blocks.KoentusBlocks;
import stevekung.mods.moreplanets.module.planets.chalos.blocks.ChalosBlocks;
import stevekung.mods.moreplanets.module.planets.diona.blocks.DionaBlocks;
import stevekung.mods.moreplanets.module.planets.fronos.blocks.FronosBlocks;
import stevekung.mods.moreplanets.module.planets.nibiru.blocks.NibiruBlocks;
import stevekung.mods.moreplanets.util.CompatibilityManagerMP;
import stevekung.mods.moreplanets.util.blocks.BlockBaseMP;
import stevekung.mods.moreplanets.util.blocks.fluid.FluidMP;
import stevekung.mods.moreplanets.util.helper.CommonRegisterHelper;
import stevekung.mods.moreplanets.util.itemblocks.ItemBlockDescription;
import stevekung.mods.moreplanets.util.itemblocks.ItemBlockSlabMP;
import stevekung.mods.stevekunglib.utils.BlockUtils;
import stevekung.mods.stevekunglib.utils.EnumHarvestLevel;

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

    // Energy Storage
    public static Block DARK_ENERGY_STORAGE_CLUSTER;
    public static Block NUCLEAR_WASTE_ENERGY_STORAGE_CLUSTER;

    // Polish
    public static Block POLISHED_TIN_DECORATION_BLOCK;
    public static Block POLISHED_ALUMINUM_DECORATION_BLOCK;

    // Tinted Glass
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

    public static Block SPACE_WARP_PAD;
    public static Block SPACE_WARP_PAD_FULL;
    public static Block ROCKET_CRUSHER;
    public static Block DUNGEON_GLOWSTONE;
    public static Block DARK_ENERGY_RECEIVER;
    public static Block SPACE_PORTAL;
    public static Block BLACK_HOLE_STORAGE;
    public static Block ALIEN_DEFENDER_BEACON;
    public static Block SHIELD_GENERATOR;

    // Slab
    public static BlockAllHalfSlab DIONA_COBBLESTONE_SLAB;
    public static BlockAllHalfSlab CHALOS_COBBLESTONE_SLAB;
    public static BlockAllHalfSlab NIBIRU_COBBLESTONE_SLAB;
    public static BlockAllHalfSlab DIONA_DUNGEON_BRICK_SLAB;
    public static BlockAllHalfSlab CHALOS_DUNGEON_BRICK_SLAB;
    public static BlockAllHalfSlab NIBIRU_DUNGEON_BRICK_SLAB;

    public static BlockAllHalfSlab CHEESE_SPORE_WOOD_SLAB;
    public static BlockAllHalfSlab INFECTED_OAK_WOOD_SLAB;
    public static BlockAllHalfSlab ALIEN_BERRY_OAK_WOOD_SLAB;

    @Deprecated public static BlockAllHalfSlab DOUBLE_DIONA_COBBLESTONE_SLAB;
    @Deprecated public static BlockAllHalfSlab DOUBLE_CHALOS_COBBLESTONE_SLAB;
    @Deprecated public static BlockAllHalfSlab DOUBLE_NIBIRU_COBBLESTONE_SLAB;
    @Deprecated public static BlockAllHalfSlab DOUBLE_DIONA_DUNGEON_BRICK_SLAB;
    @Deprecated public static BlockAllHalfSlab DOUBLE_CHALOS_DUNGEON_BRICK_SLAB;
    @Deprecated public static BlockAllHalfSlab DOUBLE_NIBIRU_DUNGEON_BRICK_SLAB;

    @Deprecated public static BlockAllHalfSlab DOUBLE_CHEESE_SPORE_WOOD_SLAB;
    @Deprecated public static BlockAllHalfSlab DOUBLE_INFECTED_OAK_WOOD_SLAB;
    @Deprecated public static BlockAllHalfSlab DOUBLE_ALIEN_BERRY_OAK_WOOD_SLAB;

    // Wall
    public static Block DIONA_COBBLESTONE_WALL;
    public static Block CHALOS_COBBLESTONE_WALL;
    public static Block NIBIRU_COBBLESTONE_WALL;
    public static Block DIONA_DUNGEON_BRICK_WALL;
    public static Block CHALOS_DUNGEON_BRICK_WALL;
    public static Block NIBIRU_DUNGEON_BRICK_WALL;

    public static Fluid FLUID_XP;

    public static void init()
    {
        /**************************************************************/
        /*************************INITIAL STUFF************************/
        /**************************************************************/

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

        // Energy Storage
        MPBlocks.DARK_ENERGY_STORAGE_CLUSTER = new BlockTieredEnergyStorageCluster("dark_energy_storage_cluster", BlockTieredEnergyStorageCluster.BlockType.DARK_ENERGY_STORAGE_CLUSTER);
        MPBlocks.NUCLEAR_WASTE_ENERGY_STORAGE_CLUSTER = new BlockTieredEnergyStorageCluster("nuclear_waste_energy_storage_cluster", BlockTieredEnergyStorageCluster.BlockType.NUCLEAR_WASTE_ENERGY_STORAGE_CLUSTER);

        // Polish
        MPBlocks.POLISHED_TIN_DECORATION_BLOCK = new BlockBaseMP("polished_tin_decoration_block", Material.ROCK).setHardness(1.5F);
        MPBlocks.POLISHED_ALUMINUM_DECORATION_BLOCK = new BlockBaseMP("polished_aluminum_decoration_block", Material.ROCK).setHardness(1.5F);

        MPBlocks.DIONA_COBBLESTONE_WALL = new BlockAllWall("diona_cobblestone_wall", BlockAllWall.BlockType.DIONA_COBBLESTONE_WALL);
        MPBlocks.CHALOS_COBBLESTONE_WALL = new BlockAllWall("chalos_cobblestone_wall", BlockAllWall.BlockType.CHALOS_COBBLESTONE_WALL);
        MPBlocks.NIBIRU_COBBLESTONE_WALL = new BlockAllWall("nibiru_cobblestone_wall", BlockAllWall.BlockType.NIBIRU_COBBLESTONE_WALL);
        MPBlocks.DIONA_DUNGEON_BRICK_WALL = new BlockAllWall("diona_dungeon_brick_wall", BlockAllWall.BlockType.DIONA_DUNGEON_BRICK_WALL);
        MPBlocks.CHALOS_DUNGEON_BRICK_WALL = new BlockAllWall("chalos_dungeon_brick_wall", BlockAllWall.BlockType.CHALOS_DUNGEON_BRICK_WALL);
        MPBlocks.NIBIRU_DUNGEON_BRICK_WALL = new BlockAllWall("nibiru_dungeon_brick_wall", BlockAllWall.BlockType.NIBIRU_DUNGEON_BRICK_WALL);

        // Tinted Glass
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

        MPBlocks.DIONA_COBBLESTONE_SLAB = new BlockAllHalfSlab("diona_cobblestone_slab", BlockAllHalfSlab.BlockType.DIONA_COBBLESTONE_SLAB);
        MPBlocks.CHALOS_COBBLESTONE_SLAB = new BlockAllHalfSlab("chalos_cobblestone_slab", BlockAllHalfSlab.BlockType.CHALOS_COBBLESTONE_SLAB);
        MPBlocks.NIBIRU_COBBLESTONE_SLAB = new BlockAllHalfSlab("nibiru_cobblestone_slab", BlockAllHalfSlab.BlockType.NIBIRU_COBBLESTONE_SLAB);
        MPBlocks.DIONA_DUNGEON_BRICK_SLAB = new BlockAllHalfSlab("diona_dungeon_brick_slab", BlockAllHalfSlab.BlockType.DIONA_DUNGEON_BRICK_SLAB);
        MPBlocks.CHALOS_DUNGEON_BRICK_SLAB = new BlockAllHalfSlab("chalos_dungeon_brick_slab", BlockAllHalfSlab.BlockType.CHALOS_DUNGEON_BRICK_SLAB);
        MPBlocks.NIBIRU_DUNGEON_BRICK_SLAB = new BlockAllHalfSlab("nibiru_dungeon_brick_slab", BlockAllHalfSlab.BlockType.NIBIRU_DUNGEON_BRICK_SLAB);

        MPBlocks.CHEESE_SPORE_WOOD_SLAB = new BlockAllHalfSlab("cheese_spore_wood_slab", BlockAllHalfSlab.BlockType.CHEESE_SPORE_WOOD_SLAB, Material.WOOD);
        MPBlocks.INFECTED_OAK_WOOD_SLAB = new BlockAllHalfSlab("infected_oak_wood_slab", BlockAllHalfSlab.BlockType.INFECTED_OAK_WOOD_SLAB, Material.WOOD);
        MPBlocks.ALIEN_BERRY_OAK_WOOD_SLAB = new BlockAllHalfSlab("alien_berry_oak_wood_slab", BlockAllHalfSlab.BlockType.ALIEN_BERRY_OAK_WOOD_SLAB, Material.WOOD);

        MPBlocks.DOUBLE_DIONA_COBBLESTONE_SLAB = new BlockAllDoubleSlab("double_diona_cobblestone_slab", BlockAllHalfSlab.BlockType.DIONA_COBBLESTONE_SLAB);
        MPBlocks.DOUBLE_CHALOS_COBBLESTONE_SLAB = new BlockAllDoubleSlab("double_chalos_cobblestone_slab", BlockAllHalfSlab.BlockType.CHALOS_COBBLESTONE_SLAB);
        MPBlocks.DOUBLE_NIBIRU_COBBLESTONE_SLAB = new BlockAllDoubleSlab("double_nibiru_cobblestone_slab", BlockAllHalfSlab.BlockType.NIBIRU_COBBLESTONE_SLAB);
        MPBlocks.DOUBLE_DIONA_DUNGEON_BRICK_SLAB = new BlockAllDoubleSlab("double_diona_dungeon_brick_slab", BlockAllHalfSlab.BlockType.DIONA_DUNGEON_BRICK_SLAB);
        MPBlocks.DOUBLE_CHALOS_DUNGEON_BRICK_SLAB = new BlockAllDoubleSlab("double_chalos_dungeon_brick_slab", BlockAllHalfSlab.BlockType.CHALOS_DUNGEON_BRICK_SLAB);
        MPBlocks.DOUBLE_NIBIRU_DUNGEON_BRICK_SLAB = new BlockAllDoubleSlab("double_nibiru_dungeon_brick_slab", BlockAllHalfSlab.BlockType.NIBIRU_DUNGEON_BRICK_SLAB);

        MPBlocks.DOUBLE_CHEESE_SPORE_WOOD_SLAB = new BlockAllDoubleSlab("double_cheese_spore_wood_slab", BlockAllHalfSlab.BlockType.CHEESE_SPORE_WOOD_SLAB, Material.WOOD);
        MPBlocks.DOUBLE_INFECTED_OAK_WOOD_SLAB = new BlockAllDoubleSlab("double_infected_oak_wood_slab", BlockAllHalfSlab.BlockType.INFECTED_OAK_WOOD_SLAB, Material.WOOD);
        MPBlocks.DOUBLE_ALIEN_BERRY_OAK_WOOD_SLAB = new BlockAllDoubleSlab("double_alien_berry_oak_wood_slab", BlockAllHalfSlab.BlockType.ALIEN_BERRY_OAK_WOOD_SLAB, Material.WOOD);

        MPBlocks.SPACE_WARP_PAD = new BlockSpaceWarpPad("space_warp_pad");
        MPBlocks.SPACE_WARP_PAD_FULL = new BlockSpaceWarpPadFull("space_warp_pad_full");
        MPBlocks.ROCKET_CRUSHER = new BlockRocketCrusher("rocket_crusher");
        MPBlocks.DUNGEON_GLOWSTONE = new BlockBaseMP("dungeon_glowstone", Material.GLASS).setSoundType(SoundType.GLASS).setResistance(100.0F).setHardness(0.3F).setLightLevel(1.0F);
        MPBlocks.DARK_ENERGY_RECEIVER = new BlockDarkEnergyReceiver("dark_energy_receiver");
        MPBlocks.SPACE_PORTAL = new BlockSpacePortal("space_portal");
        MPBlocks.BLACK_HOLE_STORAGE = new BlockBlackHoleStorage("black_hole_storage");
        MPBlocks.ALIEN_DEFENDER_BEACON = new BlockAlienDefenderBeacon("alien_defender_beacon");
        MPBlocks.SHIELD_GENERATOR = new BlockShieldGenerator("shield_generator");

        if (CompatibilityManagerMP.isModAddedXpFluid())
        {
            MPBlocks.FLUID_XP = new FluidMP("xpjuice").setLuminosity(10).setDensity(800).setViscosity(1500);
            FluidRegistry.addBucketForFluid(MPBlocks.FLUID_XP);
        }

        /**************************************************************/
        /************************REGISTER STUFF************************/
        /**************************************************************/

        // Dummy
        CommonRegisterHelper.registerBlock(MPBlocks.WARP_PAD_DUMMY, null);
        CommonRegisterHelper.registerBlock(MPBlocks.DER_SOLAR1_DUMMY, null);
        CommonRegisterHelper.registerBlock(MPBlocks.DER_SOLAR2_DUMMY, null);
        CommonRegisterHelper.registerBlock(MPBlocks.DER_SOLAR3_DUMMY, null);
        CommonRegisterHelper.registerBlock(MPBlocks.DER_SOLAR4_DUMMY, null);
        CommonRegisterHelper.registerBlock(MPBlocks.NWT_MIDDLE_DUMMY, null);
        CommonRegisterHelper.registerBlock(MPBlocks.NWT_TOP_DUMMY, null);
        CommonRegisterHelper.registerBlock(MPBlocks.SHIELD_GENERATOR_DUMMY, null);

        // Boss Spawner
        CommonRegisterHelper.registerBlock(MPBlocks.DIONA_DUNGEON_SPAWNER, null);
        CommonRegisterHelper.registerBlock(MPBlocks.CHALOS_DUNGEON_SPAWNER, null);
        CommonRegisterHelper.registerBlock(MPBlocks.NIBIRU_DUNGEON_SPAWNER, null);

        // Energy Storage
        CommonRegisterHelper.registerBlock(MPBlocks.DARK_ENERGY_STORAGE_CLUSTER, ItemBlockDescription::new);
        CommonRegisterHelper.registerBlock(MPBlocks.NUCLEAR_WASTE_ENERGY_STORAGE_CLUSTER, ItemBlockDescription::new);

        // Polish
        CommonRegisterHelper.registerBlock(MPBlocks.POLISHED_TIN_DECORATION_BLOCK);
        CommonRegisterHelper.registerBlock(MPBlocks.POLISHED_ALUMINUM_DECORATION_BLOCK);

        // Tinted Glass
        CommonRegisterHelper.registerBlock(MPBlocks.WHITE_TINTED_GLASS);
        CommonRegisterHelper.registerBlock(MPBlocks.ORANGE_TINTED_GLASS);
        CommonRegisterHelper.registerBlock(MPBlocks.MAGENTA_TINTED_GLASS);
        CommonRegisterHelper.registerBlock(MPBlocks.LIGHT_BLUE_TINTED_GLASS);
        CommonRegisterHelper.registerBlock(MPBlocks.YELLOW_TINTED_GLASS);
        CommonRegisterHelper.registerBlock(MPBlocks.LIME_TINTED_GLASS);
        CommonRegisterHelper.registerBlock(MPBlocks.PINK_TINTED_GLASS);
        CommonRegisterHelper.registerBlock(MPBlocks.GRAY_TINTED_GLASS);
        CommonRegisterHelper.registerBlock(MPBlocks.SILVER_TINTED_GLASS);
        CommonRegisterHelper.registerBlock(MPBlocks.CYAN_TINTED_GLASS);
        CommonRegisterHelper.registerBlock(MPBlocks.PURPLE_TINTED_GLASS);
        CommonRegisterHelper.registerBlock(MPBlocks.BLUE_TINTED_GLASS);
        CommonRegisterHelper.registerBlock(MPBlocks.BROWN_TINTED_GLASS);
        CommonRegisterHelper.registerBlock(MPBlocks.GREEN_TINTED_GLASS);
        CommonRegisterHelper.registerBlock(MPBlocks.RED_TINTED_GLASS);
        CommonRegisterHelper.registerBlock(MPBlocks.BLACK_TINTED_GLASS);

        // Tinted Glass Pane
        CommonRegisterHelper.registerBlock(MPBlocks.WHITE_TINTED_GLASS_PANE);
        CommonRegisterHelper.registerBlock(MPBlocks.ORANGE_TINTED_GLASS_PANE);
        CommonRegisterHelper.registerBlock(MPBlocks.MAGENTA_TINTED_GLASS_PANE);
        CommonRegisterHelper.registerBlock(MPBlocks.LIGHT_BLUE_TINTED_GLASS_PANE);
        CommonRegisterHelper.registerBlock(MPBlocks.YELLOW_TINTED_GLASS_PANE);
        CommonRegisterHelper.registerBlock(MPBlocks.LIME_TINTED_GLASS_PANE);
        CommonRegisterHelper.registerBlock(MPBlocks.PINK_TINTED_GLASS_PANE);
        CommonRegisterHelper.registerBlock(MPBlocks.GRAY_TINTED_GLASS_PANE);
        CommonRegisterHelper.registerBlock(MPBlocks.SILVER_TINTED_GLASS_PANE);
        CommonRegisterHelper.registerBlock(MPBlocks.CYAN_TINTED_GLASS_PANE);
        CommonRegisterHelper.registerBlock(MPBlocks.PURPLE_TINTED_GLASS_PANE);
        CommonRegisterHelper.registerBlock(MPBlocks.BLUE_TINTED_GLASS_PANE);
        CommonRegisterHelper.registerBlock(MPBlocks.BROWN_TINTED_GLASS_PANE);
        CommonRegisterHelper.registerBlock(MPBlocks.GREEN_TINTED_GLASS_PANE);
        CommonRegisterHelper.registerBlock(MPBlocks.RED_TINTED_GLASS_PANE);
        CommonRegisterHelper.registerBlock(MPBlocks.BLACK_TINTED_GLASS_PANE);

        CommonRegisterHelper.registerBlock(MPBlocks.DIONA_COBBLESTONE_WALL);
        CommonRegisterHelper.registerBlock(MPBlocks.CHALOS_COBBLESTONE_WALL);
        CommonRegisterHelper.registerBlock(MPBlocks.NIBIRU_COBBLESTONE_WALL);
        CommonRegisterHelper.registerBlock(MPBlocks.DIONA_DUNGEON_BRICK_WALL);
        CommonRegisterHelper.registerBlock(MPBlocks.CHALOS_DUNGEON_BRICK_WALL);
        CommonRegisterHelper.registerBlock(MPBlocks.NIBIRU_DUNGEON_BRICK_WALL);

        CommonRegisterHelper.registerBlock(MPBlocks.DIONA_COBBLESTONE_SLAB, ItemBlockSlabMP::new);
        CommonRegisterHelper.registerBlock(MPBlocks.CHALOS_COBBLESTONE_SLAB, ItemBlockSlabMP::new);
        CommonRegisterHelper.registerBlock(MPBlocks.NIBIRU_COBBLESTONE_SLAB, ItemBlockSlabMP::new);
        CommonRegisterHelper.registerBlock(MPBlocks.DIONA_DUNGEON_BRICK_SLAB, ItemBlockSlabMP::new);
        CommonRegisterHelper.registerBlock(MPBlocks.CHALOS_DUNGEON_BRICK_SLAB, ItemBlockSlabMP::new);
        CommonRegisterHelper.registerBlock(MPBlocks.NIBIRU_DUNGEON_BRICK_SLAB, ItemBlockSlabMP::new);

        CommonRegisterHelper.registerBlock(MPBlocks.CHEESE_SPORE_WOOD_SLAB, ItemBlockSlabMP::new);
        CommonRegisterHelper.registerBlock(MPBlocks.INFECTED_OAK_WOOD_SLAB, ItemBlockSlabMP::new);
        CommonRegisterHelper.registerBlock(MPBlocks.ALIEN_BERRY_OAK_WOOD_SLAB, ItemBlockSlabMP::new);

        CommonRegisterHelper.registerBlock(MPBlocks.DOUBLE_DIONA_COBBLESTONE_SLAB, null);
        CommonRegisterHelper.registerBlock(MPBlocks.DOUBLE_CHALOS_COBBLESTONE_SLAB, null);
        CommonRegisterHelper.registerBlock(MPBlocks.DOUBLE_NIBIRU_COBBLESTONE_SLAB, null);
        CommonRegisterHelper.registerBlock(MPBlocks.DOUBLE_DIONA_DUNGEON_BRICK_SLAB, null);
        CommonRegisterHelper.registerBlock(MPBlocks.DOUBLE_CHALOS_DUNGEON_BRICK_SLAB, null);
        CommonRegisterHelper.registerBlock(MPBlocks.DOUBLE_NIBIRU_DUNGEON_BRICK_SLAB, null);

        CommonRegisterHelper.registerBlock(MPBlocks.DOUBLE_CHEESE_SPORE_WOOD_SLAB, null);
        CommonRegisterHelper.registerBlock(MPBlocks.DOUBLE_INFECTED_OAK_WOOD_SLAB, null);
        CommonRegisterHelper.registerBlock(MPBlocks.DOUBLE_ALIEN_BERRY_OAK_WOOD_SLAB, null);

        CommonRegisterHelper.registerBlock(MPBlocks.SPACE_WARP_PAD, ItemBlockSpaceWarpPad::new);
        CommonRegisterHelper.registerBlock(MPBlocks.SPACE_WARP_PAD_FULL, null);
        CommonRegisterHelper.registerBlock(MPBlocks.ROCKET_CRUSHER, ItemBlockDescription::new);
        CommonRegisterHelper.registerBlock(MPBlocks.DUNGEON_GLOWSTONE);
        CommonRegisterHelper.registerBlock(MPBlocks.DARK_ENERGY_RECEIVER, ItemBlockDarkEnergyReceiver::new);

        CommonRegisterHelper.registerBlock(MPBlocks.SPACE_PORTAL);
        CommonRegisterHelper.registerBlock(MPBlocks.BLACK_HOLE_STORAGE, ItemBlockBlackHoleStorage::new);
        CommonRegisterHelper.registerBlock(MPBlocks.ALIEN_DEFENDER_BEACON);
        CommonRegisterHelper.registerBlock(MPBlocks.SHIELD_GENERATOR, ItemBlockShieldGenerator::new);

        DionaBlocks.init();
        KoentusBlocks.init();
        ChalosBlocks.init();
        NibiruBlocks.init();
        FronosBlocks.init();

        /**************************************************************/
        /**********************HARVEST LEVEL STUFF*********************/
        /**************************************************************/

        // Dummy
        BlockUtils.setBlockHarvestLevel(MPBlocks.WARP_PAD_DUMMY, EnumHarvestLevel.PICKAXE, 2);
        BlockUtils.setBlockHarvestLevel(MPBlocks.DER_SOLAR1_DUMMY, EnumHarvestLevel.PICKAXE, 2);
        BlockUtils.setBlockHarvestLevel(MPBlocks.DER_SOLAR2_DUMMY, EnumHarvestLevel.PICKAXE, 2);
        BlockUtils.setBlockHarvestLevel(MPBlocks.DER_SOLAR3_DUMMY, EnumHarvestLevel.PICKAXE, 2);
        BlockUtils.setBlockHarvestLevel(MPBlocks.DER_SOLAR4_DUMMY, EnumHarvestLevel.PICKAXE, 2);
        BlockUtils.setBlockHarvestLevel(MPBlocks.NWT_MIDDLE_DUMMY, EnumHarvestLevel.PICKAXE, 2);
        BlockUtils.setBlockHarvestLevel(MPBlocks.NWT_TOP_DUMMY, EnumHarvestLevel.PICKAXE, 2);
        BlockUtils.setBlockHarvestLevel(MPBlocks.SHIELD_GENERATOR_DUMMY, EnumHarvestLevel.PICKAXE, 2);

        // Energy Storage
        BlockUtils.setBlockHarvestLevel(MPBlocks.DARK_ENERGY_STORAGE_CLUSTER, EnumHarvestLevel.PICKAXE, 2);
        BlockUtils.setBlockHarvestLevel(MPBlocks.NUCLEAR_WASTE_ENERGY_STORAGE_CLUSTER, EnumHarvestLevel.PICKAXE, 2);

        // Polish
        BlockUtils.setBlockHarvestLevel(MPBlocks.POLISHED_TIN_DECORATION_BLOCK, EnumHarvestLevel.PICKAXE, 0);
        BlockUtils.setBlockHarvestLevel(MPBlocks.POLISHED_ALUMINUM_DECORATION_BLOCK, EnumHarvestLevel.PICKAXE, 0);

        BlockUtils.setBlockHarvestLevel(MPBlocks.DIONA_COBBLESTONE_SLAB, EnumHarvestLevel.PICKAXE, 0);
        BlockUtils.setBlockHarvestLevel(MPBlocks.CHALOS_COBBLESTONE_SLAB, EnumHarvestLevel.PICKAXE, 0);
        BlockUtils.setBlockHarvestLevel(MPBlocks.NIBIRU_COBBLESTONE_SLAB, EnumHarvestLevel.PICKAXE, 0);
        BlockUtils.setBlockHarvestLevel(MPBlocks.DIONA_DUNGEON_BRICK_SLAB, EnumHarvestLevel.PICKAXE, 1);
        BlockUtils.setBlockHarvestLevel(MPBlocks.CHALOS_DUNGEON_BRICK_SLAB, EnumHarvestLevel.PICKAXE, 1);
        BlockUtils.setBlockHarvestLevel(MPBlocks.NIBIRU_DUNGEON_BRICK_SLAB, EnumHarvestLevel.PICKAXE, 1);

        BlockUtils.setBlockHarvestLevel(MPBlocks.DOUBLE_DIONA_COBBLESTONE_SLAB, EnumHarvestLevel.PICKAXE, 0);
        BlockUtils.setBlockHarvestLevel(MPBlocks.DOUBLE_CHALOS_COBBLESTONE_SLAB, EnumHarvestLevel.PICKAXE, 0);
        BlockUtils.setBlockHarvestLevel(MPBlocks.DOUBLE_NIBIRU_COBBLESTONE_SLAB, EnumHarvestLevel.PICKAXE, 0);
        BlockUtils.setBlockHarvestLevel(MPBlocks.DOUBLE_DIONA_DUNGEON_BRICK_SLAB, EnumHarvestLevel.PICKAXE, 1);
        BlockUtils.setBlockHarvestLevel(MPBlocks.DOUBLE_CHALOS_DUNGEON_BRICK_SLAB, EnumHarvestLevel.PICKAXE, 1);
        BlockUtils.setBlockHarvestLevel(MPBlocks.DOUBLE_NIBIRU_DUNGEON_BRICK_SLAB, EnumHarvestLevel.PICKAXE, 1);

        BlockUtils.setBlockHarvestLevel(MPBlocks.CHEESE_SPORE_WOOD_SLAB, EnumHarvestLevel.AXE, 0);
        BlockUtils.setBlockHarvestLevel(MPBlocks.INFECTED_OAK_WOOD_SLAB, EnumHarvestLevel.AXE, 0);
        BlockUtils.setBlockHarvestLevel(MPBlocks.ALIEN_BERRY_OAK_WOOD_SLAB, EnumHarvestLevel.AXE, 0);

        BlockUtils.setBlockHarvestLevel(MPBlocks.DOUBLE_CHEESE_SPORE_WOOD_SLAB, EnumHarvestLevel.AXE, 0);
        BlockUtils.setBlockHarvestLevel(MPBlocks.DOUBLE_INFECTED_OAK_WOOD_SLAB, EnumHarvestLevel.AXE, 0);
        BlockUtils.setBlockHarvestLevel(MPBlocks.DOUBLE_ALIEN_BERRY_OAK_WOOD_SLAB, EnumHarvestLevel.AXE, 0);

        BlockUtils.setBlockHarvestLevel(MPBlocks.DIONA_COBBLESTONE_WALL, EnumHarvestLevel.PICKAXE, 0);
        BlockUtils.setBlockHarvestLevel(MPBlocks.CHALOS_COBBLESTONE_WALL, EnumHarvestLevel.PICKAXE, 0);
        BlockUtils.setBlockHarvestLevel(MPBlocks.NIBIRU_COBBLESTONE_WALL, EnumHarvestLevel.PICKAXE, 0);
        BlockUtils.setBlockHarvestLevel(MPBlocks.DIONA_DUNGEON_BRICK_WALL, EnumHarvestLevel.PICKAXE, 1);
        BlockUtils.setBlockHarvestLevel(MPBlocks.CHALOS_DUNGEON_BRICK_WALL, EnumHarvestLevel.PICKAXE, 1);
        BlockUtils.setBlockHarvestLevel(MPBlocks.NIBIRU_DUNGEON_BRICK_WALL, EnumHarvestLevel.PICKAXE, 1);
        BlockUtils.setBlockHarvestLevel(MPBlocks.SPACE_WARP_PAD, EnumHarvestLevel.PICKAXE, 2);
        BlockUtils.setBlockHarvestLevel(MPBlocks.SPACE_WARP_PAD_FULL, EnumHarvestLevel.PICKAXE, 2);
        BlockUtils.setBlockHarvestLevel(MPBlocks.ROCKET_CRUSHER, EnumHarvestLevel.PICKAXE, 2);
        BlockUtils.setBlockHarvestLevel(MPBlocks.DARK_ENERGY_RECEIVER, EnumHarvestLevel.PICKAXE, 2);
        BlockUtils.setBlockHarvestLevel(MPBlocks.SHIELD_GENERATOR, EnumHarvestLevel.PICKAXE, 2);

        MPBlocks.DIONA_COBBLESTONE_SLAB.setHalf(MPBlocks.DIONA_COBBLESTONE_SLAB);
        MPBlocks.CHALOS_COBBLESTONE_SLAB.setHalf(MPBlocks.CHALOS_COBBLESTONE_SLAB);
        MPBlocks.NIBIRU_COBBLESTONE_SLAB.setHalf(MPBlocks.NIBIRU_COBBLESTONE_SLAB);
        MPBlocks.DIONA_DUNGEON_BRICK_SLAB.setHalf(MPBlocks.DIONA_DUNGEON_BRICK_SLAB);
        MPBlocks.CHALOS_DUNGEON_BRICK_SLAB.setHalf(MPBlocks.CHALOS_DUNGEON_BRICK_SLAB);
        MPBlocks.NIBIRU_DUNGEON_BRICK_SLAB.setHalf(MPBlocks.NIBIRU_DUNGEON_BRICK_SLAB);
        MPBlocks.DIONA_COBBLESTONE_SLAB.setDouble(MPBlocks.DOUBLE_DIONA_COBBLESTONE_SLAB);
        MPBlocks.CHALOS_COBBLESTONE_SLAB.setDouble(MPBlocks.DOUBLE_CHALOS_COBBLESTONE_SLAB);
        MPBlocks.NIBIRU_COBBLESTONE_SLAB.setDouble(MPBlocks.DOUBLE_NIBIRU_COBBLESTONE_SLAB);
        MPBlocks.DIONA_DUNGEON_BRICK_SLAB.setDouble(MPBlocks.DOUBLE_DIONA_DUNGEON_BRICK_SLAB);
        MPBlocks.CHALOS_DUNGEON_BRICK_SLAB.setDouble(MPBlocks.DOUBLE_CHALOS_DUNGEON_BRICK_SLAB);
        MPBlocks.NIBIRU_DUNGEON_BRICK_SLAB.setDouble(MPBlocks.DOUBLE_NIBIRU_DUNGEON_BRICK_SLAB);

        MPBlocks.CHEESE_SPORE_WOOD_SLAB.setHalf(MPBlocks.CHEESE_SPORE_WOOD_SLAB);
        MPBlocks.INFECTED_OAK_WOOD_SLAB.setHalf(MPBlocks.INFECTED_OAK_WOOD_SLAB);
        MPBlocks.ALIEN_BERRY_OAK_WOOD_SLAB.setHalf(MPBlocks.ALIEN_BERRY_OAK_WOOD_SLAB);
        MPBlocks.CHEESE_SPORE_WOOD_SLAB.setDouble(MPBlocks.DOUBLE_CHEESE_SPORE_WOOD_SLAB);
        MPBlocks.INFECTED_OAK_WOOD_SLAB.setDouble(MPBlocks.DOUBLE_INFECTED_OAK_WOOD_SLAB);
        MPBlocks.ALIEN_BERRY_OAK_WOOD_SLAB.setDouble(MPBlocks.DOUBLE_ALIEN_BERRY_OAK_WOOD_SLAB);

        MPBlocks.DOUBLE_DIONA_COBBLESTONE_SLAB.setHalf(MPBlocks.DIONA_COBBLESTONE_SLAB);
        MPBlocks.DOUBLE_CHALOS_COBBLESTONE_SLAB.setHalf(MPBlocks.CHALOS_COBBLESTONE_SLAB);
        MPBlocks.DOUBLE_NIBIRU_COBBLESTONE_SLAB.setHalf(MPBlocks.NIBIRU_COBBLESTONE_SLAB);
        MPBlocks.DOUBLE_DIONA_DUNGEON_BRICK_SLAB.setHalf(MPBlocks.DIONA_DUNGEON_BRICK_SLAB);
        MPBlocks.DOUBLE_CHALOS_DUNGEON_BRICK_SLAB.setHalf(MPBlocks.CHALOS_DUNGEON_BRICK_SLAB);
        MPBlocks.DOUBLE_NIBIRU_DUNGEON_BRICK_SLAB.setHalf(MPBlocks.NIBIRU_DUNGEON_BRICK_SLAB);

        MPBlocks.DOUBLE_CHEESE_SPORE_WOOD_SLAB.setHalf(MPBlocks.CHEESE_SPORE_WOOD_SLAB);
        MPBlocks.DOUBLE_INFECTED_OAK_WOOD_SLAB.setHalf(MPBlocks.INFECTED_OAK_WOOD_SLAB);
        MPBlocks.DOUBLE_ALIEN_BERRY_OAK_WOOD_SLAB.setHalf(MPBlocks.ALIEN_BERRY_OAK_WOOD_SLAB);
    }
}