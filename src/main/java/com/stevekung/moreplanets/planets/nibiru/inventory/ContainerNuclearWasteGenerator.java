package com.stevekung.moreplanets.planets.nibiru.inventory;

import com.stevekung.moreplanets.planets.nibiru.tileentity.TileEntityNuclearWasteGenerator;

import micdoodle8.mods.galacticraft.api.item.IItemElectric;
import micdoodle8.mods.galacticraft.core.energy.EnergyUtil;
import micdoodle8.mods.galacticraft.core.inventory.SlotSpecific;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class ContainerNuclearWasteGenerator extends Container
{
    private final TileEntityNuclearWasteGenerator tile;

    public ContainerNuclearWasteGenerator(InventoryPlayer invPlayer, TileEntityNuclearWasteGenerator tile)
    {
        this.tile = tile;
        this.addSlotToContainer(new SlotSpecific(tile, 0, 122, 20, IItemElectric.class));

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
        return this.tile.isUsableByPlayer(player);
    }

    @Override
    public ItemStack transferStackInSlot(EntityPlayer player, int index)
    {
        ItemStack itemStack = ItemStack.EMPTY;
        Slot slot = this.inventorySlots.get(index);
        int size = this.inventorySlots.size();

        if (slot != null && slot.getHasStack())
        {
            ItemStack stack = slot.getStack();
            itemStack = stack.copy();

            if (index == 0)
            {
                if (!this.mergeItemStack(stack, size - 36, size, true))
                {
                    return ItemStack.EMPTY;
                }
            }
            else if (EnergyUtil.isElectricItem(stack.getItem()))
            {
                if (!this.mergeItemStack(stack, 0, 1, false))
                {
                    return ItemStack.EMPTY;
                }
            }
            else if (index < size - 9)
            {
                if (!this.mergeItemStack(stack, size - 9, size, false))
                {
                    return ItemStack.EMPTY;
                }
            }
            else if (!this.mergeItemStack(stack, size - 36, size - 9, false))
            {
                return ItemStack.EMPTY;
            }

            if (stack.getCount() == 0)
            {
                slot.putStack(ItemStack.EMPTY);
            }
            else
            {
                slot.onSlotChanged();
            }

            if (stack.getCount() == itemStack.getCount())
            {
                return ItemStack.EMPTY;
            }

            slot.onTake(player, stack);
        }
        return itemStack;
    }
}