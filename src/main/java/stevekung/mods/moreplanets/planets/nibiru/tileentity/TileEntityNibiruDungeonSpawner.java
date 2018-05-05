package stevekung.mods.moreplanets.planets.nibiru.tileentity;

import micdoodle8.mods.galacticraft.core.tile.TileEntityDungeonSpawner;
import stevekung.mods.moreplanets.planets.nibiru.entity.EntityMiniVeinFloater;

public class TileEntityNibiruDungeonSpawner extends TileEntityDungeonSpawner<EntityMiniVeinFloater>
{
    public TileEntityNibiruDungeonSpawner()
    {
        super(EntityMiniVeinFloater.class);
    }
}