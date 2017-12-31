/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 *
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.moons.europa.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.oredict.OreDictionary;
import stevekung.mods.moreplanets.core.blocks.BlockDoorMP;
import stevekung.mods.moreplanets.core.blocks.BlockDoorMP.DoorType;
import stevekung.mods.moreplanets.core.blocks.BlockFenceGateMP;
import stevekung.mods.moreplanets.core.blocks.BlockStairsMP;
import stevekung.mods.moreplanets.core.blocks.BlockStairsMP.StairsCategory;
import stevekung.mods.moreplanets.core.init.MPBlocks;
import stevekung.mods.moreplanets.core.util.RegisterHelper;
import stevekung.mods.moreplanets.moons.europa.blocks.BlockEuropaLeaves.EuropaLeafCategory;
import stevekung.mods.moreplanets.moons.europa.blocks.BlockEuropaLog.EuropaLogCategory;
import stevekung.mods.moreplanets.moons.europa.blocks.BlockEuropaSandstoneSlab.EuropaSlabCategory;
import stevekung.mods.moreplanets.moons.europa.blocks.fluids.BlockFluidEuropaWater;
import stevekung.mods.moreplanets.moons.europa.itemblocks.*;

public class EuropaBlocks
{
    public static Block europa_ice;
    public static Block packed_europa_ice;
    public static Block europa_snow_block;
    public static Block europa_ice_slush;
    public static Block europa_water_bomb;
    public static Block europa_salt;
    public static Block europa_geyser;
    public static Block europa_snow_layer;
    public static Block europa_sapling;
    public static Block europa_leaves;
    public static Block europa_log;
    public static Block europa_planks;
    public static Block europa_water;
    public static Block europa_sand;
    public static Block europa_sandstone;
    public static Block half_europa_sandstone_slab;
    public static Block double_europa_sandstone_slab;
    public static Block europa_fence;
    public static Block europa_fence_gate;
    public static Block europa_sandstone_stairs;
    public static Block europa_wood_stairs;
    public static Block europa_door;

    // Fluid
    public static Fluid europa_water_fluid;

    public static void init()
    {
        EuropaBlocks.initBlocks();
        EuropaBlocks.setHarvestLevels();
        EuropaBlocks.registerBlocks();
    }

    private static void initBlocks()
    {
        EuropaBlocks.europa_ice = new BlockEuropaIce("europa_ice");
        EuropaBlocks.packed_europa_ice = new BlockPackedEuropaIce("packed_europa_ice");
        EuropaBlocks.europa_snow_block = new BlockEuropaSnowBlock("europa_snow_block");
        EuropaBlocks.europa_ice_slush = new BlockEuropaIceSlush("europa_ice_slush");
        EuropaBlocks.europa_water_bomb = new BlockEuropaWaterBomb("europa_water_bomb");
        EuropaBlocks.europa_salt = new BlockEuropaSalt("europa_salt");
        EuropaBlocks.europa_geyser = new BlockEuropaGeyser("europa_geyser");
        EuropaBlocks.europa_snow_layer = new BlockEuropaSnowLayer("europa_snow_layer");
        EuropaBlocks.europa_sapling = new BlockEuropaSapling("europa_sapling");
        EuropaBlocks.europa_leaves = new BlockEuropaLeaves("europa_leaves", EuropaLeafCategory.CAT1);
        EuropaBlocks.europa_log = new BlockEuropaLog("europa_log", EuropaLogCategory.CAT1);
        EuropaBlocks.europa_planks = new BlockEuropaPlanks("europa_planks");
        EuropaBlocks.europa_sand = new BlockEuropaSand("europa_sand");
        EuropaBlocks.europa_sandstone = new BlockEuropaSandstone("europa_sandstone");
        EuropaBlocks.half_europa_sandstone_slab = new BlockEuropaSandstoneSlab("half_europa_sandstone_slab", false, Material.rock, EuropaSlabCategory.WOOD1);
        EuropaBlocks.double_europa_sandstone_slab = new BlockEuropaSandstoneSlab("double_europa_sandstone_slab", true, Material.rock, EuropaSlabCategory.WOOD1);
        EuropaBlocks.europa_fence = new BlockEuropaFence("europa_fence");
        EuropaBlocks.europa_fence_gate = new BlockFenceGateMP("europa_fence_gate", "europa:europa_planks");
        EuropaBlocks.europa_wood_stairs = new BlockStairsMP("europa_wood_stairs", 2.0F, StairsCategory.EUROPA_WOOD, Blocks.log);
        EuropaBlocks.europa_sandstone_stairs = new BlockStairsMP("europa_sandstone_stairs", 0.8F, StairsCategory.europa_sandstone, Blocks.stone);
        EuropaBlocks.europa_door = new BlockDoorMP("europa_door_block", "europa:europa", DoorType.EUROPA);

        EuropaBlocks.europa_water_fluid = new Fluid("europa_water_fluid").setBlock(EuropaBlocks.europa_water);
        FluidRegistry.registerFluid(EuropaBlocks.europa_water_fluid);
        EuropaBlocks.europa_water = new BlockFluidEuropaWater("europa_water_fluid");
    }

