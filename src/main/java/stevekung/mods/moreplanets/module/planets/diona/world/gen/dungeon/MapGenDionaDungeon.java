package stevekung.mods.moreplanets.module.planets.diona.world.gen.dungeon;

import net.minecraft.world.gen.structure.MapGenStructureIO;
import stevekung.mods.moreplanets.utils.world.gen.dungeon.DungeonConfigurationMP;
import stevekung.mods.moreplanets.utils.world.gen.dungeon.MapGenDungeonMP;

public class MapGenDionaDungeon extends MapGenDungeonMP
{
    private static boolean initialized;

    static
    {
        try
        {
            MapGenDionaDungeon.initiateStructures();
        }
        catch (Throwable e) {}
    }

    public MapGenDionaDungeon(DungeonConfigurationMP configuration)
    {
        super(configuration);
    }

    public static void initiateStructures() throws Throwable
    {
        if (!MapGenDionaDungeon.initialized)
        {
            MapGenStructureIO.registerStructureComponent(RoomBossDiona.class, "DionaDungeonBossRoom");
            MapGenStructureIO.registerStructureComponent(RoomTreasureDiona.class, "DionaDungeonTreasureRoom");
            MapGenStructureIO.registerStructureComponent(RoomSpawnerDiona.class, "DionaDungeonSpawnerRoom");
        }
        MapGenDionaDungeon.initialized = true;
    }

    @Override
    public String getStructureName()
    {
        return "DionaBossDungeon";
    }
}