package stevekung.mods.moreplanets.module.planets.diona.items.armor;

import net.minecraft.entity.Entity;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import stevekung.mods.moreplanets.module.planets.diona.items.DionaItems;
import stevekung.mods.moreplanets.utils.items.armor.ItemBreathableArmor;

public class ItemBreathableIllenium extends ItemBreathableArmor
{
    public ItemBreathableIllenium(String name, ArmorMaterial material, EntityEquipmentSlot type)
    {
        super(material, type);
        this.setUnlocalizedName(name);
    }

    @Override
    public String getArmorTexture(ItemStack itemStack, Entity entity, EntityEquipmentSlot slot, String type)
    {
        return "moreplanets:textures/model/armor/breathable_illenium.png";
    }

    @Override
    public Item getRepairItems()
    {
        return DionaItems.COMPRESSED_ILLENIUM;
    }

    @Override
    public int getRepairItemsMetadata()
    {
        return -1;
    }

    @Override
    public EnumGearType getGearType()
    {
        return EnumGearType.HELMET;
    }

    @Override
    public Item getBreathableArmor()
    {
        return this;
    }
}