/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 *
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.planets.siriusb.blocks;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;
import stevekung.mods.moreplanets.core.MorePlanetsCore;

public class BlockSiriusBMagmaRock extends Block
{
    public BlockSiriusBMagmaRock(String name)
    {
        super(Material.rock);
        this.setBlockName(name);
        this.setHardness(3.0F);
        this.setLightLevel(1.0F);
        this.setBlockTextureName("siriusb:magma_rock");
    }

    @Override
    public CreativeTabs getCreativeTabToDisplayOn()
    {
        return MorePlanetsCore.mpBlocksTab;
    }

    @Override
    public AxisAlignedBB getCollisionBoundingBoxFromPool(World world, int par2, int par3, int par4)
    {
        float f = 0.1F;
        return AxisAlignedBB.getBoundingBox(par2, par3, par4, par2 + 1, par3 + 1 - f, par4 + 1);
    }

    @Override
    public boolean isFireSource(World world, int x, int y, int z, ForgeDirection side)
    {
        if (side == ForgeDirection.UP)
        {
            return true;
        }
        return super.isFireSource(world, x, y, z, side);
    }

    @Override
    public void onEntityCollidedWithBlock(World world, int x, int y, int z, Entity entity)
    {
        if (entity instanceof EntityPlayer && ((EntityPlayer)entity).capabilities.isCreativeMode)
        {
            return;
        }
        entity.setFire(10);
        entity.motionX *= 0.5D;
        entity.motionZ *= 0.5D;
    }

    @Override
    public void randomDisplayTick(World world, int x, int y, int z, Random random)
    {
        if (random.nextInt(1) == 0)
        {
            world.spawnParticle("smoke", x + random.nextFloat(), y + 1.1F, z + random.nextFloat(), 0.0D, 0.0D, 0.0D);
        }
    }

    @Override
    public void harvestBlock(World world, EntityPlayer player, int x, int y, int z, int meta)
    {
        player.addStat(net.minecraft.stats.StatList.mineBlockStatArray[Block.getIdFromBlock(this)], 1);
        player.addExhaustion(0.025F);
        this.dropBlockAsItem(world, x, y, z, meta, 0);

        if (world.rand.nextInt(20) == 0)
        {
            world.setBlock(x, y, z, Blocks.flowing_lava);
        }
    }
}