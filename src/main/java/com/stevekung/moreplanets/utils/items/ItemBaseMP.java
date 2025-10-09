package com.stevekung.moreplanets.utils.items;

import com.stevekung.lib.utils.ColorUtils;
import com.stevekung.moreplanets.core.MorePlanetsMod;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class ItemBaseMP extends Item implements MorePlanetsItem
{
    private EnumSortCategoryItem category;
    private ColorUtils.RGB rgb;
    private String name;

    public ItemBaseMP() {}

    public ItemBaseMP(String name)
    {
        this.setTranslationKey(name);
    }

    @Override
    public Item setTranslationKey(String name)
    {
        this.name = name;
        return super.setTranslationKey(name);
    }

    @Override
    public CreativeTabs getCreativeTab()
    {
        return MorePlanetsMod.ITEM_TAB;
    }

    @Override
    public EnumSortCategoryItem getItemCategory()
    {
        return this.category == null ? EnumSortCategoryItem.GENERAL : this.category;
    }

    @Override
    public String getModelName()
    {
        return this.name;
    }

    @Override
    public ColorUtils.RGB getRarityColor()
    {
        return this.rgb != null ? this.rgb : null;
    }

    @Override
    public String getItemStackDisplayName(ItemStack itemStack)
    {
        return this.getRarityColor() != null ? this.getRarityColor().toColoredFont() + super.getItemStackDisplayName(itemStack) : super.getItemStackDisplayName(itemStack);
    }

    public ItemBaseMP setSortCategory(EnumSortCategoryItem category)
    {
        this.category = category;
        return this;
    }

    public ItemBaseMP setRarityRGB(ColorUtils.RGB rgb)
    {
        this.rgb = rgb;
        return this;
    }
}