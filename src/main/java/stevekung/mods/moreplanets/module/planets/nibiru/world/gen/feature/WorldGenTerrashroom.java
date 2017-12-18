package stevekung.mods.moreplanets.module.planets.nibiru.world.gen.feature;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;
import stevekung.mods.moreplanets.module.planets.nibiru.blocks.BlockHugeTerrashroom;
import stevekung.mods.moreplanets.module.planets.nibiru.blocks.NibiruBlocks;

public class WorldGenTerrashroom extends WorldGenerator
{
    public WorldGenTerrashroom()
    {
        super(true);
    }

    @Override
    public boolean generate(World world, Random rand, BlockPos pos)
    {
        Block mushroomType = NibiruBlocks.HUGE_TERRASHROOM_BLOCK;
        int i = rand.nextInt(3) + 6;
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

                BlockPos.MutableBlockPos blockpos$mutableblockpos = new BlockPos.MutableBlockPos();

                for (int l = pos.getX() - k; l <= pos.getX() + k && flag; ++l)
                {
                    for (int i1 = pos.getZ() - k; i1 <= pos.getZ() + k && flag; ++i1)
                    {
                        if (j >= 0 && j < 256)
                        {
                            Block block = world.getBlockState(blockpos$mutableblockpos.set(l, j, i1)).getBlock();

                            if (!block.isAir(world, blockpos$mutableblockpos) && !block.isLeaves(world, blockpos$mutableblockpos))
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

                if (block1 != NibiruBlocks.GREEN_VEIN_GRASS && block1 != NibiruBlocks.NIBIRU_BLOCK && block1 != NibiruBlocks.INFECTED_DIRT && block1 != NibiruBlocks.NIBIRU_ORE)
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
                        if (mushroomType == NibiruBlocks.HUGE_TERRASHROOM_BLOCK)
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

                                BlockHugeTerrashroom.BlockType blockhugemushroom$enumtype = BlockHugeTerrashroom.BlockType.valuesCached()[j2];

                                if (mushroomType == NibiruBlocks.HUGE_TERRASHROOM_BLOCK || l2 < pos.getY() + i)
                                {
                                    if ((l1 == k3 || l1 == l3) && (i2 == j1 || i2 == k1))
                                    {
                                        continue;
                                    }
                                    if (l1 == pos.getX() - (j3 - 1) && i2 == j1)
                                    {
                                        blockhugemushroom$enumtype = BlockHugeTerrashroom.BlockType.NORTH_WEST;
                                    }
                                    if (l1 == k3 && i2 == pos.getZ() - (j3 - 1))
                                    {
                                        blockhugemushroom$enumtype = BlockHugeTerrashroom.BlockType.NORTH_WEST;
                                    }
                                    if (l1 == pos.getX() + j3 - 1 && i2 == j1)
                                    {
                                        blockhugemushroom$enumtype = BlockHugeTerrashroom.BlockType.NORTH_EAST;
                                    }
                                    if (l1 == l3 && i2 == pos.getZ() - (j3 - 1))
                                    {
                                        blockhugemushroom$enumtype = BlockHugeTerrashroom.BlockType.NORTH_EAST;
                                    }
                                    if (l1 == pos.getX() - (j3 - 1) && i2 == k1)
                                    {
                                        blockhugemushroom$enumtype = BlockHugeTerrashroom.BlockType.SOUTH_WEST;
                                    }
                                    if (l1 == k3 && i2 == pos.getZ() + j3 - 1)
                                    {
                                        blockhugemushroom$enumtype = BlockHugeTerrashroom.BlockType.SOUTH_WEST;
                                    }
                                    if (l1 == pos.getX() + j3 - 1 && i2 == k1)
                                    {
                                        blockhugemushroom$enumtype = BlockHugeTerrashroom.BlockType.SOUTH_EAST;
                                    }
                                    if (l1 == l3 && i2 == pos.getZ() + j3 - 1)
                                    {
                                        blockhugemushroom$enumtype = BlockHugeTerrashroom.BlockType.SOUTH_EAST;
                                    }
                                }

                                if (blockhugemushroom$enumtype == BlockHugeTerrashroom.BlockType.CENTER && l2 < pos.getY() + i)
                                {
                                    blockhugemushroom$enumtype = BlockHugeTerrashroom.BlockType.ALL_INSIDE;
                                }

                                if (pos.getY() >= pos.getY() + i - 1 || blockhugemushroom$enumtype != BlockHugeTerrashroom.BlockType.ALL_INSIDE)
                                {
                                    BlockPos blockpos = new BlockPos(l1, l2, i2);

                                    if (world.getBlockState(blockpos).getBlock().canBeReplacedByLeaves(world, blockpos))
                                    {
                                        this.setBlockAndNotifyAdequately(world, blockpos, mushroomType.getDefaultState().withProperty(BlockHugeTerrashroom.VARIANT, blockhugemushroom$enumtype));
                                    }
                                }
                            }
                        }
                    }

                    for (int i3 = 0; i3 < i; ++i3)
                    {
                        BlockPos upN = pos.up(i3);
                        IBlockState state = world.getBlockState(upN);

                        if (state.getBlock().canBeReplacedByLeaves(world, upN))
                        {
                            this.setBlockAndNotifyAdequately(world, pos.up(i3), mushroomType.getDefaultState().withProperty(BlockHugeTerrashroom.VARIANT, BlockHugeTerrashroom.BlockType.STEM));
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