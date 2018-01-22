package stevekung.mods.moreplanets.module.planets.diona.tileentity;

import stevekung.mods.moreplanets.module.planets.diona.entity.EntityInfectedCrystallizeSlimeBoss;
import stevekung.mods.moreplanets.util.tileentity.TileEntityDungeonSpawnerMP;

public class TileEntityDionaDungeonSpawner extends TileEntityDungeonSpawnerMP<EntityInfectedCrystallizeSlimeBoss>
{
    public TileEntityDionaDungeonSpawner()
    {
        super(EntityInfectedCrystallizeSlimeBoss.class);
    }
}