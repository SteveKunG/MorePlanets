package stevekung.mods.moreplanets.module.planets.nibiru.world.gen.biome;

import stevekung.mods.moreplanets.module.planets.nibiru.blocks.NibiruBlocks;

public class BiomeInfectedRiver extends BiomeNibiru
{
    public BiomeInfectedRiver(BiomeProperties properties)
    {
        super(properties);
        this.topBlock = NibiruBlocks.INFECTED_GRASS.getDefaultState();
        this.fillerBlock = NibiruBlocks.INFECTED_DIRT.getDefaultState();
        this.stoneBlock = NibiruBlocks.NIBIRU_BLOCK.getDefaultState();
        this.getBiomeDecorator().infectedTallGrassPerChunk = 2;
        this.getBiomeDecorator().reedsPerChunk = 10;
        this.decorator.treesPerChunk = -999;
    }
}