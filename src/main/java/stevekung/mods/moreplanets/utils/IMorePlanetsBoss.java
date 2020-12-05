package stevekung.mods.moreplanets.utils;

import java.util.UUID;

import micdoodle8.mods.galacticraft.core.entities.IBoss;
import net.minecraftforge.fml.common.registry.IEntityAdditionalSpawnData;

public interface IMorePlanetsBoss extends IBoss, IEntityAdditionalSpawnData
{
    UUID getBossUUID();
    String getBossName();
    BossType getBossType();
    int getBossTextColor();
}