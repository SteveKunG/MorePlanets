package stevekung.mods.moreplanets.util.helper;

import java.util.Random;

import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.feature.WorldGenerator;

public class DecorateHelper
{
    public static void generatePlants(WorldGenerator worldGen, World world, Random rand, BlockPos pos)
    {
        int x = rand.nextInt(16) + 8;
        int z = rand.nextInt(16) + 8;
        int y = world.getHeight(pos.add(x, 0, z)).getY() * 2;

        if (y > 0)
        {
            int y1 = rand.nextInt(y);
            worldGen.generate(world, rand, pos.add(x, y1, z));
        }
    }

    public static void generateCustomTrees(WorldGenerator worldGen, World world, Random rand, Biome biome, BlockPos pos)
    {
        int x = rand.nextInt(16) + 8;
        int z = rand.nextInt(16) + 8;
        BlockPos blockpos = world.getHeight(pos.add(x, 0, z));
        worldGen.generate(world, rand, blockpos);
    }

    public static BlockPos getSimplePos(World world, BlockPos pos, Random rand)
    {
        int x = rand.nextInt(16) + 8;
        int z = rand.nextInt(16) + 8;
        int y = world.getHeight(pos.add(x, 0, z)).getY() * 2;
        return pos.add(x, y, z);
    }
}