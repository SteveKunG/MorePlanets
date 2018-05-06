package stevekung.mods.moreplanets.planets.chalos.items.armor;

import net.minecraft.entity.Entity;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import stevekung.mods.moreplanets.init.MPItems;
import stevekung.mods.moreplanets.utils.items.armor.ItemArmorMP;

public class ItemArmorDiremsium extends ItemArmorMP
{
    public ItemArmorDiremsium(String name, ArmorMaterial material, EntityEquipmentSlot type)
    {
        super(material, type);
        this.setUnlocalizedName(name);
    }

    @Override
    public String getArmorTexture(ItemStack itemStack, Entity entity, EntityEquipmentSlot slot, String type)
    {
        if (itemStack.getItem() == MPItems.DIREMSIUM_HELMET || itemStack.getItem() == MPItems.DIREMSIUM_CHESTPLATE || itemStack.getItem() == MPItems.DIREMSIUM_BOOTS)
        {
            return "moreplanets:textures/model/armor/diremsium_1.png";
        }
        else if (itemStack.getItem() == MPItems.DIREMSIUM_LEGGINGS)
        {
            return "moreplanets:textures/model/armor/diremsium_2.png";
        }
        return null;
    }

    @Override
    public Item getRepairItem()
    {
        return MPItems.COMPRESSED_DIREMSIUM;
    }
}