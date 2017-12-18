package stevekung.mods.moreplanets.module.planets.diona.world.gen;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;
import stevekung.mods.moreplanets.module.planets.diona.blocks.DionaBlocks;
import stevekung.mods.moreplanets.module.planets.diona.tileentity.TileEntityLargeInfectedCrystallize;

public class WorldGenInfectedCrystal extends WorldGenerator
{
    @Override
    public boolean generate(World world, Random rand, BlockPos pos)
    {
        for (int i = 0; i < 2; ++i)
        {
            for (EnumFacing facing : EnumFacing.VALUES)
            {
                Block block = DionaBlocks.LARGE_INFECTED_CRYSTALLIZE;

                if (world.isAirBlock(pos) && block.canPlaceBlockOnSide(world, pos, facing))
                {
                    world.setBlockState(pos, block.getDefaultState(), 2);
                    TileEntityLargeInfectedCrystallize ts = (TileEntityLargeInfectedCrystallize)world.getTileEntity(pos);
                    ts.facing = facing.getIndex();
                }
            }
        }
        return true;
    }
}