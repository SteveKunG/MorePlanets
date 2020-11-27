package stevekung.mods.moreplanets.utils.blocks.fluid;

import net.minecraft.block.Block;
import net.minecraft.block.BlockLiquid;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.fluids.IFluidBlock;

public class LiquidUtils
{
    @Deprecated
    public static boolean checkInsideBlock(EntityPlayer player, Block blockInside)
    {
        double eyeHeight = player.posY + player.getEyeHeight();
        BlockPos blockpos = new BlockPos(player.posX, eyeHeight, player.posZ);
        IBlockState iblockstate = player.world.getBlockState(blockpos);
        Block block = iblockstate.getBlock();

        if (block == blockInside)
        {
            return LiquidUtils.isInsideLiquid(player, blockpos);
        }
        else
        {
            return false;
        }
    }

    @Deprecated
    private static boolean isInsideLiquid(EntityPlayer player, BlockPos pos)
    {
        IBlockState state = player.world.getBlockState(pos);
        Block block = state.getBlock();
        double eyes = player.posY + player.getEyeHeight();
        double filled = 1.0F;

        if (block instanceof IFluidBlock)
        {
            filled = ((IFluidBlock)block).getFilledPercentage(player.world, pos);
        }
        else if (block instanceof BlockLiquid)
        {
            filled = 1.0F - (BlockLiquid.getLiquidHeightPercent(block.getMetaFromState(state)) - 1.0F / 9.0F);
        }

        if (filled < 0.0F)
        {
            return eyes > pos.getY() + (filled + 1.0F);
        }
        else
        {
            return eyes < pos.getY() + filled;
        }
    }
}