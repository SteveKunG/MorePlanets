/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 *
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.moons.io.fluids;

import java.util.Random;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import stevekung.mods.moreplanets.core.blocks.BlockFluidLavaBaseMP;
import stevekung.mods.moreplanets.moons.io.blocks.IoBlocks;

public class BlockFluidIoLava extends BlockFluidLavaBaseMP
{
    public BlockFluidIoLava(String name)
    {
        super(IoBlocks.io_lava_fluid);
        this.setBlockName(name);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void randomDisplayTick(World world, int x, int y, int z, Random rand)
    {
        if (this.blockMaterial == Material.lava && world.getBlock(x, y + 1, z).getMaterial() == Material.air && !world.getBlock(x, y + 1, z).isOpaqueCube())
        {
            if (rand.nextInt(100) == 0)
            {
                double d5 = x + rand.nextFloat();
                double d6 = y + this.maxY;
                double d7 = z + rand.nextFloat();
                world.spawnParticle("lava", d5, d6, d7, 0.0D, 0.0D, 0.0D);
                world.playSound(d5, d6, d7, "liquid.lavapop", 0.2F + rand.nextFloat() * 0.2F, 0.9F + rand.nextFloat() * 0.15F, false);
            }
            if (rand.nextInt(200) == 0)
            {
                world.playSound(x, y, z, "liquid.lava", 0.2F + rand.nextFloat() * 0.2F, 0.9F + rand.nextFloat() * 0.15F, false);
            }
        }
        if (rand.nextInt(10) == 0 && World.doesBlockHaveSolidTopSurface(world, x, y - 1, z) && !world.getBlock(x, y - 2, z).getMaterial().blocksMovement())
        {
            double d5 = x + rand.nextFloat();
            double d6 = y - 1.05D;
            double d7 = z + rand.nextFloat();
            world.spawnParticle("dripLava", d5, d6, d7, 0.0D, 0.0D, 0.0D);
        }
        super.randomDisplayTick(world, x, y, z, rand);
    }

    @Override
    public String getStillTextures()
    {
        return "lava_still";
    }

    @Override
    public String getFlowingTextures()
    {
        return "lava_flow";
    }

    @Override
    protected Block getBlockFromWaterTo()
    {
        return IoBlocks.io_block;
    }

    @Override
    protected int getBlockMetaFromWaterTo()
    {
        return 2;
    }

    @Override
    protected Block getObsidianBlock()
    {
        return Blocks.obsidian;
    }

    @Override
    protected Block getCobblestoneBlock()
    {
        return IoBlocks.io_block;
    }

    @Override
    protected int getCobblestoneBlockMeta()
    {
        return 3;
    }

    @Override
    protected Block getFireBlock()
    {
        return Blocks.fire;
    }
}