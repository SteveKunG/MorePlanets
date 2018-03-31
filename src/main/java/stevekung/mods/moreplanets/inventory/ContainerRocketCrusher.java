package stevekung.mods.moreplanets.inventory;

import micdoodle8.mods.galacticraft.api.item.IItemElectric;
import micdoodle8.mods.galacticraft.core.energy.EnergyUtil;
import micdoodle8.mods.galacticraft.core.inventory.SlotSpecific;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.inventory.SlotFurnaceOutput;
import net.minecraft.item.ItemStack;
import stevekung.mods.moreplanets.init.MPItems;
import stevekung.mods.moreplanets.tileentity.TileEntityRocketCrusher;

public class ContainerRocketCrusher extends Container
{
    private TileEntityRocketCrusher tileEntity;

    public ContainerRocketCrusher(InventoryPlayer invPlayer, TileEntityRocketCrusher tile)
    {
        this.tileEntity = tile;
        tile.compressingCraftMatrix.eventHandler = this;

        for (int x = 0; x < 3; x++)
        {
            for (int y = 0; y < 3; y++)
            {
                this.addSlotToContainer(new Slot(tile.compressingCraftMatrix, y + x * 3, 19 + y * 18, 20 + x * 18));
            }
        }

        // Battery Slot
        this.addSlotToContainer(new SlotSpecific(tile, 0, 19, 80, IItemElectric.class));

        // Plate result
        this.addSlotToContainer(new SlotFurnaceOutput(invPlayer.player, tile, 1, 143, 38));

        // Upgrade
        this.addSlotToContainer(new SlotMachineUpgrade(tile, 2, 38, 80));

        int invSlot;

        for (invSlot = 0; invSlot < 3; ++invSlot)
        {
            for (int slotStack = 0; slotStack < 9; ++slotStack)
            {
                this.addSlotToContainer(new Slot(invPlayer, slotStack + invSlot * 9 + 9, 8 + slotStack * 18, 113 + invSlot * 18));
            }
        }

        for (invSlot = 0; invSlot < 9; ++invSlot)
        {
            this.addSlotToContainer(new Slot(invPlayer, invSlot, 8 + invSlot * 18, 171));
        }
    }

    @Override
    public boolean canInteractWith(EntityPlayer player)
    {
        return this.tileEntity.isUsableByPlayer(player);
    }

    @Override
    public void onCraftMatrixChanged(IInventory inv)
    {
        super.onCraftMatrixChanged(inv);
        this.tileEntity.updateInput();
    }

    @Override
    public ItemStack transferStackInSlot(EntityPlayer player, int slot)
    {
        ItemStack itemStack = ItemStack.EMPTY;
        Slot invSlot = this.inventorySlots.get(slot);

        if (invSlot != null && invSlot.getHasStack())
        {
            ItemStack slotStack = invSlot.getStack();
            itemStack = slotStack.copy();
            boolean movedToMachineSlot = false;

            if (slot <= 11)
            {
                if (!this.mergeItemStack(slotStack, 12, 48, true))
                {
                    return ItemStack.EMPTY;
                }
                if (slot == 1 || slot == 2)
                {
                    invSlot.onSlotChange(slotStack, itemStack);
                }
            }
            else
            {
                if (EnergyUtil.isElectricItem(slotStack.getItem()))
                {
                    if (!this.mergeItemStack(slotStack, 9, 10, false))
                    {
                        return ItemStack.EMPTY;
                    }
                    movedToMachineSlot = true;
                }
                else if (slotStack.getItem() == MPItems.MACHINE_SPEED_UPGRADE)
                {
                    if (!this.mergeItemStack(slotStack, 11, 12, false))
                    {
                        return ItemStack.EMPTY;
                    }
                }
                else if (slot < 39)
                {
                    if (!this.mergeItemStack(slotStack, 0, 9, false) && !this.mergeItemStack(slotStack, 39, 48, false))
                    {
                        return ItemStack.EMPTY;
                    }
                }
                else if (!this.mergeItemStack(slotStack, 0, 9, false) && !this.mergeItemStack(slotStack, 12, 39, false))
                {
                    return ItemStack.EMPTY;
                }
            }
            if (slotStack.getCount() == 0)
            {
                if (movedToMachineSlot && itemStack.getCount() > 1)
                {
                    ItemStack remainder = itemStack.copy();
                    remainder.shrink(1);
                    invSlot.putStack(remainder);
                }
                else
                {
                    invSlot.putStack(ItemStack.EMPTY);
                }
            }
            else
            {
                invSlot.onSlotChanged();
            }

            if (slotStack.getCount() == itemStack.getCount())
            {
                return ItemStack.EMPTY;
            }
            invSlot.onTake(player, slotStack);
        }
        return itemStack;
    }

