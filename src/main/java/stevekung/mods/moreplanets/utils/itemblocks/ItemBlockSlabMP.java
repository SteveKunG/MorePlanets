package stevekung.mods.moreplanets.utils.itemblocks;

import net.minecraft.block.Block;
import net.minecraft.block.BlockSlab;
import net.minecraft.block.BlockSlab.EnumBlockHalf;
import net.minecraft.block.SoundType;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import stevekung.mods.moreplanets.utils.blocks.BlockSlabMP;
import stevekung.mods.moreplanets.utils.blocks.ISlab;

public class ItemBlockSlabMP extends ItemBlockMP
{
    public ItemBlockSlabMP(Block block)
    {
        super(block);
    }

    @Override
    public EnumActionResult onItemUse(EntityPlayer player, World world, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ)
    {
        ItemStack itemStack = player.getHeldItem(hand);

        if (this.block instanceof ISlab)
        {
            ISlab singleSlab = (ISlab) this.block;

            if (itemStack.getCount() != 0 && player.canPlayerEdit(pos.offset(facing), facing, itemStack))
            {
                IBlockState state = world.getBlockState(pos);

                if (state.getBlock() == singleSlab.getHalf())
                {
                    EnumBlockHalf enumblockhalf = state.getValue(BlockSlab.HALF);

                    if (facing == EnumFacing.UP && enumblockhalf == EnumBlockHalf.BOTTOM || facing == EnumFacing.DOWN && enumblockhalf == BlockSlab.EnumBlockHalf.TOP)
                    {
                        if (singleSlab.getDouble() == null)
                        {
                            return EnumActionResult.PASS;
                        }
                        IBlockState state1 = singleSlab.getDouble().getDefaultState();
                        AxisAlignedBB axisalignedbb = state1.getCollisionBoundingBox(world, pos);

                        if (axisalignedbb != null && world.checkNoEntityCollision(axisalignedbb.offset(pos)) && world.setBlockState(pos, state1, 11))
                        {
                            SoundType sound = singleSlab.getDouble().getSoundType(state1, world, pos, player);
                            world.playSound(player, pos, sound.getPlaceSound(), SoundCategory.BLOCKS, (sound.getVolume() + 1.0F) / 2.0F, sound.getPitch() * 0.8F);
                            itemStack.shrink(1);
                        }
                        return EnumActionResult.SUCCESS;
                    }
                }
                return this.tryPlace(player, itemStack, world, pos.offset(facing), singleSlab.getHalf(), singleSlab.getDouble()) ? EnumActionResult.SUCCESS : super.onItemUse(player, world, pos, hand, facing, hitX, hitY, hitZ);
            }
        }
        return EnumActionResult.FAIL;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public boolean canPlaceBlockOnSide(World world, BlockPos pos, EnumFacing side, EntityPlayer player, ItemStack itemStack)
    {
        if (this.block instanceof ISlab)
        {
            ISlab singleSlab = (ISlab) this.block;
            IBlockState state = world.getBlockState(pos);

            if (state.getBlock() == singleSlab.getHalf())
            {
                boolean flag = state.getValue(BlockSlab.HALF) == BlockSlab.EnumBlockHalf.TOP;

                if (side == EnumFacing.UP && !flag || side == EnumFacing.DOWN && flag)
                {
                    return true;
                }
            }
            pos = pos.offset(side);
            IBlockState state1 = world.getBlockState(pos);
            return state1.getBlock() == singleSlab.getHalf() ? true : super.canPlaceBlockOnSide(world, pos, side, player, itemStack);
        }
        return super.canPlaceBlockOnSide(world, pos, side, player, itemStack);
    }

    private boolean tryPlace(EntityPlayer player, ItemStack itemStack, World world, BlockPos pos, BlockSlabMP half, BlockSlabMP doubleSlab)
    {
        IBlockState state = world.getBlockState(pos);

        if (state.getBlock() == half)
        {
            IBlockState state1 = doubleSlab.getDefaultState();
            AxisAlignedBB axisalignedbb = state1.getCollisionBoundingBox(world, pos);

            if (axisalignedbb != null && world.checkNoEntityCollision(axisalignedbb.offset(pos)) && world.setBlockState(pos, state1, 11))
            {
                SoundType soundtype = doubleSlab.getSoundType(state1, world, pos, player);
                world.playSound(player, pos, soundtype.getPlaceSound(), SoundCategory.BLOCKS, (soundtype.getVolume() + 1.0F) / 2.0F, soundtype.getPitch() * 0.8F);
                itemStack.shrink(1);
            }
            return true;
        }
        return false;
    }
}