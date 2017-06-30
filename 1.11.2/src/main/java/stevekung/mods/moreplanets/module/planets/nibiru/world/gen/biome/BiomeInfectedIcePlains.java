package stevekung.mods.moreplanets.module.planets.nibiru.world.gen.biome;

import java.util.LinkedList;
import java.util.Random;

import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;
import stevekung.mods.moreplanets.module.planets.nibiru.blocks.NibiruBlocks;
import stevekung.mods.moreplanets.module.planets.nibiru.entity.EntityInfectedSnowman;
import stevekung.mods.moreplanets.module.planets.nibiru.world.gen.feature.WorldGenInfectedDeadTaiga2;
import stevekung.mods.moreplanets.module.planets.nibiru.world.gen.feature.WorldGenNibiruIcePath;
import stevekung.mods.moreplanets.module.planets.nibiru.world.gen.feature.WorldGenNibiruIceSpike;

public class BiomeInfectedIcePlains extends BiomeNibiru
{
    public BiomeInfectedIcePlains(BiomeProperties properties)
    {
        super(properties);
        this.topBlock = NibiruBlocks.INFECTED_GRASS.getDefaultState();
        this.fillerBlock = NibiruBlocks.INFECTED_DIRT.getDefaultState();
        this.stoneBlock = NibiruBlocks.NIBIRU_BLOCK.getDefaultState();
        this.getBiomeDecorator().infectedTallGrassPerChunk = 1;
        this.getBiomeDecorator().philipyPerChunk = 2;
        this.getBiomeDecorator().pureHurbPerChunk = 2;
        this.getBiomeDecorator().infectedTreesPerChunk = 0;
    }

    @Override
    public float getSpawningChance()
    {
        return 0.07F;
    }

    @Override
    public void initialiseMobLists(LinkedList<SpawnListEntry> mobInfo)
    {
        super.initialiseMobLists(mobInfo);
        this.spawnableMonsterList.add(new SpawnListEntry(EntityInfectedSnowman.class, 50, 1, 1));
    }

    @Override
    public void decorate(World world, Random rand, BlockPos pos)
    {
        if (rand.nextInt(100) == 0)
        {
            for (int i = 0; i < 3; ++i)
            {
                int j = rand.nextInt(16) + 8;
                int k = rand.nextInt(16) + 8;
                new WorldGenNibiruIceSpike().generate(world, rand, world.getHeight(pos.add(j, 0, k)));
            }
            for (int l = 0; l < 2; ++l)
            {
                int i1 = rand.nextInt(16) + 8;
                int j1 = rand.nextInt(16) + 8;
                new WorldGenNibiruIcePath(4).generate(world, rand, world.getHeight(pos.add(i1, 0, j1)));
            }
        }
        super.decorate(world, rand, pos);
    }

    @Override
    public WorldGenAbstractTree genBigTreeChance(Random rand)
    {
        return rand.nextInt(20) == 0 ? new WorldGenInfectedDeadTaiga2(false) : new WorldGenInfectedDeadTaiga2(true);
    }
}