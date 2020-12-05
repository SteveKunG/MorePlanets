package stevekung.mods.moreplanets.planets.fronos.world.gen;

import java.util.Collections;
import java.util.Random;

import it.unimi.dsi.fastutil.objects.ObjectIterator;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.structure.MapGenStructure;
import net.minecraft.world.gen.structure.MapGenStructureIO;
import net.minecraft.world.gen.structure.StructureStart;
import stevekung.mods.moreplanets.init.MPBiomes;
import stevekung.mods.moreplanets.utils.LoggerMP;

public class MapGenRongHouse extends MapGenStructure
{
    private boolean ranBiomeCheck;
    private ChunkPos[] structureCoords;
    private double distance;
    private int spread;

    static
    {
        MapGenStructureIO.registerStructure(MapGenRongHouse.Start.class, "RongHouse");
        MapGenStructureIO.registerStructureComponent(RongHouse.class, "RongHouse");
    }

    public MapGenRongHouse()
    {
        this.structureCoords = new ChunkPos[1];
        this.distance = 12.0D;
        this.spread = 2;
    }

    @Override
    public String getStructureName()
    {
        return "RongHouse";
    }

    @Override
    public BlockPos getNearestStructurePos(World world, BlockPos pos, boolean findUnexplored)
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
            blockpos$mutableblockpos.setPos((chunkpos.x << 4) + 8, 32, (chunkpos.z << 4) + 8);
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
            if (chunkX == chunkpos.x && chunkZ == chunkpos.z)
            {
                return true;
            }
        }
        return false;
    }

    private void generatePositions()
    {
        this.initializeStructureData(this.world);
        int i = 0;
        ObjectIterator<?> lvt_2_1_ = this.structureMap.values().iterator();

        while (lvt_2_1_.hasNext())
        {
            StructureStart structurestart = (StructureStart)lvt_2_1_.next();

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
                BlockPos blockpos = this.world.getBiomeProvider().findBiomePosition((j1 << 4) + 8, (k1 << 4) + 8, 112, Collections.singletonList(MPBiomes.FRONOS_MEADOW), rand);

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

    @Override
    protected StructureStart getStructureStart(int chunkX, int chunkZ)
    {
        return new MapGenRongHouse.Start(this.world, this.rand, chunkX, chunkZ);
    }

    public static class Start extends StructureStart
    {
        public Start() {}

        public Start(World world, Random random, int chunkX, int chunkZ)
        {
            super(chunkX, chunkZ);
            LoggerMP.debug("Generate rong house at {} {}", chunkX * 16, chunkZ * 16);
            RongHouse house = new RongHouse(random, chunkX * 16, chunkZ * 16);
            this.components.add(house);
            house.buildComponent(house, this.components, random);
            this.updateBoundingBox();
            this.markAvailableHeight(world, random, 10);
        }
    }
}