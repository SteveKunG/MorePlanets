package stevekung.mods.moreplanets.planets.nibiru.world.gen.feature;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import stevekung.mods.moreplanets.init.MPBlocks;
import stevekung.mods.stevekunglib.world.gen.WorldGenAbstractMegaJungle;

public class WorldGenInfectedMegaJungleTree extends WorldGenAbstractMegaJungle
{
    public WorldGenInfectedMegaJungleTree()
    {
        super(10, 20, MPBlocks.INFECTED_JUNGLE_LOG.getDefaultState(), MPBlocks.INFECTED_JUNGLE_LEAVES.getDefaultState());
    }

    @Override
    protected int getTreeHeight(Random rand)
    {
        return rand.nextInt(3) + 5;
    }

    @Override
    protected boolean isSoil(Block block)
    {
        return block == MPBlocks.INFECTED_GRASS_BLOCK || block == MPBlocks.INFECTED_DIRT || block == MPBlocks.INFECTED_COARSE_DIRT || block == MPBlocks.INFECTED_FARMLAND;
    }

    @Override
    protected IBlockState getVine()
    {
        return MPBlocks.INFECTED_VINES.getDefaultState();
    }
}