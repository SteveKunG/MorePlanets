package stevekung.mods.moreplanets.util;

import java.util.UUID;

import micdoodle8.mods.galacticraft.core.entities.IBoss;

public interface IMorePlanetsBoss extends IBoss
{
    public UUID getBossUUID();
    public String getBossName();
    public String getBossType();
    public int getBossTextColor();
}