package stevekung.mods.moreplanets.util.helper;

import java.util.Random;

import net.minecraft.util.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.gen.feature.WorldGenerator;

public class DecorateHelper
{
    public static void generatePlants(WorldGenerator worldGen, World world, Random rand, BlockPos pos)
    {
        int x = rand.nextInt(16) + 8;
        int z = rand.nextInt(16) + 8;
        int y = world.getHeight(pos.add(x, 0, z)).getY() + 32;

        if (y > 0)
        {
            int y1 = rand.nextInt(y);
            BlockPos blockpos1 = pos.add(x, y1, z);
            worldGen.generate(world, rand, blockpos1);
        }
    }

    public static void generateCustomTrees(WorldGenerator worldGen, World world, Random rand, BiomeGenBase biome, BlockPos pos)
    {
        int x = rand.nextInt(16) + 8;
        int z = rand.nextInt(16) + 8;
        BlockPos blockpos = world.getHeight(pos.add(x, 0, z));
        worldGen.generate(world, rand, blockpos);
    }
}