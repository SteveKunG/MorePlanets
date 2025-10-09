package com.stevekung.moreplanets.planets.nibiru.items.armor;

import com.stevekung.moreplanets.init.MPItems;
import com.stevekung.moreplanets.utils.ModelRegistryUtils;
import com.stevekung.moreplanets.utils.items.armor.ItemArmorMP;

import net.minecraft.client.model.ModelBiped;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemArmorMultalicCrystal extends ItemArmorMP
{
    public ItemArmorMultalicCrystal(String name, ArmorMaterial material, EntityEquipmentSlot type)
    {
        super(name, material, type);
    }

    @Override
    public String getArmorTexture(ItemStack itemStack, Entity entity, EntityEquipmentSlot slot, String type)
    {
        if (itemStack.getItem() == MPItems.MULTALIC_CRYSTAL_HELMET || itemStack.getItem() == MPItems.MULTALIC_CRYSTAL_CHESTPLATE || itemStack.getItem() == MPItems.MULTALIC_CRYSTAL_BOOTS)
        {
            return "moreplanets:textures/model/armor/multalic_crystal_1.png";
        }
        else if (itemStack.getItem() == MPItems.MULTALIC_CRYSTAL_LEGGINGS)
        {
            return "moreplanets:textures/model/armor/multalic_crystal_2.png";
        }
        return null;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public ModelBiped getArmorModel(EntityLivingBase entity, ItemStack itemStack, EntityEquipmentSlot armorSlot, ModelBiped defaultModel)
    {
        return ModelRegistryUtils.getTranclucentArmorModel(armorSlot, defaultModel);
    }

    @Override
    public Item getRepairItem()
    {
        return MPItems.MULTALIC_CRYSTAL_PIECES;
    }
}