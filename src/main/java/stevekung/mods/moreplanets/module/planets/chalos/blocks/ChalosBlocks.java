package stevekung.mods.moreplanets.module.planets.chalos.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraftforge.fluids.Fluid;
import stevekung.mods.moreplanets.module.planets.chalos.blocks.fluid.FluidCheeseOfMilkGas;
import stevekung.mods.moreplanets.util.blocks.*;
import stevekung.mods.moreplanets.util.blocks.BlockStairsMP.EnumStairsType;
import stevekung.mods.moreplanets.util.blocks.fluid.FluidMP;
import stevekung.mods.moreplanets.util.helper.CommonRegisterHelper;
import stevekung.mods.moreplanets.util.itemblocks.ItemBlockDescription;
import stevekung.mods.moreplanets.util.itemblocks.ItemBlockFlower;
import stevekung.mods.moreplanets.util.itemblocks.ItemBlockMultiVariant;
import stevekung.mods.stevekunglib.utils.EnumHarvestLevel;

public class ChalosBlocks
{
    public static Block CHEESE_GRASS;
    public static Block CHEESE_DIRT;
    public static Block CHEESE_FARMLAND;
    public static Block CHALOS_BLOCK;
    public static Block CHEESE_SLIME_BLOCK;
    public static Block CHEESE_OF_MILK_CAKE;
    public static BlockBushMP CHEESE_SPORE_FLOWER;
    public static Block CHALOS_ANCIENT_CHEST;
    public static Block CHALOS_TREASURE_CHEST;
    public static Block CHEESE_OF_MILK_FLUID_BLOCK;
    public static Block CHEESE_OF_MILK_GAS_BLOCK;
    public static Block CHEESE_SPORE;
    public static Block CHEESE_SPORE_STEM;
    public static Block CHEESE_SPORE_PLANKS;
    public static BlockBushMP CHEESE_TALL_GRASS;
    public static Block CHEESE_SPORE_BERRY_CROPS;
    public static BlockChalosDoublePlant CHALOS_DOUBLE_PLANT;
    public static Block CHALOS_CRAFTING_TABLE;
    public static Block CHEESE_SPORE_CHEST;
    public static Block CHALOS_COBBLESTONE_STAIRS;
    public static Block CHALOS_DUNGEON_BRICK_STAIRS;
    public static Block CHEESE_SPORE_STAIRS;
    public static Block CHEESE_SPORE_FENCE;
    public static Block CHEESE_SPORE_FENCE_GATE;
    public static BlockDoorMP CHEESE_SPORE_DOOR_BLOCK;

    public static Fluid CHEESE_OF_MILK_FLUID;
    public static Fluid CHEESE_OF_MILK_GAS;

