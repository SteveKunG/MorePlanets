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
import stevekung.mods.moreplanets.core.items.armor.ItemBreathableArmor;
import stevekung.mods.moreplanets.planets.diona.items.DionaItems;

public class ItemBreathableFronisium extends ItemBreathableArmor
{
    public ItemBreathableFronisium(String name, ArmorMaterial par2EnumArmorMaterial, int par3, int par4)
    {
        super(par2EnumArmorMaterial, par3, par4);
        this.setUnlocalizedName(name);
    }

    @Override
    public String getArmorTexture(ItemStack stack, Entity entity, int slot, String type)
    {
        if (stack.getItem() == DionaArmorItems.breathable_fronisium_helmet)
        {
            return "diona:textures/model/armor/breathable_fronisium_1.png";
        }
        return null;
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

    @Override
    public EnumGearType getGearType()
    {
        return EnumGearType.HELMET;
    }

    @Override
    public Item getBreathableArmor()
    {
        return DionaArmorItems.breathable_fronisium_helmet;
    }

    @Override
    public String getTextureLocation()
    {
        return "diona:breathable_fronisium_helmet";
    }
}