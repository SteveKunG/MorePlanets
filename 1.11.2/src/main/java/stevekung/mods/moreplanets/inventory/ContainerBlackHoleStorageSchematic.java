package stevekung.mods.moreplanets.inventory;

import micdoodle8.mods.galacticraft.core.GCItems;
import micdoodle8.mods.galacticraft.core.inventory.SlotRocketBenchResult;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.init.Items;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.InventoryCraftResult;
import net.minecraft.inventory.Slot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import stevekung.mods.moreplanets.module.planets.diona.items.DionaItems;
import stevekung.mods.moreplanets.recipe.BlackHoleStorageRecipes;

public class ContainerBlackHoleStorageSchematic extends Container
{
    public InventoryBlackHoleStorageSchematic craftMatrix = new InventoryBlackHoleStorageSchematic(this);
    public IInventory craftResult = new InventoryCraftResult();
    private World worldObj;

    public ContainerBlackHoleStorageSchematic(InventoryPlayer inventory, BlockPos pos)
    {
        this.worldObj = inventory.player.world;
        this.addSlotToContainer(new SlotRocketBenchResult(inventory.player, this.craftMatrix, this.craftResult, 0, 142, 18 + 69 + 27));
        int i;
        int j;
        int count = 10;

        // Black hole fragment
        for (i = 0; i < 3; ++i)
        {
            for (j = 0; j < 3; ++j)
            {
                this.addSlotToContainer(new SlotBlackHoleStorageSchematic(this.craftMatrix, j + i * 3 + 1, 34 + j * 18, 21 + i * 18, pos, inventory.player));
            }
        }

        // Rod
        for (i = 0; i < 3; ++i)
        {
            this.addSlotToContainer(new SlotBlackHoleStorageSchematic(this.craftMatrix, count++, 16 + i * 36, 85, pos, inventory.player));
        }

        // Plate
        for (i = 0; i < 5; ++i)
        {
            this.addSlotToContainer(new SlotBlackHoleStorageSchematic(this.craftMatrix, count++, 16 + i * 18, 103, pos, inventory.player));
        }
        for (i = 0; i < 5; ++i)
        {
            this.addSlotToContainer(new SlotBlackHoleStorageSchematic(this.craftMatrix, count++, 16 + i * 18, 121, pos, inventory.player));
        }

        // Player inventory
        for (i = 0; i < 3; ++i)
        {
            for (j = 0; j < 9; ++j)
            {
                this.addSlotToContainer(new Slot(inventory, j + i * 9 + 9, 8 + j * 18, 129 + i * 18 + 27));
            }
        }
        for (i = 0; i < 9; ++i)
        {
            this.addSlotToContainer(new Slot(inventory, i, 8 + i * 18, 18 + 169 + 27));
        }
        this.onCraftMatrixChanged(this.craftMatrix);
    }

    @Override
    public void onContainerClosed(EntityPlayer player)
    {
        super.onContainerClosed(player);

        if (!this.worldObj.isRemote)
        {
            for (int i = 0; i < this.craftMatrix.getSizeInventory(); ++i)
            {
                ItemStack itemStack = this.craftMatrix.removeStackFromSlot(i);

                if (!itemStack.isEmpty())
                {
                    player.entityDropItem(itemStack, 0.0F);
                }
            }
        }
    }

    @Override
    public void onCraftMatrixChanged(IInventory inventory)
    {
        this.craftResult.setInventorySlotContents(0, BlackHoleStorageRecipes.findMatchingBlackHoleStorageRecipe(this.craftMatrix));
    }

    @Override
    public boolean canInteractWith(EntityPlayer player)
    {
        return true;
    }

    @Override
    public ItemStack transferStackInSlot(EntityPlayer player, int slot)
    {
        ItemStack itemStack = ItemStack.EMPTY;
        Slot invSlot = this.inventorySlots.get(slot);
        int slotSize = this.inventorySlots.size();

        if (invSlot != null && invSlot.getHasStack())
        {
            ItemStack slotStack = invSlot.getStack();
            itemStack = slotStack.copy();

            if (slot < slotSize - 36)
            {
                if (!this.mergeItemStack(slotStack, slotSize - 36, slotSize, true))
                {
                    return ItemStack.EMPTY;
                }
                if (slot == 0)
                {
                    invSlot.onSlotChange(slotStack, itemStack);
                }
            }
            else
            {
                Item item = slotStack.getItem();
                int meta = slotStack.getItemDamage();

                if (item == DionaItems.DIONA_ITEM && meta == 6 || item == Items.ENDER_EYE || item == DionaItems.DIONA_ITEM && meta == 7 || item == GCItems.flagPole || item == DionaItems.TIER_4_ROCKET_PART && meta == 0)
                {
                    for (int i = 1; i < 23; i++)
                    {
                        if (this.inventorySlots.get(i).isItemValid(slotStack))
                        {
                            this.mergeOneItemTestValid(slotStack, i, i + 1);
                        }
                    }
                }
                else
                {
                    if (slot < slotSize - 9)
                    {
                        if (!this.mergeItemStack(slotStack, slotSize - 9, slotSize, false))
                        {
                            return ItemStack.EMPTY;
                        }
                    }
                    else
                    {
                        if (!this.mergeItemStack(slotStack, slotSize - 36, slotSize - 9, false))
                        {
                            return ItemStack.EMPTY;
                        }
                    }
                }
            }
            if (slotStack.getCount() == 0)
            {
                invSlot.putStack(ItemStack.EMPTY);
            }
            if (slotStack.getCount() == itemStack.getCount())
            {
                return ItemStack.EMPTY;
            }
            invSlot.onSlotChanged();
            invSlot.onTake(player, slotStack);
        }
        return itemStack;
    }

    protected boolean mergeOneItemTestValid(ItemStack itemStack, int startIndex, int endIndex)
    {
        boolean flag = false;

        if (itemStack.getCount() > 0)
        {
            Slot slot;
            ItemStack slotStack;

            for (int i = startIndex; i < endIndex; i++)
            {
                slot = this.inventorySlots.get(i);
                slotStack = slot.getStack();

                if (slotStack == ItemStack.EMPTY && slot.isItemValid(itemStack))
                {
                    ItemStack stackOneItem = itemStack.copy();
                    stackOneItem.setCount(1);
                    itemStack.shrink(1);
                    slot.putStack(stackOneItem);
                    slot.onSlotChanged();
                    flag = true;
                    break;
                }
            }
        }
        return flag;
    }
}