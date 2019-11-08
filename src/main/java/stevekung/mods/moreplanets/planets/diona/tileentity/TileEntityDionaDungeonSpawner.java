package stevekung.mods.moreplanets.planets.diona.tileentity;

import stevekung.mods.moreplanets.planets.diona.entity.EntityInfectedPurloniteSlimeBoss;
import stevekung.mods.moreplanets.utils.tileentity.TileEntityDungeonSpawnerMP;

public class TileEntityDionaDungeonSpawner extends TileEntityDungeonSpawnerMP<EntityInfectedPurloniteSlimeBoss>
{
    public TileEntityDionaDungeonSpawner()
    {
        super(EntityInfectedPurloniteSlimeBoss.class);
    }
}