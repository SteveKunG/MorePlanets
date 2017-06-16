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
import stevekung.mods.moreplanets.planets.fronos.blocks.IFronosGrass;

public class WorldGenSpaceOyster extends WorldGenerator
{
    private Block block;

    public WorldGenSpaceOyster(Block block)
    {
        this.block = block;
    }

    @Override
    public boolean generate(World world, Random rand, int x, int y, int z)
    {
        Block block = world.getBlock(x, y, z);

        while (world.isAirBlock(x, y, z) && y > 2)
        {
            --y;
        }

        if (!(block instanceof IFronosGrass))
        {
            return false;
        }
        else
        {
            for (int var7 = -2; var7 <= 2; ++var7)
            {
                for (int var8 = -2; var8 <= 2; ++var8)
                {
                    if (!world.isAirBlock(x + var7, y + 1, z + var8))
                    {
                        return false;
                    }
                }
            }
            world.setBlock(x, y + 1, z, this.block, rand.nextInt(4), 2);
            return true;
        }
    }
}