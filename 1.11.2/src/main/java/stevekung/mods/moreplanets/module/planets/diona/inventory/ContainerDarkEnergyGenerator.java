package stevekung.mods.moreplanets.module.planets.diona.inventory;

import micdoodle8.mods.galacticraft.api.item.IItemElectric;
import micdoodle8.mods.galacticraft.core.energy.EnergyUtil;
import micdoodle8.mods.galacticraft.core.inventory.SlotSpecific;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import stevekung.mods.moreplanets.module.planets.diona.tileentity.TileEntityDarkEnergyGenerator;

public class ContainerDarkEnergyGenerator extends Container
{
    private TileEntityDarkEnergyGenerator tileEntity;

    public ContainerDarkEnergyGenerator(InventoryPlayer invPlayer, TileEntityDarkEnergyGenerator generator)
    {
        this.tileEntity = generator;
        this.addSlotToContainer(new SlotSpecific(generator, 0, 23, 21, IItemElectric.class));

        // Player inv:
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
        return this.tileEntity.isUsableByPlayer(player);
    }

    @Override
    public ItemStack transferStackInSlot(EntityPlayer player, int index)
    {
        ItemStack itemStack = ItemStack.EMPTY;
        Slot slot = this.inventorySlots.get(index);
        int invSize = this.inventorySlots.size();

        if (slot != null && slot.getHasStack())
        {
            ItemStack stack = slot.getStack();
            itemStack = stack.copy();

            if (index == 0)
            {
                if (!this.mergeItemStack(stack, invSize - 36, invSize, true))
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
                else
                {
                    if (index < invSize - 9)
                    {
                        if (!this.mergeItemStack(stack, invSize - 9, invSize, false))
                        {
                            return ItemStack.EMPTY;
                        }
                    }
                    else if (!this.mergeItemStack(stack, invSize - 36, invSize - 9, false))
                    {
                        return ItemStack.EMPTY;
                    }
                }
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