package stevekung.mods.moreplanets.module.planets.chalos.items;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;
import stevekung.mods.moreplanets.module.planets.chalos.blocks.ChalosBlocks;
import stevekung.mods.moreplanets.util.items.EnumSortCategoryItem;
import stevekung.mods.moreplanets.util.items.ItemBaseMP;

public class ItemCheeseSporeSeed extends ItemBaseMP
{
    public ItemCheeseSporeSeed(String name)
    {
        super();
        this.setUnlocalizedName(name);
    }

    @Override
    public boolean onItemUse(ItemStack itemStack, EntityPlayer player, World world, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ)
    {
        if (facing != EnumFacing.UP)
        {
            return false;
        }
        else if (!player.canPlayerEdit(pos.offset(facing), facing, itemStack))
        {
            return false;
        }
        else if (world.getBlockState(pos).getBlock() == ChalosBlocks.CHEESE_FARMLAND && world.isAirBlock(pos.up()))
        {
            world.setBlockState(pos.up(), ChalosBlocks.CHEESE_SPORE_BERRY_CROPS.getDefaultState());
            --itemStack.stackSize;
            return true;
        }
        else
        {
            return false;
        }
    }

    @Override
    public EnumSortCategoryItem getItemCategory(int meta)
    {
        return EnumSortCategoryItem.PLANT_SEEDS;
    }

    @Override
    public String getName()
    {
        return "cheese_spore_seed";
    }
}