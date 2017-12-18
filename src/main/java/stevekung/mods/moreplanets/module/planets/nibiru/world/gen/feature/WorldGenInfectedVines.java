package stevekung.mods.moreplanets.module.planets.nibiru.world.gen.feature;

import java.util.Random;

import net.minecraft.block.state.IBlockState;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;
import stevekung.mods.moreplanets.module.planets.nibiru.blocks.NibiruBlocks;
import stevekung.mods.moreplanets.util.blocks.BlockVinesMP;

public class WorldGenInfectedVines extends WorldGenerator
{
    @Override
    public boolean generate(World world, Random rand, BlockPos position)
    {
        for (; position.getY() < 128; position = position.up())
        {
            if (world.isAirBlock(position))
            {
                for (EnumFacing enumfacing : EnumFacing.Plane.HORIZONTAL.facings())
                {
                    if (NibiruBlocks.INFECTED_VINES.canPlaceBlockOnSide(world, position, enumfacing))
                    {
                        IBlockState iblockstate = NibiruBlocks.INFECTED_VINES.getDefaultState().withProperty(BlockVinesMP.NORTH, Boolean.valueOf(enumfacing == EnumFacing.NORTH)).withProperty(BlockVinesMP.EAST, Boolean.valueOf(enumfacing == EnumFacing.EAST)).withProperty(BlockVinesMP.SOUTH, Boolean.valueOf(enumfacing == EnumFacing.SOUTH)).withProperty(BlockVinesMP.WEST, Boolean.valueOf(enumfacing == EnumFacing.WEST));
                        world.setBlockState(position, iblockstate, 2);
                        break;
                    }
                }
            }
            else
            {
                position = position.add(rand.nextInt(4) - rand.nextInt(4), 0, rand.nextInt(4) - rand.nextInt(4));
            }
        }
        return true;
    }
}