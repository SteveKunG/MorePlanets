/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 *
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.core.items.tools;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import micdoodle8.mods.galacticraft.core.proxy.ClientProxyCore;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemPickaxe;
import net.minecraft.item.ItemStack;
import stevekung.mods.moreplanets.core.MorePlanetsCore;

public class ItemPickaxeMP extends ItemPickaxe
{
    public Item repairItems;
    public int repairItemsMeta;
    public String texture;

    public ItemPickaxeMP(String name, ToolMaterial material, Item item, int meta, String texture)
    {
        super(material);
        this.repairItems = item;
        this.repairItemsMeta = meta;
        this.texture = texture;
        this.setUnlocalizedName(name);
    }

    @Override
    public CreativeTabs getCreativeTab()
    {
        return MorePlanetsCore.mpToolsTab;
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
        this.itemIcon = iconRegister.registerIcon(this.texture);
    }

    /*@Override
    Self Heal Tools code TODO Used for Alien tools
    public void onUpdate(ItemStack itemStack, World world, Entity entity, int p_77663_4_, boolean p_77663_5_)
    {
        if (itemStack.getItemDamage() < itemStack.getMaxDamage())
        {
            if (entity.ticksExisted % 100 == 0)
            {
                int i = itemStack.getItemDamage();
                itemStack.setItemDamage(--i);
            }
        }
    }

    @Override
    public boolean onEntityItemUpdate(EntityItem item)
    {
        ItemStack itemStack = item.getEntityItem();

        if (itemStack.getItemDamage() < itemStack.getMaxDamage())
        {
            if (item.ticksExisted % 100 == 0)
            {
                int i = itemStack.getItemDamage();
                itemStack.setItemDamage(--i);
                item.lifespan = 6000;
                item.worldObj.playAuxSFX(2005, (int)Math.floor(item.posX), (int)Math.floor(item.posY), (int)Math.floor(item.posZ), 0);
            }
        }
        return false;
    }*/

    @Override
    public boolean getIsRepairable(ItemStack itemStack, ItemStack itemStack2)
    {
        if (itemStack2.getItem() == this.repairItems && itemStack2.getItemDamage() == this.repairItemsMeta)
        {
            return true;
        }
        return false;
    }
}