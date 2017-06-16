package stevekung.mods.moreplanets.module.planets.diona.items.armor;

import net.minecraft.entity.Entity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import stevekung.mods.moreplanets.module.planets.diona.items.DionaItems;
import stevekung.mods.moreplanets.util.items.armor.ItemArmorMP;

public class ItemArmorIllenium extends ItemArmorMP
{
    public ItemArmorIllenium(String name, ArmorMaterial material, int type)
    {
        super(material, type);
        this.setUnlocalizedName(name);
    }

    @Override
    public String getArmorTexture(ItemStack itemStack, Entity entity, int slot, String layer)
    {
        if (itemStack.getItem() == DionaItems.ILLENIUM_HELMET || itemStack.getItem() == DionaItems.ILLENIUM_CHESTPLATE || itemStack.getItem() == DionaItems.ILLENIUM_BOOTS)
        {
            return "moreplanets:textures/model/armor/illenium_1.png";
        }
        else if (itemStack.getItem() == DionaItems.ILLENIUM_LEGGINGS)
        {
            return "moreplanets:textures/model/armor/illenium_2.png";
        }
        return null;
    }

    @Override
    public Item getRepairItems()
    {
        return DionaItems.DIONA_ITEM;
    }

    @Override
    public int getRepairItemsMetadata()
    {
        return 2;
    }
}