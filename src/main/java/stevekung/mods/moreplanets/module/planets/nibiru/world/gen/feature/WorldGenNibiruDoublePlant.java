package stevekung.mods.moreplanets.module.planets.nibiru.world.gen.feature;

import java.util.Random;

import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;
import stevekung.mods.moreplanets.module.planets.nibiru.blocks.BlockNibiruDoublePlant;
import stevekung.mods.moreplanets.module.planets.nibiru.blocks.NibiruBlocks;

public class WorldGenNibiruDoublePlant extends WorldGenerator
{
    private BlockNibiruDoublePlant.BlockType type;

    public WorldGenNibiruDoublePlant(BlockNibiruDoublePlant.BlockType type)
    {
        this.type = type;
    }

    @Override
    public boolean generate(World world, Random rand, BlockPos pos)
    {
        boolean flag = false;

        for (int i = 0; i < 64; ++i)
        {
            BlockPos blockpos = pos.add(rand.nextInt(8) - rand.nextInt(8), rand.nextInt(4) - rand.nextInt(4), rand.nextInt(8) - rand.nextInt(8));

            if (world.isAirBlock(blockpos) && (!world.provider.hasNoSky() || blockpos.getY() < 254) && NibiruBlocks.NIBIRU_DOUBLE_PLANT.canBlockStay(world, blockpos, NibiruBlocks.NIBIRU_DOUBLE_PLANT.getDefaultState().withProperty(BlockNibiruDoublePlant.VARIANT, this.type)))
            {
                NibiruBlocks.NIBIRU_DOUBLE_PLANT.placeAt(world, blockpos, this.type, 2);
                flag = true;
            }
        }
        return flag;
    }
}