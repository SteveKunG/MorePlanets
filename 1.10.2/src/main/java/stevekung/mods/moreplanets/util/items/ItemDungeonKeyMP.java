package stevekung.mods.moreplanets.util.items;

import micdoodle8.mods.galacticraft.api.item.IKeyItem;
import net.minecraft.item.ItemStack;

public class ItemDungeonKeyMP extends ItemBaseMP implements IKeyItem
{
    private int tier;

    public ItemDungeonKeyMP(String name, int tier)
    {
        super();
        this.name = name;
        this.setMaxStackSize(1);
        this.setUnlocalizedName(name);
        this.tier = tier;
    }

    @Override
    public int getTier(ItemStack itemStack)
    {
        return this.tier;
    }

    @Override
    public EnumSortCategoryItem getItemCategory(int meta)
    {
        return EnumSortCategoryItem.DUNGEON_KEY;
    }
}