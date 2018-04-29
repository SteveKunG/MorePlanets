package stevekung.mods.moreplanets.utils.world.gen.dungeon;

import java.util.List;
import java.util.Random;

import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.structure.MapGenStructure;
import net.minecraft.world.gen.structure.MapGenStructureIO;
import net.minecraft.world.gen.structure.StructureComponent;
import net.minecraft.world.gen.structure.StructureStart;

public class MapGenDungeonMP extends MapGenStructure
{
    private static boolean initialized;
    protected DungeonConfigurationMP configuration;

    static
    {
        try
        {
            MapGenDungeonMP.initiateStructures();
        }
        catch (Throwable e) {}
    }

    public MapGenDungeonMP(DungeonConfigurationMP configuration)
    {
        this.configuration = configuration;
    }

    public static void initiateStructures() throws Throwable
    {
        if (!MapGenDungeonMP.initialized)
        {
            MapGenStructureIO.registerStructure(Start.class, "MPDungeon");
            MapGenStructureIO.registerStructureComponent(DungeonStartMP.class, "MPDungeonStart");
            MapGenStructureIO.registerStructureComponent(CorridorMP.class, "MPDungeonCorridor");
            MapGenStructureIO.registerStructureComponent(RoomEmptyMP.class, "MPDungeonEmptyRoom");
            MapGenStructureIO.registerStructureComponent(RoomSpawnerMP.class, "MPDungeonSpawnerRoom");
            MapGenStructureIO.registerStructureComponent(RoomChestMP.class, "MPDungeonChestRoom");
        }
        MapGenDungeonMP.initialized = true;
    }

    @Override
    public String getStructureName()
    {
        return "MPBossDungeon";
    }

    @Override
    protected boolean canSpawnStructureAtCoords(int chunkX, int chunkZ)
    {
        byte numChunks = 44;
        int i = chunkX;
        int j = chunkZ;

        if (chunkX < 0)
        {
            chunkX -= numChunks - 1;
        }
        if (chunkZ < 0)
        {
            chunkZ -= numChunks - 1;
        }
        int k = chunkX / numChunks;
        int l = chunkZ / numChunks;
        Random random = this.world.setRandomSeed(k, l, 10387312);
        k = k * numChunks;
        l = l * numChunks;
        k = k + random.nextInt(numChunks);
        l = l + random.nextInt(numChunks);
        return i == k && j == l;
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
            DungeonStartMP startPiece = new DungeonStartMP(world, configuration, rand, (chunkX << 4) + 2, (chunkZ << 4) + 2);
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
    public BlockPos getNearestStructurePos(World world, BlockPos pos, boolean findUnexplored)
    {
        return null;
    }
}