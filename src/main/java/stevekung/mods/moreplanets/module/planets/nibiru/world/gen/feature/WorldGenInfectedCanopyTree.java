package stevekung.mods.moreplanets.module.planets.nibiru.world.gen.feature;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;
import stevekung.mods.moreplanets.module.planets.nibiru.blocks.NibiruBlocks;

public class WorldGenInfectedCanopyTree extends WorldGenAbstractTree
{
    private boolean genLeaves;

    public WorldGenInfectedCanopyTree(boolean genLeaves)
    {
        super(false);
        this.genLeaves = genLeaves;
    }

    @Override
    public boolean generate(World world, Random rand, BlockPos position)
    {
        int i = rand.nextInt(3) + rand.nextInt(2) + 6;
        int j = position.getX();
        int k = position.getY();
        int l = position.getZ();

        if (k >= 1 && k + i + 1 < 256)
        {
            BlockPos blockpos = position.down();
            IBlockState state = world.getBlockState(blockpos);
            Block block = state.getBlock();

            if (!(block == NibiruBlocks.INFECTED_GRASS || block == NibiruBlocks.INFECTED_DIRT || block == NibiruBlocks.INFECTED_FARMLAND) && position.getY() < 256 - i - 1)
            {
                return false;
            }
            else if (!this.func_181638_a(world, position, i))
            {
                return false;
            }
            else
            {
                this.onPlantGrow(world, blockpos, position);
                this.onPlantGrow(world, blockpos.east(), position);
                this.onPlantGrow(world, blockpos.south(), position);
                this.onPlantGrow(world, blockpos.south().east(), position);
                EnumFacing enumfacing = EnumFacing.Plane.HORIZONTAL.random(rand);
                int i1 = i - rand.nextInt(4);
                int j1 = 2 - rand.nextInt(3);
                int k1 = j;
                int l1 = l;
                int i2 = k + i - 1;

                for (int j2 = 0; j2 < i; ++j2)
                {
                    if (j2 >= i1 && j1 > 0)
                    {
                        k1 += enumfacing.getFrontOffsetX();
                        l1 += enumfacing.getFrontOffsetZ();
                        --j1;
                    }

                    int k2 = k + j2;
                    BlockPos blockpos1 = new BlockPos(k1, k2, l1);
                    state = world.getBlockState(blockpos1);

                    if (state.getBlock().isAir(world, blockpos1) || state.getBlock().isLeaves(world, blockpos1))
                    {
                        this.func_181639_b(world, blockpos1);
                        this.func_181639_b(world, blockpos1.east());
                        this.func_181639_b(world, blockpos1.south());
                        this.func_181639_b(world, blockpos1.east().south());
                    }
                }

                for (int i3 = -2; i3 <= 0; ++i3)
                {
                    for (int l3 = -2; l3 <= 0; ++l3)
                    {
                        int k4 = -1;
                        this.func_150526_a(world, k1 + i3, i2 + k4, l1 + l3);
                        this.func_150526_a(world, 1 + k1 - i3, i2 + k4, l1 + l3);
                        this.func_150526_a(world, k1 + i3, i2 + k4, 1 + l1 - l3);
                        this.func_150526_a(world, 1 + k1 - i3, i2 + k4, 1 + l1 - l3);

                        if ((i3 > -2 || l3 > -1) && (i3 != -1 || l3 != -2))
                        {
                            k4 = 1;
                            this.func_150526_a(world, k1 + i3, i2 + k4, l1 + l3);
                            this.func_150526_a(world, 1 + k1 - i3, i2 + k4, l1 + l3);
                            this.func_150526_a(world, k1 + i3, i2 + k4, 1 + l1 - l3);
                            this.func_150526_a(world, 1 + k1 - i3, i2 + k4, 1 + l1 - l3);
                        }
                    }
                }

                if (rand.nextBoolean())
                {
                    this.func_150526_a(world, k1, i2 + 2, l1);
                    this.func_150526_a(world, k1 + 1, i2 + 2, l1);
                    this.func_150526_a(world, k1 + 1, i2 + 2, l1 + 1);
                    this.func_150526_a(world, k1, i2 + 2, l1 + 1);
                }

                for (int j3 = -3; j3 <= 4; ++j3)
                {
                    for (int i4 = -3; i4 <= 4; ++i4)
                    {
                        if ((j3 != -3 || i4 != -3) && (j3 != -3 || i4 != 4) && (j3 != 4 || i4 != -3) && (j3 != 4 || i4 != 4) && (Math.abs(j3) < 3 || Math.abs(i4) < 3))
                        {
                            this.func_150526_a(world, k1 + j3, i2, l1 + i4);
                        }
                    }
                }
                for (int k3 = -1; k3 <= 2; ++k3)
                {
                    for (int j4 = -1; j4 <= 2; ++j4)
                    {
                        if ((k3 < 0 || k3 > 1 || j4 < 0 || j4 > 1) && rand.nextInt(3) <= 0)
                        {
                            int l4 = rand.nextInt(3) + 2;

                            for (int i5 = 0; i5 < l4; ++i5)
                            {
                                this.func_181639_b(world, new BlockPos(j + k3, i2 - i5 - 1, l + j4));
                            }
                            for (int j5 = -1; j5 <= 1; ++j5)
                            {
                                for (int l2 = -1; l2 <= 1; ++l2)
                                {
                                    this.func_150526_a(world, k1 + k3 + j5, i2, l1 + j4 + l2);
                                }
                            }
                            for (int k5 = -2; k5 <= 2; ++k5)
                            {
                                for (int l5 = -2; l5 <= 2; ++l5)
                                {
                                    if (Math.abs(k5) != 2 || Math.abs(l5) != 2)
                                    {
                                        this.func_150526_a(world, k1 + k3 + k5, i2 - 1, l1 + j4 + l5);
                                    }
                                }
                            }
                        }
                    }
                }
                return true;
            }
        }
        else
        {
            return false;
        }
    }

