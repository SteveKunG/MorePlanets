package com.stevekung.moreplanets.planets.nibiru.items;

import com.stevekung.moreplanets.init.MPBlocks;
import com.stevekung.moreplanets.utils.items.MPItemCategory;
import com.stevekung.moreplanets.utils.items.ItemBaseMP;

import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class ItemInfectedMelonSeeds extends ItemBaseMP
{
    public ItemInfectedMelonSeeds(String name)
    {
        super(name);
    }

    @Override
    public EnumActionResult onItemUse(EntityPlayer player, World world, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ)
    {
        ItemStack itemStack = player.getHeldItem(hand);
        IBlockState state = world.getBlockState(pos);

        if (facing == EnumFacing.UP && player.canPlayerEdit(pos.offset(facing), facing, itemStack) && state.getBlock() == MPBlocks.INFECTED_FARMLAND && world.isAirBlock(pos.up()))
        {
            world.setBlockState(pos.up(), MPBlocks.INFECTED_MELON_STEM.getDefaultState(), 11);
            itemStack.shrink(1);
            return EnumActionResult.SUCCESS;
        }
        else
        {
            return EnumActionResult.FAIL;
        }
    }

    @Override
    public MPItemCategory getItemCategory()
    {
        return MPItemCategory.PLANT_SEEDS;
    }
}