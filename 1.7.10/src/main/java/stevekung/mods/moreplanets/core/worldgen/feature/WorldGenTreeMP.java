/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 *
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.core.worldgen.feature;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.BlockSapling;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;
import net.minecraftforge.common.util.ForgeDirection;

public class WorldGenTreeMP extends WorldGenAbstractTree
{
    private Block wood;
    private Block leaves;
    private int metaWood;
    private int metaLeaves;
    private Block sapling;
    private Block grass;
    private boolean grassBool;
    private Block dirt;

    public WorldGenTreeMP(Block wood, Block leaves, int metaWood, int metaLeaves, Block sapling, Block grass, Block dirt)
    {
        super(false);
        this.wood = wood;
        this.leaves = leaves;
        this.metaWood = metaWood;
        this.metaLeaves = metaLeaves;
        this.sapling = sapling;
        this.grass = grass;
        this.dirt = dirt;
    }

    public WorldGenTreeMP(Block wood, Block leaves, int metaWood, int metaLeaves, Block sapling, boolean grass, Block dirt)
    {
        super(false);
        this.wood = wood;
        this.leaves = leaves;
        this.metaWood = metaWood;
        this.metaLeaves = metaLeaves;
        this.sapling = sapling;
        this.grassBool = grass;
        this.dirt = dirt;
    }

    @Override
    public boolean generate(World world, Random rand, int x, int y, int z)
    {
        int l = rand.nextInt(3) + 5;
        boolean flag = true;

        if (y >= 1 && y + l + 1 <= 256)
        {
            byte b0;
            int k1;
            Block block;

            for (int i1 = y; i1 <= y + 1 + l; ++i1)
            {
                b0 = 1;

                if (i1 == y)
                {
                    b0 = 0;
                }

                if (i1 >= y + 1 + l - 2)
                {
                    b0 = 2;
                }

                for (int j1 = x - b0; j1 <= x + b0 && flag; ++j1)
                {
                    for (k1 = z - b0; k1 <= z + b0 && flag; ++k1)
                    {
                        if (i1 >= 0 && i1 < 256)
                        {
                            block = world.getBlock(j1, i1, k1);

                            if (!this.isReplaceable(world, j1, i1, k1))
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
                Block block2 = world.getBlock(x, y - 1, z);
                boolean isSoil = block2.canSustainPlant(world, x, y - 1, z, ForgeDirection.UP, (BlockSapling)this.sapling);

                if (isSoil && y < 256 - l - 1)
                {
                    this.onPlantGrow(world, x, y - 1, z);
                    b0 = 3;
                    byte b1 = 0;
                    int l1;
                    int i2;
                    int j2;
                    int i3;

                    for (k1 = y - b0 + l; k1 <= y + l; ++k1)
                    {
                        i3 = k1 - (y + l);
                        l1 = b1 + 1 - i3 / 2;

                        for (i2 = x - l1; i2 <= x + l1; ++i2)
                        {
                            j2 = i2 - x;

                            for (int k2 = z - l1; k2 <= z + l1; ++k2)
                            {
                                int l2 = k2 - z;

                                if (Math.abs(j2) != l1 || Math.abs(l2) != l1 || rand.nextInt(2) != 0 && i3 != 0)
                                {
                                    Block block1 = world.getBlock(i2, k1, k2);

                                    if (block1.isAir(world, i2, k1, k2) || block1.isLeaves(world, i2, k1, k2))
                                    {
                                        this.setBlockAndNotifyAdequately(world, i2, k1, k2, this.leaves, this.metaLeaves);
                                    }
                                }
                            }
                        }
                    }

                    for (k1 = 0; k1 < l; ++k1)
                    {
                        block = world.getBlock(x, y + k1, z);

                        if (block.isAir(world, x, y + k1, z) || block.isLeaves(world, x, y + k1, z))
                        {
                            this.setBlockAndNotifyAdequately(world, x, y + k1, z, this.wood, this.metaWood);
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

    private void onPlantGrow(World world, int x, int y, int z)
    {
        if (world.getBlock(x, y, z) == this.grass && this.grass != null && this.dirt != null)
        {
            world.setBlock(x, y, z, this.dirt, 0, 2);
        }
        else if (this.grassBool && this.dirt != null)
        {
            world.setBlock(x, y, z, this.dirt, 0, 2);
        }
        else if (world.getBlock(x, y, z) == Blocks.grass)
        {
            world.setBlock(x, y, z, Blocks.dirt, 0, 2);
        }
    }
}