/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 *
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.planets.fronos.inventory.container;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.ICrafting;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import stevekung.mods.moreplanets.core.recipe.CandyExtractorRecipes;
import stevekung.mods.moreplanets.planets.fronos.inventory.slot.SlotCandyExtractor;
import stevekung.mods.moreplanets.planets.fronos.tileentities.TileEntityCandyExtractor;

public class ContainerCandyExtractor extends Container
{
    private final TileEntityCandyExtractor candyExtractor;
    private int lastCookTime;
    private int lastExtractTime;
    private int lastItemExtractTime;

    public ContainerCandyExtractor(InventoryPlayer par1InventoryPlayer, TileEntityCandyExtractor par2TileEntityFurnace)
    {
        this.candyExtractor = par2TileEntityFurnace;
        this.addSlotToContainer(new Slot(par2TileEntityFurnace, 0, 56, 17));
        this.addSlotToContainer(new Slot(par2TileEntityFurnace, 1, 56, 53));
        this.addSlotToContainer(new SlotCandyExtractor(par1InventoryPlayer.player, par2TileEntityFurnace, 2, 116, 35));
        int i;

        for (i = 0; i < 3; ++i)
        {
            for (int j = 0; j < 9; ++j)
            {
                this.addSlotToContainer(new Slot(par1InventoryPlayer, j + i * 9 + 9, 8 + j * 18, 84 + i * 18));
            }
        }

        for (i = 0; i < 9; ++i)
        {
            this.addSlotToContainer(new Slot(par1InventoryPlayer, i, 8 + i * 18, 142));
        }
    }

    @Override
    public void addCraftingToCrafters(ICrafting par1ICrafting)
    {
        super.addCraftingToCrafters(par1ICrafting);
        par1ICrafting.sendProgressBarUpdate(this, 0, this.candyExtractor.candyCookTime);
        par1ICrafting.sendProgressBarUpdate(this, 1, this.candyExtractor.extractorTime);
        par1ICrafting.sendProgressBarUpdate(this, 2, this.candyExtractor.currentItemExtractTime);
    }

    @Override
    public void detectAndSendChanges()
    {
        super.detectAndSendChanges();

        for (int i = 0; i < this.crafters.size(); ++i)
        {
            final ICrafting icrafting = (ICrafting)this.crafters.get(i);

            if (this.lastCookTime != this.candyExtractor.candyCookTime)
            {
                icrafting.sendProgressBarUpdate(this, 0, this.candyExtractor.candyCookTime);
            }

            if (this.lastExtractTime != this.candyExtractor.extractorTime)
            {
                icrafting.sendProgressBarUpdate(this, 1, this.candyExtractor.extractorTime);
            }

            if (this.lastItemExtractTime != this.candyExtractor.currentItemExtractTime)
            {
                icrafting.sendProgressBarUpdate(this, 2, this.candyExtractor.currentItemExtractTime);
            }
        }
        this.lastCookTime = this.candyExtractor.candyCookTime;
        this.lastExtractTime = this.candyExtractor.extractorTime;
        this.lastItemExtractTime = this.candyExtractor.currentItemExtractTime;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void updateProgressBar(int par1, int par2)
    {
        if (par1 == 0)
        {
            this.candyExtractor.candyCookTime = par2;
        }

        if (par1 == 1)
        {
            this.candyExtractor.extractorTime = par2;
        }

        if (par1 == 2)
        {
            this.candyExtractor.currentItemExtractTime = par2;
        }
    }

    @Override
    public boolean canInteractWith(EntityPlayer par1EntityPlayer)
    {
        return this.candyExtractor.isUseableByPlayer(par1EntityPlayer);
    }

    @Override
    public ItemStack transferStackInSlot(EntityPlayer par1EntityPlayer, int par2)
    {
        ItemStack itemstack = null;
        final Slot slot = (Slot)this.inventorySlots.get(par2);

        if (slot != null && slot.getHasStack())
        {
            final ItemStack itemstack1 = slot.getStack();
            itemstack = itemstack1.copy();

            if (par2 == 2)
            {
                if (!this.mergeItemStack(itemstack1, 3, 39, true))
                {
                    return null;
                }

                slot.onSlotChange(itemstack1, itemstack);
            }
            else if (par2 != 1 && par2 != 0)
            {
                if (CandyExtractorRecipes.extracting().getExtractingResult(itemstack1) != null)
                {
                    if (!this.mergeItemStack(itemstack1, 0, 1, false))
                    {
                        return null;
                    }
                }
                else if (TileEntityCandyExtractor.isItemFuel(itemstack1))
                {
                    if (!this.mergeItemStack(itemstack1, 1, 2, false))
                    {
                        return null;
                    }
                }
                else if (par2 >= 3 && par2 < 30)
                {
                    if (!this.mergeItemStack(itemstack1, 30, 39, false))
                    {
                        return null;
                    }
                }
                else if (par2 >= 30 && par2 < 39 && !this.mergeItemStack(itemstack1, 3, 30, false))
                {
                    return null;
                }
            }
            else if (!this.mergeItemStack(itemstack1, 3, 39, false))
            {
                return null;
            }

            if (itemstack1.stackSize == 0)
            {
                slot.putStack((ItemStack)null);
            }
            else
            {
                slot.onSlotChanged();
            }

            if (itemstack1.stackSize == itemstack.stackSize)
            {
                return null;
            }
            slot.onPickupFromSlot(par1EntityPlayer, itemstack1);
        }
        return itemstack;
    }
}