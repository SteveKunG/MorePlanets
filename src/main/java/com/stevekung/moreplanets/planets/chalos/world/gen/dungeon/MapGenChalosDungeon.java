package com.stevekung.moreplanets.planets.chalos.world.gen.dungeon;

import net.minecraft.world.gen.structure.MapGenStructureIO;
import com.stevekung.moreplanets.utils.world.gen.dungeon.DungeonConfigurationMP;
import com.stevekung.moreplanets.utils.world.gen.dungeon.MapGenDungeonMP;

public class MapGenChalosDungeon extends MapGenDungeonMP
{
    static
    {
        MapGenStructureIO.registerStructureComponent(RoomBossChalos.class, "ChalosDungeonBossRoom");
        MapGenStructureIO.registerStructureComponent(RoomTreasureChalos.class, "ChalosDungeonTreasureRoom");
        MapGenStructureIO.registerStructureComponent(RoomSpawnerChalos.class, "ChalosDungeonSpawnerRoom");
    }

    public MapGenChalosDungeon(DungeonConfigurationMP configuration)
    {
        super(configuration);
    }

    @Override
    public String getStructureName()
    {
        return "ChalosBossDungeon";
    }
}