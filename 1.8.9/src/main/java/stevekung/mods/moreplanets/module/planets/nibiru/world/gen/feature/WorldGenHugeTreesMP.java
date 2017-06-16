package stevekung.mods.moreplanets.module.planets.nibiru.world.gen.feature;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;
import stevekung.mods.moreplanets.module.planets.nibiru.blocks.NibiruBlocks;

public abstract class WorldGenHugeTreesMP extends WorldGenAbstractTree
{
    private boolean genLeaves;
    private int baseHeight;
    private int extraRandomHeight;

    public WorldGenHugeTreesMP(boolean genLeaves, int baseHeight, int extraRandomHeight)
    {
        super(false);
        this.genLeaves = genLeaves;
        this.baseHeight = baseHeight;
        this.extraRandomHeight = extraRandomHeight;
    }

    protected int func_150533_a(Random rand)
    {
        int i = rand.nextInt(3) + this.baseHeight;

        if (this.extraRandomHeight > 1)
        {
            i += rand.nextInt(this.extraRandomHeight);
        }
        return i;
    }

    private boolean func_175926_c(World world, BlockPos p_175926_2_, int p_175926_3_)
    {
        boolean flag = true;

        if (p_175926_2_.getY() >= 1 && p_175926_2_.getY() + p_175926_3_ + 1 <= 256)
        {
            for (int i = 0; i <= 1 + p_175926_3_; ++i)
            {
                int j = 2;

                if (i == 0)
                {
                    j = 1;
                }
                else if (i >= 1 + p_175926_3_ - 2)
                {
                    j = 2;
                }

                for (int k = -j; k <= j && flag; ++k)
                {
                    for (int l = -j; l <= j && flag; ++l)
                    {
                        if (p_175926_2_.getY() + i < 0 || p_175926_2_.getY() + i >= 256 || !this.isReplaceable(world,p_175926_2_.add(k, i, l)))
                        {
                            flag = false;
                        }
                    }
                }
            }

            return flag;
        }
        else
        {
            return false;
        }
    }

    private boolean func_175927_a(BlockPos p_175927_1_, World world)
    {
        BlockPos blockpos = p_175927_1_.down();
        Block block = world.getBlockState(blockpos).getBlock();

        if (block == NibiruBlocks.INFECTED_GRASS || block == NibiruBlocks.INFECTED_DIRT || block == NibiruBlocks.INFECTED_FARMLAND && p_175927_1_.getY() >= 2)
        {
            this.onPlantGrow(world, blockpos, p_175927_1_);
            this.onPlantGrow(world, blockpos.east(), p_175927_1_);
            this.onPlantGrow(world, blockpos.south(), p_175927_1_);
            this.onPlantGrow(world, blockpos.south().east(), p_175927_1_);
            return true;
        }
        else
        {
            return false;
        }
    }

    protected boolean func_175929_a(World world, Random p_175929_2_, BlockPos p_175929_3_, int p_175929_4_)
    {
        return this.func_175926_c(world, p_175929_3_, p_175929_4_) && this.func_175927_a(p_175929_3_, world);
    }

    protected void func_175925_a(World world, BlockPos p_175925_2_, int p_175925_3_)
    {
        int i = p_175925_3_ * p_175925_3_;

        for (int j = -p_175925_3_; j <= p_175925_3_ + 1; ++j)
        {
            for (int k = -p_175925_3_; k <= p_175925_3_ + 1; ++k)
            {
                int l = j - 1;
                int i1 = k - 1;

                if (j * j + k * k <= i || l * l + i1 * i1 <= i || j * j + i1 * i1 <= i || l * l + k * k <= i)
                {
                    BlockPos blockpos = p_175925_2_.add(j, 0, k);
                    IBlockState state = world.getBlockState(blockpos);

                    if (state.getBlock().isAir(world, blockpos) || state.getBlock().isLeaves(world, blockpos))
                    {
                        this.setBlockAndNotifyAdequately(world, blockpos, NibiruBlocks.NIBIRU_LEAVES.getStateFromMeta(2));
                    }
                }
            }
        }
    }

    protected void func_175928_b(World world, BlockPos p_175928_2_, int p_175928_3_)
    {
        int i = p_175928_3_ * p_175928_3_;

        for (int j = -p_175928_3_; j <= p_175928_3_; ++j)
        {
            for (int k = -p_175928_3_; k <= p_175928_3_; ++k)
            {
                if (j * j + k * k <= i)
                {
                    BlockPos blockpos = p_175928_2_.add(j, 0, k);
                    Block block = world.getBlockState(blockpos).getBlock();

                    if (block.isAir(world, blockpos) || block.isLeaves(world, blockpos))
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

    private void onPlantGrow(World world, BlockPos pos, BlockPos source)
    {
        world.getBlockState(pos).getBlock().onPlantGrow(world, pos, source);
    }
}