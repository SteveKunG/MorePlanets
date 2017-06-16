package stevekung.mods.moreplanets.items;

import micdoodle8.mods.galacticraft.core.energy.item.ItemElectricBase;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumAction;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import stevekung.mods.moreplanets.core.MorePlanetsCore;
import stevekung.mods.moreplanets.entity.projectile.EntityLaserBullet;
import stevekung.mods.moreplanets.entity.projectile.EntityLaserBullet.EnumLaserType;
import stevekung.mods.moreplanets.init.MPItems;
import stevekung.mods.moreplanets.util.helper.ClientRegisterHelper;
import stevekung.mods.moreplanets.util.items.EnumSortCategoryItem;
import stevekung.mods.moreplanets.util.items.ISortableItem;

public class ItemLaserGun extends ItemElectricBase implements ISortableItem
{
    public ItemLaserGun(String name)
    {
        super();
        this.setMaxStackSize(1);
        this.setUnlocalizedName(name);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public ModelResourceLocation getModel(ItemStack itemStack, EntityPlayer player, int useRemaining)
    {
        if (itemStack != null && itemStack.getItem() == this && player.getItemInUse() != null)
        {
            int i = itemStack.getMaxItemUseDuration() - player.getItemInUseCount();

            if (i > 12)
            {
                return ClientRegisterHelper.getModelResourceLocation("moreplanets:laser_gun_shoot");
            }
            if (i > 0)
            {
                return ClientRegisterHelper.getModelResourceLocation("moreplanets:laser_gun_charged");
            }
        }
        return null;
    }

    @Override
    public EnumAction getItemUseAction(ItemStack stack)
    {
        return EnumAction.BOW;
    }

    @Override
    public int getMaxItemUseDuration(ItemStack itemStack)
    {
        return 16;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public CreativeTabs getCreativeTab()
    {
        return MorePlanetsCore.ITEM_TAB;
    }

    @Override
    public ItemStack onItemUseFinish(ItemStack itemStack, World world, EntityPlayer player)
    {
        boolean flag = player.capabilities.isCreativeMode;
        ItemStack bulletStack = this.findBullet(player);

        if (this.getElectricityStored(itemStack) > 0.0F && (flag || bulletStack != null))
        {
            EntityLaserBullet laser = new EntityLaserBullet(world, player, 1.0F);
            world.playSoundAtEntity(player, "moreplanets:player.shoot_laser", 1.0F, 2.0F / (1.0F * 0.4F + 1.2F) + 1.0F * 0.5F);
            int slot = -1;

            if (bulletStack == null)
            {
                bulletStack = new ItemStack(MPItems.LASER_BULLET);
            }
            for (int i = 0; i < player.inventory.mainInventory.length; ++i)
            {
                if (player.inventory.mainInventory[i] != null && player.inventory.mainInventory[i].getItem() == bulletStack.getItem())
                {
                    int meta = player.inventory.mainInventory[i].getItemDamage();

                    if (meta == 0)
                    {
                        laser.setLaserType(EnumLaserType.NORMAL);
                    }
                    slot = i;
                    break;
                }
            }
            if (!world.isRemote)
            {
                world.spawnEntityInWorld(laser);
            }
            if (!flag && slot >= 0)
            {
                this.setElectricity(itemStack, this.getElectricityStored(itemStack) - 50.0F);
                player.inventory.decrStackSize(slot, 1);
            }
        }
        return itemStack;
    }

    @Override
    public ItemStack onItemRightClick(ItemStack itemStack, World world, EntityPlayer player)
    {
        if ((player.inventory.hasItem(MPItems.LASER_BULLET) || player.capabilities.isCreativeMode) && this.getElectricityStored(itemStack) > 0.0F)
        {
            player.setItemInUse(itemStack, this.getMaxItemUseDuration(itemStack));
        }
        return itemStack;
    }

    @Override
    public float getMaxElectricityStored(ItemStack itemStack)
    {
        return 100000.0F;
    }

    @Override
    public EnumSortCategoryItem getItemCategory(int meta)
    {
        return EnumSortCategoryItem.OTHER_TOOL;
    }

    private ItemStack findBullet(EntityPlayer player)
    {
        for (int i = 0; i < player.inventory.getSizeInventory(); ++i)
        {
            ItemStack itemStack = player.inventory.getStackInSlot(i);

            if (this.isBullet(itemStack))
            {
                return itemStack;
            }
        }
        return null;
    }

    protected boolean isBullet(ItemStack itemStack)
    {
        return itemStack != null && itemStack.getItem() == MPItems.LASER_BULLET;
    }
}