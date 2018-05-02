package stevekung.mods.moreplanets.module.planets.fronos.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import stevekung.mods.moreplanets.utils.BlocksItemsRegistry;
import stevekung.mods.moreplanets.utils.blocks.*;
import stevekung.mods.stevekunglib.utils.BlockUtils;
import stevekung.mods.stevekunglib.utils.EnumHarvestLevel;

public class FronosBlocks
{
    public static BlockCobblestoneDrop FRONOS_STONE;
    public static Block FRONOS_COBBLESTONE;
    public static Block FRONOS_STONE_BRICKS;
    public static Block FRONOS_MOSSY_STONE_BRICKS;
    public static Block FRONOS_CRACKED_STONE_BRICKS;
    public static Block FRONOS_CHISELED_STONE_BRICKS;
    public static Block FRONOS_DUNGEON_BRICK;

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

    public static Block RED_CANDY_CANE;
    public static Block GREEN_CANDY_CANE;
    public static Block BLUE_CANDY_CANE;
    public static Block ORANGE_CANDY_CANE;
    public static Block PINK_CANDY_CANE;
    public static Block YELLOW_CANDY_CANE;
    public static Block PURPLE_CANDY_CANE;
    public static Block RAINBOW_CANDY_CANE;

    public static Block FRONOS_GRASS_BLOCK;
    public static Block FRONOS_DIRT;
    public static Block FRONOS_COARSE_DIRT;
    public static Block FRONOS_FARMLAND;

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
        /**************************************************************/
        /*************************INITIAL STUFF************************/
        /**************************************************************/

        FronosBlocks.FRONOS_STONE = new BlockCobblestoneDrop("fronos_stone").setHardness(1.5F);
        FronosBlocks.FRONOS_COBBLESTONE = new BlockBaseMP("fronos_cobblestone", Material.ROCK).setHardness(2.0F);
        FronosBlocks.FRONOS_STONE_BRICKS = new BlockBaseMP("fronos_stone_bricks", Material.ROCK).setHardness(1.5F);
        FronosBlocks.FRONOS_MOSSY_STONE_BRICKS = new BlockBaseMP("fronos_mossy_stone_bricks", Material.ROCK).setHardness(1.5F);
        FronosBlocks.FRONOS_CRACKED_STONE_BRICKS = new BlockBaseMP("fronos_cracked_stone_bricks", Material.ROCK).setHardness(1.5F);
        FronosBlocks.FRONOS_CHISELED_STONE_BRICKS = new BlockBaseMP("fronos_chiseled_stone_bricks", Material.ROCK).setHardness(1.5F);
        FronosBlocks.FRONOS_DUNGEON_BRICK = new BlockBaseMP("fronos_dungeon_brick", Material.ROCK).setSortCategory(EnumSortCategoryBlock.DUNGEON_BRICK).setHardness(4.0F).setResistance(40.0F);

        FronosBlocks.FRONOS_IRON_ORE = new BlockMineableOre("fronos_iron_ore").setHardness(3.0F);
        FronosBlocks.FRONOS_GOLD_ORE = new BlockMineableOre("fronos_gold_ore").setHardness(3.0F);
        FronosBlocks.FRONOS_TIN_ORE = new BlockMineableOre("fronos_tin_ore").setHardness(3.0F);
        FronosBlocks.FRONOS_COPPER_ORE = new BlockMineableOre("fronos_copper_ore").setHardness(3.0F);
        FronosBlocks.FRONOS_ALUMINUM_ORE = new BlockMineableOre("fronos_aluminum_ore").setHardness(3.0F);
        FronosBlocks.FRONOS_LEAD_ORE = new BlockMineableOre("fronos_lead_ore").setHardness(3.0F);
        FronosBlocks.FRONOS_COAL_ORE = new BlockDropableOre("fronos_coal_ore", BlockDropableOre.BlockType.COAL_ORE).setHardness(3.0F);
        FronosBlocks.FRONOS_LAPIS_ORE = new BlockDropableOre("fronos_lapis_ore", BlockDropableOre.BlockType.LAPIS_ORE).setHardness(3.0F);
        FronosBlocks.FRONOS_DIAMOND_ORE = new BlockDropableOre("fronos_diamond_ore", BlockDropableOre.BlockType.DIAMOND_ORE).setHardness(3.0F);
        FronosBlocks.FRONOS_EMERALD_ORE = new BlockDropableOre("fronos_emerald_ore", BlockDropableOre.BlockType.EMERALD_ORE).setHardness(3.0F);
        FronosBlocks.FRONOS_REDSTONE_ORE = new BlockDropableLitOre("fronos_redstone_ore", BlockDropableLitOre.BlockType.REDSTONE_ORE).setHardness(3.0F);
        FronosBlocks.FRONOS_SILICON_ORE = new BlockDropableOre("fronos_silicon_ore", BlockDropableOre.BlockType.SILICON_ORE).setHardness(3.0F);
        FronosBlocks.FRONOS_QUARTZ_ORE = new BlockDropableOre("fronos_quartz_ore", BlockDropableOre.BlockType.QUARTZ_ORE).setHardness(3.0F);
        FronosBlocks.EXTRAILONITE_ORE = new BlockMineableOre("extrailonite_ore").setHardness(5.0F);

