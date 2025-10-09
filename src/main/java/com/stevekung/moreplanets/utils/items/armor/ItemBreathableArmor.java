package com.stevekung.moreplanets.utils.items.armor;

import com.stevekung.moreplanets.utils.ModelRegistryUtils;
import com.stevekung.moreplanets.utils.items.MPItemCategory;
import com.stevekung.moreplanets.utils.items.MorePlanetsItem;

import net.minecraft.client.model.ModelBiped;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import micdoodle8.mods.galacticraft.api.item.IBreathableArmor;

public abstract class ItemBreathableArmor extends ItemArmorMP implements IBreathableArmor, MorePlanetsItem
{
    public ItemBreathableArmor(String name, ArmorMaterial material, EntityEquipmentSlot type)
    {
        super(name, material, type);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public ModelBiped getArmorModel(EntityLivingBase entity, ItemStack itemStack, EntityEquipmentSlot armorSlot, ModelBiped defaultModel)
    {
        return ModelRegistryUtils.getTranclucentArmorModel(armorSlot, defaultModel);
    }

    @Override
    public boolean canBreathe(ItemStack itemStack, EntityPlayer player, EnumGearType type)
    {
        return true;
    }

    @Override
    public boolean handleGearType(EnumGearType type)
    {
        return type == EnumGearType.HELMET;
    }

    @Override
    public MPItemCategory getItemCategory()
    {
        return MPItemCategory.HELMET_BREATHABLE;
    }
}