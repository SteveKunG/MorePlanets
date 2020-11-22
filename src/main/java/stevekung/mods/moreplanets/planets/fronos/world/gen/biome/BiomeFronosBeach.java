package stevekung.mods.moreplanets.planets.fronos.world.gen.biome;

import net.minecraft.init.Blocks;

public class BiomeFronosBeach extends BiomeFronos
{
    public BiomeFronosBeach(BiomeProperties prop)
    {
        super(prop);
        this.topBlock = Blocks.SAND.getDefaultState();
        this.fillerBlock = Blocks.SAND.getDefaultState();
        this.decorator.treesPerChunk = -999;
        this.decorator.deadBushPerChunk = 0;
        this.decorator.reedsPerChunk = 0;
        this.decorator.cactiPerChunk = 0;
    }
}