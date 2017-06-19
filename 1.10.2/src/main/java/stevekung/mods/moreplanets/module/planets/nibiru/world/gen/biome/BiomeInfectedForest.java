package stevekung.mods.moreplanets.module.planets.nibiru.world.gen.biome;

import java.util.Random;

import net.minecraft.util.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;
import stevekung.mods.moreplanets.core.config.ConfigManagerMP;
import stevekung.mods.moreplanets.module.planets.nibiru.blocks.NibiruBlocks;
import stevekung.mods.moreplanets.module.planets.nibiru.world.gen.feature.WorldGenInfectedBigTree;
import stevekung.mods.moreplanets.module.planets.nibiru.world.gen.feature.WorldGenInfectedTree;
import stevekung.mods.moreplanets.module.planets.nibiru.world.gen.feature.WorldGenInfectedVinesDirt;

public class BiomeInfectedForest extends BiomeNibiru
{
    public BiomeInfectedForest()
    {
        super(ConfigManagerMP.idBiomeInfectedForest);
        this.enableRain = true;
        this.topBlock = NibiruBlocks.INFECTED_GRASS.getDefaultState();
        this.fillerBlock = NibiruBlocks.INFECTED_DIRT.getDefaultState();
        this.stoneBlock = NibiruBlocks.NIBIRU_BLOCK.getDefaultState();
        this.getBiomeDecorator().infectedTallGrassPerChunk = 24;
        this.getBiomeDecorator().infectedTreesPerChunk = 10;
        this.getBiomeDecorator().orangeBushPerChunk = 3;
        this.getBiomeDecorator().reedsPerChunk = 10;
        this.theBiomeDecorator.treesPerChunk = -999;
        this.setTemperatureRainfall(0.7F, 0.8F);
    }

    @Override
    public void decorate(World world, Random rand, BlockPos pos)
    {
        super.decorate(world, rand, pos);

        if (rand.nextInt(25) == 0)
        {
            new WorldGenInfectedVinesDirt().generate(world, rand, pos.add(rand.nextInt(16) + 8, rand.nextInt(256), rand.nextInt(16) + 8));
        }
    }

    @Override
    public WorldGenAbstractTree genBigTreeChance(Random rand)
    {
        if (rand.nextInt(10) == 0)
        {
            return new WorldGenInfectedBigTree(true, NibiruBlocks.NIBIRU_LOG, 0, NibiruBlocks.NIBIRU_LEAVES, 0);
        }
        else if (rand.nextInt(20) == 0)
        {
            return rand.nextInt(5) == 0 ? new WorldGenInfectedBigTree(false, NibiruBlocks.NIBIRU_LOG, 0, NibiruBlocks.NIBIRU_LEAVES, 0) : new WorldGenInfectedTree(false, NibiruBlocks.NIBIRU_LOG, 0, NibiruBlocks.NIBIRU_LEAVES, 0);
        }
        else
        {
            return new WorldGenInfectedTree(true, NibiruBlocks.NIBIRU_LOG, 0, NibiruBlocks.NIBIRU_LEAVES, 0);
        }
    }
}