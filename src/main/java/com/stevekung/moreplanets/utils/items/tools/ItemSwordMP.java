package com.stevekung.moreplanets.utils.items.tools;

import com.stevekung.moreplanets.core.MorePlanetsMod;
import com.stevekung.moreplanets.integration.jei.MPJEIRecipes;
import com.stevekung.moreplanets.utils.items.EnumSortCategoryItem;
import com.stevekung.moreplanets.utils.items.MorePlanetsItem;

import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;

public class ItemSwordMP extends ItemSword implements MorePlanetsItem
{
    private Item repairItem;
    private String name;

    public ItemSwordMP(String name, ToolMaterial material, Block block)
    {
        this(name, material, Item.getItemFromBlock(block));
    }

    public ItemSwordMP(String name, ToolMaterial material, Item item)
    {
        super(material);
        this.repairItem = item;
        this.setTranslationKey(name);
        MPJEIRecipes.collectAnvilList(name, this, item);
    }

    public ItemSwordMP(ToolMaterial material)
    {
        super(material);
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
    public boolean getIsRepairable(ItemStack toRepair, ItemStack repair)
    {
        return repair.getItem() == this.repairItem;
    }

    @Override
    public EnumSortCategoryItem getItemCategory()
    {
        return EnumSortCategoryItem.SWORD;
    }

    @Override
    public String getModelName()
    {
        return this.name;
    }
}