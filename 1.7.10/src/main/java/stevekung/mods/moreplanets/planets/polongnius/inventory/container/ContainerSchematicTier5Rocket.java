/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 *
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.planets.polongnius.inventory.container;

import micdoodle8.mods.galacticraft.core.inventory.SlotRocketBenchResult;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.InventoryCraftResult;
import net.minecraft.inventory.Slot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import stevekung.mods.moreplanets.core.inventory.InventorySchematicRocket;
import stevekung.mods.moreplanets.planets.polongnius.inventory.slot.SlotSchematicTier5Rocket;
import stevekung.mods.moreplanets.planets.polongnius.recipe.Tier5RocketRecipes;

public class ContainerSchematicTier5Rocket extends Container
{
    public InventorySchematicRocket craftMatrix = new InventorySchematicRocket(this);
    public IInventory craftResult = new InventoryCraftResult();
    private World worldObj;

    public ContainerSchematicTier5Rocket(InventoryPlayer par1InventoryPlayer, int x, int y, int z)
    {
        int change = 27;
        this.worldObj = par1InventoryPlayer.player.worldObj;
        this.addSlotToContainer(new SlotRocketBenchResult(par1InventoryPlayer.player, this.craftMatrix, this.craftResult, 0, 142, 18 + 69 + change));
        int var6;
        int var7;

        // Cone
        this.addSlotToContainer(new SlotSchematicTier5Rocket(this.craftMatrix, 1, 48, -8 + change, x, y, z, par1InventoryPlayer.player));

        // Body
        for (var6 = 0; var6 < 5; ++var6)
        {
            this.addSlotToContainer(new SlotSchematicTier5Rocket(this.craftMatrix, 2 + var6, 39, -6 + var6 * 18 + 16 + change, x, y, z, par1InventoryPlayer.player));
        }

        // Body Right
        for (var6 = 0; var6 < 5; ++var6)
        {
            this.addSlotToContainer(new SlotSchematicTier5Rocket(this.craftMatrix, 7 + var6, 57, -6 + var6 * 18 + 16 + change, x, y, z, par1InventoryPlayer.player));
        }

        // Left fins
        this.addSlotToContainer(new SlotSchematicTier5Rocket(this.craftMatrix, 12, 21, 64 + change, x, y, z, par1InventoryPlayer.player));
        this.addSlotToContainer(new SlotSchematicTier5Rocket(this.craftMatrix, 13, 21, 82 + change, x, y, z, par1InventoryPlayer.player));
        this.addSlotToContainer(new SlotSchematicTier5Rocket(this.craftMatrix, 14, 21, 100 + change, x, y, z, par1InventoryPlayer.player));

        // Engine
        this.addSlotToContainer(new SlotSchematicTier5Rocket(this.craftMatrix, 15, 48, 100 + change, x, y, z, par1InventoryPlayer.player));

        // Right fins
        this.addSlotToContainer(new SlotSchematicTier5Rocket(this.craftMatrix, 16, 75, 64 + change, x, y, z, par1InventoryPlayer.player));
        this.addSlotToContainer(new SlotSchematicTier5Rocket(this.craftMatrix, 17, 75, 82 + change, x, y, z, par1InventoryPlayer.player));
        this.addSlotToContainer(new SlotSchematicTier5Rocket(this.craftMatrix, 18, 75, 100 + change, x, y, z, par1InventoryPlayer.player));

        // Addons
        for (int var8 = 0; var8 < 3; var8++)
        {
            this.addSlotToContainer(new SlotSchematicTier5Rocket(this.craftMatrix, 19 + var8, 93 + var8 * 26, -15 + change, x, y, z, par1InventoryPlayer.player));
        }

        // Player inv:

        for (var6 = 0; var6 < 3; ++var6)
        {
            for (var7 = 0; var7 < 9; ++var7)
            {
                this.addSlotToContainer(new Slot(par1InventoryPlayer, var7 + var6 * 9 + 9, 8 + var7 * 18, 129 + var6 * 18 + change));
            }
        }

        for (var6 = 0; var6 < 9; ++var6)
        {
            this.addSlotToContainer(new Slot(par1InventoryPlayer, var6, 8 + var6 * 18, 18 + 169 + change));
        }
        this.onCraftMatrixChanged(this.craftMatrix);
    }

