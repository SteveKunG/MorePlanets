package stevekung.mods.moreplanets.module.planets.nibiru.world.gen.biome;

import stevekung.mods.moreplanets.core.config.ConfigManagerMP;
import stevekung.mods.moreplanets.module.planets.nibiru.blocks.NibiruBlocks;

public class BiomeInfectedRiver extends BiomeNibiru
{
    public BiomeInfectedRiver()
    {
        super(ConfigManagerMP.idBiomeInfectedRiver);
        this.enableRain = true;
        this.topBlock = NibiruBlocks.INFECTED_GRASS.getDefaultState();
        this.fillerBlock = NibiruBlocks.INFECTED_DIRT.getDefaultState();
        this.stoneBlock = NibiruBlocks.NIBIRU_BLOCK.getDefaultState();
        this.getBiomeDecorator().infectedTallGrassPerChunk = 2;
        this.getBiomeDecorator().reedsPerChunk = 10;
        this.theBiomeDecorator.treesPerChunk = -999;
        this.setHeight(new Height(-0.5F, 0.0F));
    }
}