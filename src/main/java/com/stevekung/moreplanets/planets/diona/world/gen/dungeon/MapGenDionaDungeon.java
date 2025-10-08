package com.stevekung.moreplanets.planets.diona.world.gen.dungeon;

import com.stevekung.moreplanets.utils.world.gen.dungeon.DungeonConfigurationMP;
import com.stevekung.moreplanets.utils.world.gen.dungeon.MapGenDungeonMP;

import net.minecraft.world.gen.structure.MapGenStructureIO;

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