    @Override
    public boolean canDragIntoSlot(Slot slot)
    {
        return slot.slotNumber < 9;
    }

    @Override
    protected boolean mergeItemStack(ItemStack itemStack, int startIndex, int endIndex, boolean reverseDirection)
    {
        boolean merged = false;
        int slotIndex = startIndex;

        if (reverseDirection)
        {
            slotIndex = endIndex - 1;
        }

        Slot slot;
        ItemStack slotStack;

        if (itemStack.isStackable())
        {
            while (itemStack.getCount() > 0 && (!reverseDirection && slotIndex < endIndex || reverseDirection && slotIndex >= startIndex))
            {
                slot = this.inventorySlots.get(slotIndex);
                slotStack = slot.getStack();

                if (!slotStack.isEmpty() && slotStack.getItem() == itemStack.getItem() && itemStack.getItemDamage() == slotStack.getItemDamage() && ItemStack.areItemStackTagsEqual(itemStack, slotStack) && slotStack.getCount() < slot.getSlotStackLimit())
                {
                    int mergedStackSize = itemStack.getCount() + this.getSmaller(slotStack.getCount(), slot.getSlotStackLimit());

                    if (mergedStackSize <= itemStack.getMaxStackSize() && mergedStackSize <= slot.getSlotStackLimit())
                    {
                        itemStack.setCount(0);
                        slotStack.setCount(mergedStackSize);
                        slot.onSlotChanged();
                        merged = true;
                    }
                    else if (slotStack.getCount() < itemStack.getMaxStackSize() && slotStack.getCount() < slot.getSlotStackLimit())
                    {
                        if (slot.getSlotStackLimit() >= itemStack.getMaxStackSize())
                        {
                            itemStack.shrink(itemStack.getMaxStackSize() - slotStack.getCount());
                            slotStack.setCount(itemStack.getMaxStackSize());
                            slot.onSlotChanged();
                            merged = true;
                        }
                        else if (slot.getSlotStackLimit() < itemStack.getMaxStackSize())
                        {
                            itemStack.shrink(slot.getSlotStackLimit() - slotStack.getCount());
                            slotStack.setCount(slot.getSlotStackLimit());
                            slot.onSlotChanged();
                            merged = true;
                        }
                    }
                }

                if (reverseDirection)
                {
                    --slotIndex;
                }
                else
                {
                    ++slotIndex;
                }
            }
        }

        if (itemStack.getCount() > 0)
        {
            if (reverseDirection)
            {
                slotIndex = endIndex - 1;
            }
            else
            {
                slotIndex = startIndex;
            }

            while (!reverseDirection && slotIndex < endIndex || reverseDirection && slotIndex >= startIndex)
            {
                slot = this.inventorySlots.get(slotIndex);
                slotStack = slot.getStack();

                if (slotStack.isEmpty() && slot.isItemValid(itemStack) && slot.getSlotStackLimit() < itemStack.getCount())
                {
                    ItemStack copy = itemStack.copy();
                    copy.setCount(slot.getSlotStackLimit());
                    itemStack.shrink(slot.getSlotStackLimit());
                    slot.putStack(copy);
                    slot.onSlotChanged();
                    merged = true;
                    break;
                }
                else if (slotStack.isEmpty() && slot.isItemValid(itemStack))
                {
                    slot.putStack(itemStack.copy());
                    slot.onSlotChanged();
                    itemStack.setCount(0);
                    merged = true;
                    break;
                }

                if (reverseDirection)
                {
                    --slotIndex;
                }
                else
                {
                    ++slotIndex;
                }
            }
        }
        return merged;
    }

    private int getSmaller(int slotStackSize, int slotStackLimit)
    {
        if (slotStackSize < slotStackLimit)
        {
            return slotStackSize;
        }
        else
        {
            return slotStackLimit;
        }
    }
}