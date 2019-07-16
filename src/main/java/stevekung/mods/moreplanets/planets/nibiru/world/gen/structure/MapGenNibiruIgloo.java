package stevekung.mods.moreplanets.planets.nibiru.world.gen.structure;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.structure.MapGenStructure;
import net.minecraft.world.gen.structure.MapGenStructureIO;
import net.minecraft.world.gen.structure.StructureStart;
import stevekung.mods.moreplanets.init.MPBiomes;
import stevekung.mods.moreplanets.utils.LoggerMP;

public class MapGenNibiruIgloo extends MapGenStructure
{
    private static final List<Biome> BIOMES = Arrays.asList(MPBiomes.INFECTED_ICE_SPIKES, MPBiomes.INFECTED_SNOWY_TAIGA);
    private final int maxDistanceBetweenScatteredFeatures;

    static
    {
        MapGenStructureIO.registerStructure(MapGenNibiruIgloo.Start.class, "NibiruIgloo");
        MapGenStructureIO.registerStructureComponent(StructureNibiruIglooPieces.class, "NibiruIgloo");
    }

    public MapGenNibiruIgloo()
    {
        this.maxDistanceBetweenScatteredFeatures = 32;
    }

    @Override
    public String getStructureName()
    {
        return "NibiruIgloo";
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
        Random rand = this.world.setRandomSeed(k, l, 14357617);
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
            for (Biome biome1 : BIOMES)
            {
                if (biome == biome1)
                {
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public BlockPos getNearestStructurePos(World world, BlockPos pos, boolean findUnexplored)
    {
        this.world = world;
        return findNearestStructurePosBySpacing(world, this, pos, this.maxDistanceBetweenScatteredFeatures, 8, 14357617, false, 100, findUnexplored);
    }

    @Override
    protected StructureStart getStructureStart(int chunkX, int chunkZ)
    {
        return new MapGenNibiruIgloo.Start(this.world, this.rand, chunkX, chunkZ);
    }

    public static class Start extends StructureStart
    {
        public Start() {}

        public Start(World world, Random rand, int chunkX, int chunkZ)
        {
            super(chunkX, chunkZ);
            LoggerMP.debug("Generate nibiru igloo at {} {}", chunkX * 16, chunkZ * 16);
            StructureNibiruIglooPieces igloo = new StructureNibiruIglooPieces(rand, chunkX * 16, chunkZ * 16);
            this.components.add(igloo);
            this.updateBoundingBox();
        }
    }
}