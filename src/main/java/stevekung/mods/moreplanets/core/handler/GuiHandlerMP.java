package stevekung.mods.moreplanets.core.handler;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.IGuiHandler;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import stevekung.mods.moreplanets.client.gui.*;
import stevekung.mods.moreplanets.inventory.*;
import stevekung.mods.moreplanets.planets.diona.client.gui.GuiCrashedAlienProbe;
import stevekung.mods.moreplanets.planets.diona.client.gui.GuiDarkEnergyGenerator;
import stevekung.mods.moreplanets.planets.diona.inventory.ContainerCrashedAlienProbe;
import stevekung.mods.moreplanets.planets.diona.inventory.ContainerDarkEnergyGenerator;
import stevekung.mods.moreplanets.planets.diona.tileentity.TileEntityCrashedAlienProbe;
import stevekung.mods.moreplanets.planets.diona.tileentity.TileEntityDarkEnergyGenerator;
import stevekung.mods.moreplanets.planets.nibiru.client.gui.GuiNuclearWasteGenerator;
import stevekung.mods.moreplanets.planets.nibiru.inventory.ContainerNuclearWasteGenerator;
import stevekung.mods.moreplanets.planets.nibiru.tileentity.TileEntityNuclearWasteGenerator;
import stevekung.mods.moreplanets.tileentity.TileEntityBlackHoleStorage;
import stevekung.mods.moreplanets.tileentity.TileEntityDarkEnergyReceiver;
import stevekung.mods.moreplanets.tileentity.TileEntityShieldGenerator;
import stevekung.mods.moreplanets.tileentity.TileEntitySpaceWarpPadFull;
import stevekung.mods.moreplanets.utils.tileentity.TileEntityEnergyStorageClusterMP;
import stevekung.mods.stevekunglib.utils.ClientUtils;

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
            return new GuiDarkEnergyStorage(player.inventory, (TileEntityEnergyStorageClusterMP)tile);
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