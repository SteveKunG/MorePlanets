/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 *
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.planets.kapteynb.items.armor;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.entity.Entity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import stevekung.mods.moreplanets.core.items.armor.ItemBreathableArmor;
import stevekung.mods.moreplanets.planets.kapteynb.items.KapteynBItems;

public class ArmorBreathableIceCrystal extends ItemBreathableArmor
{
    public ArmorBreathableIceCrystal(String name, ArmorMaterial par2EnumArmorMaterial, int par3, int par4)
    {
        super(par2EnumArmorMaterial, par3, par4);
        this.setUnlocalizedName(name);
    }

    @Override
    public String getArmorTexture(ItemStack stack, Entity entity, int slot, String type)
    {
        if (stack.getItem() == KapteynBArmorItems.breathable_ice_crystal_helmet)
        {
            return "kapteynb:textures/model/armor/breathable_ice_crystal.png";
        }
        return null;
    }

    @Override
    public Item getRepairItems()
    {
        return KapteynBItems.kapteyn_b_item;
    }

    @Override
    public int getRepairItemsMetadata()
    {
        return 5;
    }

    @Override
    public EnumGearType getGearType()
    {
        return EnumGearType.HELMET;
    }

    @Override
    public Item getBreathableArmor()
    {
        return KapteynBArmorItems.breathable_ice_crystal_helmet;
    }

    @Override
    public String getTextureLocation()
    {
        return "kapteynb:breathable_ice_crystal_helmet";
    }

    @Override
    @SideOnly(Side.CLIENT)
    public boolean hasEffect(ItemStack itemStack, int pass)
    {
        if (!itemStack.isItemEnchanted())
        {
            if (itemStack.getItem() == this)
            {
                itemStack.addEnchantment(Enchantment.aquaAffinity, 1);
                itemStack.addEnchantment(Enchantment.respiration, 3);
            }
        }
        return true;
    }
}