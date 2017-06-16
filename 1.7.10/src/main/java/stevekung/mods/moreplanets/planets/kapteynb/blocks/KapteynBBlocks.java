/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 *
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.planets.kapteynb.blocks;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.oredict.OreDictionary;
import stevekung.mods.moreplanets.core.blocks.BlockStairsMP;
import stevekung.mods.moreplanets.core.blocks.BlockStairsMP.StairsCategory;
import stevekung.mods.moreplanets.core.util.RegisterHelper;
import stevekung.mods.moreplanets.planets.kapteynb.fluids.BlockFluidFrozenWater;
import stevekung.mods.moreplanets.planets.kapteynb.itemblocks.ItemBlockIcyPoisonCrystal;
import stevekung.mods.moreplanets.planets.kapteynb.itemblocks.ItemBlockKapteynB;
import stevekung.mods.moreplanets.planets.kapteynb.itemblocks.ItemBlockKapteynBIce;
import stevekung.mods.moreplanets.planets.kapteynb.itemblocks.ItemBlockUraniumWaste;

public class KapteynBBlocks
{
    public static Block kapteyn_b_block;
    public static Block kapteyn_b_ice;
    public static Block kapteyn_b_treasure_chest;
    public static Block kapteyn_b_cobblestone_stairs;
    public static Block kapteyn_b_dungeon_brick_stairs;
    public static Block frozen_water;
    public static Block rocky_soild_water;
    public static Block uranium_waste;
    public static Block kapteyn_b_ancient_chest;
    public static Block uranium_bomb;
    public static Block chest_temp;
    public static Block fallen_ice_crystal_meteor;
    public static Block frozen_water_geyser;
    public static Block icy_poison_crystal;

    // Fluid
    public static Fluid frozen_water_fluid;

    public static void init()
    {
        KapteynBBlocks.initBlocks();
        KapteynBBlocks.setHarvestLevels();
        KapteynBBlocks.registerBlocks();
    }

    private static void initBlocks()
    {
        KapteynBBlocks.kapteyn_b_block = new BlockBasicKapteynB("kapteyn-b_block");
        KapteynBBlocks.kapteyn_b_ice = new BlockKapteynBIce("kapteyn-b_ice");
        KapteynBBlocks.kapteyn_b_treasure_chest = new BlockT8KapteynBTreasureChest("kapteyn-b_treasure_chest");
        KapteynBBlocks.kapteyn_b_cobblestone_stairs = new BlockStairsMP("kapteyn-b_cobblestone_stairs", 3.25F, StairsCategory.KAPTEYNB_COBBLESTONE, Blocks.stone);
        KapteynBBlocks.kapteyn_b_dungeon_brick_stairs = new BlockStairsMP("kapteyn-b_dungeon_brick_stairs", 4.0F, StairsCategory.KAPTEYNB_BRICK, Blocks.stone);
        KapteynBBlocks.rocky_soild_water = new BlockRockySolidWater("rocky_soild_water");
        KapteynBBlocks.kapteyn_b_ancient_chest = new BlockKapteynBAncientChest("kapteyn-b_ancient_chest");
        KapteynBBlocks.uranium_waste = new BlockUraniumWaste("uranium_waste");
        KapteynBBlocks.uranium_bomb = new BlockUraniumBomb("uranium_bomb");
        KapteynBBlocks.fallen_ice_crystal_meteor = new BlockFallenIceCrystalMeteor("fallen_ice_crystal_meteor");
        KapteynBBlocks.chest_temp = new BlockKapteynBAncientChestTemp("kapteyn-b_ancient_chest_temp");
        KapteynBBlocks.frozen_water_geyser = new BlockFrozenWaterGeyser("frozen_water_geyser");
        KapteynBBlocks.icy_poison_crystal = new BlockIcyPoisonCrystal("icy_poison_crystal");

        KapteynBBlocks.frozen_water_fluid = new Fluid("frozen_water_fluid").setBlock(KapteynBBlocks.frozen_water);
        FluidRegistry.registerFluid(KapteynBBlocks.frozen_water_fluid);
        KapteynBBlocks.frozen_water = new BlockFluidFrozenWater("frozen_water_fluid");
    }

