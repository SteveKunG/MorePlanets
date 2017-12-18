/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 *
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.planets.nibiru.items;

import java.util.List;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import micdoodle8.mods.galacticraft.api.recipe.ISchematicItem;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.StatCollector;
import stevekung.mods.moreplanets.core.config.ConfigManagerMP;
import stevekung.mods.moreplanets.core.items.ItemBaseMP;

public class ItemTier6Schematic extends ItemBaseMP implements ISchematicItem
{
    public ItemTier6Schematic(String name)
    {
        super();
        this.setMaxStackSize(1);
        this.setUnlocalizedName(name);

        if (ConfigManagerMP.enableRocketWithThaiFlag)
        {
            this.setHasSubtypes(true);
        }
        else
        {
            this.setHasSubtypes(false);
        }
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void getSubItems(Item par1, CreativeTabs par2CreativeTabs, List par3List)
    {
        if (ConfigManagerMP.enableRocketWithThaiFlag)
        {
            for (int i = 0; i < this.getItemVariantsName().length; i++)
            {
                par3List.add(new ItemStack(par1, 1, i));
            }
        }
        else
        {
            for (int i = 0; i < this.getItemVariantsName().length; i++)
            {
                if (i != 0)
                {
                    par3List.add(new ItemStack(par1, 1, i));
                }
            }
        }
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void addInformation(ItemStack itemStack, EntityPlayer player, List list, boolean par4)
    {
        if (player.worldObj.isRemote)
        {
            if (itemStack.getItemDamage() == 0)
            {
                list.add(EnumChatFormatting.GRAY + StatCollector.translateToLocal("schematic.tier6.rocket.name"));
            }
            else if (itemStack.getItemDamage() == 1)
            {
                if (ConfigManagerMP.enableRocketWithThaiFlag)
                {
                    list.add(EnumChatFormatting.GRAY + StatCollector.translateToLocal("schematic.tier6.rocket.noflag.name"));
                }
                else
                {
                    list.add(EnumChatFormatting.GRAY + StatCollector.translateToLocal("schematic.tier6.rocket.name"));
                }
            }
        }
    }

    @Override
    public String[] getItemVariantsName()
    {
        return new String[] { "tier6_schematic_rocket", "tier6_schematic_rocket_no_flag" };
    }

    @Override
    public String getTexturesFolder()
    {
        return "nibiru";
    }
}