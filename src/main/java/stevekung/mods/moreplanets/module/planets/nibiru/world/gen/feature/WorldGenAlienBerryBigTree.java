package stevekung.mods.moreplanets.module.planets.nibiru.world.gen.feature;

import net.minecraft.block.Block;
import stevekung.mods.moreplanets.module.planets.nibiru.blocks.NibiruBlocks;
import stevekung.mods.stevekunglib.world.gen.WorldGenAbstractBigTree;

public class WorldGenAlienBerryBigTree extends WorldGenAbstractBigTree
{
    public WorldGenAlienBerryBigTree()
    {
        super(NibiruBlocks.ALIEN_BERRY_OAK_LOG.getDefaultState(), NibiruBlocks.ALIEN_BERRY_OAK_LEAVES.getDefaultState());
    }

    @Override
    protected boolean isSoil(Block block)
    {
        return block == NibiruBlocks.GREEN_VEIN_GRASS_BLOCK;
    }
}