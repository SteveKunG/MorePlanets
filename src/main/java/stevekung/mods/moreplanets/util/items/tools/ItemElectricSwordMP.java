package stevekung.mods.moreplanets.util.items.tools;

import java.util.List;

import javax.annotation.Nullable;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;

import micdoodle8.mods.galacticraft.api.item.ElectricItemHelper;
import micdoodle8.mods.galacticraft.api.item.IItemElectric;
import micdoodle8.mods.galacticraft.core.energy.EnergyDisplayHelper;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.EnumAction;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagDouble;
import net.minecraft.nbt.NBTTagFloat;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import stevekung.mods.moreplanets.core.MorePlanetsCore;
import stevekung.mods.moreplanets.util.helper.CommonRegisterHelper;
import stevekung.mods.moreplanets.util.items.EnumSortCategoryItem;
import stevekung.mods.moreplanets.util.items.ISortableItem;

public class ItemElectricSwordMP extends ItemSword implements IItemElectric, ISortableItem
{
    public float transferMax = 200;
    private float maxPower;

    public ItemElectricSwordMP(String name, ToolMaterial material, float maxPower)
    {
        super(material);
        this.setMaxDamage(100);
        this.setNoRepair();
        this.setUnlocalizedName(name);
        this.maxPower = maxPower;
    }

    @Override
    public float getMaxElectricityStored(ItemStack itemStack)
    {
        return this.maxPower;
    }

    @Override
    public float getDestroySpeed(ItemStack itemStack, IBlockState state)
    {
        return this.getElectricityStored(itemStack) == 0.0F ? 0.0F : super.getDestroySpeed(itemStack, state);
    }

    @Override
    public boolean hitEntity(ItemStack itemStack, EntityLivingBase living, EntityLivingBase holder)
    {
        if (this.getElectricityStored(itemStack) > 0.0F)
        {
            this.setElectricity(itemStack, this.getElectricityStored(itemStack) - 10.0F);
            return true;
        }
        return false;
    }

    @Override
    public boolean onBlockDestroyed(ItemStack itemStack, World world, IBlockState state, BlockPos pos, EntityLivingBase entityLiving)
    {
        if (state.getBlockHardness(world, pos) > 0.0F)
        {
            if (this.getElectricityStored(itemStack) > 0.0F)
            {
                this.setElectricity(itemStack, this.getElectricityStored(itemStack) - 10.0F);
            }
        }
        return true;
    }

    @Override
    public EnumAction getItemUseAction(ItemStack itemStack)
    {
        if (this.getElectricityStored(itemStack) > 0.0F)
        {
            return EnumAction.BLOCK;
        }
        return EnumAction.NONE;
    }

    @Override
    public int getMaxItemUseDuration(ItemStack itemStack)
    {
        if (this.getElectricityStored(itemStack) > 0.0F)
        {
            return 72000;
        }
        return 0;
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World world, EntityPlayer player, EnumHand hand)
    {
        ItemStack itemStack = player.getHeldItem(hand);

        if (this.getElectricityStored(itemStack) > 0.0F)
        {
            player.setActiveHand(hand);
            return new ActionResult<>(EnumActionResult.SUCCESS, itemStack);
        }
        else
        {
            return new ActionResult<>(EnumActionResult.PASS, itemStack);
        }
    }

