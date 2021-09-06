package stevekung.mods.moreplanets.planets.diona.world.gen.structure;

import java.util.Random;

import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.structure.MapGenStructure;
import net.minecraft.world.gen.structure.MapGenStructureIO;
import net.minecraft.world.gen.structure.StructureStart;
import stevekung.mods.moreplanets.utils.LoggerMP;

public class MapGenAbandonedSatellite extends MapGenStructure
{
    private final int maxDistance = 96;

    static
    {
        MapGenStructureIO.registerStructure(MapGenAbandonedSatellite.Start.class, "AbandonedSatellite");
        MapGenStructureIO.registerStructureComponent(StructureAbandonedSatellitePieces.class, "AbandonedSatellite");
    }

    @Override
    public String getStructureName()
    {
        return "AbandonedSatellite";
    }

    @Override
    protected boolean canSpawnStructureAtCoords(int chunkX, int chunkZ)
    {
        int i = chunkX;
        int j = chunkZ;

        if (chunkX < 0)
        {
            chunkX -= this.maxDistance - 1;
        }

        if (chunkZ < 0)
        {
            chunkZ -= this.maxDistance - 1;
        }

        int k = chunkX / this.maxDistance;
        int l = chunkZ / this.maxDistance;
        Random random = this.world.setRandomSeed(k, l, 14357617);
        k = k * this.maxDistance;
        l = l * this.maxDistance;
        k = k + random.nextInt(this.maxDistance - 8);
        l = l + random.nextInt(this.maxDistance - 8);

        if (i == k && j == l)
        {
            return true;
        }
        return false;
    }

    @Override
    public BlockPos getNearestStructurePos(World world, BlockPos pos, boolean findUnexplored)
    {
        this.world = world;
        return findNearestStructurePosBySpacing(world, this, pos, this.maxDistance, 8, 14357617, false, 100, findUnexplored);
    }

    @Override
    protected StructureStart getStructureStart(int chunkX, int chunkZ)
    {
        return new MapGenAbandonedSatellite.Start(this.world, this.rand, chunkX, chunkZ);
    }

    public static class Start extends StructureStart
    {
        public Start() {}

        public Start(World world, Random rand, int chunkX, int chunkZ)
        {
            super(chunkX, chunkZ);
            LoggerMP.debug("Generate Abandoned Satellite at {} {}", chunkX * 16, chunkZ * 16);
            StructureAbandonedSatellitePieces igloo = new StructureAbandonedSatellitePieces(rand, chunkX * 16, chunkZ * 16);
            this.components.add(igloo);
            this.updateBoundingBox();
        }
    }
}