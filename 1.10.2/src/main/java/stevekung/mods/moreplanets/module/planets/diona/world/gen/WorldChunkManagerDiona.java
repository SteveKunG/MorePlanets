package stevekung.mods.moreplanets.module.planets.diona.world.gen;

import micdoodle8.mods.galacticraft.api.prefab.world.gen.WorldChunkManagerSpace;
import net.minecraft.world.biome.BiomeGenBase;
import stevekung.mods.moreplanets.init.MPBiomes;

public class WorldChunkManagerDiona extends WorldChunkManagerSpace
{
    @Override
    public BiomeGenBase getBiome()
    {
        return MPBiomes.DIONA;
    }
}