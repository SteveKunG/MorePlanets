package stevekung.mods.moreplanets.planets.nibiru.world.gen.feature;

import net.minecraft.block.Block;
import stevekung.mods.moreplanets.init.MPBlocks;
import stevekung.mods.stevekunglib.world.gen.WorldGenAbstractBigTree;

public class WorldGenAlienBerryBigTree extends WorldGenAbstractBigTree
{
    public WorldGenAlienBerryBigTree()
    {
        super(MPBlocks.ALIEN_BERRY_OAK_LOG.getDefaultState(), MPBlocks.ALIEN_BERRY_OAK_LEAVES.getDefaultState());
    }

    @Override
    protected boolean isSoil(Block block)
    {
        return block == MPBlocks.GREEN_VEIN_GRASS_BLOCK;
    }
}