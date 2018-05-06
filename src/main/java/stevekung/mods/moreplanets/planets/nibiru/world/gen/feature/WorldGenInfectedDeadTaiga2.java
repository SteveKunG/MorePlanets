package stevekung.mods.moreplanets.planets.nibiru.world.gen.feature;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;
import stevekung.mods.moreplanets.init.MPBlocks;

public class WorldGenInfectedDeadTaiga2 extends WorldGenAbstractTree
{
    private boolean genLeaves;

    public WorldGenInfectedDeadTaiga2(boolean genLeaves)
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

        if (pos.getY() >= 1 && pos.getY() + i + 1 <= 256)
        {
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

                BlockPos.MutableBlockPos blockpos$mutableblockpos = new BlockPos.MutableBlockPos();

                for (int k1 = pos.getX() - j1; k1 <= pos.getX() + j1 && flag; ++k1)
                {
                    for (int l1 = pos.getZ() - j1; l1 <= pos.getZ() + j1 && flag; ++l1)
                    {
                        if (i1 >= 0 && i1 < 256)
                        {
                            Block block = world.getBlockState(blockpos$mutableblockpos.setPos(k1, i1, l1)).getBlock();

                            if (!block.isAir(world.getBlockState(blockpos$mutableblockpos.setPos(k1, i1, l1)), world, blockpos$mutableblockpos) && !block.isLeaves(world.getBlockState(blockpos$mutableblockpos.setPos(k1, i1, l1)), world, blockpos$mutableblockpos))
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
                Block block1 = world.getBlockState(down).getBlock();

                if (block1 == MPBlocks.INFECTED_GRASS_BLOCK || block1 == MPBlocks.INFECTED_DIRT || block1 == MPBlocks.INFECTED_FARMLAND && pos.getY() < 256 - i - 1)
                {
                    block1.onPlantGrow(world.getBlockState(down), world, down, pos);
                    int i3 = rand.nextInt(2);
                    int j3 = 1;
                    int k3 = 0;

                    for (int l3 = 0; l3 <= k; ++l3)
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

                                    if (world.getBlockState(blockpos).getBlock().canBeReplacedByLeaves(world.getBlockState(blockpos), world, blockpos))
                                    {
                                        if (this.genLeaves)
                                        {
                                            this.setBlockAndNotifyAdequately(world, blockpos, MPBlocks.INFECTED_OAK_LEAVES.getStateFromMeta(1));//TODO
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
                        Block block2 = world.getBlockState(upN).getBlock();

                        if (block2.isAir(world.getBlockState(upN), world, upN) || block2.isLeaves(world.getBlockState(upN), world, upN))
                        {
                            this.setBlockAndNotifyAdequately(world, pos.up(k4), MPBlocks.INFECTED_OAK_LOG.getStateFromMeta(1));//TODO
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