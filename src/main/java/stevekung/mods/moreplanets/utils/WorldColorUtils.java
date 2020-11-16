package stevekung.mods.moreplanets.utils;

import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import stevekung.mods.moreplanets.moons.koentus.dimension.WorldProviderKoentus;
import stevekung.mods.moreplanets.planets.chalos.dimension.WorldProviderChalos;

public class WorldColorUtils
{
    public static Vec3d getWorldColor(World world)
    {
        if (world.provider instanceof WorldProviderChalos)
        {
            return new Vec3d(0.9F, 0.85F, 0.65F);
        }
        else if (world.provider instanceof WorldProviderKoentus)
        {
            return new Vec3d(0.7F, 0.85F, 1.0F);
        }
        return new Vec3d(1.0F, 1.0F, 1.0F);
    }
}