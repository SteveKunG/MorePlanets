package stevekung.mods.moreplanets.module.planets.nibiru.world.gen.biome;

import java.util.LinkedList;

import stevekung.mods.moreplanets.module.planets.nibiru.blocks.NibiruBlocks;

public class BiomeInfectedDesert extends BiomeNibiru
{
    public BiomeInfectedDesert(BiomeProperties properties)
    {
        super(properties);
        this.topBlock = NibiruBlocks.INFECTED_SAND.getDefaultState();
        this.fillerBlock = NibiruBlocks.INFECTED_SAND.getDefaultState();
        this.stoneBlock = NibiruBlocks.NIBIRU_BLOCK.getDefaultState();
        this.getBiomeDecorator().infectedCactusPerChunk = 10;
        this.getBiomeDecorator().deadBushPerChunk = 2;
        this.getBiomeDecorator().batasiaDandelionPerChunk = 3;
        this.getBiomeDecorator().reedsPerChunk = 50;
        this.theBiomeDecorator.treesPerChunk = -999;
    }

    @Override
    public void initialiseMobLists(LinkedList<SpawnListEntry> mobInfo)
    {
        super.initialiseMobLists(mobInfo);
        this.spawnableCreatureList.clear();
    }
}