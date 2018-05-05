package stevekung.mods.moreplanets.planets.nibiru.world.gen.feature;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import stevekung.mods.moreplanets.planets.nibiru.blocks.NibiruBlocks;
import stevekung.mods.stevekunglib.world.gen.WorldGenAbstractMegaJungle;

public class WorldGenInfectedMegaJungleTree extends WorldGenAbstractMegaJungle
{
    public WorldGenInfectedMegaJungleTree()
    {
        super(10, 20, NibiruBlocks.INFECTED_JUNGLE_LOG.getDefaultState(), NibiruBlocks.INFECTED_JUNGLE_LEAVES.getDefaultState());
    }

    @Override
    protected int getTreeHeight(Random rand)
    {
        return rand.nextInt(3) + 5;
    }

    @Override
    protected boolean isSoil(Block block)
    {
        return block == NibiruBlocks.INFECTED_GRASS_BLOCK || block == NibiruBlocks.INFECTED_DIRT || block == NibiruBlocks.INFECTED_COARSE_DIRT || block == NibiruBlocks.INFECTED_FARMLAND;
    }

    @Override
    protected IBlockState getVine()
    {
        return NibiruBlocks.INFECTED_VINES.getDefaultState();
    }
}