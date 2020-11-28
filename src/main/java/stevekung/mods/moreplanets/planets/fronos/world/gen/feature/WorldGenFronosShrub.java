package stevekung.mods.moreplanets.planets.fronos.world.gen.feature;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenTrees;
import stevekung.mods.moreplanets.init.MPBlocks;
import stevekung.mods.stevekunglib.utils.BlockStateProperty;

public class WorldGenFronosShrub extends WorldGenTrees
{
    public WorldGenFronosShrub()
    {
        super(false);
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
            this.setBlockAndNotifyAdequately(world, position, MPBlocks.OSCALEA_LOG.getDefaultState());

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
                                this.setBlockAndNotifyAdequately(world, blockpos, rand.nextInt(10) == 0 ? WorldGenFroliaTree.FLOWERING : MPBlocks.FROLIA_LEAVES.getDefaultState().withProperty(BlockStateProperty.CHECK_DECAY, false));
                            }
                        }
                    }
                }
            }
        }
        return true;
    }

    private boolean isSoil(Block block)
    {
        return block == MPBlocks.FRONOS_GRASS_BLOCK || block == MPBlocks.FRONOS_DIRT || block == MPBlocks.FRONOS_COARSE_DIRT || block == MPBlocks.FRONOS_FARMLAND;
    }
}