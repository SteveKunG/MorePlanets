package stevekung.mods.moreplanets.planets.fronos.world.gen.feature;

import java.util.Random;

import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

public class WorldGenFronosSugarCane extends WorldGenerator
{
    @Override
    public boolean generate(World world, Random rand, BlockPos pos)
    {
        for (int i = 0; i < 20; ++i)
        {
            BlockPos blockpos = pos.add(rand.nextInt(4) - rand.nextInt(4), 0, rand.nextInt(4) - rand.nextInt(4));

            if (world.isAirBlock(blockpos))
            {
                BlockPos blockpos1 = blockpos.down();

                if (world.getBlockState(blockpos1.west()).getBlock() == Blocks.WATER || world.getBlockState(blockpos1.east()).getBlock() == Blocks.WATER || world.getBlockState(blockpos1.north()).getBlock() == Blocks.WATER || world.getBlockState(blockpos1.south()).getBlock() == Blocks.WATER)
                {
                    int j = 2 + rand.nextInt(rand.nextInt(3) + 1);

                    for (int k = 0; k < j; ++k)
                    {
                        if (Blocks.REEDS.canPlaceBlockAt(world, blockpos))
                        {
                            world.setBlockState(blockpos.up(k), Blocks.REEDS.getDefaultState(), 2);
                        }
                    }
                }
            }
        }
        return true;
    }
}