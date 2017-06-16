/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 *
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.moons.io.blocks;

import net.minecraft.block.Block;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidRegistry;
import stevekung.mods.moreplanets.core.util.RegisterHelper;
import stevekung.mods.moreplanets.moons.io.fluids.*;
import stevekung.mods.moreplanets.moons.io.itemblocks.ItemBlockIo;
import stevekung.mods.moreplanets.moons.io.itemblocks.ItemBlockMagmaRock;

public class IoBlocks
{
    public static Block io_block;
    public static Block io_magma_rock;
    public static Block liquid_red_sulfur;
    public static Block liquid_yellow_sulfur;
    public static Block liquid_orange_sulfur;
    public static Block liquid_brown_sulfur;
    public static Block io_lava;
    public static Block io_black_lava;

    public static Fluid liquid_red_sulfur_fluid;
    public static Fluid liquid_yellow_sulfur_fluid;
    public static Fluid liquid_orange_sulfur_fluid;
    public static Fluid liquid_brown_sulfur_fluid;
    public static Fluid io_lava_fluid;
    public static Fluid io_black_lava_fluid;

    public static void init()
    {
        initBlocks();
        setHarvestLevels();
        registerBlocks();
    }

    private static void initBlocks()
    {
        IoBlocks.io_block = new BlockBasicIo("io_block");
        IoBlocks.io_magma_rock = new BlockIoMagmaRock("io_magma_rock");

        IoBlocks.io_lava_fluid = new Fluid("io_lava").setBlock(IoBlocks.io_lava).setLuminosity(15).setDensity(3000).setViscosity(6000).setTemperature(1300);
        IoBlocks.io_black_lava_fluid = new Fluid("io_black_lava").setBlock(IoBlocks.io_black_lava).setLuminosity(15).setDensity(3000).setViscosity(6000).setTemperature(1300);
        IoBlocks.liquid_red_sulfur_fluid = new Fluid("liquid_red_sulfur").setBlock(IoBlocks.liquid_red_sulfur).setLuminosity(15).setDensity(3000).setViscosity(6000).setTemperature(1300);
        IoBlocks.liquid_yellow_sulfur_fluid = new Fluid("liquid_yellow_sulfur").setBlock(IoBlocks.liquid_yellow_sulfur).setLuminosity(15).setDensity(3000).setViscosity(6000).setTemperature(1300);
        IoBlocks.liquid_orange_sulfur_fluid = new Fluid("liquid_orange_sulfur").setBlock(IoBlocks.liquid_orange_sulfur).setLuminosity(15).setDensity(3000).setViscosity(6000).setTemperature(1300);
        IoBlocks.liquid_brown_sulfur_fluid = new Fluid("liquid_brown_sulfur").setBlock(IoBlocks.liquid_brown_sulfur).setLuminosity(15).setDensity(3000).setViscosity(6000).setTemperature(1300);
        FluidRegistry.registerFluid(IoBlocks.io_lava_fluid);
        FluidRegistry.registerFluid(IoBlocks.io_black_lava_fluid);
        FluidRegistry.registerFluid(IoBlocks.liquid_red_sulfur_fluid);
        FluidRegistry.registerFluid(IoBlocks.liquid_yellow_sulfur_fluid);
        FluidRegistry.registerFluid(IoBlocks.liquid_orange_sulfur_fluid);
        FluidRegistry.registerFluid(IoBlocks.liquid_brown_sulfur_fluid);
        IoBlocks.io_lava = new BlockFluidIoLava("io_lava");
        IoBlocks.io_black_lava = new BlockFluidIoBlackLava("io_black_lava");
        IoBlocks.liquid_red_sulfur = new BlockFluidLiquidRedSulfur("liquid_red_sulfur");
        IoBlocks.liquid_yellow_sulfur = new BlockFluidLiquidYellowSulfur("liquid_yellow_sulfur");
        IoBlocks.liquid_orange_sulfur = new BlockFluidLiquidOrangeSulfur("liquid_orange_sulfur");
        IoBlocks.liquid_brown_sulfur = new BlockFluidLiquidBrownSulfur("liquid_brown_sulfur");
    }

    public static void setHarvestLevels()
    {
        IoBlocks.io_block.setHarvestLevel("pickaxe", 1);
        IoBlocks.io_magma_rock.setHarvestLevel("pickaxe", 1);
    }

    private static void registerBlocks()
    {
        RegisterHelper.registerBlock(IoBlocks.io_block, ItemBlockIo.class);
        RegisterHelper.registerBlock(IoBlocks.io_magma_rock, ItemBlockMagmaRock.class);
        RegisterHelper.registerBlock(IoBlocks.io_lava);
        RegisterHelper.registerBlock(IoBlocks.io_black_lava);
        RegisterHelper.registerBlock(IoBlocks.liquid_red_sulfur);
        RegisterHelper.registerBlock(IoBlocks.liquid_yellow_sulfur);
        RegisterHelper.registerBlock(IoBlocks.liquid_orange_sulfur);
        RegisterHelper.registerBlock(IoBlocks.liquid_brown_sulfur);
    }
}