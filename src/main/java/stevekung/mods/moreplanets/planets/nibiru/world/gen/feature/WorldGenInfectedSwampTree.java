package stevekung.mods.moreplanets.planets.nibiru.world.gen.feature;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.BlockVine;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;
import stevekung.mods.moreplanets.init.MPBlocks;

public class WorldGenInfectedSwampTree extends WorldGenAbstractTree
{
    private boolean genLeaves;

    public WorldGenInfectedSwampTree(boolean genLeaves)
    {
        super(false);
        this.genLeaves = genLeaves;
    }

    @Override
    public boolean generate(World world, Random rand, BlockPos pos)
    {
        int i;
        for (i = rand.nextInt(4) + 5; world.getBlockState(pos.down()).getBlock() == MPBlocks.INFECTED_WATER_FLUID_BLOCK; pos = pos.down()) {}
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
                    k = 3;
                }

                BlockPos.MutableBlockPos blockpos$mutableblockpos = new BlockPos.MutableBlockPos();

                for (int l = pos.getX() - k; l <= pos.getX() + k && flag; ++l)
                {
                    for (int i1 = pos.getZ() - k; i1 <= pos.getZ() + k && flag; ++i1)
                    {
                        if (j >= 0 && j < 256)
                        {
                            Block block = world.getBlockState(blockpos$mutableblockpos.setPos(l, j, i1)).getBlock();

                            if (!block.isAir(world.getBlockState(blockpos$mutableblockpos.setPos(l, j, i1)), world, blockpos$mutableblockpos.setPos(l, j, i1)) && !block.isLeaves(world.getBlockState(blockpos$mutableblockpos.setPos(l, j, i1)), world, blockpos$mutableblockpos.setPos(l, j, i1)))
                            {
                                if (block != MPBlocks.INFECTED_WATER_FLUID_BLOCK)
                                {
                                    flag = false;
                                }
                                else if (j > pos.getY())
                                {
                                    flag = false;
                                }
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
                    block1.onPlantGrow(world.getBlockState(down), world, pos.down(),pos);

                    for (int l1 = pos.getY() - 3 + i; l1 <= pos.getY() + i; ++l1)
                    {
                        int k2 = l1 - (pos.getY() + i);
                        int i3 = 2 - k2 / 2;

                        for (int k3 = pos.getX() - i3; k3 <= pos.getX() + i3; ++k3)
                        {
                            int l3 = k3 - pos.getX();

                            for (int j1 = pos.getZ() - i3; j1 <= pos.getZ() + i3; ++j1)
                            {
                                int k1 = j1 - pos.getZ();

                                if (Math.abs(l3) != i3 || Math.abs(k1) != i3 || rand.nextInt(2) != 0 && k2 != 0)
                                {
                                    BlockPos blockpos = new BlockPos(k3, l1, j1);

                                    if (world.getBlockState(blockpos).getBlock().canBeReplacedByLeaves(world.getBlockState(blockpos), world, blockpos))
                                    {
                                        if (this.genLeaves)
                                        {
                                            this.setBlockAndNotifyAdequately(world, blockpos, MPBlocks.INFECTED_OAK_LEAVES.getDefaultState());
                                        }
                                    }
                                }
                            }
                        }
                    }

                    for (int i2 = 0; i2 < i; ++i2)
                    {
                        BlockPos upN = pos.up(i2);
                        Block block2 = world.getBlockState(upN).getBlock();

                        if (block2.isAir(world.getBlockState(upN), world, upN) || block2.isLeaves(world.getBlockState(upN), world, upN) || block2 == MPBlocks.INFECTED_WATER_FLUID_BLOCK)
                        {
                            this.setBlockAndNotifyAdequately(world, pos.up(i2), MPBlocks.INFECTED_OAK_LOG.getDefaultState());
                        }
                    }

                    for (int j2 = pos.getY() - 3 + i; j2 <= pos.getY() + i; ++j2)
                    {
                        int l2 = j2 - (pos.getY() + i);
                        int j3 = 2 - l2 / 2;
                        BlockPos.MutableBlockPos blockpos$mutableblockpos1 = new BlockPos.MutableBlockPos();

                        for (int i4 = pos.getX() - j3; i4 <= pos.getX() + j3; ++i4)
                        {
                            for (int j4 = pos.getZ() - j3; j4 <= pos.getZ() + j3; ++j4)
                            {
                                blockpos$mutableblockpos1.setPos(i4, j2, j4);

                                if (world.getBlockState(blockpos$mutableblockpos1).getBlock().isLeaves(world.getBlockState(blockpos$mutableblockpos1), world, blockpos$mutableblockpos1))
                                {
                                    BlockPos blockpos3 = blockpos$mutableblockpos1.west();
                                    BlockPos blockpos4 = blockpos$mutableblockpos1.east();
                                    BlockPos blockpos1 = blockpos$mutableblockpos1.north();
                                    BlockPos blockpos2 = blockpos$mutableblockpos1.south();

                                    if (rand.nextInt(4) == 0 && world.getBlockState(blockpos3).getBlock().isAir(world.getBlockState(blockpos3), world,blockpos3))
                                    {
                                        this.addVine(world, blockpos3, BlockVine.EAST);
                                    }
                                    if (rand.nextInt(4) == 0 && world.getBlockState(blockpos4).getBlock().isAir(world.getBlockState(blockpos4), world,blockpos4))
                                    {
                                        this.addVine(world, blockpos4, BlockVine.WEST);
                                    }
                                    if (rand.nextInt(4) == 0 && world.getBlockState(blockpos1).getBlock().isAir(world.getBlockState(blockpos1), world,blockpos1))
                                    {
                                        this.addVine(world, blockpos1, BlockVine.SOUTH);
                                    }
                                    if (rand.nextInt(4) == 0 && world.getBlockState(blockpos2).getBlock().isAir(world.getBlockState(blockpos2), world,blockpos2))
                                    {
                                        this.addVine(world, blockpos2, BlockVine.NORTH);
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

    private void addVine(World world, BlockPos pos, PropertyBool property)
    {
        IBlockState iblockstate = MPBlocks.INFECTED_VINES.getDefaultState().withProperty(property, Boolean.valueOf(true));
        this.setBlockAndNotifyAdequately(world, pos, iblockstate);
        int i = 4;

        for (pos = pos.down(); world.getBlockState(pos).getBlock().isAir(iblockstate, world, pos) && i > 0; --i)
        {
            this.setBlockAndNotifyAdequately(world, pos, iblockstate);
            pos = pos.down();
        }
    }
}