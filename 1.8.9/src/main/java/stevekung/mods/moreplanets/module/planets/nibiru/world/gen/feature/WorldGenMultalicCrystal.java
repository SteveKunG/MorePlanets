package stevekung.mods.moreplanets.module.planets.nibiru.world.gen.feature;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;
import stevekung.mods.moreplanets.module.planets.nibiru.blocks.NibiruBlocks;
import stevekung.mods.moreplanets.module.planets.nibiru.tileentity.TileEntityMultalicCrystal;

public class WorldGenMultalicCrystal extends WorldGenerator
{
    @Override
    public boolean generate(World world, Random rand, BlockPos pos)
    {
        for (EnumFacing facing : EnumFacing.VALUES)
        {
            Block block = NibiruBlocks.MULTALIC_CRYSTAL;

            if (world.isAirBlock(pos) && block.canPlaceBlockOnSide(world, pos, facing))
            {
                world.setBlockState(pos, block.getDefaultState(), 2);
                TileEntityMultalicCrystal ts = (TileEntityMultalicCrystal)world.getTileEntity(pos);
                ts.facing = facing.getIndex();
            }
        }
        return true;
    }
}