    private boolean func_181638_a(World p_181638_1_, BlockPos p_181638_2_, int p_181638_3_)
    {
        int i = p_181638_2_.getX();
        int j = p_181638_2_.getY();
        int k = p_181638_2_.getZ();
        BlockPos.MutableBlockPos blockpos$mutableblockpos = new BlockPos.MutableBlockPos();

        for (int l = 0; l <= p_181638_3_ + 1; ++l)
        {
            int i1 = 1;

            if (l == 0)
            {
                i1 = 0;
            }
            if (l >= p_181638_3_ - 1)
            {
                i1 = 2;
            }

            for (int j1 = -i1; j1 <= i1; ++j1)
            {
                for (int k1 = -i1; k1 <= i1; ++k1)
                {
                    if (!this.isReplaceable(p_181638_1_, blockpos$mutableblockpos.set(i + j1, j + l, k + k1)))
                    {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    private void func_181639_b(World p_181639_1_, BlockPos p_181639_2_)
    {
        if (this.func_150523_a(p_181639_1_.getBlockState(p_181639_2_).getBlock()))
        {
            this.setBlockAndNotifyAdequately(p_181639_1_, p_181639_2_, NibiruBlocks.NIBIRU_LOG.getStateFromMeta(1));
        }
    }

    private void func_150526_a(World world, int p_150526_2_, int p_150526_3_, int p_150526_4_)
    {
        if (this.genLeaves)
        {
            BlockPos blockpos = new BlockPos(p_150526_2_, p_150526_3_, p_150526_4_);
            IBlockState state = world.getBlockState(blockpos);

            if (state.getBlock().isAir(world, blockpos))
            {
                this.setBlockAndNotifyAdequately(world, blockpos, NibiruBlocks.NIBIRU_LEAVES.getStateFromMeta(1));
            }
        }
    }

    private void onPlantGrow(World world, BlockPos pos, BlockPos source)
    {
        world.getBlockState(pos).getBlock().onPlantGrow(world, pos, source);
    }
}