package stevekung.mods.moreplanets.module.planets.nibiru.world.gen.structure;

import java.util.List;
import java.util.Random;

import com.google.common.collect.Lists;

import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.structure.MapGenStructure;
import net.minecraft.world.gen.structure.MapGenStructureIO;
import net.minecraft.world.gen.structure.StructureComponent;
import net.minecraft.world.gen.structure.StructureStart;
import stevekung.mods.moreplanets.util.MPLog;

public class MapGenNibiruStronghold extends MapGenStructure
{
    public List<Biome> allowedBiomes;
    private boolean ranBiomeCheck;
    private ChunkPos[] structureCoords;
    private double distance;
    private int spread;

    static
    {
        MapGenStructureIO.registerStructure(Start.class, "NibiruStronghold");
        StructureNibiruStrongholdPieces.registerStrongholdPieces();
    }

    public MapGenNibiruStronghold()
    {
        this.structureCoords = new ChunkPos[128];
        this.distance = 32.0D;
        this.spread = 3;
        this.allowedBiomes = Lists.newArrayList();

        for (Biome biome : Biome.REGISTRY)
        {
            if (biome != null && biome.getBaseHeight() > 0.0F)
            {
                this.allowedBiomes.add(biome);
            }
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
            this.generatePositions();
            this.ranBiomeCheck = true;
        }
        for (ChunkPos chunkpos : this.structureCoords)
        {
            if (chunkX == chunkpos.chunkXPos && chunkZ == chunkpos.chunkZPos)
            {
                return true;
            }
        }
        return false;
    }

    @Override
    protected StructureStart getStructureStart(int chunkX, int chunkZ)
    {
        MapGenNibiruStronghold.Start start;
        for (start = new MapGenNibiruStronghold.Start(this.world, this.rand, chunkX, chunkZ); start.getComponents().isEmpty() || ((StructureNibiruStrongholdPieces.Stairs2)start.getComponents().get(0)).strongholdPortalRoom == null; start = new MapGenNibiruStronghold.Start(this.world, this.rand, chunkX, chunkZ)) {}
        return start;
    }

    @Override
    public BlockPos getClosestStrongholdPos(World world, BlockPos pos, boolean findUnexplored)
    {
        if (!this.ranBiomeCheck)
        {
            this.generatePositions();
            this.ranBiomeCheck = true;
        }

        BlockPos blockpos = null;
        BlockPos.MutableBlockPos blockpos$mutableblockpos = new BlockPos.MutableBlockPos(0, 0, 0);
        double d0 = Double.MAX_VALUE;

        for (ChunkPos chunkpos : this.structureCoords)
        {
            blockpos$mutableblockpos.setPos((chunkpos.chunkXPos << 4) + 8, 32, (chunkpos.chunkZPos << 4) + 8);
            double d1 = blockpos$mutableblockpos.distanceSq(pos);

            if (blockpos == null)
            {
                blockpos = new BlockPos(blockpos$mutableblockpos);
                d0 = d1;
            }
            else if (d1 < d0)
            {
                blockpos = new BlockPos(blockpos$mutableblockpos);
                d0 = d1;
            }
        }
        return blockpos;
    }

    private void generatePositions()
    {
        this.initializeStructureData(this.world);
        int i = 0;

        for (StructureStart structurestart : this.structureMap.values())
        {
            if (i < this.structureCoords.length)
            {
                this.structureCoords[i++] = new ChunkPos(structurestart.getChunkPosX(), structurestart.getChunkPosZ());
            }
        }

        Random rand = new Random();
        rand.setSeed(this.world.getSeed());
        double d1 = rand.nextDouble() * Math.PI * 2.0D;
        int j = 0;
        int k = 0;
        int l = this.structureMap.size();

        if (l < this.structureCoords.length)
        {
            for (int i1 = 0; i1 < this.structureCoords.length; ++i1)
            {
                double d0 = 4.0D * this.distance + this.distance * j * 6.0D + (rand.nextDouble() - 0.5D) * this.distance * 2.5D;
                int j1 = (int)Math.round(Math.cos(d1) * d0);
                int k1 = (int)Math.round(Math.sin(d1) * d0);
                BlockPos blockpos = this.world.getBiomeProvider().findBiomePosition((j1 << 4) + 8, (k1 << 4) + 8, 112, this.allowedBiomes, rand);

                if (blockpos != null)
                {
                    j1 = blockpos.getX() >> 4;
                k1 = blockpos.getZ() >> 4;
                }
                if (i1 >= l)
                {
                    this.structureCoords[i1] = new ChunkPos(j1, k1);
                }

                d1 += Math.PI * 2D / this.spread;
                ++k;

                if (k == this.spread)
                {
                    ++j;
                    k = 0;
                    this.spread += 2 * this.spread / (j + 1);
                    this.spread = Math.min(this.spread, this.structureCoords.length - i1);
                    d1 += rand.nextDouble() * Math.PI * 2.0D;
                }
            }
        }
    }

    public static class Start extends StructureStart
    {
        public Start() {}

        public Start(World world, Random rand, int x, int z)
        {
            super(x, z);
            MPLog.debug("Generate Nibiru Stronghold at x:{} z:{}", x * 16, z * 16);
            StructureNibiruStrongholdPieces.prepareStructurePieces();
            StructureNibiruStrongholdPieces.Stairs2 stairs2 = new StructureNibiruStrongholdPieces.Stairs2(rand, (x << 4) + 2, (z << 4) + 2);
            this.components.add(stairs2);
            stairs2.buildComponent(stairs2, this.components, rand);
            List<StructureComponent> list = stairs2.pendingChildren;

            while (!list.isEmpty())
            {
                int i = rand.nextInt(list.size());
                StructureComponent structurecomponent = list.remove(i);
                structurecomponent.buildComponent(stairs2, this.components, rand);
            }
            this.updateBoundingBox();
            this.markAvailableHeight(world, rand, 10);
        }
    }
}