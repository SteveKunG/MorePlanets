package stevekung.mods.moreplanets.module.planets.nibiru.world.gen.structure;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Random;

import com.google.common.collect.Lists;

import net.minecraft.util.BlockPos;
import net.minecraft.util.MathHelper;
import net.minecraft.world.ChunkCoordIntPair;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.gen.structure.MapGenStructure;
import net.minecraft.world.gen.structure.MapGenStructureIO;
import net.minecraft.world.gen.structure.StructureComponent;
import net.minecraft.world.gen.structure.StructureStart;
import stevekung.mods.moreplanets.util.MPLog;

public class MapGenNibiruStronghold extends MapGenStructure
{
    public List<BiomeGenBase> biomeToGen;
    private boolean ranBiomeCheck;
    private ChunkCoordIntPair[] structureCoords;
    private double distance;
    private int spread;

    static
    {
        MapGenStructureIO.registerStructure(Start.class, "NibiruStronghold");
        StructureNibiruStrongholdPieces.registerStrongholdPieces();
    }

    public MapGenNibiruStronghold()
    {
        this.structureCoords = new ChunkCoordIntPair[3];
        this.distance = 32.0D;
        this.spread = 128;
        this.biomeToGen = Lists.<BiomeGenBase>newArrayList();

        for (BiomeGenBase biomegenbase : BiomeGenBase.getBiomeGenArray())
        {
            if (biomegenbase != null && biomegenbase.minHeight > 0.0F)
            {
                this.biomeToGen.add(biomegenbase);
            }
        }
    }

    public MapGenNibiruStronghold(Map<String, String> map)
    {
        this();

        for (Entry<String, String> entry : map.entrySet())
        {
            if (entry.getKey().equals("distance"))
            {
                this.distance = MathHelper.parseDoubleWithDefaultAndMax(entry.getValue(), this.distance, 1.0D);
            }
            else if (entry.getKey().equals("count"))
            {
                this.structureCoords = new ChunkCoordIntPair[MathHelper.parseIntWithDefaultAndMax(entry.getValue(), this.structureCoords.length, 1)];
            }
            else if (entry.getKey().equals("spread"))
            {
                this.spread = MathHelper.parseIntWithDefaultAndMax(entry.getValue(), this.spread, 1);
            }
        }
    }

    public boolean canMobSpawn(BlockPos pos)
    {
        StructureStart structurestart = this.func_175797_c(pos);

        if (structurestart != null && structurestart instanceof Start && !structurestart.getComponents().isEmpty())
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    @Override
    public String getStructureName()
    {
        return "NibiruStronghold";
    }

    @Override
    protected boolean canSpawnStructureAtCoords(int chunkX, int chunkZ)
    {
        if (!this.ranBiomeCheck)
        {
            Random random = new Random();
            random.setSeed(this.worldObj.getSeed());
            double d0 = random.nextDouble() * Math.PI * 2.0D;
            int i = 1;

            for (int j = 0; j < this.structureCoords.length; ++j)
            {
                double d1 = (1.25D * i + random.nextDouble()) * this.distance * i;
                int k = (int)Math.round(Math.cos(d0) * d1);
                int l = (int)Math.round(Math.sin(d0) * d1);
                BlockPos blockpos = this.worldObj.getWorldChunkManager().findBiomePosition((k << 4) + 8, (l << 4) + 8, 112, this.biomeToGen, random);

                if (blockpos != null)
                {
                    k = blockpos.getX() >> 4;
                l = blockpos.getZ() >> 4;
                }

                this.structureCoords[j] = new ChunkCoordIntPair(k, l);
                d0 += Math.PI * 2D * i / this.spread;

                if (j == this.spread)
                {
                    i += 2 + random.nextInt(5);
                    this.spread += 1 + random.nextInt(2);
                }
            }
            this.ranBiomeCheck = true;
        }

        for (ChunkCoordIntPair chunkcoordintpair : this.structureCoords)
        {
            if (chunkX == chunkcoordintpair.chunkXPos && chunkZ == chunkcoordintpair.chunkZPos)
            {
                return true;
            }
        }
        return false;
    }

    @Override
    protected List<BlockPos> getCoordList()
    {
        List<BlockPos> list = Lists.<BlockPos>newArrayList();

        for (ChunkCoordIntPair chunkcoordintpair : this.structureCoords)
        {
            if (chunkcoordintpair != null)
            {
                list.add(chunkcoordintpair.getCenterBlock(64));
            }
        }
        return list;
    }

    @Override
    protected StructureStart getStructureStart(int chunkX, int chunkZ)
    {
        Start mapgenstronghold$start;
        for (mapgenstronghold$start = new Start(this.worldObj, this.rand, chunkX, chunkZ); mapgenstronghold$start.getComponents().isEmpty() || ((StructureNibiruStrongholdPieces.Stairs2)mapgenstronghold$start.getComponents().get(0)).strongholdPortalRoom == null; mapgenstronghold$start = new Start(this.worldObj, this.rand, chunkX, chunkZ)) {}
        return mapgenstronghold$start;
    }

    public static class Start extends StructureStart
    {
        public Start() {}

        public Start(World world, Random rand, int chunkX, int chunkZ)
        {
            super(chunkX, chunkZ);
            MPLog.debug("Generate stronghold at %s %s", chunkX * 16, chunkZ * 16);
            StructureNibiruStrongholdPieces.prepareStructurePieces();
            StructureNibiruStrongholdPieces.Stairs2 structurestrongholdpieces$stairs2 = new StructureNibiruStrongholdPieces.Stairs2(0, rand, (chunkX << 4) + 2, (chunkZ << 4) + 2);
            this.components.add(structurestrongholdpieces$stairs2);
            structurestrongholdpieces$stairs2.buildComponent(structurestrongholdpieces$stairs2, this.components, rand);
            List<StructureComponent> list = structurestrongholdpieces$stairs2.field_75026_c;

            while (!list.isEmpty())
            {
                int i = rand.nextInt(list.size());
                StructureComponent structurecomponent = list.remove(i);
                structurecomponent.buildComponent(structurestrongholdpieces$stairs2, this.components, rand);
            }
            this.updateBoundingBox();
            this.markAvailableHeight(world, rand, 10);
        }
    }
}