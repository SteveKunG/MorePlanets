package stevekung.mods.stevekunglib.utils;

import net.minecraft.block.Block;
import net.minecraft.block.BlockLiquid;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fluids.BlockFluidBase;
import stevekung.mods.stevekunglib.utils.enums.EnumHarvestLevel;

public class BlockUtils
{
    public static void setFireBurn(Block block, int encouragement, int flammibility)
    {
        Blocks.FIRE.setFireInfo(block, encouragement, flammibility);
    }

    public static void setBlockHarvestLevel(Block block, EnumHarvestLevel harvestLevel, int level)
    {
        block.setHarvestLevel(harvestLevel.toString(), level);
    }

    public static void setToolHarvestLevel(Item item, EnumHarvestLevel harvestLevel, int level)
    {
        item.setHarvestLevel(harvestLevel.toString(), level);
    }

    public static boolean isFluid(World world, BlockPos pos)
    {
        return world.getBlockState(pos).getBlock() instanceof BlockLiquid || world.getBlockState(pos).getBlock() instanceof BlockFluidBase;
    }
}