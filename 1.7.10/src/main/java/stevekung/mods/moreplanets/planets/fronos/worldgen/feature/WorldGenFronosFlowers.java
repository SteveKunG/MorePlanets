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

public class WorldGenFronosFlowers extends WorldGenerator
{
    private Block flower;
    private int flowerMeta;

    public WorldGenFronosFlowers(Block block, int meta)
    {
        this.flower = block;
        this.flowerMeta = meta;
    }

    @Override
    public boolean generate(World world, Random rand, int par3, int par4, int par5)
    {
        for (int i = 0; i < 64; ++i)
        {
            int i1 = par3 + rand.nextInt(8) - rand.nextInt(8);
            int j1 = par4 + rand.nextInt(4) - rand.nextInt(4);
            int k1 = par5 + rand.nextInt(8) - rand.nextInt(8);

            if (world.isAirBlock(i1, j1, k1) && (!world.provider.hasNoSky || j1 < 127) && (world.getFullBlockLightValue(i1, j1, k1) >= 8 || world.canBlockSeeTheSky(i1, j1, k1)) && this.flower.canPlaceBlockOnSide(world, i1, j1, k1, 1))
            {
                world.setBlock(i1, j1, k1, this.flower, this.flowerMeta, 2);
            }
        }
        return true;
    }
}