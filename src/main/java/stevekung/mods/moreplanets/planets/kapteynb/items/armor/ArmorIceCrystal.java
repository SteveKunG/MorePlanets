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
import stevekung.mods.moreplanets.core.items.armor.ItemArmorMP;
import stevekung.mods.moreplanets.planets.kapteynb.items.KapteynBItems;

public class ArmorIceCrystal extends ItemArmorMP
{
    public ArmorIceCrystal(String name, ArmorMaterial par2EnumArmorMaterial, int par3, int par4)
    {
        super(par2EnumArmorMaterial, par3, par4);
        this.setUnlocalizedName(name);
    }

    @Override
    public String getArmorTexture(ItemStack stack, Entity entity, int slot, String type)
    {
        if (stack.getItem() == KapteynBArmorItems.ice_crystal_helmet || stack.getItem() == KapteynBArmorItems.ice_crystal_chestplate || stack.getItem() == KapteynBArmorItems.ice_crystal_boots)
        {
            return "kapteynb:textures/model/armor/ice_crystal_1.png";
        }
        if (stack.getItem() == KapteynBArmorItems.ice_crystal_leggings)
        {
            return "kapteynb:textures/model/armor/ice_crystal_2.png";
        }
        return null;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public boolean hasEffect(ItemStack itemStack, int pass)
    {
        if (!itemStack.isItemEnchanted())
        {
            if (itemStack.getItem() == KapteynBArmorItems.ice_crystal_helmet)
            {
                itemStack.addEnchantment(Enchantment.protection, 3);
                itemStack.addEnchantment(Enchantment.aquaAffinity, 1);
                itemStack.addEnchantment(Enchantment.respiration, 2);
                itemStack.addEnchantment(Enchantment.unbreaking, 3);
            }
            if (itemStack.getItem() == KapteynBArmorItems.ice_crystal_chestplate)
            {
                itemStack.addEnchantment(Enchantment.protection, 4);
                itemStack.addEnchantment(Enchantment.blastProtection, 2);
                itemStack.addEnchantment(Enchantment.thorns, 2);
                itemStack.addEnchantment(Enchantment.unbreaking, 3);
            }
            if (itemStack.getItem() == KapteynBArmorItems.ice_crystal_leggings)
            {
                itemStack.addEnchantment(Enchantment.protection, 3);
                itemStack.addEnchantment(Enchantment.fireProtection, 2);
                itemStack.addEnchantment(Enchantment.projectileProtection, 2);
                itemStack.addEnchantment(Enchantment.unbreaking, 3);
            }
            if (itemStack.getItem() == KapteynBArmorItems.ice_crystal_boots)
            {
                itemStack.addEnchantment(Enchantment.protection, 4);
                itemStack.addEnchantment(Enchantment.fireProtection, 2);
                itemStack.addEnchantment(Enchantment.featherFalling, 4);
                itemStack.addEnchantment(Enchantment.unbreaking, 3);
            }
        }
        return true;
    }

    @Override
    public String getTextureLocation()
    {
        return "kapteynb";
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
}