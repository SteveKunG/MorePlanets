package stevekung.mods.moreplanets.planets.nibiru.world.gen.biome;

import java.util.Random;

import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;
import stevekung.mods.stevekunglib.utils.WorldDecorateUtils;

public class BiomeInfectedForest extends BiomeNibiru
{
    public BiomeInfectedForest(BiomeProperties prop)
    {
        super(prop);
        this.decorator.infectedTallGrassPerChunk = 2;
        this.decorator.infectedTreesPerChunk = 10;
        this.decorator.orangeBushPerChunk = 3;
        this.decorator.reedsPerChunk = 10;
    }

    @Override
    public void decorate(World world, Random rand, BlockPos pos)
    {
        super.decorate(world, rand, pos);

        if (rand.nextInt(25) == 0)
        {
            BiomeNibiru.SCATTERED_DIRT.generate(world, rand, WorldDecorateUtils.getSimplePos(world, pos, rand));
        }
    }

    @Override
    public WorldGenAbstractTree getRandomTreeFeature(Random rand)
    {
        if (rand.nextInt(10) == 0)
        {
            return BiomeNibiru.BIG_TREE;
        }
        else if (rand.nextInt(20) == 0)
        {
            return rand.nextInt(5) == 0 ? BiomeNibiru.BIG_TREE_NO_LEAVES : BiomeNibiru.TREE_NO_LEAVES;
        }
        else
        {
            return BiomeNibiru.TREE;
        }
    }
}