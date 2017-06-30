package stevekung.mods.moreplanets.module.planets.nibiru.inventory;

import micdoodle8.mods.galacticraft.api.item.IItemElectric;
import micdoodle8.mods.galacticraft.core.energy.EnergyUtil;
import micdoodle8.mods.galacticraft.core.inventory.SlotSpecific;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import stevekung.mods.moreplanets.module.planets.nibiru.tileentity.TileEntityNuclearWasteGenerator;

public class ContainerNuclearWasteGenerator extends Container
{
    private TileEntityNuclearWasteGenerator tileEntity;

    public ContainerNuclearWasteGenerator(InventoryPlayer invPlayer, TileEntityNuclearWasteGenerator generator)
    {
        this.tileEntity = generator;
        this.addSlotToContainer(new SlotSpecific(generator, 0, 122, 20, IItemElectric.class));

        for (int i = 0; i < 3; ++i)
        {
            for (int j = 0; j < 9; ++j)
            {
                this.addSlotToContainer(new Slot(invPlayer, j + i * 9 + 9, 8 + j * 18, 51 + 68 + i * 18));
            }
        }
        for (int i = 0; i < 9; ++i)
        {
            this.addSlotToContainer(new Slot(invPlayer, i, 8 + i * 18, 61 + 116));
        }
    }

    @Override
    public boolean canInteractWith(EntityPlayer player)
    {
        return this.tileEntity.isUseableByPlayer(player);
    }

    @Override
    public ItemStack transferStackInSlot(EntityPlayer player, int slot)
    {
        ItemStack itemStack = null;
        Slot invSlot = this.inventorySlots.get(slot);
        int slotSize = this.inventorySlots.size();

        if (invSlot != null && invSlot.getHasStack())
        {
            ItemStack stack = invSlot.getStack();
            itemStack = stack.copy();

            if (slot == 0)
            {
                if (!this.mergeItemStack(stack, slotSize - 36, slotSize, true))
                {
                    return null;
                }
            }
            else
            {
                if (EnergyUtil.isElectricItem(stack.getItem()))
                {
                    if (!this.mergeItemStack(stack, 0, 1, false))
                    {
                        return null;
                    }
                }
                else
                {
                    if (slot < slotSize - 9)
                    {
                        if (!this.mergeItemStack(stack, slotSize - 9, slotSize, false))
                        {
                            return null;
                        }
                    }
                    else if (!this.mergeItemStack(stack, slotSize - 36, slotSize - 9, false))
                    {
                        return null;
                    }
                }
            }

            if (stack.stackSize == 0)
            {
                invSlot.putStack((ItemStack) null);
            }
            else
            {
                invSlot.onSlotChanged();
            }

            if (stack.stackSize == itemStack.stackSize)
            {
                return null;
            }
            invSlot.onPickupFromSlot(player, stack);
        }
        return itemStack;
    }
}