/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 *
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.planets.pluto.items;

import micdoodle8.mods.galacticraft.api.item.IArmorGravity;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import stevekung.mods.moreplanets.core.items.armor.ItemArmorMP;

public class ItemGravityBoots extends ItemArmorMP implements IArmorGravity
{
    public ItemGravityBoots(String name, ArmorMaterial material, int render, int type)
    {
        super(material, render, type);
        this.setUnlocalizedName(name);
    }

    @Override
    public String getArmorTexture(ItemStack stack, Entity entity, int slot, String type)
    {
        if (stack.getItem() == PlutoItems.gravity_boots)
        {
            return "pluto:textures/model/armor/gravity_boots.png";
        }
        return null;
    }

    @Override
    public String getTextureLocation()
    {
        return "pluto";
    }

    @Override
    public Item getRepairItems()
    {
        return null;
    }

    @Override
    public int getRepairItemsMetadata()
    {
        return -1;
    }

    @Override
    public int gravityOverrideIfLow(EntityPlayer player)
    {
        return 50;
    }

    @Override
    public int gravityOverrideIfHigh(EntityPlayer player)
    {
        return 75;
    }
}