package stevekung.mods.moreplanets.module.planets.nibiru.world.gen.feature;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.BlockPos;
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

    protected int getHeight(Random rand)
    {
        int i = rand.nextInt(3) + this.baseHeight;

        if (this.extraRandomHeight > 1)
        {
            i += rand.nextInt(this.extraRandomHeight);
        }
        return i;
    }

    private boolean isSpaceAt(World world, BlockPos pos, int height)
    {
        boolean flag = true;

        if (pos.getY() >= 1 && pos.getY() + height + 1 <= 256)
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
                        if (pos.getY() + i < 0 || pos.getY() + i >= 256 || !this.isReplaceable(world, pos.add(k, i, l)))
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
        Block block = world.getBlockState(blockpos).getBlock();

        if (block == NibiruBlocks.INFECTED_GRASS || block == NibiruBlocks.INFECTED_DIRT || block == NibiruBlocks.INFECTED_FARMLAND && pos.getY() >= 2)
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

    protected boolean ensureGrowable(World world, BlockPos pos, int height)
    {
        return this.isSpaceAt(world, pos, height) && this.ensureDirtsUnderneath(pos, world);
    }

    protected void growLeavesLayerStrict(World world, BlockPos pos, int width)
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
                    BlockPos blockpos = pos.add(j, 0, k);
                    IBlockState state = world.getBlockState(blockpos);

                    if (state.getBlock().isAir(state, world, blockpos) || state.getBlock().isLeaves(state, world, blockpos))
                    {
                        this.setBlockAndNotifyAdequately(world, blockpos, NibiruBlocks.NIBIRU_LEAVES.getStateFromMeta(2));
                    }
                }
            }
        }
    }

    protected void growLeavesLayer(World world, BlockPos pos, int width)
    {
        int i = width * width;

        for (int j = -width; j <= width; ++j)
        {
            for (int k = -width; k <= width; ++k)
            {
                if (j * j + k * k <= i)
                {
                    BlockPos blockpos = pos.add(j, 0, k);
                    Block block = world.getBlockState(blockpos).getBlock();

                    if (block.isAir(world.getBlockState(blockpos), world, blockpos) || block.isLeaves(world.getBlockState(blockpos), world, blockpos))
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
        world.getBlockState(pos).getBlock().onPlantGrow(world.getBlockState(pos), world, pos, source);
    }
}