        FronosBlocks.RED_CANDY_CANE = new BlockHorizontalMP("red_candy_cane", Material.CLOTH).setSoundType(SoundType.CLOTH).setHardness(0.55F).setResistance(3.0F);
        FronosBlocks.GREEN_CANDY_CANE = new BlockHorizontalMP("green_candy_cane", Material.CLOTH).setSoundType(SoundType.CLOTH).setHardness(0.55F).setResistance(3.0F);
        FronosBlocks.BLUE_CANDY_CANE = new BlockHorizontalMP("blue_candy_cane", Material.CLOTH).setSoundType(SoundType.CLOTH).setHardness(0.55F).setResistance(3.0F);
        FronosBlocks.ORANGE_CANDY_CANE = new BlockHorizontalMP("orange_candy_cane", Material.CLOTH).setSoundType(SoundType.CLOTH).setHardness(0.55F).setResistance(3.0F);
        FronosBlocks.PINK_CANDY_CANE = new BlockHorizontalMP("pink_candy_cane", Material.CLOTH).setSoundType(SoundType.CLOTH).setHardness(0.55F).setResistance(3.0F);
        FronosBlocks.YELLOW_CANDY_CANE = new BlockHorizontalMP("yellow_candy_cane", Material.CLOTH).setSoundType(SoundType.CLOTH).setHardness(0.55F).setResistance(3.0F);
        FronosBlocks.PURPLE_CANDY_CANE = new BlockHorizontalMP("purple_candy_cane", Material.CLOTH).setSoundType(SoundType.CLOTH).setHardness(0.55F).setResistance(3.0F);
        FronosBlocks.RAINBOW_CANDY_CANE = new BlockHorizontalMP("rainbow_candy_cane", Material.CLOTH).setSoundType(SoundType.CLOTH).setHardness(0.55F).setResistance(3.0F);

        FronosBlocks.FRONOS_GRASS_BLOCK = new BlockFronosGrass("fronos_grass_block");
        FronosBlocks.FRONOS_DIRT = new BlockTerraformable("fronos_dirt", Material.GROUND).setSoundType(SoundType.GROUND).setHardness(0.55F);
        FronosBlocks.FRONOS_COARSE_DIRT = new BlockTerraformable("fronos_coarse_dirt", Material.GROUND).setSoundType(SoundType.GROUND).setHardness(0.55F);
        FronosBlocks.FRONOS_FARMLAND = new BlockFronosFarmland("fronos_farmland");

        FronosBlocks.GRAPE_JELLY_BLOCK = new BlockJelly("grape_jelly_block", BlockJelly.BlockType.GRAPE_JELLY_BLOCK);
        FronosBlocks.RASPBERRY_JELLY_BLOCK = new BlockJelly("raspberry_jelly_block", BlockJelly.BlockType.RASPBERRY_JELLY_BLOCK);
        FronosBlocks.STRAWBERRY_JELLY_BLOCK = new BlockJelly("strawberry_jelly_block", BlockJelly.BlockType.STRAWBERRY_JELLY_BLOCK);
        FronosBlocks.BERRY_JELLY_BLOCK = new BlockJelly("berry_jelly_block", BlockJelly.BlockType.BERRY_JELLY_BLOCK);
        FronosBlocks.LIME_JELLY_BLOCK = new BlockJelly("lime_jelly_block", BlockJelly.BlockType.LIME_JELLY_BLOCK);
        FronosBlocks.ORANGE_JELLY_BLOCK = new BlockJelly("orange_jelly_block", BlockJelly.BlockType.ORANGE_JELLY_BLOCK);
        FronosBlocks.GREEN_JELLY_BLOCK = new BlockJelly("green_jelly_block", BlockJelly.BlockType.GREEN_JELLY_BLOCK);
        FronosBlocks.LEMON_JELLY_BLOCK = new BlockJelly("lemon_jelly_block", BlockJelly.BlockType.LEMON_JELLY_BLOCK);

        /**************************************************************/
        /************************REGISTER STUFF************************/
        /**************************************************************/

