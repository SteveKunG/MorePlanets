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
                this.addSlotToContainer(new Slot(tile.compressingCraftMatrix, y + x * 3, 19 + y * 18, 18 + x * 18));
            }
        }

        // Battery Slot
        this.addSlotToContainer(new SlotSpecific(tile, 0, 55, 75, IItemElectric.class));

        // Plate result
        this.addSlotToContainer(new SlotFurnaceOutput(invPlayer.player, tile, 1, 143, 38));

        int invSlot;

        for (invSlot = 0; invSlot < 3; ++invSlot)
        {
            for (int slotStack = 0; slotStack < 9; ++slotStack)
            {
                this.addSlotToContainer(new Slot(invPlayer, slotStack + invSlot * 9 + 9, 8 + slotStack * 18, 117 + invSlot * 18));
            }
        }

        for (invSlot = 0; invSlot < 9; ++invSlot)
        {
            this.addSlotToContainer(new Slot(invPlayer, invSlot, 8 + invSlot * 18, 175));
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
        ItemStack itemStack = null;
        Slot invSlot = this.inventorySlots.get(slot);

        if (invSlot != null && invSlot.getHasStack())
        {
            ItemStack slotStack = invSlot.getStack();
            itemStack = slotStack.copy();

            if (slot <= 11)
            {
                if (!this.mergeItemStack(slotStack, 12, 47, true))
                {
                    return null;
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
                        return null;
                    }
                }
                else if (slot < 39)
                {
                    if (!this.mergeItemStack(slotStack, 0, 9, false) && !this.mergeItemStack(slotStack, 39, 48, false))
                    {
                        return null;
                    }
                }
                else if (!this.mergeItemStack(slotStack, 0, 9, false) && !this.mergeItemStack(slotStack, 12, 39, false))
                {
                    return null;
                }
            }
            if (slotStack.getCount() == 0)
            {
                invSlot.putStack((ItemStack) null);
            }
            else
            {
                invSlot.onSlotChanged();
            }

            if (slotStack.getCount() == itemStack.getCount())
            {
                return null;
            }
            invSlot.onTake(player, slotStack);
        }
        return itemStack;
    }
}