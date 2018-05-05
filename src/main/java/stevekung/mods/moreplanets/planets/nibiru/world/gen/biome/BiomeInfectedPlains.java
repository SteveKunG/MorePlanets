package stevekung.mods.moreplanets.planets.nibiru.world.gen.biome;

import static net.minecraftforge.common.BiomeDictionary.Type.DEAD;
import static net.minecraftforge.common.BiomeDictionary.Type.PLAINS;

import java.util.Random;

import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;
import stevekung.mods.moreplanets.core.MorePlanetsMod;
import stevekung.mods.moreplanets.planets.nibiru.blocks.NibiruBlocks;
import stevekung.mods.moreplanets.planets.nibiru.world.gen.feature.WorldGenInfectedBigTree;
import stevekung.mods.moreplanets.planets.nibiru.world.gen.feature.WorldGenInfectedTrees;
import stevekung.mods.moreplanets.utils.world.gen.feature.WorldGenDoublePlantMP;
import stevekung.mods.stevekunglib.utils.WorldDecorateUtils;

public class BiomeInfectedPlains extends BiomeNibiru
{
    public BiomeInfectedPlains(BiomeProperties properties)
    {
        super(properties);
        this.topBlock = NibiruBlocks.INFECTED_GRASS_BLOCK.getDefaultState();
        this.fillerBlock = NibiruBlocks.INFECTED_DIRT.getDefaultState();
        this.stoneBlock = NibiruBlocks.NIBIRU_ROCK.getDefaultState();
        this.getBiomeDecorator().infectedTallGrassPerChunk = 10;
        this.getBiomeDecorator().extraTreeChance = 0.05F;
        this.getBiomeDecorator().infectedTreesPerChunk = 0;
        this.getBiomeDecorator().reedsPerChunk = 10;
        this.decorator.treesPerChunk = -999;
    }

    @Override
    public void registerTypes(Biome biome)
    {
        MorePlanetsMod.COMMON_REGISTRY.registerBiomeType(biome, PLAINS, DEAD);
    }

    @Override
    public void decorate(World world, Random rand, BlockPos pos)
    {
        double d0 = GRASS_COLOR_NOISE.getValue((pos.getX() + 8) / 200.0D, (pos.getZ() + 8) / 200.0D);

        if (d0 < -0.8D)
        {
            this.getBiomeDecorator().infectedTallGrassPerChunk = 86;
        }
        else
        {
            this.getBiomeDecorator().infectedTallGrassPerChunk = 128;

            for (int i = 0; i < 7; ++i)
            {
                new WorldGenDoublePlantMP(NibiruBlocks.INFECTED_TALL_GRASS).generate(world, rand, WorldDecorateUtils.getSimplePos(world, pos, rand));
            }
        }
        super.decorate(world, rand, pos);
    }

    @Override
    public WorldGenAbstractTree getRandomTreeFeature(Random rand)
    {
        return rand.nextInt(3) == 0 ? new WorldGenInfectedBigTree(true, NibiruBlocks.INFECTED_OAK_LOG.getDefaultState(), NibiruBlocks.INFECTED_OAK_LEAVES.getDefaultState()) : new WorldGenInfectedTrees(true, NibiruBlocks.INFECTED_OAK_LOG.getDefaultState(), NibiruBlocks.INFECTED_OAK_LEAVES.getDefaultState());
    }
}