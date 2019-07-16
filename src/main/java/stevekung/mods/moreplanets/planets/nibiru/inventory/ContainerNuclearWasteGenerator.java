package stevekung.mods.moreplanets.planets.nibiru.inventory;

import micdoodle8.mods.galacticraft.api.item.IItemElectric;
import micdoodle8.mods.galacticraft.core.energy.EnergyUtil;
import micdoodle8.mods.galacticraft.core.inventory.SlotSpecific;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import stevekung.mods.moreplanets.planets.nibiru.tileentity.TileEntityNuclearWasteGenerator;

public class ContainerNuclearWasteGenerator extends Container
{
    private TileEntityNuclearWasteGenerator tile;

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
        Slot invSlot = this.inventorySlots.get(index);
        int slotSize = this.inventorySlots.size();

        if (invSlot != null && invSlot.getHasStack())
        {
            ItemStack stack = invSlot.getStack();
            itemStack = stack.copy();
            boolean movedToMachineSlot = false;

            if (index == 0)
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
                    movedToMachineSlot = true;
                }
                else
                {
                    if (index < slotSize - 9)
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
                    movedToMachineSlot = true;
                }
            }

            if (stack.getCount() == 0)
            {
                if (movedToMachineSlot && itemStack.getCount() > 1)
                {
                    ItemStack remainder = itemStack.copy();
                    remainder.shrink(1);
                    invSlot.putStack(remainder);
                }
                else
                {
                    invSlot.putStack(ItemStack.EMPTY);
                }
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