package stevekung.mods.moreplanets.utils.itemblocks;

import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class ItemBlockMultiFlower extends ItemBlockMultiVariant
{
    public ItemBlockMultiFlower(Block block)
    {
        super(block);
    }

    @Override
    public EnumActionResult onItemUse(EntityPlayer player, World world, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ)
    {
        IBlockState state = world.getBlockState(pos);
        Block block = state.getBlock();
        ItemStack itemStack = player.getHeldItem(hand);

        if (!block.isReplaceable(world, pos))
        {
            pos = pos.offset(facing);
        }
        if (!itemStack.isEmpty() && player.canPlayerEdit(pos, facing, itemStack) && world.mayPlace(this.block, pos, false, facing, (Entity)null))
        {
            int i = this.getMetadata(itemStack.getMetadata());
            IBlockState iblockstate1 = this.block.getStateForPlacement(world, pos, facing, hitX, hitY, hitZ, i, player, hand);

            if (this.placeBlockAt(itemStack, player, world, pos, facing, hitX, hitY, hitZ, iblockstate1))
            {
                SoundType sound = world.getBlockState(pos).getBlock().getSoundType(world.getBlockState(pos), world, pos, player);
                world.playSound(player, pos, sound.getPlaceSound(), SoundCategory.BLOCKS, (sound.getVolume() + 1.0F) / 2.0F, sound.getPitch() * 0.8F);
                itemStack.shrink(1);
                return EnumActionResult.SUCCESS;
            }
            return EnumActionResult.PASS;
        }
        else
        {
            return EnumActionResult.FAIL;
        }
    }
}