package stevekung.mods.moreplanets.planets.diona.world.gen;

import micdoodle8.mods.galacticraft.api.prefab.world.gen.BiomeProviderSpace;
import net.minecraft.world.biome.Biome;
import stevekung.mods.moreplanets.init.MPBiomes;

public class BiomeProviderDiona extends BiomeProviderSpace
{
    @Override
    public Biome getBiome()
    {
        return MPBiomes.DIONA;
    }
}