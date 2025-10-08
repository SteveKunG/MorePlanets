package com.stevekung.moreplanets.planets.nibiru.items.armor;

import com.stevekung.moreplanets.init.MPItems;
import com.stevekung.moreplanets.utils.items.armor.ItemBreathableArmor;

import net.minecraft.entity.Entity;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class ItemBreathableMultalicCrystal extends ItemBreathableArmor
{
    public ItemBreathableMultalicCrystal(String name, ArmorMaterial material, EntityEquipmentSlot type)
    {
        super(material, type);
        this.setTranslationKey(name);
    }

    @Override
    public String getArmorTexture(ItemStack itemStack, Entity entity, EntityEquipmentSlot slot, String type)
    {
        return "moreplanets:textures/model/armor/breathable_multalic_crystal.png";
    }

    @Override
    public Item getRepairItem()
    {
        return MPItems.MULTALIC_CRYSTAL_PIECES;
    }
}