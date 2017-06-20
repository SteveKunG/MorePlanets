package stevekung.mods.moreplanets.util.items.armor;

import micdoodle8.mods.galacticraft.api.item.IBreathableArmor;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import stevekung.mods.moreplanets.core.MorePlanetsCore;
import stevekung.mods.moreplanets.util.items.EnumSortCategoryItem;
import stevekung.mods.moreplanets.util.items.ISingleItemRender;
import stevekung.mods.moreplanets.util.items.ISortableItem;

public abstract class ItemBreathableArmor extends ItemArmor implements IBreathableArmor, ISortableItem, ISingleItemRender
{
    public ItemBreathableArmor(ArmorMaterial material, EntityEquipmentSlot type)
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
    public boolean canBreathe(ItemStack itemStack, EntityPlayer player, EnumGearType type)
    {
        if (itemStack.getItem() == this.getBreathableArmor())
        {
            return true;
        }
        return false;
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
    public boolean handleGearType(EnumGearType type)
    {
        if (type == this.getGearType())
        {
            return true;
        }
        return false;
    }

    @Override
    public EnumSortCategoryItem getItemCategory(int meta)
    {
        return EnumSortCategoryItem.HELMET_BREATHABLE;
    }

    @Override
    public Item getItem()
    {
        return this;
    }

    protected abstract Item getRepairItems();
    protected abstract int getRepairItemsMetadata();
    protected abstract EnumGearType getGearType();
    protected abstract Item getBreathableArmor();
}