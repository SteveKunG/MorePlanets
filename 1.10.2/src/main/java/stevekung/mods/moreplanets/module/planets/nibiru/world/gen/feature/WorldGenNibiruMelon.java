package stevekung.mods.moreplanets.module.planets.nibiru.world.gen.feature;

import java.util.Random;

import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;
import stevekung.mods.moreplanets.module.planets.nibiru.blocks.NibiruBlocks;

public class WorldGenNibiruMelon extends WorldGenerator
{
    @Override
    public boolean generate(World world, Random rand, BlockPos pos)
    {
        for (int i = 0; i < 64; ++i)
        {
            BlockPos blockpos = pos.add(rand.nextInt(8) - rand.nextInt(8), rand.nextInt(4) - rand.nextInt(4), rand.nextInt(8) - rand.nextInt(8));

            if (NibiruBlocks.INFECTED_MELON_BLOCK.canPlaceBlockAt(world, blockpos) && world.getBlockState(blockpos.down()).getBlock() == NibiruBlocks.INFECTED_GRASS)
            {
                world.setBlockState(blockpos, NibiruBlocks.INFECTED_MELON_BLOCK.getDefaultState(), 2);
            }
        }
        return true;
    }
}