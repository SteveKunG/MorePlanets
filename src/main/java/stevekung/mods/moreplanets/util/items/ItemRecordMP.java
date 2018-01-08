package stevekung.mods.moreplanets.util.items;

import micdoodle8.mods.galacticraft.core.util.GCCoreUtil;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemRecord;
import net.minecraft.item.ItemStack;
import net.minecraft.util.SoundEvent;
import stevekung.mods.moreplanets.core.MorePlanetsCore;

public class ItemRecordMP extends ItemRecord implements ISortableItem, ISingleItemRender
{
    protected String name;

    public ItemRecordMP(String name, String recordName, SoundEvent sound)
    {
        super(recordName, sound);
        this.name = name;
        this.setUnlocalizedName(name);
    }

    @Override
    public CreativeTabs getCreativeTab()
    {
        return MorePlanetsCore.ITEM_TAB;
    }

    @Override
    public EnumSortCategoryItem getItemCategory(int meta)
    {
        return EnumSortCategoryItem.RECORD;
    }

    @Override
    public String getUnlocalizedName(ItemStack itemStack)
    {
        return GCCoreUtil.translate("item.record");
    }

    @Override
    public String getName()
    {
        return this.name;
    }
}