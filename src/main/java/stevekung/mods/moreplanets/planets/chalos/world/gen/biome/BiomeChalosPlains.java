package stevekung.mods.moreplanets.planets.chalos.world.gen.biome;

import static net.minecraftforge.common.BiomeDictionary.Type.PLAINS;

import java.util.Random;

import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import stevekung.mods.moreplanets.core.MorePlanetsMod;
import stevekung.mods.moreplanets.init.MPBlocks;
import stevekung.mods.moreplanets.utils.world.gen.feature.WorldGenDoublePlantMP;

public class BiomeChalosPlains extends BiomeChalos
{
    public BiomeChalosPlains(BiomeProperties properties)
    {
        super(properties);
        this.topBlock = MPBlocks.CHEESE_GRASS_BLOCK.getDefaultState();
        this.fillerBlock = MPBlocks.CHEESE_DIRT.getDefaultState();
        this.stoneBlock = MPBlocks.CHALOS_ROCK.getDefaultState();
    }

    @Override
    public void registerTypes(Biome biome)
    {
        MorePlanetsMod.COMMON_REGISTRY.registerBiomeType(biome, PLAINS);
    }

    @Override
    public void decorate(World world, Random rand, BlockPos pos)
    {
        for (int i = 0; i < 7; ++i)
        {
            int j = rand.nextInt(16) + 8;
            int k = rand.nextInt(16) + 8;
            int l = rand.nextInt(world.getHeight(pos.add(j, 0, k)).getY() + 32);
            new WorldGenDoublePlantMP(MPBlocks.CHEESE_TALL_GRASS).generate(world, rand, pos.add(j, l, k));
        }
        super.decorate(world, rand, pos);
    }
}