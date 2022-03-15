package stevekung.mods.moreplanets.utils.items.armor;

import micdoodle8.mods.galacticraft.api.item.GCRarity;
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
import stevekung.mods.moreplanets.integration.jei.MPJEIRecipes;
import stevekung.mods.moreplanets.utils.ModelRegistryUtils;
import stevekung.mods.moreplanets.utils.client.renderer.IItemModelRender;
import stevekung.mods.moreplanets.utils.items.EnumSortCategoryItem;
import stevekung.mods.moreplanets.utils.items.ISortableItem;

public abstract class ItemBreathableArmor extends ItemArmor implements IBreathableArmor, ISortableItem, IItemModelRender, GCRarity
{
    private String name;

    public ItemBreathableArmor(ArmorMaterial material, EntityEquipmentSlot type)
    {
        super(material, -1, type);
    }

    @Override
    public Item setTranslationKey(String name)
    {
        this.name = name;
        MPJEIRecipes.collectAnvilList(name, this, this.getRepairItem());
        return super.setTranslationKey(name);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public ModelBiped getArmorModel(EntityLivingBase entity, ItemStack itemStack, EntityEquipmentSlot armorSlot, ModelBiped defaultModel)
    {
        return ModelRegistryUtils.getTranclucentArmorModel(armorSlot, defaultModel);
    }

    @Override
    public CreativeTabs getCreativeTab()
    {
        return MorePlanetsMod.ITEM_TAB;
    }

    @Override
    public boolean canBreathe(ItemStack itemStack, EntityPlayer player, EnumGearType type)
    {
        return true;
    }

    @Override
    public boolean getIsRepairable(ItemStack toRepair, ItemStack repair)
    {
        if (this.getRepairItem() == null)
        {
            return false;
        }
        if (repair.getItem() == this.getRepairItem())
        {
            return true;
        }
        return false;
    }

    @Override
    public boolean handleGearType(EnumGearType type)
    {
        return type == EnumGearType.HELMET;
    }

    @Override
    public EnumSortCategoryItem getItemCategory()
    {
        return EnumSortCategoryItem.HELMET_BREATHABLE;
    }

    @Override
    public String getName()
    {
        return this.name;
    }

    protected abstract Item getRepairItem();
}