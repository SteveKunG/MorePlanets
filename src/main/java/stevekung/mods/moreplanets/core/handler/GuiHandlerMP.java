package stevekung.mods.moreplanets.core.handler;

import micdoodle8.mods.galacticraft.core.util.PlayerUtil;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.IGuiHandler;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import stevekung.mods.moreplanets.client.gui.*;
import stevekung.mods.moreplanets.inventory.*;
import stevekung.mods.moreplanets.module.planets.diona.client.gui.GuiCrashedAlienProbe;
import stevekung.mods.moreplanets.module.planets.diona.client.gui.GuiDarkEnergyGenerator;
import stevekung.mods.moreplanets.module.planets.diona.inventory.ContainerCrashedAlienProbe;
import stevekung.mods.moreplanets.module.planets.diona.inventory.ContainerDarkEnergyGenerator;
import stevekung.mods.moreplanets.module.planets.diona.tileentity.TileEntityCrashedAlienProbe;
import stevekung.mods.moreplanets.module.planets.diona.tileentity.TileEntityDarkEnergyGenerator;
import stevekung.mods.moreplanets.module.planets.nibiru.client.gui.GuiNuclearWasteGenerator;
import stevekung.mods.moreplanets.module.planets.nibiru.inventory.ContainerNuclearWasteGenerator;
import stevekung.mods.moreplanets.module.planets.nibiru.tileentity.TileEntityNuclearWasteGenerator;
import stevekung.mods.moreplanets.tileentity.*;
import stevekung.mods.moreplanets.util.helper.CommonRegisterHelper;

public class GuiHandlerMP implements IGuiHandler
{
    @Override
    public Object getServerGuiElement(int id, EntityPlayer player, World world, int x, int y, int z)
    {
        EntityPlayerMP playerBase = PlayerUtil.getPlayerBaseServerFromPlayer(player, false);
        TileEntity tile = world.getTileEntity(new BlockPos(x, y, z));

        if (playerBase == null)
        {
            return null;
        }
        if (tile != null)
        {
            if (tile instanceof TileEntityRocketCrusher)
            {
                return new ContainerRocketCrusher(player.inventory, (TileEntityRocketCrusher)tile);
            }
            else if (tile instanceof TileEntitySpaceWarpPadFull)
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
            else if (tile instanceof TileEntityDarkEnergyStorageCluster)
            {
                return new ContainerDarkEnergyStorage(player.inventory, (TileEntityDarkEnergyStorageCluster)tile);
            }
            else if (tile instanceof TileEntityNuclearWasteStorageCluster)
            {
                return new ContainerNuclearWasteEnergyStorage(player.inventory, (TileEntityNuclearWasteStorageCluster)tile);
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
        return null;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public Object getClientGuiElement(int id, EntityPlayer player, World world, int x, int y, int z)
    {
        if (CommonRegisterHelper.isClient())
        {
            TileEntity tile = world.getTileEntity(new BlockPos(x, y, z));

            if (tile != null)
            {
                if (tile instanceof TileEntityRocketCrusher)
                {
                    return new GuiRocketCrusher(player.inventory, (TileEntityRocketCrusher)tile);
                }
                else if (tile instanceof TileEntitySpaceWarpPadFull)
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
                else if (tile instanceof TileEntityDarkEnergyStorageCluster)
                {
                    return new GuiDarkEnergyStorage(player.inventory, (TileEntityDarkEnergyStorageCluster)tile);
                }
                else if (tile instanceof TileEntityNuclearWasteStorageCluster)
                {
                    return new GuiNuclearWasteEnergyStorage(player.inventory, (TileEntityNuclearWasteStorageCluster)tile);
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
        return null;
    }
}