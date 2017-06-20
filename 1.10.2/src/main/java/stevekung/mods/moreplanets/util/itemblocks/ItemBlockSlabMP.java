package stevekung.mods.moreplanets.util.itemblocks;

import net.minecraft.block.Block;
import net.minecraft.block.BlockSlab;
import net.minecraft.block.BlockSlab.EnumBlockHalf;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
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
    public boolean onItemUse(ItemStack itemStack, EntityPlayer player, World world, BlockPos pos, EnumFacing side, float hitX, float hitY, float hitZ)
    {
        if (this.block instanceof ISlabBlock)
        {
            ISlabBlock singleSlab = (ISlabBlock) this.block;

            if (itemStack.stackSize == 0)
            {
                return false;
            }
            else if (!player.canPlayerEdit(pos.offset(side), side, itemStack))
            {
                return false;
            }
            else
            {
                Object object = singleSlab.getHalf().getVariant(itemStack);
                IBlockState state = world.getBlockState(pos);

                if (state.getBlock() == singleSlab.getHalf())
                {
                    IProperty iproperty = singleSlab.getHalf().getVariantProperty();
                    Comparable comparable = state.getValue(iproperty);
                    EnumBlockHalf enumblockhalf = state.getValue(BlockSlab.HALF);

                    if ((side == EnumFacing.UP && enumblockhalf == EnumBlockHalf.BOTTOM || side == EnumFacing.DOWN && enumblockhalf == BlockSlab.EnumBlockHalf.TOP) && comparable == object)
                    {
                        IBlockState state1 = singleSlab.getDouble().getDefaultState().withProperty(iproperty, comparable);

                        if (world.checkNoEntityCollision(singleSlab.getDouble().getCollisionBoundingBox(world, pos, state1)) && world.setBlockState(pos, state1, 3))
                        {
                            world.playSoundEffect(pos.getX() + 0.5F, pos.getY() + 0.5F, pos.getZ() + 0.5F, singleSlab.getDouble().stepSound.getPlaceSound(), (singleSlab.getDouble().stepSound.getVolume() + 1.0F) / 2.0F, singleSlab.getDouble().stepSound.getFrequency() * 0.8F);
                            --itemStack.stackSize;
                        }
                        return true;
                    }
                }
                return this.tryPlace(itemStack, world, pos.offset(side), object, singleSlab.getHalf(), singleSlab.getDouble()) ? true : super.onItemUse(itemStack, player, world, pos, side, hitX, hitY, hitZ);
            }
        }
        return super.onItemUse(itemStack, player, world, pos, side, hitX, hitY, hitZ);
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
            Object object = singleSlab.getHalf().getVariant(itemStack);
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

    private boolean tryPlace(ItemStack itemStack, World world, BlockPos pos, Object variantInStack, BlockSlabMP half, BlockSlabMP doubleSlab)
    {
        IBlockState state = world.getBlockState(pos);

        if (state.getBlock() == half)
        {
            Comparable comparable = state.getValue(half.getVariantProperty());

            if (comparable == variantInStack)
            {
                IBlockState state1 = doubleSlab.getDefaultState().withProperty((IProperty)half.getVariantProperty(), comparable);

                if (world.checkNoEntityCollision(doubleSlab.getCollisionBoundingBox(world, pos, state1)) && world.setBlockState(pos, state1, 3))
                {
                    world.playSoundEffect(pos.getX() + 0.5F, pos.getY() + 0.5F, pos.getZ() + 0.5F, doubleSlab.stepSound.getPlaceSound(), (doubleSlab.stepSound.getVolume() + 1.0F) / 2.0F, doubleSlab.stepSound.getFrequency() * 0.8F);
                    --itemStack.stackSize;
                }
                return true;
            }
        }
        return false;
    }
}