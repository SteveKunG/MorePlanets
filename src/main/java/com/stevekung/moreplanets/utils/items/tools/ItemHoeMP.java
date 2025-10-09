package com.stevekung.moreplanets.utils.items.tools;

import com.stevekung.moreplanets.core.MorePlanetsMod;
import com.stevekung.moreplanets.integration.jei.MPJEIRecipes;
import com.stevekung.moreplanets.utils.items.MPItemCategory;
import com.stevekung.moreplanets.utils.items.MorePlanetsItem;

import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemHoe;
import net.minecraft.item.ItemStack;

public class ItemHoeMP extends ItemHoe implements MorePlanetsItem
{
    private final Item repairItem;
    private final String name;

    public ItemHoeMP(String name, ToolMaterial material, Block block)
    {
        this(name, material, Item.getItemFromBlock(block));
    }

    public ItemHoeMP(String name, ToolMaterial material, Item item)
    {
        super(material);
        this.repairItem = item;
        this.name = name;
        this.setTranslationKey(name);
        MPJEIRecipes.collectAnvilList(name, this, item);
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
    public MPItemCategory getItemCategory()
    {
        return MPItemCategory.HOE;
    }

    @Override
    public String getModelName()
    {
        return this.name;
    }
}