        BlocksItemsRegistry.registerBlock(FronosBlocks.FRONOS_STONE);
        BlocksItemsRegistry.registerBlock(FronosBlocks.FRONOS_COBBLESTONE);
        BlocksItemsRegistry.registerBlock(FronosBlocks.FRONOS_STONE_BRICKS);
        BlocksItemsRegistry.registerBlock(FronosBlocks.FRONOS_MOSSY_STONE_BRICKS);
        BlocksItemsRegistry.registerBlock(FronosBlocks.FRONOS_CRACKED_STONE_BRICKS);
        BlocksItemsRegistry.registerBlock(FronosBlocks.FRONOS_CHISELED_STONE_BRICKS);
        BlocksItemsRegistry.registerBlock(FronosBlocks.FRONOS_DUNGEON_BRICK);

        BlocksItemsRegistry.registerBlock(FronosBlocks.FRONOS_IRON_ORE);
        BlocksItemsRegistry.registerBlock(FronosBlocks.FRONOS_GOLD_ORE);
        BlocksItemsRegistry.registerBlock(FronosBlocks.FRONOS_TIN_ORE);
        BlocksItemsRegistry.registerBlock(FronosBlocks.FRONOS_COPPER_ORE);
        BlocksItemsRegistry.registerBlock(FronosBlocks.FRONOS_ALUMINUM_ORE);
        BlocksItemsRegistry.registerBlock(FronosBlocks.FRONOS_LEAD_ORE);
        BlocksItemsRegistry.registerBlock(FronosBlocks.FRONOS_COAL_ORE);
        BlocksItemsRegistry.registerBlock(FronosBlocks.FRONOS_LAPIS_ORE);
        BlocksItemsRegistry.registerBlock(FronosBlocks.FRONOS_DIAMOND_ORE);
        BlocksItemsRegistry.registerBlock(FronosBlocks.FRONOS_EMERALD_ORE);
        BlocksItemsRegistry.registerBlock(FronosBlocks.FRONOS_REDSTONE_ORE);
        BlocksItemsRegistry.registerBlock(FronosBlocks.FRONOS_SILICON_ORE);
        BlocksItemsRegistry.registerBlock(FronosBlocks.FRONOS_QUARTZ_ORE);
        BlocksItemsRegistry.registerBlock(FronosBlocks.EXTRAILONITE_ORE);

        BlocksItemsRegistry.registerBlock(FronosBlocks.RED_CANDY_CANE);
        BlocksItemsRegistry.registerBlock(FronosBlocks.GREEN_CANDY_CANE);
        BlocksItemsRegistry.registerBlock(FronosBlocks.BLUE_CANDY_CANE);
        BlocksItemsRegistry.registerBlock(FronosBlocks.ORANGE_CANDY_CANE);
        BlocksItemsRegistry.registerBlock(FronosBlocks.PINK_CANDY_CANE);
        BlocksItemsRegistry.registerBlock(FronosBlocks.YELLOW_CANDY_CANE);
        BlocksItemsRegistry.registerBlock(FronosBlocks.PURPLE_CANDY_CANE);
        BlocksItemsRegistry.registerBlock(FronosBlocks.RAINBOW_CANDY_CANE);

        BlocksItemsRegistry.registerBlock(FronosBlocks.FRONOS_GRASS_BLOCK);
        BlocksItemsRegistry.registerBlock(FronosBlocks.FRONOS_DIRT);
        BlocksItemsRegistry.registerBlock(FronosBlocks.FRONOS_COARSE_DIRT);
        BlocksItemsRegistry.registerBlock(FronosBlocks.FRONOS_FARMLAND);

        BlocksItemsRegistry.registerBlock(FronosBlocks.GRAPE_JELLY_BLOCK);
        BlocksItemsRegistry.registerBlock(FronosBlocks.RASPBERRY_JELLY_BLOCK);
        BlocksItemsRegistry.registerBlock(FronosBlocks.STRAWBERRY_JELLY_BLOCK);
        BlocksItemsRegistry.registerBlock(FronosBlocks.BERRY_JELLY_BLOCK);
        BlocksItemsRegistry.registerBlock(FronosBlocks.LIME_JELLY_BLOCK);
        BlocksItemsRegistry.registerBlock(FronosBlocks.ORANGE_JELLY_BLOCK);
        BlocksItemsRegistry.registerBlock(FronosBlocks.GREEN_JELLY_BLOCK);
        BlocksItemsRegistry.registerBlock(FronosBlocks.LEMON_JELLY_BLOCK);

        /**************************************************************/
        /**********************HARVEST LEVEL STUFF*********************/
        /**************************************************************/

