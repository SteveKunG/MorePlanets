/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 *
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.planets.venus.worldgen.feature;

import java.util.Random;

import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;
import stevekung.mods.moreplanets.planets.venus.blocks.VenusBlocks;

public class WorldGenSurfaceLava extends WorldGenerator
{
    @Override
    public boolean generate(World world, Random rand, int x, int y, int z)
    {
        if (world.getBlock(x, y - 1, z) == VenusBlocks.venus_block && world.getBlockMetadata(x, y - 1, z) == 0 && world.getBlock(x, y + 1, z) == Blocks.air)
        {
            world.setBlock(x, y, z, Blocks.flowing_lava, 0, 2);
        }
        return true;
    }
}