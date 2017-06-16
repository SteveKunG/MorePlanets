/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 *
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.core.items.armor;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import micdoodle8.mods.galacticraft.api.item.IBreathableArmor;
import micdoodle8.mods.galacticraft.core.proxy.ClientProxyCore;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import stevekung.mods.moreplanets.core.MorePlanetsCore;

public abstract class ItemBreathableArmor extends ItemArmor implements IBreathableArmor
{
    public ItemBreathableArmor(ArmorMaterial material, int render, int type)
    {
        super(material, render, type);
    }

    @Override
    public CreativeTabs getCreativeTab()
    {
        return MorePlanetsCore.mpArmorTab;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public EnumRarity getRarity(ItemStack itemStack)
    {
        return ClientProxyCore.galacticraftItem;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void registerIcons(IIconRegister iconRegister)
    {
        this.itemIcon = iconRegister.registerIcon(this.getTextureLocation());
    }

    @Override
    public boolean canBreathe(ItemStack itemStack, EntityPlayer player, EnumGearType type)
    {
        if (itemStack.getItem() == this.getBreathableArmor())
        {
            return true;
        }
        return false;
    }

    @Override
    public boolean getIsRepairable(ItemStack itemStack, ItemStack itemStack2)
    {
        if (itemStack2.getItem() == this.getRepairItems() && itemStack2.getItemDamage() == this.getRepairItemsMetadata())
        {
            return true;
        }
        return false;
    }

    @Override
    public boolean handleGearType(EnumGearType type)
    {
        if (type == this.getGearType())
        {
            return true;
        }
        return false;
    }

    public abstract Item getRepairItems();
    public abstract int getRepairItemsMetadata();
    public abstract EnumGearType getGearType();
    public abstract Item getBreathableArmor();
    public abstract String getTextureLocation();
}