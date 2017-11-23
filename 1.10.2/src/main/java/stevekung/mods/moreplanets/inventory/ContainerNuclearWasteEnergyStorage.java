package stevekung.mods.moreplanets.inventory;

import micdoodle8.mods.galacticraft.api.item.IItemElectric;
import micdoodle8.mods.galacticraft.core.energy.EnergyUtil;
import micdoodle8.mods.galacticraft.core.inventory.SlotSpecific;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import stevekung.mods.moreplanets.tileentity.TileEntityNuclearWasteStorageCluster;

public class ContainerNuclearWasteEnergyStorage extends Container
{
    private TileEntityNuclearWasteStorageCluster tileEntity;

    public ContainerNuclearWasteEnergyStorage(InventoryPlayer invPlayer, TileEntityNuclearWasteStorageCluster batteryBox)
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
        this.tileEntity.playersUsing.add(invPlayer.player);
    }

    @Override
    public void onContainerClosed(EntityPlayer player)
    {
        super.onContainerClosed(player);
        this.tileEntity.playersUsing.remove(player);
    }

    @Override
    public boolean canInteractWith(EntityPlayer player)
    {
        return this.tileEntity.isUseableByPlayer(player);
    }

    @Override
    public ItemStack transferStackInSlot(EntityPlayer player, int slotID)
    {
        ItemStack returnStack = null;
        Slot slot = this.inventorySlots.get(slotID);
        int invSize = this.inventorySlots.size();

        if (slot != null && slot.getHasStack())
        {
            ItemStack itemStack = slot.getStack();
            returnStack = itemStack.copy();

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
                                return null;
                            }
                        }
                    }
                    else
                    {
                        if (!this.mergeItemStack(itemStack, 0, 4, false))
                        {
                            return null;
                        }
                    }
                }
                else
                {
                    if (slotID < invSize - 9)
                    {
                        if (!this.mergeItemStack(itemStack, invSize - 9, invSize, false))
                        {
                            return null;
                        }
                    }
                    else if (!this.mergeItemStack(itemStack, invSize - 36, invSize - 9, false))
                    {
                        return null;
                    }
                }
            }
            else if (!this.mergeItemStack(itemStack, 4, 38, false))
            {
                return null;
            }

            if (itemStack.stackSize == 0)
            {
                slot.putStack((ItemStack) null);
            }
            else
            {
                slot.onSlotChanged();
            }

            if (itemStack.stackSize == returnStack.stackSize)
            {
                return null;
            }
            slot.onPickupFromSlot(player, itemStack);
        }
        return returnStack;
    }
}