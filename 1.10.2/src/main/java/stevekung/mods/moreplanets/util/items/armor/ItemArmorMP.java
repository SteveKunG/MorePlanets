package stevekung.mods.moreplanets.util.items.armor;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import stevekung.mods.moreplanets.core.MorePlanetsCore;
import stevekung.mods.moreplanets.util.items.EnumSortCategoryItem;
import stevekung.mods.moreplanets.util.items.ISortableItem;

public abstract class ItemArmorMP extends ItemArmor implements ISortableItem
{
    public ItemArmorMP(ArmorMaterial material, int type)
    {
        super(material, -1, type);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public CreativeTabs getCreativeTab()
    {
        return MorePlanetsCore.ITEM_TAB;
    }

    @Override
    public boolean getIsRepairable(ItemStack toRepair, ItemStack repair)
    {
        if (this.getRepairItems() == null && this.getRepairItemsMetadata() == -1)
        {
            return false;
        }
        if (repair.getItem() == this.getRepairItems() && repair.getItemDamage() == this.getRepairItemsMetadata())
        {
            return true;
        }
        return false;
    }

    @Override
    public EnumSortCategoryItem getItemCategory(int meta)
    {
        switch (this.armorType)
        {
        case 0:
        default:
            return EnumSortCategoryItem.HELMET;
        case 1:
            return EnumSortCategoryItem.CHESTPLATE;
        case 2:
            return EnumSortCategoryItem.LEGGINGS;
        case 3:
            return EnumSortCategoryItem.BOOTS;
        }
    }

    protected abstract Item getRepairItems();
    protected abstract int getRepairItemsMetadata();
}