    @Override
    public Multimap<String, AttributeModifier> getAttributeModifiers(EntityEquipmentSlot slot, ItemStack itemStack)
    {
        return this.getElectricityStored(itemStack) > 0.0F ? super.getAttributeModifiers(slot, itemStack) : HashMultimap.create();
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void addInformation(ItemStack itemStack, @Nullable World world, List<String> list, ITooltipFlag flag)
    {
        TextFormatting color = null;
        float joules = this.getElectricityStored(itemStack);

        if (joules <= this.getMaxElectricityStored(itemStack) / 3)
        {
            color = TextFormatting.DARK_RED;
        }
        else if (joules > this.getMaxElectricityStored(itemStack) * 2 / 3)
        {
            color = TextFormatting.DARK_GREEN;
        }
        else
        {
            color = TextFormatting.GOLD;
        }
        list.add(color + EnergyDisplayHelper.getEnergyDisplayS(joules) + "/" + EnergyDisplayHelper.getEnergyDisplayS(this.getMaxElectricityStored(itemStack)));
    }

    @Override
    public void onCreated(ItemStack itemStack, World world, EntityPlayer player)
    {
        this.setElectricity(itemStack, 0);
    }

    @Override
    public float recharge(ItemStack itemStack, float energy, boolean doReceive)
    {
        float rejectedElectricity = Math.max(this.getElectricityStored(itemStack) + energy - this.getMaxElectricityStored(itemStack), 0);
        float energyToReceive = energy - rejectedElectricity;

        if (energyToReceive > this.transferMax)
        {
            rejectedElectricity += energyToReceive - this.transferMax;
            energyToReceive = this.transferMax;
        }
        if (doReceive)
        {
            this.setElectricity(itemStack, this.getElectricityStored(itemStack) + energyToReceive);
        }
        return energyToReceive;
    }

    @Override
    public float discharge(ItemStack itemStack, float energy, boolean doTransfer)
    {
        float energyToTransfer = Math.min(Math.min(this.getElectricityStored(itemStack), energy), this.transferMax);

        if (doTransfer)
        {
            this.setElectricity(itemStack, this.getElectricityStored(itemStack) - energyToTransfer);
        }
        return energyToTransfer;
    }

    @Override
    public int getTierGC(ItemStack itemStack)
    {
        return 1;
    }

    @Override
    public void setElectricity(ItemStack itemStack, float joules)
    {
        if (itemStack.getTagCompound() == null)
        {
            itemStack.setTagCompound(new NBTTagCompound());
        }
        float electricityStored = Math.max(Math.min(joules, this.getMaxElectricityStored(itemStack)), 0);
        itemStack.getTagCompound().setFloat("Electricity", electricityStored);
        itemStack.setItemDamage((int) (100 - electricityStored / this.getMaxElectricityStored(itemStack) * 100));
    }

    @Override
    public float getTransfer(ItemStack itemStack)
    {
        return Math.min(this.transferMax, this.getMaxElectricityStored(itemStack) - this.getElectricityStored(itemStack));
    }

    @Override
    public float getElectricityStored(ItemStack itemStack)
    {
        if (itemStack.getTagCompound() == null)
        {
            itemStack.setTagCompound(new NBTTagCompound());
        }

        float energyStored = 0f;

        if (itemStack.getTagCompound().hasKey("Electricity"))
        {
            NBTBase obj = itemStack.getTagCompound().getTag("Electricity");

            if (obj instanceof NBTTagDouble)
            {
                energyStored = ((NBTTagDouble) obj).getFloat();
            }
            else if (obj instanceof NBTTagFloat)
            {
                energyStored = ((NBTTagFloat) obj).getFloat();
            }
        }
        itemStack.setItemDamage((int) (100 - energyStored / this.getMaxElectricityStored(itemStack) * 100));
        return energyStored;
    }

    @Override
    public void getSubItems(CreativeTabs creativeTabs, NonNullList<ItemStack> list)
    {
        if (CommonRegisterHelper.isItemTab(creativeTabs))
        {
            list.add(ElectricItemHelper.getWithCharge(new ItemStack(this), this.getMaxElectricityStored(new ItemStack(this))));
        }
    }

    @Override
    public CreativeTabs getCreativeTab()
    {
        return MorePlanetsCore.ITEM_TAB;
    }

    @Override
    public boolean getIsRepairable(ItemStack toRepair, ItemStack repair)
    {
        return false;
    }

    @Override
    public EnumSortCategoryItem getItemCategory(int meta)
    {
        return EnumSortCategoryItem.SWORD;
    }
}