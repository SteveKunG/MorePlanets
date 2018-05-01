package stevekung.mods.moreplanets.utils.items;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemRecord;
import net.minecraft.item.ItemStack;
import net.minecraft.util.SoundEvent;
import stevekung.mods.moreplanets.core.MorePlanetsMod;
import stevekung.mods.moreplanets.utils.client.renderer.IItemModelRender;
import stevekung.mods.stevekunglib.utils.LangUtils;

public class ItemRecordMP extends ItemRecord implements ISortableItem, IItemModelRender
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
        return MorePlanetsMod.ITEM_TAB;
    }

    @Override
    public EnumSortCategoryItem getItemCategory()
    {
        return EnumSortCategoryItem.RECORD;
    }

    @Override
    public String getUnlocalizedName(ItemStack itemStack)
    {
        return LangUtils.translate("item.record");
    }

    @Override
    public String getName()
    {
        return this.name;
    }
}