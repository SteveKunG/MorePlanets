package stevekung.mods.moreplanets.planets.nibiru.world.gen.biome;

public class BiomeInfectedOcean extends BiomeNibiru
{
    public BiomeInfectedOcean(BiomeProperties prop)
    {
        super(prop);
        this.decorator.seaweedPerChunk = 4;
        this.decorator.treesPerChunk = -999;
    }

    @Override
    public TempCategory getTempCategory()
    {
        return TempCategory.OCEAN;
    }
}