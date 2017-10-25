package stevekung.mods.moreplanets.items;

import javax.annotation.Nullable;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.IItemPropertyGetter;
import net.minecraft.item.ItemFishingRod;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.stats.StatList;
import net.minecraft.util.*;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import stevekung.mods.moreplanets.core.MorePlanetsCore;
import stevekung.mods.moreplanets.entity.projectile.EntitySpaceFishHook;
import stevekung.mods.moreplanets.util.items.EnumSortCategoryItem;
import stevekung.mods.moreplanets.util.items.ISingleItemRender;
import stevekung.mods.moreplanets.util.items.ISortableItem;

public class ItemSpaceFishingRod extends ItemFishingRod implements ISortableItem, ISingleItemRender
{
    public ItemSpaceFishingRod(String name)
    {
        this.setMaxDamage(127);
        this.setMaxStackSize(1);
        this.setUnlocalizedName(name);

        this.addPropertyOverride(new ResourceLocation("cast"), new IItemPropertyGetter()
        {
            @Override
            @SideOnly(Side.CLIENT)
            public float apply(ItemStack itemStack, @Nullable World world, @Nullable EntityLivingBase entity)
            {
                return entity == null ? 0.0F : entity.getHeldItemMainhand() == itemStack && itemStack.hasTagCompound() && itemStack.getTagCompound().getBoolean("Cast") ? 1.0F : 0.0F;
            }
        });
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
    public ActionResult<ItemStack> onItemRightClick(ItemStack itemStack, World world, EntityPlayer player, EnumHand hand)
    {
        if (player.fishEntity != null)
        {
            int i = player.fishEntity.handleHookRetraction();
            itemStack.damageItem(i, player);
            player.swingArm(hand);

            if (itemStack.getTagCompound().getBoolean("Cast"))
            {
                itemStack.getTagCompound().setBoolean("Cast", false);
            }
        }
        else
        {
            world.playSound((EntityPlayer)null, player.posX, player.posY, player.posZ, SoundEvents.ENTITY_BOBBER_THROW, SoundCategory.NEUTRAL, 0.5F, 0.4F / (itemRand.nextFloat() * 0.4F + 0.8F));

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
            player.swingArm(hand);
            player.addStat(StatList.getObjectUseStats(this));
        }
        return new ActionResult<>(EnumActionResult.SUCCESS, itemStack);
    }

    @Override
    public int getItemEnchantability()
    {
        return 1;
    }

    @Override
    public String getName()
    {
        return "space_fishing_rod";
    }
}