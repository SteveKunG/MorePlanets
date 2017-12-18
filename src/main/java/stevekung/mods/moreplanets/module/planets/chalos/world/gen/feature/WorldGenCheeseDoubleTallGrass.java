package stevekung.mods.moreplanets.module.planets.chalos.world.gen.feature;

import java.util.Random;

import net.minecraft.util.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;
import stevekung.mods.moreplanets.module.planets.chalos.blocks.BlockChalosDoubleTallGrass;
import stevekung.mods.moreplanets.module.planets.chalos.blocks.ChalosBlocks;

public class WorldGenCheeseDoubleTallGrass extends WorldGenerator
{
    @Override
    public boolean generate(World world, Random rand, BlockPos pos)
    {
        for (int i = 0; i < 64; ++i)
        {
            BlockPos blockpos = pos.add(rand.nextInt(8) - rand.nextInt(8), rand.nextInt(4) - rand.nextInt(4), rand.nextInt(8) - rand.nextInt(8));

            if (world.isAirBlock(blockpos) && (!world.provider.getHasNoSky() || blockpos.getY() < 254) && ChalosBlocks.CHEESE_DOUBLE_TALL_GRASS.canBlockStay(world, blockpos, ChalosBlocks.CHEESE_DOUBLE_TALL_GRASS.getDefaultState()))
            {
                world.setBlockState(blockpos, ChalosBlocks.CHEESE_DOUBLE_TALL_GRASS.getDefaultState().withProperty(BlockChalosDoubleTallGrass.HALF, BlockChalosDoubleTallGrass.EnumBlockHalf.LOWER).withProperty(BlockChalosDoubleTallGrass.VARIANT, BlockChalosDoubleTallGrass.BlockType.CHEESE_DOUBLE_TALL_GRASS), 2);
                world.setBlockState(blockpos.up(), ChalosBlocks.CHEESE_DOUBLE_TALL_GRASS.getDefaultState().withProperty(BlockChalosDoubleTallGrass.HALF, BlockChalosDoubleTallGrass.EnumBlockHalf.UPPER).withProperty(BlockChalosDoubleTallGrass.VARIANT, BlockChalosDoubleTallGrass.BlockType.CHEESE_DOUBLE_TALL_GRASS), 2);
            }
        }
        return true;
    }
}