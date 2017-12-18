/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 *
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.planets.fronos.worldgen.feature;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.BlockAir;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

public class WorldGenCloud extends WorldGenerator
{
    private Block block;
    private int meta;

    public WorldGenCloud(Block block, int meta)
    {
        this.block = block;
        this.meta = meta;
    }

    @Override
    public boolean generate(World world, Random rand, int par3, int par4, int par5)
    {
        Block block = world.getBlock(par3, par4, par5);

        if (!(block instanceof BlockAir))
        {
            return false;
        }
        else
        {
            if (rand.nextInt(4) == 0)
            {
                world.setBlock(par3, par4, par5, this.block, this.meta, 2);
            }
            else if (rand.nextInt(6) == 0)
            {
                world.setBlock(par3, par4, par5, this.block, this.meta, 2);
                world.setBlock(par3 - 1, par4, par5, this.block, this.meta, 2);
                world.setBlock(par3 + 1, par4, par5, this.block, this.meta, 2);
                world.setBlock(par3, par4, par5 - 1, this.block, this.meta, 2);
                world.setBlock(par3, par4, par5 + 1, this.block, this.meta, 2);
                world.setBlock(par3, par4 - 1, par5, this.block, this.meta, 2);
                world.setBlock(par3, par4 + 1, par5, this.block, this.meta, 2);
            }
        }
        return true;
    }
}