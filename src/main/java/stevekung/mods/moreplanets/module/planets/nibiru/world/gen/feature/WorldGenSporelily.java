package stevekung.mods.moreplanets.module.planets.nibiru.world.gen.feature;

import java.util.Random;

import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;
import stevekung.mods.moreplanets.module.planets.nibiru.blocks.NibiruBlocks;

public class WorldGenSporelily extends WorldGenerator
{
    @Override
    public boolean generate(World world, Random rand, BlockPos pos)
    {
        for (int i = 0; i < 10; ++i)
        {
            int j = pos.getX() + rand.nextInt(8) - rand.nextInt(8);
            int k = pos.getY() + rand.nextInt(4) - rand.nextInt(4);
            int l = pos.getZ() + rand.nextInt(8) - rand.nextInt(8);

            if (world.isAirBlock(new BlockPos(j, k, l)) && NibiruBlocks.SPORELILY.canBlockStay(world, new BlockPos(j, k, l), NibiruBlocks.SPORELILY.getDefaultState()))
            {
                world.setBlockState(new BlockPos(j, k, l), NibiruBlocks.SPORELILY.getDefaultState(), 2);
            }
        }
        return true;
    }
}