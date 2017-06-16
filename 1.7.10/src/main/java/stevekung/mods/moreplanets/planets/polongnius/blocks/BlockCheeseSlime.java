/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 *
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.planets.polongnius.blocks;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.BlockBreakable;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.init.Blocks;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import stevekung.mods.moreplanets.core.MorePlanetsCore;

public class BlockCheeseSlime extends BlockBreakable
{
    public BlockCheeseSlime(String name)
    {
        super("polongnius:cheese_slime", Material.glass, false);
        this.setStepSound(MorePlanetsCore.soundTypeSlime);
        this.setHardness(0.2F);
        this.setBlockName(name);
    }

    @Override
    public CreativeTabs getCreativeTabToDisplayOn()
    {
        return MorePlanetsCore.mpBlocksTab;
    }

    @Override
    public int getRenderType()
    {
        return MorePlanetsCore.proxy.getBlockRender(this);
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
    @SideOnly(Side.CLIENT)
    public int getRenderBlockPass()
    {
        return 1;
    }

    @Override
    public AxisAlignedBB getCollisionBoundingBoxFromPool(World world, int par2, int par3, int par4)
    {
        float dy = 0.75F;
        Block block = world.getBlock(par2, par3 - 1, par4);
        AxisAlignedBB box = null;

        if (block != this && block != Blocks.air)
        {
            box = AxisAlignedBB.getBoundingBox(par2, par3, par4, par2 + 1, par3 + 1 - dy, par4 + 1);
        }
        return box;
    }

    @Override
    public void onEntityCollidedWithBlock(World world, int x, int y, int z, Entity entity)
    {
        float m = 1.05F;
        entity.fallDistance = 0.0F;

        if (world.getBlock(x + 1, y, z) == this && world.getBlock(x - 1, y, z) == this && world.getBlock(x, y, z + 1) == this && world.getBlock(x, y, z - 1) == this)
        {
            m = 1.55F;
        }

        entity.fallDistance = 0.0F;

        if (entity.motionY < -0.15)
        {
            entity.motionX *= m;
            entity.motionY *= -m;
            entity.motionZ *= m;
        }
        else
        {
            entity.motionX *= 0.5D;
            entity.motionY *= 0.5D;
            entity.motionZ *= 0.5D;
        }
    }

    @Override
    @SideOnly(Side.CLIENT)
    public boolean shouldSideBeRendered(IBlockAccess par1IBlockAccess, int par2, int par3, int par4, int par5)
    {
        return true;
    }
}