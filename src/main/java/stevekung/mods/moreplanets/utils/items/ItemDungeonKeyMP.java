package stevekung.mods.moreplanets.utils.items;

public class ItemDungeonKeyMP extends ItemBaseMP implements IDungeonKey
{
    public ItemDungeonKeyMP(String name, int tier)
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