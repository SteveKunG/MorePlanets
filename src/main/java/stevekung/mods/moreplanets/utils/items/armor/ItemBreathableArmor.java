package stevekung.mods.moreplanets.utils.items.armor;

import micdoodle8.mods.galacticraft.api.item.IBreathableArmor;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import stevekung.mods.moreplanets.core.MorePlanetsMod;
import stevekung.mods.moreplanets.utils.helper.ClientRegisterHelper;
import stevekung.mods.moreplanets.utils.items.EnumSortCategoryItem;
import stevekung.mods.moreplanets.utils.items.ISingleItemRender;
import stevekung.mods.moreplanets.utils.items.ISortableItem;

public abstract class ItemBreathableArmor extends ItemArmor implements IBreathableArmor, ISortableItem, ISingleItemRender
{
    private String name;

    public ItemBreathableArmor(ArmorMaterial material, EntityEquipmentSlot type)
    {
        super(material, -1, type);
    }

    @Override
    public Item setUnlocalizedName(String name)
    {
        this.name = name;
        return super.setUnlocalizedName(name);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public ModelBiped getArmorModel(EntityLivingBase entity, ItemStack itemStack, EntityEquipmentSlot armorSlot, ModelBiped defaultModel)
    {
        return ClientRegisterHelper.getTranclucentArmorModel(armorSlot, defaultModel);
    }

    @Override
    public CreativeTabs getCreativeTab()
    {
        return MorePlanetsMod.ITEM_TAB;
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
    public String getName()
    {
        return this.name;
    }

    protected abstract Item getRepairItems();
    protected abstract int getRepairItemsMetadata();
    protected abstract EnumGearType getGearType();
    protected abstract Item getBreathableArmor();
}