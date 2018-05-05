package stevekung.mods.moreplanets.planets.chalos.world.gen.dungeon;

import net.minecraft.world.gen.structure.MapGenStructureIO;
import stevekung.mods.moreplanets.utils.world.gen.dungeon.DungeonConfigurationMP;
import stevekung.mods.moreplanets.utils.world.gen.dungeon.MapGenDungeonMP;

public class MapGenChalosDungeon extends MapGenDungeonMP
{
    private static boolean initialized;

    static
    {
        try
        {
            MapGenChalosDungeon.initiateStructures();
        }
        catch (Throwable e) {}
    }

    public MapGenChalosDungeon(DungeonConfigurationMP configuration)
    {
        super(configuration);
    }

    public static void initiateStructures() throws Throwable
    {
        if (!MapGenChalosDungeon.initialized)
        {
            MapGenStructureIO.registerStructureComponent(RoomBossChalos.class, "ChalosDungeonBossRoom");
            MapGenStructureIO.registerStructureComponent(RoomTreasureChalos.class, "ChalosDungeonTreasureRoom");
            MapGenStructureIO.registerStructureComponent(RoomSpawnerChalos.class, "ChalosDungeonSpawnerRoom");
        }
        MapGenChalosDungeon.initialized = true;
    }

    @Override
    public String getStructureName()
    {
        return "ChalosBossDungeon";
    }
}