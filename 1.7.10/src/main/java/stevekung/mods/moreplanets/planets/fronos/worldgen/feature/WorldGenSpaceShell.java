/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 *
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.planets.fronos.worldgen.feature;

import java.util.Random;

import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;
import stevekung.mods.moreplanets.planets.fronos.blocks.FronosBlocks;

public class WorldGenSpaceShell extends WorldGenerator
{
    @Override
    public boolean generate(World world, Random rand, int par3, int par4, int par5)
    {
        for (int i = 0; i < 64; ++i)
        {
            int i1 = par3 + rand.nextInt(8) - rand.nextInt(8);
            int j1 = par4 + rand.nextInt(4) - rand.nextInt(4);
            int k1 = par5 + rand.nextInt(8) - rand.nextInt(8);

            if (world.isAirBlock(i1, j1, k1) && (world.getBlock(i1, j1 - 1, k1) == Blocks.sand || world.getBlock(i1, j1 - 1, k1) == FronosBlocks.fronos_sand && world.getBlockMetadata(i1, j1 - 1, k1) == 1) && FronosBlocks.space_shell.canPlaceBlockAt(world, i1, j1, k1))
            {
                world.setBlock(i1, j1, k1, FronosBlocks.space_shell, rand.nextInt(16), 2);
            }
        }
        return true;
    }
}