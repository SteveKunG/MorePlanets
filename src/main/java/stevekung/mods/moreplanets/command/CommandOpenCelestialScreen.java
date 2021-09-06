package stevekung.mods.moreplanets.command;

import micdoodle8.mods.galacticraft.core.GalacticraftCore;
import micdoodle8.mods.galacticraft.core.util.PlayerUtil;
import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.text.Style;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.util.text.TextFormatting;
import stevekung.mods.moreplanets.core.event.WorldTickEventHandler;
import stevekung.mods.moreplanets.network.PacketSimpleMP;
import stevekung.mods.moreplanets.network.PacketSimpleMP.EnumSimplePacketMP;
import stevekung.mods.moreplanets.utils.itemblocks.IItemRarity;
import stevekung.mods.stevekunglib.utils.ColorUtils;

public class CommandOpenCelestialScreen extends CommandBase
{
    @Override
    public String getUsage(ICommandSender sender)
    {
        return "/" + this.getName();
    }

    @Override
    public int getRequiredPermissionLevel()
    {
        return 0;
    }

    @Override
    public String getName()
    {
        return "mpcelestial";
    }

    @Override
    public void execute(MinecraftServer server, ICommandSender sender, String[] args) throws CommandException
    {
        if (args.length == 1 && args[0].equals("disable"))
        {
            if (!WorldTickEventHandler.survivalPlanetData.disableMessage)
            {
                WorldTickEventHandler.survivalPlanetData.disableMessage = true;
                sender.sendMessage(new TextComponentString(ColorUtils.stringToRGB(IItemRarity.ALIEN).toColoredFont() + "[More Planets] ").appendSibling(new TextComponentTranslation("command.mpcelestial.1").setStyle(new Style().setColor(TextFormatting.YELLOW))));
            }
        }
        else
        {
            if (!WorldTickEventHandler.survivalPlanetData.hasSurvivalPlanetData)
            {
                EntityPlayerMP player = PlayerUtil.getPlayerBaseServerFromPlayerUsername(sender.getName(), true);
                GalacticraftCore.packetPipeline.sendTo(new PacketSimpleMP(EnumSimplePacketMP.C_OPEN_SURVIVAL_PLANET_GUI, player.dimension), player);
            }
            else
            {
                sender.sendMessage(new TextComponentString(ColorUtils.stringToRGB(IItemRarity.ALIEN).toColoredFont() + "[More Planets] ").appendSibling(new TextComponentTranslation("command.mpcelestial.2").setStyle(new Style().setColor(TextFormatting.RED))));
            }
        }
    }
}