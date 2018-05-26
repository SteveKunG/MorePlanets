package stevekung.mods.moreplanets.planets.nibiru.world.gen.feature;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;
import stevekung.mods.moreplanets.init.MPBlocks;

public class WorldGenInfectedDeadPine extends WorldGenAbstractTree
{
    private boolean genLeaves;

    public WorldGenInfectedDeadPine(boolean genLeaves)
    {
        super(false);
        this.genLeaves = genLeaves;
    }

    @Override
    public boolean generate(World world, Random rand, BlockPos pos)
    {
        int i = rand.nextInt(5) + 7;
        int j = i - rand.nextInt(2) - 3;
        int k = i - j;
        int l = 1 + rand.nextInt(k + 1);

        if (pos.getY() >= 1 && pos.getY() + i + 1 <= 256)
        {
            boolean flag = true;

            for (int i1 = pos.getY(); i1 <= pos.getY() + 1 + i && flag; ++i1)
            {
                int j1 = 1;

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
                        if (i1 >= 0 && i1 < 256)
                        {
                            if (!this.isReplaceable(world, mutablePos.setPos(k1, i1, l1)))
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

                if (isSoil && pos.getY() < 256 - i - 1)
                {
                    block.onPlantGrow(state, world, down, pos);
                    int k2 = 0;

                    for (int l2 = pos.getY() + i; l2 >= pos.getY() + j; --l2)
                    {
                        if (this.genLeaves)
                        {
                            for (int j3 = pos.getX() - k2; j3 <= pos.getX() + k2; ++j3)
                            {
                                int k3 = j3 - pos.getX();

                                for (int i2 = pos.getZ() - k2; i2 <= pos.getZ() + k2; ++i2)
                                {
                                    int j2 = i2 - pos.getZ();

                                    if (Math.abs(k3) != k2 || Math.abs(j2) != k2 || k2 <= 0)
                                    {
                                        BlockPos blockpos = new BlockPos(j3, l2, i2);
                                        state = world.getBlockState(blockpos);

                                        if (state.getBlock().canBeReplacedByLeaves(state, world, blockpos))
                                        {
                                            this.setBlockAndNotifyAdequately(world, blockpos, MPBlocks.INFECTED_SPRUCE_LEAVES.getDefaultState());
                                        }
                                    }
                                }
                            }
                        }

                        if (k2 >= 1 && l2 == pos.getY() + j + 1)
                        {
                            --k2;
                        }
                        else if (k2 < l)
                        {
                            ++k2;
                        }
                    }

                    for (int i3 = 0; i3 < i - 1; ++i3)
                    {
                        BlockPos upN = pos.up(i3);
                        state = world.getBlockState(upN);

                        if (state.getBlock().isAir(state, world, upN) || state.getBlock().isLeaves(state, world, upN))
                        {
                            this.setBlockAndNotifyAdequately(world, pos.up(i3), MPBlocks.INFECTED_SPRUCE_LOG.getDefaultState());
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