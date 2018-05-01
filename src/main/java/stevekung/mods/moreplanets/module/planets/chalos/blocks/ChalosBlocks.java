package stevekung.mods.moreplanets.module.planets.chalos.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraftforge.fluids.Fluid;
import stevekung.mods.moreplanets.core.MorePlanetsMod;
import stevekung.mods.moreplanets.module.planets.chalos.blocks.fluid.FluidGaseousCheeseMilk;
import stevekung.mods.moreplanets.utils.BlocksItemsRegistry;
import stevekung.mods.moreplanets.utils.blocks.*;
import stevekung.mods.moreplanets.utils.blocks.BlockStairsMP.EnumStairsType;
import stevekung.mods.moreplanets.utils.blocks.fluid.FluidMP;
import stevekung.mods.moreplanets.utils.itemblocks.ItemBlockDescription;
import stevekung.mods.stevekunglib.utils.BlockUtils;
import stevekung.mods.stevekunglib.utils.EnumHarvestLevel;

public class ChalosBlocks
{
    public static Block CHEESE_DIRT;
    public static Block CHEESE_COARSE_DIRT;

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

    public static Block CHEESE_GRASS_BLOCK;
    public static Block CHEESE_FARMLAND;
    public static Block CHEESE_SLIME_BLOCK;
    public static Block CHEESE_MILK_CAKE;
    public static BlockBushMP CHEESE_SPORE_FLOWER;
    public static Block CHALOS_ANCIENT_CHEST;
    public static Block CHALOS_TREASURE_CHEST;
    public static Block CHEESE_MILK_FLUID_BLOCK;
    public static Block GASEOUS_CHEESE_MILK_BLOCK;
    public static Block CHEESE_SPORE;
    public static Block CHEESE_SPORE_STEM;
    public static Block CHEESE_SPORE_PLANKS;
    public static BlockBushMP CHEESE_GRASS;
    public static Block CHEESE_SPORE_BERRY_CROPS;
    public static BlockDoublePlantMP CHEESE_TALL_GRASS;
    public static Block CHEESE_SPORE_CRAFTING_TABLE;
    public static Block CHEESE_SPORE_CHEST;
    public static Block CHALOS_COBBLESTONE_STAIRS;
    public static Block CHALOS_DUNGEON_BRICK_STAIRS;
    public static Block CHEESE_SPORE_STAIRS;
    public static Block CHEESE_SPORE_FENCE;
    public static Block CHEESE_SPORE_FENCE_GATE;
    public static BlockDoorMP CHEESE_SPORE_DOOR_BLOCK;

    public static Fluid CHEESE_MILK_FLUID;
    public static Fluid GASEOUS_CHEESE_MILK;

