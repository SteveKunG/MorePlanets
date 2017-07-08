package stevekung.mods.moreplanets.module.planets.chalos.items.armor;

import net.minecraft.entity.Entity;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import stevekung.mods.moreplanets.module.planets.chalos.items.ChalosItems;
import stevekung.mods.moreplanets.util.items.armor.ItemBreathableArmor;

public class ItemBreathableDiremsium extends ItemBreathableArmor
{
    public ItemBreathableDiremsium(String name, ArmorMaterial material, EntityEquipmentSlot type)
    {
        super(material, type);
        this.setUnlocalizedName(name);
    }

    @Override
    public String getArmorTexture(ItemStack itemStack, Entity entity, EntityEquipmentSlot slot, String type)
    {
        return "moreplanets:textures/model/armor/breathable_diremsium.png";
    }

    @Override
    public Item getRepairItems()
    {
        return ChalosItems.CHALOS_ITEM;
    }

    @Override
    public int getRepairItemsMetadata()
    {
        return 2;
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

    @Override
    public String getName()
    {
        return "breathable_diremsium_helmet";
    }
}