    private static void setHarvestLevels()
    {
        KapteynBBlocks.kapteyn_b_block.setHarvestLevel("shovel", 0, 0);
        KapteynBBlocks.kapteyn_b_block.setHarvestLevel("shovel", 0, 1);
        KapteynBBlocks.kapteyn_b_block.setHarvestLevel("pickaxe", 1, 2);
        KapteynBBlocks.kapteyn_b_block.setHarvestLevel("pickaxe", 1, 3);
        KapteynBBlocks.kapteyn_b_block.setHarvestLevel("pickaxe", 1, 4);
        KapteynBBlocks.kapteyn_b_block.setHarvestLevel("pickaxe", 1, 5);
        KapteynBBlocks.kapteyn_b_block.setHarvestLevel("pickaxe", 1, 6);
        KapteynBBlocks.kapteyn_b_block.setHarvestLevel("pickaxe", 1, 7);
        KapteynBBlocks.kapteyn_b_block.setHarvestLevel("pickaxe", 1, 8);
        KapteynBBlocks.kapteyn_b_block.setHarvestLevel("pickaxe", 1, 9);
        KapteynBBlocks.kapteyn_b_block.setHarvestLevel("pickaxe", 1, 10);
        KapteynBBlocks.kapteyn_b_block.setHarvestLevel("pickaxe", 1, 11);
        KapteynBBlocks.kapteyn_b_block.setHarvestLevel("pickaxe", 1, 12);
        KapteynBBlocks.kapteyn_b_block.setHarvestLevel("pickaxe", 1, 13);
        KapteynBBlocks.kapteyn_b_block.setHarvestLevel("pickaxe", 1, 14);
        KapteynBBlocks.frozen_water_geyser.setHarvestLevel("pickaxe", 1);
        KapteynBBlocks.kapteyn_b_cobblestone_stairs.setHarvestLevel("pickaxe", 1);
        KapteynBBlocks.kapteyn_b_dungeon_brick_stairs.setHarvestLevel("pickaxe", 1);
        KapteynBBlocks.rocky_soild_water.setHarvestLevel("shovel", 0);
        KapteynBBlocks.kapteyn_b_ancient_chest.setHarvestLevel("axe", 0);
    }

    private static void registerBlocks()
    {
        RegisterHelper.registerBlock(KapteynBBlocks.kapteyn_b_block, ItemBlockKapteynB.class);
        RegisterHelper.registerBlock(KapteynBBlocks.rocky_soild_water);
        RegisterHelper.registerBlock(KapteynBBlocks.frozen_water_geyser);
        RegisterHelper.registerBlock(KapteynBBlocks.kapteyn_b_ice, ItemBlockKapteynBIce.class);
        RegisterHelper.registerBlock(KapteynBBlocks.uranium_bomb);
        RegisterHelper.registerBlock(KapteynBBlocks.kapteyn_b_treasure_chest);
        RegisterHelper.registerBlock(KapteynBBlocks.kapteyn_b_ancient_chest);
        RegisterHelper.registerBlock(KapteynBBlocks.kapteyn_b_cobblestone_stairs);
        RegisterHelper.registerBlock(KapteynBBlocks.kapteyn_b_dungeon_brick_stairs);
        RegisterHelper.registerBlock(KapteynBBlocks.fallen_ice_crystal_meteor);
        RegisterHelper.registerBlock(KapteynBBlocks.uranium_waste, ItemBlockUraniumWaste.class);
        RegisterHelper.registerBlock(KapteynBBlocks.icy_poison_crystal, ItemBlockIcyPoisonCrystal.class);
        RegisterHelper.registerBlock(KapteynBBlocks.chest_temp, ItemBlockUraniumWaste.class);
        RegisterHelper.registerBlock(KapteynBBlocks.frozen_water);

        OreDictionary.registerOre("oreNamerium", new ItemStack(KapteynBBlocks.kapteyn_b_block, 1, 4));
        OreDictionary.registerOre("oreFrozenIron", new ItemStack(KapteynBBlocks.kapteyn_b_block, 1, 5));
        OreDictionary.registerOre("oreUranium", new ItemStack(KapteynBBlocks.kapteyn_b_block, 1, 6));
        OreDictionary.registerOre("oreTin", new ItemStack(KapteynBBlocks.kapteyn_b_block, 1, 7));
        OreDictionary.registerOre("oreCopper", new ItemStack(KapteynBBlocks.kapteyn_b_block, 1, 8));
        OreDictionary.registerOre("oreRedstone", new ItemStack(KapteynBBlocks.kapteyn_b_block, 1, 9));

        OreDictionary.registerOre("blockNamerium", new ItemStack(KapteynBBlocks.kapteyn_b_block, 1, 11));
        OreDictionary.registerOre("blockFrozenIron", new ItemStack(KapteynBBlocks.kapteyn_b_block, 1, 12));
        OreDictionary.registerOre("blockUranium", new ItemStack(KapteynBBlocks.kapteyn_b_block, 1, 13));
    }
}