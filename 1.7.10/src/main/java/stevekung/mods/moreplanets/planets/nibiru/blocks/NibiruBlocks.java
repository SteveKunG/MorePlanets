/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 *
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.planets.nibiru.blocks;

import micdoodle8.mods.galacticraft.core.items.ItemBlockDesc;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;
import stevekung.mods.moreplanets.core.blocks.BlockDoorMP;
import stevekung.mods.moreplanets.core.blocks.BlockDoorMP.DoorType;
import stevekung.mods.moreplanets.core.blocks.BlockFenceGateMP;
import stevekung.mods.moreplanets.core.blocks.BlockStairsMP;
import stevekung.mods.moreplanets.core.blocks.BlockStairsMP.StairsCategory;
import stevekung.mods.moreplanets.core.itemblocks.ItemBlockDirtMP;
import stevekung.mods.moreplanets.core.util.RegisterHelper;
import stevekung.mods.moreplanets.planets.nibiru.blocks.BlockNibiruLog.NibiruLogCategory;
import stevekung.mods.moreplanets.planets.nibiru.itemblocks.*;

public class NibiruBlocks
{
    public static Block nibiru_block;
    public static Block nibiru_cobblestone_stairs;
    public static Block ancient_dark_wood_stairs;
    public static Block nibiru_treasure_chest;
    public static Block nibiru_ancient_chest;
    public static Block nibiru_sapling;
    public static Block helium_block;
    public static Block infected_worm_egg_rock;
    public static Block infected_zombie_egg;
    public static Block ancient_dark_leaves;
    public static Block nibiru_log;
    public static Block nibiru_wooden_planks;
    public static Block power_crystal_generator;
    public static Block ichorius_torch;
    public static Block orange_leaves;
    public static Block orange_wood_stairs;
    public static Block infected_grass;
    public static Block infected_dirt;
    public static Block oil_rock;
    public static Block infected_vine;
    public static Block nibiru_dungeon_brick_stairs;
    public static Block infected_farmland;
    public static Block nibiru_fence;
    public static Block ancient_dark_fence_gate;
    public static Block orange_fence_gate;
    public static Block infected_cavernous_vine;
    public static Block ancient_dark_door;
    public static Block orange_door;

    public static void init()
    {
        NibiruBlocks.initBlocks();
        NibiruBlocks.setHarvestLevels();
        NibiruBlocks.registerBlocks();
        NibiruBlocks.setFireBurn();
    }

    private static void initBlocks()
    {
        NibiruBlocks.nibiru_block = new BlockBasicNibiru("nibiru_block");
        NibiruBlocks.nibiru_treasure_chest = new BlockT6NibiruTreasureChest("nibiru_treasure_chest");
        NibiruBlocks.nibiru_ancient_chest = new BlockNibiruAncientChest("nibiru_ancient_chest");
        NibiruBlocks.nibiru_sapling = new BlockNibiruSapling("nibiru_sapling");
        NibiruBlocks.helium_block = new BlockHelium("helium_block");
        NibiruBlocks.infected_worm_egg_rock = new BlockInfectedWormEggRock("infected_worm_egg");
        NibiruBlocks.infected_zombie_egg = new BlockInfectedZombieEgg("infected_zombie_egg");
        NibiruBlocks.ancient_dark_leaves = new BlockAncientDarkLeaves("ancient_dark_leaves");
        NibiruBlocks.nibiru_log = new BlockNibiruLog("nibiru_log", NibiruLogCategory.CAT1);
        NibiruBlocks.nibiru_wooden_planks = new BlockNibiruWoodenPlanks("nibiru_planks");
        NibiruBlocks.power_crystal_generator = new BlockPowerCrystalGenerator("power_crystal_generator");
        NibiruBlocks.ichorius_torch = new BlockIchoriusTorch("ichorius_torch");
        NibiruBlocks.ancient_dark_wood_stairs = new BlockStairsMP("ancient_dark_wood_stairs", 2.0F, StairsCategory.ancient_dark_wood, Blocks.log);
        NibiruBlocks.nibiru_cobblestone_stairs = new BlockStairsMP("nibiru_cobblestone_stairs", 4.25F, StairsCategory.NIBIRU_COBBLESTONE, Blocks.stone);
        NibiruBlocks.orange_leaves = new BlockOrangeLeaves("orange_leaves");
        NibiruBlocks.orange_wood_stairs = new BlockStairsMP("orange_wood_stairs", 2.0F, StairsCategory.ORANGE_WOOD, Blocks.log);
        NibiruBlocks.infected_grass = new BlockInfectedGrass("infected_grass");
        NibiruBlocks.infected_dirt = new BlockInfectedDirt("infected_dirt");
        NibiruBlocks.oil_rock = new BlockOilRock("oil_rock");
        NibiruBlocks.infected_vine = new BlockInfectedVine("infected_vine");
        NibiruBlocks.nibiru_dungeon_brick_stairs = new BlockStairsMP("nibiru_dungeon_brick_stairs", 4.0F, StairsCategory.NIBIRU_BRICK, Blocks.stone);
        NibiruBlocks.infected_farmland = new BlockNibiruFarmland("nibiru_farmland");
        NibiruBlocks.nibiru_fence = new BlockNibiruFence("nibiru_fence");
        NibiruBlocks.ancient_dark_fence_gate = new BlockFenceGateMP("ancient_dark_fence_gate", "nibiru:ancient_dark_wood_planks");
        NibiruBlocks.orange_fence_gate = new BlockFenceGateMP("orange_fence_gate", "nibiru:orange_wood_planks");
        NibiruBlocks.infected_cavernous_vine = new BlockInfectedCavernousVine("infected_cavernous_vine");
        NibiruBlocks.ancient_dark_door = new BlockDoorMP("ancient_dark_door_block", "nibiru:ancient_dark", DoorType.ANCIENT_DARK);
        NibiruBlocks.orange_door = new BlockDoorMP("orange_door_block", "nibiru:orange", DoorType.ORANGE);
    }

