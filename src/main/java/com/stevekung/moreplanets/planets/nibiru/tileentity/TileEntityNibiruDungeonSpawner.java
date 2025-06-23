package com.stevekung.moreplanets.planets.nibiru.tileentity;

import micdoodle8.mods.galacticraft.core.tile.TileEntityDungeonSpawner;
import com.stevekung.moreplanets.planets.nibiru.entity.EntityMiniVeinFloater;

public class TileEntityNibiruDungeonSpawner extends TileEntityDungeonSpawner<EntityMiniVeinFloater>
{
    public TileEntityNibiruDungeonSpawner()
    {
        super(EntityMiniVeinFloater.class);
    }
}