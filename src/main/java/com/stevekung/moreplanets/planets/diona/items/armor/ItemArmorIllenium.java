package com.stevekung.moreplanets.planets.diona.items.armor;

import com.stevekung.moreplanets.init.MPItems;
import com.stevekung.moreplanets.utils.items.armor.ItemArmorMP;

import net.minecraft.entity.Entity;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class ItemArmorIllenium extends ItemArmorMP
{
    public ItemArmorIllenium(String name, ArmorMaterial material, EntityEquipmentSlot type)
    {
        super(name, material, type);
    }

    @Override
    public String getArmorTexture(ItemStack itemStack, Entity entity, EntityEquipmentSlot slot, String type)
    {
        if (itemStack.getItem() == MPItems.ILLENIUM_HELMET || itemStack.getItem() == MPItems.ILLENIUM_CHESTPLATE || itemStack.getItem() == MPItems.ILLENIUM_BOOTS)
        {
            return "moreplanets:textures/model/armor/illenium_1.png";
        }
        else if (itemStack.getItem() == MPItems.ILLENIUM_LEGGINGS)
        {
            return "moreplanets:textures/model/armor/illenium_2.png";
        }
        return null;
    }

    @Override
    public Item getRepairItem()
    {
        return MPItems.COMPRESSED_ILLENIUM;
    }
}