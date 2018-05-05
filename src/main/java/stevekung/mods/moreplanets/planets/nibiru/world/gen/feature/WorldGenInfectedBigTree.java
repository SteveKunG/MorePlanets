package stevekung.mods.moreplanets.planets.nibiru.world.gen.feature;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import stevekung.mods.moreplanets.planets.nibiru.blocks.NibiruBlocks;
import stevekung.mods.stevekunglib.world.gen.WorldGenAbstractBigTree;

public class WorldGenInfectedBigTree extends WorldGenAbstractBigTree
{
    public WorldGenInfectedBigTree(boolean genLeaves, IBlockState log, IBlockState leaves)
    {
        super(log, leaves);
        this.genLeaves = genLeaves;
    }

    @Override
    protected boolean isSoil(Block block)
    {
        return block == NibiruBlocks.INFECTED_GRASS_BLOCK || block == NibiruBlocks.INFECTED_DIRT || block == NibiruBlocks.INFECTED_COARSE_DIRT || block == NibiruBlocks.INFECTED_FARMLAND;
    }
}