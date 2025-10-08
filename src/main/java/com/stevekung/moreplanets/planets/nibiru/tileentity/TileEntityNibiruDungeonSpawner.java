package com.stevekung.moreplanets.planets.nibiru.tileentity;

import com.stevekung.moreplanets.planets.nibiru.entity.EntityMiniVeinFloater;

import micdoodle8.mods.galacticraft.core.tile.TileEntityDungeonSpawner;

public class TileEntityNibiruDungeonSpawner extends TileEntityDungeonSpawner<EntityMiniVeinFloater>
{
    public TileEntityNibiruDungeonSpawner()
    {
        super(EntityMiniVeinFloater.class);
    }
}