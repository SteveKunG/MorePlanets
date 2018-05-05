package stevekung.mods.moreplanets.planets.nibiru.world.gen.feature;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import stevekung.mods.moreplanets.planets.nibiru.blocks.NibiruBlocks;
import stevekung.mods.stevekunglib.world.gen.WorldGenAbstractShrub;

public class WorldGenInfectedShrub extends WorldGenAbstractShrub
{
    public WorldGenInfectedShrub(IBlockState log, IBlockState leaves)
    {
        super(NibiruBlocks.INFECTED_OAK_LOG.getDefaultState(), NibiruBlocks.INFECTED_OAK_LEAVES.getDefaultState());
    }

    @Override
    protected boolean isSoil(Block block)
    {
        return block == NibiruBlocks.INFECTED_GRASS_BLOCK || block == NibiruBlocks.INFECTED_DIRT || block == NibiruBlocks.INFECTED_COARSE_DIRT || block == NibiruBlocks.INFECTED_FARMLAND;
    }
}