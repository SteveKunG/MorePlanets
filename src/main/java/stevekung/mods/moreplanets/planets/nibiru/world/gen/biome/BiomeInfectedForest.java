package stevekung.mods.moreplanets.planets.nibiru.world.gen.biome;

import java.util.Random;

import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;
import stevekung.mods.moreplanets.planets.nibiru.world.gen.feature.WorldGenInfectedRoofedTree;
import stevekung.mods.stevekunglib.utils.WorldDecorateUtils;

public class BiomeInfectedForest extends BiomeNibiru
{
    private static final WorldGenInfectedRoofedTree ROOFED_TREE = new WorldGenInfectedRoofedTree(true);
    private static final WorldGenInfectedRoofedTree ROOFED_TREE_NO_LEAVES = new WorldGenInfectedRoofedTree(false);
    private final Type type;

    public BiomeInfectedForest(BiomeProperties prop, Type type)
    {
        super(prop);
        this.type = type;
        this.decorator.treesPerChunk = 10;
        this.decorator.grassPerChunk = 80;
        this.decorator.orangeBushPerChunk = 3;
    }

    @Override
    public void decorate(World world, Random rand, BlockPos pos)
    {
        super.decorate(world, rand, pos);

        if (rand.nextInt(25) == 0)
        {
            BiomeNibiru.SCATTERED_DIRT.generate(world, rand, WorldDecorateUtils.getSimplePos(world, pos, rand));
        }
        if (this.type == Type.DARK)
        {
            WorldGenAbstractTree tree = this.getRandomTreeFeature(rand);
            tree.setDecorationDefaults();

            if (tree.generate(world, rand, pos))
            {
                tree.generateSaplings(world, rand, pos);
            }
        }
    }

    @Override
    public WorldGenAbstractTree getRandomTreeFeature(Random rand)
    {
        if (this.type == Type.DARK)
        {
            if (rand.nextInt(8) < 1)
            {
                return BiomeInfectedForest.ROOFED_TREE_NO_LEAVES;
            }
            else if (rand.nextInt(20) == 0)
            {
                return rand.nextInt(5) == 0 ? BiomeInfectedForest.ROOFED_TREE : BiomeNibiru.TREE;
            }
            else
            {
                return BiomeNibiru.TREE_NO_LEAVES;
            }
        }
        else
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

    public static enum Type
    {
        NORMAL,
        DARK;
    }
}