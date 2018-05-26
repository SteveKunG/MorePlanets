package stevekung.mods.moreplanets.planets.nibiru.world.gen.feature;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;
import stevekung.mods.moreplanets.init.MPBlocks;

public class WorldGenNibiruBlockBlob extends WorldGenerator
{
    private final IBlockState state;
    private final int radius;

    public WorldGenNibiruBlockBlob(IBlockState state, int radius)
    {
        super(false);
        this.state = state;
        this.radius = radius;
    }

    @Override
    public boolean generate(World world, Random rand, BlockPos pos)
    {
        while (true)
        {
            label50:
            {
            if (pos.getY() > 3)
            {
                if (world.isAirBlock(pos.down()))
                {
                    break label50;
                }

                Block block = world.getBlockState(pos.down()).getBlock();

                if (block != MPBlocks.INFECTED_GRASS_BLOCK && block != MPBlocks.INFECTED_DIRT && block != MPBlocks.NIBIRU_ROCK)
                {
                    break label50;
                }
            }

            if (pos.getY() <= 3)
            {
                return false;
            }

            int i1 = this.radius;

            for (int i = 0; i1 >= 0 && i < 3; ++i)
            {
                int j = i1 + rand.nextInt(2);
                int k = i1 + rand.nextInt(2);
                int l = i1 + rand.nextInt(2);
                float f = (j + k + l) * 0.333F + 0.5F;

                for (BlockPos blockpos : BlockPos.getAllInBox(pos.add(-j, -k, -l), pos.add(j, k, l)))
                {
                    if (blockpos.distanceSq(pos) <= f * f)
                    {
                        world.setBlockState(blockpos, this.state, 4);
                    }
                }
                pos = pos.add(-(i1 + 1) + rand.nextInt(2 + i1 * 2), 0 - rand.nextInt(2), -(i1 + 1) + rand.nextInt(2 + i1 * 2));
            }
            return true;
            }
        pos = pos.down();
        }
    }
}