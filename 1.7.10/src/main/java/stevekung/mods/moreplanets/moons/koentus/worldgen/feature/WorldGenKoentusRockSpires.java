/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 *
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.moons.koentus.worldgen.feature;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;
import stevekung.mods.moreplanets.moons.koentus.blocks.KoentusBlocks;

public class WorldGenKoentusRockSpires extends WorldGenerator
{
    private Block block;
    private int meta;

    public WorldGenKoentusRockSpires(Block block, int meta)
    {
        super(false);
        this.block = block;
        this.meta = meta;
    }

    @Override
    public boolean generate(World world, Random rand, int x, int y, int z)
    {
        while (y > 3)
        {
            if (!world.isAirBlock(x, y - 1, z))
            {
                Block block = world.getBlock(x, y - 1, z);

                if (block == KoentusBlocks.koentus_block && world.getBlockMetadata(x, y - 1, z) == 0)
                {
                    break;
                }
            }
            y--;
        }
        if (y <= 3)
        {
            return false;
        }

        int i = this.meta;
        int j = 0;

        while (i >= 0 && j < 3)
        {
            int k = i + rand.nextInt(2);
            int m = i + rand.nextInt(2);
            int n = i + rand.nextInt(2);
            float f1 = (k + m + n) * 0.333F + 0.5F;

            for (int i1 = x - k; i1 <= x + k; i1++)
            {
                for (int i2 = z - n; i2 <= z + n; i2++)
                {
                    for (int i3 = y - m; i3 <= y + m; i3++)
                    {
                        float f2 = i1 - x;
                        float f3 = i2 - z;
                        float f4 = i3 - y;

                        if (f2 * f2 + f3 * f3 + f4 * f4 <= f1 * f1)
                        {
                            world.setBlock(i1, i3, i2, this.block, 0, 4);
                        }
                    }
                }
            }
            x += -(i + 1) + rand.nextInt(2 + i * 2);
            z += -(i + 1) + rand.nextInt(2 + i * 2);
            y += 0 - rand.nextInt(2);
            j++;
        }
        return true;
    }
}