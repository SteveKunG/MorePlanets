package stevekung.mods.moreplanets.module.planets.nibiru.items;

import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import stevekung.mods.moreplanets.module.planets.nibiru.blocks.NibiruBlocks;
import stevekung.mods.moreplanets.util.items.EnumSortCategoryItem;
import stevekung.mods.moreplanets.util.items.ItemBaseMP;

public class ItemInfectedWheatSeeds extends ItemBaseMP
{
    public ItemInfectedWheatSeeds(String name)
    {
        super();
        this.setUnlocalizedName(name);
    }

    @Override
    public EnumActionResult onItemUse(EntityPlayer player, World world, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ)
    {
        ItemStack itemStack = player.getHeldItem(hand);
        IBlockState state = world.getBlockState(pos);

        if (facing == EnumFacing.UP && player.canPlayerEdit(pos.offset(facing), facing, itemStack) && state.getBlock() == NibiruBlocks.INFECTED_FARMLAND && world.isAirBlock(pos.up()))
        {
            world.setBlockState(pos.up(), NibiruBlocks.INFECTED_WHEAT_BLOCK.getDefaultState(), 11);
            itemStack.shrink(1);
            return EnumActionResult.SUCCESS;
        }
        else
        {
            return EnumActionResult.FAIL;
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
        return "infected_wheat_seeds";
    }
}