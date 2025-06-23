package com.stevekung.moreplanets.planets.chalos.tileentity;

import com.stevekung.moreplanets.planets.chalos.entity.EntityCheeseCubeEyeBoss;
import com.stevekung.moreplanets.utils.tileentity.TileEntityDungeonSpawnerMP;

public class TileEntityChalosDungeonSpawner extends TileEntityDungeonSpawnerMP<EntityCheeseCubeEyeBoss>
{
    public TileEntityChalosDungeonSpawner()
    {
        super(EntityCheeseCubeEyeBoss.class);
    }
}