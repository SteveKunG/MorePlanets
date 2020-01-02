package stevekung.mods.moreplanets.planets.diona.world.gen.structure;

import java.util.Random;

import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.structure.MapGenStructure;
import net.minecraft.world.gen.structure.MapGenStructureIO;
import net.minecraft.world.gen.structure.StructureStart;
import stevekung.mods.moreplanets.init.MPBiomes;
import stevekung.mods.moreplanets.utils.LoggerMP;

public class MapGenAbandonedSatellite extends MapGenStructure
{
    private final int maxDistanceBetweenScatteredFeatures;

    static
    {
        MapGenStructureIO.registerStructure(MapGenAbandonedSatellite.Start.class, "AbandonedSatellite");
        MapGenStructureIO.registerStructureComponent(StructureAbandonedSatellitePieces.class, "AbandonedSatellite");
    }

    public MapGenAbandonedSatellite()
    {
        this.maxDistanceBetweenScatteredFeatures = 256;
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
            chunkX -= this.maxDistanceBetweenScatteredFeatures - 1;
        }
        if (chunkZ < 0)
        {
            chunkZ -= this.maxDistanceBetweenScatteredFeatures - 1;
        }

        int k = chunkX / this.maxDistanceBetweenScatteredFeatures;
        int l = chunkZ / this.maxDistanceBetweenScatteredFeatures;
        Random rand = this.world.setRandomSeed(k, l, 19029028);
        k = k * this.maxDistanceBetweenScatteredFeatures;
        l = l * this.maxDistanceBetweenScatteredFeatures;
        k = k + rand.nextInt(this.maxDistanceBetweenScatteredFeatures - 8);
        l = l + rand.nextInt(this.maxDistanceBetweenScatteredFeatures - 8);

        if (i == k && j == l)
        {
            Biome biome = this.world.getBiomeProvider().getBiome(new BlockPos(i * 16 + 8, 0, j * 16 + 8));

            if (biome == null)
            {
                return false;
            }
            if (biome == MPBiomes.DIONA)
            {
                return true;
            }
        }
        return false;
    }

    @Override
    public BlockPos getNearestStructurePos(World world, BlockPos pos, boolean findUnexplored)
    {
        this.world = world;
        return findNearestStructurePosBySpacing(world, this, pos, this.maxDistanceBetweenScatteredFeatures, 8, 19029028, false, 150, findUnexplored);
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