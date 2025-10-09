package com.stevekung.moreplanets.utils.items.armor;

import com.stevekung.moreplanets.core.MorePlanetsMod;
import com.stevekung.moreplanets.integration.jei.MPJEIRecipes;
import com.stevekung.moreplanets.utils.items.MPItemCategory;
import com.stevekung.moreplanets.utils.items.MorePlanetsItem;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;

public abstract class ItemArmorMP extends ItemArmor implements MorePlanetsItem
{
    private String name;

    public ItemArmorMP(ArmorMaterial material, EntityEquipmentSlot type)
    {
        super(material, -1, type);
    }

    @Override
    public Item setTranslationKey(String name)
    {
        this.name = name;
        MPJEIRecipes.collectAnvilList(name, this, this.getRepairItem());
        return super.setTranslationKey(name);
    }

    @Override
    public CreativeTabs getCreativeTab()
    {
        return MorePlanetsMod.ITEM_TAB;
    }

    @Override
    public boolean getIsRepairable(ItemStack toRepair, ItemStack repair)
    {
        if (this.getRepairItem() == null)
        {
            return false;
        }
        return repair.getItem() == this.getRepairItem();
    }

    @Override
    public MPItemCategory getItemCategory()
    {
        switch (this.armorType)
        {
        case CHEST:
            return MPItemCategory.CHESTPLATE;
        case LEGS:
            return MPItemCategory.LEGGINGS;
        case FEET:
            return MPItemCategory.BOOTS;
            default:
                return MPItemCategory.HELMET;
        }
    }

    @Override
    public String getItemStackDisplayName(ItemStack itemStack)
    {
        return this.getRarityColor() != null ? this.getRarityColor().toColoredFont() + super.getItemStackDisplayName(itemStack) : super.getItemStackDisplayName(itemStack);
    }

    @Override
    public String getModelName()
    {
        return this.name;
    }

    protected abstract Item getRepairItem();
}