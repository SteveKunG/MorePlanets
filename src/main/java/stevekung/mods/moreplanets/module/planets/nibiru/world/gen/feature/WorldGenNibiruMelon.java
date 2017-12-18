package stevekung.mods.moreplanets.module.planets.nibiru.world.gen.feature;

import java.util.Random;

import net.minecraft.util.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;
import stevekung.mods.moreplanets.module.planets.nibiru.blocks.NibiruBlocks;

public class WorldGenNibiruMelon extends WorldGenerator
{
    @Override
    public boolean generate(World worldIn, Random rand, BlockPos position)
    {
        for (int i = 0; i < 64; ++i)
        {
            BlockPos blockpos = position.add(rand.nextInt(8) - rand.nextInt(8), rand.nextInt(4) - rand.nextInt(4), rand.nextInt(8) - rand.nextInt(8));

            if (NibiruBlocks.INFECTED_MELON_BLOCK.canPlaceBlockAt(worldIn, blockpos) && worldIn.getBlockState(blockpos.down()).getBlock() == NibiruBlocks.INFECTED_GRASS)
            {
                worldIn.setBlockState(blockpos, NibiruBlocks.INFECTED_MELON_BLOCK.getDefaultState(), 2);
            }
        }
        return true;
    }
}