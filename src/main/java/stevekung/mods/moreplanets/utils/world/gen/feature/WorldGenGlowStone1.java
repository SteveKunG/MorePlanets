package stevekung.mods.moreplanets.utils.world.gen.feature;

import java.util.Random;

import net.minecraft.init.Blocks;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;
import stevekung.mods.stevekunglib.utils.enums.CachedEnum;

public class WorldGenGlowStone1 extends WorldGenerator
{
    @Override
    public boolean generate(World world, Random rand, BlockPos pos)
    {
        if (!world.isAirBlock(pos))
        {
            return false;
        }
        else if (world.getBlockState(pos.down()).getBlock() != Blocks.NETHERRACK)
        {
            return false;
        }
        else
        {
            world.setBlockState(pos, Blocks.GLOWSTONE.getDefaultState(), 2);

            for (int i = 0; i < 1500; ++i)
            {
                BlockPos blockpos = pos.add(rand.nextInt(8) - rand.nextInt(8), -rand.nextInt(12), rand.nextInt(8) - rand.nextInt(8));

                if (world.isAirBlock(blockpos))
                {
                    int j = 0;

                    for (EnumFacing enumfacing : CachedEnum.facingValues)
                    {
                        if (world.getBlockState(blockpos.offset(enumfacing)).getBlock() == Blocks.GLOWSTONE)
                        {
                            ++j;
                        }
                        if (j > 1)
                        {
                            break;
                        }
                    }
                    if (j == 1)
                    {
                        world.setBlockState(blockpos, Blocks.GLOWSTONE.getDefaultState(), 2);
                    }
                }
            }
            return true;
        }
    }
}