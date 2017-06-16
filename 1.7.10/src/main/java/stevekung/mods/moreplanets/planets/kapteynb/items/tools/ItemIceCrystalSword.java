/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 *
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.planets.kapteynb.items.tools;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import micdoodle8.mods.galacticraft.core.proxy.ClientProxyCore;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import stevekung.mods.moreplanets.core.MorePlanetsCore;
import stevekung.mods.moreplanets.core.init.MPPotions;
import stevekung.mods.moreplanets.planets.kapteynb.items.KapteynBItems;

public class ItemIceCrystalSword extends ItemSword
{
    public ItemIceCrystalSword(String name, ToolMaterial par2EnumToolMaterial)
    {
        super(par2EnumToolMaterial);
        this.setUnlocalizedName(name);
    }

    @Override
    public CreativeTabs getCreativeTab()
    {
        return MorePlanetsCore.mpToolsTab;
    }

    @Override
    public boolean hitEntity(ItemStack stack, EntityLivingBase entity, EntityLivingBase entity2)
    {
        entity.addPotionEffect(new PotionEffect(MPPotions.chemical.id, 20));
        entity.addPotionEffect(new PotionEffect(Potion.moveSlowdown.id, 60));
        entity.addPotionEffect(new PotionEffect(MPPotions.icy_poison.id, 80));
        stack.damageItem(1, entity2);
        return true;
    }

    @Override
    public EnumRarity getRarity(ItemStack par1ItemStack)
    {
        return ClientProxyCore.galacticraftItem;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public boolean hasEffect(ItemStack itemStack, int pass)
    {
        if (!itemStack.isItemEnchanted())
        {
            itemStack.addEnchantment(Enchantment.sharpness, 5);
            itemStack.addEnchantment(Enchantment.looting, 2);
            itemStack.addEnchantment(Enchantment.fireAspect, 2);
            itemStack.addEnchantment(Enchantment.unbreaking, 3);
        }
        return true;
    }

    @Override
    public void registerIcons(IIconRegister par1IconRegister)
    {
        this.itemIcon = par1IconRegister.registerIcon(this.getUnlocalizedName().replace("item.", "kapteynb:"));
    }

    @Override
    public boolean getIsRepairable(ItemStack par1ItemStack, ItemStack par2ItemStack)
    {
        if (par2ItemStack.getItem() == KapteynBItems.kapteyn_b_item && par2ItemStack.getItemDamage() == 5)
        {
            return true;
        }
        return false;
    }
}