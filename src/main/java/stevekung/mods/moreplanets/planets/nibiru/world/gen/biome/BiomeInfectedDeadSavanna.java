package stevekung.mods.moreplanets.planets.nibiru.world.gen.biome;

import java.util.Random;

import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;
import stevekung.mods.moreplanets.init.MPBlocks;
import stevekung.mods.moreplanets.planets.nibiru.world.gen.feature.WorldGenInfectedDeadSavannaTree;
import stevekung.mods.moreplanets.planets.nibiru.world.gen.feature.WorldGenInfectedTrees;
import stevekung.mods.moreplanets.utils.world.gen.feature.WorldGenDoublePlantMP;
import stevekung.mods.stevekunglib.utils.WorldDecorateUtils;

public class BiomeInfectedDeadSavanna extends BiomeNibiru
{
    private static final WorldGenInfectedTrees TREE = new WorldGenInfectedTrees(false, MPBlocks.INFECTED_OAK_LOG.getDefaultState(), MPBlocks.INFECTED_OAK_LEAVES.getDefaultState());
    private static final WorldGenInfectedDeadSavannaTree SAVANNA_TREE = new WorldGenInfectedDeadSavannaTree();
    private static final WorldGenDoublePlantMP TALL_GRASS = new WorldGenDoublePlantMP(MPBlocks.INFECTED_TALL_GRASS);

    public BiomeInfectedDeadSavanna(BiomeProperties prop)
    {
        super(prop);
        this.decorator.treesPerChunk = 1;
        this.decorator.flowersPerChunk = 4;
        this.decorator.grassPerChunk = 110;
    }

    @Override
    public IBlockState pickRandomModdedFlower(Random rand, BlockPos pos)
    {
        return MPBlocks.PHILIPY.getDefaultState();
    }

    @Override
    public void addDefaultFlowers()
    {
        this.addFlower(MPBlocks.PHILIPY.getDefaultState(), 20);
        this.addFlower(MPBlocks.PURE_HERB.getDefaultState(), 10);
    }

    @Override
    public WorldGenAbstractTree getRandomTreeFeature(Random rand)
    {
        return rand.nextInt(5) > 0 ? BiomeInfectedDeadSavanna.TREE : BiomeInfectedDeadSavanna.SAVANNA_TREE;
    }

    @Override
    public void decorate(World world, Random rand, BlockPos pos)
    {
        double grassColorNoise = GRASS_COLOR_NOISE.getValue((pos.getX() + 8) / 200.0D, (pos.getZ() + 8) / 200.0D);

        if (grassColorNoise > -0.8D)
        {
            for (int i = 0; i < 12; ++i)
            {
                BlockPos blockPos = WorldDecorateUtils.getSimplePos(world, pos, rand);

                if (world.getBlockState(blockPos).getBlock() == MPBlocks.INFECTED_GRASS_BLOCK)
                {
                    BiomeNibiru.SCATTERED_DIRT.generate(world, rand, blockPos);
                }
            }
        }
        for (int i = 0; i < 7; ++i)
        {
            BiomeInfectedDeadSavanna.TALL_GRASS.generate(world, rand, WorldDecorateUtils.getSimplePos(world, pos, rand));
        }
        super.decorate(world, rand, pos);
    }
}