    private static void setHarvestLevels()
    {
        NibiruBlocks.ancient_dark_wood_stairs.setHarvestLevel("axe", 0);
        NibiruBlocks.nibiru_ancient_chest.setHarvestLevel("axe", 0);
        NibiruBlocks.nibiru_log.setHarvestLevel("axe", 0);
        NibiruBlocks.nibiru_wooden_planks.setHarvestLevel("axe", 0);
        NibiruBlocks.orange_wood_stairs.setHarvestLevel("axe", 0);
        NibiruBlocks.nibiru_fence.setHarvestLevel("axe", 0);
        NibiruBlocks.ancient_dark_fence_gate.setHarvestLevel("axe", 0);
        NibiruBlocks.orange_fence_gate.setHarvestLevel("axe", 0);
        NibiruBlocks.infected_grass.setHarvestLevel("shovel", 0);
        NibiruBlocks.infected_dirt.setHarvestLevel("shovel", 0);
        NibiruBlocks.infected_farmland.setHarvestLevel("shovel", 0);
        NibiruBlocks.nibiru_block.setHarvestLevel("pickaxe", 1);
        NibiruBlocks.nibiru_cobblestone_stairs.setHarvestLevel("pickaxe", 1);
        NibiruBlocks.nibiru_dungeon_brick_stairs.setHarvestLevel("pickaxe", 1);
        NibiruBlocks.oil_rock.setHarvestLevel("pickaxe", 1);
        NibiruBlocks.infected_worm_egg_rock.setHarvestLevel("pickaxe", 1);
        NibiruBlocks.power_crystal_generator.setHarvestLevel("pickaxe", 1);
    }

    private static void setFireBurn()
    {
        RegisterHelper.setFireBurn(NibiruBlocks.ancient_dark_leaves, 30, 60);
        RegisterHelper.setFireBurn(NibiruBlocks.orange_leaves, 30, 60);
        RegisterHelper.setFireBurn(NibiruBlocks.nibiru_wooden_planks, 5, 20);
        RegisterHelper.setFireBurn(NibiruBlocks.ancient_dark_wood_stairs, 5, 20);
        RegisterHelper.setFireBurn(NibiruBlocks.nibiru_fence, 5, 20);
        RegisterHelper.setFireBurn(NibiruBlocks.ancient_dark_fence_gate, 5, 20);
        RegisterHelper.setFireBurn(NibiruBlocks.orange_fence_gate, 5, 20);
        RegisterHelper.setFireBurn(NibiruBlocks.orange_wood_stairs, 5, 20);
        RegisterHelper.setFireBurn(NibiruBlocks.nibiru_sapling, 60, 100);
        RegisterHelper.setFireBurn(NibiruBlocks.infected_vine, 15, 100);
        RegisterHelper.setFireBurn(NibiruBlocks.nibiru_log, 5, 5);
    }

