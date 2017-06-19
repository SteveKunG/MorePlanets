package stevekung.mods.moreplanets.util;

import net.minecraft.block.BlockDispenser;
import net.minecraft.dispenser.BehaviorDefaultDispenseItem;
import net.minecraft.dispenser.IBlockSource;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import stevekung.mods.moreplanets.util.items.ItemBucketMP;

public class BehaviorProjectileDispenseBucketMP extends BehaviorDefaultDispenseItem
{
    private BehaviorDefaultDispenseItem item = new BehaviorDefaultDispenseItem();

    @Override
    public ItemStack dispenseStack(IBlockSource source, ItemStack itemStack)
    {
        ItemBucketMP bucket = (ItemBucketMP)itemStack.getItem();
        BlockPos blockpos = source.getBlockPos().offset((EnumFacing)source.getBlockState().getValue(BlockDispenser.FACING));

        if (bucket.tryPlaceContainedLiquid((EntityPlayer)null, source.getWorld(), blockpos))
        {
            itemStack.setItem(Items.BUCKET);
            itemStack.stackSize = 1;
            return itemStack;
        }
        else
        {
            return this.dispense(source, itemStack);
        }
    }
}