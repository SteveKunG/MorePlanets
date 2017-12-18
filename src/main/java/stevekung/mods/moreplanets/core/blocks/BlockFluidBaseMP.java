/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 *
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.core.blocks;

import java.util.Random;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fluids.BlockFluidClassic;
import net.minecraftforge.fluids.Fluid;

public abstract class BlockFluidBaseMP extends BlockFluidClassic
{
    @SideOnly(Side.CLIENT)
    IIcon stillIcon;
    @SideOnly(Side.CLIENT)
    IIcon flowingIcon;
    Fluid fluid;
    int field_149815_a;

    public BlockFluidBaseMP(Fluid fluid)
    {
        super(fluid, Material.water);
        this.fluid = fluid;
    }

    public BlockFluidBaseMP(Fluid fluid, Material material)
    {
        super(fluid, material);
        this.fluid = fluid;
    }

    @Override
    public boolean canDisplace(IBlockAccess world, int x, int y, int z)
    {
        if (world.getBlock(x, y, z).getMaterial().isLiquid())
        {
            return false;
        }
        if (world.getBlock(x, y, z).getMaterial() == Material.lava)
        {
            return false;
        }
        return super.canDisplace(world, x, y, z);
    }

    @Override
    public boolean displaceIfPossible(World world, int x, int y, int z)
    {
        if (world.getBlock(x, y, z).getMaterial().isLiquid())
        {
            return false;
        }
        if (world.getBlock(x, y, z).getMaterial() == Material.lava)
        {
            return false;
        }
        return super.displaceIfPossible(world, x, y, z);
    }

    @Override
    public void updateTick(World world, int x, int y, int z, Random rand)
    {
        super.updateTick(world, x, y, z, rand);

        if (this.blockMaterial == Material.water)
        {
            int l = this.getLevel(world, x, y, z);
            byte b0 = 1;

            int i1 = this.tickRate(world);
            int j1;

            if (l > 0)
            {
                byte b1 = -100;
                this.field_149815_a = 0;
                int l1 = this.checkAdjacentBlock(world, x - 1, y, z, b1);
                l1 = this.checkAdjacentBlock(world, x + 1, y, z, l1);
                l1 = this.checkAdjacentBlock(world, x, y, z - 1, l1);
                l1 = this.checkAdjacentBlock(world, x, y, z + 1, l1);
                j1 = l1 + b0;

                if (j1 >= 8 || l1 < 0)
                {
                    j1 = -1;
                }

                if (this.getLevel(world, x, y + 1, z) >= 0)
                {
                    int k1 = this.getLevel(world, x, y + 1, z);

                    if (k1 >= 8)
                    {
                        j1 = k1;
                    }
                    else
                    {
                        j1 = k1 + 8;
                    }
                }

                if (this.field_149815_a >= 2)
                {
                    if (world.getBlock(x, y - 1, z).getMaterial().isSolid())
                    {
                        j1 = 0;
                    }
                    else if (world.getBlock(x, y - 1, z).getMaterial() == this.blockMaterial && world.getBlockMetadata(x, y - 1, z) == 0)
                    {
                        j1 = 0;
                    }
                }

                if (j1 == l)
                {
                    return;
                }
                else
                {
                    l = j1;

                    if (j1 < 0)
                    {
                        world.setBlockToAir(x, y, z);
                    }
                    else
                    {
                        world.setBlockMetadataWithNotify(x, y, z, j1, 2);
                        world.scheduleBlockUpdate(x, y, z, this, i1);
                        world.notifyBlocksOfNeighborChange(x, y, z, this);
                    }
                }
            }
        }
    }

    private int checkAdjacentBlock(World world, int x, int y, int z, int level)
    {
        int i1 = this.getLevel(world, x, y, z);

        if (i1 < 0)
        {
            return level;
        }
        else
        {
            if (i1 == 0)
            {
                ++this.field_149815_a;
            }
            if (i1 >= 8)
            {
                i1 = 0;
            }
            return level >= 0 && i1 >= level ? level : i1;
        }
    }

    private int getLevel(World world, int x, int y, int z)
    {
        return world.getBlock(x, y, z).getMaterial() == this.blockMaterial ? world.getBlockMetadata(x, y, z) : -1;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void registerBlockIcons(IIconRegister iconRegister)
    {
        this.stillIcon = iconRegister.registerIcon(this.getStillTextures());
        this.flowingIcon = iconRegister.registerIcon(this.getFlowingTextures());
        this.fluid.setFlowingIcon(this.flowingIcon);
        this.fluid.setStillIcon(this.stillIcon);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public IIcon getIcon(int par1, int par2)
    {
        return par1 != 0 && par1 != 1 ? this.flowingIcon : this.stillIcon;
    }

    public abstract String getStillTextures();
    public abstract String getFlowingTextures();
}