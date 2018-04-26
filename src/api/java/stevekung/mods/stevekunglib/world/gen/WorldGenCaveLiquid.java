package stevekung.mods.stevekunglib.world.gen;

import java.util.Random;

import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

public class WorldGenCaveLiquid extends WorldGenerator
{
    private final IBlockState state;
    private final IBlockState stateToGen;

    public WorldGenCaveLiquid(IBlockState state, IBlockState stateToGen)
    {
        this.state = state;
        this.stateToGen = stateToGen;
    }

    @Override
    public boolean generate(World world, Random rand, BlockPos pos)
    {
        if (world.getBlockState(pos.up()) != this.state)
        {
            return false;
        }
        else if (world.getBlockState(pos.down()) != this.state)
        {
            return false;
        }
        else
        {
            IBlockState state = world.getBlockState(pos);

            if (!state.getBlock().isAir(state, world, pos) && state != this.state)
            {
                return false;
            }
            else
            {
                int i = 0;
                int j = 0;

                if (world.getBlockState(pos.west()) == this.state)
                {
                    ++i;
                }
                if (world.getBlockState(pos.east()) == this.state)
                {
                    ++i;
                }
                if (world.getBlockState(pos.north()) == this.state)
                {
                    ++i;
                }
                if (world.getBlockState(pos.south()) == this.state)
                {
                    ++i;
                }
                if (world.isAirBlock(pos.west()))
                {
                    ++j;
                }
                if (world.isAirBlock(pos.east()))
                {
                    ++j;
                }
                if (world.isAirBlock(pos.north()))
                {
                    ++j;
                }
                if (world.isAirBlock(pos.south()))
                {
                    ++j;
                }

                if (i == 3 && j == 1)
                {
                    world.setBlockState(pos, this.stateToGen, 2);
                    world.immediateBlockTick(pos, this.stateToGen, rand);
                }
                return true;
            }
        }
    }
}