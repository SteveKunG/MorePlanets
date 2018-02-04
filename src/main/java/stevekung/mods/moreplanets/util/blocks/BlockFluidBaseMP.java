package stevekung.mods.moreplanets.util.blocks;

import java.util.Iterator;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fluids.BlockFluidClassic;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import stevekung.mods.moreplanets.core.MorePlanetsCore;
import stevekung.mods.moreplanets.util.EnumParticleTypesMP;

public abstract class BlockFluidBaseMP extends BlockFluidClassic implements ISingleBlockRender
{
    private int adjacentSourceBlocks;

    public BlockFluidBaseMP(Fluid fluid)
    {
        super(fluid, Material.WATER);
        this.setHardness(100.0F);
    }

    public BlockFluidBaseMP(Fluid fluid, Material material)
    {
        super(fluid, material);
        this.setHardness(100.0F);
    }

    @Override
    public boolean isPassable(IBlockAccess world, BlockPos pos)
    {
        return this.blockMaterial != Material.LAVA;
    }

    @Override
    public boolean canDisplace(IBlockAccess world, BlockPos pos)
    {
        if (world.getBlockState(pos).getMaterial().isLiquid())
        {
            return false;
        }
        if (world.getBlockState(pos).getMaterial() == Material.LAVA)
        {
            return false;
        }
        return super.canDisplace(world, pos);
    }

    @Override
    public boolean displaceIfPossible(World world, BlockPos pos)
    {
        if (world.getBlockState(pos).getMaterial().isLiquid())
        {
            return false;
        }
        if (world.getBlockState(pos).getMaterial() == Material.LAVA)
        {
            return false;
        }
        return super.displaceIfPossible(world, pos);
    }

    @Override
    public void updateTick(World world, BlockPos pos, IBlockState state, Random rand)
    {
        super.updateTick(world, pos, state, rand);

        if (this.isInfinite())
        {
            int i = state.getValue(LEVEL).intValue();
            byte b0 = 1;

            int j = this.tickRate(world);
            int i1;

            if (i > 0)
            {
                int k = -100;
                this.adjacentSourceBlocks = 0;
                EnumFacing enumfacing;

                for (Iterator iterator = EnumFacing.Plane.HORIZONTAL.iterator(); iterator.hasNext(); k = this.checkAdjacentBlock(world, pos.offset(enumfacing), k))
                {
                    enumfacing = (EnumFacing)iterator.next();
                }

                int l = k + b0;

                if (l >= 8 || k < 0)
                {
                    l = -1;
                }

                if (this.getLevel(world, pos.up()) >= 0)
                {
                    i1 = this.getLevel(world, pos.up());

                    if (i1 >= 8)
                    {
                        l = i1;
                    }
                    else
                    {
                        l = i1 + 8;
                    }
                }

                if (this.adjacentSourceBlocks >= 2)
                {
                    IBlockState iblockstate2 = world.getBlockState(pos.down());

                    if (iblockstate2.getMaterial().isSolid())
                    {
                        l = 0;
                    }
                    else if (iblockstate2.getMaterial() == this.blockMaterial && iblockstate2.getValue(LEVEL).intValue() == 0)
                    {
                        l = 0;
                    }
                }

                if (l == i)
                {
                    this.placeStaticBlock(world, pos, state);
                }
                else
                {
                    i = l;

                    if (l < 0)
                    {
                        world.setBlockToAir(pos);
                    }
                    else
                    {
                        state = state.withProperty(LEVEL, Integer.valueOf(l));
                        world.setBlockState(pos, state, 2);
                        world.scheduleUpdate(pos, this, j);
                        world.notifyNeighborsOfStateChange(pos, this, false);
                    }
                }
            }
            else
            {
                this.placeStaticBlock(world, pos, state);
            }
        }
    }

    @Override
    @SideOnly(Side.CLIENT)
    public boolean shouldSideBeRendered(IBlockState state, IBlockAccess world, BlockPos pos, EnumFacing facing)
    {
        IBlockState iblockstate = world.getBlockState(pos.offset(facing));
        Block block = iblockstate.getBlock();

        if (block == this)
        {
            return false;
        }
        if (block != this && block instanceof BlockFluidBaseMP)
        {
            return true;
        }
        return super.shouldSideBeRendered(state, world, pos, facing);
    }

    private void placeStaticBlock(World world, BlockPos pos, IBlockState state)
    {
        world.setBlockState(pos, this.getDefaultState().withProperty(LEVEL, state.getValue(LEVEL)), 2);
    }

    private int checkAdjacentBlock(World world, BlockPos pos, int level)
    {
        int j = this.getLevel(world, pos);

        if (j < 0)
        {
            return level;
        }
        else
        {
            if (j == 0)
            {
                ++this.adjacentSourceBlocks;
            }
            if (j >= 8)
            {
                j = 0;
            }
            return level >= 0 && j >= level ? level : j;
        }
    }

    private int getLevel(IBlockAccess world, BlockPos pos)
    {
        return world.getBlockState(pos).getMaterial() == this.blockMaterial ? world.getBlockState(pos).getValue(LEVEL).intValue() : -1;
    }

    protected void triggerMixEffects(World world, BlockPos pos)
    {
        world.playSound((EntityPlayer)null, pos, SoundEvents.BLOCK_LAVA_EXTINGUISH, SoundCategory.BLOCKS, 0.5F, 2.6F + (world.rand.nextFloat() - world.rand.nextFloat()) * 0.8F);

        for (int i = 0; i < 8; ++i)
        {
            MorePlanetsCore.PROXY.spawnParticle(EnumParticleTypesMP.MC_SMOKE_LARGE, pos.getX() + Math.random(), pos.getY() + 1.2D, pos.getZ() + Math.random());
        }
    }

    protected abstract boolean isInfinite();
}