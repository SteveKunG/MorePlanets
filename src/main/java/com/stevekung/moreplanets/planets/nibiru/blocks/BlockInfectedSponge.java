package com.stevekung.moreplanets.planets.nibiru.blocks;

import java.util.*;

import com.stevekung.lib.utils.enums.CachedEnum;
import com.stevekung.moreplanets.core.MorePlanetsMod;
import com.stevekung.moreplanets.init.MPBlocks;
import com.stevekung.moreplanets.utils.EnumParticleTypesMP;
import com.stevekung.moreplanets.utils.blocks.BlockBaseMP;

import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.Tuple;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BlockInfectedSponge extends BlockBaseMP
{
    private final boolean isWet;

    public BlockInfectedSponge(String name, boolean isWet)
    {
        super(name, Material.SPONGE);
        this.setHardness(0.6F);
        this.setSoundType(SoundType.PLANT);
        this.isWet = isWet;
    }

    @Override
    public void onBlockAdded(World world, BlockPos pos, IBlockState state)
    {
        this.tryAbsorb(world, pos);
    }

    @Override
    public void neighborChanged(IBlockState state, World world, BlockPos pos, Block block, BlockPos fromPos)
    {
        this.tryAbsorb(world, pos);
    }

    private void tryAbsorb(World world, BlockPos pos)
    {
        if (!this.isWet && this.absorb(world, pos))
        {
            world.setBlockState(pos, MPBlocks.INFECTED_WET_SPONGE.getDefaultState(), 2);
            world.playEvent(2001, pos, Block.getIdFromBlock(MPBlocks.INFECTED_WATER_FLUID_BLOCK));
        }
    }

    private boolean absorb(World world, BlockPos pos)
    {
        Queue<Tuple<BlockPos, Integer>> queue = new LinkedList<>();
        List<BlockPos> list = new ArrayList<>();
        queue.add(new Tuple<>(pos, 0));
        int i = 0;

        while (!queue.isEmpty())
        {
            Tuple<BlockPos, Integer> tuple = queue.poll();
            BlockPos blockpos = tuple.getFirst();
            int j = tuple.getSecond();

            for (EnumFacing facing : CachedEnum.facingValues)
            {
                BlockPos blockpos1 = blockpos.offset(facing);

                if (world.getBlockState(blockpos1).getBlock() == MPBlocks.INFECTED_WATER_FLUID_BLOCK)
                {
                    world.setBlockState(blockpos1, Blocks.AIR.getDefaultState(), 2);
                    list.add(blockpos1);
                    ++i;

                    if (j < 6)
                    {
                        queue.add(new Tuple<>(blockpos1, j + 1));
                    }
                }
            }

            if (i > 64)
            {
                break;
            }
        }

        for (BlockPos blockpos2 : list)
        {
            world.notifyNeighborsOfStateChange(blockpos2, Blocks.AIR, false);
        }
        return i > 0;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void randomDisplayTick(IBlockState state, World world, BlockPos pos, Random rand)
    {
        if (this.isWet)
        {
            EnumFacing enumfacing = EnumFacing.random(rand);

            if (enumfacing != EnumFacing.UP && !world.getBlockState(pos.offset(enumfacing)).isSideSolid(world, pos.offset(enumfacing), EnumFacing.UP))
            {
                double d0 = pos.getX();
                double d1 = pos.getY();
                double d2 = pos.getZ();

                if (enumfacing == EnumFacing.DOWN)
                {
                    d1 -= 0.05D;
                    d0 += rand.nextDouble();
                    d2 += rand.nextDouble();
                }
                else
                {
                    d1 += rand.nextDouble() * 0.8D;

                    if (enumfacing.getAxis() == EnumFacing.Axis.X)
                    {
                        d2 += rand.nextDouble();

                        if (enumfacing == EnumFacing.EAST)
                        {
                            ++d0;
                        }
                        else
                        {
                            d0 += 0.05D;
                        }
                    }
                    else
                    {
                        d0 += rand.nextDouble();

                        if (enumfacing == EnumFacing.SOUTH)
                        {
                            ++d2;
                        }
                        else
                        {
                            d2 += 0.05D;
                        }
                    }
                }
                MorePlanetsMod.PROXY.spawnParticle(EnumParticleTypesMP.INFECTED_WATER_DRIP, d0, d1, d2);
            }
        }
    }
}