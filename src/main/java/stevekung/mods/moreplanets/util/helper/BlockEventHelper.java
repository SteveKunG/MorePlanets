package stevekung.mods.moreplanets.util.helper;

import net.minecraft.block.BlockLiquid;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fluids.BlockFluidBase;

public class BlockEventHelper
{
    public static boolean isLiquidBlock(World world, BlockPos pos)
    {
        return world.getBlockState(pos).getBlock() instanceof BlockLiquid || world.getBlockState(pos).getBlock() instanceof BlockFluidBase;
    }
}