        BlockUtils.setBlockHarvestLevel(FronosBlocks.FRONOS_COAL_ORE, EnumHarvestLevel.PICKAXE, 0);
        BlockUtils.setBlockHarvestLevel(FronosBlocks.FRONOS_QUARTZ_ORE, EnumHarvestLevel.PICKAXE, 0);
        BlockUtils.setBlockHarvestLevel(FronosBlocks.FRONOS_IRON_ORE, EnumHarvestLevel.PICKAXE, 1);
        BlockUtils.setBlockHarvestLevel(FronosBlocks.FRONOS_TIN_ORE, EnumHarvestLevel.PICKAXE, 1);
        BlockUtils.setBlockHarvestLevel(FronosBlocks.FRONOS_COPPER_ORE, EnumHarvestLevel.PICKAXE, 1);
        BlockUtils.setBlockHarvestLevel(FronosBlocks.FRONOS_ALUMINUM_ORE, EnumHarvestLevel.PICKAXE, 1);
        BlockUtils.setBlockHarvestLevel(FronosBlocks.FRONOS_LEAD_ORE, EnumHarvestLevel.PICKAXE, 1);
        BlockUtils.setBlockHarvestLevel(FronosBlocks.FRONOS_LAPIS_ORE, EnumHarvestLevel.PICKAXE, 1);
        BlockUtils.setBlockHarvestLevel(FronosBlocks.FRONOS_GOLD_ORE, EnumHarvestLevel.PICKAXE, 2);
        BlockUtils.setBlockHarvestLevel(FronosBlocks.FRONOS_DIAMOND_ORE, EnumHarvestLevel.PICKAXE, 2);
        BlockUtils.setBlockHarvestLevel(FronosBlocks.FRONOS_EMERALD_ORE, EnumHarvestLevel.PICKAXE, 2);
        BlockUtils.setBlockHarvestLevel(FronosBlocks.FRONOS_REDSTONE_ORE, EnumHarvestLevel.PICKAXE, 2);
        BlockUtils.setBlockHarvestLevel(FronosBlocks.FRONOS_SILICON_ORE, EnumHarvestLevel.PICKAXE, 2);
        BlockUtils.setBlockHarvestLevel(FronosBlocks.EXTRAILONITE_ORE, EnumHarvestLevel.PICKAXE, 2);

        BlockUtils.setBlockHarvestLevel(FronosBlocks.FRONOS_STONE, EnumHarvestLevel.PICKAXE, 0);
        BlockUtils.setBlockHarvestLevel(FronosBlocks.FRONOS_COBBLESTONE, EnumHarvestLevel.PICKAXE, 0);
        BlockUtils.setBlockHarvestLevel(FronosBlocks.FRONOS_STONE_BRICKS, EnumHarvestLevel.PICKAXE, 0);
        BlockUtils.setBlockHarvestLevel(FronosBlocks.FRONOS_MOSSY_STONE_BRICKS, EnumHarvestLevel.PICKAXE, 0);
        BlockUtils.setBlockHarvestLevel(FronosBlocks.FRONOS_CRACKED_STONE_BRICKS, EnumHarvestLevel.PICKAXE, 0);
        BlockUtils.setBlockHarvestLevel(FronosBlocks.FRONOS_CHISELED_STONE_BRICKS, EnumHarvestLevel.PICKAXE, 0);
        BlockUtils.setBlockHarvestLevel(FronosBlocks.FRONOS_DUNGEON_BRICK, EnumHarvestLevel.PICKAXE, 1);

        BlockUtils.setBlockHarvestLevel(FronosBlocks.FRONOS_GRASS_BLOCK, EnumHarvestLevel.SHOVEL, 0);
        BlockUtils.setBlockHarvestLevel(FronosBlocks.FRONOS_DIRT, EnumHarvestLevel.SHOVEL, 0);
        BlockUtils.setBlockHarvestLevel(FronosBlocks.FRONOS_COARSE_DIRT, EnumHarvestLevel.SHOVEL, 0);
        BlockUtils.setBlockHarvestLevel(FronosBlocks.FRONOS_FARMLAND, EnumHarvestLevel.SHOVEL, 0);

        /**************************************************************/
        /************************FIRE BURN STUFF***********************/
        /**************************************************************/

        BlockUtils.setFireBurn(FronosBlocks.RED_CANDY_CANE, 60, 100);
        BlockUtils.setFireBurn(FronosBlocks.GREEN_CANDY_CANE, 60, 100);
        BlockUtils.setFireBurn(FronosBlocks.BLUE_CANDY_CANE, 60, 100);
        BlockUtils.setFireBurn(FronosBlocks.ORANGE_CANDY_CANE, 60, 100);
        BlockUtils.setFireBurn(FronosBlocks.PINK_CANDY_CANE, 60, 100);
        BlockUtils.setFireBurn(FronosBlocks.YELLOW_CANDY_CANE, 60, 100);
        BlockUtils.setFireBurn(FronosBlocks.PURPLE_CANDY_CANE, 60, 100);
        BlockUtils.setFireBurn(FronosBlocks.RAINBOW_CANDY_CANE, 60, 100);

        FronosBlocks.FRONOS_STONE.setDrop(FronosBlocks.FRONOS_COBBLESTONE);
    }
}