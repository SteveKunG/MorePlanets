package stevekung.mods.moreplanets.planets.fronos.worldgen.feature;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;
import stevekung.mods.moreplanets.planets.fronos.blocks.FronosBlocks;

public class WorldGenBigSkyMushroom extends WorldGenerator
{
    private int mushroomType = 0;

    public WorldGenBigSkyMushroom()
    {
        super(true);
    }

    @Override
    public boolean generate(World world, Random rand, int x, int y, int z)
    {
        int l = rand.nextInt(2);

        if (this.mushroomType >= 0)
        {
            l = this.mushroomType;
        }

        int i1 = rand.nextInt(3) + 4;
        boolean flag = true;

        if (y >= 1 && y + i1 + 1 < 256)
        {
            int k1;
            int l1;

            for (int j1 = y; j1 <= y + 1 + i1; ++j1)
            {
                byte b0 = 3;

                if (j1 <= y + 3)
                {
                    b0 = 0;
                }

                for (k1 = x - b0; k1 <= x + b0 && flag; ++k1)
                {
                    for (l1 = z - b0; l1 <= z + b0 && flag; ++l1)
                    {
                        if (j1 >= 0 && j1 < 256)
                        {
                            Block block = world.getBlock(k1, j1, l1);

                            if (!block.isAir(world, k1, j1, l1) && !block.isLeaves(world, k1, j1, l1))
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
                Block block1 = world.getBlock(x, y - 1, z);

                if (block1 != FronosBlocks.fronos_block)
                {
                    return false;
                }
                else
                {
                    int k2 = y + i1;

                    if (l == 1)
                    {
                        k2 = y + i1 - 3;
                    }

                    for (k1 = k2; k1 <= y + i1; ++k1)
                    {
                        l1 = 1;

                        if (k1 < y + i1)
                        {
                            ++l1;
                        }

                        if (l == 0)
                        {
                            l1 = 3;
                        }

                        for (int l2 = x - l1; l2 <= x + l1; ++l2)
                        {
                            for (int i2 = z - l1; i2 <= z + l1; ++i2)
                            {
                                int j2 = 5;

                                if (l2 == x - l1)
                                {
                                    --j2;
                                }

                                if (l2 == x + l1)
                                {
                                    ++j2;
                                }

                                if (i2 == z - l1)
                                {
                                    j2 -= 3;
                                }

                                if (i2 == z + l1)
                                {
                                    j2 += 3;
                                }

                                if (l == 0 || k1 < y + i1)
                                {
                                    if ((l2 == x - l1 || l2 == x + l1) && (i2 == z - l1 || i2 == z + l1))
                                    {
                                        continue;
                                    }
                                    if (l2 == x - (l1 - 1) && i2 == z - l1)
                                    {
                                        j2 = 1;
                                    }
                                    if (l2 == x - l1 && i2 == z - (l1 - 1))
                                    {
                                        j2 = 1;
                                    }
                                    if (l2 == x + l1 - 1 && i2 == z - l1)
                                    {
                                        j2 = 3;
                                    }
                                    if (l2 == x + l1 && i2 == z - (l1 - 1))
                                    {
                                        j2 = 3;
                                    }
                                    if (l2 == x - (l1 - 1) && i2 == z + l1)
                                    {
                                        j2 = 7;
                                    }
                                    if (l2 == x - l1 && i2 == z + l1 - 1)
                                    {
                                        j2 = 7;
                                    }
                                    if (l2 == x + l1 - 1 && i2 == z + l1)
                                    {
                                        j2 = 9;
                                    }
                                    if (l2 == x + l1 && i2 == z + l1 - 1)
                                    {
                                        j2 = 9;
                                    }
                                }

                                if (j2 == 5 && k1 < y + i1)
                                {
                                    j2 = 0;
                                }
                                if ((j2 != 0 || y >= y + i1 - 1) && world.getBlock(l2, k1, i2).canBeReplacedByLeaves(world, l2, k1, i2))
                                {
                                    this.setBlockAndNotifyAdequately(world, l2, k1, i2, FronosBlocks.huge_sky_mushroom, j2);
                                }
                            }
                        }
                    }

                    for (k1 = 0; k1 < i1; ++k1)
                    {
                        Block block2 = world.getBlock(x, y + k1, z);

                        if (block2.canBeReplacedByLeaves(world, x, y + k1, z))
                        {
                            this.setBlockAndNotifyAdequately(world, x, y + k1, z, FronosBlocks.huge_sky_mushroom, 10);
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