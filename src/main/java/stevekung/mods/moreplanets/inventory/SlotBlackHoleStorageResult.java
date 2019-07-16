package stevekung.mods.moreplanets.inventory;

import micdoodle8.mods.galacticraft.core.util.GCCoreUtil;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import stevekung.mods.moreplanets.init.MPBlocks;
import stevekung.mods.moreplanets.network.PacketSimpleMP;
import stevekung.mods.moreplanets.network.PacketSimpleMP.EnumSimplePacketMP;

public class SlotBlackHoleStorageResult extends Slot
{
    private final IInventory inventory;
    private final EntityPlayer player;

    public SlotBlackHoleStorageResult(EntityPlayer player, IInventory invCraft, IInventory result, int index, int x, int y)
    {
        super(result, index, x, y);
        this.player = player;
        this.inventory = invCraft;
    }

    @Override
    public boolean isItemValid(ItemStack itemStack)
    {
        return false;
    }

    @Override
    public ItemStack onTake(EntityPlayer player, ItemStack itemStack)
    {
        for (int i = 0; i < this.inventory.getSizeInventory(); ++i)
        {
            ItemStack slotStack = this.inventory.getStackInSlot(i);

            if (!slotStack.isEmpty())
            {
                this.inventory.decrStackSize(i, 1);

                if (slotStack.getItem().hasContainerItem(slotStack))
                {
                    ItemStack result = new ItemStack(slotStack.getItem().getContainerItem());

                    if (!this.player.inventory.addItemStackToInventory(result))
                    {
                        if (this.inventory.getStackInSlot(i).isEmpty())
                        {
                            this.inventory.setInventorySlotContents(i, result);
                        }
                        else
                        {
                            this.player.entityDropItem(result, 0.0F);
                        }
                    }
                }
            }
        }
        if (itemStack.getItem() == Item.getItemFromBlock(MPBlocks.BLACK_HOLE_STORAGE) && player instanceof EntityPlayerMP)
        {
            PacketSimpleMP.sendToAllAround(new PacketSimpleMP(EnumSimplePacketMP.C_PLAY_CREATED_BLACK_HOLE_SOUND, GCCoreUtil.getDimensionID(player.world)), player.world, GCCoreUtil.getDimensionID(player.world), player.getPosition(), 32);
        }
        return itemStack;
    }
}
