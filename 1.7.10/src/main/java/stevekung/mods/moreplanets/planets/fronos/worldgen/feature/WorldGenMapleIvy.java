/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 *
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.planets.fronos.worldgen.feature;

import java.util.Random;

import net.minecraft.util.Direction;
import net.minecraft.util.Facing;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;
import stevekung.mods.moreplanets.planets.fronos.blocks.FronosBlocks;

public class WorldGenMapleIvy extends WorldGenerator
{
    @Override
    public boolean generate(World world, Random random, int x, int y, int z)
    {
        int var6 = x;

        for (int var7 = z; y < 80; ++y)
        {
            if (world.isAirBlock(x, y, z))
            {
                for (int var8 = 2; var8 <= 5; ++var8)
                {
                    if (FronosBlocks.maple_ivy.canPlaceBlockOnSide(world, x, y, z, var8))
                    {
                        if (random.nextInt(4) == 0)
                        {
                            world.setBlock(x, y, z, FronosBlocks.maple_ivy, 1 << Direction.facingToDirection[Facing.oppositeSide[var8]], 2);
                        }
                        break;
                    }
                }
            }
            else
            {
                x = var6 + random.nextInt(4) - random.nextInt(4);
                z = var7 + random.nextInt(4) - random.nextInt(4);
            }
        }
        return true;
    }
}