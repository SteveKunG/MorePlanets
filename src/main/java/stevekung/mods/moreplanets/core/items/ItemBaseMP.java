/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 *
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.core.items;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import micdoodle8.mods.galacticraft.core.proxy.ClientProxyCore;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import stevekung.mods.moreplanets.core.MorePlanetsCore;

public abstract class ItemBaseMP extends Item
{
    private IIcon[] icons = new IIcon[this.getItemVariantsName().length];

    public ItemBaseMP()
    {
        super();
        this.setHasSubtypes(true);
    }

    @Override
    public CreativeTabs getCreativeTab()
    {
        return MorePlanetsCore.mpItemsTab;
    }

    @Override
    public IIcon getIconFromDamage(int meta)
    {
        if (this.icons.length > meta)
        {
            return this.icons[meta];
        }
        return super.getIconFromDamage(meta);
    }

    @Override
    public int getMetadata(int meta)
    {
        return meta;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public EnumRarity getRarity(ItemStack itemStack)
    {
        return ClientProxyCore.galacticraftItem;
    }

    @Override
    public String getUnlocalizedName(ItemStack itemStack)
    {
        if (this.icons.length > itemStack.getItemDamage())
        {
            if (this.reverseName())
            {
                return "item." + this.getItemVariantsName()[itemStack.getItemDamage()] + "." + super.getUnlocalizedName(itemStack).replace("item.", "");
            }
            return super.getUnlocalizedName(itemStack) + "." + this.getItemVariantsName()[itemStack.getItemDamage()];
        }
        return "unnamed";
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void registerIcons(IIconRegister iconRegister)
    {
        int i = 0;

        for (String name : this.getItemVariantsName())
        {
            if (this.getTexturesFolder() == null)
            {
                return;
            }
            this.icons[i++] = iconRegister.registerIcon(this.getTexturesFolder() + ":" + name);
        }
    }

    public abstract String[] getItemVariantsName();
    public abstract String getTexturesFolder();

    public boolean reverseName()
    {
        return false;
    }
}