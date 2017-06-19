package stevekung.mods.moreplanets.module.planets.nibiru.world.gen.structure;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Random;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.gen.structure.MapGenStructure;
import net.minecraft.world.gen.structure.MapGenStructureIO;
import net.minecraft.world.gen.structure.StructureComponent;
import net.minecraft.world.gen.structure.StructureStart;
import stevekung.mods.moreplanets.init.MPBiomes;
import stevekung.mods.moreplanets.util.MPLog;

public class MapGenNibiruVillage extends MapGenStructure
{
    public static List<BiomeGenBase> villageSpawnBiomes = Arrays.<BiomeGenBase>asList(new BiomeGenBase[] {MPBiomes.INFECTED_PLAINS, MPBiomes.INFECTED_DESERT, MPBiomes.INFECTED_DEAD_SAVANNA, MPBiomes.GREEN_VEIN});
    private int terrainType;
    private int distance;
    private int nearDistance;

    static
    {
        MapGenStructureIO.registerStructure(MapGenNibiruVillage.Start.class, "NibiruVillage");
        StructureNibiruVillagePieces.registerVillagePieces();
    }

    public MapGenNibiruVillage()
    {
        this.distance = 32;
        this.nearDistance = 8;
    }

    public MapGenNibiruVillage(Map<String, String> map)
    {
        this();

        for (Entry<String, String> entry : map.entrySet())
        {
            if (entry.getKey().equals("size"))
            {
                this.terrainType = MathHelper.parseIntWithDefaultAndMax(entry.getValue(), this.terrainType, 0);
            }
            else if (entry.getKey().equals("distance"))
            {
                this.distance = MathHelper.parseIntWithDefaultAndMax(entry.getValue(), this.distance, this.nearDistance + 1);
            }
        }
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
        Random random = this.worldObj.setRandomSeed(k, l, 10387312);
        k = k * this.distance;
        l = l * this.distance;
        k = k + random.nextInt(this.distance - this.nearDistance);
        l = l + random.nextInt(this.distance - this.nearDistance);

        if (i == k && j == l)
        {
            boolean flag = this.worldObj.getWorldChunkManager().areBiomesViable(i * 16 + 8, j * 16 + 8, 0, MapGenNibiruVillage.villageSpawnBiomes);

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
        return new MapGenNibiruVillage.Start(this.worldObj, this.rand, chunkX, chunkZ, this.terrainType);
    }

    public static class Start extends StructureStart
    {
        private boolean hasMoreThanTwoComponents;

        public Start() {}

        public Start(World world, Random rand, int chunkX, int chunkZ, int p_i2092_5_)
        {
            super(chunkX, chunkZ);
            MPLog.debug("Generate village at %s %s", chunkX * 16, chunkZ * 16);
            List<StructureNibiruVillagePieces.PieceWeight> list = StructureNibiruVillagePieces.getStructureVillageWeightedPieceList(rand, p_i2092_5_);
            StructureNibiruVillagePieces.Start structurevillagepieces$start = new StructureNibiruVillagePieces.Start(world.getWorldChunkManager(), 0, rand, (chunkX << 4) + 2, (chunkZ << 4) + 2, list, p_i2092_5_);
            this.components.add(structurevillagepieces$start);
            structurevillagepieces$start.buildComponent(structurevillagepieces$start, this.components, rand);
            List<StructureComponent> list1 = structurevillagepieces$start.field_74930_j;
            List<StructureComponent> list2 = structurevillagepieces$start.field_74932_i;

            while (!list1.isEmpty() || !list2.isEmpty())
            {
                if (list1.isEmpty())
                {
                    int i = rand.nextInt(list2.size());
                    StructureComponent structurecomponent = list2.remove(i);
                    structurecomponent.buildComponent(structurevillagepieces$start, this.components, rand);
                }
                else
                {
                    int j = rand.nextInt(list1.size());
                    StructureComponent structurecomponent2 = list1.remove(j);
                    structurecomponent2.buildComponent(structurevillagepieces$start, this.components, rand);
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