package stevekung.mods.moreplanets.planets.nibiru.world.gen.biome;

import java.util.Random;

import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.chunk.ChunkPrimer;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;
import stevekung.mods.moreplanets.init.MPBlocks;
import stevekung.mods.moreplanets.planets.nibiru.world.gen.feature.WorldGenInfectedDeadPine;
import stevekung.mods.moreplanets.planets.nibiru.world.gen.feature.WorldGenInfectedDeadSpruce;
import stevekung.mods.moreplanets.planets.nibiru.world.gen.feature.WorldGenInfectedMegaPineTree;
import stevekung.mods.moreplanets.planets.nibiru.world.gen.feature.WorldGenNibiruBlockBlob;
import stevekung.mods.moreplanets.utils.world.gen.feature.WorldGenDoublePlantMP;
import stevekung.mods.stevekunglib.utils.WorldDecorateUtils;

public class BiomeInfectedTaiga extends BiomeNibiru
{
    private static final WorldGenNibiruBlockBlob MOSSY_ROCK = new WorldGenNibiruBlockBlob(MPBlocks.NIBIRU_VEIN_COBBLESTONE.getDefaultState(), 0);
    private static final WorldGenDoublePlantMP LARGE_FERN = new WorldGenDoublePlantMP(MPBlocks.INFECTED_LARGE_FERN);
    private static final WorldGenInfectedDeadPine DEAD_PINE = new WorldGenInfectedDeadPine(true);
    private static final WorldGenInfectedDeadPine DEAD_PINE_NO_LEAVES = new WorldGenInfectedDeadPine(false);
    private static final WorldGenInfectedDeadSpruce DEAD_SPRUCE = new WorldGenInfectedDeadSpruce(true);
    private static final WorldGenInfectedDeadSpruce DEAD_SPRUCE_NO_LEAVES = new WorldGenInfectedDeadSpruce(false);
    private static final WorldGenInfectedMegaPineTree MEGA_PINE = new WorldGenInfectedMegaPineTree(true, false);
    private static final WorldGenInfectedMegaPineTree MEGA_PINE_BASE_HEIGHT = new WorldGenInfectedMegaPineTree(true, true);
    private static final WorldGenInfectedMegaPineTree MEGA_PINE_NO_LEAVES = new WorldGenInfectedMegaPineTree(false, false);
    private static final WorldGenInfectedMegaPineTree MEGA_PINE_BASE_HEIGHT_NO_LEAVES = new WorldGenInfectedMegaPineTree(false, true);
    private Type type;

    public BiomeInfectedTaiga(BiomeProperties prop, Type type)
    {
        super(prop);
        this.type = type;
        this.decorator.treesPerChunk = 10;

        if (type != Type.MEGA && type != Type.MEGA_SPRUCE)
        {
            this.decorator.grassPerChunk = 40;
        }
        else
        {
            this.decorator.grassPerChunk = 100;
            this.decorator.deadBushPerChunk = 1;
        }
    }

    @Override
    public void decorate(World world, Random rand, BlockPos pos)
    {
        if (type != Type.MEGA && type != Type.MEGA_SPRUCE)
        {
            this.decorator.grassPerChunk = 40;
        }
        else
        {
            this.decorator.grassPerChunk = 100;
            this.decorator.deadBushPerChunk = 1;
        }
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
        if ((this.type == Type.MEGA || this.type == Type.MEGA_SPRUCE) && rand.nextInt(3) == 0)
        {
            if (rand.nextInt(20) == 0)
            {
                return this.type != Type.MEGA_SPRUCE && rand.nextInt(13) != 0 ? BiomeInfectedTaiga.MEGA_PINE_NO_LEAVES : BiomeInfectedTaiga.MEGA_PINE_BASE_HEIGHT_NO_LEAVES;
            }
            else
            {
                return this.type != Type.MEGA_SPRUCE && rand.nextInt(13) != 0 ? BiomeInfectedTaiga.MEGA_PINE : BiomeInfectedTaiga.MEGA_PINE_BASE_HEIGHT;
            }
        }
        else
        {
            if (rand.nextInt(20) == 0)
            {
                return rand.nextInt(3) == 0 ? BiomeInfectedTaiga.DEAD_PINE_NO_LEAVES : BiomeInfectedTaiga.DEAD_SPRUCE_NO_LEAVES;
            }
            else
            {
                return rand.nextInt(3) == 0 ? BiomeInfectedTaiga.DEAD_PINE : BiomeInfectedTaiga.DEAD_SPRUCE;
            }
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