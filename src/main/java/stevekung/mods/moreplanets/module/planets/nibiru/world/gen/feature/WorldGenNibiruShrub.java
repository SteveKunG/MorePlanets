package stevekung.mods.moreplanets.module.planets.nibiru.world.gen.feature;

import java.util.Random;

import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import stevekung.mods.moreplanets.module.planets.nibiru.blocks.NibiruBlocks;

public class WorldGenNibiruShrub extends WorldGenInfectedTrees
{
    private IBlockState log;
    private IBlockState leaves;

    public WorldGenNibiruShrub(IBlockState log, IBlockState leaves)
    {
        super(false, NibiruBlocks.INFECTED_OAK_LOG.getDefaultState(), NibiruBlocks.INFECTED_OAK_LEAVES.getDefaultState());
        this.log = log;
        this.leaves = leaves;
    }

    @Override
    public boolean generate(World world, Random rand, BlockPos pos)
    {
        for (IBlockState state = world.getBlockState(pos); (state.getBlock().isAir(state, world, pos) || state.getBlock().isLeaves(state, world, pos)) && pos.getY() > 0; state = world.getBlockState(pos))
        {
            pos = pos.down();
        }

        IBlockState state = world.getBlockState(pos);

        if (state.getBlock() == NibiruBlocks.INFECTED_GRASS_BLOCK || state.getBlock() == NibiruBlocks.INFECTED_DIRT || state.getBlock() == NibiruBlocks.INFECTED_FARMLAND)
        {
            pos = pos.up();
            this.setBlockAndNotifyAdequately(world, pos, this.log);

            for (int i = pos.getY(); i <= pos.getY() + 2; ++i)
            {
                int j = i - pos.getY();
                int k = 2 - j;

                for (int l = pos.getX() - k; l <= pos.getX() + k; ++l)
                {
                    int i1 = l - pos.getX();

                    for (int j1 = pos.getZ() - k; j1 <= pos.getZ() + k; ++j1)
                    {
                        int k1 = j1 - pos.getZ();

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