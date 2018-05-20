package stevekung.mods.moreplanets.planets.nibiru.world.gen.biome;

import java.util.Random;

import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;
import stevekung.mods.moreplanets.init.MPBlocks;
import stevekung.mods.moreplanets.planets.nibiru.world.gen.feature.WorldGenInfectedDeadTaiga1;
import stevekung.mods.moreplanets.planets.nibiru.world.gen.feature.WorldGenInfectedDeadTaiga2;
import stevekung.mods.moreplanets.utils.world.gen.feature.WorldGenDoublePlantMP;
import stevekung.mods.stevekunglib.utils.WorldDecorateUtils;

public class BiomeInfectedDeadTaiga extends BiomeNibiru
{
    private static final WorldGenDoublePlantMP LARGE_FERN = new WorldGenDoublePlantMP(MPBlocks.INFECTED_LARGE_FERN);

    public BiomeInfectedDeadTaiga(BiomeProperties prop)
    {
        super(prop);
        this.decorator.infectedTreesPerChunk = 10;
        this.decorator.infectedTallGrassPerChunk = 7;
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
            BiomeInfectedDeadTaiga.LARGE_FERN.generate(world, rand, WorldDecorateUtils.getSimplePos(world, pos, rand));
        }
        super.decorate(world, rand, pos);
    }

    @Override
    public WorldGenAbstractTree getRandomTreeFeature(Random rand)
    {
        if (rand.nextInt(20) == 0)
        {
            return rand.nextInt(3) == 0 ? new WorldGenInfectedDeadTaiga1(false) : new WorldGenInfectedDeadTaiga2(false);//TODO
        }
        else
        {
            return rand.nextInt(3) == 0 ? new WorldGenInfectedDeadTaiga1(true) : new WorldGenInfectedDeadTaiga2(true);//TODO
        }
    }
}