/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 *
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.moons.koentus.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.BlockSapling;
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
import stevekung.mods.moreplanets.moons.koentus.blocks.BlockCrystalLeaves.KoentusLeafCategory;
import stevekung.mods.moreplanets.moons.koentus.blocks.BlockCrystalLog.KoentusLogCategory;
import stevekung.mods.moreplanets.moons.koentus.itemblocks.*;

public class KoentusBlocks
{
    public static Block koentus_block;
    public static Block koentus_ice;
    public static Block koentus_cobblestone_stairs;
    public static Block koentus_dungeon_brick_stairs;
    public static Block koentus_treasure_chest;
    public static Block eledos_egg;
    public static Block white_crystal_torch;
    public static Block fallen_koentus_meteor;
    public static Block koentus_ancient_stone_stairs;
    public static Block koentus_ancient_brick_stairs;
    public static Block koentus_ancient_chest;
    public static Block glowing_ice_stone;
    public static Block crystal_segment;
    public static Block crystal_dirt;
    public static Block crystal_wooden_planks;
    public static Block crystal_log;
    public static BlockSapling crystal_sapling;
    public static Block crystal_leaves;
    public static Block crystal_fence;
    public static Block crystal_fence_gate;
    public static Block crystal_farmland;
    public static Block crystal_wood_stairs;
    public static Block crystal_door;

    public static void init()
    {
        KoentusBlocks.initBlocks();
        KoentusBlocks.setHarvestLevels();
        KoentusBlocks.registerBlocks();
        KoentusBlocks.setFireBurn();
    }

    private static void initBlocks()
    {
        KoentusBlocks.koentus_block = new BlockBasicKoentus("koentus_block");
        KoentusBlocks.koentus_ice = new BlockKoentusIce("koentus_ice");
        KoentusBlocks.koentus_cobblestone_stairs = new BlockStairsMP("koentus_cobblestone_stairs", 3.25F, StairsCategory.KOENTUS_COBBLESTONE, Blocks.stone);
        KoentusBlocks.koentus_dungeon_brick_stairs = new BlockStairsMP("koentus_dungeon_brick_stairs", 4.0F, StairsCategory.KOENTUS_BRICK, Blocks.stone);
        KoentusBlocks.fallen_koentus_meteor = new BlockFallenKoentusMeteor("fallen_koentus_meteor");
        KoentusBlocks.eledos_egg = new BlockEledosEgg("eledos_egg");
        KoentusBlocks.white_crystal_torch = new BlockWhiteCrystalTorch("white_crystal_torch");
        KoentusBlocks.koentus_treasure_chest = new BlockT4KoentusTreasureChest("koentus_treasure_chest");
        KoentusBlocks.koentus_ancient_stone_stairs = new BlockStairsMP("koentus_ancient_stone_stairs", 2.5F, StairsCategory.KOENTUS_ANCIENT_STONE, Blocks.stone);
        KoentusBlocks.koentus_ancient_brick_stairs = new BlockStairsMP("koentus_ancient_brick_stairs", 2.5F, StairsCategory.KOENTUS_ANCIENT_BRICK, Blocks.stone);
        KoentusBlocks.koentus_ancient_chest = new BlockKoentusAncientChest("koentus_ancient_chest");
        KoentusBlocks.glowing_ice_stone = new BlockGlowingIceStone("glowing_ice_stone");
        KoentusBlocks.crystal_segment = new BlockCrystalSegment("crystal_segment");
        KoentusBlocks.crystal_dirt = new BlockCrystalDirt("crystal_dirt");
        KoentusBlocks.crystal_wooden_planks = new BlockCrystalWoodenPlanks("crystal_planks");
        KoentusBlocks.crystal_log = new BlockCrystalLog("crystal_log", KoentusLogCategory.CAT1);
        KoentusBlocks.crystal_sapling = new BlockCrystalSapling("crystal_sapling");
        KoentusBlocks.crystal_leaves = new BlockCrystalLeaves("crystal_leaves", KoentusLeafCategory.CAT1);
        KoentusBlocks.crystal_fence = new BlockCrystalFence("crystal_fence");
        KoentusBlocks.crystal_fence_gate = new BlockFenceGateMP("crystal_fence_gate", "koentus:crystal_planks");
        KoentusBlocks.crystal_farmland = new BlockCrystalFarmland("crystal_farmland");
        KoentusBlocks.crystal_wood_stairs = new BlockStairsMP("crystal_wood_stairs", 2.0F, StairsCategory.CRYSTAL_WOOD, Blocks.log);
        KoentusBlocks.crystal_door = new BlockDoorMP("crystal_door_block", "koentus:crystal", DoorType.CRYSTAL);
    }

