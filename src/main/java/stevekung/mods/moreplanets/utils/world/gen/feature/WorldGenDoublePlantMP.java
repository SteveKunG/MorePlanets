package stevekung.mods.moreplanets.utils.world.gen.feature;

import java.util.Random;

import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;
import stevekung.mods.moreplanets.utils.blocks.BlockDoublePlantMP;

public class WorldGenDoublePlantMP extends WorldGenerator
{
    private BlockDoublePlantMP block;

    public WorldGenDoublePlantMP(BlockDoublePlantMP block)
    {
        this.block = block;
    }

    @Override
    public boolean generate(World world, Random rand, BlockPos pos)
    {
        boolean flag = false;

        for (int i = 0; i < 64; ++i)
        {
            BlockPos blockpos = pos.add(rand.nextInt(8) - rand.nextInt(8), rand.nextInt(4) - rand.nextInt(4), rand.nextInt(8) - rand.nextInt(8));

            if (world.isAirBlock(blockpos) && (!world.provider.isNether() || blockpos.getY() < 254) && this.block.canPlaceBlockAt(world, blockpos))
            {
                this.block.placeAt(world, blockpos, this.block, 2);
                flag = true;
            }
        }
        return flag;
    }
}