package stevekung.mods.moreplanets.module.planets.nibiru.world.gen.biome;

import java.util.LinkedList;

import stevekung.mods.moreplanets.module.planets.nibiru.blocks.NibiruBlocks;

public class BiomeInfectedDeepOcean extends BiomeNibiru
{
    public BiomeInfectedDeepOcean(BiomeProperties properties)
    {
        super(properties);
        properties.setBaseHeight(-1.8F);
        properties.setHeightVariation(0.1F);
        this.topBlock = NibiruBlocks.INFECTED_GRASS.getDefaultState();
        this.fillerBlock = NibiruBlocks.INFECTED_DIRT.getDefaultState();
        this.stoneBlock = NibiruBlocks.NIBIRU_BLOCK.getDefaultState();
        this.getBiomeDecorator().seaweedPerChunk = 4;
        this.theBiomeDecorator.treesPerChunk = -999;
    }

    @Override
    public TempCategory getTempCategory()
    {
        return TempCategory.OCEAN;
    }

    @Override
    public void initialiseMobLists(LinkedList<SpawnListEntry> mobInfo)
    {
        super.initialiseMobLists(mobInfo);
        this.spawnableCreatureList.clear();
    }
}