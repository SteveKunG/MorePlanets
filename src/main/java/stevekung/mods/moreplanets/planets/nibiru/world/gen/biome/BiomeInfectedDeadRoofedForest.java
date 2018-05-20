package stevekung.mods.moreplanets.planets.nibiru.world.gen.biome;

import java.util.Random;

import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;
import stevekung.mods.moreplanets.planets.nibiru.world.gen.feature.WorldGenInfectedCanopyTree;
import stevekung.mods.stevekunglib.utils.WorldDecorateUtils;

public class BiomeInfectedDeadRoofedForest extends BiomeNibiru
{
    public BiomeInfectedDeadRoofedForest(BiomeProperties prop)
    {
        super(prop);
        this.decorator.infectedTreesPerChunk = -999;
        this.decorator.infectedTallGrassPerChunk = 2;
        this.decorator.orangeBushPerChunk = 3;
        this.decorator.reedsPerChunk = 10;
    }

    @Override
    public WorldGenAbstractTree getRandomTreeFeature(Random rand)
    {
        if (rand.nextInt(8) < 1)
        {
            return new WorldGenInfectedCanopyTree(false);//TODO
        }
        else if (rand.nextInt(20) == 0)
        {
            return rand.nextInt(5) == 0 ? new WorldGenInfectedCanopyTree(true) : BiomeNibiru.TREE;//TODO
        }
        else
        {
            return BiomeNibiru.TREE_NO_LEAVES;
        }
    }

    @Override
    public void decorate(World world, Random rand, BlockPos pos)
    {
        for (int i = 0; i < 4; ++i)
        {
            for (int j = 0; j < 4; ++j)
            {
                int x = i * 4 + 1 + 8 + rand.nextInt(3);
                int z = j * 4 + 1 + 8 + rand.nextInt(3);
                BlockPos blockPos = world.getHeight(pos.add(x, 0, z));
                WorldGenAbstractTree tree = this.getRandomTreeFeature(rand);

                if (tree.generate(world, rand, blockPos))
                {
                    tree.generateSaplings(world, rand, blockPos);
                }
            }
        }
        if (rand.nextInt(25) == 0)
        {
            BiomeNibiru.SCATTERED_DIRT.generate(world, rand, WorldDecorateUtils.getSimplePos(world, pos, rand));
        }
        super.decorate(world, rand, pos);
    }
}