package stevekung.mods.moreplanets.planets.nibiru.world.gen.biome;

import java.util.LinkedList;
import java.util.Random;

import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import stevekung.mods.moreplanets.init.MPBlocks;
import stevekung.mods.moreplanets.planets.nibiru.world.gen.feature.WorldGenInfectedDesertWells;

public class BiomeInfectedDesert extends BiomeNibiru
{
    private static final WorldGenInfectedDesertWells WELLS = new WorldGenInfectedDesertWells();

    public BiomeInfectedDesert(BiomeProperties prop)
    {
        super(prop);
        this.topBlock = MPBlocks.INFECTED_SAND.getDefaultState();
        this.fillerBlock = MPBlocks.INFECTED_SAND.getDefaultState();
        this.decorator.infectedCactusPerChunk = 10;
        this.decorator.deadBushPerChunk = 2;
        this.decorator.batasiaDandelionPerChunk = 3;
        this.decorator.reedsPerChunk = 50;
    }

    @Override
    public void decorate(World world, Random rand, BlockPos pos)
    {
        if (rand.nextInt(1000) == 0)
        {
            BlockPos blockpos = world.getHeight(pos.add(rand.nextInt(16) + 8, 0, rand.nextInt(16) + 8)).up();
            BiomeInfectedDesert.WELLS.generate(world, rand, blockpos);
        }
        if (rand.nextInt(64) == 0)
        {
            BiomeNibiru.FOSSILS.generate(world, rand, pos);
        }
        super.decorate(world, rand, pos);
    }

    @Override
    public void initialiseMobLists(LinkedList<Biome.SpawnListEntry> mobInfo)
    {
        super.initialiseMobLists(mobInfo);
        this.spawnableCreatureList.clear();
    }
}