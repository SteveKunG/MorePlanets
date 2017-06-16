/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 *
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.core.command;

import micdoodle8.mods.galacticraft.core.util.PlayerUtil;
import micdoodle8.mods.galacticraft.core.util.WorldUtil;
import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
import net.minecraft.command.WrongUsageException;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.StatCollector;
import net.minecraft.world.WorldServer;
import stevekung.mods.moreplanets.core.config.ConfigManagerMP;
import stevekung.mods.moreplanets.core.player.MPPlayerStats;
import stevekung.mods.moreplanets.core.util.WorldUtilMP;

public class CommandHomePlanet extends CommandBase
{
    @Override
    public int getRequiredPermissionLevel()
    {
        return 2;
    }

    @Override
    public boolean canCommandSenderUseCommand(ICommandSender sender)
    {
        return MinecraftServer.getServer().isSinglePlayer() || super.canCommandSenderUseCommand(sender);
    }

    @Override
    public String getCommandUsage(ICommandSender sender)
    {
        return "/" + this.getCommandName();
    }

    @Override
    public String getCommandName()
    {
        return "homeplanettp";
    }

    @Override
    public void processCommand(ICommandSender sender, String[] length)
    {
        EntityPlayerMP playerBase = PlayerUtil.getPlayerBaseServerFromPlayerUsername(sender.getCommandSenderName(), true);
        MPPlayerStats stats = MPPlayerStats.get(playerBase);

        if (length.length < 1)
        {
            if (!stats.usingHomePlanetCommand)
            {
                MinecraftServer server = MinecraftServer.getServer();
                WorldServer worldserver = server.worldServerForDimension(server.worldServers[0].provider.dimensionId);

                if (ConfigManagerMP.homePlanetName == null || ConfigManagerMP.homePlanetName == "planet.")
                {
                    throw new WrongUsageException(StatCollector.translateToLocal("commands.homeplanettp.confignull"));
                }
                else
                {
                    int dimID = WorldUtil.getProviderForNameServer(ConfigManagerMP.homePlanetName).dimensionId;
                    WorldUtilMP.setHomePlanetDimension(playerBase, dimID, worldserver);
                    stats.usingHomePlanetCommand = true;
                    CommandBase.func_152373_a(sender, this, "commands.homeplanettp.success", new Object[] { playerBase.getGameProfile().getName(), StatCollector.translateToLocal(ConfigManagerMP.homePlanetName) });
                }
            }
            else
            {
                throw new WrongUsageException(StatCollector.translateToLocalFormatted("commands.homeplanettp.alreadyuse", playerBase.getGameProfile().getName()));
            }
        }
        else
        {
            throw new WrongUsageException(StatCollector.translateToLocalFormatted("commands.dimensiontp.tooMany", this.getCommandUsage(sender)), new Object[0]);
        }
    }
}