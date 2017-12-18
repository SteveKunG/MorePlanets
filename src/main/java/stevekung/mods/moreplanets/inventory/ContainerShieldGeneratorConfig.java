package stevekung.mods.moreplanets.inventory;

import micdoodle8.mods.galacticraft.api.item.IItemElectric;
import micdoodle8.mods.galacticraft.core.energy.EnergyUtil;
import micdoodle8.mods.galacticraft.core.inventory.SlotSpecific;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.init.Items;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
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
        return this.tile.isUseableByPlayer(player);
    }

    @Override
    public ItemStack transferStackInSlot(EntityPlayer player, int index)
    {
        ItemStack itemStack = null;
        Slot slot = this.inventorySlots.get(index);
        int invSize = this.inventorySlots.size();

        if (slot != null && slot.getHasStack())
        {
            ItemStack slotStack = slot.getStack();
            itemStack = slotStack.copy();

            if (index == 0 || index == 1 || index == 2 || index == 3)
            {
                if (!this.mergeItemStack(slotStack, invSize - 36, invSize, true))
                {
                    return null;
                }
            }
            else
            {
                if (EnergyUtil.isElectricItem(slotStack.getItem()))
                {
                    if (!this.mergeItemStack(slotStack, 0, 1, false))
                    {
                        return null;
                    }
                }
                else if (slotStack.getItem() == Items.REDSTONE)
                {
                    if (!this.mergeItemValidSize(slotStack, 1, 2))
                    {
                        return null;
                    }
                }
                else if (slotStack.getItem() == Items.ENDER_PEARL)
                {
                    if (!this.mergeItemValidSize(slotStack, 2, 3))
                    {
                        return null;
                    }
                }
                else if (slotStack.getItem() == Items.DIAMOND)
                {
                    if (!this.mergeItemValidSize(slotStack, 3, 4))
                    {
                        return null;
                    }
                }
                else
                {
                    if (index < invSize - 9)
                    {
                        if (!this.mergeItemStack(slotStack, invSize - 9, invSize, false))
                        {
                            return null;
                        }
                    }
                    else if (!this.mergeItemStack(slotStack, invSize - 36, invSize - 9, false))
                    {
                        return null;
                    }
                }
            }
            if (slotStack.stackSize == 0)
            {
                slot.putStack((ItemStack) null);
            }
            else
            {
                slot.onSlotChanged();
            }
            if (slotStack.stackSize == itemStack.stackSize)
            {
                return null;
            }
            slot.onPickupFromSlot(player, slotStack);
        }
        return itemStack;
    }

    private boolean mergeItemValidSize(ItemStack itemStack, int startIndex, int endIndex)
    {
        boolean flag = false;

        if (itemStack.stackSize > 0)
        {
            Slot slot;
            ItemStack slotStack;

            for (int i = startIndex; i < endIndex; i++)
            {
                slot = this.inventorySlots.get(i);
                slotStack = slot.getStack();

                if (slotStack == null && slot.isItemValid(itemStack))
                {
                    ItemStack stackOneItem = itemStack.copy();
                    stackOneItem.stackSize = slot.getSlotStackLimit();
                    itemStack.stackSize -= slot.getSlotStackLimit();
                    slot.putStack(stackOneItem);
                    slot.onSlotChanged();
                    flag = true;
                    break;
                }
            }
        }
        return flag;
    }
}