    public static void init()
    {
        /**************************************************************/
        /*************************INITIAL STUFF************************/
        /**************************************************************/

        ChalosBlocks.CHEESE_GRASS = new BlockCheeseGrass("cheese_grass");
        ChalosBlocks.CHEESE_DIRT = new BlockCheeseDirt("cheese_dirt");
        ChalosBlocks.CHEESE_FARMLAND = new BlockCheeseFarmland("cheese_farmland");
        ChalosBlocks.CHALOS_BLOCK = new BlockChalos("chalos_block");
        ChalosBlocks.CHEESE_SLIME_BLOCK = new BlockCheeseSlime("cheese_slime_block");
        ChalosBlocks.CHEESE_OF_MILK_CAKE = new BlockCheeseOfMilkCake("cheese_of_milk_cake");
        ChalosBlocks.CHEESE_SPORE_FLOWER = new BlockCheeseSporeFlower("cheese_spore_flower");
        ChalosBlocks.CHALOS_ANCIENT_CHEST = new BlockChalosAncientChest("chalos_ancient_chest");
        ChalosBlocks.CHALOS_TREASURE_CHEST = new BlockChalosTreasureChest("chalos_treasure_chest");
        ChalosBlocks.CHEESE_SPORE = new BlockCheeseSpore("cheese_spore_block");
        ChalosBlocks.CHEESE_SPORE_STEM = new BlockCheeseSporeStem("cheese_spore_stem");
        ChalosBlocks.CHEESE_SPORE_PLANKS = new BlockBaseMP("cheese_spore_planks", Material.WOOD).setSoundType(SoundType.WOOD).setHardness(2.0F).setResistance(5.0F);
        ChalosBlocks.CHEESE_TALL_GRASS = new BlockCheeseTallGrass("cheese_tall_grass");
        ChalosBlocks.CHEESE_SPORE_BERRY_CROPS = new BlockCheeseSporeBerryCrops("cheese_spore_berry_crops");
        ChalosBlocks.CHALOS_DOUBLE_PLANT = new BlockChalosDoublePlant("chalos_double_plant");
        ChalosBlocks.CHALOS_CRAFTING_TABLE = new BlockChalosCraftingTable("chalos_crafting_table");
        ChalosBlocks.CHEESE_SPORE_CHEST = new BlockCheeseSporeChest("cheese_spore_chest");
        ChalosBlocks.CHALOS_COBBLESTONE_STAIRS = new BlockStairsMP("chalos_cobblestone_stairs", EnumStairsType.COBBLESTONE);
        ChalosBlocks.CHALOS_DUNGEON_BRICK_STAIRS = new BlockStairsMP("chalos_dungeon_brick_stairs", EnumStairsType.DUNGEON_BRICK).setSortCategory(EnumSortCategoryBlock.STAIRS_DUNGEON_BRICK);
        ChalosBlocks.CHEESE_SPORE_STAIRS = new BlockStairsMP("cheese_spore_stairs", EnumStairsType.WOODEN).setSortCategory(EnumSortCategoryBlock.STAIRS_WOODEN);
        ChalosBlocks.CHEESE_SPORE_FENCE = new BlockFenceMP("cheese_spore_fence");
        ChalosBlocks.CHEESE_SPORE_FENCE_GATE = new BlockFenceGateMP("cheese_spore_fence_gate");
        ChalosBlocks.CHEESE_SPORE_DOOR_BLOCK = new BlockDoorMP("cheese_spore_door_block");

        ChalosBlocks.CHEESE_OF_MILK_FLUID = new FluidMP("cheese_of_milk_fluid").setBlock(ChalosBlocks.CHEESE_OF_MILK_FLUID_BLOCK).setViscosity(1000);
        ChalosBlocks.CHEESE_OF_MILK_GAS = new FluidCheeseOfMilkGas("cheese_of_milk_gas", "moreplanets:blocks/cheese_of_milk_gas", "moreplanets:blocks/cheese_of_milk_gas").setBlock(ChalosBlocks.CHEESE_OF_MILK_GAS_BLOCK);
        CommonRegisterHelper.registerFluid(ChalosBlocks.CHEESE_OF_MILK_FLUID);
        CommonRegisterHelper.registerFluid(ChalosBlocks.CHEESE_OF_MILK_GAS);
        ChalosBlocks.CHEESE_OF_MILK_GAS_BLOCK = new BlockGasCheeseOfMilk("cheese_of_milk_gas");
        ChalosBlocks.CHEESE_OF_MILK_FLUID_BLOCK = new BlockFluidCheeseOfMilk("cheese_of_milk_fluid");

        /**************************************************************/
        /************************REGISTER STUFF************************/
        /**************************************************************/

        CommonRegisterHelper.registerBlock(ChalosBlocks.CHEESE_GRASS);
        CommonRegisterHelper.registerBlock(ChalosBlocks.CHEESE_DIRT, ItemBlockMultiVariant::new);
        CommonRegisterHelper.registerBlock(ChalosBlocks.CHALOS_BLOCK, ItemBlockMultiVariant::new);
        CommonRegisterHelper.registerBlock(ChalosBlocks.CHEESE_SPORE_STEM);
        CommonRegisterHelper.registerBlock(ChalosBlocks.CHEESE_SPORE);
        CommonRegisterHelper.registerBlock(ChalosBlocks.CHEESE_SPORE_PLANKS);
        CommonRegisterHelper.registerBlock(ChalosBlocks.CHEESE_SLIME_BLOCK);
        CommonRegisterHelper.registerBlock(ChalosBlocks.CHALOS_ANCIENT_CHEST);
        CommonRegisterHelper.registerBlock(ChalosBlocks.CHALOS_TREASURE_CHEST);
        CommonRegisterHelper.registerBlock(ChalosBlocks.CHEESE_OF_MILK_CAKE, ItemBlockDescription::new);
        CommonRegisterHelper.registerBlock(ChalosBlocks.CHEESE_SPORE_FLOWER, ItemBlockFlower::new);
        CommonRegisterHelper.registerBlock(ChalosBlocks.CHEESE_TALL_GRASS, ItemBlockFlower::new);
        CommonRegisterHelper.registerBlock(ChalosBlocks.CHEESE_FARMLAND);
        CommonRegisterHelper.registerBlock(ChalosBlocks.CHEESE_OF_MILK_FLUID_BLOCK);
        CommonRegisterHelper.registerBlock(ChalosBlocks.CHEESE_OF_MILK_GAS_BLOCK);
        CommonRegisterHelper.registerBlock(ChalosBlocks.CHEESE_SPORE_BERRY_CROPS, null);
        CommonRegisterHelper.registerBlock(ChalosBlocks.CHALOS_DOUBLE_PLANT, ItemBlockFlower::new);
        CommonRegisterHelper.registerBlock(ChalosBlocks.CHALOS_CRAFTING_TABLE);
        CommonRegisterHelper.registerBlock(ChalosBlocks.CHEESE_SPORE_CHEST);
        CommonRegisterHelper.registerBlock(ChalosBlocks.CHALOS_COBBLESTONE_STAIRS);
        CommonRegisterHelper.registerBlock(ChalosBlocks.CHALOS_DUNGEON_BRICK_STAIRS);
        CommonRegisterHelper.registerBlock(ChalosBlocks.CHEESE_SPORE_STAIRS);
        CommonRegisterHelper.registerBlock(ChalosBlocks.CHEESE_SPORE_FENCE);
        CommonRegisterHelper.registerBlock(ChalosBlocks.CHEESE_SPORE_FENCE_GATE);
        CommonRegisterHelper.registerBlock(ChalosBlocks.CHEESE_SPORE_DOOR_BLOCK, null);

        /**************************************************************/
        /**********************HARVEST LEVEL STUFF*********************/
        /**************************************************************/

        for (int i = 0; i < BlockChalos.BlockType.valuesCached().length; i++)
        {
            if (i == 2 || i == 3)
            {
                CommonRegisterHelper.setBlockHarvestLevel(ChalosBlocks.CHALOS_BLOCK, EnumHarvestLevel.PICKAXE, 2, i);
            }
            else if (i >= 5 && i <= 11)
            {
                CommonRegisterHelper.setBlockHarvestLevel(ChalosBlocks.CHALOS_BLOCK, EnumHarvestLevel.PICKAXE, 1, i);
            }
            else
            {
                CommonRegisterHelper.setBlockHarvestLevel(ChalosBlocks.CHALOS_BLOCK, EnumHarvestLevel.PICKAXE, 0, i);
            }
        }

        CommonRegisterHelper.setBlockHarvestLevel(ChalosBlocks.CHALOS_COBBLESTONE_STAIRS, EnumHarvestLevel.PICKAXE, 0);
        CommonRegisterHelper.setBlockHarvestLevel(ChalosBlocks.CHALOS_DUNGEON_BRICK_STAIRS, EnumHarvestLevel.PICKAXE, 1);
        CommonRegisterHelper.setBlockHarvestLevel(ChalosBlocks.CHEESE_GRASS, EnumHarvestLevel.SHOVEL, 0);
        CommonRegisterHelper.setBlockHarvestLevel(ChalosBlocks.CHEESE_DIRT, EnumHarvestLevel.SHOVEL, 0);
        CommonRegisterHelper.setBlockHarvestLevel(ChalosBlocks.CHEESE_FARMLAND, EnumHarvestLevel.SHOVEL, 0);
        CommonRegisterHelper.setBlockHarvestLevel(ChalosBlocks.CHALOS_ANCIENT_CHEST, EnumHarvestLevel.AXE, 0);
        CommonRegisterHelper.setBlockHarvestLevel(ChalosBlocks.CHEESE_SPORE_STEM, EnumHarvestLevel.AXE, 0);
        CommonRegisterHelper.setBlockHarvestLevel(ChalosBlocks.CHEESE_SPORE_PLANKS, EnumHarvestLevel.AXE, 0);
        CommonRegisterHelper.setBlockHarvestLevel(ChalosBlocks.CHALOS_CRAFTING_TABLE, EnumHarvestLevel.AXE, 0);
        CommonRegisterHelper.setBlockHarvestLevel(ChalosBlocks.CHEESE_SPORE_CHEST, EnumHarvestLevel.AXE, 0);
        CommonRegisterHelper.setBlockHarvestLevel(ChalosBlocks.CHEESE_SPORE_STAIRS, EnumHarvestLevel.AXE, 0);
        CommonRegisterHelper.setBlockHarvestLevel(ChalosBlocks.CHEESE_SPORE_FENCE, EnumHarvestLevel.AXE, 0);
        CommonRegisterHelper.setBlockHarvestLevel(ChalosBlocks.CHEESE_SPORE_FENCE_GATE, EnumHarvestLevel.AXE, 0);
        CommonRegisterHelper.setBlockHarvestLevel(ChalosBlocks.CHEESE_SPORE_DOOR_BLOCK, EnumHarvestLevel.AXE, 0);

        /**************************************************************/
        /************************FIRE BURN STUFF***********************/
        /**************************************************************/

        CommonRegisterHelper.setFireBurn(ChalosBlocks.CHEESE_SPORE_FLOWER, 60, 100);
        CommonRegisterHelper.setFireBurn(ChalosBlocks.CHEESE_SPORE, 60, 100);
        CommonRegisterHelper.setFireBurn(ChalosBlocks.CHEESE_TALL_GRASS, 60, 100);
        CommonRegisterHelper.setFireBurn(ChalosBlocks.CHALOS_DOUBLE_PLANT, 60, 100);
        CommonRegisterHelper.setFireBurn(ChalosBlocks.CHEESE_SPORE_STEM, 5, 20);
        CommonRegisterHelper.setFireBurn(ChalosBlocks.CHEESE_SPORE_PLANKS, 5, 20);
        CommonRegisterHelper.setFireBurn(ChalosBlocks.CHEESE_SPORE_STAIRS, 5, 20);
        CommonRegisterHelper.setFireBurn(ChalosBlocks.CHEESE_SPORE_FENCE, 5, 20);
        CommonRegisterHelper.setFireBurn(ChalosBlocks.CHEESE_SPORE_FENCE_GATE, 5, 20);
    }
}