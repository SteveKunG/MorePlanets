package stevekung.mods.moreplanets.items;

import java.util.List;

import javax.annotation.Nullable;

import micdoodle8.mods.galacticraft.api.item.ElectricItemHelper;
import micdoodle8.mods.galacticraft.api.item.IItemElectric;
import micdoodle8.mods.galacticraft.core.energy.EnergyDisplayHelper;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumAction;
import net.minecraft.item.IItemPropertyGetter;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagDouble;
import net.minecraft.nbt.NBTTagFloat;
import net.minecraft.util.*;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import stevekung.mods.moreplanets.core.MorePlanetsMod;
import stevekung.mods.moreplanets.entity.projectile.EntityLaserBullet;
import stevekung.mods.moreplanets.entity.projectile.EntityLaserBullet.EnumLaserType;
import stevekung.mods.moreplanets.init.MPItems;
import stevekung.mods.moreplanets.init.MPSounds;
import stevekung.mods.moreplanets.util.helper.CommonRegisterHelper;
import stevekung.mods.moreplanets.util.items.EnumSortCategoryItem;
import stevekung.mods.moreplanets.util.items.ISingleItemRender;
import stevekung.mods.moreplanets.util.items.ISortableItem;
import stevekung.mods.moreplanets.util.items.ItemBaseMP;

public class ItemLaserGun extends ItemBaseMP implements ISortableItem, ISingleItemRender, IItemElectric
{
    private float transferMax = 200.0F;
    private static final int DAMAGE_RANGE = 100;

    public ItemLaserGun(String name)
    {
        super();
        this.setMaxStackSize(1);
        this.setMaxDamage(DAMAGE_RANGE);
        this.setNoRepair();
        this.setUnlocalizedName(name);

        this.addPropertyOverride(new ResourceLocation("pull"), new IItemPropertyGetter()
        {
            @Override
            @SideOnly(Side.CLIENT)
            public float apply(ItemStack itemStack, @Nullable World world, @Nullable EntityLivingBase living)
            {
                if (living == null)
                {
                    return 0.0F;
                }
                else
                {
                    ItemStack gun = living.getActiveItemStack();

                    if (living instanceof EntityPlayer)
                    {
                        EntityPlayer player = (EntityPlayer) living;

                        if (!gun.isEmpty() && gun.getItem() == MPItems.LASER_GUN)
                        {
                            int i = itemStack.getMaxItemUseDuration() - player.getItemInUseCount();

                            if (i > 12)
                            {
                                return 0.9F;
                            }
                            if (i > 0)
                            {
                                return 0.65F;
                            }
                        }
                    }
                    return 0.0F;
                }
            }
        });
        this.addPropertyOverride(new ResourceLocation("pulling"), new IItemPropertyGetter()
        {
            @Override
            @SideOnly(Side.CLIENT)
            public float apply(ItemStack itemStack, @Nullable World world, @Nullable EntityLivingBase living)
            {
                return living != null && living.isHandActive() && living.getActiveItemStack() == itemStack ? 1.0F : 0.0F;
            }
        });
    }

    @Override
    public EnumAction getItemUseAction(ItemStack itemStack)
    {
        return EnumAction.BOW;
    }

    @Override
    public int getMaxItemUseDuration(ItemStack itemStack)
    {
        return 16;
    }

    @Override
    public CreativeTabs getCreativeTab()
    {
        return MorePlanetsMod.ITEM_TAB;
    }

    @Override
    public void getSubItems(CreativeTabs creativeTabs, NonNullList<ItemStack> list)
    {
        if (CommonRegisterHelper.isItemTab(creativeTabs))
        {
            list.add(ElectricItemHelper.getUncharged(new ItemStack(this)));
            list.add(ElectricItemHelper.getWithCharge(new ItemStack(this), this.getMaxElectricityStored(new ItemStack(this))));
        }
    }

