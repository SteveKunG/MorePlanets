package stevekung.mods.moreplanets.planets.nibiru.world.gen.feature;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import stevekung.mods.moreplanets.init.MPBlocks;
import stevekung.mods.stevekunglib.world.gen.WorldGenAbstractShrub;

public class WorldGenInfectedShrub extends WorldGenAbstractShrub
{
    public WorldGenInfectedShrub(IBlockState log, IBlockState leaves)
    {
        super(MPBlocks.INFECTED_OAK_LOG.getDefaultState(), MPBlocks.INFECTED_OAK_LEAVES.getDefaultState());
    }

    @Override
    protected boolean isSoil(Block block)
    {
        return block == MPBlocks.INFECTED_GRASS_BLOCK || block == MPBlocks.INFECTED_DIRT || block == MPBlocks.INFECTED_COARSE_DIRT || block == MPBlocks.INFECTED_FARMLAND;
    }
}