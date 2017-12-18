package stevekung.mods.moreplanets.items;

import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemFishingRod;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import stevekung.mods.moreplanets.core.MorePlanetsCore;
import stevekung.mods.moreplanets.entity.projectile.EntitySpaceFishHook;
import stevekung.mods.moreplanets.util.helper.ClientRegisterHelper;
import stevekung.mods.moreplanets.util.items.EnumSortCategoryItem;
import stevekung.mods.moreplanets.util.items.ISortableItem;

public class ItemSpaceFishingRod extends ItemFishingRod implements ISortableItem
{
    public ItemSpaceFishingRod(String name)
    {
        this.setMaxDamage(127);
        this.setMaxStackSize(1);
        this.setUnlocalizedName(name);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public ModelResourceLocation getModel(ItemStack itemStack, EntityPlayer player, int useRemaining)
    {
        if (itemStack.hasTagCompound() && itemStack.getTagCompound().getBoolean("Cast"))
        {
            return ClientRegisterHelper.getModelResourceLocation("moreplanets:space_fishing_rod_cast");
        }
        else
        {
            return null;
        }
    }

    @Override
    @SideOnly(Side.CLIENT)
    public CreativeTabs getCreativeTab()
    {
        return MorePlanetsCore.ITEM_TAB;
    }

    @Override
    public EnumSortCategoryItem getItemCategory(int meta)
    {
        return EnumSortCategoryItem.FISHING_ROD;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public boolean isFull3D()
    {
        return true;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public boolean shouldRotateAroundWhenRendering()
    {
        return true;
    }

    @Override
    public void onUpdate(ItemStack itemStack, World world, Entity entity, int slot, boolean isSelected)
    {
        if (!isSelected)
        {
            if (itemStack.hasTagCompound() && itemStack.getTagCompound().getBoolean("Cast"))
            {
                itemStack.getTagCompound().setBoolean("Cast", false);
            }
        }
        else
        {
            if (entity instanceof EntityPlayer)
            {
                if (!world.isRemote)
                {
                    EntityPlayer player = (EntityPlayer) entity;

                    if (player.fishEntity == null)
                    {
                        if (itemStack.hasTagCompound() && itemStack.getTagCompound().getBoolean("Cast"))
                        {
                            itemStack.getTagCompound().setBoolean("Cast", false);
                        }
                    }
                }
            }
        }
    }

    @Override
    public ItemStack onItemRightClick(ItemStack itemStack, World world, EntityPlayer player)
    {
        if (player.fishEntity != null)
        {
            int i = player.fishEntity.handleHookRetraction();
            itemStack.damageItem(i, player);
            player.swingItem();

            if (itemStack.getTagCompound().getBoolean("Cast"))
            {
                itemStack.getTagCompound().setBoolean("Cast", false);
            }
        }
        else
        {
            world.playSoundAtEntity(player, "random.bow", 0.5F, 0.4F / (itemRand.nextFloat() * 0.4F + 0.8F));

            if (!world.isRemote)
            {
                world.spawnEntityInWorld(new EntitySpaceFishHook(world, player));
            }
            if (itemStack.hasTagCompound())
            {
                itemStack.getTagCompound().setBoolean("Cast", true);
            }
            else
            {
                itemStack.setTagCompound(new NBTTagCompound());
                itemStack.getTagCompound().setBoolean("Cast", true);
            }
            player.swingItem();
        }
        return itemStack;
    }

    @Override
    public int getItemEnchantability()
    {
        return 1;
    }
}