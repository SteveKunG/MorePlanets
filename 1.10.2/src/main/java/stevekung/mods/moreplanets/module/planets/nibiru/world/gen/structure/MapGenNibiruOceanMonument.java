package stevekung.mods.moreplanets.module.planets.nibiru.world.gen.structure;

import java.util.*;
import java.util.Map.Entry;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome.SpawnListEntry;
import net.minecraft.world.gen.structure.MapGenStructure;
import net.minecraft.world.gen.structure.MapGenStructureIO;
import net.minecraft.world.gen.structure.StructureBoundingBox;
import net.minecraft.world.gen.structure.StructureStart;
import stevekung.mods.moreplanets.init.MPBiomes;
import stevekung.mods.moreplanets.module.planets.nibiru.entity.EntityInfectedGuardian;
import stevekung.mods.moreplanets.util.MPLog;

public class MapGenNibiruOceanMonument extends MapGenStructure
{
    private int spacing;
    private int separation;
    public static List<BiomeGenBase> biomeListToGen = Arrays.<BiomeGenBase>asList(new BiomeGenBase[] {MPBiomes.INFECTED_OCEAN, MPBiomes.INFECTED_DEEP_OCEAN});
    private static List<SpawnListEntry> monsterSpawnList = Lists.<SpawnListEntry>newArrayList();

    static
    {
        MapGenStructureIO.registerStructure(Start.class, "NibiruOceanMonument");
        StructureNibiruOceanMonumentPieces.registerOceanMonumentPieces();
        MapGenNibiruOceanMonument.monsterSpawnList.add(new SpawnListEntry(EntityInfectedGuardian.class, 1, 2, 4));
    }

    public MapGenNibiruOceanMonument()
    {
        this.spacing = 8;
        this.separation = 1;
    }

    public MapGenNibiruOceanMonument(Map<String, String> map)
    {
        this();

        for (Entry<String, String> entry : map.entrySet())
        {
            if (entry.getKey().equals("spacing"))
            {
                this.spacing = MathHelper.parseIntWithDefaultAndMax(entry.getValue(), this.spacing, 1);
            }
            else if (entry.getKey().equals("separation"))
            {
                this.separation = MathHelper.parseIntWithDefaultAndMax(entry.getValue(), this.separation, 1);
            }
        }
    }

    @Override
    public String getStructureName()
    {
        return "NibiruOceanMonument";
    }

    @Override
    protected boolean canSpawnStructureAtCoords(int chunkX, int chunkZ)
    {
        int i = chunkX;
        int j = chunkZ;

        if (chunkX < 0)
        {
            chunkX -= this.spacing - 1;
        }
        if (chunkZ < 0)
        {
            chunkZ -= this.spacing - 1;
        }

        int k = chunkX / this.spacing;
        int l = chunkZ / this.spacing;
        Random random = this.worldObj.setRandomSeed(k, l, 10387313);
        k = k * this.spacing;
        l = l * this.spacing;
        k = k + (random.nextInt(this.spacing - this.separation) + random.nextInt(this.spacing - this.separation)) / 2;
        l = l + (random.nextInt(this.spacing - this.separation) + random.nextInt(this.spacing - this.separation)) / 2;

        if (i == k && j == l)
        {
            BiomeGenBase getBiome = this.worldObj.getWorldChunkManager().getBiomeGenerator(new BlockPos(i * 16 + 8, 64, j * 16 + 8), (BiomeGenBase)null);

            if (getBiome != MPBiomes.INFECTED_OCEAN && getBiome != MPBiomes.INFECTED_DEEP_OCEAN)
            {
                return false;
            }

            boolean flag = this.worldObj.getWorldChunkManager().areBiomesViable(i * 16 + 8, j * 16 + 8, 29, MapGenNibiruOceanMonument.biomeListToGen);

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
        return new MapGenNibiruOceanMonument.Start(this.worldObj, this.rand, chunkX, chunkZ);
    }

    public List<SpawnListEntry> getSpawnList()
    {
        return MapGenNibiruOceanMonument.monsterSpawnList;
    }

    public static class Start extends StructureStart
    {
        private Set<ChunkCoordIntPair> field_175791_c = Sets.<ChunkCoordIntPair>newHashSet();
        private boolean field_175790_d;

        public Start() {}

        public Start(World world, Random rand, int chunkX, int chunkZ)
        {
            super(chunkX, chunkZ);
            MPLog.debug("Generate ocean monument at %s %s", chunkX * 16, chunkZ * 16);
            this.func_175789_b(world, rand, chunkX, chunkZ);
        }

        private void func_175789_b(World world, Random p_175789_2_, int p_175789_3_, int p_175789_4_)
        {
            p_175789_2_.setSeed(world.getSeed());
            long i = p_175789_2_.nextLong();
            long j = p_175789_2_.nextLong();
            long k = p_175789_3_ * i;
            long l = p_175789_4_ * j;
            p_175789_2_.setSeed(k ^ l ^ world.getSeed());
            int i1 = p_175789_3_ * 16 + 8 - 29;
            int j1 = p_175789_4_ * 16 + 8 - 29;
            EnumFacing enumfacing = EnumFacing.Plane.HORIZONTAL.random(p_175789_2_);
            this.components.add(new StructureNibiruOceanMonumentPieces.MonumentBuilding(p_175789_2_, i1, j1, enumfacing));
            this.updateBoundingBox();
            this.field_175790_d = true;
        }

        @Override
        public void generateStructure(World world, Random rand, StructureBoundingBox structurebb)
        {
            if (!this.field_175790_d)
            {
                this.components.clear();
                this.func_175789_b(world, rand, this.getChunkPosX(), this.getChunkPosZ());
            }
            super.generateStructure(world, rand, structurebb);
        }

        @Override
        public boolean func_175788_a(ChunkCoordIntPair pair)
        {
            return this.field_175791_c.contains(pair) ? false : super.func_175788_a(pair);
        }

        @Override
        public void func_175787_b(ChunkCoordIntPair pair)
        {
            super.func_175787_b(pair);
            this.field_175791_c.add(pair);
        }

        @Override
        public void writeToNBT(NBTTagCompound tagCompound)
        {
            super.writeToNBT(tagCompound);
            NBTTagList nbttaglist = new NBTTagList();

            for (ChunkCoordIntPair chunkcoordintpair : this.field_175791_c)
            {
                NBTTagCompound nbttagcompound = new NBTTagCompound();
                nbttagcompound.setInteger("X", chunkcoordintpair.chunkXPos);
                nbttagcompound.setInteger("Z", chunkcoordintpair.chunkZPos);
                nbttaglist.appendTag(nbttagcompound);
            }
            tagCompound.setTag("Processed", nbttaglist);
        }

        @Override
        public void readFromNBT(NBTTagCompound tagCompound)
        {
            super.readFromNBT(tagCompound);

            if (tagCompound.hasKey("Processed", 9))
            {
                NBTTagList nbttaglist = tagCompound.getTagList("Processed", 10);

                for (int i = 0; i < nbttaglist.tagCount(); ++i)
                {
                    NBTTagCompound nbttagcompound = nbttaglist.getCompoundTagAt(i);
                    this.field_175791_c.add(new ChunkCoordIntPair(nbttagcompound.getInteger("X"), nbttagcompound.getInteger("Z")));
                }
            }
        }
    }
}