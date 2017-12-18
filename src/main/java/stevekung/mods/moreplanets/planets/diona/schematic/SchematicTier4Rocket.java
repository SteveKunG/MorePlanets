/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 *
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.planets.diona.schematic;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import micdoodle8.mods.galacticraft.api.recipe.ISchematicPage;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.item.ItemStack;
import stevekung.mods.moreplanets.core.config.ConfigManagerMP;
import stevekung.mods.moreplanets.planets.diona.gui.GuiSchematicTier4Rocket;
import stevekung.mods.moreplanets.planets.diona.inventory.container.ContainerSchematicTier4Rocket;
import stevekung.mods.moreplanets.planets.diona.items.DionaItems;

public class SchematicTier4Rocket implements ISchematicPage
{
    @Override
    public int getPageID()
    {
        return ConfigManagerMP.idTier4RocketSchematic;
    }

    @Override
    public int getGuiID()
    {
        return ConfigManagerMP.idTier4RocketSchematicGui;
    }

    @Override
    public ItemStack getRequiredItem()
    {
        return new ItemStack(DionaItems.tier4_rocket_schematic, 1, 0);
    }

    @SideOnly(Side.CLIENT)
    @Override
    public GuiScreen getResultScreen(EntityPlayer player, int x, int y, int z)
    {
        return new GuiSchematicTier4Rocket(player.inventory, x, y, z);
    }

    @Override
    public Container getResultContainer(EntityPlayer player, int x, int y, int z)
    {
        return new ContainerSchematicTier4Rocket(player.inventory, x, y, z);
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