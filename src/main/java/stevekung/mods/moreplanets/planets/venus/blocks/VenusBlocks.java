/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 *
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.planets.venus.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;
import stevekung.mods.moreplanets.core.blocks.BlockStairsMP;
import stevekung.mods.moreplanets.core.blocks.BlockStairsMP.StairsCategory;
import stevekung.mods.moreplanets.core.util.RegisterHelper;
import stevekung.mods.moreplanets.planets.venus.blocks.BlockVenusSandstoneSlab.VenusSlabCategory;
import stevekung.mods.moreplanets.planets.venus.itemblocks.ItemBlockVenus;
import stevekung.mods.moreplanets.planets.venus.itemblocks.ItemBlockVenusSandstone;
import stevekung.mods.moreplanets.planets.venus.itemblocks.ItemBlockVenusSandstoneSlab;

public class VenusBlocks
{
    public static Block venus_block;
    public static Block venus_redstone_ore;
    public static Block venus_redstone_ore_active;
    public static Block venus_smoke_geyser;
    public static Block venus_magma_rock;
    public static Block venus_sand;
    public static Block venusian_blaze_egg;
    public static Block sulfur_torch;
    public static Block venus_ancient_chest;
    public static Block venus_treasure_chest;
    public static Block venus_sandstone;
    public static Block half_venus_sandstone_slab;
    public static Block double_venus_sandstone_slab;
    public static Block venus_sandstone_stairs;
    public static Block venus_cobblestone_stairs;
    public static Block venus_dungeon_brick_stairs;

    public static void init()
    {
        VenusBlocks.initBlocks();
        VenusBlocks.setHarvestLevels();
        VenusBlocks.registerBlocks();
    }

    private static void initBlocks()
    {
        VenusBlocks.venus_block = new BlockBasicVenus("venus_block");
        VenusBlocks.venus_redstone_ore = new BlockVenusRedstoneOre("venus_redstone_ore", false).setBlockTextureName("venus:venus_redstone_ore");
        VenusBlocks.venus_redstone_ore_active = new BlockVenusRedstoneOre("venus_redstone_ore_active", true).setBlockTextureName("venus:venus_redstone_ore");
        VenusBlocks.venus_smoke_geyser = new BlockVenusSmokeGeyser("venus_smoke_geyser");
        VenusBlocks.venus_sand = new BlockVenusSand("venus_sand");
        VenusBlocks.venus_magma_rock = new BlockVenusMagmaRock("venus_magma_rock");
        VenusBlocks.venusian_blaze_egg = new BlockVenusianBlazeEgg("venusian_blaze_egg");
        VenusBlocks.sulfur_torch = new BlockSulfurTorch("sulfur_torch");
        VenusBlocks.venus_ancient_chest = new BlockVenusAncientChest("venus_ancient_chest");
        VenusBlocks.venus_treasure_chest = new BlockVenusTreasureChest("venus_treasure_chest");
        VenusBlocks.venus_sandstone = new BlockVenusSandstone("venus_sandstone");
        VenusBlocks.half_venus_sandstone_slab = new BlockVenusSandstoneSlab("half_venus_sandstone_slab", false, Material.rock, VenusSlabCategory.WOOD1);
        VenusBlocks.double_venus_sandstone_slab = new BlockVenusSandstoneSlab("double_venus_sandstone_slab", true, Material.rock, VenusSlabCategory.WOOD1);
        VenusBlocks.venus_sandstone_stairs = new BlockStairsMP("venus_sandstone_stairs", 0.8F, StairsCategory.venus_sandstone, Blocks.stone);
        VenusBlocks.venus_cobblestone_stairs = new BlockStairsMP("venus_cobblestone_stairs", 2.0F, StairsCategory.venus_cobblestone, Blocks.stone);
        VenusBlocks.venus_dungeon_brick_stairs = new BlockStairsMP("venus_dungeon_brick_stairs", 2.0F, StairsCategory.venus_dungeon_brick, Blocks.stone);
    }

