package com.stevekung.moreplanets.planets.nibiru.world.gen.dungeon;

import java.util.List;
import java.util.Random;

import com.stevekung.moreplanets.utils.world.gen.dungeon.DungeonConfigurationMP;
import com.stevekung.moreplanets.utils.world.gen.dungeon.MapGenDungeonMP;

import net.minecraft.world.gen.structure.MapGenStructureIO;
import net.minecraft.world.gen.structure.StructureComponent;
import net.minecraft.world.gen.structure.StructureStart;

public class MapGenNibiruDungeon extends MapGenDungeonMP
{
    static
    {
        MapGenStructureIO.registerStructure(Start.class, "NibiruDungeon");
        MapGenStructureIO.registerStructureComponent(DungeonStartNibiru.class, "NibiruDungeonStart");
        MapGenStructureIO.registerStructureComponent(RoomEmptyNibiru.class, "NibiruDungeonEmptyRoom");
        MapGenStructureIO.registerStructureComponent(RoomSpawnerNibiru.class, "NibiruDungeonSpawnerRoom");
        MapGenStructureIO.registerStructureComponent(RoomChestNibiru.class, "NibiruDungeonChestRoom");
        MapGenStructureIO.registerStructureComponent(RoomBossNibiru.class, "NibiruDungeonBossRoom");
        MapGenStructureIO.registerStructureComponent(RoomTreasureNibiru.class, "NibiruDungeonTreasureRoom");
        MapGenStructureIO.registerStructureComponent(RoomSpawnerNibiru.class, "NibiruDungeonSpawnerRoom");
        MapGenStructureIO.registerStructureComponent(RoomEntranceNibiru.class, "NibiruDungeonEntranceRoom");
        MapGenStructureIO.registerStructureComponent(CorridorNibiru.class, "NibiruDungeonCorridor");
    }

    public MapGenNibiruDungeon(DungeonConfigurationMP configuration)
    {
        super(configuration);
    }

    @Override
    protected StructureStart getStructureStart(int chunkX, int chunkZ)
    {
        return new Start(this.rand, chunkX, chunkZ, this.configuration);
    }

    @Override
    public String getStructureName()
    {
        return "NibiruBossDungeon";
    }

    public static class Start extends StructureStart
    {
        public Start() {}

        public Start(Random rand, int chunkX, int chunkZ, DungeonConfigurationMP configuration)
        {
            super(chunkX, chunkZ);
            DungeonStartNibiru startPiece = new DungeonStartNibiru(configuration, rand, (chunkX << 4) + 2, (chunkZ << 4) + 2);
            startPiece.buildComponent(startPiece, this.components, rand);
            List<StructureComponent> list = startPiece.attachedComponents;

            while (!list.isEmpty())
            {
                int i = rand.nextInt(list.size());
                StructureComponent structurecomponent = list.remove(i);
                structurecomponent.buildComponent(startPiece, this.components, rand);
            }
            this.updateBoundingBox();
        }
    }
}