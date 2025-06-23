package com.stevekung.moreplanets.utils.items.tools;

import micdoodle8.mods.galacticraft.api.item.GCRarity;
import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemAxe;
import net.minecraft.item.ItemStack;
import com.stevekung.moreplanets.core.MorePlanetsMod;
import com.stevekung.moreplanets.integration.jei.MPJEIRecipes;
import com.stevekung.moreplanets.utils.client.renderer.IItemModelRender;
import com.stevekung.moreplanets.utils.items.EnumSortCategoryItem;
import com.stevekung.moreplanets.utils.items.ISortableItem;
import stevekung.mods.stevekunglib.utils.enums.EnumToolSpeed;

public class ItemAxeMP extends ItemAxe implements ISortableItem, IItemModelRender, GCRarity
{
    private final Item repairItem;
    private String name;

    public ItemAxeMP(String name, ToolMaterial material, Block block, EnumToolSpeed speed)
    {
        this(name, material, Item.getItemFromBlock(block), speed);
    }

    public ItemAxeMP(String name, ToolMaterial material, Item item, EnumToolSpeed speed)
    {
        super(material, material.getAttackDamage(), speed.getSpeed());
        this.repairItem = item;
        this.setTranslationKey(name);
        MPJEIRecipes.collectAnvilList(name, this, item);
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
        return EnumSortCategoryItem.AXE;
    }

    @Override
    public String getName()
    {
        return this.name;
    }
}