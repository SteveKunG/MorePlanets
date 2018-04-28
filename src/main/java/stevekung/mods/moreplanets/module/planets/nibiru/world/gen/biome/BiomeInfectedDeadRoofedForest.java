package stevekung.mods.moreplanets.module.planets.nibiru.world.gen.biome;

import static net.minecraftforge.common.BiomeDictionary.Type.DEAD;
import static net.minecraftforge.common.BiomeDictionary.Type.DENSE;
import static net.minecraftforge.common.BiomeDictionary.Type.FOREST;
import static net.minecraftforge.common.BiomeDictionary.Type.SPOOKY;

import java.util.Random;

import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;
import stevekung.mods.moreplanets.core.MorePlanetsMod;
import stevekung.mods.moreplanets.module.planets.nibiru.blocks.NibiruBlocks;
import stevekung.mods.moreplanets.module.planets.nibiru.world.gen.feature.WorldGenInfectedCanopyTree;
import stevekung.mods.moreplanets.module.planets.nibiru.world.gen.feature.WorldGenInfectedTrees;
import stevekung.mods.moreplanets.module.planets.nibiru.world.gen.feature.WorldGenInfectedVinesDirt;
import stevekung.mods.stevekunglib.utils.WorldDecorateUtils;

public class BiomeInfectedDeadRoofedForest extends BiomeNibiru
{
    public BiomeInfectedDeadRoofedForest(BiomeProperties properties)
    {
        super(properties);
        this.topBlock = NibiruBlocks.INFECTED_GRASS.getDefaultState();
        this.fillerBlock = NibiruBlocks.INFECTED_DIRT.getDefaultState();
        this.stoneBlock = NibiruBlocks.NIBIRU_BLOCK.getDefaultState();
        this.getBiomeDecorator().infectedTreesPerChunk = -999;
        this.getBiomeDecorator().infectedTallGrassPerChunk = 2;
        this.getBiomeDecorator().orangeBushPerChunk = 3;
        this.getBiomeDecorator().reedsPerChunk = 10;
        this.decorator.treesPerChunk = -999;
    }

    @Override
    public void registerTypes(Biome biome)
    {
        MorePlanetsMod.COMMON_REGISTRY.registerBiomeType(biome, SPOOKY, DENSE, FOREST, DEAD);
    }

    @Override
    public WorldGenAbstractTree getRandomTreeFeature(Random rand)
    {
        if (rand.nextInt(8) < 1)
        {
            return new WorldGenInfectedCanopyTree(false);
        }
        else if (rand.nextInt(20) == 0)
        {
            return rand.nextInt(5) == 0 ? new WorldGenInfectedCanopyTree(true) : new WorldGenInfectedTrees(true, NibiruBlocks.NIBIRU_LOG.getDefaultState(), NibiruBlocks.NIBIRU_LEAVES.getDefaultState());
        }
        else
        {
            return new WorldGenInfectedTrees(false, NibiruBlocks.NIBIRU_LOG.getDefaultState(), NibiruBlocks.NIBIRU_LEAVES.getDefaultState());
        }
    }

    @Override
    public void decorate(World world, Random rand, BlockPos pos)
    {
        for (int i = 0; i < 4; ++i)
        {
            for (int j = 0; j < 4; ++j)
            {
                int k = i * 4 + 1 + 8 + rand.nextInt(3);
                int l = j * 4 + 1 + 8 + rand.nextInt(3);
                BlockPos blockpos = world.getHeight(pos.add(k, 0, l));
                WorldGenAbstractTree worldgenabstracttree = this.getRandomTreeFeature(rand);

                if (worldgenabstracttree.generate(world, rand, blockpos))
                {
                    worldgenabstracttree.generateSaplings(world, rand, blockpos);
                }
            }
        }
        if (rand.nextInt(25) == 0)
        {
            new WorldGenInfectedVinesDirt().generate(world, rand, WorldDecorateUtils.getSimplePos(world, pos, rand));
        }
        super.decorate(world, rand, pos);
    }
}