/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 *
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.planets.fronos.worldgen.feature;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;
import stevekung.mods.moreplanets.planets.fronos.blocks.FronosBlocks;

public class WorldGenCandyCane2 extends WorldGenerator
{
    @Override
    public boolean generate(World world, Random rand, int x, int y, int z)
    {
        Block block = world.getBlock(x, y, z);

        while (world.isAirBlock(x, y, z) && y > 1)
        {
            --y;
        }

        if (!(block == FronosBlocks.frosted_cake))
        {
            return false;
        }
        else
        {
            for (int i = -2; i <= 2; ++i)
            {
                for (int j = -2; j <= 2; ++j)
                {
                    if (!world.isAirBlock(x + i, y + 1, z + j))
                    {
                        return false;
                    }
                }
            }

            world.setBlock(x, y + 1, z, FronosBlocks.candy_cane2, 0, 2);
            world.setBlock(x, y + 2, z, FronosBlocks.candy_cane2, 0, 2);
            world.setBlock(x, y + 3, z, FronosBlocks.candy_cane2, 1, 2);
            world.setBlock(x, y + 4, z, FronosBlocks.candy_cane2, 1, 2);
            world.setBlock(x, y + 5, z, FronosBlocks.candy_cane2, 2, 2);
            world.setBlock(x, y + 6, z, FronosBlocks.candy_cane2, 2, 2);
            world.setBlock(x, y + 7, z, FronosBlocks.candy_cane2, 3, 2);
            world.setBlock(x, y + 8, z, FronosBlocks.candy_cane2, 3, 2);
            world.setBlock(x, y + 9, z, FronosBlocks.cream_block, rand.nextInt(4), 2);

            if (rand.nextInt(1) == 0)
            {
                world.setBlock(x, y + 10, z, FronosBlocks.ovantine_block, 0, 2);
            }
            if (rand.nextInt(2) == 0)
            {
                world.setBlock(x, y + 10, z, FronosBlocks.chocolate_block, 0, 2);
            }
            if (rand.nextInt(3) == 0)
            {
                world.setBlock(x, y + 10, z, FronosBlocks.cookie_block, 0, 2);
            }
            if (rand.nextInt(4) == 0)
            {
                world.setBlock(x, y + 10, z, FronosBlocks.jelly_block, rand.nextInt(6), 2);
            }
            return true;
        }
    }
}