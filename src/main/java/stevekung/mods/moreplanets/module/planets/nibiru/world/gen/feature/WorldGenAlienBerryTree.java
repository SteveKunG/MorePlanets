package stevekung.mods.moreplanets.module.planets.nibiru.world.gen.feature;

import java.util.Random;

import net.minecraft.block.Block;
import stevekung.mods.moreplanets.module.planets.nibiru.blocks.NibiruBlocks;
import stevekung.mods.stevekunglib.world.gen.WorldGenAbstractTree;

public class WorldGenAlienBerryTree extends WorldGenAbstractTree
{
    public WorldGenAlienBerryTree()
    {
        super(NibiruBlocks.ALIEN_BERRY_OAK_LOG.getDefaultState(), NibiruBlocks.ALIEN_BERRY_OAK_LEAVES.getDefaultState());
    }

    @Override
    protected int getTreeHeight(Random rand)
    {
        return rand.nextInt(3) + 5;
    }

    @Override
    protected boolean isSoil(Block block)
    {
        return block == NibiruBlocks.GREEN_VEIN_GRASS_BLOCK;
    }
}