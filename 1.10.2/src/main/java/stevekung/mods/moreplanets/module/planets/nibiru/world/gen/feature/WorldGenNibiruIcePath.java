package stevekung.mods.moreplanets.module.planets.nibiru.world.gen.feature;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;
import stevekung.mods.moreplanets.module.planets.nibiru.blocks.NibiruBlocks;

public class WorldGenNibiruIcePath extends WorldGenerator
{
    private int basePathWidth;

    public WorldGenNibiruIcePath(int basePathWidth)
    {
        this.basePathWidth = basePathWidth;
    }

    @Override
    public boolean generate(World world, Random rand, BlockPos position)
    {
        while (world.isAirBlock(position) && position.getY() > 2)
        {
            position = position.down();
        }

        if (world.getBlockState(position).getBlock() != NibiruBlocks.INFECTED_GRASS)
        {
            return false;
        }
        else
        {
            int i = rand.nextInt(this.basePathWidth - 2) + 2;
            int j = 1;

            for (int k = position.getX() - i; k <= position.getX() + i; ++k)
            {
                for (int l = position.getZ() - i; l <= position.getZ() + i; ++l)
                {
                    int i1 = k - position.getX();
                    int j1 = l - position.getZ();

                    if (i1 * i1 + j1 * j1 <= i * i)
                    {
                        for (int k1 = position.getY() - j; k1 <= position.getY() + j; ++k1)
                        {
                            BlockPos blockpos = new BlockPos(k, k1, l);
                            Block block = world.getBlockState(blockpos).getBlock();

                            if (block == NibiruBlocks.INFECTED_GRASS || block == NibiruBlocks.INFECTED_DIRT || block == NibiruBlocks.INFECTED_ICE)
                            {
                                world.setBlockState(blockpos, NibiruBlocks.INFECTED_PACKED_ICE.getDefaultState(), 2);
                            }
                        }
                    }
                }
            }
            return true;
        }
    }
}