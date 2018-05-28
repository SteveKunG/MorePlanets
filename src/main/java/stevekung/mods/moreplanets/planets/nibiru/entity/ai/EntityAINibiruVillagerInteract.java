package stevekung.mods.moreplanets.planets.nibiru.entity.ai;

import net.minecraft.entity.ai.EntityAIWatchClosest2;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.init.Items;
import net.minecraft.inventory.InventoryBasic;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.MathHelper;
import stevekung.mods.moreplanets.init.MPItems;
import stevekung.mods.moreplanets.planets.nibiru.entity.EntityNibiruVillager;

public class EntityAINibiruVillagerInteract extends EntityAIWatchClosest2
{
    private int interactionDelay;
    private final EntityNibiruVillager entity;

    public EntityAINibiruVillagerInteract(EntityNibiruVillager entity)
    {
        super(entity, EntityNibiruVillager.class, 3.0F, 0.02F);
        this.entity = entity;
    }

    @Override
    public void startExecuting()
    {
        super.startExecuting();

        if (this.entity.canAbondonItems() && this.closestEntity instanceof EntityNibiruVillager && ((EntityNibiruVillager)this.closestEntity).wantsMoreFood())
        {
            this.interactionDelay = 10;
        }
        else
        {
            this.interactionDelay = 0;
        }
    }

    @Override
    public void updateTask()
    {
        super.updateTask();

        if (this.interactionDelay > 0)
        {
            --this.interactionDelay;

            if (this.interactionDelay == 0)
            {
                InventoryBasic inv = this.entity.getVillagerInventory();

                for (int i = 0; i < inv.getSizeInventory(); ++i)
                {
                    ItemStack itemStack = inv.getStackInSlot(i);
                    ItemStack itemStack1 = ItemStack.EMPTY;

                    if (!itemStack.isEmpty())
                    {
                        Item item = itemStack.getItem();

                        if ((item == Items.BREAD || item == MPItems.TERRABERRY) && itemStack.getCount() > 3)
                        {
                            int l = itemStack.getCount() / 2;
                            itemStack.shrink(l);
                            itemStack1 = new ItemStack(item, l, itemStack.getMetadata());
                        }
                        else if (item == MPItems.INFECTED_WHEAT && itemStack.getCount() > 5)
                        {
                            int j = itemStack.getCount() / 2 / 3 * 3;
                            int k = j / 3;
                            itemStack.shrink(j);
                            itemStack1 = new ItemStack(Items.BREAD, k, 0);
                        }

                        if (itemStack.isEmpty())
                        {
                            inv.setInventorySlotContents(i, ItemStack.EMPTY);
                        }
                    }

                    if (!itemStack1.isEmpty())
                    {
                        double d0 = this.entity.posY - 0.30000001192092896D + this.entity.getEyeHeight();
                        EntityItem item = new EntityItem(this.entity.world, this.entity.posX, d0, this.entity.posZ, itemStack1);
                        float f1 = this.entity.rotationYawHead;
                        float f2 = this.entity.rotationPitch;
                        item.motionX = -MathHelper.sin(f1 * 0.017453292F) * MathHelper.cos(f2 * 0.017453292F) * 0.3F;
                        item.motionZ = MathHelper.cos(f1 * 0.017453292F) * MathHelper.cos(f2 * 0.017453292F) * 0.3F;
                        item.motionY = -MathHelper.sin(f2 * 0.017453292F) * 0.3F + 0.1F;
                        item.setDefaultPickupDelay();
                        this.entity.world.spawnEntity(item);
                        break;
                    }
                }
            }
        }
    }
}