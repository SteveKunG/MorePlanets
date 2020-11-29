package stevekung.mods.moreplanets.planets.fronos.item;

import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import stevekung.mods.moreplanets.init.MPBlocks;
import stevekung.mods.moreplanets.utils.itemblocks.ItemBlockMP;

public class ItemBlockFronosMushroom extends ItemBlockMP
{
    public ItemBlockFronosMushroom(Block block)
    {
        super(block);
    }

    @Override
    public EnumActionResult onItemUse(EntityPlayer player, World world, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ)
    {
        ItemStack itemstack = player.getHeldItem(hand);

        if (!player.canPlayerEdit(pos.offset(facing), facing, itemstack))
        {
            return EnumActionResult.FAIL;
        }
        else
        {
            IBlockState iblockstate = world.getBlockState(pos);
            Block block = iblockstate.getBlock();

            if (block == MPBlocks.OSCALEA_LOG)
            {
                if (facing == EnumFacing.DOWN || facing == EnumFacing.UP)
                {
                    return EnumActionResult.FAIL;
                }

                pos = pos.offset(facing);

                if (world.isAirBlock(pos))
                {
                    IBlockState iblockstate1 = MPBlocks.FRONOS_MUSHROOM.getStateForPlacement(world, pos, facing, hitX, hitY, hitZ, 0, player, hand);
                    SoundType soundtype = world.getBlockState(pos).getBlock().getSoundType(world.getBlockState(pos), world, pos, player);
                    world.playSound(player, pos, SoundEvents.BLOCK_GRASS_PLACE, SoundCategory.BLOCKS, (soundtype.getVolume() + 1.0F) / 2.0F, soundtype.getPitch() * 0.8F);
                    world.setBlockState(pos, iblockstate1, 10);

                    if (!player.capabilities.isCreativeMode)
                    {
                        itemstack.shrink(1);
                    }
                    return EnumActionResult.SUCCESS;
                }
            }
            return EnumActionResult.FAIL;
        }
    }
}