package stevekung.mods.moreplanets.planets.nibiru.world.gen.feature;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;
import stevekung.mods.moreplanets.planets.nibiru.blocks.BlockHugeTerrashroom;
import stevekung.mods.moreplanets.planets.nibiru.blocks.NibiruBlocks;

public class WorldGenTerrashroom extends WorldGenerator
{
    public WorldGenTerrashroom()
    {
        super(true);
    }

    @Override
    public boolean generate(World world, Random rand, BlockPos pos)
    {
        Block block = NibiruBlocks.HUGE_TERRASHROOM_BLOCK;
        int i = rand.nextInt(3) + 6;

        if (rand.nextInt(12) == 0)
        {
            i *= 2;
        }

        boolean flag = true;

        if (pos.getY() >= 1 && pos.getY() + i + 1 < 256)
        {
            for (int j = pos.getY(); j <= pos.getY() + 1 + i; ++j)
            {
                int k = 3;

                if (j <= pos.getY() + 3)
                {
                    k = 0;
                }

                BlockPos.MutableBlockPos mutablePos = new BlockPos.MutableBlockPos();

                for (int l = pos.getX() - k; l <= pos.getX() + k && flag; ++l)
                {
                    for (int i1 = pos.getZ() - k; i1 <= pos.getZ() + k && flag; ++i1)
                    {
                        if (j >= 0 && j < 256)
                        {
                            IBlockState state = world.getBlockState(mutablePos.setPos(l, j, i1));

                            if (!state.getBlock().isAir(state, world, mutablePos) && !state.getBlock().isLeaves(state, world, mutablePos))
                            {
                                flag = false;
                            }
                        }
                        else
                        {
                            flag = false;
                        }
                    }
                }
            }

            if (!flag)
            {
                return false;
            }
            else
            {
                Block block1 = world.getBlockState(pos.down()).getBlock();

                if (block1 != NibiruBlocks.GREEN_VEIN_GRASS_BLOCK && block1 != NibiruBlocks.TERRASTONE)
                {
                    return false;
                }
                else
                {
                    int k2 = pos.getY() + i;

                    for (int l2 = k2; l2 <= pos.getY() + i; ++l2)
                    {
                        int j3 = 1;

                        if (l2 < pos.getY() + i)
                        {
                            ++j3;
                        }
                        if (block == NibiruBlocks.HUGE_TERRASHROOM_BLOCK)
                        {
                            j3 = 4;
                        }

                        int k3 = pos.getX() - j3;
                        int l3 = pos.getX() + j3;
                        int j1 = pos.getZ() - j3;
                        int k1 = pos.getZ() + j3;

                        for (int l1 = k3; l1 <= l3; ++l1)
                        {
                            for (int i2 = j1; i2 <= k1; ++i2)
                            {
                                int j2 = 5;

                                if (l1 == k3)
                                {
                                    --j2;
                                }
                                else if (l1 == l3)
                                {
                                    ++j2;
                                }

                                if (i2 == j1)
                                {
                                    j2 -= 3;
                                }
                                else if (i2 == k1)
                                {
                                    j2 += 3;
                                }

                                BlockHugeTerrashroom.BlockType type = BlockHugeTerrashroom.BlockType.values[j2];

                                if (block == NibiruBlocks.HUGE_TERRASHROOM_BLOCK || l2 < pos.getY() + i)
                                {
                                    if ((l1 == k3 || l1 == l3) && (i2 == j1 || i2 == k1))
                                    {
                                        continue;
                                    }
                                    if (l1 == pos.getX() - (j3 - 1) && i2 == j1)
                                    {
                                        type = BlockHugeTerrashroom.BlockType.NORTH_WEST;
                                    }
                                    if (l1 == k3 && i2 == pos.getZ() - (j3 - 1))
                                    {
                                        type = BlockHugeTerrashroom.BlockType.NORTH_WEST;
                                    }
                                    if (l1 == pos.getX() + j3 - 1 && i2 == j1)
                                    {
                                        type = BlockHugeTerrashroom.BlockType.NORTH_EAST;
                                    }
                                    if (l1 == l3 && i2 == pos.getZ() - (j3 - 1))
                                    {
                                        type = BlockHugeTerrashroom.BlockType.NORTH_EAST;
                                    }
                                    if (l1 == pos.getX() - (j3 - 1) && i2 == k1)
                                    {
                                        type = BlockHugeTerrashroom.BlockType.SOUTH_WEST;
                                    }
                                    if (l1 == k3 && i2 == pos.getZ() + j3 - 1)
                                    {
                                        type = BlockHugeTerrashroom.BlockType.SOUTH_WEST;
                                    }
                                    if (l1 == pos.getX() + j3 - 1 && i2 == k1)
                                    {
                                        type = BlockHugeTerrashroom.BlockType.SOUTH_EAST;
                                    }
                                    if (l1 == l3 && i2 == pos.getZ() + j3 - 1)
                                    {
                                        type = BlockHugeTerrashroom.BlockType.SOUTH_EAST;
                                    }
                                }
                                if (type == BlockHugeTerrashroom.BlockType.CENTER && l2 < pos.getY() + i)
                                {
                                    type = BlockHugeTerrashroom.BlockType.ALL_INSIDE;
                                }
                                if (pos.getY() >= pos.getY() + i - 1 || type != BlockHugeTerrashroom.BlockType.ALL_INSIDE)
                                {
                                    BlockPos blockpos = new BlockPos(l1, l2, i2);
                                    IBlockState state = world.getBlockState(blockpos);

                                    if (state.getBlock().canBeReplacedByLeaves(state, world, blockpos))
                                    {
                                        this.setBlockAndNotifyAdequately(world, blockpos, block.getDefaultState().withProperty(BlockHugeTerrashroom.VARIANT, type));
                                    }
                                }
                            }
                        }
                    }
                    for (int i3 = 0; i3 < i; ++i3)
                    {
                        IBlockState state = world.getBlockState(pos.up(i3));

                        if (state.getBlock().canBeReplacedByLeaves(state, world, pos.up(i3)))
                        {
                            this.setBlockAndNotifyAdequately(world, pos.up(i3), block.getDefaultState().withProperty(BlockHugeTerrashroom.VARIANT, BlockHugeTerrashroom.BlockType.STEM));
                        }
                    }
                    return true;
                }
            }
        }
        else
        {
            return false;
        }
    }
}