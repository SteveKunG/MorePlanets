package stevekung.mods.moreplanets.module.planets.nibiru.world.gen.biome;

import static net.minecraftforge.common.BiomeDictionary.Type.DEAD;
import static net.minecraftforge.common.BiomeDictionary.Type.OCEAN;

import net.minecraft.world.biome.Biome;
import stevekung.mods.moreplanets.module.planets.nibiru.blocks.NibiruBlocks;
import stevekung.mods.moreplanets.util.helper.CommonRegisterHelper;

public class BiomeInfectedOcean extends BiomeNibiru
{
    public BiomeInfectedOcean(BiomeProperties properties)
    {
        super(properties);
        this.topBlock = NibiruBlocks.INFECTED_GRASS.getDefaultState();
        this.fillerBlock = NibiruBlocks.INFECTED_DIRT.getDefaultState();
        this.stoneBlock = NibiruBlocks.NIBIRU_BLOCK.getDefaultState();
        this.getBiomeDecorator().seaweedPerChunk = 4;
        this.decorator.treesPerChunk = -999;
    }

    @Override
    public void registerTypes(Biome biome)
    {
        CommonRegisterHelper.registerBiomeType(biome, OCEAN, DEAD);
    }

    @Override
    public TempCategory getTempCategory()
    {
        return TempCategory.OCEAN;
    }
}