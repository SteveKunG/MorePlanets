/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 *
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.planets.fronos.worldgen.feature;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;
import stevekung.mods.moreplanets.planets.fronos.blocks.FronosBlocks;
import stevekung.mods.moreplanets.planets.fronos.blocks.IFronosGrass;

public class WorldGenClayFronos extends WorldGenerator
{
    private int numberOfBlocks;

    public WorldGenClayFronos(int num)
    {
        this.numberOfBlocks = num;
    }

    @Override
    public boolean generate(World world, Random rand, int x, int y, int z)
    {
        if (world.getBlock(x, y, z).getMaterial() != Material.water)
        {
            return false;
        }
        else
        {
            int l = rand.nextInt(this.numberOfBlocks - 2) + 2;
            byte b0 = 1;

            for (int i1 = x - l; i1 <= x + l; ++i1)
            {
                for (int j1 = z - l; j1 <= z + l; ++j1)
                {
                    int k1 = i1 - x;
                    int l1 = j1 - z;

                    if (k1 * k1 + l1 * l1 <= l * l)
                    {
                        for (int i2 = y - b0; i2 <= y + b0; ++i2)
                        {
                            Block block = world.getBlock(i1, i2, j1);

                            if (block instanceof IFronosGrass || block == FronosBlocks.fronos_dirt || block == Blocks.clay)
                            {
                                world.setBlock(i1, i2, j1, Blocks.clay, 0, 2);
                            }
                        }
                    }
                }
            }
            return true;
        }
    }
}