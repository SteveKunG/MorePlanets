package com.stevekung.moreplanets.utils.items;

import com.stevekung.lib.utils.ColorUtils;
import com.stevekung.moreplanets.core.MorePlanetsMod;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;

public abstract class ItemFoodMP extends ItemFood implements MorePlanetsItem
{
    private final String name;
    private ColorUtils.RGB rgb;

    public ItemFoodMP(String name)
    {
        super(0, false);
        this.name = name;
        this.setTranslationKey(name);
    }

    @Override
    public CreativeTabs getCreativeTab()
    {
        return MorePlanetsMod.ITEM_TAB;
    }

    @Override
    public MPItemCategory getItemCategory()
    {
        return MPItemCategory.FOOD;
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