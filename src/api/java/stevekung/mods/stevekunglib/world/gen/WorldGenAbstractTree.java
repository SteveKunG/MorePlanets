package stevekung.mods.stevekunglib.world.gen;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public abstract class WorldGenAbstractTree extends net.minecraft.world.gen.feature.WorldGenAbstractTree
{
    protected IBlockState log;
    protected IBlockState leaves;
    protected boolean genLeaves = true;

    public WorldGenAbstractTree(IBlockState log, IBlockState leaves)
    {
        super(false);
        this.log = log;
        this.leaves = leaves;
    }

    @Override
    public boolean generate(World world, Random rand, BlockPos pos)
    {
        int i = this.getTreeHeight(rand);
        boolean flag = true;

        if (pos.getY() >= 1 && pos.getY() + i + 1 <= world.getHeight())
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
                        if (j >= 0 && j < world.getHeight())
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
                IBlockState state = world.getBlockState(pos.down());

                if (this.isSoil(state.getBlock()) && pos.getY() < world.getHeight() - i - 1)
                {
                    state.getBlock().onPlantGrow(state, world, pos.down(), pos);

                    if (this.genLeaves)
                    {
                        for (int i3 = pos.getY() - 3 + i; i3 <= pos.getY() + i; ++i3)
                        {
                            int i4 = i3 - (pos.getY() + i);
                            int j1 = 1 - i4 / 2;

                            for (int k1 = pos.getX() - j1; k1 <= pos.getX() + j1; ++k1)
                            {
                                int l1 = k1 - pos.getX();

                                for (int i2 = pos.getZ() - j1; i2 <= pos.getZ() + j1; ++i2)
                                {
                                    int j2 = i2 - pos.getZ();

                                    if (Math.abs(l1) != j1 || Math.abs(j2) != j1 || rand.nextInt(2) != 0 && i4 != 0)
                                    {
                                        BlockPos blockpos = new BlockPos(k1, i3, i2);
                                        state = world.getBlockState(blockpos);

                                        if (state.getBlock().isAir(state, world, blockpos) || state.getBlock().isLeaves(state, world, blockpos) || state.getMaterial() == Material.VINE)
                                        {
                                            this.setBlockAndNotifyAdequately(world, blockpos, this.leaves);
                                        }
                                    }
                                }
                            }
                        }
                    }

                    for (int j3 = 0; j3 < i; ++j3)
                    {
                        BlockPos upN = pos.up(j3);
                        state = world.getBlockState(upN);

                        if (state.getBlock().isAir(state, world, upN) || state.getBlock().isLeaves(state, world, upN) || state.getMaterial() == Material.VINE)
                        {
                            this.setBlockAndNotifyAdequately(world, pos.up(j3), this.log);
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

    @Override
    public boolean isReplaceable(World world, BlockPos pos)
    {
        IBlockState state = world.getBlockState(pos);
        return state.getBlock().isAir(state, world, pos) || state.getBlock().isLeaves(state, world, pos) || state.getBlock().isWood(world, pos) || this.canGrowInto(state.getBlock());
    }

    @Override
    protected boolean canGrowInto(Block block)
    {
        Material material = block.getDefaultState().getMaterial();
        return material == Material.AIR || material == Material.LEAVES;
    }

    protected int getTreeHeight(Random rand)
    {
        return rand.nextInt(3) + 4;
    }

    protected abstract boolean isSoil(Block block);
}