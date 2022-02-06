package stevekung.mods.moreplanets.planets.fronos.world.gen.feature;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import stevekung.mods.moreplanets.init.MPBlocks;
import stevekung.mods.stevekunglib.utils.BlockStateProperty;
import stevekung.mods.stevekunglib.world.gen.WorldGenAbstractTree;

public class WorldGenOscaleaTrees extends WorldGenAbstractTree
{
    public WorldGenOscaleaTrees()
    {
        super(MPBlocks.OSCALEA_LOG.getDefaultState(), MPBlocks.OSCALEA_LEAVES.getDefaultState());
    }

    @Override
    protected int getTreeHeight(Random rand)
    {
        return super.getTreeHeight(rand) + rand.nextInt(3);
    }

    @Override
    public boolean generate(World world, Random rand, BlockPos pos)
    {
        int i = this.getTreeHeight(rand);
        boolean flag = true;

        if (pos.getY() >= 1 && pos.getY() + i + 1 <= world.getHeight())
        {
            for (int j = pos.getY(); j <= pos.getY() + 1 + i; ++j)
            {
                int k = 1;

                if (j == pos.getY())
                {
                    k = 0;
                }

                if (j >= pos.getY() + 1 + i - 2)
                {
                    k = 2;
                }

                BlockPos.MutableBlockPos blockpos$mutableblockpos = new BlockPos.MutableBlockPos();

                for (int l = pos.getX() - k; l <= pos.getX() + k && flag; ++l)
                {
                    for (int i1 = pos.getZ() - k; i1 <= pos.getZ() + k && flag; ++i1)
                    {
                        if (j >= 0 && j < world.getHeight())
                        {
                            if (!this.isReplaceable(world,blockpos$mutableblockpos.setPos(l, j, i1)))
                            {
                                flag = false;
                            }
                        }
                        else
                        {
                            flag = false;
                        }
                    }
                }
            }

            if (!flag)
            {
                return false;
            }
            else
            {
                IBlockState state = world.getBlockState(pos.down());

                if (this.isSoil(state.getBlock()) && pos.getY() < world.getHeight() - i - 1)
                {
                    state.getBlock().onPlantGrow(state, world, pos.down(), pos);

                    if (this.genLeaves)
                    {
                        for (int i3 = pos.getY() - 2 + i; i3 <= pos.getY() + i; ++i3)// leaves to below
                        {
                            int i4 = i3 - (pos.getY() + i);
                            int j1 = rand.nextBoolean() ? 2 + i4 / 2 : 1 - i4 / 2;//size

                            for (int k1 = pos.getX() - j1; k1 <= pos.getX() + j1; ++k1)
                            {
                                int l1 = k1 - pos.getX();

                                for (int i2 = pos.getZ() - j1; i2 <= pos.getZ() + j1; ++i2)
                                {
                                    int j2 = i2 - pos.getZ();

                                    if (Math.abs(l1) != j1 || Math.abs(j2) != j1 || rand.nextInt(2) != 0 && i4 != 0)
                                    {
                                        BlockPos blockpos = new BlockPos(k1, i3, i2);
                                        state = world.getBlockState(blockpos);

                                        if (state.getBlock().isAir(state, world, blockpos) || state.getBlock().isLeaves(state, world, blockpos) || state.getMaterial() == Material.VINE)
                                        {
                                            this.setBlockAndNotifyAdequately(world, blockpos, this.leaves);
                                        }
                                    }
                                }
                            }
                        }
                    }

                    for (int j3 = 0; j3 < i; ++j3)
                    {
                        BlockPos upN = pos.up(j3);
                        state = world.getBlockState(upN);

                        if (state.getBlock().isAir(state, world, upN) || state.getBlock().isLeaves(state, world, upN) || state.getMaterial() == Material.VINE)
                        {
                            this.setBlockAndNotifyAdequately(world, pos.up(j3), this.log);
                        }

                        if (rand.nextInt(5) == 0 && j3 == 0)
                        {
                            for (int l3 = 0; l3 < 2; ++l3)
                            {
                                for (EnumFacing enumfacing : EnumFacing.Plane.HORIZONTAL)
                                {
                                    if (rand.nextInt(4 - l3) == 0)
                                    {
                                        EnumFacing enumfacing1 = enumfacing.getOpposite();
                                        BlockPos pos3 = pos.add(enumfacing1.getXOffset(), j3 + l3, enumfacing1.getZOffset());
                                        state = world.getBlockState(pos3);

                                        if (state.getBlock().isAir(state, world, pos3))
                                        {
                                            this.setBlockAndNotifyAdequately(world, pos3, MPBlocks.FRONOS_MUSHROOM.getDefaultState().withProperty(BlockStateProperty.FACING_HORIZON, enumfacing));
                                        }
                                    }
                                }
                            }
                        }
                    }
                    return true;
                }
                else
                {
                    return false;
                }
            }
        }
        else
        {
            return false;
        }
    }

    @Override
    protected boolean isSoil(Block block)
    {
        return block == MPBlocks.FRONOS_GRASS_BLOCK || block == MPBlocks.FRONOS_DIRT || block == MPBlocks.FRONOS_COARSE_DIRT || block == MPBlocks.FRONOS_FARMLAND;
    }
}