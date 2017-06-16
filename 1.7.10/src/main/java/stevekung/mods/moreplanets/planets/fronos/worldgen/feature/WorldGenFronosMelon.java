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
import stevekung.mods.moreplanets.planets.fronos.blocks.IFronosGrass;

public class WorldGenFronosMelon extends WorldGenerator
{
    @Override
    public boolean generate(World world, Random rand, int var3, int var4, int var5)
    {
        for (int i = 0; i < 64; ++i)
        {
            int var7 = var3 + rand.nextInt(8) - rand.nextInt(8);
            int var8 = var4 + rand.nextInt(4) - rand.nextInt(4);
            int var9 = var5 + rand.nextInt(8) - rand.nextInt(8);

            if (world.isAirBlock(var7, var8, var9) && world.getBlock(var7, var8 - 1, var9) instanceof IFronosGrass && Blocks.melon_block.canPlaceBlockAt(world, var7, var8, var9))
            {
                world.setBlock(var7, var8, var9, Blocks.melon_block);
            }
        }
        return true;
    }
}