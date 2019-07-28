package stevekung.mods.moreplanets.utils;

import java.util.UUID;

import micdoodle8.mods.galacticraft.core.entities.IBoss;
import net.minecraftforge.fml.common.registry.IEntityAdditionalSpawnData;

public interface IMorePlanetsBoss extends IBoss, IEntityAdditionalSpawnData
{
    public UUID getBossUUID();
    public String getBossName();
    public BossType getBossType();
    public int getBossTextColor();
}