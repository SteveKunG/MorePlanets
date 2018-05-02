package stevekung.mods.moreplanets.module.planets.nibiru.world.gen.feature;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;
import stevekung.mods.moreplanets.module.planets.nibiru.blocks.NibiruBlocks;

public class WorldGenInfectedDeadSavannaTree extends WorldGenAbstractTree
{
    public WorldGenInfectedDeadSavannaTree()
    {
        super(false);
    }

    @Override
    public boolean generate(World world, Random rand, BlockPos pos)
    {
        int i = rand.nextInt(3) + rand.nextInt(3) + 5;
        boolean flag = true;

        if (pos.getY() >= 1 && pos.getY() + i + 1 <= 256)
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
                        if (j >= 0 && j < 256)
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
                BlockPos down = pos.down();
                Block block = world.getBlockState(down).getBlock();

                if ((block == NibiruBlocks.INFECTED_GRASS_BLOCK || block == NibiruBlocks.INFECTED_DIRT || block == NibiruBlocks.INFECTED_FARMLAND) && pos.getY() < 256 - i - 1)
                {
                    block.onPlantGrow(world.getBlockState(down), world, down, pos);
                    EnumFacing enumfacing = EnumFacing.Plane.HORIZONTAL.random(rand);
                    int k2 = i - rand.nextInt(4) - 1;
                    int l2 = 3 - rand.nextInt(3);
                    int i3 = pos.getX();
                    int j1 = pos.getZ();
                    int k1 = 0;

                    for (int l1 = 0; l1 < i; ++l1)
                    {
                        int i2 = pos.getY() + l1;

                        if (l1 >= k2 && l2 > 0)
                        {
                            i3 += enumfacing.getFrontOffsetX();
                            j1 += enumfacing.getFrontOffsetZ();
                            --l2;
                        }

                        BlockPos blockpos = new BlockPos(i3, i2, j1);
                        block = world.getBlockState(blockpos).getBlock();

                        if (block.isAir(world.getBlockState(blockpos), world, blockpos) || block.isLeaves(world.getBlockState(blockpos), world, blockpos))
                        {
                            this.placeLogAt(world, blockpos);
                            k1 = i2;
                        }
                    }

                    BlockPos blockpos2 = new BlockPos(i3, k1, j1);

                    for (int j3 = -3; j3 <= 3; ++j3)
                    {
                        for (int i4 = -3; i4 <= 3; ++i4)
                        {
                            if (Math.abs(j3) != 3 || Math.abs(i4) != 3)
                            {
                                this.placeLeafAt(world, blockpos2.add(j3, 0, i4));
                            }
                        }
                    }

                    blockpos2 = blockpos2.up();

                    for (int k3 = -1; k3 <= 1; ++k3)
                    {
                        for (int j4 = -1; j4 <= 1; ++j4)
                        {
                            this.placeLeafAt(world, blockpos2.add(k3, 0, j4));
                        }
                    }

                    this.placeLeafAt(world, blockpos2.east(2));
                    this.placeLeafAt(world, blockpos2.west(2));
                    this.placeLeafAt(world, blockpos2.south(2));
                    this.placeLeafAt(world, blockpos2.north(2));
                    i3 = pos.getX();
                    j1 = pos.getZ();
                    EnumFacing enumfacing1 = EnumFacing.Plane.HORIZONTAL.random(rand);

                    if (enumfacing1 != enumfacing)
                    {
                        int l3 = k2 - rand.nextInt(2) - 1;
                        int k4 = 1 + rand.nextInt(3);
                        k1 = 0;

                        for (int l4 = l3; l4 < i && k4 > 0; --k4)
                        {
                            if (l4 >= 1)
                            {
                                int j2 = pos.getY() + l4;
                                i3 += enumfacing1.getFrontOffsetX();
                                j1 += enumfacing1.getFrontOffsetZ();
                                BlockPos blockpos1 = new BlockPos(i3, j2, j1);
                                block = world.getBlockState(blockpos1).getBlock();

                                if (block.isAir(world.getBlockState(blockpos1), world, blockpos2) || block.isLeaves(world.getBlockState(blockpos1), world, blockpos2))
                                {
                                    this.placeLogAt(world, blockpos1);
                                    k1 = j2;
                                }
                            }
                            ++l4;
                        }

                        if (k1 > 0)
                        {
                            BlockPos blockpos3 = new BlockPos(i3, k1, j1);

                            for (int i5 = -2; i5 <= 2; ++i5)
                            {
                                for (int k5 = -2; k5 <= 2; ++k5)
                                {
                                    if (Math.abs(i5) != 2 || Math.abs(k5) != 2)
                                    {
                                        this.placeLeafAt(world, blockpos3.add(i5, 0, k5));
                                    }
                                }
                            }

                            blockpos3 = blockpos3.up();

                            for (int j5 = -1; j5 <= 1; ++j5)
                            {
                                for (int l5 = -1; l5 <= 1; ++l5)
                                {
                                    this.placeLeafAt(world, blockpos3.add(j5, 0, l5));
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

    private void placeLogAt(World world, BlockPos pos)
    {
        this.setBlockAndNotifyAdequately(world, pos, NibiruBlocks.INFECTED_OAK_LOG.getDefaultState());
    }

    private void placeLeafAt(World world, BlockPos pos)
    {
        Block block = world.getBlockState(pos).getBlock();

        if (block.isAir(world.getBlockState(pos), world, pos) || block.isLeaves(world.getBlockState(pos), world, pos))
        {
            this.setBlockAndNotifyAdequately(world, pos, Blocks.AIR.getDefaultState());
        }
    }
}