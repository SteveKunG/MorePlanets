package stevekung.mods.moreplanets.module.planets.nibiru.world.gen.structure;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.structure.MapGenStructure;
import net.minecraft.world.gen.structure.MapGenStructureIO;
import net.minecraft.world.gen.structure.StructureComponent;
import net.minecraft.world.gen.structure.StructureStart;
import stevekung.mods.moreplanets.init.MPBiomes;
import stevekung.mods.moreplanets.util.MPLog;

public class MapGenNibiruVillage extends MapGenStructure
{
    public static List<Biome> VILLAGE_SPAWN_BIOMES = new ArrayList<>(Arrays.asList(MPBiomes.INFECTED_PLAINS, MPBiomes.INFECTED_DESERT, MPBiomes.INFECTED_DEAD_SAVANNA, MPBiomes.GREEN_VEIN));
    private int size;
    private int distance;

    static
    {
        MapGenStructureIO.registerStructure(Start.class, "NibiruVillage");
        StructureNibiruVillagePieces.registerVillagePieces();
    }

    public MapGenNibiruVillage()
    {
        this.distance = 32;
    }

    @Override
    public String getStructureName()
    {
        return "NibiruVillage";
    }

    @Override
    protected boolean canSpawnStructureAtCoords(int chunkX, int chunkZ)
    {
        int i = chunkX;
        int j = chunkZ;

        if (chunkX < 0)
        {
            chunkX -= this.distance - 1;
        }
        if (chunkZ < 0)
        {
            chunkZ -= this.distance - 1;
        }

        int k = chunkX / this.distance;
        int l = chunkZ / this.distance;
        Random random = this.world.setRandomSeed(k, l, 10387312);
        k = k * this.distance;
        l = l * this.distance;
        k = k + random.nextInt(this.distance - 8);
        l = l + random.nextInt(this.distance - 8);

        if (i == k && j == l)
        {
            boolean flag = this.world.getBiomeProvider().areBiomesViable(i * 16 + 8, j * 16 + 8, 0, VILLAGE_SPAWN_BIOMES);

            if (flag)
            {
                return true;
            }
        }
        return false;
    }

    @Override
    protected StructureStart getStructureStart(int chunkX, int chunkZ)
    {
        return new Start(this.world, this.rand, chunkX, chunkZ, this.size);
    }

    @Override
    public BlockPos getNearestStructurePos(World world, BlockPos pos, boolean findUnexplored)
    {
        this.world = world;
        return MapGenStructure.findNearestStructurePosBySpacing(world, this, pos, this.distance, 8, 10387312, false, 100, findUnexplored);
    }

    public static class Start extends StructureStart
    {
        private boolean hasMoreThanTwoComponents;

        public Start() {}

        public Start(World world, Random rand, int x, int z, int size)
        {
            super(x, z);
            MPLog.debug("Generate village at x:{} z:{}", x * 16, z * 16);
            List<StructureNibiruVillagePieces.PieceWeight> list = StructureNibiruVillagePieces.getStructureVillageWeightedPieceList(rand, size);
            StructureNibiruVillagePieces.Start start = new StructureNibiruVillagePieces.Start(world.getBiomeProvider(), rand, (x << 4) + 2, (z << 4) + 2, list, size);
            this.components.add(start);
            start.buildComponent(start, this.components, rand);
            List<StructureComponent> list1 = start.pendingRoads;
            List<StructureComponent> list2 = start.pendingHouses;

            while (!list1.isEmpty() || !list2.isEmpty())
            {
                if (list1.isEmpty())
                {
                    int i = rand.nextInt(list2.size());
                    StructureComponent structurecomponent = list2.remove(i);
                    structurecomponent.buildComponent(start, this.components, rand);
                }
                else
                {
                    int j = rand.nextInt(list1.size());
                    StructureComponent structurecomponent2 = list1.remove(j);
                    structurecomponent2.buildComponent(start, this.components, rand);
                }
            }

            this.updateBoundingBox();
            int k = 0;

            for (StructureComponent structurecomponent1 : this.components)
            {
                if (!(structurecomponent1 instanceof StructureNibiruVillagePieces.Road))
                {
                    ++k;
                }
            }
            this.hasMoreThanTwoComponents = k > 2;
        }

        @Override
        public boolean isSizeableStructure()
        {
            return this.hasMoreThanTwoComponents;
        }

        @Override
        public void writeToNBT(NBTTagCompound tagCompound)
        {
            super.writeToNBT(tagCompound);
            tagCompound.setBoolean("Valid", this.hasMoreThanTwoComponents);
        }

        @Override
        public void readFromNBT(NBTTagCompound tagCompound)
        {
            super.readFromNBT(tagCompound);
            this.hasMoreThanTwoComponents = tagCompound.getBoolean("Valid");
        }
    }
}