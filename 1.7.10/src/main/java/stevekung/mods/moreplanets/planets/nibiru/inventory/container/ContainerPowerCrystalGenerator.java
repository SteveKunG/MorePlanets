/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 *
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.planets.nibiru.inventory.container;

import micdoodle8.mods.galacticraft.core.inventory.SlotSpecific;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import stevekung.mods.moreplanets.core.items.IPowerCrystal;
import stevekung.mods.moreplanets.planets.nibiru.tileentities.TileEntityPowerCrystalGenerator;

public class ContainerPowerCrystalGenerator extends Container
{
    private TileEntityPowerCrystalGenerator tileEntity;

    public ContainerPowerCrystalGenerator(InventoryPlayer par1InventoryPlayer, TileEntityPowerCrystalGenerator tileEntity)
    {
        this.tileEntity = tileEntity;
        this.addSlotToContainer(new SlotSpecific(tileEntity, 0, 26, 34, IPowerCrystal.class));
        int var3;

        for (var3 = 0; var3 < 3; ++var3)
        {
            for (int var4 = 0; var4 < 9; ++var4)
            {
                this.addSlotToContainer(new Slot(par1InventoryPlayer, var4 + var3 * 9 + 9, 8 + var4 * 18, 84 + var3 * 18));
            }
        }

        for (var3 = 0; var3 < 9; ++var3)
        {
            this.addSlotToContainer(new Slot(par1InventoryPlayer, var3, 8 + var3 * 18, 142));
        }
    }

    @Override
    public void onContainerClosed(EntityPlayer entityplayer)
    {
        super.onContainerClosed(entityplayer);
    }

    @Override
    public boolean canInteractWith(EntityPlayer par1EntityPlayer)
    {
        return this.tileEntity.isUseableByPlayer(par1EntityPlayer);
    }

    @Override
    public ItemStack transferStackInSlot(EntityPlayer player, int slot)
    {
        ItemStack itemStack = null;
        Slot invSlot = (Slot) this.inventorySlots.get(slot);

        if (invSlot != null && invSlot.getHasStack())
        {
            ItemStack itemStack1 = invSlot.getStack();
            itemStack = itemStack1.copy();

            if (slot != 0)
            {
                if (itemStack1.getItem() instanceof IPowerCrystal && ((IPowerCrystal)itemStack1.getItem()).isPowerCrystal(itemStack1.getItemDamage()))
                {
                    boolean isCrystal = false;

                    if (TileEntityPowerCrystalGenerator.isCrystal(itemStack1, isCrystal))
                    {
                        if (!this.mergeItemStack(itemStack1, 0, 1, false))
                        {
                            return null;
                        }
                    }
                }
                else if (slot >= 28)
                {
                    if (!this.mergeItemStack(itemStack1, 1, 28, false))
                    {
                        return null;
                    }
                }
                else if (!this.mergeItemStack(itemStack1, 28, 37, false))
                {
                    return null;
                }
            }
            else if (!this.mergeItemStack(itemStack1, 1, 37, false))
            {
                return null;
            }
            if (itemStack1.stackSize == 0)
            {
                invSlot.putStack((ItemStack) null);
            }
            else
            {
                invSlot.onSlotChanged();
            }
            if (itemStack1.stackSize == itemStack.stackSize)
            {
                return null;
            }
            invSlot.onPickupFromSlot(player, itemStack1);
        }
        return itemStack;
    }
}