/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 *
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.planets.kapteynb.items;

import java.util.List;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import micdoodle8.mods.galacticraft.api.recipe.ISchematicItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.StatCollector;
import stevekung.mods.moreplanets.core.items.ItemMorePlanet;

public class ItemTier8Schematic extends ItemMorePlanet implements ISchematicItem
{
    public ItemTier8Schematic(String name)
    {
        super();
        this.setMaxStackSize(1);
        this.setUnlocalizedName(name);
        this.setTextureName("kapteynb:tier8_schematic_rocket");
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void addInformation(ItemStack itemStack, EntityPlayer player, List list, boolean par4)
    {
        if (player.worldObj.isRemote)
        {
            list.add(EnumChatFormatting.GRAY + StatCollector.translateToLocal("schematic.tier8.rocket.name"));
        }
    }
}