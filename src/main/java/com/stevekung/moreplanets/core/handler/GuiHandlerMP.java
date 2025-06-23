package com.stevekung.moreplanets.core.handler;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.IGuiHandler;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import com.stevekung.moreplanets.client.gui.*;
import com.stevekung.moreplanets.inventory.*;

import com.stevekung.moreplanets.planets.diona.client.gui.GuiCrashedAlienProbe;
import com.stevekung.moreplanets.planets.diona.client.gui.GuiDarkEnergyGenerator;
import com.stevekung.moreplanets.planets.diona.inventory.ContainerCrashedAlienProbe;
import com.stevekung.moreplanets.planets.diona.inventory.ContainerDarkEnergyGenerator;
import com.stevekung.moreplanets.planets.diona.tileentity.TileEntityCrashedAlienProbe;
import com.stevekung.moreplanets.planets.diona.tileentity.TileEntityDarkEnergyGenerator;
import com.stevekung.moreplanets.planets.nibiru.client.gui.GuiNuclearWasteGenerator;
import com.stevekung.moreplanets.planets.nibiru.inventory.ContainerNuclearWasteGenerator;
import com.stevekung.moreplanets.planets.nibiru.tileentity.TileEntityNuclearWasteGenerator;
import com.stevekung.moreplanets.tileentity.TileEntityBlackHoleStorage;
import com.stevekung.moreplanets.tileentity.TileEntityDarkEnergyReceiver;
import com.stevekung.moreplanets.tileentity.TileEntityShieldGenerator;
import com.stevekung.moreplanets.tileentity.TileEntitySpaceWarpPadFull;
import com.stevekung.moreplanets.utils.tileentity.TileEntityEnergyStorageClusterMP;
import stevekung.mods.stevekunglib.utils.client.ClientUtils;

public class GuiHandlerMP implements IGuiHandler
{
    @Override
    public Object getServerGuiElement(int id, EntityPlayer player, World world, int x, int y, int z)
    {
        TileEntity tile = world.getTileEntity(new BlockPos(x, y, z));

        if (tile == null)
        {
            return null;
        }
        if (tile instanceof TileEntitySpaceWarpPadFull)
        {
            return new ContainerSpaceWarpPad(player.inventory, (TileEntitySpaceWarpPadFull)tile);
        }
        else if (tile instanceof TileEntityDarkEnergyReceiver)
        {
            return new ContainerDarkEnergyReceiver(player.inventory, (TileEntityDarkEnergyReceiver)tile);
        }
        else if (tile instanceof TileEntityNuclearWasteGenerator)
        {
            return new ContainerNuclearWasteGenerator(player.inventory, (TileEntityNuclearWasteGenerator)tile);
        }
        else if (tile instanceof TileEntityEnergyStorageClusterMP)
        {
            return new ContainerEnergyStorageCluster(player.inventory, (TileEntityEnergyStorageClusterMP)tile);
        }
        else if (tile instanceof TileEntityDarkEnergyGenerator)
        {
            return new ContainerDarkEnergyGenerator(player.inventory, (TileEntityDarkEnergyGenerator)tile);
        }
        else if (tile instanceof TileEntityCrashedAlienProbe)
        {
            return new ContainerCrashedAlienProbe(player.inventory, (TileEntityCrashedAlienProbe)tile, player);
        }
        else if (tile instanceof TileEntityBlackHoleStorage)
        {
            return new ContainerBlackHoleStorage(player.inventory, (TileEntityBlackHoleStorage)tile);
        }
        else if (tile instanceof TileEntityShieldGenerator)
        {
            return new ContainerShieldGenerator(player.inventory, (TileEntityShieldGenerator)tile);
        }
        return tile;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public Object getClientGuiElement(int id, EntityPlayer player, World world, int x, int y, int z)
    {
        TileEntity tile = world.getTileEntity(new BlockPos(x, y, z));

        if (!ClientUtils.isClient() || tile == null)
        {
            return null;
        }
        if (tile instanceof TileEntitySpaceWarpPadFull)
        {
            return new GuiSpaceWarpPad(player.inventory, (TileEntitySpaceWarpPadFull)tile);
        }
        else if (tile instanceof TileEntityDarkEnergyReceiver)
        {
            return new GuiDarkEnergyReceiver(player.inventory, (TileEntityDarkEnergyReceiver)tile);
        }
        else if (tile instanceof TileEntityNuclearWasteGenerator)
        {
            return new GuiNuclearWasteGenerator(player.inventory, (TileEntityNuclearWasteGenerator)tile);
        }
        else if (tile instanceof TileEntityEnergyStorageClusterMP)
        {
            return new GuiEnergyStorageClusterMP(player.inventory, (TileEntityEnergyStorageClusterMP)tile);
        }
        else if (tile instanceof TileEntityDarkEnergyGenerator)
        {
            return new GuiDarkEnergyGenerator(player.inventory, (TileEntityDarkEnergyGenerator)tile);
        }
        else if (tile instanceof TileEntityCrashedAlienProbe)
        {
            return new GuiCrashedAlienProbe(player.inventory, (TileEntityCrashedAlienProbe)tile);
        }
        else if (tile instanceof TileEntityBlackHoleStorage)
        {
            return new GuiBlackHoleStorage(player.inventory, (TileEntityBlackHoleStorage)tile);
        }
        else if (tile instanceof TileEntityShieldGenerator)
        {
            return new GuiShieldGenerator(player.inventory, (TileEntityShieldGenerator)tile);
        }
        return tile;
    }
}