    private static void registerBlocks()
    {
        RegisterHelper.registerBlock(NibiruBlocks.nibiru_block, ItemBlockNibiru.class);
        RegisterHelper.registerBlock(NibiruBlocks.infected_grass);
        RegisterHelper.registerBlock(NibiruBlocks.infected_dirt, ItemBlockDirtMP.class);
        RegisterHelper.registerBlock(NibiruBlocks.oil_rock, ItemBlockOilRock.class);
        RegisterHelper.registerBlock(NibiruBlocks.helium_block);
        RegisterHelper.registerBlock(NibiruBlocks.infected_worm_egg_rock);
        RegisterHelper.registerBlock(NibiruBlocks.nibiru_log, ItemBlockNibiruLog.class);
        RegisterHelper.registerBlock(NibiruBlocks.nibiru_wooden_planks, ItemBlockNibiruWoodenPlanks.class);
        RegisterHelper.registerBlock(NibiruBlocks.ancient_dark_leaves, ItemBlockNibiruFruitsLeaves.class);
        RegisterHelper.registerBlock(NibiruBlocks.orange_leaves, ItemBlockNibiruFruitsLeaves.class);
        RegisterHelper.registerBlock(NibiruBlocks.ancient_dark_wood_stairs);
        RegisterHelper.registerBlock(NibiruBlocks.orange_wood_stairs);
        RegisterHelper.registerBlock(NibiruBlocks.nibiru_cobblestone_stairs);
        RegisterHelper.registerBlock(NibiruBlocks.nibiru_dungeon_brick_stairs);
        RegisterHelper.registerBlock(NibiruBlocks.power_crystal_generator, ItemBlockDesc.class);
        RegisterHelper.registerBlock(NibiruBlocks.nibiru_fence, ItemBlockNibiruFence.class);
        RegisterHelper.registerBlock(NibiruBlocks.ancient_dark_fence_gate);
        RegisterHelper.registerBlock(NibiruBlocks.orange_fence_gate);
        RegisterHelper.registerBlock(NibiruBlocks.nibiru_treasure_chest);
        RegisterHelper.registerBlock(NibiruBlocks.nibiru_ancient_chest);
        RegisterHelper.registerBlock(NibiruBlocks.infected_zombie_egg);
        RegisterHelper.registerBlock(NibiruBlocks.nibiru_sapling, ItemBlockNibiruSapling.class);
        RegisterHelper.registerBlock(NibiruBlocks.infected_vine);
        RegisterHelper.registerBlock(NibiruBlocks.infected_cavernous_vine);
        RegisterHelper.registerBlock(NibiruBlocks.ichorius_torch);
        RegisterHelper.registerBlock(NibiruBlocks.infected_farmland);
        RegisterHelper.registerBlock(NibiruBlocks.ancient_dark_door);
        RegisterHelper.registerBlock(NibiruBlocks.orange_door);

        OreDictionary.registerOre("oreIchorius", new ItemStack(NibiruBlocks.nibiru_block, 1, 4));
        OreDictionary.registerOre("oreNorium", new ItemStack(NibiruBlocks.nibiru_block, 1, 5));
        OreDictionary.registerOre("oreDiamond", new ItemStack(NibiruBlocks.nibiru_block, 1, 6));
        OreDictionary.registerOre("oreCoal", new ItemStack(NibiruBlocks.nibiru_block, 1, 7));
        OreDictionary.registerOre("oreRedGem", new ItemStack(NibiruBlocks.nibiru_block, 1, 8));
        OreDictionary.registerOre("oreOil", new ItemStack(NibiruBlocks.oil_rock, 1, 0));
        OreDictionary.registerOre("oreOil", new ItemStack(NibiruBlocks.oil_rock, 1, 1));

        OreDictionary.registerOre("blockIchorius", new ItemStack(NibiruBlocks.nibiru_block, 1, 9));
        OreDictionary.registerOre("blockNorium", new ItemStack(NibiruBlocks.nibiru_block, 1, 10));
        OreDictionary.registerOre("blockRedGem", new ItemStack(NibiruBlocks.nibiru_block, 1, 11));
    }
}