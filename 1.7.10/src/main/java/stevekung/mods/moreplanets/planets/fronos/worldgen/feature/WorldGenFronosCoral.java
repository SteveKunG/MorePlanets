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

public class WorldGenFronosCoral extends WorldGenerator
{
    @Override
    public boolean generate(World par1World, Random rand, int par3, int par4, int par5)
    {
        for (int l = 0; l < 64; ++l)
        {
            int i1 = par3 + rand.nextInt(8) - rand.nextInt(8);
            int j1 = par4 + rand.nextInt(4) - rand.nextInt(4);
            int k1 = par5 + rand.nextInt(8) - rand.nextInt(8);

            if ((par1World.getBlock(i1, j1, k1) == Blocks.water || par1World.getBlock(i1, j1, k1) == Blocks.flowing_water) && (par1World.getBlock(i1, j1 + 1, k1) == Blocks.water || par1World.getBlock(i1, j1 + 1, k1) == Blocks.flowing_water) && FronosBlocks.coral.canPlaceBlockOnSide(par1World, i1, j1, k1, 1))
            {
                par1World.setBlock(i1, j1, k1, FronosBlocks.coral, 5 + rand.nextInt(2), 2);
            }
        }
        return true;
    }
}