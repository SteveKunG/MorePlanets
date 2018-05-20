package stevekung.mods.moreplanets.planets.nibiru.world.gen.biome;

import java.util.LinkedList;
import java.util.Random;

import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import stevekung.mods.moreplanets.init.MPBlocks;
import stevekung.mods.stevekunglib.utils.WorldDecorateUtils;

public class BiomeInfectedDesert extends BiomeNibiru
{
    public BiomeInfectedDesert(BiomeProperties prop)
    {
        super(prop);
        this.topBlock = MPBlocks.INFECTED_SAND.getDefaultState();
        this.fillerBlock = MPBlocks.INFECTED_SAND.getDefaultState();
        this.decorator.infectedCactusPerChunk = 10;
        this.decorator.deadBushPerChunk = 2;
        this.decorator.batasiaDandelionPerChunk = 3;
        this.decorator.reedsPerChunk = 50;
        this.decorator.treesPerChunk = -999;
    }

    @Override
    public void decorate(World world, Random rand, BlockPos pos)
    {
        if (rand.nextInt(64) == 0)
        {
            BiomeNibiru.FOSSILS.generate(world, rand, WorldDecorateUtils.getSimplePos(world, pos, rand));
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