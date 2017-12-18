package stevekung.mods.moreplanets.module.planets.chalos.items.armor;

import net.minecraft.entity.Entity;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import stevekung.mods.moreplanets.module.planets.chalos.items.ChalosItems;
import stevekung.mods.moreplanets.util.items.armor.ItemArmorMP;

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
        if (itemStack.getItem() == ChalosItems.DIREMSIUM_HELMET || itemStack.getItem() == ChalosItems.DIREMSIUM_CHESTPLATE || itemStack.getItem() == ChalosItems.DIREMSIUM_BOOTS)
        {
            return "moreplanets:textures/model/armor/diremsium_1.png";
        }
        else if (itemStack.getItem() == ChalosItems.DIREMSIUM_LEGGINGS)
        {
            return "moreplanets:textures/model/armor/diremsium_2.png";
        }
        return null;
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
}