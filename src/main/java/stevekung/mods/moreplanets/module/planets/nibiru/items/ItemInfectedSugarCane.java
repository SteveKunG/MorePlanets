package stevekung.mods.moreplanets.module.planets.nibiru.items;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;
import stevekung.mods.moreplanets.module.planets.nibiru.blocks.NibiruBlocks;
import stevekung.mods.moreplanets.util.blocks.BlockBushMP;
import stevekung.mods.moreplanets.util.helper.BlockStateHelper;
import stevekung.mods.moreplanets.util.items.EnumSortCategoryItem;
import stevekung.mods.moreplanets.util.items.ItemBaseMP;

public class ItemInfectedSugarCane extends ItemBaseMP
{
    public ItemInfectedSugarCane(String name)
    {
        super();
        this.setUnlocalizedName(name);
    }

    @Override
    public boolean onItemUse(ItemStack itemStack, EntityPlayer player, World world, BlockPos pos, EnumFacing side, float hitX, float hitY, float hitZ)
    {
        IBlockState iblockstate = world.getBlockState(pos);
        Block block = iblockstate.getBlock();
        BlockBushMP cane = NibiruBlocks.INFECTED_SUGAR_CANE_BLOCK;

        if (block == NibiruBlocks.INFECTED_SNOW_LAYER && iblockstate.getValue(BlockStateHelper.LAYERS).intValue() < 1)
        {
            side = EnumFacing.UP;
        }
        else if (!block.isReplaceable(world, pos))
        {
            pos = pos.offset(side);
        }

        if (!player.canPlayerEdit(pos, side, itemStack))
        {
            return false;
        }
        else if (itemStack.stackSize == 0)
        {
            return false;
        }
        else if (cane.canBlockStay(world, pos, cane.getDefaultState()) && world.isAirBlock(pos.up()))
        {
            if (world.getBlockState(pos).getBlock() != cane)
            {
                world.playSoundEffect(pos.getX() + 0.5F, pos.getY() + 0.5F, pos.getZ() + 0.5F, cane.stepSound.getPlaceSound(), (cane.stepSound.getVolume() + 1.0F) / 2.0F, cane.stepSound.getFrequency() * 0.8F);
                world.setBlockState(pos, cane.getDefaultState());
                --itemStack.stackSize;
                return true;
            }
            else
            {
                return false;
            }
        }
        return false;
    }

    @Override
    public EnumSortCategoryItem getItemCategory(int meta)
    {
        return EnumSortCategoryItem.PLACEABLE_PLANT;
    }

    @Override
    public String getName()
    {
        return "infected_sugar_cane";
    }
}