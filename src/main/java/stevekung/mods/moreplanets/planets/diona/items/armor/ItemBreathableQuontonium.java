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

public class ItemBreathableQuontonium extends ItemBreathableArmor
{
    public ItemBreathableQuontonium(String name, ArmorMaterial par2EnumArmorMaterial, int par3, int par4)
    {
        super(par2EnumArmorMaterial, par3, par4);
        this.setUnlocalizedName(name);
    }

    @Override
    public String getArmorTexture(ItemStack stack, Entity entity, int slot, String layer)
    {
        if (stack.getItem() == DionaArmorItems.breathable_quontonium_helmet)
        {
            return "diona:textures/model/armor/breathable_quontonium_1.png";
        }
        return null;
    }

    @Override
    public String getTextureLocation()
    {
        return "diona:breathable_quontonium_helmet";
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

    @Override
    public EnumGearType getGearType()
    {
        return EnumGearType.HELMET;
    }

    @Override
    public Item getBreathableArmor()
    {
        return DionaArmorItems.breathable_quontonium_helmet;
    }
}