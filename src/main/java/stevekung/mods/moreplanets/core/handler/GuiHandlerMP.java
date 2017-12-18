/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 *
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.core.handler;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.network.IGuiHandler;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import micdoodle8.mods.galacticraft.api.vector.Vector3;
import micdoodle8.mods.galacticraft.core.util.PlayerUtil;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ChatComponentText;
import net.minecraft.world.World;
import stevekung.mods.moreplanets.planets.fronos.gui.GuiCandyExtractor;
import stevekung.mods.moreplanets.planets.fronos.inventory.container.ContainerCandyExtractor;
import stevekung.mods.moreplanets.planets.fronos.tileentities.TileEntityCandyExtractor;
import stevekung.mods.moreplanets.planets.fronos.tileentities.TileEntityMineralWaterGenerator;
import stevekung.mods.moreplanets.planets.nibiru.gui.GuiPowerCrystalGenerator;
import stevekung.mods.moreplanets.planets.nibiru.inventory.container.ContainerPowerCrystalGenerator;
import stevekung.mods.moreplanets.planets.nibiru.tileentities.TileEntityPowerCrystalGenerator;
import stevekung.mods.moreplanets.planets.polongnius.gui.GuiUltraVioletSolarPanel;
import stevekung.mods.moreplanets.planets.polongnius.inventory.container.ContainerUltraVioletSolarPanel;
import stevekung.mods.moreplanets.planets.polongnius.tileentities.TileEntityUltraVioletSolarPanel;

public class GuiHandlerMP implements IGuiHandler
{
    @Override
    public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z)
    {
        EntityPlayerMP playerBase = PlayerUtil.getPlayerBaseServerFromPlayer(player, false);
        TileEntity tile = world.getTileEntity(x, y, z);

        if (playerBase == null)
        {
            player.addChatMessage(new ChatComponentText("More Planet's player instance null server-side. This is a bug."));
            return null;
        }
        if (tile != null)
        {
            if (tile instanceof TileEntityUltraVioletSolarPanel)
            {
                return new ContainerUltraVioletSolarPanel(player.inventory, (TileEntityUltraVioletSolarPanel) tile);
            }
            else if (tile instanceof TileEntityPowerCrystalGenerator)
            {
                return new ContainerPowerCrystalGenerator(player.inventory, (TileEntityPowerCrystalGenerator) tile);
            }
            else if (tile instanceof TileEntityCandyExtractor)
            {
                return new ContainerCandyExtractor(player.inventory, (TileEntityCandyExtractor) tile);
            }
            else if (tile instanceof TileEntityMineralWaterGenerator)
            {
                //return new ContainerMineralWaterGenerator(player.inventory, (TileEntityMineralWaterGenerator) tile);
            }
        }
        return tile;
    }

    @Override
    public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z)
    {
        if (FMLCommonHandler.instance().getEffectiveSide() == Side.CLIENT)
        {
            return this.getClientGuiElement(ID, player, world, new Vector3(x, y, z));
        }
        return null;
    }

    @SideOnly(Side.CLIENT)
    private Object getClientGuiElement(int ID, EntityPlayer player, World world, Vector3 position)
    {
        TileEntity tile = world.getTileEntity(position.intX(), position.intY(), position.intZ());

        if (tile != null)
        {
            if (tile instanceof TileEntityUltraVioletSolarPanel)
            {
                return new GuiUltraVioletSolarPanel(player.inventory, (TileEntityUltraVioletSolarPanel) world.getTileEntity(position.intX(), position.intY(), position.intZ()));
            }
            else if (tile instanceof TileEntityPowerCrystalGenerator)
            {
                return new GuiPowerCrystalGenerator(player.inventory, (TileEntityPowerCrystalGenerator) world.getTileEntity(position.intX(), position.intY(), position.intZ()));
            }
            else if (tile instanceof TileEntityCandyExtractor)
            {
                return new GuiCandyExtractor(player.inventory, (TileEntityCandyExtractor) world.getTileEntity(position.intX(), position.intY(), position.intZ()));
            }
            else if (tile instanceof TileEntityMineralWaterGenerator)
            {
                //return new GuiMineralWaterGenerator(player.inventory, (TileEntityMineralWaterGenerator) world.getTileEntity(position.intX(), position.intY(), position.intZ()));
            }
        }
        return tile;
    }
}