    @Override
    public ItemStack onItemUseFinish(ItemStack itemStack, World world, EntityLivingBase living)
    {
        if (living instanceof EntityPlayer)
        {
            EntityPlayer player = (EntityPlayer) living;
            boolean flag = player.capabilities.isCreativeMode;
            ItemStack bulletStack = this.findBullet(player);

            if (this.getElectricityStored(itemStack) > 0.0F && (flag || !bulletStack.isEmpty()))
            {
                EntityLaserBullet laser = new EntityLaserBullet(world, player, 1.0F);
                world.playSound(player, player.getPosition(), MPSounds.LASER_SHOOTED, SoundCategory.PLAYERS, 1.0F, 2.0F / (1.0F * 0.4F + 1.2F) + 1.0F * 0.5F);
                int slot = -1;

                if (bulletStack.isEmpty())
                {
                    bulletStack = new ItemStack(MPItems.LASER_BULLET);
                }
                for (int i = 0; i < player.inventory.mainInventory.size(); ++i)
                {
                    if (!player.inventory.mainInventory.get(i).isEmpty() && player.inventory.mainInventory.get(i).getItem() == bulletStack.getItem())
                    {
                        if (bulletStack.getItem() == MPItems.LASER_BULLET)
                        {
                            laser.setLaserType(EnumLaserType.NORMAL);
                        }
                        if (bulletStack.getItem() == MPItems.INFECTED_CRYSTALLIZED_LASER_BULLET)
                        {
                            laser.setLaserType(EnumLaserType.INFECTED_CRYSTALLIZED);
                        }
                        slot = i;
                        break;
                    }
                }
                if (!world.isRemote)
                {
                    world.spawnEntity(laser);
                }
                if (!flag && slot >= 0)
                {
                    this.setElectricity(itemStack, this.getElectricityStored(itemStack) - 50.0F);
                    player.inventory.decrStackSize(slot, 1);
                }
            }
        }
        return itemStack;
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World world, EntityPlayer player, EnumHand hand)
    {
        boolean flag = !this.findBullet(player).isEmpty();
        ItemStack itemStack = player.getHeldItem(hand);

        if (this.getElectricityStored(itemStack) > 0.0F)
        {
            if (!player.capabilities.isCreativeMode && !flag)
            {
                return !flag ? new ActionResult<>(EnumActionResult.FAIL, itemStack) : new ActionResult<>(EnumActionResult.PASS, itemStack);
            }
            else
            {
                player.setActiveHand(hand);
                return new ActionResult<>(EnumActionResult.SUCCESS, itemStack);
            }
        }
        return new ActionResult<>(EnumActionResult.PASS, itemStack);
    }

    @Override
    public float getMaxElectricityStored(ItemStack itemStack)
    {
        return 100000.0F;
    }

    @Override
    public float recharge(ItemStack itemStack, float energy, boolean doRecharge)
    {
        float rejectedElectricity = Math.max(this.getElectricityStored(itemStack) + energy - this.getMaxElectricityStored(itemStack), 0);
        float energyToReceive = energy - rejectedElectricity;

        if (energyToReceive > this.transferMax)
        {
            rejectedElectricity += energyToReceive - this.transferMax;
            energyToReceive = this.transferMax;
        }
        if (doRecharge)
        {
            this.setElectricity(itemStack, this.getElectricityStored(itemStack) + energyToReceive);
        }
        return energyToReceive;
    }

    @Override
    public float discharge(ItemStack itemStack, float energy, boolean doDischarge)
    {
        float thisEnergy = this.getElectricityStored(itemStack);
        float energyToTransfer = Math.min(Math.min(thisEnergy, energy), this.transferMax);

        if (doDischarge)
        {
            this.setElectricity(itemStack, thisEnergy - energyToTransfer);
        }
        return energyToTransfer;
    }

