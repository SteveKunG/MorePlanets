package stevekung.mods.moreplanets.planets.nibiru.world.gen.biome;

import java.util.LinkedList;
import java.util.Random;

import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.chunk.ChunkPrimer;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;
import stevekung.mods.moreplanets.init.MPBlocks;
import stevekung.mods.moreplanets.planets.nibiru.entity.EntityTerrastoneGolem;
import stevekung.mods.moreplanets.planets.nibiru.world.gen.feature.WorldGenAlienBerryBigTree;
import stevekung.mods.moreplanets.planets.nibiru.world.gen.feature.WorldGenAlienBerryTree;
import stevekung.mods.moreplanets.planets.nibiru.world.gen.feature.WorldGenTerrashroom;
import stevekung.mods.moreplanets.utils.world.gen.feature.WorldGenDoublePlantMP;
import stevekung.mods.stevekunglib.utils.WorldDecorateUtils;
import stevekung.mods.stevekunglib.world.gen.WorldGenFlowersBase;

public class BiomeGreenVeinFields extends BiomeNibiru
{
    private static final WorldGenDoublePlantMP TALL_GRASS = new WorldGenDoublePlantMP(MPBlocks.GREEN_VEIN_TALL_GRASS);
    private static final WorldGenTerrashroom TERRASHROOM = new WorldGenTerrashroom();
    private static final WorldGenFlowersBase TERRASHROOM_FLOWER = new WorldGenFlowersBase(MPBlocks.TERRASHROOM.getDefaultState());
    private static final WorldGenAlienBerryTree TREE = new WorldGenAlienBerryTree();
    private static final WorldGenAlienBerryBigTree BIG_TREE = new WorldGenAlienBerryBigTree();

    public BiomeGreenVeinFields(BiomeProperties prop)
    {
        super(prop);
        this.topBlock = MPBlocks.GREEN_VEIN_GRASS_BLOCK.getDefaultState();
        this.decorator.pureHurbPerChunk = -999;
        this.decorator.terrapuffHurbPerChunk = 4;
        this.decorator.greenVeinTallGrassPerChunk = 128;
        this.decorator.infectedTreesPerChunk = 4;
    }

    @Override
    public void initialiseMobLists(LinkedList<Biome.SpawnListEntry> mobInfo)
    {
        super.initialiseMobLists(mobInfo);
        this.spawnableCreatureList.add(new Biome.SpawnListEntry(EntityTerrastoneGolem.class, 8, 2, 4));
        this.spawnableMonsterList.clear();
    }

    @Override
    public void decorate(World world, Random rand, BlockPos pos)
    {
        double grassColorNoise = GRASS_COLOR_NOISE.getValue((pos.getX() + 8) / 200.0D, (pos.getZ() + 8) / 200.0D);

        if (grassColorNoise > -0.8D)
        {
            for (int i = 0; i < 7; ++i)
            {
                BiomeGreenVeinFields.TALL_GRASS.generate(world, rand, WorldDecorateUtils.getSimplePos(world, pos, rand));
            }
        }
        if (rand.nextInt(15) == 0)
        {
            BiomeGreenVeinFields.TERRASHROOM.generate(world, rand, WorldDecorateUtils.getSimplePos(world, pos, rand));
        }
        if (rand.nextInt(4) == 0)
        {
            BiomeGreenVeinFields.TERRASHROOM_FLOWER.generate(world, rand, WorldDecorateUtils.getSimplePos(world, pos, rand));
        }
        super.decorate(world, rand, pos);
    }

    @Override
    public WorldGenAbstractTree getRandomTreeFeature(Random rand)
    {
        return rand.nextInt(3) == 0 ? BiomeGreenVeinFields.BIG_TREE : BiomeGreenVeinFields.TREE;
    }

    @Override
    public void genTerrainBlocks(World world, Random rand, ChunkPrimer primer, int chunkX, int chunkZ, double noiseVal)
    {
        this.liquidBlock = MPBlocks.PURIFIED_WATER_FLUID_BLOCK.getDefaultState();
        super.genTerrainBlocks(world, rand, primer, chunkX, chunkZ, noiseVal);
    }
}