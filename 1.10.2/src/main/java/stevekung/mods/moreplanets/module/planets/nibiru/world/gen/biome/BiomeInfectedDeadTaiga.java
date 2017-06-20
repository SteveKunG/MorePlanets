package stevekung.mods.moreplanets.module.planets.nibiru.world.gen.biome;

import java.util.Random;

import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;
import stevekung.mods.moreplanets.module.planets.nibiru.blocks.NibiruBlocks;
import stevekung.mods.moreplanets.module.planets.nibiru.world.gen.feature.WorldGenInfectedDeadTaiga1;
import stevekung.mods.moreplanets.module.planets.nibiru.world.gen.feature.WorldGenInfectedDeadTaiga2;
import stevekung.mods.moreplanets.module.planets.nibiru.world.gen.feature.WorldGenInfectedVinesDirt;

public class BiomeInfectedDeadTaiga extends BiomeNibiru
{
    public BiomeInfectedDeadTaiga(BiomeProperties properties)
    {
        super(properties);
        this.topBlock = NibiruBlocks.INFECTED_GRASS.getDefaultState();
        this.fillerBlock = NibiruBlocks.INFECTED_DIRT.getDefaultState();
        this.stoneBlock = NibiruBlocks.NIBIRU_BLOCK.getDefaultState();
        this.getBiomeDecorator().infectedTreesPerChunk = 10;
        this.getBiomeDecorator().infectedTallGrassPerChunk = 4;
    }

    @Override
    public void decorate(World world, Random rand, BlockPos pos)
    {
        if (rand.nextInt(25) == 0)
        {
            new WorldGenInfectedVinesDirt().generate(world, rand, pos.add(rand.nextInt(16) + 8, rand.nextInt(256), rand.nextInt(16) + 8));
        }
        super.decorate(world, rand, pos);
    }

    @Override
    public WorldGenAbstractTree genBigTreeChance(Random rand)
    {
        if (rand.nextInt(20) == 0)
        {
            return rand.nextInt(3) == 0 ? new WorldGenInfectedDeadTaiga1(false) : new WorldGenInfectedDeadTaiga2(false);
        }
        else
        {
            return rand.nextInt(3) == 0 ? new WorldGenInfectedDeadTaiga1(true) : new WorldGenInfectedDeadTaiga2(true);
        }
    }
}