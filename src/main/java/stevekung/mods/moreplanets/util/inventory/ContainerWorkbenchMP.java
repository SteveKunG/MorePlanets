package stevekung.mods.moreplanets.util.inventory;

import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.*;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.CraftingManager;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class ContainerWorkbenchMP extends Container
{
    private final InventoryCrafting craftMatrix = new InventoryCrafting(this, 3, 3);
    private final IInventory craftResult = new InventoryCraftResult();
    private final World worldObj;
    private final BlockPos pos;
    private final Block table;

    public ContainerWorkbenchMP(InventoryPlayer inv, World world, BlockPos pos, Block table)
    {
        this.worldObj = world;
        this.pos = pos;
        this.table = table;
        this.addSlotToContainer(new SlotCrafting(inv.player, this.craftMatrix, this.craftResult, 0, 124, 35));

        for (int i = 0; i < 3; ++i)
        {
            for (int j = 0; j < 3; ++j)
            {
                this.addSlotToContainer(new Slot(this.craftMatrix, j + i * 3, 30 + j * 18, 17 + i * 18));
            }
        }
        for (int k = 0; k < 3; ++k)
        {
            for (int i1 = 0; i1 < 9; ++i1)
            {
                this.addSlotToContainer(new Slot(inv, i1 + k * 9 + 9, 8 + i1 * 18, 84 + k * 18));
            }
        }
        for (int l = 0; l < 9; ++l)
        {
            this.addSlotToContainer(new Slot(inv, l, 8 + l * 18, 142));
        }
        this.onCraftMatrixChanged(this.craftMatrix);
    }

    @Override
    public void onCraftMatrixChanged(IInventory inventory)
    {
        this.craftResult.setInventorySlotContents(0, CraftingManager.findMatchingResult(this.craftMatrix, this.worldObj));
    }

    @Override
    public void onContainerClosed(EntityPlayer player)
    {
        super.onContainerClosed(player);

        if (!this.worldObj.isRemote)
        {
            for (int i = 0; i < 9; ++i)
            {
                ItemStack itemStack = this.craftMatrix.removeStackFromSlot(i);

                if (!itemStack.isEmpty())
                {
                    player.entityDropItem(itemStack, 0.0F);
                }
            }
        }
    }

    @Override
    public boolean canInteractWith(EntityPlayer player)
    {
        return this.worldObj.getBlockState(this.pos).getBlock() != this.table ? false : player.getDistanceSq(this.pos.getX() + 0.5D, this.pos.getY() + 0.5D, this.pos.getZ() + 0.5D) <= 64.0D;
    }

    @Override
    public ItemStack transferStackInSlot(EntityPlayer player, int index)
    {
        ItemStack itemStack = ItemStack.EMPTY;
        Slot slot = this.inventorySlots.get(index);

        if (slot != null && slot.getHasStack())
        {
            ItemStack itemStack1 = slot.getStack();
            itemStack = itemStack1.copy();

            if (index == 0)
            {
                if (!this.mergeItemStack(itemStack1, 10, 46, true))
                {
                    return ItemStack.EMPTY;
                }
                slot.onSlotChange(itemStack1, itemStack);
            }
            else if (index >= 10 && index < 37)
            {
                if (!this.mergeItemStack(itemStack1, 37, 46, false))
                {
                    return ItemStack.EMPTY;
                }
            }
            else if (index >= 37 && index < 46)
            {
                if (!this.mergeItemStack(itemStack1, 10, 37, false))
                {
                    return ItemStack.EMPTY;
                }
            }
            else if (!this.mergeItemStack(itemStack1, 10, 46, false))
            {
                return ItemStack.EMPTY;
            }

            if (itemStack1.getCount() == 0)
            {
                slot.putStack(ItemStack.EMPTY);
            }
            else
            {
                slot.onSlotChanged();
            }

            if (itemStack1.getCount() == itemStack.getCount())
            {
                return ItemStack.EMPTY;
            }
            slot.onTake(player, itemStack1);
        }
        return itemStack;
    }

    @Override
    public boolean canMergeSlot(ItemStack itemStack, Slot slot)
    {
        return slot.inventory != this.craftResult && super.canMergeSlot(itemStack, slot);
    }
}