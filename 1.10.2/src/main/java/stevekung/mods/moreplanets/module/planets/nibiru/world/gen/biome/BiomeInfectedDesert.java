package stevekung.mods.moreplanets.module.planets.nibiru.world.gen.biome;

import stevekung.mods.moreplanets.core.config.ConfigManagerMP;
import stevekung.mods.moreplanets.module.planets.nibiru.blocks.NibiruBlocks;

public class BiomeInfectedDesert extends BiomeNibiru
{
    public BiomeInfectedDesert()
    {
        super(ConfigManagerMP.idBiomeInfectedDesert);
        this.enableRain = false;
        this.topBlock = NibiruBlocks.INFECTED_SAND.getDefaultState();
        this.fillerBlock = NibiruBlocks.INFECTED_SAND.getDefaultState();
        this.stoneBlock = NibiruBlocks.NIBIRU_BLOCK.getDefaultState();
        this.setTemperatureRainfall(2.0F, 0.0F);
        this.setHeight(new Height(0.125F, 0.05F));
        this.getBiomeDecorator().infectedCactusPerChunk = 20;
        this.getBiomeDecorator().deadBushPerChunk = 8;
        this.getBiomeDecorator().batasiaDandelionPerChunk = 3;
        this.getBiomeDecorator().reedsPerChunk = 50;
        this.theBiomeDecorator.treesPerChunk = -999;
    }
}