package stevekung.mods.moreplanets.module.planets.nibiru.world.gen.feature;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;
import stevekung.mods.moreplanets.module.planets.nibiru.blocks.NibiruBlocks;

public class WorldGenNibiruShrub extends WorldGenInfectedTree
{
    private IBlockState leaves;
    private IBlockState wood;

    public WorldGenNibiruShrub(IBlockState wood, IBlockState leaves)
    {
        super(true, NibiruBlocks.NIBIRU_LOG, 0, NibiruBlocks.NIBIRU_LEAVES, 0);
        this.wood = wood;
        this.leaves = leaves;
    }

    @Override
    public boolean generate(World world, Random rand, BlockPos position)
    {
        Block block;

        do
        {
            block = world.getBlockState(position).getBlock();

            if (!block.isAir(world, position) && !block.isLeaves(world, position))
            {
                break;
            }
            position = position.down();
        }
        while (position.getY() > 0);

        Block block1 = world.getBlockState(position).getBlock();

        if (block1 == NibiruBlocks.INFECTED_GRASS || block1 == NibiruBlocks.INFECTED_DIRT || block1 == NibiruBlocks.INFECTED_FARMLAND)
        {
            position = position.up();
            this.setBlockAndNotifyAdequately(world, position, this.wood);

            for (int i = position.getY(); i <= position.getY() + 2; ++i)
            {
                int j = i - position.getY();
                int k = 2 - j;

                for (int l = position.getX() - k; l <= position.getX() + k; ++l)
                {
                    int i1 = l - position.getX();

                    for (int j1 = position.getZ() - k; j1 <= position.getZ() + k; ++j1)
                    {
                        int k1 = j1 - position.getZ();

                        if (Math.abs(i1) != k || Math.abs(k1) != k || rand.nextInt(2) != 0)
                        {
                            BlockPos blockpos = new BlockPos(l, i, j1);

                            if (world.getBlockState(blockpos).getBlock().canBeReplacedByLeaves(world, blockpos))
                            {
                                this.setBlockAndNotifyAdequately(world, blockpos, this.leaves);
                            }
                        }
                    }
                }
            }
        }
        return true;
    }
}