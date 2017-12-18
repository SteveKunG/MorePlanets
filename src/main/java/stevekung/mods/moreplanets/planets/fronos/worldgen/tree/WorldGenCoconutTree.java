/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 *
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.planets.fronos.worldgen.tree;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;
import stevekung.mods.moreplanets.planets.fronos.blocks.FronosBlocks;
import stevekung.mods.moreplanets.planets.fronos.blocks.IFronosGrass;

public class WorldGenCoconutTree extends WorldGenAbstractTree
{
    private int bMax;

    public WorldGenCoconutTree(int bMax)
    {
        super(false);
        this.bMax = bMax;
    }

    @Override
    public boolean generate(World world, Random rand, int x, int y, int z)
    {
        while (world.isAirBlock(x, y, z) && y > 2)
        {
            --y;
        }

        Block block = world.getBlock(x, y, z);

        if (block != Blocks.grass && block != Blocks.dirt && !(block instanceof IFronosGrass) && block != FronosBlocks.fronos_dirt)
        {
            return false;
        }
        else
        {
            for (int var7 = -2; var7 <= 2; ++var7)
            {
                for (int var8 = -2; var8 <= 2; ++var8)
                {
                    if (world.isAirBlock(x + var7, y - 1, z + var8) && world.isAirBlock(x + var7, y - 2, z + var8) && !world.isAirBlock(x + var7, y, z + var8))
                    {
                        return false;
                    }
                }
            }

            int h = 1;

            for (int b = 0; b < this.bMax; b++)
            {
                this.buildBlock(world, x, y + h, z, FronosBlocks.fronos_log, 0);

                if (b == this.bMax - 1)
                {
                    this.generateTop(world, x, y + h, z);
                }
                else
                {
                    h++;
                }
            }
            return true;
        }
    }

    public void generateTop(World world, int x, int y, int z)
    {
        this.buildBlock(world, x + 2, y - 1, z, FronosBlocks.fronos_colorized_leaves, 0);
        this.buildBlock(world, x - 2, y - 1, z, FronosBlocks.fronos_colorized_leaves, 0);
        this.buildBlock(world, x, y - 1, z + 2, FronosBlocks.fronos_colorized_leaves, 0);
        this.buildBlock(world, x, y - 1, z - 2, FronosBlocks.fronos_colorized_leaves, 0);

        this.buildBlock(world, x + 1, y, z, FronosBlocks.fronos_colorized_leaves, 0);
        this.buildBlock(world, x - 1, y, z, FronosBlocks.fronos_colorized_leaves, 0);
        this.buildBlock(world, x, y, z + 1, FronosBlocks.fronos_colorized_leaves, 0);
        this.buildBlock(world, x, y, z - 1, FronosBlocks.fronos_colorized_leaves, 0);
        this.buildBlock(world, x + 2, y, z + 2, FronosBlocks.fronos_colorized_leaves, 0);
        this.buildBlock(world, x - 2, y, z - 2, FronosBlocks.fronos_colorized_leaves, 0);
        this.buildBlock(world, x + 2, y, z - 2, FronosBlocks.fronos_colorized_leaves, 0);
        this.buildBlock(world, x - 2, y, z + 2, FronosBlocks.fronos_colorized_leaves, 0);

        this.buildBlock(world, x + 1, y + 1, z - 1, FronosBlocks.fronos_colorized_leaves, 0);
        this.buildBlock(world, x - 1, y + 1, z + 1, FronosBlocks.fronos_colorized_leaves, 0);
        this.buildBlock(world, x + 1, y + 1, z + 1, FronosBlocks.fronos_colorized_leaves, 0);
        this.buildBlock(world, x - 1, y + 1, z - 1, FronosBlocks.fronos_colorized_leaves, 0);
        this.buildBlock(world, x, y + 1, z, FronosBlocks.fronos_colorized_leaves, 0);

        this.buildBlock(world, x + 2, y + 2, z, FronosBlocks.fronos_colorized_leaves, 0);
        this.buildBlock(world, x - 2, y + 2, z, FronosBlocks.fronos_colorized_leaves, 0);
        this.buildBlock(world, x, y + 2, z + 2, FronosBlocks.fronos_colorized_leaves, 0);
        this.buildBlock(world, x, y + 2, z - 2, FronosBlocks.fronos_colorized_leaves, 0);
    }

    public void buildBlock(World world, int x, int y, int z, Block block, int meta)
    {
        if (world.isAirBlock(x, y, z) || world.getBlock(x, y, z).isLeaves(world, x, y, z))
        {
            world.setBlock(x, y, z, block, meta, 2);
        }
    }
}