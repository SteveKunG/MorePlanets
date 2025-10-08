package com.stevekung.moreplanets.utils;

import java.util.UUID;

import net.minecraftforge.fml.common.registry.IEntityAdditionalSpawnData;

import micdoodle8.mods.galacticraft.core.entities.IBoss;

public interface IMorePlanetsBoss extends IBoss, IEntityAdditionalSpawnData
{
    UUID getBossUUID();
    String getBossName();
    BossType getBossType();
    int getBossTextColor();
}