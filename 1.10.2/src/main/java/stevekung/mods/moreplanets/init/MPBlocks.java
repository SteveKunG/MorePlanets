package stevekung.mods.moreplanets.init;

import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;
import stevekung.mods.moreplanets.blocks.*;
import stevekung.mods.moreplanets.blocks.decoration.*;
import stevekung.mods.moreplanets.itemblocks.ItemBlockBlackHoleStorage;
import stevekung.mods.moreplanets.itemblocks.ItemBlockDarkEnergyReceiver;
import stevekung.mods.moreplanets.itemblocks.ItemBlockSpaceWarpPad;
import stevekung.mods.moreplanets.itemblocks.ItemBlockTieredEnergyStorage;
import stevekung.mods.moreplanets.module.planets.chalos.blocks.ChalosBlocks;
import stevekung.mods.moreplanets.module.planets.diona.blocks.DionaBlocks;
import stevekung.mods.moreplanets.module.planets.fronos.blocks.FronosBlocks;
import stevekung.mods.moreplanets.module.planets.nibiru.blocks.NibiruBlocks;
import stevekung.mods.moreplanets.util.EnumHarvestLevel;
import stevekung.mods.moreplanets.util.blocks.BlockBaseMP;
import stevekung.mods.moreplanets.util.blocks.BlockSlabMP;
import stevekung.mods.moreplanets.util.helper.CommonRegisterHelper;
import stevekung.mods.moreplanets.util.itemblocks.ItemBlockColoredMP;
import stevekung.mods.moreplanets.util.itemblocks.ItemBlockDescription;
import stevekung.mods.moreplanets.util.itemblocks.ItemBlockMultiVariant;
import stevekung.mods.moreplanets.util.itemblocks.ItemBlockSlabMP;

public class MPBlocks
{
    // Base
    public static BlockDummy DUMMY_BLOCK;
    public static Block SPACE_WARP_PAD;
    public static Block SPACE_WARP_PAD_FULL;
    public static Block SPACE_DUNGEON_SPAWNER;
    public static Block ROCKET_CRUSHER;
    public static Block DUNGEON_GLOWSTONE;
    public static Block TINTED_GLASS;
    public static Block TINTED_GLASS_PANE;
    public static Block POLISHED_SPACE_DECORATION;
    public static Block DARK_ENERGY_RECEIVER;
    public static Block TIERED_ENERGY_STORAGE_CLUSTER;
    public static Block SPACE_PORTAL;
    public static Block BLACK_HOLE_STORAGE;
    public static Block ALIEN_DEFENDER_BEACON;

    // Slab
    public static BlockSlabMP HALF_DUNGEON_BRICK_SLAB_1;
    public static BlockSlabMP DOUBLE_DUNGEON_BRICK_SLAB_1;
    public static BlockSlabMP HALF_COBBLESTONE_SLAB_1;
    public static BlockSlabMP DOUBLE_COBBLESTONE_SLAB_1;
    public static BlockSlabMP HALF_WOODEN_SLAB_1;
    public static BlockSlabMP DOUBLE_WOODEN_SLAB_1;

    // Wall
    public static Block COBBLESTONE_WALL;
    public static Block DUNGEON_BRICK_WALL;

