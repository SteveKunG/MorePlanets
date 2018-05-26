package stevekung.mods.moreplanets.planets.nibiru.world.gen.feature;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;
import stevekung.mods.moreplanets.init.MPBlocks;

public class WorldGenInfectedDeadSpruce extends WorldGenAbstractTree
{
    private boolean genLeaves;

    public WorldGenInfectedDeadSpruce(boolean genLeaves)
    {
        super(false);
        this.genLeaves = genLeaves;
    }

    @Override
    public boolean generate(World world, Random rand, BlockPos pos)
    {
        int i = rand.nextInt(4) + 6;
        int j = 1 + rand.nextInt(2);
        int k = i - j;
        int l = 2 + rand.nextInt(2);
        boolean flag = true;

        if (pos.getY() >= 1 && pos.getY() + i + 1 <= world.getHeight())
        {
            for (int i1 = pos.getY(); i1 <= pos.getY() + 1 + i && flag; ++i1)
            {
                int j1;

                if (i1 - pos.getY() < j)
                {
                    j1 = 0;
                }
                else
                {
                    j1 = l;
                }

                BlockPos.MutableBlockPos mutablePos = new BlockPos.MutableBlockPos();

                for (int k1 = pos.getX() - j1; k1 <= pos.getX() + j1 && flag; ++k1)
                {
                    for (int l1 = pos.getZ() - j1; l1 <= pos.getZ() + j1 && flag; ++l1)
                    {
                        if (i1 >= 0 && i1 < world.getHeight())
                        {
                            IBlockState state = world.getBlockState(mutablePos.setPos(k1, i1, l1));

                            if (!state.getBlock().isAir(state, world, mutablePos.setPos(k1, i1, l1)) && !state.getBlock().isLeaves(state, world, mutablePos.setPos(k1, i1, l1)))
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
                BlockPos down = pos.down();
                IBlockState state = world.getBlockState(down);
                Block block = state.getBlock();
                boolean isSoil = block == MPBlocks.INFECTED_GRASS_BLOCK || block == MPBlocks.INFECTED_DIRT || block == MPBlocks.INFECTED_COARSE_DIRT || block == MPBlocks.INFECTED_FARMLAND;

                if (isSoil && pos.getY() < world.getHeight() - i - 1)
                {
                    block.onPlantGrow(state, world, down, pos);
                    int i3 = rand.nextInt(2);
                    int j3 = 1;
                    int k3 = 0;

                    for (int l3 = 0; l3 <= k; ++l3)
                    {
                        if (this.genLeaves)
                        {
                            int j4 = pos.getY() + i - l3;

                            for (int i2 = pos.getX() - i3; i2 <= pos.getX() + i3; ++i2)
                            {
                                int j2 = i2 - pos.getX();

                                for (int k2 = pos.getZ() - i3; k2 <= pos.getZ() + i3; ++k2)
                                {
                                    int l2 = k2 - pos.getZ();

                                    if (Math.abs(j2) != i3 || Math.abs(l2) != i3 || i3 <= 0)
                                    {
                                        BlockPos blockpos = new BlockPos(i2, j4, k2);
                                        state = world.getBlockState(blockpos);

                                        if (state.getBlock().canBeReplacedByLeaves(state, world, blockpos))
                                        {
                                            this.setBlockAndNotifyAdequately(world, blockpos, MPBlocks.INFECTED_SPRUCE_LEAVES.getDefaultState());
                                        }
                                    }
                                }
                            }
                        }

                        if (i3 >= j3)
                        {
                            i3 = k3;
                            k3 = 1;
                            ++j3;

                            if (j3 > l)
                            {
                                j3 = l;
                            }
                        }
                        else
                        {
                            ++i3;
                        }
                    }

                    int i4 = rand.nextInt(3);

                    for (int k4 = 0; k4 < i - i4; ++k4)
                    {
                        BlockPos upN = pos.up(k4);
                        state = world.getBlockState(upN);

                        if (state.getBlock().isAir(state, world, upN) || state.getBlock().isLeaves(state, world, upN))
                        {
                            this.setBlockAndNotifyAdequately(world, pos.up(k4), MPBlocks.INFECTED_SPRUCE_LOG.getDefaultState());
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
}