    private static void setHarvestLevels()
    {
        KoentusBlocks.koentus_block.setHarvestLevel("pickaxe", 0);
        KoentusBlocks.fallen_koentus_meteor.setHarvestLevel("pickaxe", 1);
        KoentusBlocks.eledos_egg.setHarvestLevel("pickaxe", 1);
        KoentusBlocks.crystal_segment.setHarvestLevel("pickaxe", 1);
        KoentusBlocks.koentus_ancient_chest.setHarvestLevel("axe", 0);
        KoentusBlocks.crystal_log.setHarvestLevel("axe", 0);
        KoentusBlocks.crystal_fence.setHarvestLevel("axe", 0);
        KoentusBlocks.crystal_fence_gate.setHarvestLevel("axe", 0);
        KoentusBlocks.crystal_wooden_planks.setHarvestLevel("axe", 0);
        KoentusBlocks.crystal_wood_stairs.setHarvestLevel("axe", 0);
        KoentusBlocks.crystal_door.setHarvestLevel("axe", 0);
        KoentusBlocks.crystal_dirt.setHarvestLevel("shovel", 0);
        KoentusBlocks.crystal_farmland.setHarvestLevel("shovel", 0);
        KoentusBlocks.koentus_cobblestone_stairs.setHarvestLevel("pickaxe", 1);
        KoentusBlocks.koentus_dungeon_brick_stairs.setHarvestLevel("pickaxe", 1);
        KoentusBlocks.koentus_ancient_stone_stairs.setHarvestLevel("pickaxe", 1);
        KoentusBlocks.koentus_ancient_brick_stairs.setHarvestLevel("pickaxe", 1);
    }

    private static void setFireBurn()
    {
        RegisterHelper.setFireBurn(KoentusBlocks.crystal_wooden_planks, 5, 20);
        RegisterHelper.setFireBurn(KoentusBlocks.crystal_wood_stairs, 5, 20);
        RegisterHelper.setFireBurn(KoentusBlocks.crystal_fence, 5, 20);
        RegisterHelper.setFireBurn(KoentusBlocks.crystal_fence_gate, 5, 20);
        RegisterHelper.setFireBurn(KoentusBlocks.crystal_log, 5, 5);
        RegisterHelper.setFireBurn(KoentusBlocks.crystal_sapling, 60, 100);
        RegisterHelper.setFireBurn(KoentusBlocks.crystal_leaves, 30, 60);
    }

    private static void registerBlocks()
    {
        RegisterHelper.registerBlock(KoentusBlocks.koentus_block, ItemBlockKoentus.class);
        RegisterHelper.registerBlock(KoentusBlocks.crystal_dirt, ItemBlockDirtMP.class);
        RegisterHelper.registerBlock(KoentusBlocks.crystal_wooden_planks);
        RegisterHelper.registerBlock(KoentusBlocks.crystal_log, ItemBlockCrystalLog.class);
        RegisterHelper.registerBlock(KoentusBlocks.crystal_leaves);
        RegisterHelper.registerBlock(KoentusBlocks.koentus_ice, ItemBlockKoentusIce.class);
        RegisterHelper.registerBlock(KoentusBlocks.glowing_ice_stone, ItemBlockGlowingIce.class);
        RegisterHelper.registerBlock(KoentusBlocks.crystal_wood_stairs);
        RegisterHelper.registerBlock(KoentusBlocks.koentus_cobblestone_stairs);
        RegisterHelper.registerBlock(KoentusBlocks.koentus_dungeon_brick_stairs);
        RegisterHelper.registerBlock(KoentusBlocks.koentus_ancient_stone_stairs);
        RegisterHelper.registerBlock(KoentusBlocks.koentus_ancient_brick_stairs);
        RegisterHelper.registerBlock(KoentusBlocks.koentus_treasure_chest);
        RegisterHelper.registerBlock(KoentusBlocks.koentus_ancient_chest);
        RegisterHelper.registerBlock(KoentusBlocks.crystal_fence);
        RegisterHelper.registerBlock(KoentusBlocks.crystal_fence_gate);
        RegisterHelper.registerBlock(KoentusBlocks.crystal_farmland);
        RegisterHelper.registerBlock(KoentusBlocks.eledos_egg);
        RegisterHelper.registerBlock(KoentusBlocks.fallen_koentus_meteor, ItemBlockKoentusMeteor.class);
        RegisterHelper.registerBlock(KoentusBlocks.crystal_segment);
        RegisterHelper.registerBlock(KoentusBlocks.white_crystal_torch);
        RegisterHelper.registerBlock(KoentusBlocks.crystal_sapling);
        RegisterHelper.registerBlock(KoentusBlocks.crystal_door);

        OreDictionary.registerOre("oreTin", new ItemStack(KoentusBlocks.koentus_block, 1, 4));
        OreDictionary.registerOre("oreCopper", new ItemStack(KoentusBlocks.koentus_block, 1, 5));
        OreDictionary.registerOre("oreWhiteCrystal", new ItemStack(KoentusBlocks.koentus_block, 1, 6));
        OreDictionary.registerOre("oreEMP", new ItemStack(KoentusBlocks.koentus_block, 1, 7));
        OreDictionary.registerOre("oreBecterialFossil", new ItemStack(KoentusBlocks.koentus_block, 1, 8));

        OreDictionary.registerOre("blockWhiteCrystal", new ItemStack(KoentusBlocks.koentus_block, 1, 9));
        OreDictionary.registerOre("blockEMP", new ItemStack(KoentusBlocks.koentus_block, 1, 10));
    }
}