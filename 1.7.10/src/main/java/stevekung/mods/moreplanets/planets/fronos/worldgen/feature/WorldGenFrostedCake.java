/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 *
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.planets.fronos.worldgen.feature;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;
import stevekung.mods.moreplanets.planets.fronos.blocks.IFronosGrass;

public class WorldGenFrostedCake extends WorldGenerator
{
    private Block frostedCake;
    private int frostedCakeMetadata;

    public WorldGenFrostedCake(Block par1, int par2)
    {
        this.frostedCake = par1;
        this.frostedCakeMetadata = par2;
    }

    @Override
    public boolean generate(World par1World, Random par2Random, int par3, int par4, int par5)
    {
        for (Block var11; ((var11 = par1World.getBlock(par3, par4, par5)) == Blocks.air || var11 == Blocks.leaves) && par4 > 0; --par4)
        {
        }

        for (int var7 = 0; var7 < 128; ++var7)
        {
            int var8 = par3 + par2Random.nextInt(8) - par2Random.nextInt(8);
            int var9 = par4 + par2Random.nextInt(4) - par2Random.nextInt(4);
            int var10 = par5 + par2Random.nextInt(8) - par2Random.nextInt(8);

            if (par1World.isAirBlock(var8, var9, var10) && par1World.getBlock(var8, var9 - 1, var10) instanceof IFronosGrass)
            {
                par1World.setBlock(var8, var9 - 1, var10, this.frostedCake, this.frostedCakeMetadata, 2);
            }
        }
        return true;
    }
}