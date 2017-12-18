package stevekung.mods.moreplanets.util.blocks;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.BlockDoor;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;

public class BlockDoorMP extends BlockDoor
{
    private Item doorItem;

    public BlockDoorMP(String name)
    {
        super(Material.wood);
        this.setHardness(3.0F);
        this.setStepSound(soundTypeWood);
        this.setUnlocalizedName(name);
        this.setDefaultState(this.getDefaultState().withProperty(FACING, EnumFacing.NORTH).withProperty(OPEN, Boolean.valueOf(false)).withProperty(HINGE, BlockDoorMP.EnumHingePosition.LEFT).withProperty(POWERED, Boolean.valueOf(false)).withProperty(HALF, BlockDoorMP.EnumDoorHalf.LOWER));
    }

    @Override
    public ItemStack getPickBlock(MovingObjectPosition mov, World world, BlockPos pos, EntityPlayer player)
    {
        return new ItemStack(this.doorItem);
    }

    @Override
    public Item getItemDropped(IBlockState state, Random rand, int fortune)
    {
        if (!(state.getValue(HALF) == BlockDoor.EnumDoorHalf.UPPER))
        {
            return this.doorItem;
        }
        return null;
    }

    @Override
    public void onNeighborBlockChange(World world, BlockPos pos, IBlockState state, Block neighborBlock)
    {
        if (state.getValue(HALF) == BlockDoor.EnumDoorHalf.UPPER)
        {
            if (world.getBlockState(pos.down()).getBlock() != this)
            {
                world.setBlockToAir(pos);
            }
            else if (neighborBlock != this)
            {
                this.onNeighborBlockChange(world, pos.down(), world.getBlockState(pos.down()), neighborBlock);
            }
        }
        else
        {
            boolean flag1 = false;

            if (world.getBlockState(pos.up()).getBlock() != this)
            {
                world.setBlockToAir(pos);
                flag1 = true;
            }

            if (!World.doesBlockHaveSolidTopSurface(world, pos.down()))
            {
                world.setBlockToAir(pos);
                flag1 = true;

                if (world.getBlockState(pos.up()).getBlock() == this)
                {
                    world.setBlockToAir(pos.up());
                }
            }

            if (flag1)
            {
                if (!world.isRemote)
                {
                    this.dropBlockAsItem(world, pos, state, 0);
                }
            }
            else
            {
                boolean flag = world.isBlockPowered(pos) || world.isBlockPowered(pos.up());

                if ((flag || neighborBlock.canProvidePower()) && neighborBlock != this && flag != world.getBlockState(pos.up()).getValue(POWERED).booleanValue())
                {
                    world.setBlockState(pos.up(), world.getBlockState(pos.up()).withProperty(POWERED, Boolean.valueOf(flag)), 2);

                    if (flag != state.getValue(OPEN).booleanValue())
                    {
                        world.setBlockState(pos, state.withProperty(OPEN, Boolean.valueOf(flag)), 2);
                        world.markBlockRangeForRenderUpdate(pos, pos);
                        world.playAuxSFXAtEntity((EntityPlayer)null, flag ? 1003 : 1006, pos, 0);
                    }
                }
            }
        }
    }

    public void setDoorItem(Item item)
    {
        this.doorItem = item;
    }
}