    public static void init()
    {
        /**************************************************************/
        /*************************INITIAL STUFF************************/
        /**************************************************************/

        MPBlocks.DUMMY_BLOCK = new BlockDummy("dummy_block");
        MPBlocks.SPACE_WARP_PAD = new BlockSpaceWarpPad("space_warp_pad");
        MPBlocks.SPACE_WARP_PAD_FULL = new BlockSpaceWarpPadFull("space_warp_pad_full");
        MPBlocks.SPACE_DUNGEON_SPAWNER = new BlockSpaceDungeonSpawner("space_dungeon_spawner");
        MPBlocks.ROCKET_CRUSHER = new BlockRocketCrusher("rocket_crusher");
        MPBlocks.DUNGEON_GLOWSTONE = new BlockBaseMP("dungeon_glowstone", Material.GLASS).setSoundType(SoundType.GLASS).setResistance(100.0F).setHardness(0.3F).setLightLevel(1.0F);
        MPBlocks.TINTED_GLASS = new BlockTintedGlass("tinted_glass");
        MPBlocks.TINTED_GLASS_PANE = new BlockTintedGlassPane("tinted_glass_pane");
        MPBlocks.POLISHED_SPACE_DECORATION = new BlockPolishedSpaceDecoration("polished_space_decoration");
        MPBlocks.DARK_ENERGY_RECEIVER = new BlockDarkEnergyReceiver("dark_energy_receiver");
        MPBlocks.HALF_DUNGEON_BRICK_SLAB_1 = new BlockHalfDungeonBrickSlab1("half_dungeon_brick_slab_1");
        MPBlocks.DOUBLE_DUNGEON_BRICK_SLAB_1 = new BlockDoubleDungeonBrickSlab1("double_dungeon_brick_slab_1");
        MPBlocks.TIERED_ENERGY_STORAGE_CLUSTER = new BlockTieredEnergyStorage("tiered_energy_storage_cluster");
        MPBlocks.HALF_COBBLESTONE_SLAB_1 = new BlockHalfCobblestoneSlab1("half_cobblestone_slab_1");
        MPBlocks.DOUBLE_COBBLESTONE_SLAB_1 = new BlockDoubleCobblestoneSlab1("double_cobblestone_slab_1");
        MPBlocks.HALF_WOODEN_SLAB_1 = new BlockHalfWoodenSlab1("half_wooden_slab_1");
        MPBlocks.DOUBLE_WOODEN_SLAB_1 = new BlockDoubleWoodenSlab1("double_wooden_slab_1");
        MPBlocks.COBBLESTONE_WALL = new BlockCobblestoneWall("cobblestone_wall_mp");
        MPBlocks.DUNGEON_BRICK_WALL = new BlockDungeonBrickWall("dungeon_brick_wall_mp");
        MPBlocks.SPACE_PORTAL = new BlockSpacePortal("space_portal");
        MPBlocks.BLACK_HOLE_STORAGE = new BlockBlackHoleStorage("black_hole_storage");
        MPBlocks.ALIEN_DEFENDER_BEACON = new BlockAlienDefenderBeacon("alien_defender_beacon");

        /**************************************************************/
        /************************REGISTER STUFF************************/
        /**************************************************************/

        CommonRegisterHelper.registerBlock(MPBlocks.DUMMY_BLOCK, null);
        CommonRegisterHelper.registerBlock(MPBlocks.SPACE_WARP_PAD, ItemBlockSpaceWarpPad::new);
        CommonRegisterHelper.registerBlock(MPBlocks.SPACE_WARP_PAD_FULL, null);
        CommonRegisterHelper.registerBlock(MPBlocks.ROCKET_CRUSHER, ItemBlockDescription::new);
        CommonRegisterHelper.registerBlock(MPBlocks.DUNGEON_GLOWSTONE);
        CommonRegisterHelper.registerBlock(MPBlocks.SPACE_DUNGEON_SPAWNER, null);
        CommonRegisterHelper.registerBlock(MPBlocks.TINTED_GLASS, ItemBlockColoredMP::new);
        CommonRegisterHelper.registerBlock(MPBlocks.TINTED_GLASS_PANE, ItemBlockColoredMP::new);
        CommonRegisterHelper.registerBlock(MPBlocks.POLISHED_SPACE_DECORATION, ItemBlockMultiVariant::new);
        CommonRegisterHelper.registerBlock(MPBlocks.DARK_ENERGY_RECEIVER, ItemBlockDarkEnergyReceiver::new);
        CommonRegisterHelper.registerBlock(MPBlocks.HALF_DUNGEON_BRICK_SLAB_1, ItemBlockSlabMP::new);
        CommonRegisterHelper.registerBlock(MPBlocks.DOUBLE_DUNGEON_BRICK_SLAB_1, null);
        CommonRegisterHelper.registerBlock(MPBlocks.TIERED_ENERGY_STORAGE_CLUSTER, ItemBlockTieredEnergyStorage::new);
        CommonRegisterHelper.registerBlock(MPBlocks.HALF_COBBLESTONE_SLAB_1, ItemBlockSlabMP::new);
        CommonRegisterHelper.registerBlock(MPBlocks.DOUBLE_COBBLESTONE_SLAB_1, null);
        CommonRegisterHelper.registerBlock(MPBlocks.HALF_WOODEN_SLAB_1, ItemBlockSlabMP::new);
        CommonRegisterHelper.registerBlock(MPBlocks.DOUBLE_WOODEN_SLAB_1, null);
        CommonRegisterHelper.registerBlock(MPBlocks.COBBLESTONE_WALL, ItemBlockMultiVariant::new);
        CommonRegisterHelper.registerBlock(MPBlocks.DUNGEON_BRICK_WALL, ItemBlockMultiVariant::new);
        CommonRegisterHelper.registerBlock(MPBlocks.SPACE_PORTAL);
        CommonRegisterHelper.registerBlock(MPBlocks.BLACK_HOLE_STORAGE, ItemBlockBlackHoleStorage::new);
        CommonRegisterHelper.registerBlock(MPBlocks.ALIEN_DEFENDER_BEACON);

        DionaBlocks.init();
        ChalosBlocks.init();
        NibiruBlocks.init();
        FronosBlocks.init();

        /**************************************************************/
        /**********************HARVEST LEVEL STUFF*********************/
        /**************************************************************/

        CommonRegisterHelper.setBlockHarvestLevel(MPBlocks.HALF_WOODEN_SLAB_1, EnumHarvestLevel.AXE, 0);
        CommonRegisterHelper.setBlockHarvestLevel(MPBlocks.DOUBLE_WOODEN_SLAB_1, EnumHarvestLevel.AXE, 0);
        CommonRegisterHelper.setBlockHarvestLevel(MPBlocks.POLISHED_SPACE_DECORATION, EnumHarvestLevel.PICKAXE, 0);
        CommonRegisterHelper.setBlockHarvestLevel(MPBlocks.HALF_COBBLESTONE_SLAB_1, EnumHarvestLevel.PICKAXE, 0);
        CommonRegisterHelper.setBlockHarvestLevel(MPBlocks.DOUBLE_COBBLESTONE_SLAB_1, EnumHarvestLevel.PICKAXE, 0);
        CommonRegisterHelper.setBlockHarvestLevel(MPBlocks.COBBLESTONE_WALL, EnumHarvestLevel.PICKAXE, 0);
        CommonRegisterHelper.setBlockHarvestLevel(MPBlocks.DUNGEON_BRICK_WALL, EnumHarvestLevel.PICKAXE, 1);
        CommonRegisterHelper.setBlockHarvestLevel(MPBlocks.HALF_DUNGEON_BRICK_SLAB_1, EnumHarvestLevel.PICKAXE, 1);
        CommonRegisterHelper.setBlockHarvestLevel(MPBlocks.DOUBLE_DUNGEON_BRICK_SLAB_1, EnumHarvestLevel.PICKAXE, 1);
        CommonRegisterHelper.setBlockHarvestLevel(MPBlocks.DUMMY_BLOCK, EnumHarvestLevel.PICKAXE, 2);
        CommonRegisterHelper.setBlockHarvestLevel(MPBlocks.SPACE_WARP_PAD, EnumHarvestLevel.PICKAXE, 2);
        CommonRegisterHelper.setBlockHarvestLevel(MPBlocks.SPACE_WARP_PAD_FULL, EnumHarvestLevel.PICKAXE, 2);
        CommonRegisterHelper.setBlockHarvestLevel(MPBlocks.ROCKET_CRUSHER, EnumHarvestLevel.PICKAXE, 2);
        CommonRegisterHelper.setBlockHarvestLevel(MPBlocks.DARK_ENERGY_RECEIVER, EnumHarvestLevel.PICKAXE, 2);
        CommonRegisterHelper.setBlockHarvestLevel(MPBlocks.TIERED_ENERGY_STORAGE_CLUSTER, EnumHarvestLevel.PICKAXE, 2);

        /**************************************************************/
        /********************ORE DICTIONARY STUFF**********************/
        /**************************************************************/

        CommonRegisterHelper.registerOreDictionary("glowstone", MPBlocks.DUNGEON_GLOWSTONE);
        CommonRegisterHelper.registerOreDictionary("slabWood", new ItemStack(MPBlocks.HALF_WOODEN_SLAB_1, 1, OreDictionary.WILDCARD_VALUE));
        CommonRegisterHelper.registerOreDictionary("blockGlass", new ItemStack(MPBlocks.TINTED_GLASS, 1, OreDictionary.WILDCARD_VALUE));
        CommonRegisterHelper.registerOreDictionary("paneGlass", new ItemStack(MPBlocks.TINTED_GLASS_PANE, 1, OreDictionary.WILDCARD_VALUE));
    }
}