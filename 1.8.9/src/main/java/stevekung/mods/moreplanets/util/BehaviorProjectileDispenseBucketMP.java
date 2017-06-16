package stevekung.mods.moreplanets.util;

import net.minecraft.block.BlockDispenser;
import net.minecraft.dispenser.BehaviorDefaultDispenseItem;
import net.minecraft.dispenser.IBlockSource;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockPos;
import stevekung.mods.moreplanets.util.items.ItemBucketMP;

public class BehaviorProjectileDispenseBucketMP extends BehaviorDefaultDispenseItem
{
    private BehaviorDefaultDispenseItem item = new BehaviorDefaultDispenseItem();

    @Override
    public ItemStack dispenseStack(IBlockSource source, ItemStack itemStack)
    {
        ItemBucketMP bucket = (ItemBucketMP)itemStack.getItem();
        BlockPos pos = source.getBlockPos().offset(BlockDispenser.getFacing(source.getBlockMetadata()));

        if (bucket.tryPlaceContainedLiquid(source.getWorld(), pos))
        {
            itemStack.setItem(Items.bucket);
            itemStack.stackSize = 1;
            return itemStack;
        }
        else
        {
            return this.item.dispense(source, itemStack);
        }
    }
}