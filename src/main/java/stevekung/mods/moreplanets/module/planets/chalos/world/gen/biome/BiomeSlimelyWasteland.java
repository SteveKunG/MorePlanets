package stevekung.mods.moreplanets.module.planets.chalos.world.gen.biome;

import java.util.LinkedList;

import stevekung.mods.moreplanets.module.planets.chalos.blocks.ChalosBlocks;

public class BiomeSlimelyWasteland extends BiomeChalos
{
    public BiomeSlimelyWasteland(BiomeProperties properties)
    {
        super(properties);
        this.topBlock = ChalosBlocks.CHEESE_SLIME_BLOCK.getDefaultState();
        this.fillerBlock = ChalosBlocks.CHEESE_SLIME_BLOCK.getDefaultState();
        this.stoneBlock = ChalosBlocks.CHALOS_BLOCK.getDefaultState();
    }

    @Override
    public void initialiseMobLists(LinkedList<SpawnListEntry> mobInfo)
    {
        super.initialiseMobLists(mobInfo);
        this.spawnableCreatureList.clear();
    }
}