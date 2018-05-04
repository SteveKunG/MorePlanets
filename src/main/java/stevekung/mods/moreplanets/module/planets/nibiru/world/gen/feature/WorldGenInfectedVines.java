package stevekung.mods.moreplanets.module.planets.nibiru.world.gen.feature;

import java.util.Random;

import net.minecraft.block.BlockVine;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;
import stevekung.mods.moreplanets.module.planets.nibiru.blocks.NibiruBlocks;

public class WorldGenInfectedVines extends WorldGenerator
{
    @Override
    public boolean generate(World world, Random rand, BlockPos pos)
    {
        for (; pos.getY() < 128; pos = pos.up())
        {
            if (world.isAirBlock(pos))
            {
                for (EnumFacing facing : EnumFacing.Plane.HORIZONTAL.facings())
                {
                    if (NibiruBlocks.INFECTED_VINES.canPlaceBlockOnSide(world, pos, facing))
                    {
                        IBlockState iblockstate = NibiruBlocks.INFECTED_VINES.getDefaultState().withProperty(BlockVine.NORTH, facing == EnumFacing.NORTH).withProperty(BlockVine.EAST, facing == EnumFacing.EAST).withProperty(BlockVine.SOUTH, facing == EnumFacing.SOUTH).withProperty(BlockVine.WEST, facing == EnumFacing.WEST);
                        world.setBlockState(pos, iblockstate, 2);
                        break;
                    }
                }
            }
            else
            {
                pos = pos.add(rand.nextInt(4) - rand.nextInt(4), 0, rand.nextInt(4) - rand.nextInt(4));
            }
        }
        return true;
    }
}