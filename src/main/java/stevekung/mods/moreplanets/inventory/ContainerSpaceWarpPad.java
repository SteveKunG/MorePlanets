package stevekung.mods.moreplanets.inventory;

import micdoodle8.mods.galacticraft.api.item.IItemElectric;
import micdoodle8.mods.galacticraft.core.energy.EnergyUtil;
import micdoodle8.mods.galacticraft.core.energy.tile.TileBaseElectricBlock;
import micdoodle8.mods.galacticraft.core.inventory.SlotSpecific;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import stevekung.mods.moreplanets.init.MPItems;
import stevekung.mods.moreplanets.tileentity.TileEntitySpaceWarpPadFull;

public class ContainerSpaceWarpPad extends Container
{
    private TileBaseElectricBlock tileEntity;

    public ContainerSpaceWarpPad(InventoryPlayer invPlayer, TileEntitySpaceWarpPadFull tile)
    {
        this.tileEntity = tile;
        this.addSlotToContainer(new SlotSpecific(tile, 0, 22, 72, IItemElectric.class));
        this.addSlotToContainer(new SlotSpaceWarpPad(tile, 1, 22, 54));

        int i;
        int j;

        for (i = 0; i < 3; ++i)
        {
            for (j = 0; j < 9; ++j)
            {
                this.addSlotToContainer(new Slot(invPlayer, j + i * 9 + 9, 8 + j * 18, 112 + i * 18));
            }
        }

        for (i = 0; i < 9; ++i)
        {
            this.addSlotToContainer(new Slot(invPlayer, i, 8 + i * 18, 170));
        }
    }

    @Override
    public boolean canInteractWith(EntityPlayer player)
    {
        return this.tileEntity.isUsableByPlayer(player);
    }

    @Override
    public ItemStack transferStackInSlot(EntityPlayer player, int slot)
    {
        ItemStack itemStack = ItemStack.EMPTY;
        Slot invSlot = this.inventorySlots.get(slot);
        int slotSize = this.inventorySlots.size();

        if (invSlot != null && invSlot.getHasStack())
        {
            ItemStack stack = invSlot.getStack();
            itemStack = stack.copy();

            if (slot == 0 || slot == 1)
            {
                if (!this.mergeItemStack(stack, slotSize - 36, slotSize, true))
                {
                    return ItemStack.EMPTY;
                }
            }
            else
            {
                if (EnergyUtil.isElectricItem(stack.getItem()))
                {
                    if (!this.mergeItemStack(stack, 0, 1, false))
                    {
                        return ItemStack.EMPTY;
                    }
                }
                else if (stack.getItem() == MPItems.SPACE_WARPER_CORE)
                {
                    if (!this.mergeItemStack(stack, 1, 2, false))
                    {
                        return ItemStack.EMPTY;
                    }
                }
                else
                {
                    if (slot < slotSize - 9)
                    {
                        if (!this.mergeItemStack(stack, slotSize - 9, slotSize, false))
                        {
                            return ItemStack.EMPTY;
                        }
                    }
                    else if (!this.mergeItemStack(stack, slotSize - 36, slotSize - 9, false))
                    {
                        return ItemStack.EMPTY;
                    }
                }
            }

            if (stack.getCount() == 0)
            {
                invSlot.putStack(ItemStack.EMPTY);
            }
            else
            {
                invSlot.onSlotChanged();
            }

            if (stack.getCount() == itemStack.getCount())
            {
                return ItemStack.EMPTY;
            }
            invSlot.onTake(player, stack);
        }
        return itemStack;
    }
}