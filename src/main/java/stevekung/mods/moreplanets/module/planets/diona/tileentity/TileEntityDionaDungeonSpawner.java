package stevekung.mods.moreplanets.module.planets.diona.tileentity;

import stevekung.mods.moreplanets.module.planets.diona.entity.EntityInfectedCrystallizedSlimeBoss;
import stevekung.mods.moreplanets.util.tileentity.TileEntityDungeonSpawnerMP;

public class TileEntityDionaDungeonSpawner extends TileEntityDungeonSpawnerMP<EntityInfectedCrystallizedSlimeBoss>
{
    public TileEntityDionaDungeonSpawner()
    {
        super(EntityInfectedCrystallizedSlimeBoss.class);
    }
}