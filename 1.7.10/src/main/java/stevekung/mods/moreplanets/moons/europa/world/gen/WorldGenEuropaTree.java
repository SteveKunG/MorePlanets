package stevekung.mods.moreplanets.moons.europa.world.gen;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;
import stevekung.mods.moreplanets.moons.europa.blocks.EuropaBlocks;

public class WorldGenEuropaTree extends WorldGenAbstractTree
{
    public WorldGenEuropaTree()
    {
        super(true);
    }

    @Override
    public boolean generate(World world, Random rand, int x, int y, int z)
    {
        int l = rand.nextInt(4) + 6;
        int i1 = 1 + rand.nextInt(2);
        int j1 = l - i1;
        int k1 = 2 + rand.nextInt(2);
        boolean flag = true;

        if (y >= 1 && y + l + 1 <= 256)
        {
            int i2;
            int l3;

            for (int l1 = y; l1 <= y + 1 + l && flag; ++l1)
            {
                if (l1 - y < i1)
                {
                    l3 = 0;
                }
                else
                {
                    l3 = k1;
                }

                for (i2 = x - l3; i2 <= x + l3 && flag; ++i2)
                {
                    for (int j2 = z - l3; j2 <= z + l3 && flag; ++j2)
                    {
                        if (l1 >= 0 && l1 < 256)
                        {
                            Block block = world.getBlock(i2, l1, j2);

                            if (!block.getMaterial().isSolid())
                            {
                                flag = true;
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

                if (block1 == EuropaBlocks.europa_ice_slush && y < 256 - l - 1)
                {
                    l3 = rand.nextInt(2);
                    i2 = 1;
                    byte b0 = 0;
                    int k2;
                    int i4;

                    for (i4 = 0; i4 <= j1; ++i4)
                    {
                        k2 = y + l - i4;

                        for (int l2 = x - l3; l2 <= x + l3; ++l2)
                        {
                            int i3 = l2 - x;

                            for (int j3 = z - l3; j3 <= z + l3; ++j3)
                            {
                                int k3 = j3 - z;

                                if (Math.abs(i3) != l3 || Math.abs(k3) != l3 || l3 <= 0)
                                {
                                    if (!World.doesBlockHaveSolidTopSurface(world, l2, k2, j3))
                                    {
                                        this.setBlockAndNotifyAdequately(world, l2, k2, j3, EuropaBlocks.europa_leaves, 0);
                                    }
                                }
                            }
                        }

                        if (l3 >= i2)
                        {
                            l3 = b0;
                            b0 = 1;
                            ++i2;

                            if (i2 > k1)
                            {
                                i2 = k1;
                            }
                        }
                        else
                        {
                            ++l3;
                        }
                    }

                    i4 = rand.nextInt(3);

                    for (k2 = 0; k2 < l - i4; ++k2)
                    {
                        if (!World.doesBlockHaveSolidTopSurface(world, x, y + k2, z))
                        {
                            this.setBlockAndNotifyAdequately(world, x, y + k2, z, EuropaBlocks.europa_log, 0);
                        }
                    }
                    return true;
                }
                else
                {
                    return false;
                }
            }
        }
        else
        {
            return false;
        }
    }
}