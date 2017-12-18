package stevekung.mods.moreplanets.module.planets.nibiru.world.gen.biome;

import stevekung.mods.moreplanets.core.config.ConfigManagerMP;
import stevekung.mods.moreplanets.module.planets.nibiru.blocks.NibiruBlocks;

public class BiomeInfectedDeepOcean extends BiomeNibiru
{
    public BiomeInfectedDeepOcean()
    {
        super(ConfigManagerMP.idBiomeInfectedDeepOcean);
        this.enableRain = true;
        this.topBlock = NibiruBlocks.INFECTED_GRASS.getDefaultState();
        this.fillerBlock = NibiruBlocks.INFECTED_DIRT.getDefaultState();
        this.stoneBlock = NibiruBlocks.NIBIRU_BLOCK.getDefaultState();
        this.getBiomeDecorator().seaweedPerChunk = 4;
        this.theBiomeDecorator.treesPerChunk = -999;
        this.setHeight(new Height(-1.8F, 0.1F));
    }

    @Override
    public TempCategory getTempCategory()
    {
        return TempCategory.OCEAN;
    }
}