package stevekung.mods.stevekunglib.world.gen;

import java.util.Random;

import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public abstract class WorldGenAbstractHugeTrees extends WorldGenAbstractTree
{
    protected final int baseHeight;
    private final IBlockState leaves;
    private int extraRandomHeight;

    public WorldGenAbstractHugeTrees(int baseHeight, int extraRandomHeight, IBlockState leaves)
    {
        super(null, null);
        this.baseHeight = baseHeight;
        this.extraRandomHeight = extraRandomHeight;
        this.leaves = leaves;
    }

    protected int getHeight(Random rand)
    {
        int i = rand.nextInt(3) + this.baseHeight;

        if (this.extraRandomHeight > 1)
        {
            i += rand.nextInt(this.extraRandomHeight);
        }
        return i;
    }

    private boolean isSpaceAt(World world, BlockPos leavesPos, int height)
    {
        boolean flag = true;

        if (leavesPos.getY() >= 1 && leavesPos.getY() + height + 1 <= 256)
        {
            for (int i = 0; i <= 1 + height; ++i)
            {
                int j = 2;

                if (i == 0)
                {
                    j = 1;
                }
                else if (i >= 1 + height - 2)
                {
                    j = 2;
                }

                for (int k = -j; k <= j && flag; ++k)
                {
                    for (int l = -j; l <= j && flag; ++l)
                    {
                        if (leavesPos.getY() + i < 0 || leavesPos.getY() + i >= 256 || !this.isReplaceable(world,leavesPos.add(k, i, l)))
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

    private boolean ensureDirtsUnderneath(BlockPos pos, World world)
    {
        BlockPos blockpos = pos.down();
        IBlockState state = world.getBlockState(blockpos);
        boolean isSoil = this.isSoil(state.getBlock());

        if (isSoil && pos.getY() >= 2)
        {
            this.onPlantGrow(world, blockpos, pos);
            this.onPlantGrow(world, blockpos.east(), pos);
            this.onPlantGrow(world, blockpos.south(), pos);
            this.onPlantGrow(world, blockpos.south().east(), pos);
            return true;
        }
        else
        {
            return false;
        }
    }

    protected boolean ensureGrowable(World world, Random rand, BlockPos treePos, int height)
    {
        return this.isSpaceAt(world, treePos, height) && this.ensureDirtsUnderneath(treePos, world);
    }

    protected void growLeavesLayerStrict(World world, BlockPos layerCenter, int width)
    {
        int i = width * width;

        for (int j = -width; j <= width + 1; ++j)
        {
            for (int k = -width; k <= width + 1; ++k)
            {
                int l = j - 1;
                int i1 = k - 1;

                if (j * j + k * k <= i || l * l + i1 * i1 <= i || j * j + i1 * i1 <= i || l * l + k * k <= i)
                {
                    BlockPos blockpos = layerCenter.add(j, 0, k);
                    IBlockState state = world.getBlockState(blockpos);

                    if (state.getBlock().isAir(state, world, blockpos) || state.getBlock().isLeaves(state, world, blockpos))
                    {
                        this.setBlockAndNotifyAdequately(world, blockpos, this.leaves);
                    }
                }
            }
        }
    }

    protected void growLeavesLayer(World world, BlockPos layerCenter, int width)
    {
        int i = width * width;

        for (int j = -width; j <= width; ++j)
        {
            for (int k = -width; k <= width; ++k)
            {
                if (j * j + k * k <= i)
                {
                    BlockPos blockpos = layerCenter.add(j, 0, k);
                    IBlockState state = world.getBlockState(blockpos);

                    if (state.getBlock().isAir(state, world, blockpos) || state.getBlock().isLeaves(state, world, blockpos))
                    {
                        this.setBlockAndNotifyAdequately(world, blockpos, this.leaves);
                    }
                }
            }
        }
    }

    private void onPlantGrow(World world, BlockPos pos, BlockPos source)
    {
        IBlockState state = world.getBlockState(pos);
        state.getBlock().onPlantGrow(state, world, pos, source);
    }
}