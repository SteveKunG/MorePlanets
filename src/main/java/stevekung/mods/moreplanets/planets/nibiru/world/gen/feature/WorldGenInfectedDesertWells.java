package stevekung.mods.moreplanets.planets.nibiru.world.gen.feature;

import java.util.Random;

import net.minecraft.block.state.IBlockState;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;
import stevekung.mods.moreplanets.init.MPBlocks;

public class WorldGenInfectedDesertWells extends WorldGenerator
{
    private static final IBlockState SANDSTONE_SLAB = MPBlocks.INFECTED_SANDSTONE_SLAB.getDefaultState();
    private static final IBlockState SANDSTONE = MPBlocks.INFECTED_SANDSTONE.getDefaultState();
    private static final IBlockState WATER = MPBlocks.INFECTED_WATER_FLUID_BLOCK.getDefaultState();

    @Override
    public boolean generate(World world, Random rand, BlockPos pos)
    {
        while (world.isAirBlock(pos) && pos.getY() > 2)
        {
            pos = pos.down();
        }
        if (world.getBlockState(pos).getBlock() != MPBlocks.INFECTED_SAND)
        {
            return false;
        }
        else
        {
            for (int i = -2; i <= 2; ++i)
            {
                for (int j = -2; j <= 2; ++j)
                {
                    if (world.isAirBlock(pos.add(i, -1, j)) && world.isAirBlock(pos.add(i, -2, j)))
                    {
                        return false;
                    }
                }
            }
            for (int l = -1; l <= 0; ++l)
            {
                for (int l1 = -2; l1 <= 2; ++l1)
                {
                    for (int k = -2; k <= 2; ++k)
                    {
                        world.setBlockState(pos.add(l1, l, k), WorldGenInfectedDesertWells.SANDSTONE, 2);
                    }
                }
            }

            world.setBlockState(pos, WorldGenInfectedDesertWells.WATER, 2);

            for (EnumFacing enumfacing : EnumFacing.Plane.HORIZONTAL)
            {
                world.setBlockState(pos.offset(enumfacing), WorldGenInfectedDesertWells.WATER, 2);
            }

            for (int i1 = -2; i1 <= 2; ++i1)
            {
                for (int i2 = -2; i2 <= 2; ++i2)
                {
                    if (i1 == -2 || i1 == 2 || i2 == -2 || i2 == 2)
                    {
                        world.setBlockState(pos.add(i1, 1, i2), WorldGenInfectedDesertWells.SANDSTONE, 2);
                    }
                }
            }

            world.setBlockState(pos.add(2, 1, 0), WorldGenInfectedDesertWells.SANDSTONE_SLAB, 2);
            world.setBlockState(pos.add(-2, 1, 0), WorldGenInfectedDesertWells.SANDSTONE_SLAB, 2);
            world.setBlockState(pos.add(0, 1, 2), WorldGenInfectedDesertWells.SANDSTONE_SLAB, 2);
            world.setBlockState(pos.add(0, 1, -2), WorldGenInfectedDesertWells.SANDSTONE_SLAB, 2);

            for (int j1 = -1; j1 <= 1; ++j1)
            {
                for (int j2 = -1; j2 <= 1; ++j2)
                {
                    if (j1 == 0 && j2 == 0)
                    {
                        world.setBlockState(pos.add(j1, 4, j2), WorldGenInfectedDesertWells.SANDSTONE, 2);
                    }
                    else
                    {
                        world.setBlockState(pos.add(j1, 4, j2), WorldGenInfectedDesertWells.SANDSTONE_SLAB, 2);
                    }
                }
            }
            for (int k1 = 1; k1 <= 3; ++k1)
            {
                world.setBlockState(pos.add(-1, k1, -1), WorldGenInfectedDesertWells.SANDSTONE, 2);
                world.setBlockState(pos.add(-1, k1, 1), WorldGenInfectedDesertWells.SANDSTONE, 2);
                world.setBlockState(pos.add(1, k1, -1), WorldGenInfectedDesertWells.SANDSTONE, 2);
                world.setBlockState(pos.add(1, k1, 1), WorldGenInfectedDesertWells.SANDSTONE, 2);
            }
            return true;
        }
    }
}