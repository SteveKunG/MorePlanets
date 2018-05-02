package stevekung.mods.stevekunglib.world.gen;

import java.util.Random;

import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public abstract class WorldGenAbstractShrub extends WorldGenAbstractTree
{
    public WorldGenAbstractShrub(IBlockState log, IBlockState leaves)
    {
        super(log, leaves);
    }

    @Override
    public boolean generate(World world, Random rand, BlockPos position)
    {
        for (IBlockState iblockstate = world.getBlockState(position); (iblockstate.getBlock().isAir(iblockstate, world, position) || iblockstate.getBlock().isLeaves(iblockstate, world, position)) && position.getY() > 0; iblockstate = world.getBlockState(position))
        {
            position = position.down();
        }

        IBlockState state = world.getBlockState(position);

        if (this.isSoil(state.getBlock()))
        {
            position = position.up();
            this.setBlockAndNotifyAdequately(world, position, this.log);

            for (int i = position.getY(); i <= position.getY() + 2; ++i)
            {
                int j = i - position.getY();
                int k = 2 - j;

                for (int l = position.getX() - k; l <= position.getX() + k; ++l)
                {
                    int i1 = l - position.getX();

                    for (int j1 = position.getZ() - k; j1 <= position.getZ() + k; ++j1)
                    {
                        int k1 = j1 - position.getZ();

                        if (Math.abs(i1) != k || Math.abs(k1) != k || rand.nextInt(2) != 0)
                        {
                            BlockPos blockpos = new BlockPos(l, i, j1);
                            state = world.getBlockState(blockpos);

                            if (state.getBlock().canBeReplacedByLeaves(state, world, blockpos))
                            {
                                this.setBlockAndNotifyAdequately(world, blockpos, this.leaves);
                            }
                        }
                    }
                }
            }
        }
        return true;
    }
}