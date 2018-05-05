package stevekung.mods.moreplanets.planets.nibiru.world.gen.feature;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import stevekung.mods.moreplanets.planets.nibiru.blocks.NibiruBlocks;
import stevekung.mods.stevekunglib.world.gen.WorldGenAbstractTree;

public class WorldGenInfectedTrees extends WorldGenAbstractTree
{
    public WorldGenInfectedTrees(boolean genLeaves, IBlockState leaves, IBlockState log)
    {
        super(leaves, log);
        this.genLeaves = genLeaves;
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
}