    private static void setHarvestLevels()
    {
        VenusBlocks.venus_block.setHarvestLevel("pickaxe", 1);
        VenusBlocks.venus_magma_rock.setHarvestLevel("pickaxe", 1);
        VenusBlocks.venus_smoke_geyser.setHarvestLevel("pickaxe", 1);
        VenusBlocks.venus_redstone_ore.setHarvestLevel("pickaxe", 2);
        VenusBlocks.venus_redstone_ore_active.setHarvestLevel("pickaxe", 2);
        VenusBlocks.venus_sand.setHarvestLevel("shovel", 0);
        VenusBlocks.venus_ancient_chest.setHarvestLevel("axe", 0);
        VenusBlocks.venus_sandstone.setHarvestLevel("pickaxe", 0);
        VenusBlocks.half_venus_sandstone_slab.setHarvestLevel("pickaxe", 0);
        VenusBlocks.double_venus_sandstone_slab.setHarvestLevel("pickaxe", 0);
        VenusBlocks.venus_sandstone_stairs.setHarvestLevel("pickaxe", 0);
        VenusBlocks.venus_cobblestone_stairs.setHarvestLevel("pickaxe", 0);
        VenusBlocks.venus_dungeon_brick_stairs.setHarvestLevel("pickaxe", 0);
    }

    private static void registerBlocks()
    {
        RegisterHelper.registerBlock(VenusBlocks.venus_block, ItemBlockVenus.class);
        RegisterHelper.registerBlock(VenusBlocks.venus_redstone_ore);
        RegisterHelper.registerBlock(VenusBlocks.venus_redstone_ore_active);
        RegisterHelper.registerBlock(VenusBlocks.venus_smoke_geyser);
        RegisterHelper.registerBlock(VenusBlocks.venus_magma_rock);
        RegisterHelper.registerBlock(VenusBlocks.venus_sand);
        RegisterHelper.registerBlock(VenusBlocks.venus_sandstone, ItemBlockVenusSandstone.class);
        RegisterHelper.registerBlock(VenusBlocks.half_venus_sandstone_slab, ItemBlockVenusSandstoneSlab.class, VenusBlocks.half_venus_sandstone_slab, VenusBlocks.double_venus_sandstone_slab);
        RegisterHelper.registerBlock(VenusBlocks.double_venus_sandstone_slab, ItemBlockVenusSandstoneSlab.class, VenusBlocks.half_venus_sandstone_slab, VenusBlocks.double_venus_sandstone_slab);
        RegisterHelper.registerBlock(VenusBlocks.sulfur_torch);
        RegisterHelper.registerBlock(VenusBlocks.venus_cobblestone_stairs);
        RegisterHelper.registerBlock(VenusBlocks.venus_dungeon_brick_stairs);
        RegisterHelper.registerBlock(VenusBlocks.venus_sandstone_stairs);
        RegisterHelper.registerBlock(VenusBlocks.venusian_blaze_egg);
        RegisterHelper.registerBlock(VenusBlocks.venus_ancient_chest);
        RegisterHelper.registerBlock(VenusBlocks.venus_treasure_chest);

        OreDictionary.registerOre("oreSulfur", new ItemStack(VenusBlocks.venus_block, 1, 4));
        OreDictionary.registerOre("oreLead", new ItemStack(VenusBlocks.venus_block, 1, 5));
        OreDictionary.registerOre("oreTin", new ItemStack(VenusBlocks.venus_block, 1, 6));
        OreDictionary.registerOre("oreCopper", new ItemStack(VenusBlocks.venus_block, 1, 7));
        OreDictionary.registerOre("oreCoal", new ItemStack(VenusBlocks.venus_block, 1, 8));
        OreDictionary.registerOre("oreIron", new ItemStack(VenusBlocks.venus_block, 1, 9));
        OreDictionary.registerOre("oreGold", new ItemStack(VenusBlocks.venus_block, 1, 10));
        OreDictionary.registerOre("oreRedstone", new ItemStack(VenusBlocks.venus_redstone_ore, 1, 0));

        OreDictionary.registerOre("blockLead", new ItemStack(VenusBlocks.venus_block, 1, 11));

        OreDictionary.registerOre("sand", VenusBlocks.venus_sand);
    }
}