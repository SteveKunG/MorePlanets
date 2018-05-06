package stevekung.mods.moreplanets.planets.nibiru.world.gen.feature;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;
import stevekung.mods.moreplanets.init.MPBlocks;

public class WorldGenInfectedDeadTaiga1 extends WorldGenAbstractTree
{
    private boolean genLeaves;

    public WorldGenInfectedDeadTaiga1(boolean genLeaves)
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
                            if (!this.isReplaceable(world,blockpos$mutableblockpos.setPos(k1, i1, l1)))
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
                Block block = world.getBlockState(down).getBlock();

                if (block == MPBlocks.INFECTED_GRASS_BLOCK || block == MPBlocks.INFECTED_DIRT || block == MPBlocks.INFECTED_FARMLAND && pos.getY() < 256 - i - 1)
                {
                    block.onPlantGrow(world.getBlockState(down), world, down, pos);
                    int k2 = 0;

                    for (int l2 = pos.getY() + i; l2 >= pos.getY() + j; --l2)
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
                        Block block1 = world.getBlockState(upN).getBlock();

                        if (block1.isAir(world.getBlockState(upN), world, upN) || block1.isLeaves(world.getBlockState(upN), world, upN))
                        {
                            this.setBlockAndNotifyAdequately(world, pos.up(i3), MPBlocks.INFECTED_OAK_LOG.getStateFromMeta(1));//TODO
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