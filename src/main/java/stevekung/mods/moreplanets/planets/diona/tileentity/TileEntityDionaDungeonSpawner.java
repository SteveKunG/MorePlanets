package stevekung.mods.moreplanets.planets.diona.tileentity;

import stevekung.mods.moreplanets.planets.diona.entity.EntityInfectedCrystallizedSlimeBoss;
import stevekung.mods.moreplanets.utils.tileentity.TileEntityDungeonSpawnerMP;

public class TileEntityDionaDungeonSpawner extends TileEntityDungeonSpawnerMP<EntityInfectedCrystallizedSlimeBoss>
{
    public TileEntityDionaDungeonSpawner()
    {
        super(EntityInfectedCrystallizedSlimeBoss.class);
    }
}