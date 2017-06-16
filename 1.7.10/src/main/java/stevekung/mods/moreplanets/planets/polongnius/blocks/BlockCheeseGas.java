/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 *
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.planets.polongnius.blocks;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.BlockBreakable;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import stevekung.mods.moreplanets.core.MorePlanetsCore;

public class BlockCheeseGas extends BlockBreakable
{
    int tick;

    public BlockCheeseGas(String name)
    {
        super("polongnius:cheese_gas_block", Material.cloth, false);
        this.setStepSound(soundTypeCloth);
        this.setHardness(0.25F);
        this.setResistance(0.5F);
        this.setBlockName(name);
    }

    @Override
    public CreativeTabs getCreativeTabToDisplayOn()
    {
        return MorePlanetsCore.mpBlocksTab;
    }

    @Override
    public void onEntityCollidedWithBlock(World world, int x, int y, int z, Entity entity)
    {
        this.tick++;

        if (entity instanceof EntityLivingBase)
        {
            if (!((EntityLivingBase)entity).isSneaking())
            {
                entity.motionX *= 1.0F;
                entity.motionY = 0.15F;
                entity.motionZ *= 1.0F;
                entity.fallDistance = 0.0F;
            }
            else
            {
                entity.motionX *= 1.0F;
                entity.motionY = -0.05F;
                entity.motionZ *= 1.0F;
                entity.fallDistance = 0.0F;
            }
        }
    }

    @Override
    public AxisAlignedBB getCollisionBoundingBoxFromPool(World world, int var2, int var3, int var4)
    {
        return null;
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
    @SideOnly(Side.CLIENT)
    public boolean shouldSideBeRendered(IBlockAccess world, int x, int y, int z, int side)
    {
        return super.shouldSideBeRendered(world, x, y, z, 1 - side);
    }
}