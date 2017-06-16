package stevekung.mods.moreplanets.module.planets.nibiru.world.gen.feature;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;
import stevekung.mods.moreplanets.module.planets.nibiru.blocks.NibiruBlocks;
import stevekung.mods.moreplanets.util.blocks.BlockVinesMP;

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
                            if (!this.isReplaceable(world,blockpos$mutableblockpos.set(l, j, i1)))
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

                if (block1 == NibiruBlocks.INFECTED_GRASS || block1 == NibiruBlocks.INFECTED_DIRT || block1 == NibiruBlocks.INFECTED_FARMLAND && pos.getY() < 256 - i - 1)
                {
                    block1.onPlantGrow(world, down, pos);
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

                                    if (block.isAir(world, blockpos) || block.isLeaves(world, blockpos) || block.getMaterial() == Material.vine)
                                    {
                                        if (this.genLeaves)
                                        {
                                            this.setBlockAndNotifyAdequately(world, blockpos, NibiruBlocks.NIBIRU_LEAVES.getStateFromMeta(2));
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

                        if (block2.isAir(world, upN) || block2.isLeaves(world, upN) || block2.getMaterial() == Material.vine)
                        {
                            this.setBlockAndNotifyAdequately(world, pos.up(j3), NibiruBlocks.NIBIRU_LOG.getStateFromMeta(2));

                            if (this.vinesGrow && j3 > 0)
                            {
                                if (rand.nextInt(3) > 0 && world.isAirBlock(pos.add(-1, j3, 0)))
                                {
                                    this.func_181651_a(world, pos.add(-1, j3, 0), BlockVinesMP.EAST);
                                }
                                if (rand.nextInt(3) > 0 && world.isAirBlock(pos.add(1, j3, 0)))
                                {
                                    this.func_181651_a(world, pos.add(1, j3, 0), BlockVinesMP.WEST);
                                }
                                if (rand.nextInt(3) > 0 && world.isAirBlock(pos.add(0, j3, -1)))
                                {
                                    this.func_181651_a(world, pos.add(0, j3, -1), BlockVinesMP.SOUTH);
                                }
                                if (rand.nextInt(3) > 0 && world.isAirBlock(pos.add(0, j3, 1)))
                                {
                                    this.func_181651_a(world, pos.add(0, j3, 1), BlockVinesMP.NORTH);
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
                                    blockpos$mutableblockpos1.set(l4, k3, i5);

                                    if (world.getBlockState(blockpos$mutableblockpos1).getBlock().isLeaves(world,blockpos$mutableblockpos1))
                                    {
                                        BlockPos blockpos2 = blockpos$mutableblockpos1.west();
                                        BlockPos blockpos3 = blockpos$mutableblockpos1.east();
                                        BlockPos blockpos4 = blockpos$mutableblockpos1.north();
                                        BlockPos blockpos1 = blockpos$mutableblockpos1.south();

                                        if (rand.nextInt(4) == 0 && world.getBlockState(blockpos2).getBlock().isAir(world,blockpos2))
                                        {
                                            this.func_181650_b(world, blockpos2, BlockVinesMP.EAST);
                                        }
                                        if (rand.nextInt(4) == 0 && world.getBlockState(blockpos3).getBlock().isAir(world,blockpos3))
                                        {
                                            this.func_181650_b(world, blockpos3, BlockVinesMP.WEST);
                                        }
                                        if (rand.nextInt(4) == 0 && world.getBlockState(blockpos4).getBlock().isAir(world,blockpos4))
                                        {
                                            this.func_181650_b(world, blockpos4, BlockVinesMP.SOUTH);
                                        }
                                        if (rand.nextInt(4) == 0 && world.getBlockState(blockpos1).getBlock().isAir(world,blockpos1))
                                        {
                                            this.func_181650_b(world, blockpos1, BlockVinesMP.NORTH);
                                        }
                                    }
                                }
                            }
                        }

                        if (rand.nextInt(5) == 0 && i > 5)
                        {
                            for (int l3 = 0; l3 < 2; ++l3)
                            {
                                for (EnumFacing enumfacing : EnumFacing.Plane.HORIZONTAL)
                                {
                                    if (rand.nextInt(4 - l3) == 0)
                                    {
                                        EnumFacing enumfacing1 = enumfacing.getOpposite();
                                        this.func_181652_a(world, rand.nextInt(3), pos.add(enumfacing1.getFrontOffsetX(), i - 5 + l3, enumfacing1.getFrontOffsetZ()), enumfacing);
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

    private void func_181652_a(World p_181652_1_, int p_181652_2_, BlockPos p_181652_3_, EnumFacing p_181652_4_)
    {
        //this.setBlockAndNotifyAdequately(p_181652_1_, p_181652_3_, Blocks.cocoa.getDefaultState().withProperty(BlockCocoa.AGE, Integer.valueOf(p_181652_2_)).withProperty(BlockDirectional.FACING, p_181652_4_));
    }

    private void func_181651_a(World p_181651_1_, BlockPos p_181651_2_, PropertyBool p_181651_3_)
    {
        this.setBlockAndNotifyAdequately(p_181651_1_, p_181651_2_, NibiruBlocks.INFECTED_VINES.getDefaultState().withProperty(p_181651_3_, Boolean.valueOf(true)));
    }

    private void func_181650_b(World p_181650_1_, BlockPos p_181650_2_, PropertyBool p_181650_3_)
    {
        this.func_181651_a(p_181650_1_, p_181650_2_, p_181650_3_);
        int i = 4;

        for (p_181650_2_ = p_181650_2_.down(); p_181650_1_.getBlockState(p_181650_2_).getBlock().isAir(p_181650_1_,p_181650_2_) && i > 0; --i)
        {
            this.func_181651_a(p_181650_1_, p_181650_2_, p_181650_3_);
            p_181650_2_ = p_181650_2_.down();
        }
    }
}