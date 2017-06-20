package stevekung.mods.moreplanets.module.planets.nibiru.world.gen.biome;

import stevekung.mods.moreplanets.module.planets.nibiru.blocks.NibiruBlocks;

public class BiomeInfectedDesert extends BiomeNibiru
{
    public BiomeInfectedDesert(BiomeProperties properties)
    {
        super(properties);
        properties.setRainDisabled();
        properties.setTemperature(2.0F);
        properties.setBaseHeight(0.125F);
        properties.setHeightVariation(0.05F);
        this.topBlock = NibiruBlocks.INFECTED_SAND.getDefaultState();
        this.fillerBlock = NibiruBlocks.INFECTED_SAND.getDefaultState();
        this.stoneBlock = NibiruBlocks.NIBIRU_BLOCK.getDefaultState();
        this.getBiomeDecorator().infectedCactusPerChunk = 20;
        this.getBiomeDecorator().deadBushPerChunk = 8;
        this.getBiomeDecorator().batasiaDandelionPerChunk = 3;
        this.getBiomeDecorator().reedsPerChunk = 50;
        this.theBiomeDecorator.treesPerChunk = -999;
    }
}