package com.stevekung.moreplanets.utils.items;

public class ItemDungeonKeyMP extends ItemBaseMP implements IDungeonKey
{
    public ItemDungeonKeyMP(String name)
    {
        super(name);
        this.setMaxStackSize(1);
    }

    @Override
    public MPItemCategory getItemCategory()
    {
        return MPItemCategory.DUNGEON_KEY;
    }
}