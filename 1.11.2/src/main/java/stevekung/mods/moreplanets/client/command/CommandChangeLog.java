package stevekung.mods.moreplanets.client.command;

import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.server.MinecraftServer;
import stevekung.mods.moreplanets.client.gui.GuiFullChangeLog;

public class CommandChangeLog extends CommandBase
{
    @Override
    public String getCommandName()
    {
        return "mpchangelog";
    }

    @Override
    public void execute(MinecraftServer server, ICommandSender sender, String[] args) throws CommandException
    {
        new GuiFullChangeLog().display();
    }

    @Override
    public String getCommandUsage(ICommandSender sender)
    {
        return "/" + this.getCommandName();
    }

    @Override
    public int getRequiredPermissionLevel()
    {
        return 0;
    }
}