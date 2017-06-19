package stevekung.mods.moreplanets.module.planets.nibiru.world.gen.feature;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;
import stevekung.mods.moreplanets.module.planets.nibiru.blocks.NibiruBlocks;

public class WorldGenNibiruClay extends WorldGenerator
{
    private int numberOfBlocks;

    public WorldGenNibiruClay(int num)
    {
        this.numberOfBlocks = num;
    }

    @Override
    public boolean generate(World world, Random rand, BlockPos pos)
    {
        if (world.getBlockState(pos).getBlock() != NibiruBlocks.INFECTED_WATER_FLUID_BLOCK)
        {
            return false;
        }
        else
        {
            int i = rand.nextInt(this.numberOfBlocks - 2) + 2;
            int j = 1;

            for (int k = pos.getX() - i; k <= pos.getX() + i; ++k)
            {
                for (int l = pos.getZ() - i; l <= pos.getZ() + i; ++l)
                {
                    int i1 = k - pos.getX();
                    int j1 = l - pos.getZ();

                    if (i1 * i1 + j1 * j1 <= i * i)
                    {
                        for (int k1 = pos.getY() - j; k1 <= pos.getY() + j; ++k1)
                        {
                            BlockPos blockpos = new BlockPos(k, k1, l);
                            Block block = world.getBlockState(blockpos).getBlock();

                            if (block == NibiruBlocks.INFECTED_DIRT || block == NibiruBlocks.INFECTED_CLAY)
                            {
                                world.setBlockState(blockpos, NibiruBlocks.INFECTED_CLAY.getDefaultState(), 2);
                            }
                        }
                    }
                }
            }
            return true;
        }
    }
}