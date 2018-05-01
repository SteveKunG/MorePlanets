package stevekung.mods.moreplanets.module.planets.nibiru.world.gen.biome;

import static net.minecraftforge.common.BiomeDictionary.Type.FOREST;
import static net.minecraftforge.common.BiomeDictionary.Type.MAGICAL;
import static net.minecraftforge.common.BiomeDictionary.Type.RARE;

import java.util.LinkedList;
import java.util.Random;

import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;
import stevekung.mods.moreplanets.core.MorePlanetsMod;
import stevekung.mods.moreplanets.module.planets.nibiru.blocks.NibiruBlocks;
import stevekung.mods.moreplanets.module.planets.nibiru.entity.EntityTerrastoneGolem;
import stevekung.mods.moreplanets.module.planets.nibiru.world.gen.feature.WorldGenAlienBerryBigTree;
import stevekung.mods.moreplanets.module.planets.nibiru.world.gen.feature.WorldGenAlienBerryTree;
import stevekung.mods.moreplanets.module.planets.nibiru.world.gen.feature.WorldGenNibiruDoublePlant;
import stevekung.mods.moreplanets.module.planets.nibiru.world.gen.feature.WorldGenTerrashroom;
import stevekung.mods.stevekunglib.world.gen.WorldGenFlowersBase;

public class BiomeGreenVein extends BiomeNibiru
{
    public BiomeGreenVein(BiomeProperties properties)
    {
        super(properties);
        this.topBlock = NibiruBlocks.GREEN_VEIN_GRASS_BLOCK.getDefaultState();
        this.fillerBlock = NibiruBlocks.INFECTED_DIRT.getDefaultState();
        this.stoneBlock = NibiruBlocks.NIBIRU_ROCK.getDefaultState();
        this.spawnableCreatureList.add(new SpawnListEntry(EntityTerrastoneGolem.class, 8, 2, 4));
        this.getBiomeDecorator().pureHurbPerChunk = -999;
        this.getBiomeDecorator().terrapuffHurbPerChunk = 4;
        this.getBiomeDecorator().greenVeinTallGrassPerChunk = 128;
        this.getBiomeDecorator().infectedTreesPerChunk = 4;
    }

    @Override
    public void registerTypes(Biome biome)
    {
        MorePlanetsMod.COMMON_REGISTRY.registerBiomeType(biome, FOREST, RARE, MAGICAL);
    }

    @Override
    public void initialiseMobLists(LinkedList<SpawnListEntry> mobInfo)
    {
        super.initialiseMobLists(mobInfo);
        this.spawnableMonsterList.clear();
    }

    @Override
    public void decorate(World world, Random rand, BlockPos pos)
    {
        double d0 = GRASS_COLOR_NOISE.getValue((pos.getX() + 8) / 200.0D, (pos.getZ() + 8) / 200.0D);

        if (d0 > -0.8D)
        {
            for (int i = 0; i < 7; ++i)
            {
                int j = rand.nextInt(16) + 8;
                int k = rand.nextInt(16) + 8;
                int l = rand.nextInt(world.getHeight(pos.add(j, 0, k)).getY() + 32);
                new WorldGenNibiruDoublePlant(NibiruBlocks.GREEN_VEIN_TALL_GRASS).generate(world, rand, pos.add(j, l, k));
            }
        }
        if (rand.nextInt(15) == 0)
        {
            int x = rand.nextInt(16) + 8;
            int z = rand.nextInt(16) + 8;
            new WorldGenTerrashroom().generate(world, rand, world.getHeight(pos.add(x, 0, z)));
        }
        if (rand.nextInt(4) == 0)
        {
            int x = rand.nextInt(16) + 8;
            int z = rand.nextInt(16) + 8;
            BlockPos blockpos2 = world.getHeight(pos.add(x, 0, z));
            new WorldGenFlowersBase(NibiruBlocks.TERRASHROOM.getDefaultState()).generate(world, rand, blockpos2);
        }
        super.decorate(world, rand, pos);
    }

    @Override
    public WorldGenAbstractTree getRandomTreeFeature(Random rand)
    {
        return rand.nextInt(3) == 0 ? new WorldGenAlienBerryBigTree() : new WorldGenAlienBerryTree();
    }
}