    public static void setHarvestLevels()
    {
        EuropaBlocks.europa_snow_block.setHarvestLevel("shovel", 0);
        EuropaBlocks.europa_ice_slush.setHarvestLevel("shovel", 0);
        EuropaBlocks.europa_salt.setHarvestLevel("pickaxe", 0);
        EuropaBlocks.europa_geyser.setHarvestLevel("pickaxe", 0);
        EuropaBlocks.europa_snow_layer.setHarvestLevel("shovel", 0);
        EuropaBlocks.europa_log.setHarvestLevel("axe", 0);
        EuropaBlocks.europa_planks.setHarvestLevel("axe", 0);
        EuropaBlocks.europa_sand.setHarvestLevel("shovel", 0);
        EuropaBlocks.europa_sandstone.setHarvestLevel("pickaxe", 0);
        EuropaBlocks.europa_sandstone_stairs.setHarvestLevel("pickaxe", 0);
        EuropaBlocks.half_europa_sandstone_slab.setHarvestLevel("pickaxe", 0);
        EuropaBlocks.double_europa_sandstone_slab.setHarvestLevel("pickaxe", 0);
        EuropaBlocks.europa_fence.setHarvestLevel("axe", 0);
        EuropaBlocks.europa_fence_gate.setHarvestLevel("axe", 0);
        EuropaBlocks.europa_wood_stairs.setHarvestLevel("axe", 0);
    }

    private static void registerBlocks()
    {
        RegisterHelper.registerBlock(EuropaBlocks.europa_ice, ItemBlockEuropaIce.class);
        RegisterHelper.registerBlock(EuropaBlocks.packed_europa_ice);
        RegisterHelper.registerBlock(EuropaBlocks.europa_snow_block);
        RegisterHelper.registerBlock(EuropaBlocks.europa_planks);
        RegisterHelper.registerBlock(EuropaBlocks.europa_log, ItemBlockEuropaLog.class);
        RegisterHelper.registerBlock(EuropaBlocks.europa_leaves, ItemBlockEuropaLeaves.class);
        RegisterHelper.registerBlock(EuropaBlocks.europa_ice_slush);
        RegisterHelper.registerBlock(EuropaBlocks.europa_salt);
        RegisterHelper.registerBlock(EuropaBlocks.europa_water_bomb);
        RegisterHelper.registerBlock(EuropaBlocks.europa_geyser, ItemBlockEuropaGeyser.class);
        RegisterHelper.registerBlock(EuropaBlocks.europa_sand);
        RegisterHelper.registerBlock(EuropaBlocks.europa_sandstone, ItemBlockEuropaSandstone.class);
        RegisterHelper.registerBlock(EuropaBlocks.half_europa_sandstone_slab, ItemBlockEuropaSandstoneSlab.class, EuropaBlocks.half_europa_sandstone_slab, EuropaBlocks.double_europa_sandstone_slab);
        RegisterHelper.registerBlock(EuropaBlocks.double_europa_sandstone_slab, ItemBlockEuropaSandstoneSlab.class, EuropaBlocks.half_europa_sandstone_slab, EuropaBlocks.double_europa_sandstone_slab);
        RegisterHelper.registerBlock(EuropaBlocks.europa_snow_layer, ItemBlockEuropaSnowLayer.class);
        RegisterHelper.registerBlock(EuropaBlocks.europa_fence);
        RegisterHelper.registerBlock(EuropaBlocks.europa_fence_gate);
        RegisterHelper.registerBlock(EuropaBlocks.europa_sandstone_stairs);
        RegisterHelper.registerBlock(EuropaBlocks.europa_wood_stairs);
        RegisterHelper.registerBlock(EuropaBlocks.europa_sapling, ItemBlockEuropaSapling.class);
        RegisterHelper.registerBlock(EuropaBlocks.europa_water);
        RegisterHelper.registerBlock(EuropaBlocks.europa_door);

        RegisterHelper.setFireBurn(EuropaBlocks.europa_sapling, 60, 100);
        RegisterHelper.setFireBurn(EuropaBlocks.europa_log, 5, 5);
        RegisterHelper.setFireBurn(EuropaBlocks.europa_leaves, 30, 60);
        RegisterHelper.setFireBurn(EuropaBlocks.europa_fence, 5, 20);
        RegisterHelper.setFireBurn(EuropaBlocks.europa_fence_gate, 5, 20);
        RegisterHelper.setFireBurn(EuropaBlocks.europa_wood_stairs, 5, 20);

        OreDictionary.registerOre("logWood", new ItemStack(EuropaBlocks.europa_log));
        OreDictionary.registerOre("plankWood", new ItemStack(EuropaBlocks.europa_planks));
        OreDictionary.registerOre("slabWood", new ItemStack(MPBlocks.wooden_slab_half, 1, 5));
        OreDictionary.registerOre("stairWood", new ItemStack(EuropaBlocks.europa_wood_stairs));
    }
}