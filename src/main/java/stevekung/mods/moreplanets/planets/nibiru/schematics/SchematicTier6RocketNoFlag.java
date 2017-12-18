/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 *
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.planets.nibiru.schematics;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import micdoodle8.mods.galacticraft.api.recipe.ISchematicPage;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.item.ItemStack;
import stevekung.mods.moreplanets.core.config.ConfigManagerMP;
import stevekung.mods.moreplanets.planets.nibiru.gui.GuiSchematicTier6RocketNoFlag;
import stevekung.mods.moreplanets.planets.nibiru.inventory.container.ContainerSchematicTier6RocketNoFlag;
import stevekung.mods.moreplanets.planets.nibiru.items.NibiruItems;

public class SchematicTier6RocketNoFlag implements ISchematicPage
{
    @Override
    public int getPageID()
    {
        return ConfigManagerMP.idSchematicTier6RocketNoFlag;
    }

    @Override
    public int getGuiID()
    {
        return ConfigManagerMP.idGuiSchematicTier6RocketNoFlag;
    }

    @Override
    public ItemStack getRequiredItem()
    {
        return new ItemStack(NibiruItems.tier6_rocket_schematic, 1, 1);
    }

    @SideOnly(Side.CLIENT)
    @Override
    public GuiScreen getResultScreen(EntityPlayer player, int x, int y, int z)
    {
        return new GuiSchematicTier6RocketNoFlag(player.inventory, x, y, z);
    }

    @Override
    public Container getResultContainer(EntityPlayer player, int x, int y, int z)
    {
        return new ContainerSchematicTier6RocketNoFlag(player.inventory, x, y, z);
    }

    @Override
    public int compareTo(ISchematicPage o)
    {
        if (this.getPageID() > o.getPageID())
        {
            return 1;
        }
        else
        {
            return -1;
        }
    }
}