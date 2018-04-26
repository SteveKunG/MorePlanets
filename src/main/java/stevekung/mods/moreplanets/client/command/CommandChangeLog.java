package stevekung.mods.moreplanets.client.command;

import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.server.MinecraftServer;
import stevekung.mods.moreplanets.client.gui.GuiFullChangeLog;
import stevekung.mods.stevekunglib.utils.ClientCommandBase;

public class CommandChangeLog extends ClientCommandBase
{
    @Override
    public String getName()
    {
        return "mpchangelog";
    }

    @Override
    public void execute(MinecraftServer server, ICommandSender sender, String[] args) throws CommandException
    {
        GuiFullChangeLog gui = new GuiFullChangeLog();
        gui.display();
    }
}