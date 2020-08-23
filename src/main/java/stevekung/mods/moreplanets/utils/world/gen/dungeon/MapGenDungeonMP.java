package stevekung.mods.moreplanets.utils.world.gen.dungeon;

import java.util.List;
import java.util.Random;

import micdoodle8.mods.galacticraft.api.world.IGalacticraftWorldProvider;
import micdoodle8.mods.galacticraft.core.world.gen.dungeon.MapGenDungeon;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.structure.MapGenStructure;
import net.minecraft.world.gen.structure.MapGenStructureIO;
import net.minecraft.world.gen.structure.StructureComponent;
import net.minecraft.world.gen.structure.StructureStart;

public class MapGenDungeonMP extends MapGenStructure
{
    protected final DungeonConfigurationMP configuration;

    static
    {
        MapGenStructureIO.registerStructure(Start.class, "MPDungeon");
        MapGenStructureIO.registerStructureComponent(DungeonStartMP.class, "MPDungeonStart");
        MapGenStructureIO.registerStructureComponent(CorridorMP.class, "MPDungeonCorridor");
        MapGenStructureIO.registerStructureComponent(RoomEmptyMP.class, "MPDungeonEmptyRoom");
        MapGenStructureIO.registerStructureComponent(RoomSpawnerMP.class, "MPDungeonSpawnerRoom");
        MapGenStructureIO.registerStructureComponent(RoomChestMP.class, "MPDungeonChestRoom");
        MapGenStructureIO.registerStructureComponent(RoomEntranceMP.class, "MPDungeonEntranceRoom");
    }

    public MapGenDungeonMP(DungeonConfigurationMP configuration)
    {
        this.configuration = configuration;
    }

    @Override
    public String getStructureName()
    {
        return "MPBossDungeon";
    }

    @Override
    protected boolean canSpawnStructureAtCoords(int chunkX, int chunkZ)
    {
        long dungeonPos = MapGenDungeon.getDungeonPosForCoords(this.world, chunkX, chunkZ, ((IGalacticraftWorldProvider) this.world.provider).getDungeonSpacing());
        int i = (int) (dungeonPos >> 32);
        int j = (int) dungeonPos; // Java automatically gives the 32 least significant bits
        return i == chunkX && j == chunkZ;
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