package stevekung.mods.moreplanets.planets.chalos.tileentity;

import stevekung.mods.moreplanets.planets.chalos.entity.EntityCheeseCubeEyeBoss;
import stevekung.mods.moreplanets.utils.tileentity.TileEntityDungeonSpawnerMP;

public class TileEntityChalosDungeonSpawner extends TileEntityDungeonSpawnerMP<EntityCheeseCubeEyeBoss>
{
    public TileEntityChalosDungeonSpawner()
    {
        super(EntityCheeseCubeEyeBoss.class);
    }
}