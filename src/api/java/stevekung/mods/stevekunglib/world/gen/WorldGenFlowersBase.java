package stevekung.mods.stevekunglib.world.gen;

import java.util.Random;

import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

public class WorldGenFlowersBase extends WorldGenerator
{
    private IBlockState flower;

    public WorldGenFlowersBase(IBlockState flower)
    {
        this.flower = flower;
    }

    @Override
    public boolean generate(World world, Random rand, BlockPos pos)
    {
        for (int i = 0; i < 128; ++i)
        {
            BlockPos pos1 = pos.add(rand.nextInt(8) - rand.nextInt(8), rand.nextInt(4) - rand.nextInt(4), rand.nextInt(8) - rand.nextInt(8));

            if (world.isAirBlock(pos1) && (!world.provider.isNether() || pos1.getY() < 255) && this.flower.getBlock().canPlaceBlockAt(world, pos1))
            {
                world.setBlockState(pos1, this.flower, 2);
            }
        }
        return true;
    }
}