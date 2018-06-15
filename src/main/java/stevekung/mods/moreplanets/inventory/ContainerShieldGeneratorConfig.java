package stevekung.mods.moreplanets.inventory;

import micdoodle8.mods.galacticraft.api.item.IItemElectric;
import micdoodle8.mods.galacticraft.core.energy.EnergyUtil;
import micdoodle8.mods.galacticraft.core.inventory.SlotSpecific;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import stevekung.mods.moreplanets.init.MPItems;
import stevekung.mods.moreplanets.tileentity.TileEntityShieldGenerator;

public class ContainerShieldGeneratorConfig extends Container
{
    private TileEntityShieldGenerator tile;

    public ContainerShieldGeneratorConfig(InventoryPlayer inventory, TileEntityShieldGenerator tile)
    {
        this.tile = tile;
        this.addSlotToContainer(new SlotSpecific(tile, 0, 152, 78, IItemElectric.class));
        this.addSlotToContainer(new SlotShieldGeneratorUpgrade(tile, 1, 152, 24, "damage"));
        this.addSlotToContainer(new SlotShieldGeneratorUpgrade(tile, 2, 152, 42, "size"));
        this.addSlotToContainer(new SlotShieldGeneratorUpgrade(tile, 3, 152, 60, "capacity"));

        int i;
        int j;

        for (i = 0; i < 3; ++i)
        {
            for (j = 0; j < 9; ++j)
            {
                this.addSlotToContainer(new Slot(inventory, j + i * 9 + 9, 8 + j * 18, 136 + i * 18));
            }
        }
        for (i = 0; i < 9; ++i)
        {
            this.addSlotToContainer(new Slot(inventory, i, 8 + i * 18, 194));
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
        int invSize = this.inventorySlots.size();

        if (slot != null && slot.getHasStack())
        {
            ItemStack slotStack = slot.getStack();
            itemStack = slotStack.copy();

            if (index < invSize - 36)
            {
                if (!this.mergeItemStack(slotStack, invSize - 36, invSize, true))
                {
                    return ItemStack.EMPTY;
                }
            }
            else
            {
                if (EnergyUtil.isElectricItem(slotStack.getItem()))
                {
                    if (!this.mergeItemStack(slotStack, 0, 1, false))
                    {
                        return ItemStack.EMPTY;
                    }
                }
                else if (slotStack.getItem() == MPItems.SHIELD_DAMAGE_UPGRADE)
                {
                    if (!this.mergeItemStack(slotStack, 1, 2, false))
                    {
                        return ItemStack.EMPTY;
                    }
                }
                else if (slotStack.getItem() == MPItems.SHIELD_SIZE_UPGRADE)
                {
                    if (!this.mergeItemStack(slotStack, 2, 3, false))
                    {
                        return ItemStack.EMPTY;
                    }
                }
                else if (slotStack.getItem() == MPItems.SHIELD_CAPACITY_UPGRADE)
                {
                    if (!this.mergeItemStack(slotStack, 3, 4, false))
                    {
                        return ItemStack.EMPTY;
                    }
                }
                else if (index < invSize - 9)
                {
                    if (!this.mergeItemStack(slotStack, invSize - 9, invSize, false))
                    {
                        return ItemStack.EMPTY;
                    }
                }
                else if (!this.mergeItemStack(slotStack, invSize - 36, invSize - 9, false))
                {
                    return ItemStack.EMPTY;
                }
            }

            if (slotStack.isEmpty())
            {
                slot.putStack(ItemStack.EMPTY);
            }
            else
            {
                slot.onSlotChanged();
            }

            if (slotStack.getCount() == itemStack.getCount())
            {
                return ItemStack.EMPTY;
            }
            slot.onTake(player, slotStack);
        }
        return itemStack;
    }
}