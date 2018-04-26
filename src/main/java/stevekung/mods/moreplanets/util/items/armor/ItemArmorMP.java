package stevekung.mods.moreplanets.util.items.armor;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import stevekung.mods.moreplanets.core.MorePlanetsMod;
import stevekung.mods.moreplanets.util.items.EnumSortCategoryItem;
import stevekung.mods.moreplanets.util.items.ISortableItem;

public abstract class ItemArmorMP extends ItemArmor implements ISortableItem
{
    public ItemArmorMP(ArmorMaterial material, EntityEquipmentSlot type)
    {
        super(material, -1, type);
    }

    @Override
    public CreativeTabs getCreativeTab()
    {
        return MorePlanetsMod.ITEM_TAB;
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
        case HEAD:
        default:
            return EnumSortCategoryItem.HELMET;
        case CHEST:
            return EnumSortCategoryItem.CHESTPLATE;
        case LEGS:
            return EnumSortCategoryItem.LEGGINGS;
        case FEET:
            return EnumSortCategoryItem.BOOTS;
        }
    }

    protected abstract Item getRepairItems();
    protected abstract int getRepairItemsMetadata();
}