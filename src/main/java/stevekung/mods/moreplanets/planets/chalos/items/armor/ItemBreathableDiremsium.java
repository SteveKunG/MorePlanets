package stevekung.mods.moreplanets.planets.chalos.items.armor;

import net.minecraft.entity.Entity;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import stevekung.mods.moreplanets.init.MPItems;
import stevekung.mods.moreplanets.utils.items.armor.ItemBreathableArmor;

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
    public Item getRepairItem()
    {
        return MPItems.COMPRESSED_DIREMSIUM;
    }
}