package stevekung.mods.moreplanets.util.itemblocks;

import net.minecraft.block.Block;
import net.minecraft.block.BlockSlab;
import net.minecraft.block.BlockSlab.EnumBlockHalf;
import net.minecraft.block.SoundType;
import net.minecraft.block.properties.IProperty;
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
import stevekung.mods.moreplanets.util.blocks.BlockSlabMP;
import stevekung.mods.moreplanets.util.blocks.IBlockVariants;
import stevekung.mods.moreplanets.util.blocks.ISlabBlock;

public class ItemBlockSlabMP extends ItemBlockBaseMP
{
    public ItemBlockSlabMP(Block block)
    {
        super(block);
    }

    @Override
    public EnumActionResult onItemUse(ItemStack itemStack, EntityPlayer player, World world, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ)
    {
        if (this.block instanceof ISlabBlock)
        {
            ISlabBlock singleSlab = (ISlabBlock) this.block;

            if (itemStack.stackSize != 0 && player.canPlayerEdit(pos.offset(facing), facing, itemStack))
            {
                Object object = singleSlab.getHalf().getTypeForItem(itemStack);
                IBlockState state = world.getBlockState(pos);

                if (state.getBlock() == singleSlab.getHalf())
                {
                    IProperty iproperty = singleSlab.getHalf().getVariantProperty();
                    Comparable comparable = state.getValue(iproperty);
                    EnumBlockHalf enumblockhalf = state.getValue(BlockSlab.HALF);

                    if ((facing == EnumFacing.UP && enumblockhalf == EnumBlockHalf.BOTTOM || facing == EnumFacing.DOWN && enumblockhalf == BlockSlab.EnumBlockHalf.TOP) && comparable == object)
                    {
                        IBlockState state1 = singleSlab.getDouble().getDefaultState().withProperty(iproperty, comparable);
                        AxisAlignedBB axisalignedbb = state1.getCollisionBoundingBox(world, pos);

                        if (axisalignedbb != Block.NULL_AABB && world.checkNoEntityCollision(axisalignedbb.offset(pos)) && world.setBlockState(pos, state1, 11))
                        {
                            SoundType sound = singleSlab.getDouble().getSoundType(state1, world, pos, player);
                            world.playSound(player, pos, sound.getPlaceSound(), SoundCategory.BLOCKS, (sound.getVolume() + 1.0F) / 2.0F, sound.getPitch() * 0.8F);
                            --itemStack.stackSize;
                        }
                        return EnumActionResult.SUCCESS;
                    }
                }
                return this.tryPlace(player, itemStack, world, pos.offset(facing), object, singleSlab.getHalf(), singleSlab.getDouble()) ? EnumActionResult.SUCCESS : super.onItemUse(itemStack, player, world, pos, hand, facing, hitX, hitY, hitZ);
            }
        }
        else
        {
            return EnumActionResult.FAIL;
        }
        return EnumActionResult.FAIL;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public boolean canPlaceBlockOnSide(World world, BlockPos pos, EnumFacing side, EntityPlayer player, ItemStack itemStack)
    {
        if (this.block instanceof ISlabBlock)
        {
            ISlabBlock singleSlab = (ISlabBlock) this.block;

            BlockPos blockpos1 = pos;
            IProperty iproperty = singleSlab.getHalf().getVariantProperty();
            Object object = singleSlab.getHalf().getTypeForItem(itemStack);
            IBlockState state = world.getBlockState(pos);

            if (state.getBlock() == singleSlab.getHalf())
            {
                boolean flag = state.getValue(BlockSlab.HALF) == BlockSlab.EnumBlockHalf.TOP;

                if ((side == EnumFacing.UP && !flag || side == EnumFacing.DOWN && flag) && object == state.getValue(iproperty))
                {
                    return true;
                }
            }
            pos = pos.offset(side);
            IBlockState state1 = world.getBlockState(pos);
            return state1.getBlock() == singleSlab.getHalf() && object == state1.getValue(iproperty) ? true : super.canPlaceBlockOnSide(world, blockpos1, side, player, itemStack);
        }
        return super.canPlaceBlockOnSide(world, pos, side, player, itemStack);
    }

    @Override
    protected String[] getBlockVariantsName()
    {
        if (this.block instanceof IBlockVariants)
        {
            return ((IBlockVariants)this.block).getVariantsName().getNameList();
        }
        return new String[] {};
    }

    private boolean tryPlace(EntityPlayer player, ItemStack itemStack, World world, BlockPos pos, Object variantInStack, BlockSlabMP half, BlockSlabMP doubleSlab)
    {
        IBlockState state = world.getBlockState(pos);

        if (state.getBlock() == half)
        {
            Comparable comparable = state.getValue(half.getVariantProperty());

            if (comparable == variantInStack)
            {
                IBlockState state1 = doubleSlab.getDefaultState().withProperty((IProperty)half.getVariantProperty(), comparable);
                AxisAlignedBB axisalignedbb = state1.getCollisionBoundingBox(world, pos);

                if (axisalignedbb != Block.NULL_AABB && world.checkNoEntityCollision(axisalignedbb.offset(pos)) && world.setBlockState(pos, state1, 11))
                {
                    SoundType soundtype = doubleSlab.getSoundType(state1, world, pos, player);
                    world.playSound(player, pos, soundtype.getPlaceSound(), SoundCategory.BLOCKS, (soundtype.getVolume() + 1.0F) / 2.0F, soundtype.getPitch() * 0.8F);
                    --itemStack.stackSize;
                }
                return true;
            }
        }
        return false;
    }
}