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

public class ItemArmorFronisium extends ItemArmorMP
{
    public ItemArmorFronisium(String name, ArmorMaterial par2EnumArmorMaterial, int par3, int par4)
    {
        super(par2EnumArmorMaterial, par3, par4);
        this.setUnlocalizedName(name);
    }

    @Override
    public String getArmorTexture(ItemStack stack, Entity entity, int slot, String type)
    {
        if (stack.getItem() == DionaArmorItems.fronisium_helmet || stack.getItem() == DionaArmorItems.fronisium_chestplate || stack.getItem() == DionaArmorItems.fronisium_boots)
        {
            return "diona:textures/model/armor/fronisium_1.png";
        }
        if (stack.getItem() == DionaArmorItems.fronisium_leggings)
        {
            return "diona:textures/model/armor/fronisium_2.png";
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
        return 3;
    }
}