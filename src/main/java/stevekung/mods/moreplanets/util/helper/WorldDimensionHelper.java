package stevekung.mods.moreplanets.util.helper;

import micdoodle8.mods.galacticraft.core.util.WorldUtil;
import net.minecraft.world.WorldProvider;
import net.minecraft.world.WorldServer;
import stevekung.mods.moreplanets.core.config.ConfigManagerMP;

public class WorldDimensionHelper
{
    public static WorldServer getStartWorld(WorldServer worldOld)
    {
        WorldProvider wp = WorldUtil.getProviderForNameServer(ConfigManagerMP.startedPlanet);
        WorldServer worldNew = wp == null ? null : (WorldServer) wp.world;

        if (worldNew != null)
        {
            return worldNew;
        }
        return worldOld;
    }
}