    @Override
    public void onContainerClosed(EntityPlayer player)
    {
        super.onContainerClosed(player);

        if (!this.worldObj.isRemote)
        {
            for (int var2 = 1; var2 < this.craftMatrix.getSizeInventory(); ++var2)
            {
                ItemStack slot = this.craftMatrix.getStackInSlotOnClosing(var2);

                if (slot != null)
                {
                    player.entityDropItem(slot, 0.0F);
                }
            }
        }
    }

    @Override
    public void onCraftMatrixChanged(IInventory par1IInventory)
    {
        this.craftResult.setInventorySlotContents(0, Tier5RocketRecipes.findMatchingTier5RocketRecipe(this.craftMatrix));
    }

    @Override
    public boolean canInteractWith(EntityPlayer player)
    {
        return true;
    }

    @Override
    public ItemStack transferStackInSlot(EntityPlayer player, int slotId)
    {
        ItemStack itemStack = null;
        Slot slot = (Slot) this.inventorySlots.get(slotId);

        if (slot != null && slot.getHasStack())
        {
            ItemStack stack = slot.getStack();
            itemStack = stack.copy();

            if (slotId <= 21)
            {
                if (!this.mergeItemStack(stack, 22, 58, false))
                {
                    return null;
                }
                slot.onSlotChange(stack, itemStack);
            }
            else
            {
                boolean valid = false;

                for (int i = 1; i < 19; i++)
                {
                    Slot testSlot = (Slot) this.inventorySlots.get(i);

                    if (!testSlot.getHasStack() && testSlot.isItemValid(itemStack))
                    {
                        valid = true;
                        break;
                    }
                }
                if (valid)
                {
                    if (!this.mergeOneItemTestValid(stack, 1, 19, false))
                    {
                        return null;
                    }
                }
                else
                {
                    if (itemStack.getItem() == Item.getItemFromBlock(Blocks.chest))
                    {
                        if (!this.mergeOneItemTestValid(stack, 19, 22, false))
                        {
                            return null;
                        }
                    }
                    else if (slotId >= 22 && slotId < 49)
                    {
                        if (!this.mergeItemStack(stack, 49, 58, false))
                        {
                            return null;
                        }
                    }
                    else if (slotId >= 49 && slotId < 58)
                    {
                        if (!this.mergeItemStack(stack, 22, 49, false))
                        {
                            return null;
                        }
                    }
                    else if (!this.mergeItemStack(stack, 22, 58, false))
                    {
                        return null;
                    }
                }
            }

            if (stack.stackSize == 0)
            {
                slot.putStack((ItemStack) null);
            }
            else
            {
                slot.onSlotChanged();
            }

            if (stack.stackSize == itemStack.stackSize)
            {
                return null;
            }
            slot.onPickupFromSlot(player, stack);
        }
        return itemStack;
    }

    protected boolean mergeOneItemTestValid(ItemStack itemStack, int par2, int par3, boolean par4)
    {
        boolean flag1 = false;

        if (itemStack.stackSize > 0)
        {
            Slot slot;
            ItemStack slotStack;

            for (int k = par2; k < par3; k++)
            {
                slot = (Slot) this.inventorySlots.get(k);
                slotStack = slot.getStack();

                if (slotStack == null && slot.isItemValid(itemStack))
                {
                    ItemStack stackOneItem = itemStack.copy();
                    stackOneItem.stackSize = 1;
                    itemStack.stackSize--;
                    slot.putStack(stackOneItem);
                    slot.onSlotChanged();
                    flag1 = true;
                    break;
                }
            }
        }
        return flag1;
    }
}