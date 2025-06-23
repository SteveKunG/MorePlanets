package com.stevekung.moreplanets.utils.items;

public class ItemDungeonKeyMP extends ItemBaseMP implements IDungeonKey
{
    public ItemDungeonKeyMP(String name)
    {
        this.setMaxStackSize(1);
        this.setTranslationKey(name);
    }

    @Override
    public EnumSortCategoryItem getItemCategory()
    {
        return EnumSortCategoryItem.DUNGEON_KEY;
    }
}