package stevekung.mods.moreplanets.planets.nibiru.world.gen.feature;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.BlockVine;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;
import stevekung.mods.moreplanets.planets.nibiru.blocks.NibiruBlocks;

public class WorldGenInfectedJungleTrees extends WorldGenAbstractTree
{
    private int minTreeHeight;
    private boolean genLeaves;
    private boolean vinesGrow;

    public WorldGenInfectedJungleTrees(boolean genLeaves, int minTreeHeight, boolean vinesGrow)
    {
        super(false);
        this.genLeaves = genLeaves;
        this.minTreeHeight = minTreeHeight;
        this.vinesGrow = vinesGrow;
    }

    @Override
    public boolean generate(World world, Random rand, BlockPos pos)
    {
        int i = rand.nextInt(3) + this.minTreeHeight;
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
                Block block1 = world.getBlockState(down).getBlock();

                if (block1 == NibiruBlocks.INFECTED_GRASS_BLOCK || block1 == NibiruBlocks.INFECTED_DIRT || block1 == NibiruBlocks.INFECTED_FARMLAND && pos.getY() < 256 - i - 1)
                {
                    block1.onPlantGrow(world.getBlockState(down), world, down, pos);
                    int k2 = 3;
                    int l2 = 0;

                    for (int i3 = pos.getY() - k2 + i; i3 <= pos.getY() + i; ++i3)
                    {
                        int i4 = i3 - (pos.getY() + i);
                        int j1 = l2 + 1 - i4 / 2;

                        for (int k1 = pos.getX() - j1; k1 <= pos.getX() + j1; ++k1)
                        {
                            int l1 = k1 - pos.getX();

                            for (int i2 = pos.getZ() - j1; i2 <= pos.getZ() + j1; ++i2)
                            {
                                int j2 = i2 - pos.getZ();

                                if (Math.abs(l1) != j1 || Math.abs(j2) != j1 || rand.nextInt(2) != 0 && i4 != 0)
                                {
                                    BlockPos blockpos = new BlockPos(k1, i3, i2);
                                    Block block = world.getBlockState(blockpos).getBlock();

                                    if (block.isAir(world.getBlockState(blockpos), world, blockpos) || block.isLeaves(world.getBlockState(blockpos), world, blockpos) || world.getBlockState(blockpos).getMaterial() == Material.VINE)
                                    {
                                        if (this.genLeaves)
                                        {
                                            this.setBlockAndNotifyAdequately(world, blockpos, NibiruBlocks.INFECTED_JUNGLE_LEAVES.getDefaultState());
                                        }
                                    }
                                }
                            }
                        }
                    }

                    for (int j3 = 0; j3 < i; ++j3)
                    {
                        BlockPos upN = pos.up(j3);
                        Block block2 = world.getBlockState(upN).getBlock();

                        if (block2.isAir(world.getBlockState(upN), world, upN) || block2.isLeaves(world.getBlockState(upN), world, upN) || world.getBlockState(upN).getMaterial() == Material.VINE)
                        {
                            this.setBlockAndNotifyAdequately(world, pos.up(j3), NibiruBlocks.INFECTED_JUNGLE_LOG.getDefaultState());

                            if (this.vinesGrow && j3 > 0)
                            {
                                if (rand.nextInt(3) > 0 && world.isAirBlock(pos.add(-1, j3, 0)))
                                {
                                    this.addVine(world, pos.add(-1, j3, 0), BlockVine.EAST);
                                }
                                if (rand.nextInt(3) > 0 && world.isAirBlock(pos.add(1, j3, 0)))
                                {
                                    this.addVine(world, pos.add(1, j3, 0), BlockVine.WEST);
                                }
                                if (rand.nextInt(3) > 0 && world.isAirBlock(pos.add(0, j3, -1)))
                                {
                                    this.addVine(world, pos.add(0, j3, -1), BlockVine.SOUTH);
                                }
                                if (rand.nextInt(3) > 0 && world.isAirBlock(pos.add(0, j3, 1)))
                                {
                                    this.addVine(world, pos.add(0, j3, 1), BlockVine.NORTH);
                                }
                            }
                        }
                    }

                    if (this.vinesGrow)
                    {
                        for (int k3 = pos.getY() - 3 + i; k3 <= pos.getY() + i; ++k3)
                        {
                            int j4 = k3 - (pos.getY() + i);
                            int k4 = 2 - j4 / 2;
                            BlockPos.MutableBlockPos blockpos$mutableblockpos1 = new BlockPos.MutableBlockPos();

                            for (int l4 = pos.getX() - k4; l4 <= pos.getX() + k4; ++l4)
                            {
                                for (int i5 = pos.getZ() - k4; i5 <= pos.getZ() + k4; ++i5)
                                {
                                    blockpos$mutableblockpos1.setPos(l4, k3, i5);

                                    if (world.getBlockState(blockpos$mutableblockpos1).getBlock().isLeaves(world.getBlockState(blockpos$mutableblockpos1), world, blockpos$mutableblockpos1))
                                    {
                                        BlockPos blockpos2 = blockpos$mutableblockpos1.west();
                                        BlockPos blockpos3 = blockpos$mutableblockpos1.east();
                                        BlockPos blockpos4 = blockpos$mutableblockpos1.north();
                                        BlockPos blockpos1 = blockpos$mutableblockpos1.south();

                                        if (rand.nextInt(4) == 0 && world.getBlockState(blockpos2).getBlock().isAir(world.getBlockState(blockpos2), world, blockpos2))
                                        {
                                            this.addHangingVine(world, blockpos2, BlockVine.EAST);
                                        }
                                        if (rand.nextInt(4) == 0 && world.getBlockState(blockpos3).getBlock().isAir(world.getBlockState(blockpos3), world, blockpos3))
                                        {
                                            this.addHangingVine(world, blockpos3, BlockVine.WEST);
                                        }
                                        if (rand.nextInt(4) == 0 && world.getBlockState(blockpos4).getBlock().isAir(world.getBlockState(blockpos4), world, blockpos4))
                                        {
                                            this.addHangingVine(world, blockpos4, BlockVine.SOUTH);
                                        }
                                        if (rand.nextInt(4) == 0 && world.getBlockState(blockpos1).getBlock().isAir(world.getBlockState(blockpos1), world, blockpos1))
                                        {
                                            this.addHangingVine(world, blockpos1, BlockVine.NORTH);
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

    private void addVine(World world, BlockPos pos, PropertyBool property)
    {
        this.setBlockAndNotifyAdequately(world, pos, NibiruBlocks.INFECTED_VINES.getDefaultState().withProperty(property, Boolean.valueOf(true)));
    }

    private void addHangingVine(World world, BlockPos pos, PropertyBool property)
    {
        this.addVine(world, pos, property);
        int i = 4;

        for (pos = pos.down(); world.getBlockState(pos).getBlock().isAir(world.getBlockState(pos), world,pos) && i > 0; --i)
        {
            this.addVine(world, pos, property);
            pos = pos.down();
        }
    }
}