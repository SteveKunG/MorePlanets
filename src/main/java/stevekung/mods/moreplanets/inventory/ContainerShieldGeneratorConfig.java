package stevekung.mods.moreplanets.inventory;

import micdoodle8.mods.galacticraft.api.item.IItemElectric;
import micdoodle8.mods.galacticraft.core.energy.EnergyUtil;
import micdoodle8.mods.galacticraft.core.inventory.SlotSpecific;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import stevekung.mods.moreplanets.init.MPItems;
import stevekung.mods.moreplanets.tileentity.TileEntityShieldGenerator;

public class ContainerShieldGeneratorConfig extends Container
{
    private TileEntityShieldGenerator tile;

    public ContainerShieldGeneratorConfig(InventoryPlayer inventory, TileEntityShieldGenerator tile)
    {
        this.tile = tile;
        this.addSlotToContainer(new SlotSpecific(tile, 0, 152, 78, IItemElectric.class));
        this.addSlotToContainer(new SlotShieldGeneratorUpgrade(tile, 1, 152, 24, "damage"));
        this.addSlotToContainer(new SlotShieldGeneratorUpgrade(tile, 2, 152, 42, "size"));
        this.addSlotToContainer(new SlotShieldGeneratorUpgrade(tile, 3, 152, 60, "capacity"));

        int i;
        int j;

        for (i = 0; i < 3; ++i)
        {
            for (j = 0; j < 9; ++j)
            {
                this.addSlotToContainer(new Slot(inventory, j + i * 9 + 9, 8 + j * 18, 136 + i * 18));
            }
        }
        for (i = 0; i < 9; ++i)
        {
            this.addSlotToContainer(new Slot(inventory, i, 8 + i * 18, 194));
        }
    }

    @Override
    public boolean canInteractWith(EntityPlayer player)
    {
        return this.tile.isUsableByPlayer(player);
    }

    @Override
    public ItemStack transferStackInSlot(EntityPlayer player, int index)
    {
        ItemStack itemStack = ItemStack.EMPTY;
        Slot slot = this.inventorySlots.get(index);
        int invSize = this.inventorySlots.size();

        if (slot != null && slot.getHasStack())
        {
            ItemStack slotStack = slot.getStack();
            itemStack = slotStack.copy();
            boolean movedToMachineSlot = false;

            if (index == 0 || index == 1 || index == 2 || index == 3)
            {
                if (!this.mergeItemStack(slotStack, invSize - 36, invSize, true))
                {
                    return ItemStack.EMPTY;
                }
            }
            else
            {
                if (EnergyUtil.isElectricItem(slotStack.getItem()))
                {
                    if (!this.mergeItemStack(slotStack, 0, 1, false))
                    {
                        return ItemStack.EMPTY;
                    }
                    movedToMachineSlot = true;
                }
                else if (slotStack.getItem() == MPItems.SHIELD_DAMAGE_UPGRADE)
                {
                    if (!this.mergeItemStack(slotStack, 1, 2, false))
                    {
                        return ItemStack.EMPTY;
                    }
                }
                else if (slotStack.getItem() == MPItems.SHIELD_SIZE_UPGRADE)
                {
                    if (!this.mergeItemStack(slotStack, 2, 3, false))
                    {
                        return ItemStack.EMPTY;
                    }
                }
                else if (slotStack.getItem() == MPItems.SHIELD_CAPACITY_UPGRADE)
                {
                    if (!this.mergeItemStack(slotStack, 3, 4, false))
                    {
                        return ItemStack.EMPTY;
                    }
                }
                else
                {
                    if (index < invSize - 9)
                    {
                        if (!this.mergeItemStack(slotStack, invSize - 9, invSize, false))
                        {
                            return ItemStack.EMPTY;
                        }
                    }
                    else if (!this.mergeItemStack(slotStack, invSize - 36, invSize - 9, false))
                    {
                        return ItemStack.EMPTY;
                    }
                    movedToMachineSlot = true;
                }
            }
            if (slotStack.getCount() == 0)
            {
                if (movedToMachineSlot && itemStack.getCount() > 1)
                {
                    ItemStack remainder = itemStack.copy();
                    remainder.shrink(1);
                    slot.putStack(remainder);
                }
                else
                {
                    slot.putStack(ItemStack.EMPTY);
                }
            }
            else
            {
                slot.onSlotChanged();
            }
            if (slotStack.getCount() == itemStack.getCount())
            {
                return ItemStack.EMPTY;
            }
            slot.onTake(player, slotStack);
        }
        return itemStack;
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