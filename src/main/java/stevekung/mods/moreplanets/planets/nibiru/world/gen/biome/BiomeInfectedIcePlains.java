package stevekung.mods.moreplanets.planets.nibiru.world.gen.biome;

import java.util.LinkedList;
import java.util.Random;

import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;
import stevekung.mods.moreplanets.planets.nibiru.entity.EntityInfectedSnowman;
import stevekung.mods.moreplanets.planets.nibiru.world.gen.feature.WorldGenInfectedDeadTaiga2;
import stevekung.mods.moreplanets.planets.nibiru.world.gen.feature.WorldGenNibiruIcePath;
import stevekung.mods.moreplanets.planets.nibiru.world.gen.feature.WorldGenNibiruIceSpike;

public class BiomeInfectedIcePlains extends BiomeNibiru
{
    private static final WorldGenNibiruIceSpike ICE_SPIKE = new WorldGenNibiruIceSpike();
    private static final WorldGenNibiruIcePath ICE_PATH = new WorldGenNibiruIcePath(4);

    public BiomeInfectedIcePlains(BiomeProperties prop)
    {
        super(prop);
        this.decorator.infectedTallGrassPerChunk = 1;
        this.decorator.philipyPerChunk = 2;
        this.decorator.pureHurbPerChunk = 2;
        this.decorator.infectedTreesPerChunk = 0;
    }

    @Override
    public float getSpawningChance()
    {
        return 0.07F;
    }

    @Override
    public void initialiseMobLists(LinkedList<Biome.SpawnListEntry> mobInfo)
    {
        super.initialiseMobLists(mobInfo);
        this.spawnableMonsterList.add(new Biome.SpawnListEntry(EntityInfectedSnowman.class, 50, 1, 1));
    }

    @Override
    public void decorate(World world, Random rand, BlockPos pos)
    {
        if (rand.nextInt(100) == 0)
        {
            for (int i = 0; i < 3; ++i)
            {
                BiomeInfectedIcePlains.ICE_SPIKE.generate(world, rand, world.getHeight(pos.add(rand.nextInt(16) + 8, 0, rand.nextInt(16) + 8)));
            }
            for (int l = 0; l < 2; ++l)
            {
                BiomeInfectedIcePlains.ICE_PATH.generate(world, rand, world.getHeight(pos.add(rand.nextInt(16) + 8, 0, rand.nextInt(16) + 8)));
            }
        }
        super.decorate(world, rand, pos);
    }

    @Override
    public WorldGenAbstractTree getRandomTreeFeature(Random rand)
    {
        return rand.nextInt(20) == 0 ? new WorldGenInfectedDeadTaiga2(false) : new WorldGenInfectedDeadTaiga2(true);//TODO
    }
}