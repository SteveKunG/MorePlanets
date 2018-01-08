package stevekung.mods.moreplanets.module.planets.chalos.world.gen.feature;

import java.util.Random;

import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;
import stevekung.mods.moreplanets.module.planets.chalos.blocks.BlockChalosDoublePlant;
import stevekung.mods.moreplanets.module.planets.chalos.blocks.ChalosBlocks;

public class WorldGenCheeseDoubleTallGrass extends WorldGenerator
{
    @Override
    public boolean generate(World world, Random rand, BlockPos pos)
    {
        for (int i = 0; i < 64; ++i)
        {
            BlockPos blockpos = pos.add(rand.nextInt(8) - rand.nextInt(8), rand.nextInt(4) - rand.nextInt(4), rand.nextInt(8) - rand.nextInt(8));

            if (world.isAirBlock(blockpos) && (!world.provider.isNether() || blockpos.getY() < 254) && ChalosBlocks.CHALOS_DOUBLE_PLANT.canBlockStay(world, blockpos, ChalosBlocks.CHALOS_DOUBLE_PLANT.getDefaultState()))
            {
                ChalosBlocks.CHALOS_DOUBLE_PLANT.placeAt(world, blockpos, BlockChalosDoublePlant.BlockType.DOUBLE_CHEESE_TALL_GRASS, 2);
            }
        }
        return true;
    }
}