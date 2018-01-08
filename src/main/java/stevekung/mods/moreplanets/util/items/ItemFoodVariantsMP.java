package stevekung.mods.moreplanets.util.items;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import stevekung.mods.moreplanets.core.MorePlanetsCore;

public abstract class ItemFoodVariantsMP extends ItemFood implements ISortableItem
{
    public ItemFoodVariantsMP()
    {
        super(0, false);
        this.setHasSubtypes(true);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public CreativeTabs getCreativeTab()
    {
        return MorePlanetsCore.ITEM_TAB;
    }

    @Override
    public int getMetadata(int meta)
    {
        return meta;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void getSubItems(CreativeTabs creativeTabs, NonNullList<ItemStack> list)
    {
        for (int i = 0; i < this.getItemVariantsName().length; i++)
        {
            list.add(new ItemStack(this, 1, i));
        }
    }

    @Override
    public String getUnlocalizedName(ItemStack itemStack)
    {
        int meta = itemStack.getItemDamage();
        return meta >= this.getItemVariantsName().length ? super.getUnlocalizedName(itemStack) + "." + this.getItemVariantsName()[0] : super.getUnlocalizedName(itemStack) + "." + this.getItemVariantsName()[meta];
    }

    @Override
    public EnumSortCategoryItem getItemCategory(int meta)
    {
        return EnumSortCategoryItem.FOOD;
    }

    protected abstract String[] getItemVariantsName();
}