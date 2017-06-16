/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 *
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.planets.diona.items.armor;

import net.minecraft.entity.Entity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import stevekung.mods.moreplanets.core.items.armor.ItemArmorMP;
import stevekung.mods.moreplanets.planets.diona.items.DionaItems;

public class ItemArmorQuontonium extends ItemArmorMP
{
    public ItemArmorQuontonium(String name, ArmorMaterial material, int render, int type)
    {
        super(material, render, type);
        this.setUnlocalizedName(name);
    }

    @Override
    public String getArmorTexture(ItemStack stack, Entity entity, int slot, String layer)
    {
        if (stack.getItem() == DionaArmorItems.quontonium_helmet || stack.getItem() == DionaArmorItems.quontonium_chestplate || stack.getItem() == DionaArmorItems.quontonium_boots)
        {
            return "diona:textures/model/armor/quontonium_1.png";
        }
        else if (stack.getItem() == DionaArmorItems.quontonium_leggings)
        {
            return "diona:textures/model/armor/quontonium_2.png";
        }
        return null;
    }

    @Override
    public String getTextureLocation()
    {
        return "diona";
    }

    @Override
    public Item getRepairItems()
    {
        return DionaItems.diona_item;
    }

    @Override
    public int getRepairItemsMetadata()
    {
        return 2;
    }
}