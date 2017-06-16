package stevekung.mods.moreplanets.module.planets.nibiru.world.gen.biome;

import stevekung.mods.moreplanets.core.config.ConfigManagerMP;
import stevekung.mods.moreplanets.module.planets.nibiru.blocks.NibiruBlocks;

public class BiomeInfectedOcean extends BiomeNibiru
{
    public BiomeInfectedOcean()
    {
        super(ConfigManagerMP.idBiomeInfectedOcean);
        this.enableRain = true;
        this.topBlock = NibiruBlocks.INFECTED_GRASS.getDefaultState();
        this.fillerBlock = NibiruBlocks.INFECTED_DIRT.getDefaultState();
        this.stoneBlock = NibiruBlocks.NIBIRU_BLOCK.getDefaultState();
        this.setHeight(new Height(-1.0F, 0.1F));
        this.getBiomeDecorator().seaweedPerChunk = 4;
        this.theBiomeDecorator.treesPerChunk = -999;
    }

    @Override
    public TempCategory getTempCategory()
    {
        return TempCategory.OCEAN;
    }
}