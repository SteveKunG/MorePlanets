package stevekung.mods.moreplanets.planets.chalos.world.gen;

import java.util.Random;

import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.structure.MapGenStructure;
import net.minecraft.world.gen.structure.MapGenStructureIO;
import net.minecraft.world.gen.structure.StructureStart;
import stevekung.mods.moreplanets.init.MPBiomes;
import stevekung.mods.moreplanets.planets.chalos.world.gen.structure.ComponentCheeseSporeHutPieces;

public class MapGenCheeseSporeHutFeature extends MapGenStructure
{
    static
    {
        MapGenStructureIO.registerStructure(MapGenCheeseSporeHutFeature.Start.class, "CheeseSporeHut");
        MapGenStructureIO.registerStructureComponent(ComponentCheeseSporeHutPieces.CheeseSporeHut.class, "ChalosHut");
    }

    private int maxDistanceBetweenScatteredFeatures;

    public MapGenCheeseSporeHutFeature()
    {
        this.maxDistanceBetweenScatteredFeatures = 48;
    }

    @Override
    public String getStructureName()
    {
        return "CheeseSporeHut";
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
            if (biome == MPBiomes.CHALOS_PLAINS)
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
        return findNearestStructurePosBySpacing(world, this, pos, this.maxDistanceBetweenScatteredFeatures, 8, 14357617, false, 100, findUnexplored);
    }

    @Override
    protected StructureStart getStructureStart(int chunkX, int chunkZ)
    {
        return new MapGenCheeseSporeHutFeature.Start(this.world, this.rand, chunkX, chunkZ);
    }

    public static class Start extends StructureStart
    {
        public Start() {}

        public Start(World world, Random rand, int chunkX, int chunkZ)
        {
            super(chunkX, chunkZ);
            ComponentCheeseSporeHutPieces.CheeseSporeHut component = new ComponentCheeseSporeHutPieces.CheeseSporeHut(rand, chunkX * 16, chunkZ * 16);
            this.components.add(component);
            this.updateBoundingBox();
        }
    }
}