package stevekung.mods.moreplanets.planets.nibiru.world.gen.dungeon;

import java.util.List;
import java.util.Random;

import net.minecraft.world.World;
import net.minecraft.world.gen.structure.MapGenStructureIO;
import net.minecraft.world.gen.structure.StructureComponent;
import net.minecraft.world.gen.structure.StructureStart;
import stevekung.mods.moreplanets.utils.world.gen.dungeon.DungeonConfigurationMP;
import stevekung.mods.moreplanets.utils.world.gen.dungeon.MapGenDungeonMP;

public class MapGenNibiruDungeon extends MapGenDungeonMP
{
    private static boolean initialized;

    static
    {
        try
        {
            MapGenNibiruDungeon.initiateStructures();
        }
        catch (Throwable e) {}
    }

    public MapGenNibiruDungeon(DungeonConfigurationMP configuration)
    {
        super(configuration);
    }

    public static void initiateStructures() throws Throwable
    {
        if (!MapGenNibiruDungeon.initialized)
        {
            MapGenStructureIO.registerStructure(Start.class, "NibiruDungeon");
            MapGenStructureIO.registerStructureComponent(DungeonStartNibiru.class, "NibiruDungeonStart");
            MapGenStructureIO.registerStructureComponent(RoomEmptyNibiru.class, "NibiruDungeonEmptyRoom");
            MapGenStructureIO.registerStructureComponent(RoomSpawnerNibiru.class, "NibiruDungeonSpawnerRoom");
            MapGenStructureIO.registerStructureComponent(RoomChestNibiru.class, "NibiruDungeonChestRoom");
            MapGenStructureIO.registerStructureComponent(RoomBossNibiru.class, "NibiruDungeonBossRoom");
            MapGenStructureIO.registerStructureComponent(RoomTreasureNibiru.class, "NibiruDungeonTreasureRoom");
            MapGenStructureIO.registerStructureComponent(RoomSpawnerNibiru.class, "NibiruDungeonSpawnerRoom");
            MapGenStructureIO.registerStructureComponent(CorridorNibiru.class, "NibiruCorridor");
        }
        MapGenNibiruDungeon.initialized = true;
    }

    @Override
    protected StructureStart getStructureStart(int chunkX, int chunkZ)
    {
        return new Start(this.world, this.rand, chunkX, chunkZ, this.configuration);
    }

    public static class Start extends StructureStart
    {
        public Start() {}

        public Start(World world, Random rand, int chunkX, int chunkZ, DungeonConfigurationMP configuration)
        {
            super(chunkX, chunkZ);
            DungeonStartNibiru startPiece = new DungeonStartNibiru(world, configuration, rand, (chunkX << 4) + 2, (chunkZ << 4) + 2);
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

    @Override
    public String getStructureName()
    {
        return "NibiruBossDungeon";
    }
}