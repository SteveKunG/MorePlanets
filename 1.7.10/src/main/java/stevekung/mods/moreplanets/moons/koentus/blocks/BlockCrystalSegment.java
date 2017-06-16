/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 *
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.moons.koentus.blocks;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.BlockBreakable;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import stevekung.mods.moreplanets.core.MorePlanetsCore;

public class BlockCrystalSegment extends BlockBreakable
{
    public BlockCrystalSegment(String name)
    {
        super("koentus:crystal_segment", Material.rock, false);
        this.setHardness(3.0F);
        this.setResistance(5.0F);
        this.setBlockName(name);
    }

    @Override
    public CreativeTabs getCreativeTabToDisplayOn()
    {
        return MorePlanetsCore.mpBlocksTab;
    }

    @Override
    public AxisAlignedBB getCollisionBoundingBoxFromPool(World world, int x, int y, int z)
    {
        int meta = world.getBlockMetadata(x, y, z);

        switch (meta)
        {
        case 0:
            return AxisAlignedBB.getBoundingBox(x + 0.374D, y, z + 0.374D, x + 0.626D, y + 1.0D, z + 0.626D);
        case 4:
            return AxisAlignedBB.getBoundingBox(x, y + 0.374D, z + 0.374D, x + 1.00D, y + 0.626D, z + 0.626D);
        case 8:
            return AxisAlignedBB.getBoundingBox(x + 0.374D, y + 0.374D, z, x + 0.626D, y + 0.626D, z + 1.00D);
        default:
            return AxisAlignedBB.getBoundingBox(x, y, z, x + 1.0D, y + 1.0D, z + 1.0D);
        }
    }

    @Override
    public AxisAlignedBB getSelectedBoundingBoxFromPool(World world, int x, int y, int z)
    {
        int meta = world.getBlockMetadata(x, y, z);

        switch (meta)
        {
        case 0:
            return AxisAlignedBB.getBoundingBox(x + 0.374D, y, z + 0.374D, x + 0.626D, y + 1.0D, z + 0.626D);
        case 4:
            return AxisAlignedBB.getBoundingBox(x, y + 0.374D, z + 0.374D, x + 1.00D, y + 0.626D, z + 0.626D);
        case 8:
            return AxisAlignedBB.getBoundingBox(x + 0.374D, y + 0.374D, z, x + 0.626D, y + 0.626D, z + 1.00D);
        default:
            return AxisAlignedBB.getBoundingBox(x, y, z, x + 1.0D, y + 1.0D, z + 1.0D);
        }
    }

    @Override
    public void setBlockBoundsBasedOnState(IBlockAccess world, int x, int y, int z)
    {
        int meta = world.getBlockMetadata(x, y, z);

        float minX;
        float minY;
        float minZ;
        float maxX;
        float maxY;
        float maxZ;

        switch (meta)
        {
        case 0:
            minY = 0F;
            minX = minZ = 0.374F;
            maxX = maxZ = 0.626F;
            maxY = 1.0F;
            break;
        case 4:
            minX = 0F;
            minY = minZ = 0.374F;
            maxX = 1.00F;
            maxY = maxZ = 0.626F;
            break;
        case 8:
            minX = minY = 0.374F;
            minZ = 0F;
            maxX = maxY = 0.626F;
            maxZ = 1.00F;
            break;
        default:
            minY = 0F;
            minX = minZ = 0.0F;
            maxX = maxZ = 1.0F;
            maxY = 1.0F;
            break;
        }
        this.setBlockBounds(minX, minY, minZ, maxX, maxY, maxZ);
    }

    @Override
    public int getDamageValue(World world, int x, int y, int z)
    {
        return 0;
    }

    @Override
    public boolean isOpaqueCube()
    {
        return false;
    }

    @Override
    public boolean renderAsNormalBlock()
    {
        return false;
    }

    @Override
    public int getRenderType()
    {
        return MorePlanetsCore.proxy.getBlockRender(this);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public boolean shouldSideBeRendered(IBlockAccess par1IBlockAccess, int par2, int par3, int par4, int par5)
    {
        return true;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public int getRenderBlockPass()
    {
        return 1;
    }

    @Override
    public int damageDropped(int meta)
    {
        return 0;
    }

    @Override
    public int onBlockPlaced(World world, int x, int y, int z, int side, float hitX, float hitY, float hitZ, int meta)
    {
        byte orientation = 0;

        switch (side)
        {
        case 0:
        case 1:
            orientation = 0;
            break;
        case 2:
        case 3:
            orientation = 8;
            break;
        case 4:
        case 5:
            orientation = 4;
            break;
        }
        return 0 | orientation;
    }
}