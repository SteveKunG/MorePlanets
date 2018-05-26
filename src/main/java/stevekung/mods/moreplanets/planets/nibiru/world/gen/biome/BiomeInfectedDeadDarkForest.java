package stevekung.mods.moreplanets.planets.nibiru.world.gen.biome;

import java.util.Random;

import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;
import stevekung.mods.moreplanets.planets.nibiru.world.gen.feature.WorldGenInfectedRoofedTree;
import stevekung.mods.stevekunglib.utils.WorldDecorateUtils;

public class BiomeInfectedDeadDarkForest extends BiomeNibiru
{
    private static final WorldGenInfectedRoofedTree ROOFED_TREE = new WorldGenInfectedRoofedTree(true);
    private static final WorldGenInfectedRoofedTree ROOFED_TREE_NO_LEAVES = new WorldGenInfectedRoofedTree(false);

    public BiomeInfectedDeadDarkForest(BiomeProperties prop)
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
            return BiomeInfectedDeadDarkForest.ROOFED_TREE_NO_LEAVES;
        }
        else if (rand.nextInt(20) == 0)
        {
            return rand.nextInt(5) == 0 ? BiomeInfectedDeadDarkForest.ROOFED_TREE : BiomeNibiru.TREE;
        }
        else
        {
            return BiomeNibiru.TREE_NO_LEAVES;
        }
    }

    @Override
    public void decorate(World world, Random rand, BlockPos pos)
    {
        if (rand.nextInt(25) == 0)
        {
            BiomeNibiru.SCATTERED_DIRT.generate(world, rand, WorldDecorateUtils.getSimplePos(world, pos, rand));
        }
        super.decorate(world, rand, pos);
    }
}