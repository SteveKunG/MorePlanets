package stevekung.mods.moreplanets.utils;

import micdoodle8.mods.galacticraft.api.vector.Vector3;
import net.minecraft.world.World;
import stevekung.mods.moreplanets.moons.koentus.dimension.WorldProviderKoentus;
import stevekung.mods.moreplanets.planets.chalos.dimension.WorldProviderChalos;

public class WorldColorUtils
{
    public static Vector3 getWorldColor(World world)
    {
        if (world.provider instanceof WorldProviderChalos)
        {
            return new Vector3(0.9F, 0.85F, 0.65F);
        }
        else if (world.provider instanceof WorldProviderKoentus)
        {
            return new Vector3(0.7F, 0.85F, 1.0F);
        }
        return new Vector3(1.0F, 1.0F, 1.0F);
    }
}