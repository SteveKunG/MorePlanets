package stevekung.mods.stevekunglib.utils;

import net.minecraft.block.Block;
import net.minecraft.block.BlockLiquid;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fluids.BlockFluidBase;
import net.minecraftforge.oredict.OreDictionary;

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

    @Deprecated
    public static void setBlockHarvestLevel(Block block, EnumHarvestLevel harvestLevel, int level, int meta) //TODO Remove 1.13
    {
        block.setHarvestLevel(harvestLevel.toString(), level, block.getStateFromMeta(meta));
    }

    public static void setToolHarvestLevel(Item item, EnumHarvestLevel harvestLevel, int level)
    {
        item.setHarvestLevel(harvestLevel.toString(), level);
    }

    public static boolean isFluid(World world, BlockPos pos)
    {
        return world.getBlockState(pos).getBlock() instanceof BlockLiquid || world.getBlockState(pos).getBlock() instanceof BlockFluidBase;
    }

    public static void registerOreDictionary(String name, Block block)//TODO Remove 1.13
    {
        OreDictionary.registerOre(name, block);
    }

    public static void registerOreDictionary(String name, Item item)//TODO Remove 1.13
    {
        OreDictionary.registerOre(name, item);
    }

    public static void registerOreDictionary(String name, ItemStack itemStack)//TODO Remove 1.13
    {
        OreDictionary.registerOre(name, itemStack);
    }
}