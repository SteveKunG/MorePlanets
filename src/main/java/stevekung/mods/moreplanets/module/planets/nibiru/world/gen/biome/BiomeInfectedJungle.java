package stevekung.mods.moreplanets.module.planets.nibiru.world.gen.biome;

import static net.minecraftforge.common.BiomeDictionary.Type.*;

import java.util.Random;

import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;
import net.minecraft.world.gen.feature.WorldGenerator;
import stevekung.mods.moreplanets.core.MorePlanetsMod;
import stevekung.mods.moreplanets.module.planets.nibiru.blocks.NibiruBlocks;
import stevekung.mods.moreplanets.module.planets.nibiru.world.gen.feature.*;
import stevekung.mods.stevekunglib.utils.WorldDecorateUtils;
import stevekung.mods.stevekunglib.world.gen.WorldGenFlowersBase;

public class BiomeInfectedJungle extends BiomeNibiru
{
    public BiomeInfectedJungle(BiomeProperties properties)
    {
        super(properties);
        this.topBlock = NibiruBlocks.INFECTED_GRASS_BLOCK.getDefaultState();
        this.fillerBlock = NibiruBlocks.INFECTED_DIRT.getDefaultState();
        this.stoneBlock = NibiruBlocks.NIBIRU_ROCK.getDefaultState();
        this.getBiomeDecorator().infectedTallGrassPerChunk = 25;
        this.getBiomeDecorator().vealiumVinePerChunk = 4;
        this.getBiomeDecorator().pyoloniaPerChunk = 4;
        this.getBiomeDecorator().infectedTreesPerChunk = 50;
        this.getBiomeDecorator().infectedFernPerChunk = 25;
        this.getBiomeDecorator().pureHurbPerChunk = 4;
        this.decorator.treesPerChunk = -999;
    }

    @Override
    public void registerTypes(Biome biome)
    {
        MorePlanetsMod.COMMON_REGISTRY.registerBiomeType(biome, HOT, WET, DENSE, JUNGLE, DEAD);
    }

    @Override
    public WorldGenAbstractTree getRandomTreeFeature(Random rand)
    {
        return rand.nextInt(10) == 0 ? new WorldGenInfectedBigTree(true, NibiruBlocks.INFECTED_OAK_LOG, 0, NibiruBlocks.INFECTED_OAK_LEAVES, 0) : rand.nextInt(2) == 0 ? new WorldGenNibiruShrub(NibiruBlocks.INFECTED_OAK_LOG.getDefaultState(), NibiruBlocks.INFECTED_OAK_LEAVES.getDefaultState()) : rand.nextInt(3) == 0 ? new WorldGenInfectedMegaJungle(false, 10, 20) : new WorldGenInfectedJungleTrees(true, 4 + rand.nextInt(7), true);
    }

    @Override
    public WorldGenerator getRandomWorldGenForGrass(Random rand)
    {
        return rand.nextInt(4) == 0 ? new WorldGenFlowersBase(NibiruBlocks.INFECTED_FERN.getDefaultState()) : new WorldGenFlowersBase(NibiruBlocks.INFECTED_GRASS.getDefaultState());
    }

    @Override
    public void decorate(World world, Random rand, BlockPos pos)
    {
        super.decorate(world, rand, pos);
        int i = rand.nextInt(16) + 8;
        int j = rand.nextInt(16) + 8;
        new WorldGenNibiruMelon().generate(world, rand, WorldDecorateUtils.getSimplePos(world, pos, rand));
        WorldGenInfectedVines worldgenvines = new WorldGenInfectedVines();

        for (j = 0; j < 50; ++j)
        {
            worldgenvines.generate(world, rand, pos.add(i, 128, j));
        }
        if (rand.nextInt(25) == 0)
        {
            new WorldGenInfectedVinesDirt().generate(world, rand, WorldDecorateUtils.getSimplePos(world, pos, rand));
        }
    }
}