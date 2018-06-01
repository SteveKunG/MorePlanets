package stevekung.mods.moreplanets.utils;

import micdoodle8.mods.galacticraft.core.util.WorldUtil;
import net.minecraft.world.WorldProvider;
import net.minecraft.world.WorldServer;
import stevekung.mods.moreplanets.core.event.WorldTickEventHandler;

public class PlanetSpawnerUtils
{
    public static WorldServer getStartWorld(WorldServer worldOld)
    {
        WorldProvider wp = WorldUtil.getProviderForNameServer(WorldTickEventHandler.survivalPlanetData.survivalPlanetName);
        WorldServer worldNew = wp == null ? null : (WorldServer)wp.world;

        if (worldNew != null)
        {
            return worldNew;
        }
        return worldOld;
    }
}