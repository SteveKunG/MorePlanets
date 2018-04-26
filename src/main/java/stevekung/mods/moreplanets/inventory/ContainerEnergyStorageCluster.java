package stevekung.mods.moreplanets.inventory;

import micdoodle8.mods.galacticraft.api.item.IItemElectric;
import micdoodle8.mods.galacticraft.core.energy.EnergyUtil;
import micdoodle8.mods.galacticraft.core.inventory.SlotSpecific;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import stevekung.mods.moreplanets.util.tileentity.TileEntityEnergyStorageMP;

public class ContainerEnergyStorageCluster extends Container
{
    private TileEntityEnergyStorageMP tileEntity;

    public ContainerEnergyStorageCluster(InventoryPlayer invPlayer, TileEntityEnergyStorageMP batteryBox)
    {
        this.tileEntity = batteryBox;
        // Top slot for battery output
        this.addSlotToContainer(new SlotSpecific(batteryBox, 0, 15, 24, IItemElectric.class));
        this.addSlotToContainer(new SlotSpecific(batteryBox, 1, 33, 24, IItemElectric.class));
        // Bottom slot for batter input
        this.addSlotToContainer(new SlotSpecific(batteryBox, 2, 15, 48, IItemElectric.class));
        this.addSlotToContainer(new SlotSpecific(batteryBox, 3, 33, 48, IItemElectric.class));
        int i;

        for (i = 0; i < 3; ++i)
        {
            for (int j = 0; j < 9; ++j)
            {
                this.addSlotToContainer(new Slot(invPlayer, j + i * 9 + 9, 8 + j * 18, 89 + i * 18));
            }
        }
        for (i = 0; i < 9; ++i)
        {
            this.addSlotToContainer(new Slot(invPlayer, i, 8 + i * 18, 147));
        }
    }

    @Override
    public boolean canInteractWith(EntityPlayer player)
    {
        return this.tileEntity.isUsableByPlayer(player);
    }

    @Override
    public ItemStack transferStackInSlot(EntityPlayer player, int slotID)
    {
        ItemStack returnStack = ItemStack.EMPTY;
        Slot slot = this.inventorySlots.get(slotID);
        int invSize = this.inventorySlots.size();

        if (slot != null && slot.getHasStack())
        {
            ItemStack itemStack = slot.getStack();
            returnStack = itemStack.copy();
            boolean movedToMachineSlot = false;

            if (slotID != 0 && slotID != 1 && slotID != 2 && slotID != 3)
            {
                if (EnergyUtil.isElectricItem(itemStack.getItem()))
                {
                    if (EnergyUtil.isChargedElectricItem(itemStack))
                    {
                        if (!this.mergeItemStack(itemStack, 2, 4, false))
                        {
                            if (EnergyUtil.isFillableElectricItem(itemStack) && !this.mergeItemStack(itemStack, 0, 1, false))
                            {
                                return ItemStack.EMPTY;
                            }
                        }
                        movedToMachineSlot = true;
                    }
                    else
                    {
                        if (!this.mergeItemStack(itemStack, 0, 4, false))
                        {
                            return ItemStack.EMPTY;
                        }
                        movedToMachineSlot = true;
                    }
                }
                else
                {
                    if (slotID < invSize - 9)
                    {
                        if (!this.mergeItemStack(itemStack, invSize - 9, invSize, false))
                        {
                            return ItemStack.EMPTY;
                        }
                    }
                    else if (!this.mergeItemStack(itemStack, invSize - 36, invSize - 9, false))
                    {
                        return ItemStack.EMPTY;
                    }
                }
            }
            else if (!this.mergeItemStack(itemStack, 4, 38, false))
            {
                return ItemStack.EMPTY;
            }

            if (itemStack.getCount() == 0)
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

            if (itemStack.getCount() == returnStack.getCount())
            {
                return ItemStack.EMPTY;
            }
            slot.onTake(player, itemStack);
        }
        return returnStack;
    }
}