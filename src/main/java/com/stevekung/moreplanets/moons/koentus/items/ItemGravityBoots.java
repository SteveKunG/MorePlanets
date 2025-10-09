package com.stevekung.moreplanets.moons.koentus.items;

import com.stevekung.lib.utils.ColorUtils;
import com.stevekung.moreplanets.utils.itemblocks.ItemRarity;
import com.stevekung.moreplanets.utils.items.MPItemCategory;
import com.stevekung.moreplanets.utils.items.armor.ItemArmorMP;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

import micdoodle8.mods.galacticraft.api.item.IArmorGravity;

public class ItemGravityBoots extends ItemArmorMP implements IArmorGravity
{
    public ItemGravityBoots(String name, ArmorMaterial material)
    {
        super(name, material, EntityEquipmentSlot.FEET);
    }

    @Override
    public String getArmorTexture(ItemStack itemStack, Entity entity, EntityEquipmentSlot slot, String type)
    {
        return "moreplanets:textures/model/armor/gravity_boots.png";
    }

    @Override
    public int gravityOverrideIfLow(EntityPlayer player)
    {
        return 50;
    }

    @Override
    public int gravityOverrideIfHigh(EntityPlayer player)
    {
        return 75;
    }

    @Override
    protected Item getRepairItem()
    {
        return null;
    }

    @Override
    public MPItemCategory getItemCategory()
    {
        return MPItemCategory.OTHER_TOOL;
    }

    @Override
    public ColorUtils.RGB getRarityColor()
    {
        return ColorUtils.stringToRGB(ItemRarity.SPECIAL);
    }
}