    public static void init()
    {
        /**************************************************************/
        /*************************INITIAL STUFF************************/
        /**************************************************************/

        ChalosBlocks.CHEESE_DIRT = new BlockTerraformable("cheese_dirt", Material.GROUND).setSoundType(SoundType.GROUND).setHardness(0.55F);
        ChalosBlocks.CHEESE_COARSE_DIRT = new BlockTerraformable("cheese_coarse_dirt", Material.GROUND).setSoundType(SoundType.GROUND).setHardness(0.55F);

        ChalosBlocks.CHALOS_ROCK = new BlockCobblestoneDrop("chalos_rock").setHardness(1.5F);
        ChalosBlocks.CHALOS_COBBLESTONE = new BlockBaseMP("chalos_cobblestone", Material.ROCK).setHardness(2.0F);
        ChalosBlocks.DIREMSIUM_ORE = new BlockMineableOre("diremsium_ore").setHardness(3.0F);
        ChalosBlocks.ZYPTORIUM_ORE = new BlockMineableOre("zyptorium_ore").setHardness(3.0F);
        ChalosBlocks.CHEESE_MILK_ORE = new BlockDropableOre("cheese_milk_ore", BlockDropableOre.BlockType.CHEESE_MILK_ORE);
        ChalosBlocks.CHALOS_IRON_ORE = new BlockMineableOre("chalos_iron_ore").setHardness(3.0F);
        ChalosBlocks.CHALOS_TIN_ORE = new BlockMineableOre("chalos_tin_ore").setHardness(3.0F);
        ChalosBlocks.CHALOS_COPPER_ORE = new BlockMineableOre("chalos_copper_ore").setHardness(3.0F);
        ChalosBlocks.CHALOS_ALUMINUM_ORE = new BlockMineableOre("chalos_aluminum_ore").setHardness(3.0F);
        ChalosBlocks.DIREMSIUM_BLOCK = new BlockCompressedMetal("diremsium_block").setSoundType(SoundType.METAL).setHardness(5.0F);
        ChalosBlocks.ZYPTORIUM_BLOCK = new BlockCompressedMetal("zyptorium_block").setSoundType(SoundType.METAL).setHardness(5.0F);
        ChalosBlocks.CHALOS_DUNGEON_BRICK = new BlockBaseMP("chalos_dungeon_brick", Material.ROCK).setSortCategory(EnumSortCategoryBlock.DUNGEON_BRICK).setHardness(4.0F).setResistance(40.0F);

        ChalosBlocks.CHEESE_GRASS_BLOCK = new BlockCheeseGrassBlock("cheese_grass_block");
        ChalosBlocks.CHEESE_FARMLAND = new BlockCheeseFarmland("cheese_farmland");
        ChalosBlocks.CHEESE_SLIME_BLOCK = new BlockCheeseSlime("cheese_slime_block");
        ChalosBlocks.CHEESE_MILK_CAKE = new BlockCheeseMilkCake("cheese_milk_cake");
        ChalosBlocks.CHEESE_SPORE_FLOWER = new BlockSaplingMP("cheese_spore_flower", BlockSaplingMP.BlockType.CHEESE_SPORE_FLOWER);
        ChalosBlocks.CHALOS_ANCIENT_CHEST = new BlockChalosAncientChest("chalos_ancient_chest");
        ChalosBlocks.CHALOS_TREASURE_CHEST = new BlockChalosTreasureChest("chalos_treasure_chest");
        ChalosBlocks.CHEESE_SPORE = new BlockCheeseSpore("cheese_spore_block");
        ChalosBlocks.CHEESE_SPORE_STEM = new BlockLogMP("cheese_spore_stem");
        ChalosBlocks.CHEESE_SPORE_PLANKS = new BlockBaseMP("cheese_spore_planks", Material.WOOD).setSoundType(SoundType.WOOD).setHardness(2.0F).setResistance(5.0F);
        ChalosBlocks.CHEESE_GRASS = new BlockPlaceableBushMP("cheese_grass", BlockPlaceableBushMP.BlockType.CHEESE_GRASS);
        ChalosBlocks.CHEESE_SPORE_BERRY_CROPS = new BlockCheeseSporeBerryCrops("cheese_spore_berry_crops");
        ChalosBlocks.CHEESE_TALL_GRASS = new BlockDoublePlantMP("cheese_tall_grass", BlockDoublePlantMP.BlockType.CHEESE_TALL_GRASS);
        ChalosBlocks.CHEESE_SPORE_CRAFTING_TABLE = new BlockCraftingTableMP("cheese_spore_crafting_table");
        ChalosBlocks.CHEESE_SPORE_CHEST = new BlockCheeseSporeChest("cheese_spore_chest");
        ChalosBlocks.CHALOS_COBBLESTONE_STAIRS = new BlockStairsMP("chalos_cobblestone_stairs", EnumStairsType.COBBLESTONE);
        ChalosBlocks.CHALOS_DUNGEON_BRICK_STAIRS = new BlockStairsMP("chalos_dungeon_brick_stairs", EnumStairsType.DUNGEON_BRICK).setSortCategory(EnumSortCategoryBlock.STAIRS_DUNGEON_BRICK);
        ChalosBlocks.CHEESE_SPORE_STAIRS = new BlockStairsMP("cheese_spore_stairs", EnumStairsType.WOODEN).setSortCategory(EnumSortCategoryBlock.STAIRS_WOODEN);
        ChalosBlocks.CHEESE_SPORE_FENCE = new BlockFenceMP("cheese_spore_fence");
        ChalosBlocks.CHEESE_SPORE_FENCE_GATE = new BlockFenceGateMP("cheese_spore_fence_gate");
        ChalosBlocks.CHEESE_SPORE_DOOR_BLOCK = new BlockDoorMP("cheese_spore_door_block");

        ChalosBlocks.CHEESE_MILK_FLUID = new FluidMP("cheese_milk_fluid").setBlock(ChalosBlocks.CHEESE_MILK_FLUID_BLOCK).setViscosity(1000);
        ChalosBlocks.GASEOUS_CHEESE_MILK = new FluidGaseousCheeseMilk("gaseous_cheese_milk", "moreplanets:blocks/gaseous_cheese_milk", "moreplanets:blocks/gaseous_cheese_milk").setBlock(ChalosBlocks.GASEOUS_CHEESE_MILK_BLOCK);
        MorePlanetsMod.COMMON_REGISTRY.registerFluid(ChalosBlocks.CHEESE_MILK_FLUID);
        MorePlanetsMod.COMMON_REGISTRY.registerFluid(ChalosBlocks.GASEOUS_CHEESE_MILK);
        ChalosBlocks.GASEOUS_CHEESE_MILK_BLOCK = new BlockGaseousCheeseMilk("gaseous_cheese_milk");
        ChalosBlocks.CHEESE_MILK_FLUID_BLOCK = new BlockFluidCheeseMilk("cheese_milk_fluid");

        /**************************************************************/
        /************************REGISTER STUFF************************/
        /**************************************************************/

        BlocksItemsRegistry.registerBlock(ChalosBlocks.CHALOS_ROCK);
        BlocksItemsRegistry.registerBlock(ChalosBlocks.CHALOS_COBBLESTONE);
        BlocksItemsRegistry.registerBlock(ChalosBlocks.DIREMSIUM_ORE);
        BlocksItemsRegistry.registerBlock(ChalosBlocks.ZYPTORIUM_ORE);
        BlocksItemsRegistry.registerBlock(ChalosBlocks.CHEESE_MILK_ORE);
        BlocksItemsRegistry.registerBlock(ChalosBlocks.CHALOS_IRON_ORE);
        BlocksItemsRegistry.registerBlock(ChalosBlocks.CHALOS_TIN_ORE);
        BlocksItemsRegistry.registerBlock(ChalosBlocks.CHALOS_COPPER_ORE);
        BlocksItemsRegistry.registerBlock(ChalosBlocks.CHALOS_ALUMINUM_ORE);
        BlocksItemsRegistry.registerBlock(ChalosBlocks.DIREMSIUM_BLOCK);
        BlocksItemsRegistry.registerBlock(ChalosBlocks.ZYPTORIUM_BLOCK);
        BlocksItemsRegistry.registerBlock(ChalosBlocks.CHALOS_DUNGEON_BRICK);

        BlocksItemsRegistry.registerBlock(ChalosBlocks.CHEESE_GRASS_BLOCK);
        BlocksItemsRegistry.registerBlock(ChalosBlocks.CHEESE_DIRT);
        BlocksItemsRegistry.registerBlock(ChalosBlocks.CHEESE_COARSE_DIRT);
        BlocksItemsRegistry.registerBlock(ChalosBlocks.CHEESE_SPORE_STEM);
        BlocksItemsRegistry.registerBlock(ChalosBlocks.CHEESE_SPORE);
        BlocksItemsRegistry.registerBlock(ChalosBlocks.CHEESE_SPORE_PLANKS);
        BlocksItemsRegistry.registerBlock(ChalosBlocks.CHEESE_SLIME_BLOCK);
        BlocksItemsRegistry.registerBlock(ChalosBlocks.CHALOS_ANCIENT_CHEST);
        BlocksItemsRegistry.registerBlock(ChalosBlocks.CHALOS_TREASURE_CHEST);
        BlocksItemsRegistry.registerBlock(ChalosBlocks.CHEESE_MILK_CAKE, ItemBlockDescription::new);
        BlocksItemsRegistry.registerBlock(ChalosBlocks.CHEESE_SPORE_FLOWER);
        BlocksItemsRegistry.registerBlock(ChalosBlocks.CHEESE_GRASS);
        BlocksItemsRegistry.registerBlock(ChalosBlocks.CHEESE_FARMLAND);
        BlocksItemsRegistry.registerBlock(ChalosBlocks.CHEESE_MILK_FLUID_BLOCK);
        BlocksItemsRegistry.registerBlock(ChalosBlocks.GASEOUS_CHEESE_MILK_BLOCK);
        BlocksItemsRegistry.registerBlock(ChalosBlocks.CHEESE_SPORE_BERRY_CROPS, null);
        BlocksItemsRegistry.registerBlock(ChalosBlocks.CHEESE_TALL_GRASS);
        BlocksItemsRegistry.registerBlock(ChalosBlocks.CHEESE_SPORE_CRAFTING_TABLE);
        BlocksItemsRegistry.registerBlock(ChalosBlocks.CHEESE_SPORE_CHEST);
        BlocksItemsRegistry.registerBlock(ChalosBlocks.CHALOS_COBBLESTONE_STAIRS);
        BlocksItemsRegistry.registerBlock(ChalosBlocks.CHALOS_DUNGEON_BRICK_STAIRS);
        BlocksItemsRegistry.registerBlock(ChalosBlocks.CHEESE_SPORE_STAIRS);
        BlocksItemsRegistry.registerBlock(ChalosBlocks.CHEESE_SPORE_FENCE);
        BlocksItemsRegistry.registerBlock(ChalosBlocks.CHEESE_SPORE_FENCE_GATE);
        BlocksItemsRegistry.registerBlock(ChalosBlocks.CHEESE_SPORE_DOOR_BLOCK, null);

        /**************************************************************/
        /**********************HARVEST LEVEL STUFF*********************/
        /**************************************************************/

        BlockUtils.setBlockHarvestLevel(ChalosBlocks.CHEESE_DIRT, EnumHarvestLevel.SHOVEL, 0);
        BlockUtils.setBlockHarvestLevel(ChalosBlocks.CHEESE_COARSE_DIRT, EnumHarvestLevel.SHOVEL, 0);

        BlockUtils.setBlockHarvestLevel(ChalosBlocks.CHALOS_ROCK, EnumHarvestLevel.PICKAXE, 0);
        BlockUtils.setBlockHarvestLevel(ChalosBlocks.CHALOS_COBBLESTONE, EnumHarvestLevel.PICKAXE, 0);
        BlockUtils.setBlockHarvestLevel(ChalosBlocks.DIREMSIUM_ORE, EnumHarvestLevel.PICKAXE, 2);
        BlockUtils.setBlockHarvestLevel(ChalosBlocks.ZYPTORIUM_ORE, EnumHarvestLevel.PICKAXE, 2);
        BlockUtils.setBlockHarvestLevel(ChalosBlocks.CHEESE_MILK_ORE, EnumHarvestLevel.PICKAXE, 1);
        BlockUtils.setBlockHarvestLevel(ChalosBlocks.CHALOS_IRON_ORE, EnumHarvestLevel.PICKAXE, 1);
        BlockUtils.setBlockHarvestLevel(ChalosBlocks.CHALOS_TIN_ORE, EnumHarvestLevel.PICKAXE, 1);
        BlockUtils.setBlockHarvestLevel(ChalosBlocks.CHALOS_COPPER_ORE, EnumHarvestLevel.PICKAXE, 1);
        BlockUtils.setBlockHarvestLevel(ChalosBlocks.CHALOS_ALUMINUM_ORE, EnumHarvestLevel.PICKAXE, 1);
        BlockUtils.setBlockHarvestLevel(ChalosBlocks.DIREMSIUM_BLOCK, EnumHarvestLevel.PICKAXE, 1);
        BlockUtils.setBlockHarvestLevel(ChalosBlocks.ZYPTORIUM_BLOCK, EnumHarvestLevel.PICKAXE, 1);
        BlockUtils.setBlockHarvestLevel(ChalosBlocks.CHALOS_DUNGEON_BRICK, EnumHarvestLevel.PICKAXE, 1);

        BlockUtils.setBlockHarvestLevel(ChalosBlocks.CHALOS_COBBLESTONE_STAIRS, EnumHarvestLevel.PICKAXE, 0);
        BlockUtils.setBlockHarvestLevel(ChalosBlocks.CHALOS_DUNGEON_BRICK_STAIRS, EnumHarvestLevel.PICKAXE, 1);
        BlockUtils.setBlockHarvestLevel(ChalosBlocks.CHEESE_GRASS_BLOCK, EnumHarvestLevel.SHOVEL, 0);
        BlockUtils.setBlockHarvestLevel(ChalosBlocks.CHEESE_FARMLAND, EnumHarvestLevel.SHOVEL, 0);
        BlockUtils.setBlockHarvestLevel(ChalosBlocks.CHALOS_ANCIENT_CHEST, EnumHarvestLevel.AXE, 0);
        BlockUtils.setBlockHarvestLevel(ChalosBlocks.CHEESE_SPORE_STEM, EnumHarvestLevel.AXE, 0);
        BlockUtils.setBlockHarvestLevel(ChalosBlocks.CHEESE_SPORE_PLANKS, EnumHarvestLevel.AXE, 0);
        BlockUtils.setBlockHarvestLevel(ChalosBlocks.CHEESE_SPORE_CRAFTING_TABLE, EnumHarvestLevel.AXE, 0);
        BlockUtils.setBlockHarvestLevel(ChalosBlocks.CHEESE_SPORE_CHEST, EnumHarvestLevel.AXE, 0);
        BlockUtils.setBlockHarvestLevel(ChalosBlocks.CHEESE_SPORE_STAIRS, EnumHarvestLevel.AXE, 0);
        BlockUtils.setBlockHarvestLevel(ChalosBlocks.CHEESE_SPORE_FENCE, EnumHarvestLevel.AXE, 0);
        BlockUtils.setBlockHarvestLevel(ChalosBlocks.CHEESE_SPORE_FENCE_GATE, EnumHarvestLevel.AXE, 0);
        BlockUtils.setBlockHarvestLevel(ChalosBlocks.CHEESE_SPORE_DOOR_BLOCK, EnumHarvestLevel.AXE, 0);

        /**************************************************************/
        /************************FIRE BURN STUFF***********************/
        /**************************************************************/

        BlockUtils.setFireBurn(ChalosBlocks.CHEESE_SPORE_FLOWER, 60, 100);
        BlockUtils.setFireBurn(ChalosBlocks.CHEESE_SPORE, 60, 100);
        BlockUtils.setFireBurn(ChalosBlocks.CHEESE_GRASS, 60, 100);
        BlockUtils.setFireBurn(ChalosBlocks.CHEESE_TALL_GRASS, 60, 100);
        BlockUtils.setFireBurn(ChalosBlocks.CHEESE_SPORE_STEM, 5, 20);
        BlockUtils.setFireBurn(ChalosBlocks.CHEESE_SPORE_PLANKS, 5, 20);
        BlockUtils.setFireBurn(ChalosBlocks.CHEESE_SPORE_STAIRS, 5, 20);
        BlockUtils.setFireBurn(ChalosBlocks.CHEESE_SPORE_FENCE, 5, 20);
        BlockUtils.setFireBurn(ChalosBlocks.CHEESE_SPORE_FENCE_GATE, 5, 20);

        ChalosBlocks.CHALOS_ROCK.setDrop(ChalosBlocks.CHALOS_COBBLESTONE);
    }
}