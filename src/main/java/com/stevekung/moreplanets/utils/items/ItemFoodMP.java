package com.stevekung.moreplanets.utils.items;

import com.stevekung.lib.utils.ColorUtils;
import com.stevekung.moreplanets.core.MorePlanetsMod;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;

public abstract class ItemFoodMP extends ItemFood implements MorePlanetsItem
{
    private String name;
    private ColorUtils.RGB rgb;

    public ItemFoodMP()
    {
        super(0, false);
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
        return EnumSortCategoryItem.FOOD;
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

    public ItemFoodMP setRarityRGB(ColorUtils.RGB rgb)
    {
        this.rgb = rgb;
        return this;
    }
}