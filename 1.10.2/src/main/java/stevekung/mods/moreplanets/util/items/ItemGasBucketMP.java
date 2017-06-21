package stevekung.mods.moreplanets.util.items;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fluids.BlockFluidBase;

public class ItemGasBucketMP extends ItemBucketMP
{
    private Block isFull;
    private int gasState;

    public ItemGasBucketMP(String name, Block gas)
    {
        super();
        this.name = name;
        this.isFull = gas;
        this.setUnlocalizedName(name);
        this.setContainerItem(Items.BUCKET);
    }

    public ItemGasBucketMP(String name, Block gas, int gasState)
    {
        super();
        this.isFull = gas;
        this.gasState = gasState;
        this.setUnlocalizedName(name);
        this.setContainerItem(Items.BUCKET);
    }

    @Override
    public ItemStack onItemRightClick(ItemStack itemStack, World world, EntityPlayer player)
    {
        boolean flag = this.isFull == Blocks.air;
        MovingObjectPosition movingobjectposition = this.getMovingObjectPositionFromPlayer(world, player, flag);

        if (movingobjectposition == null)
        {
            return itemStack;
        }
        else
        {
            if (movingobjectposition.typeOfHit == MovingObjectPosition.MovingObjectType.BLOCK)
            {
                BlockPos blockpos = movingobjectposition.getBlockPos();

                if (!world.isBlockModifiable(player, blockpos))
                {
                    return itemStack;
                }

                if (flag)
                {
                    if (!player.canPlayerEdit(blockpos.offset(movingobjectposition.sideHit), movingobjectposition.sideHit, itemStack))
                    {
                        return itemStack;
                    }
                }
                else
                {
                    if (this.isFull == Blocks.air)
                    {
                        return new ItemStack(Items.BUCKET);
                    }

                    BlockPos blockpos1 = blockpos.offset(movingobjectposition.sideHit);

                    if (!player.canPlayerEdit(blockpos1, movingobjectposition.sideHit, itemStack))
                    {
                        return itemStack;
                    }
                    if (this.tryPlaceContainedLiquid(world, blockpos1) && !player.capabilities.isCreativeMode)
                    {
                        return new ItemStack(Items.BUCKET);
                    }
                }
            }
            return itemStack;
        }
    }

    @Override
    public boolean tryPlaceContainedLiquid(World world, BlockPos pos)
    {
        if (this.isFull == Blocks.AIR)
        {
            return false;
        }
        else
        {
            Material material = world.getBlockState(pos).getBlock().getMaterial();
            boolean flag = !material.isSolid();

            if (!world.isAirBlock(pos) && !flag)
            {
                return false;
            }
            else
            {
                if (!world.isRemote && flag && !material.isLiquid())
                {
                    world.destroyBlock(pos, true);
                }
                world.setBlockState(pos, this.isFull.getDefaultState().withProperty(BlockFluidBase.LEVEL, 7 - this.gasState), 3);
                return true;
            }
        }
    }

    @Override
    public EnumSortCategoryItem getItemCategory(int meta)
    {
        return EnumSortCategoryItem.BUCKET_GAS;
    }
}