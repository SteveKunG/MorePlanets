package stevekung.mods.moreplanets.util.items;

import javax.annotation.Nullable;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
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
    public ActionResult<ItemStack> onItemRightClick(World world, EntityPlayer player, EnumHand hand)
    {
        ItemStack itemStack = player.getHeldItem(hand);
        boolean flag = this.isFull == Blocks.AIR;
        RayTraceResult raytraceresult = this.rayTrace(world, player, flag);

        if (raytraceresult == null)
        {
            return new ActionResult(EnumActionResult.PASS, itemStack);
        }
        else
        {
            if (raytraceresult.typeOfHit == RayTraceResult.Type.BLOCK)
            {
                BlockPos blockpos = raytraceresult.getBlockPos();

                if (!world.isBlockModifiable(player, blockpos))
                {
                    return new ActionResult(EnumActionResult.FAIL, itemStack);
                }
                if (flag)
                {
                    if (!player.canPlayerEdit(blockpos.offset(raytraceresult.sideHit), raytraceresult.sideHit, itemStack))
                    {
                        return new ActionResult(EnumActionResult.FAIL, itemStack);
                    }
                }
                else
                {
                    BlockPos blockpos1 = blockpos.offset(raytraceresult.sideHit);

                    if (!player.canPlayerEdit(blockpos1, raytraceresult.sideHit, itemStack))
                    {
                        return new ActionResult(EnumActionResult.FAIL, itemStack);
                    }
                    if (this.tryPlaceContainedLiquid(player, world, blockpos1) && !player.capabilities.isCreativeMode)
                    {
                        return !player.capabilities.isCreativeMode ? new ActionResult(EnumActionResult.SUCCESS, new ItemStack(Items.BUCKET)) : new ActionResult(EnumActionResult.SUCCESS, itemStack);
                    }
                }
            }
            return new ActionResult(EnumActionResult.FAIL, itemStack);
        }
    }

    @Override
    public boolean tryPlaceContainedLiquid(@Nullable EntityPlayer player, World world, BlockPos pos)
    {
        if (this.isFull == Blocks.AIR)
        {
            return false;
        }
        else
        {
            Material material = world.getBlockState(pos).getMaterial();
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