package stevekung.mods.moreplanets.planets.nibiru.world.gen.biome;

import java.util.Random;

import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.chunk.ChunkPrimer;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;
import net.minecraft.world.gen.feature.WorldGenMegaPineTree;
import net.minecraft.world.gen.feature.WorldGenTaiga1;
import net.minecraft.world.gen.feature.WorldGenTaiga2;
import stevekung.mods.moreplanets.init.MPBlocks;
import stevekung.mods.moreplanets.planets.nibiru.world.gen.feature.WorldGenNibiruBlockBlob;
import stevekung.mods.moreplanets.utils.world.gen.feature.WorldGenDoublePlantMP;
import stevekung.mods.stevekunglib.utils.WorldDecorateUtils;

public class BiomeInfectedTaiga extends BiomeNibiru
{
    private static final WorldGenNibiruBlockBlob MOSSY_ROCK = new WorldGenNibiruBlockBlob(MPBlocks.NIBIRU_VEIN_COBBLESTONE.getDefaultState(), 0);
    private static final WorldGenDoublePlantMP LARGE_FERN = new WorldGenDoublePlantMP(MPBlocks.INFECTED_LARGE_FERN);
    private Type type;

    public BiomeInfectedTaiga(BiomeProperties prop, Type type)
    {
        super(prop);
        this.type = type;
        this.decorator.infectedTreesPerChunk = 10;

        if (type != Type.MEGA && type != Type.MEGA_SPRUCE)
        {
            this.decorator.infectedTallGrassPerChunk = 1;
        }
        else
        {
            this.decorator.infectedTallGrassPerChunk = 7;
            this.decorator.deadBushPerChunk = 1;
        }
    }

    @Override
    public void decorate(World world, Random rand, BlockPos pos)
    {
        if (rand.nextInt(25) == 0)
        {
            BiomeNibiru.SCATTERED_DIRT.generate(world, rand, WorldDecorateUtils.getSimplePos(world, pos, rand));
        }
        for (int i = 0; i < 7; ++i)
        {
            BiomeInfectedTaiga.LARGE_FERN.generate(world, rand, WorldDecorateUtils.getSimplePos(world, pos, rand));
        }

        if (this.type == Type.MEGA || this.type == Type.MEGA_SPRUCE)
        {
            int count = rand.nextInt(3);

            for (int i = 0; i < count; ++i)
            {
                BlockPos blockpos = world.getHeight(pos.add(rand.nextInt(16) + 8, 0, rand.nextInt(16) + 8));
                BiomeInfectedTaiga.MOSSY_ROCK.generate(world, rand, blockpos);
            }
        }
        super.decorate(world, rand, pos);
    }

    @Override
    public WorldGenAbstractTree getRandomTreeFeature(Random rand)
    {
        //        if (rand.nextInt(20) == 0)
        //        {
        //            return rand.nextInt(3) == 0 ? new WorldGenInfectedDeadTaiga1(false) : new WorldGenInfectedDeadTaiga2(false);//TODO
        //        }
        //        else
        //        {
        //            return rand.nextInt(3) == 0 ? new WorldGenInfectedDeadTaiga1(true) : new WorldGenInfectedDeadTaiga2(true);//TODO
        //        }
        //        private static final WorldGenTaiga1 PINE_GENERATOR = new WorldGenTaiga1();
        //        private static final WorldGenTaiga2 SPRUCE_GENERATOR = new WorldGenTaiga2(false);
        //        private static final WorldGenMegaPineTree MEGA_PINE_GENERATOR = new WorldGenMegaPineTree(false, false);
        //        private static final WorldGenMegaPineTree MEGA_SPRUCE_GENERATOR = new WorldGenMegaPineTree(false, true);
        if ((this.type == Type.MEGA || this.type == Type.MEGA_SPRUCE) && rand.nextInt(3) == 0)
        {
            return this.type != Type.MEGA_SPRUCE && rand.nextInt(13) != 0 ? new WorldGenMegaPineTree(false, false) : new WorldGenMegaPineTree(false, true);
        }
        else
        {
            return rand.nextInt(3) == 0 ? new WorldGenTaiga1() : new WorldGenTaiga2(false);
        }
    }

    @Override
    public void genTerrainBlocks(World world, Random rand, ChunkPrimer primer, int chunkX, int chunkZ, double noiseVal)
    {
        if (this.type == Type.MEGA || this.type == Type.MEGA_SPRUCE)
        {
            this.topBlock = MPBlocks.INFECTED_GRASS_BLOCK.getDefaultState();
            this.fillerBlock = MPBlocks.INFECTED_DIRT.getDefaultState();

            if (noiseVal > 1.75D)
            {
                this.topBlock = MPBlocks.INFECTED_COARSE_DIRT.getDefaultState();
            }
            else if (noiseVal > -0.95D)
            {
                this.topBlock = MPBlocks.INFECTED_PODZOL.getDefaultState();
            }
        }
        super.genTerrainBlocks(world, rand, primer, chunkX, chunkZ, noiseVal);
    }

    public static enum Type
    {
        NORMAL,
        MEGA,
        MEGA_SPRUCE;
    }
}