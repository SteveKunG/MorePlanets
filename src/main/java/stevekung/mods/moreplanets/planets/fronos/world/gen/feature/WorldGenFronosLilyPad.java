package stevekung.mods.moreplanets.planets.fronos.world.gen.feature;

import java.util.Random;

import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;
import stevekung.mods.moreplanets.init.MPBlocks;

public class WorldGenFronosLilyPad extends WorldGenerator
{
    @Override
    public boolean generate(World world, Random rand, BlockPos pos)
    {
        for (int i = 0; i < 10; ++i)
        {
            int j = pos.getX() + rand.nextInt(8) - rand.nextInt(8);
            int k = pos.getY() + rand.nextInt(4) - rand.nextInt(4);
            int l = pos.getZ() + rand.nextInt(8) - rand.nextInt(8);
            BlockPos newPos = new BlockPos(j, k, l);

            if (world.isAirBlock(newPos) && MPBlocks.FRONOS_LILY_PAD.canPlaceBlockAt(world, newPos))
            {
                world.setBlockState(newPos, MPBlocks.FRONOS_LILY_PAD.getDefaultState(), 2);
            }
        }
        return true;
    }
}