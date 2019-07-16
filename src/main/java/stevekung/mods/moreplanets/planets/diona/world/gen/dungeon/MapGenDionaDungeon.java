package stevekung.mods.moreplanets.planets.diona.world.gen.dungeon;

import net.minecraft.world.gen.structure.MapGenStructureIO;
import stevekung.mods.moreplanets.utils.world.gen.dungeon.DungeonConfigurationMP;
import stevekung.mods.moreplanets.utils.world.gen.dungeon.MapGenDungeonMP;

public class MapGenDionaDungeon extends MapGenDungeonMP
{
    static
    {
        MapGenStructureIO.registerStructureComponent(RoomBossDiona.class, "DionaDungeonBossRoom");
        MapGenStructureIO.registerStructureComponent(RoomTreasureDiona.class, "DionaDungeonTreasureRoom");
        MapGenStructureIO.registerStructureComponent(RoomSpawnerDiona.class, "DionaDungeonSpawnerRoom");
    }

    public MapGenDionaDungeon(DungeonConfigurationMP configuration)
    {
        super(configuration);
    }

    @Override
    public String getStructureName()
    {
        return "DionaBossDungeon";
    }
}