/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 *
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.planets.pluto.blocks;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.oredict.OreDictionary;
import stevekung.mods.moreplanets.core.blocks.BlockStairsMP;
import stevekung.mods.moreplanets.core.blocks.BlockStairsMP.StairsCategory;
import stevekung.mods.moreplanets.core.util.RegisterHelper;
import stevekung.mods.moreplanets.planets.pluto.fluids.BlockFluidMethane;
import stevekung.mods.moreplanets.planets.pluto.fluids.BlockFluidNitrogen;
import stevekung.mods.moreplanets.planets.pluto.itemblocks.ItemBlockPluto;

public class PlutoBlocks
{
    public static Block pluto_block;
    public static Block xeonium_glowstone;
    public static Block xeonium_torch;
    public static Block frozen_methane_block;
    public static Block frozen_nitrogen_block;
    public static Block pluto_ancient_chest;
    public static Block pluto_treasure_chest;
    public static Block space_potato_block;
    public static Block liquid_methane;
    public static Block liquid_nitrogen;
    public static Block pluto_cobblestone_stairs;
    public static Block pluto_dungeon_brick_stairs;

    public static Fluid liquid_methane_fluid;
    public static Fluid liquid_nitrogen_fluid;

    public static void init()
    {
        PlutoBlocks.initBlocks();
        PlutoBlocks.setHarvestLevels();
        PlutoBlocks.registerBlocks();
    }

    private static void initBlocks()
    {
        PlutoBlocks.pluto_block = new BlockBasicPluto("pluto_block");
        PlutoBlocks.xeonium_glowstone = new BlockXeoniumGlowstone("xeonium_glowstone");
        PlutoBlocks.xeonium_torch = new BlockXeoniumTorch("xeonium_torch");
        PlutoBlocks.frozen_methane_block = new BlockFrozenMethane("frozen_methane_block");
        PlutoBlocks.frozen_nitrogen_block = new BlockFrozenNitrogen("frozen_nitrogen_block");
        PlutoBlocks.pluto_ancient_chest = new BlockPlutoAncientChest("pluto_ancient_chest");
        PlutoBlocks.pluto_treasure_chest = new BlockPlutoTreasureChest("pluto_treasure_chest");
        PlutoBlocks.space_potato_block = new BlockSpacePotato("space_potato_block");
        PlutoBlocks.pluto_cobblestone_stairs = new BlockStairsMP("pluto_cobblestone_stairs", 2.0F, StairsCategory.pluto_cobblestone, Blocks.stone);
        PlutoBlocks.pluto_dungeon_brick_stairs = new BlockStairsMP("pluto_dungeon_brick_stairs", 2.0F, StairsCategory.pluto_dungeon_brick, Blocks.stone);

        PlutoBlocks.liquid_methane_fluid = new Fluid("liquid_methane_fluid").setBlock(PlutoBlocks.liquid_methane).setViscosity(3000);
        PlutoBlocks.liquid_nitrogen_fluid = new Fluid("liquid_nitrogen_fluid").setBlock(PlutoBlocks.liquid_nitrogen).setViscosity(3000);
        FluidRegistry.registerFluid(PlutoBlocks.liquid_methane_fluid);
        FluidRegistry.registerFluid(PlutoBlocks.liquid_nitrogen_fluid);
        PlutoBlocks.liquid_methane = new BlockFluidMethane("liquid_methane_fluid");
        PlutoBlocks.liquid_nitrogen = new BlockFluidNitrogen("liquid_nitrogen_fluid");
    }

    private static void setHarvestLevels()
    {
        PlutoBlocks.pluto_block.setHarvestLevel("pickaxe", 3);
        PlutoBlocks.frozen_methane_block.setHarvestLevel("pickaxe", 2);
        PlutoBlocks.frozen_nitrogen_block.setHarvestLevel("pickaxe", 2);
        PlutoBlocks.pluto_cobblestone_stairs.setHarvestLevel("pickaxe", 0);
        PlutoBlocks.pluto_dungeon_brick_stairs.setHarvestLevel("pickaxe", 0);
        PlutoBlocks.pluto_ancient_chest.setHarvestLevel("axe", 0);
    }

    private static void registerBlocks()
    {
        RegisterHelper.registerBlock(PlutoBlocks.pluto_block, ItemBlockPluto.class);
        RegisterHelper.registerBlock(PlutoBlocks.xeonium_glowstone);
        RegisterHelper.registerBlock(PlutoBlocks.frozen_methane_block);
        RegisterHelper.registerBlock(PlutoBlocks.frozen_nitrogen_block);
        RegisterHelper.registerBlock(PlutoBlocks.pluto_ancient_chest);
        RegisterHelper.registerBlock(PlutoBlocks.pluto_treasure_chest);
        RegisterHelper.registerBlock(PlutoBlocks.pluto_cobblestone_stairs);
        RegisterHelper.registerBlock(PlutoBlocks.pluto_dungeon_brick_stairs);
        RegisterHelper.registerBlock(PlutoBlocks.xeonium_torch);
        RegisterHelper.registerBlock(PlutoBlocks.liquid_methane);
        RegisterHelper.registerBlock(PlutoBlocks.liquid_nitrogen);
        RegisterHelper.registerBlock(PlutoBlocks.space_potato_block);

        OreDictionary.registerOre("oreMeteor", new ItemStack(PlutoBlocks.pluto_block, 1, 4));
        OreDictionary.registerOre("oreFrozenIron", new ItemStack(PlutoBlocks.pluto_block, 1, 5));
        OreDictionary.registerOre("oreIron", new ItemStack(PlutoBlocks.pluto_block, 1, 6));
        OreDictionary.registerOre("oreXeonium", new ItemStack(PlutoBlocks.pluto_block, 1, 7));
    }
}