    @Override
    public float getElectricityStored(ItemStack itemStack)
    {
        if (itemStack.getTagCompound() == null)
        {
            itemStack.setTagCompound(new NBTTagCompound());
        }

        float energyStored = 0.0F;

        if (itemStack.getTagCompound().hasKey("electricity"))
        {
            NBTBase base = itemStack.getTagCompound().getTag("electricity");

            if (base instanceof NBTTagDouble)
            {
                energyStored = ((NBTTagDouble) base).getFloat();
            }
            else if (base instanceof NBTTagFloat)
            {
                energyStored = ((NBTTagFloat) base).getFloat();
            }
        }
        else
        {
            if (itemStack.getItemDamage() == DAMAGE_RANGE)
            {
                return 0F;
            }
            energyStored = this.getMaxElectricityStored(itemStack) * (DAMAGE_RANGE - itemStack.getItemDamage()) / DAMAGE_RANGE;
            itemStack.getTagCompound().setFloat("electricity", energyStored);
        }
        itemStack.setItemDamage(DAMAGE_RANGE - (int) (energyStored / this.getMaxElectricityStored(itemStack) * DAMAGE_RANGE));
        return energyStored;
    }

    @Override
    public void setElectricity(ItemStack itemStack, float joules)
    {
        if (itemStack.getTagCompound() == null)
        {
            itemStack.setTagCompound(new NBTTagCompound());
        }

        float electricityStored = Math.max(Math.min(joules, this.getMaxElectricityStored(itemStack)), 0);

        if (joules > 0F || itemStack.getTagCompound().hasKey("electricity"))
        {
            itemStack.getTagCompound().setFloat("electricity", electricityStored);
        }
        itemStack.setItemDamage(DAMAGE_RANGE - (int) (electricityStored / this.getMaxElectricityStored(itemStack) * DAMAGE_RANGE));
    }

    @Override
    public float getTransfer(ItemStack itemStack)
    {
        return Math.min(this.transferMax, this.getMaxElectricityStored(itemStack) - this.getElectricityStored(itemStack));
    }

    @Override
    public int getTierGC(ItemStack itemStack)
    {
        return 1;
    }

    @Override
    public EnumSortCategoryItem getItemCategory(int meta)
    {
        return EnumSortCategoryItem.OTHER_TOOL;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void addInformation(ItemStack itemStack, @Nullable World world, List<String> tooltip, ITooltipFlag flag)
    {
        TextFormatting color = TextFormatting.GOLD;
        float joules = this.getElectricityStored(itemStack);

        if (joules <= this.getMaxElectricityStored(itemStack) / 3)
        {
            color = TextFormatting.DARK_RED;
        }
        else if (joules > this.getMaxElectricityStored(itemStack) * 2 / 3)
        {
            color = TextFormatting.DARK_GREEN;
        }
        tooltip.add(color + EnergyDisplayHelper.getEnergyDisplayS(joules) + "/" + EnergyDisplayHelper.getEnergyDisplayS(this.getMaxElectricityStored(itemStack)));
    }

    private ItemStack findBullet(EntityPlayer player)
    {
        if (this.isBullet(player.getHeldItem(EnumHand.OFF_HAND)))
        {
            return player.getHeldItem(EnumHand.OFF_HAND);
        }
        else if (this.isBullet(player.getHeldItem(EnumHand.MAIN_HAND)))
        {
            return player.getHeldItem(EnumHand.MAIN_HAND);
        }
        else
        {
            for (int i = 0; i < player.inventory.getSizeInventory(); ++i)
            {
                ItemStack itemStack = player.inventory.getStackInSlot(i);

                if (this.isBullet(itemStack))
                {
                    return itemStack;
                }
            }
            return ItemStack.EMPTY;
        }
    }

    protected boolean isBullet(ItemStack itemStack)
    {
        return !itemStack.isEmpty() && (itemStack.getItem() == MPItems.LASER_BULLET || itemStack.getItem() == MPItems.INFECTED_CRYSTALLIZED_LASER_BULLET);
    }
}